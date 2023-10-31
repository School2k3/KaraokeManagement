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

public class ThongKeDichVu_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblThongKeDichVu, lblThang, lblNam, lblTongDichVu, lblTongDoanhThuDichVu, lblVDN;
	private JTextField txtTongDichVu, txtTongDoanhThuDichVu;
	private JComboBox<Integer> cmbThang, cmbNam;
	private JButton btnThongKe;
	private JScrollPane scrThongKeDichVu;
	private DefaultTableModel modelThongKeDichVu;
	private JTable tblThongKeDichVu;
	/**
	 * Create the panel.
	 */
	public ThongKeDichVu_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		lblThongKeDichVu = new JLabel("Thống kê dịch vụ");
		lblThongKeDichVu.setBounds(640, 11, 319, 49);
		lblThongKeDichVu.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlThongKeDichVu.add(lblThongKeDichVu);
		add(lblThongKeDichVu);

		// Thêm label Tháng
		lblThang = new JLabel("Tháng");
		lblThang.setBounds(63, 99, 150, 30);
		lblThang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDichVu.add(lblThang);
		add(lblThang);

		// Thêm label Năm
		lblNam = new JLabel("Năm");
		lblNam.setBounds(63, 169, 150, 30);
		lblNam.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDichVu.add(lblNam);
		add(lblNam);

		// Thêm label Tổng tiền bán được
		lblTongDoanhThuDichVu = new JLabel("Tổng doanh thu dịch vụ");
		lblTongDoanhThuDichVu.setBounds(63, 897, 250, 30);
		lblTongDoanhThuDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDichVu.add(lblTongDoanhThuDichVu);
		add(lblTongDoanhThuDichVu);

		// Thêm label VND
		lblVDN = new JLabel("VNĐ");
		lblVDN.setBounds(633, 897, 200, 30);
		lblVDN.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDichVu.add(lblVDN);
		add(lblVDN);

		// Thêm label tổng hóa đơn
		lblTongDichVu = new JLabel("Tổng dịch vụ");
		lblTongDichVu.setBounds(1000, 241, 124, 30);
		lblTongDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDichVu.add(lblTongDichVu);
		add(lblTongDichVu);

		// Thêm combobox tháng
		cmbThang = new JComboBox<>();
		for (int i = 1; i <= 12; i++) {
			cmbThang.addItem(i);
		}
		cmbThang.setBounds(223, 101, 150, 30);
		cmbThang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbThang.setEditable(false);
		cmbThang.setFocusable(false);
		((JLabel) cmbThang.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeDichVu.add(cmbThang);
		add(cmbThang);

		// Thêm combobox năm
		cmbNam = new JComboBox<>();
		for (int i = 2000; i <= LocalDateTime.now().getYear(); i++) {
			cmbNam.addItem(i);
		}
		cmbNam.setBounds(223, 171, 150, 30);
		cmbNam.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbNam.setEditable(false);
		cmbNam.setFocusable(false);
		((JLabel) cmbNam.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeDichVu.add(cmbNam);
		add(cmbNam);

		// Thêm button Thống kê
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBounds(1213, 890, 200, 45);
		btnThongKe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnThongKe.setBackground(new Color(120, 255, 239));
		btnThongKe.setFocusable(false);
//		pnlThongKeDichVu.add(btnThongKe);
		add(btnThongKe);

		// Thêm textField
		// Tổng tiền bán được
		txtTongDoanhThuDichVu = new JTextField("0.00");
		txtTongDoanhThuDichVu.setBounds(313, 897, 300, 30);
		txtTongDoanhThuDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongDoanhThuDichVu.setBackground(new Color(120, 255, 239));
		txtTongDoanhThuDichVu.setEditable(false);
		txtTongDoanhThuDichVu.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeDichVu.add(txtTongDoanhThuDichVu);
		add(txtTongDoanhThuDichVu);

		// Tổng hóa đơn
		txtTongDichVu = new JTextField("0");
		txtTongDichVu.setBounds(1150, 241, 320, 30);
		txtTongDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongDichVu.setBackground(new Color(120, 255, 239));
		txtTongDichVu.setEditable(false);
		txtTongDichVu.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeDichVu.add(txtTongDichVu);
		add(txtTongDichVu);

		String[] header = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Số lượng", "Đơn giá", "Ngày lập",
				"Thành tiền" };
		modelThongKeDichVu = new DefaultTableModel(header, 0);

		add(scrThongKeDichVu = new JScrollPane(tblThongKeDichVu = new JTable(modelThongKeDichVu),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrThongKeDichVu.setBounds(63, 300, 1461, 560);
		scrThongKeDichVu.setBackground(new Color(120, 255, 239));
		scrThongKeDichVu.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrThongKeDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblThongKeDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblThongKeDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}

}
