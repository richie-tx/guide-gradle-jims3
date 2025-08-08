<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/18/2006  C Shimek      - per defect 27486, revised Condition Name to Court Policy Name in list header -->
<!-- 01/18/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to copy, update and delete buttons -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around buttons -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - courtPolicySearchResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="courtPolicyTitle" name="courtPolicyForm" property="courtPolicyTitle" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" name="courtPolicyForm" property="courtGroup1Caption" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" name="courtPolicyForm" property="courtGroup2Caption" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" name="courtPolicyForm" property="courtGroup3Caption" type="java.lang.String"/>

<bean:define id="btnsEnabled" value="false" type="java.lang.String"/>
<jims2:isAllowed requiredFeatures="CS-CPOL-COPY">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
<jims2:isAllowed requiredFeatures="CS-CPOL-UPDATE">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
	
<jims2:isAllowed requiredFeatures="CS-CPOL-DELETE">
<% btnsEnabled="true"; %>
</jims2:isAllowed>


<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleCourtPolicySelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|49">
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
							<td align="center" class="header">
								<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.searchResults" />
							</td>
						  </tr>
						</table>
						<!-- END HEADING TABLE -->
						 <!-- BEGIN ERROR TABLE -->
							    <table width=98% align="center">							
								    <tr>
									    <td align="center" class="errorAlert"><html:errors></html:errors></td>
								    </tr>		
							    </table>
						    <!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul>
									<li>Click on hyperlink to View </li>
									<logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
									<li>Or select radio button and click appropriate button.</li>
									</logic:notEqual>
								</ul></td>
						  </tr>
						</table>
						<!-- BEGIN  TABLE -->
						<logic:empty name="courtPolicyForm" property="courtPolicySearchResults">
							<bean:message key="error.noRecords" />
						</logic:empty>
						
						<logic:notEmpty name="courtPolicyForm" property="courtPolicySearchResults">
							<div align="center">
								<bean:size id="courtPolicySearchResultsSize" name="courtPolicyForm" property="courtPolicySearchResults"/>
								<bean:write name="courtPolicySearchResultsSize"/> 
								<bean:message key="prompt.resultsFor" />&nbsp;
								<bean:message key="<%=courtGroup1Caption%>" />: 
								<logic:notEmpty name="courtPolicyForm" property="group1Name">
									<logic:notEqual name="courtPolicyForm" property="group1Name" value="">
										<bean:write	name="courtPolicyForm" property="group1Name" />&nbsp;
									</logic:notEqual>
									<logic:equal name="courtPolicyForm" property="group1Name" value="">>
										All &nbsp;
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="courtPolicyForm" property="group1Name">
									All &nbsp;
								</logic:empty>	
								
								
						<logic:notEmpty name="courtPolicyForm" property="group2Name">
							<bean:message key="<%=courtGroup2Caption%>" />: <bean:write
								name="courtPolicyForm" property="group2Name" />&nbsp;</logic:notEmpty>
						<logic:notEmpty name="courtPolicyForm" property="group3Name">
							<bean:message key="<%=courtGroup3Caption%>" />: <bean:write
								name="courtPolicyForm" property="group3Name" />&nbsp;</logic:notEmpty>
						<%-- <logic:equal name="courtPolicyForm"
							property="allCourtsSelected" value="true">
							<bean:message key="prompt.courtsAll" />
						</logic:equal>
						<logic:notEqual name="courtPolicyForm"
							property="allCourtsSelected" value="true">Selected Courts</logic:notEqual>
							--%>
							</div>
					<%--/logic:notEmpty--%> 
							
						    
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
								<tr class="detailHead">
									<td width=1%></td>
									<td class=subHead><bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="prompt.name" /></td>
									<td class=subHead><bean:message key="<%=courtGroup1Caption%>"/></td>
									<td class=subHead><bean:message key="<%=courtGroup2Caption%>"/></td>
									<td class=subHead><bean:message key="<%=courtGroup3Caption%>"/></td>
								</tr>
								
								<%
								int RecordCounter = 0; 
								String bgcolor = ""; 
								RecordCounter = 0; 
								bgcolor = ""; %> 	
										
								<logic:iterate id="courtPolicySearchResultsIter" name="courtPolicyForm" property="courtPolicySearchResults">
									<tr class= <% RecordCounter++;
										bgcolor = "alternateRow";                      
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";
										out.print(bgcolor);  %>  >
										<td width=1%>
										<logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
										<logic:equal name="courtPolicyForm" property="onlyMatch" value="true">
											<input type="radio" name="policyId" value="<bean:write name="courtPolicySearchResultsIter" property="policyId"/>" checked>
										</logic:equal>	
										<logic:equal name="courtPolicyForm" property="onlyMatch" value="false">
											<html:radio property="policyId" idName="courtPolicySearchResultsIter"  value="policyId" />
										</logic:equal>	
										</logic:notEqual>

										</td>
										<td>
											<a href="/<msp:webapp/>displayCourtPolicyView.do?policyId=<bean:write name='courtPolicySearchResultsIter' property='policyId'/>">
											<bean:write name="courtPolicySearchResultsIter" property="name" /></a>
										</td>
										<td><bean:write name="courtPolicySearchResultsIter" property="group1Name" /></td>
										<td><bean:write name="courtPolicySearchResultsIter" property="group2Name" /></td>
										<td><bean:write name="courtPolicySearchResultsIter" property="group3Name" /></td>
									</tr>
								</logic:iterate>
							</table>
						</logic:notEmpty>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  <tr>
					    	<td align="center">
						      <input type="button" value="Back" name="return" onClick="history.go(-1)">
								<jims2:isAllowed requiredFeatures="CS-CPOL-COPY">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form));"><bean:message key="button.copy"/></html:submit>
								</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures="CS-CPOL-UPDATE">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form));"><bean:message key="button.update"/></html:submit>
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form));"><bean:message key="button.updateAssociations"/></html:submit>
								</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures="CS-CPOL-DELETE">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form));"><bean:message key="button.delete"/></html:submit>
								</jims2:isAllowed>
								<input type="button" value='<bean:message key="button.cancel" />' name="return" onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);">
					    	</td>
					  	</tr>
					  	<tr>
					  		<td align="center">

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

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
