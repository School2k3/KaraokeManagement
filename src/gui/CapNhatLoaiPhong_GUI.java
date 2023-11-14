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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import connectDB.ConnectDB;
import dao.LoaiPhong_DAO;
import entity.LoaiPhong;
import entity.Phong;

public class CapNhatLoaiPhong_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaLoaiPhong, txtTenLoaiPhong;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	private JTable tblLoaiPhong;
	private DefaultTableModel model;
	private ArrayList<LoaiPhong> dsLoaiPhong;
	private LoaiPhong_DAO lp_dao;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CapNhatLoaiPhong_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		lp_dao = new LoaiPhong_DAO();
		setSize(1600, 1050);
		setLayout(null);
		JLabel lblCapNhatLoaiPhong = new JLabel("Cập nhật loại phòng");
		lblCapNhatLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblCapNhatLoaiPhong.setBounds(603, 11, 393, 49);
		add(lblCapNhatLoaiPhong);

		JLabel lblMaLoaiPhong = new JLabel("Mã loại phòng:");
		lblMaLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaLoaiPhong.setBounds(344, 159, 170, 62);
		add(lblMaLoaiPhong);

		JLabel lblTenLoaiPhong = new JLabel("Tên loại phòng:");
		lblTenLoaiPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTenLoaiPhong.setBounds(880, 159, 156, 62);
		add(lblTenLoaiPhong);

		txtMaLoaiPhong = new JTextField();
		txtMaLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtMaLoaiPhong.setColumns(10);
		txtMaLoaiPhong.setBounds(496, 174, 273, 32);
		add(txtMaLoaiPhong);

		txtTenLoaiPhong = new JTextField();
		txtTenLoaiPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenLoaiPhong.setColumns(10);
		txtTenLoaiPhong.setBounds(1033, 174, 233, 32);
		add(txtTenLoaiPhong);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(333, 290, 133, 49);
		btnThem.setFocusable(false);
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(626, 292, 133, 44);
		btnSua.setFocusable(false);
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoa.setBounds(894, 292, 121, 44);
		btnXoa.setFocusable(false);
		add(btnXoa);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXoaTrang.setBounds(1153, 292, 170, 44);
		btnXoaTrang.setFocusable(false);
		add(btnXoaTrang);

		// set color button
		btnThem.setBackground(new Color(217, 217, 217));
		btnSua.setBackground(new Color(217, 217, 217));
		btnXoa.setBackground(new Color(217, 217, 217));
		btnXoaTrang.setBackground(new Color(217, 217, 217));

		// table
		JScrollPane scrLoaiPhong = new JScrollPane();
		String[] header = { "Mã loại Phòng", "Tên loại phòng" };
		model = new DefaultTableModel(header, 0);

		add(scrLoaiPhong = new JScrollPane(tblLoaiPhong = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		scrLoaiPhong.setBounds(222, 359, 1264, 641);
		scrLoaiPhong.setBackground(new Color(120, 255, 239));
		scrLoaiPhong.getViewport().setBackground(Color.WHITE);
		scrLoaiPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblLoaiPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblLoaiPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		add(scrLoaiPhong);
		loadDanhSachLoaiPhong();
		docDuLieuVaoTable();

		// them su kien
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		tblLoaiPhong.addMouseListener(this);
	}

	/**
	 * xoa trang cac input
	 */
	private void xoaTrang() {
		txtMaLoaiPhong.setText("");
		txtTenLoaiPhong.setText("");
	}

	private void loadDanhSachLoaiPhong() throws Exception {
		dsLoaiPhong = lp_dao.getAllTableLoaiPhong();
	}

	/**
	 * Hàm đọc dữ liệu vào database addRow: Thêm các dòng dữ liệu từ database vào
	 * table
	 * 
	 * @throws Exception
	 */
	private void docDuLieuVaoTable() throws Exception {
		model.setRowCount(0);
		for (LoaiPhong lp : dsLoaiPhong) {
			model.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String ma = txtMaLoaiPhong.getText().trim();
		String ten = txtTenLoaiPhong.getText().trim();
		if (o.equals(btnThem)) {
			if (validData()) {
				try {
					LoaiPhong lp = new LoaiPhong(ma, ten);
					if (lp_dao.themLoaiPhong(lp)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						loadDanhSachLoaiPhong();
						docDuLieuVaoTable();
						xoaTrang();
					} else {
						int chkMa = 0; // Nếu bằng 0 thì chưa có mã để cập nhật
						int chkTen = 0; // Nếu >0 thì tên dịch vụ này đã có. Không thể cập nhật được
						loadDanhSachLoaiPhong();
						for (LoaiPhong loaiPhong : dsLoaiPhong) {
							String tmpMa = loaiPhong.getMaLoaiPhong().trim();
							String tmpTen = loaiPhong.getTenLoaiPhong().trim();
							if (ma.equalsIgnoreCase(tmpMa))
								chkMa = 1;
							if (ten.equalsIgnoreCase(tmpTen))
								chkTen = 1;
						}
						if (chkMa > 0) {
							JOptionPane.showMessageDialog(this, "Mã loại phòng không được trùng.\nThêm thất bại");
							txtMaLoaiPhong.requestFocus();
							txtMaLoaiPhong.selectAll();
						} else if (chkTen > 0) {
							JOptionPane.showMessageDialog(this, "Tên loại phòng này đã có.\nnThêm thất bại");
							txtTenLoaiPhong.requestFocus();
							txtTenLoaiPhong.selectAll();
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
					LoaiPhong lp = new LoaiPhong(ma, ten);
					if (lp_dao.capNhatLoaiPhong(lp)) {
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						docDuLieuVaoTable();
						xoaTrang();
					} else {
						int chkMa = 0; // Nếu bằng 0 thì chưa có mã để cập nhật
						int chkTen = 0; // Nếu >0 thì tên dịch vụ này đã có. Không thể cập nhật được
						loadDanhSachLoaiPhong();
						for (LoaiPhong loaiPhong : dsLoaiPhong) {
							String tmpMa = loaiPhong.getMaLoaiPhong().trim();
							String tmpTen = loaiPhong.getTenLoaiPhong().trim();
							if (ma.equalsIgnoreCase(tmpMa))
								chkMa = 1;
							if (ten.equalsIgnoreCase(tmpTen))
								chkTen = 1;
						}
						if (chkMa == 00) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy mã cần cập nhật.\nCập nhật thất bại");
							txtMaLoaiPhong.requestFocus();
							txtMaLoaiPhong.selectAll();
						} else if (chkTen > 0) {
							JOptionPane.showMessageDialog(this, "Tên loại phòng này đã có.\nCập nhật thất bại");
							txtTenLoaiPhong.requestFocus();
							txtTenLoaiPhong.selectAll();
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
					if (lp_dao.xoaLoaiPhongByMa(ma)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiPhong();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiPhong lp : dsLoaiPhong) {
							String s = lp.getMaLoaiPhong();
							if (ma.equalsIgnoreCase(s))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại phòng đã được sử dụng.");
						else
							JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy mã dịch vụ cần xóa!");
						xoaTrang();
					}
				}
			} else if (ma.equals("") && !ten.equals("")) {
				int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (act == JOptionPane.YES_OPTION) {
					if (lp_dao.xoaLoaiPhongByTen(ten)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiPhong();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiPhong lp : dsLoaiPhong) {
							String s = lp.getTenLoaiPhong();
							if (ten.equalsIgnoreCase(s))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại phòng đã được sử dụng");
						else
							JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy tên phòng cần xóa");
						xoaTrang();

					}
				}
			} else {
				int act = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (act == JOptionPane.YES_OPTION) {
					if (lp_dao.xoaLoaiPhongByMaVaTen(ma, ten)) {
						try {
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							loadDanhSachLoaiPhong();
							docDuLieuVaoTable();
							xoaTrang();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						int n = 0;
						for (LoaiPhong lp : dsLoaiPhong) {
							String s = lp.getMaLoaiPhong();
							if (ma.equalsIgnoreCase(s))
								n += 1;
						}
						if (n > 0)
							JOptionPane.showMessageDialog(this, "Loại phòng đã được sử dụng");
						else
							JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy phòng cần xóa");
						xoaTrang();
					}
				}
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblLoaiPhong)) {
			int row = tblLoaiPhong.getSelectedRow();
			txtMaLoaiPhong.setText(model.getValueAt(row, 0).toString());
			txtTenLoaiPhong.setText(model.getValueAt(row, 1).toString());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private boolean validData() {
		String ma = txtMaLoaiPhong.getText().trim();
		String ten = txtTenLoaiPhong.getText().trim();
		if (ma.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã loại phòng không được rỗng");
			txtMaLoaiPhong.requestFocus();
			return false;
		} else if (!ma.matches("LP\\d{1}")) {
			JOptionPane.showMessageDialog(this, "Mã loại phòng gồm 3 kí tự bắt đầu bằng LP và 1 kí tự số \n VD: LP1");
			txtMaLoaiPhong.requestFocus();
			txtMaLoaiPhong.selectAll();
			return false;
		} else if (ten.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên loại phòng không được rỗng");
			txtTenLoaiPhong.requestFocus();
			return false;
		} else if (!ten.matches("^[a-zA-Z0-9\u00C0-\u1EF9 ]+$")) {
			JOptionPane.showMessageDialog(this, "Tên loại phòng không được chứa kí tự đặc biệt");
			txtTenLoaiPhong.requestFocus();
			txtTenLoaiPhong.selectAll();
			return false;
		}
		return true;
	}
}
