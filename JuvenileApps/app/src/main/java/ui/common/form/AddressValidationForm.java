/*
 * Created on Sep 14, 2005
 *
 */
package ui.common.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author Rcooper
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressValidationForm extends ActionForm
{
    private String currentAddressInd;

    private String AddressStatus;

    private String validAddressMessage;

    private String validZipCode;

    private String validKeymap;

    private String validStreetName;

    private String validStateId;

    private double validLongitude;

    private String validAddressTypeId;

    private double validLatitude;

    private String validStreetTypeId;

    private String validCountryId;

    private String validAdditionalZipCode;

    private String validCity;

    private String validAddrNum;

    private String inputPage;

    private String validAddress2;

    private String validAptNum;

    private String validAddressId;

    private String validStreetNum;

    /**
     * @return
     */
    public String getCurrentAddressInd()
    {
        return currentAddressInd;
    }

    /**
     * @return
     */
    public String getValidAdditionalZipCode()
    {
        return validAdditionalZipCode;
    }

    /**
     * @return
     */
    public String getValidAddress2()
    {
        return validAddress2;
    }

    /**
     * @return
     */
    public String getValidAddressId()
    {
        return validAddressId;
    }

    /**
     * @return
     */
    public String getAddressStatus()
    {
        return AddressStatus;
    }

    /**
     * @return
     */
    public String getValidAddressTypeId()
    {
        return validAddressTypeId;
    }

    /**
     * @return
     */
    public String getValidAptNum()
    {
        return validAptNum;
    }

    /**
     * @return
     */
    public String getValidCity()
    {
        return validCity;
    }

    /**
     * @return
     */
    public String getValidCountryId()
    {
        return validCountryId;
    }

    /**
     * @return
     */
    public String getValidKeymap()
    {
        return validKeymap;
    }

    /**
     * @return
     */
    public double getValidLatitude()
    {
        return validLatitude;
    }

    /**
     * @return
     */
    public double getValidLongitude()
    {
        return validLongitude;
    }

    /**
     * @return
     */
    public String getValidStateId()
    {
        return validStateId;
    }

    /**
     * @return
     */
    public String getValidStreetName()
    {
        return validStreetName;
    }

    /**
     * @return
     */
    public String getValidStreetNum()
    {
        return validStreetNum;
    }

    /**
     * @return
     */
    public String getValidStreetTypeId()
    {
        return validStreetTypeId;
    }

    /**
     * @return
     */
    public String getValidZipCode()
    {
        return validZipCode;
    }

    /**
     * @param string
     */
    public void setCurrentAddressInd(String string)
    {
        currentAddressInd = string;
    }

    /**
     * @param string
     */
    public void setValidAdditionalZipCode(String string)
    {
        validAdditionalZipCode = string;
    }

    /**
     * @param string
     */
    public void setValidAddress2(String string)
    {
        validAddress2 = string;
    }

    /**
     * @param string
     */
    public void setValidAddressId(String string)
    {
        validAddressId = string;
    }

    /**
     * @param string
     */
    public void setAddressStatus(String string)
    {
        AddressStatus = string;
    }

    /**
     * @param string
     */
    public void setValidAddressTypeId(String string)
    {
        validAddressTypeId = string;
    }

    /**
     * @param string
     */
    public void setValidAptNum(String string)
    {
        validAptNum = string;
    }

    /**
     * @param string
     */
    public void setValidCity(String string)
    {
        validCity = string;
    }

    /**
     * @param string
     */
    public void setValidCountryId(String string)
    {
        validCountryId = string;
    }

    /**
     * @param string
     */
    public void setValidKeymap(String string)
    {
        validKeymap = string;
    }

    /**
     * @param d
     */
    public void setValidLatitude(double d)
    {
        validLatitude = d;
    }

    /**
     * @param d
     */
    public void setValidLongitude(double d)
    {
        validLongitude = d;
    }

    /**
     * @param string
     */
    public void setValidStateId(String string)
    {
        validStateId = string;
    }

    /**
     * @param string
     */
    public void setValidStreetName(String string)
    {
        validStreetName = string;
    }

    /**
     * @param string
     */
    public void setValidStreetNum(String string)
    {
        validStreetNum = string;
    }

    /**
     * @param string
     */
    public void setValidStreetTypeId(String string)
    {
        validStreetTypeId = string;
    }

    /**
     * @param string
     */
    public void setValidZipCode(String string)
    {
        validZipCode = string;
    }

    /**
     * @return
     */
    public String getValidAddressMessage()
    {
        return validAddressMessage;
    }

    /**
     * @param string
     */
    public void setValidAddressMessage(String string)
    {
        validAddressMessage = string;
    }

    /**
     * @return
     */
    public String getInputPage()
    {
        return inputPage;
    }

    /**
     * @return
     */
    public String getValidAddrNum()
    {
        return validAddrNum;
    }

    /**
     * @param string
     */
    public void setInputPage(String string)
    {
        inputPage = string;
    }

    /**
     * @param string
     */
    public void setValidAddrNum(String string)
    {
        validAddrNum = string;
    }

    public List getAssociateAddresses()
    {
        return new ArrayList();
    }

}
