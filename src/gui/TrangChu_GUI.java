package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TrangChu_GUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem mniCapNhatPhong, mniCapNhatLoaiPhong, mniTimPhong, mniDatPhong,
						mniCapNhatDichVu, mniCapNhatLoaiDichVu, mniTimDichVu, mniThongKeDichVu,
						mniCapNhatKhachHang, mniTimKiemKhachHang, mniThongKeKhachHang, 
						mniCapNhatNhanVien, mniTimKiemNhanVien, mniThongKeSoLuongHoaDon,
						mniLapHoaDon, mniThanhToan, mniThongKeDoanhThu;
	private JMenu mnTroGiup;
	private JTabbedPane tabbedPane;
	private TimKiemKhachHang_GUI pnlTimKiemKhachHang;
	private int x = 0, w = 300, h = 50, dms1 = 298, dms2 = 50;

	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TrangChu_GUI() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlFull = new JPanel();
		pnlFull.setLayout(null);
		pnlFull.setBounds(0, 0, 1920, 1080);
		pnlFull.setBackground(Color.WHITE);
		// Thanh menu
		JMenuBar mnuBar = new JMenuBar();
		mnuBar.setLayout(null);
		mnuBar.setBounds(0, 0, 300, 1041);
		mnuBar.setBackground(new Color(120, 255, 239));
		mnuBar.setBorder(new LineBorder(new Color(120, 255, 239)));
		
		JLabel lblTenCuaHang = new JLabel("Karaoke Nice");
		lblTenCuaHang.setBounds(70, 25, 200, 25);
		lblTenCuaHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		mnuBar.add(lblTenCuaHang);
		
		// Phần JMenu
		JMenu mnPhong = new JMenu("Phòng");
		mnPhong.setBounds(x, 200, w, h);
