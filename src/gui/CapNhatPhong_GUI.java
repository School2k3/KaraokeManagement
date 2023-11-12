package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CapNhatPhong_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhong, txtGia, txtTenPhong;
	private JTable tblPhong;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	/**
	 * Create the panel.
	 */
	public CapNhatPhong_GUI() {
		// Thiết lập size cho giao diện
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Cập nhật phòng");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitle.setBounds(603, 11, 393, 49);
		add(lblTitle);
		
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaPhong.setBounds(89, 118, 131, 48);
		add(lblMaPhong);
		
		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoaiPhong.setBounds(89, 192, 131, 48);
		add(lblLoaiPhong);
		
		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenPhong.setBounds(907, 118, 109, 48);
		add(lblTenPhong);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTrangThai.setBounds(907, 192, 109, 48);
		add(lblTrangThai);
		
		JLabel lblGia = new JLabel("Đơn giá:");
		lblGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGia.setBounds(89, 268, 131, 48);
		add(lblGia);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(247, 363, 117, 48);
		btnThem.setBackground(new Color(217, 217, 217));
		btnThem.setFocusable(false);
		add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(578, 363, 109, 48);
		btnSua.setBackground(new Color(217, 217, 217));
		btnSua.setFocusable(false);
		add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setBounds(884, 363, 109, 48);
		btnXoa.setBackground(new Color(217, 217, 217));
		btnXoa.setFocusable(false);
		add(btnXoa);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(1163, 363, 161, 48);
		btnXoaTrang.setBackground(new Color(217, 217, 217));
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaPhong.setLabelFor(txtMaPhong);
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(230, 126, 440, 32);
		add(txtMaPhong);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtGia.setColumns(10);
		txtGia.setBounds(230, 276, 440, 32);
		add(txtGia);
		
		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenPhong.setLabelFor(txtTenPhong);
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(1026, 126, 405, 32);
		add(txtTenPhong);
		
		JComboBox cmbLoaiPhong = new JComboBox();
		lblLoaiPhong.setLabelFor(cmbLoaiPhong);
		cmbLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(230, 200, 270, 32);
		add(cmbLoaiPhong);
		
		JComboBox cmbTrangThai = new JComboBox();
		lblTrangThai.setLabelFor(cmbTrangThai);
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setBounds(1026, 200, 270, 32);
		add(cmbTrangThai);
		
		JScrollPane scrPhong = new JScrollPane();
		scrPhong.setBounds(89, 459, 1439, 520);
		add(scrPhong);
		
		tblPhong = new JTable();
		tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrPhong.setViewportView(tblPhong);
		tblPhong.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã phòng", "Tên phòng", "Loại phòng", "Trạng thái", "Đơn giá"
				}
			));
		tblPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	}

}
