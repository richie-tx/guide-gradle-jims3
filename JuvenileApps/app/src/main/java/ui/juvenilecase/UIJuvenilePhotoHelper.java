/*
 * Created on Feb 22, 2007
 */
package ui.juvenilecase;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenilePhotosEvent;
import messaging.juvenile.reply.JuvenilePhotoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.common.Photo;
import ui.juvenilecase.form.JuvenilePhotoForm;

/**
 * @author bschwartz
 * 
 * Issues with Juvenile Photos - 3/7/2006
 * 
 * 1. Most photos in the database seem to have a size of 32700 bytes which in
 * most cases is only half of the image. There are a few smaller images that
 * display completely.
 * 
 * 2. For testing this action uses the JuvenileProfileForm to get the Juvenile
 * ID. If this action is to be used in a wider scope then the Juvenile ID should
 * be obtained through another method. One option would be to pass it as a link
 * parameter.
 * 
 * 3. There may be multiple photos per juvenile, a link parameter may be
 * required to support the selection of a specific photo.
 * 
 * 4. If multiple photos are available there must be a way to determine what the
 * primnary photo is.
 * 
 * 5. If there are multiple photos it would be optimal to bring them all back at
 * once when a multi-photo view is requested. The issue with this is that the
 * photos must be 'linked to' independently so they must be cached in the
 * session. This could contribute to excess memory usage in the session if it
 * isn't managed well.
 *  
 */
public class UIJuvenilePhotoHelper {

	private static final String MIME_TYPE_IMAGE = "image/jpg";
	
	/**
	 * Photos -getMostRecentPhotoInit
	 * @param myPhotoForm
	 */
	public static void getMostRecentPhotoInit(JuvenilePhotoForm myPhotoForm) {
		// the Juvenile Number should be set in the form at this point by struts

		if (myPhotoForm.getJuvenileNumber() == null || myPhotoForm.getJuvenileNumber().equalsIgnoreCase("")) {
			return; // Invalid request.
		}

		// Get The most recent Juvenile Photo and initialize other photo form
		// elements

		if (myPhotoForm.getMostRecentPhoto() == null) { // don't already have
														// the most recent
														// otherwise we have it
			// first time calling or else no photos exist
			getAllPhotos(myPhotoForm);
			if (myPhotoForm.getAllPhotos() == null || myPhotoForm.getAllPhotos().size() <= 0) {
				myPhotoForm.setTotalPhotosAvailable(0);
				return; // no photos to get.
			}
			// Get the most recent photo which should be the first photo in the
			// list
			Iterator iter = myPhotoForm.getAllPhotos().iterator();
			/*if (iter.hasNext()) {
				myPhotoForm.setMostRecentPhoto((Photo) iter.next());
				iter = null;
			}*/
			while (iter.hasNext()) 
			{
			    Photo ph = (Photo) iter.next();
		            //JuvenileActivityTypeCodeResponseEvent resp = new JuvenileActivityTypeCodeResponseEvent();
			    String name=ph.getPhotoName();
			    if(name!=null)
			    {
				if(!(name.substring(0, name.lastIndexOf(".")).contains("g")) && !(name.substring(0, name.lastIndexOf(".")).contains("t")))
				{
        				myPhotoForm.setMostRecentPhoto(ph);
        				break;	
				}
			    }
			}
			if (myPhotoForm.getMostRecentPhoto() == null || myPhotoForm.getMostRecentPhoto().getPhotoName() == null) {
				return;
			}
			String mostRecentPhotoName = myPhotoForm.getMostRecentPhoto().getPhotoName();
			myPhotoForm.setSelectedValue(mostRecentPhotoName);
			getSpecificPhoto(myPhotoForm);
			if (myPhotoForm.getCurrentSelectedPhoto() != null)
				myPhotoForm.setMostRecentPhoto(myPhotoForm.getCurrentSelectedPhoto());
		}

		return; // don't do anything
	}
	
