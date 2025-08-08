<!DOCTYPE HTML>
<%-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page --%>
<%--MODIFICATIONS --%>
<%-- 01/18/2007	Debbie Williamson	Create JSP --%>
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
<title><bean:message key="title.heading"/> - mentalHealthHospitalizationDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>



</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin=0 onKeyDown="return checkEnterKey(event,true)">
<html:form action="/submitMentalHealthHospitalizationCreate" target="content">
<logic:equal name="hospitalizationForm" property="actionType" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|196">
</logic:equal>
<logic:equal name="hospitalizationForm" property="actionType" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|193">
</logic:equal>
<logic:equal name="hospitalizationForm" property="actionType" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|194">
</logic:equal>

<%-- begin heading table --%>
<table width='100%'>
  <logic:notEqual name="hospitalizationForm" property="actionType" value="view">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.create"/>&nbsp;<bean:message key="prompt.mentalHealth"/> 
        <bean:message key="prompt.hospitalization"/>
        <logic:equal name="hospitalizationForm" property="actionType" value="summary"><bean:message key="title.summary"/></logic:equal>
        <logic:equal name="hospitalizationForm" property="actionType" value="confirm"><bean:message key="title.confirmation"/></logic:equal>   		
    </td>	
  </tr>
  </logic:notEqual>
  <logic:equal name="hospitalizationForm" property="actionType" value="view">
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/> 
        <bean:message key="prompt.hospitalization"/> <bean:message key="title.details"/>
		</td>
	  </tr>
	</logic:equal>
 <logic:equal name="hospitalizationForm" property="actionType" value="confirm">
 	<tr><td align=center class='confirm'>Hospitalization Confirmed.</td></tr>
 </logic:equal>
</table>
<%-- end heading table --%>
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<%-- begin instruction table --%>

<div class='spacer'></div>
<table width="98%" border="0">
  <logic:equal name="hospitalizationForm" property="actionType" value="summary">
   <tr> 
    <td>
     <ul>
    		<li>Review information, then select <b>Finish</b> button to save Hospitalization.</li>
    		<li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
  </logic:equal>
  <logic:equal name="hospitalizationForm" property="actionType" value="view">
  <tr>
    <td  align="left">
     <ul>
    		<li>Review information, then select <b>Back</b> button to return to list</li>
      </ul>
    </td>
  </tr>
  </logic:equal>
</table>
<%-- end instruction table --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- begin edit detail table --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign=top>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
                    <%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
						<%--script type='text/javascript'>renderTabs("Interview Info")</script--%>
					<%--tabs end--%>
                    <%--script type="text/javascript">renderTabs("Interview Info");</script--%></td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>
          <%-- BEGIN  MENTAL HEALTH TABLE --%>
					<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>
                                                <%--tabs end--%>      	
												<%--script type="text/javascript">renderInterviewInfo("Mental Health");</script--%>
                                            </td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
										</tr>
									</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
										</tr>
										<tr>
											<td valign=top align=center>
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign=top>
														<%--tabs start--%>
															<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																<tiles:put name="tabid" value="hospitalization"/>
																<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
															</tiles:insert>	
														<%--tabs end--%>
                                                        <%--script type="text/javascript">renderTestResultsTabs("Hosp");</script--%>
                                                        </td>
													</tr>
													<tr>
														<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
													</tr>
												</table>

												<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableRed">
													<tr>
														<td valign=top align=center>

														<div class='spacer'></div>															
      											<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
      												<tr>
      													<td valign=top class=detailHead><bean:message key="prompt.hospitalization"/>
                                                                                        <bean:message key="prompt.information"/></td>
      												</tr>
      												<tr>
      													<td align=center>
      														<table border=0 cellpadding=2 cellspacing=1 width='98%'>
      															<tr>
      																<td class=formDeLabel nowrap><bean:message key="prompt.facilityName"/></td>
      																<td class=formDe colspan=4><bean:write name="hospitalizationForm" property="hospRec.facilityName" /></td>
      														  </tr>
   															  <tr>
																	<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.admissionType"/></td>
																	<td class=formDe><bean:write name="hospitalizationForm" property="hospRec.admissionType" /></td>
      																<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.admissionDate"/></td>
      																<td class=formDe><bean:write name="hospitalizationForm" property="hospRec.admissionDate" /></td>
      														  </tr>
   																	<tr>
      																<td class=formDeLabel nowrap><bean:message key="prompt.releaseDate"/></td>
      																<td class=formDe colspan=4><bean:write name="hospitalizationForm" property="hospRec.releaseDate" /></td>
   																	</tr>
      														  <tr>
      														  	<td class=formDeLabel valign=top nowrap><bean:message key="prompt.admittingPhysician"/></td>
      																<td class=formDe colspan=4><bean:write name="hospitalizationForm" property="hospRec.physicianNameStr" /></td>
      														  </tr>
      														  <tr>
      																<td class=formDeLabel nowrap><bean:message key="prompt.physicianPhone"/></td>
      																<td class=formDe colspan=4><bean:write name="hospitalizationForm" property="hospRec.physicianPhoneStr" /></td>
      															</tr>
      															<tr>
      																<td colspan=4 class=formDeLabel nowrap><bean:message key="prompt.hospitalizationReason"/></td>
      															</tr>
      															<tr>
      																<td colspan=4 class=formDe><bean:write name="hospitalizationForm" property="hospRec.hospitalizationReason" /></td>
      															</tr>
      														</table>
      													</td>
      												</tr>
      											</table>						
															

                            <%-- begin button table --%>
                       <div class='spacer'></div>
                            <table border="0" width="100%">
                            <tr>
                              <logic:equal name="hospitalizationForm" property="actionType" value="summary">
                                <td align="center">
                									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit> 
                									<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit> 
                									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
                                </td>
                               </logic:equal>
                              </tr>
                              <tr>
                               <logic:equal name="hospitalizationForm" property="actionType" value="confirm">
                                <td align="center">
                    									<html:submit property="submitAction"><bean:message key="button.returnToHospitalization" /></html:submit> 
                                </td>
                                </logic:equal>
                              </tr>
                              <tr>
                              <logic:equal name="hospitalizationForm" property="actionType" value="view">
                                <td align="center">
              					      			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                                </td>
                               </logic:equal>
                              </tr>
                            </table>
														<%-- end button table --%>
                        			
														</td>
													</tr>
												</table>
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
									<div class='spacer'></div><%-- END MENTAL HEALTH TABLE --%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		 </td>
	</tr>
</table>
<%-- end detail table --%>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

