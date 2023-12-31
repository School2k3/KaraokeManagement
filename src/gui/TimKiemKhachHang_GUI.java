package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class TimKiemKhachHang_GUI extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTimKiemKhachHang, lblMaKhachHang, lblHoTenKhachHang, lblSoDienThoai, lblCCCD, lblGioiTinh;
	private JTextField txtMaKhachHang, txtHoTenKhachHang, txtSoDienThoai, txtCCCD;
	private JComboBox<String> cmbGioiTinh;
	private JButton btnTimKiem, btnXemDanhSachKhachHang;
	private JScrollPane scrKhachHang;
	private DefaultTableModel modelKhachHang;
	public JTable tblKhachHang;
	private ArrayList<KhachHang> arlKhachHang;
	private KhachHang_DAO khachHang_DAO;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public TimKiemKhachHang_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// Khởi tạo biến khachHang_DAO
		khachHang_DAO = new KhachHang_DAO();

		setSize(1600, 1055);
		setLayout(null);
		// Tạo panel tìm kiếm khách hàng

		lblTimKiemKhachHang = new JLabel("Tìm kiếm khách hàng");
		lblTimKiemKhachHang.setBounds(600, 11, 399, 49);
		lblTimKiemKhachHang.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlTimKiemKhachHang.add(lblTimKiemKhachHang);
		add(lblTimKiemKhachHang);

		// Thêm Label Mã khách hàng
		lblMaKhachHang = new JLabel("Mã khách hàng");
		lblMaKhachHang.setBounds(104, 100, 150, 30);
		lblMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemKhachHang.add(lblMaKhachHang);
		add(lblMaKhachHang);

		// Thêm Label Họ tên khách hàng
		lblHoTenKhachHang = new JLabel("Họ tên khách hàng");
		lblHoTenKhachHang.setBounds(104, 170, 200, 30);
		lblHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemKhachHang.add(lblHoTenKhachHang);
		add(lblHoTenKhachHang);

		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(943, 100, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemKhachHang.add(lblSoDienThoai);
		add(lblSoDienThoai);

		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("CCCD");
		lblCCCD.setBounds(943, 170, 150, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemKhachHang.add(lblCCCD);
		add(lblCCCD);

		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(104, 240, 150, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlTimKiemKhachHang.add(lblGioiTinh);
		add(lblGioiTinh);

		// Thêm TextField Mã khách hàng
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(314, 102, 361, 30);
		txtMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemKhachHang.add(txtMaKhachHang);
		add(txtMaKhachHang);

		// Thêm TextField Họ tên khách hàng
		txtHoTenKhachHang = new JTextField();
		txtHoTenKhachHang.setBounds(314, 172, 361, 30);
		txtHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemKhachHang.add(txtHoTenKhachHang);
		add(txtHoTenKhachHang);

		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1123, 102, 362, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemKhachHang.add(txtSoDienThoai);
		add(txtSoDienThoai);

		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(1123, 172, 362, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlTimKiemKhachHang.add(txtCCCD);
		add(txtCCCD);

		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setBounds(314, 242, 200, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlTimKiemKhachHang.add(cmbGioiTinh);
		add(cmbGioiTinh);

		// Add item cho Combobox
		cmbGioiTinh.addItem("");
		cmbGioiTinh.addItem("Nam");
		cmbGioiTinh.addItem("Nữ");

		// Thêm các button
		// Button tìm kiếm

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(170, 308, 200, 45);
		btnTimKiem.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
//		pnlTimKiemKhachHang.add(btnTimKiem);
		add(btnTimKiem);

		// Button Xem danh sách khách hàng
		btnXemDanhSachKhachHang = new JButton("Xem danh sách khách hàng");
		btnXemDanhSachKhachHang.setBounds(1084, 308, 350, 45);
		btnXemDanhSachKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnXemDanhSachKhachHang.setBackground(new Color(217, 217, 217));
		btnXemDanhSachKhachHang.setFocusable(false);
//		pnlTimKiemKhachHang.add(btnXemDanhSachKhachHang);
		add(btnXemDanhSachKhachHang);

		// Tạo Table thông tin khách hàng
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Căn cước công dân" };
		modelKhachHang = new DefaultTableModel(header, 0);

		add(scrKhachHang = new JScrollPane(tblKhachHang = new JTable(modelKhachHang),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrKhachHang.setBounds(55, 380, 1483, 622);
		scrKhachHang.setBackground(new Color(120, 255, 239));
		scrKhachHang.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrKhachHang.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblKhachHang.getTableHeader().setBackground(new Color(120, 255, 239));
		tblKhachHang.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		tblKhachHang.setRowHeight(50);

		// Set Size Width ColumnTable
//		tblKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(200);
//		tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(400);
//		tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(150);
//		tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(220);
//		tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(210);
		// Load dữ liệu từ database vào table
		loadDanhSachKhachHang();
		docDuLieuVaoTable();

		// Thêm sự kiện cho các button
		btnTimKiem.addActionListener(this);
		btnXemDanhSachKhachHang.addActionListener(this);
		cmbGioiTinh.addActionListener(this);
		// Thêm sự kiện nhấn nút enter
		txtMaKhachHang.addKeyListener(this);
		txtHoTenKhachHang.addKeyListener(this);
		cmbGioiTinh.addKeyListener(this);
		txtSoDienThoai.addKeyListener(this);
		txtCCCD.addKeyListener(this);
		btnTimKiem.addKeyListener(this);
		// Thêm sự kiện mouseListenner
		tblKhachHang.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			/**
			 * Khởi tạo biến mã khách hàng để lưu thông tin nhập vào trên textField Mã khách
			 * hàng Lấy các dòng dữ liệu sau đó xóa tất cả các yếu tố có trong table Sau đó
			 * tải lại dữ liệu hiện có trên database lên lại table
			 * 
			 * Truy cập vào DAO để lấy phương thức lấy danh sách khách hàng theo mã Nếu danh
			 * sách khách hàng rỗng hoặc có độ lớn <= 0 (có nghĩa là không có dữ liệu) ->
			 * Thông báo "Không tìm thấy khách hàng nào" Ngược lại -> Đọc dữ liệu vào table
			 */

			String maKhachHang = txtMaKhachHang.getText().trim();
			String hoTenKhachHang = txtHoTenKhachHang.getText().trim();
			String gioiTinh = ((JTextField) cmbGioiTinh.getEditor().getEditorComponent()).getText();
			String soDienThoai = txtSoDienThoai.getText().trim();
			String canCuocCongDan = txtCCCD.getText().trim();
			modelKhachHang.getDataVector().removeAllElements();
			/***
			 * Tìm kiếm tất cả
			 */
			if (!maKhachHang.equalsIgnoreCase("") && !hoTenKhachHang.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty()) && !soDienThoai.equalsIgnoreCase("")
					&& !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameSoDienThoaiCCCDAndGioiTinh(maKhachHang,
							hoTenKhachHang, soDienThoai, canCuocCongDan, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm 4 textField Tìm kiếm theo mã, họ tên, giới tính, số điện thoại
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !hoTenKhachHang.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty()) && !soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameSoDienThoaiAndGioiTinh(maKhachHang,
							hoTenKhachHang, soDienThoai, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm kiếm theo mã, họ tên, giới tính, căn cước
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !hoTenKhachHang.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty()) && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameCCCDAndGioiTinh(maKhachHang, hoTenKhachHang,
							canCuocCongDan, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm kiếm theo mã, họ tên, căn cước, số điện thoại
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !hoTenKhachHang.equalsIgnoreCase("")
					&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameSoDienThoaiAndCCCD(maKhachHang, hoTenKhachHang,
							soDienThoai, canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm kiếm theo họ tên, căn cước, số điện thoại, giới tính
			 */
			else if ((gioiTinh != null || gioiTinh.isEmpty()) && !hoTenKhachHang.equalsIgnoreCase("")
					&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameCCCDSoDienThoaiAndGioiTinh(hoTenKhachHang,
							canCuocCongDan, gioiTinh, soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm kiếm theo mã, căn cước, số điện thoại, giới tính
			 */
			else if ((gioiTinh != null || gioiTinh.isEmpty()) && !maKhachHang.equalsIgnoreCase("")
					&& !soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaSoDienThoaiCCCDAndGioiTinh(maKhachHang,
							canCuocCongDan, gioiTinh, soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm 3 textField Tìm theo mã, họ tên , số điện thoại
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
					&& !hoTenKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameAndSoDienThoai(maKhachHang, hoTenKhachHang,
							soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo mã, họ tên, căn cước
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
					&& !hoTenKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameAndCCCD(maKhachHang, hoTenKhachHang,
							canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo mã, họ tên, giới tính
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
					&& !hoTenKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaNameAndGioiTinh(maKhachHang, hoTenKhachHang,
							gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo mã, số điện thoại, giới tính
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
					&& !soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaSoDienThoaiVaGioiTinh(maKhachHang, soDienThoai,
							gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo mã, căn cước, giới tính
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())
					&& !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaCCCDVaGioiTinh(maKhachHang, canCuocCongDan,
							gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo mã, số điện thoại, căn cước
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
					&& !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaSoDienThoaiAndCCCD(maKhachHang, soDienThoai,
							canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo họ tên, số điện thoại, căn cước
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
					&& !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameSoDienThoaiAndCCCD(hoTenKhachHang, soDienThoai,
							canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo họ tên, số điện thoại, giới tính
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty())) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameSoDienThoaiAndGioiTinh(hoTenKhachHang,
							soDienThoai, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo họ tên, căn cước, giới tính
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty())) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameCCCDAndGioiTinh(hoTenKhachHang, canCuocCongDan,
							gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo căn cước, số điện thoại, giới tính
			 */
			else if (!soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")
					&& (gioiTinh != null || gioiTinh.isEmpty())) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByCCCDGioiTinhAndSoDienThoai(canCuocCongDan,
							soDienThoai, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm 2 textField Tìm theo mã, họ tên
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !hoTenKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaAndName(maKhachHang, hoTenKhachHang);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo mã, giới tính
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaVaGioiTinh(maKhachHang, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo mã, số điện thoại
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaVaSoDienThoai(maKhachHang, soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo mã, căn cước
			 */
			else if (!maKhachHang.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaVaCCCD(maKhachHang, canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo họ tên, giới tính
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && (gioiTinh != null || gioiTinh.isEmpty())) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameVaGioiTinh(hoTenKhachHang, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo họ tên, số điện thoại
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && !soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameVaSoDienThoai(hoTenKhachHang, soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo họ tên, căn cước
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByNameVaCCCD(hoTenKhachHang, canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo giới tính, số điện thoại
			 */
			else if ((gioiTinh != null || gioiTinh.isEmpty()) && !soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByGioiTinhVaSoDienThoai(gioiTinh, soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo giới tính, căn cước
			 */
			else if ((gioiTinh != null || gioiTinh.isEmpty()) && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByGioiTinhVaCCCD(canCuocCongDan, gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo số điện thoại, căn cước
			 */
			else if (!soDienThoai.equalsIgnoreCase("") && !canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangBySoDienThoaiVaCCCD(soDienThoai, canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm 1 textField Tìm theo mã
			 */
			else if (!maKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByMaKhachHang(maKhachHang);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			/***
			 * Tìm theo họ tên
			 */
			else if (!hoTenKhachHang.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByHoTenKhachHang(hoTenKhachHang);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo giới tính
			 */
			else if (gioiTinh != null || gioiTinh.isEmpty()) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByGioiTinh(gioiTinh);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo số điện thoại
			 */
			else if (!soDienThoai.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangBySoDienThoai(soDienThoai);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			/***
			 * Tìm theo căn cước
			 */
			else if (!canCuocCongDan.equalsIgnoreCase("")) {
				try {
					arlKhachHang = khachHang_DAO.getListKhachHangByCCCD(canCuocCongDan);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (arlKhachHang == null || arlKhachHang.size() <= 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
			} else {
				try {
					docDuLieuVaoTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXemDanhSachKhachHang)) {
			/**
			 * Lấy các dòng dữ liệu sau đó xóa tất cả các yếu tố có trong table Sau đó tải
			 * lại dữ liệu hiện có trên database lên lại table Nếu danh sách khách hàng rỗng
			 * -> thông báo "Không tìm thấy khách hàng nào" Ngược lại -> Đọc dữ liệu vào
			 * table
			 */
			modelKhachHang.getDataVector().removeAllElements();
			try {
				loadDanhSachKhachHang();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (arlKhachHang == null || arlKhachHang.size() <= 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào");
				refesh();
			} else
				try {
					docDuLieuVaoTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			refesh();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		Object key = e.getKeyCode();
		// Bắt sự kiện nhấn phím enter nhấn btnTimKiem
		if (o.equals(txtMaKhachHang) || o.equals(txtHoTenKhachHang) || o.equals(txtSoDienThoai) || o.equals(txtCCCD)) {
			if (key.equals(KeyEvent.VK_ENTER)) {
				btnTimKiem.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tblKhachHang)) {
			/*
			 * Tạo biến row có kiểu dữ liệu int Để table Khách hàng lấy dòng được chọn
			 */
			int row = tblKhachHang.rowAtPoint(e.getPoint());
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
		modelKhachHang.getDataVector().removeAllElements();
		if (arlKhachHang == null || arlKhachHang.size() <= 0)
			return;
		for (KhachHang khachHang : arlKhachHang) {
			/**
			 * 
			 */
			modelKhachHang.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTenKhachHang(),
					khachHang.isGioiTinh(), khachHang.getSoDienThoai(), khachHang.getCanCuocCongDan() });
		}
	}

	public void refesh() {
		txtMaKhachHang.setText("");
		txtHoTenKhachHang.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		txtSoDienThoai.setText("");
		txtCCCD.setText("");
		txtMaKhachHang.requestFocus();
	}
}
