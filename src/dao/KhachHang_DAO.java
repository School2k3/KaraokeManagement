package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	private static KhachHang_DAO instance = new KhachHang_DAO();

	public static KhachHang_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<KhachHang> getAllTableKhachHang() throws Exception{
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from [dbo].[KhachHang]";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String soDienThoai = rs.getString(4);
				String canCuocCongDan = rs.getString(5);
				
				KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, soDienThoai, canCuocCongDan);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlKhachHang;
	}
	
	
}
