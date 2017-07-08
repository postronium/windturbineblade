import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class BladeExporter {
	
	private String fileName;
	
	public BladeExporter(String fileName) {
		this.fileName = fileName;
	}
	
	public void export(Blade b) {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("#Generated by blade creator v0.1");
		lines.add("mtllib blade.mtl");
		lines.add("o blade");
		
		lines.addAll(generateVectorLines(b));
		lines.add("usemtl None");
		lines.add("s off");
		lines.addAll(generateFaceLines(b));
		
		try {
			writeToFile(lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToFile(ArrayList<String> lines) throws IOException {
		File fout = new File(fileName);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (int i = 0; i < lines.size(); i++) {
			bw.write(lines.get(i));
			bw.newLine();
		}
	 
		bw.close();
	}
	
	private ArrayList<String> generateVectorLines(Blade b) {
		ArrayList<String> lines = new ArrayList<String>();
		
		for (int i = 0; i < b.getPoints().size(); i++) {
			double[] pts = b.getPoints().get(i);
			String l = "v "+pts[0]+" "+pts[1]+" "+pts[2];
			lines.add(l);
		}
		
		return lines;
	}
	
	private ArrayList<String> generateFaceLines(Blade b) {
		ArrayList<String> lines = new ArrayList<String>();
		
		for (int i = 0; i < b.getFaces().size(); i++) {
			int[] f = b.getFaces().get(i);
			String l = "f "+f[0]+" "+f[1]+" "+f[2];
			lines.add(l);
		}
		
		return lines;
	}

}
