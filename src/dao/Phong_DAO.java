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
	public List<Phong> getAllPhongByMaHoaDon(String maTim) {
		List<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from HoaDon where maHoaDon = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTim);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String trangThai = rs.getString(3);
				LoaiPhong maLoaiPhong = new LoaiPhong(rs.getString(4));
				double donGia = rs.getDouble(5);
				
				Phong phong = new Phong(maPhong, tenPhong, trangThai, maLoaiPhong, donGia);
				dsPhong.add(phong);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
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
	public boolean themPhong(Phong p)  {
        //thêm dịch vụ
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO Phong VALUES (?,?,?,?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,p.getMaPhong());
            statement.setString(2,p.getTenPhong());
//            statement.setString(3,p.());
//            statement.setString(4,p.());
//            statement.setString(5,p.());
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
    public boolean capNhatPhong(Phong p) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE Phong SET tenPhong=?, donGia=?, trangThai=?, tenLoaiPhong=? WHERE maPhong=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, p.getTenPhong());
            statement.setDouble(2, p.getDonGia());
            statement.setString(3, p.getTrangThai());
//            statement.setString(4, p.);
//            statement.setString(5, p.);
//            statement.setString(6, p.);
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
}
