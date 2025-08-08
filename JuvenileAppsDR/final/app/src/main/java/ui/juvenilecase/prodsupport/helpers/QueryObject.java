package ui.juvenilecase.prodsupport.helpers;

import java.util.ArrayList;
import java.util.Iterator;

public class QueryObject {

	//All values initialized to fill data gaps
	
	//For All
	String casefileid = " ";
	String createuser = " ";
	String createdate = " ";
	String updateuser = " ";
	String updatedate = " ";
	String createjims2user = " ";
	String updatejims2user = " ";
	
	//For MenuOptions
	String feature = "";
	String forward = "";
	String visibleInQuery = "";
	
	//For GetNextRowGeneric method in Constants.java
	ArrayList<QueryColumn> columns = new ArrayList<QueryColumn>();
	
	//For activities
	String activityid = " ";
	String activitycd = " ";
	String inactivedate = " ";
	String title = " ";
	String activitydate = " ";
	
	//For Casefiles
	String activationdate = " ";
	String juvenileid = " ";
	String casestatuscd = " ";
	String sprvsiontypecd = " ";
	String officerid = " ";
	String juvseqnum = " ";
	String supervsionenddate = " ";
	
	String ismaysineeded    = " ";
	String isriskrefrneeded = " ";
	String isriskintvneeded = " ";
	String isrisktestneeded = " ";
	String isriskcommneeded = " ";
	String isriskresineeded = " ";
	String isriskprogneeded = " ";
	String isbenasmntneeded = " ";
	
	//For Assignments
	String assignmentid = " ";
	String caseloadmgrcd = " ";
	String assmntadddate = " ";
	String referralnumber = " ";
	String serviceunitcd = " ";
	String assmntlevelcd = " ";
	boolean wasMigrated = false;
	public boolean wasChecked = false;
	
	//For Caseplans
	String caseplanid = " ";
	String reviewdate = " ";
	String planstatuscd = " ";
	String placementid = " ";
		
	//For CasefileClosings
	String casefileclosngid = " ";
	String supervisionoutcome = " ";
	String casefileclosngstats = " ";
	String expectedrelesedate = " ";
	String cntrollingreferral = " ";
	String facltyrlsesereason = " ";
	String permanencyplan = " ";
	String facility = " ";
	String levelofcare = " ";
	String petitionnumber = " ";
	String exitplantempltlctn = " ";
	String isclosngpktgen = " ";
	String isclosngltrgen = " ";
	
	//For Interviews
	
	String interviewid = " ";
	String caleventid = " ";
	String iviewdate = " ";
	String iviewtypecd = " ";
	String locationid = " ";
	String addressid = " ";
	String juvlocunitid = " ";
	String statuscd = " ";
	String iscstmaddrsvalid = " ";
	String otherrecsintv = " ";
	
	//For all docs
	String doctablename= " ";
	String doctableOID = " ";
	String documentId = " ";
	
	//For iviewdocs
	String iviewdocid = " ";
	String doctypecd = " ";
	
	//For noncompdocs and commonappdocs
	String noncompdocid = " ";
	String commonappdocid = " ";
	String casenoncomnoteid = " ";
	String document = " ";
	
	//For riskanalyses
	String riskanalysisid= " ";
	String finalscore= " ";
	String assessmenttype= " ";
	String jpouserid= " ";
	String dateentered= " ";
	String iscustody= " ";
	String recommendation = " ";
	
	//For juvprogrefs
	String juvprogrefid= " ";
	String ackdate= " ";
	String begindate= " ";
	String enddate= " ";
	String assignedhours= " ";
	String sentdate= " ";
	String substatuscd= " ";
	String provprogramid= " ";
	String lastactiondate= " ";
	String outcomecd= " ";
	String iscourtordered= " ";
	
	//For code drop-downs
	String code = " ";
	String description = " ";
	
	//For beneasmnts
	String beneasmntid= " ";
	String consrelationid= " ";
	String requestdate= " ";
	String entrydate= " ";
	String title4eofficerid= " ";
	String title4eofficername= " ";
	String juvrelationdesc= " ";
	String requestorname= " ";
	
	//For suprules
	String supruleid = " ";
	String completionstatusid = " ";
	String monitorfrequencyid = " ";
	String conditionid = " ";
	String completiondate = " ";
	String ruletypecd = " ";
	
	//For caleventconts
	String caleventcontid = " ";
	
	//For nttasks
	String taskid = " ";
	String sevlevel = " ";
	String tasksubject = " ";
	String topic = " ";
	String accepteddate = " ";
	String closeddate = " ";
	String duedate = " ";
	String submitteddate = " ";
	String sourceid = " ";
	String ownerid = " ";
	
	//For eventtasks
	String eventtaskid = " ";
	String executed = " ";
	String nextnoticedate = " ";
	String firstnoticedate = " ";
	String eventname = " ";
	String schedulename = " ";
	String taskname = " ";
	String taskstatus = " ";
	
	//For jcjpoassnmnthists
	String assnmnthistid = " ";
	String jpoassnmntdt = " ";
	
	//For Risk Assessment deletes
	String riskresponsesid = " ";
	String riskweightresid = " ";
	String responsetext = " ";
	
