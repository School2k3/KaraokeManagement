package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;

public class TimKiemDichVu_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDichVu, txtTenDichVu, txtGiaDichVu;
	private JTable tblDichVu;
	private JButton btnTimKiem, btnTaiLai;
	private JScrollPane srcTimDichVu;
	private DefaultTableModel model;
	private JComboBox<String> cmbLoaiDichVu;
	private DichVu_DAO dv_dao;
	private ArrayList<DichVu> dsDichVu;
	private ArrayList<LoaiDichVu> dsLDV;
	private LoaiDichVu_DAO ldv_dao;

	public TimKiemDichVu_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dv_dao = new DichVu_DAO();
		ldv_dao = new LoaiDichVu_DAO();
		setSize(1600, 1050);
		setLayout(null);
		JLabel lblTitle = new JLabel("Tìm kiếm dịch vụ");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitle.setBounds(603, 11, 393, 49);
		add(lblTitle);

		JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
		lblMaDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaDichVu.setBounds(223, 137, 131, 48);
		add(lblMaDichVu);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDonGia.setBounds(780, 137, 131, 48);
		add(lblDonGia);

		JLabel lblTenDichVu = new JLabel("Tên dịch vụ:");
		lblTenDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenDichVu.setBounds(223, 229, 131, 48);
		add(lblTenDichVu);

		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ:");
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoaiDichVu.setBounds(780, 229, 131, 48);
		add(lblLoaiDichVu);

		txtMaDichVu = new JTextField();
		txtMaDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaDichVu.setLabelFor(txtMaDichVu);
		txtMaDichVu.setBounds(364, 153, 242, 32);
		add(txtMaDichVu);
		txtMaDichVu.setColumns(10);

		txtTenDichVu = new JTextField();
		txtTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTenDichVu.setLabelFor(txtTenDichVu);
		txtTenDichVu.setColumns(10);
		txtTenDichVu.setBounds(364, 237, 242, 32);
		add(txtTenDichVu);

		txtGiaDichVu = new JTextField();
		txtGiaDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDonGia.setLabelFor(txtGiaDichVu);
		txtGiaDichVu.setColumns(10);
		txtGiaDichVu.setBounds(893, 145, 242, 32);
		add(txtGiaDichVu);

		cmbLoaiDichVu = new JComboBox<String>();
		lblLoaiDichVu.setLabelFor(cmbLoaiDichVu);
		cmbLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoaiDichVu.setBounds(921, 237, 214, 32);
		add(cmbLoaiDichVu);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(217, 217, 217));
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(422, 327, 173, 48);
		btnTimKiem.setFocusable(false);
		add(btnTimKiem);

		btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTaiLai.setBounds(744, 327, 192, 48);
		btnTaiLai.setBackground(new Color(217, 217, 217));
		btnTaiLai.setFocusable(false);
		add(btnTaiLai);

		srcTimDichVu = new JScrollPane();
		tblDichVu = new JTable();
		tblDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		srcTimDichVu.setViewportView(tblDichVu);
		// table
		String[] header = { "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá" };
		model = new DefaultTableModel(header, 0);
		tblDichVu.setModel(model);
		add(srcTimDichVu = new JScrollPane(tblDichVu = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		tblDichVu.setFont(new Font("SansSerif", Font.PLAIN, 18));
		srcTimDichVu.setEnabled(false);
		srcTimDichVu.setBounds(143, 407, 1274, 632);
		srcTimDichVu.setBackground(new Color(120, 255, 239));
		srcTimDichVu.getViewport().setBackground(Color.WHITE);
		tblDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		tblDichVu.setRowHeight(50);
		add(srcTimDichVu);
		loadDanhSachDichVu();
		docLieuLenTable();
		addGiaTriCmbLoai();

		btnTimKiem.addActionListener(this);
		btnTaiLai.addActionListener(this);
		tblDichVu.addMouseListener(this);

	}

	public void addGiaTriCmbLoai() throws Exception {
		dsLDV = ldv_dao.getCmbLoaiDichVu();
		cmbLoaiDichVu.addItem("");
		for (LoaiDichVu loaiDichVu : dsLDV) {
			cmbLoaiDichVu.addItem(loaiDichVu.getTenLoaiDichVu());
		}
	}

	/**
	 * Hàm tải dữ liệu từ database
	 */
	private void loadDanhSachDichVu() throws Exception {
		dsDichVu = dv_dao.getAllTableDichVu();
	}

	private void docLieuLenTable() throws Exception {
		model.setRowCount(0);
		for (DichVu dv : dsDichVu) {
			model.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(), dv.getLoaiDichVu().getTenLoaiDichVu(),
					dv.getSoLuongTon(), dv.getDonGia() });
		}
	}

	private void xoaTrang() {
		txtMaDichVu.setText("");
		txtTenDichVu.setText("");
		;
		txtGiaDichVu.setText("");
		cmbLoaiDichVu.setSelectedIndex(0);
	}

	private void them() throws Exception {
		String ma = txtMaDichVu.getText().trim();
		String ten = txtTenDichVu.getText().trim();
		String loai = cmbLoaiDichVu.getSelectedItem().toString();
		String giaTxt = txtGiaDichVu.getText().trim();
		Double gia = 0.0;
		if (!giaTxt.equals(""))
			gia = Double.parseDouble(giaTxt);
		model.getDataVector().removeAllElements();
		if (!ma.equals("") && ten.equals("") && giaTxt.equals("") && loai.isEmpty()) { // Mã
			dsDichVu = dv_dao.getListDichVuByMa(ma);
		} else if (ma.equals("") && !ten.equals("") && giaTxt.equals("") && loai.isEmpty()) { // Tên
			dsDichVu = dv_dao.getListDichVuByTen(ten);
		} else if (ma.equals("") && ten.equals("") && !giaTxt.equals("") && loai.isEmpty()) { // Đơn giá
			dsDichVu = dv_dao.getListDichVuByGia(gia);
		} else if (ma.equals("") && ten.equals("") && giaTxt.equals("") && !loai.isEmpty()) { // Loại
			dsDichVu = dv_dao.getListDichVuByLoai(loai);
			// Tìm theo 2 tiêu chí
		} else if (!ma.equals("") && ten.equals("") && giaTxt.equals("") && !loai.isEmpty()) { // Mã và loại
			dsDichVu = dv_dao.getListDichVuByMaVaLoai(loai, ma);
		} else if (!ma.equals("") && !ten.equals("") && giaTxt.equals("") && loai.isEmpty()) { // Mã tên
			dsDichVu = dv_dao.getListDichVuByMaVaTen(ten, ma);
		} else if (!ma.equals("") && ten.equals("") && !giaTxt.equals("") && loai.isEmpty()) { // Mã Giá
			dsDichVu = dv_dao.getListDichVuByMaVaGia(gia, ma);
		} else if (ma.equals("") && !ten.equals("") && !giaTxt.equals("") && loai.isEmpty()) { // Tên và giá
			dsDichVu = dv_dao.getListDichVuByTenVaGia(ten, gia);
		} else if (ma.equals("") && !ten.equals("") && giaTxt.equals("") && !loai.isEmpty()) { // Tên và loại
			dsDichVu = dv_dao.getListDichVuByTenVaLoai(loai, ten);
		} else if (ma.equals("") && ten.equals("") && !giaTxt.equals("") && !loai.isEmpty()) { // Giá và loại
			dsDichVu = dv_dao.getListDichVuByGiaVaLoai(gia, loai);

			// Tìm theo 3 tiêu chí
		} else if (!ma.equals("") && !ten.equals("") && giaTxt.equals("") && !loai.isEmpty()) { // Mã tên loại
			dsDichVu = dv_dao.getListDichVuByMaTenVaLoai(loai, ma, ten);
		} else if (!ma.equals("") && !ten.equals("") && !giaTxt.equals("") && loai.isEmpty()) { // Mã tên giá
			dsDichVu = dv_dao.getListDichVuByMaTenVaGia(gia, ma, ten);
		} else if (!ma.equals("") && ten.equals("") && !giaTxt.equals("") && !loai.isEmpty()) { // Mã giá loại
			dsDichVu = dv_dao.getListDichVuByMaLoaiVaGia(gia, ma, loai);
		} else if (ma.equals("") && !ten.equals("") && !giaTxt.equals("") && !loai.isEmpty()) { // Tên giá loại
			dsDichVu = dv_dao.getListDichVuByTenGiaVaLoai(gia, ten, loai);
			// Tìm theo tất cả tiêu chí
		} else if (!ma.equals("") && !ten.equals("") && !giaTxt.equals("") && !loai.isEmpty()) { // 4 tiêu chí
			dsDichVu = dv_dao.getListDichVuByAll(gia, ten, loai, ma);
		}
		// Thực hiện update table
		// Nếu danh sách dịch vụ rỗng thì thông báo
		// Nếu không rỗng thì đọc dữ liệu lên table
		if (dsDichVu == null || dsDichVu.size() <= 0) {
			JOptionPane.showMessageDialog(this, "Không tim thấy");
			try {
				loadDanhSachDichVu();
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
				cmbLoaiDichVu.removeAllItems();
				addGiaTriCmbLoai();
				loadDanhSachDichVu();
				docLieuLenTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimKiem)) {
			String giaTxt = txtGiaDichVu.getText().trim();
			if (giaTxt.equals("")) {
				try {
					them();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				if (validData())
					try {
						them();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblDichVu)) {
			int row = tblDichVu.getSelectedRow();
			txtMaDichVu.setText(model.getValueAt(row, 0).toString());
			txtTenDichVu.setText(model.getValueAt(row, 1).toString());
			cmbLoaiDichVu.setSelectedItem(model.getValueAt(row, 2));
			txtGiaDichVu.setText(model.getValueAt(row, 4).toString());
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
		String gia = txtGiaDichVu.getText().trim();
		if (!gia.matches("^[1-9]\\d*(\\.\\d+)?$")) {
			JOptionPane.showMessageDialog(this, "Đơn giá chỉ được nhập số dương");
			txtGiaDichVu.requestFocus();
			txtGiaDichVu.selectAll();
			return false;
		}
		return true;

	}
}
