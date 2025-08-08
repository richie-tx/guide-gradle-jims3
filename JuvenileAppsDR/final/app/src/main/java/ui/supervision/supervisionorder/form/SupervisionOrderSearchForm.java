/*
 * Created on Nov 29, 2005  
 */
package ui.supervision.supervisionorder.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;

import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SocialSecurity;
import ui.common.UIUtil;

/**
 * @author hrodriguez
 *  
 */
public class SupervisionOrderSearchForm extends ActionForm
{
    private String action = "";

    //	Fields
    private String caseNum;

    private List caseOrderList;

    private String caseOrderListSize;

    private String cdi;
    
    private String court;

    private String cjis;

    private String connection;

    private String connectionId;

    private Collection connectionList; //Drop Down Lists

    private Date dateOfBirth;

    private boolean delete = false;

    private String driverLicenseNum;

    private String fbiNum;

    private String firstName;

    private String lastName;

    private boolean listsSet;

    private String middleName;

    private Name name = new Name();

    private String offense;

    //private Collection offenseCodeList; //Drop Down Lists

    private String offenseId;

    //private Map offenseMap;

    private String orderId;

    private String orderStatus;

    private String orderStatusId;
    

    private Collection orderStatusList; //Drop Down Lists

    private String orderVersion;

    private String originalOrderTitleId;

    private String race;

    private String raceId;

    private Collection raceList; //Drop Down Lists

    private String secondaryAction = "";

    private String selectedValue = "";

    private String sex;

    private String sexId;

    private Collection sexList; //Drop Down Lists

    private String sid;

    private String spn;

    private SocialSecurity ssn = new SocialSecurity("");

    private String ssn1;

    private String ssn2;

    private String ssn3;

    private String stateId;

    private Collection stateList; //Drop Down Lists

    private Collection superviseeList;

    private String superviseeListSize;

    private boolean update = false;
    
    private String userPosition;

    private int versionNum;

    private String versionType;

    private String versionTypeId;

    private Collection versionTypeList; //Drop Down Lists

    public SupervisionOrderSearchForm()
    {
        clear();
        //UISupervisionOrderLoadCodeTables.getInstance().setSupervisionOrderSearchForm(this);
		boolean sort = true;
        orderStatusList = CodeHelper.getCodes(PDCodeTableConstants.ORDER_STATUS, sort);
		versionTypeList = CodeHelper.getCodes(PDCodeTableConstants.VERSION_TYPE, sort);
		raceList = CodeHelper.getRaceCodes(sort);
		sexList = CodeHelper.getSexCodes(sort);
		stateList = CodeHelper.getStateCodes(sort);
    }

