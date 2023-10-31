package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frm_NhanPhong extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlFull;
	private JTextField txtTimKiemKhachHang;
	private DefaultTableModel modelPhieuDatPhong;
	private JTable tblPhieuDatPhong;
	private JButton btnTimKiem, btnNhanPhong, btnQuayLai;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_NhanPhong frame = new Frm_NhanPhong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frm_NhanPhong() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(380, 100, 1200, 800);
		pnlFull = new JPanel();
		pnlFull.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlFull);
		pnlFull.setLayout(null);
		
		JLabel lblNhanPhong = new JLabel("Nhận phòng");
		lblNhanPhong.setBounds(479, 11, 226, 52);
		lblNhanPhong.setFont(new Font("SansSerif", Font.BOLD, 40));
		pnlFull.add(lblNhanPhong);
		
		JLabel lblNhapThongTin = new JLabel("Nhập thông tin khách hàng cần tìm");
		lblNhapThongTin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNhapThongTin.setBounds(25, 81, 301, 26);
		pnlFull.add(lblNhapThongTin);
		
		txtTimKiemKhachHang = new JTextField();
		txtTimKiemKhachHang.setColumns(10);
		txtTimKiemKhachHang.setBounds(350, 80, 560, 30);
		pnlFull.add(txtTimKiemKhachHang);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setFocusable(false);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setBounds(940, 72, 211, 45);
		pnlFull.add(btnTimKiem);
		
		JLabel lblDanhSachDon = new JLabel("Danh sách đơn đặt phòng");
		lblDanhSachDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDanhSachDon.setBounds(25, 130, 226, 26);
		pnlFull.add(lblDanhSachDon);
		
		JPanel pnlTableChiTietHoaDon = new JPanel();
		pnlTableChiTietHoaDon.setBounds(0, 111, 1496, 271);

		// Tạo bảng chọn phòng
		JPanel pnlTablePhieuDatPhong = new JPanel();
		pnlTablePhieuDatPhong.setBounds(25, 185, 1126, 480);
		String[] colHeaderPhieuDatPhong = {"STT", "Mã phiếu đặt", "Tên khách hàng", "Số điện thoại", "Tên phòng", "Ngày đặt", "Thời gian đặt"};
		modelPhieuDatPhong = new DefaultTableModel(colHeaderPhieuDatPhong, 0);
		pnlTablePhieuDatPhong.setLayout(null);
		getContentPane().add(pnlTablePhieuDatPhong);
		tblPhieuDatPhong = new JTable(modelPhieuDatPhong);
		JScrollPane scrPhieuDatPhong = new JScrollPane(tblPhieuDatPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhieuDatPhong.setBounds(0, 0, 1126, 480);
		scrPhieuDatPhong.setBackground(new Color(120, 255, 239));
		scrPhieuDatPhong.getViewport().setBackground(Color.WHITE);
		scrPhieuDatPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblPhieuDatPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblPhieuDatPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhieuDatPhong.add(scrPhieuDatPhong);
		pnlFull.add(pnlTablePhieuDatPhong);
		
		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNhanPhong.setFocusable(false);
		btnNhanPhong.setBackground(new Color(217, 217, 217));
		btnNhanPhong.setBounds(685, 691, 211, 35);
		pnlFull.add(btnNhanPhong);
		
		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnQuayLai.setFocusable(false);
		btnQuayLai.setBackground(new Color(217, 217, 217));
		btnQuayLai.setBounds(940, 691, 211, 35);
		pnlFull.add(btnQuayLai);
		
		// Thêm sự kiện cho các nút
		btnTimKiem.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnQuayLai.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			
		}
		else if (o.equals(btnNhanPhong)) {
			
		}
		else if (o.equals(btnQuayLai)) {
			this.dispose();
		}
	}
}
