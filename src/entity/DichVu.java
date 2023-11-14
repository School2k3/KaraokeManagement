package entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import entity.LoaiDichVu;
public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private int soLuongTon;
	private LoaiDichVu loaiDichVu;
	private double donGia;
	public DichVu() {
		super();
	}
	public DichVu(String maDichVu)throws Exception {
		setMaDichVu(maDichVu);
	}
	
	public DichVu(String maDichVu, String tenDichVu) throws Exception {
		super();
		setMaDichVu(maDichVu);
		setTenDichVu(tenDichVu);
	}
	public DichVu(String maDichVu, String tenDichVu, int soLuongTon,
			LoaiDichVu loaiDichVu, double donGia) throws Exception {
		setMaDichVu(maDichVu);
		setTenDichVu(tenDichVu);
		setSoLuongTon(soLuongTon);
		setLoaiDichVu(loaiDichVu);
		setDonGia(donGia);
	}
	public String getMaDichVu() {
		return maDichVu;
	}
	public String getTenDichVu() {
		return tenDichVu;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public LoaiDichVu getLoaiDichVu() {
		return loaiDichVu;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setMaDichVu(String maDichVu) throws Exception {
		this.maDichVu = maDichVu;
	}
	public void setTenDichVu(String tenDichVu) throws Exception {
			this.tenDichVu = tenDichVu;
	}
	public void setSoLuongTon(int soLuongTon)throws Exception {
		this.soLuongTon = soLuongTon;

	}
	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
			this.loaiDichVu = loaiDichVu;

	}
	public void setDonGia(double donGia)throws Exception {
			this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu
				+ ", soLuongTon=" + soLuongTon + ", loaiDichVu=" + loaiDichVu
				+ ", donGia=" + donGia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maDichVu == null) ? 0 : maDichVu.hashCode());
		result = prime * result
				+ ((tenDichVu == null) ? 0 : tenDichVu.hashCode());
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
		DichVu other = (DichVu) obj;
		if (maDichVu == null) {
			if (other.maDichVu != null)
				return false;
		} else if (!maDichVu.equals(other.maDichVu))
			return false;
		if (tenDichVu == null) {
			if (other.tenDichVu != null)
				return false;
		} else if (!tenDichVu.equals(other.tenDichVu))
			return false;
		return true;
	}
}
