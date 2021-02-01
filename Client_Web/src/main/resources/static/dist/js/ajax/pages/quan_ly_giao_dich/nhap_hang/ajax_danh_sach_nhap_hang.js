var inputMaNhapHang, selectNhaCungCap, inputTuNgay, inputDenNgay, btnTimKiem, tableData;

$(function(){
    inputMaNhapHang = $("#bimo1");
    selectNhaCungCap = $("#bimo2");
    inputTuNgay = $("#bimo3");
    inputDenNgay = $("#bimo4");
    btnTimKiem = $("#btn-search");
    tableData = $("tbody");

    viewSelectNhaCungCap(selectNhaCungCap);

    clickSearchPhieuNhapHang();
    searchDanhSachNhapHang("",null, null, 0);

})

function clickSearchPhieuNhapHang() {
    btnTimKiem.click(function () {
        let maPhieuNhap = inputMaNhapHang.val();
        let nhaCungCap = selectNhaCungCap.val();
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if(check) {
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            searchDanhSachNhapHang(maPhieuNhap,valTuNgay, valDenNgay,nhaCungCap ,1, 10);
        }
    })
}

function searchDanhSachNhapHang(maPhieuNhap,valTuNgay, valDenNgay,nhaCungCap ,page = 1, size = 10) {
    phieuNhapHangSearch(maPhieuNhap, valTuNgay, valDenNgay, nhaCungCap, page).then(rs => {
        if(rs.message === "found") {
            $('#pagination').pagination({
                dataSource: function (done) {
                    let result = [];
                    for (let i = 1; i <= rs.data.totalPages; i++) result.push(1);
                    done(result);
                },
                locator: 'items',
                totalNumber: rs.data.totalPages,
                pageSize: 1,
                showPageNumbers: true,
                showPrevious: true,
                showNext: true,
                // showNavigator: true,
                showFirstOnEllipsisShow: true,
                showLastOnEllipsisShow: true,
                callback: function (response, pagination) {
                    if (pagination.pageNumber == 1) {
                        setViewDanhSachNhapHang( rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    phieuNhapHangSearch(maPhieuNhap, valTuNgay, valDenNgay, nhaCungCap, pagination.pageNumber).then(rs => {
                        setViewDanhSachNhapHang(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        }
    }).catch(err => {
        alterDanger("Server search error danh sach nhap hang", TIME_ALTER);
        console.log(err);
    })
}

function setViewDanhSachNhapHang(list, pageNumber) {
    let view = `<tr>
                <th>STT</th>
                <th>Mã nhập hàng</th>
                <th>Thời gian</th>
                <th>Nhà cung cấp</th>
                <th>Cần trả NCC</th>
                <th>Trạng thái</th>
            </tr>`;
    let len = list.length;
    if (len > 0) {
        view += list.map((data, index) => `<tr>
                <td>${(pageNumber - 1)*10 + index + 1}</td>
                <td><a href="nhap-hang-chi-tiet?id=${data.id}" target="_blank">${viewField(data.maPhieu)}</a></td>
                <td>${viewDateTime(data.thoiGian)}</td>
                <td>${data.nhaCungCap == null ? "" : viewField(data.nhaCungCap.ten)}</td>
                <td>${viewField(data.tienPhaiTra)}</td>
                <td>${viewTrangThaiPhieuNhap(data)}</td>
            </tr>`).join("");
        if(len < 10) {
            len++;
            for (let i = len; i <= 10; i++) {
                view += `<tr><td>${(pageNumber - 1)*10 + i}</td><td></td><td></td><td></td><td></td><td></td></tr>`
            }
        }
    } else {
        view += `<tr><td colspan="6">Không có thông tin phù hợp</td></tr>`
    }
    tableData.html(view);
}

function checkThoiGianTHDN() {
    let rs = true;
    let {check: checkTuNgay, val: valTuNgay} = checkNgayTKDSDN(inputTuNgay);
    let {check: checkDenNgay, val: valDenNgay} = checkNgayTKDSDN(inputDenNgay);
    let selectorDenNgay = inputDenNgay.parents(".form-group");
    if (valTuNgay !== "" && valDenNgay !== "" && !compareDate(valTuNgay, valDenNgay)) {
        rs = false;
        viewError(selectorDenNgay, "Thời gian giới hạn phải lớn hơn");
    } else hiddenError(selectorDenNgay);
    return {check: rs, valTuNgay: valTuNgay, valDenNgay: valDenNgay};
}

function checkNgayTKDSDN(selectorNgay) {
    let rs = false;
    let val = selectorNgay.val();
    let selector = selectorNgay.parents(".form-group");
    if (val === '' || regexDate(val)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector, "Chưa đúng định dạng ngày");
    return {check: rs, val: val};
}