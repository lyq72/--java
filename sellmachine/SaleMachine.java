import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;


import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.util.LinkedList;

public class SaleMachine extends JFrame {
	private static final long serialVersionUID = 6576104659382463412L;
	private static final String ADMIN = "ADMIN"; // 管理员密码

	private static LinkedList<DrinkBasicInfo> drinkList;


	private double userMoneySum; // 用户投入的钱数
	private int userOneCount; // 用户一元计数
	private int userFiveCount; // 用户五毛计数
	private double sysMoneySum; // 系统的总钱数
	private int sysOneCount = 2; // 系统一元计数
	private int sysFiveCount = 20; // 系统五毛计数
	private JTextField txt_UserMoney;
	
	//图形界面相关
	private JButton btn_UserFive;
	private JButton btn_UserOne;
	private JButton btn_Show;
	private JButton btn_Buy;
	private JButton btn_Cancle;
	private JButton btn_Admin;
	private JPanel panel_Admin;
	private JLabel lbl_SysMoneySum;
	private JTextField txt_SysSum;
	private JLabel lbl_SysOneSum;
	private JTextField txt_SysOne;
	//private JLabel lbl_SysFiveSum;
	//private JTextField txt_SysFive;
	private JButton btn_Save;
	private JButton btn_Logout;

