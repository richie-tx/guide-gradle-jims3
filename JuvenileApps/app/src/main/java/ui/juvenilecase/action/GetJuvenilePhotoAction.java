package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenilePhotosEvent;
import messaging.juvenile.reply.JuvenilePhotoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Photo;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenilePhotoHelper;
import ui.juvenilecase.form.JuvenilePhotoForm;

/**
 * @author bschwartz
 * 
 *         Issues with Juvenile Photos - 3/7/2006
 * 
 *         1. Most photos in the database seem to have a size of 32700 bytes
 *         which in most cases is only half of the image. There are a few
 *         smaller images that display completely.
 * 
 *         2. For testing this action uses the JuvenileProfileForm to get the
 *         Juvenile ID. If this action is to be used in a wider scope then the
 *         Juvenile ID should be obtained through another method. One option
 *         would be to pass it as a link parameter.
 * 
 *         3. There may be multiple photos per juvenile, a link parameter may be
 *         required to support the selection of a specific photo.
 * 
 *         4. If multiple photos are available there must be a way to determine
 *         what the primnary photo is.
 * 
 *         5. If there are multiple photos it would be optimal to bring them all
 *         back at once when a multi-photo view is requested. The issue with
 *         this is that the photos must be 'linked to' independently so they
 *         must be cached in the session. This could contribute to excess memory
 *         usage in the session if it isn't managed well.
 * 
 */
public class GetJuvenilePhotoAction extends LookupDispatchAction
{
	// private static final String MIME_TYPE_IMAGE = "image/jpg";

	/*
	 * 
	 */
	public ActionForward getMostRecentPhoto(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		JuvenilePhotoForm myPhotoForm = (JuvenilePhotoForm)aForm;
		
		//ER-11051 GANG TATTOO STARTS
		String isTattoo = (String) aRequest.getParameter("isTattoo");
		if(isTattoo!=null && isTattoo.equals("true")){ // comes from gang
			
			UIJuvenilePhotoHelper.getMostRecentTattooInit(myPhotoForm);
			
			if( myPhotoForm != null && myPhotoForm.getMostRecentPhoto() != null )
			{
				UIJuvenilePhotoHelper.returnPhoto(aResponse, myPhotoForm.getMostRecentTattoo().getThumbNail());
			}
		}	//ER-11051 GANG TATTOO ENDS
		else
		{
			UIJuvenilePhotoHelper.getMostRecentPhotoInit(myPhotoForm);
			
			if( myPhotoForm != null && myPhotoForm.getMostRecentPhoto() != null )
			{
				UIJuvenilePhotoHelper.returnPhoto(aResponse, 
						myPhotoForm.getMostRecentPhoto().getThumbNail());
			}
		}
		return null;
	}
	
