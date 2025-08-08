package mojo.ui.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mojo.km.config.AppProperties;

/**
 * <B>TableEventGovernor</B> is used as an intermediary to
 * limit the number of TableModelEvents that get sent to
 * a particular listener.
 *
 * @author G. Lovaasen
 *
 * @modelguid {08E2319F-847A-4EED-87A7-71CE75176746}
 */
public class TableEventGovernor implements TableModelListener, Runnable
{  
  
	/**
	 * Duration of the sleep between repaints.  The default is
	 * specified here.  If a value can be pulled from the
	 * configuration file, it will be used instead.
	 * @modelguid {B03F9877-774C-4FC9-8A9F-93099B1C4DDB}
	 */
	private int SLEEP_TIME = 500;
	
	/**
	 * Configuration file name that will be used to pull the 
	 * sleep time if the Propertysetup object has not been
	 * instantiated.
	 * @modelguid {33044305-B80A-4053-94B7-ED665D35D9DC}
	 */
	private static final String PROPERTIES_FILE = "Setup.cfg";
	
	/**
	 * Property name to be pulled for sleep time.
	 * @modelguid {90867784-B48A-4E1E-B921-B82E366745CA}
	 */
	private static final String SLEEP_TIME_PROP = "GovernorSleepTime";

	/**
	 * Table that will ultimately receive a TableModelEvent.
	 * @modelguid {648A157B-FA35-4A83-9BD2-670B0D579914}
	 */
	private TableModelListener table;
	
	/**
	 * Table Model to which whose events will be subscribed.
	 * @modelguid {B5439FA5-95E7-49F1-87CA-13749972B9A7}
	 */
	private TableModel model;
	
	
	/**
	 * Indicates whether or not the table requires update because
	 * the model has had a change.
	 * @modelguid {5502709E-BDCD-413A-9A25-2895C062CBC2}
	 */
	private boolean isTableDirty;
	
  /**
   * This is the only defined constructor for this class.
   *
   * @param table  TableModelListener reference to the table that
   *               will ultimately receive the TableModelEvent.
   * @param model  TableModel to which whose events will be subscribed.
   * @modelguid {D45B8119-10DC-4E89-AF11-6DA4F41577BD}
   */
  public TableEventGovernor(TableModelListener table, TableModel model)
  {
    this.table = table;
    this.model = model;
    model.addTableModelListener(this);
    
    // Retrieve sleep time
	  String sleepTime = null;
	  try 
	  {
	    sleepTime = AppProperties.getInstance().getProperty(SLEEP_TIME_PROP);
	  }
	  catch (Exception e) 
	  {
	  }
	  if (sleepTime == null)
	  {
	    File propFile = new File(PROPERTIES_FILE);
	    if (propFile.exists())
	    {
	      try
	      {
	        FileInputStream stream = new FileInputStream(propFile);
	        Properties properties = new Properties();
	        properties.load(stream);
	        sleepTime = properties.getProperty(SLEEP_TIME_PROP);
	      }
	      catch (Exception ex)
	      {
	      }
	    }
	  }
	  if (sleepTime != null)
	  {
	    SLEEP_TIME = Integer.parseInt(sleepTime);
	  }
  }
  
	// Implement the TableModelListener interface
 
  /**
   * This method is used to set the isTableDirty flag when
   * the TableModel has changed.
   * @modelguid {5B155612-4539-4672-B914-F43F212AE101}
   */
  public void tableChanged(TableModelEvent event)
  {
    isTableDirty = true;
  }
	
	// Implement the Runnable interface
	
  /**
   * This method will continue to execute for the life of the JVM.
   * Each time through, it sees if the table requires update.  If
   * so, it creates a TableModelEvent and calls the tableChanged()
   * method on the TableModelListener.
   * @modelguid {B351EB11-6FA6-4554-9A45-5FEB71C2A9BB}
   */
  public void run()
  {
    while (true) 
    {
      if (isTableDirty) 
      {
        table.tableChanged(new TableModelEvent(model));
        isTableDirty = false;
      }
      try 
      {
        Thread.sleep(SLEEP_TIME);
      }
      catch (InterruptedException e)
      {
      }
    }
  }
	
	
}
