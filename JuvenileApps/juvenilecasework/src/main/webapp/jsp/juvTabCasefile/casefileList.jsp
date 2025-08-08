<!DOCTYPE HTML>
<%-- Used to display search casefile results --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>
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

<title>Juvenile Casework - casefileList.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|165">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.casefileList"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
		  <ul>
        <li>Click on hyperlinked Supervision Number to view casefile details.</li>
      </ul>
		</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<div class=spacer></div>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>


<%--BEGIN FORM TAG--%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				  <tr>
						<td valign=top>
							<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="casefilestab"/>
								<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
							</tiles:insert>				
						</td>
				  </tr>
				  <tr>
				  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				  </tr>
	      </table>

        <%-- BEGIN DETAIL TABLE --%>
   			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
    			<tr>
    				<td valign=top align=center>
    					<%-- begin blue tabs (2nd row) --%>
							<div class=spacer></div>	
    					<table width='98%' border="0" cellpadding="0" cellspacing="0">
    						<tr>
    							<td valign=top> 
    								<%--tabs start--%>
    								<tiles:insert page="/jsp/caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
    									<tiles:put name="tabid" value="casefileInfo"/>
    									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
    								</tiles:insert>			
    								<%--tabs end--%>
    							</td>
    						</tr>
    						<tr id='blueBottomLiner'>
    							<td bgcolor='#6699ff'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
    						</tr>
    					</table>
					
  					<%-- BEGIN Interview TABLE --%>
   					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  						<tr>
  							<td align=center>
									<div class=spacer></div>
									<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class=detailHead>Casefiles</td>
										</tr>
										<tr>
											<td>
												<table cellpadding=2 cellspacing=1 width='100%'>
													<tr bgcolor='#cccccc'>
														<td class=formDeLabel><bean:message key="prompt.supervision" />&nbsp;#</td>
														<td class=formDeLabel nowrap="nowrap" width="3%"><bean:message key="prompt.sequence" />&nbsp;#
		<!-- 38834 -->									<jims2:sortResults beanName="juvenileProfileCasefileListForm" results="juvenileProfileCasefileList" primaryPropSort="sequenceNum"   primarySortType="INTEGER"  defaultSortOrder="DESC" defaultSort="true" sortId="1" /></td>
														<td class=formDeLabel><bean:message key="prompt.probationOfficer" /></td>
														<td class=formDeLabel><bean:message key="prompt.caseStatus" /></td>
														<td class=formDeLabel><bean:message key="prompt.supervisionType"/></td>
														<td class=formDeLabel><bean:message key="prompt.assignmentDate"/></td>
														<td class=formDeLabel><bean:message key="prompt.supervision"/>&nbsp;<bean:message key="prompt.endDate"/></td>
														<td class=formDeLabel><bean:message key="prompt.controllingReferral"/></td>
														<td class=formDeLabel><bean:message key="prompt.supervisionOutcome"/></td>														
													</tr>	
	  											<logic:empty name="juvenileProfileCasefileListForm" property="juvenileProfileCasefileList">
	  												<tr><td>No casefiles for this Juvenile.</td></tr>
	  											</logic:empty>
	
													<logic:notEmpty name="juvenileProfileCasefileListForm" property="juvenileProfileCasefileList">
	  												<logic:iterate id="casefiles" name="juvenileProfileCasefileListForm" property="juvenileProfileCasefileList" indexId="indexer"> 
	  			  									<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
	    												<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
	 							  	    				<td><a onclick="spinner();" href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
	 							  	    			</logic:equal>
	
								  	    			<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
								  	    				<td><a onclick="spinner();" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<%out.print(naming.PDJuvenileCaseConstants.SUPERVISIONNUM_PARAM);%>=<bean:write name="casefiles" property="supervisionNum"/>"><bean:write name="casefiles" property="supervisionNum"/></a></td>
								  	    			</logic:notEqual>			 	
								  		  			
	  													<td><bean:write name="casefiles" property="sequenceNum"/></td>										
	  													<td><bean:write name="casefiles" property="probationOfficer"/></td>										
	  													<td><bean:write name="casefiles" property="caseStatus"/></td>
	  													<td><bean:write name="casefiles" property="supervisionType"/></td>
	  													<td><bean:write name="casefiles" property="assignmentAddDate"/></td>
	  													<td><bean:write name="casefiles" property="supervisionEndDate"/></td>
	  													<td>
	  														<span title='<bean:write name="casefiles" property="refNumWithOffense"/>'>
	  															<bean:write name="casefiles" property="controllingReferralId"/>
	  														</span>
	  													</td>	  													
	  													<td title='<bean:write name="casefiles" property="supervisionOutcomeDescriptionId"/>'><bean:write name="casefiles" property="supervisionOutcome"/></td>
	  												</tr>
													</logic:iterate>
	  										</logic:notEmpty>
											</table>
										</td>
									</tr>
								</table>
								<div class=spacer></div>	
	
							</td>
						</tr>
					</table>
					<%-- END CASEFILE TABLE --%>				 
	
					<%-- BEGIN BUTTON TABLE --%>
					<table border="0" align="center">
					  <tr align="center">
					    <td>
					    	<html:submit property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:submit>
	 						</td>
					    <html:form action="/displayJuvenileMasterInformation" target="content"> 
					   	<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
					  </tr>
					</table>
					<%-- END BUTTON TABLE --%>
				</td>
			</tr>
		</table>
	</td>
  </tr>
</table> 		
<%-- END DETAIL TABLE --%>

</html:form>
   	
<div class=spacer></div>	
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>

</html:html>

