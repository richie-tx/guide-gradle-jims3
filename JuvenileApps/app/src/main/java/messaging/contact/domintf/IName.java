/*
 * Created on Feb 25, 2006
 *
 */
package messaging.contact.domintf;

/**
 * @author Jim Fisher
 *
 */
public interface IName
{
	String getPrefix();
	String getFirstName();
	String getLastName();
	String getMiddleName();
	String getSuffix();
	void setPrefix(String string);
	void setFirstName(String string);
	void setLastName(String string);
	void setMiddleName(String string);
	void setSuffix(String string);
	
	
	// TODO The methods below need to be removed eventually : Note the Jsp pages
	// need to be fixed first to ensure they are not calling these methods
	String getCompleteFullNameFirst();
	String getCompleteFullNameLast();
	String getFormattedName();
	String getFullNameFirst();
	String getFullNameLast();
	
	void clear();
}
