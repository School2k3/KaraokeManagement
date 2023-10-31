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

public class ThongKeDoanhThu_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblThongKeDoanhThu, lblThang, lblNam, lblTongDoanhThu, lblTongTienDichVu, lblTongTienPhong, lblVND,
					lblVNDTongTienDichVu, lblVNDTongTienPhong;
	private JTextField txtTongDoanhThu, txtTongTienDichVu, txtTongTienPhong;
	private JComboBox<Integer> cmbThang, cmbNam;
	private JButton btnThongKe;
	private JScrollPane scrThongKeDoanhThu;
	private DefaultTableModel modelThongKeDoanhThu;
	private JTable tblThongKeDoanhThu;
	/**
	 * Create the panel.
	 */
	public ThongKeDoanhThu_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		lblThongKeDoanhThu = new JLabel("Thống kê doanh thu");
		lblThongKeDoanhThu.setBounds(615, 11, 370, 49);
		lblThongKeDoanhThu.setFont(new Font("SansSerif", Font.BOLD, 40));
//		pnlThongKeDoanhThu.add(lblThongKeDoanhThu);
		add(lblThongKeDoanhThu);

		// Thêm label Tháng
		lblThang = new JLabel("Tháng");
		lblThang.setBounds(80, 98, 150, 30);
		lblThang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblThang);
		add(lblThang);

		// Thêm label Năm
		lblNam = new JLabel("Năm");
		lblNam.setBounds(80, 168, 150, 30);
		lblNam.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblNam);
		add(lblNam);

		// Thêm label Tổng doanh thu
		lblTongDoanhThu = new JLabel("Tổng doanh thu");
		lblTongDoanhThu.setBounds(68, 950, 250, 30);
		lblTongDoanhThu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblTongDoanhThu);
		add(lblTongDoanhThu);

		// Thêm label VND
		lblVND = new JLabel("VNĐ");
		lblVND.setBounds(638, 950, 100, 30);
		lblVND.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblVND);
		add(lblVND);

		// Thêm label VND tổng tiền phòng
		lblVNDTongTienPhong = new JLabel("VNĐ");
		lblVNDTongTienPhong.setBounds(1381, 168, 100, 30);
		lblVNDTongTienPhong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblVNDTongTienPhong);
		add(lblVNDTongTienPhong);

		// Thêm label VND tổng tiền dịch vụ
		lblVNDTongTienDichVu = new JLabel("VNĐ");
		lblVNDTongTienDichVu.setBounds(1381, 238, 100, 30);
		lblVNDTongTienDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblVNDTongTienDichVu);
		add(lblVNDTongTienDichVu);

		// Thêm label tổng tiền phòng
		lblTongTienPhong = new JLabel("Tổng tiền phòng");
		lblTongTienPhong.setBounds(881, 168, 200, 30);
		lblTongTienPhong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblTongTienPhong);
		add(lblTongTienPhong);

		// Thêm label tổng tiền dịch vụ
		lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ");
		lblTongTienDichVu.setBounds(881, 238, 200, 30);
		lblTongTienDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		pnlThongKeDoanhThu.add(lblTongTienDichVu);
		add(lblTongTienDichVu);

		// Thêm combobox tháng
		cmbThang = new JComboBox<>();
		for (int i = 1; i <= 12; i++) {
			cmbThang.addItem(i);
		}
		cmbThang.setBounds(240, 100, 150, 30);
		cmbThang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbThang.setEditable(false);
		cmbThang.setFocusable(false);
		((JLabel) cmbThang.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeDoanhThu.add(cmbThang);
		add(cmbThang);

		// Thêm combobox năm
		cmbNam = new JComboBox<>();
		for (int i = 2000; i <= LocalDateTime.now().getYear(); i++) {
			cmbNam.addItem(i);
		}
		cmbNam.setBounds(240, 170, 150, 30);
		cmbNam.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cmbNam.setEditable(false);
		cmbNam.setFocusable(false);
		((JLabel) cmbNam.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
//		pnlThongKeDoanhThu.add(cmbNam);
		add(cmbNam);

		// Thêm button Thống kê
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBounds(1281, 943, 200, 45);
		btnThongKe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnThongKe.setBackground(new Color(120, 255, 239));
		btnThongKe.setFocusable(false);
//		pnlThongKeDoanhThu.add(btnThongKe);
		add(btnThongKe);

		// Thêm textField
		// Tổng doanh thu
		txtTongDoanhThu = new JTextField("0.00");
		txtTongDoanhThu.setBounds(228, 950, 390, 30);
		txtTongDoanhThu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongDoanhThu.setBackground(new Color(120, 255, 239));
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeDoanhThu.add(txtTongDoanhThu);
		add(txtTongDoanhThu);

		// Tổng tiền phòng
		txtTongTienPhong = new JTextField("0.00");
		txtTongTienPhong.setBounds(1061, 168, 300, 30);
		txtTongTienPhong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongTienPhong.setBackground(new Color(120, 255, 239));
		txtTongTienPhong.setEditable(false);
		txtTongTienPhong.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeDoanhThu.add(txtTongTienPhong);
		add(txtTongTienPhong);

		// Tổng tiền dịch vụ
		txtTongTienDichVu = new JTextField("0.00");
		txtTongTienDichVu.setBounds(1061, 238, 300, 30);
		txtTongTienDichVu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		txtTongTienDichVu.setBackground(new Color(120, 255, 239));
		txtTongTienDichVu.setEditable(false);
		txtTongTienDichVu.setHorizontalAlignment(JTextField.RIGHT);
//		pnlThongKeDoanhThu.add(txtTongTienDichVu);
		add(txtTongTienDichVu);

		String[] header = { "STT", "Mã phòng (dịch vụ)", "Tên phòng (dịch vụ)", "Đơn giá", "Số lượng", "Ngày lập",
				"Tên nhân viên", "Thành tiền" };
		modelThongKeDoanhThu = new DefaultTableModel(header, 0);

		add(scrThongKeDoanhThu = new JScrollPane(tblThongKeDoanhThu = new JTable(modelThongKeDoanhThu),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrThongKeDoanhThu.setBounds(80, 298, 1420, 620);
		scrThongKeDoanhThu.setBackground(new Color(120, 255, 239));
		scrThongKeDoanhThu.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrThongKeDoanhThu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblThongKeDoanhThu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblThongKeDoanhThu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	}

}
