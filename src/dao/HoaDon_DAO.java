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
import entity.PhieuDatPhong;
import entity.Phong;

public class HoaDon_DAO {
	public List<HoaDon> getAllHoaDon() {
		/*
		 * Tạo hàm để lấy danh sách tất cả các hóa đơn có trong database
		 */
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select maHoaDon, hd.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, hd.maNhanVien, hd.maPhong, p.tenPhong, thoiGianBatDau, thoiGianKetThuc, hd.trangThai from HoaDon hd join KhachHang kh on hd.maKhachHang = kh.maKhachHang join NhanVien nv on hd.maNhanVien = nv.maNhanVien join Phong p on hd.maPhong = p.maPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				KhachHang maKhachHang = new KhachHang(rs.getString("maKhachHang"), rs.getString("hoTenKhachHang"), rs.getString("soDienThoai"));
				NhanVien maNhanVien = new NhanVien(rs.getString("maNhanVien"));
				Phong maPhong = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
				Timestamp thoiGianBatDau = rs.getTimestamp("thoiGianBatDau");
				Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
				String trangThai = rs.getString("trangThai");
				
				HoaDon hd = new HoaDon(maHoaDon, maKhachHang, maNhanVien, maPhong, thoiGianBatDau, thoiGianKetThuc, trangThai);
				dsHD.add(hd);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHD;
	}
	
	/*
	 * Lấy danh sách tất cả các hóa đơn chưa thanh toán từ database
	 */
	public List<HoaDon> getAllHoaDonChuaThanhToan() {
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select maHoaDon, hd.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, hd.maNhanVien, hd.maPhong, p.tenPhong, thoiGianBatDau, thoiGianKetThuc, hd.trangThai from HoaDon hd join KhachHang kh on hd.maKhachHang = kh.maKhachHang join NhanVien nv on hd.maNhanVien = nv.maNhanVien join Phong p on hd.maPhong = p.maPhong where hd.trangThai = N'Chưa thanh toán'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				KhachHang maKhachHang = new KhachHang(rs.getString("maKhachHang"), rs.getString("hoTenKhachHang"), rs.getString("soDienThoai"));
				NhanVien maNhanVien = new NhanVien(rs.getString("maNhanVien"));
				Phong maPhong = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
				Timestamp thoiGianBatDau = rs.getTimestamp("thoiGianBatDau");
				Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
				String trangThai = rs.getString("trangThai");
				
				HoaDon hd = new HoaDon(maHoaDon, maKhachHang, maNhanVien, maPhong, thoiGianBatDau, thoiGianKetThuc, trangThai);
				dsHD.add(hd);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHD;
	}
	
	/*
	 * Lấy hóa đơn theo mã hóa đơn từ database
	 */
	public HoaDon getAllHoaDonByMaHoaDon(String maTim) {
		HoaDon hoaDon = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from HoaDon where maHoaDon = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTim);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				KhachHang maKhachHang = new KhachHang(rs.getString("maKhachHang"));
				NhanVien maNhanVien = new NhanVien(rs.getString("maNhanVien"));
				Phong maPhong = new Phong(rs.getString("maPhong"));
				Timestamp thoiGianBatDau = rs.getTimestamp("thoiGianBatDau");
				Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
				String trangThai = rs.getString("trangThai");
				
				hoaDon = new HoaDon(maHoaDon, maKhachHang, maNhanVien, maPhong, thoiGianBatDau, thoiGianKetThuc, trangThai);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hoaDon;
	}
	
	/*
	 * Tạo hàm để lấy danh sách tất cả các hóa đơn có tên khách hàng cần tìm trong database
	 */
	public List<HoaDon> getAllHoaDonByTenKhachHang(String chuoiTim) {
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "Select maHoaDon, hd.maKhachHang, kh.hoTenKhachHang, kh.soDienThoai, hd.maNhanVien, hd.maPhong, p.tenPhong, thoiGianBatDau, thoiGianKetThuc, hd.trangThai from HoaDon hd join KhachHang kh on hd.maKhachHang = kh.maKhachHang join NhanVien nv on hd.maNhanVien = nv.maNhanVien join Phong p on hd.maPhong = p.maPhong where " + chuoiTim;
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				KhachHang maKhachHang = new KhachHang(rs.getString("maKhachHang"), rs.getString("hoTenKhachHang"), rs.getString("soDienThoai"));
				NhanVien maNhanVien = new NhanVien(rs.getString("maNhanVien"));
				Phong maPhong = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
				Timestamp thoiGianBatDau = rs.getTimestamp("thoiGianBatDau");
				Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
				String trangThai = rs.getString("trangThai");
				
				HoaDon hd = new HoaDon(maHoaDon, maKhachHang, maNhanVien, maPhong, thoiGianBatDau, thoiGianKetThuc, trangThai);
				dsHD.add(hd);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHD;
	}
	/*
	 * Tạo hàm để thêm 1 hóa đơn vào trong database
	 */
	public boolean insertHoaDon(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into HoaDon values(?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, hd.getMaHoaDon());
			preparedStatement.setString(2, hd.getKhachHang().getMaKhachHang());
			preparedStatement.setString(3, hd.getNhanVien().getMaNhanVien());
			preparedStatement.setString(4, hd.getPhong().getMaPhong());
			preparedStatement.setTimestamp(5, hd.getThoiGianBatDau());
			preparedStatement.setTimestamp(6, hd.getThoiGianKetThuc());
			preparedStatement.setString(7, hd.getTrangThai());
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
	 * Tạo hàm để phát sinh mã hóa đơn tăng dần
	 */
	public String phatSinhMaHoaDon() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String maHoaDon = null;
		try {
			String sql = "select top 1 maHoaDon from HoaDon order by maHoaDon desc";
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maHoaDonCuoi = rs.getString("maHoaDon");
				String phanSo = maHoaDonCuoi.substring(2); // Lấy phần số từ mã hóa đơn cuối cùng
				int count = Integer.parseInt(phanSo) + 1; // Tăng giá trị đếm lên 1
				maHoaDon = "HD" + String.format("%04d", count); // Định dạng lại giá trị thành chuỗi
			} else {
				maHoaDon = "HD0001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maHoaDon;
	}
	
	/*
	 * Tạo hám để cập nhật trạng thái cho 1 hóa đơn sau khi đã thanh toán
	 */
    public boolean updateTrangThai(String maHoaDon, String trangThai) {
        int n = 0;
        PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "update HoaDon set trangThai = ? where maHoaDon = ?";
        try {
        	preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setString(1, trangThai);
        	preparedStatement.setString(2, maHoaDon);
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
    
	/*
	 * Tạo hám để cập nhật thời gian kết thúc cho 1 hóa đơn sau khi bấm nút trả phòng
	 */
    public boolean updateThoiGianKetThuc(String maHoaDon, Timestamp thoiGianKetThuc) {
        int n = 0;
        PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "update HoaDon set thoiGianKetThuc = ? where maHoaDon = ?";
        try {
        	preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setTimestamp(1, thoiGianKetThuc);
        	preparedStatement.setString(2, maHoaDon);
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
