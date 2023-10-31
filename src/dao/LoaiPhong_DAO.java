package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {
	private static LoaiPhong_DAO instance = new LoaiPhong_DAO();

	public static LoaiPhong_DAO getInstance() {
		return instance;
	}

	public ArrayList<LoaiPhong> getAllTableLoaiPhong() throws Exception {
		ArrayList<LoaiPhong> arlLoaiPhong = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[LoaiPhong]";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoaiPhong = rs.getString(1);
				String tenLoaiPhong = rs.getString(2);

				LoaiPhong loaiPhong = new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				arlLoaiPhong.add(loaiPhong);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlLoaiPhong;
	}
}
