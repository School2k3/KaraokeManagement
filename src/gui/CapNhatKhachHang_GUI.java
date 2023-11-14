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
import dao.KhachHang_DAO;
import entity.KhachHang;

public class CapNhatKhachHang_GUI extends JPanel implements ActionListener, MouseListener, KeyListener {
	private JLabel lblCapNhatKhachHang, lblMaKhachHang, lblHoTenKhachHang, lblSoDienThoai, lblCCCD, lblGioiTinh;
	private JTextField txtMaKhachHang, txtHoTenKhachHang, txtSoDienThoai, txtCCCD;
	private JComboBox<String> cmbGioiTinh;
	private JButton btnThem, btnSua, btnLamMoi;
	private JScrollPane scrKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tblKhachHang;
	private static final long serialVersionUID = 1L;
	private ArrayList<KhachHang> arlKhachHang;
	private KhachHang_DAO khachHang_DAO;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public CapNhatKhachHang_GUI() throws Exception {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		khachHang_DAO = new KhachHang_DAO();
		setSize(1600, 1055);
		setLayout(null);
		// Phần cập nhật khách hàng
		lblCapNhatKhachHang = new JLabel("Cập nhật khách hàng");
		lblCapNhatKhachHang.setBounds(603, 11, 393, 49);
		lblCapNhatKhachHang.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlCapNhatKhachHang.add(lblCapNhatKhachHang);
		add(lblCapNhatKhachHang);

		// Thêm Label Mã khách hàng
		lblMaKhachHang = new JLabel("Mã khách hàng");
		lblMaKhachHang.setBounds(55, 98, 150, 30);
		lblMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblMaKhachHang);
		add(lblMaKhachHang);

		// Thêm Label Họ tên khách hàng
		lblHoTenKhachHang = new JLabel("Họ tên khách hàng");
		lblHoTenKhachHang.setBounds(900, 100, 200, 30);
		lblHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblHoTenKhachHang);
		add(lblHoTenKhachHang);

		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(55, 170, 200, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblGioiTinh);
		add(lblGioiTinh);

		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(900, 170, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblSoDienThoai);
		add(lblSoDienThoai);

		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("Căn cước công dân");
		lblCCCD.setBounds(55, 242, 200, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblCCCD);
		add(lblCCCD);

		// Thêm TextField Mã khách hàng
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(265, 100, 300, 30);
		txtMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		txtMaKhachHang.setEditable(false);
//		pnlCapNhatKhachHang.add(txtMaKhachHang);
		add(txtMaKhachHang);

		// Thêm TextField Họ tên khách hàng
		txtHoTenKhachHang = new JTextField();
		txtHoTenKhachHang.setBounds(1100, 102, 300, 30);
		txtHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtHoTenKhachHang);
		add(txtHoTenKhachHang);

		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setBounds(265, 170, 300, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlCapNhatKhachHang.add(cmbGioiTinh);
		add(cmbGioiTinh);

		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1100, 172, 300, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtSoDienThoai);
		add(txtSoDienThoai);

		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(265, 242, 300, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtCCCD);
		add(txtCCCD);

		// Add item cho Combobox
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");

		// Thêm Các button

		// Button Thêm
		btnThem = new JButton("Thêm");
		btnThem.setBounds(620, 245, 200, 45);
		btnThem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnThem.setBackground(new Color(217, 217, 217));
		btnThem.setFocusable(false);
//		pnlCapNhatKhachHang.add(btnThem);
		add(btnThem);

		// Button Sửa
		btnSua = new JButton("Sửa");
		btnSua.setBounds(934, 245, 200, 45);
		btnSua.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnSua.setBackground(new Color(217, 217, 217));
		btnSua.setFocusable(false);
//		pnlCapNhatKhachHang.add(btnSua);
		add(btnSua);

		// Button Làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(1231, 245, 200, 45);
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnLamMoi.setBackground(new Color(217, 217, 217));
		btnLamMoi.setFocusable(false);
