<!DOCTYPE HTML>
<%-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page --%>
<%--MODIFICATIONS --%>
<%-- 01/18/2007	Debbie Williamson	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to Add Hospitalization History button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - mentalHealthHospitalizationList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>



</head> 
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleMentalHealthHospitalizationSelection" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|195">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/> - 
			<bean:message key="prompt.hospitalization"/> <bean:message key="prompt.list"/>
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on hyperlinked Admit Date to view hospitalization details.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
						<%--script type='text/javascript'>renderTabs("Interview Info")</script--%>
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
<%-- BEGIN TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign="top">
<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="mentalhealthtab"/>
												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert>	
											<%--script type='text/javascript'>renderInterviewInfo("Mental Health")</script--%>
<%--tabs end--%>
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
															<%--tabs start--%>
																<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="hospitalization"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
															<%--script type='text/javascript'>renderTestResultsTabs("Hosp")</script--%>
															<%--tabs end--%>
														</td>
													</tr>
													<tr>
												  	  <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
												    </tr>
												</table>

									<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
  									<tr>
  										<td valign="top" align="center">
                				<% int RecordCounter = 0; %>												
  											<div class='spacer'></div>
  											<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  												<tr>
  													<td class="detailHead"><bean:message key="prompt.hospitalization"/></td>
  												</tr>

               						<logic:empty name="hospitalizationForm" property="hospitalizationList">
													  <tr>
														  <td colspan="4">
															  <table width='100%'>
                  						    <tr class="detailHead">
                    								<td>No Hospitalization Data Available.</td>
                  						    </tr>
																</table>
															</td>
														</tr>
              						</logic:empty>

               						<logic:notEmpty name="hospitalizationForm" property="hospitalizationList"> 
  												<tr>
  													<td>
  														<table width='100%' border="0" cellpadding="2" cellspacing="1">
  							                <tr class="formDeLabel">  
  							                  <td valign="top"><bean:message key="prompt.admissionDate"/></td>
  							                  <td valign="top"><bean:message key="prompt.facility"/></td>
  							                  <td valign="top"><bean:message key="prompt.admittingPhysician"/></td>
  							                  <td valign="top"><bean:message key="prompt.releaseDate"/></td>
  							                </tr>

                              <%-- Begin Pagination Header Tag--%>
                              <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
                              <pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
                                 		 maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

                         			 <input type="hidden" name="pager.offset" value="<%= offset %>">
                                <%-- End Pagination header stuff --%>

                  							<logic:iterate id="hospIndex" name="hospitalizationForm" property="hospitalizationList" >
                  								<%-- Begin Pagination item wrap --%>
                  								<bean:define id="hospId" name="hospIndex" property="hospitalizationId"/>
         		                      <pg:item>
                    								<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
        							                  <td valign="top"><a href="/<msp:webapp/>handleMentalHealthHospitalizationSelection.do?submitAction=View&selectedValue=<bean:write name="hospIndex" property="hospitalizationId"/>">
                                           <bean:write name="hospIndex" property="admissionDate" formatKey="date.format.mmddyyyy" /></a>
																				</td>
        							                  <td valign="top"><bean:write name="hospIndex" property="facilityName" /></td>
        							                  <td valign="top"><bean:write name="hospIndex" property="admittingPhysicianName" /></td>
        							                  <td valign="top"><bean:write name="hospIndex" property="releaseDate" formatKey="date.format.mmddyyyy" /></td>
        							                </tr>
                    							  </pg:item>
                              		   <%-- End Pagination item wrap --%>
                                </logic:iterate>
    							            </table>
    							          </td>
    										  </tr>
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
                    		    </td>
                   		    </tr>
                 		    </table>
                  		    
                      </pg:pager>
                    </logic:notEmpty>
                  		<%-- End Pagination navigation Row--%>


                        <%-- BEGIN BUTTON TABLE --%>
												<div class='spacer'></div>
                        <table border="0" width="100%">
                          <tr>
                            <td align="center">
                            	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_HOSP_U%>'> 			
                					<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
                					<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
                						<html:submit property="submitAction" ><bean:message key="button.addHospitalizationHistory" /></html:submit>
                					</logic:notEqual>
                					<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
										<html:submit property="submitAction" ><bean:message key="button.addHospitalizationHistory" /></html:submit>
									</jims2:isAllowed>
									</logic:equal>				
                					</logic:equal>	
               					</jims2:isAllowed>
           						<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>
                            </td>
                          </tr>
                        </table>
                        <%-- END BUTTON TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
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
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>