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
import entity.NhanVien;
import entity.Phong;

public class NhanVien_DAO {
	private static NhanVien_DAO instance = new NhanVien_DAO();

	public static NhanVien_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<NhanVien> getAllTableNhanVien() throws Exception{
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from [dbo].[NhanVien]";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoTenNhanVien = rs.getString(2);
				String gioiTinh = rs.getString(3);
				int namSinh = rs.getInt(4);
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String canCuocCongDan = rs.getString(7);
				String matKhau = rs.getString(8);
				String chucVu = rs.getString(9);
				
				NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, matKhau, chucVu);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return arlNhanVien;
	}
	
	public NhanVien getNhanVienByMaNhanVien(String maNV) throws Exception{
		NhanVien nhanVien = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from [dbo].[NhanVien] where maNhanVien = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maNV);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoTenNhanVien = rs.getString(2);
				String gioiTinh = rs.getString(3);
				int namSinh = rs.getInt(4);
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String canCuocCongDan = rs.getString(7);
				String matKhau = rs.getString(8);
				String chucVu = rs.getString(9);
				
				nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, matKhau, chucVu);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanVien;
	}
	
	/*
	 * Hàm để kiểm tra thông tin đăng nhập bao gồm số diện thoại nhân viên và mật khẩu
	 */
	public NhanVien getAllTableNhanVienBySoDienThoaiAndMatKhau(String sdt, String pass) throws Exception {
		NhanVien nv = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from NhanVien where soDienThoai = '" + sdt + "' and matKhau = '" + pass + "'";
            Statement statement = con.createStatement();
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);
            // Duyệt trên kết quả trả về
            while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String hoTenNhanVien = rs.getString(2);
				String gioiTinh = rs.getString(3);
				int namSinh = rs.getInt(4);
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String canCuocCongDan = rs.getString(7);
				String matKhau = rs.getString(8);
				String chucVu = rs.getString(9);
                nv = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, matKhau, chucVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
}
