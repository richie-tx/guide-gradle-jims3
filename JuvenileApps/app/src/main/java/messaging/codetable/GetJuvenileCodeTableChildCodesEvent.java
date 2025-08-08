package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCodeTableChildCodesEvent extends RequestEvent{
	
private String codeTableName;

/**
 * @return the codeTableName
 */
public String getCodeTableName() {
	return codeTableName;
}

/**
 * @param codeTableName the codeTableName to set
 */
public void setCodeTableName(String codeTableName) {
	this.codeTableName = codeTableName;
}	

}
