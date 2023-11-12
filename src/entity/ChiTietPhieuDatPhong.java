package entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ChiTietPhieuDatPhong {
	private PhieuDatPhong phieuDatPhong;
	private Phong phong;
	private Timestamp thoiGianDat;
	public ChiTietPhieuDatPhong() {
		super();
	}
	public ChiTietPhieuDatPhong(PhieuDatPhong phieuDatPhong, Phong phong, Timestamp thoiGianDat) {
		super();
		this.phieuDatPhong = phieuDatPhong;
		this.phong = phong;
		this.thoiGianDat = thoiGianDat;
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
	public Timestamp getThoiGianDat() {
		return thoiGianDat;
	}
	public void setThoiGianDat(Timestamp thoiGianDat) throws Exception {
		if (thoiGianDat != null) {
			this.thoiGianDat = thoiGianDat;
		}
			else {
				throw new Exception("Ngày đặt không được để trống");
			}
	}
//	public Date getGioDat() {
//		return gioDat;
//	}
//	public void setGioDat(Date gioDat) throws Exception {
//		if (gioDat != null) {
//			this.gioDat = gioDat;
//		}
//			else {
//				throw new Exception("Giờ đặt không được để trống");
//			}
//	}
	@Override
	public String toString() {
		return "ChiTietPhieuDatPhong [phieuDatPhong=" + phieuDatPhong + ", ngayDat=" + thoiGianDat
				+ "]";
	}
	
}
