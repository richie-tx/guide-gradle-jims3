/*
 * Created on Mar 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.form;

import java.util.Collection;
import org.apache.struts.action.ActionForm;
import ui.common.Photo;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhotoForm extends ActionForm
{
	// PHOTO FORM SPECIFIC
	transient Photo mostRecentPhoto=null;    // This is the photo that is the most recent of all the photos usually a thumbnail
	private transient Photo currentSelectedPhoto=null;    // This is the photo that is currently selected for viewing not a thumbnail usually
	private Collection allPhotos=null;  // Usually holds thumbs
	int totalPhotosAvailable=-1;   // Total number of photos available. -1 is a flag used to indicate this has never been set
	
	// Tattoo FORM SPECIFIC task 11051 starts
	transient Photo mostRecentTattoo=null;    // This is the Tattoo that is the most recent of all the Tattoos usually a thumbnail
	private transient Photo currentSelectedTattoo=null;    // This is the Tattoo that is currently selected for viewing not a thumbnail usually
	private Collection allTattoos=null;  // Usually holds thumbs
	int totalTattoosAvailable=-1;   // Total number of Tattoos available. -1 is a flag used to indicate this has never been set
	//task 11051 ends
	
	// GENERIC
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	public PhotoForm(){
		mostRecentPhoto=null;    // This is the photo that is the most recent of all the photos
		currentSelectedPhoto=null;    // This is the photo that is currently selected for viewing
		allPhotos=null;
		totalPhotosAvailable=0;
		
		//task 11051 starts
		mostRecentTattoo=null;    // This is the Tattoo that is the most recent of all the Tattoos
		currentSelectedTattoo=null;    // This is the Tattoo that is currently selected for viewing
		allTattoos=null;
		totalTattoosAvailable=0;
		//task 11051 ends
		
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
	}
	
	
	public void clearAll(){
		mostRecentPhoto=null;    // This is the photo that is the most recent of all the photos
		currentSelectedPhoto=null;    // This is the photo that is currently selected for viewing
		allPhotos=null;
		totalPhotosAvailable=0;
		
		//task 11051 starts
		mostRecentTattoo=null;     // This is the Tattoo that is the most recent of all the Tattoos
		currentSelectedTattoo=null;    // This is the Tattoo that is currently selected for viewing
		allTattoos=null;
		totalTattoosAvailable=0;
		//task 11051 ends
		
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
	}
	
	
	
	/**
	 * @return
	 */
	public Collection getAllPhotos()
	{
		return allPhotos;
	}

	/**
	 * @return
	 */
	public Photo getCurrentSelectedPhoto()
	{
		return currentSelectedPhoto;
	}

	/**
	 * @return
	 */
	public Photo getMostRecentPhoto()
	{
		return mostRecentPhoto;
	}

	/**
	 * @param list
	 */
	public void setAllPhotos(Collection list)
	{
		allPhotos = list;
	}

	/**
	 * @param photo
	 */
	public void setCurrentSelectedPhoto(Photo photo)
	{
		currentSelectedPhoto = photo;
	}

	/**
	 * @param photo
	 */
	public void setMostRecentPhoto(Photo photo)
	{
		mostRecentPhoto = photo;
	}

	/**
	 * @return
	 */
	public Collection getAllTattoos()
	{
		return allTattoos;
	}

	/**
	 * @return
	 */
	public Photo getCurrentSelectedTattoo()
	{
		return currentSelectedTattoo;
	}

	/**
	 * @return
	 */
	public Photo getMostRecentTattoo()
	{
		return mostRecentTattoo;
	}

	/**
	 * @param list
	 */
	public void setAllTattoos(Collection list)
	{
		allTattoos = list;
	}

	/**
	 * @param Tattoo
	 */
	public void setCurrentSelectedTattoo(Photo Tattoo)
	{
		currentSelectedTattoo = Tattoo;
	}

	/**
	 * @param Tattoo
	 */
	public void setMostRecentTattoo(Photo Tattoo)
	{
		mostRecentTattoo = Tattoo;
	}
	
	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
	}

	/**
	 * @return
	 */
	public int getTotalPhotosAvailable()
	{
		return totalPhotosAvailable;
	}

	/**
	 * @param i
	 */
	public void setTotalPhotosAvailable(int i)
	{
		totalPhotosAvailable = i;
	}
	
	/**
	 * @return
	 */
	public int getTotalTattoosAvailable()
	{
		return totalTattoosAvailable;
	}

	/**
	 * @param i
	 */
	public void setTotalTattoosAvailable(int i)
	{
		totalTattoosAvailable = i;
	}

	
}// END CLASS