	/**
	 * Photos- getAllPhotos
	 * @param myPhotoForm
	 */
	public static void getAllPhotos(JuvenilePhotoForm myPhotoForm) {
		// the Juvenile Number should be set in the form at this point by struts

		if (myPhotoForm.getJuvenileNumber() == null || myPhotoForm.getJuvenileNumber().equalsIgnoreCase("")) {
			return; // Invalid request.
		}

		// Get The most recent Juvenile Photo and initialize other photo form
		// elements

		if (myPhotoForm.getAllPhotos() == null || myPhotoForm.getAllPhotos().size() <= 0) { // don't already have the most recent otherwise we have it
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myPhotoForm.getJuvenileNumber());
			event.setPhotoName(null);
			event.setMostRecent(false);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			MessageUtil.processReturnException(compositeResponse);
			Collection photosResp = MessageUtil.compositeToCollection(compositeResponse,
					JuvenilePhotoResponseEvent.class);
			myPhotoForm.setAllPhotos(null);
			myPhotoForm.setTotalPhotosAvailable(0);
			boolean mostRecent = false;
			if (photosResp != null && photosResp.size() > 0) // 
			{
				ArrayList photos = new ArrayList();
				Collections.sort((List) photosResp); // aranged with most recent
													 // first
				Iterator iter = photosResp.iterator();
				while (iter.hasNext()) {
					JuvenilePhotoResponseEvent respEvent = (JuvenilePhotoResponseEvent) iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					if (myPhotoForm.getTotalPhotosAvailable() <= 0)
						myPhotoForm.setTotalPhotosAvailable(respEvent.getTotalPhotoCount());
					photos.add(myPhoto);
				}
				myPhotoForm.setAllPhotos(photos);

			}
		}
		return;
	}
	/**
	 * Photos - getSpecificPhoto
	 * @param myPhotoForm
	 */
	public static void getSpecificPhoto(JuvenilePhotoForm myPhotoForm) {
		// the Juvenile Number should be set in the form at this point by struts
		boolean skip = false;
		if (myPhotoForm.getSelectedValue() == null || myPhotoForm.getSelectedValue().equalsIgnoreCase("")) {
			return; // Invalid request no name to find
		}
		if (myPhotoForm.getCurrentSelectedPhoto() != null) {
			if (myPhotoForm.getCurrentSelectedPhoto().getPhotoName().equalsIgnoreCase(myPhotoForm.getSelectedValue())) {
				//				do nothing already have the photo
				skip = true;
			}

		}
		// Get The Juvenile Photo
		if (!skip) {
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myPhotoForm.getJuvenileNumber());
			event.setMostRecent(false);
			event.setPhotoName(myPhotoForm.getSelectedValue());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			Collection photos = MessageUtil.compositeToCollection(compositeResponse, JuvenilePhotoResponseEvent.class);
			if (photos != null && photos.size() > 0) // expecting only one photo
			{
				//		Collections.sort((List)photos);
				Iterator iter = photos.iterator();
				if (iter.hasNext()) {
					JuvenilePhotoResponseEvent respEvent = (JuvenilePhotoResponseEvent) iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					myPhotoForm.setCurrentSelectedPhoto(myPhoto);
				}
			}
		}
	}
	
	// Tattoos implementation starts below similar to photos task 11051
	
	/**
	 * ER-11051 GANG TATTOO
	 * Get most recent tattoo - getMostRecentTattooInit
	 * @param myTattooForm
	 */
	public static void getMostRecentTattooInit(JuvenilePhotoForm myTattooPhoto) {
		// the Juvenile Number should be set in the form at this point by struts

		if (myTattooPhoto.getJuvenileNumber() == null || myTattooPhoto.getJuvenileNumber().equalsIgnoreCase("")) {
			return; // Invalid request.
		}

		// Get The most recent Juvenile Photo and initialize other photo form
		// elements
		//myTattooPhoto.clearAll();
		if (myTattooPhoto.getMostRecentTattoo() == null) { // don't already have
														// the most recent
														// otherwise we have it
			// first time calling or else no photos exist
			getAllTattoos(myTattooPhoto);
			if (myTattooPhoto.getAllTattoos() == null || myTattooPhoto.getAllTattoos().size() <= 0) {
				myTattooPhoto.setTotalTattoosAvailable(0);
				return; // no photos to get.
			}
			// Get the most recent photo which should be the first photo in the
			// list
			Iterator iter = myTattooPhoto.getAllTattoos().iterator();
			if (iter.hasNext()) {
				myTattooPhoto.setMostRecentTattoo((Photo) iter.next());
				iter = null;
			}
			if (myTattooPhoto.getMostRecentTattoo() == null || myTattooPhoto.getMostRecentTattoo().getPhotoName() == null) {
				return;
			}
			String mostRecentTattooPhotoName = myTattooPhoto.getMostRecentTattoo().getPhotoName();
			myTattooPhoto.setSelectedValue(mostRecentTattooPhotoName);
			getSpecificTattoo(myTattooPhoto);
			if (myTattooPhoto.getCurrentSelectedTattoo() != null)
				myTattooPhoto.setMostRecentTattoo(myTattooPhoto.getCurrentSelectedTattoo());
		}
		return; // don't do anything
	}
	
	/**
	 * ER-11051 GANG TATTOO
	 * Get all Tattoos
	 * @param myTattooPhoto
	 */
	public static void getAllTattoos(JuvenilePhotoForm myTattooPhoto) {
		// the Juvenile Number should be set in the form at this point by struts

		if (myTattooPhoto.getJuvenileNumber() == null || myTattooPhoto.getJuvenileNumber().equalsIgnoreCase("")) {
			return; // Invalid request.
		}

		// Get The most recent Juvenile Photo and initialize other photo form
		// elements

		if (myTattooPhoto.getAllTattoos() == null || myTattooPhoto.getAllTattoos().size() <= 0) { // don't already have the most recent otherwise we have it
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myTattooPhoto.getJuvenileNumber());
			event.setPhotoName(null);
			event.setMostRecent(false);
			event.setTattoo(myTattooPhoto.getIsTattoo());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			MessageUtil.processReturnException(compositeResponse);
			Collection tattooPhotosResp = MessageUtil.compositeToCollection(compositeResponse,
					JuvenilePhotoResponseEvent.class);
			myTattooPhoto.setAllTattoos(null);
			myTattooPhoto.setTotalTattoosAvailable(0);
			boolean mostRecent = false;
			if (tattooPhotosResp != null && tattooPhotosResp.size() > 0) // 
			{
				ArrayList tattoos = new ArrayList();
				Collections.sort((List) tattooPhotosResp); // aranged with most recent
													 // first
				Iterator iter = tattooPhotosResp.iterator();
				while (iter.hasNext()) {
					JuvenilePhotoResponseEvent respEvent = (JuvenilePhotoResponseEvent) iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					if (myTattooPhoto.getTotalTattoosAvailable() <= 0)
						myTattooPhoto.setTotalTattoosAvailable(respEvent.getTotalPhotoCount());
					tattoos.add(myPhoto);
				}
				myTattooPhoto.setAllTattoos(tattoos);

			}
		}
		return;
	}
	
	/**
	 * ER-11051 GANG TATTOO
	 * Get Specific Tattoo.
	 * @param myPhotoForm
	 */
	public static void getSpecificTattoo(JuvenilePhotoForm myTattooPhoto) {
		// the Juvenile Number should be set in the form at this point by struts
		boolean skip = false;
		if (myTattooPhoto.getSelectedValue() == null || myTattooPhoto.getSelectedValue().equalsIgnoreCase("")) {
			return; // Invalid request no name to find
		}
		if (myTattooPhoto.getCurrentSelectedTattoo() != null) {
			if (myTattooPhoto.getCurrentSelectedTattoo().getPhotoName().equalsIgnoreCase(myTattooPhoto.getSelectedValue())) {
				//do nothing already have the photo
				skip = true;
			}

		}
		// Get The Juvenile Photo
		if (!skip) {
			GetJuvenilePhotosEvent event = new GetJuvenilePhotosEvent();
			event.setJuvenileNum(myTattooPhoto.getJuvenileNumber());
			event.setMostRecent(false);
			event.setPhotoName(myTattooPhoto.getSelectedValue());
			event.setTattoo(myTattooPhoto.getIsTattoo());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			Collection tattoos = MessageUtil.compositeToCollection(compositeResponse, JuvenilePhotoResponseEvent.class);
			if (tattoos != null && tattoos.size() > 0) // expecting only one photo
			{
				//		Collections.sort((List)photos);
				Iterator iter = tattoos.iterator();
				if (iter.hasNext()) {
					JuvenilePhotoResponseEvent respEvent = (JuvenilePhotoResponseEvent) iter.next();
					Photo myPhoto = new Photo();
					UIJuvenileHelper.mapJuvPhotoRespEvtToAPhoto(respEvent, myPhoto);
					myTattooPhoto.setCurrentSelectedTattoo(myPhoto);
				}
			}
		}
	}

	/**
	 * Generic method for both tattoo and photo
	 * @param aResponse
	 * @param aPhotoBytes
	 * @return
	 * @throws Exception
	 */
	public static boolean returnPhoto(HttpServletResponse aResponse, byte[] aPhotoBytes) throws Exception {
		try {
			OutputStream out = aResponse.getOutputStream();
			if (aPhotoBytes != null && aPhotoBytes.length > 0) {
				aResponse.setContentType(MIME_TYPE_IMAGE);
				out.write(aPhotoBytes);
				out.flush();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

}
