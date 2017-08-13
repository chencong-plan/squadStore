package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.IOrderDetailsService;
import cc.ccoder.squadStore.service.IOrderService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.service.impl.OrderDetailsServiceImpl;
import cc.ccoder.squadStore.service.impl.OrderServiceImpl;
import cc.ccoder.squadStore.service.impl.UserInfoServiceImpl;
import cc.ccoder.squadStore.util.FileUtils;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderManagerJF extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private IOrderService iOrderService = new OrderServiceImpl();
	private IOrderDetailsService iOrderDetailsService = new OrderDetailsServiceImpl();
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	private JButton btn_getOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderManagerJF frame = new OrderManagerJF();
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
	public OrderManagerJF() {
		setTitle("订单");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(5, 5, 774, 367);
		contentPane.add(scrollPane);

		String[] objs = new String[] { "编号", "顾客名称", "数量", "总价", "配送地址", "下单时间", "是否接单" };
		tableModel = new DefaultTableModel(objs, 0);
		// mod.setRowCount(14);
		table = new JTable(tableModel);

		// 内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		initalModel();

		scrollPane.setViewportView(table);

		btn_getOrder = new JButton("接单");
		btn_getOrder.setFont(new Font("楷体", Font.PLAIN, 15));
		btn_getOrder.setBounds(33, 404, 93, 27);
		contentPane.add(btn_getOrder);
		btn_getOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rowNums = table.getSelectedRows();
				if (rowNums.length > 0) {
					int check = JOptionPane.showConfirmDialog(OrderManagerJF.this, "是否接单", "提示",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						for (int i = 0; i < rowNums.length; i++) {
							int id = (int) tableModel.getValueAt(rowNums[i], 0);
							if (iOrderService.updateOrderDeliverState(id, 1)) {
								JOptionPane.showMessageDialog(OrderManagerJF.this, "接单成功");
							}else {
								JOptionPane.showMessageDialog(OrderManagerJF.this, "接单失败");
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(OrderManagerJF.this, "未选中任何行");
				}
				initalModel();
			}
		});
	}

	private void initalModel() {
		tableModel.setRowCount(0);
		UserInfo userInfo = iUserInfoService.getSimpleUserInfo(FileUtils.getSomeByFile("user_login.txt"));
		if (userInfo == null) {
			return;
		}
		for (Order order : iOrderService.getMoreOrdersByUserId()) {
			List<OrderDetails> lists = iOrderDetailsService.getMoreOrderDetails(userInfo.getId(), order.getId());
			int number = lists.size();
			double totalPrice = 0.0;
			for (OrderDetails orderDetails : lists) {
				totalPrice = totalPrice + orderDetails.getTotalPrice();
			}

			if (order.getState() == 1) {
				if (order.getDeliverState() == 1) {
					tableModel.addRow(new Object[] { order.getId(), order.getName(), number, totalPrice,
							order.getAddress(), order.getCreatedTime(), "已接单，派送中" });
				} else {
					tableModel.addRow(new Object[] { order.getId(), order.getName(), number, totalPrice,
							order.getAddress(), order.getCreatedTime(), "已支付,未接单" });
				}
			}
		}

	}

}
