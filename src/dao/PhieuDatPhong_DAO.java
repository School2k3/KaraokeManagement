package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class PhieuDatPhong_DAO {
	public List<PhieuDatPhong> getAllPhieuDatPhong() {
		List<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select maPhieuDat, pdp.maKhachHang, pdp.maNhanVien, ngayLap, trangThai from PhieuDatPhong pdp join KhachHang kh on pdp.maKhachHang = kh.maKhachHang join NhanVien nv on pdp.maNhanVien = nv.maNhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				PhieuDatPhong pdp = new PhieuDatPhong();
				KhachHang kh = new KhachHang();
				NhanVien nv = new NhanVien();

				pdp.setMaPhieuDat(rs.getString("maPhieuDat"));
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				pdp.setNgayLap(rs.getDate("NgayLap"));
				pdp.setTrangThai(rs.getString("trangThai"));

				pdp.setKhachHang(kh);
				pdp.setNhanVien(nv);
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
}
