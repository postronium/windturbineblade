import java.util.ArrayList;


public class RotorBladeBuilder {
	
	private static final double RESOLITION = 0.01;
	private static final double WIDTH_SCALE = 1;
	
	private Profil profil;
	private Calculator calc;
	private double length;
	
	public RotorBladeBuilder(Profil profil, double wind, double rotation, double length) {
		this.profil = profil;
		calc = new Calculator(wind, rotation);
		this.length = length;
	}
	
	public Blade build() {
		Blade b = new Blade();
		
		ArrayList<Profil> profs = generateProfiles();
		
		fillBladeWithPoints(b, profs);
		
		ArrayList<int[]> faces = generateTriFaces(profs);
		
		b.addFaces(faces);
		
		return b;
	}
	
	private ArrayList<Profil> generateProfiles(){
		ArrayList<Profil> profs = new ArrayList<Profil>();
		
		for (int i = 0; i < length*(1/RESOLITION); i++) {
			profs.add(profil.copy().rotate(calc.getAngleForRadius(i*RESOLITION)));
		}
		
		return profs;
	}
	
	private void fillBladeWithPoints(Blade b, ArrayList<Profil> profils) {
		for (int i = 0; i < profils.size(); i++) {
			for (int j = 0; j < profils.get(i).getPts().size(); j++) {
				double[] d3Pt = new double[3];
				d3Pt[0] = WIDTH_SCALE*profils.get(i).getPts().get(j)[0];
				d3Pt[1] = WIDTH_SCALE*profils.get(i).getPts().get(j)[1];
				d3Pt[2] = i*RESOLITION;
				b.addPoint(d3Pt);
			}
		}
	}
	
	private ArrayList<int[]> generateTriFaces(ArrayList<Profil> profils) {
		ArrayList<int[]> faces = new ArrayList<int[]>();
		for (int i = 0; i < profils.size()-1; i++) {
			int n = profils.get(i).getPts().size();
			for (int j = 0; j < n-1; j++) {
				faces.add(new int[]{i*n+j+1, (i+1)*n+j+1, i*n+j+1+1});
				faces.add(new int[]{(i+1)*n+j+1, i*n+j+1+1, (i+1)*n+j+1+1});
			}
			faces.add(new int[]{i*n+1, (i+1)*n+1, i*n+(n-1)+1});
			faces.add(new int[]{(i+1)*n+1, i*n+(n-1)+1, (i+1)*n+(n-1)+1});
		}
		
		return faces;
	}
	
	private ArrayList<int[]> generateQuadFaces(ArrayList<Profil> profils) {
		ArrayList<int[]> faces = new ArrayList<int[]>();
		for (int i = 0; i < profils.size()-1; i++) {
			int n = profils.get(i).getPts().size();
			for (int j = 0; j < n-1; j++) {
				faces.add(new int[]{i*n+j, (i+1)*n+j, i*n+j+1});
				faces.add(new int[]{(i+1)*n+j, i*n+j+1, (i+1)*n+j+1});
			}
			faces.add(new int[]{i*n, (i+1)*n, i*n+(n-1)});
			faces.add(new int[]{(i+1)*n, i*n+(n-1), (i+1)*n+(n-1)});
		}
		
		return faces;
	}
}
