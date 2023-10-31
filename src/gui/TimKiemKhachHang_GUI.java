package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TimKiemKhachHang_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTimKiemKhachHang, lblMaKhachHang, lblHoTenKhachHang, lblSoDienThoai, lblCCCD, lblGioiTinh;
	private JTextField txtMaKhachHang, txtHoTenKhachHang, txtSoDienThoai, txtCCCD;
	private JComboBox<String> cmbGioiTinh;
	private JButton btnDangXuat, btnTimKiem, btnDatPhong, btnLapHoaDon, btnXemDanhSachKhachHang;
	private JScrollPane scrKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tblKhachHang;
	/**
	 * Create the panel.
	 */
	public TimKiemKhachHang_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		// Tạo panel tìm kiếm khách hàng
//		JPanel pnlTimKiemKhachHang = new JPanel();
//		pnlTimKiemKhachHang.setLayout(null);
//		pnlTimKiemKhachHang.setBounds(300, 0, 1620, 1080);
//		pnlTimKiemKhachHang.setBackground(Color.WHITE);
		
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
		
		// Button Đặt phòng
		btnDatPhong = new JButton("Đặt phòng");
		btnDatPhong.setBounds(475, 308, 200, 45);
		btnDatPhong.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnDatPhong.setBackground(new Color(217, 217, 217));
		btnDatPhong.setFocusable(false);
//		pnlTimKiemKhachHang.add(btnDatPhong);
		add(btnDatPhong);
		
		// Button Lập hóa đơn
		btnLapHoaDon = new JButton("Lập hóa đơn");
		btnLapHoaDon.setBounds(780, 308, 200, 45);
		btnLapHoaDon.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnLapHoaDon.setBackground(new Color(217, 217, 217));
		btnLapHoaDon.setFocusable(false);
//		pnlTimKiemKhachHang.add(btnLapHoaDon);
		add(btnLapHoaDon);
		
		// Button Xem danh sách khách hàng
		btnXemDanhSachKhachHang = new JButton("Xem danh sách khách hàng");
		btnXemDanhSachKhachHang.setBounds(1084, 308, 350, 45);
		btnXemDanhSachKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		btnXemDanhSachKhachHang.setBackground(new Color(217, 217, 217));
		btnXemDanhSachKhachHang.setFocusable(false);
//		pnlTimKiemKhachHang.add(btnXemDanhSachKhachHang);
		add(btnXemDanhSachKhachHang);
		
		// Tạo Table thông tin khách hàng
		String[] header = {
					"Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Căn cước công dân" 
				};
		modelKhachHang = new DefaultTableModel(header, 0);
				
		add(scrKhachHang = new JScrollPane(tblKhachHang = new JTable(modelKhachHang), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrKhachHang.setBounds(104, 364, 1380, 570);
		scrKhachHang.setBackground(new Color(120, 255, 239));
		scrKhachHang.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrKhachHang.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblKhachHang.getTableHeader().setBackground(new Color(120, 255, 239));
		tblKhachHang.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				
				
		// Set Size Width ColumnTable
//		tblKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(200);
//		tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(400);
//		tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(150);
//		tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(220);
//		tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(210);
	}

}
