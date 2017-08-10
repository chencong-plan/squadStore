package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IShoppingService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.service.impl.ShoppingServiceImpl;

import javax.swing.JButton;

public class UserInfoCenterJF extends JFrame {

	private static final long serialVersionUID = 1L;

	private IShoppingService iShoppingService = new ShoppingServiceImpl();
	private ICommodityService iCommodityService = new CommodityServiceImpl();

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
	private JButton btn_addOrder;
	private JButton btn_pay;

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
			}
		});

		btn_shopping = new JButton("购物车");
		btn_shopping.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_shopping.setBounds(323, 3, 97, 30);
		contentPane.add(btn_shopping);
		btn_shopping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_shoppingOrder.setVisible(true);
				initalModelShopping();

			}
		});

		btn_order = new JButton("订单");
		btn_order.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_order.setBounds(150, 3, 97, 30);
		contentPane.add(btn_order);
		
		 btn_addOrder = new JButton();
		btn_addOrder.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_addOrder.setBounds(10, 109, 97, 30);
		contentPane.add(btn_addOrder);
		
		 btn_pay = new JButton();
		btn_pay.setFont(new Font("楷体", Font.PLAIN, 16));
		btn_pay.setBounds(10, 109, 97, 30);
		contentPane.add(btn_pay);
		btn_order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_shoppingOrder.setVisible(true);
				initalModelOrder();
			}
		});

		// 默认加载购物车
		initalModelShopping();
	}

	public void initalModelShopping() {
		String[] objs = new String[] { "编号", "名称", "数量", "单价", "总价", "下单时间" };
		tableModelShopping = new DefaultTableModel(objs, 0);
		table.setModel(tableModelShopping);
		showShoppingModel();
		scrollPane.setViewportView(table);
		label_userCenter.setText("购物车");
		btn_addOrder.setText("下单");
	}

	public void initalModelOrder() {
		String[] objs = new String[] { "编号", "订单" };
		tableModelOrder = new DefaultTableModel(objs, 0);
		table.setModel(tableModelOrder);
		scrollPane.setViewportView(table);

		label_userCenter.setText("订单");
		btn_pay.setText("支付");
	}

	public void showShoppingModel() {
		for (Shopping shopping : iShoppingService.getMoreShoppings()) {
			Commodity commodity = iCommodityService.getSimpleCommodity(shopping.getCommodityId());
			if (commodity != null) {
				tableModelShopping.addRow(new Object[] { shopping.getId(), commodity.getName(), commodity.getPrice(),
						shopping.getNumber(), shopping.getTotalPrice(), shopping.getCreatedTime() });
			}
		}
	}
}
