/*
 * Created on Aug 24, 2005
 *
 */
package ui.supervision.SupervisionOptions;

import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.REMatch;
import gnu.regexp.RESyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.supervisionoptions.GetAllCourtsEvent;
import messaging.supervisionoptions.GetAllGroupsEvent;
import messaging.supervisionoptions.GetAssociatedConditionsForCourtPolicyEvent;
import messaging.supervisionoptions.GetAssociatedConditionsForDepartmentPolicyEvent;
import messaging.supervisionoptions.GetCourtPoliciesInUseEvent;
import messaging.supervisionoptions.GetCourtPolicyDetailsEvent;
import messaging.supervisionoptions.GetDepartmentPolicyDetailsEvent;
import messaging.supervisionoptions.GetDeptPoliciesInUseEvent;
import messaging.supervisionoptions.GetDetailDictionaryEvent;
import messaging.supervisionoptions.GetFilteredCourtsEvent;
import messaging.supervisionoptions.GetSupervisionConditionDetailsEvent;
import messaging.supervisionoptions.ValidateCourtPolicyEvent;
import messaging.supervisionoptions.ValidateDepartmentPolicyEvent;
import messaging.supervisionoptions.ValidateSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyInUseResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyInUseResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionOptionsControllerServiceNames;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

/**
 * @author bschwartz
 * 
  */
public class UISupervisionOptionHelper {
    private static final String[] defaultDateFormats = { "MM/dd/yyyy", "MM/dd/yy" };

    /**
     * Attempt to parse a string into a date using a varity of formats.
     */
    public static Date parseDate(String aStringDate, String[] formats) {
        if (aStringDate == null || aStringDate.trim().length() == 0) {
            return null;
        }

        Date date = null;
        int formatIndex = 0;
        String format = formats[formatIndex];

        while (date == null && format != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            dateFormatter.setLenient(false);
            try {
                date = dateFormatter.parse(aStringDate);
                break;
            } catch (ParseException e) {
                /// continue
            }
            format = formats[++formatIndex];
        }
        return date;
    }

    public static Date parseDate(String aStringDate) {
        return parseDate(aStringDate, defaultDateFormats);
    }

