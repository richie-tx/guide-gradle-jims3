/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.scheduling;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RequestAcknowledgementEvent extends TaskEvent
{
public String warrantNumber;
public String officerName;
public String fileStampDate;

/**
 * @return
 */
public String getFileStampDate()
{
	return fileStampDate;
}

/**
 * @return
 */
public String getOfficerName()
{
	return officerName;
}

/**
 * @return
 */
public String getWarrantNumber()
{
	return warrantNumber;
}

/**
 * @param string
 */
public void setFileStampDate(String string)
{
	fileStampDate = string;
}

/**
 * @param string
 */
public void setOfficerName(String string)
{
	officerName = string;
}

/**
 * @param string
 */
public void setWarrantNumber(String string)
{
	warrantNumber = string;
}

}
