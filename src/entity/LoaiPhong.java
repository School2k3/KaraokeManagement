package entity;

import java.util.Objects;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	public LoaiPhong() {
		super();
	}
	public LoaiPhong(String maLoaiPhong) throws Exception{
		super();
		setMaLoaiPhong(maLoaiPhong);
	}
	public LoaiPhong(String maLoaiPhong,String tenLoaiPhong) throws Exception{
		super();
		setMaLoaiPhong(maLoaiPhong);
		setTenLoaiPhong(tenLoaiPhong);
	}
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) throws Exception{
		if(!maLoaiPhong.trim().equals("")) {
			this.maLoaiPhong = maLoaiPhong;
		}else {
			throw new Exception("Mã loại phòng không được để rỗng");
		}
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) throws Exception {
		if(!tenLoaiPhong.trim().equals("")) {
			this.tenLoaiPhong = tenLoaiPhong;
		}else {
			throw new Exception("Tên loại phòng không được để rỗng");
		}
	}
	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoaiPhong, other.maLoaiPhong);
	}
	
	
	
}
