package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;

public class HoaDon_DAO {
	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select maHoaDon, hd.maKhachHang, hd.maNhanVien, hd.maPhong, ngayLap, trangThai"
					+ "from HoaDon hd join KhachHang kh on hd.maKhachHang = kh.maKhachHang"
					+ "join NhanVien nv on hd.maNhanVien = nv.maNhanVien"
					+ "join Phong p on hd.maPhong = p.maPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {	
				HoaDon hd = new HoaDon();
				KhachHang kh = new KhachHang();
				NhanVien nv = new NhanVien();
				Phong p = new Phong();
				
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				p.setMaPhong(rs.getString("maPhong"));
				hd.setNgayLap(rs.getDate("NgayLap"));
				hd.setTrangThai(rs.getString("trangThai"));
				
				hd.setKhachHang(kh);
				hd.setNhanVien(nv);
				hd.setPhong(p);
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
}
