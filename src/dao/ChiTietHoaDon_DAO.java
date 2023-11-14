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
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;

public class ChiTietHoaDon_DAO {
	/*
	 * Lấy danh sách tất cả các chi tiết hóa đơn trong database
	 */
	public List<ChiTietHoaDon> getAllChiTietHoaDon() {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select cthd.maHoaDon, cthd.maDichVu, dv.tenDichVu, cthd.soLuong, cthd.donGia from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon join DichVu dv on cthd.maDichVu = dv.maDichVu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				HoaDon maHoaDon = new HoaDon(rs.getString("maHoaDon"));
				DichVu maDichVu = new DichVu(rs.getString("maDichVu"), rs.getString("tenDichVu"));
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");

				ChiTietHoaDon cthd = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, donGia);
				dsCTHD.add(cthd);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTHD;
	}
	
	/*
	 * Lấy danh sách tất cả các chi tiết hóa đơn theo mã hóa đơn từ database
	 */
	public List<ChiTietHoaDon> getAllChiTietHoaDonByMaHoaDon(String maTim) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select cthd.maHoaDon, cthd.maDichVu, dv.tenDichVu, cthd.soLuong, cthd.donGia from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon join DichVu dv on cthd.maDichVu = dv.maDichVu where cthd.maHoaDon = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTim);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				HoaDon maHoaDon = new HoaDon(rs.getString("maHoaDon"));
				DichVu maDichVu = new DichVu(rs.getString("maDichVu"), rs.getString("tenDichVu"));
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");

				ChiTietHoaDon cthd = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, donGia);
				dsCTHD.add(cthd);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTHD;
	}
	
	/*
	 * Thêm chi tiết hóa đơn vào trong database
	 */
	public boolean insertChiTietHoaDon(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into ChiTietHoaDon values(?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, cthd.getHoaDon().getMaHoaDon());
			preparedStatement.setString(2, cthd.getDichVu().getMaDichVu());
			preparedStatement.setInt(3, cthd.getSoLuong());
			preparedStatement.setDouble(4, cthd.getDonGia());
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
	 * Lấy mã hóa đơn gần nhất
	 */
	public String getMaHoaDonCuoi() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String maHoaDonCuoi = null;
		try {
			String sql = "select top 1 maHoaDon from HoaDon order by maHoaDon desc";
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maHoaDonCuoi = rs.getString("maHoaDon");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maHoaDonCuoi;
	}
}
