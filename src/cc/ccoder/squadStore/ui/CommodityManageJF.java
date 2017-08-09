package cc.ccoder.squadStore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import javax.swing.JSplitPane;

public class CommodityManageJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JButton btn_add;
	private JButton btn_update;
	private JButton btn_offShelf;
	private JButton btn_delete;
	
	
	//将商品商品service层注入进来
	private ICommodityService iCommodityService = new CommodityServiceImpl();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommodityManageJF frame = new CommodityManageJF();
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
	public CommodityManageJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSquadstore = new JLabel("SquadStore商品后台管理");
		lblSquadstore.setFont(new Font("楷体", Font.PLAIN, 19));
		lblSquadstore.setBounds(311, 10, 220, 39);
		contentPane.add(lblSquadstore);

		btn_add = new JButton("添加");
		btn_add.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_add.setBounds(21, 95, 74, 29);
		contentPane.add(btn_add);

		btn_update = new JButton("修改");
		btn_update.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_update.setBounds(21, 162, 74, 29);
		contentPane.add(btn_update);

		btn_offShelf = new JButton("下架");
		btn_offShelf.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_offShelf.setBounds(21, 232, 74, 29);
		contentPane.add(btn_offShelf);

		btn_delete = new JButton("删除");
		btn_delete.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_delete.setBounds(21, 301, 74, 29);
		contentPane.add(btn_delete);
		
		panel = new JPanel();
		panel.setBounds(113, 49, 661, 374);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 641, 354);
		panel.add(scrollPane);
		
		table = new JTable();
		String[] objs = new String[] {
				"编号", "名称", "状态", "New column", "New column", "New column", "New column", "New column"
			};
		tableModel = new DefaultTableModel(objs, 0);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
//		scrollPane = new JScrollPane();
//		scrollPane.setBounds(105, 54, 669, 343);
//		contentPane.add(scrollPane);
//		
//		table = new JTable();
//		String[] objs = new String[] {
//				"序号", "名称", "状态", "价格", "描述", "图片", "上架时间", "更新时间"
//			};
//		tableModel = new DefaultTableModel(objs, 0);
//		table.setModel(tableModel);
//		scrollPane.setViewportView(table);
//		initalModal();
//		contentPane.add(scrollPane);
//		this.add(contentPane);
	}
	
}
