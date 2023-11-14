package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Frm_HoaDon extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlFull;
	public JTextField txtMaHoaDon, txtNhanVienLap, txtKhachHang, txtTenPhong, txtLoaiPhong, txtDonGia, txtThoiGianBatDau, txtThoiGianKetThuc, txtThoiGianSuDung, txtTienPhong, txtTienDichVu, txtThueVAT, txtTongTien, txtTienThua;
	public DefaultTableModel modelChiTietDichVu;
	public JTable tblChiTietDichVu;
	public JButton btnThanhToan, btnInHoaDon, btnQuayLai;
	public HoaDon_DAO hoaDonDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_HoaDon frame = new Frm_HoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Frm_HoaDon() throws Exception {
		// Thiết kế kích cỡ cho hóa đơn
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(380, 30, 1200, 1000);
		pnlFull = new JPanel();
		pnlFull.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlFull);
		pnlFull.setLayout(null);

		JLabel lblTitleKaraoke = new JLabel("Karaoke 3T");
		lblTitleKaraoke.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitleKaraoke.setBounds(485, 0, 214, 52);
		pnlFull.add(lblTitleKaraoke);

		JLabel lblTitleHoaDon = new JLabel("Hóa đơn");
		lblTitleHoaDon.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitleHoaDon.setBounds(511, 124, 161, 52);
		pnlFull.add(lblTitleHoaDon);

		JLabel lblDiaChiKaraoke = new JLabel("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, TP HCM");
		lblDiaChiKaraoke.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDiaChiKaraoke.setBounds(350, 52, 486, 26);
		pnlFull.add(lblDiaChiKaraoke);

		JLabel lblSoDienThoaiKaraoke = new JLabel("090.963.1257");
		lblSoDienThoaiKaraoke.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoaiKaraoke.setBounds(530, 89, 122, 26);
		pnlFull.add(lblSoDienThoaiKaraoke);

		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon
				.setBorder(new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP));
		pnlThongTinHoaDon.setBounds(40, 175, 1100, 240);
		pnlFull.add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn");
		lblMaHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaHoaDon.setBounds(21, 25, 106, 26);
		pnlThongTinHoaDon.add(lblMaHoaDon);

		JLabel lblNhanVienLap = new JLabel("Nhân viên lập");
		lblNhanVienLap.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNhanVienLap.setBounds(21, 67, 116, 26);
		pnlThongTinHoaDon.add(lblNhanVienLap);

		JLabel lblKhachHang = new JLabel("Khách hàng");
		lblKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblKhachHang.setBounds(21, 109, 102, 26);
		pnlThongTinHoaDon.add(lblKhachHang);

		JLabel lblTenPhong = new JLabel("Tên phòng");
		lblTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenPhong.setBounds(21, 151, 92, 26);
		pnlThongTinHoaDon.add(lblTenPhong);

		JLabel lblLoaiPhong = new JLabel("Loại phòng");
		lblLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLoaiPhong.setBounds(21, 193, 102, 26);
		pnlThongTinHoaDon.add(lblLoaiPhong);

		JLabel lblDonGia = new JLabel("Đơn giá phòng");
		lblDonGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDonGia.setBounds(590, 25, 128, 26);
		pnlThongTinHoaDon.add(lblDonGia);

		JLabel lblThoiGianBatDau = new JLabel("Thời gian bắt đầu");
		lblThoiGianBatDau.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianBatDau.setBounds(590, 67, 153, 26);
		pnlThongTinHoaDon.add(lblThoiGianBatDau);

		JLabel lblThoiGianKetThuc = new JLabel("Thời gian kết thúc");
		lblThoiGianKetThuc.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianKetThuc.setBounds(590, 109, 164, 26);
		pnlThongTinHoaDon.add(lblThoiGianKetThuc);

		JLabel lblThoiGianSuDung = new JLabel("Thời gian sử dụng");
		lblThoiGianSuDung.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianSuDung.setBounds(590, 151, 164, 26);
		pnlThongTinHoaDon.add(lblThoiGianSuDung);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtMaHoaDon.setBounds(180, 25, 300, 26);
		pnlThongTinHoaDon.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);

		txtNhanVienLap = new JTextField();
		txtNhanVienLap.setEditable(false);
		txtNhanVienLap.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtNhanVienLap.setColumns(10);
		txtNhanVienLap.setBounds(180, 67, 300, 26);
		pnlThongTinHoaDon.add(txtNhanVienLap);

		txtKhachHang = new JTextField();
		txtKhachHang.setEditable(false);
		txtKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtKhachHang.setColumns(10);
		txtKhachHang.setBounds(180, 109, 300, 26);
		pnlThongTinHoaDon.add(txtKhachHang);

		txtTenPhong = new JTextField();
		txtTenPhong.setEditable(false);
		txtTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(180, 151, 300, 26);
		pnlThongTinHoaDon.add(txtTenPhong);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setEditable(false);
		txtLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtLoaiPhong.setColumns(10);
		txtLoaiPhong.setBounds(180, 193, 300, 26);
		pnlThongTinHoaDon.add(txtLoaiPhong);

		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(778, 25, 300, 26);
		pnlThongTinHoaDon.add(txtDonGia);

		txtThoiGianBatDau = new JTextField();
		txtThoiGianBatDau.setEditable(false);
		txtThoiGianBatDau.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtThoiGianBatDau.setColumns(10);
		txtThoiGianBatDau.setBounds(778, 67, 300, 26);
		pnlThongTinHoaDon.add(txtThoiGianBatDau);

		txtThoiGianKetThuc = new JTextField();
		txtThoiGianKetThuc.setEditable(false);
		txtThoiGianKetThuc.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtThoiGianKetThuc.setColumns(10);
		txtThoiGianKetThuc.setBounds(778, 109, 300, 26);
		pnlThongTinHoaDon.add(txtThoiGianKetThuc);

		txtThoiGianSuDung = new JTextField();
		txtThoiGianSuDung.setEditable(false);
		txtThoiGianSuDung.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtThoiGianSuDung.setColumns(10);
		txtThoiGianSuDung.setBounds(778, 151, 300, 26);
		pnlThongTinHoaDon.add(txtThoiGianSuDung);

		// Tạo bảng chi tiết dịch vụ
		JPanel pnlTableChiTietDichVu = new JPanel();
		pnlTableChiTietDichVu.setBorder(new TitledBorder(null, "Thông tin chi tiết dịch vụ đã đặt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTableChiTietDichVu.setBounds(40, 426, 1100, 250);
		pnlFull.add(pnlTableChiTietDichVu);
		pnlTableChiTietDichVu.setLayout(null);
		String[] colHeaderChiTietHoaDon = { "STT", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền" };
		modelChiTietDichVu = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietDichVu = new JTable(modelChiTietDichVu);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(10, 22, 1080, 217);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChiTietDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChiTietDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableChiTietDichVu.add(scrPhongDat);
		
		// Thêm các nút cho giao diện
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThanhToan.setBounds(131, 916, 231, 34);
		pnlFull.add(btnThanhToan);
		
		btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setEnabled(false);
		btnInHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnInHoaDon.setBounds(492, 916, 231, 34);
		pnlFull.add(btnInHoaDon);
		
		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnQuayLai.setBounds(853, 916, 200, 34);
		pnlFull.add(btnQuayLai);
		
		// Thêm các thông tin tính tiền hóa đơn cho giao diện
		JLabel lblTienPhong = new JLabel("Tiền phòng (VNĐ)");
		lblTienPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTienPhong.setBounds(75, 687, 275, 31);
		pnlFull.add(lblTienPhong);
		
		txtTienPhong = new JTextField();
		txtTienPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTienPhong.setEditable(false);
		txtTienPhong.setColumns(10);
		txtTienPhong.setBounds(735, 687, 352, 31);
		pnlFull.add(txtTienPhong);
		
		JLabel lblTienDichVu = new JLabel("Tiền dịch vụ (VNĐ)");
		lblTienDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTienDichVu.setBounds(75, 729, 275, 31);
		pnlFull.add(lblTienDichVu);
		
		txtTienDichVu = new JTextField();
		txtTienDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTienDichVu.setEditable(false);
		txtTienDichVu.setColumns(10);
		txtTienDichVu.setBounds(735, 729, 352, 31);
		pnlFull.add(txtTienDichVu);
		
		JLabel lblThueVAT = new JLabel("Thuế VAT (10%) (VNĐ)");
		lblThueVAT.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThueVAT.setBounds(75, 771, 275, 31);
		pnlFull.add(lblThueVAT);
		
		txtThueVAT = new JTextField();
		txtThueVAT.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtThueVAT.setEditable(false);
		txtThueVAT.setColumns(10);
		txtThueVAT.setBounds(735, 771, 352, 31);
		pnlFull.add(txtThueVAT);
		
		JLabel lblTongTien = new JLabel("Tổng tiền cần thanh toán (VNĐ)");
		lblTongTien.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTongTien.setBounds(76, 813, 275, 31);
		pnlFull.add(lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(736, 813, 352, 31);
		pnlFull.add(txtTongTien);
		
		JLabel lblTienThua = new JLabel("Tiền thừa (VNĐ)");
		lblTienThua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTienThua.setBounds(76, 855, 275, 31);
		pnlFull.add(lblTienThua);
		
		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTienThua.setEditable(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(736, 855, 352, 31);
		pnlFull.add(txtTienThua);
		
		hoaDonDAO = new HoaDon_DAO();
		
		// Thiết lập các sự kiện cho các nút
		btnThanhToan.addActionListener(this);
		btnInHoaDon.addActionListener(this);
		btnQuayLai.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThanhToan)) {
			JOptionPane.showMessageDialog(this, "Đã thanh toán hóa đơn thành công!");
			String maHoaDon = txtMaHoaDon.getText();
			hoaDonDAO.updateTrangThai(maHoaDon, "Đã thanh toán");
		} else if (o.equals(btnInHoaDon)) {
			
		} else if (o.equals(btnQuayLai)) {
			this.dispose();
		}
	}


	
}
