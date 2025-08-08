<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/30/2006		AWidjaja Create JSP--%>
<%-- 02/08/2007 	Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012     CShimek     #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/16/2012 	CShimek     #73565 added age > 20 check (juvUnder21) to Update Summary Notes button --%>
<%-- 10/24/2012 	DGibler		#73746 MJCW: Add Street Number suffix field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>
<%@ page import="naming.InterviewConstants" %>



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


<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/interviewUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript' type="text/javascript">
	$(document).ready(function(){
		if ("<bean:write name="juvenileInterviewForm" property="currentInterview.interviewType"/>" == "UNSCHEDULED EVENT"){
			console.log("Unscheduled event");
			$("#progressNotesLbl").css("display","none");
			$("#progressNotesField").css("display","none");
		} else {
			$("#progressNotesLbl").css("display","block");
			$("#progressNotesField").css("display","block");
		}
	})
	function processOther()
	{  // "Other" is the last option in the list  ...
		var last = document.interviewDetailsForm.recordsInventory.options.length ;
    
		if( document.interviewDetailsForm.recordsInventory.options[ (last -1) ].selected )
		{
			show( 'otherInventoryRecordsIncomplete', 1, 'row' ) ;
		}
		else
		{
			show( 'otherInventoryRecordsIncomplete', 0, 'row' ) ;
		}
	}

	function checkLocation()
	{
		var offset = document.interviewDetailsForm.locationSelection.selectedIndex ;
	  var tText = document.interviewDetailsForm.locationSelection.options[ offset ].text ;
		
		if( tText == 'Enter a new address' )
			addressTable( 'edit', 1 ) ;
		else
			addressTable( 'edit', 0 ) ;
	}

	function addressTable( rows, showa )
	{
		if( rows == 'edit' )
		{		// this is for the edit section
			for( var i = 0; i < 5; i++ )
			{
				show( ('addr' +i), showa, 'row' ) ;
			}
		}
		else
		{		// this is for the summary section
			for( var i = 0; i < 4; i++ )
			{
				show( ('saddr' +i), showa, 'row' ) ;
			}
		} 
	}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/submitJuvInterviewCreate" target="content"> 

<logic:equal name="juvenileInterviewForm" property="action" value="create">
    <logic:equal name="juvenileInterviewForm" property="status" value="summary">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|89">
    </logic:equal>
    <logic:equal name="juvenileInterviewForm" property="status" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|88">
    </logic:equal>
    <logic:equal name="juvenileInterviewForm" property="status" value="view">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|91">
    </logic:equal>
</logic:equal>    
<logic:equal name="juvenileInterviewForm" property="action" value="update">
    <logic:equal name="juvenileInterviewForm" property="status" value="summary">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|124">
    </logic:equal>
    <logic:equal name="juvenileInterviewForm" property="status" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|125">
    </logic:equal>
    <logic:equal name="juvenileInterviewForm" property="status" value="view">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|91">
    </logic:equal>
