

async function searchHoaDon(maHoaDon="",tenKhachHang="",tenNhanVien="", ngayDau=null, ngayCuoi=null, trangThai=-1,page=1,size=10) {
    return ajaxGet(`v1/admin/hoa-don/search?ma-hoa-don=${maHoaDon}&ten-khach-hang=${tenKhachHang}&ten-nhan-vien=${tenNhanVien}${ngayDau == null ? "" : `&ngay-dau=${ngayDau}`}${ngayCuoi == null ? "" : `&ngay-cuoi=${ngayCuoi}`}&trang-thai=${trangThai}&page=${page}&size${size}`);

}