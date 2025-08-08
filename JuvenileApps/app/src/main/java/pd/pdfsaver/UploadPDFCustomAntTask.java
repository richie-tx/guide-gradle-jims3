/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author mchowdhury
 *
 */
public class UploadPDFCustomAntTask {
	private String readDir;
	private String writeDir;
	private String url;
	private String logFile;
	private Logger log = Logger.getLogger("");
		
	public void execute(){        
		try {
			// Create a new handler that uses the simple formatter
            try {
                FileHandler fh = new FileHandler(this.logFile);
                fh.setFormatter(new SimpleFormatter());
                log.addHandler(fh);
            } catch (IOException e) {
            }

            // step 1: convert the text file into multiple text file after parsing
			new TextFileParser().parse(readDir, writeDir, log);
			//step 2: Conversion Text file to PDF
			new PDFConverter().convertText2PDF(writeDir, log);
			// step 3: save pdfs
			new PDFSaver().save(writeDir, this.url, log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }

	/**
	 * @return Returns the writeDir.
	 */
	public String getWriteDir() {
		return writeDir;
	}
	/**
	 * @param writeDir The writeDir to set.
	 */
	public void setWriteDir(String writeDir) {
		this.writeDir = writeDir;
	}
	/**
	 * @return Returns the readDir.
	 */
	public String getReadDir() {
		return readDir;
	}
	/**
	 * @param readDir The readDir to set.
	 */
	public void setReadDir(String readDir) {
		this.readDir = readDir;
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return Returns the logFile.
	 */
	public String getLogFile() {
		return logFile;
	}
	/**
	 * @param logFile The logFile to set.
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
}