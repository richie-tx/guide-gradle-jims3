/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * @author mchowdhury
 *
 */
public class TextFileParser extends FileUtility{

	public static final String Identifier = "|||||";
	
	/**
	 * @param readDirectory
	 * @param writeDirectory
	 * @throws IOException
	 * 
	 */
	protected void parse(String readDirectory, String writeDirectory, Logger log) throws IOException {
		long startTime = Calendar.getInstance().getTimeInMillis();
		log.info("Parsing Start=" + new Timestamp(startTime));
	    File dir = new File(writeDirectory);
		if(dir != null && dir.isDirectory()){
			// delete the previous files
			File deleteFiles [] = dir.listFiles();
			if(deleteFiles != null){
				for(int j=0;j<deleteFiles.length;j++){
					File file = deleteFiles[j];
					file.delete();
				}
			}
		}		
		
		File direcory = new File(readDirectory);
		int bufferLength = 25000;
		if(direcory != null && direcory.isDirectory()){
	    	File [] files = direcory.listFiles(fileFilter);
	    	for(int k=0;k<files.length;k++){
	    	    File readFile = files[k];
	    	    log.info("Started Reading " + readFile.getAbsolutePath());
	    		
				StringBuffer fileData = new StringBuffer(2000);
				char[] buf = new char[bufferLength];
	    		int numRead=0;
	    		String fileName = "";
	    		String brokenFileName = "";
	    		File file = null;
	    		
	    		BufferedReader reader = new BufferedReader(new FileReader(readFile));
	    		while((numRead=reader.read(buf)) != -1){
	    			String readData = String.valueOf(buf, 0, numRead);
	    			if(!brokenFileName.equals("")){
	    				readData = brokenFileName + readData;
	    				brokenFileName = "";
	    			}
	    			if(readData == null) continue;
	    			if(readData.startsWith(Identifier) || readData.indexOf(Identifier) > 0){
	    				if(!readData.startsWith(Identifier) && readData.indexOf(Identifier) > 0){
	    					int index = readData.indexOf(Identifier);
	    					
	    					try {
	    						 write2File(fileName, readData.substring(0,index));
	    						 fileName = writeDirectory + "/" + readData.substring(index+5, index+16).trim() + ".txt";
	    						 write2File(fileName, readData.substring(index));
	    	                } catch (IOException e) {
	    	        			 log.severe("Exception " + e.getMessage());
	    	                	 e.printStackTrace();
	    	                } catch (StringIndexOutOfBoundsException e) {
	    	        			 log.severe("Exception " + e.getMessage());
	    		            	 brokenFileName = readData.substring(index);
	    	                }
	    	                buf = new char[bufferLength];
	    				}else if(readData.startsWith(Identifier)){
	    					fileName = writeDirectory + "/" + readData.substring(5,20).trim() + ".txt";
	    					try {
	    						write2File(fileName, readData.substring(0));
	    		            } catch (IOException e) {
	    		    			log.severe("Exception " + e.getMessage());
	    		                e.printStackTrace();
	    		            } 
	    		            buf = new char[bufferLength];
	    				}    
	    			}else if(readData.endsWith("||||") || readData.endsWith("|||") || readData.endsWith("||") || readData.endsWith("|") && !readData.endsWith(Identifier)){
    					int index = readData.indexOf("||||");
    					if(index < 0){
    						index = readData.indexOf("|||");
    						if(index < 0){
    							index = readData.indexOf("||");
    							if(index < 0){
    								index = readData.indexOf("|");
    							}
    						}
    					}					
    					try {
    						write2File(fileName, readData.substring(0,index));
    						brokenFileName = readData.substring(index);
     	                } catch (IOException e) {
    	                	e.printStackTrace();
   		            	    log.severe("Exception " + e.getMessage());
    	                } catch (StringIndexOutOfBoundsException e) {
    		            	 brokenFileName = readData.substring(index);
    		            	 log.severe("Exception for fileName=" + fileName + " " + e.getMessage());
    	                }
    	                buf = new char[bufferLength];
    				}else{
	    				try {
    						write2File(fileName, readData);
	                    } catch (IOException e) {
   		            	    log.severe("Exception " + e.getMessage());
	                    	e.printStackTrace();
	                    }
	                    fileData.append(readData);
	    			    buf = new char[bufferLength];
	    			}
	    		}
	    		reader.close();
           	    log.info("Finished Reading " + readFile.getAbsolutePath() + ": Created " + dir.listFiles().length + " files.");
	    	}
	    	log.info("Parsing Elapsed Time=" + (System.currentTimeMillis() - startTime)/(60*1000F) + " m");
		}		
	}
	
    // This filter only returns a file which ends with .txt
    FileFilter fileFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.getName().endsWith(".txt");
        }
    };
}