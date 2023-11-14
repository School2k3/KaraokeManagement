package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ScrollPaneConstants;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;
import entity.Phong;

public class CapNhatLoaiDichVu_GUI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaLoaiDichVu, txtTenLoaiDichVu;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	private JTable tblLoaiDichVu;
	private DefaultTableModel model;
	private ArrayList<LoaiDichVu> dsLoaiDichVu;
	private LoaiDichVu_DAO ldv_dao;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CapNhatLoaiDichVu_GUI() throws Exception {
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
		setSize(1600, 1050);
		setLayout(null);
		JLabel lblTitle = new JLabel("Cập nhật loại dịch vụ");
		lblTitle.setBounds(603, 11, 393, 49);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		add(lblTitle);

		JLabel lblMaDichVu = new JLabel("Mã loại dịch vụ:");
		lblMaDichVu.setBounds(202, 131, 157, 48);
		lblMaDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblMaDichVu);

		txtMaLoaiDichVu = new JTextField();
		txtMaLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtMaLoaiDichVu.setBounds(364, 139, 228, 32);
		lblMaDichVu.setLabelFor(txtMaLoaiDichVu);
		txtMaLoaiDichVu.setColumns(10);
		add(txtMaLoaiDichVu);

		JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ:");
		lblLoaiDichVu.setBounds(818, 131, 131, 48);
		lblLoaiDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblLoaiDichVu);

		txtTenLoaiDichVu = new JTextField();
		txtTenLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenLoaiDichVu.setBounds(960, 139, 242, 32);
		lblLoaiDichVu.setLabelFor(txtTenLoaiDichVu);
		txtTenLoaiDichVu.setColumns(10);
		add(txtTenLoaiDichVu);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(419, 249, 131, 49);
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setFocusable(false);
		btnThem.setBackground(new Color(217, 217, 217));
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(958, 249, 131, 49);
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setFocusable(false);
		btnXoa.setBackground(new Color(217, 217, 217));
		add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(671, 251, 131, 49);
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setFocusable(false);
		btnSua.setBackground(new Color(217, 217, 217));
		add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(1179, 249, 163, 49);
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setFocusable(false);
		btnXoaTrang.setBackground(new Color(217, 217, 217));
		add(btnXoaTrang);

		// table
		JScrollPane scrLoaiDichVu = new JScrollPane();
		String[] header = { "Mã loại dịch vụ", "Tên loại dịch vụ" };
		model = new DefaultTableModel(header, 0);

		add(scrLoaiDichVu = new JScrollPane(tblLoaiDichVu = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		tblLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 17));
		scrLoaiDichVu.setBounds(175, 337, 1291, 674);
		scrLoaiDichVu.setBackground(new Color(120, 255, 239));
		scrLoaiDichVu.getViewport().setBackground(Color.WHITE);
		scrLoaiDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblLoaiDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblLoaiDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		tblLoaiDichVu.setRowHeight(50);
		loadDanhSachLoaiDichVu();
		docDuLieuVaoTable();
		// them su kien
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		tblLoaiDichVu.addMouseListener(this);
	}

	/**
	 * xoa trang cac input
	 */
	private void xoaTrang() {
		txtMaLoaiDichVu.setText("");
		txtMaLoaiDichVu.requestFocus();
		txtTenLoaiDichVu.setText("");
	}

	private void loadDanhSachLoaiDichVu() throws Exception {
		dsLoaiDichVu = ldv_dao.getAllTableLoaiDichVu();
	}

	/**
	 * Hàm đọc dữ liệu vào database addRow: Thêm các dòng dữ liệu từ database vào
	 * table
	 * 
	 * @throws Exception
	 */
	private void docDuLieuVaoTable() throws Exception {
		model.setRowCount(0);
		for (LoaiDichVu ldv : dsLoaiDichVu) {
			model.addRow(new Object[] { ldv.getMaLoaiDichVu(), ldv.getTenLoaiDichVu() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String ma = txtMaLoaiDichVu.getText().trim();
		String ten = txtTenLoaiDichVu.getText().trim();
		if (o.equals(btnThem)) {
			if (validData()) {
				try {
					LoaiDichVu ldv = new LoaiDichVu(ma, ten);
					if (ldv_dao.themLoaiDichVu(ldv)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						loadDanhSachLoaiDichVu();
						docDuLieuVaoTable();
						xoaTrang();
					} else {
						int chkMa = 0; // >0 thì đã có mã nên không thêm được
						int chkTen = 0; // ==1 thì đã có loại dịch vụ này
						loadDanhSachLoaiDichVu();
						for (LoaiDichVu ldv1 : dsLoaiDichVu) {
							String tmpMa = ldv1.getMaLoaiDichVu();
							String tmpTen = ldv1.getTenLoaiDichVu();
							if (ma.equalsIgnoreCase(tmpMa))
								chkMa = 1;
							if (ten.equalsIgnoreCase(tmpTen))
								chkTen = 1;
						}
						if (chkMa > 0) {
							txtMaLoaiDichVu.requestFocus();
							txtMaLoaiDichVu.selectAll();
							JOptionPane.showMessageDialog(this, "Mã loại dịch vụ không được trùng!\nThêm thất bại");
						} else if (chkTen > 0) {
							txtTenLoaiDichVu.requestFocus();
							txtTenLoaiDichVu.selectAll();
							JOptionPane.showMessageDialog(this, "Tên loại dịch vụ này đã có!\nVui lòng đổi tên khác.");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnSua)) {
			if (validData()) {
				try {
					LoaiDichVu ldv = new LoaiDichVu(ma, ten);
					if (ldv_dao.capNhatLoaiDichVu(ldv)) {
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						loadDanhSachLoaiDichVu();
						docDuLieuVaoTable();
						xoaTrang();
					} else {
						int chkMa = 0; // >0 thì đã có mã nên không thêm được
						int chkTen = 0; // ==1 thì đã có loại dịch vụ này
						loadDanhSachLoaiDichVu();
						for (LoaiDichVu ldv1 : dsLoaiDichVu) {
							String tmpMa = ldv1.getMaLoaiDichVu();
							String tmpTen = ldv1.getTenLoaiDichVu();
							if (ma.equalsIgnoreCase(tmpMa))
								chkMa = 1;
							if (ten.equalsIgnoreCase(tmpTen))
								chkTen = 1;
						}
						if (chkMa == 0) {
							txtMaLoaiDichVu.requestFocus();
							txtMaLoaiDichVu.selectAll();
							JOptionPane.showMessageDialog(this, "Không tìm thấy mã!\nCập nhật thất bại");
						} else if (chkTen > 0) {
							txtTenLoaiDichVu.requestFocus();
							txtTenLoaiDichVu.selectAll();
							JOptionPane.showMessageDialog(this, "Tên loại dịch vụ này đã có!\nVui lòng đổi tên khác.");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXoa)) {
			if (ma.equals("") && ten.equals("")) {
				JOptionPane.showMessageDialog(this, "Nhập dữ liệu cần xóa");
			} else if (!ma.equals("") && ten.equals("")) {
				int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (act == JOptionPane.YES_OPTION) {
					if (ldv_dao.xoaLoaiDichVuByMa(ma)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiDichVu();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiDichVu ldv : dsLoaiDichVu) {
							String s = ldv.getMaLoaiDichVu();
							if (ma.equalsIgnoreCase(s))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại dịch vụ đã được sử dụng");
						else
							JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy mã dịch vụ cần xóa");
						xoaTrang();

					}
				}
			} else if (ma.equals("") && !ten.equals("")) {
				int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (act == JOptionPane.YES_OPTION) {
					if (ldv_dao.xoaLoaiDichVuByTen(ten)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiDichVu();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiDichVu ldv : dsLoaiDichVu) {
							String s = ldv.getTenLoaiDichVu();
							if (ten.equalsIgnoreCase(s))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại dịch vụ đã được sử dụng");
						else
							JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy tên dịch vụ cần xóa");
						xoaTrang();
					}
				}
			} else {
				int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (act == JOptionPane.YES_OPTION) {
					if (ldv_dao.xoaLoaiDichVuByMaVaTen(ma, ten)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiDichVu();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiDichVu ldv : dsLoaiDichVu) {
							String s = ldv.getMaLoaiDichVu();
							String tmp = ldv.getTenLoaiDichVu();
							if (ma.equalsIgnoreCase(s) && ten.equals(tmp))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại dịch vụ đã được sử dụng");
						else
							JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ cần xóa!");
						xoaTrang();
					}
				}
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblLoaiDichVu)) {
			int row = tblLoaiDichVu.getSelectedRow();
			txtMaLoaiDichVu.setText(model.getValueAt(row, 0).toString());
			txtTenLoaiDichVu.setText(model.getValueAt(row, 1).toString());
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean validData() {
		String maLoaiDichVu = txtMaLoaiDichVu.getText().trim();
		String tenLoaiDichVu = txtTenLoaiDichVu.getText().trim();
		if (maLoaiDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã loại dịch vụ không được rỗng");
			txtMaLoaiDichVu.requestFocus();
			return false;
		} else if (!maLoaiDichVu.matches("LDV\\d{3}")) {
			JOptionPane.showMessageDialog(this,
					"Mã loại dịch vụ gồm 6 kí tự bắt đầu bằng LDV và 3 kí tự số \n VD: LDV001");
			txtMaLoaiDichVu.requestFocus();
			txtMaLoaiDichVu.selectAll();
			return false;
		} else if (tenLoaiDichVu.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên loại dịch vụ không được rỗng");
			txtTenLoaiDichVu.requestFocus();
			return false;
		} else if (!tenLoaiDichVu.matches("^[a-zA-Z0-9\u00C0-\u1EF9 ]+$")) {
			JOptionPane.showMessageDialog(this, "Tên loại dịch vụ không được chứa kí tự đặc biệt");
			txtTenLoaiDichVu.requestFocus();
			txtTenLoaiDichVu.selectAll();
			return false;
		}
		return true;
	}
}
