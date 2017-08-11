package cc.ccoder.squadStore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.util.DateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateCommodityJF extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int commodityId;
	public static int state;
	private JTextField field_name;
	private JTextField field_price;
	private JTextField field_describe;
	private JButton btn_back;
	private JButton btn_submit;
	private JLabel label_name;
	private JLabel label_price;
	private JLabel lable_describe;

	// 将商品service接口注入进来
	private ICommodityService iCommodityService = new CommodityServiceImpl();


	private CommodityManageJF commodityManageJF;

	public void setCommodityManageJF(CommodityManageJF commodityManageJF) {
		this.commodityManageJF = commodityManageJF;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCommodityJF frame = new UpdateCommodityJF();
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
	public UpdateCommodityJF() {
		setTitle("修改商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		label_name = new JLabel("名称");
		label_name.setFont(new Font("楷体", Font.PLAIN, 17));
		label_name.setBounds(120, 30, 46, 30);
		getContentPane().add(label_name);

		field_name = new JTextField();
		field_name.setBounds(176, 32, 147, 30);
		getContentPane().add(field_name);
		field_name.setColumns(10);

		label_price = new JLabel("价格");
		label_price.setFont(new Font("楷体", Font.PLAIN, 17));
		label_price.setBounds(120, 70, 46, 30);
		getContentPane().add(label_price);

		field_price = new JTextField();
		field_price.setColumns(10);
		field_price.setBounds(176, 72, 147, 30);
		getContentPane().add(field_price);

		lable_describe = new JLabel("描述");
		lable_describe.setFont(new Font("楷体", Font.PLAIN, 17));
		lable_describe.setBounds(120, 115, 46, 30);
		getContentPane().add(lable_describe);

		field_describe = new JTextField();
		field_describe.setColumns(10);
		field_describe.setBounds(176, 121, 147, 30);
		getContentPane().add(field_describe);

		btn_submit = new JButton("提交");
		btn_submit.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_submit.setBounds(84, 185, 93, 30);
		getContentPane().add(btn_submit);
		// 提交修改的商品信息
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = field_name.getText();
				String strPrice = field_price.getText();
				String describe = field_describe.getText();
				if ("".equals(name) || "".equals(strPrice) || "".equals(describe)) {
					JOptionPane.showMessageDialog(UpdateCommodityJF.this, "输入框不能够为空");
				} else {
					try {
						double price = Double.parseDouble(strPrice);
						Commodity commodity = new Commodity();
						commodity.setId(UpdateCommodityJF.this.commodityId);
						commodity.setStoreId(1);
						commodity.setName(name);
						commodity.setState(UpdateCommodityJF.this.state);
						commodity.setPrice(price);
						commodity.setDescribe(describe);
						commodity.setPricture("");
						commodity.setUpdatedTime(DateUtils.getNowTime());
						boolean flag = iCommodityService.updateCommodity(commodity);
						if (flag) {
							int num = JOptionPane.showConfirmDialog(UpdateCommodityJF.this, "修改成功，是否退出", "提示",
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							if (num == 0) {
								UpdateCommodityJF.this.dispose();
								commodityManageJF.initModel(commodityManageJF.getPageNum(), 10);
							}
						} else {
							JOptionPane.showMessageDialog(UpdateCommodityJF.this, "修改失败");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(UpdateCommodityJF.this, "输入的不是数字");
					}
				}
			}
		});

		btn_back = new JButton("返回");
		btn_back.setFont(new Font("楷体", Font.PLAIN, 17));
		btn_back.setBounds(269, 190, 93, 30);
		getContentPane().add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateCommodityJF.this.dispose();
				CommodityManageJF commodityManageJF = new CommodityManageJF();
				commodityManageJF.setVisible(true);
				commodityManageJF.initModel(1, 10);
			}
		});

		// 加载数据
		initView();
	}

	// 将传递过来的ID 查询该ID所对应的商品的信息 并将其填充到相应的控件当中
	public void initView() {
		int commodityId = this.commodityId;
		Commodity commodity = iCommodityService.getSimpleCommodity(commodityId);
		if (commodity != null) {
			this.state = commodity.getState();
			field_name.setText(commodity.getName());
			field_price.setText(commodity.getPrice().toString());
			field_describe.setText(commodity.getDescribe());
		} else {
			JOptionPane.showMessageDialog(UpdateCommodityJF.this, "加载失败");
		}

	}
}
