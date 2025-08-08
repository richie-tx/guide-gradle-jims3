<!DOCTYPE HTML>
<%-- Summary & Confirmation screen of Job Create --%>
<%--MODIFICATIONS --%>
<%-- 06/13/2005	HRodriguez	Create JSP --%>
<%-- 06/28/2007 LDeen		Defect #42874-change Back button to Back to Jobs List --%>
<%-- 07/14/2015 R Capestani #27637 Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Jobs UI) --%>

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

<%-- STYLE SHEET LINK --%>
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvTabInterview - juvenileInterviewInfoJobCreateSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">


<logic:equal name="juvenileJobForm" property="action" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|198">
</logic:equal>
<logic:equal name="juvenileJobForm" property="action" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|199">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header">
      <logic:equal name="juvenileJobForm" property="action" value="summary">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Job Summary  				
          <tr>
            <td>
     					<ul>
                <li>Select the Finish button to continue.</li>
              </ul>
  					</td>
          </tr>
      </logic:equal>

      <logic:equal name="juvenileJobForm" property="action" value="confirm">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -&nbsp;Create Job Confirmation   				
        <tr>
          <td class="confirm">Job Information successfully created.</td>
        </tr>
      </logic:equal>
    </td>
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
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert>				
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
							<html:form action="/submitJuvenileJobsCreate" target="content">		
								<tr>
									<td valign='top' align='center'>																		
						            	<%-- BEGIN JOB TABLE --%>
						            	<div class='spacer'></div>																		
										<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">						 
											<tr class='detailHead'>
												<td align='left' class='detailHead' colspan="2"><bean:message key="prompt.juvenileEmploymentInfo" /></td>																
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.employmentStatus" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="employmentStatusDescription" /></td>
											</tr>
											<tr>									
												<td class='formDeLabel'><bean:message key="prompt.placeEmployed" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="employmentPlace" /></td>			
											</tr>		
											<tr>									
								              	<td class='formDeLabel'><bean:message key="prompt.salary" /></td>
								              	<td class='formDe'>$<bean:write name="juvenileJobForm" property="salary" formatKey="currency.format"/></td>
								            </tr>
								            <tr>								
								              	<td class='formDeLabel'><bean:message key="prompt.salary" /> Rate</td>
								               <td class='formDe'><bean:write name="juvenileJobForm" property="salaryRateDescription" /></td>
								            </tr>				
											<tr>
												<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.supervisorName" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="supervisorLastName" /><logic:notEqual name="juvenileJobForm" property="supervisorLastName" value=""><logic:notEqual name="juvenileJobForm" property="supervisorFirstName" value="">,</logic:notEqual></logic:notEqual> 
								                 	<bean:write name="juvenileJobForm" property="supervisorFirstName" />
								                 	<bean:write name="juvenileJobForm" property="supervisorMiddleName" /> 
												</td>
											</tr>
											<tr>				
												<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.supervisorFamilyMember#" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="supervisorFamilyNum" />
													<a href="/<msp:webapp/>displayFamilyMber.do?familyMemberNum=<bean:write name="juvenileJobForm" property="supervisorFamilyNum"/>"></a>
												</td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.hoursWorkedPerWeek" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="workHours" /></td>			
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.jobDescription" /></td>
												<td class='formDe'><bean:write name="juvenileJobForm" property="jobDescription" /></td>				    
											</tr>
										</table>
										<%-- END JOB TABLE --%>	
										<%-- BEGIN BUTTON TABLE --%>
										<div class='spacer'></div>
						        		<table	align='center'>
						          			<tr>		
						            			<logic:equal name="juvenileJobForm" property="action" value="summary">
						              				<html:form action="/submitJuvenileJobsCreate" target="content">
										                <td>   		 		
										                  	<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>								    
										                  	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">				
										                    	<bean:message key="button.finish"></bean:message>
										                  	</html:submit>
										                  	<html:button property="button.back" onclick="history.back();"><bean:message key="button.cancel"></bean:message></html:button>
										              	</td>
									              	</html:form>
						            			</logic:equal>					    
									            <logic:equal name="juvenileJobForm" property="action" value="confirm">
									                <td>
									                  <input type="button" name="submitAction" 
									                  	value="<bean:message key='button.backToJobsList'/>" 
								                  		onclick="changeFormActionURL('/<msp:webapp/>displayJuvenileJobList.do?juvnum=<bean:write name="juvenileJobForm" property="juvenileId"/>',true);">
									                </td>
									            </logic:equal>					    	    	
						          			</tr>
						        		</table>
									    <div class='spacer'></div>
										<%-- END BUTTON TABLE --%>										
									</td>
								</tr>
							</html:form>
							<%-- END FORM --%>
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
    	</td>
	</tr>
</table>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
