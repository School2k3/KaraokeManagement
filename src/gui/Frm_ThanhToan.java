package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Frm_ThanhToan extends JPanel {
	private JTextField txtTimKiem, txtSoLuong;
	private JTable tblHoaDon, tblChiTietHoaDon;
	private DefaultTableModel modelPhong, modelChiTiethoaDon;
	private static final long serialVersionUID = 1L;
	private JTextField txtTongTien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;

	/**
	 * Create the panel.
	 */
	public Frm_ThanhToan() {
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblThanhToan = new JLabel("Thanh toán");
		lblThanhToan.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblThanhToan.setBounds(694, 11, 211, 62);
		add(lblThanhToan);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(240, 80, 893, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(1281, 71, 211, 45);
		add(btnTimKiem);
		
		JLabel lblTimHoaDon = new JLabel("Tìm hóa đơn");
		lblTimHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTimHoaDon.setBounds(51, 79, 112, 22);
		add(lblTimHoaDon);
		
		JLabel lblHoaDon = new JLabel("Thông tin hóa đơn");
		lblHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHoaDon.setBounds(51, 122, 158, 30);
		add(lblHoaDon);
		
		JPanel pnlTableHoaDon = new JPanel();
		pnlTableHoaDon.setBounds(244, 129, 1303, 201);
		String[] colHeaderPhong = {"Mã hóa đơn", "Tên phòng", "Khách hàng", "Giờ vào", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTableHoaDon.setLayout(null);
		tblHoaDon = new JTable(modelPhong);
		JScrollPane scrHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrHoaDon.setBounds(0, 0, 1303, 201);
		pnlTableHoaDon.add(scrHoaDon);
		add(pnlTableHoaDon);
		
		JPanel pnlChonDichVu = new JPanel();
		pnlChonDichVu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(61, 341, 956, 122);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);
		
		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ");
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLoaiDichVu.setBounds(28, 27, 103, 31);
		pnlChonDichVu.add(lblLoaiDichVu);
		
		JComboBox cmbLoaiDichVu = new JComboBox();
		cmbLoaiDichVu.setBounds(170, 27, 275, 31);
		pnlChonDichVu.add(cmbLoaiDichVu);
		
		JLabel lblTenDichVu = new JLabel("Tên dịch vụ");
		lblTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenDichVu.setBounds(28, 69, 103, 31);
		pnlChonDichVu.add(lblTenDichVu);
		
		JComboBox cmbTenDichVu = new JComboBox();
		cmbTenDichVu.setBounds(170, 69, 275, 31);
		pnlChonDichVu.add(cmbTenDichVu);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoLuong.setBounds(522, 27, 103, 31);
		pnlChonDichVu.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(676, 27, 73, 31);
		pnlChonDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JButton btnThemDichVuVaoHoaDon = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThemDichVuVaoHoaDon.setBounds(522, 67, 404, 35);
		pnlChonDichVu.add(btnThemDichVuVaoHoaDon);
		btnThemDichVuVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlThongTinHoaDon.setBounds(61, 480, 1496, 352);
		add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);
		
		JLabel lblThongTinHoaDon = new JLabel("Thông tin hóa đơn");
		lblThongTinHoaDon.setBounds(633, 11, 230, 34);
		lblThongTinHoaDon.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlThongTinHoaDon.add(lblThongTinHoaDon);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(240, 50, 102, 26);
		pnlThongTinHoaDon.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(813, 50, 114, 26);
		pnlThongTinHoaDon.add(lblSoDienThoai);
		
		JLabel lblChiTietHoaDon = new JLabel("Thông tin chi tiết hóa đơn");
		lblChiTietHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChiTietHoaDon.setBounds(10, 79, 221, 26);
		pnlThongTinHoaDon.add(lblChiTietHoaDon);
		
		JPanel pnlTableChiTietHoaDon = new JPanel();
		pnlTableChiTietHoaDon.setBounds(0, 111, 1496, 241);
		pnlThongTinHoaDon.add(pnlTableChiTietHoaDon);
		pnlTableChiTietHoaDon.setLayout(null);
		String[] colHeaderChiTietHoaDon = {"STT", "Mã phòng/dịch vụ", "Tên phòng/dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
		modelChiTiethoaDon = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTiethoaDon);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 1496, 241);
		pnlTableChiTietHoaDon.add(scrPhongDat);
		
		JPanel pnlThongTinThanhToan = new JPanel();
		pnlThongTinThanhToan.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinThanhToan.setBounds(61, 835, 1496, 160);
		add(pnlThongTinThanhToan);
		pnlThongTinThanhToan.setLayout(null);
		
		JLabel lblThoiGianVao = new JLabel("Thời gian vào");
		lblThoiGianVao.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianVao.setBounds(83, 22, 118, 31);
		pnlThongTinThanhToan.add(lblThoiGianVao);
		
		JComboBox cmbGio = new JComboBox();
		cmbGio.setBounds(313, 22, 45, 30);
		pnlThongTinThanhToan.add(cmbGio);
		
		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(368, 27, 17, 20);
		pnlThongTinThanhToan.add(lblTime);
		
		JComboBox cmbPhut = new JComboBox();
		cmbPhut.setBounds(386, 22, 45, 30);
		pnlThongTinThanhToan.add(cmbPhut);
		
		JLabel lblThoiGianTraPhong = new JLabel("Thời gian trả phòng");
		lblThoiGianTraPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianTraPhong.setBounds(83, 64, 170, 31);
		pnlThongTinThanhToan.add(lblThoiGianTraPhong);
		
		JComboBox cmbGio_1 = new JComboBox();
		cmbGio_1.setBounds(313, 64, 45, 30);
		pnlThongTinThanhToan.add(cmbGio_1);
		
		JLabel lblTime_1 = new JLabel(":");
		lblTime_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime_1.setBounds(368, 69, 17, 20);
		pnlThongTinThanhToan.add(lblTime_1);
		
		JComboBox cmbPhut_1 = new JComboBox();
		cmbPhut_1.setBounds(386, 64, 45, 30);
		pnlThongTinThanhToan.add(cmbPhut_1);
		
		JButton btnTraPhong = new JButton("Trả phòng");
		btnTraPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTraPhong.setBounds(118, 106, 241, 35);
		pnlThongTinThanhToan.add(btnTraPhong);
		
		JLabel lblTongTien = new JLabel("Tổng tiền cần thanh toán (VNĐ)");
		lblTongTien.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTongTien.setBounds(559, 22, 275, 31);
		pnlThongTinThanhToan.add(lblTongTien);
		
		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa (VNĐ)");
		lblTienKhachDua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTienKhachDua.setBounds(559, 64, 275, 31);
		pnlThongTinThanhToan.add(lblTienKhachDua);
		
		JLabel lblTienThua = new JLabel("Tiền thừa (VNĐ)");
		lblTienThua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTienThua.setBounds(559, 106, 275, 31);
		pnlThongTinThanhToan.add(lblTienThua);
		
		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(855, 22, 352, 31);
		pnlThongTinThanhToan.add(txtTongTien);
		
		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(855, 64, 352, 31);
		pnlThongTinThanhToan.add(txtTienKhachDua);
		
		txtTienThua = new JTextField();
		txtTienThua.setEditable(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(855, 106, 352, 31);
		pnlThongTinThanhToan.add(txtTienThua);
		
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThanhToan.setBounds(1233, 20, 241, 35);
		pnlThongTinThanhToan.add(btnThanhToan);
		
		JButton btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnInHoaDon.setBounds(1233, 104, 241, 35);
		pnlThongTinThanhToan.add(btnInHoaDon);
	}

}
