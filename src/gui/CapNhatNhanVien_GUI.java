package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhanVien;

public class CapNhatNhanVien_GUI extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblCapNhatNhanVien, lblMaNhanVien, lblHoTenNhanVien, lblSoDienThoai, lblCCCD, lblGioiTinh,
			lblNamSinh, lblDiaChi, lblMatKhau, lblChucVu;
	private JTextField txtMaNhanVien, txtHoTenNhanVien, txtSoDienThoai, txtCCCD, txtNamSinh, txtDiaChi, txtMatKhau;
	private JComboBox<String> cmbGioiTinh, cmbChucVu;
	private JButton btnThem, btnSua, btnLamMoi;
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
	public CapNhatNhanVien_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nhanVien_DAO = new NhanVien_DAO();

		// Phần cập nhật nhân viên
		setSize(1600, 1055);
		setLayout(null);
//		 Tạo panel cập nhật nhân viên
//		JPanel pnlCapNhatNhanVien = new JPanel();
//		pnlCapNhatNhanVien.setLayout(null);
//		pnlCapNhatNhanVien.setBounds(300, 0, 1620, 1080);
//		pnlCapNhatNhanVien.setBackground(Color.WHITE);

		lblCapNhatNhanVien = new JLabel("Cập nhật nhân viên");
		lblCapNhatNhanVien.setBounds(621, 11, 357, 49);
		lblCapNhatNhanVien.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlCapNhatNhanVien.add(lblCapNhatNhanVien);
		add(lblCapNhatNhanVien);

		// Thêm Label Mã nhân viên
		lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setBounds(76, 100, 150, 30);
		lblMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblMaNhanVien);
		add(lblMaNhanVien);

		// Thêm Label Họ tên nhân viên
		lblHoTenNhanVien = new JLabel("Họ tên nhân viên");
		lblHoTenNhanVien.setBounds(907, 100, 180, 30);
		lblHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblHoTenNhanVien);
		add(lblHoTenNhanVien);

		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(907, 240, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblSoDienThoai);
		add(lblSoDienThoai);

		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("Căn cước công dân");
		lblCCCD.setBounds(76, 310, 200, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblCCCD);
		add(lblCCCD);

		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(76, 170, 200, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblGioiTinh);
		add(lblGioiTinh);

		// Thêm label Mật khẩu
		lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setBounds(76, 380, 150, 30);
		lblMatKhau.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblMatKhau);
		add(lblMatKhau);

		// Thêm label năm sinh
		lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setBounds(907, 170, 150, 30);
		lblNamSinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblNamSinh);
		add(lblNamSinh);

		// Thêm label chức vụ
		lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setBounds(907, 310, 150, 30);
		lblChucVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblChucVu);
		add(lblChucVu);

		// Thêm label địa chỉ
		lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(76, 240, 150, 30);
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblDiaChi);
		add(lblDiaChi);

		// Thêm TextField Mã nhân viên
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(286, 102, 410, 30);
		txtMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtMaNhanVien);
		txtMaNhanVien.setEditable(false);
		add(txtMaNhanVien);

		// Thêm TextField Họ tên nhân viên
		txtHoTenNhanVien = new JTextField();
		txtHoTenNhanVien.setBounds(1087, 102, 439, 30);
		txtHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtHoTenNhanVien);
		add(txtHoTenNhanVien);

		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1087, 242, 439, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtSoDienThoai);
		add(txtSoDienThoai);

		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(286, 312, 410, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtCCCD);
		add(txtCCCD);

		// Thêm TextField Mật khẩu
		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(286, 382, 410, 30);
		txtMatKhau.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtMatKhau);
		add(txtMatKhau);

		// Thêm textField Năm sinh
		txtNamSinh = new JTextField();
		txtNamSinh.setBounds(1087, 172, 439, 30);
		txtNamSinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtNamSinh);
		add(txtNamSinh);

		// Thêm textField Địa chỉ
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(286, 242, 410, 30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtDiaChi);
		add(txtDiaChi);

		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setBounds(286, 172, 200, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlCapNhatNhanVien.add(cmbGioiTinh);
		add(cmbGioiTinh);

		// Thêm combobox Chức vụ
		cmbChucVu = new JComboBox<String>();
		cmbChucVu.setBounds(1087, 312, 250, 30);
		cmbChucVu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbChucVu.setEditable(false);
		cmbChucVu.setFocusable(false);
//		pnlCapNhatNhanVien.add(cmbChucVu);
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
		btnThem = new JButton("Thêm");
		btnThem.setBounds(250, 440, 200, 45);
		btnThem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnThem.setBackground(new Color(217, 217, 217));
		btnThem.setFocusable(false);
//		pnlCapNhatNhanVien.add(btnThem);
		add(btnThem);

		// Button Sửa
		btnSua = new JButton("Sửa");
		btnSua.setBounds(700, 440, 200, 45);
		btnSua.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnSua.setBackground(new Color(217, 217, 217));
		btnSua.setFocusable(false);
//		pnlCapNhatNhanVien.add(btnSua);
		add(btnSua);

		// Button Làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(1150, 440, 200, 45);
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnLamMoi.setBackground(new Color(217, 217, 217));
		btnLamMoi.setFocusable(false);
//		pnlCapNhatNhanVien.add(btnLamMoi);
		add(btnLamMoi);

		// Tạp table thông tin nhân viên
		String[] header = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
				"CCCD", "Mật khẩu", "Chức vụ" };
		modelNhanVien = new DefaultTableModel(header, 0);

		add(scrNhanVien = new JScrollPane(tblNhanVien = new JTable(modelNhanVien),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrNhanVien.setBounds(59, 499, 1500, 490);
		scrNhanVien.setBackground(new Color(120, 255, 239));
		scrNhanVien.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrNhanVien.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblNhanVien.getTableHeader().setBackground(new Color(120, 255, 239));
		tblNhanVien.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		tblNhanVien.setRowHeight(50);
		tblNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

		// Set Size Width ColumnTable
		tblNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(140);
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(235);
		tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(350);
		tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(120);
		tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(140);
		tblNhanVien.getColumnModel().getColumn(8).setPreferredWidth(145);

		// Load dữ liệu từ database vào table
		loadDanhSachNhanVien();
		docDuLieuVaoTable();

		// Thêm sự kiện
		// Các button
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		// Thêm sự kiện mouseListener
		tblNhanVien.addMouseListener(this);

		// Add sự kiện cho nút enter
		txtMaNhanVien.addKeyListener(this);
		txtHoTenNhanVien.addKeyListener(this);
		txtNamSinh.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtSoDienThoai.addKeyListener(this);
		txtCCCD.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		btnThem.addKeyListener(this);
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (txtMaNhanVien.getText().trim().length() < 0) {
				try {
					txtMaNhanVien.setText(generatesIncrementalCode());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				if (validData()) {
					try {
						txtMaNhanVien.setText(generatesIncrementalCode());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						String maNhanVien = txtMaNhanVien.getText().trim();
						String hoTenNhanVien = txtHoTenNhanVien.getText().trim();
						String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
						int namSinh = Integer.parseInt(txtNamSinh.getText().trim());
						String diaChi = txtDiaChi.getText().trim();
						String soDienThoai = txtSoDienThoai.getText().trim();
						String canCuocCongDan = txtCCCD.getText().trim();
						String matKhau = txtMatKhau.getText().trim();
						String chucVu = cmbChucVu.getSelectedItem().toString();

						NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi,
								soDienThoai, canCuocCongDan, matKhau, chucVu);
						try {
							if (nhanVien_DAO.insert(nhanVien)) {
								modelNhanVien.addRow(new Object[] { nhanVien.getMaNhanVien(),
										nhanVien.getHoTenNhanVien(), nhanVien.isGioiTinh(), nhanVien.getNamSinh(),
										nhanVien.getDiaChi(), nhanVien.getSoDienThoai(), nhanVien.getCanCuocCongDan(),
										nhanVien.getMatKhau(), nhanVien.isChucVu() });
								JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
								// Thông báo cho biết rằng tất cả giá trị ô trong các hàng của bảng có thể đã
								// thay đổi.
								modelNhanVien.fireTableDataChanged();
								loadDanhSachNhanVien();
							} else {
								JOptionPane.showMessageDialog(this, "Mã nhân viên đã có (Trùng mã nhân viên)");
							}
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công");
						}
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (o.equals(btnSua)) {
			/***
			 * Lấy dữ liệu từ các textField Bắt lỗi ngoại lệ của hàm new NhanVien
			 */
			int row = tblNhanVien.getSelectedRow();
			if (row >= 0) {
				/***
				 * Chạy ngoại lệ để ném lỗi
				 */
				if (validDataUpdate()) {
					try {
						String maNhanVien = txtMaNhanVien.getText().trim();
						String hoTenNhanVien = txtHoTenNhanVien.getText().trim();
						String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
						int namSinh = Integer.parseInt(txtNamSinh.getText().trim());
						String diaChi = txtDiaChi.getText().trim();
						String soDienThoai = txtSoDienThoai.getText().trim();
						String canCuocCongDan = txtCCCD.getText().trim();
						String matKhau = txtMatKhau.getText().trim();
						String chucVu = cmbChucVu.getSelectedItem().toString();

						NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, namSinh, diaChi, soDienThoai,
								canCuocCongDan, matKhau, chucVu);
						/***
						 * Tạo biến trả về true/false Tiến hành lấy các dữ liệu trên table về textField
						 * -> tiến hành sửa Nếu đúng tất cả các dữ liệu return Cập nhật thành công ngược
						 * lại return Cập nhật thất bại
						 */
						boolean capNhat = nhanVien_DAO.update(nhanVien);
						if (capNhat) {
							modelNhanVien.setValueAt(nhanVien.getMaNhanVien(), row, 0);
							modelNhanVien.setValueAt(nhanVien.getHoTenNhanVien(), row, 1);
							modelNhanVien.setValueAt(nhanVien.isGioiTinh(), row, 2);
							modelNhanVien.setValueAt(nhanVien.getNamSinh(), row, 3);
							modelNhanVien.setValueAt(nhanVien.getDiaChi(), row, 4);
							modelNhanVien.setValueAt(nhanVien.getSoDienThoai(), row, 5);
							modelNhanVien.setValueAt(nhanVien.getCanCuocCongDan(), row, 6);
							modelNhanVien.setValueAt(nhanVien.getMatKhau(), row, 7);
							modelNhanVien.setValueAt(nhanVien.isChucVu(), row, 8);
							JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
							modelNhanVien.fireTableDataChanged();
							loadDanhSachNhanVien();
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
						}
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng muốn sửa");
			}
		} else if (o.equals(btnLamMoi)) {
			refeshTextField();
		}
	}

	// Hàm làm mới các textField
	public void refeshTextField() {
		txtMaNhanVien.setText("");
		txtHoTenNhanVien.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		txtNamSinh.setText("");
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtCCCD.setText("");
		txtMatKhau.setText("");
		cmbChucVu.setSelectedIndex(0);
		txtMaNhanVien.requestFocus();
	}

	// Hàm phát sinh mã tăng dần
	public String generatesIncrementalCode() throws Exception {
		String maNhanVien = "";
		int n = nhanVien_DAO.getAllTableNhanVien().size();
		if (n < 9) {
			do {
				n += 1;
				maNhanVien = "NV00" + String.valueOf(n);
				arlNhanVien = nhanVien_DAO.getAllTableNhanVien();
			} while (arlNhanVien.contains(maNhanVien));
		} else if (n < 99) {
			do {
				n += 1;
				maNhanVien = "NV0" + String.valueOf(n);
				arlNhanVien = nhanVien_DAO.getAllTableNhanVien();
			} while (arlNhanVien.contains(maNhanVien));
		} else if (n < 999) {
			do {
				n += 1;
				maNhanVien = "NV" + String.valueOf(n);
				arlNhanVien = nhanVien_DAO.getAllTableNhanVien();
			} while (arlNhanVien.contains(maNhanVien));
		}
		return maNhanVien;
	}

	public boolean validData() {
		String maNhanVien = txtMaNhanVien.getText().trim();
		String hoTenNhanVien = txtHoTenNhanVien.getText().trim();
//		String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		String namSinh = txtNamSinh.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String canCuocCongDan = txtCCCD.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
//		String chucVu = cmbChucVu.getSelectedItem().toString();

		if (maNhanVien.matches("(NV)\\d{3}")) {
			for (NhanVien nhanVien : arlNhanVien) {
				if (nhanVien.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
					JOptionPane.showMessageDialog(this, "Error: Mã nhân viên đã tồn tại (Trùng mã nhân viên)");
					return false;
				}
			}
		}
		if (!(hoTenNhanVien.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
			txtHoTenNhanVien.requestFocus();
			return false;
		} else if (!hoTenNhanVien.matches(
				"^([A-ZĐÁÀẢÃẠÉÈẺẼẸÍÌỉĨỊÓÒỎÕỌÔỐỒỔỖỘÚÙỦŨỤĂẮẰẲẴẶÂẤẦẨẪẬĐĨỈỊƠỞỜỞỠỢƯỨỪỬỮỰÝỲỶỸỴ][a-zđáàảãạéèẻẽẹêềếểễệíìỉĩịóòỏõọôốồổỗộúùủũụăắằẳẵặâấầẩẫậđĩỉịơởờởỡợưứừửữựýỳỷỹỵ]*(\\\\s|$))+")) {
			/***
			 * Chữ cái đầu mỗi chữ phái viết hoa Tên không có chữ số Tên không được có ký tự
			 * đặc biệt
			 */
			JOptionPane.showMessageDialog(this,
					"Error: Tên nhân viên không hợp lệ. \n Yêu cầu họ tên: \n Chữ cái đầu mỗi chữ phái viết hoa \n Tên không được có ký tự đặc biệt \n Tên không có chữ số");
			return false;
		}
		if (namSinh.length() > 0) {
			try {
				int namSinhDung = Integer.parseInt(namSinh);
				if (namSinhDung >= 2006) {
					JOptionPane.showMessageDialog(this, "Nhân viên phải trên 18 tuổi");
					txtNamSinh.selectAll();
					txtNamSinh.requestFocus();
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error: Năm sinh phải nhập là số");
				txtNamSinh.selectAll();
				txtNamSinh.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Năm sinh không được để trống");
			return false;
		}
		if (!(diaChi.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để rỗng");
			txtDiaChi.requestFocus();
			return false;
		} else if (!diaChi.matches("^[\\p{L}0-9][\\p{L}0-9\\s,.'-]*$")) {
			/***
			 * ^[\\p{L}0-9] đảm bảo rằng địa chỉ bắt đầu bằng một chữ cái hoặc số
			 * [\\p{L}0-9\\s,.'-]*$ cho phép bất kỳ ký tự chữ cái, số, khoảng trắng, dấu
			 * phẩy, dấu chấm, dấu nháy đơn, và dấu gạch ngang nào xuất hiện sau ký tự đầu
			 * tiên
			 */
			JOptionPane.showMessageDialog(this,
					"Địa chỉ không hợp lệ. (Địa chỉ không dùng các ký tự đặc biệt như (@,!,#,$,%...))");
			txtDiaChi.requestFocus();
			return false;
		}
		if (!(soDienThoai.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để rỗng");
			txtSoDienThoai.requestFocus();
			return false;
		} else if (!soDienThoai.matches("(09|08|03|07|05|02)\\d{8}")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không hợp lệ (Số điện thoại bắt đầu bằng (09, 08, 03, 07, 05, 02)) và số điện thoại có 10 số");
			return false;
		}
		if (!(canCuocCongDan.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Căn cước công dân không được để rỗng");
			txtCCCD.requestFocus();
			return false;
		} else if (!canCuocCongDan.matches("(0)\\d{11}")) {
			JOptionPane.showMessageDialog(this,
					"Căn cước không hợp lệ. Căn cước bắt đầu bằng số 0 (Căn cước có đúng 12 số)");
			return false;
		} else {
			for (NhanVien nhanVien : arlNhanVien) {
				if (nhanVien.getCanCuocCongDan().equalsIgnoreCase(canCuocCongDan)) {
					JOptionPane.showMessageDialog(this, "Error: Căn cước đã tồn tại (Trùng căn cước công dân)");
					return false;
				}
			}
		}
		if (!(matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để rỗng");
			txtMatKhau.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validDataUpdate() {
		String hoTenNhanVien = txtHoTenNhanVien.getText().trim();
		String namSinh = txtNamSinh.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String canCuocCongDan = txtCCCD.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
		if (!(hoTenNhanVien.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
			txtHoTenNhanVien.requestFocus();
			return false;
		} else if (!hoTenNhanVien.matches(
				"^([A-ZĐÁÀẢÃẠÉÈẺẼẸÍÌỉĨỊÓÒỎÕỌÚÙỦŨỤĂẮẰẲẴẶÂẤẦẨẪẬĐĨỈỊƠỞỜỞỠỢƯỨỪỬỮỰ][a-zđáàảãạéèẻẽẹêềếểễệíìỉĩịóòỏõọúùủũụăắằẳẵặâấầẩẫậđĩỉịơởờởỡợưứừửữự]*(\\s|$))+")) {
			/***
			 * Chữ cái đầu mỗi chữ phái viết hoa Tên không có chữ số Tên không được có ký tự
			 * đặc biệt
			 */

			JOptionPane.showMessageDialog(this,
					"Error: Tên nhân viên không hợp lệ. \n Yêu cầu họ tên: \n Chữ cái đầu mỗi chữ phái viết hoa \n Tên không được có ký tự đặc biệt \n Tên không có chữ số");
			return false;
		}
		if (namSinh.length() > 0) {
			try {
				int namSinhDung = Integer.parseInt(namSinh);
				if (namSinhDung >= 2006) {
					JOptionPane.showMessageDialog(this, "Nhân viên phải trên 18 tuổi");
					txtNamSinh.selectAll();
					txtNamSinh.requestFocus();
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error: Năm sinh phải nhập là số");
				txtNamSinh.selectAll();
				txtNamSinh.requestFocus();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Năm sinh không được để trống");
			return false;
		}
		if (!(diaChi.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để rỗng");
			txtDiaChi.requestFocus();
			return false;
		} else if (!diaChi.matches("^[\\p{L}0-9][\\p{L}0-9\\s,.'-]*$")) {
			/***
			 * ^[\\p{L}0-9] đảm bảo rằng địa chỉ bắt đầu bằng một chữ cái hoặc số
			 * [\\p{L}0-9\\s,.'-]*$ cho phép bất kỳ ký tự chữ cái, số, khoảng trắng, dấu
			 * phẩy, dấu chấm, dấu nháy đơn, và dấu gạch ngang nào xuất hiện sau ký tự đầu
			 * tiên
			 */
			JOptionPane.showMessageDialog(this,
					"Địa chỉ không hợp lệ. (Địa chỉ không dùng các ký tự đặc biệt như (@,!,#,$,%...))");
			txtDiaChi.requestFocus();
			return false;
		}
		if (!(soDienThoai.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để rỗng");
			txtSoDienThoai.requestFocus();
			return false;
		} else if (!soDienThoai.matches("(09|08|03|07|05|02)\\d{8}")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không hợp lệ (Số điện thoại bắt đầu bằng (09, 08, 03, 07, 05, 02)) và số điện thoại có 10 số");
			return false;
		}
		if (!(canCuocCongDan.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Căn cước công dân không được để rỗng");
			txtCCCD.requestFocus();
			return false;
		} else if (!canCuocCongDan.matches("(0)\\d{11}")) {
			JOptionPane.showMessageDialog(this,
					"Căn cước không hợp lệ. Căn cước bắt đầu bằng số 0 (Căn cước có đúng 12 số)");
			return false;
		} 
		if (!(matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để rỗng");
			txtMatKhau.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		Object key = e.getKeyCode();
		// Bắt sự kiện nhấn phím enter
		if (o.equals(txtMaNhanVien) || o.equals(txtHoTenNhanVien) || o.equals(txtNamSinh) || o.equals(txtDiaChi)
				|| o.equals(txtSoDienThoai) || o.equals(txtCCCD) || o.equals(txtMatKhau)) {
			if (key.equals(KeyEvent.VK_ENTER)) {
				btnThem.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
