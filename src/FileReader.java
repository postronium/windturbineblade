import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;


public class FileReader {

	private String source;

	public FileReader(String source) {
		this.source = source;
	}

	public LinkedList<String> getText() {
		LinkedList<String> lines = new LinkedList<String>();
		Scanner sc = null;

		try {
			sc = new Scanner(new File(source));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(sc.hasNext()) {
			lines.add(sc.next()+" ");
		}

		sc.close();

		return lines;
	}

}
