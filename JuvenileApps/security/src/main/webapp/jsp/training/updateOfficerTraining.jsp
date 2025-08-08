<!DOCTYPE HTML>

<%-- Used for searching of juvenile profile in MJCW --%>
<%--MODIFICATIONS --%>
<%-- RYoung  11/16/2022	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>





<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading" /> - updateOfficerTraining.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<%-- Javascript for emulated navigation --%>
<script>

$(document).ready(function () {
	
	//default fields
	$("#trainingTopicId").val("0");
	$("#beginDate").val("");
	$("#endDate").val("");
	$("#trainingHrs").val('0.0');
	
	if(typeof $("#beginDate") != "undefined"){
		datePickerSingle($("#beginDate"),"Date",false);
	}
	
	if(typeof $("#endDate") != "undefined"){
		datePickerSingle($("#endDate"),"Date",false);
	}		
	
	$("#submitBtn").on('click', function (e) {
		
		var officerOID = $("#officerID").val(); 
		var selectedId = $("#trainingTopicId").val();
		var begDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var hours = $("#trainingHrs").val();
		
		var newBegDate = formatDate(begDate,0);
		var newEndDate = formatDate(endDate,0);
		
		if( selectedId == 0 ){
			
			alert("Training topic is required.");
			$("#trainingTopicId").focus();
			return false;
		}
		
		if( begDate == "" ){
			
			alert("Training begin Date is required.");
			$("#beginDate").focus();
			return false;
		}
		
		if( endDate == "" ){
			
			alert("Training end Date is required.");
			$("#endDate").focus();
			return false;
		}
		
		if( newBegDate > newEndDate ){
			
			alert("Training begin Date must be less or equal to Training End Date.");
			$("#begDate").focus();
			return false;
		}
		
		if ( isNaN(hours, false )){
			alert("Training hours must be numeric value.");
			$("#trainingHrs").focus();
			return false;
		}
				
		$('form').attr('action','/security.war/handleOfficerTraining.do?submitAction=Add Selected&officerProfileId='+officerOID);
		$('form').submit();
    });	
	
 	function formatDate(date,add) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
	

});

</script>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>


</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/handleOfficerTraining" target="content" method="post">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<br/>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Officer Update- Create Training</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
</br></br>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   <tr>
     <td>
  	  <ul>
        <li>Select the type of search you wish or enter Juvenile Number.</li>
    	<li>Enter/Select required fields and other search values then select submit button to search.</li> 
     </ul>	
	</td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  </tr>
  
  <tr>     	
   	<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
 	<tr>
		<td>
	  		<table width='100%' cellpadding="2" cellspacing="1">
		 		  <tr class="headerLabel" > 
					<td class="formDeLabel"> <bean:message key="prompt.officerName"/>
					<td class="formDeLabel"><bean:message key="prompt.deptCode"/>
					<td class="formDeLabel"><bean:message key="prompt.departmentName"/>
					<td class="formDeLabel"><bean:message key="prompt.other"/> <bean:message key="prompt.id"/>&nbsp;#
					<td class="formDeLabel"><bean:message key="prompt.badge"/>&nbsp;#
					<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
					<td class="formDeLabel">Mgr&nbsp;<bean:message key="prompt.userId" />
					<td class="formDeLabel"><bean:message key="prompt.status"/>
			
				</tr>

				<tr>
					<td><bean:write name="officerForm" property="lastName"/>, <bean:write name="officerForm" property="firstName"/> <bean:write name="officerForm" property="middleName"/>
					<td ><bean:write name="officerForm" property="departmentId"/></td>
					<td ><bean:write name="officerForm" property="departmentName"/></td>
					<td ><bean:write name="officerForm" property="otherIdNum"/></td>
					<td ><bean:write name="officerForm" property="badgeNum"/></td>
					<td ><bean:write name="officerForm" property="userId"/></td>
					<td ><bean:write name="officerForm" property="managerId"/></td>
					<td ><bean:write name="officerForm" property="status"/></td>
				
				</tr>
			</table>
		</td>
	</tr>

</table>

