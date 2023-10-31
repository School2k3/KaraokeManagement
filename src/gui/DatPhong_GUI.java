package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class DatPhong_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tblChonPhong, tblPhongDat;
	private JTextField txtNgayDat;
	private DefaultTableModel modelPhong, modelPhongDat;

	/**
	 * Create the panel.
	 */
	public DatPhong_GUI() {
		setSize(1600, 1055);
		setLayout(null);
		
		JLabel lblDatPhong = new JLabel("�?ặt phòng");
		lblDatPhong.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblDatPhong.setBounds(698, 11, 203, 62);
		add(lblDatPhong);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(61, 81, 768, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTimKiem.setBounds(908, 73, 211, 45);
		add(btnTimKiem);
		
		JLabel lblChonPhong = new JLabel("Ch�?n phòng");
		lblChonPhong.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChonPhong.setBounds(61, 132, 123, 30);
		add(lblChonPhong);
		
		JPanel pnlTablePhong = new JPanel();
		pnlTablePhong.setBounds(243, 129, 1303, 289);
		String[] colHeaderPhong = {"Mã phòng", "Tên phòng", "Loại phòng", "�?ơn giá", "Trạng thái"};
		modelPhong = new DefaultTableModel(colHeaderPhong, 0);
		pnlTablePhong.setLayout(null);
		tblChonPhong = new JTable(modelPhong);
		JScrollPane scrPhong = new JScrollPane(tblChonPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhong.setBounds(0, 0, 1303, 289);
		scrPhong.setBackground(new Color(120, 255, 239));
		scrPhong.getViewport().setBackground(Color.WHITE);
//		scrKhachHang.setViewportBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		scrPhong.setBorder(BorderFactory.createLineBorder(new Color(185, 185, 185)));
		tblChonPhong.getTableHeader().setBackground(new Color(120, 255, 239));
		tblChonPhong.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		pnlTablePhong.add(scrPhong);
		add(pnlTablePhong);
		
		JLabel lblNgayDat = new JLabel("Ngày đặt phòng");
		lblNgayDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNgayDat.setBounds(61, 429, 153, 30);
		add(lblNgayDat);
		
		txtNgayDat = new JTextField();
		txtNgayDat.setBounds(244, 430, 240, 29);
		add(txtNgayDat);
		txtNgayDat.setColumns(10);
		
		JLabel lblGioDat = new JLabel("Gi�? đặt");
		lblGioDat.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGioDat.setBounds(614, 429, 93, 30);
		add(lblGioDat);
		
		JComboBox cmbGio = new JComboBox();
		cmbGio.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cmbGio.setBounds(729, 429, 45, 30);
		add(cmbGio);
		
		JComboBox cmbPhut = new JComboBox();
		cmbPhut.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cmbPhut.setBounds(802, 429, 45, 30);
		add(cmbPhut);
		
		JLabel lblTime = new JLabel(":");
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTime.setBounds(784, 434, 17, 20);
		add(lblTime);
		
		JButton btnThemPhieuDatPhong = new JButton("Thêm vào phiếu đặt phòng");
		btnThemPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnThemPhieuDatPhong.setBounds(949, 429, 303, 31);
		add(btnThemPhieuDatPhong);
		
		JButton btnXoaTrang = new JButton("Làm mới");
		btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnXoaTrang.setBounds(1357, 430, 170, 30);
		add(btnXoaTrang);
		
		JPanel pnlPhieuDatPhong = new JPanel();
		pnlPhieuDatPhong.setBorder(new LineBorder(new Color(192, 192, 192)));
		pnlPhieuDatPhong.setBounds(61, 470, 1496, 474);
		add(pnlPhieuDatPhong);
		pnlPhieuDatPhong.setLayout(null);
		
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
		
		JPanel pnlTablePhongDat = new JPanel();
		pnlTablePhongDat.setBounds(0, 144, 1496, 328);
		pnlPhieuDatPhong.add(pnlTablePhongDat);
		pnlTablePhongDat.setLayout(null);
		String[] colHeaderDatPhong = {"Mã phòng", "Tên phòng", "Ngày đặt", "Th�?i gian đặt"};
		modelPhongDat = new DefaultTableModel(colHeaderDatPhong, 0);
		tblPhongDat = new JTable(modelPhongDat);
		JScrollPane scrPhongDat = new JScrollPane(tblPhongDat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDat.setBounds(0, 0, 1496, 328);
		pnlTablePhongDat.add(scrPhongDat);
		
		JButton btnLuuPhieuDatPhong = new JButton("Lưu phiếu đặt phòng");
		btnLuuPhieuDatPhong.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLuuPhieuDatPhong.setBounds(949, 955, 303, 31);
		add(btnLuuPhieuDatPhong);
		
		JButton btnHuyPhieu = new JButton("Hủy phiếu");
		btnHuyPhieu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnHuyPhieu.setBounds(1357, 956, 170, 30);
		add(btnHuyPhieu);
		
	}
}