</logic:equal>
  

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >
	    <bean:message key="title.juvenileCasework"/> - 
			<bean:message key="title.mjcwConductInterview"/> -	
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">	
				<logic:equal name="juvenileInterviewForm" property="action" value="create">
					Create 
				</logic:equal>
				<logic:equal name="juvenileInterviewForm" property="action" value="update">
					Update 
				</logic:equal>
			</logic:equal>	
			Interview
			<logic:equal name="juvenileInterviewForm" property="status" value="summary">
				Summary
			</logic:equal>
			<logic:equal name="juvenileInterviewForm" property="status" value="confirm">
				Confirmation
			</logic:equal>
			<logic:equal name="juvenileInterviewForm" property="status" value="view">
				Details
			</logic:equal>
		</td>
	</tr>
    <tr>
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
	</tr>
	<logic:equal name="juvenileInterviewForm" property="status" value="confirm">
		<tr>
			<td align='center' class='confirm'>Interview has been successfully
				<logic:equal name="juvenileInterviewForm" property="action" value="updateSummaryNotes">
					 updated.
				</logic:equal>
				<logic:equal name="juvenileInterviewForm" property="action" value="update">
					 updated.
				</logic:equal>
				<logic:equal name="juvenileInterviewForm" property ="action" value="create">
					created.
				</logic:equal>
		</td></tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
		<logic:equal name="juvenileInterviewForm" property="status" value="summary">
			Click Finish button to save the Interview information.
		</logic:equal>
			
		<logic:equal name="juvenileInterviewForm" property="status" value="view">
			<li>Select Back button to return to previous page.</li>
		</logic:equal>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				

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
            							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a> <b>|</b> </td>
            						</tr>
            					</table>
              			</td>
            	    </tr>
						  	</table>

 								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign='top' align='center'>
											<div class='spacer'></div>
          						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
          							<tr>
          								<td class='detailHead'>Interview Detail</td>
          							</tr>
          							<tr>
          								<td valign='top' align='center'>
          									<table width='100%' border="0" cellpadding="4" cellspacing="1" >
          										<tr>
          											<td valign='top' class='formDeLabel' colspan='4'>
          												<table width='100%' cellpadding='0' cellspacing='0' border='0'>
          													<tr>
 										  								<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
 										  									<td class='formDeLabel' nowrap='nowrap' valign='top'>&nbsp;Photos (Juvenile has no photos)</td>
 										  								</logic:empty>
 										  								
 										  								<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
	          														<td width='1%'><a href="javascript:showHideMulti('Photos', 'phChar', 1, '/<msp:webapp/>')" border='0'><img border='0' src="/<msp:webapp/>images/expand.gif" name="Photos"></a></td>
	          														<td class='formDeLabel' nowrap='nowrap' valign='top'>&nbsp;Photos</td>
          														</logic:notEmpty>
          													</tr>
          												</table>
          											</td>
          										</tr>
          										<tr id="phChar0" class='hidden'>
          											<td valign='top'>
          												<table width='98%' cellpadding='2' cellspacing='2' border='0'>
          													<tr bgcolor='white'>
          														<td valign='top' width='70%'>
          															<table width='98%' cellpadding='4' cellspacing='1'>              								
          																<tr>
          																	<td  width='6%' nowrap='nowrap'>
          																		
          																		<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
          										  									<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)"  > 
          										  									 <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>" width="128" border='1'> 
          										  									</a>
          										  								</logic:notEmpty>
          																	</td>
          																</tr>
          																<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
          																	<tr>
          																		<td align='left'><bean:write name="juvenilePhotoForm" property="mostRecentPhoto.entryDate" formatKey="date.format.mmddyyyy"/></td>
          																	</tr>
          																</logic:notEmpty>	
          															</table>
          														</td>
          													</tr>
          												</table>
          											</td>
          										</tr>
          										
          										<tr>
          											<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Date</td>
          											<td class='formDe'><bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="date.format.mmddyyyy"/></td>
          											<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Time</td>
          											<td id='interviewTime' class='formDe'><bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="time.format.hhmma"/></td>
          										</tr>
          										<tr> 
          											<td class='formDeLabel' nowrap='nowrap' valign='top' width="1%">Person(s) Interviewed</td> 
          											<td class='formDe' colspan="3">
          											
          												<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
          													<logic:iterate id="personsIter" name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
          														<bean:write name="personsIter" property="formattedName"/><br>
          													</logic:iterate>
          											</logic:notEmpty>
          												
          											</td> 
          										 </tr> 
          										
          										<tr id='recordInventory'> 
          											<td class='formDeLabel' nowrap='nowrap' valign='top' width='1%'>Records Inventory</td> 
          											<td class='formDe' colspan="3">
          												<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay">
          													<bean:size id="recordsSize" name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay"/>	
          													<logic:iterate indexId="recordsIndex" id="recordsIter" name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay">
          														<logic:notEmpty name="recordsIter">
          															<bean:write name="recordsIter"/><br>
          														</logic:notEmpty>
          													</logic:iterate>
          												</logic:notEmpty>
          											</td> 
          										</tr> 
          										<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.otherInventoryRecords">
          											<tr>
          												<td class='formDeLabel' nowrap='nowrap' valign='top' width='1%'>Other Records Inventory</td> 
          												<td class='formDe' colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.otherInventoryRecords" /></td> 
          											</tr>
          										</logic:notEmpty>
          										<tr> 
          											<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Type</td> 
          											<td class='formDe' colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.interviewType"/></td> 
          										</tr> 
          										<tr id='interviewLocation'>
          											<td class='formDeLabel' nowrap='nowrap' width="1%">Interview Location</td> 
          											<td class='formDe' colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription"/></td> 
          										</tr>
          										
          										<jims:if name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription" value="New Address" op="equal">
          											<jims:or name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription" value="" op="equal"/>
          												<jims:then>
          										
          													<tr id='saddr0'>
          														<td class='formDeLabel' >Street Number</td>
          														<td class='formDe' ><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetNum"/></td>
          													    <td class='formDeLabel' >Street Number Suffix</td>
          													    <td class='formDe' >
	          													    <logic:notEmpty name="juvenileInterviewForm" property="currentInterview.newAddress.streetNumSuffixCode">
	          															<bean:write name="juvenileInterviewForm" property="currentInterview.streetNumSuffix"/>
	          														</logic:notEmpty>
          														</td>
          													</tr>
          													<tr>	
          														<td class="formDeLabel">Street Name</td>
          														<td class='formDe' colspan='3'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetName"/></td>
          													</tr>
          
          													<tr id='saddr1'>
          														<td class="formDeLabel">Street Type</td>
          														<td class='formDe'>
          															<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.newAddress.streetTypeCode">
          																<bean:write name="juvenileInterviewForm" property="currentInterview.streetType"/>
          															</logic:notEmpty>
          														</td>
          														<td class="formDeLabel">Apt/Suite</td>
          														<td class='formDe'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.aptNum"/></td>
          													</tr>
          													  <tr id='saddr2'>
          													  <td class="formDeLabel">City</td>
          													  <td class='formDe'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.city"/></td>
          													  <td class="formDeLabel">State</td>
          													  <td class='formDe'>
          														<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.newAddress.stateCode">
          																<bean:write name="juvenileInterviewForm" property="currentInterview.state"/>
          														</logic:notEmpty>
          													  </td>
          													</tr>
          													  <tr id='saddr3'>
          													  <td class="formDeLabel">Zip Code</td>
          													  <td class='formDe' colspan='3'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.zipCode"/>-<bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.additionalZipCode"/></td>
          													</tr>
          												</jims:then>												
          										</jims:if>				

          										<!-- JIMS200058316 begin  -->	
          										<logic:notEqual name="juvenileInterviewForm" property="action" value="create">
	          										<logic:notEqual name="juvenileInterviewForm" property="action" value="update">
	          										<logic:notEqual name="juvenileInterviewForm" property="action" value="recordSumm">
	          										    <logic:notEqual name="juvenileInterviewForm" property="status" value="confirm">
          											 <tr>
		    													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.attendance" /> <bean:message key="prompt.status" /></td>
		    													<td class="formDe" colspan='3'><bean:write name="juvenileInterviewForm" property="currentInterview.attendanceStatusDescription"/></td>
		    												</tr>
		    											</logic:notEqual>				
	    						   					</logic:notEqual>
	    						   					</logic:notEqual>
    						   					</logic:notEqual>

         										<jims2:if name="juvenileInterviewForm" property="action" op="equal" value="create">
  													<jims2:or name="juvenileInterviewForm" property="action" op="equal" value="recordSumm" />
  													<jims2:then>	
															<tr>										
												  			<div class='spacer'></div>
											            <table align="center" width="100%" colspan="2" cellpadding="2" cellspacing="1" class="borderTableBlue">  
											            	<tr> <%-- table titlebar --%>
											            		<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.attendance" /></td>
											            	</tr>
											            	<tr>
											            		<td valign='top' align='center'>
											            			<table width='100%' colspan="2" cellpadding="2" cellspacing="1">
										                			<tr>
																	      		<td class='formDeLabel' width='1%' nowrap='nowrap'>
																							<bean:message key="prompt.attendance" />&nbsp;<bean:message key="prompt.status" />
																						</td>
																						<td class='formDe'>
														            			<bean:write name="juvenileInterviewForm" property="attendanceStatus"/>	
																						</td>
															      			</tr>
														      			<tr> 
																					<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>
																			    <td class="formDe"><bean:write name="juvenileInterviewForm" property="addlAttendees"/></td>
														      			</tr>
														      			<tr> 
																					<td class='formDeLabel' valign='top' width='1%'>											
																						<bean:message key="prompt.additionalAttendeeNames"/>
																					</td> 
																					<td class='formDe' colspan="3">									
																						<bean:write name="juvenileInterviewForm" property="selectedAttendeeNamesAsString"/>
																					</td> 
																			</tr> 
																		</table>
																	</td>
																</tr>
															</table>
														</tr>
													</jims2:then>
												</jims2:if>
													
												<%--	<logic:equal name="juvenileInterviewForm" property="action" value="update">
													<logic:equal name="juvenileInterviewForm" property="status" value="confirm"> --%>
													<jims2:if name="juvenileInterviewForm" property="action" op="equal" value="update">
														<jims2:or name="juvenileInterviewForm" property="status" op="equal" value="confirm"/>
														<jims2:then>		
														<tr>										
											  			<div class='spacer'></div>
											            <table align="center" width="100%" colspan="2" cellpadding="2" cellspacing="1" class="borderTableBlue">  
											            	<tr> <%-- table titlebar --%>
											            		<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.attendance" /></td>
											            	</tr>
											            	<tr>
											            		<td valign='top' align='center'>
											            			<table width='100%' colspan="2" cellpadding="2" cellspacing="1">
										                			<tr>
																	      		<td class='formDeLabel' width='1%' nowrap='nowrap'>
																							<bean:message key="prompt.attendance" />&nbsp;<bean:message key="prompt.status" />
																						</td>
																						<td class='formDe'>
														            			<bean:write name="juvenileInterviewForm" property="attendanceStatus"/>	
																						</td>
															      			</tr>
															      			<tr> 
																						<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>
																				    <td class="formDe"><bean:write name="juvenileInterviewForm" property="addlAttendees"/></td>
															      			</tr>
															      			<tr> 
																						<td class='formDeLabel' valign='top' width='1%'>											
																							<bean:message key="prompt.additionalAttendeeNames"/>
																						</td> 
																						<td class='formDe' colspan="3">									
																							<bean:write name="juvenileInterviewForm" property="selectedAttendeeNamesAsString"/>
																						</td> 
																					</tr> 
																				</table>
																			</td>
																		</tr>
																	</table>
																</tr>
													</jims2:then>
													</jims2:if>			
												<%--		</logic:equal>		
													</logic:equal> --%>
          										<tr> 
          											<td class='formDeLabel' valign='top' nowrap='nowrap' colspan="4">Comments</td> 
          										</tr> 
          										<tr> 
          											<td class='formDe' colspan="4"><bean:write name="juvenileInterviewForm" property="currentInterview.userComments"/></td> 
          										</tr>
          						<!-- JIMS200058316 begin  -->													
          										<tr> 
          											<td class='formDeLabel' valign='top' nowrap='nowrap' colspan="4">Summary Notes</td> 
          										</tr> 
          										<tr> 
          											<td class='formDe' colspan="4"><bean:write name="juvenileInterviewForm" property="currentInterview.summaryNote"/></td> 
          										</tr>
          						<!-- JIMS200058316 begin  -->				
          										<tr> 
          											<td id="progressNotesLbl"class='formDeLabel' valign='top' nowrap='nowrap' colspan="4">Progress&nbsp;Notes&nbsp;</td> 
          										</tr> 
          										<tr> 
          											<td id="progressNotesField" class='formDe' colspan="4">
          											<logic:notEqual  name="juvenileInterviewForm" property="action" value="recordSumm">
          												<logic:empty name="juvenileInterviewForm" property="currentInterview.progressReport">
	          												No Progress Notes.
          												</logic:empty>
          												<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.progressReport">
	          												<bean:write name="juvenileInterviewForm" property="currentInterview.progressReport"/>
	          											</logic:notEmpty>
	          											</logic:notEqual>
          											</td> 
          										</tr>
          						<!-- JIMS200058316 begin  -->													
          									</table>
          									<div class='spacer'></div>
			          								</td>
			          							</tr>
			          						</table>
			          						<%-- BEGIN BUTTON TABLE --%>
			          						<div class='spacer'></div>
			                      <table width="100%">
			                   			<logic:equal name="juvenileInterviewForm" property="status" value="summary">
			                      			<tr>
			                      				<td align="center">
			                      					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			                      					<html:submit  onclick="spinner()" property="submitAction" styleId="finishId"><bean:message key="button.finish"/></html:submit>
			                      					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			                      				</td>
			                      			</tr>
			                      			</logic:equal>
			                      			
			                      			<logic:equal name="juvenileInterviewForm" property="status" value="confirm">
			                      				<logic:empty name="juvenileInterviewForm" property="currentInterview.interviewTasks">
			                      					<tr>
			                      						<td align="center">
			                      							<html:submit property="submitAction" onclick="spinner(); disableSubmit(this, this.form)"><bean:message key="button.continueToInterviewWizard"/></html:submit>
			                      							<html:submit property="submitAction" onclick="spinner(); disableSubmit(this, this.form)"><bean:message key="button.continueToClassicInterview"/></html:submit>
			                      						</td>
			                      					</tr>
			                      					<tr>

			                      						<td align="center">
															<input type="button" id="viewInterviewsBtn"
															value="<bean:message key='button.interviewList'/>">
														</td>
			                      						
			                      						
			                      					</tr>
			                      				</logic:empty>
			                      				<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.interviewTasks">
			                      					</tr>
			                      						<td align="center">
			                      							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			                      							<input type="button" onclick="return (changeFormActionURL(this.form.name, 
			                      								'/<msp:webapp/>displayJuvInterviewChecklist.do?submitAction=Interview Checklist', true) && disableSubmit(this, this.form));"
			                      								value="<bean:message key='button.interviewChecklist'/>">		                      							

			                      								<td align="center">
															<input type="button" id="viewInterviewsBtn"
															value="<bean:message key='button.interviewList'/>">
														</td>
			                      							
			                      						</td>
			                      					</tr>
			                      				</logic:notEmpty>
			                      			</logic:equal>
			                      			
			                      			<logic:equal name="juvenileInterviewForm" property="status" value="view">
			                      				</tr>
			                      					<td align="center">
			                      						<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			                      						<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
				                      						<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
					                      						<logic:equal name="juvenileInterviewForm" property="currentInterview.interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
					                      						<logic:notEqual name="juvenileInterviewForm" property="currentInterview.interviewTypeId" value="<%=InterviewConstants.INTERVIEW_TYPE_ID_DR%>">
					                      							<logic:equal name="juvenileInterviewForm" property="authorized" value="true">
					                      								<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_U%>'>
					                      									<html:submit onclick="spinner()" property="submitAction" styleId="updateSummaryNotesId" ><bean:message key="button.updateSummaryNotes"/></html:submit>
					                      								</jims2:isAllowed>
					                      							</logic:equal>
					                      							</logic:notEqual>
					                      						</logic:equal>
					                      					</logic:equal>
					                      				</logic:equal>		
			                      						<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			                      					</td>
			                      				</tr>
			                      			</logic:equal>
						               	</table>
						                      <%-- END BUTTON TABLE --%>
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
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
