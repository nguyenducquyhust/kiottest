
async function searchGiaBan(chiNhanhId = 0,text = "",page = 1, size = 8) {
    return ajaxGet(`v1/admin/lich-su-gia-ban/search?chi-nhanh-id=${chiNhanhId}&text=${text}&page=${page}&size=${size}`);
}

async function findByDonViHangHoaId(donViHangHoaId,page = 1, size = 5) {
    return ajaxGet(`v1/admin/lich-su-gia-ban/find-by-don-vi-hang-hoa-id?donViHangHoaId=${donViHangHoaId}&page=${page}&size=${size}`);
}

async function searchHH(tenHangHoa = "", nhomHangId= 0, thuongHieuId=0, page = 1, size = 5) {
    return ajaxGet(`v1/admin/hang-hoa/search?ten-hang-hoa=${tenHangHoa}&nhom-hang-id=${nhomHangId}&thuong-hieu-id=${thuongHieuId}&page=${page}&size=${size}`);
}

async function saveGiaBan(donViHangHoaId = 0, lichSuGiaBan){
    return ajaxPost(`v1/admin/lich-su-gia-ban/upload?don-vi-hang-hoa-id=${donViHangHoaId}`, lichSuGiaBan);
}

async function saveDonViHangHoa(donViHangHoa,hangHoaId,donViId) {
    return ajaxPost(`v1/admin/don-vi-hang-hoa/upload?hang-hoa-id=${hangHoaId}&don-vi-id=${donViId}`,donViHangHoa);
}


