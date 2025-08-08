//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenilePhotosEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenilePhotosEvent extends RequestEvent 
{
   public String juvenileNum;
   public String photoName = null;
   public boolean mostRecent=false;
   public boolean isTattoo; //task 11051
   


/**
    * @roseuid 42B19684008C
    */
   public GetJuvenilePhotosEvent() 
   {
   }
   


/**
 * @return
 */
public String getJuvenileNum()
{
	return juvenileNum;
}

/**
 * @return
 */
public boolean isMostRecent()
{
	return mostRecent;
}

/**
 * @return
 */
public String getPhotoName()
{
	return photoName;
}


/**
 * @param string
 */
public void setJuvenileNum(String string)
{
	juvenileNum = string;
}

/**
 * @param b
 */
public void setMostRecent(boolean b)
{
	mostRecent = b;
}

/**
 * @param string
 */
public void setPhotoName(String string)
{
	photoName = string;
}

// added for gang tattoo 11051. 

public boolean isTattoo() {
	return isTattoo;
}



public void setTattoo(boolean isTattoo) {
	this.isTattoo = isTattoo;
}


}
