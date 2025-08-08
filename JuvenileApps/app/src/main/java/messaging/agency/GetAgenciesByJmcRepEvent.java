//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetAgenciesByJmcRepEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class GetAgenciesByJmcRepEvent extends RequestEvent 
{
   
   private String jmcRepId;
   /**
    * @roseuid 4256F0C50177
    */
   public GetAgenciesByJmcRepEvent() 
   {
    
   }

   /**
    * @return
    */
    public String getJmcRepId()
    {
	   return jmcRepId;
    }

    /**
     * @param string
     */
     public void setJmcRepId(String string)
     {
	    jmcRepId = string;
     }

}
