package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiTietPhieuDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Frm_NhanPhong extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlFull;
	private JTextField txtTimKiemKhachHang;
	private DefaultTableModel modelPhieuDatPhong;
	public JTable tblPhieuDatPhong;
	private JButton btnTimKiem, btnNhanPhong, btnQuayLai;
	private Phong_DAO phongDAO;
	private PhieuDatPhong_DAO phieuDatPhongDAO;
	private ChiTietPhieuDatPhong_DAO ctPhieuDatPhongDAO;
	private KhachHang_DAO khachHangDAO;
	private List<Phong> listPhong;
	private List<PhieuDatPhong> listPhieuDatPhong;
	private List<ChiTietPhieuDatPhong> listCTPhieuDatPhong;
	private List<KhachHang> listKhachHang;
	private String mapd;
	private Timestamp tgdat;

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
	 * 
	 * @throws Exception
	 */
	public Frm_NhanPhong() throws Exception {

		// Kết nối với ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		phongDAO = new Phong_DAO();
		phieuDatPhongDAO = new PhieuDatPhong_DAO();
		ctPhieuDatPhongDAO = new ChiTietPhieuDatPhong_DAO();
		khachHangDAO = new KhachHang_DAO();

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

		// Tạo bảng chứa danh sách phiếu đặt phòng
		JPanel pnlTablePhieuDatPhong = new JPanel();
		pnlTablePhieuDatPhong.setBounds(25, 185, 1126, 480);
		String[] colHeaderPhieuDatPhong = { "STT", "Mã phiếu đặt", "Tên khách hàng", "Số điện thoại", "Tên phòng",
				"Ngày đặt", "Thời gian đặt" };
		modelPhieuDatPhong = new DefaultTableModel(colHeaderPhieuDatPhong, 0);
		pnlTablePhieuDatPhong.setLayout(null);
		getContentPane().add(pnlTablePhieuDatPhong);
		tblPhieuDatPhong = new JTable(modelPhieuDatPhong);
		JScrollPane scrPhieuDatPhong = new JScrollPane(tblPhieuDatPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

		listPhong = phongDAO.getAllTablePhong();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listCTPhieuDatPhong = ctPhieuDatPhongDAO.getAllChiTietPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();

		updateTableData_PhieuDatPhong(listPhieuDatPhong);

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
			String hoTenKhachHang = txtTimKiemKhachHang.getText().trim();
			String chuoiTim = "";	
			if (hoTenKhachHang.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập tên khách hàng cần tìm kiếm!");
			} else {
				chuoiTim += "kh.hoTenKhachHang like '%" + txtTimKiemKhachHang.getText() + "%'";
				List<PhieuDatPhong> dsPhieuDatPhongByHoTenKH;
				try {
					dsPhieuDatPhongByHoTenKH = phieuDatPhongDAO.getAllPhieuDatPhongByTenKhachHang(chuoiTim);
					if (dsPhieuDatPhongByHoTenKH.size() != 0) {
						modelPhieuDatPhong.getDataVector().removeAllElements();
						updateTableData_PhieuDatPhong(dsPhieuDatPhongByHoTenKH);
					}
					else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy tên khách hàng cần tìm!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnNhanPhong)) {
			int row = tblPhieuDatPhong.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn đơn đặt phòng cần được nhận phòng!");
			} else {
				this.dispose();
			}
		} else if (o.equals(btnQuayLai)) {
			this.dispose();
		}
	}

	// Cập nhật dữ liệu phòng vào trong bảng chọn phòng
	private void updateTableData_PhieuDatPhong(List<PhieuDatPhong> list) {
		modelPhieuDatPhong.getDataVector().removeAllElements();
		int count = 1;
		for (PhieuDatPhong pdp : list) {
			if (pdp.getTrangThai().equals("Chờ nhận phòng")) {
				String tenphong = "";
				String ngaydat = "";
				String giodat = "";
				String mactpdp = pdp.getMaPhieuDat();
				for (ChiTietPhieuDatPhong ctpdp : listCTPhieuDatPhong) {
					if (ctpdp.getPhieuDatPhong().getMaPhieuDat().equals(mactpdp)) {
						tenphong = ctpdp.getPhong().getTenPhong();
						ngaydat = formatDate(ctpdp.getThoiGianDat());
						giodat = formatTime(ctpdp.getThoiGianDat());
						tgdat = ctpdp.getThoiGianDat();
						mapd = pdp.getMaPhieuDat();
						break;
					}
				}
				modelPhieuDatPhong
						.addRow(new Object[] { count, pdp.getMaPhieuDat(), pdp.getKhachHang().getHoTenKhachHang(),
								pdp.getKhachHang().getSoDienThoai(), tenphong, ngaydat, giodat });
				count++;
			}
		}
	}

	private String formatDate(Date currentDate) {
		// Định dạng theo mẫu yyyy-MM-dd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Chuyển đổi và in ra ngày giờ theo định dạng
		return sdf.format(currentDate);
	}

	private String formatTime(Date currentDate) {
		// Định dạng theo mẫu HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		// Chuyển đổi và in ra ngày giờ theo định dạng
		return sdf.format(currentDate);
	}

	public JButton getBtnNhanPhong() {
		return btnNhanPhong;
	}

	public String getHoTenKhachHang() {
		int row = tblPhieuDatPhong.getSelectedRow();
		return tblPhieuDatPhong.getValueAt(row, 2).toString();
	}

	public String getSoDienThoai() {
		int row = tblPhieuDatPhong.getSelectedRow();
		return tblPhieuDatPhong.getValueAt(row, 3).toString();
	}
	
	public String getMaPhieuDat() {
		int row = tblPhieuDatPhong.getSelectedRow();
		return tblPhieuDatPhong.getValueAt(row, 1).toString();
	}
	
	public Timestamp getThoiGianDat() {
		return tgdat;
	}

}
