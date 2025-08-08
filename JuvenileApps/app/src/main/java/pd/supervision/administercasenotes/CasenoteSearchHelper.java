// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\CasenoteSearchHelper.java

package pd.supervision.administercasenotes;

import java.util.Date;
import java.util.Iterator;

import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.supervisionorder.SupervisionOrderHelper;

import messaging.administercasenotes.GetCasenotesByAdvancedSearchEvent;
import messaging.administercasenotes.GetCasenotesByCollateralEvent;
import messaging.administercasenotes.GetCasenotesByIdsEvent;
import messaging.administercasenotes.GetCasenotesBySubjectEvent;
import messaging.administercasenotes.GetCasenotesBySupervisionOrderValuesEvent;
import messaging.administercasenotes.GetCasenotesEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;

public class CasenoteSearchHelper {
    public static String ALL = "AL";

    /**
     * @roseuid 450975000344
     */
    public CasenoteSearchHelper() {

    }

    public static Iterator getCasenotesAdvancedSearch(
            GetCasenotesEvent getCasenotesEvent) {
        GetCasenotesByAdvancedSearchEvent advSearchEvent = new GetCasenotesByAdvancedSearchEvent();
        advSearchEvent.setAssociateIds(getCasenotesEvent.getAssociateIds());
        advSearchEvent.setBeginDate(getCasenotesEvent.getBeginDate());
        advSearchEvent.setEndDate(getCasenotesEvent.getEndDate());

        if (getCasenotesEvent.getCaseIds() != null
                && getCasenotesEvent.getCaseIds().size() > 0) {
            advSearchEvent.setCaseIds(getCasenotesEvent.getCaseIds());
            GetCasenotesBySupervisionOrderValuesEvent getBySoValuesEvent = new GetCasenotesBySupervisionOrderValuesEvent();
            getBySoValuesEvent.setCaseIds(advSearchEvent.getCaseIds());
            getBySoValuesEvent = CasenoteSearchHelper.calculateSearchDateRange(getBySoValuesEvent);
            advSearchEvent.setBeginDate(getBySoValuesEvent.getBeginDate());
            advSearchEvent.setEndDate(getBySoValuesEvent.getEndDate());
        }
        advSearchEvent.setCasenoteTypeId(getCasenotesEvent.getCasenoteTypeId());
        advSearchEvent.setCollateralId(getCasenotesEvent.getCollateralId());

        if (getCasenotesEvent.getCourtNum() != null
                && !getCasenotesEvent.getCourtNum().equals("")) {
            Court court = Court.find("courtNumber", getCasenotesEvent
                    .getCourtNum());
            //Set event with court OID.
            if (court != null) {
                advSearchEvent.setCourtNum(court.getOID().toString());
            }
        }
        
        advSearchEvent.setHowGeneratedId(getCasenotesEvent.getHowGeneratedId());
        advSearchEvent.setSubjects(getCasenotesEvent.getSubjects());
        advSearchEvent.setSuperviseeId(getCasenotesEvent.getSuperviseeId());
        advSearchEvent.setSupervisionPeriodId(getCasenotesEvent
                .getSupervisionPeriodId());
        if (advSearchEvent.getHowGeneratedId() != null
                && (advSearchEvent.getHowGeneratedId().equals(ALL)
                		|| advSearchEvent.getHowGeneratedId().equals("CB"))) {
        	advSearchEvent.setHowGeneratedId(null);
        }
        if (advSearchEvent.getCasenoteTypeId() != null
                && advSearchEvent.getCasenoteTypeId().equals(ALL)) {
        	advSearchEvent.setCasenoteTypeId(null);
        }
 
        return Casenote.findAll(advSearchEvent);
    }

    /**
     * @param getCasenotesEvent
     * @return java.util.Iterator
     * @return java.util.Collection
     * @roseuid 45072F890037
     */
    public static Iterator getCasenotesBySubject(
            GetCasenotesEvent getCasenotesEvent) {

        GetCasenotesBySubjectEvent getBySubject = new GetCasenotesBySubjectEvent();
        getBySubject.setSubjects(getCasenotesEvent.getSubjects());
        getBySubject.setSuperviseeId(SpnCasenotesHandler.padSpn(getCasenotesEvent.getSuperviseeId()));
        getBySubject.setSupervisionPeriodId(getCasenotesEvent
                .getSupervisionPeriodId());
        return Casenote.findAll(getBySubject);

    }
    /**
     * @param getCasenotesEvent
     * @return java.util.Iterator
     * @roseuid 45072F890037
     */
    public static Iterator getCasenotesByAssociate(
            GetCasenotesEvent getCasenotesEvent) {

        GetCasenotesByCollateralEvent getByCollateralEvent = new GetCasenotesByCollateralEvent();
        
        getByCollateralEvent.setCollateralId(getCasenotesEvent.getCollateralId());
        getByCollateralEvent.setAssociateIds(getCasenotesEvent.getAssociateIds());
        getByCollateralEvent.setSuperviseeId(getCasenotesEvent.getSuperviseeId());
        getByCollateralEvent.setSupervisionPeriodId(getCasenotesEvent.getSupervisionPeriodId());
        return Casenote.findAll(getByCollateralEvent);

    }