	/**
	 * getSpecificPhotoReturn
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSpecificPhotoReturn(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception
	{
		JuvenilePhotoForm myPhotoForm = (JuvenilePhotoForm)aForm;
		//ER-11051 GANG TATTOO STARTS
		String isTattoo = (String) aRequest.getParameter("isTattoo");
		if(isTattoo!=null && isTattoo.equals("true")){ // comes from gang -displayjuvenilegangsAction
				getSpecificTattoo(myPhotoForm);
			
			if( myPhotoForm != null && myPhotoForm.getCurrentSelectedTattoo() != null )
			{
				UIJuvenilePhotoHelper.returnPhoto(aResponse, 
						myPhotoForm.getCurrentSelectedTattoo().getPhoto());
			}
		}//ER-11051 GANG TATTOO ENDS
		else
		{
			getSpecificPhoto(myPhotoForm);
			
			if( myPhotoForm != null && myPhotoForm.getCurrentSelectedPhoto() != null )
			{
				UIJuvenilePhotoHelper.returnPhoto(aResponse, 
						myPhotoForm.getCurrentSelectedPhoto().getPhoto());
			}
		}
		return null;
	}

	/**
	 * getJuvenilePhotoPage
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJuvenilePhotoPage(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws Exception
	{
		JuvenilePhotoForm myPhotoForm = (JuvenilePhotoForm)aForm;
		
		//ER-11051 GANG TATTOO STARTS
		String isTattoo = (String) aRequest.getParameter("isTattoo");
		if(isTattoo!=null && isTattoo.equals("true")){
			if( myPhotoForm != null && notNullNotEmptyString(myPhotoForm.getSelectedValue()))
			{
				getSpecificTattoo(myPhotoForm);
			}
			return aMapping.findForward(UIConstants.TATTOO_SUCCESS);
		}//ER-11051 GANG TATTOO STARTS
		else
		{
			if( myPhotoForm != null && notNullNotEmptyString(myPhotoForm.getSelectedValue()))
			{
				getSpecificPhoto(myPhotoForm);
			}
			return aMapping.findForward(UIConstants.SUCCESS);
		}
	}

	/**
	 * Get Thumb
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward getThumb(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws Exception
	{
		JuvenilePhotoForm myPhotoForm = (JuvenilePhotoForm)aForm;
		
		//ER-11051 GANG TATTOO STARTS
		String isTattoo = (String) aRequest.getParameter("isTattoo");
		if(isTattoo!=null && isTattoo.equals("true"))
		{
			// the Juvenile Number should be set in the form at this point by strut
			if( myPhotoForm == null || 
					myPhotoForm.getAllTattoos() == null  ||
					nullOrEmptyString( myPhotoForm.getSelectedValue() ))
			{
				return null;
			}
			
			Iterator iter = myPhotoForm.getAllTattoos().iterator();
			while( iter.hasNext() )
			{
				Photo myPhoto = (Photo)iter.next();
				if( myPhoto.getPhotoName().equalsIgnoreCase( myPhotoForm.getSelectedValue()) )
				{
					myPhotoForm.setSelectedValue("");
					UIJuvenilePhotoHelper.returnPhoto(aResponse, myPhoto.getThumbNail());
					break;
				}
			}
		} //ER-11051 GANG TATTOO ENDS
		else
		{
			// the Juvenile Number should be set in the form at this point by strut
			if( myPhotoForm == null || 
					myPhotoForm.getAllPhotos() == null  ||
					nullOrEmptyString( myPhotoForm.getSelectedValue() ))
			{
				return null;
			}
			
			Iterator iter = myPhotoForm.getAllPhotos().iterator();
			while( iter.hasNext() )
			{
				Photo myPhoto = (Photo)iter.next();
				if( myPhoto.getPhotoName().equalsIgnoreCase( myPhotoForm.getSelectedValue()) )
				{
					myPhotoForm.setSelectedValue("");
					UIJuvenilePhotoHelper.returnPhoto(aResponse, myPhoto.getThumbNail());
					break;
				}
			}
		}
		return null;
	}

	/**
	 * getSpecificPhoto
	 * @param myPhotoForm
	 */
	private static void getSpecificPhoto(JuvenilePhotoForm myPhotoForm)
	{
		// the Juvenile Number should be set in the form at this point by struts
		boolean skip = false;
		
		if( myPhotoForm.getSelectedValue() == null ||
				myPhotoForm.getSelectedValue().equalsIgnoreCase("") )
		{
			return; // Invalid request no name to find
		}

		String selectedPhoto = myPhotoForm.getSelectedValue() ;
		
		if( myPhotoForm.getCurrentSelectedPhoto() != null )
		{
			if( myPhotoForm.getCurrentSelectedPhoto().getPhotoName()
					.equalsIgnoreCase( selectedPhoto ) )
			{
				// do nothing already have the photo
				skip = true;
			}
		}

		if( !skip )
		{ // Get The Juvenile Photo
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myPhotoForm.getJuvenileNumber());
			event.setMostRecent(false);
			event.setPhotoName( selectedPhoto );

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			
			Collection photos = MessageUtil.compositeToCollection(compositeResponse,
					JuvenilePhotoResponseEvent.class);
			
			if( photos != null && photos.size() > 0 ) // expecting only one photo
			{
				// Collections.sort((List)photos);
				Iterator iter = photos.iterator();
				if( iter.hasNext() )
				{
					JuvenilePhotoResponseEvent respEvent = 
							(JuvenilePhotoResponseEvent)iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					myPhotoForm.setCurrentSelectedPhoto(myPhoto);
				}
			}
			else
			{
				myPhotoForm.setCurrentSelectedPhoto( (Photo)null );				
			}
		}
	}
	
	/**
	 * ER-11051 GANG TATTOO 
	 * Get Specific Tattoo
	 * @param myPhotoForm
	 */
	private static void getSpecificTattoo(JuvenilePhotoForm myPhotoForm)
	{
		// the Juvenile Number should be set in the form at this point by struts
		boolean skip = false;
		
		if( myPhotoForm.getSelectedValue() == null ||
				myPhotoForm.getSelectedValue().equalsIgnoreCase("") )
		{
			return; // Invalid request no name to find
		}

		String selectedTattoo = myPhotoForm.getSelectedValue() ;
		
		if( myPhotoForm.getCurrentSelectedTattoo() != null)
		{
			if( myPhotoForm.getCurrentSelectedTattoo().getPhotoName()
					.equalsIgnoreCase( selectedTattoo ) )
			{
				// do nothing already have the photo
				skip = true;
			}
		}

		if( !skip )
		{ // Get The Juvenile Photo
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myPhotoForm.getJuvenileNumber());
			event.setMostRecent(false);
			event.setPhotoName( selectedTattoo );
			event.setTattoo(true);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			
			Collection tattoos = MessageUtil.compositeToCollection(compositeResponse,
					JuvenilePhotoResponseEvent.class);
			
			if( tattoos != null && tattoos.size() > 0 ) // expecting only one photo
			{
				// Collections.sort((List)photos);
				Iterator iter = tattoos.iterator();
				if( iter.hasNext() )
				{
					JuvenilePhotoResponseEvent respEvent = 
							(JuvenilePhotoResponseEvent)iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					myPhotoForm.setCurrentSelectedTattoo(myPhoto);
				}
			}
			else
			{
				myPhotoForm.setCurrentSelectedTattoo( (Photo)null );				
			}
		}
	}
	
	/*
	 * given a string, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  str.length() > 0 ) ;
	}
	
	/*
	 * given a string, return true if it's not null and not empty
	 */
	private boolean nullOrEmptyString( String str )
	{
		return( str == null  ||  str.length() == 0 ) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.photoDetail", "getSpecificPhotoReturn");
		keyMap.put("button.link", "getJuvenilePhotoPage");
		keyMap.put("button.photoMostRecent", "getMostRecentPhoto");
		keyMap.put("button.photoThumbnail", "getThumb");
		return keyMap;
	}

}
