package com.tavi.tavi_mrs.utils;

import com.tavi.tavi_mrs.entities.hoa_don.HoaDon;
import com.tavi.tavi_mrs.entities.json.TrangThai;
import com.tavi.tavi_mrs.entities.json.TrangThaiNguoiDung;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {


    public static Object getCellValue(Cell cell) {
        if (cell != null) {
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                case BOOLEAN:
                    return cell.getBooleanCellValue();
                case NUMERIC:
                    return (int) cell.getNumericCellValue();
                case STRING:
                    return cell.getStringCellValue();
                default:
                    return null;
            }
        } else return "";
    }

    public static Iterator<Row> getRows(String url, int sheetIndex) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(url));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet.iterator();
    }

    public static Iterator<Row> getRows(File file, int sheetIndex) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet.iterator();
    }

    public static XSSFWorkbook createListBillExcel(List<HoaDon> billList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Bill Information");
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        //Write Title
        {
            Row row = sheet.createRow(0);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue("STT");
            cellSTT.setCellStyle(style);
            //Mã hoa don
            Cell cellMHD = row.createCell(1, CellType.STRING);
            cellMHD.setCellValue("Code");
            cellMHD.setCellStyle(style);
            //Thoi gian tao
            Cell cellTTK = row.createCell(2, CellType.STRING);
            cellTTK.setCellValue("Created Time");
            cellTTK.setCellStyle(style);
            //Ten khach hang
            Cell cellTT = row.createCell(3, CellType.STRING);
            cellTT.setCellValue("Employee name");
            cellTT.setCellStyle(style);
            //ten to chuc
            Cell cellCN = row.createCell(4, CellType.STRING);
            cellTT.setCellValue("Customer name");
            cellTT.setCellStyle(style);
            //So dien thoai khach hang
            Cell cellHT = row.createCell(5, CellType.STRING);
            cellHT.setCellValue("Total money");
            cellHT.setCellStyle(style);
            //Dia chi
            Cell cellEmail = row.createCell(6, CellType.STRING);
            cellEmail.setCellValue("Customer pay money");
            cellEmail.setCellStyle(style);

            Cell cellpay = row.createCell(7, CellType.STRING);
            cellpay.setCellValue("Customer payed money");
            cellpay.setCellStyle(style);
            //Trang Thai
            Cell cellSDT = row.createCell(8, CellType.STRING);
            cellSDT.setCellValue("Status");
            cellSDT.setCellStyle(style);
            //Note bill
            Cell cellDC = row.createCell(9, CellType.STRING);
            cellDC.setCellValue("Note bill");
            cellDC.setCellStyle(style);

        }
        //Write data
        int rowNum = 1;
        for (HoaDon bill : billList) {
            Row row = sheet.createRow(rowNum++);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue(rowNum - 1);

            Cell cellMTK = row.createCell(1, CellType.STRING);
            cellMTK.setCellValue(bill.getMa());

            Cell cellTTK = row.createCell(2, CellType.STRING);
            cellTTK.setCellValue(bill.getThoiGian());

            Cell cellTT = row.createCell(3, CellType.STRING);
            cellTT.setCellValue(bill.getNguoiDung().getHoVaTen());

            Cell cellCN = row.createCell(4, CellType.STRING);
            cellTT.setCellValue(bill.getKhachHang().getTenKhachHang());

            Cell cellHT = row.createCell(5, CellType.STRING);
            cellHT.setCellValue(bill.getTongTien());

            Cell cellEmail = row.createCell(6, CellType.STRING);
            try {
                cellEmail.setCellValue(bill.getTienKhachTra());
            }catch (NullPointerException ex){
                cellEmail.setCellValue("");
            }
            Cell cellSDT = row.createCell(7, CellType.STRING);
            try{
                cellSDT.setCellValue(bill.getTienTraLaiKhach());
            }catch (NullPointerException ex){
                cellSDT.setCellValue("");
            }

            Cell cellDC = row.createCell(8, CellType.STRING);
            try {
                cellDC.setCellValue(TrangThai.trangThai.get(bill.getTrangThai()));
            }catch (NullPointerException ex){
                cellDC.setCellValue("");
            }

            Cell cellGT = row.createCell(9, CellType.STRING);
            try {
                cellGT.setCellValue(bill.getGhiChu());
            }catch (NullPointerException ex){
                cellGT.setCellValue("");
            }

        }
        return workbook;
    }

    public static XSSFWorkbook createDanhSachTaiKhoanExcel(List<NguoiDung> nguoiDungsList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Thông tin tài khoản");
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        //Write Title
        {
            Row row = sheet.createRow(0);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue("STT");
            cellSTT.setCellStyle(style);
            //Mã tài khoản
            Cell cellMTK = row.createCell(1, CellType.STRING);
            cellMTK.setCellValue("Mã tài khoản");
            cellMTK.setCellStyle(style);
            //Tên tài khoản
            Cell cellTTK = row.createCell(2, CellType.STRING);
            cellTTK.setCellValue("Tên tài khoản");
            cellTTK.setCellStyle(style);
            //Trạng thái
            Cell cellTT = row.createCell(3, CellType.STRING);
            cellTT.setCellValue("Trạng thái");
            cellTT.setCellStyle(style);
            //Họ và tên
            Cell cellHT = row.createCell(4, CellType.STRING);
            cellHT.setCellValue("Họ và tên");
            cellHT.setCellStyle(style);
            //Email
            Cell cellEmail = row.createCell(5, CellType.STRING);
            cellEmail.setCellValue("Email");
            cellEmail.setCellStyle(style);
            //Số điện thoại
            Cell cellSDT = row.createCell(6, CellType.STRING);
            cellSDT.setCellValue("Số điện thoại");
            cellSDT.setCellStyle(style);
            //Địa chỉ
            Cell cellDC = row.createCell(7, CellType.STRING);
            cellDC.setCellValue("Địa chỉ");
            cellDC.setCellStyle(style);
            //Giới tính
            Cell cellGT = row.createCell(8, CellType.STRING);
            cellGT.setCellValue("Giới tính");
            cellGT.setCellStyle(style);
            //Ngày sinh
            Cell cellNS = row.createCell(9, CellType.STRING);
            cellNS.setCellValue("Ngày sinh");
            cellNS.setCellStyle(style);
            //Thời gian khởi tạo
            Cell cellTGKT = row.createCell(10, CellType.STRING);
            cellTGKT.setCellValue("Thời gian khởi tạo");
            cellTGKT.setCellStyle(style);
            //Thời gian hết hạn
            Cell cellTGHH = row.createCell(11, CellType.STRING);
            cellTGHH.setCellValue("Thời gian hết hạn");
            cellTGHH.setCellStyle(style);
//            //File ảnh đại diện
//            Cell cellAVT = row.createCell(12, CellType.STRING);
//            cellAVT.setCellStyle(style);
//            cellAVT.setCellValue("Ảnh đại diện");
        }
        //Write data
        int rowNum = 1;
        for (NguoiDung nguoiDung : nguoiDungsList) {
            Row row = sheet.createRow(rowNum++);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue(rowNum - 1);
            //Mã tài khoản
            Cell cellMTK = row.createCell(1, CellType.STRING);
            cellMTK.setCellValue(nguoiDung.getMaTaiKhoan());
            //Tên tài khoản
            Cell cellTTK = row.createCell(2, CellType.STRING);
            cellTTK.setCellValue(nguoiDung.getTaiKhoan());
            //Trạng thái
            Cell cellTT = row.createCell(3, CellType.STRING);
            cellTT.setCellValue(TrangThaiNguoiDung.trangThai.get(nguoiDung.getTrangThai()));
            //Họ và tên
            Cell cellHT = row.createCell(4, CellType.STRING);
            cellHT.setCellValue(nguoiDung.getHoVaTen());
            //Email
            Cell cellEmail = row.createCell(5, CellType.STRING);
            cellEmail.setCellValue(nguoiDung.getEmail());
            //Số điện thoại
            Cell cellSDT = row.createCell(6, CellType.STRING);
            cellSDT.setCellValue(nguoiDung.getSoDienThoai());
            //Địa chỉ
            Cell cellDC = row.createCell(7, CellType.STRING);
            cellDC.setCellValue(nguoiDung.getDiaChi());
            //Giới tính
            Cell cellGT = row.createCell(8, CellType.STRING);
            cellGT.setCellValue(nguoiDung.getGioiTinh());
            //Ngày sinh
            Cell cellNS = row.createCell(9, CellType.STRING);
            cellNS.setCellValue(nguoiDung.getNgaySinh());
            //Thời gian khởi tạo
            Cell cellTGKT = row.createCell(10, CellType.STRING);
            cellTGKT.setCellValue(nguoiDung.getThoiGianKhoiTao());
            //Thời gian hết hạn
            Cell cellTGHH = row.createCell(11, CellType.STRING);
            cellTGHH.setCellValue(nguoiDung.getThoiGianHetHan());
//            //File ảnh đại diện
//            Cell cellAVT = row.createCell(12, CellType.STRING);
//            cellAVT.setCellValue(nguoiDung.getUrlAnhDaiDien());
        }
        return workbook;
    }


}
