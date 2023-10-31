package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.DichVu_DAO;
import entity.DichVu;

public class CapNhatDichVu_GUI extends JPanel implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDichVu,txtTenDichVu,txtDonGia,txtSoLuongTon;
	private JTable tblDichVu;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang;
	private JScrollPane scrDichVu;
	private JComboBox cmbLoai;
	private DichVu_DAO dv_dao;
	private DefaultTableModel model;
	private ArrayList<DichVu> dsDV;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public CapNhatDichVu_GUI() throws Exception {
		dv_dao= new DichVu_DAO();
		setSize(1600,1050);
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
		
		JLabel lblTenDichVu = new JLabel("Tên loại dịch vụ:");
		lblTenDichVu.setBounds(726, 140, 132, 62);
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
		txtTenDichVu.setBounds(900, 155, 341, 32);
		txtTenDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenDichVu.setColumns(10);
		add(txtTenDichVu);
		
		txtDonGia = new JTextField();
		txtDonGia.setBounds(301, 312, 233, 32);
		txtDonGia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtDonGia.setColumns(10);
		add(txtDonGia);
		
		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setBounds(900, 228, 233, 32);
		txtSoLuongTon.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtSoLuongTon.setColumns(10);
		add(txtSoLuongTon);
		
		cmbLoai = new JComboBox();
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
		
		//set color btn
		btnThem.setBackground(new Color(217, 217, 217));
		btnSua.setBackground(new Color(217, 217, 217));
		btnXoa.setBackground(new Color(217, 217, 217));
		btnXoaTrang.setBackground(new Color(217, 217, 217));
		

		//table
		String[] header = { "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Số lượng tồn", "Đơn giá" };
		model = new DefaultTableModel(header, 0);

		add(scrDichVu = new JScrollPane(tblDichVu = new JTable(model),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		scrDichVu.setBounds(102, 521, 1387, 500);
		scrDichVu.setBackground(new Color(120, 255, 239));
		scrDichVu.getViewport().setBackground(Color.WHITE);
		scrDichVu.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
		tblDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		docDuLieu();

		
		
		//them su kien
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}
	public void docDuLieu() throws Exception {
		dsDV = dv_dao.layThongTin();
		model.setRowCount(0);
		for (DichVu dv : dsDV) {
			model.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(),dv.getLoaiDichVu().getTenLoaiDichVu(), dv.getSoLuongTon(), dv.getDonGia() });
		}
	}
	public void xoaTrang(){
		txtMaDichVu.setText("");
		txtMaDichVu.requestFocus();
		txtTenDichVu.setText("");
		txtDonGia.setText("");
		txtSoLuongTon.setText("");
		cmbLoai.setSelectedIndex(0);
	}
	public void them(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if(o.equals(btnXoaTrang)){
			xoaTrang();
		}
		else if (o.equals(btnThem)) {
			
		}
		else if (o.equals(btnSua)) {
			
		}else {
				
		}
		
		
	}
	public boolean validData(){
		
		return true;
		
	}
}
