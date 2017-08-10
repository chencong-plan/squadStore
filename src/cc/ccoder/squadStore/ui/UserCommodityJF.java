package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class UserCommodityJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private DefaultTableModel tableModel;

	private JLabel label_user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCommodityJF frame = new UserCommodityJF();
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
	public UserCommodityJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 53, 739, 378);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 729, 358);
		panel.add(scrollPane);

		table = new JTable();
		String[] objs = new String[] { "菜名", "价格", "菜品介绍", "上架时间" };
		tableModel = new DefaultTableModel(objs, 0);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);

		JLabel lblsquadstroe = new JLabel("欢迎来到SquadStroe家常菜馆");
		lblsquadstroe.setFont(new Font("楷体", Font.PLAIN, 17));
		lblsquadstroe.setBounds(287, 10, 235, 27);
		contentPane.add(lblsquadstroe);

		label_user = new JLabel("");
		label_user.setIcon(new ImageIcon("C:\\Users\\chencong\\git\\squadStore\\image\\label_userIcon.png"));
		label_user.setFont(new Font("楷体", Font.PLAIN, 15));
		label_user.setBounds(21, 10, 47, 40);
		contentPane.add(label_user);
	}
}
