package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {
	public ArrayList<LoaiPhong> getAllTableLoaiPhong() throws Exception{
		ArrayList<LoaiPhong> dsLoaiPhong= new ArrayList<LoaiPhong>();
		try {
            /*	
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM LoaiPhong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                LoaiPhong lp= new LoaiPhong(ma, ten);
                dsLoaiPhong.add(lp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsLoaiPhong;
	}
	public boolean themLoaiPhong(LoaiPhong loaiPhong)  {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO LoaiPhong VALUES (?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,loaiPhong.getMaLoaiPhong());
            statement.setString(2,loaiPhong.getTenLoaiPhong());
            n = statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
            try {
                statement.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    public boolean capNhatLoaiPhong(LoaiPhong loaiPhong) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE LoaiPhong SET tenLoaiPhong=? WHERE maLoaiPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, loaiPhong.getTenLoaiPhong());
            statement.setString(2, loaiPhong.getMaLoaiPhong());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
	public ArrayList<LoaiPhong> getcmbLoaiPhong() throws Exception{
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct maLoaiPhong,tenLoaiPhong from LoaiPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoaiPhong= rs.getString(1);
				String tenLoaiPhong= rs.getString(2);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				dsLoaiPhong.add(lp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	public boolean xoaLoaiPhongByMa(String maPhong){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiPhong WHERE maLoaiPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maPhong);
            n = statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
	public boolean xoaLoaiPhongByTen(String tenLoaiPhong){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiPhong WHERE tenLoaiPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,tenLoaiPhong);
            n = statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
	public boolean xoaLoaiPhongByMaVaTen(String maLoaiPhong, String tenLoaiPhong){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiPhong WHERE maLoaiPhong=? AND tenLoaiPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maLoaiPhong);
            statement.setString(2,tenLoaiPhong);
            n = statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
}
