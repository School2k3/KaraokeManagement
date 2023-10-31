package dao;

import connectDB.ConnectDB;
import entity.*;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;
public class DichVu_DAO {
	public ArrayList<DichVu> layThongTin() throws Exception{
		ArrayList<DichVu> dsDichVu= new ArrayList<DichVu>();
		try {
            /*	
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM DichVu";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                LoaiDichVu loaiDichVu= new LoaiDichVu(rs.getString(4));
                double donGia = rs.getDouble(5);
                DichVu dv= new DichVu(maDichVu, tenDichVu, soLuongTon, loaiDichVu, donGia);
                dsDichVu.add(dv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsDichVu;
	}
	public boolean themDichVu(DichVu dichVu)  {
        //thêm dịch vụ
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO DichVu VALUES (?,?,?,?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,dichVu.getMaDichVu());
            statement.setString(2,dichVu.getTenDichVu());
            statement.setInt(3,dichVu.getSoLuongTon());
            statement.setString(4,dichVu.getLoaiDichVu().getTenLoaiDichVu());
            statement.setDouble(5, dichVu.getDonGia());
            
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
	public boolean xoaDichVu(String maDV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM DichVu WHERE maDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maDV);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể xóa dịch vụ");
        }finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
	/*
    TODO Cập nhật dịch vụ
     */
    public boolean capNhatDichVu(DichVu dichVu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE DichVu SET tenDichVu=?, soLuongTon=?, maLoaiDichVu=?, donGia=? WHERE maDichVu=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, dichVu.getTenDichVu());
            statement.setInt(2, dichVu.getSoLuongTon());
            statement.setString(3, dichVu.getLoaiDichVu().getTenLoaiDichVu());
            statement.setDouble(4, dichVu.getDonGia());
            statement.setString(5, dichVu.getMaDichVu());

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
//    public boolean capNhatLoaiDichVu(DichVu dichVu) {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement statement = null;
//        int n = 0;
//        try {
//            String SQL = "UPDATE DichVu SET tenDichVu=?, soLuongTon=?, maLoaiDichVu=?, donGia=? WHERE maDichVu=?";
//            statement = con.prepareStatement(SQL);
//            statement.setString(1, dichVu.getTenDichVu());
//            statement.setInt(2, dichVu.getSoLuongTon());
//            n = statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                statement.close();
//            } catch (SQLException e2) {
//                e2.printStackTrace();
//            }
//        }
//        return n > 0;
//    }
    public ArrayList<DichVu> timByMa(String maDV ) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from DichVu where maDichVu=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maDV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DichVu dv = new DichVu(maDV);
				dsDV.add(dv);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsDV;
	}
    //tim theo ma
//    public ArrayList<DichVu> getListDichVuByMa(String maDichVu) throws Exception {
//		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
//		ConnectDB.getInstance();
//		PreparedStatement preparedStatement = null;
//		try {
//			Connection con = ConnectDB.getConnection();
//			String sql = "Select * from [dbo].[DichVu] where maDichVu like ?";
//			preparedStatement = con.prepareStatement(sql);
//			preparedStatement.setNString(1, "%" + maDichVu + "%");
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				DichVu dv = new DichVu(rs);
//				dsDV.add(dv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dsDV;
//	}
}
