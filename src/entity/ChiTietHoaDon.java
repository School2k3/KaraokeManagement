package entity;

import java.sql.Timestamp;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private DichVu dichVu;
	private int soLuong;
	private double donGia;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(HoaDon hoaDon, DichVu dichVu, int soLuong, double donGia) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
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
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) throws Exception {
		if(soLuong > 0)
			this.soLuong = soLuong;
			else
				throw new Exception("Số lượng phải lớn hơn 0!");
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
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", dichVu=" + dichVu + ", soLuong=" + soLuong + ", donGia=" + donGia
				+ "]";
	}


	
	
}
