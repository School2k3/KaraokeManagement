package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CapNhatKhachHang_GUI extends JPanel implements ActionListener {
	private JLabel lblCapNhatKhachHang, lblMaKhachHang, lblHoTenKhachHang, lblSoDienThoai, lblCCCD, lblGioiTinh;
	private JTextField txtMaKhachHang, txtHoTenKhachHang, txtSoDienThoai, txtCCCD;
	private JComboBox<String> cmbGioiTinh;
	private JButton btnThem, btnSua, btnLamMoi;
	private JScrollPane scrKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tblKhachHang;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CapNhatKhachHang_GUI() {
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
		lblHoTenKhachHang.setBounds(55, 170, 200, 30);
		lblHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblHoTenKhachHang);
		add(lblHoTenKhachHang);
		
		// Thêm Label Số điện thoại
		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setBounds(882, 100, 150, 30);
		lblSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblSoDienThoai);
		add(lblSoDienThoai);
		
		// Thêm Label Căn cước công dân
		lblCCCD = new JLabel("CCCD");
		lblCCCD.setBounds(882, 170, 150, 30);
		lblCCCD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblCCCD);
		add(lblCCCD);
		
		// Thêm Label Giới tính
		lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(55, 242, 150, 30);
		lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlCapNhatKhachHang.add(lblGioiTinh);
		add(lblGioiTinh);
		
		// Thêm TextField Mã khách hàng
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(265, 100, 300, 30);
		txtMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtMaKhachHang);
		add(txtMaKhachHang);
		
		// Thêm TextField Họ tên khách hàng
		txtHoTenKhachHang = new JTextField();
		txtHoTenKhachHang.setBounds(265, 170, 300, 30);
		txtHoTenKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtHoTenKhachHang);
		add(txtHoTenKhachHang);
		
		// Thêm TextField Số điện thoại
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(1062, 102, 300, 30);
		txtSoDienThoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtSoDienThoai);
		add(txtSoDienThoai);
		
		
		// Thêm TextField Cân cước công dân
		txtCCCD = new JTextField();
		txtCCCD.setBounds(1062, 172, 300, 30);
		txtCCCD.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//		pnlCapNhatKhachHang.add(txtCCCD);
		add(txtCCCD);
		
		
		// Thêm combobox Giới tính
		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setBounds(265, 242, 300, 30);
		cmbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbGioiTinh.setEditable(false);
		cmbGioiTinh.setFocusable(false);
//		pnlCapNhatKhachHang.add(cmbGioiTinh);
		add(cmbGioiTinh);
		
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
		String[] header = {
				"Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Căn cước công dân" 
		};
		modelKhachHang = new DefaultTableModel(header, 0);
		
		add(scrKhachHang = new JScrollPane(tblKhachHang = new JTable(modelKhachHang), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrKhachHang.setBounds(55, 343, 1483, 622);
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
			
		// Add Sự kiện cho các button
//		btnThem.addActionListener(this);
//		btnSua.addActionListener(this);
//		btnLamMoi.addActionListener(this);
//		btnDangXuat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
