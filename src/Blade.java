import java.util.ArrayList;


public class Blade {
	
	//x = 0,  y = 1, z = 2
	private ArrayList<double[]> points;
	//triangle faces
	private ArrayList<int[]> faces;
	
	public Blade() {
		points = new ArrayList<double[]>();
		faces = new ArrayList<int[]>();
	}
	
	public void addPoint(double[] pt) {
		points.add(pt);
	}
	
	public void addPoints(ArrayList<double[]> pts) {
		points.addAll(pts);
	}
	
	public void addFace(int[] f) {
		faces.add(f);
	}
	
	public void addFaces(ArrayList<int[]> faces) {
		this.faces.addAll(faces);
	}
	
	public ArrayList<double[]> getPoints() {
		return this.points;
	}
	
	public void setPoints(ArrayList<double[]> pts) {
		this.points = pts;
	}
	
	public void setFaces(ArrayList<int[]> f) {
		this.faces = f;
	}
	
	public ArrayList<int[]> getFaces() {
		return this.faces;
	}

}
