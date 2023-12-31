package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoTenNhanVien;
	private String gioiTinh;
	private int namSinh;
	private String diaChi;
	private String soDienThoai;
	private String canCuocCongDan;
	private String matKhau;
	private String chucVu;

	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien) throws Exception {
		super();
		setMaNhanVien(maNhanVien);
	}

	public NhanVien(String maNhanVien, String soDienThoai) throws Exception {
		super();
		setMaNhanVien(maNhanVien);
		setSoDienThoai(soDienThoai);
	}
	
	public NhanVien(String maNhanVien, String hoTenNhanVien, String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String chucVu) throws Exception {
		super();
		setMaNhanVien(maNhanVien);
		setHoTenNhanVien(hoTenNhanVien);
		setGioiTinh(gioiTinh);
		setNamSinh(namSinh);
		setDiaChi(diaChi);
		setSoDienThoai(soDienThoai);
		setCanCuocCongDan(canCuocCongDan);
		setChucVu(chucVu);
	}

	public NhanVien(String maNhanVien, String hoTenNhanVien, String gioiTinh, int namSinh, String diaChi,
			String soDienThoai, String canCuocCongDan, String matKhau, String chucVu) throws Exception {
		super();
		setMaNhanVien(maNhanVien);
		setHoTenNhanVien(hoTenNhanVien);
		setGioiTinh(gioiTinh);
		setNamSinh(namSinh);
		setDiaChi(diaChi);
		setSoDienThoai(soDienThoai);
		setCanCuocCongDan(canCuocCongDan);
		setMatKhau(matKhau);
		setChucVu(chucVu);
	}
	public NhanVien(ResultSet rs) throws SQLException, Exception {
		this(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"), rs.getString("gioiTinh"), rs.getInt("namSinh"),
				rs.getString("diaChi"), rs.getString("soDienThoai"), rs.getString("canCuocCongDan"),
				rs.getString("chucVu"));
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) throws Exception {
		if (!maNhanVien.trim().equals("")) {
			this.maNhanVien = maNhanVien;
		} else {
			throw new Exception("Mã nhân viên không được để rỗng");
		}
	}

	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}

	public void setHoTenNhanVien(String hoTenNhanVien) throws Exception {
		if (!hoTenNhanVien.trim().equals("")) {
			this.hoTenNhanVien = hoTenNhanVien;
		} else {
			throw new Exception("Họ tên nhân viên không được để rỗng");
		}
	}

	public String isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		if (!gioiTinh.trim().equals("")) {
			this.gioiTinh = gioiTinh;
		}
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) throws Exception {
		if (namSinh < 2006) {
			this.namSinh = namSinh;
		} else {
			throw new Exception("Nhân viên phải trên 18 tuổi");
		}
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) throws Exception {
		if (!diaChi.trim().equals("")) {
			this.diaChi = diaChi;
		} else {
			throw new Exception("Địa chỉ không được để rỗng");
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

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) throws Exception {
		if (!matKhau.trim().equals("")) {
			this.matKhau = matKhau;
		} else {
			throw new Exception("Mật khẩu không được để rỗng");
		}
	}

	public String isChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		if (!chucVu.trim().equals("")) {
			this.chucVu = chucVu;
		}
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", namSinh=" + namSinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", canCuocCongDan="
				+ canCuocCongDan + ", matKhau=" + matKhau + ", chucVu=" + chucVu + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

}
