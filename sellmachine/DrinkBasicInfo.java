import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DrinkBasicInfo {
	//饮料ID 价格 库存 选择数量 已售量 名称
	private int ID;
	private double Price;
	private int Number;
	private int count;
	private int sales;
	private String Name;
	
	//图形界面相关
	private GoodPanel goodPanel;
	private GridBagConstraints gridBagConstraints1;
	private GridBagConstraints gridBagConstraints2;
	private GridBagConstraints gridBagConstraints3;
	private JLabel jLabel;
	private JTextField jTextField;

	public DrinkBasicInfo(int iD, double price, int number, String name, String img, int gridx, int gridy) {
		super();
		count = 0;
		ID = iD;
		Price = price; 
		Number = number;
		Name = name;
		sales = 0;
		this.goodPanel = new GoodPanel(img, price, name, ID, Number);
		this.gridBagConstraints1 = new GridBagConstraints();
		this.gridBagConstraints2 = new GridBagConstraints();
		this.gridBagConstraints3 = new GridBagConstraints();
		this.jTextField = new JTextField();
		jTextField.setColumns(10);
		//jTextField.setToolTipText("可以输入修改后的数量，点击保存按钮完成系统中饮料数量的修改。");
		this.jLabel = new JLabel(name + "数量:");
		gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints2.insets = new Insets(0, 5, 5, 0);
		gridBagConstraints3.insets = new Insets(0, 0, 5, (gridx + 1) % 2 * 5);
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridx = gridx;
		gridBagConstraints1.gridy = gridy;
		gridBagConstraints2.gridx = gridx * 2;
		gridBagConstraints2.gridy = gridy + 1;
		gridBagConstraints3.gridx = gridx * 2 + 1;
		gridBagConstraints3.gridy = gridy + 1;

	}

	@Override
	public String toString() {
		return "DrinkBasicInfo [ID=" + ID + ", Price=" + Price + ", Number=" + Number + ", Name=" + Name
				+ ", goodPanel=" + goodPanel + ", gridBagConstraints=" + gridBagConstraints1 + "]";
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public GridBagConstraints getGridBagConstraints1() {
		return gridBagConstraints1;
	}

	public void setGridBagConstraints1(GridBagConstraints gridBagConstraints1) {
		this.gridBagConstraints1 = gridBagConstraints1;
	}

	public GridBagConstraints getGridBagConstraints2() {
		return gridBagConstraints2;
	}

	public void setGridBagConstraints2(GridBagConstraints gridBagConstraints2) {
		this.gridBagConstraints2 = gridBagConstraints2;
	}

	public GridBagConstraints getGridBagConstraints3() {
		return gridBagConstraints3;
	}

	public void setGridBagConstraints3(GridBagConstraints gridBagConstraints3) {
		this.gridBagConstraints3 = gridBagConstraints3;
	}

	public JLabel getjLabel() { 
		return jLabel;
	}

	public void setjLabel(JLabel jLabel) {
		this.jLabel = jLabel;
	}

	public JTextField getjTextField() {
		return jTextField;
	}

	public void setjTextField(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public GoodPanel getGoodPanel() {
		return goodPanel;
	}

	public void setGoodPanel(GoodPanel goodPanel) {
		this.goodPanel = goodPanel;
	}

}// end DrinkBasicInfo
