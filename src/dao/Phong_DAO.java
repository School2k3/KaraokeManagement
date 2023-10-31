package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class Phong_DAO {
	private static Phong_DAO instance = new Phong_DAO();

	public static Phong_DAO getInstance() {
		return instance;
	}

	public ArrayList<Phong> getAllTablePhong() throws Exception {
		ArrayList<Phong> arlPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from [dbo].[Phong]";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String trangThai = rs.getString(3);
				LoaiPhong maLoaiPhong = new LoaiPhong(rs.getString(4));
				double donGia = rs.getDouble(5);
				
				Phong phong = new Phong(maPhong, tenPhong, trangThai, maLoaiPhong, donGia);
				arlPhong.add(phong);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return arlPhong;
	}
}
