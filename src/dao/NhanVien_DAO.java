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
	
	public ArrayList<NhanVien> getAllTableNhanVienTruMatKhau() throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from [dbo].[NhanVien]";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
		}
		return arlNhanVien;
	}

	/***
	 * Hàm thêm nhân viên
	 * 
	 * @param nhanVien
	 * @return n > 0
	 */

	public boolean insert(NhanVien nhanVien) {
		/***
		 * Kết nối vào database Tạo biến PreparedStatement = null; Tạo biến n = 0 kiểu
		 * int Chạy lệnh try catch bắt lỗi ngoại lệ của SQL
		 */
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			/***
			 * Viết câu lệnh sql vào biến kiểu chuỗi Kết nối câu lệnh vào database Nhận các
			 * biến kiểu chuỗi thông qua việc lấy từ Entity KhachHang Biến n đảm nhiệm việc
			 * cập nhật câu lệnh
			 */
			String sql = "insert into [dbo].[NhanVien] (maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, matKhau, chucVu)"
					+ "values (?,?,?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, nhanVien.getMaNhanVien());
			preparedStatement.setString(2, nhanVien.getHoTenNhanVien());
			preparedStatement.setString(3, nhanVien.isGioiTinh());
			preparedStatement.setInt(4, nhanVien.getNamSinh());
			preparedStatement.setString(5, nhanVien.getDiaChi());
			preparedStatement.setString(6, nhanVien.getSoDienThoai());
			preparedStatement.setString(7, nhanVien.getCanCuocCongDan());
			preparedStatement.setString(8, nhanVien.getMatKhau());
			preparedStatement.setString(9, nhanVien.isChucVu());

			n = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	/***
	 * Hàm cập nhật nhân viên
	 * 
	 * @param nhanVien
	 * @return n > 0
	 */
	public boolean update(NhanVien nhanVien) {
		/***
		 * Kết nối vào database Tạo biến PreparedStatement = null; Tạo biến n = 0 kiểu
		 * int Chạy lệnh try catch bắt lỗi ngoại lệ của SQL
		 */
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			/***
			 * Viết câu lệnh sql vào biến kiểu chuỗi Kết nối câu lệnh vào database Nhận các
			 * biến kiểu chuỗi thông qua việc lấy từ Entity KhachHang Biến n đảm nhiệm việc
			 * cập nhật câu lệnh
			 */
			String sql = "update [dbo].[NhanVien] set hoTenNhanVien = ?, gioiTinh = ?, namSinh = ?, diaChi = ?, soDienThoai = ?, canCuocCongDan = ?, matKhau = ?, chucVu = ? where maNhanVien = ?";
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, nhanVien.getHoTenNhanVien());
			preparedStatement.setString(2, nhanVien.isGioiTinh());
			preparedStatement.setInt(3, nhanVien.getNamSinh());
			preparedStatement.setString(4, nhanVien.getDiaChi());
			preparedStatement.setString(5, nhanVien.getSoDienThoai());
			preparedStatement.setString(6, nhanVien.getCanCuocCongDan());
			preparedStatement.setString(7, nhanVien.getMatKhau());
			preparedStatement.setString(8, nhanVien.isChucVu());
			preparedStatement.setString(9, nhanVien.getMaNhanVien());
			n = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	// Tìm kiếm tất cả
	public ArrayList<NhanVien> getListNhanVienByAll(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, soDienThoai + "%");
			preparedStatement.setNString(7, canCuocCongDan + "%");
			preparedStatement.setNString(8, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	/***
	 * Tìm 7 field
	 * 
	 * @param maNhanVien
	 * @param hoTenNhanVien
	 * @param gioiTinh
	 * @param namSinh
	 * @param diaChi
	 * @param soDienThoai
	 * @param canCuocCongDan
	 * @return
	 * @throws Exception
	 */

	// Trừ chức vụ
	public ArrayList<NhanVien> getListNhanVienTruChucVu(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, soDienThoai + "%");
			preparedStatement.setNString(7, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước
	public ArrayList<NhanVien> getListNhanVienTruCanCuoc(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, soDienThoai + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoai(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruDiaChi(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			int namSinh, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh
	public ArrayList<NhanVien> getListNhanVienTruNamSinh(String maNhanVien, String hoTenNhanVien, String gioiTinh,
			String diaChi, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ giới tính
	public ArrayList<NhanVien> getListNhanVienTruGioiTinh(String maNhanVien, String hoTenNhanVien, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ họ tên
	public ArrayList<NhanVien> getListNhanVienTruHoTen(String maNhanVien, String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ mã
	public ArrayList<NhanVien> getListNhanVienTruMa(String hoTenNhanVien, String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			preparedStatement.setNString(7, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	/***
	 * Tìm 6 field
	 */
	// Trừ chức vụ, căn cước
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuoc(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoai(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoai(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndGioiTinh(String maNhanVien, String hoTenNhanVien, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndHoTen(String maNhanVien, String gioiTinh, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndMa(String hoTenNhanVien, String gioiTinh, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			int namSinh, String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndHoTen(String maNhanVien, String gioiTinh, int namSinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndMa(String hoTenNhanVien, String gioiTinh, int namSinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, giới tính
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			int namSinh, String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndHoTen(String maNhanVien, String gioiTinh, int namSinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndMa(String hoTenNhanVien, String gioiTinh, int namSinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndGioiTinh(String maNhanVien, String hoTenNhanVien, int namSinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndHoTen(String maNhanVien, String gioiTinh, int namSinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndMa(String hoTenNhanVien, String gioiTinh, int namSinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			String diaChi, String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndHoTen(String maNhanVien, String gioiTinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndMa(String hoTenNhanVien, String gioiTinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruGioiTinhAndHoTen(String maNhanVien, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruGioiTinhAndMa(String hoTenNhanVien, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruHoTenAndMa(String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			preparedStatement.setNString(6, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoai(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			int namSinh, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			int namSinh, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String diaChi, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndHoTenAndMa(String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChi(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			int namSinh, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String diaChi, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndHoTenAndMa(String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinh(String maNhanVien, String hoTenNhanVien,
			String gioiTinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndHoTenAndMa(String gioiTinh, int namSinh, String diaChi,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndGioiTinh(String maNhanVien, String hoTenNhanVien,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndHoTenAndMa(String gioiTinh, int namSinh, String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndGioiTinhAndHoTen(String maNhanVien, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndGioiTinhAndMa(String hoTenNhanVien, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndHoTenAndMa(String gioiTinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruGioiTinhAndHoTenAndMa(int namSinh, String diaChi, String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	/***
	 * Tìm 4 field
	 */
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChi(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setInt(4, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndHoTen(String maNhanVien,
			String gioiTinh, int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndMa(String hoTenNhanVien,
			String gioiTinh, int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndHoTen(String maNhanVien, String gioiTinh,
			int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndMa(String hoTenNhanVien, String gioiTinh,
			int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndHoTenAndMa(String gioiTinh, int namSinh,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndHoTen(String maNhanVien,
			String gioiTinh, int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndMa(String hoTenNhanVien,
			String gioiTinh, int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndHoTenAndMa(String gioiTinh, int namSinh,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndHoTenAndMa(String gioiTinh, int namSinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndHoTen(String maNhanVien, String diaChi,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien, String diaChi,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndHoTenAndMa(String gioiTinh, String diaChi,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndHoTen(String maNhanVien,
			String gioiTinh, int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndMa(String hoTenNhanVien,
			String gioiTinh, int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndHoTen(String maNhanVien,
			int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoai, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndMa(String hoTenNhanVien,
			int namSinh, String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndHoTenAndMa(String gioiTinh, int namSinh,
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndHoTen(String maNhanVien, String gioiTinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndMa(String hoTenNhanVien, String gioiTinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndHoTenAndMa(String gioiTinh, int namSinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndGioiTinhAndHoTen(String maNhanVien, String diaChi,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien, String diaChi,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndNamSinhAndHoTenAndMa(String gioiTinh, String diaChi,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndGioiTinhAndHoTenAndMa(int namSinh, String diaChi,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(String maNhanVien, int namSinh,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndMa(String hoTenNhanVien, int namSinh,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndHoTenAndMa(String gioiTinh, int namSinh,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String diaChi, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndHoTenAndMa(String gioiTinh, String diaChi,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndGioiTinhAndHoTenAndMa(int namSinh, String diaChi,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh, String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndGioiTinhAndHoTenAndMa(int namSinh, String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruNamSinhAndGioiTinhAndHoTenAndMa(String diaChi, String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	/***
	 * Trừ 5 field
	 */
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinh(String maNhanVien,
			String hoTenNhanVien, String gioiTinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(3, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndHoTen(String maNhanVien,
			String gioiTinh, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndMa(String hoTenNhanVien,
			String gioiTinh, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setInt(3, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndHoTen(String maNhanVien,
			int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndMa(String hoTenNhanVien,
			int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndHoTenAndMa(String gioiTinh,
			int namSinh, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndHoTen(String maNhanVien,
			int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndMa(String hoTenNhanVien,
			int namSinh, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndHoTenAndMa(String gioiTinh, int namSinh,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String diaChi, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndHoTenAndMa(String gioiTinh, String diaChi,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndHoTenAndMa(int namSinh, String diaChi,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndHoTen(String maNhanVien,
			int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndMa(String hoTenNhanVien,
			int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndHoTenAndMa(String gioiTinh,
			int namSinh, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndHoTenAndMa(int namSinh,
			String diaChi, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndHoTenAndMa(int namSinh,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndHoTenAndMa(String diaChi,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, sô điện thoại, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(String maNhanVien,
			String hoTenNhanVien, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(String maNhanVien,
			String gioiTinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndMa(String hoTenNhanVien,
			String gioiTinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(String maNhanVien,
			int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndMa(String hoTenNhanVien,
			int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndHoTenAndMa(String gioiTinh,
			int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndHoTenAndMa(int namSinh,
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndHoTenAndMa(int namSinh,
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(String hoTenNhanVien,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(int namSinh,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ số điện thoại, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(String diaChi,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(String soDienThoai,
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where soDienThoai like ? and canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, soDienThoai + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(
			String maNhanVien, String hoTenNhanVien) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and hoTenNhanVien like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + hoTenNhanVien + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(
			String maNhanVien, String gioiTinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndMa(
			String hoTenNhanVien, String gioiTinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(
			String maNhanVien, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndMa(
			String hoTenNhanVien, int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setInt(2, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndHoTenAndMa(String gioiTinh,
			int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setInt(2, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(
			String maNhanVien, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(
			String hoTenNhanVien, String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, số điện thoại, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndHoTenAndMa(int namSinh,
			String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTen(
			String maNhanVien, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndMa(
			String hoTenNhanVien, String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndHoTenAndMa(int namSinh,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, căn cước, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndHoTenAndMa(String diaChi,
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(
			String maNhanVien, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(
			String hoTenNhanVien, String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(String gioiTinh,
			String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndHoTenAndMa(int namSinh,
			String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, số điện thoại, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(String diaChi,
			String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ chức vụ, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(String soDienThoai,
			String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, soDienThoai + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}

	// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(String maNhanVien,
			String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, sô điện thoại, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(
			String hoTenNhanVien, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(
			String gioiTinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, số điện thoại, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(
			int namSinh, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where and namSinh = ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, số điện thoại, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String diaChi, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String soDienThoai, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where soDienThoai like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, soDienThoai + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String canCuocCongDan, String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where canCuocCongDan like ? and chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, canCuocCongDan + "%");
			preparedStatement.setNString(2, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	
	/***
	 * Tìm 1 field (Trừ 7 field) 
	 */
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(
			String maNhanVien) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where maNhanVien like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maNhanVien + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(
			String hoTenNhanVien) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where hoTenNhanVien like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenNhanVien + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(
			String gioiTinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(
			int namSinh) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where namSinh = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, namSinh);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String diaChi) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where diaChi like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + diaChi + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String soDienThoai) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String canCuocCongDan) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
	// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
	public ArrayList<NhanVien> getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(
			String chucVu) throws Exception {
		ArrayList<NhanVien> arlNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai, canCuocCongDan, chucVu from [dbo].[NhanVien] where chucVu like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + chucVu + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien(rs);
				arlNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlNhanVien;
	}
}
