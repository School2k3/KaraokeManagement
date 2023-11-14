package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiDichVu_DAO;
import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiDichVu;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import javax.swing.DefaultComboBoxModel;

public class ThanhToan_GUI extends JPanel implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	public JLabel lblHienThiTenKhachHang, lblHienThiSoDienThoai;
	private JTextField txtTimKiem, txtSoLuong, txtTongTien, txtTienKhachDua, txtTienThua, txtThoiGianVao;
	public JTable tblHoaDon, tblChonDichVu, tblChiTietHoaDon;
	private DefaultTableModel modelHoaDon, modelDichVu, modelChiTietHoaDon;
	public JButton btnTimKiem, btnTraPhong, btnThemDichVuVaoHoaDon, btnThanhToan, btnLamMoi;
	private JComboBox cmbLoaiDichVu;
	private DecimalFormat df;
	private Frm_HoaDon winHoaDon;
	private NhanVien nhanVien = null;
	private Phong_DAO phongDAO;
	private LoaiPhong_DAO loaiPhongDAO;
	private DichVu_DAO dichVuDAO;
	private LoaiDichVu_DAO loaiDichVuDAO;
	private PhieuDatPhong_DAO phieuDatPhongDAO;
	private KhachHang_DAO khachHangDAO;
	private NhanVien_DAO nhanVienDAO;
	private HoaDon_DAO hoaDonDAO;
	private ChiTietHoaDon_DAO chiTietHoaDonDAO;
	private List<Phong> listPhong;
	private List<LoaiPhong> listLoaiPhong;
	private List<DichVu> listDichVu;
	private List<LoaiDichVu> listLoaiDichVu;
	private List<PhieuDatPhong> listPhieuDatPhong;
	private List<KhachHang> listKhachHang;
	private List<HoaDon> listHoaDon;
	private List<HoaDon> listHoaDonChuaThanhToan;
	private List<ChiTietHoaDon> listChiTietHoaDon;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public ThanhToan_GUI(NhanVien nhanVien) throws Exception {
		this.nhanVien = nhanVien;
		// Kết nối với ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		df = new DecimalFormat("#,##0.00");

		phongDAO = new Phong_DAO();
		loaiPhongDAO = new LoaiPhong_DAO();
		dichVuDAO = new DichVu_DAO();
		loaiDichVuDAO = new LoaiDichVu_DAO();
		phieuDatPhongDAO = new PhieuDatPhong_DAO();
		khachHangDAO = new KhachHang_DAO();
		nhanVienDAO = new NhanVien_DAO();
		hoaDonDAO = new HoaDon_DAO();
		chiTietHoaDonDAO = new ChiTietHoaDon_DAO();

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
		setPlaceholder(txtTimKiem, "Nhập tên khách hàng cần tìm");

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(1155, 69, 180, 45);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
		add(btnTimKiem);

		btnLamMoi = new JButton("Làm mới bảng");
		btnLamMoi.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnLamMoi.setBounds(1353, 69, 180, 45);
		btnLamMoi.setBackground(new Color(217, 217, 217));
		btnLamMoi.setFocusable(false);
		add(btnLamMoi);

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
		String[] colHeaderPhong = { "Mã hóa đơn", "Tên phòng", "Khách hàng", "Giờ vào", "Trạng thái" };
		modelHoaDon = new DefaultTableModel(colHeaderPhong, 0);
		pnlTableHoaDon.setLayout(null);
		tblHoaDon = new JTable(modelHoaDon);
		JScrollPane scrHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		pnlChonDichVu.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(51, 341, 520, 486);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);

		// Tạo bảng chọn dịch vụ
		JPanel pnlTableDichVu = new JPanel();
		pnlTableDichVu.setBounds(15, 62, 490, 354);
		String[] colHeaderDichVu = { "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá" };
		modelDichVu = new DefaultTableModel(colHeaderDichVu, 0);
		pnlTableDichVu.setLayout(null);
		tblChonDichVu = new JTable(modelDichVu);
		JScrollPane scrDichVu = new JScrollPane(tblChonDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDichVu.setBounds(0, 0, 490, 354);
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
		btnThemDichVuVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemDichVuVaoHoaDon.setFocusable(false);

		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ");
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLoaiDichVu.setBounds(25, 24, 103, 22);
		pnlChonDichVu.add(lblLoaiDichVu);

		cmbLoaiDichVu = new JComboBox();
		cmbLoaiDichVu.setBounds(255, 21, 230, 30);
		pnlChonDichVu.add(cmbLoaiDichVu);

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
		String[] colHeaderChiTietHoaDon = { "STT", "Mã phòng/dịch vụ", "Tên phòng/dịch vụ", "Số lượng", "Đơn giá",
				"Thành tiền" };
		modelChiTietHoaDon = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTietHoaDon);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 955, 364);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChiTietHoaDon.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChiTietHoaDon.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableChiTietHoaDon.add(scrPhongDat);

		lblHienThiTenKhachHang = new JLabel("");
		lblHienThiTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHienThiTenKhachHang.setBounds(250, 48, 270, 26);
		pnlThongTinHoaDon.add(lblHienThiTenKhachHang);

		lblHienThiSoDienThoai = new JLabel("");
		lblHienThiSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHienThiSoDienThoai.setBounds(702, 48, 195, 26);
		pnlThongTinHoaDon.add(lblHienThiSoDienThoai);

		JPanel pnlThongTinThanhToan = new JPanel();
		pnlThongTinThanhToan.setBorder(
				new TitledBorder(null, "Thông tin chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinThanhToan.setBounds(61, 835, 1496, 160);
		add(pnlThongTinThanhToan);
		pnlThongTinThanhToan.setLayout(null);

		JLabel lblThoiGianVao = new JLabel("Thời gian vào");
		lblThoiGianVao.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianVao.setBounds(83, 22, 118, 31);
		pnlThongTinThanhToan.add(lblThoiGianVao);

		txtThoiGianVao = new JTextField();
		txtThoiGianVao.setBounds(258, 22, 200, 30);
		txtThoiGianVao.setEditable(false);
		pnlThongTinThanhToan.add(txtThoiGianVao);

		btnTraPhong = new JButton("Trả phòng");
		btnTraPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTraPhong.setBounds(118, 106, 241, 35);
		btnTraPhong.setBackground(new Color(217, 217, 217));
		btnTraPhong.setFocusable(false);
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
		btnThanhToan.setBounds(1234, 100, 241, 35);
		btnThanhToan.setBackground(new Color(217, 217, 217));
		btnThanhToan.setFocusable(false);
		pnlThongTinThanhToan.add(btnThanhToan);
		
		// Lấy trong database các danh sách của các thực thể có liên quan đến giao diện
		// này
		listPhong = phongDAO.getAllTablePhong();
		listLoaiPhong = loaiPhongDAO.getAllTableLoaiPhong();
		listDichVu = dichVuDAO.getAllTableDichVu();
		listLoaiDichVu = loaiDichVuDAO.getAllTableLoaiDichVu();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();
		listHoaDon = hoaDonDAO.getAllHoaDon();
		listHoaDonChuaThanhToan = hoaDonDAO.getAllHoaDonChuaThanhToan();
		listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDon();
		
		// Cập nhật lại dữ liệu cho các bảng
		updateTableData_DichVu(listDichVu);
		updateComboBox_LoaiDichVu();
		modelHoaDon.getDataVector().removeAllElements();
		updateTableData_HoaDon(listHoaDonChuaThanhToan);

		// Thêm sự kiện cho các nút và combobox
		btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemDichVuVaoHoaDon.addActionListener(this);
		btnTraPhong.addActionListener(this);
		btnThanhToan.addActionListener(this);
		cmbLoaiDichVu.addActionListener(this);
		tblHoaDon.addMouseListener(this);
		txtTienKhachDua.addKeyListener(this);
	}
	
	// Cập nhật dữ liệu về danh sách dịch vụ vào trong bảng chọn dịch vụ
	private void updateTableData_DichVu(List<DichVu> list) {
		modelDichVu.getDataVector().removeAllElements();
		for (DichVu dv : list) {
			modelDichVu.addRow(new Object[] { dv.getTenDichVu(), dv.getLoaiDichVu().getTenLoaiDichVu(),
					dv.getSoLuongTon(), dv.getDonGia() });
		}
	}

	// Cập nhật dữ liệu về danh sách hóa đơn vào trong bảng danh sách hóa đơn
	private void updateTableData_HoaDon(List<HoaDon> list) {
		for (HoaDon hd : list) {
			modelHoaDon.addRow(new Object[] { hd.getMaHoaDon(), hd.getPhong().getTenPhong(),
					hd.getKhachHang().getHoTenKhachHang(), formatDatetime(hd.getThoiGianBatDau()), hd.getTrangThai() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Khi nhấn chọn 1 dòng hóa đơn trong bảng hóa đơn sẽ hiển thị ra các thông tin liên quan đến hóa đơn thanh toán
		int count = 1;
		int row = tblHoaDon.getSelectedRow();
		String tenphong = tblHoaDon.getValueAt(row, 1).toString();
		String tenkh = tblHoaDon.getValueAt(row, 2).toString();
		String tgdat = tblHoaDon.getValueAt(row, 3).toString();
		lblHienThiTenKhachHang.setText(tenkh);
		txtThoiGianVao.setText(tgdat);
		// Chạy vòng lặp để lấy ra số diện thoại khách hàng
		for (KhachHang kh : listKhachHang) {
			if (kh.getHoTenKhachHang().equals(tenkh)) {
				lblHienThiSoDienThoai.setText(kh.getSoDienThoai());
				break;
			}
		}
		modelChiTietHoaDon.getDataVector().removeAllElements();
		// Chạy vòng lặp để lấy ra danh sách phòng đã đặt
		for (Phong p : listPhong) {
			if (p.getTenPhong().equals(tenphong)) {
				modelChiTietHoaDon.addRow(
						new Object[] { count, p.getMaPhong(), p.getTenPhong(), 0 + "h", p.getDonGia(), p.getDonGia() });
				count++;
			}
		}
		// Chạy vòng lặp để lấy ra danh sách các dịch vụ đã đặt
		for (ChiTietHoaDon cthd : listChiTietHoaDon) {
			String mahd = cthd.getHoaDon().getMaHoaDon();
			if (tblHoaDon.getValueAt(row, 0).toString().equals(mahd)) {
				modelChiTietHoaDon
						.addRow(new Object[] { count, cthd.getDichVu().getMaDichVu(), cthd.getDichVu().getTenDichVu(),
								cthd.getSoLuong(), cthd.getDonGia(), cthd.getSoLuong() * cthd.getDonGia() });
				count++;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Khi nhập tiền khách đưa, ta nhấn nút Enter, chương trình sẽ tính tiền thừa cho khách hàng
		Object o = e.getSource();
		if (o.equals(txtTienKhachDua)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				tinhTienThua();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) { // Tìm kiếm tên khách hàng trong bảng hóa đơn
			String hoTenKhachHang = txtTimKiem.getText().trim();
			String chuoiTim = "";
			if (hoTenKhachHang.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập tên khách hàng cần tìm kiếm!");
			} else {
				chuoiTim += "kh.hoTenKhachHang like N'%" + txtTimKiem.getText() + "%'";
				List<HoaDon> dsHoaDonByHoTenKH;
				try {
					dsHoaDonByHoTenKH = hoaDonDAO.getAllHoaDonByTenKhachHang(chuoiTim);
					if (dsHoaDonByHoTenKH.size() != 0) {
						modelHoaDon.getDataVector().removeAllElements();
						updateTableData_HoaDon(dsHoaDonByHoTenKH);
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy tên khách hàng cần tìm!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnLamMoi)) {
			// Làm mới bảng hóa đơn
			listHoaDonChuaThanhToan = hoaDonDAO.getAllHoaDonChuaThanhToan();
			listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDon();
			modelHoaDon.getDataVector().removeAllElements();
			updateTableData_HoaDon(listHoaDonChuaThanhToan);
		} else if (o.equals(btnThemDichVuVaoHoaDon)) {
			int count = 0;
			int rowCount_1 = tblChiTietHoaDon.getRowCount();
			// Xét trường hợp khách hàng đã được chọn chưa
			if (lblHienThiTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Cần phải có khách hàng mới có thể thêm dịch vụ!");
			} else if (txtSoLuong.getText().trim().equals("")) { // Xét xem số lượng đã nhập chưa
				JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng!");
				txtSoLuong.requestFocus();
			} else if (!txtSoLuong.getText().trim().matches("^\\d+$")) {
				JOptionPane.showMessageDialog(this, "Số lượng phải là chữ số!");
			} else {
				int row = tblChonDichVu.getSelectedRow();
				String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
				int slt = (int) tblChonDichVu.getValueAt(row, 2);
				int sl = Integer.parseInt(txtSoLuong.getText());
				// Xét trường hợp số lượng vừa nhập có lớn hơn số lượng tồn không
				if (sl > slt) {
					JOptionPane.showMessageDialog(this, "Số lượng đặt dịch vụ phải bé hơn số lượng tồn!");
					txtSoLuong.requestFocus();
				} else {
					try {
						String tendv = tblChonDichVu.getValueAt(row, 0).toString();
						for (DichVu dv : listDichVu) {
							if (dv.getTenDichVu().equals(tendv)) {
								count = modelChiTietHoaDon.getRowCount();
								count++;
								modelChiTietHoaDon.addRow(new Object[] { count, dv.getMaDichVu(), dv.getTenDichVu(), sl,
										dv.getDonGia(), tinhThanhTienDichVu(sl, dv.getDonGia()) });
							}
						}
						txtSoLuong.setText("");
						tblChonDichVu.setValueAt(tinhSoLuongTon(slt, sl), row, 2);
						capNhatLaiSoLuongTonTrongTableDichVu();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else if (o.equals(btnTraPhong)) { // Khách hàng khi sử dụng xong phòng hát có thể nhấn nút trả phòng
			int row = tblHoaDon.getSelectedRow();
			// Khai báo các biến hóa đơn
			String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
			HoaDon hd = hoaDonDAO.getAllHoaDonByMaHoaDon(maHoaDon);
			try {
				int rowCount = tblChiTietHoaDon.getRowCount();
				// Chạy vòng lặp để thêm các chi tiết hóa đơn
				for (int row_1 = 1; row_1 < rowCount; row_1++) {
					HoaDon hd2 = new HoaDon(maHoaDon);
					String madv = tblChiTietHoaDon.getValueAt(row_1, 1).toString();
					int sl = Integer.parseInt(tblChiTietHoaDon.getValueAt(row_1, 3).toString());
					double dongia = Double.parseDouble(tblChiTietHoaDon.getValueAt(row_1, 4).toString().replace(",", ""));
					DichVu dv = new DichVu(madv);
					ChiTietHoaDon cthd = new ChiTietHoaDon(hd2, dv, sl, dongia);
					chiTietHoaDonDAO.insertChiTietHoaDon(cthd);
				}
				// Gán các giá trị cần thiết để hiển thị những thông tin và bắt đầu tính toán các giá trị
				Phong p = phongDAO.getPhongByMaPhong(hd.getPhong().getMaPhong());
				String maPhong = p.getMaPhong();
				double dongia = p.getDonGia();
				HoaDon hoaDon = new HoaDon(maHoaDon, new Phong(maPhong, dongia));
				// Khởi tạo giá trị thời gian trả phòng và gán giá trị là ngày hiện tại
				Timestamp thoigiantra = new Timestamp(System.currentTimeMillis());
				hoaDonDAO.updateThoiGianKetThuc(maHoaDon, thoigiantra);
				double hour = tinhGioDatPhong();
				double tienPhong = hoaDon.tinhTienPhong(hour, dongia);
				// Cập nhật thời gian sử dụng phòng và tiền phòng sau khi trả phòng
				tblChiTietHoaDon.setValueAt(HourFormat(hour) + "h", 0, 3);
				tblChiTietHoaDon.setValueAt(df.format(tienPhong), 0, 5);
				double tongTienPhongVaDichVu = tienPhong + tinhTongTienDichVu();
				double thue = 0.1 * tongTienPhongVaDichVu;
				double tongTienHoaDon = tongTienPhongVaDichVu + thue;
				txtTongTien.setText(df.format(tongTienHoaDon));
				// Cập nhật trạng thái cho phòng sau khi trả phòng
				phongDAO.updateTrangThai(maPhong, "Trống");
				JOptionPane.showMessageDialog(this, "Đã trả phòng này!");
				listHoaDonChuaThanhToan = hoaDonDAO.getAllHoaDonChuaThanhToan();
				System.out.println(thoigiantra);
				System.out.println(hour);
				System.out.println(dongia);
				System.out.println(df.format(tienPhong));
				System.out.println(tinhTongTienDichVu());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (o.equals(btnThanhToan)) { // Sau khi trả phòng cho khách hàng và tính toán tiền thừa, ta có thể nhấn nút thanh toán để thanh toán và xuất hóa đơn cho khách hàng
			// Xét từng trường hợp để có thể bấm nút thanh toán được
			int row = tblHoaDon.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn cần thanh toán!");
			} else if (txtTongTien.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Phòng này chưa được trả!");
			} else if (txtTienKhachDua.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tiền khách đưa!");
			} else if (txtTienThua.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Chưa thể thanh toán vì chưa hiển thị tiền thừa!");
			} else {
				try {
					winHoaDon = new Frm_HoaDon();
					winHoaDon.setVisible(true);
					hienThiThongTinHoaDon();
					winHoaDon.btnThanhToan.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							winHoaDon.btnInHoaDon.setEnabled(true);
							winHoaDon.btnThanhToan.setEnabled(false);
						}
					});
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (o.equals(cmbLoaiDichVu)) { // Lọc danh sách dịch vụ theo tên loại dịch vụ
			String dvTim = cmbLoaiDichVu.getSelectedItem().toString();
			if (!dvTim.equals("")) {
				modelDichVu.getDataVector().removeAllElements();
				try {
					listDichVu = dichVuDAO.getAllTableDichVuByTenLoaiDichVu(dvTim);
					updateTableData_DichVu(listDichVu);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					listDichVu = dichVuDAO.getAllTableDichVu();
					updateTableData_DichVu(listDichVu);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	// Thêm chữ gợi ý vào trong JTextField
	private void setPlaceholder(JTextField textField, String placeholder) {
		textField.setForeground(Color.GRAY);
		textField.setText(placeholder);

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText(placeholder);
				}
			}
		});
	}

	// Cập nhật loại dich vụ vào combobox
	private void updateComboBox_LoaiDichVu() {
		for (LoaiDichVu ldv : listLoaiDichVu) {
			cmbLoaiDichVu.addItem(ldv.getTenLoaiDichVu());
		}
	}

	private String formatDatetime(Date currentDate) {
		// Định dạng theo mẫu yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// Chuyển đổi và in ra ngày giờ theo định dạng
		return sdf.format(currentDate);
	}

	/*
	 * Hàm để tính tổng tiền dịch vụ mà khách hàng đã đặt
	 */
	private double tinhTongTienDichVu() {
		int row = tblHoaDon.getSelectedRow();
		double thanhTienDichVu = 0.0;
		List<ChiTietHoaDon> cthd = chiTietHoaDonDAO
				.getAllChiTietHoaDonByMaHoaDon(tblHoaDon.getValueAt(row, 0).toString());
		for (ChiTietHoaDon ct : cthd) {
			thanhTienDichVu += ct.getSoLuong() * ct.getDonGia();
		}
		return thanhTienDichVu;
	}

	/*
	 * Hàm để tính giờ đặt phòng cho khách hàng (thoiGianKetThuc - thoiGianBatDau)
	 */
	private double tinhGioDatPhong() {
		int row = tblHoaDon.getSelectedRow();
		double minute = 0.0;
		HoaDon hd = hoaDonDAO.getAllHoaDonByMaHoaDon(tblHoaDon.getValueAt(row, 0).toString());

		long duration = hd.getThoiGianKetThuc().getTime() - hd.getThoiGianBatDau().getTime();
		minute = TimeUnit.MILLISECONDS.toMinutes(duration);

		if (minute <= 60) {
			minute = 60;
		}
		minute /= 15;
		return minute * 1.0 / 4.0;
	}

	/*
	 * Hàm để hiển thị thời gian sử dụng chính xác
	 */
	public String tinhThoiGianSuDung() {
		int row = tblHoaDon.getSelectedRow();
		double minute = 0.0;
		HoaDon hd = hoaDonDAO.getAllHoaDonByMaHoaDon(tblHoaDon.getValueAt(row, 0).toString());

		long duration = hd.getThoiGianKetThuc().getTime() - hd.getThoiGianBatDau().getTime();
		minute = TimeUnit.MILLISECONDS.toMinutes(duration);

		if (minute <= 60) {
			minute = 60;
		}

		int gio = (int) (minute / 60);
		int phut = (int) (minute % 60);
		return "" + gio + " giờ " + phut + " phút";
	}

	/*
	 * Hàm để hiển thị các thông tin hóa đơn trong Frm_HoaDon
	 */
	private void hienThiThongTinHoaDon() throws Exception {
		int row = tblHoaDon.getSelectedRow();
		int count = 1;
		String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
		HoaDon hd = hoaDonDAO.getAllHoaDonByMaHoaDon(maHoaDon);
		NhanVien nv = nhanVienDAO.getNhanVienByMaNhanVien(hd.getNhanVien().getMaNhanVien());
		KhachHang kh = khachHangDAO.getKhachHangByMaKhachHang(hd.getKhachHang().getMaKhachHang());
		Phong p = phongDAO.getPhongByMaPhong(hd.getPhong().getMaPhong());
		String nhanVienLap = nv.getHoTenNhanVien();
		String khachHang = kh.getHoTenKhachHang();
		String tenPhong = p.getTenPhong();
		String maPhong = p.getMaPhong();
		String loaiPhong = p.getLoaiPhong().getTenLoaiPhong();
		double dongia = p.getDonGia();
		// khởi tạo 2 giá trị thời gian mặc định là ngày hiện tại
		Timestamp thoiGianBatDau = new Timestamp(System.currentTimeMillis());
		Timestamp thoiGianKetThuc = new Timestamp(System.currentTimeMillis());
		String thoiGianSuDung = tinhThoiGianSuDung();
		// Chạy vòng lặp để lấy mã phòng và đơn giá
//		for (Phong p : listPhong) {
//			if (p.getTenPhong().equals(tenPhong)) {
//				loaiPhong = p.getLoaiPhong().getTenLoaiPhong();
//				maPhong = p.getMaPhong();
//				dongia = p.getDonGia();
//				break;
//			}
//		}
		// Lấy thời gian bắt đầu và thời gian kết thúc
		thoiGianBatDau = hd.getThoiGianBatDau();
		thoiGianKetThuc = hd.getThoiGianKetThuc();

		// Truyền các thuộc tính vào trong kiểu HoaDon để thực hiện việc tính toán các
		// số liệu hiển thị trong thông tin hóa đơn
		HoaDon hoaDon = new HoaDon(maHoaDon, new Phong(maPhong, dongia));
		double hour = tinhGioDatPhong();
		double tienPhong = hoaDon.tinhTienPhong(hour, dongia);
		double tienDichVu = tinhTongTienDichVu();
		double tongTienPhongVaDichVu = tienPhong + tienDichVu;
		double thue = 0.1 * tongTienPhongVaDichVu;
		double tongTienHoaDon = tongTienPhongVaDichVu + thue;
		double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().replace(",", ""));
		// Hiển thị các dữ liệu đã được tính toán sang phần thông tin hóa đơn
		winHoaDon.txtMaHoaDon.setText(maHoaDon);
		winHoaDon.txtNhanVienLap.setText(nhanVienLap);
		winHoaDon.txtKhachHang.setText(khachHang);
		winHoaDon.txtTenPhong.setText(tenPhong);
		winHoaDon.txtLoaiPhong.setText(loaiPhong);
		winHoaDon.txtDonGia.setText(df.format(dongia));
		winHoaDon.txtThoiGianBatDau.setText(formatDatetime(thoiGianBatDau));
		winHoaDon.txtThoiGianKetThuc.setText(formatDatetime(thoiGianKetThuc));
		winHoaDon.txtThoiGianSuDung.setText(thoiGianSuDung);
		// Hiển thị danh sách các dịch vụ mà khách hàng đã đặt
		listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDonByMaHoaDon(maHoaDon);
		for (ChiTietHoaDon cthd : listChiTietHoaDon) {
			String mahd = cthd.getHoaDon().getMaHoaDon();
			if (tblHoaDon.getValueAt(row, 0).toString().equals(mahd)) {
				winHoaDon.modelChiTietDichVu
						.addRow(new Object[] { count, cthd.getDichVu().getTenDichVu(), cthd.getSoLuong(),
								df.format(cthd.getDonGia()), df.format(cthd.getSoLuong() * cthd.getDonGia()) });
				count++;
			}
		}
		// Hiển thị các giá trị: tiền phòng, tiền dịch vụ, thuế VAT, tổng tiền hóa đơn
		// và tiền thừa
		winHoaDon.txtTienPhong.setText(df.format(tienPhong));
		winHoaDon.txtTienDichVu.setText(df.format(tienDichVu));
		winHoaDon.txtThueVAT.setText(df.format(thue));
		winHoaDon.txtTongTien.setText(df.format(tongTienHoaDon));
		winHoaDon.txtTienThua.setText(df.format(tienKhachDua - tongTienHoaDon));
	}

	/**
	 * Hàm tính tiền thừa cho khách hàng
	 */
	public void tinhTienThua() {
		// Xét trường hợp giá trị tiền khách trả không phải là chữ số
		if (txtTienKhachDua.getText().trim().matches("^\\d+$")) {
			double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().replace(",", ""));
			double tongTien = Double.parseDouble(txtTongTien.getText().replace(",", ""));
			// Xét trường hợp nhập tiền khách đưa bé hơn tổng tiền cần trả
			if (tienKhachDua < tongTien) {
				JOptionPane.showMessageDialog(this, "Tiền khách đưa phải >= tổng tiền");
				txtTienKhachDua.selectAll();
				txtTienKhachDua.requestFocus();
				txtTienKhachDua.removeAll();
				// Trường hợp nếu khung tiền khách trả khác rỗng và lớn hơn tổng tiền hóa đơn
			} else if (!txtTongTien.getText().equals("")) {
				if (tienKhachDua >= tongTien) {
					txtTienThua.setText(df.format(tienKhachDua - tongTien));
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tiền khách trả phải là số và >= tổng tiền");
		}

	}
	
	// Cập nhật lại số lượng tồn trong bảng chọn dịch vụ
	private void capNhatLaiSoLuongTonTrongTableDichVu() throws Exception {
		int rowCount = tblChonDichVu.getRowCount();
		for (int row = 0; row < rowCount; row++) {
			String maDichVu = tblChonDichVu.getValueAt(row, 0).toString();
			int soLuongTon = Integer.parseInt(tblChonDichVu.getValueAt(row, 2).toString());
			dichVuDAO.updateSoLuongTon(maDichVu, soLuongTon);
		}
		listDichVu = dichVuDAO.getAllTableDichVu();
	}

	/*
	 * Hàm dịnh dạng lại thời gian sử dụng phòng của khách hàng về 1 chữ số thập
	 * phân
	 */
	private String HourFormat(double hour) {
		DecimalFormat decimalFormat = new DecimalFormat("##.0");
		String formattedNumber = decimalFormat.format(hour);
		return formattedNumber;
	}
	
	// Tính số lượng tồn còn lại của dịch vụ
	private int tinhSoLuongTon(int soLuongTon, int soLuong) {
		return soLuongTon - soLuong;
	}

	// Tính tổng thành tiền cho dịch vụ
	private double tinhThanhTienDichVu(int soLuong, double donGia) {
		return soLuong * donGia;
	}
}
