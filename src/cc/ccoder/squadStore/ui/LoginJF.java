package cc.ccoder.squadStore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import cc.ccoder.squadStore.comm.ConstInfo;
import cc.ccoder.squadStore.service.IStoreService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.StoreServiceImpl;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.FileUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class LoginJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField field_username; // 用户名输入框
	private JPasswordField field_password; // 密码输入框
	private JRadioButton rdbtn_user; // 用户
	private JRadioButton rdbtn_store; // 商家
	private JButton btn_register;

	// 注入用户的服务层接口
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	// 注入商家服务层接口
	private IStoreService iStoreService = new StoreServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJF frame = new LoginJF();
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
	public LoginJF() {
		setTitle("SquadStore外卖系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_login = new JLabel("登   录");
		label_login.setFont(new Font("楷体", Font.PLAIN, 30));
		label_login.setBounds(236, 27, 144, 36);
		contentPane.add(label_login);

		JLabel label_username = new JLabel("用户名");
		label_username.setFont(new Font("楷体", Font.PLAIN, 20));
		label_username.setBounds(140, 83, 86, 36);
		contentPane.add(label_username);

		field_username = new JTextField();
		field_username.setBounds(248, 86, 209, 28);
		contentPane.add(field_username);
		field_username.setColumns(10);

		JLabel label_password = new JLabel("密  码");
		label_password.setHorizontalAlignment(SwingConstants.TRAILING);
		label_password.setFont(new Font("楷体", Font.PLAIN, 20));
		label_password.setBounds(116, 158, 86, 36);
		contentPane.add(label_password);

		field_password = new JPasswordField();
		field_password.setColumns(10);
		field_password.setBounds(248, 158, 209, 28);
		contentPane.add(field_password);

		rdbtn_user = new JRadioButton(" 用 户");
		rdbtn_user.setFont(new Font("楷体", Font.PLAIN, 17));
		rdbtn_user.setBounds(228, 209, 86, 23);
		contentPane.add(rdbtn_user);

		rdbtn_store = new JRadioButton(" 商 家");
		rdbtn_store.setFont(new Font("楷体", Font.PLAIN, 17));
		rdbtn_store.setBounds(343, 209, 86, 23);
		contentPane.add(rdbtn_store);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_store);
		buttonGroup.add(rdbtn_user);
		rdbtn_user.setSelected(true); // 默认选中用户

		JButton btn_login = new JButton(" 登  录");
		btn_login.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_login.setBounds(169, 249, 119, 36);
		contentPane.add(btn_login);
		// 登录按钮点击事件
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 用户登录
				String username = field_username.getText();
				String password = new String(field_password.getPassword());
				if (username.equals("") || password.equals("")) {
					JOptionPane.showConfirmDialog(LoginJF.this, "输入框不能够为空", "提示", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					if (rdbtn_user.isSelected()) {
						// 用户登录
						if (iUserInfoService.isLogin(username, password)) {
							System.out.println("登录成功");
							// 就将当前登录的用户信息写入指定文件当中
							FileUtils.setSomeByFile(ConstInfo.FILE_PATH.getName(), username);
							// 然后跳转到主页面--个人中心
						} else {
							JOptionPane.showConfirmDialog(LoginJF.this, "用户名或者密码错误", "登录失败", JOptionPane.YES_NO_OPTION,
									JOptionPane.ERROR_MESSAGE);
						}
					}
					if (rdbtn_store.isSelected()) {
						// 商家登录
						if (iStoreService.isStoreLogin(username, password)) {
							System.out.println("登录成功");
							// 跳转到商家管理页面
						} else {
							JOptionPane.showConfirmDialog(LoginJF.this, "用户名或者密码错误", "登录失败", JOptionPane.YES_NO_OPTION,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		btn_register = new JButton(" 注  册");
		btn_register.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_register.setBounds(338, 249, 119, 36);
		contentPane.add(btn_register);
		// 如果商家选中 则不允许注册
		rdbtn_store.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 选择商家 则不允许注册
				btn_register.setEnabled(false);
			}
		});
		rdbtn_user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_register.setEnabled(true);
			}
		});
		// 注册按钮的点击事件
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 弹出注册对话框
				UserRegisterJF userRegisterJF = new UserRegisterJF();
				userRegisterJF.setVisible(true);
				LoginJF.this.dispose();
			}
		});
	}
}