    public static String formatDate(Date aDate) {
        if (aDate == null) {
            return null;
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat(defaultDateFormats[0]);
        return dateFormatter.format(aDate);
    }

    public static boolean loadSupervisionConditionForm(SupervisionConditionForm form, String conditionId) {
        GetSupervisionConditionDetailsEvent postEvt = new GetSupervisionConditionDetailsEvent();

        postEvt.setConditionId(conditionId);
        
        CompositeResponse compositeResponse = MessageUtil.postRequest(postEvt);
        
        List searchResults = MessageUtil.compositeToList(compositeResponse, ConditionDetailResponseEvent.class);

        Iterator iter = searchResults.iterator();
        if (iter.hasNext()) {
            ConditionDetailResponseEvent reply = (ConditionDetailResponseEvent) iter.next();

            form.setConditionId(reply.getConditionId());
            form.setConditionName(reply.getName());
            form.setGroup1Id(reply.getGroup1Id());
            form.setGroup1Name(reply.getGroup1Name());
            form.setGroup2Id(reply.getGroup2Id());
            form.setGroup2Name(reply.getGroup2Name());
            form.setGroup3Id(reply.getGroup3Id());
            form.setGroup3Name(reply.getGroup3Name());

            if (reply.isSpecialCondition()) {
            	form.setSpecialCondition(true);
                form.setFixedLiteral(reply.getResolvedDescription());
                form.setConditionLiteral(replaceTags(reply.getDescription()));
                form.setConditionSpanishLiteral(reply.getSpanishDescription());
                
            } else {
            	form.setSpecialCondition(false);
            	form.setConditionLiteral(replaceTags(reply.getDescription()));
                form.setConditionSpanishLiteral(reply.getSpanishDescription());
            }
            form.setEffectiveDate(UISupervisionOptionHelper.formatDate(reply.getEffectiveDate()));
            form.setInactiveDate(UISupervisionOptionHelper.formatDate(reply.getInactiveDate()));
            form.setDeleteNote(reply.getInactiveReason());
            form.setStandard(reply.isStandard());
            form.setNotes(reply.getNotes());
            form.setSeverityLevelId(reply.getSeverityLevelId());
            form.setJurisdictionId(reply.getJurisdictionId());

            //look up names from code
            if (form.getSeverityLevelId() != null) {
                Map map = form.getSeverityLevelsMap();
                if (map == null)
                    form.getSeverityLevels();

                String severityLevelDescription = (String) form.getSeverityLevelsMap().get(form.getSeverityLevelId());

                form.setSeverityLevel(severityLevelDescription);
            }

            if (form.getJurisdictionId() != null) {
                Map map = form.getJurisdictionsMap();
                if (map == null)
                    form.getJurisdictions();
                String jurisdiction = (String) form.getJurisdictionsMap().get(form.getJurisdictionId());
                form.setJurisdiction(jurisdiction);
            }

            if (form.getPeriodsMap() == null) {
                form.getPeriods();
            }
            form.setPeriod((String) form.getPeriodsMap().get(reply.getEventPeriodTypeId()));
            form.setPeriodId(reply.getEventPeriodTypeId());
            form.setPeriodValue(String.valueOf(reply.getEventPeriodValue()));
            form.setEventCount(String.valueOf(reply.getEventCount()));

            //get the list of documents
            Collection selDocumentList = reply.getDocuments();
            Iterator selDocumentListIter = selDocumentList.iterator();
            // set selected document ids
            String[] docIds = new String[selDocumentList.size()];
            form.setSelDocumentIds((String[]) selDocumentList.toArray(docIds));
            // set selected document names
            StringBuffer selDocuments = new StringBuffer();
            if (form.getDocumentsMap() == null) {
                form.getDocuments();
            }
            while (selDocumentListIter.hasNext()) {
                String documentId = (String) selDocumentListIter.next();
                String name = (String) form.getDocumentsMap().get(documentId);
                selDocuments.append(name);
                if (selDocumentListIter.hasNext()) {
                    selDocuments.append(", ");
                }
            }
            form.setSelDocuments(selDocuments.toString());

            //get SupervisionTypes
            Collection supTypeList = reply.getSupervisionTypes();
            Iterator supTypeIter = supTypeList.iterator();
            // set selected supervisionType ids
            String[] supTypeIds = new String[supTypeList.size()];
            form.setSelSupervisionTypes((String[]) supTypeList.toArray(supTypeIds));
            // set selected supervisionType names
            StringBuffer selSupTypeNames = new StringBuffer();
            if (form.getSupervisionTypesMap() == null || form.getSupervisionTypesMap().isEmpty()) {
                form.getSupervisionTypes();
            }
            while (supTypeIter.hasNext()) {
                String supTypeId = (String) supTypeIter.next();
                String name = (String) form.getSupervisionTypesMap().get(supTypeId);
                selSupTypeNames.append(name);
                if (supTypeIter.hasNext()) {
                    selSupTypeNames.append(", ");
                }
            }
            form.setDispSelSupTypes(selSupTypeNames.toString());
            if(form.getSelSupervisionTypes()!=null){
				form.setOldSelSupervisionTypes(form.getSelSupervisionTypes());
			}
			else{
				form.setOldSelSupervisionTypes(null);
			}

            //get the list of event types
            Collection selEventTypesList = reply.getEventTypes();
            Iterator selEventTypesListIter = selEventTypesList.iterator();
            // set selected eventType ids
            String[] strings = new String[selEventTypesList.size()];
            form.setSelectedEventTypeIds((String[]) selEventTypesList.toArray(strings));
            // set selected eventtype names
            StringBuffer selEventTypes = new StringBuffer();
            if (form.getEventTypesMap() == null) {
                form.getEventTypes();
            }
            while (selEventTypesListIter.hasNext()) {
                String eventType = (String) selEventTypesListIter.next();
                if (eventType != null && !eventType.equals("") && !eventType.equalsIgnoreCase("null")) {
                    selEventTypes.append((String) form.getEventTypesMap().get(eventType));
                    if (selEventTypesListIter.hasNext()) {
                        selEventTypes.append(", ");
                    }
                }
            }
            form.setSelectedEventTypes(selEventTypes.toString());

            //convert back variable elements to selected courts, variable
            // elements & exception court variable elements
            Map map = new HashMap(); //variable elements in normal courts
            Map exceptionCourts = new HashMap(); //map of CourtBeans (exception
                                                 // courts)
            Map cvesMap = new HashMap(); //map of CourtVariableElementBean
            Map courtsMap = createCourtMap(form.getCourts()); //look up map of
                                                              // all the courts
            Map courtBeans = new HashMap(); //

            HashMap exceptionCourtVarElemBeans = new HashMap();

            Iterator it = reply.getVariableElements().iterator();
            while (it.hasNext()) {

                VariableElementResponseEvent vere = (VariableElementResponseEvent) it.next();
                createCourtBeans(vere, courtsMap, courtBeans);

                if (!vere.isExceptionCourt()) {
                    if (!map.containsKey(vere.getVariableElementTypeId())) {
                        map.put(vere.getVariableElementTypeId(), vere);
                    }
                } else {
                    if (!exceptionCourts.containsKey(vere.getVariableElementTypeId())) {
                        exceptionCourts.put(vere.getVariableElementTypeId(), vere);
                    }

                    if (!cvesMap.containsKey(vere.getVariableElementTypeId())) {
                        CourtVariableElementBean cve = new CourtVariableElementBean();
                        cve.setCourtNumber(vere.getCourtId());
                        Collection cves = new ArrayList();
                        cves.add(vere);
                        cve.setVariableElements(cves);
                        cvesMap.put(vere.getCourtId(), cve);
                    } else {
                        //look up and put the variable element in the list
                        CourtVariableElementBean cve = (CourtVariableElementBean) cvesMap.get(vere.getVariableElementTypeId());
                        cve.getVariableElements().add(vere);
                    }

                    //create/append to exceptionCourtVarElemBeans
                    if (exceptionCourtVarElemBeans.containsKey(vere.getCourtId())) {
                        CourtVariableElementBean cve = (CourtVariableElementBean) exceptionCourtVarElemBeans.get(vere.getCourtId());

                        Collection veres = cve.getVariableElements();

                        //guarantee if there's a cve, there's at least a vere,
                        // no duplicate is allowed too
                        veres.add(vere);

                    } else {
                        CourtVariableElementBean cve = new CourtVariableElementBean();
                        cve.setCourtNumber(vere.getCourtId());

                        Collection veres = new ArrayList();
                        veres.add(vere);
                        cve.setVariableElements(veres);
                        exceptionCourtVarElemBeans.put(vere.getCourtId(), cve);
                    }

                }
            }

            form.setVariableElementResponseEvents(map.values());
            form.setSelectedCourts(courtBeans.values());
            form.setExceptionCourtVarElemBeans(exceptionCourtVarElemBeans.values());

            //--------- ASSOCIATIONS -----------------
            // clear collections
            form.clearAssociatedCourtPolicies();
            form.clearAssociatedDeptPolicies();
            // get associated CourtPolicies
            List courtPolicies = MessageUtil.compositeToList(compositeResponse, CourtPolicyResponseEvent.class);
            MessageUtil.processEmptyCollection(courtPolicies);
            if (courtPolicies != null) {
                Iterator cPols = courtPolicies.iterator();
                while (cPols.hasNext()) {
                    CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent) cPols.next();
                    AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cpre);
                    form.insertAssociatedCourtPolicy(asscBean);
                }
            }

            // get associated DepartmentPolicies
            List deptPolicies = MessageUtil.compositeToList(compositeResponse, DepartmentPolicyResponseEvent.class);
            MessageUtil.processEmptyCollection(deptPolicies);
            if (deptPolicies != null) {
                Iterator dPols = deptPolicies.iterator();
                while (dPols.hasNext()) {
                    DepartmentPolicyResponseEvent dpre = (DepartmentPolicyResponseEvent) dPols.next();
                    AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(dpre);
                    form.insertAssociatedDeptPolicy(asscBean);
                }
            }
            return true;
        }
        return false;
    }

    public static Map createCourtMap(Collection courts) {
        Map map = new HashMap();
        Iterator it = courts.iterator();
        while (it.hasNext()) {
            CourtBean cb = (CourtBean) it.next();
            Iterator cres = cb.getCourts().iterator();
            while (cres.hasNext()) {
                CourtResponseEvent cre = (CourtResponseEvent) cres.next();
                map.put(cre.getCourtId(), cre);
            }
        }
        return map;
    }

    private static boolean createCourtBeans(VariableElementResponseEvent vere, Map courtsMap, Map courtBeans) {
        String category = null;

        //get category of this court
        CourtResponseEvent cre = (CourtResponseEvent) courtsMap.get(vere.getCourtId());

        if (cre == null) {
            //error condition, unknown court Id.
            return false;
        } else {
            category = cre.getCourtCategory();

            if (!courtBeans.containsKey(category)) {
                //create new category and insert the court
                CourtBean cb = new CourtBean();
                cb.setCategory(category);
                cb.setCategoryDesc(cre.getCourtCategoryDesc());

                Collection courts = new ArrayList();
                cre.setExceptionCourt(vere.isExceptionCourt());
                courts.add(cre);
                cb.setCourts(courts);

                courtBeans.put(category, cb);
                return true;
            } else {
                CourtBean cb = (CourtBean) courtBeans.get(category);
                Collection courts = cb.getCourts();

                if (courts != null && courts.size() > 0) {
                    //iterate through courts in each category
                    Iterator courtsIter = courts.iterator();

                    boolean courtFound = false;

                    while (courtsIter.hasNext()) {
                        CourtResponseEvent cre2 = (CourtResponseEvent) courtsIter.next();
                        if (cre2.getCourtId().equalsIgnoreCase(vere.getCourtId())) {
                            courtFound = true;
                            break;
                        }
                    }

                    if (!courtFound) {
                        cre.setExceptionCourt(vere.isExceptionCourt());
                        courts.add(cre);
                    }

                }

            }

            /*
             * //get the category of the court
             * if(courtsMap.containsKey(vere.getCourtId())) {
             *  }
             */
            return true;
        }
    }

    public static boolean loadCourtPolicyForm(CourtPolicyForm form, String policyId) {
        GetCourtPolicyDetailsEvent postEvt = new GetCourtPolicyDetailsEvent();

        postEvt.setCourtPolicyId(policyId);

        CompositeResponse compositeResponse = MessageUtil.postRequest(postEvt);
        Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, CourtPolicyDetailResponseEvent.class);

        Iterator iter = searchResults.iterator();
        if (iter.hasNext()) {
            CourtPolicyDetailResponseEvent reply = (CourtPolicyDetailResponseEvent) iter.next();

            //form.clear();
            form.setPolicyId(reply.getPolicyId());
            form.setCourtPolicyName(reply.getName());
            form.setGroup1Id(reply.getGroup1Id());
            form.setGroup1Name(reply.getGroup1Name());
            form.setGroup2Id(reply.getGroup2Id());
            form.setGroup2Name(reply.getGroup2Name());
            form.setGroup3Id(reply.getGroup3Id());
            form.setGroup3Name(reply.getGroup3Name());
            form.setCourtPolicyLiteral(reply.getDescription());
            form.setEffectiveDate(UISupervisionOptionHelper.formatDate(reply.getEffectiveDate()));
            form.setInactiveDate(UISupervisionOptionHelper.formatDate(reply.getInactiveDate()));
            form.setStandard(reply.isStandard());
            form.setNotes(reply.getNotes());

            form.setDepartmentPolicy(reply.isDepartmentPolicy());

            if (form.getPeriodsMap() == null) {
                form.getPeriods();
            }
            form.setPeriod((String) form.getPeriodsMap().get(reply.getEventPeriodTypeId()));
            form.setPeriodValue(String.valueOf(reply.getEventPeriodValue()));
            form.setEventCount(String.valueOf(reply.getEventCountValue()));
            //get the list of event types
            Collection selEventTypesList = reply.getEventTypes();
            Iterator selEventTypesListIter = selEventTypesList.iterator();

            StringBuffer selEventTypes = new StringBuffer();
            if (form.getEventTypesMap() == null) {
                form.getEventTypes();
            }
            while (selEventTypesListIter.hasNext()) {
                String eventType = (String) selEventTypesListIter.next();
                if (eventType != null && !eventType.equals("") && !eventType.equalsIgnoreCase("null")) {

                    selEventTypes.append((String) form.getEventTypesMap().get(eventType));
                    if (selEventTypesListIter.hasNext()) {
                        selEventTypes.append(", ");
                    }
                }
            }
            form.setEventTypesAsString(selEventTypes.toString());

            String[] strings = new String[selEventTypesList.size()];
            form.setSelectedEventTypeIds((String[]) selEventTypesList.toArray(strings));

            //convert back variable elements to selected courts, variable
            // elements & exception court variable elements
            Map map = new HashMap(); //variable elements in normal courts
            Map exceptionCourts = new HashMap(); //map of CourtBeans (exception
                                                 // courts)
            Map cvesMap = new HashMap(); //map of CourtVariableElementBean
            Map courtsMap = createCourtMap(form.getCourts()); //look up map of
                                                              // all the courts
            Map courtBeans = new HashMap(); //

            HashMap exceptionCourtVarElemBeans = new HashMap();

            Iterator it = reply.getVariableElements().iterator();
            while (it.hasNext()) {

                VariableElementResponseEvent vere = (VariableElementResponseEvent) it.next();
                createCourtBeans(vere, courtsMap, courtBeans);

                if (!vere.isExceptionCourt()) {
                    if (!map.containsKey(vere.getVariableElementTypeId())) {
                        map.put(vere.getVariableElementTypeId(), vere);
                    }
                } else {
                    if (!exceptionCourts.containsKey(vere.getVariableElementTypeId())) {
                        exceptionCourts.put(vere.getVariableElementTypeId(), vere);
                    }

                    if (!cvesMap.containsKey(vere.getVariableElementTypeId())) {
                        CourtVariableElementBean cve = new CourtVariableElementBean();
                        cve.setCourtNumber(vere.getCourtId());
                        Collection cves = new ArrayList();
                        cves.add(vere);
                        cve.setVariableElements(cves);
                        cvesMap.put(vere.getCourtId(), cve);
                    } else {
                        //look up and put the variable element in the list
                        CourtVariableElementBean cve = (CourtVariableElementBean) cvesMap.get(vere.getVariableElementTypeId());
                        cve.getVariableElements().add(vere);
                    }

                    //create/append to exceptionCourtVarElemBeans
                    if (exceptionCourtVarElemBeans.containsKey(vere.getCourtId())) {
                        CourtVariableElementBean cve = (CourtVariableElementBean) exceptionCourtVarElemBeans.get(vere.getCourtId());

                        Collection veres = cve.getVariableElements();

                        //guarantee if there's a cve, there's at least a vere,
                        // no duplicate is allowed too
                        veres.add(vere);

                    } else {
                        CourtVariableElementBean cve = new CourtVariableElementBean();
                        cve.setCourtNumber(vere.getCourtId());

                        Collection veres = new ArrayList();
                        veres.add(vere);
                        cve.setVariableElements(veres);
                        exceptionCourtVarElemBeans.put(vere.getCourtId(), cve);
                    }
                }
            }

            form.setVariableElementResponseEvents(map.values());
            form.setSelectedCourts(courtBeans.values());
            form.setExceptionCourtVarElemBeans(exceptionCourtVarElemBeans.values());

            //------- ASSOCIATIONS-----------
            // clear collections
            form.clearAssociatedConditions();
            // get associated CourtPolicies
            Collection conditions = MessageUtil.compositeToCollection(compositeResponse, ConditionResponseEvent.class);
            MessageUtil.processEmptyCollection(conditions);

            if (conditions != null) {
                Iterator conds = conditions.iterator();
                while (conds.hasNext()) {
                    ConditionResponseEvent cre = (ConditionResponseEvent) conds.next();
                    AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cre);
                    form.insertAssociatedCondition(asscBean);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isCourtPolicyInUse(String courtPolicyId) {
        if ((isCourtPolicyInUse(courtPolicyId, null)) != null) {
            return true;
        } else
            return false;
    }

    public static Collection isCourtPolicyInUse(String courtPolicyId, CourtPolicyForm aForm) {
 
        GetCourtPoliciesInUseEvent requestEvent = (GetCourtPoliciesInUseEvent) EventFactory
                .getInstance(SupervisionOptionsControllerServiceNames.GETCOURTPOLICIESINUSE);

        requestEvent.setPolicyId(courtPolicyId);

        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        CourtPolicyInUseResponseEvent responseEvent = (CourtPolicyInUseResponseEvent) MessageUtil
                .filterComposite(response, CourtPolicyInUseResponseEvent.class);
        if (responseEvent != null) {

            return responseEvent.getCourtIds();
        } else {
            return null;
        }
    }

    public static boolean loadDepartmentPolicyForm(DepartmentPolicyForm form, String policyId) {
        GetDepartmentPolicyDetailsEvent postEvt = new GetDepartmentPolicyDetailsEvent();

        postEvt.setDepartmentPolicyId(policyId);

        CompositeResponse compositeResponse = MessageUtil.postRequest(postEvt);
        Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, DepartmentPolicyDetailResponseEvent.class);
        Iterator iter = searchResults.iterator();
        if (iter.hasNext()) {
            DepartmentPolicyDetailResponseEvent reply = (DepartmentPolicyDetailResponseEvent) iter.next();

            //form.clear();
            form.setPolicyId(reply.getDepartmentId());
            form.setName(reply.getName());
            form.setGroup1Id(reply.getGroup1Id());
            form.setGroup1Name(reply.getGroup1Name());
            form.setGroup2Id(reply.getGroup2Id());
            form.setGroup2Name(reply.getGroup2Name());
            form.setGroup3Id(reply.getGroup3Id());
            form.setGroup3Name(reply.getGroup3Name());
            form.setDepartmentPolicy(reply.getDescription());
            form.setEffectiveDate(UISupervisionOptionHelper.formatDate(reply.getEffectiveDate()));
            form.setInactiveDate(UISupervisionOptionHelper.formatDate(reply.getInactiveDate()));
            form.setNotes(reply.getNotes());

            //look up the courtId in the courts collection
            //create a CourtRespEvent map to make search fast for the selected
            // courts
            Map courtMap = UISupervisionOptionHelper.createCourtMap(form.getCourts());
            Map courtBeanMap = new HashMap();
            Collection selCourts = reply.getCourtIds();

            if (selCourts != null) {
                Iterator selCourtsIter = selCourts.iterator();
                while (selCourtsIter.hasNext()) {
                    String courtNum = (String) selCourtsIter.next();
                    CourtResponseEvent cre = (CourtResponseEvent) courtMap.get(courtNum);
                   
                   if(cre != null){
                	   CourtBean cb = (CourtBean) courtBeanMap.get(cre.getCourtCategory());
                	   if (cb == null) {
                		   cb = new CourtBean();
                		   cb.setCategory(cre.getCourtCategory());
                		   cb.setCategoryDesc(cre.getCourtCategoryDesc());
                		   courtBeanMap.put(cre.getCourtCategory(), cb);
                	   }
                    cb.insertCourt(cre);
                   }
                }
            }
            form.setSelectedCourts(courtBeanMap.values());

            //---- ASSOCIATIONS----------
            // clear collections
            form.clearAssociatedConditions();
            // get associated CourtPolicies
            Collection conditions = MessageUtil.compositeToCollection(compositeResponse, ConditionResponseEvent.class);
            MessageUtil.processEmptyCollection(conditions);
            if (conditions != null) {
                Iterator conds = conditions.iterator();
                while (conds.hasNext()) {
                    ConditionResponseEvent cre = (ConditionResponseEvent) conds.next();
                    AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cre);
                    form.insertAssociatedCondition(asscBean);
                }
            }

            return true;
        }

        return false;
    }

    public static HashMap createCodeTableMapping(Collection c) {
        HashMap hashMap = new HashMap();
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            CodeResponseEvent re = (CodeResponseEvent) iter.next();
            hashMap.put(re.getCode(), re.getDescription());

        }

        return hashMap;
    }

    public static Collection fetchGroups(String agencyId) {
        GetAllGroupsEvent event = new GetAllGroupsEvent();
        event.setAgencyId(agencyId);
        CompositeResponse re = MessageUtil.postRequest(event);
        Collection groups = MessageUtil.compositeToCollection(re, GroupResponseEvent.class);
        Collections.sort((ArrayList) MessageUtil.processEmptyCollection(groups));

        return groups;
    }

    public static Collection fetchDetailDictionary(String agencyId) {
        GetDetailDictionaryEvent detailDictionaryEvent = new GetDetailDictionaryEvent();
        detailDictionaryEvent.setAgencyId(agencyId);
        CompositeResponse re = MessageUtil.postRequest(detailDictionaryEvent);
        Collection varElementTypes = MessageUtil.compositeToCollection(re, VariableElementTypeResponseEvent.class);
        Collections.sort((ArrayList) MessageUtil.processEmptyCollection(varElementTypes));
        return varElementTypes;
    }

    public static Collection fetchCSCDFilteredCourts() {
        // get filtered cscd courts - #76132
        GetFilteredCourtsEvent courtsEvent = new GetFilteredCourtsEvent();
        CompositeResponse cr = MessageUtil.postRequest(courtsEvent);
        Collection courts = MessageUtil.compositeToCollection(cr, CourtResponseEvent.class);
        if (courts != null)
            Collections.sort((List) courts);
        return MessageUtil.processEmptyCollection(courts);
    }
    
    public static Collection fetchCourts() {
        // get courts
        GetAllCourtsEvent courtsEvent = new GetAllCourtsEvent();
        CompositeResponse cr = MessageUtil.postRequest(courtsEvent);
        Collection courts = MessageUtil.compositeToCollection(cr, CourtResponseEvent.class);
        if (courts != null)
            Collections.sort((List) courts);
        return MessageUtil.processEmptyCollection(courts);
    }

    public static Collection fetchAssociatedConditionsForCourtPolicy(String policyId) {
        GetAssociatedConditionsForCourtPolicyEvent conditionsEvent = new GetAssociatedConditionsForCourtPolicyEvent();
        conditionsEvent.setPolicyId(policyId);
        CompositeResponse cr = MessageUtil.postRequest(conditionsEvent);
        Collection conditions = MessageUtil.compositeToCollection(cr, ConditionResponseEvent.class);
        return MessageUtil.processEmptyCollection(conditions);
    }

    public static Collection fetchAssociatedConditionsForDeptPolicy(String policyId) {
        GetAssociatedConditionsForDepartmentPolicyEvent conditionsEvent = new GetAssociatedConditionsForDepartmentPolicyEvent();
        conditionsEvent.setPolicyId(policyId);
        CompositeResponse cr = MessageUtil.postRequest(conditionsEvent);
        Collection conditions = MessageUtil.compositeToCollection(cr, ConditionResponseEvent.class);
 
        return MessageUtil.processEmptyCollection(conditions);
    }
    
    /**
     * Return a new collection of courts that are just within the selected category.
     * @param aCrtCategory
     * @param courts -- CourtBean Objects
     * @return
     */
    public static Collection filterCourtBeansForCrtCategory(String aCrtCategory, Collection courts) {
        Collection courtBeans = new ArrayList();
        // If you want to sort alphabetically by description add that either
         if (courts != null) {
            Iterator it = courts.iterator();
            while (it.hasNext()) {
                CourtBean courtBean = (CourtBean) it.next();
                // get the court category first
                String category = courtBean.getCategory();
                if(category!=null && category.equals(aCrtCategory)){
	                    courtBeans.add(courtBean);
                }
            }
        }
        return courtBeans;
    }
    
    public static Collection filterCourtBeansFor2CrtCategories(String aCrtCategory1,String aCrtCategory2 , Collection courts) {
        Collection courtBeans = new ArrayList();
        // If you want to sort alphabetically by description add that either
         if (courts != null) {
            Iterator it = courts.iterator();
            while (it.hasNext()) {
                CourtBean courtBean = (CourtBean) it.next();
                // get the court category first
                String category = courtBean.getCategory();
                if(category!=null && (category.equals(aCrtCategory1)|| category.equals(aCrtCategory2))){
	                    courtBeans.add(courtBean);
                }
            }
        }
        return courtBeans;
    }

    /**
     * get a filtered list on only CSCD beans from m204 program that only retrieves filtered list without special courts such as ica
     * @return
     */
    public static Collection getFilteredCourtBeans() {
        Map courtBeanMap = new HashMap();
        Collection courtBeans = new ArrayList();
        Collection courts = fetchCSCDFilteredCourts();
        // If you want to sort alphabetically by description add that either
        // here or
        // change the CourtResponseEvent compare method.
        if (courts != null) {
            Iterator it = courts.iterator();
            CourtBean courtBean = null;
            while (it.hasNext()) {
                CourtResponseEvent cre = (CourtResponseEvent) it.next();
                // get the court category first
                String category = cre.getCourtCategory();
                String agencyId = SecurityUIHelper.getUserAgencyId();
                
                courtBean = (CourtBean) courtBeanMap.get(category);
                if (courtBean == null) {
                    courtBean = new CourtBean();
                    courtBean.setCategory(category);
                    courtBean.setCategoryDesc(cre.getCourtCategoryDesc());
                    
                    if ((agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) &&
                    		( (courtBean.getCategory().equals("CC")) ||
                    		  (courtBean.getCategory().equals("CR")) ||	
                    		  (courtBean.getCategory().equals("OC"))
                    		) ) {
                    	courtBeans.add(courtBean);
                        courtBeanMap.put(category, courtBean);
                    } else if (!agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) {
                    	courtBeans.add(courtBean);
                    	courtBeanMap.put(category, courtBean);
                    }
                }
                courtBean.insertCourt(cre);
            }
        }
        return courtBeans;
    }
    
    /**
     * get an unfiltered list of CSCD courts from m204 program that includes all courts including ICA and special courts.
     * @return
     */
    public static Collection getUnFilteredCourtBeans() {
        Map courtBeanMap = new HashMap();
        Collection courtBeans = new ArrayList();
        Collection courts = fetchCourts();
        // If you want to sort alphabetically by description add that either
        // here or
        // change the CourtResponseEvent compare method.
        if (courts != null) {
            Iterator it = courts.iterator();
            CourtBean courtBean = null;
            while (it.hasNext()) {
                CourtResponseEvent cre = (CourtResponseEvent) it.next();
                // get the court category first
                String category = cre.getCourtCategory();
                String agencyId = SecurityUIHelper.getUserAgencyId();
                
                courtBean = (CourtBean) courtBeanMap.get(category);
                if (courtBean == null) {
                    courtBean = new CourtBean();
                    courtBean.setCategory(category);
                    courtBean.setCategoryDesc(cre.getCourtCategoryDesc());
                    
                    if ((agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) &&
                    		( (courtBean.getCategory().equals("CC")) ||
                    		  (courtBean.getCategory().equals("CR")) ||	
                    		  (courtBean.getCategory().equals("OC"))
                    		) ) {
                    	courtBeans.add(courtBean);
                        courtBeanMap.put(category, courtBean);
                    } else if (!agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) {
                    	courtBeans.add(courtBean);
                    	courtBeanMap.put(category, courtBean);
                    }
                }
                courtBean.insertCourt(cre);
            }
        }
        return courtBeans;
    }

    public static Collection tokenizeVariables(String inputStr, String startTag, String endTag) {
        Collection toreturn = new ArrayList();

        String pattern = startTag + "[^" + startTag + endTag + "]*" + endTag;
        //matches everything except start tag or end tag

        RE regex = null;

        try {
            regex = new RE(pattern, RESyntax.RE_CHAR_CLASSES);
        } catch (REException ree) {
            return null;
        }

        Enumeration matches = regex.getMatchEnumeration(inputStr);
        while (matches.hasMoreElements()) {
            REMatch aMatch = (REMatch) matches.nextElement();

            String variableName = null;
            String withXMLString = aMatch.toString();
            String xmlStrippedString = UIUtil.removeXMLtags(withXMLString, true);
            if (startTag.startsWith("\\")) {
                variableName = xmlStrippedString.substring(startTag.length() - 1, xmlStrippedString.length() - (endTag.length() - 1));
            } else {
                variableName = xmlStrippedString.substring(startTag.length(), xmlStrippedString.length() - endTag.length());
            }

            if (variableName != null) {
                 toreturn.add(variableName);
            }
        }

        return toreturn;
    }

    public static HashMap createDetailDictionaryNameIdMapping(Collection detailDictionary) {
        Iterator detailDictionaryIter = detailDictionary.iterator();
        HashMap detailDictionaryHashMap = new HashMap();

        while (detailDictionaryIter.hasNext()) {
            VariableElementTypeResponseEvent varElementTypeRE = (VariableElementTypeResponseEvent) detailDictionaryIter.next();

            detailDictionaryHashMap.put(varElementTypeRE.getName().toUpperCase(), varElementTypeRE);
        }

        return detailDictionaryHashMap;
    }

    public static Collection filter(Collection originalList, String searchStrName, String searchStrDesc, String searchStrType) {
        boolean filterByName = false;
        boolean filterByDescription = false;
        boolean filterByType = false;

        if (searchStrName != null && !searchStrName.equals("") && !searchStrName.trim().equals("")) {
            filterByName = true;
            searchStrName = searchStrName.toLowerCase();
            //If no Wild card is specified by the user then add a * wild card
            // at the end
            if (searchStrName.indexOf('*') < 0) {
                searchStrName = searchStrName + "*";
            }
        }
        // dp added for Type search
        if ((searchStrType != null) && !searchStrType.equals("") && !searchStrType.equals("All")) {
            searchStrType = searchStrType.toLowerCase();
            filterByType = true;
        }

        if (searchStrDesc != null && !searchStrDesc.equals("") && !searchStrDesc.trim().equals("")) {
            filterByDescription = true;
            searchStrDesc = searchStrDesc.toLowerCase();

            //If no Wild card is specified by the user then add a * wild card
            // at the end
            if (searchStrDesc.indexOf('*') < 0) {
                searchStrDesc = searchStrDesc + "*";
            }
        }

        if (!filterByName && !filterByDescription && !filterByType) {
            //no filter criteria, so show original list
            return originalList;
        }

        Collection nameList = new ArrayList();
        if (filterByName) {
            for (Iterator iter = originalList.iterator(); iter.hasNext();) {
                VariableElementTypeResponseEvent varElement = (VariableElementTypeResponseEvent) iter.next();
                if (filterListRE(searchStrName, varElement.getName())) {
                    nameList.add(varElement);
                }
            }
        } else { // use originalList as filterdList for further match
            nameList = originalList;
        }

        Collection descList = new ArrayList();
        if (filterByDescription) {
            for (Iterator iter = nameList.iterator(); iter.hasNext();) {
                VariableElementTypeResponseEvent varElement = (VariableElementTypeResponseEvent) iter.next();
                if (filterListRE(searchStrDesc, varElement.getDescription())) {
                    descList.add(varElement);
                }
            }
        } else {
            descList = nameList;
        }

        Collection finalList = new ArrayList();
        if (filterByType) {
            for (Iterator iter = descList.iterator(); iter.hasNext();) {
                VariableElementTypeResponseEvent varElement = (VariableElementTypeResponseEvent) iter.next();
                // temporary solution
                String type = null;
                if (varElement.getIsReference()) {
                    type = "Reference";
                } else {
                    type = "Variable";
                }
                if (filterListRE(searchStrType, type)) {
                    finalList.add(varElement);
                }
            }
        } else {
            finalList = descList;
        }

        return finalList;
    }

    public static CompositeResponse validateCondition(String agencyId, String name, String conditionId) {
        ValidateSupervisionConditionEvent reqEvent = new ValidateSupervisionConditionEvent();
        reqEvent.setAgencyId(agencyId);
        reqEvent.setName(name);
        reqEvent.setConditionId(conditionId);

        CompositeResponse response = MessageUtil.postRequest(reqEvent);
        
        return response;

    }

    public static CompositeResponse validateCourtPolicy(String agencyId, String name, String policyId) {
        ValidateCourtPolicyEvent reqEvent = new ValidateCourtPolicyEvent();
        reqEvent.setAgencyId(agencyId);
        reqEvent.setName(name);
        reqEvent.setPolicyId(policyId);
        CompositeResponse response = MessageUtil.postRequest(reqEvent);

        return response;

    }

    public static CompositeResponse validateDepartmentPolicy(String agencyId, String name, String policyId) {
        ValidateDepartmentPolicyEvent reqEvent = new ValidateDepartmentPolicyEvent();
        reqEvent.setAgencyId(agencyId);
        reqEvent.setName(name);
        reqEvent.setPolicyId(policyId);

        CompositeResponse response = MessageUtil.postRequest(reqEvent);

        return response;

    }

    /** Added for the filter functionality * */
    public static String wildcardToRegexp(String wildcard) {
        StringBuffer s = new StringBuffer(wildcard.length());
        s.append('^');
        for (int i = 0, is = wildcard.length(); i < is; i++) {
            char c = wildcard.charAt(i);
            switch (c) {
            case '*':
                s.append('.');
                s.append('*');
                break;
            case '?':
                s.append('.');
                break;
            // escape special regexp-characters
            case '(':
            case ')':
            case '[':
            case ']':
            case '$':
            case '^':
            case '.':
            case '{':
            case '}':
            case '|':
            case '\\':
                s.append('\\');
                s.append(c);
                break;
            default:
                s.append(c);
                break;
            }
        }
        s.append('$');
        return (s.toString());
    }

    public static boolean filterListRE(String searchStr, String nameDesc) {
        //return a match if name/desc passed in is empty
        if (nameDesc == null || nameDesc.length() == 0) {
            if (searchStr.equals("*"))
                return true;
            else
                return false;
        }

        boolean reMatch = false;
        try {
            RE r = new RE(wildcardToRegexp(searchStr));
            reMatch = r.isMatch(nameDesc.toLowerCase());
        } catch (REException reEx) {
            reEx.printStackTrace();
            reMatch = false;
        }

        return reMatch;
    }

    public static String createLiteralSample(String conditionLiteral, Collection varElements, HashMap detailDictionaryNameIdMapping) {
        Iterator varElementsIter = varElements.iterator();
        String toreturn = conditionLiteral;

        while (varElementsIter.hasNext()) {
            String varElementName = (String) varElementsIter.next();
            //need to get sample value for each of the variable element

            VariableElementTypeResponseEvent varElementTypeRE = (VariableElementTypeResponseEvent) detailDictionaryNameIdMapping.get(varElementName
                    .toUpperCase());

            if (varElementTypeRE != null) {
                String sampleValue = "" + varElementTypeRE.getSampleValue();
                boolean isReference = varElementTypeRE.getIsReference();

                String token = null;
                //token = varElementName;
                if (isReference) {
                    token = "[" + varElementName + "]";
                } else {
                    token = "{{" + varElementName + "}}";
                }
                token=UIUtil.regExSpecCharEscapeFix(token);
                RE regex = null;
                try {
                    regex = new RE(token);
                    //The dollar sign denotes an anchor in RegEx.
                    sampleValue = sampleValue.replace('$', '^');
                    toreturn = regex.substituteAll(toreturn, sampleValue);
                    toreturn = toreturn.replace('^', '$');
                } catch (REException e) {
                    e.printStackTrace();
                }
            }
        }

        return toreturn;
    }
    
  
    
   

    public static AssociateBean createAsscBean(CourtPolicyResponseEvent cpre) {
        AssociateBean asscBean = new AssociateBean();
        asscBean.setGroup1Id(cpre.getGroup1Id());
        asscBean.setGroup1Name(cpre.getGroup1Name());
        asscBean.setGroup2Id(cpre.getGroup2Id());
        asscBean.setGroup2Name(cpre.getGroup2Name());
        asscBean.setGroup3Id(cpre.getGroup3Id());
        asscBean.setGroup3Name(cpre.getGroup3Name());
        asscBean.setObjId(cpre.getPolicyId());
        asscBean.setObjName(cpre.getName());
        asscBean.setObjType(AssociateBean.COURTPOLICY);

        return asscBean;
    }

    public static AssociateBean createAsscBean(DepartmentPolicyResponseEvent dpre) {
        AssociateBean asscBean = new AssociateBean();
        asscBean.setGroup1Id(dpre.getGroup1Id());
        asscBean.setGroup1Name(dpre.getGroup1Name());
        asscBean.setGroup2Id(dpre.getGroup2Id());
        asscBean.setGroup2Name(dpre.getGroup2Name());
        asscBean.setGroup3Id(dpre.getGroup3Id());
        asscBean.setGroup3Name(dpre.getGroup3Name());
        asscBean.setObjId(dpre.getAgencyPolicyId());
        asscBean.setObjName(dpre.getName());
        asscBean.setObjType(AssociateBean.DEPARTMENTPOLICY);

        return asscBean;
    }

    public static AssociateBean createAsscBean(ConditionResponseEvent cpre) {
        AssociateBean asscBean = new AssociateBean();
        asscBean.setGroup1Id(cpre.getGroup1Id());
        asscBean.setGroup1Name(cpre.getGroup1Name());
        asscBean.setGroup2Id(cpre.getGroup2Id());
        asscBean.setGroup2Name(cpre.getGroup2Name());
        asscBean.setGroup3Id(cpre.getGroup3Id());
        asscBean.setGroup3Name(cpre.getGroup3Name());
        asscBean.setObjId(cpre.getConditionId());
        asscBean.setObjName(cpre.getName());
        asscBean.setObjType(AssociateBean.CONDITION);

        return asscBean;
    }

    public static String getGroup1Name(Collection groups, String groupId) {
        String groupName = null;
        for (Iterator it = groups.iterator(); it.hasNext();) {
            GroupResponseEvent gre = (GroupResponseEvent) it.next();
            if (gre.getGroupId().equals(groupId)) {
                groupName = gre.getName();
                break;
            }
        }
        return groupName;
    }

    public static boolean isDeptPolicyInUse(String deptPolicyId) {
        if ((isDeptPolicyInUse(deptPolicyId, null)) != null) {
            return true;
        } else
            return false;
    }

    public static Collection isDeptPolicyInUse(String deptPolicyId, DepartmentPolicyForm aForm) {
        GetDeptPoliciesInUseEvent requestEvent = (GetDeptPoliciesInUseEvent) EventFactory
                .getInstance(SupervisionOptionsControllerServiceNames.GETDEPTPOLICIESINUSE);

        requestEvent.setPolicyId(deptPolicyId);

        CompositeResponse response = MessageUtil.postRequest(requestEvent);
        DepartmentPolicyInUseResponseEvent responseEvent = (DepartmentPolicyInUseResponseEvent) MessageUtil.filterComposite(response,
                DepartmentPolicyInUseResponseEvent.class);
        if (responseEvent != null) {

            return responseEvent.getCourtIds();
        } else {
            return null;
        }
    }
    
    public static String replaceTags(String string)
	{
		String resolvedString = "";
		if(string != null)
		{
			resolvedString = string.replaceAll("<strong>", "<b>").replaceAll("</strong>", "</b>");
		}
		return resolvedString;
	}    
}
