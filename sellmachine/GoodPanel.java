import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * @author th 商品面板，包含商品的图片和名称，以及是否选中的复选框
 */
class GoodPanel extends JPanel {

	private static final long serialVersionUID = 4364216395712746083L;
	private static final String TIP = "单击图片可以选择购买或者取消购买";

	private String picPath;
	private String name;
	private JTextField txt_Price;
	private double price;
	private JLabel lbl_Pic;
	private JCheckBox cbox_Select;
	private int goodId;
	private int goodCount;
	private JLabel lbl_Remain;
	private JTextField txt_Remain;

	GoodPanel(String picPath, double price, String name, int goodId, int goodCount) {
		this.picPath = picPath;
		this.price = price;
		this.name = name;
		this.goodId = goodId;
		this.goodCount = goodCount;
		this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 225, 0), 2)));
		this.setLayout(new BorderLayout(0, 0));
		lbl_Pic = new JLabel();
		this.add(lbl_Pic, BorderLayout.CENTER);
		lbl_Pic.setToolTipText(TIP);
		// 单击图片可以选择购买或者取消购买；
		lbl_Pic.addMouseListener(mouseAdapter);

		JPanel panel_Prop = new JPanel();
		this.add(panel_Prop, BorderLayout.SOUTH);

		JLabel lbl_Name = new JLabel(this.name);
		panel_Prop.add(lbl_Name);

		txt_Price = new JTextField();
		txt_Price.setEditable(false);
		panel_Prop.add(txt_Price);
		txt_Price.setColumns(2);
		txt_Price.setText(""+this.price);


		cbox_Select = new JCheckBox("购买");
		if (this.goodCount <= 0) {
			cbox_Select.setSelected(false);
		}
		panel_Prop.add(cbox_Select);

		lbl_Remain = new JLabel("剩余：");
		panel_Prop.add(lbl_Remain);
		txt_Remain = new JTextField();
		txt_Remain.setEditable(false);
		panel_Prop.add(txt_Remain);
		txt_Remain.setColumns(2);
		lbl_Remain.setVisible(false);
		txt_Remain.setVisible(false);
		initPic();
	}

	/**
	 * 图片的响应事件
	 */
	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (goodCount > 0)
				cbox_Select.setSelected(!cbox_Select.isSelected());
		}
	};

	/**
	 * 设置商品数量
	 * 
	 * @param goodCount
	 */
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
		if (this.goodCount <= 0) {
			cbox_Select.setEnabled(false);
		} else {
			if (!cbox_Select.isEnabled()) {
				cbox_Select.setEnabled(true);
			}
		}
	}

	/**
	 * 初始化显示的商品图片
	 */
	private void initPic() {
		if (this.picPath == null) {
			return;
		}
		new Thread() {
			@Override
			public void run() {
				int width = getWidth();
				while (width == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					width = getWidth();
				}
				int height = getHeight();
				File input = new File(picPath);
				if (!input.exists()) {
					return;
				}
				try {
					BufferedImage image = ImageIO.read(input);
					ImageIcon bgIcon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
					lbl_Pic.setIcon(bgIcon);
					repaint();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 是否购买该商品
	 * 
	 * @return
	 */
	public boolean isSelected() {
		return cbox_Select.isSelected();
	}

	/**
	 * 获取商品编号
	 * 
	 * @return
	 */
	public int getGoodId() {
		return goodId;
	}

	/**
	 * 购买完毕后需要重置
	 */
	public void reInit() {
		cbox_Select.setSelected(false);
	}

	/**
	 * 显示商品余量
	 */ 
	public int showRemain() {
		new Thread() {
			@Override
			public void run() {
				lbl_Remain.setVisible(true);
				txt_Remain.setText(20-goodCount + "");
				txt_Remain.setVisible(true);
				repaint();
				try {
					Thread.sleep(5000);
					lbl_Remain.setVisible(false); 
					txt_Remain.setVisible(false);
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		return goodCount;
	}
}
