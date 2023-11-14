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
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import dao.LoaiPhong_DAO;
import entity.DichVu;
import entity.LoaiDichVu;

public class CapNhatDichVu_GUI extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDichVu, txtTenDichVu, txtDonGia, txtSoLuongTon;
	private JTable tblDichVu;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	private JScrollPane scrDichVu;
	private JComboBox<String> cmbLoai;
	private DichVu_DAO dv_dao;
	private DefaultTableModel model;
	private ArrayList<DichVu> dsDV;
	private ArrayList<LoaiDichVu> dsLDV;
	private LoaiDichVu_DAO ldv_dao;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public CapNhatDichVu_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ldv_dao = new LoaiDichVu_DAO();
		dv_dao = new DichVu_DAO();
		setSize(1600, 1050);
		setLayout(null);
		JLabel lblCapNhatDichVu = new JLabel("Cập nhật dịch vụ");
		lblCapNhatDichVu.setBounds(603, 11, 393, 49);
		lblCapNhatDichVu.setFont(new Font("SansSerif", Font.BOLD, 40));
		add(lblCapNhatDichVu);

		JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
		lblMaDichVu.setBounds(166, 140, 132, 62);
		lblMaDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblMaDichVu);

		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ:");
		lblLoaiDichVu.setBounds(166, 213, 132, 62);
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblLoaiDichVu);

		JLabel lblTenDichVu = new JLabel("Tên dịch vụ:");
		lblTenDichVu.setBounds(726, 140, 173, 62);
		lblTenDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblTenDichVu);

		JLabel lblSoLuongTon = new JLabel("Số lượng tồn:");
		lblSoLuongTon.setBounds(726, 213, 132, 62);
		lblSoLuongTon.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblSoLuongTon);

		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setBounds(166, 297, 132, 62);
		lblDonGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblDonGia);

		txtMaDichVu = new JTextField();
		txtMaDichVu.setBounds(301, 155, 285, 32);
		txtMaDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		add(txtMaDichVu);
		txtMaDichVu.setColumns(10);

		txtTenDichVu = new JTextField();
		txtTenDichVu.setBounds(904, 155, 341, 32);
		txtTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenDichVu.setColumns(10);
		add(txtTenDichVu);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(301, 312, 233, 32);
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtDonGia.setColumns(10);
		add(txtDonGia);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setBounds(904, 228, 233, 32);
		txtSoLuongTon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtSoLuongTon.setColumns(10);
		add(txtSoLuongTon);

		cmbLoai = new JComboBox<String>();
		cmbLoai.setBounds(301, 228, 233, 32);
		cmbLoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		add(cmbLoai);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(259, 393, 121, 44);
		btnThem.setFocusable(false);
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(523, 393, 121, 44);
		btnSua.setFocusable(false);
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setBounds(778, 393, 121, 44);
		btnXoa.setFocusable(false);
		add(btnXoa);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(1015, 393, 170, 44);
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);

		// set color btn
		btnThem.setBackground(new Color(217, 217, 217));
		btnSua.setBackground(new Color(217, 217, 217));
		btnXoa.setBackground(new Color(217, 217, 217));
		btnXoaTrang.setBackground(new Color(217, 217, 217));

		// table
		String[] header = { "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá" };
		model = new DefaultTableModel(header, 0);

		add(scrDichVu = new JScrollPane(tblDichVu = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		tblDichVu.setForeground(Color.BLACK);
		scrDichVu.setEnabled(false);
		tblDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrDichVu.setBounds(102, 521, 1387, 500);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		tblDichVu.setRowHeight(50);
		loadDanhSachDichVu();
		docDuLieuLenTable();
		addGiaTriCmbLoai();

		// them su kien
		tblDichVu.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	public void loadDanhSachDichVu() throws Exception {
		dsDV = dv_dao.getAllTableDichVu();
	}

	public void docDuLieuLenTable() throws Exception {
		model.setRowCount(0);
		for (DichVu dv : dsDV) {
			model.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(), dv.getLoaiDichVu().getTenLoaiDichVu(),
					dv.getSoLuongTon(), dv.getDonGia() });
		}
	}

	public void xoaTrang() {
		txtMaDichVu.setText("");
		txtMaDichVu.requestFocus();
		txtTenDichVu.setText("");
		txtDonGia.setText("");
		txtSoLuongTon.setText("");
		cmbLoai.setSelectedIndex(0);
	}

	public void addGiaTriCmbLoai() throws Exception {
		dsLDV = ldv_dao.getCmbLoaiDichVu();
		cmbLoai.removeAllItems();
		for (LoaiDichVu ldv : dsLDV) {
			cmbLoai.addItem(ldv.getTenLoaiDichVu());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			if (validData()) {
				String maDichVu = txtMaDichVu.getText();
				String tenDichVu = txtTenDichVu.getText();
				String tenLoaiDichVu = cmbLoai.getSelectedItem().toString();
				double donGia = Double.parseDouble(txtDonGia.getText().trim());
				int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
				String tmp = "";
				try {
					LoaiDichVu ldv = new LoaiDichVu(tmp, tenLoaiDichVu);
					DichVu dv = new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
					if (dv_dao.themDichVu(dv)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						loadDanhSachDichVu();
						docDuLieuLenTable();
					} else {
						int chkMa = 0; // Nếu bằng 0 thì chưa có mã để cập nhật
						int chkTen = 0; // Nếu >0 thì tên dịch vụ này đã có. Không thể cập nhật được
						loadDanhSachDichVu();
						for (DichVu dichVu : dsDV) {
							String ma = dichVu.getMaDichVu().trim();
							String ten = dichVu.getTenDichVu().trim();
							if (maDichVu.equalsIgnoreCase(ma))
								chkMa = 1;
							if (tenDichVu.equalsIgnoreCase(ten))
								chkTen = 1;
						}
						if (chkMa > 0) {
							JOptionPane.showMessageDialog(this, "Mã dịch vụ không được trùng.\nCập nhật thất bại!");
							txtMaDichVu.requestFocus();
							txtMaDichVu.selectAll();
						} else if (chkTen > 0) {
							JOptionPane.showMessageDialog(this, "Tên loại dịch vụ này đã có.\nCập nhật thất bại!");
							txtTenDichVu.requestFocus();
							txtTenDichVu.selectAll();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnSua)) {
			if (validData()) {
				String maDichVu = txtMaDichVu.getText();
				String tenDichVu = txtTenDichVu.getText();
				String tenLoaiDichVu = cmbLoai.getSelectedItem().toString();
				double donGia = Double.parseDouble(txtDonGia.getText().trim());
				int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
				String tmp = "";
				try {
					LoaiDichVu ldv = new LoaiDichVu(tmp, tenLoaiDichVu);
					DichVu dv = new DichVu(maDichVu, tenDichVu, soLuongTon, ldv, donGia);
					if (dv_dao.capNhatDichVu(dv)) {
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						loadDanhSachDichVu();
						docDuLieuLenTable();
					} else {
						int chkMa = 0; // Nếu bằng 0 thì chưa có mã để cập nhật
						int chkTen = 0; // Nếu >0 thì tên dịch vụ này đã có. Không thể cập nhật được
						loadDanhSachDichVu();
						for (DichVu dichVu : dsDV) {
							String ma = dichVu.getMaDichVu().trim();
							String ten = dichVu.getTenDichVu().trim();
							if (maDichVu.equalsIgnoreCase(ma))
								chkMa = 1;
							if (tenDichVu.equalsIgnoreCase(ten))
								chkTen = 1;
						}
						if (chkMa == 0) {
							JOptionPane.showMessageDialog(this, "Mã dịch vụ không tìm thấy.\nCập nhật thất bại");
							txtMaDichVu.requestFocus();
							txtMaDichVu.selectAll();
						} else if (chkTen == 1) {
							JOptionPane.showMessageDialog(this, "Tên loại dịch vụ này đã có.\nCập nhật thất bại");
							txtTenDichVu.requestFocus();
							txtTenDichVu.selectAll();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXoa)) {
			int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
					JOptionPane.YES_NO_OPTION);
			if (act == JOptionPane.YES_OPTION) {
				String maDichVu = txtMaDichVu.getText().trim();
				if (dv_dao.xoaDichVuByMa(maDichVu)) {
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					try {
						loadDanhSachDichVu();
						docDuLieuLenTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Xóa thất bại!\nVui lòng chọn dịch vụ cần xóa.");
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
			cmbLoai.setSelectedItem(model.getValueAt(row, 2).toString());
			txtSoLuongTon.setText(model.getValueAt(row, 3).toString());
			txtDonGia.setText(model.getValueAt(row, 4).toString());
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
		String maDichVu = txtMaDichVu.getText().trim();
		String tenDichVu = txtTenDichVu.getText().trim();
		String tenLoaiDichVu = cmbLoai.getSelectedItem().toString();
		String donGia = txtDonGia.getText().trim();
		String soLuongTon = txtSoLuongTon.getText().trim();
		if (maDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã dịch vụ không được rỗng");
			txtMaDichVu.requestFocus();
			return false;
		} else if (!maDichVu.matches("DV\\d{3}")) {
			JOptionPane.showMessageDialog(this,
					"mã dịch vụ phải gồm 5 kí tự bắt đầu bằng DV và 3 kí tự số \n VD: DV001");
			txtMaDichVu.selectAll();
			txtMaDichVu.requestFocus();
			return false;
		} else if (tenDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên dịch vụ không được rỗng");
			txtTenDichVu.requestFocus();
			return false;
		} else if (!tenDichVu.matches("^[a-zA-Z0-9\u00C0-\u1EF9 ]+$")) {
			txtTenDichVu.selectAll();
			txtTenDichVu.requestFocus();
			JOptionPane.showMessageDialog(this, "Tên dịch vụ  không được chứa kí tự đặc biệt");
			return false;
		} else if (!donGia.matches("^[1-9]\\d*(\\.\\d+)?$")) {
			JOptionPane.showMessageDialog(this, "Đơn giá chỉ được nhập số dương");
			txtDonGia.requestFocus();
			txtDonGia.selectAll();
			return false;
		} else if (!soLuongTon.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(this, "Số lượng tồn chỉ được nhập số dương");
			txtSoLuongTon.requestFocus();
			txtSoLuongTon.selectAll();
			return false;
		} else if (tenLoaiDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng không chọn loại dịch vụ rỗng!");
			return false;
		}
		return true;
	}
}
