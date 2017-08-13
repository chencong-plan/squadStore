package cc.ccoder.squadStore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Address;
import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.IAddressService;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IOrderDetailsService;
import cc.ccoder.squadStore.service.IOrderService;
import cc.ccoder.squadStore.service.IShoppingService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.AddressServiceImpl;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.service.impl.OrderDetailsServiceImpl;
import cc.ccoder.squadStore.service.impl.OrderServiceImpl;
import cc.ccoder.squadStore.service.impl.ShoppingServiceImpl;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.FileUtils;

import javax.swing.JButton;

public class UserInfoCenterJF extends JFrame {

	private static final long serialVersionUID = 1L;

	private IShoppingService iShoppingService = new ShoppingServiceImpl();
	private ICommodityService iCommodityService = new CommodityServiceImpl();
	private IOrderDetailsService iOrderDetailsService = new OrderDetailsServiceImpl();
	private IAddressService iAddressService = new AddressServiceImpl();
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	private IOrderService iOrderService = new OrderServiceImpl();

	private JPanel contentPane;
	private JTable table;

	private DefaultTableModel tableModelShopping;
	private DefaultTableModel tableModelOrder;
	private JScrollPane scrollPane;

	private JButton btn_shopping;
	private JPanel panel_shoppingOrder;
	private JPanel panel_userInfo;
	private JButton btn_userInfoCenter;
	private JButton btn_order;
	private JLabel label_userCenter;
	private JButton btn_pay;
	private JButton btn_back;
	private JButton btn_addOrder;
	private JButton btn_showOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoCenterJF frame = new UserInfoCenterJF();
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
	public UserInfoCenterJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label_userCenter = new JLabel("");
		label_userCenter.setFont(new Font("楷体", Font.PLAIN, 21));
		label_userCenter.setBounds(10, 10, 109, 30);
		contentPane.add(label_userCenter);

