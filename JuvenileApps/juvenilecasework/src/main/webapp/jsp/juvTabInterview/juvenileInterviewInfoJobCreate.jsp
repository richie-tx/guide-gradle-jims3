<!DOCTYPE HTML>
<%-- User selects the "Add More Jobs" button to create new job --%>
<%--MODIFICATIONS --%>
<%-- 06/13/2005	HRodriguez	Create JSP --%>
<%-- 06/28/2005 Surya 		Modified JSP --%>
<%-- 06/28/2007 LDeen		Defect #42874-change Salary Rate to new row to match PT--%>
<%-- 07/12/2007 jjose		Defect #42862-making family member supervisor num a drop down list and adding is fam member checkbox--%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 07/16/2015 R Capestani    	#27637  Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Jobs UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<meta http-equiv="X-UA-Compatible" content="IE=9, chrome=1" />
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileJobForm" />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileInterviewInfoJobCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/juvenileInterviewInfoJobCreate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript'>

function showHideSup()
{
	var myElem = document.getElementById('isSup');
	if(myElem.checked)
	{
		show('supFamily', 1, 'row');
		
		var myElemLN = document.getElementsByName('supervisorLastName')[0];
		var myElemFN = document.getElementsByName('supervisorFirstName')[0];
		var myElemMN = document.getElementsByName('supervisorMiddleName')[0];
		myElemLN.value = "";
		myElemFN.value = "";
		myElemMN.value = "";
		
		show('supNonFamily', 0, 'row');
		
	}
	else
	{
		show('supNonFamily', 1, 'row');
		
		var myElemFamSup = document.getElementsByName('supervisorFamilyNum')[0];
		myElemFamSup.selectedIndex = 0;
		show('supFamily', 0, 'row');
	}
}

function checkSupFamilyMem()
{
	var myElem = document.getElementById('isSup');
	if(myElem.checked)
	{
		var myElemFamSup = document.getElementsByName('supervisorFamilyNum')[0];
		if( myElemFamSup.options[myElemFamSup.selectedIndex].value == "" )
		{
			alert("A family member has been indicated as the supervisor and therefore a family member must be selected");
			return false;
		}
	}
	return true;
}
</script>

</head> 
<%--END HEADER TAG--%>

