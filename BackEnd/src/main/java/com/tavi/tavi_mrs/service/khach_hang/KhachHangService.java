package com.tavi.tavi_mrs.service.khach_hang;

import com.tavi.tavi_mrs.entities.khach_hang.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {

    List<KhachHang> findAll();

    Optional<KhachHang> findByIdAndXoa(int id, boolean xoa);

    Page<KhachHang> findByTenKhachHangAndDienThoaiAndEmail(String tenKhachHang, String dienThoai, String email, String facebook, String diaChi, Pageable pageable);

    Page<KhachHang> findAll(Pageable pageable);

    Optional<KhachHang> save(KhachHang khachHang);

    Page<KhachHang> search(String text, Pageable pageable);


    Boolean deleted(int id);
}
