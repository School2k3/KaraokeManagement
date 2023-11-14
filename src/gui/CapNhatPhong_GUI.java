package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.DichVu;
import entity.LoaiPhong;
import entity.Phong;

public class CapNhatPhong_GUI extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhong, txtGiaPhong, txtTenPhong;
	private JTable tblPhong;
	/**
	 * Create the panel.
	 */
	private JButton btnThem, btnSua,btnTaiLai;
	private JScrollPane scrPhong;
	private DefaultTableModel model;
	private ArrayList<Phong> dsPhong;
	private ArrayList<LoaiPhong> dsLP;
	private Phong_DAO Phong_DAO;
	private LoaiPhong_DAO lp_dao;
	private JComboBox<String> cmbLoai;
	private JComboBox<String> cmbTrangThai;
	public CapNhatPhong_GUI() throws Exception {
		/**
		 * Connect to database
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Phong_DAO= new Phong_DAO();
		lp_dao= new LoaiPhong_DAO();
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Cập nhật phòng");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblTitle.setBounds(703, 11, 396, 59);
		add(lblTitle);
		
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMaPhong.setBounds(89, 118, 131, 48);
		add(lblMaPhong);
		
		JLabel lblLoai = new JLabel("Loại phòng:");
		lblLoai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLoai.setBounds(89, 192, 131, 48);
		add(lblLoai);
		
		JLabel lblTen = new JLabel("Tên phòng:");
		lblTen.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTen.setBounds(907, 118, 109, 48);
		add(lblTen);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTrangThai.setBounds(907, 192, 109, 48);
		add(lblTrangThai);
		
		JLabel lblGia = new JLabel("Đơn giá:");
		lblGia.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGia.setBounds(89, 268, 131, 48);
		add(lblGia);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThem.setBounds(251, 363, 203, 66);
		btnThem.setBackground(new Color(217, 217, 217));
		btnThem.setFocusable(false);
		btnThem.setBackground(new Color(217, 217, 217));
		add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSua.setBounds(690, 363, 203, 66);
		btnSua.setBackground(new Color(217, 217, 217));
		btnSua.setFocusable(false);
		add(btnSua);
		
		btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTaiLai.setBounds(1128, 363, 203, 66);
		btnTaiLai.setBackground(new Color(217, 217, 217));
		btnTaiLai.setFocusable(false);
		add(btnTaiLai);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMaPhong.setLabelFor(txtMaPhong);
		txtMaPhong.setColumns(10);
		txtMaPhong.setBounds(230, 126, 440, 32);
		add(txtMaPhong);
		
		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(230, 276, 440, 32);
		add(txtGiaPhong);
		
		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTen.setLabelFor(txtTenPhong);
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(1026, 126, 405, 32);
		add(txtTenPhong);
		
		cmbLoai = new JComboBox<>();
		lblLoai.setLabelFor(cmbLoai);
		cmbLoai.setFocusable(false);
		cmbLoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbLoai.setBounds(230, 200, 270, 32);
		add(cmbLoai);
		
		
		cmbTrangThai = new JComboBox<String>();
		cmbTrangThai.addItem("Trống");
		cmbTrangThai.addItem("Đặt trước");
		cmbTrangThai.addItem("Đang sử dụng");
		cmbTrangThai.setFocusable(false);
		lblTrangThai.setLabelFor(cmbTrangThai);
		cmbTrangThai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cmbTrangThai.setBounds(1026, 200, 270, 32);
		add(cmbTrangThai);
		
		scrPhong = new JScrollPane();
		tblPhong = new JTable();
		tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrPhong.setViewportView(tblPhong);
		//table
		String[] header = { "Mã phòng", "Tên phòng", "Loại phòng",  "Đơn giá","Trạng thái"};
		model = new DefaultTableModel(header, 0);
		tblPhong.setModel(model);
		add(scrPhong = new JScrollPane(tblPhong = new JTable(model),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
					BorderLayout.CENTER);
		tblPhong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrPhong.setEnabled(false);
		scrPhong.setBounds(89, 459, 1439, 520);
		scrPhong.setBackground(new Color(120, 255, 239));
				scrPhong.getViewport().setBackground(Color.WHITE);
		scrPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
		tblPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblPhong.setRowHeight(50);
		add(scrPhong);
		getItemCmbLoaiPhong();
		loadDanhSachPhong();
		docLieuLenTable();
		
		
		//them su kien
				tblPhong.addMouseListener(this);
				btnThem.addActionListener(this);
				btnSua.addActionListener(this);
				btnTaiLai.addActionListener(this);
	}
	private void loadDanhSachPhong() throws Exception{
		dsPhong= Phong_DAO.getAllTablePhong();
	}
	private void docLieuLenTable() throws Exception{
		model.setRowCount(0);
		for (Phong p : dsPhong) {
			double donGiaTMP = p.getDonGia();
            DecimalFormat decimalFormat = new DecimalFormat("#,###.## VND");
            String donGiaString = decimalFormat.format(donGiaTMP);
			model.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), donGiaString, p.getTrangThai()});
		}
	}
	//Chuyển từ #,###.## VND thành chuỗi kí tự số thực 
	private String doiFormatThanhStringSoThuc(String giaTxt){
		try {
            // Loại bỏ các ký tự không phải số từ chuỗi gia
            String giaWithoutCurrency = giaTxt.replaceAll("[^0-9.]+", "");

            // Chuyển chuỗi thành số thực
            return giaWithoutCurrency;
        } catch (NumberFormatException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
            return "";
        }
	}
	private void xoaTrang() throws Exception{
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtGiaPhong.setText("");
		cmbTrangThai.setSelectedIndex(0);
		getItemCmbLoaiPhong();
		cmbLoai.setSelectedIndex(0);
		loadDanhSachPhong();
		docLieuLenTable();
	}
	private void getItemCmbLoaiPhong() throws Exception{
		dsLP= lp_dao.getcmbLoaiPhong();
		cmbLoai.removeAllItems();
		for (LoaiPhong lp : dsLP) {
			cmbLoai.addItem(lp.getTenLoaiPhong());
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o=e.getSource();
		if (o.equals(tblPhong)) {
			int row= tblPhong.getSelectedRow();
			txtMaPhong.setText(model.getValueAt(row, 0).toString());
			txtTenPhong.setText(model.getValueAt(row, 1).toString());
			cmbLoai.setSelectedItem(model.getValueAt(row, 2).toString());
			String giaTmp= doiFormatThanhStringSoThuc(model.getValueAt(row, 3).toString()); //Lấy giá trị col  3 đổi sang chuỗi kiểu String là số
			txtGiaPhong.setText(giaTmp);
			cmbTrangThai.setSelectedItem(model.getValueAt(row, 4).toString());
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if(o.equals(btnTaiLai)){
			try {
				xoaTrang();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (o.equals(btnThem)) {
			if(validData()){
				String ma= txtMaPhong.getText().trim();
				String ten= txtTenPhong.getText().trim();
				String giaText= txtGiaPhong.getText().trim();
				Double donGia= Double.parseDouble(giaText);
				String maLoai= ".";
				String tenLoai= cmbLoai.getSelectedItem().toString();
				String trangThai= cmbTrangThai.getSelectedItem().toString();
				try {
					LoaiPhong lp = new LoaiPhong(maLoai, tenLoai);
					Phong p= new Phong(ma, ten, trangThai, lp, donGia);
					if(Phong_DAO.themPhong(p)){
						JOptionPane.showMessageDialog(this, "Thêm phòng thành công");
						loadDanhSachPhong();
						docLieuLenTable();
					}else {
						int chkMa=0; //Tìm kiếm nếu chkma>0 thì đã có mã không thêm được. Thông báo thêm thất bại.
						int chkTen=0; //Tìm kiếm nếu chkTen>0 thì đã có tên không thêm được. Thông báo thêm thất bại.
						for (Phong phong : dsPhong) {
							String tmpMa= phong.getMaPhong().trim();
							String tmpTen= phong.getTenPhong().trim();
							if(ma.equalsIgnoreCase(tmpMa))
								chkMa=1;
							if(ten.equalsIgnoreCase(tmpTen))
								chkTen=1;
						}
						if(chkMa>0){
							txtMaPhong.selectAll();
							txtMaPhong.requestFocus();
							JOptionPane.showMessageDialog(this, "Mã không được trùng\nThêm thất bại");
						}else if (chkTen>0) {
							JOptionPane.showMessageDialog(this, "Thêm thất bại!\nVui lòng đổi tên khác.");
							txtTenPhong.selectAll();
							txtTenPhong.requestFocus();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if (o.equals(btnSua)) {
			if(validData()){
				String ma= txtMaPhong.getText().trim();
				String ten= txtTenPhong.getText().trim();
				String giaText= txtGiaPhong.getText().trim();
				Double donGia= Double.parseDouble(giaText);
				String maLoai= ".";
				String tenLoai= cmbLoai.getSelectedItem().toString();
				String trangThai= cmbTrangThai.getSelectedItem().toString();
				try {
					LoaiPhong lp = new LoaiPhong(maLoai, tenLoai);
					Phong p= new Phong(ma, ten, trangThai, lp, donGia);
					if(Phong_DAO.capNhatPhong(p)){
						JOptionPane.showMessageDialog(this, "Cập nhật phòng thành công!.");
						loadDanhSachPhong();
						docLieuLenTable();
					}else {
						int chkMa=0; //Nếu bằng  0 thì chưa có mã để cập nhật
						int chkTen=0; //Nếu >0 thì tên dịch vụ này đã có. Không thể cập nhật được
						loadDanhSachPhong();
						for (Phong phong : dsPhong) {
							String tmpMa= phong.getMaPhong().trim();
							String tmpTen= phong.getTenPhong().trim();
							if(ma.equalsIgnoreCase(tmpMa))
								chkMa=1;
							if(ten.equalsIgnoreCase(tmpTen))
								chkTen=1;
						}
						if(chkMa==0){
							JOptionPane.showMessageDialog(this, "Mã phòng không tìm thấy.\nCập nhật thất bại");
							txtMaPhong.requestFocus();
							txtMaPhong.selectAll();
						}else if (chkTen>0) {
							JOptionPane.showMessageDialog(this, "Tên loại phòng này đã có.\nCập nhật thất bại");
							txtTenPhong.requestFocus();
							txtTenPhong.selectAll();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	private boolean validData(){
		String ma= txtMaPhong.getText().trim();
		String ten= txtTenPhong.getText().trim();
		String giaText= txtGiaPhong.getText().trim();
		if(ma.equals("")){
			JOptionPane.showMessageDialog(this, "Mã phòng không được rỗng!");
			txtMaPhong.requestFocus();
			return false;
		}else if (!ma.matches("P\\d{2}")) {
			JOptionPane.showMessageDialog(this, "Mã phòng gồm 3 kí tự bắt đầu bằng  P và 2 kí tự số.\nVD: P01.");
			txtMaPhong.selectAll();
			txtMaPhong.requestFocus();
			return false;
		}else if (ten.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được rỗng");
			txtTenPhong.requestFocus();
			return false;
		}else if (!ten.matches("^[a-zA-Z0-9\u00C0-\u1EF9 ]+$")) {
			JOptionPane.showMessageDialog(this, "Tên không được chứa kí tự đặc biệt");
			txtTenPhong.requestFocus();
			txtTenPhong.selectAll();
			return false;
		}else if (giaText.equals("")) {
			JOptionPane.showMessageDialog(this, "Giá không được rỗng");
			txtGiaPhong.requestFocus();
			return false;
		}else if (!giaText.matches("^[1-9]\\d*(\\.\\d+)?$")) {
			JOptionPane.showMessageDialog(this, "Giá phải là số dương");
			txtGiaPhong.selectAll();
			txtGiaPhong.requestFocus();
			return false;
		}
		return true;
	}
}