<html:javascript formName="juvenileJobForm"/>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onLoad="showHideSup()" topmargin='0' leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.create"/>&nbsp;Job</td> 
 	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div> 
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select the <b>Next</b> button to view Job List.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>   
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   		<td valign='top'>
   			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
					</td>
				</tr>
				<tr>
		  			<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
			  	</tr>
			</table>				          
      
      		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
      	  		<tr>
      		 		<td>
			            <html:form action="/displayJuvenileJobsCreateSummary" target="content" onsubmit="return validateJuvenileJobForm(this);">
			            <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|197">			
	      					<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			      				<tr>
			      					<td valign='top' align='center'>
										<div class='spacer'></div>
		         					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
			            				<tr>
			            					<td valign='top'>
			            						<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
			            							<tiles:put name="tabid" value="job"/>
			            							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			            						</tiles:insert>	
			            					</td>
			            				</tr>
			            				<tr>
			          			  			<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
			          			  		</tr>
			          			  	</table>
				
					      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					      				<tr>
				      						<td valign='top' align='center'>																		
							                <%-- BEGIN JOB TABLE --%>
							                	<div class='spacer'></div> 																		
					      						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
					      							<tr class='detailHead' >
					      								<td align='left' class='detailHead' colspan="4"><bean:message key="prompt.juvenileEmploymentInfo" /></td>																	
					      							</tr>
					      							<tr>
					      						    	<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.employmentStatus"/></td>
					      								<td class='formDe'>
							      				           <html:select property="employmentStatusId">
								             				      <html:option value=""><bean:message key="select.generic" /></html:option>
							      				       	      <html:optionsCollection property="empolymentStatus" value="code" label="description" /> 
							      				  	  	   </html:select> 								
					      								</td>
					      							</tr>
					      							<tr>								
					      								<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.placeEmployed" /></td>
					      								<td class='formDe'><html:text size="55" maxlength="55" property="employmentPlace" /></td>
					      							</tr>	
					
					      							<tr>								
					      								<td class='formDeLabel'><bean:message key="prompt.salary" /></td>
					      								<td class='formDe'><html:text size="10" maxlength="10" property="salary" /></td>
					      							</tr>
					      							<tr>								
					      								<td class='formDeLabel'><bean:message key="prompt.salary" /> Rate</td>
					      								<td class='formDe'>
							      				           <html:select property="salaryRateId">
								             				      <html:option value=""><bean:message key="select.generic" /></html:option>
							      				       	      <html:optionsCollection property="salaryRate" value="code" label="description" /> 
							      				  	  	   </html:select> 								
					      								</td>
					      							</tr>	
													<tr>												
					      								<td class='formDeLabel' width='1%' nowrap='nowrap'>Supervisor is Family Member</td>
					      								<td class='formDe' colspan='3'><input type="checkbox" id="isSup" onclick="showHideSup()"/></td>	
				      								</tr>
					      							<tr id="supNonFamily" class="visibleTR">
					      								<td class='formDeLabel' valign="top"><bean:message key="prompt.supervisorName" /></td>
					      								<td colspan='3' class='formDe'>
					      									<table border="0" cellpadding='2' cellspacing='1'>
					      										<tr class='formDeLabel'>
					      											<td colspan="2"><bean:message key="prompt.last" /></td>
					      											
					      										</tr>
					      										<tr class='formDe'>
					      											<td colspan="2"><html:text size="30" maxlength="75" property="supervisorLastName" /></td>								
					      										</tr>
					      										<tr class='formDeLabel'>												
					      											<td><bean:message key="prompt.first" /></td>
					      											<td><bean:message key="prompt.middle" /></td>
					      										</tr>					
					      										<tr class='formDe'>
					      											<td><html:text size="25" maxlength="50" property="supervisorFirstName" /></td>
					      											<td><html:text size="25" maxlength="50" property="supervisorMiddleName" /></td>
					      										</tr>
					      									</table>					      
					      								</td>									
					      							</tr>
					      							<tr id="supFamily" class="hidden">												
					      								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.supervisorFamilyMember#" /></td>
					      								<td class='formDe' colspan='3'>
					      									<html:select property="supervisorFamilyNum" >
							      								<html:option value=""><bean:message key="select.generic" /></html:option>
					     				       	      			<html:optionsCollection property="activeFamMembers" value="memberId" label="description" /> 
						      								</html:select>
					      								</td>	
					      							</tr>
					      							<tr>
					      								<td class='formDeLabel'><bean:message key="prompt.hoursWorkedPerWeek" /></td>
					      								<td class='formDe' colspan='3'><html:text size="20" maxlength="20" property="workHours" /></td>			
					      							</tr>
					      							<tr>
					      								<td class='formDeLabel'><bean:message key="prompt.jobDescription" /></td>
					      								<td class='formDe' colspan='3'><html:text size="55" maxlength="255" property="jobDescription" /></td>				    
					      							</tr>							
				                 				</table><%-- END JOB TABLE --%>
	
								                 <%-- BEGIN BUTTON TABLE --%>
								                 <div class='spacer'></div>
										         <table align='center'>
										             <tr>
											             <td>
											               <html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>				
											               <html:submit property="submitAction" onclick="return (validateJobFields(this.form) && checkSupFamilyMem())">				
											                  <bean:message key="button.next"></bean:message>
											               </html:submit>
											               <html:button property="button.back" onclick="history.back();"><bean:message key="button.cancel"></bean:message></html:button>
											             </td>										           </tr>
										         </table>									         
									        <%-- END BUTTON TABLE --%>						 
							            	</td>
							            </tr>
							        </table>
				          			<div class='spacer'></div>
				              		</td>
					            </tr>
	          				</table>
	          			<div class='spacer'></div><%-- END DETAIL TABLE --%>
	          			</html:form>
			    	</td>
			  	</tr>
			</table>
    	</td>
  </tr>
</table>

<%-- END FORM --%>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
