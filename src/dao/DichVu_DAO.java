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
        PreparedStatement statement =null;
        String SQL = "DECLARE @n nvarchar(30)" +"\n"
        			+"SELECT @n = maLoaiDichVu FROM LoaiDichVu WHERE tenLoaiDichVu LIKE ?" + "\n"
        			+"insert into DichVu values(?,?,?,@n,?)";
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();

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

    public boolean capNhatDichVu(DichVu dv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
        	String SQL = "DECLARE @ma nvarchar(30)" +"\n"
        			+"SELECT @ma = maLoaiDichVu FROM LoaiDichVu WHERE tenLoaiDichVu LIKE ?" + "\n"
        			+"update DichVu "+"\n"
        			+ "set tenDichVu=?, soLuongTon=?, donGia=?, maLoaiDichVu= @ma"+"\n"
        			+ "where maDichVu=?";
            statement = con.prepareStatement(SQL);
            String tenLDV= dv.getLoaiDichVu().getTenLoaiDichVu();
            statement.setNString(1, "%"+ tenLDV +"%");
            statement.setNString(2, dv.getTenDichVu());
            statement.setInt(3, dv.getSoLuongTon());
            statement.setDouble(4, dv.getDonGia());
            statement.setNString(5, dv.getMaDichVu());
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
    //tim theo 1 tieu chi
    
    //Mã
    public ArrayList<DichVu> getListDichVuByMa(String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where maDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
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
    //Tên
    public ArrayList<DichVu> getListDichVuByTen(String ten) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
		     Connection con = ConnectDB.getConnection();
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where tenDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
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
	//Giá
	public ArrayList<DichVu> getListDichVuByGia(double gia) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
		       Connection con = ConnectDB.getConnection();
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
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
	//Loại
    public ArrayList<DichVu> getListDichVuByLoai(String loai) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		try {
			ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
			PreparedStatement preparedStatement = null;
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%"+loai+"%");
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
    //Tìm theo 2 tiêu chí
    
    //Mã và loại
    public ArrayList<DichVu> getListDichVuByMaVaLoai(String loai, String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
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
    //Mã và tên
    public ArrayList<DichVu> getListDichVuByMaVaTen(String ten, String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where maDichVu like ? AND tenDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
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
    
    //Mã và giá
    public ArrayList<DichVu> getListDichVuByMaVaGia(double gia, String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where maDichVu like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setDouble(2, gia);
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
    
    //Tên và loại
    public ArrayList<DichVu> getListDichVuByTenVaLoai(String loai, String ten) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where tenDichVu like ? AND ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
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
    
    //Tên và giá
    public ArrayList<DichVu> getListDichVuByTenVaGia(String ten, double gia) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where tenDichVu like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setDouble(2, gia);
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
    
    //Giá và loại
    public ArrayList<DichVu> getListDichVuByGiaVaLoai(double gia, String loai) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where donGia=? AND ldv.tenLoaiDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
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
    //Tìm theo 3 tiêu chí
    
    //Mã tên và loại
    public ArrayList<DichVu> getListDichVuByMaTenVaLoai(String loai, String ma, String ten) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
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
    //Mã tên và giá
    public ArrayList<DichVu> getListDichVuByMaTenVaGia(double gia, String ma, String ten) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where maDichVu like ? AND tenDichVu like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
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
    //Mã loại và giá
    public ArrayList<DichVu> getListDichVuByMaLoaiVaGia(double gia, String ma, String loai) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where maDichVu like ? AND ldv.tenLoaiDichVu like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setDouble(3, gia);
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
    //Tên giá và loại
    public ArrayList<DichVu> getListDichVuByTenGiaVaLoai(double gia, String ten, String loai) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where tenDichVu like ? AND ldv.tenLoaiDichVu like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setDouble(3, gia);
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
    //4 tiêu chí
    public ArrayList<DichVu> getListDichVuByAll(double gia, String ten, String loai, String ma) throws Exception {
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT maDichVu, tenDichVu, soLuongTon, ldv.maLoaiDichVu,ldv.tenLoaiDichVu, donGia from DichVu dv join LoaiDichVu ldv  on dv.maLoaiDichVu= ldv.maLoaiDichVu"+ "\n"
					+ "where tenDichVu like ? AND ldv.tenLoaiDichVu like ? AND donGia=? AND maDichVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + ma + "%");
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

    
    //XóaTheoMã
    public boolean xoaDichVuByMa(String maDV){
        PreparedStatement statement = null;
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
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
