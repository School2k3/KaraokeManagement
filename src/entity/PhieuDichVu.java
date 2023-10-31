package entity;

import java.sql.Date;

public class PhieuDichVu {
	private String maPhieuDichVu;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Date ngayLap;
	private String trangThai;

	public String getMaPhieuDichVu() {
		return maPhieuDichVu;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setMaPhieuDichVu(String maPhieuDichVu) throws Exception {
		if (!maPhieuDichVu.trim().equals(""))
			this.maPhieuDichVu = maPhieuDichVu;
		else
			throw new Exception("Lỗi mã phiếu dịch vụ không được rỗng");
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public void setTrangThai(String trangThai) throws Exception {
		if(!trangThai.trim().equals(""))
		this.trangThai = trangThai;
		else
			throw new Exception("Lỗi trạng thái không được rỗng");
	}

	public PhieuDichVu(String maPhieuDichVu, KhachHang khachHang,
			NhanVien nhanVien, Date ngayLap, String trangThai) throws Exception {
		setMaPhieuDichVu(maPhieuDichVu);
		setKhachHang(khachHang);
		setNhanVien(nhanVien);
		setNgayLap(ngayLap);
		setTrangThai(trangThai);
	}

	public PhieuDichVu(String maPhieuDichVu) throws Exception {
		setMaPhieuDichVu(maPhieuDichVu);
	}

	public PhieuDichVu() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maPhieuDichVu == null) ? 0 : maPhieuDichVu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDichVu other = (PhieuDichVu) obj;
		if (maPhieuDichVu == null) {
			if (other.maPhieuDichVu != null)
				return false;
		} else if (!maPhieuDichVu.equals(other.maPhieuDichVu))
			return false;
		return true;
	}
	
}