<%-- BEGIN INQUIRY TABLE --%>
	 	<div class='spacer'></div>
			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' class='detailHead'>
						<table width='100%' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='1%' class='detailHead'>
								  <a href="javascript:showHideMulti('TSD Training', 'pChar', 1, '/<msp:webapp/>')">
			   					<img id="imgId" border='0' src="/<msp:webapp/>images/expand.gif" name="TSD Training"/></a>
										TSD Training
									<logic:empty name="officerForm" property="officerTraining">
										(No TSD Training)
									</logic:empty>
								</td>
							</tr>
						</table>
					</td> 
				</tr>
				<tr id="pChar0" class='hidden'>
					<td valign='top'>
					<div class='scrollingDiv200'>
						<table width='100%' cellpadding='2' cellspacing='0'>
						<tr bgcolor='white'>
								<td valign='top' width='100%'>
									<table width='100%' cellpadding='4' cellspacing='1'>
										<logic:notEmpty name="officerForm" property="officerTraining">
											<thead>
											<tr>
												<td class="hidden">
            										<jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="sortOrder" primarySortType="INTEGER" defaultSortOrder="DESC" sortId="1" />
            									</td>
												<td class="formDeLabel">
													<bean:message key="prompt.trainingTopic"/>
													<jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="trainingDescription" primarySortType="STRING" defaultSortOrder="DESC" sortId="2"/> 
												</td>
												<td class="formDeLabel">
													<bean:message key="prompt.trainingBegDate"/>
													 <jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="trainingBeginDate" primarySortType="DATE" defaultSortOrder="DESC" sortId="3"/>
												</td>
												<td class="formDeLabel">
													<bean:message key="prompt.trainingEndDate"/> 
													<jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="trainingEndDate" primarySortType="DATE" defaultSortOrder="DESC" sortId="4"/>
												</td>
												<td class="formDeLabel">
													<bean:message key="prompt.trainingHours"/>
													<jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="trainingHours" primarySortType="INTEGER" defaultSortOrder="DESC" sortId="5"/>
												</td>  											
											</tr>
											</thead>
											<tbody>
											<logic:iterate id="trainingIndex" name="officerForm" property="officerTraining" indexId="index">	
												<bean:size id="pcharSize" name="officerForm" property="officerTraining"/>		
												
												<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%> " height="100%">
												
													<td class="hidden">
 														<bean:write name="trainingIndex" property="sortOrder" /> 
													</td>
													<td>
														<bean:write name="trainingIndex" property="trainingDescription" />
														
													</td>
													<td>
														<bean:write name="trainingIndex" property="trainingBeginDate" formatKey="date.format.mmddyyyy"/> 
														
													</td>
													<td>
														<bean:write name="trainingIndex" property="trainingEndDate" formatKey="date.format.mmddyyyy"/>
														
													</td>
													<td>
														<bean:write name="trainingIndex" property="trainingHours" /> 
													</td>
													</div>
												</tr>	
																				 
											</logic:iterate>
											</tbody>
											
										</logic:notEmpty>
									</table>
								</td>
							</tr>
								
						</table>
						</div>
					</td>
				</tr>
				
		 </table> 
 <!--   <span id="trainingSpan"> -->
  	<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
 	  <tr> 
            <td class="detailHead" colspan="4" >&nbsp;TSD Training Entry</td> 
      </tr>
   	  <tr> 
       <td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.trainingTopic"/></td>
     <td class='formDe'> 
	      <html:select name="officerForm" property="trainingTopicId" styleId="trainingTopicId" >
		  <html:option value="0">Please Select</html:option>
	      <html:optionsCollection name="officerForm" property="trainingTopics" value="trainingTopicId" label="description"/>
	      </html:select>			
	</td>
     </tr>     
     <tr>
     	<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.trainingBegDate"/></td>
     	 <td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" styleId="beginDate" property="trainingBegDate" size="10" maxlength="10"/>
	  </td> 
     </tr>
     <tr>
     	<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.trainingEndDate"/></td>
     	<td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" property="trainingEndDate" styleId="endDate" size="10" maxlength="10"/>
	  </td>  
     </tr>       
     <tr>
     	<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.trainingHours"/></td> 
     	<td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" property="trainingHours" styleId="trainingHrs" size="5" maxlength="5"/>
	  </td> 
     </tr>        
    </table>
<!--     </span> -->

<%-- BEGIN BUTTON TABLE --%>

<table width="100%"> 
    <tr>
    	   <br />
	    <td align="center">
	    <input type="submit" id="submitBtn" value="<bean:message key='button.addSelected'/>" name="submitAction"/>
			<input type="submit" value="<bean:message key='button.cancel'/>" name="submitAction" 
						onclick="javascript:changeFormActionURL('/<msp:webapp/>submitOfficerProfileUpdate.do?submitAction=<bean:message key="button.cancel"/>', true);">			
		</td> 		

   </tr>
</table>
<html:hidden  name="officerForm" property="officerProfileId" styleId="officerID"/>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>