    public void clear()
    {
        // Never clear the action
        //		action = "";
        caseNum = "";
        cdi = "";
        court = "";
        cjis = "";
        dateOfBirth = null;
        driverLicenseNum = "";
        connection = "";
        fbiNum = "";
        firstName = "";
        lastName = "";
        middleName = "";
        name = new Name();
        offense = "";
        offenseId = "";
        orderId = "";
        versionTypeId = "";
        race = "";
        sex = "";
        sid = "";
        spn = "";
        ssn1 = "";
        ssn2 = "";
        ssn3 = "";
        ssn = new SocialSecurity("");

        superviseeListSize = "";
        caseOrderListSize = "";

        //	Drop Downs
        connectionId = "";
        raceId = "";
        sexId = "";
        stateId = "";

        // Collections and Drop Down Lists
        superviseeList = new ArrayList();
        caseOrderList = new ArrayList();

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
    public String getCaseNum()
    {
        return caseNum;
    }

    /**
     * @return
     */
    public List getCaseOrderList()
    {
        return caseOrderList;
    }

    /**
     * @return
     */
    public String getCaseOrderListSize()
    {
        return caseOrderListSize;
    }

    /**
     * @return
     */
    public String getCdi()
    {
        return cdi;
    }
    /**
     * @return
     */
    public String getCourt()
    {
        return court;
    }
    /**
     * @return
     */
    public String getCjis()
    {
        return cjis;
    }

    /**
     * @return
     */
    public String getConnection()
    {
        return SupervisionOrderListHelper.getCodeDescription(this.connectionList, this.connectionId);
    }

    /**
     * @return
     */
    public String getConnectionId()
    {
        return connectionId;
    }

    /**
     * @return
     */
    public Collection getConnectionList()
    {
        return connectionList;
    }

    /**
     * @return
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return
     */
    public String getDateOfBirthAsString()
    {
        if (dateOfBirth == null)
        {
            return "";
        }
        else
        {
            return DateUtil.dateToString(dateOfBirth, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public String getDriverLicenseNum()
    {
        return driverLicenseNum;
    }

    /**
     * @return
     */
    public String getFbiNum()
    {
        return fbiNum;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * @return
     */
    public Name getName()
    {
        return name;
    }

    /**
     * @return
     */
    public String getOffense()
    {
		String descr = "";
		if (offenseId != null && !offenseId.equals("")){
		    String tempOffenseId = UIUtil.stripZeroes(offenseId);
		    if (tempOffenseId != null && !tempOffenseId.equals(""))
		    {
		        descr = CodeHelper.getOffenseCodeDescription(tempOffenseId);
		    }
		}
		setOffense(descr);
		return descr;
    }

    /**
     * @return
     */
//    public Collection getOffenseCodeList()
//    {
//        return offenseCodeList;
//    }

    /**
     * @return
     */
    public String getOffenseId()
    {
        return offenseId;
    }

    /**
     * @return
     */
//    public Map getOffenseMap()
//    {
//        return offenseMap;
//    }

    /**
     * @return
     */
    public String getOrderId()
    {
        return orderId;
    }

    /**
     * @return
     */
    public String getOrderStatus()
    {
        return SupervisionOrderListHelper.getCodeDescription(this.orderStatusList, this.orderStatusId);
    }

    /**
     * @return
     */
    public String getOrderStatusId()
    {
        return orderStatusId;
    }

    /**
     * @return
     */
    public Collection getOrderStatusList()
    {
        return orderStatusList;
    }

    /**
     * @return Returns the originalOrderTitleId.
     */
    public String getOriginalOrderTitleId()
    {
        return originalOrderTitleId;
    }

    /**
     * @return
     */
    public String getRace()
    {
        return SupervisionOrderListHelper.getCodeDescription(this.raceList, this.raceId);
    }

    /**
     * @return
     */
    public String getRaceId()
    {
        return raceId;
    }

    /**
     * @return
     */
    public Collection getRaceList()
    {
        return raceList;
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
    public String getSex()
    {
        return SupervisionOrderListHelper.getCodeDescription(this.sexList, this.sexId);
    }

    /**
     * @return
     */
    public String getSexId()
    {
        return sexId;
    }

    /**
     * @return
     */
    public Collection getSexList()
    {
        return sexList;
    }

    /**
     * @return
     */
    public String getSid()
    {
        return sid;
    }

    /**
     * @return
     */
    public String getSpn()
    {
        return spn;
    }

    /**
     * @return
     */
    public SocialSecurity getSsn()
    {
        SocialSecurity socialSecurity = new SocialSecurity(this.getSsn1(), this.getSsn2(), this.ssn3);
        return socialSecurity;
    }

    /**
     * @return
     */
    public String getSsn1()
    {
        return ssn1;
    }

    /**
     * @return
     */
    public String getSsn2()
    {
        return ssn2;
    }

    /**
     * @return
     */
    public String getSsn3()
    {
        return ssn3;
    }

    public String getState()
    {
        return SupervisionOrderListHelper.getCodeDescription(this.stateList, this.stateId);
    }

    /**
     * @return
     */
    public String getStateId()
    {
        return stateId;
    }

    /**
     * @return
     */
    public Collection getStateList()
    {
        return stateList;
    }

    /**
     * @return
     */
    public Collection getSuperviseeList()
    {
        return superviseeList;
    }

    /**
     * @return
     */
    public String getSuperviseeListSize()
    {
        return superviseeListSize;
    }

	/**
	 * @return the userPosition
	 */
	public String getUserPosition() {
		return userPosition;
	}
	
    /**
     * @return
     */
    public int getVersionNum()
    {
        return versionNum;
    }

    /**
     * @return
     */
    public String getVersionType()
    {
        return versionType;
    }

    /**
     * @return
     */
    public String getVersionTypeId()
    {
        return versionTypeId;
    }

    /**
     * @return
     */
    public Collection getVersionTypeList()
    {
        return this.versionTypeList;
    }

    /**
     * @return
     */
    public boolean isDelete()
    {
        return delete;
    }

    //DAWN - THIS IS NEW
    /**
     * @return
     */
    public boolean isListsSet()
    {
        return listsSet;
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
     * @param string
     */
    public void setCaseNum(String string)
    {
        caseNum = string;
    }

    /**
     * @param collection
     */
    public void setCaseOrderList(List orders)
    {
        caseOrderList = orders;
    }

    /**
     * @param string
     */
    public void setCaseOrderListSize(String string)
    {
        caseOrderListSize = string;
    }

    /**
     * @param string
     */
    public void setCdi(String string)
    {
        cdi = string;
    }

    /**
     * @param string
     */
    public void setCourt(String string)
    {
        court = string;
    }
    
    /**
     * @param string
     */
    public void setCjis(String string)
    {
        cjis = string;
    }

    /**
     * @param string
     */
    public void setConnection(String string)
    {
        connection = string;
    }

    /**
     * @param string
     */
    public void setConnectionId(String string)
    {
        connectionId = string;
    }

    /**
     * @param collection
     */
    public void setConnectionList(Collection collection)
    {
        connectionList = collection;
    }

    /**
     * @param date
     */
    public void setDateOfBirth(Date aDate)
    {
        dateOfBirth = aDate;
    }

    /**
     * @param string
     */
    public void setDateOfBirthAsString(String string)
    {
        if (string == null || string.trim().equals(""))
        {
            dateOfBirth = null;
        }
        else
        {
            dateOfBirth = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
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
    public void setDriverLicenseNum(String string)
    {
        driverLicenseNum = string;
    }

    /**
     * @param string
     */
    public void setFbiNum(String string)
    {
        fbiNum = string;
    }

    /**
     * @param string
     */
    public void setFirstName(String string)
    {
        firstName = string;
    }

    /**
     * @param string
     */
    public void setLastName(String string)
    {
        lastName = string;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b)
    {
        listsSet = b;
    }

    /**
     * @param string
     */
    public void setMiddleName(String string)
    {
        middleName = string;
    }

    /**
     * @param name
     */
    public void setName(Name name)
    {
        this.name = name;
    }

    /**
     * @param string
     */
    public void setOffense(String string)
    {
        offense = string;
    }

    /**
     * @param collection
     */
//    public void setOffenseCodeList(Collection collection)
//    {
//        offenseCodeList = collection;
//    }

    /**
     * @param string
     */
    public void setOffenseId(String string)
    {
        offenseId = string;
    }

    /**
     * @param map
     */
//    public void setOffenseMap(Map map)
//    {
//        offenseMap = map;
//    }

    /**
     * @param string
     */
    public void setOrderId(String string)
    {
        orderId = string;
    }

    /**
     * @param string
     */
    public void setOrderStatus(String string)
    {
        orderStatus = string;
    }

    /**
     * @param string
     */
    public void setOrderStatusId(String string)
    {
        orderStatusId = string;
    }

    /**
     * @param collection
     */
    public void setOrderStatusList(Collection collection)
    {
        orderStatusList = collection;
    }

    /**
     * @param string
     */
    public void setOrderVersion(String string)
    {
        orderVersion = string;
    }

    /**
     * @param originalOrderTitleId
     *            The originalOrderTitleId to set.
     */
    public void setOriginalOrderTitleId(String originalOrderTitleId)
    {
        this.originalOrderTitleId = originalOrderTitleId;
    }

    /**
     * @param string
     */
    public void setRace(String string)
    {
        race = string;
    }

    /**
     * @param string
     */
    public void setRaceId(String string)
    {
        raceId = string;
    }

    /**
     * @param collection
     */
    public void setRaceList(Collection collection)
    {
        raceList = collection;
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
     * @param string
     */
    public void setSex(String string)
    {
        sex = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
        sexId = string;
    }

    /**
     * @param collection
     */
    public void setSexList(Collection collection)
    {
        sexList = collection;
    }

    /**
     * @param string
     */
    public void setSid(String string)
    {
        sid = string;
    }

    /**
     * @param string
     */
    public void setSpn(String string)
    {
        spn = string;
    }

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
        ssn = security;
    }

    /**
     * @param string
     */
    public void setSsn1(String string)
    {
        ssn1 = string;
    }

    /**
     * @param string
     */
    public void setSsn2(String string)
    {
        ssn2 = string;
    }

    /**
     * @param string
     */
    public void setSsn3(String string)
    {
        ssn3 = string;
    }

    /**
     * @param string
     */
    public void setStateId(String string)
    {
        stateId = string;
    }

    /**
     * @param collection
     */
    public void setStateList(Collection collection)
    {
        stateList = collection;
    }

    /**
     * @param collection
     */
    public void setSuperviseeList(Collection collection)
    {
        //		if (collection != null)
        //				{
        //					Collections.sort((List) collection);
        //				}
        superviseeList = collection;
    }

    /**
     * @param string
     */
    public void setSuperviseeListSize(String string)
    {
        superviseeListSize = string;
    }

    /**
     * @param b
     */
    public void setUpdate(boolean b)
    {
        update = b;
    }

	/**
	 * @param userPosition the userPosition to set
	 */
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	
    /**
     * @param i
     */
    public void setVersionNum(int i)
    {
        versionNum = i;
    }

    /**
     * @param string
     */
    public void setVersionType(String string)
    {
        versionType = string;
    }

    /**
     * @param string
     */
    public void setVersionTypeId(String string)
    {
        versionTypeId = string;
    }

    /**
     * @param collection
     */
    public void setVersionTypeList(Collection collection)
    {
        versionTypeList = collection;
    }

}
