package cc.ccoder.squadStore.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IOrderDetailsService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.service.impl.OrderDetailsServiceImpl;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.FileUtils;

public class UserShowOrderInfoJF extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private ScrollPane scrollPane;
	private IOrderDetailsService iOrderDetailsService = new OrderDetailsServiceImpl();
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	private ICommodityService iCommodityService = new CommodityServiceImpl();
	public static int orderId ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserShowOrderInfoJF frame = new UserShowOrderInfoJF();
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
	public UserShowOrderInfoJF() {
		setTitle("订单详情");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 22, 774, 429);
		contentPane.add(scrollPane);

		String[] objs = new String[] { "编号", "顾客名称", "菜名", "单价", "数量", "总价", "下单时间" };
		tableModel = new DefaultTableModel(objs, 0);
		// mod.setRowCount(14);
		table = new JTable(tableModel);

		// 内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		initalModel();
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		table.getTableHeader().setResizingAllowed(false);// 列大小不可改变
		scrollPane.setViewportView(table);

		// mod.addRow(new Object[]{"重启鸡公煲","3","56"});

		// String[] objs = new String[] { "编号", "顾客名称", "菜名", "单价", "数量", "总价", "下单时间"
		// };

	}

	public void initalModel() {
		tableModel.setRowCount(0);
		String username = FileUtils.getSomeByFile("user_login.txt");
		if (username == null) {
			JOptionPane.showMessageDialog(UserShowOrderInfoJF.this, "用户没有登录");
			return;
		}
		UserInfo userInfo = iUserInfoService.getSimpleUserInfo(username);

		if (userInfo == null) {
			return;
		}
		for (OrderDetails orderDetails : iOrderDetailsService.getMoreOrderDetails(userInfo.getId(), orderId)) {
			Commodity commodity = iCommodityService.getSimpleCommodity(orderDetails.getCommodityId());
			if (commodity == null) {
				return;
			}
			tableModel
					.addRow(new Object[] { orderDetails.getId(), username, commodity.getName(), orderDetails.getPrice(),
							orderDetails.getNumber(), orderDetails.getTotalPrice(), orderDetails.getCreatedTime() });
		}
	}
}
