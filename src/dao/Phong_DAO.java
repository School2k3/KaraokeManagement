package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;

public class Phong_DAO {
	private static Phong_DAO instance = new Phong_DAO();

	public static Phong_DAO getInstance() {
		return instance;
	}

	public ArrayList<Phong> getAllTablePhong() throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong,lp.tenLoaiPhong, donGia,trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p = new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	/*
	 * Lấy phòng theo mã phòng từ database
	 */
	public Phong getPhongByMaPhong(String maTim) {
		Phong phong = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong where maPhong = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTim);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				phong = new Phong(maPhong, tenPhong, trangThai, lp, donGia);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return phong;
	}
	
	public ArrayList<Phong> getAllTablePhongByTenPhongOrTenLoaiPhong(String chuoiTim) throws Exception {
		ArrayList<Phong> arlPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select maPhong, tenPhong, trangThai, p.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong = lp.maLoaiPhong where " + chuoiTim;
			PreparedStatement preparedStatement = con.prepareStatement(sql);;
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String trangThai = rs.getString(3);
				LoaiPhong maLoaiPhong = new LoaiPhong(rs.getString(4), rs.getString(5));
				double donGia = rs.getDouble(6);
				
				Phong phong = new Phong(maPhong, tenPhong, trangThai, maLoaiPhong, donGia);
				arlPhong.add(phong);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlPhong;
	}
	public ArrayList<Phong> getAllTablePhongByTrangThai(String ttTim) throws Exception {
		ArrayList<Phong> arlPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from [dbo].[Phong] where TrangThai = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);;
			preparedStatement.setString(1, ttTim);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = preparedStatement.executeQuery();
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
	/*
	 * Tạo hám để cập nhật trạng thái cho 1 phòng sau khi đã đặt phòng
	 */
    public boolean updateTrangThai(String maPhong, String trangThai) {
        int n = 0;
        PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "update Phong set trangThai = ? where maPhong = ?";
        try {
        	preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setString(1, trangThai);
        	preparedStatement.setString(2, maPhong);
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

    public ArrayList<Phong> getListPhongByTenLoai(String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + tenLoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByMaVaTenLoai(String ma, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where maPhong like ? AND lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByTenVaTenLoai(String ten, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where tenPhong like ? AND lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByDonGiaVaTenLoai(double gia, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where donGia=? AND lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByMaDonGiaVaTenLoai(String ma, double gia, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where donGia=? AND lp.tenLoaiPhong like ? AND maPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			preparedStatement.setNString(3, "%" + ma + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByTenDonGiaVaTenLoai(String ten, double gia, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where donGia=? AND lp.tenLoaiPhong like ? AND tenPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			preparedStatement.setNString(3, "%" + ten + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getListPhongByMaTenVaTenLoai(String ma, String ten, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where tenPhong like ? AND lp.tenLoaiPhong like ? AND maPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			preparedStatement.setNString(3, "%" + ma + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}public ArrayList<Phong> getListPhongByMaTenDonGiaVaTenLoai(double gia,String ma, String ten, String tenLoai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maPhong, tenPhong, lp.maLoaiPhong, lp.tenLoaiPhong, donGia, trangThai from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"
					+ "where tenPhong like ? AND lp.tenLoaiPhong like ? AND maPhong like ? AND donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + tenLoai + "%");
			preparedStatement.setNString(3, "%" + ma + "%");
			preparedStatement.setDouble(4, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong= rs.getString(1);
				String tenPhong= rs.getString(2);
				String maLoaiPhong= rs.getString(3);
				String tenLoaiPhong= rs.getString(4);
				double donGia= rs.getDouble(5);
				String trangThai= rs.getString(6);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
				Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	public ArrayList<Phong> getCmbTrangThai() throws Exception{
		ArrayList<LoaiPhong> dsTrangThai = new ArrayList<LoaiPhong>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct trangThai from LoaiPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoaiPhong= rs.getString(1);
				String tenLoaiPhong= rs.getString(2);
				LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
			//	dsLoaiPhong.add(lp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return dsTrangThai;
		return null;
	}

	//Thêm phòng
	public boolean themPhong(Phong p)  {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "declare @ma nvarchar(6)"+"\n"
        		+"select @ma=maLoaiPhong from LoaiPhong where tenLoaiPhong =?"+"\n"
        		+ "\nINSERT INTO Phong VALUES (?,?,?,@ma,?)";
        int n = 0;
        try {
            statement = con.prepareStatement(SQL);
            statement.setNString(1,p.getLoaiPhong().getTenLoaiPhong());
            statement.setString(2,p.getMaPhong());
            statement.setNString(3,p.getTenPhong());
            statement.setString(4,p.getTrangThai());
            statement.setDouble(5, p.getDonGia());
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
	//UPDATE PHÒNG
    public boolean capNhatPhong(Phong p) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "declare @ma nvarchar(6)" +"\n"
            		+"select @ma=maLoaiPhong from LoaiPhong where tenLoaiPhong = ?" +"\n"
            				+"update Phong" +"\n"
            				+"set tenPhong=?, trangThai=?, maLoaiPhong=@ma, donGia=?"  +"\n"
            				+"where maPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setNString(1, p.getLoaiPhong().getTenLoaiPhong());
            statement.setNString(2, p.getTenPhong());
            statement.setNString(3, p.getTrangThai());
            statement.setDouble(4, p.getDonGia());
            statement.setNString(5, p.getMaPhong());
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
    
    //Tìm theo 1 tiêu chí
    
    //Mã
    public ArrayList<Phong> getListPhongByMa(String ma) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    //Tên
    public ArrayList<Phong> getListPhongByTen(String ten) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    //Trạng thái
    public ArrayList<Phong> getListPhongByTrangThai(String stt) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    //Loại phòng
    public ArrayList<Phong> getListPhongByLoai(String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    //Giá
    public ArrayList<Phong> getListPhongByGia(double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    
    //Tìm theo 2 tiêu chí
    
    //Mã và tên
    public ArrayList<Phong> getListPhongByMavaTen(String ma, String ten) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    //Mã và Giá
    public ArrayList<Phong> getListPhongByMavaGia(String ma, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setDouble(2, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Mã và Loại
    public ArrayList<Phong> getListPhongByMavaLoai(String ma, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Mã và Trạng thái
    public ArrayList<Phong> getListPhongByMaVaTrangThai(String stt, String ma) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where trangThai like ? and maPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + stt + "%");
			preparedStatement.setNString(2, "%" + ma + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Tên và Giá
    public ArrayList<Phong> getListPhongByTenvaGia(String ten, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ? and donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setDouble(2, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Tên và Loại
    public ArrayList<Phong> getListPhongByTenVaLoai(String ten, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Tên và Trạng thái
    public ArrayList<Phong> getListPhongByTenVaTrangThai(String stt, String ten) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where trangThai like ? and tenPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + stt + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Giá và Loại
    public ArrayList<Phong> getListPhongByGiaVaLoai(String loai, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where lp.tenLoaiPhong like ? and donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + loai + "%");
			preparedStatement.setDouble(2, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	//Giá và Trạng thái
    public ArrayList<Phong> getListPhongByGiaVaTrangThai(String stt, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where trangThai like ? and donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + stt + "%");
			preparedStatement.setDouble(2, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	//Loại và Trạng thái
    public ArrayList<Phong> getListPhongByLoaiVaTrangThai(String stt, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where trangThai like ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + stt + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

    //Tìm theo 3 tiêu chí
    
//    Mã, Tên và Giá
    public ArrayList<Phong> getListPhongByMaTenvaGia(String ma, String ten, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ? and donGia=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Mã, Tên và Loại
    public ArrayList<Phong> getListPhongByMaTenVaLoai(String ma, String loai, String ten) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setNString(3, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Mã, Tên và Trạng thái
    public ArrayList<Phong> getListPhongByMaTenVaTrangThai(String ma, String stt, String ten) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Mã, Giá và Loại
    public ArrayList<Phong> getListPhongByMaGiaVaLoai(String ma, String loai, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and donGia= ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setDouble(2, gia);
			preparedStatement.setNString(3, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Mã, Giá và Trạng thái
    public ArrayList<Phong> getListPhongByMaGiaVaTrangThai(String ma, String stt, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and donGia= ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setDouble(2, gia);
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Mã, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByMaLoaiVaTrangThai(String ma, String stt, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and lp.tenLoaiPhong ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Tên, Giá và Loại
    public ArrayList<Phong> getListPhongByTenGiaVaLoai(String ten, String loai, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ? and donGia= ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setDouble(2, gia);
			preparedStatement.setNString(3, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Tên, Giá và Trạng thái
    public ArrayList<Phong> getListPhongByTenGiaVaTrangThai(String ten, String stt, double gia) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ? and donGia= ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setDouble(2, gia);
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Tên, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByTenLoaiVaTrangThai(String ten, String stt, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where tenPhong like ? and lp.tenLoaiPhong like ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ten + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Giá, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByGiaLoaiVaTrangThai(double gia, String stt, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where donGia = ? and lp.tenLoaiPhong like ? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, gia);
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setNString(3, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    Tìm theo 4 trong 5 tiêu chí:
    
//    	Mã, Tên, Giá và Loại
    public ArrayList<Phong> getListPhongByMaTenGiaVaLoai(String ma, String ten, double gia, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ? and donGia=? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    	Mã, Tên, Giá và Trạng thái
    public ArrayList<Phong> getListPhongByMaTenGiaVaTrangThai(String ma, String ten, double gia, String stt) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ? and donGia=? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    	Mã, Tên, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByMaTenLoaiVaTrangThai(String ma, String ten, String stt, String loai) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ? and trangThai like ? and lp.tenLoaiPhong like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setNString(3, "%" + stt + "%");
			preparedStatement.setNString(4, "%" + loai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    	Mã, Giá, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByMaGiaLoaiVaTrangThai(String ma, String loai, double gia, String stt) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and lp.tenLoaiPhong like ? and donGia=? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + loai + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
//    	Tên, Giá, Loại và Trạng thái
    public ArrayList<Phong> getListPhongByTenGiaLoaiVaTrangThai(String loai, String ten, double gia, String stt) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where lp.tenLoaiPhong like ? and tenPhong like ? and donGia=? and trangThai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + loai + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
    
    //Tìm theo 5 tiêu chí
    public ArrayList<Phong> getListPhongByAll(String ma, String ten, double gia, String loai, String stt) throws Exception {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement preparedStatement = null;
        ConnectDB.getInstance();
		try {
            Connection con = ConnectDB.getConnection();
			String sql = "select maPhong, tenPhong, trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, donGia from Phong p join LoaiPhong lp on p.maLoaiPhong= lp.maLoaiPhong"+ "\n"
					+ "where maPhong like ? and tenPhong like ? and donGia=? and lp.tenLoaiPhong like ? and trangThai Like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + ma + "%");
			preparedStatement.setNString(2, "%" + ten + "%");
			preparedStatement.setDouble(3, gia);
			preparedStatement.setNString(4, "%" + loai + "%");
			preparedStatement.setNString(5, "%" + stt + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                String trangThai= rs.getString(3);
                String maLoaiPhong= rs.getString(4);
                String tenLoaiPhong= rs.getString(5);
                double donGia = rs.getDouble(6);
                LoaiPhong lp= new LoaiPhong(maLoaiPhong, tenLoaiPhong);
                Phong p= new Phong(maPhong, tenPhong, trangThai, lp, donGia);
                dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
}
