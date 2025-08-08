<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 03/12/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 05/20/2010 RYoung & CShimek  - 65546 revised jsp to be cleaner added date validation ( removed from action) -->


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/closeCase/closeCase.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script type="text/javascript">

function validateFields(theForm)
{
     var noOfCases = document.getElementsByName("numberOfCases")[0].value;
     var fldName = "";
     var fldValue = "";
     var msg = "";   
     var caseId = "";
     var caseNum = "";
     var result = "";
     var inactivateDateId = "";
     for(var i = 0; i < noOfCases; i++)
     {
           caseId = "caseNumber" + i;
        caseNum = document.getElementById(caseId).value;
        inactivateDateId = "closeCasesList[" + i + "].terminationDateStr"; 
        fldValue = document.getElementById(inactivateDateId).value;
           fldName = "Inactivation Date for Case# " + caseNum;
           result = validateDate(fldValue, fldName, true);
           if (result != "") {
                if (msg == ""){
                     document.getElementById(inactivateDateId).focus();
                }    
                msg += result;
           }               
     }
     if (msg == ""){
           return true;
     }          
     alert(msg);  
     return false;
}

function validateDate(fldValue, fldName, futureDateEdit)
{
      var msg = "";
      var numericRegExp = /^[0-9]*$/;
      if (fldValue == "")
      {
           msg = fldName + " is required.\n";
           return msg;
      }
      if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
      {
           msg = fldName + " must be in the MM/DD/YYYY format.\n";
           return msg;
      }
      var dValues = fldValue.split('/');
      var month = dValues[0];
      var day = dValues[1];
      var year = dValues[2];

      if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
           msg = fldName + " is not a valid date.\n";
           return msg;     
      } 

      if (month.length != 2 || day.length != 2 || year.length != 4) {
           msg = fldName + " must be in the MM/DD/YYYY format.\n";
           return msg;     
      } 

	  if (month < 1 || month > 12)
	  {
	           msg = fldName + " is not valid.\n";
	           return msg;          
	  }
	  if (day < 1 || day > 31) {
	           msg = fldName + " is not valid.\n";
	           return msg;          
	  }
	  if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
	  {
	           msg = fldName + " is not valid.\n";
	           return msg;     
	  }
	  if (month == 2) {
	        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	        if (day > 29 || (day == 29 && !leap)) {
	                msg = fldName + " is not valid.\n";
	                return msg;     
	        }
	  }    
    if (futureDateEdit && msg == ""){
           var dt = fldValue + " 00:00";
           var fldDateTime = new Date(dt);
           var curDateTime = new Date();
           if (fldDateTime > curDateTime){
                msg = fldName + " can not be future value.\n";
                return msg;                     
           }    
    }
     return msg;
}

function doThis(){
	for (x=0; x< document.forms[0].length; x++){
		if (document.forms[0].elements[x].type == 'text'){
			document.forms[0].elements[x].focus();
			break; 
		}
	}
}
</script>
<SCRIPT LANGUAGE="JavaScript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</SCRIPT>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="doThis();">
<html:form action="/displayCloseCase" target="content" >
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/HC_Case/Close_Supervision_HC_Case.htm#|2">
<div align="center">
<!-- BEGIN MAIN TABLE -->	
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
		<!-- BEGIN BLUE TABS TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--blue tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
						<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
		<!-- END BLUE TABS TABLE -->	
		<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.closeCase"/></td>
							</tr>
						</table>
					<!-- END HEADING TABLE -->
					
					<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
					<!-- END ERROR TABLE -->
						
						<!-- BEGIN INSTRUCTION TABLE -->
						<div class="instructions" align="left">
							<ul>
								<li>Enter Inactivation Date for each case and click Submit.</li>
							</ul>
						</div>
						<div class="spacer"></div>
						<div class="required" align="left"><bean:message key="prompt.3.diamond"/> Required field &nbsp;&nbsp;*All date fields must be in the format of mm/dd/yyyy.</div>
						<div class="spacer"></div>
						<!-- END INSTRUCTION TABLE -->
						
						<!--header start-->
						<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
						</tiles:insert>
						<!--header end-->
						<br>
						<!-- BEGIN CLOSE CASE TABLE -->
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<bean:message key="prompt.closeCase"/>&nbsp;<bean:message key="prompt.info"/>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<% int idxCounter=0; %>
										<tr class="formDeLabel">
											<td><bean:message key="prompt.case"/></td>
											<td><bean:message key="prompt.court"/></td>
											<td><bean:message key="prompt.3.diamond"/><bean:message key="prompt.inactivationDate"/></td>
										</tr>
									    <logic:iterate id="closeCasesList" name="caseAssignmentForm" property="closeCasesList" indexId="index">
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td>
													<bean:write name="closeCasesList" property="criminalCaseId"/>
													<input type="hidden" name="caseNumber<%out.print(idxCounter);%>" value="<bean:write name="closeCasesList" property="criminalCaseId"/>" />
												</td>
												<td><bean:write name="closeCasesList" property="courtId"/></td>
												<td class="formDe">
											<html:text name="closeCasesList" property="terminationDateStr" maxlength="10" size="10" indexed="true"/> 
												<a href="#" onClick="cal1.select(document.forms[0]['closeCasesList[' +  <%out.print(idxCounter);%> + '].terminationDateStr'],'anchor<%out.print(idxCounter);%>','MM/dd/yyyy'); return false;" )
														NAME="anchor<%out.print(idxCounter);%>" ID="anchor<%out.print(idxCounter);%>"><bean:message key="prompt.3.calendar"/></a> 
	               								</td>
											</tr>
											<% idxCounter++; %>
	                                      </logic:iterate> 
                                     <input type="hidden" name="numberOfCases" value="<%= idxCounter %>" />
									</table>
								</td>
							</tr>
						</table>
						<!-- BEGIN CLOSE CASE TABLE -->
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.submit" /></html:submit>
									<html:reset><bean:message key="button.reset" /></html:reset>
									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<!-- END BLUE BORDER TABLE -->
			<br>
		</td>
	</tr>
</table>
<!-- END MAIN TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
