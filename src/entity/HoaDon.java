package entity;

import java.sql.Date;

public class HoaDon {
	private String maHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Phong phong;
	private Date ngayLap;
	private String trangThai;
	public HoaDon() {
		super();
	}
	public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Phong phong, Date ngayLap,
			String trangThai) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.phong = phong;
		this.ngayLap = ngayLap;
		this.trangThai = trangThai;
	}
	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) throws Exception {
		if (maHoaDon != null) {
			this.maHoaDon = maHoaDon;
		}
			else {
				throw new Exception("Mã hóa đơn không được để trống");
			}
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) throws Exception {
		if (ngayLap != null) {
			this.ngayLap = ngayLap;
		}
			else {
				throw new Exception("Ngày lập không được để trống");
			}
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", trangThai=" + trangThai + "]";
	}
	
}