    /**
     * @param caseNums
     * @param superviseeId
     * @param supervisionPeriodId
     * @return java.util.Collection
     * @roseuid 4508438D0238
     */
    public static Iterator getCasenotesBySupervisionOrderValues(
            GetCasenotesEvent getEvent) {
        GetCasenotesBySupervisionOrderValuesEvent getBySoValuesEvent = new GetCasenotesBySupervisionOrderValuesEvent();
        getBySoValuesEvent.setSuperviseeId(getEvent.getSuperviseeId());
        getBySoValuesEvent.setSupervisionPeriodId(getEvent.getSupervisionPeriodId());
        boolean isExists = false;
        if (getEvent.getCourtNum() != null && !getEvent.getCourtNum().equals("")) {
            Court court = Court.find("courtNumber", getEvent.getCourtNum());
            //Set event with court OID.
            if (court != null) {
                getBySoValuesEvent.setCourtNum(court.getOID().toString());
            }
            isExists = true;
        }
        if (getEvent.getCaseIds() != null && getEvent.getCaseIds().size() > 0) {
            Iterator iter = getEvent.getCaseIds().iterator();
            if (iter != null && iter.hasNext()) {
                String caseId = (String) iter.next();
                if (caseId.equals(ALL)) {
                    getBySoValuesEvent.setCaseIds(null);
                } else {
                    getBySoValuesEvent.setCaseIds(getEvent.getCaseIds());
                    getBySoValuesEvent = CasenoteSearchHelper.calculateSearchDateRange(getBySoValuesEvent);
                }
            }
            isExists = true;
        }
        
        if (getEvent.getCollateralId() != null && !getEvent.getCollateralId().equals("")){
            getBySoValuesEvent.setCollateralId(getEvent.getCollateralId());
            isExists = true;
        }

        Iterator iterator = null;
        if(!isExists){
        	// quick fix for performance
        	GetCasenotesByIdsEvent gEvent = new GetCasenotesByIdsEvent();
        	gEvent.setSpn(getEvent.getSuperviseeId());
        	gEvent.setSupervisionPeriodId(getEvent.getSupervisionPeriodId());
        	iterator = Casenote.findAll(gEvent);
        }else{
            iterator = Casenote.findAll(getBySoValuesEvent);        	
        }
        return iterator;
    }

    /**
     * @param getBySoValuesEvent
     */
    private static GetCasenotesBySupervisionOrderValuesEvent calculateSearchDateRange(
            GetCasenotesBySupervisionOrderValuesEvent getBySoValuesEvent) {
        Date beginDate = null;
        Date endDate = null;
        Iterator iter = getBySoValuesEvent.getCaseIds().iterator();
        String caseId = null;
        String agencyId = PDSecurityHelper.getUserAgencyId();
        
        while (iter.hasNext()) {
            caseId = (String) iter.next();
            SupervisionPeriodResponseEvent spre = SupervisionOrderHelper.getCaseSupervisionPeriod(agencyId, caseId);
            if (beginDate == null
                    || spre.getSupervisionBeginDate().before(beginDate)) {
                beginDate = spre.getSupervisionBeginDate();
            }
            if (spre.getSupervisionEndDate() == null){
                endDate = new Date();
            } else if (endDate != null && spre.getSupervisionEndDate().after(endDate)){	
                		endDate = spre.getSupervisionEndDate();
            } else {
                endDate = spre.getSupervisionEndDate();
            }
            
        }
        getBySoValuesEvent.setBeginDate(beginDate);
        getBySoValuesEvent.setEndDate(endDate);
        return getBySoValuesEvent;
    }

    /**
     * @param getEvent
     * @return
     */
    public static Iterator getCasenotes(GetCasenotesEvent getEvent) {
        if (getEvent.getHowGeneratedId() != null
                && (getEvent.getHowGeneratedId().equals(ALL)
                		|| getEvent.getHowGeneratedId().equals("CB"))) {
            getEvent.setHowGeneratedId(null);
        }
        if (getEvent.getCasenoteTypeId() != null
                && getEvent.getCasenoteTypeId().equals(ALL)) {
            getEvent.setCasenoteTypeId(null);
        }
 
        return Casenote.findAll(getEvent);
    }
}
