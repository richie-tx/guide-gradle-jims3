<!DOCTYPE HTML>

<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.UIConstants" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>


<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarEventAppointmentLetter.jsp</title>
</head>

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0">
<html:form action="/submitCalendarInventory" target="content"> 

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">
		<bean:message key="title.heading" />&nbsp;<bean:message key="title.juvenileCasework" />-<bean:message key="prompt.documentRequest" />&nbsp;	
	</td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Select one or more documents for the juvenile to bring at the next appointment.</li>        
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CASEFILE HEADER TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<!-- END CASEFILE HEADER TABLE -->


<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">  	
  <tr>
    <td>	 	
      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="calendartab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
      <%--tabs end--%>

  		<table width='100%' cellpadding="2" cellspacing="1" class="borderTableBlue">	
  			<tr>
  				<td>
					
  				
  					<table width='100%' align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">
  						<tr>
  							<td class="detailHead">Juvenile Information - Document Request</td>
  						</tr>
  						<tr>
  							<td>
  								<table width='98%' align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">
  									<tr>
  										<td class="detailHead"><a href="javascript:showHideMulti('inventory', 'achar', 1,'/<msp:webapp/>');"><img border=0 src="/<msp:webapp/>images/expand.gif" name="inventory"></a>&nbsp;Document Inventory on file</td>
  									</tr>
  									<tr id="achar0" class='hidden'>
  										<td>
    										<!-- BEGIN ATTENDEE TABLE -->
    										<table align="center" width="100%" cellpadding="2" cellspacing="1">  
    											<logic:empty name="calendarEventListForm" property="currentInterview.currentRecordsInventory">
    												<tr>
    													<td>No documents on file yet</td>
    												</tr>										
    											</logic:empty>
    											<logic:notEmpty name="calendarEventListForm" property="currentInterview.currentRecordsInventory">
    												<logic:iterate indexId="index" id="recordsIter" name="calendarEventListForm" property="currentInterview.currentRecordsInventory">
    													<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
    														<td><bean:write name="recordsIter"/></td>
    													</tr>
    												</logic:iterate>		
    											</logic:notEmpty>
    											<logic:notEmpty name="calendarEventListForm" property="currentInterview.currentOtherInventoryRecords">
    												<tr>
    													<td class="detailHead">Other Records Inventory</td> 
    												</tr>	
    												<logic:iterate id="othersIter" name="calendarEventListForm" property="currentInterview.currentOtherInventoryRecords">
    													<tr>	
    														<td class="formDe" ><bean:write name="othersIter"/></td> 
    													</tr>
    												</logic:iterate>
    											</logic:notEmpty>
    									  </table>
    									</td>
    								</tr>
    							</table>
    						</td>
    					</tr>
    					<tr>
    						<td>								
    							<table width='98%' align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">									
    								<tr>
    									<td class="formDeLabel">Select Documents for Appointment</td>
    								</tr>
    								<tr> 						
    									<td align="left"> 
    										<html:select name="calendarEventListForm" property="currentInterview.recordsInventoryIds" size="6" multiple="true" styleId="curIntRecordsInventoryIds"> 
    											<html:optionsCollection name="calendarEventListForm" property="recordsInventoryList" value="code" label="description" />
 												</html:select> 
 											</td> 
 										</tr>									
 										<tr id='otherRowTitle' class='hidden'>
 											<td class="formDeLabel" nowrap width="1%">Other Inventory Record(s)</td>													
 										</tr>	
 										<tr id='otherRow' class='hidden'>	
 											<td class="formDe"><html:text name="calendarEventListForm" property="currentInterview.otherInventoryRecords" size="50" /></td>
 										</tr> 													
   								</table>
   							</td>							
   						</tr>
						</table>

    			  <table width="100%" border="0">  
    					<tr>
    					  <td align="center">
  								<!--This will reset previously selected document appointment letter.-->
  									<input type="hidden" name="resetDocApptLetter" value="true">
  									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
  									<!--awidjaja: Removed onclick to check if document attendance is selected. It is not required for the user to select a document attendance before he/she can generate an appointment letter-->
  									<html:submit property="submitAction"><bean:message key="button.generateBFOAppointmentLetterEnglish"/></html:submit> 																
  									<html:submit property="submitAction"><bean:message key="button.generateBFOAppointmentLetterSpanish"/></html:submit>
  									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    						</td>
    					</tr>
    			  </table>
    				<!-- END BUTTON TABLE -->
        		<div class='spacer'></div>
    			</td>				
    		</tr>
    	</table>
    </td>
  </tr>
</table>
<!-- END DETAIL TABLE -->




</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