	//For progrefcomments
	String progrefcommentsid = " ";
	String commentstxt = " ";
	String commentsdate = " ";
	String username = " ";
	
	//For eventreferrals
	String eventreferralid = " ";
	String serveventid = " ";
	
	//For CSPROGRFASGNHISTs
	String progrfasgnhistid;
	String prgrefasgndate;
	
	//For juveniles
	String juvenilefname;
	String juvenilemname;
	String juvenilelname;
	String sex;
	String ssn1;
	String ssn2;
	String race;
	String birthdate;
	
	//For traits
	String traitsid = " ";
	String comments = " ";
	String dispositionnum = " ";
	String supervisionnum = " ";
	String traittype_id = " ";
	String traitstaus = " ";
	
	//For Calevent Modifications
	String servattendId = "";
	String serveventId = "";
	String attendstatuscd = "";
	String progressnotes = "";
	String addlattendees = "";
	
	//For CASRVEVENT Modifications
	String eventmaximum = "";
	String eventminimum = "";
	String eventstatuscd = "";
	String eventtypecd = "";
	String jvsrvprvprofid="";
	String serviceid="";
	String eventcomments="";
	String schoolderivedcd="";
	String memaddressid="";
	String currentenroll="";
	String facilitycd="";
	String mememployid="";
	String contactfirstname="";
	String contactlastname="";
	String sexoffender="";
	String restrictother="";
		
	//For MAYSI Assessments
	String maysiassessmntId = "";
	String assessoption = "";
	String assessofficerId = "";
	String assessdate = "";
	String haspreviousmaysi = "";
	String isadministered = "";
	String juvlocunitId = "";
	String lengthofstay = "";
	String facilitytypecd = "";
	String reasonnotdone = "";
	String gendercd = "";
	String racecd = "";
	String testage = "";	
	
	//For MAYSI Details
	String maysidetailId = "";
	String screeningdate = "";
	String juvenileage = "";
	String gender = "";
	String ethnicbackground = "";
	String priorstay = "";
	String facilitytype = "";
	String facilityname = "";
	String angryirritable = "";
	String thoughtdisturb = "";
	String somaticcomplaints = "";
	String alcoholdruguse = "";
	String suicideideation = "";
	String traumaticexp = "";
	String depressedanxious = "";
	String issubasmntref = "";
	String assessmentfound = "";
	String outcomedesccd = "";
	
	//For SchoolHists
	String schoolhistId = "";
	String lastattendeddate = "";
	String currentgradecd = "";
	String exittypecd = "";
	String appropgradecd = "";
	String schoolcd = "";
	String schooldistcd = "";
	String teacode = "";
	String verifieddate = "";
	String gradeavg = "";
	String graderepeatnum ="";
	String gradesrepeatcd = "";
	String participationcd = "";
	String pgmattendingcd = "";
	String ruleinfractioncd = "";
	String attstatuscd = "";
	String truancyhistory = "";
	String homeschooldistcd = "";
	String homeschoolcd = "";
	
	
	
