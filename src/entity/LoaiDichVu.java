package entity;

public class LoaiDichVu {
	private String maDichVu;
	private String tenLoaiDichVu;
	public String getMaDichVu() {
		return maDichVu;
	}
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}
	public void setTenLoaiDichVu(String tenLoaiDichVu)throws Exception {
		
		if(tenLoaiDichVu.trim().equals(""))
			this.tenLoaiDichVu = tenLoaiDichVu;
			else
				throw new Exception("Lỗi mã loại dịch vụ không được rỗng!");
	}
	public LoaiDichVu(String maDichVu, String tenLoaiDichVu)throws Exception {
		this.maDichVu= maDichVu;
		setTenLoaiDichVu(tenLoaiDichVu);
	}
	public LoaiDichVu(String maDichVu)throws Exception {
		this.maDichVu= maDichVu;
	}
	public LoaiDichVu() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maDichVu == null) ? 0 : maDichVu.hashCode());
		result = prime * result
				+ ((tenLoaiDichVu == null) ? 0 : tenLoaiDichVu.hashCode());
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
		if (maDichVu == null) {
			if (other.maDichVu != null)
				return false;
		} else if (!maDichVu.equals(other.maDichVu))
			return false;
		if (tenLoaiDichVu == null) {
			if (other.tenLoaiDichVu != null)
				return false;
		} else if (!tenLoaiDichVu.equals(other.tenLoaiDichVu))
			return false;
		return true;
	}
	
	
}
