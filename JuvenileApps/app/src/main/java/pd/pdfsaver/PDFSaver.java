/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import pd.juvenilecase.casefile.CommonAppDocMetadata;

/**
 * @author mchowdhury
 *
 */
public class PDFSaver extends FileUtility{
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws SQLException
	 * @throws SQLException
	 * @params readDirectory.
	 */
	public void save(String readDirectory, String url, Logger log) throws ClassNotFoundException{        
		long startTime = System.currentTimeMillis();
		log.info("Saving Start=" + new Timestamp(startTime));
		File dir = new File(readDirectory);
		Connection con = null;
				
	    if(dir != null && dir.isDirectory()){
	    	File [] files = dir.listFiles(fileFilter);
	    	try {
				con = DB2Connection.getConnection(url);
			} catch (ClassNotFoundException e1) {
				log.severe("Exception=" + e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				log.severe("Exception=" + e1.getMessage());
				e1.printStackTrace();
			}
			int j=0;
			for(int i=0; i<files.length; i++ ){
	        	File file = files[i];
	        	if(file != null){
	        	    try {
	        	    	CommonAppDocMetadata pojo = createPOJO(file, readDirectory, con, log);
						if(pojo != null){							
							CasefilePOJOPersistence.insertPDF(pojo, con, log);
						}else{
							String temp [] = file.getName().split(".pdf");
							String juvenileId = temp[0];
							juvenileId = juvenileId.substring(0, juvenileId.length() - 4);
							log.info(i + ":No Casefile found for " + juvenileId);
							j++;
						}
					} catch (IOException e) {
						log.severe("IOException for " + file.getAbsolutePath());
					}	
	        	}
            }
			
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				log.severe("Exception=" + e.getMessage());
				e.printStackTrace();
			}
			log.info(j + " pdf files was not saved since they do not have juvenile or casefile in JIMS2");
	    }
	    log.info("Saving Elapsed Time=" + (System.currentTimeMillis() - startTime)/(60*1000F) + " m");
	    
    }
	
    /**
	 * @param file
	 * @return
     * @throws IOException
     * @throws ClassNotFoundException
	 */
	private CommonAppDocMetadata createPOJO(File file, String inputFrom, Connection con, Logger log) throws IOException, ClassNotFoundException {
		String temp [] = file.getName().split(".pdf");
		String juvenileId = temp[0];
		juvenileId = juvenileId.substring(0, juvenileId.length() - 4);
		CommonAppDocMetadata pojo = null;
		if(juvenileId != null && !juvenileId.equals("")){
			if(juvenileId.startsWith("0")){
				juvenileId = juvenileId.substring(1);
			}	
			
			int casefileId = CasefilePOJOPersistence.retrieveCasefile(juvenileId, con, log);
			
			if(casefileId != 0){
				byte [] content = getBytesFromFile(file);
				pojo = new CommonAppDocMetadata();
				pojo.setCasefileId("" + casefileId);
				pojo.setDocument(content);
			}else{
				String dir = inputFrom + File.separator + "copy";
				boolean success = (new File(dir)).mkdir();
				copy(file.getAbsolutePath(),(dir + File.separator + file.getName()));
			} 
			file.delete();
		}		
		return pojo;
	}

	// This filter only returns a file which ends with .pdf
    FileFilter fileFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.getName().endsWith(".pdf");
        }
    };
    
    private byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.out.println("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}