<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/30/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- parentalRights.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>


<script type='text/javascript'>
function enableDropDown(myBox)
{
	var dropDown = myBox.form["guardians["+myBox.id+"].explanationMethod"];
	if(dropDown)
	{
		if(myBox.checked)
		{
			dropDown.disabled = false;
		}
		else
		{
			dropDown.disabled = true;
			dropDown.value = "";
		}
	}
}

function runScript()
{
	var count = 0;
	while(true)
	{
		var checkBoxName = "guardians["+count+"].selected";
		var checkBox = document.forms[0][checkBoxName];
		
		if(checkBox)
		{
			enableDropDown(checkBox);
		}
		else
			break;
			
		count++ ;	
	}
}

function customValidation()
{
	var count = 0;	
	var aCheckBoxSelected = false;
	
	while(true)
	{
		var checkBoxName = "guardians["+count+"].selected";
		var checkBox = document.forms[0][checkBoxName];
		var dropDown = document.forms[0]["guardians["+count+"].explanationMethod"];
		
		if(checkBox)
		{
			if(checkBox.checked == true)
				aCheckBoxSelected = true;
				
			if(checkBox.checked == true && dropDown.value == "")
			{
				alert("An explanation method must be selected for every chosen guardian.");
				return false;
			}
		}
		else
			break;
		
		count++ ;	
	}
	
	if(aCheckBoxSelected == false)
	{
		alert("A guardian must be selected in order to continue.");
		return false;
	}
	
	return true;
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
} 

</script>
</head>

 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="runScript()"> 
<html:form action="/displayParentalRights" target="content">

