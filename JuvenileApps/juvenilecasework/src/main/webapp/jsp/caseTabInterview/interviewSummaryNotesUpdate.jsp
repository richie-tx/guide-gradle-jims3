<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/01/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>



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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/interviewUpdate.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayJuvInterviewSummary" target="content"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >
		<bean:message key="title.mjcwConductInterview"/> - Update Interview Summary Notes
	</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div> 
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Interview Date to see details.</li>
        <li>Select Add Interview button to create an interview.</li>
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
          										  									 <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>" width="128" border='1'></a>
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
            													<logic:iterate id="recordsIter" name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay">
            														<logic:notEmpty name="recordsIter">
            															<bean:write name="recordsIter"/>
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
            												 
            										<logic:empty name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription">
            											<tr id='saddr0'>
            												<td class='formDeLabel' >Street number</td>
            												<td class='formDe' ><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetNum"/></td>
            												<td class="formDeLabel">Street Name</td>
            												<td class='formDe'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetName"/></td>
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
            										</logic:empty>							
            																			
            										<tr> 
            											<td class='formDeLabel' valign='top' nowrap='nowrap' colspan="4">Summary Notes&nbsp;
	            											<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
	            												<tiles:put name="tTextField" value="currentInterview.summaryNote"/>
	            												<tiles:put name="tSpellCount" value="spellBtn3" />
	          												</tiles:insert>
	          												(Max. characters allowed: 32000)
					              					</td>  
            										</tr> 
            										<tr> 
            											<td class='formDe' colspan="4"><html:textarea name="juvenileInterviewForm" property="currentInterview.summaryNote" rows="3" style="width:100%" styleId="summaryNoteId1"/></td> 
            										</tr>
            									</table>
            								</td>
            							</tr>
            						</table>

         						<%-- BEGIN BUTTON TABLE --%>
                    <div class='spacer'></div>             
                    <table width="100%">
                    	<tr>
                    		<td align="center">
                    			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>		
                    			<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.submit"/></html:submit>
                    	        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    		</td>
                    	</tr>
                    </table>
                    <%-- END BUTTON TABLE --%>
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

