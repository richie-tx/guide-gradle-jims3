/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mchowdhury
 *
 */
public class FileUtility{

	/**
	 * @param fileName
	 * @param data
	 */
	protected void write2File(String fileName, String data) throws IOException{
		File file = new File(fileName);
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		out.write(data);
	    out.close();
	}
	
	/**
	 * @param filePath 
	 */
	protected String readFileAsString(String filePath) throws IOException{
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[50000];
		int numRead=0;
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
	
	protected void copy(String readFrom, String writeTo) throws IOException {
		File inputFile = new File(readFrom);
	    File outputFile = new File(writeTo);

	    FileReader in = new FileReader(inputFile);
	    FileWriter out = new FileWriter(outputFile);
	    int c;

	    while ((c = in.read()) != -1)
	      out.write(c);
        in.close();
	    out.close();
	  }

}