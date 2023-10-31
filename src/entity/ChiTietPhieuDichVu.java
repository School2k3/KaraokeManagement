package entity;

public class ChiTietPhieuDichVu {
	private PhieuDichVu phieuDichVu;
	private DichVu dichVu;
	private int soLuong;
	private double donGia;
	public PhieuDichVu getPhieuDichVu() {
		return phieuDichVu;
	}
	public DichVu getDichVu() {
		return dichVu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setPhieuDichVu(PhieuDichVu phieuDichVu) {
		this.phieuDichVu = phieuDichVu;
	}
	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
	public void setSoLuong(int soLuong) throws Exception {
		if(soLuong>=0)
		this.soLuong = soLuong;
		else
			throw new Exception("Lỗi số lượng không được nhỏ hơn 0");
	}
	public void setDonGia(double donGia) throws Exception {
		
		if(donGia>=0)
			this.donGia = donGia;
			else
				throw new Exception("Lỗi đơn giá không được nhỏ hơn 0");
	}
	public ChiTietPhieuDichVu(PhieuDichVu phieuDichVu, DichVu dichVu,
			int soLuong, double donGia) throws Exception {
		setPhieuDichVu(phieuDichVu);
		setDichVu(dichVu);
		setSoLuong(soLuong);
		setDonGia(donGia);
	}
	
	
	
	
}
