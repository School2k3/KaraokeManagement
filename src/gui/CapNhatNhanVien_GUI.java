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
import entity.KhachHang;
import entity.NhanVien;

public class CapNhatNhanVien_GUI extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JLabel lblCapNhatNhanVien, lblMaNhanVien, lblHoTenNhanVien, 
					lblSoDienThoai, lblCCCD, lblGioiTinh, lblNamSinh, lblDiaChi, lblMatKhau, lblChucVu;
	private JTextField txtMaNhanVien, txtHoTenNhanVien, txtSoDienThoai, txtCCCD, txtNamSinh, txtDiaChi, txtMatKhau;
	private JComboBox cmbGioiTinh, cmbChucVu;
	private JButton btnThem, btnSua, btnLamMoi;
	private JScrollPane scrNhanVien;
	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private ArrayList<NhanVien> arlNhanVien;
	private NhanVien_DAO nhanVien_DAO;
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public CapNhatNhanVien_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
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
		lblHoTenNhanVien.setBounds(76, 170, 200, 30);
		lblHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblHoTenNhanVien);
		add(lblHoTenNhanVien);
		
		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(907, 100, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblSoDienThoai);
		add(lblSoDienThoai);
		
		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("CCCD");
		lblCCCD.setBounds(907, 170, 150, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblCCCD);
		add(lblCCCD);
		
		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(76, 240, 150, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblGioiTinh);
		add(lblGioiTinh);
		
		// Thêm label Mật khẩu
		lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setBounds(907, 240, 150, 30);
		lblMatKhau.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblMatKhau);
		add(lblMatKhau);
		
		// Thêm label năm sinh
		lblNamSinh = new JLabel("Năm sinh");
		lblNamSinh.setBounds(76, 310, 150, 30);
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
		lblDiaChi.setBounds(76, 380, 150, 30);
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatNhanVien.add(lblDiaChi);
		add(lblDiaChi);
		
		// Thêm TextField Mã nhân viên
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(286, 102, 410, 30);
		txtMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtMaNhanVien);
		add(txtMaNhanVien);
		
		// Thêm TextField Họ tên nhân viên
		txtHoTenNhanVien = new JTextField();
		txtHoTenNhanVien.setBounds(286, 172, 410, 30);
		txtHoTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtHoTenNhanVien);
		add(txtHoTenNhanVien);
		
		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1087, 102, 439, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtSoDienThoai);
		add(txtSoDienThoai);
		
		
		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(1087, 172, 439, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtCCCD);
		add(txtCCCD);
		
		// Thêm TextField Mật khẩu
		txtMatKhau = new JTextField();
		txtMatKhau.setBounds(1087, 242, 439, 30);
		txtMatKhau.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtMatKhau);
		add(txtMatKhau);
		
		// Thêm textField Năm sinh
		txtNamSinh = new JTextField();
		txtNamSinh.setBounds(286, 312, 410, 30);
		txtNamSinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtNamSinh);
		add(txtNamSinh);
		
		// Thêm textField Địa chỉ
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(286, 382, 900, 30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatNhanVien.add(txtDiaChi);
		add(txtDiaChi);
		
		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setBounds(286, 242, 200, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlCapNhatNhanVien.add(cmbGioiTinh);
		add(cmbGioiTinh);
		
		// Thêm combobox Chức vụ
		cmbChucVu = new JComboBox();
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
		String[] header = {
				"Mã nhân viên", "Tên nhân viên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại", "CCCD", "Mật khẩu", "Chức vụ" 
		};
		modelNhanVien = new DefaultTableModel(header, 0);
		
		add(scrNhanVien = new JScrollPane(tblNhanVien = new JTable(modelNhanVien), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrNhanVien.setBounds(76, 499, 1450, 490);
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
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		
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
		 * Nếu arlNhanVien(danh sách nhân viên) rỗng và độ lớn danh sách <= 0 Thì
		 * không trả về gì cả Ngược lại duyệt danh sách nhân viên bằng vòng for Sau đó
		 * thêm dòng dữ liệu đối tượng nhân viên với các thuộc tính liên quan vào model
		 */
		if (arlNhanVien == null || arlNhanVien.size() <= 0)
			return;
		for (NhanVien nhanVien : arlNhanVien) {
			modelNhanVien.addRow(new Object[] {
					nhanVien.getMaNhanVien(), nhanVien.getHoTenNhanVien(), nhanVien.isGioiTinh(), nhanVien.getNamSinh(), nhanVien.getDiaChi(),
					nhanVien.getSoDienThoai(), nhanVien.getCanCuocCongDan(), nhanVien.getMatKhau(), nhanVien.isChucVu()
			});
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
			 * Lấy dữ liệu từ dòng được chọn cột 1 là cột họ tên nhân viên Đưa lên
			 * textField txtHoTenNhanVien
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
			 * Lấy dữ liệu từ dòng được chọn cột 4 là cột địa chỉ Đưa lên textField txtDiaChi
			 */
			txtDiaChi.setText(modelNhanVien.getValueAt(row, 4).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 5 là cột số điện thoại đưa lên TextField txtSoDienThoai
			 */
			txtSoDienThoai.setText(modelNhanVien.getValueAt(row, 5).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 6 là cột CCCD đưa lên TextField txtCCCD
			 */
			txtCCCD.setText(modelNhanVien.getValueAt(row, 6).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 7 là cột mật khẩu đưa lên TextField txtMatKhau
			 */
			txtMatKhau.setText(modelNhanVien.getValueAt(row, 7).toString());
			/**
			 * Lấy dữ liệu từ dòng được chọn cột 8 là cột chức vụ Đưa lên combobox
			 * cmbChucVu
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
