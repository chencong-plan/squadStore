package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import cc.ccoder.squadStore.comm.ConstInfo;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.DateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UserRegisterJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField field_username;
	private JLabel label_showInfo;
	private JLabel label_password;
	private JPasswordField field_password;
	private JLabel label_repass;
	private JPasswordField field_repass;
	private JLabel label_email;
	private JTextField field_email;
	private JLabel label_showPassInfo;
	private JButton btn_register;
	private JButton btn_back;

	// 将用户service接口注入进来
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegisterJF frame = new UserRegisterJF();
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
	public UserRegisterJF() {
		setTitle("新用户注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 这只不允许最大化
		setResizable(false);
		setBounds(400, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_username = new JLabel("用户名");
		label_username.setFont(new Font("楷体", Font.PLAIN, 17));
		label_username.setBounds(105, 37, 74, 27);
		contentPane.add(label_username);

		field_username = new JTextField();
		field_username.setBounds(200, 36, 167, 31);
		contentPane.add(field_username);
		field_username.setColumns(10);

		label_showInfo = new JLabel("");
		label_showInfo.setFont(new Font("楷体", Font.PLAIN, 13));
		label_showInfo.setBounds(386, 36, 179, 27);
		contentPane.add(label_showInfo);

		label_password = new JLabel("密  码");
		label_password.setFont(new Font("楷体", Font.PLAIN, 17));
		label_password.setBounds(105, 87, 74, 27);
		contentPane.add(label_password);

		field_password = new JPasswordField();
		field_password.setColumns(10);
		field_password.setBounds(200, 86, 167, 31);
		contentPane.add(field_password);

		label_repass = new JLabel("确认密码");
		label_repass.setFont(new Font("楷体", Font.PLAIN, 17));
		label_repass.setBounds(105, 143, 74, 27);
		contentPane.add(label_repass);

		field_repass = new JPasswordField();
		field_repass.setColumns(10);
		field_repass.setBounds(200, 142, 167, 31);
		contentPane.add(field_repass);

		label_email = new JLabel("邮  箱");
		label_email.setFont(new Font("楷体", Font.PLAIN, 17));
		label_email.setBounds(105, 196, 74, 27);
		contentPane.add(label_email);

		field_email = new JTextField();
		field_email.setColumns(10);
		field_email.setBounds(200, 192, 167, 31);
		contentPane.add(field_email);

		label_showPassInfo = new JLabel("");
		label_showPassInfo.setFont(new Font("楷体", Font.PLAIN, 13));
		label_showPassInfo.setBounds(386, 143, 179, 27);
		contentPane.add(label_showPassInfo);

		btn_register = new JButton("注  册");
		btn_register.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_register.setBounds(155, 285, 93, 31);
		contentPane.add(btn_register);
		// 注册按钮的点击事件 首先需要判断用户输入的内容是否为空
		// 然后判断用户输入的用户名是否存在于数据库当中 如果存在则该用户名不允许注册 否则注册
		// 然后判断用户输入多次密码是否相同 如果相同 则允许注册否则提示密码不一致
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获得用户输入的内容
				String username = field_username.getText();
				String password = new String(field_password.getPassword());
				String repass = new String(field_repass.getPassword());
				String email = field_email.getText();
				if ("".equals(username) || "".equals(password) || "".equals(repass) || "".equals(email)) {
					JOptionPane.showConfirmDialog(UserRegisterJF.this, "输入框不能够为空", "提示", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// 当输入框都不为空之后才进行操作
					if (iUserInfoService.isExistsUserName(username)) {
						// 输入的用户名没有被注册
						// 判断两次密码是否一致
						if (password.equals(repass)) {
							// 密码一致 进行注册
							UserInfo userInfo = new UserInfo();
							userInfo.setUsername(username);
							userInfo.setPassword(password);
							userInfo.setEmail(email);
							userInfo.setCreatedTime(DateUtils.getNowTime());
							userInfo.setUpdatedTime(DateUtils.getNowTime());
							//开始注册
							int result = iUserInfoService.UserInfoRegister(userInfo);
							if (result==ConstInfo.SUCCESS.getIndex()) {
								//操作成功
								int num = JOptionPane.showConfirmDialog(UserRegisterJF.this, "注册成功,前去登录吧","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
								if (num == 0) {
									UserRegisterJF.this.dispose();
									LoginJF loginJF = new LoginJF();
									loginJF.setVisible(true);
								}
							}else{
								JOptionPane.showConfirmDialog(UserRegisterJF.this, "注册失败,请稍后重试","错误",JOptionPane.YES_OPTION,JOptionPane.ERROR_MESSAGE);
							}
						} else {
							// 密码不一致 不允许注册
							label_showPassInfo.setText("密码不一致");
							label_showPassInfo.setForeground(Color.red);
						}
					} else {
						label_showInfo.setText("用户名已经存在");
						label_showPassInfo.setForeground(Color.red);
					}
				}
			}
		});

		btn_back = new JButton("返  回");
		btn_back.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_back.setBounds(308, 285, 93, 31);
		contentPane.add(btn_back);
		// 返回按钮的点击事件
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 当前页面消失，登录页面显示
				UserRegisterJF.this.dispose();
				LoginJF loginJF = new LoginJF();
				loginJF.setVisible(true);
			}
		});

	}

}
