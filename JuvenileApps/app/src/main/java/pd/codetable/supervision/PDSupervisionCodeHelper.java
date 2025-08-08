/*
 * Created on Apr 17, 2007
 *
 */
package pd.codetable.supervision;

import java.util.Iterator;

import pd.security.PDSecurityHelper;

import messaging.codetable.GetSupervisionCodesByCodeEvent;
import messaging.codetable.GetSupervisionCodesByValueEvent;
import messaging.codetable.GetSupervisionCodesEvent;

/**
 * @author dgibler
 *  
 */
public class PDSupervisionCodeHelper {
    /**
     * @param codeTableName
     * @return
     */
    public static Iterator getSupervisionCodes(String codetableName) {
        Iterator iter = null;
        if (codetableName != null) {
            iter = PDSupervisionCodeHelper.getSupervisionCodes(PDSecurityHelper.getUserAgencyId(),codetableName);
        }
        return iter;
    }
    /**
     * @param agencyId
     * @param codeTableName
     * @return
     */
    public static Iterator getSupervisionCodes(String agencyId, String codetableName) {
        Iterator iter = null;
        if (codetableName != null) {
            GetSupervisionCodesEvent codeTableEvent = new GetSupervisionCodesEvent();
            codeTableEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
            codeTableEvent.setCodeTableName(codetableName);
            iter = SupervisionCode.findAll(codeTableEvent);
        }
        return iter;
    }
    /**
     * @param codetableName
     * @param codeValue
     * @return
     */
    public static SupervisionCode getSupervisionCodeByValue(String codetableName, String codeValue) {
        SupervisionCode code = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDSecurityHelper.getUserAgencyId(), codetableName, codeValue);
        return code;
    }
    /**
     * @param agencyId
     * @param codetableName
     * @param codeValue
     * @return
     */
    public static SupervisionCode getSupervisionCodeByValue(String agencyId, String codetableName, String codeValue) {
        GetSupervisionCodesByCodeEvent getCodesEvent = new GetSupervisionCodesByCodeEvent();
        getCodesEvent.setCodeTableName(codetableName);
        getCodesEvent.setAgencyId(agencyId);
        getCodesEvent.setCodeId(codeValue);
        Iterator iter = SupervisionCode.findAll(getCodesEvent);
        SupervisionCode code = null;
        if (iter != null && iter.hasNext()) {
            code = (SupervisionCode) iter.next();
        }
        return code;
    }
    
    /**
     * @param agencyId
     * @param codetableName
     * @param codeValue
     * @return
     */
    public static Iterator getSupervisionCodesByValue(String codetableName, String codeValue) {
    	GetSupervisionCodesByValueEvent getCodesEvent = new GetSupervisionCodesByValueEvent();
        getCodesEvent.setCodeTableName(codetableName);
        getCodesEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        getCodesEvent.setDescription(codeValue);
        return SupervisionCode.findAll(getCodesEvent);
    }
    
    
    /**
     * @param OID(codeId)
     * @return
     */
    public static SupervisionCode getSupervisionCodeByCodeId(String codeId) {
        SupervisionCode code = SupervisionCode.find(codeId);
        return code;
    }
}
