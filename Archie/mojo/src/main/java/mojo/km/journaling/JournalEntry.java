//Source file: C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\journaling\\JournalEntry.java

package mojo.km.journaling;

import mojo.km.persistence.PersistentObject;
import java.util.Date;

public class JournalEntry extends PersistentObject 
{
   private String contextId;
   private String contextType;
   private Date dateCreated;
   private String createdByUserOID;
   private String text;
   
   /**
    * @roseuid 42BB12EB013A
    */
   public JournalEntry() 
   {
    
   }
}
