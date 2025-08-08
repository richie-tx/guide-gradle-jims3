<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/12/2006	 Hien Rodriguez - Create JSP -->
<!-- 10/18/2006	 Hien Rodriguez - Remove OR and use hide/show javascript. -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->
<!-- 06/25/2008  LDeen			 Defect#52571 remove back & cancel buttons -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
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
<title>outOfCountyCase/search.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="outOfCountyCaseSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
function renderSearch(theSelect){
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	switch (theSelectValue)
	{
		case "SPN":
			document.getElementsByName("caseNum")[0].value="";
			show("spnSearchHeader",1,"row");
			show("spnSearchSPN",1,"row");
			show("caseSearchHeader",0,"row");
			show("caseSearchCaseNum",0,"row");
			document.getElementsByName("spn")[0].focus();			
		break
	
		case "CASE":
			document.getElementsByName("spn")[0].value="";
			show("spnSearchHeader",0,"row");
			show("spnSearchSPN",0,"row");
			show("caseSearchHeader",1,"row");
			show("caseSearchCaseNum",1,"row");
			document.getElementsByName("caseNum")[0].focus();			
		break
	}
}

function validateSearch(theForm)
{	
	var theSelect=theForm.searchBy;
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;

	if (theSelectValue=="SPN" && theForm.spn.value == "")
	{		
     	alert("SPN is required for search.");
     	theForm.spn.focus();
     	return false;
   	}
	if (theSelectValue=="CASE" && theForm.caseNum.value == "")
	{		
     	alert("Case number is required for search.");
     	theForm.caseNum.focus();
     	return false;
   	}
   	return true;
}
</script> 

</head>
<body topmargin="0" leftmargin="0" onload="renderSearch(document.outOfCountyCaseSearchForm.searchBy)" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayOutOfCountyCaseSearchResults" target="content" focus="spn">
<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|1">
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
						<tiles:put name="tab" value="setupTab"/>
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="oocTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
								<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>							
							    			<td align="center" class="header">
												<bean:message key="title.outOfCountyCase" />&nbsp;-&nbsp;<bean:message key="title.search" />
											</td>						
						  				</tr>
									</table>
								<!-- END HEADING TABLE -->
								<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors/></td>
										</tr>		
									</table>
								<!-- END ERROR TABLE -->
								<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td><ul>
												<li>Select Search by to change the search type.</li>
												<li>Enter the required field(s) and click Submit to see results.</li>
											</ul></td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.requiredFields"/></td>												
										</tr>										
									</table>
								<!-- END INSTRUCTION TABLE -->
								<!-- BEGIN SELECT SEARCH TABLE -->
									<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.searchBy"/></td>
											<td class="formDe">
												<select name="searchBy" onchange="renderSearch(this)">
													<option value="CASE" >CASE</option>
													<option value="SPN" selected="selected">SPN</option>
												</select>
											</td>
										</tr>
									</table>
									<br>
					                <table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
					                	<tr id="spnSearchHeader" >
											<td class="detailHead" colspan="2"><bean:message key="prompt.searchBySPN" />&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>displayOutOfCountyCaseSearchResults.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch"/></a></td>
											</tr>
										<tr id="caseSearchHeader">
											<td class="detailHead" colspan="2"><bean:message key="prompt.searchByCase" /></td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
									                <tr id="spnSearchSPN">
									                   	<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.SPN" /></td>
									                    <td class="formDe"><html:text property="spn" maxlength="8" size="8"/></td>
									                </tr>
								                  	<tr id="caseSearchCaseNum">
								                    	<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.case#" /></td>
														<td class="formDe"><html:text property="caseNum" maxlength="12" size="12"/></td>
													</tr>
												</table>
											</td>
										</tr>
									 </table>
						 		<!-- END SELECT SEARCH TABLE -->	
									<br>
								<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return (validateSearch(this.form) && validateOutOfCountyCaseSearchForm(this.form) && disableSubmit(this, this.form));">
													<bean:message key="button.submit"></bean:message></html:submit>&nbsp;
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.refresh"></bean:message></html:submit>&nbsp;
												 </td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
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