	public String getCntrollingreferral() {
		return cntrollingreferral;
	}
	public void setCntrollingreferral(String cntrollingreferral) {
		this.cntrollingreferral = cntrollingreferral;
	}
	public String getActivationdate() {
		return activationdate;
	}
	public void setActivationdate(String activationdate) {
		this.activationdate = activationdate;
	}
	public String getJuvenileid() {
		return juvenileid;
	}
	public void setJuvenileid(String juvenile_id) {
		this.juvenileid = juvenile_id;
	}
	public String getCasestatuscd() {
		return casestatuscd;
	}
	public void setCasestatuscd(String casestatuscd) {
		this.casestatuscd = casestatuscd;
	}
	public String getSprvsiontypecd() {
		return sprvsiontypecd;
	}
	public void setSprvsiontypecd(String sprvsiontypecd) {
		this.sprvsiontypecd = sprvsiontypecd;
	}
	public String getSupervsionenddate() {
		return supervsionenddate;
	}
	public void setSupervsionenddate(String supervsionenddate) {
		this.supervsionenddate = supervsionenddate;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCasefileid() {
		return casefileid;
	}
	public void setCasefileid(String casefileId) {
		this.casefileid = casefileId;
	}
	public String getOfficerid() {
		return officerid;
	}
	public void setOfficerid(String officer_id) {
		this.officerid = officer_id;
	}
	public String getJuvseqnum() {
		return juvseqnum;
	}
	public void setJuvseqnum(String juvseqnum) {
		this.juvseqnum = juvseqnum;
	}
	public String getAssignmentid() {
		return assignmentid;
	}
	public void setAssignmentid(String assignmentid) {
		this.assignmentid = assignmentid;
	}
	public String getCaseloadmgrcd() {
		return caseloadmgrcd;
	}
	public void setCaseloadmgrcd(String caseloadmgrcd) {
		this.caseloadmgrcd = caseloadmgrcd;
	}
	public String getAssmntadddate() {
		return assmntadddate;
	}
	public void setAssmntadddate(String assmntadddate) {
		this.assmntadddate = assmntadddate;
	}
	public String getReferralnumber() {
		return referralnumber;
	}
	public void setReferralnumber(String referralnumber) {
		this.referralnumber = referralnumber;
	}
	public String getServiceunitcd() {
		return serviceunitcd;
	}
	public void setServiceunitcd(String serviceunitcd) {
		this.serviceunitcd = serviceunitcd;
	}
	public String getAssmntlevelcd() {
		return assmntlevelcd;
	}
	public void setAssmntlevelcd(String assmntlevelcd) {
		this.assmntlevelcd = assmntlevelcd;
	}
	public String getReviewdate() {
		return reviewdate;
	}
	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
	public String getPlanstatuscd() {
		return planstatuscd;
	}
	public void setPlanstatuscd(String planstatuscd) {
		this.planstatuscd = planstatuscd;
	}
	public String getPlacementid() {
		return placementid;
	}
	public void setPlacementid(String placementid) {
		this.placementid = placementid;
	}
	public String getCasefileclosngid() {
		return casefileclosngid;
	}
	public void setCasefileclosngid(String casefileclosngid) {
		this.casefileclosngid = casefileclosngid;
	}
	public String getSupervisionoutcome() {
		return supervisionoutcome;
	}
	public void setSupervisionoutcome(String supervisionoutcome) {
		this.supervisionoutcome = supervisionoutcome;
	}

	public String getCasefileclosngstats() {
		return casefileclosngstats;
	}
	public void setCasefileclosngstats(String casefileclosngstats) {
		this.casefileclosngstats = casefileclosngstats;
	}

	
	public String getExpectedrelesedate() {
		return expectedrelesedate;
	}
	public void setExpectedrelesedate(String expectedrelesedate) {
		this.expectedrelesedate = expectedrelesedate;
	}
	
	public String getCaseplanid() {
		return caseplanid;
	}
	public void setCaseplanid(String caseplanid) {
		this.caseplanid = caseplanid;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreatejims2user() {
		return createjims2user;
	}
	public void setCreatejims2user(String createjims2user) {
		this.createjims2user = createjims2user;
	}
	public String getUpdatejims2user() {
		return updatejims2user;
	}
	public void setUpdatejims2user(String updatejims2user) {
		this.updatejims2user = updatejims2user;
	}
	public String getInterviewid() {
		return interviewid;
	}
	public void setInterviewid(String interviewid) {
		this.interviewid = interviewid;
	}
	public String getCaleventid() {
		return caleventid;
	}
	public void setCaleventid(String caleventid) {
		this.caleventid = caleventid;
	}
	public String getIviewdate() {
		return iviewdate;
	}
	public void setIviewdate(String iviewdate) {
		this.iviewdate = iviewdate;
	}
	public String getIviewtypecd() {
		return iviewtypecd;
	}
	public void setIviewtypecd(String iviewtypecd) {
		this.iviewtypecd = iviewtypecd;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getJuvlocunitid() {
		return juvlocunitid;
	}
	public void setJuvlocunitid(String juvlocunitid) {
		this.juvlocunitid = juvlocunitid;
	}
	public String getStatuscd() {
		return statuscd;
	}
	public void setStatuscd(String statuscd) {
		this.statuscd = statuscd;
	}
	public String getIscstmaddrsvalid() {
		return iscstmaddrsvalid;
	}
	public void setIscstmaddrsvalid(String iscstmaddrsvalid) {
		this.iscstmaddrsvalid = iscstmaddrsvalid;
	}
	public String getOtherrecsintv() {
		return otherrecsintv;
	}
	public void setOtherrecsintv(String otherrecsintv) {
		this.otherrecsintv = otherrecsintv;
	}
	public String getIviewdocid() {
		return iviewdocid;
	}
	public void setIviewdocid(String iviewdocid) {
		this.iviewdocid = iviewdocid;
	}
	public String getDoctypecd() {
		return doctypecd;
	}
	public void setDoctypecd(String doctypecd) {
		this.doctypecd = doctypecd;
	}
	public String getRiskanalysisid() {
		return riskanalysisid;
	}
	public void setRiskanalysisid(String riskanalysisid) {
		this.riskanalysisid = riskanalysisid;
	}
	public String getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(String finalscore) {
		this.finalscore = finalscore;
	}
	public String getAssessmenttype() {
		return assessmenttype;
	}
	public void setAssessmenttype(String assessmenttype) {
		this.assessmenttype = assessmenttype;
	}
	public String getJpouserid() {
		return jpouserid;
	}
	public void setJpouserid(String jpouserid) {
		this.jpouserid = jpouserid;
	}
	public String getDateentered() {
		return dateentered;
	}
	public void setDateentered(String dateentered) {
		this.dateentered = dateentered;
	}
	public String getIscustody() {
		return iscustody;
	}
	public void setIscustody(String iscustody) {
		this.iscustody = iscustody;
	}
	public String getJuvprogrefid() {
		return juvprogrefid;
	}
	public void setJuvprogrefid(String juvprogrefid) {
		this.juvprogrefid = juvprogrefid;
	}
	public String getAckdate() {
		return ackdate;
	}
	public void setAckdate(String ackdate) {
		this.ackdate = ackdate;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAssignedhours() {
		return assignedhours;
	}
	public void setAssignedhours(String assignedhours) {
		this.assignedhours = assignedhours;
	}
	public String getSentdate() {
		return sentdate;
	}
	public void setSentdate(String sentdate) {
		this.sentdate = sentdate;
	}
	public String getSubstatuscd() {
		return substatuscd;
	}
	public void setSubstatuscd(String substatuscd) {
		this.substatuscd = substatuscd;
	}
	public String getProvprogramid() {
		return provprogramid;
	}
	public void setProvprogramid(String provprogramid) {
		this.provprogramid = provprogramid;
	}
	public String getLastactiondate() {
		return lastactiondate;
	}
	public void setLastactiondate(String lastactiondate) {
		this.lastactiondate = lastactiondate;
	}
	public String getOutcomecd() {
		return outcomecd;
	}
	public void setOutcomecd(String outcomecd) {
		this.outcomecd = outcomecd;
	}
	public String getIscourtordered() {
		return iscourtordered;
	}
	public void setIscourtordered(String iscourtordered) {
		this.iscourtordered = iscourtordered;
	}
	public String getBeneasmntid() {
		return beneasmntid;
	}
	public void setBeneasmntid(String beneasmntid) {
		this.beneasmntid = beneasmntid;
	}
	public String getConsrelationid() {
		return consrelationid;
	}
	public void setConsrelationid(String consrelationid) {
		this.consrelationid = consrelationid;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public String getTitle4eofficerid() {
		return title4eofficerid;
	}
	public void setTitle4eofficerid(String title4eofficerid) {
		this.title4eofficerid = title4eofficerid;
	}
	public String getTitle4eofficername() {
		return title4eofficername;
	}
	public void setTitle4eofficername(String title4eofficername) {
		this.title4eofficername = title4eofficername;
	}
	public String getJuvrelationdesc() {
		return juvrelationdesc;
	}
	public void setJuvrelationdesc(String juvrelationdesc) {
		this.juvrelationdesc = juvrelationdesc;
	}
	public String getRequestorname() {
		return requestorname;
	}
	public void setRequestorname(String requestorname) {
		this.requestorname = requestorname;
	}
	public String getSupruleid() {
		return supruleid;
	}
	public void setSupruleid(String supruleid) {
		this.supruleid = supruleid;
	}
	public String getCompletionstatusid() {
		return completionstatusid;
	}
	public void setCompletionstatusid(String completionstatusid) {
		this.completionstatusid = completionstatusid;
	}
	public String getMonitorfrequencyid() {
		return monitorfrequencyid;
	}
	public void setMonitorfrequencyid(String monitorfrequencyid) {
		this.monitorfrequencyid = monitorfrequencyid;
	}
	public String getConditionid() {
		return conditionid;
	}
	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}
	public String getCompletiondate() {
		return completiondate;
	}
	public void setCompletiondate(String completiondate) {
		this.completiondate = completiondate;
	}
	public String getRuletypecd() {
		return ruletypecd;
	}
	public void setRuletypecd(String ruletypecd) {
		this.ruletypecd = ruletypecd;
	}
	public String getCaleventcontid() {
		return caleventcontid;
	}
	public void setCaleventcontid(String caleventcontid) {
		this.caleventcontid = caleventcontid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getSevlevel() {
		return sevlevel;
	}
	public void setSevlevel(String sevlevel) {
		this.sevlevel = sevlevel;
	}
	public String getTasksubject() {
		return tasksubject;
	}
	public void setTasksubject(String tasksubject) {
		this.tasksubject = tasksubject;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAccepteddate() {
		return accepteddate;
	}
	public void setAccepteddate(String accepteddate) {
		this.accepteddate = accepteddate;
	}
	public String getCloseddate() {
		return closeddate;
	}
	public void setCloseddate(String closeddate) {
		this.closeddate = closeddate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getSubmitteddate() {
		return submitteddate;
	}
	public void setSubmitteddate(String submitteddate) {
		this.submitteddate = submitteddate;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getEventtaskid() {
		return eventtaskid;
	}
	public void setEventtaskid(String eventtaskid) {
		this.eventtaskid = eventtaskid;
	}
	public String getExecuted() {
		return executed;
	}
	public void setExecuted(String executed) {
		this.executed = executed;
	}
	public String getNextnoticedate() {
		return nextnoticedate;
	}
	public void setNextnoticedate(String nextnoticedate) {
		this.nextnoticedate = nextnoticedate;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getSchedulename() {
		return schedulename;
	}
	public void setSchedulename(String schedulename) {
		this.schedulename = schedulename;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	public String getFirstnoticedate() {
		return firstnoticedate;
	}
	public void setFirstnoticedate(String firstnoticedate) {
		this.firstnoticedate = firstnoticedate;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getActivitycd() {
		return activitycd;
	}
	public void setActivitycd(String activitycd) {
		this.activitycd = activitycd;
	}
	public String getInactivedate() {
		return inactivedate;
	}
	public void setInactivedate(String inactivedate) {
		this.inactivedate = inactivedate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getActivitydate() {
		return activitydate;
	}
	public void setActivitydate(String activitydate) {
		this.activitydate = activitydate;
	}
	public String getAssnmnthistid() {
		return assnmnthistid;
	}
	public void setAssnmnthistid(String jpoassnmnthistid) {
		this.assnmnthistid = jpoassnmnthistid;
	}
	public String getJpoassnmntdt() {
		return jpoassnmntdt;
	}
	public void setJpoassnmntdt(String jpoassnmntdt) {
		this.jpoassnmntdt = jpoassnmntdt;
	}
	public String getRiskresponsesid() {
		return riskresponsesid;
	}
	public void setRiskresponsesid(String riskresponsesid) {
		this.riskresponsesid = riskresponsesid;
	}
	public String getRiskweightresid() {
		return riskweightresid;
	}
	public void setRiskweightresid(String riskweightresid) {
		this.riskweightresid = riskweightresid;
	}
	public String getResponsetext() {
		return responsetext;
	}
	public void setResponsetext(String responsetext) {
		this.responsetext = responsetext;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProgrefcommentsid() {
		return progrefcommentsid;
	}
	public void setProgrefcommentsid(String progrefcommentsid) {
		this.progrefcommentsid = progrefcommentsid;
	}
	public String getCommentstxt() {
		return commentstxt;
	}
	public void setCommentstxt(String commentstxt) {
		this.commentstxt = commentstxt;
	}
	public String getCommentsdate() {
		return commentsdate;
	}
	public void setCommentsdate(String commentsdate) {
		this.commentsdate = commentsdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEventreferralid() {
		return eventreferralid;
	}
	public void setEventreferralid(String eventreferralid) {
		this.eventreferralid = eventreferralid;
	}
	public String getServeventid() {
		return serveventid;
	}
	public void setServeventid(String serveventid) {
		this.serveventid = serveventid;
	}
	public String getProgrfasgnhistid() {
		return progrfasgnhistid;
	}
	public void setProgrfasgnhistid(String csprogrfasgnhistid) {
		this.progrfasgnhistid = csprogrfasgnhistid;
	}
	public String getPrgrefasgndate() {
		return prgrefasgndate;
	}
	public void setPrgrefasgndate(String prgrefasgndate) {
		this.prgrefasgndate = prgrefasgndate;
	}
	public String getJuvenilefname() {
		return juvenilefname;
	}
	public void setJuvenilefname(String juvenilefname) {
		this.juvenilefname = juvenilefname;
	}
	public String getJuvenilemname() {
		return juvenilemname;
	}
	public void setJuvenilemname(String juvenilemname) {
		this.juvenilemname = juvenilemname;
	}
	public String getJuvenilelname() {
		return juvenilelname;
	}
	public void setJuvenilelname(String juvenilelname) {
		this.juvenilelname = juvenilelname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSsn1() {
		return ssn1;
	}
	public void setSsn1(String ssn1) {
		this.ssn1 = ssn1;
	}
	public String getSsn2() {
		return ssn2;
	}
	public void setSsn2(String ssn2) {
		this.ssn2 = ssn2;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getTraitsid() {
		return traitsid;
	}
	public void setTraitsid(String traitsid) {
		this.traitsid = traitsid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDispositionnum() {
		return dispositionnum;
	}
	public void setDispositionnum(String dispositionnum) {
		this.dispositionnum = dispositionnum;
	}
	public String getSupervisionnum() {
		return supervisionnum;
	}
	public void setSupervisionnum(String supervisionnum) {
		this.supervisionnum = supervisionnum;
	}
	public String getTraittype_id() {
		return traittype_id;
	}
	public void setTraittype_id(String traittype_id) {
		this.traittype_id = traittype_id;
	}
	public String getTraitstaus() {
		return traitstaus;
	}
	public void setTraitstaus(String traitstaus) {
		this.traitstaus = traitstaus;
	}
	public boolean isWasMigrated() {
		return wasMigrated;
	}
	public void setWasMigrated(boolean wasMigrated) {
		this.wasMigrated = wasMigrated;
	}
	public boolean isWasChecked() {
		return wasChecked;
	}
	public void setWasChecked(boolean wasChecked) {
		this.wasChecked = wasChecked;
	}
	public String getIsmaysineeded() {
		return ismaysineeded;
	}
	public void setIsmaysineeded(String ismaysineeded) {
		this.ismaysineeded = ismaysineeded;
	}
	public String getIsriskrefrneeded() {
		return isriskrefrneeded;
	}
	public void setIsriskrefrneeded(String isriskrefrneeded) {
		this.isriskrefrneeded = isriskrefrneeded;
	}
	public String getIsriskintvneeded() {
		return isriskintvneeded;
	}
	public void setIsriskintvneeded(String isriskintvneeded) {
		this.isriskintvneeded = isriskintvneeded;
	}
	public String getIsrisktestneeded() {
		return isrisktestneeded;
	}
	public void setIsrisktestneeded(String isrisktestneeded) {
		this.isrisktestneeded = isrisktestneeded;
	}
	public String getIsriskcommneeded() {
		return isriskcommneeded;
	}
	public void setIsriskcommneeded(String isriskcommneeded) {
		this.isriskcommneeded = isriskcommneeded;
	}
	public String getIsriskresineeded() {
		return isriskresineeded;
	}
	public void setIsriskresineeded(String isriskresineeded) {
		this.isriskresineeded = isriskresineeded;
	}
	public String getIsriskprogneeded() {
		return isriskprogneeded;
	}
	public void setIsriskprogneeded(String isriskprogneeded) {
		this.isriskprogneeded = isriskprogneeded;
	}
	public String getIsbenasmntneeded() {
		return isbenasmntneeded;
	}
	public void setIsbenasmntneeded(String isbenasmntneeded) {
		this.isbenasmntneeded = isbenasmntneeded;
	}
	public String getFacltyrlsesereason() {
		return facltyrlsesereason;
	}
	public void setFacltyrlsesereason(String facltyrlsesereason) {
		this.facltyrlsesereason = facltyrlsesereason;
	}
	public String getPermanencyplan() {
		return permanencyplan;
	}
	public void setPermanencyplan(String permanencyplan) {
		this.permanencyplan = permanencyplan;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getLevelofcare() {
		return levelofcare;
	}
	public void setLevelofcare(String levelofcare) {
		this.levelofcare = levelofcare;
	}
	public String getPetitionnumber() {
		return petitionnumber;
	}
	public void setPetitionnumber(String petitionnumber) {
		this.petitionnumber = petitionnumber;
	}
	public String getExitplantempltlctn() {
		return exitplantempltlctn;
	}
	public void setExitplantempltlctn(String exitplantempltlctn) {
		this.exitplantempltlctn = exitplantempltlctn;
	}
	public String getIsclosngpktgen() {
		return isclosngpktgen;
	}
	public void setIsclosngpktgen(String isclosngpktgen) {
		this.isclosngpktgen = isclosngpktgen;
	}
	public String getIsclosngltrgen() {
		return isclosngltrgen;
	}
	public void setIsclosngltrgen(String isclosngltrgen) {
		this.isclosngltrgen = isclosngltrgen;
	}
	public String getAttendstatuscd() {
		return attendstatuscd;
	}
	public void setAttendstatuscd(String attendstatuscd) {
		this.attendstatuscd = attendstatuscd;
	}
	public String getProgressnotes() {
		return progressnotes;
	}
	public void setProgressnotes(String progressnotes) {
		this.progressnotes = progressnotes;
	}
	public String getAddlattendees() {
		return addlattendees;
	}
	public void setAddlattendees(String addlattendees) {
		this.addlattendees = addlattendees;
	}
	public String getServattendId() {
		return servattendId;
	}
	public void setServattendId(String servattendId) {
		this.servattendId = servattendId;
	}
	public String getServeventId() {
		return serveventId;
	}
	public void setServeventId(String serveventId) {
		this.serveventId = serveventId;
	}
	public String getMaysiassessmntId() {
		return maysiassessmntId;
	}
	public void setMaysiassessmntId(String maysiassessmntId) {
		this.maysiassessmntId = maysiassessmntId;
	}
	public String getAssessoption() {
		return assessoption;
	}
	public void setAssessoption(String assessoption) {
		this.assessoption = assessoption;
	}
	public String getAssessofficerId() {
		return assessofficerId;
	}
	public void setAssessofficerId(String assessofficerId) {
		this.assessofficerId = assessofficerId;
	}
	public String getAssessdate() {
		return assessdate;
	}
	public void setAssessdate(String assessdate) {
		this.assessdate = assessdate;
	}
	public String getHaspreviousmaysi() {
		return haspreviousmaysi;
	}
	public void setHaspreviousmaysi(String haspreviousmaysi) {
		this.haspreviousmaysi = haspreviousmaysi;
	}
	public String getIsadministered() {
		return isadministered;
	}
	public void setIsadministered(String isadministered) {
		this.isadministered = isadministered;
	}
	public String getJuvlocunitId() {
		return juvlocunitId;
	}
	public void setJuvlocunitId(String juvlocunitId) {
		this.juvlocunitId = juvlocunitId;
	}
	public String getLengthofstay() {
		return lengthofstay;
	}
	public void setLengthofstay(String lengthofstay) {
		this.lengthofstay = lengthofstay;
	}
	public String getFacilitytypecd() {
		return facilitytypecd;
	}
	public void setFacilitytypecd(String facilitytypecd) {
		this.facilitytypecd = facilitytypecd;
	}
	public String getReasonnotdone() {
		return reasonnotdone;
	}
	public void setReasonnotdone(String reasonnotdone) {
		this.reasonnotdone = reasonnotdone;
	}
	public String getGendercd() {
		return gendercd;
	}
	public void setGendercd(String gendercd) {
		this.gendercd = gendercd;
	}
	public String getRacecd() {
		return racecd;
	}
	public void setRacecd(String racecd) {
		this.racecd = racecd;
	}
	public String getTestage() {
		return testage;
	}
	public void setTestage(String testage) {
		this.testage = testage;
	}
	public String getMaysidetailId() {
		return maysidetailId;
	}
	public void setMaysidetailId(String maysidetailId) {
		this.maysidetailId = maysidetailId;
	}
	public String getScreeningdate() {
		return screeningdate;
	}
	public void setScreeningdate(String screeningdate) {
		this.screeningdate = screeningdate;
	}
	public String getJuvenileage() {
		return juvenileage;
	}
	public void setJuvenileage(String juvenileage) {
		this.juvenileage = juvenileage;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEthnicbackground() {
		return ethnicbackground;
	}
	public void setEthnicbackground(String ethnicbackground) {
		this.ethnicbackground = ethnicbackground;
	}
	public String getPriorstay() {
		return priorstay;
	}
	public void setPriorstay(String priorstay) {
		this.priorstay = priorstay;
	}
	public String getFacilitytype() {
		return facilitytype;
	}
	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}
	public String getFacilityname() {
		return facilityname;
	}
	public void setFacilityname(String facilityname) {
		this.facilityname = facilityname;
	}
	public String getAngryirritable() {
		return angryirritable;
	}
	public void setAngryirritable(String angryirritable) {
		this.angryirritable = angryirritable;
	}
	public String getThoughtdisturb() {
		return thoughtdisturb;
	}
	public void setThoughtdisturb(String thoughtdisturb) {
		this.thoughtdisturb = thoughtdisturb;
	}
	public String getSomaticcomplaints() {
		return somaticcomplaints;
	}
	public void setSomaticcomplaints(String somaticcomplaints) {
		this.somaticcomplaints = somaticcomplaints;
	}
	public String getAlcoholdruguse() {
		return alcoholdruguse;
	}
	public void setAlcoholdruguse(String alcoholdruguse) {
		this.alcoholdruguse = alcoholdruguse;
	}
	public String getSuicideideation() {
		return suicideideation;
	}
	public void setSuicideideation(String suicideideation) {
		this.suicideideation = suicideideation;
	}
	public String getTraumaticexp() {
		return traumaticexp;
	}
	public void setTraumaticexp(String traumaticexp) {
		this.traumaticexp = traumaticexp;
	}
	public String getDepressedanxious() {
		return depressedanxious;
	}
	public void setDepressedanxious(String depressedanxious) {
		this.depressedanxious = depressedanxious;
	}
	public String getIssubasmntref() {
		return issubasmntref;
	}
	public void setIssubasmntref(String issubasmntref) {
		this.issubasmntref = issubasmntref;
	}
	public String getAssessmentfound() {
		return assessmentfound;
	}
	public void setAssessmentfound(String assessmentfound) {
		this.assessmentfound = assessmentfound;
	}
	public ArrayList<QueryColumn> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<QueryColumn> columns) {
		this.columns = columns;
	}
	
	/**Returns the value of the Column matching the name provided**/
	public String getColumn(String columnName){
		QueryColumn foundColumn = null;
		Iterator iter = columns.iterator();
		while (iter.hasNext()){
			QueryColumn current = (QueryColumn)iter.next();
			if (current.getColumnName().equalsIgnoreCase(columnName))
				foundColumn = current;
		}
		return foundColumn.getColumnValue();
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getVisibleInQuery() {
		return visibleInQuery;
	}
	public void setVisibleInQuery(String visibleInQuery) {
		this.visibleInQuery = visibleInQuery;
	}
	public String getOutcomedesccd() {
		return outcomedesccd;
	}
	public void setOutcomedesccd(String outcomedesccd) {
		this.outcomedesccd = outcomedesccd;
	}
	public String getSchoolhistId() {
		return schoolhistId;
	}
	public void setSchoolhistId(String schoolhistId) {
		this.schoolhistId = schoolhistId;
	}
	public String getLastattendeddate() {
		return lastattendeddate;
	}
	public void setLastattendeddate(String lastattendeddate) {
		this.lastattendeddate = lastattendeddate;
	}
	public String getCurrentgradecd() {
		return currentgradecd;
	}
	public void setCurrentgradecd(String currentgradecd) {
		this.currentgradecd = currentgradecd;
	}
	public String getExittypecd() {
		return exittypecd;
	}
	public void setExittypecd(String exittypecd) {
		this.exittypecd = exittypecd;
	}
	public String getAppropgradecd() {
		return appropgradecd;
	}
	public void setAppropgradecd(String appropgradecd) {
		this.appropgradecd = appropgradecd;
	}
	public String getSchoolcd() {
		return schoolcd;
	}
	public void setSchoolcd(String schoolcd) {
		this.schoolcd = schoolcd;
	}
	public String getSchooldistcd() {
		return schooldistcd;
	}
	public void setSchooldistcd(String schooldistcd) {
		this.schooldistcd = schooldistcd;
	}
	public String getTeacode() {
		return teacode;
	}
	public void setTeacode(String teacode) {
		this.teacode = teacode;
	}
	public String getVerifieddate() {
		return verifieddate;
	}
	public void setVerifieddate(String verifieddate) {
		this.verifieddate = verifieddate;
	}
	public String getGradeavg() {
		return gradeavg;
	}
	public void setGradeavg(String gradeavg) {
		this.gradeavg = gradeavg;
	}
	public String getGraderepeatnum() {
		return graderepeatnum;
	}
	public void setGraderepeatnum(String graderepeatnum) {
		this.graderepeatnum = graderepeatnum;
	}
	public String getGradesrepeatcd() {
		return gradesrepeatcd;
	}
	public void setGradesrepeatcd(String gradesrepeatcd) {
		this.gradesrepeatcd = gradesrepeatcd;
	}
	public String getParticipationcd() {
		return participationcd;
	}
	public void setParticipationcd(String participationcd) {
		this.participationcd = participationcd;
	}
	public String getPgmattendingcd() {
		return pgmattendingcd;
	}
	public void setPgmattendingcd(String pgmattendingcd) {
		this.pgmattendingcd = pgmattendingcd;
	}
	public String getRuleinfractioncd() {
		return ruleinfractioncd;
	}
	public void setRuleinfractioncd(String ruleinfractioncd) {
		this.ruleinfractioncd = ruleinfractioncd;
	}
	public String getAttstatuscd() {
		return attstatuscd;
	}
	public void setAttstatuscd(String attstatuscd) {
		this.attstatuscd = attstatuscd;
	}
	public String getTruancyhistory() {
		return truancyhistory;
	}
	public void setTruancyhistory(String truancyhistory) {
		this.truancyhistory = truancyhistory;
	}
	public String getHomeschooldistcd() {
		return homeschooldistcd;
	}
	public void setHomeschooldistcd(String homeschooldistcd) {
		this.homeschooldistcd = homeschooldistcd;
	}
	public String getHomeschoolcd() {
		return homeschoolcd;
	}
	public void setHomeschoolcd(String homeschoolcd) {
		this.homeschoolcd = homeschoolcd;
	}
	public String getNoncompdocid() {
		return noncompdocid;
	}
	public void setNoncompdocid(String noncompdocid) {
		this.noncompdocid = noncompdocid;
	}
	public String getCommonappdocid() {
		return commonappdocid;
	}
	public void setCommonappdocid(String commonappdocid) {
		this.commonappdocid = commonappdocid;
	}
	public String getCasenoncomnoteid() {
		return casenoncomnoteid;
	}
	public void setCasenoncomnoteid(String casenoncomnoteid) {
		this.casenoncomnoteid = casenoncomnoteid;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getDoctablename() {
		return doctablename;
	}
	public void setDoctablename(String doctablename) {
		this.doctablename = doctablename;
	}
	public String getDoctableOID() {
		return doctableOID;
	}
	public void setDoctableOID(String doctableOID) {
		this.doctableOID = doctableOID;
	}
	public String getDocumentId() {
		String documentid="";
		
		if (this.doctablename.equals("JCCASENONCOMNOTE")
			||this.doctablename.equals("JCCASENONCOMNSNC")
			||this.doctablename.equals("JCCASENONCOMNPRV"))
			documentid = this.casenoncomnoteid;
		
		if (this.doctablename.equals("JCCOMMONAPPDOC"))
			documentid = this.commonappdocid;
		
		if (this.doctablename.equals("JCIVIEWDOC"))
			documentid = this.iviewdocid;
				
		return documentid;
	}
	public void setDocumentId(String documentid) {
		this.documentId = documentid;
	}
	public String getEventmaximum() {
		return eventmaximum;
	}
	public void setEventmaximum(String eventmaximum) {
		this.eventmaximum = eventmaximum;
	}
	public String getEventminimum() {
		return eventminimum;
	}
	public void setEventminimum(String eventminimum) {
		this.eventminimum = eventminimum;
	}
	public String getEventstatuscd() {
		return eventstatuscd;
	}
	public void setEventstatuscd(String eventstatuscd) {
		this.eventstatuscd = eventstatuscd;
	}
	public String getEventtypecd() {
		return eventtypecd;
	}
	public void setEventtypecd(String eventtypecd) {
		this.eventtypecd = eventtypecd;
	}
	public String getJvsrvprvprofid() {
		return jvsrvprvprofid;
	}
	public void setJvsrvprvprofid(String jvsrvprvprofid) {
		this.jvsrvprvprofid = jvsrvprvprofid;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getEventcomments() {
		return eventcomments;
	}
	public void setEventcomments(String eventcomments) {
		this.eventcomments = eventcomments;
	}
	public String getSchoolderivedcd() {
		return schoolderivedcd;
	}
	public void setSchoolderivedcd(String schoolderivedcd) {
		this.schoolderivedcd = schoolderivedcd;
	}
	public String getMemaddressid() {
		return memaddressid;
	}
	public void setMemaddressid(String memaddressid) {
		this.memaddressid = memaddressid;
	}
	public String getCurrentenroll() {
		return currentenroll;
	}
	public void setCurrentenroll(String currentenroll) {
		this.currentenroll = currentenroll;
	}
	public String getFacilitycd() {
		return facilitycd;
	}
	public void setFacilitycd(String facilitycd) {
		this.facilitycd = facilitycd;
	}
	public String getMememployid() {
		return mememployid;
	}
	public void setMememployid(String mememployid) {
		this.mememployid = mememployid;
	}
	public String getContactfirstname() {
		return contactfirstname;
	}
	public void setContactfirstname(String contactfirstname) {
		this.contactfirstname = contactfirstname;
	}
	public String getContactlastname() {
		return contactlastname;
	}
	public void setContactlastname(String contactlastname) {
		this.contactlastname = contactlastname;
	}
	public String getSexoffender() {
		return sexoffender;
	}
	public void setSexoffender(String sexoffender) {
		this.sexoffender = sexoffender;
	}
	public String getRestrictother() {
		return restrictother;
	}
	public void setRestrictother(String restrictother) {
		this.restrictother = restrictother;
	}
}


