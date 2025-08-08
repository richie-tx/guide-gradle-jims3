<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose - Create JSP -->
<!-- 08/28/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 04/23/2008  C. Shimek	     Defect#51102 remove update address and update employment hrefs -->
<!-- 05/14/2008  L Deen		     Defect#51788 display LName, FName MName for supervisee -->
<!-- 09/24/2008  C. Shimek	     Defect#54502 revised Unit prompt to Program Unit -->
<!-- 10/22/2008  C. Shimek	     Defect#55102 removed Contact Reason -->
<!-- 11/13/2009  C. Shimek	     Defect#62386 revised Next Contact Date into seperated Date and Time display -->
<!-- 02/25/2010  D. Williamson   Defect#64009 Removed unneeded javascript code that was causing runtime errors in Create Casenote -->
<!-- 09/03/2010  D. Williamson   ER #66311 Clarified Case Begin/End date. -->
<!-- 09/10/2010  D. Williamson	 ER #66573 Display Address and Employment in Header.	 --> 
<!-- 01/14/2011  R. Capestani	 ER #68604 Display Name on Case in Casenote Header(UI/PD-RCK)	 --> 
<!-- 04/20/2011  R Young		 Defect #69758 End Date displaying incorrectly on Casenote header -->
<!-- 09/21/2012  R. Capestani	 ER #74303 added hyperlink to View Supervision Order from Casenotes(UI) --> 
<!-- 10/01/2012  R. Capestani	 ER #74303 added hyperlink to View Caseload Order from Casenotes(UI) --> 
<!-- 10/01/2012  R. Capestani	 ER #74303 added hyperlink to View Supervisee Details from Casenotes(UI) -->
<!-- 10/16/2012  R. Capestani	 ER #74303 display address on one line -->
<!-- 10/19/2012  R. Capestani	 ER #74303 set Supervisee name on casenote list header to name on supervision order -->
<!-- 10/31/2012  C. Shimek	     Activity #74534 revised caseRow prompt from Offense to Printed Offense Description -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Common Supervision - common/superviseeInfoHeader.jsp</title>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="javascript" type="text/javascript">

