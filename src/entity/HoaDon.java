package entity;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class HoaDon {
	private String maHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Phong phong;
	private Timestamp thoiGianBatDau;
	private Timestamp thoiGianKetThuc;
	private String trangThai;
	public HoaDon() {
		super();
	}
	
	public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Phong phong, Timestamp thoiGianBatDau,
			Timestamp thoiGianKetThuc, String trangThai) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.phong = phong;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.trangThai = trangThai;
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	
	public HoaDon(String maHoaDon, Phong phong) {
		super();
		this.maHoaDon = maHoaDon;
		this.phong = phong;
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
	public Timestamp getThoiGianBatDau() {
		return this.thoiGianBatDau;
	}
	public void setThoiGianBatDau(Timestamp thoiGianBatDau) throws Exception {
		if (thoiGianBatDau != null) {
			this.thoiGianBatDau = thoiGianBatDau;
		}
			else {
				throw new Exception("Thời gian bắt đầu không được để trống");
			}
	}
	public Timestamp getThoiGianKetThuc() {
		return this.thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(Timestamp thoiGianKetThuc) throws Exception {
		if (thoiGianKetThuc != null) {
			this.thoiGianKetThuc = thoiGianKetThuc;
		}
			else {
				throw new Exception("Thời gian kết thúc không được để trống");
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
		return "HoaDon [maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", phong="
				+ phong + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + ", trangThai="
				+ trangThai + "]";
	}
	
    public double tinhTienPhong(double hour) {
        if (hour < 1.0) {
        	hour = 1.0;
        }

        return hour * this.getPhong().getDonGia();
    }
}
