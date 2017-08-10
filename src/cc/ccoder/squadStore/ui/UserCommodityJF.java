package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IShoppingService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.service.impl.ShoppingServiceImpl;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.DateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UserCommodityJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 注入商品service层接口
	private ICommodityService iCommodityService = new CommodityServiceImpl();
	// 注入用户service接口
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	// 注入购物车service接口
	private IShoppingService iShoppingService = new ShoppingServiceImpl();

	private JPanel contentPane;
	private JTable table;

	private DefaultTableModel tableModel;
	private JLabel label_user;

	private int pageNum = 1;
	private int pageSize = 10;
	private int pageSum = getCount();

	private JLabel label_proPage;
	private JLabel label_pageNow;
	private JLabel label_nextPage;
	private JButton btn_addOrder;
	private JButton btn_addShopping;

	public static String username;

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
		panel.setBounds(10, 53, 739, 338);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 729, 323);
		panel.add(scrollPane);

		table = new JTable();

		// 内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		String[] objs = new String[] { "编号", "菜名", "价格", "菜品介绍", "上架时间" };
		tableModel = new DefaultTableModel(objs, 0);
		table.setModel(tableModel);
		table.setRowHeight(30);
		initModel(pageNum, pageSize);
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

		label_proPage = new JLabel("上一页");
		label_proPage.setFont(new Font("楷体", Font.PLAIN, 15));
		label_proPage.setBounds(14, 421, 54, 18);
		contentPane.add(label_proPage);
		label_proPage.addMouseListener(new MouseClickProPage());

		label_pageNow = new JLabel("");
		label_pageNow.setText(pageNum + " / " + (pageSum / pageSize));
		label_pageNow.setFont(new Font("楷体", Font.PLAIN, 15));
		label_pageNow.setBounds(63, 421, 65, 17);
		contentPane.add(label_pageNow);

		label_nextPage = new JLabel("下一页");
		label_nextPage.setFont(new Font("楷体", Font.PLAIN, 15));
		label_nextPage.setBounds(120, 423, 54, 18);
		contentPane.add(label_nextPage);
		label_nextPage.addMouseListener(new MouseClickNextPage());

		btn_addOrder = new JButton("下单");
		btn_addOrder.setFont(new Font("楷体", Font.PLAIN, 15));
		btn_addOrder.setBounds(497, 421, 107, 30);
		contentPane.add(btn_addOrder);

		btn_addShopping = new JButton("添加购物车");
		btn_addShopping.setFont(new Font("楷体", Font.PLAIN, 15));
		btn_addShopping.setBounds(631, 419, 118, 30);
		contentPane.add(btn_addShopping);
		btn_addShopping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addToShopping();
			}
		});

	}

	private void initModel(int pageNum, int pageSize) {
		tableModel.setRowCount(0);
		for (Commodity commodity : iCommodityService.getMoreCommodityByOnShelf(pageNum, pageSize)) {
			tableModel.addRow(new Object[] { commodity.getId(), commodity.getName(), commodity.getPrice(),
					commodity.getDescribe(), commodity.getCreatedTime() });
		}
	}

	private int getCount() {
		int count = 0;
		for (Commodity commodity : iCommodityService.getMoreCommodityInfos()) {
			if (commodity.getState() == 1) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 添加商品进入购物车
	 */
	public void addToShopping() {
		int rowNum = table.getSelectedRow();
		if (rowNum >= 0) {
			int commodityId = (int) tableModel.getValueAt(rowNum, 0);
			try {
				int number = Integer.parseInt(JOptionPane.showInputDialog("输入数量"));
				if (number > 0) {
					number = Math.round(number);
					Shopping shopping = new Shopping();
					UserInfo userInfo = iUserInfoService.getSimpleUserInfo(username);
					Commodity commodity = iCommodityService.getSimpleCommodity(commodityId);
					if (userInfo != null && commodity != null) {
						shopping.setUserId(userInfo.getId());
						shopping.setCommodityId(commodityId);
						shopping.setNumber(number);
						shopping.setTotalPrice(commodity.getPrice() * number); // 浮点计算
						shopping.setCreatedTime(DateUtils.getNowTime());
						shopping.setUpdatedTime(DateUtils.getNowTime());
						boolean flag = iShoppingService.addToShopping(shopping);
						if (flag) {
							int num = JOptionPane.showConfirmDialog(UserCommodityJF.this, "添加购物车成功，是否前往购物车？", "提示",
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							if (num == 0) {
								// 前往个人中心 打开购物车
								//System.out.println("前往购物车");
								UserInfoCenterJF userInfoCenterJF = new UserInfoCenterJF();
								userInfoCenterJF.setVisible(true);
								UserCommodityJF.this.dispose();
							}
							// 否则啥都不干
						}
					} else {
						JOptionPane.showMessageDialog(UserCommodityJF.this, "加入购物车失败");
					}
				} else {
					JOptionPane.showMessageDialog(UserCommodityJF.this, "输入正确的数量");
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(UserCommodityJF.this, "输入正确的数量");
			}
		} else {
			JOptionPane.showMessageDialog(UserCommodityJF.this, "未选中任何菜");
		}
	}

	class MouseClickNextPage implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (pageNum < (pageSum / pageSize)) {
				pageNum++;
			}
			initModel(pageNum, pageSize);
			label_pageNow.setText(pageNum + " / " + (pageSum / pageSize));
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	class MouseClickProPage implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (pageNum > 1) {
				pageNum--;
			}
			initModel(pageNum, pageSize);
			label_pageNow.setText(pageNum + " / " + (pageSum / pageSize));
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

}
