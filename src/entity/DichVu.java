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
		if(maDichVu.trim().equals(""))
		this.maDichVu = maDichVu;
		else
			throw new Exception("Lỗi mã dịch vụ không được rỗng!");
	}
	public void setTenDichVu(String tenDichVu) throws Exception {
		if(tenDichVu.trim().equals(""))
			this.tenDichVu = tenDichVu;
			else
				throw new Exception("Lỗi tên dịch vụ không được rỗng!");
	}
	public void setSoLuongTon(int soLuongTon)throws Exception {
		if(soLuongTon>=0)
		this.soLuongTon = soLuongTon;
		else
			throw new Exception("lỗi số lượng tồn không được nhỏ hơn 0!");
	}
	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
			this.loaiDichVu = loaiDichVu;

	}
	public void setDonGia(double donGia)throws Exception {
		if(donGia>=0)
			this.donGia = donGia;
			else
				throw new Exception("Đơn giá không được nhỏ hơn 0!");
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
		return true;
	}
}
