/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tavi.tavi_mrs.entities.hang_hoa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Hang_Hoa", schema = "dbo")
public class HangHoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @NotNull
    @Column(name = "ten_hang_hoa")
    private String tenHangHoa;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "tich_diem")
    private Integer tichDiem;

    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "phan_tram_giam_gia")
    private Float phanTramGiamGia;

    @Column(name = "url_hinh_anh_1")
    private String urlHinhAnh1;

    @Column(name = "url_hinh_anh_2")
    private String urlHinhAnh2;

    @Column(name = "url_hinh_anh_3")
    private String urlHinhAnh3;

    @Column(name = "url_hinh_anh_4")
    private String urlHinhAnh4;

    @Column(name = "url_hinh_anh_5")
    private String urlHinhAnh5;

    @Column(name = "xoa")
    private Boolean xoa;
}
