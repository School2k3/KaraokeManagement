package entity;

import java.sql.Date;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private DichVu dichVu;
	private double donGia;
	private Date thoiGianBatDau;
	private Date thoiGianKetThuc;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(HoaDon hoaDon, DichVu dichVu, double donGia, Date thoiGianBatDau,
			Date thoiGianKetThuc) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
		this.donGia = donGia;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public DichVu getDichVu() {
		return dichVu;
	}
	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) throws Exception {
		if(donGia>=0)
			this.donGia = donGia;
			else
				throw new Exception("Đơn giá không được nhỏ hơn 0!");
	}
	public Date getThoiGianBatDau() {
		return thoiGianBatDau;
	}
	public void setThoiGianBatDau(Date thoiGianBatDau) throws Exception {
		if (thoiGianBatDau != null) {
			this.thoiGianBatDau = thoiGianBatDau;
		}
			else {
				throw new Exception("Thời gian bắt đầu không được để trống");
			}
	}
	public Date getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(Date thoiGianKetThuc) throws Exception {
		if (thoiGianKetThuc != null) {
			this.thoiGianKetThuc = thoiGianKetThuc;
		}
			else {
				throw new Exception("Thời gian kết thúc không được để trống");
			}
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", donGia=" + donGia + ", thoiGianBatDau=" + thoiGianBatDau
				+ ", thoiGianKetThuc=" + thoiGianKetThuc + "]";
	}
	
}
