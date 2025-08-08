package mojo.km.config;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/** @modelguid {031502D1-FF07-4994-8AE4-34F4D3A2DF54} */
public class BuildProperties extends GenericProperties {
	/** @modelguid {D2927FAB-B058-4CD5-8EED-A80BF78179C2} */
	private List contextManagers = new Vector();
	/** @modelguid {9A0B250B-2D2C-40FC-AC9B-61BEDE68080B} */
	private List replyDispatches = new Vector();
	/** @modelguid {4A50DDBF-C976-4AE1-932E-3D26F800F5F8} */
	private List requestDispatches = new Vector();
	/** @modelguid {18AD2B5C-F244-4FC9-AAD0-3D7BE221531E} */
	private List TOPLinkMaps = new Vector();
	
	/** @modelguid {3E6417CF-F0D5-4FDA-8071-BBBA2C6462B8} */
	public static BuildProperties getInstance() {
		return MojoProperties.getInstance().getBuildProperties();
	}
	
	/** @modelguid {17F1877F-AE3B-4E40-AE35-BE43788C7F7A} */
	public Iterator getContextManagerList() {
		return contextManagers.iterator();
	}
	
	/** @modelguid {B6C4C722-7784-45F6-9CD4-A6AF2B74A3E4} */
	public Iterator getReplyDispatchList() {
		return replyDispatches.iterator();
	}
	
	/** @modelguid {22183EBF-7EB1-4795-9938-977D204407EA} */
	public Iterator getRequestDispatchList() {
		return requestDispatches.iterator();
	}
	
	/** @modelguid {440A1426-6A6F-46DF-87C0-E05835686084} */
	public Iterator getTOPLinkMapList() {
		return TOPLinkMaps.iterator();
	}
	
	/** @modelguid {EF8FC590-3A5B-4536-9C71-B57D39742064} */
	public void addContextManager(String aContextManager) {
		contextManagers.add(aContextManager);
	}
	
	/** @modelguid {404AD677-2495-414B-9EC9-E0CD26274AE7} */
	public void addReplyDispatch(String aReplyDispatch) {
		replyDispatches.add(aReplyDispatch);
	}
	
	/** @modelguid {CF6DBD39-3CC8-44EE-A238-D1D608240F93} */
	public void addRequestDispatch(String aRequestDispatch) {
		requestDispatches.add(aRequestDispatch);
	}
	
	/** @modelguid {9CE33030-C226-415E-ADDB-EE628E85BF20} */
	public void addTOPLinkMap(String aMap) {
		TOPLinkMaps.add(aMap);
	}
}