//		pnlCapNhatKhachHang.add(btnLamMoi);
		add(btnLamMoi);

		// Tạo Table thông tin khách hàng
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Căn cước công dân" };
		modelKhachHang = new DefaultTableModel(header, 0);

		add(scrKhachHang = new JScrollPane(tblKhachHang = new JTable(modelKhachHang),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrKhachHang.setBounds(55, 343, 1483, 622);
		scrKhachHang.setBackground(new Color(120, 255, 239));
		scrKhachHang.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrKhachHang.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblKhachHang.getTableHeader().setBackground(new Color(120, 255, 239));
		tblKhachHang.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		tblKhachHang.setRowHeight(50);
		tblKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

		// Set Size Width ColumnTable
//		tblKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(200);
//		tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(400);
//		tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(150);
//		tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(220);
//		tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(210);

		// Add Sự kiện cho các button
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
//		btnDangXuat.addActionListener(this);

		// Thêm sự kiện mouseListenner
		tblKhachHang.addMouseListener(this);

		// Add sự kiện nút enter
		txtMaKhachHang.addKeyListener(this);
		txtHoTenKhachHang.addKeyListener(this);
		txtSoDienThoai.addKeyListener(this);
		txtCCCD.addKeyListener(this);
		btnThem.addKeyListener(this);

		// Load dữ liệu từ database vào table
		loadDanhSachKhachHang();
		docDuLieuVaoTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (txtMaKhachHang.getText().trim().length() < 0) {
				try {
					txtMaKhachHang.setText(generatesIncrementalCode());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {

				if (validData()) {
					try {
						txtMaKhachHang.setText(generatesIncrementalCode());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					String maKhachHang = txtMaKhachHang.getText().trim();
					String hoTenKhachHang = txtHoTenKhachHang.getText().trim();
					String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
					String soDienThoai = txtSoDienThoai.getText().trim();
					String canCuocCongDan = txtCCCD.getText().trim();
					try {
						KhachHang khachHang = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh, soDienThoai,
								canCuocCongDan);
						try {
							if (khachHang_DAO.insert(khachHang)) {
								modelKhachHang.addRow(new Object[] { khachHang.getMaKhachHang(),
										khachHang.getHoTenKhachHang(), khachHang.isGioiTinh(),
										khachHang.getSoDienThoai(), khachHang.getCanCuocCongDan() });
								JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
								// Thông báo cho biết rằng tất cả giá trị ô trong các hàng của bảng có thể đã
								// thay đổi.
								modelKhachHang.fireTableDataChanged();
								loadDanhSachKhachHang();
							} else {
								JOptionPane.showMessageDialog(this, "Mã khách hàng đã có (Trùng mã)");
							}
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(this, "Thêm khách hàng không thành công");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		} else if (o.equals(btnSua)) {
			String maKhachHang = txtMaKhachHang.getText().trim();
			String hoTenKhachHang = txtHoTenKhachHang.getText().trim();
			String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
			String soDienThoai = txtSoDienThoai.getText().trim();
			String canCuocCongDan = txtCCCD.getText().trim();
			int row = tblKhachHang.getSelectedRow();
			if (row >= 0) {
				if (validDataUpdate()) {
					try {
						KhachHang khachHang = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh, soDienThoai,
								canCuocCongDan);
						try {
							boolean capNhat = khachHang_DAO.update(khachHang);
							if (capNhat) {
								modelKhachHang.setValueAt(khachHang.getMaKhachHang(), row, 0);
								modelKhachHang.setValueAt(khachHang.getHoTenKhachHang(), row, 1);
								modelKhachHang.setValueAt(khachHang.isGioiTinh(), row, 2);
								modelKhachHang.setValueAt(khachHang.getSoDienThoai(), row, 3);
								modelKhachHang.setValueAt(khachHang.getCanCuocCongDan(), row, 4);
								JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
								modelKhachHang.fireTableDataChanged();
								loadDanhSachKhachHang();
							} else {
								JOptionPane.showMessageDialog(this, "Cập nhật khách hàng không thành công");
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
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

	/**
	 * Hàm tải dữ liệu từ database
	 */
	private void loadDanhSachKhachHang() throws Exception {
		/*
		 * Lấy dữ liệu từ database
		 */
		arlKhachHang = khachHang_DAO.getAllTableKhachHang();
	}

	/**
	 * Hàm đọc dữ liệu vào database addRow: Thêm các dòng dữ liệu từ database vào
	 * table
	 * 
	 * @throws Exception
	 */
	public void docDuLieuVaoTable() throws Exception {
		/**
		 * Nếu arlKhachHang(danh sách khách hàng) rỗng và độ lớn danh sách <= 0 Thì
		 * không trả về gì cả Ngược lại duyệt danh sách khách hàng bằng vòng for Sau đó
		 * thêm dòng dữ liệu đối tượng khách hàng với các thuộc tính liên quan vào model
		 */
		if (arlKhachHang == null || arlKhachHang.size() <= 0)
			return;
		for (KhachHang khachHang : arlKhachHang) {
			modelKhachHang.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTenKhachHang(),
					khachHang.isGioiTinh(), khachHang.getSoDienThoai(), khachHang.getCanCuocCongDan() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblKhachHang)) {
			/*
			 * Tạo biến row có kiểu dữ liệu int Để table Khách hàng lấy dòng được chọn
			 */
			int row = tblKhachHang.getSelectedRow();
			/*
			 * Lấy dữ liệu từ dòng được chọn cột 0 là cột mã khách hàng Đưa lên textField
			 * txtKhachHang
			 */
			txtMaKhachHang.setText(modelKhachHang.getValueAt(row, 0).toString());
			/*
			 * Lấy dữ liệu từ dòng được chọn cột 1 là cột họ tên khách hàng Đưa lên
			 * textField txtHoTenKhachHang
			 */
			txtHoTenKhachHang.setText(modelKhachHang.getValueAt(row, 1).toString());
			/*
			 * Lấy dữ liệu từ dòng được chọn cột 2 là cột giới tính Đưa lên combobox
			 * cmbGioiTinh
			 */
			cmbGioiTinh.setSelectedItem(modelKhachHang.getValueAt(row, 2).toString());
			/*
			 * Lấy dữ liệu từ dòng được chọn cột 3 là cột số điện thoại Đưa lên textField
			 * txtSoDienThoai
			 */
			txtSoDienThoai.setText(modelKhachHang.getValueAt(row, 3).toString());
			/*
			 * Lấy dữ liệu từ dòng được chọn cột 4 là cột CCCD Đưa lên textField txtCCCD
			 */
			txtCCCD.setText(modelKhachHang.getValueAt(row, 4).toString());

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

	public void refeshTextField() {
		txtMaKhachHang.setText("");
		txtHoTenKhachHang.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		txtSoDienThoai.setText("");
		txtCCCD.setText("");
		txtMaKhachHang.requestFocus();
	}

	public boolean validData() {
		String maKhachHang = txtMaKhachHang.getText().trim();
		String hoTenKhachHang = txtHoTenKhachHang.getText().trim();
//		String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String canCuocCongDan = txtCCCD.getText().trim();

//		if (!(maKhachHang.length() > 0)) {
//			JOptionPane.showMessageDialog(this, "Error: Mã khách hàng không được để trống");
//			return false;
//		} else 
//		if (!maKhachHang.matches("(KH)\\d{5}")) {
//			JOptionPane.showMessageDialog(this,
//					"Error: Mã khách hàng không hợp lệ. Nhập lại mã khách hàng theo VD: KH00001");
//			return false;
//		} else {
		if (maKhachHang.matches("(KH)\\d{5}")) {
			for (KhachHang khachHang : arlKhachHang) {
				if (khachHang.getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
					JOptionPane.showMessageDialog(this, "Error: Mã khách hàng đã tồn tại (Trùng mã khách hàng)");
					return false;
				}
			}
		}

		if (!((hoTenKhachHang).length() > 0)) {
			JOptionPane.showMessageDialog(this, "Error: Tên khách hàng không được để trống");
			return false;
		} else if (!hoTenKhachHang.matches(
				"^([A-ZĐÁÀẢÃẠÉÈẺẼẸÍÌỉĨỊÓÒỎÕỌÚÙỦŨỤĂẮẰẲẴẶÂẤẦẨẪẬĐĨỈỊƠỞỜỞỠỢƯỨỪỬỮỰ][a-zđáàảãạéèẻẽẹêềếểễệíìỉĩịóòỏõọúùủũụăắằẳẵặâấầẩẫậđĩỉịơởờởỡợưứừửữự]*(\\s|$))+")) {
			/***
			 * Chữ cái đầu mỗi chữ phái viết hoa
			 * Tên không có chữ số
			 * Tên không được có ký tự đặc biệt
			 */
			
			JOptionPane.showMessageDialog(this,
					"Error: Tên khách hàng không hợp lệ. \n Yêu cầu họ tên: \n Chữ cái đầu mỗi chữ phái viết hoa \n Tên không được có ký tự đặc biệt \n Tên không có chữ số");
			return false;
		}

		if (!(soDienThoai.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Error: Số điện thoại không được để rỗng");
			return false;
		} else if (!soDienThoai.matches("(09|08|03|07|05|02)\\d{8}")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không hợp lệ (Số điện thoại bắt đầu bằng (09, 08, 03, 07, 05, 02)) và số điện thoại có 10 số");
			return false;
		}
		if (!(canCuocCongDan.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Error: Căn cước không được để rỗng");
			return false;
		} else if (!canCuocCongDan.matches("(0)\\d{11}")) {
			JOptionPane.showMessageDialog(this,
					"Căn cước không hợp lệ. Căn cước bắt đầu bằng số 0 (Căn cước có đúng 12 số)");
			return false;
		} else {
			for (KhachHang khachHang : arlKhachHang) {
				if (khachHang.getCanCuocCongDan().equalsIgnoreCase(canCuocCongDan)) {
					JOptionPane.showMessageDialog(this, "Error: Căn cước đã tồn tại (Trùng căn cước công dân)");
					return false;
				}
			}
		}
		return true;
	}

	public boolean validDataUpdate() {
		String maKhachHang = txtMaKhachHang.getText().trim();
		String hoTenKhachHang = txtHoTenKhachHang.getText().trim();
//		String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String canCuocCongDan = txtCCCD.getText().trim();
		
		if (!hoTenKhachHang.matches(
				"^([A-ZĐÁÀẢÃẠÉÈẺẼẸÍÌỉĨỊÓÒỎÕỌÚÙỦŨỤĂẮẰẲẴẶÂẤẦẨẪẬĐĨỈỊƠỞỜỞỠỢƯỨỪỬỮỰ][a-zđáàảãạéèẻẽẹêềếểễệíìỉĩịóòỏõọúùủũụăắằẳẵặâấầẩẫậđĩỉịơởờởỡợưứừửữự]*(\\s|$))+")) {
			/***
			 * Chữ cái đầu mỗi chữ phái viết hoa
			 * Tên không có chữ số
			 * Tên không được có ký tự đặc biệt
			 */
			
			JOptionPane.showMessageDialog(this,
					"Error: Tên khách hàng không lệ. \n Yêu cầu họ tên: \n Chữ cái đầu mỗi chữ phái viết hoa \n Tên không được có ký tự đặc biệt \n Tên không có chữ số");
			return false;
		}
		
		if (!soDienThoai.matches("(09|08|03|07|05|02)\\d{8}")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không hợp lệ (Số điện thoại bắt đầu bằng (09, 08, 03, 07, 05, 02)) và số điện thoại có 10 số");
			return false;
		}
		
		if (!canCuocCongDan.matches("(0)\\d{11}")) {
			JOptionPane.showMessageDialog(this,
					"Căn cước không hợp lệ. Căn cước bắt đầu bằng số 0 (Căn cước có đúng 12 số)");
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
		// Bắt sự kiện nhấn phím enter nhấn btnThem
		if (o.equals(txtMaKhachHang) || o.equals(txtHoTenKhachHang) || o.equals(txtSoDienThoai) || o.equals(txtCCCD)) {
			if (key.equals(KeyEvent.VK_ENTER)) {
				btnThem.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	// Hàm phát sinh mã tăng dần
	public String generatesIncrementalCode() throws Exception {

		String maKhachHang = "";
		long n = khachHang_DAO.getAllTableKhachHang().size();
		if (n < 9) {
			do {
				n += 1;
				maKhachHang = "KH0000" + String.valueOf(n);
				arlKhachHang = khachHang_DAO.getAllTableKhachHang();
			} while (arlKhachHang.contains(maKhachHang));
		} else if (n < 99) {
			do {
				n += 1;
				maKhachHang = "KH000" + String.valueOf(n);
				arlKhachHang = khachHang_DAO.getAllTableKhachHang();
			} while (arlKhachHang.contains(maKhachHang));
		} else if (n < 999) {
			do {
				n += 1;
				maKhachHang = "KH00" + String.valueOf(n);
				arlKhachHang = khachHang_DAO.getAllTableKhachHang();
			} while (arlKhachHang.contains(maKhachHang));
		} else if (n < 9999) {
			do {
				n += 1;
				maKhachHang = "KH0" + String.valueOf(n);
				arlKhachHang = khachHang_DAO.getAllTableKhachHang();
			} while (arlKhachHang.contains(maKhachHang));
		} else if (n < 99999) {
			do {
				n += 1;
				maKhachHang = "KH" + String.valueOf(n);
				arlKhachHang = khachHang_DAO.getAllTableKhachHang();
			} while (arlKhachHang.contains(maKhachHang));
		}
		return maKhachHang;
	}
}
