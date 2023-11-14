package gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import dao.PhieuDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;
import entity.PhieuDichVu;
import gui.DatPhong_GUI.DateLabelFormatter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class DatDichVu_GUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JButton btnTimKiem, btnThemPhieuDatDichVu, btnLamMoi, btnLuuPhieuDatDichVu,btnHuyPhieu;
	private DefaultTableModel modelDichVu, modelDatDichVu;
	private JTable tblChonDichVu, tblDatDichVu;
	private ArrayList<DichVu> dsDichVu;
	private DichVu_DAO dv_dao;
	private JComboBox<String> cmbLoai, cmbGio, cmbPhut;
	private LoaiDichVu_DAO ldv_dao;
	private ArrayList<LoaiDichVu> dsLoaiDichVu;
	private ArrayList<PhieuDichVu> dsPhieuDichVu;
	private PhieuDichVu_DAO pdv_dao;
	private TimKiemKhachHang_GUI pnltimKiemKH;
	private SqlDateModel modelDate;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public DatDichVu_GUI() throws Exception {
		setSize(1600,1050);
		setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		dv_dao= new DichVu_DAO();
		ldv_dao= new LoaiDichVu_DAO();
		JLabel lblTitle = new JLabel("Đặt dịch vụ");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitle.setBounds(681, 11, 223, 61);
		add(lblTitle);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 768, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		setPlaceholder(txtTimKiem, "Nhập tên dịch vụ cần tìm");

		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(908, 73, 211, 45);
		btnTimKiem.setFocusable(false);
		btnTimKiem.setBackground(new Color(217, 217, 217));
		add(btnTimKiem);
		
		JLabel lblChonDichVu = new JLabel("Chọn dịch vụ");
		lblChonDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonDichVu.setBounds(61, 132, 123, 30);
		add(lblChonDichVu);
		
		JPanel pnlTableDichVu = new JPanel();
		pnlTableDichVu.setBounds(243, 129, 1303, 289);
		String[] headerDichVu = {"Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá"};
		modelDichVu = new DefaultTableModel(headerDichVu, 0);
		pnlTableDichVu.setLayout(null);
		tblChonDichVu = new JTable(modelDichVu);
		JScrollPane scrDichVu = new JScrollPane(tblChonDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDichVu.setBounds(0, 0, 1303, 289);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
		tblChonDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblChonDichVu.setRowHeight(50);
		pnlTableDichVu.add(scrDichVu);
		add(pnlTableDichVu);
		
		
		btnThemPhieuDatDichVu = new JButton("Thêm vào phiếu đặt dịch vụ");
		btnThemPhieuDatDichVu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnThemPhieuDatDichVu.setBounds(949, 429, 303, 31);
		btnThemPhieuDatDichVu.setFocusable(false);
		btnThemPhieuDatDichVu.setBackground(new Color(217, 217, 217));
		add(btnThemPhieuDatDichVu);
		
		JLabel lblNgayDat = new JLabel("Ngày đặt dịch vụ");
		lblNgayDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNgayDat.setBounds(61, 429, 153, 30);
		add(lblNgayDat);

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
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLamMoi.setBounds(1357, 430, 170, 30);
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBackground(new Color(217, 217, 217));
		add(btnLamMoi);
		
		JPanel pnlPhieuDatDichVu = new JPanel();
		pnlPhieuDatDichVu.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlPhieuDatDichVu.setBounds(61, 470, 1496, 474);
		add(pnlPhieuDatDichVu);
		pnlPhieuDatDichVu.setLayout(null);
		
		JLabel lblPhieuDatDichVu = new JLabel("Phiếu đặt dịch vụ");
		lblPhieuDatDichVu.setBounds(646, 11, 242, 34);
		lblPhieuDatDichVu.setFont(new Font("SansSerif", Font.BOLD, 26));
		pnlPhieuDatDichVu.add(lblPhieuDatDichVu);
		
		JLabel lblTenKhachHang = new JLabel("Khách hàng");
		lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenKhachHang.setBounds(240, 56, 102, 26);
		pnlPhieuDatDichVu.add(lblTenKhachHang);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(813, 56, 114, 26);
		pnlPhieuDatDichVu.add(lblSoDienThoai);
		
		JLabel lblDanhSachDichVu = new JLabel("Danh sách dịch vụ đã đặt");
		lblDanhSachDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDanhSachDichVu.setBounds(10, 97, 236, 26);
		pnlPhieuDatDichVu.add(lblDanhSachDichVu);
		
		JPanel pnlTableDatDichVu = new JPanel();
		pnlTableDatDichVu.setBounds(0, 144, 1496, 328);
		pnlPhieuDatDichVu.add(pnlTableDatDichVu);
		pnlTableDatDichVu.setLayout(null);
		String[] headerDatDichVu = {"Mã dịch vụ", "Tên dịch vụ", "Ngày đặt", "Thời gian đặt"};
		modelDatDichVu = new DefaultTableModel(headerDatDichVu, 0);
		tblDatDichVu = new JTable(modelDatDichVu);
		JScrollPane scrDatDichVu = new JScrollPane(tblDatDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDatDichVu.setBounds(0, 0, 1496, 328);
		pnlTableDatDichVu.add(scrDatDichVu);
		
		btnLuuPhieuDatDichVu = new JButton("Lưu phiếu đặt dịch vụ");
		btnLuuPhieuDatDichVu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLuuPhieuDatDichVu.setBounds(949, 955, 303, 31);
		btnLuuPhieuDatDichVu.setFocusable(false);
		btnLuuPhieuDatDichVu.setBackground(new Color(217, 217, 217));
		add(btnLuuPhieuDatDichVu);
		
		btnHuyPhieu = new JButton("Hủy phiếu");
		btnHuyPhieu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnHuyPhieu.setBounds(1357, 956, 170, 30);
		btnHuyPhieu.setFocusable(false);
		btnHuyPhieu.setBackground(new Color(217, 217, 217));
		add(btnHuyPhieu);
		
		cmbLoai = new JComboBox<String>();
		cmbLoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoai.setFocusable(false);
		cmbLoai.setBounds(1357, 81, 170, 30);
		add(cmbLoai);
		
		//Hàm
		addItemLoaiDichVu(); //Thêm item loại dịch vụ vào cmbLoai
		loadDanhSachDichVu(); //lấy toàn bộ dịch vụ thêm vào danh sách dịch vụ
		docDichVuLenTable(); // đọc dữ liệu từ danh sách dịch vụ lên table 
		
		//
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		cmbLoai.addActionListener(this);
		btnHuyPhieu.addActionListener(this);
		btnLuuPhieuDatDichVu.addActionListener(this);
		btnThemPhieuDatDichVu.addActionListener(this);
		
	}
	// Placeholder cho txt tìm kiếm
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
	private void addItemLoaiDichVu() throws Exception{
		dsLoaiDichVu= ldv_dao.getCmbLoaiDichVu();
		cmbLoai.removeAllItems();
		cmbLoai.addItem("");
		for (LoaiDichVu ldv : dsLoaiDichVu) {
			cmbLoai.addItem(ldv.getTenLoaiDichVu());
		}
	}
	private void loadDanhSachDichVu() throws Exception{
		dsDichVu= dv_dao.getAllTableDichVu();
	}
	private void docDichVuLenTable(){
		modelDichVu.setRowCount(0);
		for (DichVu dv : dsDichVu) {
			double donGiaTMP = dv.getDonGia();
            DecimalFormat decimalFormat = new DecimalFormat("#,###.## VND");
            String donGiaString = decimalFormat.format(donGiaTMP);
			modelDichVu.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(),dv.getLoaiDichVu().getTenLoaiDichVu(), dv.getSoLuongTon(), donGiaString });
		}
	}
	private void lamMoi() throws Exception{
		txtTimKiem.setText(null);
		cmbLoai.setSelectedIndex(0);
		cmbGio.setSelectedIndex(0);
		cmbPhut.setSelectedIndex(0);
		loadDanhSachDichVu();
		docDichVuLenTable();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if(o.equals(btnLamMoi)){
			try {
				lamMoi();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (o.equals(cmbLoai)) {
			String ten= txtTimKiem.getText().trim();
			String loai= cmbLoai.getSelectedItem().toString();
			if(!loai.equals("") && !ten.equals("")){
				modelDichVu.getDataVector().removeAllElements();
				try {
					dsDichVu= dv_dao.getListDichVuByTenVaLoai(loai, ten);
					docDichVuLenTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if (!loai.equals("") && ten.equals("")) {
				modelDichVu.getDataVector().removeAllElements();
				try {
					dsDichVu= dv_dao.getListDichVuByLoai(loai);
					docDichVuLenTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					loadDanhSachDichVu();
					docDichVuLenTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if (o.equals(btnTimKiem)) {
			String ten= txtTimKiem.getText().trim();
			String loai= cmbLoai.getSelectedItem().toString();
			if(ten.equals("")){
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên dịch vụ!");
				txtTimKiem.requestFocus();
			}else if(!ten.equals("") && loai.equals("")){
				try {
					dsDichVu= dv_dao.getListDichVuByTen(ten);
					if(dsDichVu== null || dsDichVu.size()<=0){
						JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ");
						txtTimKiem.requestFocus();
						txtTimKiem.selectAll();
					}else {
						docDichVuLenTable();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else {
				try {
					dsDichVu= dv_dao.getListDichVuByTenVaLoai(loai, ten);
					if(dsDichVu== null || dsDichVu.size()<=0){
						JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ");
						txtTimKiem.requestFocus();
						txtTimKiem.selectAll();
					}else {
						docDichVuLenTable();
					}
				} catch (Exception e1) {
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
}
