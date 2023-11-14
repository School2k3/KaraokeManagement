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
import entity.NhanVien;

public class TimKiemNhanVien_GUI extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTimKiemNhanVien, lblMaNhanVien, lblHoTenNhanVien, lblSoDienThoai, lblCCCD, lblGioiTinh,
			lblNamSinh, lblDiaChi, lblChucVu;
	private JTextField txtMaNhanVien, txtHoTenNhanVien, txtSoDienThoai, txtCCCD, txtNamSinh, txtDiaChi;
	private JComboBox<String> cmbGioiTinh, cmbChucVu;
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
			e.printStackTrace();
		}

		nhanVien_DAO = new NhanVien_DAO();

		// Tạo panel tìm kiếm nhân viên
		setSize(1600, 1055);
		setLayout(null);

		lblTimKiemNhanVien = new JLabel("Tìm kiếm nhân viên");
		lblTimKiemNhanVien.setBounds(618, 11, 363, 49);
		lblTimKiemNhanVien.setFont(new Font("SansSerif", Font.BOLD, 40));
		add(lblTimKiemNhanVien);

		// Thêm Label Mã nhân viên
		lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setBounds(76, 100, 150, 30);
		lblMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblMaNhanVien);

		// Thêm Label Họ tên nhân viên
		lblHoTenNhanVien = new JLabel("Họ tên nhân viên");
		lblHoTenNhanVien.setBounds(907, 100, 180, 30);
		lblHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblHoTenNhanVien);

		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(907, 240, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblSoDienThoai);

		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("Căn cước công dân");
		lblCCCD.setBounds(76, 310, 200, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblCCCD);

		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(76, 170, 200, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblGioiTinh);

		// Thêm label Mật khẩu
//		lblMatKhau = new JLabel("Mật khẩu");
//		lblMatKhau.setBounds(76, 380, 150, 30);
//		lblMatKhau.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		add(lblMatKhau);

		// Thêm label năm sinh
		lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setBounds(907, 170, 150, 30);
		lblNamSinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblNamSinh);

		// Thêm label chức vụ
		lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setBounds(907, 310, 150, 30);
		lblChucVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblChucVu);

		// Thêm label địa chỉ
		lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(76, 240, 150, 30);
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(lblDiaChi);

		// Thêm TextField Mã nhân viên
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(286, 102, 410, 30);
		txtMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtMaNhanVien);

		// Thêm TextField Họ tên nhân viên
		txtHoTenNhanVien = new JTextField();
		txtHoTenNhanVien.setBounds(1087, 102, 439, 30);
		txtHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtHoTenNhanVien);

		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1087, 242, 439, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtSoDienThoai);

		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(286, 312, 410, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtCCCD);

		// Thêm TextField Mật khẩu
