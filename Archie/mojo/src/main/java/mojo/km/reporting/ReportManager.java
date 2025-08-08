/*
 * Created on Mar 14, 2005
 *
 */
package mojo.km.reporting;

import java.lang.reflect.Field;
import java.util.Vector;
import java.util.Iterator;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.config.AppProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;

/**
 * @stereotype control
 * @eventPackage mojo.km.messaging.reporting
 * @author RDassharma, Nick Popov
 */
public class ReportManager
{

	public static final String REPORTING_CLASS = "mojo.km.reporting.wordxml.MSWordReporting";

	/**
	 * @stereotype design
	 * @commandList mojo.km.reporting.transactions.ReportingCommand
	 */
	public void reportRequest()
	{
	}
	
	public static IReport getInstance(String aContextKey)
	{
	    
	    IReport r = null;
		
		try
		{
		   String className = null;
		   EntityMappingProperties eProps = MojoProperties.getInstance().getEntityMap(aContextKey);
		   EventQueryProperties qProps = null;
		   
		   Iterator i = eProps.getQueryCallbacks();
		   
		   while (i.hasNext()){
		       qProps = (EventQueryProperties) i.next();
		   }

            if (qProps == null)
            {
                className = null;
            }
            else
            {
                className = qProps.getMappingClassName();
            }
            if (className == null)
            {
                className = REPORTING_CLASS;
            }
            r = (IReport) Class.forName(className).newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	

	public static IReport getInstance()
	{
		IReport r = null;
		try
		{
		    String className = AppProperties.getInstance().getReportingAdapter();
			
			if (className == null)
			{
				className = REPORTING_CLASS;
			}
			r = (IReport) Class.forName(className).newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}

	public static Vector getFieldVector(ReportRequestEvent event)
	{
		Vector v = new Vector();
		try
		{
			Iterator entityList = event.getDataObjects();
			while (entityList.hasNext())
			{
				Object entity = (Object) entityList.next();
				Field[] ff = entity.getClass().getDeclaredFields();
				for (int i = 0; i < ff.length; i++)
				{
					String fullFieldName = ff[i].getName();
					String fieldName =
						fullFieldName.substring(fullFieldName.lastIndexOf(".") + 1, fullFieldName.length());
					String getterName =
						"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
					String fieldVectorTuple = fieldName + " = " + getterName;
					System.out.println("Field Vector Tuple : " + fieldVectorTuple);
					v.add(fieldVectorTuple);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return v;
	}
}

