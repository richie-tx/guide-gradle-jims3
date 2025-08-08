<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 11/18/2005		AWidjaja Create JSP--%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Button security features --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/16/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to New Assessment button --%>
<%-- 08/25/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- benefitsAssessmentCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayBenefitsAssessmentCreate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|7"> 

  <%-- BEGIN HEADING TABLE --%> 
  <table width='100%'> 
    <tr> 
      <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.benefitsAssessments"/></td> 
    </tr> 
  </table> 
  <%-- END HEADING TABLE --%> 
  <br> 
  <%-- BEGIN INSTRUCTION TABLE --%> 
  <table align="center" width="98%" border="0"> 
    <tr> 
      <td> <ul> 
          <li>Click on hyperlinked Entry Date to view Benefits Assessment Details.</li>  
          </ul></td> 
    </tr> 
  </table> 
  <%-- END INSTRUCTION TABLE --%> 

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>
    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				

		 <%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="benefitsAssess"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
									  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
								  </tr>
							  	</table>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>
				
					  <%-- BEGIN DETAIL TABLE --%> 
											  
						<%-- Begin Pagination Header Tag--%>
						<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
						
						<pg:pager
							 index="center"
							 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
							 maxIndexPages="10"
							 export="offset,currentPageNumber=pageNumber"
							 scope="request">
							 <input type="hidden" name="pager.offset" value="<%= offset %>">
						<%-- End Pagination header stuff --%>

						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align=center>
							<tr>
								<td colspan=4 class=detailHead><bean:message key="prompt.previousBenefitsAssessments"/></td>
							</tr>
							<tr>
								<td>
									<table cellpadding=2 cellspacing=1 width='100%'>
									<logic:notEmpty name="juvenileCasefileForm" property="previousBenefitsAssessments">
										<tr class=formDeLabel>
											<%--td valign=top></td--%>
											<td valign=top><bean:message key="prompt.entryDate"/></td>
											<td valign=top><bean:message key="prompt.name"/></td>
											<td valign=top><bean:message key="prompt.eligibilityType"/></td>
											<td valign=top><bean:message key="prompt.eligible"/></td>
											<td valign=top><bean:message key="prompt.receiving"/></td>
											
										</tr>
										
											<logic:iterate indexId="prevAssessmentIndex" id="prevAssessmentIter" name="juvenileCasefileForm" property="previousBenefitsAssessments">
												<%-- Begin Pagination item wrap --%>
								  <pg:item>
												<tr <%
													if(prevAssessmentIndex.intValue() % 2 != 0)
														out.println("bgcolor=#f0f0f0");
													%>
												height="100%">
													<td align='left' valign=top><a href="/<msp:webapp/>displayBenefitsAssessmentView.do?currentAssessment.assessmentId=<bean:write name='prevAssessmentIter' property='assessmentId'/>"><bean:write name="prevAssessmentIter" property="entryDate" formatKey="date.format.mmddyyyy"/></a></td>													
													
													<%-- Define the values as local variables for scripting--%>
													<bean:define id="isEligibleForMedicaid" name="prevAssessmentIter" property="eligibleForMedicaid" type="java.lang.Boolean"/>
													<bean:define id="isReceivingMedicaid" name="prevAssessmentIter" property="receivingMedicaid" type="java.lang.Boolean"/>
													<bean:define id="isEligibleForTitleIVe" name="prevAssessmentIter" property="eligibleForTitleIVe" type="java.lang.Boolean"/>
													<bean:define id="isReceivingTitleIVe" name="prevAssessmentIter" property="receivingTitleIVe" type="java.lang.Boolean"/>
													
													
													<td align='left' valign=top><bean:write name="prevAssessmentIter" property="guardianName"/></td>
													<td align='left' valign=top><bean:message key="prompt.medicaid"/></td>
													
													
													<td align='left' valign=top><%out.println(UIUtil.getYesNo(isEligibleForMedicaid.booleanValue(), false));%></td>
													<td align='left' valign=top><%out.println(UIUtil.getYesNo(isReceivingMedicaid.booleanValue(), false));%></td>
												</tr>
												<tr <%
													if(prevAssessmentIndex.intValue() % 2 != 0)
														out.println("bgcolor=#f0f0f0");
													%>
												height="100%">
													<td valign=top></td>
													<td valign=top></td>
													<td align='left' valign=top><bean:message key="prompt.titleIVECandidacy"/></td>
													<td align='left' valign=top><%out.println(UIUtil.getYesNo(isEligibleForTitleIVe.booleanValue(), false));%></td>
													<td align='left' valign=top><%out.println(UIUtil.getYesNo(isReceivingTitleIVe.booleanValue(), false));%></td>
												
												</tr>
												 </pg:item>
									<%-- End Pagination item wrap --%>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="juvenileCasefileForm" property="previousBenefitsAssessments">
											<tr class=formDeLabel>									
												<td valign=top>None</td>
											</tr>											   
											
										</logic:empty>										
									</table>
									
									<%-- Begin Pagination navigation Row--%>
									<table align="center">
  									<tr>
    									<td>
    										<pg:index>
    											<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
    												<tiles:put name="pagerUniqueName" value="pagerSearch"/>
    												<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
    											</tiles:insert>
    										</pg:index>
    										<%-- End Pagination navigation Row--%>
    									 </td>
  									 </tr>
									 </table>
								
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
       					<logic:empty name="juvenileCasefileForm" property="previousBenefitsAssessments">
																		   
							<tr>
								<td align="center">
								<table width="100%" cellpadding="2" cellspacing="1">

									<%-- BEGIN Family Financial Info --%>
        								 <nested:iterate id="guardianList" name="juvenileFamilyForm" property="currentConstellation.guardiansList">										
        								 	  <div class='spacer'></div>
        										<table width='98%' cellspacing=0 cellpadding=2 border=0 class=borderTableBlue> 
        										  <tr> 
                                 <td valign=top class=detailHead colspan=5><bean:message key="prompt.family"/> <bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/> 
                                 <bean:write name="guardianList" property="name"/></td> 
                               </tr>
                              <tr>
    													   <td valign="top" colspan="4">
                                  <tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
                                  <tiles:put name="familyFinancialInfo" beanName="guardianList" />	
                                  </tiles:insert>
                                  </td>
                               </tr> 
        										</table>
														<div class='spacer'></div>
        								 </nested:iterate>
								</table>
								<div class='spacer'></div>
								</td>
							</tr>
						</logic:empty>
						<%-- Begin Pagination Closing Tag --%>
						</pg:pager>
						<%-- End Pagination Closing Tag --%>
						
						  <%-- BEGIN BUTTON TABLE --%>
						  
						  <table border="0" align="center"> 
						    <tr> 
							     <td align="center">
						    		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp;
							     	<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
								     	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_BENASSESS_U%>'>	 
											<html:submit property="submitAction"><bean:message key="button.requestNewAssessment"/></html:submit>
								     	</jims2:isAllowed>
							     	</logic:equal>
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

					  <%-- END DETAIL TABLE --%> 
  				</td>
  			</tr>
  		</table>
  	</td>
	</tr>
</table>

<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div> 

<%-- Display javascript alert that notification has been sent--%>

<logic:messagesPresent message="true">
  <html:messages id="message" message="true">
  	<script  type='text/javascript'>alert('<bean:write name="message"/>');</script>
  </html:messages>
</logic:messagesPresent>

</html:form> 
</body>
</html:html>
