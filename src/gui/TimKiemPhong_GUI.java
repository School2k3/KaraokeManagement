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

public class TimKiemPhong_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhong, txtTenPhong, txtGiaPhong;
	private JTable tblTimKiemPhong;
	private JButton btnTim, btnXoaTrang;
	/**
	 * Create the panel.
	 */
	public TimKiemPhong_GUI() {
		setSize(1600,1050);
		setLayout(null);
		JLabel lblTmKimPhng = new JLabel("Tìm kiếm phòng");
		lblTmKimPhng.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTmKimPhng.setBounds(603, 11, 393, 49);
		add(lblTmKimPhng);
		
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaPhong.setBounds(361, 132, 110, 48);
		add(lblMaPhong);
		
		JLabel lblGia = new JLabel("Đơn giá:");
		lblGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGia.setBounds(833, 132, 88, 48);
		add(lblGia);
		
		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenPhong.setBounds(361, 213, 110, 48);
		add(lblTenPhong);
		
		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoaiPhong.setBounds(833, 213, 118, 48);
		add(lblLoaiPhong);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaPhong.setLabelFor(txtMaPhong);
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(483, 140, 251, 32);
		add(txtMaPhong);
		
		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenPhong.setLabelFor(txtTenPhong);
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(483, 219, 251, 36);
		add(txtTenPhong);
		
		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGia.setLabelFor(txtGiaPhong);
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(950, 140, 227, 32);
		add(txtGiaPhong);
		
		JComboBox cmbLoaiPhong = new JComboBox();
		lblLoaiPhong.setLabelFor(cmbLoaiPhong);
		cmbLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(950, 221, 227, 32);
		add(cmbLoaiPhong);
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTim.setBounds(524, 345, 137, 48);
		btnTim.setFocusable(false);
		add(btnTim);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(867, 345, 175, 48);
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);
		
		JScrollPane scrTimKiemPhong = new JScrollPane();
		scrTimKiemPhong.setBounds(189, 423, 1290, 585);
		add(scrTimKiemPhong);
		
		tblTimKiemPhong = new JTable();
		tblTimKiemPhong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 ph\u00F2ng", "T\u00EAn ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "\u0110\u01A1n gi\u00E1", "Tr\u1EA1ng th\u00E1i"
			}
		));
		tblTimKiemPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblTimKiemPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		tblTimKiemPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		scrTimKiemPhong.setViewportView(tblTimKiemPhong);
	}

}
