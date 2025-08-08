<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 04/26/2011 Debbie Williamson	Create JSP--%>
<%-- 08/24/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


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
<title>Juvenile Casework - petitionCourt.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!-- Javascript for emulated navigation -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

</head>

<body topmargin=0 leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|352">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/>&nbsp;-&nbsp;<bean:message key="title.juvenileProfile"/>&nbsp;-&nbsp;<bean:message key="prompt.court"/>
                                       <bean:message key="prompt.orders"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Select a hyperlinked Court Order to view its details in a pop-up window.</li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<%-- BEGIN CASEFILE HEADER INFO --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert>
<%-- END CASEFILE HEADER INFO --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<%--tabs start--%>
					<td valign="top"><tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="referralstab" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
					</td>
					<%--tabs end--%>
				</tr>
				<tr>
					<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align=center>
  				<!-- BEGIN TABLE -->  				
	            <div class='spacer'></div>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
								    <!--tabs start-->
									  	<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false"> 
 										    <tiles:insert page="../caseworkCommon/juvenileProfilePetitionTabs.jsp" flush="true">
		  					  				    <tiles:put name="tabid" value="courtOrder"/>
		  					  				    <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
		  					  			    </tiles:insert>
	  					  			    </logic:equal>	
									  	<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="true"> 
 										    <tiles:insert page="../caseworkCommon/referralTabs.jsp" flush="true">
		  					  			    </tiles:insert>
	  					  			    </logic:equal>	
									  <!--tabs end-->
									</td>
								</tr>
								<tr>
							  	<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
							  </tr>
							</table>
								<%-- BEGIN DETAILS --%>
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
						      <tr>
								<td valign="top" align=center>
            						<!-- begin court order table -->
												<div class=spacer></div>
              					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              						<tr>
              							<td class=detailHead><bean:message key="prompt.court"/>&nbsp;<bean:message key="prompt.orders"/>&nbsp;for Petition # -&nbsp;<bean:write name="legacyCourtOrdersForm" property="petitionNum"/></td>
              						</tr>
              						<tr>
                						<td>
                							<table cellpadding=2 cellspacing=1 width='100%'>
                							<logic:empty name="legacyCourtOrdersForm" property="courtOrders"> 
										       <tr class='detailHead'>
													<td colspan="4">No Court Orders Available</td>
										       </tr>
											</logic:empty>

											<logic:notEmpty name="legacyCourtOrdersForm" property="courtOrders">
                								<tr class="formDeLabel">
                									<td class=subHead><bean:message key="prompt.court"/>&nbsp;<bean:message key="prompt.date"/>
                									  <div>
									                    <jims2:sortResults beanName="legacyCourtOrdersForm" results="courtOrders" primaryPropSort="courtDate" primarySortType="DATE"  defaultSort="true" defaultSortOrder="DESC" sortId="1" />
								                      </div>
								                    </td>  
                									<td class=subHead><bean:message key="prompt.court"/>
                									  <div>
								                        <jims2:sortResults beanName="legacyCourtOrdersForm" results="courtOrders" primaryPropSort="juvenileCourt" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
							                          </div>
							                        </td>  
                									<td class=subHead><bean:message key="prompt.hearingType"/>
                									  <div>
								                        <jims2:sortResults beanName="legacyCourtOrdersForm" results="courtOrders" primaryPropSort="hearingTypeDescription" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
							                          </div>
							                        </td>  
                									<td class=subHead><bean:message key="prompt.respondentAttorney"/>
                									  <div>
								                        <jims2:sortResults beanName="legacyCourtOrdersForm" results="courtOrders" primaryPropSort="respondentAttorneyName" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
							                          </div>
							                        </td>  
                									<td class=subHead><bean:message key="prompt.offense"/>
                									  <div>
								                        <jims2:sortResults beanName="legacyCourtOrdersForm" results="courtOrders" primaryPropSort="juvenileOffenseCodeDescription" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							                          </div>
							                        </td>  
                							    </tr>
                							<logic:iterate indexId="index" id="coIndex" name="legacyCourtOrdersForm" property="courtOrders" >	    
                							  <tr  class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                							       <td valign=top><a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayLegacyCourtOrderDetails.do?submitAction=Link&petitionNum=<bean:write name="coIndex" property="petitionNum"/>&courtOrderID=<bean:write name="coIndex" property="courtOrderID" />',500, 800);"><bean:write name="coIndex" property="courtDate" formatKey="date.format.mmddyyyy"/></a></td>	    
                									<td valign=top><bean:write name="coIndex" property="juvenileCourt"/></td>
                									<td valign=top><bean:write name="coIndex" property="hearingTypeDescription"/></td>
                									<td valign=top><bean:write name="coIndex" property="respondentAttorneyName"/></td>
                									<td valign=top><bean:write name="coIndex" property="juvenileOffenseCodeDescription"/></td>
                							  </tr>
                							</logic:iterate>     
                							  </logic:notEmpty>
                							</table>
										</td>
									</tr>
												</table>
            						<!-- end court order table -->

						<div class=spacer4px></div>						
                        <!-- BEGIN BUTTON TABLE -->
                        <table border="0" cellpadding=1 cellspacing=1 align=center>
                          <tr>
                            <td align="center">
                              <html:form action="/displayJuvenileProfileReferralList.do?submitAction=Referrals">
                                 <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
                            		<bean:message key="button.back"></bean:message>
                            	 </html:button>
                                 <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                              </html:form>
                            </td>
                          </tr>
                        </table>
                        <!-- END BUTTON TABLE -->
                       
            					</td>
            			  </tr>
            			</table>
            			<div class=spacer4px></div>
      					</td>
      			  </tr>
      			</table>
      			<div class=spacer4px></div>
					</td>
			  </tr>
			</table>
   	<%--td>
  </tr>
</table--%>
<!-- END DETAIL TABLE -->

<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

