<!DOCTYPE HTML>
<!-- User selects the "Add Health Issues" button on Medical List page -->
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewInfoMedicalHealthIssuesCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/medical.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>

</head>

<body topmargin=0 leftmargin="0">
<html:form action="/displayMedicalHealthIssuesUpdateSummary"  target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|304">

<!-- BEGIN HEADING TABLE -->
<table width='100%'> 
	   <tr>
	    <td align="center" class="header" >Juvenile Casework - Juvenile Profile - Create Medical Health Issues</td>
	  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%" border="0">	
	  <tr>
	    <td>
	      <ul>
	        <li>Enter information as appropriate.</li>
	        <li>Select the Next button to view the summary screen.</li>
	        <li>Select the Back button to return to the Interview Medical screen.</li>
	      </ul>
				</td>
		</tr>
 
    <tr>
		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction" /></td>
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
  														<logic:equal name="medicalForm" property="hsRec.action" value="create">
  															<tr>
																<td valign=top class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.issue"/></td>
  																<td valign=top class="formDe">
																		<html:select property="hsRec.issueId" name="medicalForm">
																					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																					   <html:optionsCollection property="healthIssues" value="code" label="description"></html:optionsCollection> 
																					<%-- <jims2:codetable codeTableName='HEALTH_ISSUE'></jims2:codetable> --%>
																					</html:select>  
																	</td>
																</tr>
														</logic:equal>
														<logic:equal name="medicalForm" property="hsRec.action" value="update">
																<tr>
																	<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.issue"/></td>
	  																<td valign=top class="formDe" colspan=3><bean:write name="medicalForm" property="hsRec.issue"/></td>
																</tr>
														</logic:equal>
														<logic:equal name="medicalForm" property="hsRec.action" value="create">
																<tr>
																	<td valign=top class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.issueStatus"/></td>
  																	<td valign=top class="formDe">
																		<html:select property="hsRec.issueStatusId" name="medicalForm">
																					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																					  <jims2:codetable codeTableName='HEALTH_ISSUE_STATUS'></jims2:codetable>
																					</html:select>  
                                  									</td>
  																</tr>
  														</logic:equal>
  														<logic:equal name="medicalForm" property="hsRec.action" value="update">
		  														<tr>
																		<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.issueStatus"/></td>
		  																<td valign=top class="formDe" colspan=3><bean:write name="medicalForm" property="hsRec.issueStatus"/></td>
																</tr>
														</logic:equal>
  														<logic:equal name="medicalForm" property="hsRec.action" value="create">
  															<tr>
  																<td valign='top' class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.status"/></td>
  																<td valign='top' class="formDe">
    																<html:select property="hsRec.healthStatusId" name="medicalForm">
   																	   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
    																	  <jims2:codetable codeTableName='HEALTH_STATUS'></jims2:codetable>
   																	</html:select></td>
  															</tr>
  														</logic:equal>
  														<logic:equal name="medicalForm" property="hsRec.action" value="update">
  															<tr>
  																<td valign=top class="formDeLabel" width='1%' nowrap><bean:message key="prompt.status"/></td>  																					
  																<%-- <td valign=top class="formDe" colspan=3><bean:write property="hsRec.healthStatus" name="medicalForm"/></td> --%>
  																<td valign='top' class="formDe">
    																<html:select property="hsRec.healthStatusId" name="medicalForm" disabled="true">
   																	   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
    																	  <jims2:codetable codeTableName='HEALTH_STATUS'></jims2:codetable>
   																	</html:select>
   																					
   																</td>
   																	
  															</tr>
  														</logic:equal>
  														<logic:equal name="medicalForm" property="hsRec.action" value="create">
  															<tr>
																<td valign=top class="formDeLabel" nowrap><bean:message key="prompt.conditionSeverity"/></td>
  																<td valign=top class="formDe">
																			<html:select property="hsRec.conditionSeverityId" name="medicalForm">
																					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																					  <jims2:codetable codeTableName='HEALTH_CONDITION_SEVERITY'></jims2:codetable>
																					</html:select>  
                                  								</td>
															</tr>
														</logic:equal>
														<logic:equal name="medicalForm" property="hsRec.action" value="update">
															<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.conditionSeverity"/></td>
  																<td valign=top class="formDe" colspan=3><bean:write name="medicalForm" property="hsRec.conditionSeverity"/></td>
															</tr>
														</logic:equal>
														<logic:equal name="medicalForm" property="hsRec.action" value="create">
															<tr>
																<td valign=top class="formDeLabel" nowrap><bean:message key="prompt.conditionLevel"/></td>
  																<td valign=top class="formDe">
																				<html:select property="hsRec.conditionLevelId" name="medicalForm">
																					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																					  <jims2:codetable codeTableName='HEALTH_CONDITION_LEVEL'></jims2:codetable>
																					</html:select>  
                                  								 </td>
  															</tr>
  													 </logic:equal>
  													 <logic:equal name="medicalForm" property="hsRec.action" value="update">
  													 		<tr>
																<td valign=top class="formDeLabel" width=1% nowrap><bean:message key="prompt.conditionLevel"/></td>
  																<td valign=top class="formDe" colspan=3><bean:write name="medicalForm" property="hsRec.conditionLevel"/></td>
															</tr>
														</logic:equal>
  															<logic:equal name="medicalForm" property="hsRec.action" value="update">
  															<tr>
  																<td valign='top' class="formDeLabel" width='1%' nowrap='nowrap' colspan=4><bean:message key="prompt.2.diamond"/><bean:message key="prompt.modificationReason"/>&nbsp;
					                            					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
					                            						<tiles:put name="tTextField" value="hsRec.modificationReason"/>
					                             						<tiles:put name="tSpellCount" value="spellBtn1" />
					                          						</tiles:insert>
					                          						(Max. characters allowed: 250)
					                          					</td>
  															</tr>
  															<tr> <!-- width = 250 for "reason" field -->
  																<td valign='top' class="formDe" colspan=4><html:textarea rows="4" style="width:100%" name="medicalForm" property="hsRec.modificationReason"></html:textarea></td>
  															</tr>
  															</logic:equal>
  															
  															
  														</table>
  													</td>
  												</tr>
  											</table>							

                        <!-- BEGIN BUTTON TABLE -->
                        <div class=spacer></div>
                        <table border="0" width="100%">
                        <logic:equal name="medicalForm" property="hsRec.action" value="create">
                          <tr>
                          	<td align="center">
                          		 <html:submit property="submitAction">
										<bean:message key="button.back"></bean:message>
									 </html:submit>									 
									 <html:submit property="submitAction" styleId="healthIssuesNext">
										<bean:message key="button.next"></bean:message>
									</html:submit>		
									 <html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>         
                          	</td>
                          </tr>	
                          </logic:equal>
                          <logic:equal name="medicalForm" property="hsRec.action" value="update">
                          <tr>
                          	<td align="center">
                          		 <html:submit property="submitAction">
										<bean:message key="button.back"></bean:message>
									 </html:submit>									 
									 <html:submit property="submitAction" styleId="healthIssuesUpdate">
										<bean:message key="button.next"></bean:message>
									</html:submit>		
									 <html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>  
                          </tr> 
                          </logic:equal>				
      					</table> <!-- END BUTTON TABLE -->
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
<!-- END DETAIL TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

