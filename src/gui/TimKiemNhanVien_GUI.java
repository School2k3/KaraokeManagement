package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class TimKiemNhanVien_GUI extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTimKiemNhanVien, lblMaNhanVien, lblHoTenNhanVien, lblSoDienThoai, lblCCCD, lblGioiTinh,
			lblNamSinh, lblDiaChi, lblMatKhau, lblChucVu;
	private JTextField txtMaNhanVien, txtHoTenNhanVien, txtSoDienThoai, txtCCCD, txtNamSinh, txtDiaChi, txtMatKhau;
	private JComboBox cmbGioiTinh, cmbChucVu;
	private JButton btnTimKiem, btnXemDanhSachNhanVien;
	private JScrollPane scrNhanVien;
	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private ArrayList<NhanVien> arlNhanVien;
	private NhanVien_DAO nhanVien_DAO;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public TimKiemNhanVien_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		nhanVien_DAO = new NhanVien_DAO();

		// Tạo panel tìm kiếm nhân viên
		setSize(1600, 1055);
		setLayout(null);

		lblTimKiemNhanVien = new JLabel("Tìm kiếm nhân viên");
		lblTimKiemNhanVien.setBounds(618, 11, 363, 49);
		lblTimKiemNhanVien.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlTimKiemNhanVien.add(lblTimKiemNhanVien);
		add(lblTimKiemNhanVien);

		// Thêm Label Mã nhân viên
		lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setBounds(70, 100, 150, 30);
		lblMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblMaNhanVien);
		add(lblMaNhanVien);

		// Thêm Label Họ tên nhân viên
		lblHoTenNhanVien = new JLabel("Họ tên nhân viên");
		lblHoTenNhanVien.setBounds(70, 170, 200, 30);
		lblHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblHoTenNhanVien);
		add(lblHoTenNhanVien);

		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(890, 100, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblSoDienThoai);
		add(lblSoDienThoai);

		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("CCCD");
		lblCCCD.setBounds(890, 170, 150, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblCCCD);
		add(lblCCCD);

		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(70, 240, 150, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblGioiTinh);
		add(lblGioiTinh);

		// Thêm label Mật khẩu
		lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setBounds(890, 240, 150, 30);
		lblMatKhau.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblMatKhau);
		add(lblMatKhau);

		// Thêm label năm sinh
		lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setBounds(70, 310, 150, 30);
		lblNamSinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblNamSinh);
		add(lblNamSinh);

		// Thêm label chức vụ
		lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setBounds(890, 310, 150, 30);
		lblChucVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblChucVu);
		add(lblChucVu);

		// Thêm label địa chỉ
		lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(70, 380, 150, 30);
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemNhanVien.add(lblDiaChi);
		add(lblDiaChi);

		// Thêm TextField Mã nhân viên
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(280, 102, 450, 30);
		txtMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtMaNhanVien);
		add(txtMaNhanVien);

		// Thêm TextField Họ tên nhân viên
		txtHoTenNhanVien = new JTextField();
		txtHoTenNhanVien.setBounds(280, 172, 450, 30);
		txtHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtHoTenNhanVien);
		add(txtHoTenNhanVien);

		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1070, 102, 450, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtSoDienThoai);
		add(txtSoDienThoai);

		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(1070, 172, 450, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtCCCD);
		add(txtCCCD);

		// Thêm TextField Mật khẩu
		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(1070, 242, 450, 30);
		txtMatKhau.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtMatKhau);
		add(txtMatKhau);

		// Thêm textField Năm sinh
		txtNamSinh = new JTextField();
		txtNamSinh.setBounds(280, 312, 450, 30);
		txtNamSinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtNamSinh);
		add(txtNamSinh);

		// Thêm textField Địa chỉ
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(280, 382, 930, 30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemNhanVien.add(txtDiaChi);
		add(txtDiaChi);

		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setBounds(280, 242, 200, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlTimKiemNhanVien.add(cmbGioiTinh);
		add(cmbGioiTinh);

		// Thêm combobox Chức vụ
		cmbChucVu = new JComboBox();
		cmbChucVu.setBounds(1070, 312, 250, 30);
		cmbChucVu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbChucVu.setEditable(false);
		cmbChucVu.setFocusable(false);
//		pnlTimKiemNhanVien.add(cmbChucVu);
		add(cmbChucVu);

		// Add item cho Combobox
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");
		cmbGioiTinh.setSelectedIndex(0);
		cmbGioiTinh.requestFocus();

		cmbChucVu.addItem("Nhân viên");
		cmbChucVu.addItem("Nhân viên quản lý");
		cmbChucVu.setSelectedIndex(0);
		cmbGioiTinh.requestFocus();

		// Thêm các button
		// Button thêm
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(300, 440, 200, 45);
		btnTimKiem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
//		pnlTimKiemNhanVien.add(btnTimKiem);
		add(btnTimKiem);

		// Button Làm mới
		btnXemDanhSachNhanVien = new JButton("Xem danh sách nhân viên");
		btnXemDanhSachNhanVien.setBounds(890, 440, 400, 45);
		btnXemDanhSachNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnXemDanhSachNhanVien.setBackground(new Color(217, 217, 217));
		btnXemDanhSachNhanVien.setFocusable(false);
//		pnlTimKiemNhanVien.add(btnXemDanhSachNhanVien);
		add(btnXemDanhSachNhanVien);

		// Tạp table thông tin nhân viên
		String[] header = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
				"CCCD", "Mật khẩu", "Chức vụ" };
		modelNhanVien = new DefaultTableModel(header, 0);

		add(scrNhanVien = new JScrollPane(tblNhanVien = new JTable(modelNhanVien),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrNhanVien.setBounds(70, 500, 1450, 450);
		scrNhanVien.setBackground(new Color(120, 255, 239));
		scrNhanVien.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrNhanVien.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblNhanVien.getTableHeader().setBackground(new Color(120, 255, 239));
		tblNhanVien.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		// Set Size Width ColumnTable
//		tblNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(110);
//		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(200);
//		tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(80);
//		tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(80);
//		tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(200);
//		tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(120);
//		tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(120);
//		tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(140);
//		tblNhanVien.getColumnModel().getColumn(8).setPreferredWidth(145);

		// Load dữ liệu từ database vào table
		loadDanhSachNhanVien();
		docDuLieuVaoTable();

		// Thêm sự kiện
		// Các button
		btnTimKiem.addActionListener(this);
		btnXemDanhSachNhanVien.addActionListener(this);

		// Thêm sự kiện mouseListener
		tblNhanVien.addMouseListener(this);
	}

	/**
	 * Hàm tải dữ liệu từ database
	 */
	private void loadDanhSachNhanVien() throws Exception {
		/*
		 * Lấy dữ liệu từ database
		 */
		arlNhanVien = nhanVien_DAO.getAllTableNhanVien();
	}

	/**
	 * Hàm đọc dữ liệu vào database addRow: Thêm các dòng dữ liệu từ database vào
	 * table
	 * 
	 * @throws Exception
	 */
	public void docDuLieuVaoTable() throws Exception {
		/**
		 * Nếu arlNhanVien(danh sách nhân viên) rỗng và độ lớn danh sách <= 0 Thì không
		 * trả về gì cả Ngược lại duyệt danh sách nhân viên bằng vòng for Sau đó thêm
		 * dòng dữ liệu đối tượng nhân viên với các thuộc tính liên quan vào model
		 */
		if (arlNhanVien == null || arlNhanVien.size() <= 0)
			return;
		for (NhanVien nhanVien : arlNhanVien) {
			modelNhanVien.addRow(new Object[] { nhanVien.getMaNhanVien(), nhanVien.getHoTenNhanVien(),
					nhanVien.isGioiTinh(), nhanVien.getNamSinh(), nhanVien.getDiaChi(), nhanVien.getSoDienThoai(),
					nhanVien.getCanCuocCongDan(), nhanVien.getMatKhau(), nhanVien.isChucVu() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tblNhanVien)) {
			/**
			 * Tạo biến row có kiểu dữ liệu int Để table nhân viên lấy dòng được chọn
			 */
			int row = tblNhanVien.getSelectedRow();
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 0 là cột mã khách hàng Đưa lên textField
			 * txtNhanVien
			 */
			txtMaNhanVien.setText(modelNhanVien.getValueAt(row, 0).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 1 là cột họ tên nhân viên Đưa lên textField
			 * txtHoTenNhanVien
			 */
			txtHoTenNhanVien.setText(modelNhanVien.getValueAt(row, 1).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 2 là cột giới tính Đưa lên combobox
			 * cmbGioiTinh
			 */
			cmbGioiTinh.setSelectedItem(modelNhanVien.getValueAt(row, 2).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 3 là cột năm sinh Đưa lên textField
			 * txtNamSinh
			 */
			txtNamSinh.setText(modelNhanVien.getValueAt(row, 3).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 4 là cột địa chỉ Đưa lên textField
			 * txtDiaChi
			 */
			txtDiaChi.setText(modelNhanVien.getValueAt(row, 4).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 5 là cột số điện thoại đưa lên TextField
			 * txtSoDienThoai
			 */
			txtSoDienThoai.setText(modelNhanVien.getValueAt(row, 5).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 6 là cột CCCD đưa lên TextField txtCCCD
			 */
			txtCCCD.setText(modelNhanVien.getValueAt(row, 6).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 7 là cột mật khẩu đưa lên TextField
			 * txtMatKhau
			 */
			txtMatKhau.setText(modelNhanVien.getValueAt(row, 7).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 8 là cột chức vụ Đưa lên combobox cmbChucVu
			 */
			cmbChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 8).toString());

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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
