package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ThongKeKhachHang_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblThongKeKhachHang, lblThang, lblNam, lblTongKhachHang;
	private JTextField txtTongKhachHang;
	private JComboBox<Integer> cmbThang, cmbNam;
	private JButton btnThongKe;
	private JScrollPane scrThongKeKhachHang;
	private DefaultTableModel modelThongKeKhachHang;
	private JTable tblThongKeKhachHang;
	/**
	 * Create the panel.
	 */
	public ThongKeKhachHang_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		lblThongKeKhachHang = new JLabel("Thống kê khách hàng");
		lblThongKeKhachHang.setBounds(600, 11, 399, 49);
		lblThongKeKhachHang.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlThongKeKhachHang.add(lblThongKeKhachHang);
		add(lblThongKeKhachHang);
		
		// Thêm label Tháng
		lblThang = new JLabel("Tháng");
		lblThang.setBounds(92, 90, 150, 30);
		lblThang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeKhachHang.add(lblThang);
		add(lblThang);
		
		// Thêm label Năm
		lblNam = new JLabel("Năm");
		lblNam.setBounds(92, 160, 150, 30);
		lblNam.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeKhachHang.add(lblNam);
		add(lblNam);
		
		// Thêm label Tổng khách hàng
		lblTongKhachHang = new JLabel("Tổng khách hàng");
		lblTongKhachHang.setBounds(92, 907, 200, 30);
		lblTongKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeKhachHang.add(lblTongKhachHang);
		add(lblTongKhachHang);
		
		// Thêm combobox tháng
		cmbThang = new JComboBox<>();
		for(int i = 1; i <= 12; i++) {
			cmbThang.addItem(i);
		}
		cmbThang.setBounds(252, 92, 150, 30);
		cmbThang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbThang.setEditable(false);
		cmbThang.setFocusable(false);
		((JLabel)cmbThang.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
//		pnlThongKeKhachHang.add(cmbThang);
		add(cmbThang);
		
		// Thêm combobox năm
		cmbNam = new JComboBox<>();
		for(int i = 2000; i <= LocalDateTime.now().getYear(); i++) {
			cmbNam.addItem(i);
		}
		cmbNam.setBounds(252, 162, 150, 30);
		cmbNam.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbNam.setEditable(false);
		cmbNam.setFocusable(false);
		((JLabel)cmbNam.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
//		pnlThongKeKhachHang.add(cmbNam);
		add(cmbNam);
		
		// Thêm button Thống kê
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBounds(1215, 900, 200, 45);
		btnThongKe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnThongKe.setBackground(new Color(120, 255, 239));
		btnThongKe.setFocusable(false);
//		pnlThongKeKhachHang.add(btnThongKe);
		add(btnThongKe);
		
		txtTongKhachHang = new JTextField("0");
		txtTongKhachHang.setBounds(292, 907, 300, 30);
		txtTongKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongKhachHang.setBackground(new Color(120, 255, 239));
		txtTongKhachHang.setEditable(false);
		txtTongKhachHang.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeKhachHang.add(txtTongKhachHang);
		add(txtTongKhachHang);
		
		String[] header = {
				"STT", "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "CCCD", "Ngày lập", "Số giờ đến quán",
				"Tổng tiền hóa đơn"
		};
		modelThongKeKhachHang = new DefaultTableModel(header, 0);
		
		add(scrThongKeKhachHang = new JScrollPane(tblThongKeKhachHang = new JTable(modelThongKeKhachHang), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrThongKeKhachHang.setBounds(92, 219, 1399, 660);
		scrThongKeKhachHang.setBackground(new Color(120, 255, 239));
		scrThongKeKhachHang.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrThongKeKhachHang.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblThongKeKhachHang.getTableHeader().setBackground(new Color(120, 255, 239));
		tblThongKeKhachHang.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}

}
