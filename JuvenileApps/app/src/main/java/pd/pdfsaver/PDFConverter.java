/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.util.logging.Logger;

/**
 * @author mchowdhury
 *
 */
public class PDFConverter extends FileUtility{
	/**
	 * @param writeDir2
	 */
	protected void convertText2PDF(String writeDir2, Logger log) {	    
		long startTime = System.currentTimeMillis();
		log.info("PDF Conversion Start=" + new Timestamp(startTime));
		File dir = new File(writeDir2);
		
	    if(dir != null && dir.isDirectory()){
	    	File [] files = dir.listFiles(fileFilter);
            for(int i=0; i<files.length; i++ ){
	        	File file = files[i];
	        	if(file != null){
	        		String readFile = file.getAbsolutePath();
	        		String tempFileName [] = readFile.split(".txt");
	        		StringBuffer destFile = new StringBuffer(tempFileName [0]);
	        		destFile.append(".pdf");
	        		// step 1
	        	    Document document = new Document(PageSize.A4, 36, 24, 36, 36);
	    	        
	        	    try {
				    	// step 2
						PdfWriter.getInstance(document, new FileOutputStream(destFile.toString()));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						log.severe("Problem in converting PDF " + file.getName());
					} catch (DocumentException e) {
						e.printStackTrace();
						log.severe("Problem in converting PDF " + file.getName());
					}
	        	    // step 3
	        	    document.open();
	        	    // step 4: define a monospaced font so that formatting is preserved:
	        	    Font font = FontFactory.getFont(FontFactory.COURIER, 11);
	        	    // read the contents of the file:
	        	    String content = "";
					try {
						content = readFileAsString(readFile);
					} catch (IOException e1) {
						e1.printStackTrace();
						log.severe("Problem in converting PDF " + file.getName());
					}
					// wrap the content in a Paragraph
	        	    Paragraph p = new Paragraph(content, font);
	        	    try {
						// add the paragraph to the document
						document.add(p);
					} catch (DocumentException e2) {
						e2.printStackTrace();
						log.severe("Problem in converting PDF " + file.getName());
					}
	        	    // step 5
	        	    document.close();
	        	}
		        System.gc();
		        file.delete();
	        }
			log.info("Totla Number of PDF Converted=" + files.length);
	    } 
	    log.info("PDF Conversion Elapsed Time=" + (System.currentTimeMillis() - startTime)/(60*1000F) + " m");
   }
	
    // This filter only returns a file which ends with .txt
    FileFilter fileFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.getName().endsWith(".txt");
        }
    };
}