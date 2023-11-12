package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class DangNhap_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlDangNhap;
	private JTextField txtTenTaiKhoan;
	private JPasswordField txtPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap_GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 1119, 874);
		pnlDangNhap = new JPanel();
		pnlDangNhap.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlDangNhap.setBackground(new Color(120,255,239));
		setContentPane(pnlDangNhap);
		pnlDangNhap.setLayout(null);
		
		JLabel lblTen = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n:");
		lblTen.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/User.png")));
		lblTen.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTen.setBounds(371, 255, 167, 26);
		pnlDangNhap.add(lblTen);
		
		JLabel lblMatKhau = new JLabel("M\u1EADt kh\u1EA9u:");
		lblMatKhau.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/Key.png")));
		lblMatKhau.setLabelFor(lblMatKhau);
		lblMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMatKhau.setBounds(371, 306, 131, 26);
		pnlDangNhap.add(lblMatKhau);
		
		txtTenTaiKhoan = new JTextField();
		lblTen.setLabelFor(txtTenTaiKhoan);
		txtTenTaiKhoan.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTenTaiKhoan.setBounds(545, 248, 269, 41);
		pnlDangNhap.add(txtTenTaiKhoan);
		txtTenTaiKhoan.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(546, 303, 268, 41);
		pnlDangNhap.add(txtPwd);
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.setBackground(new Color(255, 153, 255));
		btnDangNhap.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/Log in.png")));
		btnDangNhap.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnDangNhap.setBounds(685, 441, 210, 62);
		pnlDangNhap.add(btnDangNhap);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/log out.png")));
		btnThoat.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnThoat.setBounds(384, 441, 184, 62);
		pnlDangNhap.add(btnThoat);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/dang nhap2 1.png")));
		lblBanner.setBounds(10, 143, 298, 340);
		pnlDangNhap.add(lblBanner);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/banner.png")));
		lblLogo.setBounds(261, 11, 592, 128);
		pnlDangNhap.add(lblLogo);
		
		JLabel lblFacebook = new JLabel("Karaoke Nice");
		lblFacebook.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/fb.png")));
		lblFacebook.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblFacebook.setBounds(10, 668, 225, 41);
		pnlDangNhap.add(lblFacebook);
		
		JLabel lblIns = new JLabel("Karaoke Nice");
		lblIns.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/ins.png")));
		lblIns.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblIns.setBounds(10, 720, 225, 41);
		pnlDangNhap.add(lblIns);
		
		JLabel lblMap = new JLabel("12 Nguy\u1EC5n V\u0103n B\u1EA3o, G\u00F2 V\u1EA5p, TP HCM");
		lblMap.setIcon(new ImageIcon(DangNhap_GUI.class.getResource("/icon/map.png")));
		lblMap.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblMap.setBounds(10, 772, 331, 41);
		pnlDangNhap.add(lblMap);
	}

}