	public SaleMachine() {
		drinkList = new LinkedList<DrinkBasicInfo>();


		drinkList.add(new DrinkBasicInfo(0, 3, 20, "可乐", "G:\\\\数据结构\\\\课程设计\\\\可乐.jpg", 0, 0));
		drinkList.add(new DrinkBasicInfo(1, 3.5, 20, "咖啡", "G:\\\\数据结构\\\\课程设计\\\\咖啡.jpg", 1, 0));
		drinkList.add(new DrinkBasicInfo(2, 4, 20, "橙汁", "G:\\\\数据结构\\\\课程设计\\\\橙汁.jpg", 0, 1));
		drinkList.add(new DrinkBasicInfo(3, 5, 20, "啤酒", "G:\\\\数据结构\\\\课程设计\\\\啤酒.jpg", 1, 1));

		setTitle("饮料自动售货机");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel_Good = new JPanel();
		GridBagConstraints gbc_panel_Good = new GridBagConstraints();
		gbc_panel_Good.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Good.fill = GridBagConstraints.BOTH;
		gbc_panel_Good.gridx = 0;
		gbc_panel_Good.gridy = 0;
		getContentPane().add(panel_Good, gbc_panel_Good);
		GridBagLayout gbl_panel_Good = new GridBagLayout();
		gbl_panel_Good.columnWidths = new int[] { 230, 230, 0 };
		gbl_panel_Good.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_Good.columnWeights = new double[] { 0.5, 0.5, Double.MIN_VALUE };
		gbl_panel_Good.rowWeights = new double[] { 0.5, 0.5, Double.MIN_VALUE };
		panel_Good.setLayout(gbl_panel_Good);
		for (int i = 0; i < 4; i++)
			panel_Good.add(drinkList.get(i).getGoodPanel(), drinkList.get(i).getGridBagConstraints1());

		JPanel panel_Btn = new JPanel();
		GridBagConstraints gbc_panel_Btn = new GridBagConstraints();
		gbc_panel_Btn.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_Btn.gridx = 0;
		gbc_panel_Btn.gridy = 1;
		getContentPane().add(panel_Btn, gbc_panel_Btn);
		GridBagLayout gbl_panel_Btn = new GridBagLayout();
		gbl_panel_Btn.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_Btn.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_Btn.columnWeights = new double[] { 0.25, 0.25, 0.25, 0.25, Double.MIN_VALUE };
		gbl_panel_Btn.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_Btn.setLayout(gbl_panel_Btn);

		JLabel lbl_UserMoney = new JLabel("投入金额（元）：");
		GridBagConstraints gbc_lbl_UserMoney = new GridBagConstraints();
		gbc_lbl_UserMoney.insets = new Insets(5, 5, 5, 5);
		gbc_lbl_UserMoney.gridx = 0;
		gbc_lbl_UserMoney.gridy = 0;
		panel_Btn.add(lbl_UserMoney, gbc_lbl_UserMoney);

		txt_UserMoney = new JTextField();
		txt_UserMoney.setEditable(false);
		GridBagConstraints gbc_txt_UserMoney = new GridBagConstraints();
		gbc_txt_UserMoney.insets = new Insets(5, 0, 5, 5);
		gbc_txt_UserMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_UserMoney.gridx = 1;
		gbc_txt_UserMoney.gridy = 0;
		panel_Btn.add(txt_UserMoney, gbc_txt_UserMoney);
		txt_UserMoney.setColumns(10);

		btn_UserOne = new JButton("投入一元硬币");
		GridBagConstraints gbc_btn_UserOne = new GridBagConstraints();
		gbc_btn_UserOne.insets = new Insets(5, 0, 5, 5);
		gbc_btn_UserOne.gridx = 2;
		gbc_btn_UserOne.gridy = 0;
		panel_Btn.add(btn_UserOne, gbc_btn_UserOne);
		btn_UserOne.addActionListener(handler);

		btn_Show = new JButton("查看");
		btn_Show.setToolTipText("查看自动售货机中的商品数量，显示五秒后关闭");
		GridBagConstraints gbc_btn_Show = new GridBagConstraints();
		gbc_btn_Show.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Show.gridx = 0;
		gbc_btn_Show.gridy = 1;
		panel_Btn.add(btn_Show, gbc_btn_Show);
		btn_Show.addActionListener(handler);

		btn_Buy = new JButton("购买");
		GridBagConstraints gbc_btn_Buy = new GridBagConstraints();
		gbc_btn_Buy.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Buy.gridx = 1;
		gbc_btn_Buy.gridy = 1;
		panel_Btn.add(btn_Buy, gbc_btn_Buy);
		btn_Buy.addActionListener(handler);

		btn_UserFive = new JButton("投入五毛硬币");
		GridBagConstraints gbc_btn_UserFive = new GridBagConstraints();
		gbc_btn_UserFive.insets = new Insets(5, 0, 5, 0);
		gbc_btn_UserFive.gridx = 3;
		gbc_btn_UserFive.gridy = 0;
		panel_Btn.add(btn_UserFive, gbc_btn_UserFive);
		btn_UserFive.addActionListener(handler);

		btn_Cancle = new JButton("取消");
		GridBagConstraints gbc_btn_Cancle = new GridBagConstraints();
		gbc_btn_Cancle.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Cancle.gridx = 2;
		gbc_btn_Cancle.gridy = 1;
		panel_Btn.add(btn_Cancle, gbc_btn_Cancle);
		btn_Cancle.addActionListener(handler);

		btn_Admin = new JButton("登录");
		btn_Admin.setToolTipText("需要登录密码，登录后查看饮料销售信息。");
		GridBagConstraints gbc_btn_Admin = new GridBagConstraints();
		gbc_btn_Admin.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Admin.gridx = 3;
		gbc_btn_Admin.gridy = 1;
		panel_Btn.add(btn_Admin, gbc_btn_Admin);
		btn_Admin.addActionListener(handler);

		panel_Admin = new JPanel();
		panel_Admin.setBorder(new TitledBorder(null, "管理员界面", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel_Admin, gbc_panel);
		panel_Admin.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_Admin.add(tabbedPane, BorderLayout.NORTH);

		panel_AdminMoney = new JPanel();
		tabbedPane.addTab("饮料销售信息", null, panel_AdminMoney, null);
		GridBagLayout gbl_panel_AdminMoney = new GridBagLayout();
		gbl_panel_AdminMoney.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_AdminMoney.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_AdminMoney.columnWeights = new double[] { 0.0, 0.5, 0.0, 0.5, Double.MIN_VALUE };
		gbl_panel_AdminMoney.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_AdminMoney.setLayout(gbl_panel_AdminMoney);

		lbl_SysMoneySum = new JLabel("总销售额：");
		GridBagConstraints gbc_lbl_SysMoneySum = new GridBagConstraints();
		gbc_lbl_SysMoneySum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SysMoneySum.anchor = GridBagConstraints.EAST;
		gbc_lbl_SysMoneySum.gridx = 0;
		gbc_lbl_SysMoneySum.gridy = 0;
		panel_AdminMoney.add(lbl_SysMoneySum, gbc_lbl_SysMoneySum);

		txt_SysSum = new JTextField();
		txt_SysSum.setEditable(false);
		GridBagConstraints gbc_txt_SysSum = new GridBagConstraints();
		gbc_txt_SysSum.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SysSum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SysSum.gridx = 1;
		gbc_txt_SysSum.gridy = 0;
		panel_AdminMoney.add(txt_SysSum, gbc_txt_SysSum);
		txt_SysSum.setColumns(10);

		lbl_SysOneSum = new JLabel("总销售量：");
		GridBagConstraints gbc_lbl_SysOneSum = new GridBagConstraints();
		gbc_lbl_SysOneSum.anchor = GridBagConstraints.EAST;
		gbc_lbl_SysOneSum.insets = new Insets(0, 5, 5, 5);
		gbc_lbl_SysOneSum.gridx = 0;
		gbc_lbl_SysOneSum.gridy = 1;
		panel_AdminMoney.add(lbl_SysOneSum, gbc_lbl_SysOneSum);

		txt_SysOne = new JTextField();
		txt_SysOne.setEditable(false);
		GridBagConstraints gbc_txt_SysOne = new GridBagConstraints();
		gbc_txt_SysOne.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SysOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SysOne.gridx = 1;
		gbc_txt_SysOne.gridy = 1;
		//txt_SysOne.setToolTipText("可以输入修改后的数量，点击保存按钮完成系统中一元数量的修改。");
		panel_AdminMoney.add(txt_SysOne, gbc_txt_SysOne);
		txt_SysOne.setColumns(10);

		/*lbl_SysFiveSum = new JLabel("系统五元数量：");
		GridBagConstraints gbc_lbl_SysFiveSum = new GridBagConstraints();
		gbc_lbl_SysFiveSum.anchor = GridBagConstraints.EAST;
		gbc_lbl_SysFiveSum.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_SysFiveSum.gridx = 0;
		gbc_lbl_SysFiveSum.gridy = 2;
		panel_AdminMoney.add(lbl_SysFiveSum, gbc_lbl_SysFiveSum);

		txt_SysFive = new JTextField();
		GridBagConstraints gbc_txt_SysFive = new GridBagConstraints();
		gbc_txt_SysFive.insets = new Insets(0, 0, 0, 5);
		gbc_txt_SysFive.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SysFive.gridx = 1;
		gbc_txt_SysFive.gridy = 2;
		txt_SysFive.setToolTipText("可以输入修改后的数量，点击保存按钮完成系统中一元数量的修改。");
		panel_AdminMoney.add(txt_SysFive, gbc_txt_SysFive);
		txt_SysFive.setColumns(10);*/

		/*panel_AdminGood = new JPanel();
		tabbedPane.addTab("\u4FEE\u6539\u5546\u54C1", null, panel_AdminGood, null);
		GridBagLayout gbl_panel_AdminGood = new GridBagLayout();
		gbl_panel_AdminGood.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_AdminGood.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_AdminGood.columnWeights = new double[] { 0.0, 0.5, 0.0, 0.5, Double.MIN_VALUE };
		gbl_panel_AdminGood.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_AdminGood.setLayout(gbl_panel_AdminGood);

		lbl_SysGood = new JLabel("系统总商品数量：");
		GridBagConstraints gbc_lbl_SysGood = new GridBagConstraints();
		gbc_lbl_SysGood.anchor = GridBagConstraints.EAST;
		gbc_lbl_SysGood.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SysGood.gridx = 0;
		gbc_lbl_SysGood.gridy = 0;
		panel_AdminGood.add(lbl_SysGood, gbc_lbl_SysGood);

		txt_SysGood = new JTextField();
		txt_SysGood.setEditable(false);
		GridBagConstraints gbc_txt_SysGood = new GridBagConstraints();
		gbc_txt_SysGood.insets = new Insets(0, 0, 5, 5);
		gbc_txt_SysGood.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_SysGood.gridx = 1;
		gbc_txt_SysGood.gridy = 0;
		panel_AdminGood.add(txt_SysGood, gbc_txt_SysGood);
		txt_SysGood.setColumns(10);

		for (int i = 0; i < 4; i++) {
			panel_AdminGood.add(drinkList.get(i).getjLabel(), drinkList.get(i).getGridBagConstraints2());
			panel_AdminGood.add(drinkList.get(i).getjTextField(), drinkList.get(i).getGridBagConstraints3());
		}*/
		
		JPanel panel_Statistical = new JPanel();
		
		StringBuffer sb = new StringBuffer("各销售量： ");
		for (int i = 0; i < 4; i++) {
			DrinkBasicInfo temp = drinkList.get(i);
			sb.append(temp.getName()).append("     ").append(temp.getCount());
		}
		//sb.append("销售额:     0");
		lbl_Statistical = new JLabel(sb.toString());
		panel_Statistical.add(lbl_Statistical);
		panel_Admin.add(panel_Statistical,BorderLayout.WEST);
		
		panel_BtnAdmin = new JPanel();
		FlowLayout fl_panel_BtnAdmin = (FlowLayout) panel_BtnAdmin.getLayout();
		fl_panel_BtnAdmin.setHgap(35);
		/*btn_Save = new JButton("保存");
		panel_BtnAdmin.add(btn_Save);
		btn_Save.addActionListener(handler);*/

		btn_Logout = new JButton("退出");
		panel_BtnAdmin.add(btn_Logout);
		panel_Admin.add(panel_BtnAdmin, BorderLayout.SOUTH);

		btn_Logout.addActionListener(handler);
		panel_Admin.setVisible(false);

	}
	private void lbl_StatisticalReinint() {
		StringBuffer sb = new StringBuffer("各销售量：");
		int income = 0;
		for (int i = 0; i < 4; i++) {
			DrinkBasicInfo temp = drinkList.get(i);
			//income+=temp.getSales()*temp.getPrice();
			sb.append(temp.getName()).append("     ").append(drinkList.get(i).getGoodPanel().showRemain()).append("     ");
		}
		//sb.append("销售额:    ").append(income);
		lbl_Statistical.setText(sb.toString());
	} 
	/** 
	 * 响应按钮事件
	 */
	private ActionListener handler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn_UserOne) {
				userOneCount++;
				updateUserMoney();
			} else if (e.getSource() == btn_UserFive) {
				userFiveCount++;
				updateUserMoney();
			} else if (e.getSource() == btn_Buy) {
				// 购买
				// 获取购买的商品总价，如果总价大于输入的钱数，提示用户继续投入钱
				double needSum = getGoodSum();
				if (needSum == 0) {
					JOptionPane.showMessageDialog(null, "购买失败，您没有选择任何商品，请选择需要的商品。");
					return;
				} else if (needSum > userMoneySum) {
					System.out.println(needSum + ":" + userMoneySum);

					JOptionPane.showMessageDialog(null,
							"投入的金额小于需要购买商品的总价，请再投入" + (needSum - userMoneySum) + "元或取消一些商品。");
					reinitGoodCount();
					return;
				} else {
                    // 计算需要找零的钱数
                    double remain = userMoneySum - needSum;
                    int remainFiveCount = (int)remain*2 / 5;
                    int remainOneCount = (int)remain % 5;
                    // 系统钱数和计数
                    int sysRemain =  sysFiveCount + userFiveCount- remainFiveCount;
                    if (sysRemain < 0) {
                        JOptionPane.showMessageDialog(null,
                                "抱歉，系统中没有足够的零钱，请修改够买商品或退款后使用零钱购买。");
                        return;
                    }
                    sysFiveCount = sysRemain;
                    sysOneCount = sysOneCount + userOneCount - remainOneCount;
                    sysMoneySum = sysOneCount + (double)sysFiveCount/2;

					// 修改商品的总数
					updateGoodCount();
                    // 输出购买的商品和找零的钱数
                    String result = getBuyResult(remain);
                    JOptionPane.showMessageDialog(null, result);
                    // 设置初始状态
                    reinit();
					pnlAdminUpdate();
				}
			} else if (e.getSource() == btn_Cancle) {
				// 退款
				updateUserMoney();
				if (userMoneySum > 0) {
					String remainResult = getRemianResult();
					JOptionPane.showMessageDialog(null, remainResult);
					reinit();
				} else {
					JOptionPane.showMessageDialog(null, "您投入的钱数为0，无法进行退款操作。");
				}
			} else if (e.getSource() == btn_Show) {
				// 显示剩余商品数目
				showGoodCount();
			} else if (e.getSource() == btn_Admin) {
				String input = JOptionPane.showInputDialog("请输入管理员密码，区分大小写。");

				if (ADMIN.equals(input)) {
					// 登录成功，显示管理员面板
					pnlAdminUpdate();
					panel_Admin.setVisible(true);
					repaint();
				} else {
					JOptionPane.showMessageDialog(null, "您输入的密码错误，无法进入管理员页面。");
				}
			} else if (e.getSource() == btn_Save) {
				String oneStr = txt_SysOne.getText();
				int one = Integer.parseInt(oneStr);
				/*String fiveStr = txt_SysFive.getText();
				int five = Integer.parseInt(fiveStr);
				sysOneCount = one;
				sysFiveCount = five;
				sysMoneySum = sysOneCount + sysFiveCount * 5;
				txt_SysSum.setText(sysMoneySum + "");
				int sysGoodSum = 0;
				for (int i = 0; i < 4; i++) {
					drinkList.get(i).setNumber(Integer.parseInt(drinkList.get(i).getjTextField().getText()));
					sysGoodSum += drinkList.get(i).getNumber();
				}


				pnlAdminUpdate();*/
				//JOptionPane.showMessageDialog(null, "修改成功，系统中的总余额为：" + sysMoneySum + "元；总商品数量为：" + sysGoodSum + "。");
			} else if (e.getSource() == btn_Logout) {
				panel_Admin.setVisible(false);
				repaint();
			}
		}

	};
	private JLabel lbl_Statistical;
	//private JLabel lbl_SysGood;
	//private JTextField txt_SysGood;

	private JTabbedPane tabbedPane;
	private JPanel panel_AdminMoney;
	private JPanel panel_AdminGood;
	private JPanel panel_BtnAdmin;

	/**
	 * 更新管理员面板信息
	 */
	private void pnlAdminUpdate() {
		txt_SysOne.setText(sysOneCount + "");
		txt_SysSum.setText((sysOneCount + sysFiveCount / 2)-12 + "");
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			//drinkList.get(i).getjTextField().setText("" + drinkList.get(i).getNumber());
			sum = showGoodCount();
			//drinkList.get(i).getGoodPanel().setGoodCount(drinkList.get(i).getNumber());
		}
		txt_SysOne.setText(sum + "");
		lbl_StatisticalReinint();
	}

	/**
	 * 显示商品数量
	 */
	protected int showGoodCount() {
		int goodCount = 0;
		for (int i = 0; i < 4; i++) {
		drinkList.get(i).getGoodPanel().showRemain();
		goodCount+=drinkList.get(i).getGoodPanel().showRemain();}
		return goodCount ;

	}

	/**
	 * 获取退款结果
	 * 
	 * @return
	 */
	protected String getRemianResult() {
		StringBuffer sb = new StringBuffer();
		sb.append("退款成功。您取回");
		if (userOneCount > 0) {
			sb.append(userOneCount).append("个一元硬币；");
		}

		if (userFiveCount > 0) {
			sb.append(userFiveCount).append("个五元硬币；");
		}

		return sb.toString();
	}

	/**
	 * 获取购买的商品描述
	 */
	protected String getBuyResult(double remain) {
		StringBuffer sb = new StringBuffer();
		sb.append("购买成功。您购买了");
		for (int i = 0; i < 4; i++) {
			if (drinkList.get(i).getCount() > 0)
				sb.append(drinkList.get(i).getCount()).append("瓶").append(drinkList.get(i).getName()).append("；");
		}

		if (remain >= 0) {
			sb.append("并找您" + remain + "元。");
		}
		return sb.toString();
	}

	/**
	 * 更新商品数量
	 */
	protected void updateGoodCount() {
		for (int i = 0; i < 4; i++) {
			GoodPanel temp = drinkList.get(i).getGoodPanel();
			temp.setGoodCount(drinkList.get(i).getCount());
			temp.reInit();
		}
	}

	/**
	 * 设置商品选中数为0，并初始化投入钱数等于0；
	 */
	protected void reinit() {
		reinitGoodCount();
		userOneCount = 0;
		userFiveCount = 0;
		userMoneySum = 0;
		txt_UserMoney.setText(0 + "");
	}

	/**
	 * 重新设置商品的选择数量为0
	 */
	protected void reinitGoodCount() {
		for (int i = 0; i < 4; i++)
			drinkList.get(i).setCount(0);
	}

	/**
	 * 获取购买商品需要的总钱数
	 * 
	 * @return
	 */
	protected double getGoodSum() {
		double sum = 0;
		for (int i = 0; i < 4; i++) {
			if (drinkList.get(i).getGoodPanel().isSelected()) {
				drinkList.get(i).setCount(drinkList.get(i).getCount() + 1);
				sum += (drinkList.get(i).getCount()) * drinkList.get(i).getPrice();
			}
		}

		return sum;
	}

	/**
	 * 更新用户投入的钱数
	 */
	protected void updateUserMoney() {
		userMoneySum = (userOneCount + (double)userFiveCount/2);
		txt_UserMoney.setText(userMoneySum + "");
	}

}//end SaleMachine