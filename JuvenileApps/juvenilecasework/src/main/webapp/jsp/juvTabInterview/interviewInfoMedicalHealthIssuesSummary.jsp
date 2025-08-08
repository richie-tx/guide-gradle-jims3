<!DOCTYPE HTML>
<!-- User selects the "Next" button on Create Health Issue page -->
<%--MODIFICATIONS --%>
<%--05/05/07 Uma Gopinath CREATE JSP --%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewInfoMedicalHealthIssuesCreateSummary.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/medical.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/submitMedicalHealthIssuesUpdate"  target="content">
<logic:equal name="medicalForm" property="actionType" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|301">
</logic:equal>
<logic:notEqual name="medicalForm" property="actionType" value="view">
    <logic:equal name="medicalForm" property="actionType" value="summary">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|305">
    </logic:equal>
    <logic:equal name="medicalForm" property="actionType" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|306">
    </logic:equal>
</logic:notEqual>        

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<logic:equal name="medicalForm" property="actionType" value="view">
	  <tr>
	    <td align="center" class="header" >Juvenile Casework - Juvenile Profile - Medical Health Issues</td>
	  </tr>
	</logic:equal>

 
  <logic:notEqual name="medicalForm" property="actionType" value="view">
	   <tr>
	    <td align="center" class="header" >Juvenile Casework - Juvenile Profile - Create Medical Health Issues
		<logic:equal name="medicalForm" property="actionType" value="summary"> Summary </logic:equal>
		<logic:equal name="medicalForm" property="actionType" value="confirm"> Confirmation </logic:equal>				
				</td>
	  </tr>
	</logic:notEqual>
	<logic:equal name="medicalForm" property="actionType" value="confirm">
 		<tr><td align=center class='confirm'><bean:write name="medicalForm" property="confirmMessage"/></td></tr>
	 </logic:equal>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">	
	<logic:equal name="medicalForm" property="actionType" value="summary">
		<tr>
			<td>
		      <ul>
		        <li>Review the information.</li>
		        <li>Select the Finish button to save Health Issues information.</li>
		        <li>Select the Back button to return to change data.</li>
		      </ul>
    	</td>
  	</tr>
  </logic:equal>
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
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
          <!--tabs start-->
          <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>	
          <!--tabs end-->
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr>
          <td valign=top align=center>
   					<!-- BEGIN TABLE -->
						<div class=spacer></div>
   					<table width='98%' border="0" cellpadding="0" cellspacing="0">
   						<tr>
   							<td>
   								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
   									<tr>
   										<td valign=top>
    										<!--tabs start-->
   												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" value="medical"/>
											<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
										</tiles:insert>	
    										<!--tabs end-->
   										</td>
   									</tr>
   									<tr>
 									  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
 									</tr>
 								</table>

 					
         				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  									<tr>
  										<td valign=top align=center>
											<div class=spacer></div>  											
  											<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  												<tr>
  													<td valign=top class=detailHead><bean:message key="prompt.healthIssues"/></td>
  												</tr>

  												<tr>
  													<td>
  														<table width='100%' cellspacing=1>
  															<logic:equal name="medicalForm" property="actionType" value="view">
  															<tr>
																		<td valign=top class="formDeLabel" nowrap><bean:message key="prompt.entryDate"/></td>
																		<td valign=top class="formDe" colspan=3 nowrap><bean:write name="medicalForm" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
  															</tr>
  															</logic:equal>
  															<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.issue"/></td>
  																<td valign=top class="formDe"><bean:write name="medicalForm" property="hsRec.issue"/></td>
															</tr>
															<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.issueStatus"/></td>
  																<td valign=top class="formDe"><bean:write name="medicalForm" property="hsRec.issueStatus"/></td>
															</tr>
															<tr>
  																<td valign=top class="formDeLabel" width='1%' nowrap><bean:message key="prompt.status"/></td>  																					
  																<%-- <td valign=top class="formDe" colspan=3><bean:write property="hsRec.healthStatus" name="medicalForm"/></td> --%>
  																 <td valign=top class="formDe" colspan=3>
																	<span title='<bean:write name="medicalForm" property="hsRec.healthStatusFull"/>'>
																		<bean:write name="medicalForm" property="hsRec.healthStatus" /></span>
																</td>
  															</tr>
															<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.conditionSeverity"/></td>
  																<td valign=top class="formDe"><bean:write name="medicalForm" property="hsRec.conditionSeverity"/></td>
															</tr>
															<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.conditionLevel"/></td>
  																<td valign=top class="formDe"><bean:write name="medicalForm" property="hsRec.conditionLevel"/></td>
															</tr>
															
															
															
															<logic:equal name="medicalForm" property="hsRec.action" value="update">
  															<tr>
  																<td valign=top class="formDeLabel" width='1%' nowrap colspan=4><bean:message key="prompt.modificationReason"/></td>
  															</tr>
  															<tr>
  																<td valign=top class="formDe" colspan=4><bean:write name="medicalForm" property="hsRec.modificationReason"/></td>
  															</tr>
  															</logic:equal>
  															<logic:equal name="medicalForm" property="hsRec.action" value="view">
		  														<logic:notEmpty name="medicalForm" property="hsRec.modificationReason">
		  															<tr>
		  																<td valign=top class="formDeLabel" width='1%' nowrap colspan=4><bean:message key="prompt.modificationReason"/></td>
		  															</tr>
		  															<tr>
		  																<td valign=top class="formDe" colspan=4><bean:write name="medicalForm" property="hsRec.modificationReason"/></td>
		  															</tr>
		  														</logic:notEmpty>
  															</logic:equal>
																
  														</table>
  													</td>
  												</tr>
  											</table>								
  																		
						<div class=spacer></div>
                        <!-- BEGIN BUTTON TABLE -->                        
                        <table border="0" width="100%">
                         <logic:equal name="medicalForm" property="actionType" value="summary">
                          <tr>
                          	<td align="center">
                          		<html:submit property="submitAction">
                									<bean:message key="button.back"></bean:message>
                								</html:submit>
                								<html:submit property="submitAction" >
                									<bean:message key="button.finish"></bean:message>
                								</html:submit>		
                								 <html:submit property="submitAction">
                										<bean:message key="button.cancel"></bean:message>
                									</html:submit>
                                          	</td>
                                          </tr>
                                          </logic:equal>
                          
                        </table>
                       
                        <logic:notEqual name="medicalForm" property="actionType" value="summary">
                          <table border="0" width="100%">
                        	  <tr>
                        	  	<td align="center">
                        	     	<html:submit property="submitAction">
                        				<bean:message key="button.returnToMedical"></bean:message>
                        			</html:submit>												
                        	  	</td>
                        	  </tr>
                          </table>
                        </logic:notEqual>
                        <!-- END BUTTON TABLE -->
													
  										</td>
      							</tr>
      						</table>
      					</td>
      				</tr>
      			</table>
						<div class=spacer></div><!-- END TABLE -->
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<!-- END DETAIL TABLE -->


</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>


