package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import connectDB.ConnectDB;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;

public class TimKiemPhong_GUI extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhong, txtTenPhong, txtGiaPhong;
	private JTable tblTimKiemPhong;
	private JButton btnTim, btnTaiLai;
	private DefaultTableModel model;
	private ArrayList<Phong> dsPhong;
	private ArrayList<LoaiPhong> dsLoaiPhong;
	private Phong_DAO phong_DAO;
	private LoaiPhong_DAO lp_dao;
	private JComboBox<String> cmbXepTrangThai, cmbLoaiPhong;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public TimKiemPhong_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		phong_DAO = new Phong_DAO();
		lp_dao = new LoaiPhong_DAO();
		setSize(1600, 1050);
		setLayout(null);
		JLabel lblTmKimPhng = new JLabel("Tìm kiếm phòng");
		lblTmKimPhng.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTmKimPhng.setBounds(603, 11, 393, 49);
		add(lblTmKimPhng);

		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaPhong.setBounds(265, 132, 110, 48);
		add(lblMaPhong);

		JLabel lblGia = new JLabel("Đơn giá:");
		lblGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGia.setBounds(737, 132, 88, 48);
		add(lblGia);

		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenPhong.setBounds(265, 213, 110, 48);
		add(lblTenPhong);

		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoaiPhong.setBounds(737, 213, 118, 48);
		add(lblLoaiPhong);

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaPhong.setLabelFor(txtMaPhong);
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(387, 140, 251, 32);
		add(txtMaPhong);

		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenPhong.setLabelFor(txtTenPhong);
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(387, 219, 251, 36);
		add(txtTenPhong);

		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGia.setLabelFor(txtGiaPhong);
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(854, 140, 227, 32);
		add(txtGiaPhong);

		cmbLoaiPhong = new JComboBox<String>();
		lblLoaiPhong.setLabelFor(cmbLoaiPhong);
		cmbLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(854, 221, 227, 32);
		add(cmbLoaiPhong);
		addGiaTriVaoCmb();

		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTim.setBounds(481, 283, 137, 48);
		btnTim.setFocusable(false);
		btnTim.setBackground(new Color(217, 217, 217));
		add(btnTim);

		btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTaiLai.setBounds(861, 283, 175, 48);
		btnTaiLai.setFocusable(false);
		btnTaiLai.setBackground(new Color(217, 217, 217));
		add(btnTaiLai);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTrangThai.setBounds(1136, 132, 110, 48);
		add(lblTrangThai);

		cmbXepTrangThai = new JComboBox<String>();
		cmbXepTrangThai.addItem("");
		cmbXepTrangThai.addItem("Trống");
		cmbXepTrangThai.addItem("Đang sử dụng");
		cmbXepTrangThai.setFocusable(false);
		lblTrangThai.setLabelFor(cmbXepTrangThai);
		cmbXepTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbXepTrangThai.setBounds(1242, 140, 160, 32);
		add(cmbXepTrangThai);

		// table
		JScrollPane scrTimPhong = new JScrollPane();
		String[] header = { "Mã Phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Trạng thái" };
		model = new DefaultTableModel(header, 0);

		add(scrTimPhong = new JScrollPane(tblTimKiemPhong = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrTimPhong.setBounds(189, 373, 1290, 635);
		scrTimPhong.setBackground(new Color(120, 255, 239));
		scrTimPhong.getViewport().setBackground(Color.WHITE);
		scrTimPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblTimKiemPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblTimKiemPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(scrTimPhong);
		loadDanhSachPhong();
		docLieuLenTable();
		btnTim.addActionListener(this);
		btnTaiLai.addActionListener(this);
		tblTimKiemPhong.addMouseListener(this);

	}

	private void loadDanhSachPhong() throws Exception {
		dsPhong = phong_DAO.getAllTablePhong();
	}

	private void docLieuLenTable() throws Exception {
		model.setRowCount(0);
		for (Phong p : dsPhong) {
			model.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
					p.getDonGia(), p.getTrangThai() });
		}
	}

	private void xoaTrang() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtGiaPhong.setText("");
		cmbXepTrangThai.setSelectedIndex(0);
		cmbLoaiPhong.setSelectedIndex(0);
	}

	private void addGiaTriVaoCmb() throws Exception {
		dsLoaiPhong = lp_dao.getcmbLoaiPhong();
		cmbLoaiPhong.removeAllItems();
		cmbLoaiPhong.addItem("");
		for (LoaiPhong lp : dsLoaiPhong) {
			cmbLoaiPhong.addItem(lp.getTenLoaiPhong());
		}
	}

	private void them() throws Exception {
		String ma = txtMaPhong.getText().trim();
		String ten = txtTenPhong.getText().trim();
		String stt = cmbXepTrangThai.getSelectedItem().toString();
		String loai = cmbLoaiPhong.getSelectedItem().toString();
		String giaText = txtGiaPhong.getText().trim();
		double gia = 0.0;
		if (!giaText.equals(""))
			gia = Double.parseDouble(giaText);
		model.getDataVector().removeAllElements();
		if (!ma.equals("") && !ten.equals("") && !stt.equals("") && !loai.equals("") && !giaText.equals("")) { // tìm by
																												// all
			dsPhong = phong_DAO.getListPhongByAll(ma, ten, gia, loai, stt);
		} else if (!ma.equals("") && !ten.equals("") && stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Mã
																														// tên
																														// giá
																														// loại
			dsPhong = phong_DAO.getListPhongByMaTenGiaVaLoai(ma, ten, gia, loai);
		} else if (!ma.equals("") && !ten.equals("") && !stt.equals("") && loai.equals("") && !giaText.equals("")) { // Mã
																														// tên
																														// giá
																														// trạng
																														// thái
			dsPhong = phong_DAO.getListPhongByMaTenGiaVaTrangThai(ma, ten, gia, stt);
		} else if (!ma.equals("") && !ten.equals("") && !stt.equals("") && !loai.equals("") && giaText.equals("")) { // Mã
																														// tên
																														// loại
																														// trạng
																														// thái
			dsPhong = phong_DAO.getListPhongByMaTenLoaiVaTrangThai(ma, ten, stt, loai);
		} else if (!ma.equals("") && ten.equals("") && !stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Mã
																														// giá
																														// loại
																														// trạng
																														// thái
			dsPhong = phong_DAO.getListPhongByMaGiaLoaiVaTrangThai(ma, loai, gia, stt);
		} else if (ma.equals("") && !ten.equals("") && !stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Tên
																														// giá
																														// loại
																														// trạng
																														// thái
			dsPhong = phong_DAO.getListPhongByTenGiaLoaiVaTrangThai(loai, ten, gia, stt);
		} else if (!ma.equals("") && !ten.equals("") && stt.equals("") && loai.equals("") && !giaText.equals("")) { // Mã
																													// tên
																													// giá
			dsPhong = phong_DAO.getListPhongByMaTenvaGia(ma, ten, gia);
		} else if (!ma.equals("") && !ten.equals("") && stt.equals("") && !loai.equals("") && giaText.equals("")) { // Mã
																													// tên
																													// loại
			dsPhong = phong_DAO.getListPhongByMaTenVaLoai(ma, loai, ten);
		} else if (!ma.equals("") && !ten.equals("") && !stt.equals("") && loai.equals("") && giaText.equals("")) { // Mã
																													// tên
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByMaTenVaTrangThai(ma, stt, ten);
		} else if (!ma.equals("") && ten.equals("") && stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Mã
																													// giá
																													// loại
			dsPhong = phong_DAO.getListPhongByMaGiaVaLoai(ma, loai, gia);
		} else if (!ma.equals("") && ten.equals("") && !stt.equals("") && loai.equals("") && !giaText.equals("")) { // Mã
																													// giá
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByMaGiaVaTrangThai(ma, stt, gia);
		} else if (!ma.equals("") && ten.equals("") && !stt.equals("") && !loai.equals("") && giaText.equals("")) { // Mã
																													// loại
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByMaLoaiVaTrangThai(ma, stt, loai);
		} else if (ma.equals("") && !ten.equals("") && stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Tên
																													// giá
																													// loại
			dsPhong = phong_DAO.getListPhongByTenGiaVaLoai(ten, loai, gia);
		} else if (ma.equals("") && !ten.equals("") && !stt.equals("") && loai.equals("") && !giaText.equals("")) { // Tên
																													// giá
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByTenGiaVaTrangThai(ten, stt, gia);
		} else if (ma.equals("") && !ten.equals("") && !stt.equals("") && !loai.equals("") && giaText.equals("")) { // Tên
																													// loại
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByTenLoaiVaTrangThai(ten, stt, loai);
		} else if (ma.equals("") && !ten.equals("") && stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Giá
																													// loại
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByGiaLoaiVaTrangThai(gia, stt, loai);
		} else if (!ma.equals("") && !ten.equals("") && stt.equals("") && loai.equals("") && giaText.equals("")) { // Mã
																													// và
																													// tên
			dsPhong = phong_DAO.getListPhongByMavaTen(ma, ten);
		} else if (!ma.equals("") && ten.equals("") && stt.equals("") && loai.equals("") && !giaText.equals("")) { // Mã
																													// và
																													// giá
			dsPhong = phong_DAO.getListPhongByMavaGia(ma, gia);
		} else if (!ma.equals("") && ten.equals("") && stt.equals("") && !loai.equals("") && giaText.equals("")) { // Mã
																													// và
																													// loại
			dsPhong = phong_DAO.getListPhongByMavaLoai(ma, loai);
		} else if (!ma.equals("") && ten.equals("") && !stt.equals("") && loai.equals("") && giaText.equals("")) { // Mã
																													// và
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByMaVaTrangThai(stt, ma);
		} else if (ma.equals("") && !ten.equals("") && stt.equals("") && loai.equals("") && !giaText.equals("")) { // Tên
																													// và
																													// giá
			dsPhong = phong_DAO.getListPhongByTenvaGia(ten, gia);
		} else if (ma.equals("") && !ten.equals("") && stt.equals("") && !loai.equals("") && giaText.equals("")) { // Tên
																													// và
																													// loại
			dsPhong = phong_DAO.getListPhongByTenVaLoai(ten, loai);
		} else if (ma.equals("") && !ten.equals("") && !stt.equals("") && loai.equals("") && giaText.equals("")) { // Tên
																													// và
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByTenVaTrangThai(stt, ten);
		} else if (ma.equals("") && ten.equals("") && stt.equals("") && !loai.equals("") && !giaText.equals("")) { // Giá
																													// và
																													// loại
			dsPhong = phong_DAO.getListPhongByGiaVaLoai(loai, gia);
		} else if (ma.equals("") && ten.equals("") && !stt.equals("") && loai.equals("") && !giaText.equals("")) { // Giá
																													// và
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByGiaVaTrangThai(stt, gia);
		} else if (ma.equals("") && ten.equals("") && !stt.equals("") && !loai.equals("") && giaText.equals("")) { // Loại
																													// và
																													// trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByLoaiVaTrangThai(stt, loai);
		} else if (!ma.equals("") && ten.equals("") && stt.equals("") && loai.equals("") && giaText.equals("")) { // Mã
			dsPhong = phong_DAO.getListPhongByMa(ma);
		} else if (ma.equals("") && !ten.equals("") && stt.equals("") && loai.equals("") && giaText.equals("")) { // Tên
			dsPhong = phong_DAO.getListPhongByTen(ten);
		} else if (ma.equals("") && ten.equals("") && stt.equals("") && loai.equals("") && !giaText.equals("")) { // Giá
			dsPhong = phong_DAO.getListPhongByGia(gia);
		} else if (ma.equals("") && ten.equals("") && stt.equals("") && !loai.equals("") && giaText.equals("")) { // Loại
			dsPhong = phong_DAO.getListPhongByLoai(loai);
		} else if (ma.equals("") && ten.equals("") && !stt.equals("") && loai.equals("") && giaText.equals("")) { // Trạng
																													// thái
			dsPhong = phong_DAO.getListPhongByTrangThai(stt);
		}
		// Thực hiện update table
		// Nếu danh sách dịch vụ rỗng thì thông báo
		// Nếu không rỗng thì đọc dữ liệu lên table
		if (dsPhong == null || dsPhong.size() <= 0) {
			JOptionPane.showMessageDialog(this, "Không tim thấy");
			try {
				loadDanhSachPhong();
				docLieuLenTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				docLieuLenTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTaiLai)) {
			xoaTrang();
			try {
				loadDanhSachPhong();
				docLieuLenTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (o.equals(btnTim)) {
			String giaText = txtGiaPhong.getText().trim();
			if (giaText.equals(""))
				try {
					them();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			else if (validData())
				try {
					them();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblTimKiemPhong)) {
			int row = tblTimKiemPhong.getSelectedRow();
			txtMaPhong.setText(model.getValueAt(row, 0).toString());
			txtTenPhong.setText(model.getValueAt(row, 1).toString());
			cmbLoaiPhong.setSelectedItem(model.getValueAt(row, 2));
			txtGiaPhong.setText(model.getValueAt(row, 3) + "");
			cmbXepTrangThai.setSelectedItem(model.getValueAt(row, 4));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	private boolean validData() {
		String giaText = txtGiaPhong.getText().trim();
		if (!giaText.matches("^[1-9]\\d*(\\.\\d+)?$")) {
			JOptionPane.showMessageDialog(this, "Giá phải là số dương");
			txtGiaPhong.selectAll();
			txtGiaPhong.requestFocus();
			return false;
		}
		return true;
	}
}
