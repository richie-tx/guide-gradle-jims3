<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>




<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- presentOffense.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'>
$(document).ready(function(){	
	$("#generateDraftReport").click(function(e){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleSocialHistoryData.do?submitAction=Generate Draft Report&submitType=ajax',
	        type: 'post',
	        data: $('form#socialHistoryData').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         		if (isJson(data)) {
	         			alert((JSON.parse(data)).error);
	         		} 
	         	}
	        }
	    });
	})
	
	$("#generateFinalReport").click(function(e){
		e.preventDefault();
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleSocialHistoryData.do?submitAction=Generate Final Report&submitType=ajax',
	        type: 'post',
	        data: $('form#socialHistoryData').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         		if (isJson(data)) {
	         			alert((JSON.parse(data)).error);
	         		} else {
	         			setTimeout(function(){alert("Documents can be located in the Documents tab")}, 1000);
	         			//sessionStorage.setItem("isReportGenerated", "true");
	         		}
	         	}
	         	
	        }
	    });
	})
	
	function isJson(str) {
   		try {
        	json = JSON.parse(str);
    	} catch (e) {
        	return false;
    	}
    	return true;
	}
	
})
</script>
</head>

<html:form styleId="socialHistoryData" action="/handleSocialHistoryData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|99"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0"> 

<logic:equal name="socialHistoryForm" property="socialHistoryData.generic" value="true">
	<bean:define id="presentOffenseList" toScope="request" value="socialHistoryData.presentOffensesForGeneric"/>
</logic:equal>

<logic:equal name="socialHistoryForm" property="socialHistoryData.generic" value="false">
	<logic:equal name="socialHistoryForm" property="socialHistoryData.reportToRefereeInitiation" value="true">
		<bean:define id="presentOffenseList" toScope="request" value="socialHistoryData.juvenileOffenses"/>
	</logic:equal>
	<logic:equal name="socialHistoryForm" property="socialHistoryData.reportToRefereeInitiation" value="false">
		<bean:define id="presentOffenseList" toScope="request" value="socialHistoryData.presentOffenses"/>
	</logic:equal>
</logic:equal>




<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
<tr> 
	<td align="center" class="header">Juvenile Casework - Conduct Interview<br>Social History Data - Offense</td>
