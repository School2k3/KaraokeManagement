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
	private JTextField txtMa;
	private JTextField txtGia;
	private JTextField txtTen;
	private JTable tblPhong;
	/**
	 * Create the panel.
	 */
	public CapNhatPhong_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Cập nhật phòng");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitle.setBounds(703, 11, 396, 59);
		add(lblTitle);
		
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaPhong.setBounds(89, 118, 131, 48);
		add(lblMaPhong);
		
		JLabel lblLoai = new JLabel("Loại phòng:");
		lblLoai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoai.setBounds(89, 192, 131, 48);
		add(lblLoai);
		
		JLabel lblTen = new JLabel("Tên phòng:");
		lblTen.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTen.setBounds(907, 118, 109, 48);
		add(lblTen);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTrangThai.setBounds(907, 192, 109, 48);
		add(lblTrangThai);
		
		JLabel lblGia = new JLabel("�?ơn giá:");
		lblGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGia.setBounds(89, 268, 131, 48);
		add(lblGia);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(183, 363, 203, 66);
		btnThem.setBackground(new Color(217, 217, 217));
		btnThem.setFocusable(false);
		add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(527, 363, 203, 66);
		add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setBounds(860, 363, 203, 66);
		add(btnXoa);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(1191, 363, 203, 66);
		add(btnXoaTrang);
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaPhong.setLabelFor(txtMa);
		txtMa.setColumns(10);
		txtMa.setBounds(230, 126, 440, 32);
		add(txtMa);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtGia.setColumns(10);
		txtGia.setBounds(230, 276, 440, 32);
		add(txtGia);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTen.setLabelFor(txtTen);
		txtTen.setColumns(10);
		txtTen.setBounds(1026, 126, 405, 32);
		add(txtTen);
		
		JComboBox cmbLoai = new JComboBox();
		lblLoai.setLabelFor(cmbLoai);
		cmbLoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoai.setBounds(230, 200, 270, 32);
		add(cmbLoai);
		
		JComboBox cmbTrangThai = new JComboBox();
		lblTrangThai.setLabelFor(cmbTrangThai);
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setBounds(1026, 200, 270, 32);
		add(cmbTrangThai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 459, 1439, 520);
		add(scrollPane);
		
		tblPhong = new JTable();
		tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrollPane.setViewportView(tblPhong);
		tblPhong.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã phòng", "Tên phòng", "Loại phòng", "Trạng thái", "�?ơn giá"
				}
			));
	}

}
