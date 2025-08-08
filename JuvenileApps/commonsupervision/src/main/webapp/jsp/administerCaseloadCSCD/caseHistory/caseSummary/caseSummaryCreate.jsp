<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/27/2008	 C Shimek - Create JSP -->
<!-- 01/26/2009  C Shimek - #52815 revised dateformat in status block -->
<!-- 05/24/2010  C Shimek - #65388 added secondaryAction = blank check to button table -->
<!-- 09/25/2013  R Capestani  - #76068 Format Summary of Court Actions -->
<!-- 10/03/2013 RCarter       - #76175 Add court actions update link for PRESENTED reports -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseSummmaryCreate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function printReport(){
	openWindow('handleCaseSummaryUpdates.do?submitAction=Print');
} 
//***Begin SHOW HIDE FOR SINGLE ROW Script 
function showHideComments(what,hide)
{
	if( ! hide )
	{
		document.getElementById( what ).className = 'hidden';
	}
  else 
  {
	 	document.getElementById( what ).className = 'visible';
	 	document.getElementById( what ).focus();
	}
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/handleCaseSummaryUpdates" target="content">
<!-- BEGIN BLUE TABS TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center"> 
	<tr>
		<td valign="top" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tabid" value="caseloadTab"/>
						</tiles:insert>
					</td>
				</tr>
			  	<tr>
					<td bgcolor="#6699FF" height="5"></td>
				</tr>  
			</table>
<!-- END BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
<!-- END SUPERVISEE INFORMATION TABLE  -->
								</td>
							</tr>		
						</table>
					</td>
				</tr>		
				<tr>
					<td valign="top" align="center"> 
<!-- BEGIN GREEN TABS TABLE -->				
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>						
							<tr>
								<td valign="top">
						 			<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
					   				 	<tiles:put name="tab" value="casesTab"/> 
						     		</tiles:insert>					
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66" height="5"></td> 
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->			
					</td>
				</tr>	
				<tr>
					<td valign="top" align="center"> 
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign="top" align="center">					
<!-- BEGIN HEADING TABLE -->
							 		<table width="100%">
										<tr>
											<td align="center" class="header">
		                                    	<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.CREATE%>">
		                                            <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.create"/>
		                                            <bean:message key="prompt.caseSummary"/>
		                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|7">
		                                        </logic:equal>
		                                        <logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
		                                            <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.update"/>
		                                            <bean:message key="prompt.caseSummary"/>
		                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|33">
		                                        </logic:equal>
		                                        <logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.MAINTAIN%>">
		                                            <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.maintain"/>
		                                            <bean:message key="prompt.caseSummary"/>
		                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|34">
		                                        </logic:equal>
		                                        <logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.DELETE%>">
		                                            <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.delete"/>
		                                            <bean:message key="prompt.caseSummary"/>
		                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|9">
		                                        </logic:equal>
		                                        <logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.VIEW%>">
		                                            <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.caseSummary"/>
													<bean:message key="title.details"/>
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|2">
												</logic:equal>			
											</td>
										</tr>
									</table>    
<!-- END HEADING TABLE -->
								</td>
							</tr>  
<!-- BEGIN ERROR -->
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
<!-- END ERROR -->
<!-- BEGIN CONFIRMATION TABLE -->
							<tr>
								<td align="center" class="confirm"><bean:write name="caseSummaryForm" property="confirmationMessage" /></td>
							</tr>
<!-- END CONFIRMATION TABLE -->
							<tr>
								<td align="center">
<!-- BEGIN STATUS TABLE -->
									<logic:notEqual name="caseSummaryForm" property="statusId" value="">
										<table border="0" width="99%" cellpadding="2" cellspacing="1" class="borderTable">
											<tr class="formDeLabel">
												<td><bean:message key="prompt.status" /></td>
												<td><bean:message key="prompt.status" /> <bean:message key="prompt.changed" /> <bean:message key="prompt.date" /></td>
												<td><bean:message key="prompt.createdBy" /></td>
												<td><bean:message key="prompt.createDate" /></td>
											</tr> 
									 		<tr>
												<td><bean:write name="caseSummaryForm" property="statusDesc"/></td>
												<td><bean:write name="caseSummaryForm" property="statusChangedDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
												<td><bean:write name="caseSummaryForm" property="createdByName"/></td>
												<td><bean:write name="caseSummaryForm" property="createDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>  
											</tr>
										</table>
										<bean:define id="hideBack" value="Y" />
									</logic:notEqual>	
<!-- END STATUS TABLE -->	
								</td>
							</tr>		 		
							<tr>
						 		<td height="5"></td>
						 	</tr> 
							<logic:equal name="caseSummaryForm" property="statusId" value="FL">
								<tr>
									<td align="center">
<!-- BEGIN FILING INFORMATION TABLE -->									
										<table width="99%" border="0" cellspacing="1" cellpadding="2" class="borderTable">
											<tr class="detailHead">
												<td class="detailHead" colspan="2"><bean:message key="prompt.filingInformation" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.filedDate" /></td>
												<td class="formDe">
													<bean:write name="caseSummaryForm" property="fileDateStr" formatKey="date.format.mmddyyyy"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.presentedBy" /></td>
												<td class="formDe"><bean:write name="caseSummaryForm" property="presentedByName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.whoSigned?" /></td>
												<td class="formDe"><bean:write name="caseSummaryForm" property="whoSignedName"/></td>
											</tr>
										</table>
									</td>
								</tr>	
						 		<tr>
						 			<td height="5"></td>
						 		</tr> 
<!-- END FILING INFORMATION TABLE -->												
							</logic:equal>  
							<tr>
								<td>
<!-- BEGIN DETAILS TABLE -->
									<tiles:insert page="caseSummaryElementsTile.jsp" flush="true">
									</tiles:insert>
<!-- END DETAILS TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->	
					</td>
				</tr>								
				<tr>
					<td align="center">				
<!-- BEGIN BUTTON TABLE -->
						<table width="98%" align="center" >
							<tr>
								<td height="5"></td>
							</tr>
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.VIEW%>">
								<tr>
									<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<logic:equal name="caseSummaryForm" property="allowMaintain" value="<%=UIConstants.YES%>">  
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.maintain" /></html:submit>
										</logic:equal>
										<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" />
										<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</logic:equal>	
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.DELETE%>">
								<tr>
									<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.delete" /></html:submit>
										<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" />
										<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</logic:equal>	
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.CREATE%>">
								<logic:notEqual name="caseSummaryForm" property="statusId" value="">
									<tr>
										<td align="center">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.caseSummaryList" /></html:submit>
										</td>
									</tr>
								</logic:notEqual>
								<tr>
									<td align="center">
										<logic:present name="hideBack">
											<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" />
										</logic:present>
										<logic:notPresent name="hideBack">
											<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
											<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" />
											<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
										</logic:notPresent>
									</td>
								</tr>
							</logic:equal>	
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.caseSummaryList" /></html:submit>
									</td>
								</tr>
								<tr>
									<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
										<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</logic:equal>	
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="<%=UIConstants.MAINTAIN%>">
								<logic:notEqual name="caseSummaryForm" property="statusId" value="FL" >	
									<tr>
										<td align="center">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.file/CourtActions" /></html:submit>
										</td>
									</tr>
								</logic:notEqual>
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
										<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="caseSummaryForm" property="secondaryAction" value="">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</logic:equal>
						</table>	
<!-- END BUTTON TABLE -->
					</td>
				</tr> 					
			</table>
<!-- END BLUE BORDER TABLE -->	
		</td>
	</tr>
</table>		
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>