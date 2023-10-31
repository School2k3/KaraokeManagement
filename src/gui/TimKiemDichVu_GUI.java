package gui;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class TimKiemDichVu_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDichVu, txtTenDichVu;
	private JTable tblTimKiemDichVu;
	private JButton btnTimKiem, btnXoaTrang;

	public TimKiemDichVu_GUI() {
		// Thiết lập size cho giao diện
			setSize(1600, 1050);
			setLayout(null);
			JLabel lblTitle = new JLabel("Tìm kiếm dịch vụ");
			lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
			lblTitle.setBounds(603, 11, 393, 49);
			add(lblTitle);
				
			JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
			lblMaDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblMaDichVu.setBounds(143, 135, 131, 48);
			add(lblMaDichVu);
				
			JLabel lblDonGia = new JLabel("Đơn giá:");
			lblDonGia.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblDonGia.setBounds(700, 135, 131, 48);
			add(lblDonGia);
			
			JLabel lblTenDichVu = new JLabel("Tên dịch vụ:");
			lblTenDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblTenDichVu.setBounds(143, 227, 131, 48);
			add(lblTenDichVu);
				
			JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ:");
			lblLoaiDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblLoaiDichVu.setBounds(700, 227, 131, 48);
			add(lblLoaiDichVu);
				
			txtMaDichVu = new JTextField();
			txtMaDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblMaDichVu.setLabelFor(txtMaDichVu);
			txtMaDichVu.setBounds(284, 151, 242, 32);
			add(txtMaDichVu);
			txtMaDichVu.setColumns(10);
				
			txtTenDichVu = new JTextField();
			txtTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblTenDichVu.setLabelFor(txtTenDichVu);
			txtTenDichVu.setColumns(10);
			txtTenDichVu.setBounds(284, 235, 242, 32);
			add(txtTenDichVu);
				
			JTextField txtGia = new JTextField();
			txtGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblDonGia.setLabelFor(txtGia);
			txtGia.setColumns(10);
			txtGia.setBounds(813, 143, 242, 32);
			add(txtGia);
				
			JComboBox cmbLoaiDichVu = new JComboBox();
			lblLoaiDichVu.setLabelFor(cmbLoaiDichVu);
//			cmbLoaiDichVu.setModel(new DefaultComboBoxModel(new String[] {}));
			cmbLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
			cmbLoaiDichVu.setBounds(841, 235, 214, 32);
			add(cmbLoaiDichVu);
				
			btnTimKiem = new JButton("Tìm kiếm");
			btnTimKiem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnTimKiem.setBounds(422, 327, 173, 48);
			btnTimKiem.setFocusable(false);
			add(btnTimKiem);
				
			btnXoaTrang = new JButton("Xóa trắng");
			btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnXoaTrang.setBounds(744, 327, 192, 48);
			btnXoaTrang.setFocusable(false);
			add(btnXoaTrang);
				
			JScrollPane scrTimKiemDichVu = new JScrollPane();
			scrTimKiemDichVu.setBounds(143, 407, 1274, 632);
			add(scrTimKiemDichVu);
				
			tblTimKiemDichVu = new JTable();
			tblTimKiemDichVu.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã dịch vụ", "Tên dịch vụ", "Đơn giá", "Loại dịch vụ"
				}
			));
			tblTimKiemDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
			tblTimKiemDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
			tblTimKiemDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			scrTimKiemDichVu.setViewportView(tblTimKiemDichVu);
				
	}
}
