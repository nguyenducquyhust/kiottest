package com.tavi.tavi_mrs.service.hoa_don;

import com.tavi.tavi_mrs.entities.hoa_don.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface HoaDonService {


    Optional<HoaDon> findById(int id);

    Optional<HoaDon> findById(int id , boolean xoa);

    Page<HoaDon> findAllToPage(Pageable pageable);

    Optional<HoaDon> save(HoaDon hoaDon);

    boolean setTrangThai(int id, int trangThai);

    Page<HoaDon> findByMaHoaDonAndThoiGianAndTrangThai(String maHoaDon,String tenKhachHang, String tenNhanVien, Date ngayDau, Date ngayCuoi, int trangThai, Pageable pageable);

    Page<HoaDon> findByChiNhanhAndText(int chiNhanhId, String text, Pageable pageable);

}
