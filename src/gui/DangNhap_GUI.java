package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class DangNhap_GUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlDangNhap, pnlHidePassword, pnlUnHidePassword;
	private JLabel lblHidePassword, lblUnHidePassword;
	public JTextField txtSoDienThoai;
	private JPasswordField txtPwd;
	public JButton btnDangNhap, btnThoat;
	private NhanVien nhanVien = null;
	private NhanVien_DAO nhanVienDAO;
	private TrangChu_GUI winTrangChu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI winDangNhap = new DangNhap_GUI();
					winDangNhap.setVisible(true);
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

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 150, 1119, 730);
		pnlDangNhap = new JPanel();
		pnlDangNhap.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlDangNhap.setBackground(new Color(120, 255, 239));
		setContentPane(pnlDangNhap);
		pnlDangNhap.setLayout(null);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		Image imgUser = new ImageIcon(this.getClass().getResource("/User.png")).getImage();
		lblSoDienThoai.setIcon(new ImageIcon(imgUser));
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSoDienThoai.setBounds(371, 255, 167, 26);
		pnlDangNhap.add(lblSoDienThoai);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		Image imgKey = new ImageIcon(this.getClass().getResource("/Key.png")).getImage();
		lblMatKhau.setIcon(new ImageIcon(imgKey));
		lblMatKhau.setLabelFor(lblMatKhau);
		lblMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblMatKhau.setBounds(371, 306, 131, 26);
		pnlDangNhap.add(lblMatKhau);

		txtSoDienThoai = new JTextField();
		lblSoDienThoai.setLabelFor(txtSoDienThoai);
		txtSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtSoDienThoai.setBounds(545, 248, 269, 41);
		pnlDangNhap.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		txtPwd = new JPasswordField();
		txtPwd.setBounds(546, 303, 268, 41);
		pnlDangNhap.add(txtPwd);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(new Color(255, 153, 255));
		Image imgLogIn = new ImageIcon(this.getClass().getResource("/Log in.png")).getImage();
		btnDangNhap.setIcon(new ImageIcon(imgLogIn));
		btnDangNhap.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnDangNhap.setBounds(685, 441, 210, 62);
		pnlDangNhap.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setBackground(Color.LIGHT_GRAY);
		Image imgLogOut = new ImageIcon(this.getClass().getResource("/log out.png")).getImage();
		btnThoat.setIcon(new ImageIcon(imgLogOut));
		btnThoat.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnThoat.setBounds(384, 441, 184, 62);
		pnlDangNhap.add(btnThoat);

		JLabel lblBanner = new JLabel("");
		Image imgBanner = new ImageIcon(this.getClass().getResource("/dang nhap2 1.png")).getImage();
		lblBanner.setIcon(new ImageIcon(imgBanner));
		lblBanner.setBounds(10, 143, 298, 340);
		pnlDangNhap.add(lblBanner);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgLogo = new ImageIcon(this.getClass().getResource("/banner.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imgLogo));
		lblLogo.setBounds(261, 11, 592, 128);
		pnlDangNhap.add(lblLogo);

		JLabel lblFacebook = new JLabel("Karaoke Nice");
		Image imgFB = new ImageIcon(this.getClass().getResource("/fb.png")).getImage();
		lblFacebook.setIcon(new ImageIcon(imgFB));
		lblFacebook.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblFacebook.setBounds(10, 535, 225, 41);
		pnlDangNhap.add(lblFacebook);

		JLabel lblIns = new JLabel("Karaoke Nice");
		Image imgIns = new ImageIcon(this.getClass().getResource("/ins.png")).getImage();
		lblIns.setIcon(new ImageIcon(imgIns));
		lblIns.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblIns.setBounds(10, 587, 225, 41);
		pnlDangNhap.add(lblIns);

		JLabel lblMap = new JLabel("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, TP.HCM");
		Image imgMap = new ImageIcon(this.getClass().getResource("/map.png")).getImage();
		lblMap.setIcon(new ImageIcon(imgMap));
		lblMap.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblMap.setBounds(10, 639, 390, 41);
		pnlDangNhap.add(lblMap);

		pnlHidePassword = new JPanel();
		pnlHidePassword.setBackground(new Color(120, 255, 239));
		lblHidePassword = new JLabel("");
		lblHidePassword.setBackground(new Color(120, 255, 239));
		Image imgHidePassword = new ImageIcon(this.getClass().getResource("/hide.png")).getImage();
		lblHidePassword.setIcon(new ImageIcon(imgHidePassword));
		pnlHidePassword.setBounds(820, 300, 72, 44);
		pnlHidePassword.add(lblHidePassword);
		pnlDangNhap.add(pnlHidePassword);

		pnlUnHidePassword = new JPanel();
		pnlUnHidePassword.setBackground(new Color(120, 255, 239));
		lblUnHidePassword = new JLabel("");
		Image imgUnHidePassword = new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		lblUnHidePassword.setIcon(new ImageIcon(imgUnHidePassword));
		pnlUnHidePassword.setBounds(820, 300, 72, 44);
		pnlUnHidePassword.add(lblUnHidePassword);
		pnlDangNhap.add(pnlUnHidePassword);

		nhanVienDAO = new NhanVien_DAO();

		// Thiết lập sự kiện cho 2 nút đăng nhập và thoát
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);

		// Thiết lập sự kiện cho phần ẩn mật khẩu và hiện mật khẩu
		lblHidePassword.addMouseListener(this);
		lblUnHidePassword.addMouseListener(this);
		
		// Mặc định
		txtSoDienThoai.setText("0902566537");
		txtPwd.setText("1111");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			this.dispose();
		} else if (o.equals(btnDangNhap)) {
			String soDienThoai = txtSoDienThoai.getText();
			char[] pass = txtPwd.getPassword();
			String matKhau = new String(pass);
			if (valid()) {
				try {
					if (checkAccount(soDienThoai, matKhau)) {
						JOptionPane.showMessageDialog(this, "Đang nhập thành công!");
						this.dispose();
						winTrangChu = new TrangChu_GUI(nhanVien);
						winTrangChu.setLocationRelativeTo(null);
						winTrangChu.setDefaultCloseOperation(EXIT_ON_CLOSE);
						winTrangChu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(this, "Số diện thoại hoặc mật khẩu không chính xác!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(lblHidePassword)) {
			txtPwd.setEchoChar((char) 0); // Đưa về dạng chữ thuần
			pnlHidePassword.setVisible(false);
			pnlUnHidePassword.setVisible(true);
		} else if (o.equals(lblUnHidePassword)) {
			txtPwd.setEchoChar('*'); // Đưa về các kí tự *
			pnlUnHidePassword.setVisible(false);
			pnlHidePassword.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean valid() {
		String taiKhoan = txtSoDienThoai.getText().trim();
		if (taiKhoan.equals("")) {
			JOptionPane.showMessageDialog(txtSoDienThoai, "Bạn chưa nhập số điện thoại!");
			return false;
		} else if (txtPwd.getPassword().length == 0) {
			JOptionPane.showMessageDialog(txtPwd, "Bạn chưa nhập mật khẩu!");
			return false;

		}
		return true;
	}

	public boolean checkAccount(String soDienThoai, String matKhau) throws Exception {
		nhanVien = nhanVienDAO.getAllTableNhanVienBySoDienThoaiAndMatKhau(soDienThoai, matKhau);
		if (nhanVien == null) {
			return false;
		}
		return true;
	}
}
