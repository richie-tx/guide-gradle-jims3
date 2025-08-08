/*
 * Created on Oct 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommonCollectionsResponseEvent extends ResponseEvent {
	
	private Map commonCodeTables;
	

	public CommonCollectionsResponseEvent() {
		commonCodeTables = new HashMap();
	}
	
	public void addCodeTableData(String codeTableName, ArrayList codes) {
		commonCodeTables.put(codeTableName, codes);
	}
	
	public Map getCommonCodeTableDataMap() {
		return commonCodeTables;
	}
	
	public ArrayList getCodeTableData(String codeTableName) {
		ArrayList codes = (ArrayList)commonCodeTables.get(codeTableName);
		return codes;
	}

}
