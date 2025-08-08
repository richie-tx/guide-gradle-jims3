<!DOCTYPE HTML>
<%-- User selects 'Next' button on Create Medical Hospitalization page --%>
<%--MODIFICATIONS --%>
<%-- 05/07/2007	Uma Gopinath Create JSP --%>

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
<title><bean:message key="title.heading"/> - interviewInfoMedicalHospitalizationSummary.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/medical.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>


</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin=0 onKeyDown="return checkEnterKey(event,true)">
<html:form action="/submitMedicalHospitalizationUpdate" target="content">
<logic:equal name="medicalForm" property="actionType" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|303">
</logic:equal>
<logic:equal name="medicalForm" property="actionType" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|311">
</logic:equal>
<logic:equal name="medicalForm" property="actionType" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|312">
</logic:equal>

<%-- begin heading table --%>
<table width='100%'>
  <logic:notEqual name="medicalForm" property="actionType" value="view">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.create"/>&nbsp;<bean:message key="prompt.medical"/> 
        <bean:message key="prompt.hospitalization"/>
        <logic:equal name="medicalForm" property="actionType" value="summary"><bean:message key="title.summary"/></logic:equal>
        <logic:equal name="medicalForm" property="actionType" value="confirm"><bean:message key="title.confirmation"/></logic:equal>   		
    </td>	
  </tr>
  </logic:notEqual>
  <logic:equal name="medicalForm" property="actionType" value="view">
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.medical"/> 
        <bean:message key="prompt.hospitalization"/> 
		</td>
	  </tr>
	</logic:equal>
 <logic:equal name="medicalForm" property="actionType" value="confirm">
 	<tr><td align=center class='confirm'><bean:write name="medicalForm" property="confirmMessage"/></td></tr>
 </logic:equal>
</table>
<%-- end heading table --%>

<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</TBODY>
</table>

<%-- begin instruction table --%>
<div class=spacer></div>
<table width="98%" border="0">
  <logic:equal name="medicalForm" property="actionType" value="summary">
   <tr> 
    <td>
     <ul>
    		<li>Review the information.</li>
    		<li>Select the <b>Finish</b> button to save Hospitalization information.</li>
    		<li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
  </logic:equal>
  <logic:equal name="medicalForm" property="actionType" value="view">
  <tr>
    <td>
     <ul>
    		<li>Review information, then select <b>Back</b> button to return to list.</li>
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
<div class=spacer></div>
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
					<div class=spacer></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
												<%--tabs start--%>
													<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="medical"/>
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
											<td valign=top align=center>
      											<table width='98%' border="1" cellpadding="2" cellspacing="1" class=borderTableBlue>
      												<tr>
      													<td valign=top class=detailHead><bean:message key="prompt.hospitalization"/>
                                                                                        <bean:message key="prompt.information"/></td>
      												</tr>
      												<tr>
      													<td align=center>
      														<table border="0" cellpadding="2" cellspacing="1" width='98%'>
      														
   															  <tr>
      																<td class=formDeLabel width="10%" nowrap>Admit Year</td>
      																<td class=formDe width="20%"><bean:write name="medicalForm" property="admitYear" /></td>
																	<td class=formDeLabel width="10%" nowrap><bean:message key="prompt.admissionType"/></td>
																	<td class=formDe width="20%"><bean:write name="medicalForm" property="hospRec.admissionType" /></td>      																
      														  </tr>
      														  	<tr>
      																<td class=formDeLabel nowrap><bean:message key="prompt.facilityName"/></td>
      																<td class=formDe colspan=6><bean:write name="medicalForm" property="hospRec.facilityName" /></td>
      														  </tr>
      														  <tr>
      																<td colspan=6 class=formDeLabel nowrap><bean:message key="prompt.hospitalizationReason"/></td>
      															</tr>
      															<tr>
      																<td colspan=6 class=formDe><bean:write name="medicalForm" property="hospRec.hospitalizationReason" /></td>
      															</tr>
   																<tr>
      																<td class=formDeLabel  nowrap>Length of Stay</td>
      																<td class=formDe colspan=3 ><bean:write name="medicalForm" property="hospitalLengthOfStayDesc" /></td>
   																</tr>
      														  <tr>
      														  	<td class=formDeLabel valign=top nowrap><bean:message key="prompt.admittingPhysician"/></td>
      															<td class=formDe colspan=5><bean:write name="medicalForm" property="hospRec.admittingPhysicianName.formattedName" /></td>
      														  </tr>
      														  <tr>
      															<td class=formDeLabel nowrap><bean:message key="prompt.physicianPhone"/></td>
      															<td class=formDe colspan=5><bean:write name="medicalForm" property="hospRec.physicianPhone.formattedPhoneNumber" /></td>
      														</tr>
      															
      														</table>
      													</td>
      												</tr>
      											</table>						
															

                            <%-- begin button table --%>
                           <div class=spacer></div>
                            <table border="0" width="100%">
                            <tr>
                              <logic:equal name="medicalForm" property="actionType" value="summary">
                                <td align="center">
                  									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit> 
                  									<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit> 
                  									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
                                </td>
                               </logic:equal>
                              </tr>
                             
                            
                            </table>
                            <logic:notEqual name="medicalForm" property="actionType" value="summary">
                            	<table border="0" width="100%">
                            	<tr>
                            	<td align="center">
                            		<html:submit property="submitAction"><bean:message key="button.returnToMedical"/></html:submit> 
                            	</td>
                            	 </tr>
                            	 </table>
                            </logic:notEqual>
														
														<div class=spacer></div><%-- end button table --%>
                        			
														</td>
													</tr>
												</table>
												<div class=spacer></div>
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

