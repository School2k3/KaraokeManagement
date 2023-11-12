package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiDichVu {
	private String maLoaiDichVu;
	private String tenLoaiDichVu;
	public String getMaLoaiDichVu() {
		return maLoaiDichVu;
	}
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}
	public void setTenLoaiDichVu(String tenLoaiDichVu)throws Exception {
		if(!tenLoaiDichVu.trim().equals(""))
			this.tenLoaiDichVu = tenLoaiDichVu;
			else
				throw new Exception("Lỗi tên loại dịch vụ không được rỗng!");
	}
	public LoaiDichVu(String maDichVu, String tenLoaiDichVu)throws Exception {
		this.maLoaiDichVu= maDichVu;
		setTenLoaiDichVu(tenLoaiDichVu);
	}
	public LoaiDichVu(String maLoaiDichVu)throws Exception {
		this.maLoaiDichVu= maLoaiDichVu;
	}
	public LoaiDichVu() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maLoaiDichVu == null) ? 0 : maLoaiDichVu.hashCode());
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
		LoaiDichVu other = (LoaiDichVu) obj;
		if (maLoaiDichVu == null) {
			if (other.maLoaiDichVu != null)
				return false;
		} else if (!maLoaiDichVu.equals(other.maLoaiDichVu))
			return false;
		return true;
	}

	
}
