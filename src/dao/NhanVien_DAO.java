package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	private static NhanVien_DAO instance = new NhanVien_DAO();

	public static NhanVien_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<NhanVien> getAllTableNhanVien() throws Exception{
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from [dbo].[NhanVien]";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoTenNhanVien = rs.getString(2);
				String gioiTinh = rs.getString(3);
				int namSinh = rs.getInt(4);
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String canCuocCongDan = rs.getString(7);
				String matKhau = rs.getString(8);
				String chucVu = rs.getString(9);
				
				NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, matKhau, chucVu);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return arlNhanVien;
	}
}
