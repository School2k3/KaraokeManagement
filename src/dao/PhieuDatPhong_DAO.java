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
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;

public class PhieuDatPhong_DAO {
	/*
	 * Tạo hàm để lấy danh sách tất cả các phiếu đặt phòng trong database
	 */
	public List<PhieuDatPhong> getAllPhieuDatPhong() {
		List<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select maPhieuDat, pdp.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, pdp.maNhanVien, ngayLap, trangThai from PhieuDatPhong pdp join KhachHang kh on pdp.maKhachHang = kh.maKhachHang join NhanVien nv on pdp.maNhanVien = nv.maNhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maPhieuDat = rs.getString("maPhieuDat");
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
			statement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}
	
	public List<PhieuDatPhong> getAllPhieuDatPhongByTenKhachHang(String chuoiTim) {
		List<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "Select maPhieuDat, pdp.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, pdp.maNhanVien, ngayLap, trangThai from PhieuDatPhong pdp join KhachHang kh on pdp.maKhachHang = kh.maKhachHang join NhanVien nv on pdp.maNhanVien = nv.maNhanVien where " + chuoiTim;
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhieuDat = rs.getString("maPhieuDat");
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
	 * Tạo hàm để thêm một phiếu đặt phòng vào trong database
	 */
	public boolean insertPhieuDatPhong(PhieuDatPhong pdp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into PhieuDatPhong values(?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, pdp.getMaPhieuDat());
			preparedStatement.setString(2, pdp.getKhachHang().getMaKhachHang());
			preparedStatement.setString(3, pdp.getNhanVien().getMaNhanVien());
			preparedStatement.setTimestamp(4, pdp.getNgayLap());
			preparedStatement.setString(5, pdp.getTrangThai());
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
	public String phatSinhMaPhieuDat() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String maPhieuDat = null;
		try {
			String sql = "select top 1 maPhieuDat from PhieuDatPhong order by maPhieuDat desc";
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maPhieuDatCuoi = rs.getString("maPhieuDat");
				String phanSo = maPhieuDatCuoi.substring(3); // Lấy phần số từ mã hóa đơn cuối cùng
				int count = Integer.parseInt(phanSo) + 1; // Tăng giá trị đếm lên 1
				maPhieuDat = "MPD" + String.format("%04d", count); // Định dạng lại giá trị thành chuỗi
			} else {
				maPhieuDat = "MPD0001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maPhieuDat;
	}
	/*
	 * Tạo hám để cập nhật trạng thái cho 1 phiếu đặt phòng sau khi đã đặt phòng thành công
	 */
    public boolean updateTrangThai(String maPhieuDat, String trangThai) {
        int n = 0;
        PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update PhieuDatPhong set trangThai = ? where maPhieuDat = ?";
        try {
        	preparedStatement = con.prepareStatement(query);
        	preparedStatement.setString(1, trangThai);
        	preparedStatement.setString(2, maPhieuDat);
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
}
