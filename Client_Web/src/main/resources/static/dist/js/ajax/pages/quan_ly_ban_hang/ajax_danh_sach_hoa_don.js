var arr  = [];
var inputMaHoaDon, inputTenKhachHang, inputTenNhanVien, inputTuNgay,    inputDenNgay, btnTimKiem, selectTrangThai;

const TRANG_THAI_PHIEU_NHAP_DE_TRA = ["Đang Chờ", "Đã thanh toán"];

function viewTrangThai(hoaDon) {
    return TRANG_THAI_PHIEU_NHAP_DE_TRA[hoaDon.trangThai];
}

function viewThoiGian(thoiGian){
    var tg = thoiGian.substring(11,19) + " " + thoiGian.substring(8,10) +"/"
        + thoiGian.substring(5,7) + "/" + thoiGian.substring(0,4);
    return tg;
}

$(function () {
    table=$("#table-hoa-don");
    inputMaHoaDon = $("#bimo1");
    inputTenKhachHang = $("#bimo2");
    inputTenNhanVien = $("#bimo3");
    selectTrangThai = $("#bimo4");
    inputTuNgay = $("#bimo5");
    inputDenNgay = $("#bimo6");
    btnTimKiem = $("#btn-search");
    callSearchHoaDon("","","",null,null,-1,1,10);
    clickSearchPhieuNhapHang();
    checkThoiGianTHDN();
    taiExcelTK();
});

function clickSearchPhieuNhapHang() {
    btnTimKiem.click(function () {
        let maHoaDon = inputMaHoaDon.val();
        let tenKhachHang = inputTenKhachHang.val();
        let tenNhanVien = inputTenNhanVien.val();
        let trangThai = selectTrangThai.val();
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if(check) {
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            callSearchHoaDon(maHoaDon,tenKhachHang,tenNhanVien,valTuNgay, valDenNgay, trangThai ,1, 10);
        }
    });
}

function callSearchHoaDon(maHoaDon,tenKhachHang,tenNhanVien,valTuNgay, valDenNgay, trangThai,page=1,size=10) {
    searchHoaDon(maHoaDon,tenKhachHang,tenNhanVien,valTuNgay, valDenNgay, trangThai).then(rs => {
        if (rs.message === "found") {
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
                        arr = rs.data.currentElements;
                        setViewHoaDon(1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    searchHoaDon(maHoaDon,tenKhachHang,tenNhanVien,valTuNgay, valDenNgay, trangThai, pagination.pageNumber,10).then(rs => {
                        arr = rs.data.currentElements;
                        setViewHoaDon(pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            arr = [];
            setViewHoaDon(1);
            paginationReset();
        }
    }).catch(err => {
        console.log(err);
        alterDanger("Server Error - Không tìm thấy hóa đơn");
        setViewHoaDon(1);
    })
}

function setViewHoaDon(pageNumber) {
    let view = `<tr>
                <th>STT</th>
                <th>Mã hóa đơn</th>
                <th>Thời gian</th>
                <th>Nhân viên</th>
                <th>Khách hàng</th>
                <th>Tổng tiền</th>
                <th>Tiền khách trả</th>
                <th>Tiền trả lại khách</th>
                <th>Trạng thái</th>
                </tr>`;
    let len = arr.length;
    if (len > 0) {
        view += arr.map((item, index) => `<tr data-index="${index}" class="click-thuong-hieu">
                    <td data-id="${viewField(item.id)}">${(pageNumber - 1)*10 + index + 1}</td>
                    <td><a href="hoa-don-chi-tiet?id=${item.id}" target="_blank">${viewField(item.ma)}</a></td>
                    <td>${viewThoiGian(item.thoiGian)}</td>
                    <td>${viewField(item.nguoiDung.hoVaTen)}</td>
                    <td>${viewField(item.khachHang.tenKhachHang)}</td>
                    <td>${viewField(item.tongTien)}</td>
                    <td>${viewField(item.tienKhachTra)}</td>
                    <td>${viewField(item.tienTraLaiKhach)}</td>
                    <td>${viewTrangThai(item)}</td>
                </tr>`);
        if(len < 10) {
            len++;
            for (let i = len; i <= 10; i++) {
                view += `<tr><td>${(pageNumber - 1)*10 + i}</td>  <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>`
            }
        }
    } else {
        view += `<tr><td colspan="9">Không có thông tin phù hợp</td></tr>`
    }
    table.html(view);
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

//tai excel va in thong tin hoa don
function taiExcelTK() {
    $('#btn-excel').on('click', function () {
        console.log("inds");
        ajaxGet('v1/admin/hoa-don/excel?list-hoa-don=' + arr.map(hoaDon => hoaDon.id))
            .then(rs => {
                window.open(rs.data, '_blank');
            }).catch(ex => {
            console.log(ex);
            alterDanger("Tạo file excel thất bại");
        })
    });
    clickPrintElement(".ttcttk");
}

