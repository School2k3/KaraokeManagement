package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiDichVu_DAO;
import dao.LoaiPhong_DAO;
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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class LapHoaDon_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem, txtSoLuong, txtThoiGianVao;
	public JLabel lblHienThiTenKhachHang, lblHienThiSoDienThoai;
	private JTable tblChonPhong, tblChonDichVu, tblChiTietHoaDon;
	public DefaultTableModel modelPhong, modelDichVu, modelChiTietHoaDon;
	private JButton btnTimKiem, btnNhanPhong, btnThemPhongVaoHoaDon, btnThemDichVuVaoHoaDon, btnLuuHoaDon;
	private JComboBox cmbTrangThai, cmbLoaiDichVu;
	private Frm_NhanPhong winNhanPhong;
	private JTabbedPane tabbedPane;
	private Phong_DAO phongDAO;
	private LoaiPhong_DAO loaiPhongDAO;
	private DichVu_DAO dichVuDAO;
	private LoaiDichVu_DAO loaiDichVuDAO;
	private PhieuDatPhong_DAO phieuDatPhongDAO;
	private KhachHang_DAO khachHangDAO;
	private HoaDon_DAO hoaDonDAO;
	private ChiTietHoaDon_DAO chiTietHoaDonDAO;
	private List<Phong> listPhong;
	private List<LoaiPhong> listLoaiPhong;
	private List<DichVu> listDichVu;
	private List<LoaiDichVu> listLoaiDichVu;
	private List<PhieuDatPhong> listPhieuDatPhong;
	private List<KhachHang> listKhachHang;
	private List<HoaDon> listHoaDon;
	private List<ChiTietHoaDon> listChiTietHoaDon;

	private int count = 1;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public LapHoaDon_GUI() throws Exception {

		// Kết nối với ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		phongDAO = new Phong_DAO();
		loaiPhongDAO = new LoaiPhong_DAO();
		dichVuDAO = new DichVu_DAO();
		loaiDichVuDAO = new LoaiDichVu_DAO();
		phieuDatPhongDAO = new PhieuDatPhong_DAO();
		khachHangDAO = new KhachHang_DAO();
		hoaDonDAO = new HoaDon_DAO();
		chiTietHoaDonDAO = new ChiTietHoaDon_DAO();

		// Thiết lập size cho giao diện
		setSize(1600, 1055);
		setLayout(null);

		JLabel lblLapHoaDon = new JLabel("Lập hóa đơn");
		lblLapHoaDon.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblLapHoaDon.setBounds(682, 11, 236, 62);
		add(lblLapHoaDon);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 600, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(682, 70, 211, 45);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
		add(btnTimKiem);

		JLabel lblChonPhong = new JLabel("Chọn phòng");
		lblChonPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonPhong.setBounds(61, 134, 123, 30);
		add(lblChonPhong);

		// Tạo bảng chọn phòng
		JPanel pnlTablePhong = new JPanel();
		pnlTablePhong.setBounds(244, 129, 1303, 261);
		String[] colHeaderPhong = { "Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái" };
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhong.setBounds(0, 0, 1303, 261);
		scrPhong.setBackground(new Color(120, 255, 239));
		scrPhong.getViewport().setBackground(Color.WHITE);
		scrPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhong.add(scrPhong);
		add(pnlTablePhong);

		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNhanPhong.setBounds(903, 70, 211, 45);
		btnNhanPhong.setBackground(new Color(217, 217, 217));
		btnNhanPhong.setFocusable(false);
		add(btnNhanPhong);

		btnThemPhongVaoHoaDon = new JButton("Thêm phòng vào hóa đơn");
		btnThemPhongVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThemPhongVaoHoaDon.setBounds(1184, 401, 330, 35);
		btnThemPhongVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemPhongVaoHoaDon.setFocusable(false);
		add(btnThemPhongVaoHoaDon);

		// Thiết lập phần chọn dịch vụ cần thêm
		JPanel pnlChonDichVu = new JPanel();
		pnlChonDichVu.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(61, 401, 520, 521);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);

		// Tạo bảng chọn dịch vụ
		JPanel pnlTableDichVu = new JPanel();
		pnlTableDichVu.setBounds(15, 74, 490, 392);
		String[] colHeaderDichVu = { "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá" };
		modelDichVu = new DefaultTableModel(colHeaderDichVu, 0);
		pnlTableDichVu.setLayout(null);
		tblChonDichVu = new JTable(modelDichVu);
		JScrollPane scrDichVu = new JScrollPane(tblChonDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDichVu.setBounds(0, 0, 490, 392);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableDichVu.add(scrDichVu);
		pnlChonDichVu.add(pnlTableDichVu);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoLuong.setBounds(30, 477, 103, 31);
		pnlChonDichVu.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(130, 477, 50, 31);
		pnlChonDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		btnThemDichVuVaoHoaDon = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThemDichVuVaoHoaDon.setBounds(211, 475, 280, 35);
		btnThemDichVuVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemDichVuVaoHoaDon.setFocusable(false);
		pnlChonDichVu.add(btnThemDichVuVaoHoaDon);
		btnThemDichVuVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));

		cmbLoaiDichVu = new JComboBox();
		cmbLoaiDichVu.setBounds(260, 30, 230, 30);
		pnlChonDichVu.add(cmbLoaiDichVu);

		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ");
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLoaiDichVu.setBounds(30, 30, 103, 22);
		pnlChonDichVu.add(lblLoaiDichVu);

		// Phần thông tin hóa đơn
		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlThongTinHoaDon.setBounds(602, 447, 955, 475);
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
		lblHienThiTenKhachHang.setBounds(257, 48, 270, 26);
		pnlThongTinHoaDon.add(lblHienThiTenKhachHang);

		lblHienThiSoDienThoai = new JLabel("");
		lblHienThiSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHienThiSoDienThoai.setBounds(709, 48, 195, 26);
		pnlThongTinHoaDon.add(lblHienThiSoDienThoai);

		JLabel lblThoiGianVao = new JLabel("Thời gian vào");
		lblThoiGianVao.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianVao.setBounds(71, 933, 123, 31);
		add(lblThoiGianVao);

		// Hiển thị thời gian vao của khách hàng
		txtThoiGianVao = new JTextField();
		txtThoiGianVao.setBounds(226, 933, 220, 30);
		txtThoiGianVao.setEditable(false);
		add(txtThoiGianVao);

		btnLuuHoaDon = new JButton("Lưu hóa đơn và bắt đầu nhận phòng");
		btnLuuHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnLuuHoaDon.setBounds(1195, 933, 360, 35);
		btnLuuHoaDon.setBackground(new Color(217, 217, 217));
		btnLuuHoaDon.setFocusable(false);
		add(btnLuuHoaDon);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "", "Trống", "Đặt trước", "Đang sử dụng" }));
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setBounds(1377, 85, 170, 30);
		add(cmbTrangThai);

		// Lấy trong database các danh sách của các thực thể có liên quan đến giao diện
		// này
		listPhong = phongDAO.getAllTablePhong();
		listLoaiPhong = loaiPhongDAO.getAllTableLoaiPhong();
		listDichVu = dichVuDAO.getAllTableDichVu();
		listLoaiDichVu = loaiDichVuDAO.getAllTableLoaiDichVu();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();
		listHoaDon = hoaDonDAO.getAllHoaDon();
		listChiTietHoaDon = chiTietHoaDonDAO.getAllChiTietHoaDon();

		// Cập nhật danh sách vào trong các bảng
		updateTableData_Phong();
		updateTableData_DichVu();
		updateComboBox_LoaiDichVu();

		// Thêm sự kiện cho các nút và combobox
		btnNhanPhong.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThemDichVuVaoHoaDon.addActionListener(this);
		btnThemPhongVaoHoaDon.addActionListener(this);
		btnLuuHoaDon.addActionListener(this);
		cmbLoaiDichVu.addActionListener(this);
		cmbTrangThai.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnNhanPhong)) {
			try {
				// Mở một cửa sổ mới trong đó chứa danh sách tất cả các phiếu đặt phòng đang trong trạng thái "Chờ nhận phòng"
				winNhanPhong = new Frm_NhanPhong();
				winNhanPhong.setVisible(true);
				winNhanPhong.getBtnNhanPhong().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						lblHienThiTenKhachHang.setText(winNhanPhong.getHoTenKhachHang());
						lblHienThiSoDienThoai.setText(winNhanPhong.getSoDienThoai());
						txtThoiGianVao.setText(formatDateTime(winNhanPhong.getThoiGianDat()));
						updateTableData_ThongTinHoaDon();
					}
				});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimKiem)) {

		} else if (o.equals(btnThemDichVuVaoHoaDon)) {
			if (lblHienThiTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Cần phải có khách hàng mới có thể thêm dịch vụ!");
			} else if (txtSoLuong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng!");
				txtSoLuong.requestFocus();
			} else {
				int row = tblChonDichVu.getSelectedRow();
				int slt = (int) tblChonDichVu.getValueAt(row, 2);
				int sl = Integer.parseInt(txtSoLuong.getText());
				if (sl > slt) {
					JOptionPane.showMessageDialog(this, "Số lượng đặt dịch vụ phải bé hơn số lượng tồn!");
					txtSoLuong.requestFocus();
				} else {
					String tendv = tblChonDichVu.getValueAt(row, 0).toString();
					for (DichVu dv : listDichVu) {
						if (dv.getTenDichVu().equals(tendv)) {
							int count = modelChiTietHoaDon.getRowCount();
							count++;
							modelChiTietHoaDon.addRow(new Object[] { count, dv.getMaDichVu(), dv.getTenDichVu(), sl,
									dv.getDonGia(), tinhThanhTien(sl, dv.getDonGia()) });
						}
					}
					txtSoLuong.setText("");
					tblChonDichVu.setValueAt(tinhSoLuongTon(slt, sl), row, 2);
				}
			}

		} else if (o.equals(btnThemPhongVaoHoaDon)) {
			if (lblHienThiTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng cần thêm vào hóa đơn!");
			} else {
				int count = 1;
				int row = tblChonPhong.getSelectedRow();
				String tenphong = tblChonPhong.getValueAt(row, 1).toString();
				for (Phong p : listPhong) {
					if (p.getTenPhong().equals(tenphong)) {
						modelChiTietHoaDon.addRow(new Object[] { count, p.getMaPhong(), p.getTenPhong(), 0 + "h",
								p.getDonGia(), p.getDonGia() });
					}
				}
			}
		} else if (o.equals(btnLuuHoaDon)) {
			int rowCount = tblChiTietHoaDon.getRowCount();
			String tenkh = lblHienThiTenKhachHang.getText();
			String makh = "";
			String mapd = winNhanPhong.getMaPhieuDat();
			for (KhachHang kh : listKhachHang) {
				if (kh.getHoTenKhachHang().equals(tenkh)) {
					makh = kh.getMaKhachHang();
					break;
				}
			}
			String mahd = hoaDonDAO.phatSinhMaHoaDon(); // Phát sinh mã hóa đơn tăng dần
			String manv = "NV001"; // Lấy mã nhân viên của nhân viên đang sử dụng chương trình
			String tt = "Chưa thanh toán";
			String maphong = tblChiTietHoaDon.getValueAt(0, 1).toString();
			Timestamp ngaylap = new Timestamp(System.currentTimeMillis());
			try {
				KhachHang kh = new KhachHang(makh);
				NhanVien nv = new NhanVien(manv);
				Phong p = new Phong(maphong);
				HoaDon hd = null;
				if (txtThoiGianVao.getText().equals("")) {
					hd = new HoaDon(mahd, kh, nv, p, ngaylap, null, tt);
				} else {
					Timestamp tgbatdau = winNhanPhong.getThoiGianDat();
					hd = new HoaDon(mahd, kh, nv, p, tgbatdau, null, tt);
				}
				hoaDonDAO.insertHoaDon(hd);
				for (int row = 1; row < rowCount; row++) {
					HoaDon hd2 = new HoaDon(mahd);
					String madv = tblChiTietHoaDon.getValueAt(row, 1).toString();
					int sl = Integer.parseInt(tblChiTietHoaDon.getValueAt(row, 3).toString());
					double dongia = Double.parseDouble(tblChiTietHoaDon.getValueAt(row, 4).toString());
					DichVu dv = new DichVu(madv);
					ChiTietHoaDon cthd = new ChiTietHoaDon(hd2, dv, sl, dongia);
					chiTietHoaDonDAO.insertChiTietHoaDon(cthd);
				}
				phieuDatPhongDAO.updateTrangThai(mapd, "Đã nhận phòng");
				phongDAO.updateTrangThai(maphong, "Đang sử dụng");
				txtThoiGianVao.setText("");
				capNhatLaiSoLuongTonTrongTableDichVu();
				JOptionPane.showMessageDialog(this, "Bạn đã thêm hóa đơn thành công!");
				JOptionPane.showMessageDialog(this, "Bắt đầu tính giờ nhận phòng!");
				listPhong = phongDAO.getAllTablePhong();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			modelChiTietHoaDon.getDataVector().removeAllElements();

		} else if (o.equals(cmbLoaiDichVu)) {
			String dvTim = cmbLoaiDichVu.getSelectedItem().toString();
			if (!dvTim.equals("")) {
				modelDichVu.getDataVector().removeAllElements();
				try {
					listDichVu = dichVuDAO.getAllTableDichVuByTenLoaiDichVu(dvTim);
					updateTableData_DichVu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					listDichVu = dichVuDAO.getAllTableDichVu();
					updateTableData_DichVu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (o.equals(cmbTrangThai)) {
			String ttTim = cmbTrangThai.getSelectedItem().toString();
			if (!ttTim.equals("")) {
				modelPhong.getDataVector().removeAllElements();
				try {
					listPhong = phongDAO.getAllTablePhongByTrangThai(ttTim);
					updateTableData_Phong();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					listPhong = phongDAO.getAllTablePhong();
					updateTableData_Phong();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	// Cập nhật thông tin phòng vào trong bảng
	private void updateTableData_Phong() {
		modelPhong.getDataVector().removeAllElements();
		for (Phong p : listPhong) {
			String malp = p.getLoaiPhong().getMaLoaiPhong();
			String tenlp = "";
			for (LoaiPhong lp : listLoaiPhong) {
				if (lp.getMaLoaiPhong().equals(malp)) {
					tenlp = lp.getTenLoaiPhong();
					break;
				}
			}
			modelPhong.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), tenlp, p.getDonGia(), p.getTrangThai() });
		}
	}

	// Cập nhật thông tin dịch vụ vào trong bảng
	private void updateTableData_DichVu() {
		modelDichVu.getDataVector().removeAllElements();
		for (DichVu dv : listDichVu) {
			modelDichVu.addRow(new Object[] { dv.getTenDichVu(), dv.getLoaiDichVu().getTenLoaiDichVu(),
					dv.getSoLuongTon(), dv.getDonGia() });
		}
	}

	// Cập nhật thông tin hóa đơn vào trong bảng
	private void updateTableData_ThongTinHoaDon() {
		modelChiTietHoaDon.getDataVector().removeAllElements();
		int row = winNhanPhong.tblPhieuDatPhong.getSelectedRow();
		String tenphong = winNhanPhong.tblPhieuDatPhong.getValueAt(row, 4).toString();
		for (Phong p : listPhong) {
			if (p.getTenPhong().equals(tenphong)) {
				modelChiTietHoaDon.addRow(
						new Object[] { count, p.getMaPhong(), p.getTenPhong(), 0 + "h", p.getDonGia(), p.getDonGia() });
			}
		}
	}

	// Cập nhật loại dich vụ vào combobox
	private void updateComboBox_LoaiDichVu() {
		for (LoaiDichVu ldv : listLoaiDichVu) {
			cmbLoaiDichVu.addItem(ldv.getTenLoaiDichVu());
		}
	}

	private void capNhatLaiSoLuongTonTrongTableDichVu() throws Exception {
		int rowCount = tblChonDichVu.getRowCount();
		for (int row = 0; row < rowCount; row++) {
			String maDichVu = tblChonDichVu.getValueAt(row, 0).toString();
			int soLuongTon = Integer.parseInt(tblChonDichVu.getValueAt(row, 2).toString());
			dichVuDAO.updateSoLuongTon(maDichVu, soLuongTon);
		}
		listDichVu = dichVuDAO.getAllTableDichVu();
	}

	private String formatDateTime(Date currentDate) {
		// Định dạng theo mẫu yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Chuyển đổi và in ra ngày giờ theo định dạng
		return sdf.format(currentDate);
	}

	private int tinhSoLuongTon(int soLuongTon, int soLuong) {
		return soLuongTon - soLuong;
	}

	// Tính tổng thành tiền cho dịch vụ
	private double tinhThanhTien(int soLuong, double donGia) {
		return soLuong * donGia;
	}
}