function flipText( idOfTD, textToSet )
{
	if (document.getElementById( idOfTD ).innerHTML.indexOf("Address") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Address")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Address" ;
			show("addressRow", 1, "row")
		}else {
			document.getElementById( idOfTD ).innerHTML = "View Address" ;
			show("addressRow", 0)
		}
	}else if (document.getElementById( idOfTD ).innerHTML.indexOf("Employment") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Employment")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Employment" ;
			show("employmentRow", 1, "row")
		}else 
		{
			document.getElementById( idOfTD ).innerHTML = "View Employment" ;
			show("employmentRow", 0)
		}
	}else if (document.getElementById( idOfTD ).innerHTML.indexOf("Cases") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Cases")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Cases" ;
			show("casesRow", 1, "row")
		}else {
			document.getElementById( idOfTD ).innerHTML = "View Cases" ;
			show("casesRow", 0)
		}
	}
}
</script> 
</head>
<body>
<tiles:importAttribute name="hideQuickLinks" ignore="true" />
<logic:notPresent name="hideQuickLinks">
<bean:define id="hideQuickLinks" value="false"/>
</logic:notPresent>
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr class="paddedFourPix">
		<td class="formDeLabel">Supervisee Information</td>
		<td align="right" class="formDeLabel">
		<logic:present name="hideQuickLinks">
			<logic:notEqual name="hideQuickLinks" value="true">
				<a href="javascript:flipText('vAddress', 'View Address')" id="vAddress">Hide Address</a> | <a href="javascript:flipText('vEmployment', 'View Employment')" id="vEmployment">Hide Employment</a> | <a href="javascript:flipText('vCases', 'View Cases')" id="vCases">Hide Cases</a>
			</logic:notEqual>
		</logic:present>
		</td>
	</tr>
	<tr>
		<td bgcolor="#cccccc" colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td class="headerLabel"><bean:message key="prompt.name"/></td>
					<td class="headerData">
						<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="superviseeInfoHeaderForm" property="spn"/>">
							<bean:write name="superviseeInfoHeaderForm" property="superviseeNamePtr"/>
						</a>
						 <logic:equal name="superviseeInfoHeaderForm" property="inComplianceInd" value="true">
							 <img src="/<msp:webapp/>images/greenLight.gif" name="greenLight" title="In Compliance" border="0">
						 </logic:equal>
						 <logic:notEqual name="superviseeInfoHeaderForm" property="inComplianceInd" value="true">
							 <img src="/<msp:webapp/>images/redLight.gif" name="redLight" title="Out of Compliance" border="0">
						 </logic:notEqual>
					</td>
					<td class="headerLabel"><bean:message key="prompt.SPN"/></td>
					<td class="headerData">
						<a href="/<msp:webapp/>../CommonSupervision/superviseeSearchSetupAction.do?submitAction=Submit&selectedValue=<bean:write name="superviseeInfoHeaderForm" property="spn"/>&searchType=SPN"">
							<bean:write name="superviseeInfoHeaderForm" property="spn"/>
						</a>
					</td>
				 </tr>
				<tr>
					<td class="headerLabel"><bean:message key="prompt.assignedOfficer"/></td>
					<td  class="headerData"><msp:formatter name="superviseeInfoHeaderForm" property="officerName"  format="L,F"/><bean:write name="superviseeInfoHeaderForm" property="officerPosition"/></td>
					<td class="headerLabel"><bean:message key="prompt.programUnit"/></td>
					<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="unit"/></td>
				</tr>
				<tr>
					<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.last"/> <bean:message key="prompt.assessmentDate"/></td>
					<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="lastAssessmentDate" formatKey="date.format.mmddyyyy"/></td>
					<td class="headerLabel"><bean:message key="prompt.LOS"/></td>
					<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="los"/></td>
				</tr>
				<tr>
					<td class="headerLabel"><bean:message key="prompt.nextContactDate"/></td>
					<td class="headerData">
						<bean:write name="superviseeInfoHeaderForm" property="nextContactDate" formatKey="date.format.mmddyyyy"/>&nbsp;
						<bean:write name="superviseeInfoHeaderForm" property="nextContactTime" formatKey="time.format.hhmma"/>
					</td>
					<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.contactMethod"/></td>
					<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="contactMethod"/></td>
				<%-- 	<td class="headerLabel"><bean:message key="prompt.contactReason"/></td>
					<td class="headerData"><bean:write name="superviseeInfoHeaderForm" property="contactReason"/></td> --%>
				</tr>
				
				<logic:present name="hideQuickLinks">
					<logic:notEqual name="hideQuickLinks" value="true">
						<tr id="addressRow">
							<td class="headerLabel"><bean:message key="prompt.address"/></td>
							<td colspan="5" class="headerData"><bean:write name="superviseeInfoHeaderForm" property="address.streetNum"/> <bean:write name="superviseeInfoHeaderForm" property="address.streetName"/><logic:notEmpty  name="superviseeInfoHeaderForm" property="address.streetName"></logic:notEmpty> <logic:notEmpty  name="superviseeInfoHeaderForm" property="address.aptNum"> <bean:message key="prompt.aptSuite"/> <bean:write name="superviseeInfoHeaderForm" property="address.aptNum"/> </logic:notEmpty>
								<bean:write name="superviseeInfoHeaderForm" property="address.city"/><logic:notEmpty  name="superviseeInfoHeaderForm" property="address.city">, </logic:notEmpty><bean:write name="superviseeInfoHeaderForm" property="address.stateId"/> <bean:write name="superviseeInfoHeaderForm" property="address.zipCode"/> <logic:notEmpty  name="superviseeInfoHeaderForm" property="address.zipCode"> </logic:notEmpty><logic:notEmpty name="superviseeInfoHeaderForm" property="homePhone.formattedPhoneNumber">Home: <bean:write name="superviseeInfoHeaderForm" property="homePhone.formattedPhoneNumber"/> </logic:notEmpty>
							<%-- 	 <a href=# disabled>Update Address</a> --%>
							</td>
						</tr>
						<tr id="employmentRow">
							<td class="headerLabel"><bean:message key="prompt.employment"/></td>
							<td colspan="5" class="headerData"><bean:write name="superviseeInfoHeaderForm" property="employmentInfo.employerName"/><logic:notEmpty name="superviseeInfoHeaderForm" property="employmentInfo.phone.formattedPhoneNumber">, <bean:write name="superviseeInfoHeaderForm" property="employmentInfo.phone.formattedPhoneNumber"/></logic:notEmpty><logic:notEmpty name="superviseeInfoHeaderForm" property="employmentInfo.title">, <bean:write name="superviseeInfoHeaderForm" property="employmentInfo.title"/></logic:notEmpty> 
						<%--		 <a href=# disabled>Update Employment</a> --%>
							</td>
						</tr>
						<tr class="visible" id="casesRow">
							<td colspan="6">
								<table width="100%">
									<tr>
										<td class="formDeLabel" width="10%">
											<bean:message key="prompt.case"/> <bean:message key="prompt.number"/>
											<jims:sortResults beanName="superviseeInfoHeaderForm" results="cases" primaryPropSort="caseSupPeriodBeginDateAsStr" primarySortType="STRING" defaultSort="true" defaultSortOrder="DESC" sortId="1" hideMe="true"/>
										</td>
										<td class="formDeLabel" width="5%"><bean:message key="prompt.court"/></td>
										<td class="formDeLabel" width="32%"><bean:message key="prompt.superviseeName"/></td>
										<td class="formDeLabel" width="35%"><bean:message key="prompt.printedOffenseDescription"/></td>
										<td class="formDeLabel" width="18%"><bean:message key="prompt.caseBeginEndDate"/></td>
									</tr>
									<logic:iterate id="caseList" name="superviseeInfoHeaderForm" property="cases">
										<tr>
											<td class="headerData">
												<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="caseList" property="orderId"/>')">																																						
													<bean:write name="caseList" property="caseNum"/>
												</a>
											</td>
											<td class="headerData"><bean:write name="caseList" property="court"/></td>
											<td class="headerData"><bean:write name="caseList" property="superviseeName"/></td>
											<td class="headerData"><bean:write name="caseList" property="offense"/></td>
											<td class="headerData"><bean:write name="caseList" property="caseSupPeriodBeginDateAsStr"/>-<bean:write name="caseList" property="caseSupPeriodEndDateAsStr" /></td>
										</tr>
									</logic:iterate>
								</table>	
							</td>
						</tr>
					</logic:notEqual>
				</logic:present>
			</table>
			<div class="hr"></div>
		</td>
	</tr>
</table>
</body>
</html:html>