package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class XuatHoaDonPDF {
	private HoaDon_DAO hoaDonDAO;
	private NhanVien_DAO nhanVienDAO;
	private KhachHang_DAO khachHangDAO;
	private ChiTietHoaDon_DAO chiTietHoaDonDAO;
	private List<ChiTietHoaDon> listChiTietHoaDon;

	public XuatHoaDonPDF(String chuoi) {
		HoaDon hoaDon = new HoaDon();
		KhachHang khachHang = new KhachHang();
		NhanVien nhanVien = new NhanVien();
		DecimalFormat df = new DecimalFormat("###,###,###.#");

		hoaDonDAO = new HoaDon_DAO();
		nhanVienDAO = new NhanVien_DAO();
		khachHangDAO = new KhachHang_DAO();
		chiTietHoaDonDAO = new ChiTietHoaDon_DAO();

		String split[] = chuoi.split("-");
		String maHoaDon = split[0].trim();
		String tienPhong = split[1].trim();
		String tienDichVu = split[2].trim();
		String tongTien = split[3].trim();
		String thueVAT = split[4].trim();
		String tienThua = split[5].trim();

		hoaDon = hoaDonDAO.getAllHoaDonByMaHoaDon(maHoaDon);

		try {
			khachHang = khachHangDAO.getKhachHangByMaKhachHang(hoaDon.getKhachHang().getMaKhachHang());
			nhanVien = nhanVienDAO.getNhanVienByMaNhanVien(hoaDon.getNhanVien().getMaNhanVien());
			listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDonByMaHoaDon(hoaDon.getMaHoaDon());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = new Document();
		try {
			BaseFont bf1 = BaseFont.createFont("font/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font f1 = new Font(bf1, 10);
			Font f2 = new Font(bf1, 13);

			PdfWriter write = PdfWriter.getInstance(document,
					new FileOutputStream("D:/hoadon/" + maHoaDon + "_" + khachHang.getHoTenKhachHang() + ".pdf"));

			document.open();
			Paragraph p1 = new Paragraph("QUÁN KARAOKE NICE", f2);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph p2 = new Paragraph("PHIẾU THANH TOÁN HÓA ĐƠN", f2);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setSpacingAfter(20f);
			document.add(p1);
			document.add(p2);
			document.add(new Paragraph(String.format("Tên quán:%12sQUÁN KARAOKE NICE", ""), f1));
			document.add(new Paragraph(
					String.format("Địa chỉ:%18s12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, TP.HCM", ""), f1));
			document.add(new Paragraph("\n"));
			LocalTime time = LocalTime.now();
			String s1 = String.format(
					"Mã hóa đơn:%7s" + maHoaDon + "%20s" + "Nhân viên lập:%3s" + nhanVien.getHoTenNhanVien(), "", "",
					"");
			String s2 = String.format("Ngày in:%14s" + formatDateTime(new Timestamp(System.currentTimeMillis())), "", "", "", "",
					time.getHour(), +time.getMinute(), +time.getSecond());
			String s3 = String.format(
					"Mã khách hàng:%6s" + khachHang.getMaKhachHang() + "%16s" + "Tên khách hàng:%3s"
							+ khachHang.getHoTenKhachHang() + "%16s" + "SĐT:%3s" + khachHang.getSoDienThoai(),
					"", "", "", "", "");
			document.add(new Paragraph(s1, f1));
			document.add(new Paragraph(s2, f1));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph(s3, f1));

			PdfPTable pdftable = new PdfPTable(6);
			/**
			 * for table
			 */
			pdftable.setWidthPercentage(100);
			pdftable.setSpacingBefore(5f);
			pdftable.setSpacingAfter(20f);

			float[] colwidth = { 0.75f, 1.5f, 2f, 2f, 1.5f, 2f };
			pdftable.setWidths(colwidth);

			PdfPCell c0 = new PdfPCell(new Paragraph("STT", f1));
			PdfPCell c1 = new PdfPCell(new Paragraph("Mã dịch vụ", f1));
			PdfPCell c2 = new PdfPCell(new Paragraph("Tên dịch vụ", f1));
			PdfPCell c3 = new PdfPCell(new Paragraph("Số lượng", f1));
			PdfPCell c4 = new PdfPCell(new Paragraph("Đơn giá (VNĐ)", f1));
			PdfPCell c5 = new PdfPCell(new Paragraph("Thành tiền (VNĐ)", f1));

			c0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			c0.setFixedHeight(30);
			c0.setBorderWidthRight(0f);
			c1.setBorderWidthRight(0f);
			c2.setBorderWidthRight(0f);
			c3.setBorderWidthRight(0f);
			c4.setBorderWidthRight(0f);
			c5.setBorderWidthRight(0.5f);

			pdftable.addCell(c0);
			pdftable.addCell(c1);
			pdftable.addCell(c2);
			pdftable.addCell(c3);
			pdftable.addCell(c4);
			pdftable.addCell(c5);

			int i = 1;
			for (ChiTietHoaDon cthd : listChiTietHoaDon) {
				PdfPCell c6 = new PdfPCell(new Paragraph(i++ + "", f1));
				PdfPCell c7 = new PdfPCell(new Paragraph(cthd.getDichVu().getMaDichVu(), f1));
				PdfPCell c8 = new PdfPCell(new Paragraph(cthd.getDichVu().getTenDichVu(), f1));
				PdfPCell c9 = new PdfPCell(new Paragraph(cthd.getSoLuong() + "", f1));
				PdfPCell c10 = new PdfPCell(new Paragraph(df.format(cthd.getDonGia()), f1));
				PdfPCell c11 = new PdfPCell(new Paragraph(df.format(cthd.getSoLuong() * cthd.getDonGia()), f1));

				c6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c7.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

				c6.setFixedHeight(30f);
				
				c6.setBorderWidthTop(0f);
				c6.setBorderWidthRight(0f);
				c7.setBorderWidthTop(0f);
				c7.setBorderWidthRight(0f);
				c8.setBorderWidthTop(0f);
				c8.setBorderWidthRight(0f);
				c9.setBorderWidthTop(0f);
				c9.setBorderWidthRight(0f);
				c10.setBorderWidthTop(0f);
				c10.setBorderWidthRight(0f);
				c11.setBorderWidthTop(0f);
				c11.setBorderWidthRight(0f);

				pdftable.addCell(c6);
				pdftable.addCell(c7);
				pdftable.addCell(c8);
				pdftable.addCell(c9);
				pdftable.addCell(c10);
				pdftable.addCell(c11);
			}
			document.add(new Paragraph("\n"));
			document.add(pdftable);

			document.add(new Paragraph(String.format(
					"----------------------------------------------------------------------------------------------------------------------------------")));
			document.add(new Paragraph(String.format("%-30s %-137s" + tienPhong + " VNĐ", "Tiền phòng:", ""), f1));
			document.add(new Paragraph(String.format("%-30s %-137s" + tienDichVu + " VNĐ", "Tiền dịch vụ:", ""), f1));
			document.add(new Paragraph(String.format("%-30s %-136s" + tongTien + " VNĐ", "Tổng tiền:", ""), f1));
			document.add(new Paragraph(String.format("%-30s %-132s" + thueVAT + " VNĐ", "Thuế VAT (10%):", ""), f1));

			document.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			document.add(new Paragraph(String.format("%-30s %-140s" + tienThua + " VNĐ",
					"Tiền thừa:", ""), f1));
			document.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			document.add(new Paragraph("\n\n"));
			document.add(new Paragraph(String.format("%8sKhách hàng %132s Nhân viên", "", ""), f1));
			document.add(new Paragraph(String.format("%5s(Ký, ghi rõ họ tên) %123s (Ký, ghi rõ họ tên)", "", ""), f1));
			document.add(new Paragraph("\n\n\n"));
			document.add(new Paragraph(String.format("%8s %-30s %110s %-30s", "", khachHang.getHoTenKhachHang(), "", nhanVien.getHoTenNhanVien()), f1));
			document.add(new Paragraph("\n"));
			
			document.close();
			write.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn");
			e.printStackTrace();
		}
	}
	
	private String formatDateTime(Date currentDate) {
		// Định dạng theo mẫu dd-MM-yyyy HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// Chuyển đổi và in ra ngày giờ theo định dạng
		return sdf.format(currentDate);
	}
}
