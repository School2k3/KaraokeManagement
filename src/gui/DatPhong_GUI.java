package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;

import java.awt.Color;

public class DatPhong_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tblChonPhong, tblPhongDat;
	private JTextField txtNgayDat;
	private JDatePicker dtpNgayDat;
	private DefaultTableModel modelPhong, modelPhongDat;
	private JButton btnTimKiem, btnThemPhieuDatPhong, btnXoaTrang, btnLuuPhieuDatPhong, btnHuyPhieu, btnChonKhachHang;
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
	public DatPhong_GUI(JTabbedPane tabbedPane) throws Exception {
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
		
		JLabel lblDatPhong = new JLabel("Đặt phòng");
		lblDatPhong.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblDatPhong.setBounds(698, 11, 203, 62);
		add(lblDatPhong);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 740, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(840, 73, 211, 42);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFocusable(false);
		add(btnTimKiem);
		
		JLabel lblChonPhong = new JLabel("Chọn phòng");
		lblChonPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonPhong.setBounds(61, 132, 123, 30);
		add(lblChonPhong);
		
		// Tạo bảng chọn phòng
		JPanel pnlTablePhong = new JPanel();
		pnlTablePhong.setBounds(243, 129, 1303, 289);
		String[] colHeaderPhong = {"Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhong.setBounds(0, 0, 1303, 289);
		scrPhong.setBackground(new Color(120, 255, 239));
		scrPhong.getViewport().setBackground(Color.WHITE);
		scrPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhong.add(scrPhong);
		add(pnlTablePhong);
		
		JLabel lblNgayDat = new JLabel("Ngày đặt phòng");
		lblNgayDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNgayDat.setBounds(61, 429, 153, 30);
		add(lblNgayDat);
		
		txtNgayDat = new JTextField();
		txtNgayDat.setBounds(244, 430, 240, 29);
		add(txtNgayDat);
		txtNgayDat.setColumns(10);
		
		JLabel lblGioDat = new JLabel("Giờ đặt");
		lblGioDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGioDat.setBounds(614, 429, 93, 30);
		add(lblGioDat);
		
		JComboBox cmbGio = new JComboBox();
		cmbGio.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cmbGio.setBounds(729, 429, 45, 30);
		cmbGio.setBackground(new Color(217, 217, 217));
		add(cmbGio);
		
		JComboBox cmbPhut = new JComboBox();
		cmbPhut.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cmbPhut.setBounds(802, 429, 45, 30);
		cmbPhut.setBackground(new Color(217, 217, 217));
		add(cmbPhut);
		
		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(784, 434, 17, 20);
		add(lblTime);
		
		btnThemPhieuDatPhong = new JButton("Thêm vào phiếu đặt phòng");
		btnThemPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnThemPhieuDatPhong.setBounds(949, 429, 303, 31);
		btnThemPhieuDatPhong.setBackground(new Color(217, 217, 217));
		btnThemPhieuDatPhong.setFocusable(false);
		add(btnThemPhieuDatPhong);
		
		btnXoaTrang = new JButton("Làm mới");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnXoaTrang.setBounds(1357, 430, 170, 30);
		btnXoaTrang.setBackground(new Color(217, 217, 217));
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);
		
		JPanel pnlPhieuDatPhong = new JPanel();
		pnlPhieuDatPhong.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlPhieuDatPhong.setBounds(61, 470, 1496, 474);
		add(pnlPhieuDatPhong);
		pnlPhieuDatPhong.setLayout(null);
		
		JLabel lblPhieuDatPhong = new JLabel("Phiếu đặt phòng");
		lblPhieuDatPhong.setBounds(646, 11, 205, 34);
		lblPhieuDatPhong.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlPhieuDatPhong.add(lblPhieuDatPhong);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(240, 56, 102, 26);
		pnlPhieuDatPhong.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(813, 56, 114, 26);
		pnlPhieuDatPhong.add(lblSoDienThoai);
		
		JLabel lblDanhSachPhong = new JLabel("Danh sách phòng đã đặt");
		lblDanhSachPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDanhSachPhong.setBounds(10, 97, 213, 26);
		pnlPhieuDatPhong.add(lblDanhSachPhong);
		
		// Tạo bảng phiếu đặt phòng
		JPanel pnlTablePhongDat = new JPanel();
		pnlTablePhongDat.setBounds(0, 144, 1496, 328);
		pnlPhieuDatPhong.add(pnlTablePhongDat);
		pnlTablePhongDat.setLayout(null);
		String[] colHeaderDatPhong = {"Mã phòng", "Tên phòng", "Ngày đặt", "Thời gian đặt"};
		modelPhongDat = new DefaultTableModel(colHeaderDatPhong, 0);
		tblPhongDat = new JTable(modelPhongDat);
		JScrollPane scrPhongDat = new JScrollPane(tblPhongDat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 1496, 328);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblPhongDat.getTableHeader().setBackground(new Color(120, 255, 239));
		tblPhongDat.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhongDat.add(scrPhongDat);
		
		btnLuuPhieuDatPhong = new JButton("Lưu phiếu đặt phòng");
		btnLuuPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLuuPhieuDatPhong.setBounds(949, 955, 303, 31);
		btnLuuPhieuDatPhong.setBackground(new Color(217, 217, 217));
		btnLuuPhieuDatPhong.setFocusable(false);
		add(btnLuuPhieuDatPhong);
		
		btnHuyPhieu = new JButton("Hủy phiếu");
		btnHuyPhieu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnHuyPhieu.setBounds(1357, 956, 170, 30);
		btnHuyPhieu.setBackground(new Color(217, 217, 217));
		btnHuyPhieu.setFocusable(false);
		add(btnHuyPhieu);
		
		btnChonKhachHang = new JButton("Chọn khách hàng");
		btnChonKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnChonKhachHang.setFocusable(false);
		btnChonKhachHang.setBackground(new Color(217, 217, 217));
		btnChonKhachHang.setBounds(1089, 73, 211, 42);
		add(btnChonKhachHang);
		
		JComboBox cmbTrangThai = new JComboBox();
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"", "Trống", "Đang sử dụng"}));
		cmbTrangThai.setBounds(1357, 81, 170, 30);
		add(cmbTrangThai);
		
		listPhong = phongDAO.getAllTablePhong();
		listLoaiPhong = loaiPhongDAO.getAllTableLoaiPhong();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();
		
		updateTableData_Phong();
		
		// Thêm sự kiện cho các nút
		btnChonKhachHang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnLuuPhieuDatPhong.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThemPhieuDatPhong.addActionListener(this);
		btnHuyPhieu.addActionListener(this);
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
		else if (o.equals(btnTimKiem)) {
			
		}
		else if (o.equals(btnLuuPhieuDatPhong)) {
			
		}
		else if (o.equals(btnXoaTrang)) {
			
		}
		else if (o.equals(btnThemPhieuDatPhong)) {
			
		}
		else if (o.equals(btnHuyPhieu)) {
			
		}
	}
	
	// Cập nhật dữ liệu phòng vào trong bảng chọn phòng
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
