package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private String tenPhong;
	private String trangThai;
	private LoaiPhong loaiPhong;
	private double donGia;
	public Phong() {
		super();
	}
	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}
	
	public Phong(String maPhong, String tenPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
	}
	
	public Phong(String maPhong, double donGia) {
		super();
		this.maPhong = maPhong;
		this.donGia = donGia;
	}
	public Phong(String maPhong, String tenPhong, String trangThai, LoaiPhong loaiPhong, double donGia) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.loaiPhong = loaiPhong;
		this.donGia = donGia;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) throws Exception{
			this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) throws Exception{
			this.tenPhong = tenPhong;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) throws Exception{
			this.trangThai = trangThai;
	}
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai + ", loaiPhong="
				+ loaiPhong + ", donGia=" + donGia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhong == null) ? 0 : maPhong.hashCode());
		result = prime * result
				+ ((tenPhong == null) ? 0 : tenPhong.hashCode());
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
		Phong other = (Phong) obj;
		if (maPhong == null) {
			if (other.maPhong != null)
				return false;
		} else if (!maPhong.equals(other.maPhong))
			return false;
		if (tenPhong == null) {
			if (other.tenPhong != null)
				return false;
		} else if (!tenPhong.equals(other.tenPhong))
			return false;
		return true;
	}
	
	
}
