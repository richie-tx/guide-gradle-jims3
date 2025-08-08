//Source file: C:\\views\\framework_e2\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\tools\\requirements\\MappingSynchronizer.java

package mojo.tools.requirements;

import java.util.ArrayList;
import java.util.Iterator;

import reqpro.*;
import java.lang.reflect.*;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
 
/**
 * @author mpatino and rcooper
 *
 * This class will read ReqPro Configuration Maps and create the corresponding
 * Mojo.xml for ConnectionProperties and EntityMappings.
 */
public class MappingSynchronizer {
	private static _Package persistencePkg = null;
	private static _Project project = null;
	private static _Requirement req = null;
	private static MojoProperties mojoProp = null;
	private static String callBackSource = null;
	private static PropertyBundleProperties propBundle;
	private static _Package configPkg = null;
	/**
	@roseuid 405F291002DB
	 */
	public MappingSynchronizer() {

	}

	/** 
	 * This method will create the Connection maps that will be referenced in
	 * the Callback maps. 
	 * 
	 */
	/**
	@roseuid 40586D0B0066
	 */
	public static void synchronizeConnections() {
		ConfigurationAdapter.synchronizeConnections();
	}

	/** 
	 * This method will add the Field and Parm maps to a Callback map.
	 * @param childReq
	 * @param cbProp
	 * @param fmProperties
	 */
	/**
	@roseuid 4058BF4E03C4
	 */
	public static void synchronizeMapping(){
		
		
	}

	/** 
	 * This method will create the Entity Maps and the Callback Maps that will be
	 * contained within them. 
	*/
	/**
	@roseuid 4058C1E900C0
	 */
	public static void synchronizeMappings() {
		//ConfigurationAdapter.synchronizeMappingConfiguration(); 
	}

	
	
	
	public static void main(String[] args) {
		
		try {
			ConfigurationAdapter.synchronizeConfiguration();
			//synchronizeMappings();
			//synchronizeConnections();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("MappingSynchronizer: Error updating Mojo configuration");
			e.printStackTrace();
		}
		
	}
		
}