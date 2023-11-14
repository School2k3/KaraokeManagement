package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	private static KhachHang_DAO instance = new KhachHang_DAO();

	public static KhachHang_DAO getInstance() {
		return instance;
	}

	public ArrayList<KhachHang> getAllTableKhachHang() throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from [dbo].[KhachHang]";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String soDienThoai = rs.getString(4);
				String canCuocCongDan = rs.getString(5);

				KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, soDienThoai, canCuocCongDan);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	/***
	 * Tìm 1 textField
	 * @param maKhachHang
	 * @return
	 * @throws Exception
	 */
	// Tìm kiếm khách hàng theo mã khách hàng
	public ArrayList<KhachHang> getListKhachHangByMaKhachHang(String maKhachHang) throws Exception {
		ArrayList<KhachHang> arlKhachHangByMa = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "%" + maKhachHang + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMa.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMa;
	}
	
	// Tìm kiếm khách hàng theo mã khách hàng
	public KhachHang getKhachHangByMaKhachHang(String maKhachHang) throws Exception {
		KhachHang khachHang = null;
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maKhachHang);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				khachHang = new KhachHang(rs);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}

	// Tìm kiếm theo họ tên khách hàng
	public ArrayList<KhachHang> getListKhachHangByHoTenKhachHang(String hoTenKhachHang) throws Exception {
		ArrayList<KhachHang> arlKhachHangByName = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "%" + hoTenKhachHang + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByName.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByName;
	}

	// Tìm kiếm theo giới tính
	public ArrayList<KhachHang> getListKhachHangByGioiTinh(String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHangByGioiTinh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByGioiTinh.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return arlKhachHangByGioiTinh;
	}

	// Tìm kiếm theo số điện thoại
	public ArrayList<KhachHang> getListKhachHangBySoDienThoai(String soDienThoai) throws Exception {
		ArrayList<KhachHang> arlKhachHangBySoDienThoai = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangBySoDienThoai.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangBySoDienThoai;
	}

	// Tìm kiếm theo căn cước công dân
	public ArrayList<KhachHang> getListKhachHangByCCCD(String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHangByCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByCCCD;
	}

	/***
	 * Tìm theo 2 textField
	 * @param maKhachHang
	 * @param hoTenKhachHang
	 * @return
	 * @throws Exception
	 */
	// Tìm kiếm theo Mã khách hàng và họ tên khách hàng
	public ArrayList<KhachHang> getListKhachHangByMaAndName(String maKhachHang, String hoTenKhachHang)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByMaAndName = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMaAndName.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMaAndName;
	}

	// Tìm kiếm theo mã khách hàng và giới tính
	public ArrayList<KhachHang> getListKhachHangByMaVaGioiTinh(String maKhachHang, String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHangByMaVaGioiTinh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMaVaGioiTinh.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMaVaGioiTinh;
	}

	// Tìm kiếm theo mã khách hàng và số điện thoại
	public ArrayList<KhachHang> getListKhachHangByMaVaSoDienThoai(String maKhachHang, String soDienThoai)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByMaVaSoDienThoai = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMaVaSoDienThoai.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMaVaSoDienThoai;
	}

	// Tìm kiếm theo mã khách hàng và căn cước công dân
	public ArrayList<KhachHang> getListKhachHangByMaVaCCCD(String maKhachHang, String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHangByMaVaCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMaVaCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMaVaCCCD;
	}

	// Tìm kiếm theo họ tên khách hàng và giới tính
	public ArrayList<KhachHang> getListKhachHangByNameVaGioiTinh(String hoTenKhachHang, String gioiTinh)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByNameAndGioiTinh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByNameAndGioiTinh.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByNameAndGioiTinh;
	}

	// Tìm kiếm theo họ tên khách hàng và số điện thoại
	public ArrayList<KhachHang> getListKhachHangByNameVaSoDienThoai(String hoTenKhachHang, String soDienThoai)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByNameVaSoDienThoai = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, "%" + soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByNameVaSoDienThoai.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByNameVaSoDienThoai;
	}

	// Tìm kiếm theo họ tên khách hàng và căn cước công dân
	public ArrayList<KhachHang> getListKhachHangByNameVaCCCD(String hoTenKhachHang, String canCuocCongDan)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByNameVaCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByNameVaCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByNameVaCCCD;
	}

	// Tìm kiếm theo giới tính và số điện thoại
	public ArrayList<KhachHang> getListKhachHangByGioiTinhVaSoDienThoai(String gioiTinh, String soDienThoai)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByGioiTinhVaSoDienThoai = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + gioiTinh + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByGioiTinhVaSoDienThoai.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByGioiTinhVaSoDienThoai;
	}

	// Tìm kiếm theo căn cước và giới tính
	public ArrayList<KhachHang> getListKhachHangByGioiTinhVaCCCD(String canCuocCongDan, String gioiTinh)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangByGioiTinhVaCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where canCuocCongDan like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, canCuocCongDan + "%");
			preparedStatement.setNString(2, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByGioiTinhVaCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByGioiTinhVaCCCD;
	}

	// Tìm kiếm theo số điện thoại và căn cước công dân
	public ArrayList<KhachHang> getListKhachHangBySoDienThoaiVaCCCD(String soDienThoai, String canCuocCongDan)
			throws Exception {
		ArrayList<KhachHang> arlKhachHangBySoDienThoaiVaCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, soDienThoai + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangBySoDienThoaiVaCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangBySoDienThoaiVaCCCD;
	}

	/***
	 * Tìm 3 textField
	 * @param maKhachHang
	 * @param hoTenKhachHang
	 * @param soDienThoai
	 * @return
	 * @throws Exception
	 */
	// Tìm kiếm theo Mã khách hàng, họ tên khách hàng và số điện thoại
	public ArrayList<KhachHang> getListKhachHangByMaNameAndSoDienThoai(String maKhachHang, String hoTenKhachHang,
			String soDienThoai) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã khách hàng, họ tên khách hàng và căn cước
	public ArrayList<KhachHang> getListKhachHangByMaNameAndCCCD(String maKhachHang, String hoTenKhachHang,
			String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã khách hàng, họ tên khách hàng và giới tính
	public ArrayList<KhachHang> getListKhachHangByMaNameAndGioiTinh(String maKhachHang, String hoTenKhachHang,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã khách hàng, số điện thoại và giới tính
	public ArrayList<KhachHang> getListKhachHangByMaSoDienThoaiVaGioiTinh(String maKhachHang, String soDienThoai,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and soDienThoai like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã khách hàng, căn cước và giới tính
	public ArrayList<KhachHang> getListKhachHangByMaCCCDVaGioiTinh(String maKhachHang, String canCuocCongDan,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and canCuocCongDan like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã khách hàng, số điện thoại và căn cước
	public ArrayList<KhachHang> getListKhachHangByMaSoDienThoaiAndCCCD(String maKhachHang, String soDienThoai,
			String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHangByMaSoDienThoaiAndCCCD = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + soDienThoai + "%");
			preparedStatement.setNString(3, "%" + canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHangByMaSoDienThoaiAndCCCD.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHangByMaSoDienThoaiAndCCCD;
	}

	// Tìm kiếm theo họ tên khách hàng, số điện thoại và căn cước
	public ArrayList<KhachHang> getListKhachHangByNameSoDienThoaiAndCCCD(String hoTenKhachHang, String soDienThoai,
			String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo họ tên khách hàng, số điện thoại và giới tính
	public ArrayList<KhachHang> getListKhachHangByNameCCCDAndGioiTinh(String hoTenKhachHang, String canCuocCongDan,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and canCuocCongDan like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%"+ gioiTinh+ "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}
	
	// Tìm họ tên khách hàng, căn cước và giới tính
	public ArrayList<KhachHang> getListKhachHangByNameSoDienThoaiAndGioiTinh(String hoTenKhachHang, String soDienThoai,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and soDienThoai like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%"+ gioiTinh+ "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}
	
	// Tìm theo giới tính, căn cước, số điện thoại
	public ArrayList<KhachHang> getListKhachHangByCCCDGioiTinhAndSoDienThoai(String canCuocCongDan, String soDienThoai,
			String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where canCuocCongDan like ? and soDienThoai like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, canCuocCongDan + "%");
			preparedStatement.setNString(2, soDienThoai + "%");
			preparedStatement.setNString(3, "%"+ gioiTinh+ "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}
	
	/***
	 * Tìm 4 textField
	 * 
	 * @return
	 * @throws Exception
	 */
	// Tìm kiếm theo mã, họ tên, số điện thoại, căn cước
	public ArrayList<KhachHang> getListKhachHangByMaNameSoDienThoaiAndCCCD(String maKhachHang, String hoTenKhachHang,
			String soDienThoai, String canCuocCongDan) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and soDienThoai like ? and canCuocCongDan like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã, họ tên, số điện thoại, giới tính
	public ArrayList<KhachHang> getListKhachHangByMaNameSoDienThoaiAndGioiTinh(String maKhachHang,
			String hoTenKhachHang, String soDienThoai, String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and soDienThoai like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã, họ tên, căn cước, giới tính
	public ArrayList<KhachHang> getListKhachHangByMaNameCCCDAndGioiTinh(String maKhachHang, String hoTenKhachHang,
			String canCuocCongDan, String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and canCuocCongDan like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, canCuocCongDan + "%");
			preparedStatement.setNString(4, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo họ tên, căn cước, giới tính, số điện thoại
	public ArrayList<KhachHang> getListKhachHangByNameCCCDSoDienThoaiAndGioiTinh(String hoTenKhachHang,
			String canCuocCongDan, String gioiTinh, String soDienThoai) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where hoTenKhachHang like ? and canCuocCongDan like ? and gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	// Tìm kiếm theo mã, số điện thoại, căn cước, giới tính
	public ArrayList<KhachHang> getListKhachHangByMaSoDienThoaiCCCDAndGioiTinh(String maKhachHang,
			String canCuocCongDan, String gioiTinh, String soDienThoai) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and canCuocCongDan like ? and gioiTinh like ? and soDienThoai like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, canCuocCongDan + "%");
			preparedStatement.setNString(3, "%" + gioiTinh + "%");
			preparedStatement.setNString(4, soDienThoai + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	/***
	 * Tìm tất cả
	 * 
	 * @param maKhachHang
	 * @param hoTenKhachHang
	 * @param soDienThoai
	 * @param canCuocCongDan
	 * @param gioiTinh
	 * @return
	 * @throws Exception
	 */
	// Tìm kiếm tất
	public ArrayList<KhachHang> getListKhachHangByMaNameSoDienThoaiCCCDAndGioiTinh(String maKhachHang,
			String hoTenKhachHang, String soDienThoai, String canCuocCongDan, String gioiTinh) throws Exception {
		ArrayList<KhachHang> arlKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from [dbo].[KhachHang] where maKhachHang like ? and hoTenKhachHang like ? and soDienThoai like ? and canCuocCongDan like ? and gioiTinh like ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setNString(1, "%" + maKhachHang + "%");
			preparedStatement.setNString(2, "%" + hoTenKhachHang + "%");
			preparedStatement.setNString(3, soDienThoai + "%");
			preparedStatement.setNString(4, canCuocCongDan + "%");
			preparedStatement.setNString(5, "%" + gioiTinh + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang(rs);
				arlKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arlKhachHang;
	}

	public boolean insert(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "insert into [dbo].[KhachHang] (maKhachHang, hoTenKhachHang, gioiTinh, soDienThoai, canCuocCongDan)"
					+ "values (?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, khachHang.getMaKhachHang());
			preparedStatement.setString(2, khachHang.getHoTenKhachHang());
			preparedStatement.setString(3, khachHang.isGioiTinh());
			preparedStatement.setString(4, khachHang.getSoDienThoai());
			preparedStatement.setString(5, khachHang.getCanCuocCongDan());
			n = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean update(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			String sql = "update [dbo].[KhachHang] set hoTenKhachHang = ?, gioiTinh = ?, soDienThoai = ?, canCuocCongDan = ? where maKhachHang = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, khachHang.getHoTenKhachHang());
			preparedStatement.setString(2, khachHang.isGioiTinh());
			preparedStatement.setString(3, khachHang.getSoDienThoai());
			preparedStatement.setString(4, khachHang.getCanCuocCongDan());
			preparedStatement.setString(5, khachHang.getMaKhachHang());
			n = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
}