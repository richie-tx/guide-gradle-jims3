    package pd.juvenilecase.referral;
    
import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
    
    /**
     * @roseuid 43319902003F
     */
    public class JCVOP extends PersistentObject
    {
        private String juvenileNumber;
        private String referralNumber;
        private Date referralDate;
        private String VOPOffenseCode;
        private String offenseCharge;
        private Date offenseChargeDate;
        private int inCCountyOrigPetitionedRefNum;
        private String locationIndicator;
        private int adultIndicator;
        private String adultIndicatorStr;
        private String createUserName;
        private String createJIMS2UserName;
        private String updateUserName;
        private String updateJIMS2UserName;
        private String createDate;
        private String updateDate;
    
    
        /**
         * @roseuid 433194DF00FF
         */
        public void find()
        {
    	fetch();
        }
    
        static public JCVOP find(String transId)
        {
    	IHome home = new Home();
    	JCVOP jcVOP = (JCVOP) home.find(transId, JCVOP.class);
    	return jcVOP;
        }
        
        static public JCVOP find(IEvent event)
        {
    	IHome home = new Home();
    	Iterator i = (Iterator) home.findAll(event, JCVOP.class);
    	 if (i.hasNext())
         {
             return (JCVOP) i.next();
         }
         return null;
        }
    
        /**
         * @roseuid 42A99B980107
         * @return iterator
         */
        public static Iterator findAll(IEvent event)
        {
    	IHome home = new Home();
    	return home.findAll(event, JCVOP.class);
        }
    
        /**
         * @param attributeName
         * @param attributeValue
         * @return Iterator
         */
        public static Iterator findAll(String attributeName, String attributeValue)
        {
    	IHome home = new Home();
    	return home.findAll(attributeName, attributeValue, JCVOP.class);
        }
        
        public static JCVOP findById(String OID)
        {
    	JCVOP vop = null;
    	IHome home = new Home();
    	vop = (JCVOP) home.find("OID", OID, JCVOP.class);
    	return vop;
        }
    
        /**
         * Access method for the juvenileNum property.
         * 
         * @return the current value of the juvenileNum property
         */
        public String getJuvenileNumber()
        {
    	fetch();
    	return juvenileNumber;
        }
    
        /**
         * Sets the value of the juvenileNum property.
         * 
         * @param aJuvenileNum
         *            the new value of the juvenileNum property
         */
        public void setJuvenileNumber(String aJuvenileNumber)
        {
    	if (this.juvenileNumber == null || !this.juvenileNumber.equals(aJuvenileNumber))
    	{
    	    markModified();
    	}
    	juvenileNumber = aJuvenileNumber;
        }
    
        /**
         * Access method for the referralNum property.
         * 
         * @return the current value of the referralNum property
         */
        public String getReferralNumber()
        {
    	fetch();
    	return referralNumber;
        }
    
        /**
         * Sets the value of the referralNum property.
         * 
         * @param aReferralNum
         *            the new value of the referralNum property
         */
        public void setReferralNumber(String aReferralNumber)
        {
    	if (this.referralNumber == null || !this.referralNumber.equals(aReferralNumber))
    	{
    	    markModified();
    	}
    	referralNumber = aReferralNumber;
        }
    
        public Date getReferralDate()
        {
    	fetch();
    	return referralDate;
        }
    
        public void setReferralDate(Date aReferralDate)
        {
    	if (this.referralDate == null || !this.referralDate.equals(aReferralDate))
    	{
    	    markModified();
    	}
    	referralDate = aReferralDate;
        }
    
        public String getVOPOffenseCode()
        {
    	fetch();
    	return VOPOffenseCode;
        }
    
        public void setVOPOffenseCode(String aVOPOffenseCode)
        {
    	if (this.VOPOffenseCode == null || !this.VOPOffenseCode.equals(aVOPOffenseCode))
    	{
    	    markModified();
    	}
    	VOPOffenseCode = aVOPOffenseCode;
        }
    
        public String getOffenseCharge()
        {
    	fetch();
    	return offenseCharge;
        }
    
        public void setOffenseCharge(String aOffenseCharge)
        {
    	if (this.offenseCharge == null || !this.offenseCharge.equals(aOffenseCharge))
    	{
    	    markModified();
    	}
    	offenseCharge = aOffenseCharge;
        }
    
        public Date getOffenseChargeDate()
        {
    	fetch();
    	return offenseChargeDate;
        }
    
        public void setOffenseChargeDate(Date aOffenseChargeDate)
        {
    	if (this.offenseChargeDate == null || !this.referralNumber.equals(aOffenseChargeDate))
    	{
    	    markModified();
    	}
    	offenseChargeDate = aOffenseChargeDate;
        }
    
        public int getInCCountyOrigPetitionedRefNum()
        {
    	fetch();
    	return inCCountyOrigPetitionedRefNum;
        }
    
        public void setInCCountyOrigPetitionedRefNum(int aInCCountyOrigPetitionedRefNum)
        {
    	if (this.inCCountyOrigPetitionedRefNum == 0 || this.inCCountyOrigPetitionedRefNum != aInCCountyOrigPetitionedRefNum)
    	{
    	    markModified();
    	}
    	inCCountyOrigPetitionedRefNum = aInCCountyOrigPetitionedRefNum;
        }
    
        public String getLocationIndicator()
        {
    	fetch();
    	return locationIndicator;
        }
    
        public void setLocationIndicator(String aLocationIndicator)
        {
    	if (this.locationIndicator == null || !this.referralNumber.equals(aLocationIndicator))
    	{
    	    markModified();
    	}
    	locationIndicator = aLocationIndicator;
        }
    
        public int getAdultIndicator()
        {
    	fetch();
    	return adultIndicator;
        }
    
        public void setAdultIndicator(int aAdultIndicator)
        {
    	if (this.adultIndicator == 0 || this.adultIndicator != aAdultIndicator)
    	{
    	    markModified();
    	}
    	adultIndicator = aAdultIndicator;
        }

	public String getAdultIndicatorStr()
	{
	    return adultIndicatorStr;
	}

	public void setAdultIndicatorStr(String adultIndicatorStr)
	{
	    this.adultIndicatorStr = adultIndicatorStr;
	}

	public String getCreateUserName()
	{
	    return createUserName;
	}

	public void setCreateUserName(String createUserName)
	{
	    this.createUserName = createUserName;
	}

	public String getCreateJIMS2UserName()
	{
	    return createJIMS2UserName;
	}

	public void setCreateJIMS2UserName(String createJIMS2UserName)
	{
	    this.createJIMS2UserName = createJIMS2UserName;
	}

	public String getUpdateUserName()
	{
	    return updateUserName;
	}

	public void setUpdateUserName(String updateUserName)
	{
	    this.updateUserName = updateUserName;
	}

	public String getUpdateJIMS2UserName()
	{
	    return updateJIMS2UserName;
	}

	public void setUpdateJIMS2UserName(String updateJIMS2UserName)
	{
	    this.updateJIMS2UserName = updateJIMS2UserName;
	}

	public String getCreateDate()
	{
	    return createDate;
	}

	public void setCreateDate(String createDate)
	{
	    this.createDate = createDate;
	}

	public String getUpdateDate()
	{
	    return updateDate;
	}

	public void setUpdateDate(String updateDate)
	{
	    this.updateDate = updateDate;
	}


    
    }
