package entity;

import java.sql.Timestamp;

public class PhieuDatPhong {
	private String maPhieuDat;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Timestamp ngayLap;
	private String trangThai;
	public PhieuDatPhong() {
		super();
	}
	public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, NhanVien nhanVien, Timestamp ngayLap, String trangThai) {
		super();
		this.maPhieuDat = maPhieuDat;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
		this.trangThai = trangThai;
	}
	public PhieuDatPhong(String maPhieuDat) {
		super();
		this.maPhieuDat = maPhieuDat;
	}
	public String getMaPhieuDat() {
		return maPhieuDat;
	}
	public void setMaPhieuDat(String maPhieuDat) throws Exception {
		if (maPhieuDat != null) {
			this.maPhieuDat = maPhieuDat;
		}
			else {
				throw new Exception("Mã phiếu đặt phòng không được để trống");
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
	public Timestamp getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Timestamp ngayLap) throws Exception {
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
		return "PhieuDatPhong [maPhieuDat=" + maPhieuDat + ", ngayLap=" + ngayLap + ", trangThai=" + trangThai + "]";
	}
	
}
