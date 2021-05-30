import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class User {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				SaleMachine asm = new SaleMachine();
				asm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				asm.setSize(600, 700);
				asm.setResizable(false); 
				asm.setLocationRelativeTo(null); 
				asm.setVisible(true);
			}
		});
	}
}