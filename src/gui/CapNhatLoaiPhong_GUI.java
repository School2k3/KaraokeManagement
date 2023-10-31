package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CapNhatLoaiPhong_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaLoaiPhong, txtTenLoaiPhong;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	private JTable tblLoaiPhong;
	/**
	 * Create the panel.
	 */
	public CapNhatLoaiPhong_GUI() {
		setSize(1600,1050);
		setLayout(null);
		JLabel lblCapNhatLoaiPhong = new JLabel("Cập nhật loại phòng");
		lblCapNhatLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblCapNhatLoaiPhong.setBounds(603, 11, 393, 49);
		add(lblCapNhatLoaiPhong);
		
		JLabel lblMaLoaiPhong = new JLabel("Mã loại phòng:");
		lblMaLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaLoaiPhong.setBounds(344, 159, 170, 62);
		add(lblMaLoaiPhong);
		
		JLabel lblTenLoaiPhong = new JLabel("Tên loại phòng:");
		lblTenLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenLoaiPhong.setBounds(880, 159, 156, 62);
		add(lblTenLoaiPhong);
		
		txtMaLoaiPhong = new JTextField();
		txtMaLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtMaLoaiPhong.setColumns(10);
		txtMaLoaiPhong.setBounds(496, 174, 273, 32);
		add(txtMaLoaiPhong);
		
		txtTenLoaiPhong = new JTextField();
		txtTenLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenLoaiPhong.setColumns(10);
		txtTenLoaiPhong.setBounds(1033, 174, 233, 32);
		add(txtTenLoaiPhong);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(333, 290, 133, 49);
		btnThem.setFocusable(false);
		add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(626, 292, 133, 44);
		btnSua.setFocusable(false);
		add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setBounds(894, 292, 121, 44);
		btnXoa.setFocusable(false);
		add(btnXoa);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(1153, 292, 170, 44);
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);
		
		JScrollPane scrLoaiPhong = new JScrollPane();
		scrLoaiPhong.setBounds(222, 359, 1264, 641);
		add(scrLoaiPhong);
		
		tblLoaiPhong = new JTable();
		tblLoaiPhong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 lo\u1EA1i ph\u00F2ng", "T\u00EAn lo\u1EA1i ph\u00F2ng"
			}
		));
		tblLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblLoaiPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblLoaiPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		scrLoaiPhong.setViewportView(tblLoaiPhong);
	}

}