<logic:notEqual name="state" value="summary">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|130">
</logic:notEqual>
<logic:equal name="state" value="summary">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|275">
</logic:equal>   
      

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 		
		<td align="center" class="header"><bean:message key="title.mjcwConductInterview"/> - Parental Rights
 			<logic:equal name="state" value="summary"> Summary</logic:equal>	
		</td>
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 
<table width='98%' align='center'>							
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>		
</table>

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
	<logic:notEqual name="state" value="summary">	
		<tr id="formInstr">
			<td> 
				<ul> 
					<li>Select which guardian(s) to be notified, and choose the explanation method.</li>
					<li>Enter victim injury comments.</li>
					<li>Click Next button to continue.</li>
				</ul>
			</td>
		</tr> 
	</logic:notEqual>
	<logic:equal name="state" value="summary">	
		<tr id="sumInstr">
			<td>
				<ul>
					<li>Review the information, then select the Generate Parental Rights Form to generate the form.</li>
					<li>Select Back button to return to previous page.</li>
				</ul>
			</td>
		</tr>
	</logic:equal>
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<%-- BEGIN TAB --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>	
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>	

			<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign='top' align='center'>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign='top'>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
            				<td bgcolor='#33cc66'>
            					<table border='0' cellpadding='2' cellspacing='1'>
            						<tr>
            							<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message key="prompt.viewInterviews"/></a> <b>|</b> </td>
            							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a> </td>
            						</tr>
            					</table>
              			</td>
            	    </tr>
							  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'>

      		  					<%-- BEGIN Today's Interview TABLE --%>
											<div class='spacer'></div>
      		  					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      								<tr>
      									<td class='detailHead'>Juvenile's Guardian(s)</td>
      								</tr>
      								<tr>
      									<td>

      										<logic:equal name="state" value="summary">	
      											<table width='100%' cellpadding='4' cellspacing='1'>
      												<tr class='formDeLabel'>
      													<td valign='top' nowrap='nowrap'>Member #</td>
      													<td valign='top' nowrap='nowrap'>Name</td>
      													<td valign='top' nowrap='nowrap'>Relationship</td>
      													<td valign='top' nowrap='nowrap'>Explanation Method</td>
      												</tr>
      												<logic:empty name="juvenileInterviewForm" property="guardians">
      													<tr>
      														<td colspan="2">No guardian available.</td>
      													</tr>
      												</logic:empty>
      												<nested:iterate indexId='indexer' id="guardiansIter" name="juvenileInterviewForm" property="guardians">													
      													<nested:equal property="selected" value="true">
      														<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
      															<td valign='top'><nested:write property="familyConstellationMemberNum" /></td>
      															<td valign='top'><nested:write property="memberName" /></td>
      															<td valign='top'><nested:write property="relationshipToJuv" /></td>
      															<td valign='top'><nested:write property="explanationMethod"/></td>
      														</tr>
      													</nested:equal>
      												</nested:iterate>
      											</table>
      										</logic:equal>
      										<logic:notEqual name="state" value="summary">
      											<table width='100%' cellpadding='4' cellspacing='1'>
      												<tr class='formDeLabel'>
      													<td class="subhead" valign='top' width='1%' nowrap='nowrap'></td>
      													<td valign='top' nowrap='nowrap'>Member #</td>
      													<td valign='top' nowrap='nowrap'>Name</td>
      													<td valign='top' nowrap='nowrap'>Relationship</td>
      													<td valign='top' nowrap='nowrap'>Explanation Method</td>
      												</tr>
      												<logic:empty name="juvenileInterviewForm" property="guardians">
      													<tr>
      														<td colspan="2">No guardian available.</td>
      													</tr>
      												</logic:empty>
      												<logic:notEmpty name="juvenileInterviewForm" property="guardians">
      													<nested:iterate indexId='indexer' id="guardiansIter" name="juvenileInterviewForm" property="guardians">
      														<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
      															<td valign='top'>
      																<nested:checkbox property="selected" onclick="enableDropDown(this);" styleId="<%=Integer.toString(indexer.intValue())%>"/>
      															</td>
      															<td valign='top'><nested:write property="familyConstellationMemberNum" /></td>
      															<td valign='top'><nested:write property="memberName" /></td>
      															<td valign='top'><nested:write property="relationshipToJuv" /></td>
      															<td valign='top'>
      																<nested:select property="explanationMethod" disabled="true">
      																	<html:option value="">
      																		<bean:message key="select.generic" />
      																	</html:option>
      																	<html:optionsCollection name="juvenileInterviewForm"
      																		property="explanationMethodList" value="description" label="description" />
      																</nested:select>
      															</td>
      														</tr>
      													</nested:iterate>
      												</logic:notEmpty>	
      												
      											</table>
      											<input type="hidden" name="resetGuardians" value="true">
      										</logic:notEqual>
      									</td>
      								</tr>
      							</table>
		  					<%-- END Interview TABLE --%>
		  				</td>
		  			</tr>

					<tr>
						<td>
						  <div class='spacer'></div>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGrey" align='center'>
								<tr>
									<td align='center'>
								    	
										<table border="0" cellpadding="4" cellspacing="1" width='100%'>
											<tr>
												<td class='formDeLabel' valign='top' nowrap='nowrap' colspan='2'>Victim's physical injuries comments
												
    											<logic:notEqual name="state" value="summary">
  												  &nbsp;
			                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                  						<tiles:put name="tTextField" value="victimsPhysicalInjuries"/>
			                  						<tiles:put name="tSpellCount" value="spellBtn1" />
			                						</tiles:insert>  
     												(Max. characters allowed: 255)
    											</logic:notEqual>
              									</td>
											</tr>
											<logic:equal name="state" value="summary">
												<tr>
													<td class='formDe' colspan='2'>
														<bean:write name="juvenileInterviewForm" property="victimsPhysicalInjuries"/>
													</td>
												</tr>
											</logic:equal>
											<logic:notEqual name="state" value="summary">
												<tr>
													<td class='formDe' colspan='2'>
														<html:textarea name="juvenileInterviewForm" property="victimsPhysicalInjuries" style="width:100%" rows="3" onkeydown="textCounter(victimsPhysicalInjuries,255)" onkeyup="textCounter(victimsPhysicalInjuries,255)"/>
													</td>
												</tr>
											</logic:notEqual>
											
										</table>
									</td>
								</tr>
							</table>

						  <div class='spacer'></div>
							<table align='center'>
       					<tr>
       						<td valign='top' align='center'>
       							<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>		
       							<logic:notEqual name="state" value="summary">
       								<html:submit property="submitAction" onclick="return (customValidation() && disableSubmit(this, this.form))"><bean:message key="button.next"/></html:submit>
       							</logic:notEqual>
       							<logic:equal name="state" value="summary">
       								<html:submit property="submitAction"><bean:message key="button.generateParentalRightsForm"/></html:submit>
       							</logic:equal>
       							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>

		     						 </td>
		                 </tr>
		               </table>
		               <div class='spacer'></div>
		                </td>
		                </tr>
		              </table>
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>


</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
