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
		if(!maPhong.trim().equals("")) {
			this.maPhong = maPhong;
		}else {
			throw new Exception("Mã phòng không được để rỗng");
		}
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) throws Exception{
		if(!tenPhong.trim().equals("")) {
			this.tenPhong = tenPhong;
		}else {
			throw new Exception("Tên phòng không được để rỗng");
		}
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) throws Exception{
		if(!trangThai.trim().equals("")) {
			this.trangThai = trangThai;
		}else {
			throw new Exception("Trạng thái không được để rỗng");
		}
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
		return Objects.hash(maPhong);
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
		return Objects.equals(maPhong, other.maPhong);
	}
	
	
}
