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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.util.DateUtils;

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

	// 将商品商品service层注入进来
	private ICommodityService iCommodityService = new CommodityServiceImpl();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;

	private DefaultTableModel tableModel;
	private JLabel label_proPage;
	private JLabel label_nextPage;
	private JLabel label_nowPage;

	// 初始化pageSize 和 pageNum
	private int pageNum = 1;
	private int pageSize = 10;
	private int pageSum ;

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
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCommodityJF addCommodityJF = new AddCommodityJF();
				addCommodityJF.setVisible(true);
			}
		});

		btn_update = new JButton("修改");
		btn_update.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_update.setBounds(21, 162, 74, 29);
		contentPane.add(btn_update);
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowNum = table.getSelectedRow();
				if (rowNum >= 0) {
					int id = (int) tableModel.getValueAt(rowNum, 0);
					UpdateCommodityJF.commodityId = id;
					UpdateCommodityJF uJf = new UpdateCommodityJF();
					uJf.setVisible(true);
					CommodityManageJF.this.dispose();
				} else {
					JOptionPane.showMessageDialog(CommodityManageJF.this, "未选中任何行");
				}
				initModel(pageNum,pageSize);
			}
		});

		btn_offShelf = new JButton("下架");
		btn_offShelf.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_offShelf.setBounds(21, 232, 74, 29);
		contentPane.add(btn_offShelf);
		btn_offShelf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rowNum = table.getSelectedRows();
				if (rowNum.length > 0) {
					int num = JOptionPane.showConfirmDialog(CommodityManageJF.this, "确定下架选中的商品", "提示",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (num == 0) {
						// 定义result用来计数选中的每一行是否真的被更新了
						int result = 0;
						for (int i = 0; i < rowNum.length; i++) {
							int id = (int) tableModel.getValueAt(rowNum[i], 0);
							System.out.println(id);
							boolean flag = iCommodityService.updateCommodityState(id, 0);
							if (flag) {
								result++;
							}
						}
						// 计数结果和选中行数大小相同 则说明全部操作成功
						if (result == rowNum.length) {
							JOptionPane.showMessageDialog(CommodityManageJF.this, "操作成功");
						} else {
							JOptionPane.showMessageDialog(CommodityManageJF.this, "操作不成功,部分下架");
						}
						initModel(pageNum,pageSize);
					}
				} else {
					JOptionPane.showConfirmDialog(CommodityManageJF.this, "未选中任何行", "提示", JOptionPane.YES_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btn_delete = new JButton("删除");
		btn_delete.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_delete.setBounds(21, 301, 74, 29);
		contentPane.add(btn_delete);
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rowNum = table.getSelectedRows();
				if (rowNum.length > 0) {
					int num = JOptionPane.showConfirmDialog(CommodityManageJF.this, "确定要删除，不可恢复", "提示",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (num == 0) {
						int result = 0;
						for (int i = 0; i < rowNum.length; i++) {
							int id = (int) tableModel.getValueAt(rowNum[i], 0);
							boolean flag = iCommodityService.deleteCommodity(id);
							if (flag) {
								result++;
							}
						}
						if (result == rowNum.length) {
							JOptionPane.showMessageDialog(CommodityManageJF.this, "操作成功");
						} else {
							JOptionPane.showMessageDialog(CommodityManageJF.this, "操作不成功,部分删除");
						}
					}
					initModel(pageNum,pageSize);
				} else {
					JOptionPane.showMessageDialog(CommodityManageJF.this, "没选中任何行");
				}
			}
		});

		panel = new JPanel();
		panel.setBounds(113, 49, 661, 374);
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 641, 354);
		panel.add(scrollPane);

		table = new JTable();
		table.setRowHeight(30);
		String[] objs = new String[] { "编号", "名称", "状态", "价格", "介绍", "图片", "上架时间", "更新时间" };
		tableModel = new DefaultTableModel(objs, 0);
		initModel(pageNum,pageSize);
		// table的点击事件
		// table.addMouseListener(new MouseClickEvent(table, tableModel));

		label_proPage = new JLabel("上一页");
		label_proPage.setFont(new Font("楷体", Font.PLAIN, 15));
		label_proPage.setBounds(293, 433, 57, 18);
		contentPane.add(label_proPage);
		label_proPage.addMouseListener(new MouseClickProPage());


		label_nextPage = new JLabel("下一页");
		label_nextPage.setFont(new Font("楷体", Font.PLAIN, 15));
		label_nextPage.setBounds(446, 435, 57, 18);
		contentPane.add(label_nextPage);
		label_nextPage.addMouseListener(new MouseClickNextPage());

		//显示当前页数 和 总页数
		pageSum = iCommodityService.getMoreCommodityInfos().size();
		label_nowPage = new JLabel("");
		label_nowPage.setText(pageNum+" / "+(pageSum/pageSize));
		label_nowPage.setFont(new Font("楷体", Font.PLAIN, 15));
		label_nowPage.setBounds(360, 433, 68, 18);
		contentPane.add(label_nowPage);

	}

	// 全部数据查询
	public void initModel(int pageNum,int pageSize) {
		tableModel.setRowCount(0);
		for (Commodity commodity : iCommodityService.getMoreCommodityBySize(pageNum, pageSize)) {
			tableModel.addRow(new Object[] { commodity.getId(), commodity.getName(), commodity.getState(),
					commodity.getPrice(), commodity.getDescribe(), commodity.getPricture(), commodity.getCreatedTime(),
					commodity.getUpdatedTime() });
		}
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
	}
	
	
	
	
	class MouseClickNextPage implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (pageNum < (pageSum/pageSize)) {
				pageNum++;
			}
			initModel(pageNum,pageSize);
			label_nowPage.setText(pageNum+" / "+(pageSum/pageSize));
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
	}

	class MouseClickProPage implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (pageNum > 1) {
				pageNum--;
			}
			initModel(pageNum,pageSize);
			label_nowPage.setText(pageNum+" / "+(pageSum/pageSize));
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
		
	}
}



