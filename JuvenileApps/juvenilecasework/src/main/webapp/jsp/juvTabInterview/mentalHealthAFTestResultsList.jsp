<!DOCTYPE HTML>
<!-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page -->
<!--MODIFICATIONS -->
<!-- 01/18/2007	Debbie Williamson	Create JSP -->
<!-- 07/20/2009  C Shimek   - #61004 added timeout.js  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!-- Javascript for emulated navigation -->
<title><bean:message key="title.heading"/> - mentalHealthAFTestResultsList.jsp</title>
<!-- Javascript for emulated navigation -->
<script language="JavaScript" src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

<script>/*scheduled for deletion
var currentSelectedValue = "";
function setSelectedVal(testId){
 currentSelectedValue = testId;
}
function loadView(file, actionVal){
	
	var myURL=file  + "&selectedValue=" + currentSelectedValue;	
	load(myURL,top.opener);
	window.close();
}
function load(file,target) {
   
        window.location.href = file;
}*/
</script>
</head> 
<!--END HEADER TAG-->

<body topmargin="0" leftmargin="0" >
<html:form action="/displayMentalHealthAdaptiveFuncSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|333">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/>  
                                     </td>
  </tr>
  <tr>
  	  <td align="center" class="header"><bean:message key="prompt.adaptiveFunctioning"/> 
                                       <bean:message key="prompt.test"/> 
                                       <bean:message key="prompt.results"/> 
                                       <bean:message key="prompt.list"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
         <li>Click on hyperlinked Test Date to view details.</li>
      </ul>
    </td>
  </tr> 
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->

<!--juv profile header start-->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<!--juv profile header end-->

<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
          <!--tabs start-->
              	<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
      				<tiles:put name="tabid" value="interviewinfotab"/>
      				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
      			</tiles:insert>	
          <!--tabs end-->
          </td>
        </tr>
        <tr>
		  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
		  </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
            <!-- BEGIN TABLE -->
						<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign="top">
  									    <!--tabs start-->
  												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="mentalhealthtab"/>
												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert>	
  										  <!--tabs end-->
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
									  </tr>
  								</table>

  									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  										<tr>
  											<td valign="top" align="center">
												<div class='spacer'></div>
  												<table width='98%' border="0" cellpadding="0" cellspacing="0">
  													<tr>
  														<td valign="top">
  															<!--tabs start-->
  																<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="adaptivefunctioning"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
  															<!--tabs end-->
  														</td>
  													</tr>
  													<tr>
												  	  <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												    </tr>
  												</table>
													
  												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
  												  <tr>
  												  	<td align="center">
															<div class='spacer'></div>
  												  		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  																<tr>
  																	<td colspan=4 class="detailHead">Adaptive Functioning Test Results</td>
  																</tr>
  																<tr>
  																	<td>
  																		<table width='100%' border="0" cellpadding="2" cellspacing="1">
  																		<logic:empty name="testingSessionForm" property="afResultsList"> 
																		    <tr class="detailHead">
																				<td colspan="4">No Previous Adaptive Functioning Test Results Available.</td>
																		    </tr>
																		</logic:empty>
																		<logic:notEmpty name="testingSessionForm" property="afResultsList"> 
																			<tr>
																				<td>
																					<table width="100%" border="0" cellpadding="2" cellspacing="1">
																		                <tr class="formDeLabel">  
																		                  <td valign="top"><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
																		                  <td valign="top"><bean:message key="prompt.programReferral#"/></td>
																		                  <td valign="top"><bean:message key="prompt.serviceProvider"/></td>
																		                   <td valign="top"><bean:message key="prompt.instructorName"/></td>
																		                  <td valign="top"><bean:message key="prompt.test"/> <bean:message key="prompt.name"/></td>
																		                  <td valign="top"><bean:message key="prompt.standardScore"/></td>
																		                </tr>
														                                    <!-- Begin Pagination Header Tag-->
																							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
																							<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
																							   		 maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
																				   			 <input type="hidden" name="pager.offset" value="<%= offset %>">
  																							<!-- End Pagination header stuff -->
  																							<% int RecordCounter = 0; %>	
  																								<logic:iterate id="resultsIndex" name="testingSessionForm" property="afResultsList" >
  																									<!-- Begin Pagination item wrap -->																										
  																			                      <pg:item>
  																									<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
    																                  <td valign="top"><a href="/<msp:webapp/>displayMentalHealthAdaptiveFuncSummary.do?submitAction=View&selectedValue=<bean:write name="resultsIndex" property="testId"/>">
                                                         <bean:write name="resultsIndex" property="testDate" formatKey="date.format.mmddyyyy" /></a>
  																										</td>
    																                  <td valign="top"><bean:write name="resultsIndex" property="programReferralNum" /></td>
    																                  <td valign="top"><bean:write name="resultsIndex" property="serviceProviderName" /></td>
    																                  <td valign="top"><bean:write name="resultsIndex" property="instructorName" /></td>																								                    
    																                  <td valign="top"><bean:write name="resultsIndex" property="testName" /></td>
    																                  <td valign="top"><bean:write name="resultsIndex" property="standardScore" /></td>
    																                </tr>
  																							  </pg:item>
        																			   <!-- End Pagination item wrap -->
        					                            </logic:iterate>
							  							              </table>
							  							             </td>
							  												</tr>
							  											</table>
																		<!-- Begin Pagination navigation Row-->
																		<table align="center">
  																		<tr>
    																		<td>
    																			<pg:index>
      																				<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
      																					<tiles:put name="pagerUniqueName" value="pagerSearch"/>
      																					<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
      																				</tiles:insert>
      																		 	</pg:index>
    																	    </td>
  																	    </tr>
																	    </table>
																			<div class='spacer'></div>
																					    
																			</pg:pager>
																		</logic:notEmpty>					
                              </table>

			                        <!-- BEGIN BUTTON TABLE -->
			                        <div class='spacer'></div>
			                        <table border="0" width="100%">
			                          <tr>
			                            <td align="center">
			                            	<html:submit property="submitAction">
			                 								<bean:message key="button.back"></bean:message>
			                 							</html:submit>									
			                							<html:submit property="submitAction">
			                								<bean:message key="button.cancel"></bean:message>
			                 							</html:submit>
			                            </td>
			                          </tr>
			                        </table>
			                        <!-- END BUTTON TABLE -->
                            </td>
                          </tr>
                        </table>
                        <div class="spacer"></div>
                      </td>
                    </tr>
                  </table>
					<div class='spacer'></div>
                </td>
              </tr>
            </table>
						<!-- END TABLE -->
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- END DETAIL TABLE -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>
