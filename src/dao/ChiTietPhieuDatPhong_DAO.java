package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.PhieuDatPhong;
import entity.Phong;

public class ChiTietPhieuDatPhong_DAO {
	/*
	 * Lấy danh sách các chi tiết phiếu đặt phòng trong database
	 */
	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhong() {
		List<ChiTietPhieuDatPhong> dsCTPDP = new ArrayList<ChiTietPhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select ctpdp.maPhieuDat, ctpdp.maPhong, p.tenPhong, thoiGianDat from ChiTietPhieuDatPhong ctpdp join PhieuDatPhong pdp on ctpdp.maPhieuDat = pdp.maPhieuDat join Phong p on ctpdp.maPhong = p.maPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				String maPhieuDat = rs.getString("maPhieuDat");
				String maPhong = rs.getString("maPhong");
				String tenPhong = rs.getString("tenPhong");
				Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");

				PhieuDatPhong pdp = new PhieuDatPhong(maPhieuDat);
				Phong p = new Phong(maPhong, tenPhong);
				ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong(pdp, p, thoiGianDat);
				
				dsCTPDP.add(ctpdp);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTPDP;
	}
	/*
	 * Lấy danh sách các chi tiết phiếu đặt phòng bởi mã phòng và lấy thời gian đặt phòng đó mới nhất trong database
	 */
	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongByMaPhong(String maTim) {
		List<ChiTietPhieuDatPhong> dsCTPDP = new ArrayList<ChiTietPhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "select top 1 * from ChiTietPhieuDatPhong where maPhong = ? order by thoiGianDat desc";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTim);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maPhieuDat = rs.getString("maPhieuDat");
				String maPhong = rs.getString("maPhong");
				Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");

				PhieuDatPhong pdp = new PhieuDatPhong(maPhieuDat);
				Phong p = new Phong(maPhong);
				ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong(pdp, p, thoiGianDat);
				
				dsCTPDP.add(ctpdp);
			}
			preparedStatement.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTPDP;
	}
	/*
	 * Thêm chi tiết phiếu đặt phòng vào trong database
	 */
	public boolean insertChiTietPhieuDatPhong(ChiTietPhieuDatPhong ctpdp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into ChiTietPhieuDatPhong values(?, ?, ?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ctpdp.getPhieuDatPhong().getMaPhieuDat());
			preparedStatement.setString(2, ctpdp.getPhong().getMaPhong());
			preparedStatement.setTimestamp(3, ctpdp.getThoiGianDat());
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
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
	
	/*
	 * Xóa chí tiết phiếu đặt phòng ra khỏi database khi bấm hủy phiếu
	 */
	public boolean deleteChiTietPhieuDatPhongByMaPhieuDat(String maPhieuDat){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        int n = 0;
        try {
            String sql = "delete from ChiTietPhieuDatPhong where maPhieuDat = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, maPhieuDat);
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            try {
            	preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
	
	/*
	 * Lấy mã phiếu đặt phòng gần nhất
	 */
	public String getMaPhieuDatCuoi() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String maPhieuDatCuoi = null;
		try {
			String sql = "select top 1 maPhieuDat from PhieuDatPhong order by maPhieuDat desc";
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				maPhieuDatCuoi = rs.getString("maPhieuDat");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maPhieuDatCuoi;
	}
}
