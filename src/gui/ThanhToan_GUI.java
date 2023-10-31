package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
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

public class ThanhToan_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem, txtSoLuong;
	private JTable tblHoaDon, tblChonDichVu, tblChiTietHoaDon;
	private DefaultTableModel modelPhong, modelDichVu, modelChiTiethoaDon;
	private JTextField txtTongTien, txtTienKhachDua, txtTienThua;
	private JButton btnTimKiem, btnTraPhong, btnThemDichVuVaoHoaDon, btnThanhToan, btnInHoaDon;

	/**
	 * Create the panel.
	 */
	public ThanhToan_GUI() {
		// Thiết lập size cho giao diện
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
		
		btnTimKiem = new JButton("Tìm kiếm");
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
		
		// Tạo bảng hóa đơn
		JPanel pnlTableHoaDon = new JPanel();
		pnlTableHoaDon.setBounds(244, 129, 1303, 201);
		String[] colHeaderPhong = {"Mã hóa đơn", "Tên phòng", "Khách hàng", "Giờ vào", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTableHoaDon.setLayout(null);
		tblHoaDon = new JTable(modelPhong);
		JScrollPane scrHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrHoaDon.setBounds(0, 0, 1303, 201);
		scrHoaDon.setBackground(new Color(120, 255, 239));
		scrHoaDon.getViewport().setBackground(Color.WHITE);
		scrHoaDon.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblHoaDon.getTableHeader().setBackground(new Color(120, 255, 239));
		tblHoaDon.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableHoaDon.add(scrHoaDon);
		add(pnlTableHoaDon);
		
		// Thiết lập phần chọn dịch vụ cần thêm
		JPanel pnlChonDichVu = new JPanel();
		pnlChonDichVu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(51, 341, 520, 486);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);
		
		// Tạo bảng chọn dịch vụ
		JPanel pnlTableDichVu = new JPanel();
		pnlTableDichVu.setBounds(15, 20, 490, 396);
		String[] colHeaderDichVu = {"Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá"};
		modelDichVu = new DefaultTableModel(colHeaderDichVu, 0);
		pnlTableDichVu.setLayout(null);
		tblChonDichVu = new JTable(modelDichVu);
		JScrollPane scrDichVu = new JScrollPane(tblChonDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDichVu.setBounds(0, 0, 490, 396);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableDichVu.add(scrDichVu);
		pnlChonDichVu.add(pnlTableDichVu);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoLuong.setBounds(25, 430, 103, 31);
		pnlChonDichVu.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(125, 430, 50, 31);
		pnlChonDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		btnThemDichVuVaoHoaDon = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThemDichVuVaoHoaDon.setBounds(210, 428, 280, 35);
		btnThemDichVuVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemDichVuVaoHoaDon.setFocusable(false);
		pnlChonDichVu.add(btnThemDichVuVaoHoaDon);
		btnThemDichVuVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlThongTinHoaDon.setBounds(596, 352, 955, 475);
		add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);
		
		JLabel lblThongTinHoaDon = new JLabel("Thông tin hóa đơn");
		lblThongTinHoaDon.setBounds(362, 11, 230, 34);
		lblThongTinHoaDon.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlThongTinHoaDon.add(lblThongTinHoaDon);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(130, 48, 102, 26);
		pnlThongTinHoaDon.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(570, 47, 114, 26);
		pnlThongTinHoaDon.add(lblSoDienThoai);
		
		JLabel lblChiTietHoaDon = new JLabel("Thông tin chi tiết hóa đơn");
		lblChiTietHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChiTietHoaDon.setBounds(10, 79, 221, 26);
		pnlThongTinHoaDon.add(lblChiTietHoaDon);
		
		// Tạo bảng chi tiết hóa đơn
		JPanel pnlTableChiTietHoaDon = new JPanel();
		pnlTableChiTietHoaDon.setBounds(0, 111, 955, 364);
		pnlThongTinHoaDon.add(pnlTableChiTietHoaDon);
		pnlTableChiTietHoaDon.setLayout(null);
		String[] colHeaderChiTietHoaDon = {"STT", "Mã phòng/dịch vụ", "Tên phòng/dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
		modelChiTiethoaDon = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTiethoaDon);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 955, 364);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChiTietHoaDon.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChiTietHoaDon.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableChiTietHoaDon.add(scrPhongDat);
		
		JPanel pnlThongTinThanhToan = new JPanel();
		pnlThongTinThanhToan.setBorder(new TitledBorder(null, "Thông tin chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		btnTraPhong = new JButton("Trả phòng");
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
		
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThanhToan.setBounds(1233, 20, 241, 35);
		pnlThongTinThanhToan.add(btnThanhToan);
		
		btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnInHoaDon.setBounds(1233, 104, 241, 35);
		pnlThongTinThanhToan.add(btnInHoaDon);
	}

}
