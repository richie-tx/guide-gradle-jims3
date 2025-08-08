package mojo.km.security.role.file;//no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.

/*package mojo.km.security.role.file;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import messaging.appshell.NodeEvent;
import messaging.appshell.NodeListEvent;
import mojo.km.security.role.IRoles;
import mojo.km.security.securitytransactions.XMLParser;

import org.jdom.Element;
*//**
 * @author jmcnabb
 *
 *//*
public class ACL 
{

	public NodeListEvent getList(IRoles roles)
	{
		NodeListEvent nodeListEvent = new NodeListEvent();
		try{
			
			Set nodeSet = new HashSet();
			if (roles.size() > 0)
			{
				XMLParser xmlParser = new XMLParser("mojo/km/security/role/file/aclImpl.xml");
				Element root = xmlParser.getRootElement();
				List rolesElem = root.getChildren("ROLE");
					
				for(Enumeration i = roles.getEnumeration(); i.hasMoreElements();)
				{
					String role = i.nextElement().toString();
					getFeatures(role, nodeListEvent, nodeSet, rolesElem);
				}
			}
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}
		return nodeListEvent;
	}
	
	private void getFeatures(String roleName, NodeListEvent nodeListEvent, Set parentIds, List rolesElem) throws Exception{
		Iterator it = rolesElem.iterator();
		while(it.hasNext()){
			Element roleElem = (Element)it.next();
			if(roleName.equals(roleElem.getAttributeValue("name"))){
				// get its features
				List featuresElem = roleElem.getChildren("SYSTEMACTIVITY");
				Iterator features = featuresElem.iterator();
				while(features.hasNext()){// traverse through features list
					Element featureElem = (Element)features.next();
					// add node if it has not already been added
					if(!parentIds.contains(featureElem.getAttributeValue("id"))){
						String type = NodeEvent.FOLDER;
						if(featureElem.getAttributeValue("parentId") != null){
							type = NodeEvent.LEAF;
						}
						NodeEvent parentNodeEvent = new NodeEvent(featureElem.getAttributeValue("id"),
															featureElem.getAttributeValue("parentId"),
															featureElem.getAttributeValue("name"),
															featureElem.getAttributeValue("url"),
															type
															);

						nodeListEvent.insertNode(parentNodeEvent);
						parentIds.add(parentNodeEvent.getID());
					}
				}
			}
		}
	}

}
*/