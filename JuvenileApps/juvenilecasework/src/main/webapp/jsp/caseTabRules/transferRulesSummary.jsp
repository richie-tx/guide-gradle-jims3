<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects casefile and selects Next button--%>
<%-- 02/21/2018	UGopinath		Create JSP --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %> 
<%@ page import="naming.UIConstants" %> 




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - transferRules.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitCasefileRulesTransfer" target="content">

<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.SUMMARY%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|239">
</logic:equal>
<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.CONFIRM%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|240">
</logic:equal>     

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Casefile Rules Transfer 
		 <logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.SUMMARY%>" >
             <bean:message key="title.summary" />
        </logic:equal>
        <logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.CONFIRM%>" >
             <bean:message key="title.confirmation" />
        </logic:equal> 
		</td>
	</tr>
	 <logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.CONFIRM%>" >
	  <tr>
	    <td align="center" class="confirm">Rule has been transferred.</td>
	  </tr>
  </logic:equal>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

	
<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.SUMMARY%>" >      
			<ul>
        		<li>Review information, then select the Finish button to save the information.</li>	    
        		<li>Select the Back button to return to the previous page to change information.</li>
      		</ul>
      		</logic:equal>
      		<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.CONFIRM%>" >
	      	<ul>
	        	<li>Select Return to Casefile Rules button to return to Casefile Rules list.</li>
	      	</ul>
		</logic:equal>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>


<%-- END CASEFILE HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">

      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
      <%--tabs end--%>

			<%-- BEGIN DETAIL TABLE --%>
			
					<div class="spacer"></div>
					<%-- BEGIN RULES TABLE --%>
					<%-- Begin Pagination Header Tag--%>
						<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
						
						<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
						
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				
				<tr>
					<td valign="top" align="center">
					<div class="spacer"></div>
					<%-- BEGIN RULES TABLE --%>
					
			  		<table cellpadding="2" cellspacing="1" width="98%" class="borderTableBlue">
			  			<tr>
							<td colspan="8" class="detailHead">Rule(s) to be transferred</td>
						</tr>
			  			<%int RecordCounter = 0; String bgcolor = "";%>			  		
			  				<tr bgcolor="#cccccc">
			  					<td class="subHead" nowrap><bean:message key="prompt.rule" /> <bean:message key="prompt.id" /></td>
			  					<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.name" /></td>
			  					<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.type" /></td>
			  					<td class="subHead"><bean:message key="prompt.standard" /></td>
			  					<td class="subHead"><bean:message key="prompt.monitor"/> <bean:message key="prompt.frequency" /></td>
			  					<td class="subHead"><bean:message key="prompt.status" /> <bean:message key="prompt.change" />&nbsp;<bean:message key="prompt.date" /></td>
			  					<td class="subHead"><bean:message key="prompt.completion" /> <bean:message key="prompt.status" /></td>
			  				</tr>
			
			  				<logic:iterate id="ruleIndex" name="juvenileCaseworkConditionsForm" property="selectedRules" >
			     				<%-- Begin Pagination item wrap --%>
			      		
			      					<tr class=<%RecordCounter++;  
											  bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
			      						out.print(bgcolor);%>
											>
									
			      						<td>
												  <a href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display Rule Details&selectedValue=<bean:write name="ruleIndex" property="ruleId" />">
			        						<bean:write name="ruleIndex" property="ruleId" /></a>
												</td>
			      						<td><bean:write name="ruleIndex" property="ruleName" /></td>
			      						<td><bean:write name="ruleIndex" property="ruleTypeDesc" /></td>
			      						<td><logic:equal name="ruleIndex" property="standard" value="true">
			      							 	STANDARD
			      							</logic:equal>
			      							<logic:equal name="ruleIndex" property="standard" value="false">
			      								CUSTOM
			      							</logic:equal>
			      						</td>
			      						<td><bean:write name="ruleIndex" property="ruleMonitorFreqDesc" /></td>
			      						<td><bean:write name="ruleIndex" property="completionDate" formatKey="date.format.mmddyyyy" /></td>
			      						<td><bean:write name="ruleIndex" property="completionStatus" /></td>
			      					</tr>
			      			 
			      		   <%-- End Pagination item wrap --%>
			  				</logic:iterate>
			  			
			  		</table>
					<%-- END RULES TABLE --%> 
						<%-- Begin Pagination Closing Tag --%>
						</pg:pager>	
						
					<div class='spacer'></div>
						<%-- BEGIN casefile TABLE --%>
							<%-- BEGIN CASEFILE TABLE --%>
	
							<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.rule"/>(s) <bean:message key="prompt.transfer" /> - <bean:message key="prompt.select" /> <bean:message key="prompt.casefile" /></td>
								</tr>
								<tr>
									<td>
										<table cellpadding=2 cellspacing=1 width='100%'>
											<tr bgcolor='#cccccc'>
												<td width='1%'></td>
												<td align="left" class=subHead><bean:message key="prompt.supervision" />&nbsp;#</td>
												<td align="left" class=subHead><bean:message key="prompt.sequence" />&nbsp;#</td>
												<td align="left" class=subHead><bean:message key="prompt.probationOfficer" />&nbsp;
																  <bean:message key="prompt.name" /></td>
												<td align="left" class=subHead><bean:message key="prompt.supervision" />&nbsp;
																  <bean:message key="prompt.type" /></td>
												<td align="left" class=subHead><bean:message key="prompt.caseStatus" /></td>
											</tr>
						                    <tr>
						                    	<td width='1%'></td>
						        				<td align="left"><bean:write name="juvenileCaseworkConditionsForm" property="rulesTransferCaseile.supervisionNum"/></td>
												<td align="left"><bean:write name="juvenileCaseworkConditionsForm" property="rulesTransferCaseile.sequenceNum"/></td>
												<td align="left"><bean:write name="juvenileCaseworkConditionsForm" property="rulesTransferCaseile.officerFullName"/></td>
												<td align="left"><bean:write name="juvenileCaseworkConditionsForm" property="rulesTransferCaseile.supervisionType"/></td>
												<td align="left"><bean:write name="juvenileCaseworkConditionsForm" property="rulesTransferCaseile.caseStatus"/></td>
												
											
						        			</tr>
						  				</table>
						  			</td>
						  		</tr>
						  	 </table>
						    <%-- END casefile TABLE --%>
						<%-- END casefile TABLE --%>	
						<div class='spacer'></div>			
					<%-- BEGIN BUTTONS INSIDE DETAIL TABLE --%>
					
					<div class="spacer"></div>
						<table border="0" width="100%">
							<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.SUMMARY%>" >      
					  			<tr>
									<td align="center">
					        				<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
												<bean:message key="button.back"></bean:message>
											</html:button>&nbsp;
					        	    	<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
					        			 <input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
									</td>
					   			</tr> 
							</logic:equal> 
					        <logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=UIConstants.CONFIRM%>" > 
						        <tr>
									<td align="center">
					        			<html:submit property="submitAction"><bean:message key="button.returnToCasefileRules"/></html:submit>
									</td>
					   			</tr> 
							</logic:equal>   
						</table>
					<div class="spacer"></div>
					<%-- END BUTTONS INSIDE DETAIL TABLE --%></td>
				
			<%-- END DETAIL TABLE --%>
		</td>
	</tr>
</table>
<%-- END BLUE TABS TABLE --%>

</html:form>

<div class="spacer"></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