</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Click check box to exclude from report and click on Save Changes button to save the changes.</li>
	     		 <li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
	      		<li>Click Back button to return to previous page. </li>
			</ul>
		</td>
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN HEADER INFO TABLE --%>
<div class='spacer'></div>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<%-- BEGIN TAB  --%>
	  	<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
	  		<tiles:put name="tabid" value="casefiledetailstab"/>	
	  		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  	</tiles:insert>	

			<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
					<div class='spacer'></div>
					 <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign='top'>
											<%--tabs start--%>
											<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
	 												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
											<%--tabs end--%>
										</td>
									</tr>
									<tr>
								  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
								  </tr>
					  		</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'>
											<div class='spacer'></div>						
											<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
												<tr>
													<td valign='top'>
														<table width='100%' border="0" cellpadding="0" cellspacing="0" >
															<tr>
																<td valign='top'>
																	<%--tabs start--%>
																	<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
																		<tiles:put name="tabid" value="presentOffense"/>
																	</tiles:insert>
																	<%--tabs end--%>
																</td>
															</tr>
															<tr><td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td></tr>
														</table>
						
														<%--begin outer blue border --%>
														<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
															<tr>
																<td valign='top' align='center'>
																	<!--  May have to add logic in future to use form.getSocialHistoryData().isGeneric() to find out whether to use presentOffenses for the generic report or not-->
																	<logic:empty name="socialHistoryForm" property="${requestScope.presentOffenseList}">
																		<div class='spacer'></div>
																		<table id="offenseTable" width="98%" border="0" cellpadding="2" cellspacing="0">
						 													<tr>
						 														<td valign='top' class="formDeLabel" colspan='4'>No Offense Data.</td>
						 													</tr>
						 												</table>
																	</logic:empty>
															
																	<!--  May have to add logic in future to use form.getSocialHistoryData().isGeneric() to find out whether to use presentOffenses for the generic report or not-->
																	<logic:notEmpty name="socialHistoryForm" property="${requestScope.presentOffenseList}">
																		<nested:iterate id="offenseIter" name="socialHistoryForm" property="${requestScope.presentOffenseList}">
																			<div class='spacer'></div>
																			<table width="98%" border="0" cellpadding="4" cellspacing="1" class="borderTableBlue">
							 													<tr>
							 														<td valign='top' class='detailHead' colspan='4'>Offense Information</td>
							 													</tr>

							 													<tr>
																					<td class="formDeLabel" valign='top' nowrap='nowrap'>Exclude from Section?</td>
																					<td class="formDe" valign='top' width='50%'><nested:checkbox property="excluded" value="true"/> Yes</td>
																					<td class="formDeLabel" valign='top' nowrap='nowrap' width='1%'>Present Alleged Offense?</td>
							 														<td class="formDe" valign='top'><nested:checkbox property="presentAllegedOffense" value="true"/> Yes</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel">&nbsp</td>
							 														<td class="formDe" colspan='3'>&nbsp</td>
																				</tr>
																				<tr>
							 														<td class="formDeLabel" valign='top' nowrap='nowrap' width='1%'>Petition Allegation Description</td>
							 														<td class="formDe" valign='top' colspan='3'><nested:write property="presentOffense"/></td>
																				</tr>	
							 													<tr>
							 														<td class="formDeLabel" valign='top'>Petition Filed Date</td>
							 														<td class="formDe" valign='top'><nested:write property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
							 														<td class="formDeLabel" valign='top' nowrap='nowrap'>Petition Allegation Code</td>
							 														<td class="formDe" valign='top'><nested:write property="petitionAllegation"/></td>
							 													</tr>
							 													<tr>
							 														<td class="formDeLabel" valign='top'>Petition #</td>
							 														<td class="formDe" valign='top'><nested:write property="petitionNumber"/></td>
							 														<td class="formDeLabel" valign='top'>Referral #</td>
							 														<td class="formDe" valign='top'><nested:write property="referralNumber"/></td>
							 													</tr>
																				
							 													<tr>
							 														<td class="formDeLabel" valign='top'>DA Log #</td>
							 														<td class="formDe" valign='top'><nested:write property="DALogNumber"/></td>
							 														<td class="formDeLabel" valign='top'>Transaction #</td>
							 														<td class="formDe" valign='top'><nested:write property="transactionNumber"/></td>
							 													</tr>
																				<nested:notEmpty property="adultCoActors" >
							  													<tr>
							  														<td valign='top' align='center' class='detailHead' colspan='4'>Adult Co-Actors</td>
							  													</tr>
																					<nested:iterate id="coActorIter" property="adultCoActors">
								  													<tr>
								  														<td class="formDeLabel" valign='top'>Age</td>
								  														<td class="formDe" valign='top'><nested:write property="age"/></td>
																							<td class="formDeLabel" valign='top'>Name</td>
								  														<td class="formDe" valign='top'><nested:write property="name"/></td>
								  													</tr>
																					</nested:iterate>
																				</nested:notEmpty>
																			</table>
																		</nested:iterate>
																	</logic:notEmpty>
						
																		<div class='spacer'></div>
																		<table border="0" width="100%">
																			<tr>
																				<td align="center">
																					<input type="hidden" name="resetTabName" value="presentOffense"/>
																					<input type="submit" name="submitAction" value="<bean:message key='button.back'/>"  onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
																					<html:submit property="submitAction"><bean:message key="button.saveChanges"/></html:submit> 
																					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
																				</td>
																			</tr>
																			<tr>
																				<td align="center">
																					<html:submit styleId="generateDraftReport" property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
																					<html:submit styleId="generateFinalReport" property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit> 
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
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
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

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:form>
</html:html>
