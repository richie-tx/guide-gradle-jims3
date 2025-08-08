<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/17/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<title><bean:message key="title.heading" /> - conditionSpecialSearchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script>
function showProperBtn(allBtnHide,isActive){
	
	if(allBtnHide==0){
		show('updateBtnSpan', '0', 'inline');
		show('deleteBtnSpan', '0', 'inline');
	}
	else{
		if(isActive==1){
			show('updateBtnSpan', '1', 'inline');
			show('deleteBtnSpan', '0', 'inline');
		}
		else{
			show('updateBtnSpan', '0', 'inline');
			show('deleteBtnSpan', '1', 'inline');
		}
	}
}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionConditionSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|3">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
				  	<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						
						<!-- BEGIN HEADING TABLE -->
							<table width=100%>
								<tr>
									<td align="center" class="header"><bean:message key="title.specialSupervisionCondition" />&nbsp;<bean:message key="title.searchResults" /></td>
								</tr>
							</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Click on hyperlink to View or select radio button and click Update to modify the selected condition.</li>
									</ul></td>
								</tr>
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN DETAIL TABLE -->									
							<logic:notEmpty name="supervisionConditionForm" property="conditionSearchResults">
							<div align="center">
								<bean:size id="conditionSearchResultsSize" name="supervisionConditionForm" property="conditionSearchResults"/>								
								<bean:write name="conditionSearchResultsSize"/> 
								<bean:message key="prompt.resultsFor" />
								<bean:message key="prompt.name" />:&nbsp;<bean:message key="prompt.special" />&nbsp;

								<logic:equal name="supervisionConditionForm" property="showArchived" value="true" >	
									<a href="/<msp:webapp/>addSupervisionConditionToArchive.do?submitAction=<bean:message key='prompt.hideArchived' />&showArchived=true"><bean:message key="prompt.showArchived" /></a>
								</logic:equal>
								<logic:equal name="supervisionConditionForm" property="showArchived" value="false" >	
									<a href="/<msp:webapp/>addSupervisionConditionToArchive.do?submitAction=<bean:message key='prompt.hideArchived' />&showArchived=false"><bean:message key="prompt.hideArchived" /></a>
								</logic:equal>
							</div>
							
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
								<tr class="detailHead">
									<td width=1%></td>
									<td><bean:message key="prompt.conditionName" /></td>
									<td width=5%><bean:message key="prompt.CDI" /></td>
									<td><bean:message key="prompt.case#" /></td>
									<td colspan=2><bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.name" /></td>
								</tr>
								<%
									int RecordCounter = 0; 
									String bgcolor = ""; 
									RecordCounter = 0; 
									bgcolor = ""; %>

								<logic:equal name="supervisionConditionForm" property="showArchived" value="true" >
									<logic:iterate id="conditionSearchResultsIndex" name="supervisionConditionForm" property="conditionSearchResults">
									  <logic:equal name="conditionSearchResultsIndex" property="archived" value="false" >	
									 
										<tr class= <% RecordCounter++;
											bgcolor = "alternateRow";                      
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
												out.print(bgcolor);  %>  >
												<bean:define id="btnScript"> 
												showProperBtn('1','<logic:notEmpty name="conditionSearchResultsIndex" property="caseNum">1')</logic:notEmpty><logic:empty name="conditionSearchResultsIndex" property="caseNum">0')</logic:empty>
												
												
												</bean:define>
											
											<td width=1%><html:radio idName="conditionSearchResultsIndex" property="conditionId" value="conditionId" onclick="<%=btnScript%>"/></td>
											<td>
												<a href="/<msp:webapp/>displaySupervisionConditionView.do?conditionId=<bean:write name="conditionSearchResultsIndex" property="conditionId"/>">
												<bean:write name="conditionSearchResultsIndex" property="name" /></a></td>
											<td><bean:write name="conditionSearchResultsIndex" property="cdi" /></td>
											<td><bean:write name="conditionSearchResultsIndex" property="caseNum" /></td>
											<td><bean:write name="conditionSearchResultsIndex" property="superviseeName" /></td>
											<td align=right><a href="/<msp:webapp/>addSupervisionConditionToArchive.do?submitAction=<bean:message key='prompt.archive' />&conditionId=<bean:write name="conditionSearchResultsIndex" property="conditionId"/>"><bean:message key="prompt.archive" /></a></td>
										</tr>
									  </logic:equal>
									</logic:iterate>
								</logic:equal>							

								<logic:equal name="supervisionConditionForm" property="showArchived" value="false" >
									<logic:iterate id="conditionSearchResultsIndex" name="supervisionConditionForm" property="conditionSearchResults">
										<tr class= <% RecordCounter++;
											bgcolor = "alternateRow";                      
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
												out.print(bgcolor);  %>  >
												<bean:define id="btnScript"> 
												showProperBtn('1','<logic:notEmpty name="conditionSearchResultsIndex" property="caseNum">1')</logic:notEmpty><logic:empty name="conditionSearchResultsIndex" property="caseNum">0')</logic:empty>
												
												
												</bean:define>
												
												<td width=1%><html:radio idName="conditionSearchResultsIndex" property="conditionId" value="conditionId" onclick="<%=btnScript%>"/></td>
											<td>
												<a href="/<msp:webapp/>displaySupervisionConditionView.do?conditionId=<bean:write name="conditionSearchResultsIndex" property="conditionId"/>">
												<bean:write name="conditionSearchResultsIndex" property="name" /></a></td>
											<td><bean:write name="conditionSearchResultsIndex" property="cdi" /></td>
											<td><bean:write name="conditionSearchResultsIndex" property="caseNum" /></td>
											<td><bean:write name="conditionSearchResultsIndex" property="superviseeName" /></td>
											<logic:equal name="conditionSearchResultsIndex" property="archived" value="true" >	
												<td align=right><bean:message key="prompt.archived" /></td>
											</logic:equal>
											<logic:equal name="conditionSearchResultsIndex" property="archived" value="false" >	
												<td align=right><a href="/<msp:webapp/>addSupervisionConditionToArchive.do?submitAction=<bean:message key='prompt.archive' />&conditionId=<bean:write name="conditionSearchResultsIndex" property="conditionId"/>"><bean:message key="prompt.archive" /></a></td>
											</logic:equal>
										</tr>
									</logic:iterate>
								</logic:equal>
							</table>				
							</logic:notEmpty>
						<!-- END DETAIL TABLE -->
							<br>	
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>		
										<div id="updateBtnSpan" class="hidden">
											<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
												<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
											</jims2:isAllowed>
										</div>
										<div id="deleteBtnSpan" class="hidden">
											<jims2:isAllowed requiredFeatures="CS-COND-DELETE">
												<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
											</jims2:isAllowed>
										</div>
										<html:reset onclick="showProperBtn('0','0')"><bean:message key="button.reset"></bean:message></html:reset>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
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

<!-- END  TABLE -->
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
									