//		mnPhong.getAccessibleContext().getAccessibleComponent().setBackground(Color.RED);
		mnPhong.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnPhong.setOpaque(true);
		mnPhong.setBackground(new Color(237, 237, 237));
		mnPhong.setHorizontalAlignment(SwingConstants.CENTER);
		mnuBar.add(mnPhong);
		
		JMenu mnDichVu = new JMenu("Dịch vụ");
		mnDichVu.setBounds(x, 250, w, h);
		mnPhong.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnDichVu.setOpaque(true);
		mnDichVu.setBackground(new Color(237, 237, 237));
		mnuBar.add(mnDichVu);
		
		JMenu mnKhachHang = new JMenu("Khách hàng");
		mnKhachHang.setBounds(x, 300, w, h);
		mnKhachHang.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnKhachHang.setOpaque(true);
		mnKhachHang.setBackground(new Color(237, 237, 237));
		mnuBar.add(mnKhachHang);
		
		JMenu mnNhanVien = new JMenu("Nhân Viên");
		mnNhanVien.setBounds(x, 350, w, h);
		mnNhanVien.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnNhanVien.setOpaque(true);
		mnNhanVien.setBackground(new Color(237, 237, 237));
		mnuBar.add(mnNhanVien);
		
		JMenu mnHoaDon = new JMenu("Hóa đơn");
		mnHoaDon.setBounds(x, 400, w, h);
		mnHoaDon.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnHoaDon.setOpaque(true);
		mnHoaDon.setBackground(new Color(237, 237, 237));
		mnuBar.add(mnHoaDon);
		
		mnTroGiup = new JMenu("Trợ giúp");
		mnTroGiup.setBounds(x, 450, w, h);
		mnTroGiup.getAccessibleContext().getAccessibleComponent().setForeground(Color.BLACK);
		mnTroGiup.setOpaque(true);
		mnTroGiup.setBackground(new Color(237, 237, 237));
		mnuBar.add(mnTroGiup);
		
		// Phần JLabel thông tin
		JLabel lblTaiKhoanNhanVien = new JLabel("Nhân viên: ");
		lblTaiKhoanNhanVien.setBounds(20, 650, 87, 25);
		lblTaiKhoanNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		mnuBar.add(lblTaiKhoanNhanVien);
		
		JLabel lblChucVuNhanVien = new JLabel("Chức vụ: ");
		lblChucVuNhanVien.setBounds(20, 700, 87, 25);
		lblChucVuNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		mnuBar.add(lblChucVuNhanVien);
		
		// Phần Button đăng xuất
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setBounds(100, 750, 100, 35);
		btnDangXuat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		btnDangXuat.getAccessibleContext().getAccessibleComponent().setForeground(Color.WHITE);
		btnDangXuat.setBackground(new Color(46, 159, 222));
		btnDangXuat.setFocusable(false);
		mnuBar.add(btnDangXuat);
		
		// Thêm MenuItem vào Menu
		
		// Menu phòng
		mniCapNhatPhong = new JMenuItem("Cập nhật phòng");
		mniCapNhatPhong.setPreferredSize(new Dimension(dms1, dms2));
		mnPhong.add(mniCapNhatPhong);
		
		mniCapNhatLoaiPhong = new JMenuItem("Cập nhật loại phòng");
		mniCapNhatLoaiPhong.setPreferredSize(new Dimension(dms1, dms2));
		mnPhong.add(mniCapNhatLoaiPhong);
		
		mniTimPhong = new JMenuItem("Tìm phòng");
		mniTimPhong.setPreferredSize(new Dimension(dms1, dms2));
		mnPhong.add(mniTimPhong);
		
		mniDatPhong = new JMenuItem("Đặt phòng");
		mniDatPhong.setPreferredSize(new Dimension(dms1, dms2));
		mnPhong.add(mniDatPhong);
		
		// Menu Dịch vụ
		mniCapNhatDichVu = new JMenuItem("Cập nhật dịch vụ");
		mniCapNhatDichVu.setPreferredSize(new Dimension(dms1, dms2));
		mnDichVu.add(mniCapNhatDichVu);
		
		mniCapNhatLoaiDichVu = new JMenuItem("Cập nhật loại dịch vụ");
		mniCapNhatLoaiDichVu.setPreferredSize(new Dimension(dms1, dms2));
		mnDichVu.add(mniCapNhatLoaiDichVu);
		
		mniTimDichVu = new JMenuItem("Tìm dịch vụ");
		mniTimDichVu.setPreferredSize(new Dimension(dms1, dms2));
		mnDichVu.add(mniTimDichVu);
		
		mniThongKeDichVu = new JMenuItem("Thống kê dịch vụ");
		mniThongKeDichVu.setPreferredSize(new Dimension(dms1, dms2));
		mnDichVu.add(mniThongKeDichVu);
		
		// Menu Khách hàng
		mniCapNhatKhachHang = new JMenuItem("Cập nhật khách hàng");
		mniCapNhatKhachHang.setPreferredSize(new Dimension(dms1, dms2));
		mnKhachHang.add(mniCapNhatKhachHang);
		
		mniTimKiemKhachHang = new JMenuItem("Tìm kiếm khách hàng");
		mniTimKiemKhachHang.setPreferredSize(new Dimension(dms1, dms2));
		mnKhachHang.add(mniTimKiemKhachHang);
		
		mniThongKeKhachHang = new JMenuItem("Thống kê khách hàng");
		mniThongKeKhachHang.setPreferredSize(new Dimension(dms1, dms2));
		mnKhachHang.add(mniThongKeKhachHang);
		
		// Menu Nhân viên
		mniCapNhatNhanVien = new JMenuItem("Cập nhật nhân viên");
		mniCapNhatNhanVien.setPreferredSize(new Dimension(dms1, dms2));
		mnNhanVien.add(mniCapNhatNhanVien);
		
		mniTimKiemNhanVien = new JMenuItem("Tìm kiếm nhân viên");
		mniTimKiemNhanVien.setPreferredSize(new Dimension(dms1, dms2));
		mnNhanVien.add(mniTimKiemNhanVien);
		
		mniThongKeSoLuongHoaDon = new JMenuItem("Thống kê số lượng hóa đơn");
		mniThongKeSoLuongHoaDon.setPreferredSize(new Dimension(dms1, dms2));
		mnNhanVien.add(mniThongKeSoLuongHoaDon);
		
		// Menu hóa đơn
		
		mniLapHoaDon = new JMenuItem("Lập hóa đơn");
		mniLapHoaDon.setPreferredSize(new Dimension(dms1, dms2));
		mnHoaDon.add(mniLapHoaDon);
		
		mniThanhToan = new JMenuItem("Thanh toán");
		mniThanhToan.setPreferredSize(new Dimension(dms1, dms2));
		mnHoaDon.add(mniThanhToan);
		
		mniThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
		mniThongKeDoanhThu.setPreferredSize(new Dimension(dms1, dms2));
		mnHoaDon.add(mniThongKeDoanhThu);
		
		pnlFull.add(mnuBar);
		setTitle("Quản lý karaoke");
		setSize(1920, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(pnlFull);
		
		// Thêm JTabbedPane để thêm các JPanel
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(299, -43, 1605, 1084);
		pnlFull.add(tabbedPane);
		
		// JPanel trang chủ
		JPanel pnlTrangChu = new JPanel();
		tabbedPane.addTab("TrangChu", null, pnlTrangChu, null);
		pnlTrangChu.setLayout(null);
		
		// JPanel cập nhật phòng
		CapNhatPhong_GUI pnlCapNhatPhong = new CapNhatPhong_GUI();
		tabbedPane.addTab("CapNhatPhong", null, pnlCapNhatPhong, null);
		pnlCapNhatPhong.setLayout(null);
		
		// JPanel cập nhật loại phòng
		CapNhatLoaiPhong_GUI pnlCapNhatLoaiPhong = new CapNhatLoaiPhong_GUI();
		tabbedPane.addTab("CapNhatLoaiPhong", null, pnlCapNhatLoaiPhong, null);
		pnlCapNhatLoaiPhong.setLayout(null);
		
		// JPanel tìm kiếm phòng
		TimKiemPhong_GUI pnlTimKiemPhong = new TimKiemPhong_GUI();
		tabbedPane.addTab("TimKiemPhong", null, pnlTimKiemPhong, null);
		pnlTimKiemPhong.setLayout(null);
		
		// JPanel đặt phòng
		DatPhong_GUI pnlDatPhong = new DatPhong_GUI(tabbedPane);
		tabbedPane.addTab("DatPhong", null, pnlDatPhong, null);
		pnlDatPhong.setLayout(null);
		
		// JPanel cập nhật dịch vụ
		CapNhatDichVu_GUI pnlCapNhatDichVu = new CapNhatDichVu_GUI();
		tabbedPane.addTab("CapNhatDichVu", null, pnlCapNhatDichVu, null);
		pnlCapNhatDichVu.setLayout(null);
		
		// JPanel cập nhật loại dịch vụ
		CapNhatLoaiDichVu_GUI pnlCapNhatLoaiDichVu = new CapNhatLoaiDichVu_GUI();
		tabbedPane.addTab("CapNhatLoaiDichVu", null, pnlCapNhatLoaiDichVu, null);
		pnlCapNhatLoaiDichVu.setLayout(null);
		
		// JPanel tìm kiếm dịch vụ
		TimKiemDichVu_GUI pnlTimKiemDichVu = new TimKiemDichVu_GUI();
		tabbedPane.addTab("TimKiemDichVu", null, pnlTimKiemDichVu, null);
		pnlTimKiemDichVu.setLayout(null);
		
		// JPanel thống kê dịch vụ
		ThongKeDichVu_GUI pnlThongKeDichVu = new ThongKeDichVu_GUI();
		tabbedPane.addTab("ThongKeDichVu", null, pnlThongKeDichVu, null);
		pnlThongKeDichVu.setLayout(null);
		
		// JPanel cập nhật khách hàng
		CapNhatKhachHang_GUI pnlCapNhatKhachHang = new CapNhatKhachHang_GUI();
		tabbedPane.addTab("CapNhatKhachHang", null, pnlCapNhatKhachHang, null);
		pnlCapNhatKhachHang.setLayout(null);
		
		// JPanel tìm kiếm khách hàng
		pnlTimKiemKhachHang = new TimKiemKhachHang_GUI(tabbedPane);
		tabbedPane.addTab("TimKiemKhachHang", null, pnlTimKiemKhachHang, null);
		pnlTimKiemKhachHang.setLayout(null);
		
		// JPanel thống kê khách hàng
		ThongKeKhachHang_GUI pnlThongKeKhachHang = new ThongKeKhachHang_GUI();
		tabbedPane.addTab("ThongKeKhachHang", null, pnlThongKeKhachHang, null);
		pnlThongKeKhachHang.setLayout(null);
		
		// JPanel cập nhật nhân viên
		CapNhatNhanVien_GUI pnlCapNhatNhanVien = new CapNhatNhanVien_GUI();
		tabbedPane.addTab("CapNhatNhanVien", null, pnlCapNhatNhanVien, null);
		pnlCapNhatNhanVien.setLayout(null);
		
		// JPanel tìm kiếm khách hàng
		TimKiemNhanVien_GUI pnlTimKiemNhanVien = new TimKiemNhanVien_GUI();
		tabbedPane.addTab("TimKiemNhanVien", null, pnlTimKiemNhanVien, null);
		pnlTimKiemNhanVien.setLayout(null);
		
		// JPanel thống kê số lượng hóa đơn
		ThongKeSoLuongHoaDon_GUI pnlThongKeSoLuongHoaDon = new ThongKeSoLuongHoaDon_GUI();
		tabbedPane.addTab("ThongKeSoLuongHoaDon", null, pnlThongKeSoLuongHoaDon, null);
		pnlThongKeSoLuongHoaDon.setLayout(null);

		// JPanel lập hóa đơn
		LapHoaDon_GUI pnlLapHoaDon = new LapHoaDon_GUI(tabbedPane);
		tabbedPane.addTab("LapHoaDon", null, pnlLapHoaDon, null);
		pnlLapHoaDon.setLayout(null);
		
		// JPanel thanh toán
		ThanhToan_GUI pnlThanhToan = new ThanhToan_GUI();
		tabbedPane.addTab("ThanhToan", null, pnlThanhToan, null);
		pnlThanhToan.setLayout(null);
		
		// JPanel thống kê doanh thu
		ThongKeDoanhThu_GUI pnlThongKeDoanhThu = new ThongKeDoanhThu_GUI();
		tabbedPane.addTab("ThongKeDoanhThu", null, pnlThongKeDoanhThu, null);
		pnlThongKeDoanhThu.setLayout(null);
		
		//Thêm sự kiện vào từng MenuItem
		mniCapNhatPhong.addActionListener(this);
		mniCapNhatLoaiPhong.addActionListener(this);
		mniTimPhong.addActionListener(this);
		mniDatPhong.addActionListener(this);
		mniCapNhatDichVu.addActionListener(this);
		mniCapNhatLoaiDichVu.addActionListener(this);
		mniTimDichVu.addActionListener(this);
		mniThongKeDichVu.addActionListener(this);
		mniCapNhatKhachHang.addActionListener(this);
		mniTimKiemKhachHang.addActionListener(this);
		mniThongKeKhachHang.addActionListener(this);
		mniCapNhatNhanVien.addActionListener(this);
		mniTimKiemNhanVien.addActionListener(this);
		mniThongKeSoLuongHoaDon.addActionListener(this);
		mniLapHoaDon.addActionListener(this);
		mniThanhToan.addActionListener(this);
		mniThongKeDoanhThu.addActionListener(this);
		mnTroGiup.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(mniCapNhatPhong)) {
			tabbedPane.setSelectedIndex(1);
		}
		else if (o.equals(mniCapNhatLoaiPhong)) {
			tabbedPane.setSelectedIndex(2);
		}
		else if (o.equals(mniTimPhong)) {
			tabbedPane.setSelectedIndex(3);
		}
		else if (o.equals(mniDatPhong)) {
			tabbedPane.setSelectedIndex(4);
		}
		else if (o.equals(mniCapNhatDichVu)) {
			tabbedPane.setSelectedIndex(5);
		}
		else if (o.equals(mniCapNhatLoaiDichVu)) {
			tabbedPane.setSelectedIndex(6);
		}
		else if (o.equals(mniTimDichVu)) {
			tabbedPane.setSelectedIndex(7);
		}
		else if (o.equals(mniThongKeDichVu)) {
			tabbedPane.setSelectedIndex(8);
		}
		else if (o.equals(mniCapNhatKhachHang)) {
			tabbedPane.setSelectedIndex(9);
		}
		else if (o.equals(mniTimKiemKhachHang)) {
			tabbedPane.setSelectedIndex(10);
		}
		else if (o.equals(mniThongKeKhachHang)) {
			tabbedPane.setSelectedIndex(11);
		}
		else if (o.equals(mniCapNhatNhanVien)) {
			tabbedPane.setSelectedIndex(12);
		}
		else if (o.equals(mniTimKiemNhanVien)) {
			tabbedPane.setSelectedIndex(13);
		}
		else if (o.equals(mniThongKeSoLuongHoaDon)) {
			tabbedPane.setSelectedIndex(14);
		}
		else if (o.equals(mniLapHoaDon)) {
			tabbedPane.setSelectedIndex(15);
		}
		else if (o.equals(mniThanhToan)) {
			tabbedPane.setSelectedIndex(16);
		}
		else if (o.equals(mniThongKeDoanhThu)) {
			tabbedPane.setSelectedIndex(17);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu_GUI frame = new TrangChu_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
