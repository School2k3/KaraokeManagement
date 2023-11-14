package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.LoaiDichVu;
import entity.LoaiPhong;

public class LoaiDichVu_DAO {
	//Lấy danh sách tất cả dịch vụ
	public ArrayList<LoaiDichVu> getAllTableLoaiDichVu() throws Exception{
		ArrayList<LoaiDichVu> dsLoaiDichVu= new ArrayList<LoaiDichVu>();
		try {
            /*	
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM LoaiDichVu";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maLoaiDichVu = rs.getString(1);
                String tenLoaiDichVu = rs.getString(2);
                LoaiDichVu loaiDichVu= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                dsLoaiDichVu.add(loaiDichVu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsLoaiDichVu;
	}
	//Thêm 1 dịch vụ
	public boolean themLoaiDichVu(LoaiDichVu loaiDichVu)  {
        //thêm dịch vụ
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO LoaiDichVu VALUES (?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,loaiDichVu.getMaLoaiDichVu());
            statement.setString(2,loaiDichVu.getTenLoaiDichVu());
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
	//Cập nhật dịch vụ
    public boolean capNhatLoaiDichVu(LoaiDichVu loaiDichVu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE LoaiDichVu SET tenLoaiDichVu=? WHERE maLoaiDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, loaiDichVu.getTenLoaiDichVu());
            statement.setString(2, loaiDichVu.getMaLoaiDichVu());
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
    //Xóa theo mã
	public boolean xoaLoaiDichVuByMa(String maDV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiDichVu WHERE maDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maDV);
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
	//Xóa theo tên
	public boolean xoaLoaiDichVuByTen(String tenDichVu){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiDichVu WHERE tenLoaiDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,tenDichVu);
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
	//Xóa theo mã và tên
	public boolean xoaLoaiDichVuByMaVaTen(String maDV, String tenDV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM LoaiDichVu WHERE maLoaiDichVu=? AND tenLoaiDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maDV);
            statement.setString(2,tenDV);
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
	//lấy giá trị cho cmb loại dịch vụ
	public ArrayList<LoaiDichVu> getCmbLoaiDichVu() throws Exception{
		ArrayList<LoaiDichVu> dsLoaiDichVu = new ArrayList<LoaiDichVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct maLoaiDichVu,tenLoaiDichVu from LoaiDichVu ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma= rs.getString(1);
				String ten= rs.getString(2);
				LoaiDichVu ldv= new LoaiDichVu(ma, ten);
				dsLoaiDichVu.add(ldv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiDichVu;
	}
}
