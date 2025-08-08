/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import pd.juvenile.JuvenileAbusePerpatrator;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PlacementYearsResponseEvent extends ResponseEvent
{
	private String code; 
	private String desc;
	public String getCode()
	{
	    return code;
	}

	public void setCode(String code)
	{
	    this.code = code;
	}	
	public String getDesc()
	{
	    return desc;
	}

	public void setDesc(String desc)
	{
	    this.desc = desc;
	}

}
