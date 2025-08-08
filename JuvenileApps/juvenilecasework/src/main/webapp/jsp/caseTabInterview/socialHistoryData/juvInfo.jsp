<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/20/2006		AWidjaja Create JSP--%>
<%-- 12/05/2012 	CShimek    #74731 added Parental Rights Terminated display fiedl to Family Members  --%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvInfo.jsp</title>
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
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|98"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header">Juvenile Casework - Conduct Interview<br>Social History Data - Juvenile Information</td>
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Click check box to exclude from report, and click on Save Changes button to save the changes.</li>
				<li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
				<li>Click Back button to return to previous page.</li>
			</ul>
		</td>
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
<tr>
	<td valign='top'>
		<%-- BEGIN TAB TABLE --%>
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
							  				<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
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
																		<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
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
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.gangTraits">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t1', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t1"></a>Gang Traits
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t1Span" class='hidden'>
																						<tr bgcolor='#cccccc' id="phChar0">
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>
																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>
						
																						<nested:iterate id="gangIter" name="socialHistoryForm" property="socialHistoryData.gangTraits" indexId="index">
						                                  									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																								<td valign='top' align='center'><nested:checkbox property="excluded"/></td>
																								<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeName"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>
																								<td align="left"><nested:write property="status"/></td>
																								<td align="left"><nested:write property="traitComments"/></td>
																							</tr>
																						</nested:iterate>
																					</table>
																				</td>
																			</tr>	
																		</table>
																	</logic:notEmpty>
																	
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.strengthTraits">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t2', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t2"></a>Strength Traits
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t2Span" class='hidden'>
																						<tr bgcolor='#cccccc' >
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>
																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>
																						
																						<nested:iterate id="strengthIter" name="socialHistoryForm" property="socialHistoryData.strengthTraits" indexId="index">
						                                  									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																								<td valign='top' align='center'><nested:checkbox property="excluded"/></td>
																								<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeName"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>
																								<td align="left"><nested:write property="status"/></td>
																								<td align="left"><nested:write property="traitComments"/></td>
																							</tr>
																						</nested:iterate>
																					</table>
																				</td>
																			</tr>	
																		</table>
																	</logic:notEmpty>
																	
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.substanceAbuseTraits">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t3', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t3"></a>Substance Abuse Traits
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t3Span" class='hidden'>
																						<tr bgcolor='#cccccc' >
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>
																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>
						
																						<nested:iterate id="substanceAbuseIter" name="socialHistoryForm" property="socialHistoryData.substanceAbuseTraits" indexId="index">
														                           			<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
							  																	<td valign='top' align='center'><nested:checkbox property="excluded"/></td>
							  																	<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
							  																	<td valign='top' align="left"><nested:write property="traitTypeName"/></td>
							  																	<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>
																									<td align="left"><nested:write property="status"/></td>
							  																	<td align="left"><nested:write property="traitComments"/></td>
							  																</tr>
																						</nested:iterate>
																					</table>
																				</td>
																			</tr>
																		</table>
																	</logic:notEmpty>
																		
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.substanceAbuseInformation">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">			
																			<tr>
																				<td valign='top' class='detailHead' colspan='5'>
																					<a href="javascript:showHideExpand('t4', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t4"></a>Substance Abuse Information</td>
																			</tr>
																			<tr>
																				<td>	
																					<table border='0' cellpadding='2' cellspacing='1' id="t4Span" class='hidden'>
																						<tr bgcolor='#cccccc' >
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap'>Exclude?</td>
																							<td class="subhead" valign='top' nowrap='nowrap'>Entry Date</td>														
																							<td class="subhead" valign='top' nowrap='nowrap'>Onset Age</td>
																							<td class="subhead" valign='top' >Drug Type</td>
																							<td class="subhead" valign='top' >Frequency</td>
																							<td class="subhead" valign='top' >Location of Use</td>
																							<td class="subhead" valign='top' nowrap='nowrap'>Amount Spent</td>
																							<td class="subhead" valign='top' >Degree</td>
																						</tr>
		
																						<nested:iterate id="saIter" name="socialHistoryForm" property="socialHistoryData.substanceAbuseInformation" indexId="index">
																							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
			  																					<td valign='top' align='center'><nested:checkbox property="excluded" value="true"/></td>
			  																					<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
			  																					<td valign='top' align='right'><nested:write property="onsetAge"/></td>
			  																					<td valign='top' align="left"><nested:write property="drugType"/></td>
			  																					<td valign='top' align="left"><nested:write property="frequency"/></td>
			  																					<td valign='top' align="left"><nested:write property="locationOfUse"/></td>
			  																					<td valign='top' align='right' nowrap='nowrap'>
			  																						<nested:notEmpty property="amountSpent">$</nested:notEmpty>
			  																						<nested:write property="amountSpent" formatKey="currency.format"/></td>
			  																					<td valign='top' align="left"><nested:write property="degree"/></td>
			  																				</tr>
			  																			</nested:iterate>
																					</table>
																				</td>
																			</tr>
																		</table>
																	</logic:notEmpty>
																		
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.schoolBehaviorTraits">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t5', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t5"></a>School Behavior Traits
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t5Span" class='hidden'>
																						<tr bgcolor='#cccccc' >
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>
																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>
																						
																						<nested:iterate id="strengthIter" name="socialHistoryForm" property="socialHistoryData.schoolBehaviorTraits"indexId="index">
																							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																								<td valign='top' align='center'><nested:checkbox property="excluded"/></td>
																								<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeName"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>
																								<td align="left"><nested:write property="status"/></td>
																								<td align="left"><nested:write property="traitComments"/></td>
																							</tr>
																						</nested:iterate>
																					</table>
																				</td>
																			</tr>	
																		</table>
																	</logic:notEmpty>
																	
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.schoolAttendanceTraits">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t6', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t6"></a>School Attendance Traits
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t6Span" class='hidden'>
																						<tr bgcolor='#cccccc' >
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>
																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>
																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>
																						
																						<nested:iterate id="strengthIter" name="socialHistoryForm" property="socialHistoryData.schoolAttendanceTraits" indexId="index">
																							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																								<td valign='top' align='center'><nested:checkbox property="excluded"/></td>
																								<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeName"/></td>
																								<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>
																								<td align="left"><nested:write property="status"/></td>
																								<td align="left"><nested:write property="traitComments"/></td>
																							</tr>																						</nested:iterate>																					</table>																				</td>																			</tr>																			</table>																	</logic:notEmpty>
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.educationalPerformanceTraits">																		<div class='spacer'></div>																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">																			<tr>																				<td valign='top' class='detailHead' colspan='5' align="left">																					<a href="javascript:showHideExpand('t7', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t7"></a>Educational Performance Traits
																				</td>																			</tr>																			<tr>																				<td>																					<table border='0' cellpadding='2' cellspacing='1' id="t7Span" class='hidden'>																						<tr bgcolor='#cccccc' >																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>			
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Entry Date</td>																							<td class="subhead" valign='top' width='14%' align="left">Trait Type</td>																							<td class="subhead" valign='top' width='18%' align="left">Trait Description</td>																							<td class="subhead" valign='top' width='18%' align="left">Trait Status</td>
																							<td class="subhead" valign='top' width='49%' align="left">Trait Comments</td>
																						</tr>			
																						<nested:iterate id="strengthIter" name="socialHistoryForm" property="socialHistoryData.educationalPerformanceTraits" indexId="index">						                                  									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">																								<td valign='top' align='center'><nested:checkbox property="excluded"/></td>																								<td valign='top' align="left"><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>																								<td valign='top' align="left"><nested:write property="traitTypeName"/></td>																								<td valign='top' align="left"><nested:write property="traitTypeDescription"/></td>																								<td align="left"><nested:write property="status"/></td>
																								<td align="left"><nested:write property="traitComments"/></td>																							</tr>																						</nested:iterate>																					</table>																				</td>																			</tr>																			</table>																	</logic:notEmpty>			
																	<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.programReferralList">																		<div class='spacer'></div>																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">																			<tr>																				<td valign='top' class='detailHead' colspan='5' align="left">																					<a href="javascript:showHideExpand('t8', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t8"></a>Program Referrals
																				</td>
																			</tr>																			<tr>																				<td>																					<table border='0' cellpadding='2' cellspacing='1' id="t8Span" class='hidden'>																						<tr bgcolor='#cccccc' id="phChar0">																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Exclude?</td>																							<td class="subhead" valign='top' width='15%' nowrap='nowrap' align="left">Last Action Date/Time</td>																							<td class="subhead" valign='top' width='23%' align="left">Referral Status</td>																							<td class="subhead" valign='top' width='13%' align="left">Outcome</td>																							<td class="subhead" valign='top' width='15%' align="left">Program</td>																							<td class="subhead" valign='top' width='15%' align="left">Begin Date</td>																						</tr>			
																						<nested:iterate id="programReferralIndexClosed" name="socialHistoryForm" property="socialHistoryData.programReferralList" indexId="index">								  															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">									  															<td valign='top' align='center'><nested:checkbox property="excluded" /></td>								  					 											<td align="left"><a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>handleSocialHistoryData.do?submitAction=<nested:message key="button.referral"/>&referralId=<nested:write property="referralId" />',400,700)">							  						 												<nested:write property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /> </a>							  					 											 	</td>						  					  													<td align="left"><nested:write property="referralStatusDescription" /> &nbsp;<nested:write property="referralSubStatusDescription" /></td>				                       															<td align="left"><nested:write  property="outComeDesc" /></td>							  					  												<td align="left"><nested:write property="providerProgramName" /></td>								  					 												<td align="left"><nested:write property="beginDate" formatKey="date.format.mmddyyyy" /></td>						  														      		</tr>						  			   													<%-- End Pagination item wrap --%>						  			 										      		</nested:iterate>																					</table>																				</td>																			</tr>																			</table>																 	 </logic:notEmpty>
																 	 <logic:notEmpty name="socialHistoryForm" property="socialHistoryData.familyInformation">
																		<div class='spacer'></div>
																		<table width='98%' cellpadding='2' cellspacing='0' class="borderTableBlue">
																			<tr>
																				<td valign='top' class='detailHead' colspan='5' align="left">
																					<a href="javascript:showHideExpand('t9', 'table', '/<msp:webapp/>');" border='0'>
																					<img border='0' src="/<msp:webapp/>images/expand.gif" name="t9"></a> Family Members
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<table border='0' cellpadding='2' cellspacing='1' id="t9Span" class='hidden'>
																						<tr bgcolor='#cccccc' id="phChar0">
																							<td class="subhead" valign='top' width='1%' nowrap='nowrap' align="left">Include?</td>
																							<td class="subhead" valign='top' width='5%' nowrap='nowrap' align="left">Member#</td>
																							<td class="subhead" valign='top' width='20%' align="left"><bean:message key="prompt.name" /></td>
																							<td class="subhead" valign='top' width='20%' align="left"><bean:message key="prompt.relationship" /></td>
																							<td class="subhead" valign='top' width='10%' align="left"><bean:message key="prompt.primaryCareGiver" /></td>
																							<td class="subhead" valign='top' width='5%' align="left"><bean:message key="prompt.guardian" /></td>
																							<td class="subhead" valign='top' width='5%' align="left"><bean:message key="prompt.deceased" /></td>
																							<td class="subhead" valign='top' width='15%' align="left"><bean:message key="prompt.parentalRightsTerminated" /></td>
																							<td class="subhead" valign='top' width='10%' align="left"><bean:message key="prompt.incarcerated" /></td>
																							<td class="subhead" valign='top' width='10%' align="left">Living With</td>
																						</tr>
																						<nested:iterate id="familyInformationIndexClosed" name="socialHistoryForm" property="socialHistoryData.familyInformation" indexId="index">
								  															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
									  															<td valign='top' align='center'><nested:checkbox property="included" value="true" /></td>
								  					 											<td align="left"><nested:write property="familyMemberId" /></td>
							  					  												<td align="left"><nested:write property="familyMemberName" /></td>
					                       														<td align="left"><nested:write property="relationship" /></td>
					                       														<td align="left"><nested:write property="primaryCareGiverAsStringYesOnly" /></td>
								  					  											<td align="left"><nested:write property="guardianStatusAsStringYesOnly" /></td>	
								  					 											<td align="left"><nested:write property="deceasedAsStringYesOnly" /></td>
								  					 											<td align="left"><nested:write property="parentalRightsTerminatedAsStringYesOnly"/></td>
								  					 											<td align="left"><nested:write property="incarceratedAsStringYesOnly" /></td>
								  					 											<td align="left"><nested:write property="inHomeAsStringYesOnly" /></td>
						  														      		</tr>
							  			   													<%-- End Pagination item wrap --%>
						  			 										      		</nested:iterate>
																					</table>
																				</td>
																			</tr>	
																		</table>
																 	 </logic:notEmpty>			
																	<table border="0" width="100%">																		<tr>																			<td align="center">																				<input type="hidden" name="resetTabName" value="juvInfo"/>																				<input type="submit" name="submitAction" value="<bean:message key='button.back'/>" onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">																				<html:submit property="submitAction"><bean:message key="button.saveChanges"/></html:submit> 																				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 																			</td>																		</tr>																		<tr>																			<td align="center">
																				<html:submit styleId="generateDraftReport" property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
																				<html:submit styleId="generateFinalReport" property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit>
																			</td>																		</tr>																	</table>																</td>															</tr>															</table>															<div class='spacer'></div>														</td>			
													</tr>
												</table>
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
</html:form>							
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>