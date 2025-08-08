<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/21/2007		AWidjaja Create JSP--%>
<%-- 07/17/2009     C Shimek    #61004 added timeout.js  --%>
<%-- 05/01/2012		C Shimek	#73346 Revise hardcoded TJPC prompts to TJJD --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="messaging.caseplan.reply.GoalDetailsResponseEvent" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- casePlanDocument.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleGenerateCaseplan" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|70"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - Generate Caseplan</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Generate Draft button to generate a Draft Caseplan.</li>
        <logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="PENDING">  
				<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">
        <li>Select Generate Final button to generate a Final Caseplan.</li>
        </logic:notEqual>
        </logic:notEqual>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<logic:messagesPresent>

<div class='spacer'></div>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors /></td>		  
	</tr>   	  
</table>

</logic:messagesPresent> 
<%-- BEGIN HEADER INFO TABLE --%>
<div class='spacer'></div>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">

    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="goalstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>		

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			<tr>
				<td><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
			</tr>
			<tr>
				<td valign="top" align="center">

      	<!-- Please do not remove &nbsp at the end of <td?; It is there to produce the cell border even though there is no value inside of the cell -->		
        <div id='theTemplate'>
          <table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">												
            <tr bgcolor='#f0f0f0'>
              <td class="subHead" colspan="4" nowrap="nowrap">Template:</td>
            </tr>
            <tr>
    			<td>
    				<logic:equal name="caseplanForm" property="residential" value="true">
    					<h3 align="center">CHILD/FAMILY CASEPLAN<br>(Residential Placement)</h3><br>
    				</logic:equal>
    				<logic:notEqual name="caseplanForm" property="residential" value="true">
    					<h3 align="left">Harris County<br><br>Community Supervision Caseplan - Version</h3>
    					..........................................................................................................................<br>
    					<font face="Arial" size="1"><i>Effective date 09/01/2003<i></font><br>
    					--------------------------------------------------------------------------------------------------------------------------<br>
    				</logic:notEqual>
    		        <table border="1" cellpadding="0" cellspacing="0" width="100%">
    		          <tr bgcolor="#f0f0f0">
    		            <td colspan="2" valign="top" align="center" class="subHead">IDENTIFYING INFORMATION</td>
    		          </tr>
    		          <tr>
    		            <td width="50%" valign="top" align="left"><p><strong>Child's Name: </strong><em><bean:write name="caseplanForm" property="juvDetails.fullNameFirst"/>&nbsp;</em></p></td>
    		            <td width="50%" valign="top" align="left"><p><strong>County: </strong><em>Harris</em></p></td>
    		          </tr>
    		          <tr>
    		            <td width="50%" valign="top" align="left"><p><strong>Child's Date of Birth: </strong><em><bean:write name="caseplanForm" property="juvDetails.dob" formatKey="date.format.mmddyyyy"/>&nbsp;</em></p></td>
    		            <td width="50%" valign="top" align="left"><p><strong>Caseworker PID: </strong><em><bean:write name="caseplanForm" property="juvDetails.juvNum"/>&nbsp;</em></p></td>
    		          </tr>
    		        </table>
    		        <%--adding project release date to caseform  ERJIMS200075824 MJCW -- start--%>
    		        <p><strong>PROJECTED DATE OF RELEASE FROM PROBATION:</strong>&nbsp;<bean:write name="juvenileCasefileForm" property="supervisionEndDateStr" formatKey="date.format.mmddyyyy"/></p>
    		        <%--adding project release date to caseform  ERJIMS200075824 MJCW -- end--%>
            <p align="left"><strong>PURPOSE OF PLAN: </strong>The goals and tasks outlined in this plan are designed to help resolve issues that led to your involvement with the juvenile justice system and to ensure the safety, permanency, and well being of your family. You will be expected to participate in developing this caseplan and demonstrate progress in achieving the goals listed. Your progress will be reviewed and evaluated. In addition to the activities outlined in the caseplan, you are expected to adhere to all court ordered conditions of probation. <strong></strong></p>
    		
      		<logic:equal name="caseplanForm" property="residential" value="true">
      			<p align="left"><strong>PRIOR SERVICES: </strong>List all services previously provided to help the child remain safely with the family. If no services were provided there <strong><u>MUST</strong></u> be an explanation. </p>
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
      						<tr>      							
      							<td align="left">
      								<p align="left"><b><bean:write name="caseplanForm" property="priorServices"/></b></p>
      							</td>
      						</tr>      				
      					</table>
              </logic:equal>
      		<logic:notEqual name="caseplanForm" property="residential" value="true">
      				<p align="left"><strong>PRIOR SERVICES: </strong>List all services previously provided to help the child and family</p>
      				<p align="left">
      					<table border="0" cellpadding="0" cellspacing="0" width="100%">
      						<tr>      							
      							<td align="left">
      								<p><b><bean:write name="caseplanForm" property="priorServices"/></b></p>
      							</td>
      						</tr>      				
      					</table><br>
      				</p>
      		</logic:notEqual>
      		 
      		 
    		 <br>
    		 
    		 <table border="1" cellpadding="0" cellspacing="0" width="100%">
      		 <tr><td colspan="2" align="left">
    		 <p><strong>TITLE IV-E CANDIDACY:</strong>&nbsp;Please indicate below the tool or documentation that was used to determine if the juvenile is <b><i>currently</i></b> a candidate for foster care. <i>The risk
    		 assessment must be one of the tools used, but may not be the only tool used.</i></p>
    		 </td></tr>
    		 <tr>
    		 	<td align="left"><strong><bean:message key="prompt.socialInvestHistRepDated" />:</strong>&nbsp;<bean:write name="caseplanForm" property="socialHistDated" /></td>
    		 	<td align="left"><strong><bean:message key="prompt.psychological"/>&nbsp;<bean:message key="prompt.report"/>&nbsp;<bean:message key="prompt.dated"/>:</strong>&nbsp;<bean:write name="caseplanForm" property="psychologicalRepDated" /></td>														
    		 </tr>
    		 <tr>
    		 	<td align="left"><strong><bean:message key="prompt.riskAssessdated" />:</strong>&nbsp;<bean:write name="caseplanForm" property="riskAssesmentDated" /></td>
    		 	<td align="left"><strong><bean:message key="prompt.other"/>(Date & Explain):</strong>&nbsp;<bean:write name="caseplanForm" property="otherDated" />&nbsp;&nbsp;&nbsp;<bean:write name="caseplanForm" property="explanation" /></td>														
    		 </tr>
    		 <tr>
    		 	<td colspan="2" align="left">
	    		 	<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="Yes">
	    		 		<html:checkbox property="juvFosterCareCandidateStr" disabled="true" checked/>	    		 		
	    		 	</logic:equal>
	    		 	<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="No">
	    		 		<html:checkbox property="juvFosterCareCandidateStr" disabled="true"/>    		 		
	    		 	</logic:equal>
	    		 	
		    		 	<strong><bean:message key="prompt.isJuvFosterCareCand" /></strong>
		    		 	<br>
		    		 	Based on the above information this juvenile has been determined to be (or continues to be) at imminent risk of removal from the home and placement into foster care , absent preventative pre-placement intervention services.
		    		 	If the services described in the following casplan (particularly in the medical, safety/security, emotional/mental health, and family services domains) are not effective, the plan will be removal of the juvenile from his/her home with placement into foster care.
	    		 	<br>
	    		 	<br>
	    		 	<i>Describe below the current risk factors which place the juvenile at imminent risk of removal and placement into foster care</i>
	    		 	<br>
	    		 	<br>
	    		 	<bean:write name="caseplanForm" property="titleIVEComment" />
	    		 	<br>
	    		 	<br>
    		 	</td>
    		 </tr>
    		 <tr>
    		 	<td align="left">
	    		 	
	    		 	<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="Yes">	    		 		
	    		 		<html:checkbox property="juvFosterCareCandidateStr2" disabled="true" />
	    		 	</logic:equal>
	    		 	<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="No">
	    		 		<html:checkbox property="juvFosterCareCandidateStr2" disabled="true" checked/>
	    		 	</logic:equal>
	    		 	
	    		 	<strong><bean:message key="prompt.isNotJuvFosterCareCand" /></strong></td>
	    		<td align="left"><strong><bean:message key="prompt.dateDetermMade"/>:</strong>&nbsp;
	    		 	<bean:write name="caseplanForm" property="dtDeterminationMade" />
	    		</td>														
    		 </tr>
    		 </table>
    		 
    			 
    		 
    		 
    		 <br>
    		<logic:equal name="caseplanForm" property="residential" value="true">
	        <table border="1" cellpadding="0" cellspacing="0" width="100%">
	          <tr bgcolor='#f0f0f0'>
	            <td colspan="3" valign="top" align="center"><strong>FACILITY INFORMATION</strong></td>
	          </tr>
	          <tr>
	            <td width="15%" valign="top" align="left"><p><strong>Name of Facility: </strong></p></td>
	            <td width="35%" valign="top" align="left"><bean:write name="caseplanForm" property="placementInfo.facilityName"/>&nbsp;</td>
	            <td width="50%" valign="top" align="left"><p><strong>Date of Placement: </strong><em><bean:write name="caseplanForm" property="placementInfo.entryDate" formatKey="date.format.mmddyyyy"/>&nbsp;</em></p></td>
	          </tr>
	          <tr>
	            <td width="15%" valign="top" align="left"><p><strong>Address: </strong></p></td>
	            <td width="85%" colspan="2" valign="top" align="left"><bean:write name="caseplanForm" property="placementInfo.facilityAddress"/>&nbsp;</td>
	          </tr>
	          <tr>
	            <td width="15%" valign="top" align="left"><p><strong>City/State/Zip: </strong></p></td>
	            <td width="35%" valign="top" align="left"><p><em><bean:write name="caseplanForm" property="placementInfo.facilityStateZip"/>&nbsp;</em></p></td>
	            <td width="50%" valign="top" align="left"><p><strong>Phone #: </strong><em><bean:write name="caseplanForm" property="placementInfo.facilityPhone"/></em></p></td>
	          </tr>
	        </table>
	        <p align="left"><strong>NEED FOR PLACEMENT: </strong>Explain why this child requires placement. Discuss the child's behavior <strong>AND </strong>the family situation. <strong></strong></p>
	        <p align="left"><bean:write name="caseplanForm" property="placementInfo.reasonPlacementReqd"/> </p>
	        <p align="left"><strong>APPROPRIATENESS OF PLACEMENT: </strong>Explain what specific services are being provided to safely meet the child's needs as discussed in the previous section. <strong></strong></p>
	        <p align="left"><bean:write name="caseplanForm" property="placementInfo.specificServices"/> </p>
	        <p align="left"><strong>OUT OF STATE PLACEMENT: </strong>If the child is placed outside of Texas , explain why this is in the best interest of the child. <em>(Refer to TJJD Policy #4). </em></p>
	        <p align="left"><bean:write name="caseplanForm" property="placementInfo.whyOutsideTexas"/> </p>
	        
	        <p align="left"><strong>PERMANENCY PLAN: </strong>Plan for the safe and permanent placement of the child.</strong></p>			

			<table width="100%">
				<logic:iterate indexId="permIdx" id="permanencyIter" name="caseplanForm" property="permanencyListPlan">
					<bean:define id="code123" name="permanencyIter" property="code" type="java.lang.String"/>
					<%
					if(permIdx.intValue()%3==0)
					{
						out.println("<tr>");
					}
					%>
						<td align="left"><html:checkbox disabled="true" name="caseplanForm" property="currentPlacementInfo.permanencyPlanId" value="<%=code123%>"/><bean:write name="permanencyIter" property="description"/></td>
					
				</logic:iterate>
			</table>
			
			
	        <p><strong><em>*If emancipation/independent living or other permanent living arrangement is the permanency plan,<br>
							explain why this is in the best interest of the child.</em></strong></p>
	        <p>Projected permanency date: <bean:write name="caseplanForm" property="placementInfo.expReleaseDate" formatKey="date.format.mmddyyyy"/> </p>
        </logic:equal>
		
        <table border="1" cellpadding="0" cellspacing="0" width="100%">
			<tr bgcolor='#f0f0f0'>
				<td colspan="6" valign="top"><div align="center"><strong>MEDICAL/DENTAL DOMAIN </strong><strong>NAMES &amp; ADDRESSES OF CHILD'S MOST RECENT HEALTHCARE PROVIDERS </strong><strong><em>(prior to placement) </em></strong></div></td>
			</tr>
			<tr>
				<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50%">
								<table border='1' width='100%'>
									<tr>
										<td><p align="center"><strong>MEDICAL </strong></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>Name: </strong><em><bean:write name="caseplanForm" property="medicalContact.name.formattedName"/></em></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>Address: </strong><em><bean:write name="caseplanForm" property="medicalContact.address.streetAddress"/></em></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>City/State/Zip: </strong><em><bean:write name="caseplanForm" property="medicalContact.address.cityStateZip"/></em></p></td>
									</tr>
									<tr>
										<td align="left">
											<table border="0" width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td width="15%" align="left">
														<strong>Phone #:</strong>
													</td>
													<td align="left">
														Work: <bean:write name="caseplanForm" property="medicalContact.workPhoneNumber.formattedPhoneNumber"/>
													</td>
													<tr>
														<td></td><td align="left">Mobile: <bean:write name="caseplanForm" property="medicalContact.mobilePhoneNumber.formattedPhoneNumber"/></td>
													</tr>
													<tr>
														<td></td><td align="left">Fax: <bean:write name="caseplanForm" property="medicalContact.faxPhoneNumber.formattedPhoneNumber"/></td>
													</tr>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="50%">
								<table border="1" width='100%'>
									<tr>
										<td align="left"><p align="center"><strong>DENTIST</strong></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>Name: </strong><em><bean:write name="caseplanForm" property="dentistContact.name.formattedName"/></em></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>Address: </strong><em><bean:write name="caseplanForm" property="dentistContact.address.streetAddress"/></em></p></td>
									</tr>
									<tr>
										<td align="left"><p><strong>City/State/Zip: </strong><em><bean:write name="caseplanForm" property="dentistContact.address.cityStateZip"/></em></p></td>
									</tr>
									<tr>
										<td align="left">
											<table border="0" width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td width="15%" align="left">
														<strong>Phone #:</strong>
													</td>
													<td align="left">
														Work: <bean:write name="caseplanForm" property="dentistContact.workPhoneNumber.formattedPhoneNumber"/>
													</td>
													<tr>
														<td></td><td align="left">Mobile: <bean:write name="caseplanForm" property="dentistContact.mobilePhoneNumber.formattedPhoneNumber"/></td>
													</tr>
													<tr>
														<td></td><td align="left">Fax: <bean:write name="caseplanForm" property="dentistContact.faxPhoneNumber.formattedPhoneNumber"/></td>
													</tr>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>			
			</tr>	
          <tr>
            <td width="720" colspan="6" valign="top" align="left"><p><strong>Child's current medications (including psychotropic meds): </strong><br>
				<em>
					<logic:iterate indexId="medicationListIdx" id="medicationListIter" name="caseplanForm" property="medicationList">
						<%=medicationListIdx.intValue()+1%>. <bean:write name="medicationListIter" property="medicationName"/><br>
					</logic:iterate>
				</em></p></td>
          </tr>
          <tr>
            <td width="720" colspan="6" valign="top" align="left"><p><strong>Indicate what medications are for: </strong><br>
				<em>
					<logic:iterate indexId="medicationListIdx" id="medicationListIter" name="caseplanForm" property="medicationList">
						<%=medicationListIdx.intValue()+1%>. <bean:write name="medicationListIter" property="reasonForMedication"/><br>
					</logic:iterate>
				</em></p></td>
          </tr>
          <tr>
            <td width="720" colspan="6" valign="top" align="left"><p><strong>List any other important medical information/concerns:</strong><br>
				<em>
					<logic:iterate indexId="healthIssueListIdx" id="healthIssueListIter" name="caseplanForm" property="healthIssueList">
							<%=healthIssueListIdx.intValue()+1%>. <bean:write name="healthIssueListIter" property="issueId"/><br>
					</logic:iterate>
				</em></p></td>
          </tr>
		  <logic:equal name="caseplanForm" property="residential" value="true">
				<tr>
		            <td width="720" colspan="6" valign="top" align="left"><p><strong>Date child's immunization &amp; health records were provided to caregiver:</strong><br>
						<em>
							<logic:iterate indexId="healthIssueListIdx" id="healthIssueListIter" name="caseplanForm" property="healthIssueList">
								<logic:equal name="healthIssueListIter" property="issueId" value="IMMUNIZATION RECORDS RECEIVED">
									<bean:write name="healthIssueListIter" property="entryDate" formatKey="date.format.mmddyyyy"/><br>
								</logic:equal>
							</logic:iterate>
						</em>
						</p>
					</td>
				</tr>
		  </logic:equal>
		  <logic:notEqual name="caseplanForm" property="residential" value="true">
			<tr>
	            <td width="720" colspan="6" valign="top" align="left"><p><strong>Type of medical coverage:</strong><br>
					<em>
						<logic:iterate indexId="healthIssueListIdx" id="typeOfMedicalCoverage" name="caseplanForm" property="typesOfMedicalCoverage">
							<logic:equal name="typeOfMedicalCoverage" property="receivingBenefits" value="true">
								- <bean:write name="typeOfMedicalCoverage" property="eligibilityType"/><br>
							</logic:equal>
						</logic:iterate>
					</em>
					</p>
				</td>
			</tr>
		  </logic:notEqual>
			
			<tr>
				<td colspan="6">
					<bean:define id="mdDetailsStr">goalsByGoalType(MD)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=mdDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr>			
			<tr>
				<td colspan="6" align="left">
					<bean:define id="ssDetailsStr">goalsByGoalType(SS)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="SAFETY/SECURITY DOMAIN"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=ssDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr>
			<logic:equal name="caseplanForm" property="residential" value="true">
			<tr>
				<td colspan="6" align="left"> 
					<bean:define id="rcDetailsStr">goalsByGoalType(RC)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="RECREATIONAL DOMAIN"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=rcDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr>
			</logic:equal>
			<tr>
				<td colspan="6" align="left">
					<bean:define id="edDetailsStr">goalsByGoalType(ED)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="EDUCATIONAL DOMAIN"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=edDetailsStr%>"/>
						<tiles:put name="schoolHistory" beanName="caseplanForm" beanProperty="juvenileLatestSchoolHistory"/>
					</tiles:insert>	
				</td>
			</tr>
			<tr>
				<td colspan="6" align="left">
					<bean:define id="emDetailsStr">goalsByGoalType(EM)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="EMOTIONAL DOMAIN"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=emDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr>
			<%--commented as a part of ER JIMS200077469 <tr>
				<td colspan="6">
					<bean:define id="scDetailsStr">goalsByGoalType(SC)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="RELATIONSHIP/SOCIALIZATION DOMAIN"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=scDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr> --%>
			<tr>
				<td colspan="6">
					<bean:define id="paDetailsStr">goalsByGoalType(PA)</bean:define>
			
					<tiles:insert page="goalRulesTile.jsp" flush="true">
						<tiles:put name="title" value="PREPARATION FOR ADULT LIVING/VOCATIONAL DOMAIN"/>
						<tiles:put name="additionalTitle" value="(if child is or will be 16 before next review)"/>
						<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=paDetailsStr%>"/>	
					</tiles:insert>	
				</td>
			</tr>
			<%-- <logic:equal name="caseplanForm" property="residential" value="true"> --%>
				<tr>
					<td colspan="6" align="left">
						<bean:define id="fsDetailsStr">goalsByGoalType(FS)</bean:define>
				
						<tiles:insert page="goalRulesTile.jsp" flush="true">
							<tiles:put name="title" value="FAMILY SERVICES DOMAIN"/>
							<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=fsDetailsStr%>"/>	
						</tiles:insert>	
					</td>
				</tr>
				<%--Commented as a part of ERJIMS200077469 <tr>
					<td colspan="6">
						<bean:define id="spDetailsStr">goalsByGoalType(SP)</bean:define>
				
						<tiles:insert page="goalRulesTile.jsp" flush="true">
							<tiles:put name="title" value="SUPPORT SERVICES PROVIDED TO CAREGIVER"/>
							<tiles:put name="additionalTitle" value="(identify services the department will provide to the caregiver to assist in meeting the child's needs)"/>
							<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=spDetailsStr%>"/>	
						</tiles:insert>	
					</td>
				</tr> --%>
		        <tr bgcolor='#f0f0f0'>
	            <td width="720" colspan="8" valign="top" align="center"><strong>PARTICIPATION &amp; DISTRIBUTION </strong><br>
	            	<strong><em>(enter the date each person participated in developing this caseplan and the date a copy was provided to them) </em></strong>
	        	</td>
	          </tr>
				<tr>
					<td colspan="6">
						<table width="100%" border="1" cellpadding="0" cellspacing="0" frame="void" rules="all">
							<tr bgcolor='#f0f0f0'>
					            <td width="30%">&nbsp;</td>
					            <td width="15%" valign="bottom" align="center"><strong>Child </strong></td>
					            <td width="15%" valign="bottom" align="center"><strong>Family </strong></td>
					            <td width="15%" valign="bottom" align="center"><strong>Caregiver</strong></td>
					            <td width="25%" valign="bottom" align="center"><strong>Other</strong><br><p align="left"><strong><em>Name:&nbsp;</em></strong><em><bean:write name="caseplanForm" property="othername"/>&nbsp;</em></p></td>
					        </tr>
					        <tr>
					            <td width="30%" valign="top" align="left"><p><strong>Date Notified </strong></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="childDtNotified" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="familyDtNotified" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="caregiverDtNotified" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="25%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="otherDtNotified" formatKey="date.format.mmddyyyy"/></em></p></td>
					        </tr>
					        <tr>
					            <td width="30%" valign="top" align="left"><p><strong>Method of Notification </strong></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="childNotifMethod" /></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="familyNotifMethod" /></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="caregiverNotifMethod" /></em></p></td>
					            <td width="25%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="otherNameNotifMethod" /></em></p></td>
					        </tr>
					        <tr>
					            <td width="30%" valign="top" align="left"><p><strong>Date of Participation </strong></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="childDtOfParticipation" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="familyDtOfParticipation" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="caregiverDtOfParticipation" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="25%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="otherNameDtOfParticipation" formatKey="date.format.mmddyyyy"/></em></p></td>
					        </tr>
					        <tr>
					            <td width="30%" valign="top" align="left"><p><strong>Date Copy Received/Mailed </strong></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="childMailedDt" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="familyMailedDt" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="15%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="caregiverMailedDt" formatKey="date.format.mmddyyyy"/></em></p></td>
					            <td width="25%" valign="top"><p align="center"><em><bean:write name="caseplanForm" property="otherNameMailedDt" formatKey="date.format.mmddyyyy"/></em></p></td>
					        </tr>
						</table>
					</td>
				</tr>
			<%-- </logic:equal> --%>
			<%-- <logic:notEqual name="caseplanForm" property="residential" value="true"> --%>
	 			<%--commented as a part of ERJIMS200077469 <tr>
					<td colspan="6">
						<bean:define id="fpDetailsStr">goalsByGoalType(FP)</bean:define>
				
						<tiles:insert page="goalRulesTile.jsp" flush="true">
							<tiles:put name="title" value="PARENT/CHILD PARTICIPATION DOMAIN"/>
							<tiles:put name="goals" beanName="caseplanForm" beanProperty="<%=fpDetailsStr%>"/>	
						</tiles:insert>	
					</td>
				</tr>     --%> 
			<%-- </logic:notEqual> --%>    
          <tr bgcolor='#f0f0f0'>
            <td width="720" colspan="8" valign="top"><p align="center"><strong>PLAN OF CONTACT </strong><strong><em></em></strong></p></td>
          </tr>
          <tr>
            <td width="720" colspan="8" valign="top" align="left"><p><strong>A. Level of Supervision: &nbsp; </strong><bean:write name="caseplanForm" property="supervisionLevel"/></p></td>
          </tr>
          <tr>
            <td width="720" colspan="8" valign="top" align="left"><p><strong>B. The current plan of contact between the child and JPO is as follows<i>(document frequency &amp; method)</i>: one face to face visit each week, one monthly workshop and one phone call each week. </strong>&nbsp;<bean:write name="caseplanForm" property="contactInformation" /></p></td>
          </tr>
		  <logic:iterate id="cfIter" name="caseplanForm" property="contactFrequency">
	        <tr><td width="720" colspan="8" valign="top" align="left">Goal: <bean:write name="cfIter" property="goal"/>
	          <logic:iterate id="rulesIter" name="cfIter" property="associatedRules">
	          	<logic:equal name="rulesIter" property="completionStatusId" value="O">
	          		<br> Rule: <bean:write name="rulesIter" property="ruleMonitorFreqDesc"/>
	          		<br>&nbsp;<bean:write name="rulesIter" property="ruleLiteralResolved" filter="false"/>	          
	          </logic:equal>
	       	</logic:iterate>	         
	       </td></tr>
		  </logic:iterate>
		  <logic:empty name="caseplanForm" property="contactFrequency">
			<tr><td width="720" colspan="8" valign="top">&nbsp;</td></tr>
		  </logic:empty>
        </table>
		
	<logic:equal name="caseplanForm" property="residential" value="true">
		<p align="center"><strong>PARENTAL/FAMILIAL RIGHTS AND RESPONSIBILITIES </strong></p>
        <p>Along with the right to visit your child, you have the right to be notified of any change in the placement of your child. </p>
        <p>You have a right to be notified if there is a change in your visitation schedule. </p>
        <p>You have the right to discuss any changes made in the placement of your child and the right to discuss this with the JPO's supervisor if you feel your concerns have not been addressed. </p>
        <p>You have the right to know the plan of action necessary to have your child returned to you, and you have the responsibility to follow that plan in order to correct the circumstances which required the placement of your child in substitute care. </p>
        <p>You have the right to be notified of any unusual occurrence regarding your child such as injury, illness, runaway, etc. </p>
        <p>You have the right to be notified of any child facility staffing, placement review, or administrative hearing, which has the potential for impacting the return of your child. In addition, you have the responsibility to attend these staffings/reviews and participate in the development of the plan of action. </p>
        <p>While your child is in placement, you have the right to have the situation reviewed at least every six months to ensure that appropriate treatment is being provided.</p>
        
        <br>
        <table border="1" cellpadding="0" cellspacing="0">
          <tr>
            <td width="528" valign="top"><strong>CHILD: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
          <tr>
            <td width="528" valign="top"><strong>FAMILY: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
          <tr>
            <td width="528" valign="top"><strong>JPO: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
        </table>
        <p><strong>If any party has not, or refuses to sign, explain: </strong></p>
        <p>&nbsp;</p>
        <div align="center"> 
          <strong>CHILD/FAMILY CASEPLAN</strong><br><strong>(IV-E Residential Placement)</strong>
        </div>
        <br>
        <h5>DATE FAMILY NOTIFIED OF REMOVAL FROM THE HOME: ____<em><bean:write name="caseplanForm" property="placementInfo.entryDate" formatKey="date.format.mmddyyyy"/></em>_____________
        <br>METHOD OF NOTIFICATION: ___<em>Court Order</em>_________________________________ </h5>
        
		<h5>FACILITY TYPE: <em><bean:write name="caseplanForm" property="placementInfo.juvenilePlacementType"/></em></h5>
		
        <p><strong>CLOSE PROXIMITY: </strong>Is this the closest facility available which best meets the child's specific needs? [juvenile_facility_stay.caseplanquestion1] &nbsp; <input type=checkbox checked="checked" /> Yes <input type=checkbox /> No </p>
        <p><strong>LEAST RESTRICTIVE: </strong>Is this the least restrictive environment available which best meets the child's specific needs? [juvenile_facility_stay.caseplanquestion2] &nbsp; <input type=checkbox checked="checked" /> Yes <input type=checkbox /> No </p>
        <p><strong>SCHOOL: </strong>Was proximity to the child's former school district taken into consideration when selecting this facility?
        [juvenile_facility_stay.caseplanquestion3] &nbsp; <input type=checkbox checked="checked" /> Yes <input type=checkbox /> No
        <br>Comments: </p>
        
        <p>THE SERVICES DOCUMENTED IN THIS PLAN ARE APPROPRIATE AND CONSISTENT WITH THE BEST INTEREST AND SPECIAL NEEDS OF THE CHILD. THIS PLAN DOCUMENTS THE STEPS BEING TAKEN BY ALL PARTIES TO FINALIZE THE PERMANENCY PLAN. </p>
        
        <strong>SIGNATURES: </strong><em></em>
        <table border="1" cellpadding="0" cellspacing="0">
          <tr>
            <td width="528" valign="top"><strong>CHILD: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
          <tr>
            <td width="528" valign="top"><strong>FAMILY: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
          <tr>
            <td width="528" valign="top"><strong>JPO: </strong><br>&nbsp;</td>
            <td width="192" valign="top"><strong>DATE: </strong><em><bean:write name="caseplanForm" property="todaysDate" formatKey="date.format.mmddyyyy"/></em></td>
          </tr>
        </table>
        <p><strong>If any party has not, or refuses to sign, explain: </strong></p>
        <p>&nbsp;</p>
	</logic:equal>
	<logic:notEqual name="caseplanForm" property="residential" value="true">
		<br><br><br>
		
		<table border="0" width="100%" cellpadding="5" cellspacing="5">
			<tr bgcolor='#f0f0f0'>
				<td valign="top" colspan="2">
					<p align="center"><strong>ACKLOWLEDGEMENT</strong></p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					I, the undersigned juvenile referenced above received a copy of the caseplan, understand the caseplan process and have 
					been provided a full opportunity to give my input during this caseplan. 
				</td>
			</tr>
			<tr>
				<td>______________________________________________________
				</td>
				<td>______________________________________________________
				</td>
			</tr>
			<tr>
				<td>Signature of Juvenile
				</td>
				<td>Date of Review
				</td>
			</tr>
		</table>	
	</logic:notEqual>
	
        <!--commented as a part of ER 11229
        <p align="center">
        <strong>CHILD/FAMILY CASEPLAN </strong><br>
        <strong>(Definitions of Domains) </strong></p>
        <p align="left">The preceding document is designed to satisfy requirements for Case Management Standards, Substitute Care Provider Outcome Standards and Title IV-E Standards. In developing the caseplan, all goals should be measurable in order that the child's progress can be measured when the service plan is reviewed. It should also be noted that every child may not have goals in each of the referenced domains. The domains are defined below and include an example of a stated goal. <em></em></p>
        <p align="left"><strong>Medical Domain </strong>: The medical domain is related to a child's physical health. It includes, but is not limited to, medication management, medication monitoring, and management of acute and chronic medical conditions. </p>
        <p><strong>Safety and Security Domain </strong>: The safety and security domain is characterized by the absence of harm to self and others and safety to self, others, and community. This domain includes, but is not limited to, self-harm, aggression, and destructive acts. </p>
        <p><strong>Recreational Domain </strong>: The recreational domain involves the child's ability to choose and participate in age-appropriate play and activities. This domain includes, but is not limited to, hobbies and sports. </p>
        <p><strong>Educational Domain </strong>: The educational domain is related to a child's performance, progress, and conduct in the most appropriate and least restrictive academic or vocational setting. </p>
        <p><strong>Mental/Behavioral Health Domain </strong>: The mental/behavioral health domain refers to the behavioral and emotional functioning of the child, as well as any psychiatric symptomatology that may be present. </p>
        <p><strong>Relationship/Socialization Domain </strong>: The relationship/socialization domain is characterized by, but is not limited to, a child's ability to trust, to form positive relationships, to function well as part of a family unit, as well as by the development and maintenance of age-appropriate social relationships. It is also characterized by age-appropriate social behavior, problem solving skills, and social skills in various social settings. </p>
        <p><em> </em><strong>Preparation for Adult Living (PAL) Domain: </strong>The P.A.L. domain refers to any child aged 16 years of age or older learning skills designed to assist the child to transition to independent living. Areas of training could include, but are not limited to, job seeking skills, vocational training, housing, money management, consumer awareness, hygiene, nutrition, housekeeping, transportation, etc.). Goals should be included in this domain for any youth who is currently 16 years of age, or will become 16 years of age before the next caseplan review. </p>
        <p><strong>Family Services Domain </strong>: The family services domain should include services provided to the family (or other person to whom the child will return upon discharge from residential placement) which address identified needs that contributed to the child's removal from the home. These services should be consistent with the stated permanency plan (i.e. if the child is to return to a grandparent, services should be provided to the grandparent instead of the parent). Contact with the family is required and should not be listed as a service to the family. </p>
        <p><strong>Support Services Provided to Caregiver Domain: </strong>This domain should describe what services will be provided to the caregiver to assist them in the care and rehabilitation of the child. Such services could include participation in staffings, arranging and/or coordinating home visits, providing clothing and other supplies, etc. Contact with the caregiver is required and should not be listed as a support service. </p>
        
        --></td>
        </tr>
        </table>
        </div>
		<%-- BEGIN BUTTON TABLE --%>
		<div class="spacer"></div>
		<table border="0" width="100%">
		  <tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
				<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="PENDING">  
											<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
				<input disabled="true" type="button" value="E-Signature" onClick="alertAndGo('E-Signature Enabling Technology', 'goalList.htm')">   
				</logic:notEqual>
				</logic:notEqual>   
				<html:submit property="submitAction"><bean:message key="button.generateDraft"/></html:submit>    
				<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="PENDING">  
				<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
					<html:submit property="submitAction"><bean:message key="button.generateFinal"/></html:submit>
					</logic:notEqual>
					</logic:notEqual>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		  </tr>
		</table>
		<div class="spacer"></div>
		<%-- END BUTTON TABLE --%>

		
				</td>
			 </tr>
    </table>
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
