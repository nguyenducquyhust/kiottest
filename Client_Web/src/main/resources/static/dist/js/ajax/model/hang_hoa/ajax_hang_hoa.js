async function uploadHangHoa(hangHoa, thuongHieuId, nhomHangId) {
    return ajaxPost(`v1/admin/hang-hoa/upload?thuong-hieu-id=${thuongHieuId}&nhom-hang-id=${nhomHangId}`, hangHoa, 1);
}

async function hangHoaFindAll() {
    return ajaxGet(`v1/admin/hang-hoa/find-all?size=9999`);
}

async function viewSelectHangHoa(selector, all = true) {
    let view = all ? `<option value="0">Tất cả</option>` : "";
    await hangHoaFindAll().then(rs => {
        if(rs.message == "found") {
            rs = rs.data.currentElements;
            view += rs.map(data => `<option value="${data.id}">${viewField(data.ma)} - ${viewField(data.tenHangHoa)}</option>`).join("");
        }
    }).catch(err => {
        alterDanger("Server error find all hang hoa", TIME_ALTER);
        console.log(err);
    })
    selector.html(view);
}