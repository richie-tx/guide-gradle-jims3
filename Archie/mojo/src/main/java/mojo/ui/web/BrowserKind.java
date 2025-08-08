package mojo.ui.web; 




import mojo.ui.exception.UIException;/*
* $Workfile: BrowserKind.java $
* Description: Integer Enumeration of BrowserKinds.
*                                                      
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of BrowserKinds.
*
* @author Egan Royal
* @modelguid {B6DDF62F-D6DE-4AF0-AFAF-8FFE82E5B19C}
*/
public final class BrowserKind extends IntEnum
{
  /**
  * Netscape value 0.
  * @modelguid {B82540D5-AF6C-4A8B-A303-AFD535BDEFB3}
  */
  public final static BrowserKind NETSCAPE = new BrowserKind(0);
  
  /**
  * Internet Explorer value 1.
  * @modelguid {96FAE731-75A5-4C19-90D0-1E4A302636DF}
  */
  public final static BrowserKind IE       = new BrowserKind(1);
  
  /**
  * MicroBrowser value 2.
  * @modelguid {7F99E178-BE7D-479B-9C5F-3F821D4DAE45}
  */
  public final static BrowserKind MICRO    = new BrowserKind(2);
  
  /**
  * All value -1. Used when the BrowserKind is not known.
  * @modelguid {544884AC-9B5F-4666-BAAE-6E88611E9699}
  */
  public final static BrowserKind ALL      = new BrowserKind(-1);

/**
  * Takes an int and returns a BrowserKind iff the int is valid.
  *
  * @param d the int value
  * @return The BrowserKind that corresponds to the int value.
  * @exception com.mojo.api.ui.UIException 
  * If the int value does not correspond to a valid BrowserKind
  * @modelguid {8546F5A7-456F-41C8-93D4-E11F77769C23}
  */
public static BrowserKind getInstance(int d) throws UIException {
  if (d == 0)
    return NETSCAPE;
  else if (d == 1)
    return IE;
  else if (d == -1)
    return ALL;
  else if (d == 2)
    return MICRO;
  else
    throw new UIException("Invalid BrowserKind.");
} //end getInstance    

/** Private constructor 
 * @modelguid {BA2CB932-292A-4461-B44A-A8A5D25A0D5B}
 */
private BrowserKind(int s)
{
  super(s);
}
}