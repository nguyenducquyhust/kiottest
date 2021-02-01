async function nhanVienFindAll() {
    return ajaxGet(`v1/admin/nhan-vien/find-all?size=9999`);
}

async function viewSelectNhanVien(selector, all = true) {
    let view = all ? `<option value="0">Tất cả</option>` : "";
    await nhanVienFindAll().then(rs => {
        if(rs.message == "found") {
            rs = rs.data.currentElements;
            view += rs.map(data => `<option value="${data.id}">${viewField(data.maTaiKhoan)} - ${viewField(data.hoVaTen)}</option>`).join("");
        }
    }).catch(err => {
        alterDanger("Server error find all nhan vien", TIME_ALTER);
        console.log(err);
    })
    selector.html(view);
}