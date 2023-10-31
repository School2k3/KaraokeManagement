package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class KhachHang {
	private String maKhachHang;
	private String hoTenKhachHang;
	private boolean gioiTinh;
	private String soDienThoai;
	private String canCuocCongDan;

	public KhachHang() {
		super();
	}

	public KhachHang(String maKhachHang) throws Exception {
		super();
		setMaKhachHang(maKhachHang);
	}

	public KhachHang(String maKhachHang, String hoTenKhachHang, boolean gioiTinh, String soDienThoai,
			String canCuocCongDan) throws Exception {
		super();
		setMaKhachHang(maKhachHang);
		setHoTenKhachHang(hoTenKhachHang);
		setGioiTinh(gioiTinh);
		setSoDienThoai(soDienThoai);
		setCanCuocCongDan(canCuocCongDan);
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) throws Exception {
		if (!maKhachHang.trim().equals("")) {
			this.maKhachHang = maKhachHang;
		} else {
			throw new Exception("Mã khách hàng không được để rỗng");
		}
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) throws Exception {
		if (!hoTenKhachHang.trim().equals("")) {
			this.hoTenKhachHang = hoTenKhachHang;
		} else {
			throw new Exception("Họ tên khách hàng không được để rỗng");
		}
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		if (gioiTinh == true || gioiTinh == false) {
			this.gioiTinh = gioiTinh;
		}
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) throws Exception {
		if (!soDienThoai.trim().equals("")) {
			this.soDienThoai = soDienThoai;
		} else {
			throw new Exception("Số điện thoại không được để rỗng");
		}
	}

	public String getCanCuocCongDan() {
		return canCuocCongDan;
	}

	public void setCanCuocCongDan(String canCuocCongDan) throws Exception {
		if (!canCuocCongDan.trim().equals("")) {
			this.canCuocCongDan = canCuocCongDan;
		} else {
			throw new Exception("Căn cước công dân không được để rỗng");
		}
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", gioiTinh=" + gioiTinh
				+ ", soDienThoai=" + soDienThoai + ", canCuocCongDan=" + canCuocCongDan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}

	public KhachHang(ResultSet rs) throws SQLException, Exception {
		this(rs.getString("maKhachHang"), rs.getString("hoTenKhachHang"), rs.getBoolean("gioiTinh"),
				rs.getString("soDienThoai"), rs.getString("canCuocCongDan"));
	}
}
