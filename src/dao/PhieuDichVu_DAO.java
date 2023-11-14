package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.PhieuDichVu;

public class PhieuDichVu_DAO {
	public ArrayList<PhieuDichVu> getAllPhieuDichVu() throws Exception{
		ArrayList<PhieuDichVu> dsPhieuDichVu= new ArrayList<PhieuDichVu>();
		try {
            /*	
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT maPhieuDichVu, pdv.maKhachHang, kh.tenKhachHang, kh.soDienThoai, pdv.maNhanVien, ngayLap, trangThai from PhieuDichVu pdv "
            		+ "join nhan vien nv on pdv.maNhanVien= nv.maNhanVien "
            		+ "join KhachHang kh on pdv.maKhachHang= kh.maKhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
            	String maPDV= rs.getString(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt= rs.getString(4);
                String maNhanVien = rs.getString(5);
				Timestamp ngayLap = rs.getTimestamp(6);
				String trangThai = rs.getString(7);
                KhachHang kh= new KhachHang(maKH, tenKH, sdt);
                NhanVien nv= new NhanVien(maNhanVien);
                PhieuDichVu pdv= new PhieuDichVu(maPDV, kh, nv, ngayLap, trangThai);
                dsPhieuDichVu.add(pdv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhieuDichVu;
	}
	/*
	 * Get phieuDichVu by tenKhachHang
	 */
	public List<PhieuDatPhong> getAllPhieuDatPhongByTenKhachHang(String chuoiTim) {
		List<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "Select maPhieuDichVu, pdu.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, pdu.maNhanVien, ngayLap, trangThai from PhieuDichVu pdu "
					+ "join KhachHang kh on pdu.maKhachHang = kh.maKhachHang "
					+ "join NhanVien nv on pdu.maNhanVien = nv.maNhanVien where " + chuoiTim;
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhieuDat = rs.getString("maPhieuDichVu");
				String maKhachHang = rs.getString("maKhachHang");
				String hoTenKhachHang = rs.getString("hoTenKhachHang");
				String soDienThoai = rs.getString("soDienThoai");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLap = rs.getTimestamp("NgayLap");
				String trangThai = rs.getString("trangThai");

				KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, soDienThoai);
				NhanVien nv = new NhanVien(maNhanVien);
				PhieuDatPhong pdp = new PhieuDatPhong(maPhieuDat, kh, nv, ngayLap, trangThai);
				dsPDP.add(pdp);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}
	/*
	 * Tạo hàm để thêm một phiếu dịch vụ vào trong database
	 */
	public boolean insertPhieuDichVu(PhieuDichVu pdv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into PhieuDatPhong values(?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, pdv.getMaPhieuDichVu());
			preparedStatement.setString(2, pdv.getKhachHang().getMaKhachHang());
			preparedStatement.setString(3, pdv.getNhanVien().getMaNhanVien());
			preparedStatement.setTimestamp(4, pdv.getNgayLap());
			preparedStatement.setString(5, pdv.getTrangThai());
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	/*
	 * Tạo hàm phát sinh mã phiếu đặt phòng tăng dần
	 */
//	public String phatSinhMaPhieuDichVu() {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement preparedStatement = null;
//		ResultSet rs = null;
//		String maPhieuDichVu = null;
//		try {
//			String sql = "select top 1 maPhieuDichVu from PhieuDichVu order by maPhieuDichVu desc";
//			preparedStatement = con.prepareStatement(sql);
//			rs = preparedStatement.executeQuery();
//			if (rs.next()) {
//				String maPhieuDatCuoi = rs.getString("maPhieuDichVu");
//				String phanSo = maPhieuDatCuoi.substring(3); // Lấy phần số từ mã hóa đơn cuối cùng
//				int count = Integer.parseInt(phanSo) + 1; // Tăng giá trị đếm lên 1
//				maPhieuDichVu = "MPD" + String.format("%04d", count); // Định dạng lại giá trị thành chuỗi
//			} else {
//				maPhieuDichVu = "MPD0001";
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return maPhieuDichVu;
//	}
}
