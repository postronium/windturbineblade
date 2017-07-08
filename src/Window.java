import javax.swing.JFrame;


public class Window extends JFrame {
	
	public Window() {
		super();
		super.setTitle("RotorBlade");
		super.setContentPane(new ControlPanel());
		super.setSize(500, 420);
		super.show();
	}

}