//		txtMatKhau = new JTextField();
//		txtMatKhau.setBounds(286, 382, 410, 30);
//		txtMatKhau.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		add(txtMatKhau);

		// Thêm textField Năm sinh
		txtNamSinh = new JTextField();
		txtNamSinh.setBounds(1087, 172, 439, 30);
		txtNamSinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtNamSinh);

		// Thêm textField Địa chỉ
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(286, 242, 410, 30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(txtDiaChi);

		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setBounds(286, 172, 200, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
		add(cmbGioiTinh);

		// Thêm combobox Chức vụ
		cmbChucVu = new JComboBox<String>();
		cmbChucVu.setBounds(1087, 312, 250, 30);
		cmbChucVu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbChucVu.setEditable(false);
		cmbChucVu.setFocusable(false);
		add(cmbChucVu);

		// Add item cho Combobox
		cmbGioiTinh.addItem("");
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");
		cmbGioiTinh.setSelectedIndex(0);
		cmbGioiTinh.requestFocus();

		cmbChucVu.addItem("");
		cmbChucVu.addItem("Nhân viên");
		cmbChucVu.addItem("Nhân viên quản lý");
		cmbChucVu.setSelectedIndex(0);
		cmbGioiTinh.requestFocus();

		// Thêm các button
		// Button thêm
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(400, 400, 200, 45);
		btnTimKiem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
//		pnlTimKiemNhanVien.add(btnTimKiem);
		add(btnTimKiem);

		// Button Làm mới
		btnXemDanhSachNhanVien = new JButton("Xem danh sách nhân viên");
		btnXemDanhSachNhanVien.setBounds(890, 400, 400, 45);
		btnXemDanhSachNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnXemDanhSachNhanVien.setBackground(new Color(217, 217, 217));
		btnXemDanhSachNhanVien.setFocusable(false);
//		pnlTimKiemNhanVien.add(btnXemDanhSachNhanVien);
		add(btnXemDanhSachNhanVien);

		// Tạp table thông tin nhân viên
		String[] header = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
				"CCCD", "Chức vụ" };
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
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(240);
		tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(390);
		tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(160);
		tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(160);
		tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(190);

		// Load dữ liệu từ database vào table
		loadDanhSachNhanVien();
		docDuLieuVaoTable();

		// Thêm sự kiện
		// Các button
		btnTimKiem.addActionListener(this);
		btnXemDanhSachNhanVien.addActionListener(this);

		// Thêm sự kiện mouseListener
		tblNhanVien.addMouseListener(this);
		
		// Thêm sự kiện nhấn nút enter
		txtMaNhanVien.addKeyListener(this);
		txtHoTenNhanVien.addKeyListener(this);
		txtNamSinh.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtSoDienThoai.addKeyListener(this);
		txtCCCD.addKeyListener(this);
		btnTimKiem.addKeyListener(this);
	}

	/**
	 * Hàm tải dữ liệu từ database
	 */
	private void loadDanhSachNhanVien() throws Exception {
		/*
		 * Lấy dữ liệu từ database
		 */
		arlNhanVien = nhanVien_DAO.getAllTableNhanVienTruMatKhau();
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
					nhanVien.getCanCuocCongDan(), nhanVien.isChucVu() });
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
			 * Lấy dữ liệu từ dòng được chọn cột 7 là cột chức vụ Đưa lên combobox cmbChucVu
			 */
			cmbChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 7).toString());

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
		if (o.equals(btnTimKiem)) {
			/***
			 * Tạo các biến lấy thông tin của các textField đang có Xóa tất cả các dòng dữ
			 * liệu hiện có để khi tìm thấy thông tin cần thiết thì hiện ra dòng dữ liệu đó
			 */
			String namSinh = txtNamSinh.getText().trim();
			if (namSinh.equalsIgnoreCase("")) {
				try {
					timKiem();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					timKiem();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXemDanhSachNhanVien))

		{
			/***
			 * Xóa tất cả các dòng dữ liệu có trong table sau đó Load lại dữ liệu mới nhất
			 * Nếu danh sách rỗng hoặc có độ lớn <=0 (Có nghĩa là không có dữ liệu) =>>
			 * thông báo "Không tìm thấy" Ngược lại đọc dữ liệu mới nhất vào table
			 */
			modelNhanVien.getDataVector().removeAllElements();
			try {
				loadDanhSachNhanVien();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (arlNhanVien == null || arlNhanVien.size() <= 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào");
				refesh();
			} else {
				try {
					docDuLieuVaoTable();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refesh();
			}
		}
	}

	// Hàm tạo mới các textField
	public void refesh() {
		txtMaNhanVien.setText("");
		txtHoTenNhanVien.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		txtNamSinh.setText("");
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtCCCD.setText("");
		cmbChucVu.setSelectedIndex(0);
	}

	public void timKiem() throws Exception {
		String maNhanVien = txtMaNhanVien.getText().trim();
		String hoTenNhanVien = txtHoTenNhanVien.getText().trim();
		String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		String namSinh = txtNamSinh.getText().trim();
		Integer namSinhConvert = 0;
		try {
			if (!namSinh.equalsIgnoreCase("")) {
				namSinhConvert = Integer.parseInt(namSinh);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Năm sinh phải nhập là số");
		}
		String diaChi = txtDiaChi.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String canCuocCongDan = txtCCCD.getText().trim();
		String chucVu = cmbChucVu.getSelectedItem().toString();
		modelNhanVien.getDataVector().removeAllElements();
		/***
		 * Tìm all
		 */
		if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienByAll(maNhanVien, hoTenNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		/***
		 * Tìm 7 field (Trừ 1 field)
		 */
		// Trừ chức vụ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {

			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVu(maNhanVien, hoTenNhanVien, gioiTinh, namSinhConvert,
					diaChi, soDienThoai, canCuocCongDan);
		}
		// Trừ căn cước
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuoc(maNhanVien, hoTenNhanVien, gioiTinh, namSinhConvert,
					diaChi, soDienThoai, chucVu);
		}
		// Trừ số điện thoại
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoai(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChi(maNhanVien, hoTenNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty()) && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinh(maNhanVien, hoTenNhanVien, gioiTinh, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruGioiTinh(maNhanVien, hoTenNhanVien, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruHoTen(maNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruMa(hoTenNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}

		/***
		 * Tìm 6 field (Trừ 2 field)
		 */

		// Trừ chức vụ, căn cước
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuoc(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, soDienThoai);
		}
		// Trừ chức vụ, số điện thoại
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoai(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, canCuocCongDan);
		}

		// Trừ chức vụ, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChi(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndGioiTinh(maNhanVien, hoTenNhanVien, namSinhConvert,
					diaChi, soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndHoTen(maNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndMa(hoTenNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan);
		}

		// Trừ căn cước, số điện thoại
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoai(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, chucVu);
		}
		// Trừ căn cước, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChi(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndGioiTinh(maNhanVien, hoTenNhanVien, namSinhConvert,
					diaChi, soDienThoai, chucVu);
		}
		// Trừ căn cước, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndHoTen(maNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndMa(hoTenNhanVien, gioiTinh, namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ số điện thoại, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChi(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh,
					diaChi, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, diaChi, canCuocCongDan, chucVu);
		}
		// Trừ sô điện thoại, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndHoTen(maNhanVien, gioiTinh, namSinhConvert,
					diaChi, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndMa(hoTenNhanVien, gioiTinh, namSinhConvert,
					diaChi, canCuocCongDan, chucVu);
		}

		// Trừ địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndGioiTinh(maNhanVien, hoTenNhanVien, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndHoTen(maNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndMa(hoTenNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty()) && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndHoTen(maNhanVien, gioiTinh, diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndMa(hoTenNhanVien, gioiTinh, diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruGioiTinhAndHoTen(maNhanVien, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruGioiTinhAndMa(hoTenNhanVien, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan, chucVu);
		}

		// Trừ họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruHoTenAndMa(gioiTinh, namSinhConvert, diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}

		/***
		 * Tìm 5 field (Trừ 3 field)
		 */

		// Trừ chức vụ, căn cước, số điện thoại
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoai(maNhanVien, hoTenNhanVien,
					gioiTinh, namSinhConvert, diaChi);
		}
		// Trừ chức vụ, căn cước, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChi(maNhanVien, hoTenNhanVien, gioiTinh,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndHoTen(maNhanVien, gioiTinh, namSinhConvert,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndMa(hoTenNhanVien, gioiTinh, namSinhConvert,
					diaChi, soDienThoai);
		}

		// Trừ chức vụ, số điện thoại, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChi(maNhanVien, hoTenNhanVien,
					gioiTinh, namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinh(maNhanVien, hoTenNhanVien,
					gioiTinh, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, canCuocCongDan);
		}

		// Trừ chức vụ, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndHoTen(maNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndMa(hoTenNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan);
		}

		// Trừ chức vụ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndHoTen(maNhanVien, gioiTinh, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndMa(hoTenNhanVien, gioiTinh, diaChi,
					soDienThoai, canCuocCongDan);
		}

		// Trừ chức vụ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndGioiTinhAndHoTen(maNhanVien, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndHoTenAndMa(gioiTinh, namSinhConvert, diaChi,
					soDienThoai, canCuocCongDan);
		}

		// Trừ căn cước, sô điện thoại, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChi(maNhanVien, hoTenNhanVien,
					gioiTinh, namSinhConvert, chucVu);
		}
		// Trừ căn cước, sô điện thoại, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinh(maNhanVien, hoTenNhanVien,
					gioiTinh, diaChi, chucVu);
		}
		// Trừ căn cước, sô điện thoại, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, diaChi, chucVu);
		}
		// Trừ căn cước, sô điện thoại, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, diaChi, chucVu);
		}
		// Trừ căn cước, sô điện thoại, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi, chucVu);
		}

		// Trừ căn cước, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinh(maNhanVien, hoTenNhanVien, gioiTinh,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndHoTen(maNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndMa(hoTenNhanVien, gioiTinh, namSinhConvert,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndHoTen(maNhanVien, gioiTinh, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndMa(hoTenNhanVien, gioiTinh, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndGioiTinhAndHoTen(maNhanVien, namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndHoTenAndMa(gioiTinh, namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}

		// Trừ số điện thoại, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinh(maNhanVien, hoTenNhanVien,
					gioiTinh, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien,
					diaChi, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndHoTen(maNhanVien, gioiTinh, diaChi,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndMa(hoTenNhanVien, gioiTinh, diaChi,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndGioiTinhAndHoTen(maNhanVien, namSinhConvert,
					diaChi, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert,
					diaChi, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndHoTenAndMa(gioiTinh, namSinhConvert, diaChi,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndHoTen(maNhanVien, gioiTinh, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndMa(hoTenNhanVien, gioiTinh, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndGioiTinhAndHoTen(maNhanVien, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert,
					soDienThoai, canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndHoTenAndMa(gioiTinh, namSinhConvert, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndGioiTinhAndHoTen(maNhanVien, diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty()) && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndGioiTinhAndMa(hoTenNhanVien, diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty()) && namSinh.equalsIgnoreCase("")) {

		}
		// Trừ giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {

		}

		/***
		 * Tìm 4 field
		 */
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChi(maNhanVien,
					hoTenNhanVien, gioiTinh, namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinh(maNhanVien,
					hoTenNhanVien, gioiTinh, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinh(maNhanVien,
					hoTenNhanVien, namSinhConvert, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, diaChi);
		}

		// Trừ chức vụ, căn cước, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinh(maNhanVien, hoTenNhanVien,
					gioiTinh, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinh(maNhanVien, hoTenNhanVien,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, soDienThoai);
		}

		// Trừ chức vụ, căn cước, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndHoTen(maNhanVien, namSinhConvert,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndHoTenAndMa(gioiTinh, namSinhConvert, diaChi,
					soDienThoai);
		}

		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinh(maNhanVien,
					hoTenNhanVien, gioiTinh, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinh(maNhanVien,
					hoTenNhanVien, namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndHoTenAndMa(gioiTinh, namSinhConvert,
					diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinh(maNhanVien, hoTenNhanVien,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					soDienThoai, canCuocCongDan);
		}

		// Trừ chức vụ, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndHoTen(maNhanVien, namSinhConvert,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndHoTenAndMa(gioiTinh, namSinhConvert,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndHoTen(maNhanVien, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, diaChi,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndHoTenAndMa(gioiTinh, diaChi, soDienThoai,
					canCuocCongDan);
		}

		// Trừ căn cước, số điện thoai, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinh(maNhanVien,
					hoTenNhanVien, gioiTinh, chucVu);
		}
		// Trừ căn cước, số điện thoai, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinh(maNhanVien,
					hoTenNhanVien, namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoai, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndHoTen(maNhanVien, gioiTinh,
					namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoai, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndMa(hoTenNhanVien, gioiTinh,
					namSinhConvert, chucVu);
		}

		// Trừ căn cước, số điện thoai, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoai, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoai, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoai, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoai, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, diaChi, chucVu);
		}
		// Trừ căn cước, sô điện thoại, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndHoTenAndMa(gioiTinh, namSinhConvert,
					diaChi, chucVu);
		}
		// Trừ căn cước, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndHoTen(maNhanVien, namSinhConvert,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndMa(hoTenNhanVien, namSinhConvert,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndHoTenAndMa(gioiTinh, namSinhConvert,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndGioiTinhAndHoTen(maNhanVien, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ căn cước, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndNamSinhAndHoTenAndMa(gioiTinh, diaChi, soDienThoai,
					chucVu);
		}
		// Trừ căn cước, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndGioiTinhAndHoTenAndMa(namSinhConvert, diaChi,
					soDienThoai, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndHoTen(maNhanVien, gioiTinh,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndMa(hoTenNhanVien, gioiTinh,
					canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndHoTenAndMa(gioiTinh, namSinhConvert,
					canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(maNhanVien, diaChi,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, diaChi,
					canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndHoTenAndMa(gioiTinh, diaChi,
					canCuocCongDan, chucVu);
		}

		// Trừ số điện thoại, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndGioiTinhAndHoTenAndMa(namSinhConvert, diaChi,
					canCuocCongDan, chucVu);
		}

		// Trừ địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndHoTenAndMa(gioiTinh, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert, soDienThoai,
					canCuocCongDan, chucVu);
		}
		// Trừ năm sinh, giới tính, họ tên, mã
		else if (!diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruNamSinhAndGioiTinhAndHoTenAndMa(diaChi, soDienThoai,
					canCuocCongDan, chucVu);
		}

		/***
		 * Tìm 3 field (Trừ 5 field)
		 */
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& (gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinh(maNhanVien,
					hoTenNhanVien, gioiTinh);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinh(maNhanVien,
					hoTenNhanVien, namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndHoTen(maNhanVien,
					gioiTinh, namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndMa(hoTenNhanVien,
					gioiTinh, namSinhConvert);
		}

		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndHoTen(maNhanVien,
					gioiTinh, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndMa(hoTenNhanVien,
					gioiTinh, diaChi);
		}

		// Trừ chức vụ, căn cước, số điện thoại, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, diaChi);
		}

		// Trừ chức vụ, căn cước, số điện thoại, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndHoTenAndMa(gioiTinh,
					namSinhConvert, diaChi);
		}

		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndHoTen(maNhanVien,
					gioiTinh, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndMa(hoTenNhanVien,
					gioiTinh, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndHoTenAndMa(gioiTinh,
					namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					diaChi, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndHoTenAndMa(gioiTinh, diaChi,
					soDienThoai);
		}
		// Trừ chức vụ, căn cước, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndGioiTinhAndHoTenAndMa(namSinhConvert,
					diaChi, soDienThoai);
		}

		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(maNhanVien,
					gioiTinh, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndMa(hoTenNhanVien,
					gioiTinh, canCuocCongDan);
		}

		// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, canCuocCongDan);
		}

		// Trừ chức vụ, số điện thoại, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndHoTenAndMa(gioiTinh,
					namSinhConvert, canCuocCongDan);
		}

		// Trừ chức vụ, số điện thoại, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					diaChi, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					diaChi, canCuocCongDan);
		}

		// Trừ chức vụ, số điện thoại, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndHoTenAndMa(gioiTinh, diaChi,
					canCuocCongDan);
		}

		// Trừ chức vụ, số điện thoại, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndGioiTinhAndHoTenAndMa(namSinhConvert,
					diaChi, canCuocCongDan);
		}

		// Trừ chức vụ, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh, soDienThoai,
					canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert,
					soDienThoai, canCuocCongDan);
		}
		// Trừ chức vụ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi, soDienThoai,
					canCuocCongDan);
		}

		// Trừ căn cước, sô điện thoại, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(maNhanVien,
					hoTenNhanVien, chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(maNhanVien,
					gioiTinh, chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndMa(hoTenNhanVien,
					gioiTinh, chucVu);
		}

		// Trừ căn cước, số điện thoại, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(maNhanVien,
					namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndMa(hoTenNhanVien,
					namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndHoTenAndMa(gioiTinh,
					namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoại, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoại, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					diaChi, chucVu);
		}
		// Trừ căn cước, số điện thoại, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndHoTenAndMa(gioiTinh, diaChi,
					chucVu);
		}
		// Trừ căn cước, số điện thoại, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndGioiTinhAndHoTenAndMa(namSinhConvert,
					diaChi, chucVu);
		}
		// Trừ căn cước, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					soDienThoai, chucVu);
		}

		// Trừ căn cước, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					soDienThoai, chucVu);
		}

		// Trừ căn cước, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh, soDienThoai,
					chucVu);
		}

		// Trừ căn cước, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert,
					soDienThoai, chucVu);
		}

		// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert,
					canCuocCongDan, chucVu);
		}
		// Trừ số điện thoại, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi,
					canCuocCongDan, chucVu);
		}
		// Trừ địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(soDienThoai,
					canCuocCongDan, chucVu);
		}

		/***
		 * Tìm 2 field (Trừ 6 field)
		 */
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính
		else if (!maNhanVien.equalsIgnoreCase("") && !hoTenNhanVien.equalsIgnoreCase("")
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinh(
					maNhanVien, hoTenNhanVien);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTen(maNhanVien, gioiTinh);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
				&& namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndMa(hoTenNhanVien, gioiTinh);
		}

		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTen(
					maNhanVien, namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndMa(
					hoTenNhanVien, namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && !namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndHoTenAndMa(gioiTinh,
					namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTen(maNhanVien, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndHoTenAndMa(gioiTinh,
					diaChi);
		}
		// Trừ chức vụ, căn cước, số điện thoại, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndGioiTinhAndHoTenAndMa(namSinhConvert, diaChi);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh,
					soDienThoai);
		}
		// Trừ chức vụ, căn cước, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert, soDienThoai);
		}
		// Trừ chức vụ, căn cước, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndCanCuocAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi,
					soDienThoai);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(
					maNhanVien, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(
					hoTenNhanVien, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh,
					canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiGioiTinhAndHoTenAndMa(
					namSinhConvert, canCuocCongDan);
		}
		// Trừ chức vụ, số điện thoại, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi,
					canCuocCongDan);
		}
		// Trừ chức vụ, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruChucVuAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(soDienThoai,
					canCuocCongDan);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien, chucVu);
		}
		// Trừ căn cước, sô điện thoại, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien, chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh,
					chucVu);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert, chucVu);
		}
		// Trừ căn cước, số điện thoại, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi,
					chucVu);
		}
		// Trừ căn cước, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO.getListNhanVienTruCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(soDienThoai,
					chucVu);
		}
		// Trừ số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
				&& (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(canCuocCongDan, chucVu);
		}

		/***
		 * Tìm 1 field (Trừ 7 field)
		 */
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên
		else if (!maNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTen(maNhanVien);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, mã
		else if (!hoTenNhanVien.equalsIgnoreCase("") && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndMa(hoTenNhanVien);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, năm sinh, họ tên, mã
		else if ((gioiTinh != null || gioiTinh.isEmpty()) && namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndHoTenAndMa(gioiTinh);
		}
		// Trừ chức vụ, căn cước, số điện thoại, địa chỉ, giới tính, họ tên, mã
		else if (!namSinh.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndDiaChiAndGioiTinhAndHoTenAndMa(namSinhConvert);
		}
		// Trừ chức vụ, căn cước, số điện thoại, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !diaChi.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndSoDienThoaiAndNamSinhAndGioiTinhAndHoTenAndMa(diaChi);
		}
		// Trừ chức vụ, căn cước, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndCanCuocAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(soDienThoai);
		}
		// Trừ chức vụ, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruChucVuAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(canCuocCongDan);
		}
		// Trừ căn cước, số điện thoại, địa chỉ, năm sinh, giới tính, họ tên, mã
		else if (namSinh.equalsIgnoreCase("") && (chucVu != null || chucVu.isEmpty())) {
			arlNhanVien = nhanVien_DAO
					.getListNhanVienTruCanCuocAndSoDienThoaiAndDiaChiAndNamSinhAndGioiTinhAndHoTenAndMa(chucVu);
		}

		/***
		 * Nếu danh sách rỗng và kích thước nhỏ hơn 0 Thì không tìm thấy Ngược lại thì
		 * đọc dữ liệu hiện có vào table
		 */
		if (arlNhanVien == null || arlNhanVien.size() <= 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
		} else {
			try {
				docDuLieuVaoTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		Object key = e.getKeyCode();
		// Bắt sự kiện enter cho btnTimKiem
		if (o.equals(txtMaNhanVien) || o.equals(txtHoTenNhanVien) || o.equals(txtNamSinh) || o.equals(txtDiaChi)
				|| o.equals(txtSoDienThoai) || o.equals(txtCCCD)) {
			if(key.equals(KeyEvent.VK_ENTER)) {
				btnTimKiem.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
