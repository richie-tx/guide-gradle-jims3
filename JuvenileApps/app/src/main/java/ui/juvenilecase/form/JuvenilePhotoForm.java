/*
 * Created on Mar 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.form;


import ui.common.form.PhotoForm;
import ui.juvenilecase.UIJuvenilePhotoHelper;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenilePhotoForm extends PhotoForm
{
	// Form Specific
	private String juvenileNumber=null;
	private boolean isTattoo; //added for 11051
	
	public JuvenilePhotoForm(){
	}

	public void clearAll(){
		super.clearAll();
		juvenileNumber=null;
		isTattoo=false;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		if(juvenileNumber!=null && !juvenileNumber.equalsIgnoreCase("")){
			if(string ==null || string.equalsIgnoreCase("") || !juvenileNumber.equalsIgnoreCase(string)){
					//do nothing
			}
			else //ER-11051 GANG TATTOO STARTS
			{// Juvenile number matches current juvenile number so don't reload juv info.
				if(isTattoo){
					UIJuvenilePhotoHelper.getMostRecentTattooInit(this);
				}
				return;
			}//ER-11051 GANG TATTOO ENDS
		}
		clearAll();	
		juvenileNumber = string;
		initialize();
	}
	

	public boolean getIsTattoo() {
		return isTattoo;
	}


	public void setIsTattoo(boolean isTattoo) {
		this.isTattoo = isTattoo;
	}
	
	/**
	 * Initialize juvenile photo
	 */
	private void initialize(){
		UIJuvenilePhotoHelper.getMostRecentPhotoInit(this);
	}
}
