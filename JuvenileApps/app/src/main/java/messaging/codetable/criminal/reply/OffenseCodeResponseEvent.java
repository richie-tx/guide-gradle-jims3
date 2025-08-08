/*
 * Created on Nov 11, 2004
 *
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 */
public class OffenseCodeResponseEvent extends ResponseEvent implements ICode, Comparable
{
	
	private String capitalInd;
	private String cjisCode;
	private String degree;
	private String description;
	private boolean isValid; //Used in SuggestedOrder
	private String juvOffenseCode;
	private String level;
	private String localCode;
	private String ncicCode;
	private String offenseCategory;
	private String offenseClass;
	private String offenseCodeId;
	private String penalCode;
	private String startTime;
	private String stateCodeNum;
	private String statusInd;
	private String statuteCitationCode;
	private String statuteCitationType;
	private String tjcCode;
	private String tjcLineNum;
	private String ucrCode;
	private String violentOffenseInd;

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode() {
		// TODO Auto-generated method stub
		return offenseCodeId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		OffenseCodeResponseEvent ocre = (OffenseCodeResponseEvent) arg0;

		if (this.description == null){
			this.description = "";
		}
		if (ocre.getDescription()==null){
			ocre.setDescription("");
		}
		
		int compareVal= this.description.compareTo(ocre.getDescription());
		if(compareVal==0){
		    if (this.offenseCodeId == null){
		        this.offenseCodeId = "";
		    }
		    if (ocre.getOffenseCodeId() == null){
		        ocre.offenseCodeId = "";
		    }
			if(this.offenseCodeId==null || this.offenseCodeId.trim().equals("") || 
					ocre.offenseCodeId==null || ocre.offenseCodeId.trim().equals("")){
				return compareVal;
			}
			else{
				return this.offenseCodeId.compareTo(ocre.getOffenseCodeId());
			}
			
		}
		else
			return compareVal;
	}

	/**
	 * @return
	 */
	public String getCapitalInd()
	{
		return capitalInd;
	}

	/**
	 * @return
	 */
	public String getCjisCode()
	{
		return cjisCode;
	}

	/**
	 * @return
	 */
	public String getDegree()
	{
		return degree;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public boolean getIsValid()
	{
		return isValid;
	}

	/**
	 * @return
	 */
	public String getJuvOffenseCode()
	{
		return juvOffenseCode;
	}

	/**
	 * @return
	 */
	public String getLevel()
	{
		return level;
	}

	/**
	 * @return
	 */
	public String getLocalCode()
	{
		return localCode;
	}

	/**
	 * @return
	 */
	public String getNcicCode()
	{
		return ncicCode;
	}

	/**
	 * @return
	 */
	public String getOffenseCategory()
	{
		return offenseCategory;
	}

	/**
	 * @return
	 */
	public String getOffenseClass()
	{
		return offenseClass;
	}

	/**
	 * @return
	 */
	public String getOffenseCodeId()
	{
		return offenseCodeId;
	}

	/**
	 * @return
	 */
	public String getPenalCode()
	{
		return penalCode;
	}

	/**
	 * @return
	 */
	public String getStartTime()
	{
		return startTime;
	}

	/**
	 * @return
	 */
	public String getStateCodeNum()
	{
		return stateCodeNum;
	}

	/**
	 * @return
	 */
	public String getStatusInd()
	{
		return statusInd;
	}

	/**
	 * @return
	 */
	public String getStatuteCitationCode()
	{
		return statuteCitationCode;
	}

	/**
	 * @return
	 */
	public String getStatuteCitationType()
	{
		return statuteCitationType;
	}

	/**
	 * @return
	 */
	public String getTjcCode()
	{
		return tjcCode;
	}

	/**
	 * @return
	 */
	public String getTjcLineNum()
	{
		return tjcLineNum;
	}

	/**
	 * @return
	 */
	public String getUcrCode()
	{
		return ucrCode;
	}

	/**
	 * @return
	 */
	public String getViolentOffenseInd()
	{
		return violentOffenseInd;
	}

	/**
	 * @param string
	 */
	public void setCapitalInd(String capitalInd)
	{
		this.capitalInd = capitalInd;
	}

	/**
	 * @param string
	 */
	public void setCjisCode(String cjisCode)
	{
		this.cjisCode = cjisCode;
	}

	/**
	 * @param string
	 */
	public void setDegree(String degree)
	{
		this.degree = degree;
	}

	/**
	 * @param string
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param b
	 */
	public void setIsValid(boolean b)
	{
		isValid = b;
	}

	/**
	 * @param string
	 */
	public void setJuvOffenseCode(String juvOffenseCode)
	{
		this.juvOffenseCode = juvOffenseCode;
	}

	/**
	 * @param string
	 */
	public void setLevel(String level)
	{
		this.level = level;
	}

	/**
	 * @param string
	 */
	public void setLocalCode(String localCode)
	{
		this.localCode = localCode;
	}

	/**
	 * @param string
	 */
	public void setNcicCode(String ncicCode)
	{
		this.ncicCode = ncicCode;
	}

	/**
	 * @param string
	 */
	public void setOffenseCategory(String offenseCategory)
	{
		this.offenseCategory = offenseCategory;
	}

	/**
	 * @param string
	 */
	public void setOffenseClass(String offenseClass)
	{
		this.offenseClass = offenseClass;
	}

	/**
	 * @param string
	 */
	public void setOffenseCodeId(String string)
	{
		offenseCodeId = string;
	}

	/**
	 * @param string
	 */
	public void setPenalCode(String penalCode)
	{
		this.penalCode = penalCode;
	}

	/**
	 * @param string
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @param string
	 */
	public void setStateCodeNum(String stateCodeNum)
	{
		this.stateCodeNum = stateCodeNum;
	}

	/**
	 * @param string
	 */
	public void setStatusInd(String statusInd)
	{
		this.statusInd = statusInd;
	}

	/**
	 * @param string
	 */
	public void setStatuteCitationCode(String statuteCitationCode)
	{
		this.statuteCitationCode = statuteCitationCode;
	}

	/**
	 * @param string
	 */
	public void setStatuteCitationType(String statuteCitationType)
	{
		this.statuteCitationType = statuteCitationType;
	}

	/**
	 * @param string
	 */
	public void setTjcCode(String tjcCode)
	{
		this.tjcCode = tjcCode;
	}

	/**
	 * @param string
	 */
	public void setTjcLineNum(String tjcLineNum)
	{
		this.tjcLineNum = tjcLineNum;
	}

	/**
	 * @param string
	 */
	public void setUcrCode(String ucrCode)
	{
		this.ucrCode = ucrCode;
	}

	/**
	 * @param string
	 */
	public void setViolentOffenseInd(String violentOffenseInd)
	{
		this.violentOffenseInd = violentOffenseInd;
	}

}
