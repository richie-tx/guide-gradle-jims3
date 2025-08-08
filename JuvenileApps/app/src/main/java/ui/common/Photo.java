/*
 * Created on Apr 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.Date;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Photo
{
	private byte[] thumbNail=null;
	private byte[] photo=null;
	private String photoName=null;
	private String photoFullDesc_Caption=null;
	private String photoShortDesc_Caption=null;
	private Date entryDate=null;
	
	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public byte[] getPhoto()
	{
		return photo;
	}

	/**
	 * @return
	 */
	public String getPhotoName()
	{
		return photoName;
	}

	/** Method returns the thumbnail if one exists else returns the photo
	 * @return
	 */
	public byte[] getThumbNail()
	{
		if(thumbNail==null)
			return photo;
		return thumbNail;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param bs
	 */
	public void setPhoto(byte[] bs)
	{
		photo = bs;
	}

	/**
	 * @param string
	 */
	public void setPhotoName(String string)
	{
		photoName = string;
	}

	/**
	 * @param bs
	 */
	public void setThumbNail(byte[] bs)
	{
		thumbNail = bs;
	}


	/**
	 * @return
	 */
	public String getPhotoFullDesc_Caption()
	{
		return photoFullDesc_Caption;
	}

	/**
	 * @return
	 */
	public String getPhotoShortDesc_Caption()
	{
		return photoShortDesc_Caption;
	}

	/**
	 * @param string
	 */
	public void setPhotoFullDesc_Caption(String string)
	{
		photoFullDesc_Caption = string;
	}

	/**
	 * @param string
	 */
	public void setPhotoShortDesc_Caption(String string)
	{
		photoShortDesc_Caption = string;
	}

}
