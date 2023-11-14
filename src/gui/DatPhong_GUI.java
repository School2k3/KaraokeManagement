package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import connectDB.ConnectDB;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiTietPhieuDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import gui.DatPhong_GUI.DateLabelFormatter;

import java.awt.Color;

public class DatPhong_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	public JLabel lblHienThiTenKhachHang, lblHienThiSoDienThoai;
	private JTextField txtTimKiem;
	private JTable tblChonPhong, tblPhongDat;
	private JTextField txtNgayDat;
	private JComboBox cmbTrangThai, cmbGio, cmbPhut;
	private DefaultTableModel modelPhong, modelPhongDat;
	private JButton btnTimKiem, btnThemPhieuDatPhong, btnLuuPhieuDatPhong;
	private DecimalFormat df;
	private NhanVien nhanVien;
	private Phong_DAO phongDAO;
	private LoaiPhong_DAO loaiPhongDAO;
	private PhieuDatPhong_DAO phieuDatPhongDAO;
	private ChiTietPhieuDatPhong_DAO chiTietPhieuDatPhongDAO;
	private KhachHang_DAO khachHangDAO;
	private List<Phong> listPhong;
	private List<LoaiPhong> listLoaiPhong;
	private List<PhieuDatPhong> listPhieuDatPhong;
	private List<ChiTietPhieuDatPhong> listChiTietPhieuDatPhong;
	private List<KhachHang> listKhachHang;
	private TimKiemKhachHang_GUI pnltimKiemKH;
	private SqlDateModel modelDate;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public DatPhong_GUI(NhanVien nhanVien) throws Exception {
		this.nhanVien = nhanVien;
		// Kết nối với ConnectDB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		df = new DecimalFormat("#,##0.00");
		
		// Khởi tạo các biến kiểu DAO
		phongDAO = new Phong_DAO();
		loaiPhongDAO = new LoaiPhong_DAO();
		phieuDatPhongDAO = new PhieuDatPhong_DAO();
		chiTietPhieuDatPhongDAO = new ChiTietPhieuDatPhong_DAO();
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
		setPlaceholder(txtTimKiem, "Nhập tên phòng hoặc loại phòng cần tìm");

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
		String[] colHeaderPhong = { "Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái" };
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		
		// Tạo 1 biến thuộc kiểu JDatePickerImpl thuộc thư viện JDatePicker
		modelDate = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelDate, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.setTextEditable(false);
		datePicker.setBackground(Color.WHITE);
		datePicker.setShowYearButtons(true);
		datePicker.setBounds(244, 430, 240, 29);
		this.add(datePicker);

		JLabel lblGioDat = new JLabel("Giờ đặt");
		lblGioDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGioDat.setBounds(614, 429, 93, 30);
		add(lblGioDat);

		cmbGio = new JComboBox();
		cmbGio.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
		cmbGio.setBounds(729, 429, 45, 30);
		cmbGio.setBackground(new Color(217, 217, 217));
		add(cmbGio);

		cmbPhut = new JComboBox();
		cmbPhut.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
				"45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		cmbPhut.setBounds(802, 429, 45, 30);
		cmbPhut.setBackground(new Color(217, 217, 217));
		add(cmbPhut);

		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(784, 434, 17, 20);
		add(lblTime);

		btnThemPhieuDatPhong = new JButton("Thêm vào phiếu đặt phòng");
		btnThemPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnThemPhieuDatPhong.setBounds(1180, 429, 303, 31);
		btnThemPhieuDatPhong.setBackground(new Color(217, 217, 217));
		btnThemPhieuDatPhong.setFocusable(false);
		add(btnThemPhieuDatPhong);

		JPanel pnlPhieuDatPhong = new JPanel();
		pnlPhieuDatPhong.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlPhieuDatPhong.setBounds(61, 470, 1496, 474);
		add(pnlPhieuDatPhong);
		pnlPhieuDatPhong.setLayout(null);
		
		// Phần phiếu đặt phòng
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
		String[] colHeaderDatPhong = { "Mã phòng", "Tên phòng", "Ngày đặt", "Thời gian đặt" };
		modelPhongDat = new DefaultTableModel(colHeaderDatPhong, 0);
		tblPhongDat = new JTable(modelPhongDat);
		JScrollPane scrPhongDat = new JScrollPane(tblPhongDat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 1496, 328);
		scrPhongDat.setBackground(new Color(120, 255, 239));
		scrPhongDat.getViewport().setBackground(Color.WHITE);
		scrPhongDat.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblPhongDat.getTableHeader().setBackground(new Color(120, 255, 239));
		tblPhongDat.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhongDat.add(scrPhongDat);

		lblHienThiTenKhachHang = new JLabel("");
		lblHienThiTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHienThiTenKhachHang.setBounds(376, 56, 270, 26);
		pnlPhieuDatPhong.add(lblHienThiTenKhachHang);

		lblHienThiSoDienThoai = new JLabel("");
		lblHienThiSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHienThiSoDienThoai.setBounds(975, 56, 195, 26);
		pnlPhieuDatPhong.add(lblHienThiSoDienThoai);

		btnLuuPhieuDatPhong = new JButton("Lưu phiếu đặt phòng");
		btnLuuPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLuuPhieuDatPhong.setBounds(1243, 955, 303, 31);
		btnLuuPhieuDatPhong.setBackground(new Color(217, 217, 217));
		btnLuuPhieuDatPhong.setFocusable(false);
		add(btnLuuPhieuDatPhong);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "", "Trống", "Đặt trước", "Đang sử dụng" }));
		cmbTrangThai.setBounds(1357, 81, 170, 30);
		add(cmbTrangThai);
		
		// Gán các giá trị List cho các hàm xử lý bên class DAO
		listPhong = phongDAO.getAllTablePhong();
		listLoaiPhong = loaiPhongDAO.getAllTableLoaiPhong();
		listPhieuDatPhong = phieuDatPhongDAO.getAllPhieuDatPhong();
		listChiTietPhieuDatPhong = chiTietPhieuDatPhongDAO.getAllChiTietPhieuDatPhong();
		listKhachHang = khachHangDAO.getAllTableKhachHang();

		// Đưa dữ liệu lên các bảng
		updateTableData_Phong(listPhong);

		// Thêm sự kiện cho các nút
		btnTimKiem.addActionListener(this);
		btnLuuPhieuDatPhong.addActionListener(this);
		btnThemPhieuDatPhong.addActionListener(this);
		cmbTrangThai.addActionListener(this);

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
		if (o.equals(btnTimKiem)) { // Tìm kiếm tên phòng hoặc loại phòng
			String chuoiTim = "";
			// Xét trường hợp txtTimKiem khác rỗng
			if (!txtTimKiem.getText().trim().equals("")) {
				chuoiTim += "tenPhong like N'%" + txtTimKiem.getText() + "%' or lp.tenLoaiPhong like N'%" + txtTimKiem.getText() + "%'";
				List<Phong> dsPhongByTenHoacLoaiPhong;
				try {
					// Lấy danh sách tên phòng hoặc loại phòng đang tìm
					dsPhongByTenHoacLoaiPhong = phongDAO.getAllTablePhongByTenPhongOrTenLoaiPhong(chuoiTim);
					if (dsPhongByTenHoacLoaiPhong.size() != 0) {
						modelPhong.getDataVector().removeAllElements();
						updateTableData_Phong(dsPhongByTenHoacLoaiPhong);
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy tên phòng hoặc loại phòng!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} else if (o.equals(btnLuuPhieuDatPhong)) { // Lưu phiếu đặt phòng
			int rowCount = tblPhongDat.getRowCount();
			String tenkh = lblHienThiTenKhachHang.getText();
			String makh = "";
			String manv = nhanVien.getMaNhanVien();
			String tt = "Chờ nhận phòng";
			Timestamp time = null;
			for (KhachHang kh : listKhachHang) {
				if (kh.getHoTenKhachHang().equals(tenkh)) {
					makh = kh.getMaKhachHang();
					break;
				}
			}
			// Lặp qua từng dòng của JTable
			for (int row = 0; row < rowCount; row++) {
				String maphieu = phieuDatPhongDAO.phatSinhMaPhieuDat();
				String maphong = tblPhongDat.getValueAt(row, 0).toString();
				Timestamp ngaylap = new Timestamp(System.currentTimeMillis());
				String tgdat = tblPhongDat.getValueAt(row, 2).toString() + " "
						+ tblPhongDat.getValueAt(row, 3).toString();
				time = getSimpleDateFormat(tgdat);
				try {
					KhachHang kh = new KhachHang(makh);
					NhanVien nv = new NhanVien(manv);
					PhieuDatPhong pdp = new PhieuDatPhong(maphieu, kh, nv, ngaylap, tt);
					if (phieuDatPhongDAO.insertPhieuDatPhong(pdp)) {
						Phong p = new Phong(maphong);
						PhieuDatPhong pdp2 = new PhieuDatPhong(maphieu);
						ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong(pdp2, p, time);
						if (chiTietPhieuDatPhongDAO.insertChiTietPhieuDatPhong(ctpdp)) {
							JOptionPane.showMessageDialog(this, "Bạn đã thêm phiếu đặt phòng thành công!");
							phongDAO.updateTrangThai(maphong, "Đặt trước");
							modelPhong.getDataVector().removeAllElements();
							listPhong = phongDAO.getAllTablePhong();
							updateTableData_Phong(listPhong);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (o.equals(btnThemPhieuDatPhong)) { // Thêm phiếu đặt phòng vào thông tin đặt phòng
			// Xét trường hợp nếu khách hàng chưa được chọn
			if (lblHienThiTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng cần thêm vào hóa đơn!");
			} else {
				int row = tblChonPhong.getSelectedRow();
				String trangthai = tblChonPhong.getValueAt(row, 4).toString();
				// Xét trường hợp phòng chưa được chọn
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng!");
				} else if (datePicker.equals(null)) { // Xét trường hợp ngày đặt chưa được chọn
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày đặt!");
				} else {
					try {
						String maphong = tblChonPhong.getValueAt(row, 0).toString();
						String tenphong = tblChonPhong.getValueAt(row, 1).toString();
						String datetime = datePicker.getModel().getValue() + " " + cmbGio.getSelectedItem().toString()
								+ ":" + cmbPhut.getSelectedItem().toString() + ":00";
						Timestamp thoigiandat = getSimpleDateFormat(datetime);
						// Khởi tạo biến và gán giá trị mặc định là ngày hiện tại
						Timestamp ngaydatcu = new Timestamp(System.currentTimeMillis());
						// Lấy ra danh sách tất cả các phòng có ngày đặt mới nhất
						List<ChiTietPhieuDatPhong> listCTPDP = chiTietPhieuDatPhongDAO
								.getAllChiTietPhieuDatPhongByMaPhong(maphong);

						// Xét trường hợp chọn ngày đặt phòng trước ngày hiện tại
						if (thoigiandat.before(new Timestamp(System.currentTimeMillis()))) {
							JOptionPane.showMessageDialog(this, "Ngày đặt phòng phải bằng hoặc sau ngày hiện tại!");
						}
						// Xét trường hợp phòng đang trong trạng thái "Đang sử dụng" hoặc "Đặt trước"
						else if (trangthai.equals("Đang sử dụng") || trangthai.equals("Đặt trước")) {
							for (ChiTietPhieuDatPhong ctpdp : listCTPDP) {
								ngaydatcu = ctpdp.getThoiGianDat();
							}
							// Tạo một đối tượng Calendar và đặt giá trị bằng Timestamp ban đầu
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(ngaydatcu.getTime());

							// Thêm 4 giờ vào thời gian của Calendar
							calendar.add(Calendar.HOUR_OF_DAY, 4);

							// Lấy thời gian mới từ Calendar và đặt lại vào Timestamp
							Timestamp ngaydatcu_2 = new Timestamp(calendar.getTimeInMillis());
							if (ngaydatcu_2.after(thoigiandat)) {
								JOptionPane.showMessageDialog(this,
										"Phòng này trước đó đã có người đặt hoặc đang sử dụng!");
							} else {
								// Thêm phòng vào tblPhongDat
								for (Phong p : listPhong) {
									if (p.getTenPhong().equals(tenphong)) {
										modelPhongDat.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(),
												formatDate(thoigiandat), formatTime(thoigiandat) });	
									}
								}
								JOptionPane.showMessageDialog(this, "Thêm phòng vào phiếu đặt phòng thành công!");
								listPhong = phongDAO.getAllTablePhong();
							}
						} else { // Trường hợp còn lại: phòng "Trống"
							// Thêm phòng vào tblPhongDat
							for (Phong p : listPhong) {
								if (p.getTenPhong().equals(tenphong)) {
									modelPhongDat.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(),
											formatDate(thoigiandat), formatTime(thoigiandat) });
								}
							}
							JOptionPane.showMessageDialog(this, "Thêm phòng vào phiếu đặt phòng thành công!");
							listPhong = phongDAO.getAllTablePhong();
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}  else if (o.equals(cmbTrangThai)) { // Lọc các trạng thái của phòng
			String ttTim = cmbTrangThai.getSelectedItem().toString();
			if (!ttTim.equals("")) {
				modelPhong.getDataVector().removeAllElements();
				try {
					listPhong = phongDAO.getAllTablePhongByTrangThai(ttTim);
					updateTableData_Phong(listPhong);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					listPhong = phongDAO.getAllTablePhong();
					updateTableData_Phong(listPhong);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
	
	/**
	 * Định dạng ngày của JDatePickerImpl
	 */
	public class DateLabelFormatter extends AbstractFormatter {
		private static final long serialVersionUID = -566062085698006350L;
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException { // Chuyển từ dạng chuỗi sang giá trị
			return dateFormatter.parseObject(text);
		}
		@Override
		public String valueToString(Object value) throws ParseException { // Chuyển từ giá trị sang dạng chuỗi
			if (value != null) {
				Calendar cal = (Calendar) value;

				return dateFormatter.format(cal.getTime());
			}
			return "";
		}
	}

	// Cập nhật dữ liệu phòng vào trong bảng chọn phòng
	private void updateTableData_Phong(List<Phong> list) {
		for (Phong p : list) {
			String malp = p.getLoaiPhong().getMaLoaiPhong();
			String tenlp = "";
			for (LoaiPhong lp : listLoaiPhong) {
				if (lp.getMaLoaiPhong().equals(malp)) {
					tenlp = lp.getTenLoaiPhong();
					break;
				}
			}
			modelPhong.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), tenlp, df.format(p.getDonGia()), p.getTrangThai() });
		}
	}

	// Thêm chữ gợi ý vào trong JTextField
	private void setPlaceholder(JTextField textField, String placeholder) {
		textField.setForeground(Color.GRAY);
		textField.setText(placeholder);

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText(placeholder);
				}
			}
		});
	}

	// Tạo 1 hàm dùng để chuyển giá trị ngày giờ từ kiểu String sang Timestamp
	private Timestamp getSimpleDateFormat(String datetime) {
		datetime = datePicker.getModel().getValue() + " " + cmbGio.getSelectedItem().toString() + ":"
				+ cmbPhut.getSelectedItem().toString() + ":00";
		// Định dạng cho SimpleDateFormat
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate = null;
		Timestamp time = null;
		try {
			// Chuyển đổi chuỗi thành đối tượng Date
			parsedDate = dateFormat.parse(datetime);

			// Tạo đối tượng Timestamp từ đối tượng Date
			time = new Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return time;
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
}