		panel_shoppingOrder = new JPanel();
		panel_shoppingOrder.setBounds(117, 43, 654, 408);
		contentPane.add(panel_shoppingOrder);
		panel_shoppingOrder.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 644, 398);
		panel_shoppingOrder.add(scrollPane);

		table = new JTable();

		// 内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		panel_userInfo = new JPanel();
		panel_userInfo.setBounds(133, 43, 657, 408);
		contentPane.add(panel_userInfo);

		btn_userInfoCenter = new JButton("个人信息");
		btn_userInfoCenter.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_userInfoCenter.setBounds(510, 3, 120, 30);
		contentPane.add(btn_userInfoCenter);
		btn_userInfoCenter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_shoppingOrder.setVisible(false);
				label_userCenter.setText("个人中心");
				btn_addOrder.setVisible(false);
				btn_pay.setVisible(false);
				btn_showOrder.setVisible(false);
			}
		});

		// 点击购物车 显示购物车
		btn_shopping = new JButton("购物车");
		btn_shopping.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_shopping.setBounds(182, 3, 97, 30);
		contentPane.add(btn_shopping);
		btn_shopping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_shoppingOrder.setVisible(true);
				initalModelShopping();
				// 点击购物车 显示下单按钮
				btn_addOrder.setVisible(true);
				btn_pay.setVisible(false);
				btn_showOrder.setVisible(false);
			}
		});

		btn_order = new JButton("订单");
		btn_order.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_order.setBounds(353, 3, 97, 30);
		contentPane.add(btn_order);
		btn_order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_shoppingOrder.setVisible(true);
				initalModelOrder();
				// 点击订单按钮 显示支付按钮
				btn_pay.setVisible(true);
				btn_addOrder.setVisible(false);
				btn_showOrder.setVisible(true);
			}
		});

		btn_pay = new JButton();
		btn_pay.setText("支付");
		btn_pay.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_pay.setBounds(10, 91, 93, 30);
		contentPane.add(btn_pay);
		btn_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowNum = table.getSelectedRow();
				if (rowNum >= 0) {
					int check = JOptionPane.showConfirmDialog(UserInfoCenterJF.this, "是否确认支付", "提示",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						int id = (int) tableModelOrder.getValueAt(rowNum, 0);
						if (iOrderService.updateOrderState(id, 1)) {
							initalModelOrder();
						} else {
							JOptionPane.showMessageDialog(UserInfoCenterJF.this, "支付失败");
						}
					}
				} else {
					JOptionPane.showMessageDialog(UserInfoCenterJF.this, "你还没有选中任何行");
				}

			}
		});
		btn_pay.setVisible(false);

		btn_addOrder = new JButton("下单");
		btn_addOrder.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_addOrder.setBounds(14, 91, 93, 30);
		contentPane.add(btn_addOrder);
		btn_addOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 点击下单,添加商品进入订单详情表 同时在订单表里面生成一条订单记录,将订单表的主键ID写入订单详情表当中
				int[] rowNums = table.getSelectedRows(); // 获得选中的行数
				if (rowNums.length <= 0) {
					JOptionPane.showMessageDialog(UserInfoCenterJF.this, "还没有选中任何行");
					return;
				}
				// 定义数组存放将要处理的购物车条数
				int[] shoppingIds = new int[rowNums.length];
				// 在购物车中显示下单
				for (int i = 0; i < rowNums.length; i++) {
					int id = (int) tableModelShopping.getValueAt(rowNums[i], 0);
					System.out.println(id);
					shoppingIds[i] = id;
				}
				System.out.println("开始");
				if (iOrderService.addOrder(shoppingIds)) {
					JOptionPane.showMessageDialog(UserInfoCenterJF.this, "下单成功");
				} else {
					JOptionPane.showMessageDialog(UserInfoCenterJF.this, "下单失败");
				}
				initalModelShopping();
				//显示订单详情
				btn_showOrder.setVisible(true);
			}
		});
		btn_addOrder.setVisible(false);

		btn_back = new JButton("返回");
		btn_back.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_back.setBounds(10, 421, 97, 30);
		contentPane.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfoCenterJF.this.dispose();
				UserCommodityJF uJf = new UserCommodityJF();
				uJf.setVisible(true);
			}
		});

		btn_showOrder = new JButton("订单详情");
		btn_showOrder.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_showOrder.setBounds(10, 182, 97, 30);
		contentPane.add(btn_showOrder);
		btn_showOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowNum = table.getSelectedRow();
				if (rowNum >= 0) {
					int orderId = (int) tableModelOrder.getValueAt(rowNum, 0);
					UserShowOrderInfoJF.orderId = orderId;
					UserShowOrderInfoJF userShowOrderInfoJF = new UserShowOrderInfoJF();
					userShowOrderInfoJF.setVisible(true);
					System.out.println(orderId);
				} else {
					JOptionPane.showMessageDialog(UserInfoCenterJF.this, "还没选中任何行");
				}

			}
		});
		btn_showOrder.setVisible(false);

		// 默认加载购物车
		initalModelShopping();
	}

	public void initalModelShopping() {
		String[] objs = new String[] { "编号", "名称", "数量", "单价", "总价", "下单时间" };
		tableModelShopping = new DefaultTableModel(objs, 0);
		table.setModel(tableModelShopping);
		showShoppingModel();// 显示购物车当中的数据
		scrollPane.setViewportView(table);
		label_userCenter.setText("购物车");

	}

	public void initalModelOrder() {
		String[] objs = new String[] { "编号", "顾客名称", "地址", "电话", "支付状态", "是否接单", "下单时间" };
		tableModelOrder = new DefaultTableModel(objs, 0);
		table.setModel(tableModelOrder);
		scrollPane.setViewportView(table);
		showOrderModel(); // 显示订单表当中的数据
		label_userCenter.setText("订单");
	}

	public void showShoppingModel() {
		UserInfo userInfo = iUserInfoService.getSimpleUserInfo(FileUtils.getSomeByFile("user_login.txt"));
		// System.out.println(userInfo);
		for (Shopping shopping : iShoppingService.getMoreShoppings()) {
			Commodity commodity = iCommodityService.getSimpleCommodity(shopping.getCommodityId());
			if (commodity != null && shopping.getUserId() == userInfo.getId()) {
				tableModelShopping.addRow(new Object[] { shopping.getId(), commodity.getName(), commodity.getPrice(),
						shopping.getNumber(), shopping.getTotalPrice(), shopping.getCreatedTime() });
			}
		}
	}

	public void showOrderModel() {
		UserInfo userInfo = iUserInfoService.getSimpleUserInfo(FileUtils.getSomeByFile("user_login.txt"));
		if (userInfo == null) {
			return;
		}
		for (Order order : iOrderService.getMoreOrdersByUserId()) {
			if (order != null && order.getName().equals(userInfo.getUsername())) {
				// 未支付 商家未接单
				if (order.getState() == 0) {
					tableModelOrder.addRow(new Object[] { order.getId(), userInfo.getNickname(), order.getAddress(),
							order.getPhone(), "未支付", "等待支付", order.getCreatedTime() });
				} else {
					// 已支付 商家未接单
					if (order.getDeliverState() == 0) {
						tableModelOrder.addRow(new Object[] { order.getId(), userInfo.getNickname(), order.getAddress(),
								order.getPhone(), "已支付", "商家已接单,等待发货", order.getCreatedTime() });
					} else {
						// 已支付 商家已经接单待送达
						tableModelOrder.addRow(new Object[] { order.getId(), userInfo.getNickname(), order.getAddress(),
								order.getPhone(), "已支付", "已发货，配送中", order.getCreatedTime() });
					}
				}
			}
		}
	}
}
