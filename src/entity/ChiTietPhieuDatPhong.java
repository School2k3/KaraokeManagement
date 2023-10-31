package entity;

import java.sql.Date;

public class ChiTietPhieuDatPhong {
	private PhieuDatPhong phieuDatPhong;
	private Phong phong;
	private Date ngayDat;
	private Date gioDat;
	public ChiTietPhieuDatPhong() {
		super();
	}
	public ChiTietPhieuDatPhong(PhieuDatPhong phieuDatPhong, Phong phong, Date ngayDat, Date gioDat) {
		super();
		this.phieuDatPhong = phieuDatPhong;
		this.phong = phong;
		this.ngayDat = ngayDat;
		this.gioDat = gioDat;
	}
	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}
	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) throws Exception {
		if (ngayDat != null) {
			this.ngayDat = ngayDat;
		}
			else {
				throw new Exception("Ngày đặt không được để trống");
			}
	}
	public Date getGioDat() {
		return gioDat;
	}
	public void setGioDat(Date gioDat) throws Exception {
		if (gioDat != null) {
			this.gioDat = gioDat;
		}
			else {
				throw new Exception("Giờ đặt không được để trống");
			}
	}
	@Override
	public String toString() {
		return "ChiTietPhieuDatPhong [phieuDatPhong=" + phieuDatPhong + ", ngayDat=" + ngayDat + ", gioDat=" + gioDat
				+ "]";
	}
	
}
