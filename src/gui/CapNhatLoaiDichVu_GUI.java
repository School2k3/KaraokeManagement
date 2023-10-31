package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class CapNhatLoaiDichVu_GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDichVu, txtLoaiDichVu;
	private JButton btnThem,btnSua, btnXoa, btnXoaTrang;
	private JTable tblLoaiDichVu;

	/**
	 * Create the frame.
	 */
	public CapNhatLoaiDichVu_GUI() {
				setSize(1600, 1050);
				setLayout(null);
				JLabel lblTitle = new JLabel("Cập nhật loại dịch vụ");
				lblTitle.setBounds(603, 11, 393, 49);
				lblTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
				add(lblTitle);
				
				JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
				lblMaDichVu.setBounds(265, 131, 131, 48);
				lblMaDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
				add(lblMaDichVu);
				
				txtMaDichVu = new JTextField();
				txtMaDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
				txtMaDichVu.setBounds(393, 139, 228, 32);
				lblMaDichVu.setLabelFor(txtMaDichVu);
				txtMaDichVu.setColumns(10);
				add(txtMaDichVu);
				
				JLabel lblLoaiDichVu = new JLabel("Loại dịch vụ:");
				lblLoaiDichVu.setBounds(818, 131, 131, 48);
				lblLoaiDichVu.setFont(new Font("SansSerif", Font.BOLD, 20));
				add(lblLoaiDichVu);
				
				txtLoaiDichVu = new JTextField();
				txtLoaiDichVu.setFont(new Font("SansSerif", Font.PLAIN, 20));
				txtLoaiDichVu.setBounds(960, 139, 242, 32);
				lblLoaiDichVu.setLabelFor(txtLoaiDichVu);
				txtLoaiDichVu.setColumns(10);
				add(txtLoaiDichVu);
				
				btnThem = new JButton("Thêm");
				btnThem.setBounds(419, 249, 131, 49);
				btnThem.setFont(new Font("SansSerif", Font.PLAIN, 20));
				btnThem.setFocusable(false);
				add(btnThem);
				
				btnXoa = new JButton("Xóa");
				btnXoa.setBounds(958, 249, 131, 49);
				btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 20));
				btnXoa.setFocusable(false);
				add(btnXoa);
				
				btnSua = new JButton("Sửa");
				btnSua.setBounds(671, 251, 131, 49);
				btnSua.setFont(new Font("SansSerif", Font.PLAIN, 20));
				btnSua.setFocusable(false);
				add(btnSua);
				
				btnXoaTrang = new JButton("Xóa trắng");
				btnXoaTrang.setBounds(1179, 249, 163, 49);
				btnXoaTrang.setFont(new Font("SansSerif", Font.PLAIN, 20));
				btnXoaTrang.setFocusable(false);
				add(btnXoaTrang);
				
				JScrollPane scrLoaiDichVu = new JScrollPane();
				scrLoaiDichVu.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrLoaiDichVu.setBounds(175, 337, 1291, 674);
				add(scrLoaiDichVu);
				
				tblLoaiDichVu = new JTable();
				scrLoaiDichVu.setViewportView(tblLoaiDichVu);
				tblLoaiDichVu.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"M\u00E3 d\u1ECBch v\u1EE5", "Lo\u1EA1i d\u1ECBch v\u1EE5"
					}
				));
				tblLoaiDichVu.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				tblLoaiDichVu.getTableHeader().setBackground(new Color(120, 255, 239));
				
				
				
	}
}
