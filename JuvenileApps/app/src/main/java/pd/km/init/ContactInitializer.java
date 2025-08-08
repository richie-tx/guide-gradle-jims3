/*
 * Created on Sep 26, 2005
 *
 */
package pd.km.init;

import naming.AgencyControllerServiceNames;

import org.apache.log4j.Level;

import mojo.km.logging.LogUtil;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.MessageUtil;
import mojo.struts.IStartup;

/**
 * @author glyons
 *  
 */
public class ContactInitializer implements IStartup
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.struts.IStartup#init()
     */
    public void init()
    {
        // Initialize Contact Agencies
        initAgencies();
    }

    private void initAgencies()
    {
        RequestEvent event = (RequestEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETALLAGENCIES);
        try
        {
            MessageUtil.postRequest(event);
        }
        catch (RuntimeException e)
        {
            LogUtil.log(Level.FATAL, "Exception during startup.");
            LogUtil.log(Level.FATAL, e);
        }

    }
}
