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

public class ThongKeSoLuongHoaDon_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblThongKeSoLuongHoaDon, lblThang, lblNam, lblTongHoaDon, lblTongTienBanDuoc, lblVDN;
	private JTextField txtTongHoaDon, txtTongTienBanDuoc;
	private JComboBox<Integer> cmbThang, cmbNam;
	private JButton btnThongKe;
	private JScrollPane scrThongKeHoaDon;
	private DefaultTableModel modelThongKeHoaDon;
	private JTable tblThongKeHoaDon;
	/**
	 * Create the panel.
	 */
	public ThongKeSoLuongHoaDon_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		lblThongKeSoLuongHoaDon = new JLabel("Thống kê số lượng hóa đơn");
		lblThongKeSoLuongHoaDon.setBounds(500, 11, 522, 49);
		lblThongKeSoLuongHoaDon.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlThongKeHoaDon.add(lblThongKeSoLuongHoaDon);
		add(lblThongKeSoLuongHoaDon);

		// Thêm label Tháng
		lblThang = new JLabel("Tháng");
		lblThang.setBounds(80, 101, 150, 30);
		lblThang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeHoaDon.add(lblThang);
		add(lblThang);

		// Thêm label Năm
		lblNam = new JLabel("Năm");
		lblNam.setBounds(80, 171, 150, 30);
		lblNam.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeHoaDon.add(lblNam);
		add(lblNam);

		// Thêm label Tổng tiền bán được
		lblTongTienBanDuoc = new JLabel("Tổng tiền bán được");
		lblTongTienBanDuoc.setBounds(80, 930, 200, 30);
		lblTongTienBanDuoc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeHoaDon.add(lblTongTienBanDuoc);
		add(lblTongTienBanDuoc);
		
		// Thêm label VND
		lblVDN = new JLabel("VNĐ");
		lblVDN.setBounds(600, 930, 200, 30);
		lblVDN.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeHoaDon.add(lblVDN);
		add(lblVDN);
		
		// Thêm label tổng hóa đơn
		lblTongHoaDon = new JLabel("Tổng hóa đơn");
		lblTongHoaDon.setBounds(835, 240, 200, 30);
		lblTongHoaDon.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeHoaDon.add(lblTongHoaDon);
		add(lblTongHoaDon);

		// Thêm combobox tháng
		cmbThang = new JComboBox<>();
		for (int i = 1; i <= 12; i++) {
			cmbThang.addItem(i);
		}
		cmbThang.setBounds(240, 103, 150, 30);
		cmbThang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbThang.setEditable(false);
		cmbThang.setFocusable(false);
		((JLabel) cmbThang.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeHoaDon.add(cmbThang);
		add(cmbThang);

		// Thêm combobox năm
		cmbNam = new JComboBox<>();
		for (int i = 2000; i <= LocalDateTime.now().getYear(); i++) {
			cmbNam.addItem(i);
		}
		cmbNam.setBounds(240, 173, 150, 30);
		cmbNam.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbNam.setEditable(false);
		cmbNam.setFocusable(false);
		((JLabel) cmbNam.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeHoaDon.add(cmbNam);
		add(cmbNam);

		// Thêm button Thống kê
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBounds(1239, 923, 200, 45);
		btnThongKe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnThongKe.setBackground(new Color(120, 255, 239));
		btnThongKe.setFocusable(false);
//		pnlThongKeHoaDon.add(btnThongKe);
		add(btnThongKe);

		
		// Thêm textField 
		// Tổng tiền bán được
		txtTongTienBanDuoc = new JTextField("0.00");
		txtTongTienBanDuoc.setBounds(280, 930, 300, 30);
		txtTongTienBanDuoc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongTienBanDuoc.setBackground(new Color(120, 255, 239));
		txtTongTienBanDuoc.setEditable(false);
		txtTongTienBanDuoc.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeHoaDon.add(txtTongTienBanDuoc);
		add(txtTongTienBanDuoc);
		
		// Tổng hóa đơn
		txtTongHoaDon = new JTextField("0");
		txtTongHoaDon.setBounds(985, 240, 350, 30);
		txtTongHoaDon.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongHoaDon.setBackground(new Color(120, 255, 239));
		txtTongHoaDon.setEditable(false);
		txtTongHoaDon.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeHoaDon.add(txtTongHoaDon);
		add(txtTongHoaDon);

		String[] header = { "STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Số lượng đơn đã lập", "Tổng tiền hóa đơn"};
		modelThongKeHoaDon = new DefaultTableModel(header, 0);

		add(scrThongKeHoaDon = new JScrollPane(tblThongKeHoaDon = new JTable(modelThongKeHoaDon),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrThongKeHoaDon.setBounds(80, 299, 1420, 590);
		scrThongKeHoaDon.setBackground(new Color(120, 255, 239));
		scrThongKeHoaDon.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrThongKeHoaDon.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblThongKeHoaDon.getTableHeader().setBackground(new Color(120, 255, 239));
		tblThongKeHoaDon.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}

}
