<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose - Create JSP -->
<!-- 02/09/2007	 Dawn Gibler - Correct names in supervisee list -->
<!-- 02/12/2007  C. Shimek   - added feature check to compliance button  -->
<!-- 05/13/2008  L Deen		 - removed CSCD from page title  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.Features"%>
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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCasenotes/superviseeSearchResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayAdministerCasenotesSearchResults" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Casenotes/CSCD_Casenotes.htm#|2">
<!-- COMPLIANCE FEATURE -->
<jims:isAllowed requiredFeatures="<%=Features.CS_COMPLIANCE_ACCESS%>">
	<bean:define id="allowCompliance" value="Y" />
</jims:isAllowed>
<jims:isAllowed requiredFeatures="<%=Features.CSCD_ASSESS_ACCESS%>">
	<bean:define id="allowAssessments" value="Y" />
</jims:isAllowed>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="casenotesTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>							
									<td align="center" class="header">
										<bean:message key="prompt.casenotes" />&nbsp;<bean:message key="title.searchResults" />
									</td>						
								</tr>
							</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Click on Name to view Supervisee Details.</li>
										<li>Select a Supervisee  and click appropriate button.</li>
									</ul></td>
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->
						<logic:notEmpty name="superviseeSearchForm" property="searchResults">	
							<table>
								<tr>
									<td align="center">
										<bean:size id="searchResultsSize" name="superviseeSearchForm" property="searchResults"/>
										<b><bean:write name="searchResultsSize"/></b>&nbsp; supervisee(s) found in search results</td>            					
								</tr>
							</table>  
						<!-- BEGIN DETAIL TABLE -->	
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr class="detailHead">
									<td width="1%"></td>
									<td><bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.name" /></td>
									<td><bean:message key="prompt.officer" />&nbsp;<bean:message key="prompt.name" /></td>
									<td><bean:message key="prompt.CRT" /></td>		
									<td><bean:message key="prompt.SPN" /></td>								
									<td><bean:message key="prompt.CON" /></td>
									<td><bean:message key="prompt.dob" /></td>
									<td><bean:message key="prompt.race" /></td>
									<td><bean:message key="prompt.sex" /></td>
								</tr>
								<%int RecordCounter = 0;
								String bgcolor = "";%>  
								
									<logic:iterate id="searchResultsIndex" name="superviseeSearchForm" property="searchResults">
									<logic:equal name="RecordCounter" value="0">	
									<input name="supervisionPeriodId" value='<bean:write name="searchResultsIndex" property="supervisionPeriodId"/>'/>
									</logic:equal>
										<tr
										class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											<td width="1%" class="formDe"><input type="radio" name="selectedValue" value=<bean:write name="searchResultsIndex" property="spn"/> /></td>
											<td class="formDe">
											
											<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name="searchResultsIndex" property="superviseeId"/>">
											 <msp:formatter name="searchResultsIndex" property="superviseeName" format="L, F M"/></a></td> 	
												
											<td class="formDe"><msp:formatter name="searchResultsIndex" property="officerName" format="L,F"/></td>
											<td class="formDe"><bean:write name="searchResultsIndex" property="courts" /></td>						
											<td class="formDe"><bean:write name="searchResultsIndex" property="spn" /></td>
											<td class="formDe"><bean:write name="searchResultsIndex" property="con" /></td>
											<td class="formDe"><bean:write name="searchResultsIndex" property="dateOfBirthAsStr" formatKey="date.format.mmddyyyy" /></td>
											<td class="formDe"><bean:write name="searchResultsIndex" property="race" /></td>
											<td class="formDe"><bean:write name="searchResultsIndex" property="sex" /></td>							
										</tr>		
																							
									</logic:iterate>
										
							</table>	
								</logic:notEmpty> 					
						<!-- END DETAIL TABLE -->                      
							<br>
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
									<logic:notEqual name="superviseeSearchForm" property="supervisionPeriodAsBool" value="false">
										
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');"><bean:message key="button.viewCasenotes"/></html:submit>
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');"><bean:message key="button.createCasenote"/></html:submit>
								<%-- 		<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');" >Compliance</html:submit> --%>
										<logic:present name="allowAssessments">
											<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');" ><bean:message key="button.assessments"/></html:submit>	
										</logic:present>
										<logic:notPresent name="allowAssessments">
											<html:submit property="submitAction" disabled="true"><bean:message key="button.assessments"/></html:submit>													
										</logic:notPresent>
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');" disabled="true">Associated Tasks</html:submit>
										<br>
									</logic:notEqual>
									
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
										<logic:equal name="superviseeSearchForm" property="supervisionPeriodAsBool" value="false">
											<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.');"><bean:message key="button.submit"/></html:submit>
										</logic:equal>
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>																																		 			
									</td>
								</tr>										  	
							</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>