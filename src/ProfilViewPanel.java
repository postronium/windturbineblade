import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ProfilViewPanel extends JPanel {
	
	private static final int SCALE = 400;
	private static final int SPACE_Y = 100;
	private static final int SPACE_X = 200;
	
	private Profil p;
	
	public ProfilViewPanel() {
		
	}
	
	public ProfilViewPanel(Profil p) {
		setProfil(p);
	}
	
	public void setProfil(Profil p) {
		this.p = p;
	}
	
	@Override
	public void paint(Graphics g) {
		if (p == null) {
			super.paint(g);
			return ;
		}
		
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.RED);
		ArrayList<double[]> lines = p.getAbsLines();
		for (int i = 0; i < lines.size(); i++) {
			g.drawLine(scale(lines.get(i)[0])+SPACE_X, 
					SPACE_Y-scale(lines.get(i)[1])+SPACE_Y, 
					scale(lines.get(i)[2])+SPACE_X, 
					SPACE_Y-scale(lines.get(i)[3])+SPACE_Y);
		}
//		super.paint(g);
	}
	
	private int scale(double num) {
		return (int) (num*SCALE);
	}

}
