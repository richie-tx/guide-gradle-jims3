<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/18/2006	 Hien Rodriguez - Remove OR and use hide/show javascript. -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/search.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
function renderSearch(theSelect){
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	switch (theSelectValue)
	{
		case "SPN":
			document.getElementsByName("caseNum")[0].value="";
			document.getElementsByName("cdi")[0].value="";
			show("spnSearchHeader",1,"row");
			show("spnSearchSPN",1,"row");
			show("caseSearchHeader",0,"row");
			show("caseSearchCaseNum",0,"row");
			show("caseSearchCDI",0,"row");
			document.getElementsByName("spn")[0].focus();
		break
	
		case "CASE":
			document.getElementsByName("spn")[0].value="";
			show("spnSearchHeader",0,"row");
			show("spnSearchSPN",0,"row");
			show("caseSearchHeader",1,"row");
			show("caseSearchCaseNum",1,"row");
			show("caseSearchCDI",1,"row");
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
	if (theSelectValue=="CASE" && theForm.cdi.value == "")
	{		
     	alert("CDI is required for search.");
     	theForm.cdi.focus();
     	return false;
   	}
	return true;
}
</script> 

</head>
<body topmargin=0 leftmargin="0" onload="renderSearch(document.supervisionOrderSearchForm.searchBy)" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderAdvancedSearch" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|1">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
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
					<td valign=top align=center>
						
							
						<!-- BEGIN HEADING TABLE -->
							<table width=100%>
								<tr>							
									<td align="center" class="header">
										<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.search" />
									</td>						
								</tr>
							</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
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
									<td class="required"><bean:message key="prompt.3.diamond"/>Indicates Required Fields</td>												
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->																					
						<!-- BEGIN SELECT SEARCH TABLE -->
							<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr>
									<td class=formDeLabel width=1% nowrap><bean:message key="prompt.searchBy"/></td>
									<td class=formDe>
										<select name=searchBy onchange="renderSearch(this)">
											<option value="SPN" selected><bean:message key="prompt.SPN"/></option>
											<option value="CASE" ><bean:message key="prompt.CASE"/></option>
										</select>
									</td>
								</tr>
							</table>
							<br>
			                <table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
			                	<tr id="spnSearchHeader" >
									<td class=detailHead colspan="2"><bean:message key="prompt.searchBySPN" />&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>displaySupervisionOrderAdvancedSearch.do?submitAction=<bean:message key="button.advancedSuperviseeSearch"/>"><bean:message key="button.advancedSuperviseeSearch"/></a></td>
								</tr>
								<tr id="caseSearchHeader">
									<td class=detailHead colspan="2"><bean:message key="prompt.searchByCase" /></td>
								</tr>
								<tr>
									<td>
										<table width=100% border="0" cellpadding="2" cellspacing="1">
							                <tr id="spnSearchSPN">
							                   	<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.SPN" /></td>
							                    <td class="formDe"><html:text property="spn" maxlength="8" size="8"/>
							                     </td>
							                  </tr>
						                  	<tr id="caseSearchCaseNum">
						                    	<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.case#" /></td>
												<td class="formDe"><html:text property="caseNum" maxlength="15" size="15"/></td>
											</tr>
						                  	<tr id="caseSearchCDI">
						                    	<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.CDI" /></td>
												<td class="formDe"><html:text property="cdi" maxlength="3" size="3"/></td>
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
										<html:submit property="submitAction" onclick="return validateSearch(this.form) && validateSupervisionOrderSearchForm(this.form) && disableSubmit(this, this.form);">
											<bean:message key="button.submit"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"/></html:submit>&nbsp;
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>