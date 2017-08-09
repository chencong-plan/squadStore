package cc.ccoder.squadStore.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IStoreService;
import cc.ccoder.squadStore.service.impl.CommodityServiceImpl;
import cc.ccoder.squadStore.util.DateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class AddCommodityJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fiel_name;
	private JTextField field_price;
	private JTextField field_describe;
	private JRadioButton rdbtn_onShelf;
	private JRadioButton rdbtn_offShelf;
	
	//将商品service注入进来
	private ICommodityService iCommodityService = new CommodityServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCommodityJF frame = new AddCommodityJF();
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
	public AddCommodityJF() {
		setTitle("新增商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_name = new JLabel("名  称");
		label_name.setFont(new Font("楷体", Font.PLAIN, 16));
		label_name.setBounds(31, 39, 64, 27);
		contentPane.add(label_name);
		
		fiel_name = new JTextField();
		fiel_name.setBounds(127, 39, 167, 27);
		contentPane.add(fiel_name);
		fiel_name.setColumns(10);
		
		JLabel label_state = new JLabel("状  态");
		label_state.setFont(new Font("楷体", Font.PLAIN, 16));
		label_state.setBounds(31, 77, 64, 27);
		contentPane.add(label_state);
		
		 rdbtn_onShelf = new JRadioButton("上架");
		rdbtn_onShelf.setBounds(126, 80, 72, 23);
		contentPane.add(rdbtn_onShelf);
	 	
	    rdbtn_offShelf = new JRadioButton("下架");
		rdbtn_offShelf.setBounds(222, 80, 72, 23);
		contentPane.add(rdbtn_offShelf);
		
		//这是ButtonGroup 并默认设置上架
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_offShelf);
		buttonGroup.add(rdbtn_onShelf);
		//默认设置上架
		rdbtn_onShelf.setSelected(true);
		
		JLabel label_price = new JLabel("售  价");
		label_price.setFont(new Font("楷体", Font.PLAIN, 16));
		label_price.setBounds(31, 126, 64, 27);
		contentPane.add(label_price);
		
		field_price = new JTextField();
		field_price.setColumns(10);
		field_price.setBounds(126, 127, 167, 27);
		contentPane.add(field_price);
		
		JLabel label_describe = new JLabel("商品介绍");
		label_describe.setFont(new Font("楷体", Font.PLAIN, 16));
		label_describe.setBounds(31, 173, 64, 27);
		contentPane.add(label_describe);
		
		field_describe = new JTextField();
		field_describe.setColumns(10);
		field_describe.setBounds(127, 177, 167, 27);
		contentPane.add(field_describe);
		
		JLabel lable_image = new JLabel("上传图片");
		lable_image.setFont(new Font("楷体", Font.PLAIN, 16));
		lable_image.setBounds(377, 105, 72, 27);
		contentPane.add(lable_image);
		
		JButton btn_add = new JButton("提交");
		btn_add.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_add.setBounds(120, 255, 93, 33);
		contentPane.add(btn_add);
		//新增商品
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = fiel_name.getText();
				String price = field_price.getText();
				String describe = field_describe.getText();
				if ("".equals(name) || "".equals(price)||"".equals(describe)) {
					JOptionPane.showConfirmDialog(AddCommodityJF.this, "信息需填写完整","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}else {
					//判断是选择的上架还是下架
					int state = 0;
					if (rdbtn_offShelf.isSelected()) {
						state = 1;
					}
					if(rdbtn_onShelf.isSelected()) {
						state = 0;
					}
					try {
						double p = Double.parseDouble(price);
						Commodity commodity = new Commodity();
						commodity.setStoreId(1);
						commodity.setName(name);
						commodity.setState(state);
						commodity.setPrice(p);
						commodity.setDescribe(describe);
						commodity.setCreatedTime(DateUtils.getNowTime());
						commodity.setUpdatedTime(DateUtils.getNowTime());
						boolean flag = iCommodityService.insertCommodity(commodity);
						if (flag) {
							int num = JOptionPane.showConfirmDialog(AddCommodityJF.this, "新增商品成功,是否退出","提示",JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE);
							if(num == 0) {
								AddCommodityJF.this.dispose();
								CommodityManageJF cJf = new CommodityManageJF();
								cJf.setVisible(true);
								cJf.initModel();
							}
						}else {
							JOptionPane.showConfirmDialog(AddCommodityJF.this, "新增商品失败","提示",JOptionPane.YES_OPTION,JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (Exception e) {
						JOptionPane.showConfirmDialog(AddCommodityJF.this, "价格只能够位数字","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			}
		});
		
		JButton btn_cancle = new JButton("取消");
		btn_cancle.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_cancle.setBounds(302, 255, 93, 33);
		contentPane.add(btn_cancle);
		btn_cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCommodityJF.this.dispose();
			}
		});
	}
}
