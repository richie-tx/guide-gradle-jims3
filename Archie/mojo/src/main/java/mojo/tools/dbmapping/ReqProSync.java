package mojo.tools.dbmapping;

import reqpro.*;
/**
 * @author eamundson
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReqProSync {

	public static void main(String[] args) {
        try {
            // Initialize the Java2Com Environment
            com.ibm.bridge2java.OleEnvironment.Initialize();
         } catch (Exception e) {
            System.out.println("Could not create ReqPro Control.");
            e.printStackTrace();
        }
        try {
           Application reqPro = new reqpro.Application();
            _Project project = reqPro.OpenProject((Object)"c:\\projects\\harris\\reqpro\\test\\test.rqs",
                enumOpenProjectOptions.eOpenProjOpt_RQSFile, "eamundson",
                "eric",
                0, enumRelatedProjectOptions.eRelatedProjOption_ConnectAsSpecified);
           
        } catch (Exception e) {
            System.out.println("Could not create ReqPro Control.");
            e.printStackTrace();
        }
	}
}
