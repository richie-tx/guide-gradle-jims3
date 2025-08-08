<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 01/28/2011 D Williamson Create JSP  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
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
<title><bean:message key="title.heading"/> - educationCharterPostReleaseCreate.jsp</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>


<script type="text/javascript" >
$(document).ready(function () {
	//RELEASE DATE CALENDAR.
	if(typeof  $("#postReleaseStatusDate") != "undefined"){
			datePickerSingle($("#postReleaseStatusDate"),"Post Release Status Date",false);
	}
	
	// Limit text on keyup and mouseout
	$("#otherText").on('keyup mouseout',function(){
		textLimit($(this),144);
		return false;
	});
});

function onloadForm()
{
 	showComments();
}


function checkOther()
{
  var last = document.educationCharterDetailsForm.continuingEducation.options.length ;

  if( document.educationCharterDetailsForm.continuingEducation.options[ (last -1) ].selected )
	{
       show( 'otherText', 1, 'row' ) ;
	}
  else
	{
      show( 'otherText', 0, 'row' ) ;
	}
}

function showComments(){
	var condEdSelects = document.getElementsByName("selectedIds"); 
	var otherSelected = false;
	for (x=0; x<condEdSelects.length; x++)
	{	
		if( condEdSelects[x].checked == true && condEdSelects[x].value == "OTHR")
		{
			otherSelected = true;
			break;
		} 		
	}
	if (otherSelected == true)
	{
		$("#otherText1").show();
		$("#otherText2").show();
	} else {
		$("#otherText1").hide();
		$("#otherText2").hide();
	}
}
function validateInput()
{
	var msg = "";
	var otherComment = "";
	var otherSelected = false;
	var condEdSelectCtr = 0;
	var condEdSelects = document.getElementsByName("selectedIds"); 
	var dateStr = Trim(document.educationCharterDetailsForm.postReleaseStatusDateStr.value);
	document.educationCharterDetailsForm.postReleaseStatusDateStr.value = dateStr;	
	if (dateStr == "")
	{
		msg = "Post Release Status Date is required.\n";
	}
	for (x=0; x< condEdSelects.length; x++)
	{
		if (condEdSelects[x].checked == true){
			condEdSelectCtr++;
			if (condEdSelects[x].value == "OTHR")
			{
				otherComment = Trim(document.forms[0].otherText.value);
				otherSelected = true;
			}
		}				
	}
	if (document.forms[0].postReleaseEmployedCodeId.selectedIndex == 0 && condEdSelectCtr == 0)
	{
		if (msg == "")
		{
			document.forms[0].postReleaseEmployedCodeId.focus();
		}	
		msg += "Either Employed or Continuing Education selection is required.";
	}
	if (condEdSelectCtr > 2)
	{
		msg += "Maximum of 2 Continuing Education selections allowed.\n";
	}
	if (otherSelected == true && otherComment == "")
	{
		msg += "Comments required if OTHER is selected in Continuing Education.\n"
	}		
	if (msg > "")
	{
		alert(msg);
		return false;
	}
	return true;			
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onLoad="onloadForm();">
<html:form action="displayPostReleaseCreateSummary" target="content">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.postReleaseTracking"/>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter information as appropriate.</li>
                <li>Enter date and either employment or continuing education must be entered.
                    You can also enter both fields.</li>
				<li>Select Next button to view Summary information.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">&nbsp;Required Fields&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!--juv profile header start-->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<!--juv profile header end-->
<!-- BEGIN DETAIL TABLE -->
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<!-- BEGIN GREEN TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
          <!--tabs start-->
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
				            <tiles:put name="tabid" value="interviewinfotab"/>
				            <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			            </tiles:insert>
          <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<!-- BEGIN ALIGNMENT TABLE -->						
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
<!-- BEGIN BLUE TABS TABLE -->								
									<table width="100%" border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
  										<!--tabs start-->
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										        <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										        <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									        </tiles:insert>
  										<!--tabs end-->
											</td>
										</tr>
										<tr>
											<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->		
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
										<td valign="top" align="center">
											<div class="spacer"></div>
<!-- BEGIN RED TABS TABLE -->												
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<!--tabs start-->
														<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
												           <tiles:put name="tabid" value="charter" />
												           <tiles:put name="juvnum"	value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											           </tiles:insert>
														<!--tabs end-->
													</td>
												</tr>
												<tr>
													<td bgcolor="#ff6666"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
												</tr>
											</table>	
<!-- END RED TABS TABLE -->	
<!-- BEGIN RED BORDER TABLE -->												
											<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
												<tr>
													<td valign="top" align="center">
														<div class="spacer"></div>												
<!-- BEGIN PR TRACKING TABLE -->
															<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td valign="top" class="detailHead"><bean:message key="title.postReleaseTracking"/>&nbsp;<bean:message key="prompt.information"/></td>
																</tr>
																<tr>
																	<td>
<!-- BEGIN PR TRACKING INPUT TABLE -->				
																		<table border="0" cellpadding="2" cellspacing="1" width="100%" >
																			<tr>
																				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.postReleaseStatusDate"/></td>
																				<td class="formDe" nowrap>
																					<html:text name="educationCharterDetailsForm" property="postReleaseStatusDateStr" size="10" maxlength="10" readonly="true" title="Calendar must be used for date." styleId="postReleaseStatusDate"/>
																				</td> 
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.employed"/></td>
																				<td class='formDe'>
																					<html:select property="postReleaseEmployedCodeId" >
				  												              			<html:option value=""><bean:message key="select.generic"/></html:option>
				  												             			<html:optionsCollection property="postReleaseEmployedCodeList" value="code" label="description"/>
				  												        			</html:select>  
																				</td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.continuingEducation"/></td>
																				<td class="'formDe">
<!-- BEGIN CONTINUING EDUCATION SELECT TABLE -->																				
																					<table width="100%" cellspacing="0" cellpadding="4">
																						<logic:iterate id="selIter" name="educationCharterDetailsForm" property="selectedFromList" indexId="index">
				          																	<tr>
				                																<td class="formDe" width="1%">
				                																	<input type="checkbox" name="selectedIds" value="<bean:write name='selIter' property='code'/>" indexId="index" onclick="showComments()">
				                																</td>
				                																<td class="formDe"><bean:write name="selIter" property="description"/></td>
				          																	</tr>
				     																	</logic:iterate> 
			     																	</table>
																				</td>
																			</tr>
																			<tr id="otherText1" class="hidden">	
																				<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.comments"/>&nbsp;(Max. characters allowed: 144)</td>
																			</tr>
																			<tr id="otherText2" class="hidden">	
																				<td class="formDe" colspan="2">
																					<html:textarea name="educationCharterDetailsForm" property="otherText" styleId="otherText" style="width:100%" rows="2"></html:textarea>
																				</td>
																			</tr>
																		</table>
<!-- END PR TRACKING INPUT TABLE -->
																	</td>
																</tr>
															</table>
<!-- END PR TRACKING INFO TABLE -->
															<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
															            <html:submit property="submitAction" onclick="return validateInput()"><bean:message key="button.next" /></html:submit>
															            <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																	</td>
																</tr>
															</table>
<!-- END BUTTON TABLE -->
															<div class="spacer"></div>
														</td>
													</tr>
												</table>
<!-- END RED BORDER TABLE -->													
												<div class="spacer"></div>
											</td>
										</tr>
									</table>
<!-- END BLUE BORDER TABLE -->											
								</td>
							</tr>
						</table>
<!-- END ALIGNMENT TABLE -->							
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<!-- END GREEN BORDER TABLE -->				
		</td>
	</tr>
</table>
<br>
<!-- END DETAIL TABLE -->
</html:form>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>