<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@page import="naming.UIConstants"%>
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
<title><bean:message key="title.heading" /> - manageRecords/spnSplit.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="spnSplitForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
function checkDuplicates(aIncomingForm){
	if(aIncomingForm!=null){
		var errSpnVal=aIncomingForm["erroneousSpn.spn"].value;
		errSpnVal = pad(errSpnVal);
		var validSpnVal=aIncomingForm["validSpn.spn"].value;
		validSpnVal = pad(validSpnVal);
		var s1 = document.getElementById(errSpnVal);
		var s2 = document.getElementById(validSpnVal);
		if (s1 != null){
			aIncomingForm["erroneousSpn.spn"].value = errSpnVal;
			validSpnVal=aIncomingForm["validSpn.spn"].value = validSpnVal;
			alert("SPN value has pending split completion");
			aIncomingForm["erroneousSpn.spn"].focus();
			return false;
		}	
		if (s2 != null){
			aIncomingForm["erroneousSpn.spn"].value = errSpnVal;
			validSpnVal=aIncomingForm["validSpn.spn"].value = validSpnVal;
			alert("SPN value has pending split completion");
			aIncomingForm["validSpn.spn"].focus();
			return false;
		}
		if(errSpnVal!=validSpnVal){
			aIncomingForm["erroneousSpn.spn"].value = errSpnVal;
			validSpnVal=aIncomingForm["validSpn.spn"].value = validSpnVal;
			return true;
		}
	}
	alert("SPN values cannot be the same. Leading zeroes are ignored.");
	aIncomingForm["erroneousSpn.spn"].focus();
	return false;
}
function pad(aVal){
	while (aVal.length < 8){
		aVal = '0' + aVal;
	}
	return aVal;	
}
function setExceptionId(el, theForm){
	theForm.spnSplitExceptionId.value = el;
}
</script>
</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySpnSplitSelectSupPeriod" target="content" focus="erroneousSpn.spn">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|3">
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
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
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
								<td align="center" class="header"><bean:message key="prompt.spnSplit" /></td>						
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
									<li>Enter the Erroneous and Valid SPN's then Click button for desired process. </li>
								</ul></td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.requiredFields"/></td>												
							</tr>										
						</table>
					<!-- END INSTRUCTION TABLE -->																					
					<!-- BEGIN SPN TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr>
								<td class=detailHead colspan="2"><bean:message key="prompt.spnSplit" />&nbsp;<bean:message key="prompt.information"/></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding=2 cellspacing=1 border="0">		
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.erroneousSPN" /></td>
											<td class="formDe" ><html:text property="erroneousSpn.spn" maxlength="8" size="8"/></td>
										</tr>										 
										<tr>
											<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.2.diamond"/><bean:message key="prompt.validSPN" /></td>
											<td class="formDe"><html:text property="validSpn.spn" maxlength="8" size="8"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return (validateSpnSplitForm(this.form) && checkDuplicates(this.form) && disableSubmit(this, this.form));">
										<bean:message key="button.superviseeSplit" />
									</html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return (validateSpnSplitForm(this.form) && checkDuplicates(this.form) && disableSubmit(this, this.form));">
										<bean:message key="button.caseSplit" />
									</html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.reset"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
					<!-- END BUTTON TABLE -->
						
			<BR><BR><BR><BR>
			<input type="hidden" name="spnSplitExceptionId" value=""/>	
				
			<table cellpadding='2' cellspacing='1' width='98%' align="center">	  		 
     		<logic:notEmpty name="spnSplitForm" property="spnSplitErrors">
	  		  <tr class="formDeLabel">
	    			<td width='1%' nowrap>Error Spn</td>
	    			<td>Valid Spn</td>			
	    			<td>Error Message</td>
	    			<td></td>						
	  		  </tr>
      	     <logic:iterate indexId="index" id="spnIndex" name="spnSplitForm" property="spnSplitErrors" >
      			<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
    	        	<td valign="top" id=<bean:write name="spnIndex" property="erroneousSpn"/> ><bean:write name="spnIndex" property="erroneousSpn"/></td>
      				<td valign="top" id=<bean:write name="spnIndex" property="validSpn"/>><bean:write name="spnIndex" property="validSpn"/></td>
      				<td valign="top"><bean:write name="spnIndex" property="msg"/></td>
      				<td>
      				<input type="submit" name="submitAction" value="<bean:message key="button.continue" />" onclick="return setExceptionId(<bean:write name="spnIndex" property="errorResponseId"/>, this.form)"/>
      				</td>
      			</tr>
    			</logic:iterate>
     		</logic:notEmpty>
  		    </table>				
						
						
					<!-- END SPN TABLE -->
					</td>
				</tr>
			</table>
			<br>			
		
		</td>
	</tr>
</table>
</div>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>