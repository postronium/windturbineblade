import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Exception;


public class Profil {

	//0 = x, 1 = y
	private ArrayList<double[]> pts;
	//
	private ArrayList<int[]> lines;

	private String name;

	public Profil() {
		clear();
	}

	public void clear() {
		pts = new ArrayList<double[]>();
		lines = new ArrayList<int[]>();
	}

	public void setProfil(LinkedList<String> fileContent) {
		LinkedList<Double> numbers = new LinkedList<Double>();

		//conly copys vertor numbers into the numbers list
		for (int i = 0; i < fileContent.size(); i++) {
			try {
				Double x = Double.parseDouble(fileContent.get(i));
				if (x <= 1.0 && x >= -1.0) {
					numbers.add(x);
				}
			} catch (Exception ex) {
			}
		}

		for (int i = 0; i < numbers.size()-2; i+=2) {
			pts.add(new double[]{numbers.get(i), numbers.get(i+1)});
		}

		for (int i = 0; i < pts.size()-1; i++) {
			this.lines.add(new int[]{i, i+1});
		}
		this.lines.add(new int[]{pts.size()-1, 0});
	}

	public ArrayList<double[]> getPts() {
		return pts;
	}

	public void setPts(ArrayList<double[]> pts) {
		this.pts = pts;
	}

	public ArrayList<double[]> getAbsLines() {
		ArrayList<double[]> list = new ArrayList<double[]>();
		for (int i = 0; i < lines.size(); i++) {
			list.add(new double[]{pts.get(lines.get(i)[0])[0],
					pts.get(lines.get(i)[0])[1],
					pts.get(lines.get(i)[1])[0],
					pts.get(lines.get(i)[1])[1]
					});
		}
		return list;
	}

	public void center() {
		double maxX = -9999999;
		double minX = 9999999;
		for (int i = 0; i < pts.size(); i++) {
			if (pts.get(i)[0] > maxX) {
				maxX = pts.get(i)[0];
			}
			if (pts.get(i)[0] < minX) {
				minX = pts.get(i)[0];
			}
		}

		translate(-((maxX-minX)/2)-minX, 0);
	}

	public void translate(double vectorX, double vectorY) {
		ArrayList<double[]> newPts = new ArrayList<double[]>();

		for (int i = 0; i < pts.size(); i++) {
			double[] pt = new double[2];
			pt[0] = pts.get(i)[0]+vectorX;
			pt[1] = pts.get(i)[1]+vectorY;
			newPts.add(pt);
		}

		pts = newPts;
	}

	public Profil copy() {
		Profil p = new Profil();
		p.name = this.name;
		p.pts = this.pts;
		p.lines = this.lines;
		return p;
	}

	public Profil rotate(double alpha) {
		ArrayList<double[]> newPts = new ArrayList<double[]>();

		for (int i = 0; i < pts.size(); i++) {
			double[] pt = new double[2];
			if (true) {
				pt[0] = pts.get(i)[0]*Math.cos(alpha) - pts.get(i)[1]*Math.sin(alpha);
				pt[1] = pts.get(i)[0]*Math.sin(alpha) + pts.get(i)[1]*Math.cos(alpha);
			}
			newPts.add(pt);
		}

		pts = newPts;

		return this;
	}

}
