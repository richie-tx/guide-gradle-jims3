package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 *
 */
public class JuvenilePhotoResponseEvent extends ResponseEvent  implements Comparable
{
	private String juvenileNum;
	private int totalPhotoCount;

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
	public String getJuvenileNum()
	{
		return juvenileNum;
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
	public String getPhotoFullDesc_Caption()
	{
		return photoFullDesc_Caption;
	}

	/**
	 * @return
	 */
	public String getPhotoName()
	{
		return photoName;
	}

	/**
	 * @return
	 */
	public String getPhotoShortDesc_Caption()
	{
		return photoShortDesc_Caption;
	}

	/**
	 * @return
	 */
	public byte[] getThumbNail()
	{
		return thumbNail;
	}

	/**
	 * @return
	 */
	public int getTotalPhotoCount()
	{
		return totalPhotoCount;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
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
	public void setPhotoFullDesc_Caption(String string)
	{
		photoFullDesc_Caption = string;
	}

	/**
	 * @param string
	 */
	public void setPhotoName(String string)
	{
		photoName = string;
	}

	/**
	 * @param string
	 */
	public void setPhotoShortDesc_Caption(String string)
	{
		photoShortDesc_Caption = string;
	}

	/**
	 * @param bs
	 */
	public void setThumbNail(byte[] bs)
	{
		thumbNail = bs;
	}

	/**
	 * @param i
	 */
	public void setTotalPhotoCount(int i)
	{
		totalPhotoCount = i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		JuvenilePhotoResponseEvent p2 = (JuvenilePhotoResponseEvent)o;
		// Order reversed so that newest date is at [0].
		return p2.getEntryDate().compareTo(this.getEntryDate());

	}

}
