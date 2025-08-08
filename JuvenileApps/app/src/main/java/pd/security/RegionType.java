/*
 * Created on October 11, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.security;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
//import mojo.km.naming.SchedulerConstants;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RegionType {
	private String region = "UNKNOWN";

	/**
	 * @return Returns the server.
	 */
	
	
	public RegionType() {
		 
	} 

	/**
	 * @return Returns the region.
	 */
public String getRegion() {
	String region = "Unknown";
	ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties("JDBCMSSQL");
	String location = cProp.getRegion();
	// constants definced in mojo.xml applications
	/*String prodUrl = AppProperties.getInstance().getProperty("PRODURL");
	String testUrl = AppProperties.getInstance().getProperty("TESTURL");
	String devUrl = AppProperties.getInstance().getProperty("DEVURL");
	String qaUrl = AppProperties.getInstance().getProperty("QAURL");
	String uatUrl = AppProperties.getInstance().getProperty("UATURL");
	String trainingUrl = AppProperties.getInstance().getProperty("TRAININGURL");
	String performanceUrl = AppProperties.getInstance().getProperty("PERFURL");*/
	
	if("TESTURL".equalsIgnoreCase( location)){
		region = "D2";
	}else if("PRODURL".equalsIgnoreCase( location)){
		region = "PROD";
	}else if("QAURL".equalsIgnoreCase( location)){
		region = "QA";
	}else if("UATURL".equalsIgnoreCase( location)){
		region = "UAT";
	}

	return region;
}

}
