package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class LapHoaDon_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tblChonPhong, tblChonDichVu, tblChiTietHoaDon;
	private DefaultTableModel modelPhong, modelDichVu, modelChiTiethoaDon;
	private JTextField txtSoLuong;
	private JButton btnTimKiem, btnNhanPhong, btnThemPhongVaoHoaDon, btnThemDichVuVaoHoaDon, btnTinhGio, btnChonKhachHang;
	private Frm_NhanPhong winNhanPhong;
	private JTabbedPane tabbedPane;
	private Phong_DAO phongDAO;
	private LoaiPhong_DAO loaiPhongDAO;
	private PhieuDatPhong_DAO phieuDatPhongDAO;
	private KhachHang_DAO khachHangDAO;
	private List<Phong> listPhong;
	private List<LoaiPhong> listLoaiPhong;
	private List<PhieuDatPhong> listPhieuDatPhong;
	private List<KhachHang> listKhachHang;
	
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public LapHoaDon_GUI(JTabbedPane tabbedPane) throws Exception {
		this.tabbedPane = tabbedPane;
		
		// Kết nối với ConnectDB
		try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		phongDAO = new Phong_DAO();
		loaiPhongDAO = new LoaiPhong_DAO();
		phieuDatPhongDAO = new PhieuDatPhong_DAO();
		khachHangDAO = new KhachHang_DAO();
		
		// Thiết lập size cho giao diện
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblLapHoaDon = new JLabel("Lập hóa đơn");
		lblLapHoaDon.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblLapHoaDon.setBounds(682, 11, 236, 62);
		add(lblLapHoaDon);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 600, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(682, 70, 211, 45);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
		add(btnTimKiem);
		
		JLabel lblChonPhong = new JLabel("Chọn phòng");
		lblChonPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonPhong.setBounds(61, 134, 123, 30);
		add(lblChonPhong);
		
		// Tạo bảng chọn phòng
		JPanel pnlTablePhong = new JPanel();
		pnlTablePhong.setBounds(244, 129, 1303, 261);
		String[] colHeaderPhong = {"Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhong.setBounds(0, 0, 1303, 261);
		scrPhong.setBackground(new Color(120, 255, 239));
		scrPhong.getViewport().setBackground(Color.WHITE);
		scrPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhong.add(scrPhong);
		add(pnlTablePhong);
		
		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNhanPhong.setBounds(903, 70, 211, 45);
		btnNhanPhong.setBackground(new Color(217, 217, 217));
		btnNhanPhong.setFocusable(false);
		add(btnNhanPhong);
		
		btnThemPhongVaoHoaDon = new JButton("Thêm phòng vào hóa đơn");
		btnThemPhongVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThemPhongVaoHoaDon.setBounds(1184, 401, 330, 35);
		btnThemPhongVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemPhongVaoHoaDon.setFocusable(false);
		add(btnThemPhongVaoHoaDon);
		
		// Thiết lập phần chọn dịch vụ cần thêm
		JPanel pnlChonDichVu = new JPanel();
		pnlChonDichVu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chọn dịch vụ cần thêm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChonDichVu.setBounds(61, 401, 520, 521);
		add(pnlChonDichVu);
		pnlChonDichVu.setLayout(null);
		
		// Tạo bảng chọn dịch vụ
		JPanel pnlTableDichVu = new JPanel();
		pnlTableDichVu.setBounds(15, 20, 490, 446);
		String[] colHeaderDichVu = {"Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá"};
		modelDichVu = new DefaultTableModel(colHeaderDichVu, 0);
		pnlTableDichVu.setLayout(null);
		tblChonDichVu = new JTable(modelDichVu);
		JScrollPane scrDichVu = new JScrollPane(tblChonDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDichVu.setBounds(0, 0, 490, 446);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableDichVu.add(scrDichVu);
		pnlChonDichVu.add(pnlTableDichVu);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoLuong.setBounds(30, 477, 103, 31);
		pnlChonDichVu.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(130, 477, 50, 31);
		pnlChonDichVu.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		btnThemDichVuVaoHoaDon = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThemDichVuVaoHoaDon.setBounds(211, 475, 280, 35);
		btnThemDichVuVaoHoaDon.setBackground(new Color(217, 217, 217));
		btnThemDichVuVaoHoaDon.setFocusable(false);
		pnlChonDichVu.add(btnThemDichVuVaoHoaDon);
		btnThemDichVuVaoHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlThongTinHoaDon.setBounds(602, 447, 955, 475);
		add(pnlThongTinHoaDon);
		pnlThongTinHoaDon.setLayout(null);
		
		JLabel lblThongTinHoaDon = new JLabel("Thông tin hóa đơn");
		lblThongTinHoaDon.setBounds(362, 11, 230, 34);
		lblThongTinHoaDon.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlThongTinHoaDon.add(lblThongTinHoaDon);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(130, 48, 102, 26);
		pnlThongTinHoaDon.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(570, 47, 114, 26);
		pnlThongTinHoaDon.add(lblSoDienThoai);
		
		JLabel lblChiTietHoaDon = new JLabel("Thông tin chi tiết hóa đơn");
		lblChiTietHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChiTietHoaDon.setBounds(10, 79, 221, 26);
		pnlThongTinHoaDon.add(lblChiTietHoaDon);
		
		// Tạo bảng chi tiết hóa đơn
		JPanel pnlTableChiTietHoaDon = new JPanel();
		pnlTableChiTietHoaDon.setBounds(0, 111, 955, 364);
		pnlThongTinHoaDon.add(pnlTableChiTietHoaDon);
		pnlTableChiTietHoaDon.setLayout(null);
		String[] colHeaderChiTietHoaDon = {"STT", "Mã phòng/dịch vụ", "Tên phòng/dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
		modelChiTiethoaDon = new DefaultTableModel(colHeaderChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTiethoaDon);
		JScrollPane scrPhongDat = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 955, 364);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChiTietHoaDon.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChiTietHoaDon.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTableChiTietHoaDon.add(scrPhongDat);
		
		JLabel lblThoiGianVao = new JLabel("Thời gian vào");
		lblThoiGianVao.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblThoiGianVao.setBounds(71, 933, 123, 31);
		add(lblThoiGianVao);
		
		// Thiết lập phần tính giờ đặt phòng
		JComboBox cmbGio = new JComboBox();
		cmbGio.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cmbGio.setBounds(226, 933, 45, 30);
		add(cmbGio);
		
		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(281, 938, 17, 20);
		add(lblTime);
		
		JComboBox cmbPhut = new JComboBox();
		cmbPhut.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cmbPhut.setBounds(299, 933, 45, 30);
		add(cmbPhut);
		
		btnTinhGio = new JButton("Bắt đầu tính giờ");
		btnTinhGio.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTinhGio.setBounds(1290, 936, 270, 35);
		btnTinhGio.setBackground(new Color(217, 217, 217));
		btnTinhGio.setFocusable(false);
		add(btnTinhGio);
		
		btnChonKhachHang = new JButton("Chọn khách hàng");
		btnChonKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnChonKhachHang.setFocusable(false);
		btnChonKhachHang.setBackground(new Color(217, 217, 217));
		btnChonKhachHang.setBounds(1124, 71, 211, 42);
		add(btnChonKhachHang);
		
		JComboBox cmbTrangThai = new JComboBox();
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setBounds(1377, 85, 170, 30);
		add(cmbTrangThai);

		listPhong = phongDAO.getAllTablePhong();
		listLoaiPhong = loaiPhongDAO.getAllTableLoaiPhong();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();
		
		updateTableData_Phong();
		
		btnChonKhachHang.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThemDichVuVaoHoaDon.addActionListener(this);
		btnThemPhongVaoHoaDon.addActionListener(this);
		btnTinhGio.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnChonKhachHang)) {
			tabbedPane.setSelectedIndex(10);
		}
		else if (o.equals(btnNhanPhong)) {
			winNhanPhong = new Frm_NhanPhong();
			winNhanPhong.setVisible(true);
		} else if (o.equals(btnTimKiem)) {
			
		} else if (o.equals(btnThemDichVuVaoHoaDon)) {
			
		} else if (o.equals(btnThemPhongVaoHoaDon)) {
			
		} else if (o.equals(btnTinhGio)) {
			
		}
	}
	
	private void updateTableData_Phong() {
		modelPhong.getDataVector().removeAllElements();
		for (Phong p : listPhong) {
			String malp = p.getLoaiPhong().getMaLoaiPhong();
			String tenlp = "";
			for (LoaiPhong lp : listLoaiPhong) {
				if (lp.getMaLoaiPhong().equals(malp)) {
					tenlp = lp.getTenLoaiPhong();
					break;
				}
			}
			modelPhong.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), tenlp, p.getDonGia(), p.getTrangThai()});
		}
	}
}
