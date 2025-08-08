package pd.juvenilecase;// no longer in use. Migrated to SM. Refer US #87188.
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/

/*
 * Created on Jun 19, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package pd.juvenilecase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mojo.km.utilities.DateUtil;

*//**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *//*
public class FileUtility
{
	*//**
	 * @param jjsService
	 * @param string
	 * @return
	 *//*
	protected String logMessage(JJSService jjsService, String errorMessage) {
		StringBuffer buff = new StringBuffer(DateUtil.getCurrentDate().toString());
		buff.append(" caseLoadManager=");
		buff.append(jjsService.getCaseLoadManagerId());
		buff.append(" JuvenileNum=");
		buff.append(jjsService.getJuvenileNum());
		buff.append(" ReferralNum=");
		buff.append(jjsService.getReferralNum());
		buff.append(" JPOId=");
		buff.append(jjsService.getProbationOfficerId());
		buff.append(" AssignmentLevelId=");
		buff.append(jjsService.getAssignmentLevelId());
		buff.append(" UnitId=");
		buff.append(jjsService.getUnitId());
		buff.append(" ServiceDate=");
		buff.append(DateUtil.dateToString(jjsService.getServiceDate(), DateUtil.DATE_FMT_1));
		buff.append(" AddDate=");
		buff.append(DateUtil.dateToString(jjsService.getAddDate(), DateUtil.DATE_FMT_1));
		buff.append(" ");
		buff.append(errorMessage);
		buff.append("\n");
		return buff.toString();
	}
	
	*//**
	 * @param logMessage
	 * @return
	 *//*
	protected String logMessage(String logMessage) {
		StringBuffer buff = new StringBuffer(DateUtil.getCurrentDate().toString());
		buff.append(" ");
		buff.append(logMessage);
		buff.append("\n");
		return buff.toString();
	}

	*//**
	 * @param fileName
	 * @param message
	 * @throws IOException
	 *//*
	protected void writeToFile(String fileName, String message, String fileType) throws IOException {
		String newDir = "";
		if("log".equalsIgnoreCase(fileType)){
			newDir = "../../log/log/casefile/";
		}else{
			newDir = "../../log/error/casefile/";
		}
		boolean success = (new File(newDir)).mkdirs();
	    StringBuffer file = new StringBuffer(newDir);
        file.append(fileName);
	    		
	    BufferedWriter out = new BufferedWriter(new FileWriter(file.toString(), true));
	    out.write(message);
	    out.close();
	}

	*//**
	 * @param fileType
	 * @return fullyQualifiedFileName
	 *//*
	protected String getFileName(String fileType) {
		StringBuffer outputFiileName = new StringBuffer("casefileExtraction-"); 
	    outputFiileName.append(DateUtil.dateToString(DateUtil.getCurrentDate(), "MM-dd-yyyy-hh-mm-ss"));
		outputFiileName.append(".");
		outputFiileName.append(fileType);
		return outputFiileName.toString();
	}
	
	*//**
	 * @param jjsService
	 * @param errorMessage
	 * @return
	 *//*
	protected JuvenileCasefileExtractionErrorBean createErrorBean(JJSService jjsService, String errorMessage) {
		return new JuvenileCasefileExtractionErrorBean(jjsService, errorMessage);
	}
}
*/