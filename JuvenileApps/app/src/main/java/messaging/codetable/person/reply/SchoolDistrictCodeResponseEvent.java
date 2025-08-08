/*
 * Created on Nov 11, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.person.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SchoolDistrictCodeResponseEvent extends ResponseEvent
{

	private String districtName;
	private String districtNum;
	private String magnetSchoolInd;
	private String schoolName;
	private String schoolNum;
	/**
	 * @return
	 */
	public String getDistrictName()
	{
		return districtName;
	}

	/**
	 * @return
	 */
	public String getDistrictNum()
	{
		return districtNum;
	}

	/**
	 * @return
	 */
	public String getMagnetSchoolInd()
	{
		return magnetSchoolInd;
	}

	/**
	 * @return
	 */
	public String getSchoolName()
	{
		return schoolName;
	}

	/**
	 * @return
	 */
	public String getSchoolNum()
	{
		return schoolNum;
	}

	/**
	 * @param string
	 */
	public void setDistrictName(String districtName)
	{
		this.districtName = districtName;
	}

	/**
	 * @param string
	 */
	public void setDistrictNum(String districtNum)
	{
		this.districtNum = districtNum;
	}

	/**
	 * @param string
	 */
	public void setMagnetSchoolInd(String magnetSchoolInd)
	{
		this.magnetSchoolInd = magnetSchoolInd;
	}

	/**
	 * @param string
	 */
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	/**
	 * @param string
	 */
	public void setSchoolNum(String schoolNum)
	{
		this.schoolNum = schoolNum;
	}

}
