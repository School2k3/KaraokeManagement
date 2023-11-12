package dao;

import connectDB.ConnectDB;
import entity.*;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;
public class DichVu_DAO {
	public ArrayList<DichVu> getAllTableDichVu() throws Exception{
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
            String SQL = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
            	LoaiDichVu ldv;
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                String maLoaiDichVu= rs.getString(4);
                String tenLoaiDichVu= rs.getString(5);
                double donGia = rs.getDouble(6);
                
                ldv= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                DichVu dv= new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
                dsDichVu.add(dv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsDichVu;
	}
	public boolean themDichVu(DichVu dv)  {
        //thêm dịch vụ
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "DECLARE @n nvarchar(30)" +"\n"
        			+"SELECT @n = maLoaiDichVu FROM LoaiDichVu WHERE tenLoaiDichVu LIKE ?" + "\n"
        			+"insert into DichVu values(?,?,?,@n,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            String ten= dv.getLoaiDichVu().getTenLoaiDichVu();
            statement.setNString(1,"%" + ten + "%" );
            statement.setString(2, dv.getMaDichVu());
            statement.setString(3, dv.getTenDichVu());
            statement.setInt(4, dv.getSoLuongTon());
            statement.setDouble(5, dv.getDonGia());
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
            String SQL = "UPDATE DichVu SET tenDichVu=?, soLuongTon=?, tenLoaiDichVu=?, donGia=? WHERE maDichVu=? ";
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
    //tim theo loai
    public ArrayList<DichVu> getListDichVuByLoai(String loai) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		try {
			ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
			PreparedStatement preparedStatement = null;
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"
					+ "where ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                String maLoaiDichVu= rs.getString(4);
                String tenLoaiDichVu= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiDichVu ldv= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                DichVu dv= new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
                dsDV.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsDV;
	}
    //tim theo ma va loai
    public ArrayList<DichVu> getListDichVuByMaVaLoai(String loai, String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"
					+ "where maDichVu like ? AND ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                String maLoaiDichVu= rs.getString(4);
                String tenLoaiDichVu= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiDichVu ldv= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                DichVu dv= new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
                dsDV.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDV;
	}
    //tim theo ma ten va loai
    public ArrayList<DichVu> getListDichVuByMaTenVaLoai(String loai, String ma, String ten) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"
					+ "where maDichVu like ? AND tenDichVu like ? AND ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setNString(3, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                String maLoaiDichVu= rs.getString(4);
                String tenLoaiDichVu= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiDichVu ldv= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                DichVu dv= new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
                dsDV.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDV;
	}
    public ArrayList<DichVu> getAllTableDichVuByTenLoaiDichVu(String dvTim) throws Exception {
		ArrayList<DichVu> dsDichVu = new ArrayList<DichVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu where ldv.tenLoaiDichVu = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);;
			preparedStatement.setString(1, dvTim);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				LoaiDichVu ldv;
            	
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongTon = rs.getInt(3);
                String maLoaiDichVu= rs.getString(4);
                String tenLoaiDichVu= rs.getString(5);
                double donGia = rs.getDouble(6);
                
                ldv = new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                DichVu dv = new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
                dsDichVu.add(dv);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return dsDichVu;
	}
    
    /*
     * Hàm cập nhật lại số lượng tồn của 1 dịch vụ sau khi nhập số lượng đặt dịch vụ
     */
    public boolean updateSoLuongTon(String maDichVu, int soLuong) {
    	int n = 0;
        PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update DichVu set soLuongTon = ? where maDichVu = ?";
        try {
        	preparedStatement = con.prepareStatement(query);
        	preparedStatement.setInt(1, soLuong);
        	preparedStatement.setString(2, maDichVu);
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
