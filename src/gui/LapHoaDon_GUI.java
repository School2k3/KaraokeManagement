package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LapHoaDon_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tblChonPhong, tblChiTietHoaDon;
	private DefaultTableModel modelPhong, modelChiTiethoaDon;
	private JTextField txtSoLuong;
	
	/**
	 * Create the panel.
	 */
	public LapHoaDon_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblLapHoaDon = new JLabel("Lập hóa đơn");
		lblLapHoaDon.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblLapHoaDon.setBounds(682, 11, 236, 62);
		add(lblLapHoaDon);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 768, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(908, 73, 211, 45);
		add(btnTimKiem);
		
		JLabel lblChonPhong = new JLabel("Chọn phòng");
		lblChonPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonPhong.setBounds(61, 134, 123, 30);
		add(lblChonPhong);
		
		JPanel pnlTablePhong = new JPanel();
		pnlTablePhong.setBounds(244, 129, 1303, 261);
		String[] colHeaderPhong = {"Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhong.setBounds(0, 0, 1303, 261);
		pnlTablePhong.add(scrPhong);
		add(pnlTablePhong);
		
		JButton btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNhanPhong.setBounds(1194, 70, 211, 45);
		add(btnNhanPhong);
		
		JButton btnThemPhongVaoHoaDon = new JButton("Thêm phòng vào hóa đơn");
		btnThemPhongVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThemPhongVaoHoaDon.setBounds(1184, 401, 330, 35);
		add(btnThemPhongVaoHoaDon);
		
		JPanel pnlChonDichVu = new JPanel();
		pnlChonDichVu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(61, 401, 956, 122);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);
		
		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ");
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLoaiDichVu.setBounds(28, 27, 103, 31);
		pnlChonDichVu.add(lblLoaiDichVu);
		
		JComboBox cmbLoaiDichVu = new JComboBox();
		cmbLoaiDichVu.setBounds(170, 27, 275, 31);
		pnlChonDichVu.add(cmbLoaiDichVu);
		
		JLabel lblTenDichVu = new JLabel("Tên dịch vụ");
		lblTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenDichVu.setBounds(28, 69, 103, 31);
		pnlChonDichVu.add(lblTenDichVu);
		
		JComboBox cmbTenDichVu = new JComboBox();
		cmbTenDichVu.setBounds(170, 69, 275, 31);
		pnlChonDichVu.add(cmbTenDichVu);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoLuong.setBounds(522, 27, 103, 31);
		pnlChonDichVu.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(676, 27, 73, 31);
		pnlChonDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JButton btnThemDichVuVaoHoaDon = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThemDichVuVaoHoaDon.setBounds(522, 67, 404, 35);
		pnlChonDichVu.add(btnThemDichVuVaoHoaDon);
		btnThemDichVuVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlThongTinHoaDon.setBounds(61, 540, 1496, 382);
		add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);
		
		JLabel lblThongTinHoaDon = new JLabel("Thông tin hóa đơn");
		lblThongTinHoaDon.setBounds(633, 11, 230, 34);
		lblThongTinHoaDon.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlThongTinHoaDon.add(lblThongTinHoaDon);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(240, 50, 102, 26);
		pnlThongTinHoaDon.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(813, 50, 114, 26);
		pnlThongTinHoaDon.add(lblSoDienThoai);
		
		JLabel lblChiTietHoaDon = new JLabel("Thông tin chi tiết hóa đơn");
		lblChiTietHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChiTietHoaDon.setBounds(10, 79, 221, 26);
		pnlThongTinHoaDon.add(lblChiTietHoaDon);
		
		JPanel pnlTableChiTietHoaDon = new JPanel();
		pnlTableChiTietHoaDon.setBounds(0, 111, 1496, 271);
		pnlThongTinHoaDon.add(pnlTableChiTietHoaDon);
		pnlTableChiTietHoaDon.setLayout(null);
		String[] colHeaderChiTietHoaDon = {"STT", "Mã phòng/dịch vụ", "Tên phòng/dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
		modelChiTiethoaDon = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTiethoaDon);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 1496, 271);
		pnlTableChiTietHoaDon.add(scrPhongDat);
		
		JLabel lblThoiGianVao = new JLabel("Thời gian vào");
		lblThoiGianVao.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianVao.setBounds(71, 933, 123, 31);
		add(lblThoiGianVao);
		
		JComboBox cmbGio = new JComboBox();
		cmbGio.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		cmbGio.setBounds(226, 933, 45, 30);
		add(cmbGio);
		
		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(281, 938, 17, 20);
		add(lblTime);
		
		JComboBox cmbPhut = new JComboBox();
		cmbPhut.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		cmbPhut.setBounds(299, 933, 45, 30);
		add(cmbPhut);
		
		JButton btnTinhGio = new JButton("Bắt đầu tính giờ");
		btnTinhGio.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTinhGio.setBounds(1220, 933, 270, 35);
		add(btnTinhGio);
	}
}
