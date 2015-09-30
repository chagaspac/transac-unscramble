package br.com.eigmercados.common;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Utils {

	private Utils(){
	}
	private static Utils INSTANCE = null;

	public static Utils getInstance(){
		if(INSTANCE == null) INSTANCE = new Utils();
		return INSTANCE;
	}

	public String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
	}

	public void writeTextToFile(String filename, String text) throws IOException {

		FileWriter fw = null;
		BufferedWriter out = null;
		File f = null;
		try {
			f = new File(filename);
			fw = new FileWriter(f);
			out = new BufferedWriter(fw);
			out.write(text);
			out.close();
		} catch (IOException e) {
			throw e;
		} finally {
			if(null != fw) try { fw.close(); } catch (IOException e) { throw e; }
			if(null != out) try { out.close(); } catch (IOException e) { throw e; }
		}
	}

}
