<!DOCTYPE HTML>
<!-- Used for create,update and inactivate confirmation. -->
<!--MODIFICATIONS -->
<!-- CShimek 03/30/2005	 Create JSP -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->
<!-- R Capestani		10/05/2015  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->
<%@ page import="naming.Features" %>
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - officerCreateSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<logic:equal name="officerForm" property="action" value="create" >
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|56">	
</logic:equal>
<logic:equal name="officerForm" property="action" value="update" >
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|226">	
</logic:equal>
<logic:equal name="officerForm" property="action" value="inactivate" >
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|89">	
</logic:equal>
  <!-- BEGIN HEADING TABLE -->
<logic:equal name="officerForm" property="action" value="create" >
  <table width="100%"> 
    <tr> 
      <td align="center" class="header">
        Create&nbsp;<bean:message key="title.officerProfile"/>&nbsp;Confirmation
      </td> 
    </tr> 
  </table>
</logic:equal>
<logic:equal name="officerForm" property="action" value="update" >
  <table width="100%"> 
    <tr> 
      <td align="center" class="header">
        Update&nbsp;<bean:message key="title.officerProfile"/>&nbsp;Confirmation
      </td> 
    </tr> 
  </table>
</logic:equal>
<logic:equal name="officerForm" property="action" value="inactivate" >
  <table width="100%"> 
    <tr> 
      <td align="center" class="header">
        Inactivate&nbsp;<bean:message key="title.officerProfile"/>&nbsp;Confirmation
      </td> 
    </tr> 
  </table>
</logic:equal>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
  <!-- END HEADING TABLE --> 
  <br> 
  <!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
  <table width="98%" border="0"> 
    <tr> 
      <td align="center" class="confirm"> 
		<logic:equal name="officerForm" property="action" value="create" >
			The following Officer Profile has been successfully created.
		</logic:equal>
		<logic:equal name="officerForm" property="action" value="update" >
			The following Officer Profile has been successfully updated.
		</logic:equal>
		<logic:equal name="officerForm" property="action" value="inactivate" >
			The following Officer Profile has been successfully inactivated.
		</logic:equal>
      </td> 
    </tr> 
  </table> 
  <br> 
  <!-- BEGIN DETAIL TABLE --> 
  <table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
    <tr> 
      <td class="detailHead">&nbsp;<bean:message key="prompt.officer"/>&nbsp;<bean:message key="prompt.info"/></td> 
    </tr> 
    <tr> 
      <td>
	  <!-- BEGIN OFFICER INFORMATION TABLE -->  
	  <table width="100%" border="0" cellpadding="2" cellspacing="1"> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.name"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="lastName"/><logic:notEmpty name="officerForm" property="lastName">, </logic:notEmpty>&nbsp;
                                         <bean:write name="officerForm" property="firstName"/>&nbsp;
                                         <bean:write name="officerForm" property="middleName"/></td> 
          </tr>          
		  <tr> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.badge#"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="badgeNum"/></td> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.otherId#"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="otherIdNum"/></td> 
          </tr>
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.userId"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="userId"/></td>
		    <td class="formDeLabel" nowrap><bean:message key="prompt.socialSecurity#"/></td>
			<td class="formDe"><bean:write name="officerForm" property="ssn"/></td> 
          </tr>  
		  <tr> 
            <td class="formDeLabel" nowrap><bean:message key="prompt.departmentCode"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="departmentId"/></td>
		  </tr>
		  <tr>
            <td class="formDeLabel" nowrap><bean:message key="prompt.departmentName"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="departmentName"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.officerType"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="officerType"/></td>
			<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.status"/></td>
			<td class="formDe"><bean:write name="officerForm" property="status"/></td>
          </tr> 
		  <tr> 
            <td class="formDeLabel"><bean:message key="prompt.officerSubtype"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="officerSubType"/></td> 
          </tr>
          <jims:if name="officerForm" property="departmentId" value="juv" op="equal">
			<jims:or name="officerForm" property="departmentId" value="JUV" op="equal"/>
			   <jims:then>
		          <tr>
					<td class="formDeLabel">Juv&nbsp;<bean:message key="prompt.location"/></td> 
		            <td class="formDe" colspan="3"><bean:write name="officerForm" property="juvLocation"/></td>
				  </tr>
				  <tr>
				    <td class="formDeLabel">Juv&nbsp;<bean:message key="prompt.unit"/></td> 
		            <td class="formDe" colspan="3"><bean:write name="officerForm" property="juvUnit"/></td>
				  </tr>
		  	  </jims:then>
		  </jims:if>
          <tr> 
            <!-- in jsp use workPhone.areaCode, workPhone.prefix and workPhone.4Digit --> 
            <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td> 
            <td class="formDe">
            <logic:empty name="officerForm" property="workPhone">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><bean:write name="officerForm" property="workPhone"/>&nbsp;<strong>
            <logic:notEmpty name="officerForm" property="extn"><bean:message key="prompt.ext"/>&nbsp;</strong><bean:write name="officerForm" property="extn"/></logic:notEmpty></td>
            <td colspan="2" class="formDe">
								<table cellpadding="2" cellspacing="2" class="formDe">
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.includeVendorSurvey" /></td>
										<td class="formDe"><bean:write name="officerForm" property="survey"/></td>
									</tr>
								</table>
		   </td>
		  </tr> 
		  <tr> 			
			<td class="formDeLabel"><bean:message key="prompt.homePhone"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="homePhone"/></td>
            <td colspan="2" class="formDe">
				<table cellpadding="2" cellspacing="2" class="formDe">
				 <tr>
				 <td class="formDeLabel"><bean:message key="prompt.inSupervisorPos" /></td>
				 <td class="formDe"><bean:write name="officerForm" property="supervisor"/>
			   	</td>
			 	</tr>
				</table>
			 </td>
          </tr>  
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="cellPhone"/></td> 
		  </tr> 
		  <tr> 			
			<td class="formDeLabel"><bean:message key="prompt.pager"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="pager"/></td> 
          </tr>
		  <tr> 
            <td class="formDeLabel"><bean:message key="prompt.fax"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="fax"/></td>
		  </tr> 
		  <jims:isAllowed requiredFeatures="<%=Features.JCW_OFFICERPROFILEJPOUPDATE%>">  
		  <tr> 			
			<td class="formDeLabel"><bean:message key="prompt.specialtyUnit"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="faxLocation"/></td>
		  </tr>
		  </jims:isAllowed>
		  <tr>			
            <td class="formDeLabel"><bean:message key="prompt.email"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="email"/></td> 
          </tr> 
		  <tr> 
		    <td class="formDeLabel"><bean:message key="prompt.rank"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="rank"/></td>
          </tr> 		  
          <tr>
		    <td class="formDeLabel" nowrap><bean:message key="prompt.ccj"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="divisionName"/></td>
            <td class="formDeLabel"><bean:message key="prompt.onLeave"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="assignedArea"/></td> 
		  </tr>
		  <tr>
			<td class="formDeLabel"><bean:message key="prompt.radioNumber"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="radioNumber"/></td>
            <td class="formDeLabel"><bean:message key="prompt.positionTitle"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="workShift"/></td>
		  </tr> 		  
        </table> 
        <!-- END OFFICER INFO TABLE -->
        <!--  BEGIN TSD TRAINING --> 
    	<logic:equal name="officerForm" property="action" value="update">       
	 	<div class='spacer'></div>
			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' class='detailHead'>
						<table width='100%' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='1%' class='detailHead'>
								  <a href="javascript:showHideMulti('TSD Training', 'pChar', 1, '/<msp:webapp/>')">
			   					<img id="imgId" border='0' src="/<msp:webapp/>images/expand.gif" name="TSD Training"/></a>
										TSD Training
									<logic:empty name="officerForm" property="officerTraining">
										(No TSD Training)
									</logic:empty>
								</td>
							</tr>
						</table>
					</td> 
				</tr>
			
				<tr id="pChar0" class='hidden'>
					<td valign='top'>
					<div class='scrollingDiv200'>
						<table width='100%' cellpadding='2' cellspacing='0'>
							<tr bgcolor='white'>
								<td valign='top' width='100%'>
									<table width='100%' cellpadding='4' cellspacing='1'>
										<logic:notEmpty name="officerForm" property="officerTraining">
											<thead>
											<tr>
											<td class="hidden"><jims2:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="sortOrder" primarySortType="INTEGER" defaultSortOrder="DESC" sortId="1" levelDeep="1" />
												<td class="formDeLabel"><bean:message key="prompt.trainingTopic"/></td> 
												<td class="formDeLabel"><bean:message key="prompt.trainingBegDate"/></td> 
												<td class="formDeLabel"><bean:message key="prompt.trainingEndDate"/></td> 
												<td class="formDeLabel"><bean:message key="prompt.trainingHours"/></td>  											
											</tr>
											</thead>
											<tbody>
											<logic:iterate id="trainingIndex" name="officerForm"
														property="officerTraining" indexId="index">	
												<bean:size id="pcharSize" name="officerForm" property="officerTraining"/>								
												<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%> " height="100%">
													<td><bean:write name="trainingIndex"
														property="trainingDescription" />
													</td>
													<td><bean:write name="trainingIndex"
															property="trainingBeginDate" formatKey="date.format.mmddyyyy"/>
													</td>
													<td><bean:write name="trainingIndex"
															property="trainingEndDate" formatKey="date.format.mmddyyyy"/>
													</td>
													<td><bean:write name="trainingIndex"
															property="trainingHours" />
													</td>
												</tr>										 
											</logic:iterate>
											</tbody>
										</logic:notEmpty>
									</table>
								</td>
							</tr>
						</table>
						</div>
					</td>
				</tr>
		 </table>       
  </logic:equal>
  <!-- END TSD TRAING --%>
        <!-- BEGIN WORK DAY TABLE --> 
        <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
          <tr> 
            <td class="detailHead" colspan="4"><bean:message key="prompt.workDaySchedule"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.workDay"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.startTime"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.endTime"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.offDay"/></td> 
          </tr>
          <tr> 
            <td class="formDe"><bean:message key="prompt.sunday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime0"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime0"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff0" value="Y">x</logic:equal></td> 
          </tr>
          <tr> 
            <td class="formDe"><bean:message key="prompt.monday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime1"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime1"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff1" value="Y">x</logic:equal></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.tuesday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime2"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime2"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff2" value="Y">x</logic:equal></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.wednesday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime3"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime3"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff3" value="Y">x</logic:equal></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.thursday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime4"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime4"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff4" value="Y">x</logic:equal></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.friday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime5"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime5"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff5" value="Y">x</logic:equal></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.saturday"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="startTime6"/></td> 
            <td class="formDe">&nbsp;<bean:write name="officerForm" property="endTime6"/></td> 
            <td class="formDe">&nbsp;<logic:equal name="officerForm" property="dayOff6" value="Y">x</logic:equal></td> 
          </tr> 
        </table> 
        <!-- END ADDRESS TABLE --> 
        <!-- BEGIN ADDRESS TABLE --> 
        <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
          <tr> 
            <td class="detailHead" colspan="3">&nbsp;<bean:message key="prompt.workAddress"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.streetNumber"/></td> 
            <td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName"/></td> 
          </tr>
          <tr>
            <td class="formDe"><bean:write name="officerForm" property="streetNumber"/>&nbsp;</td> 
            <td class="formDe" colspan="2"><bean:write name="officerForm" property="streetName"/></td>
          </tr>             
          <tr>  
            <td class="formDeLabel"><bean:message key="prompt.streetType"/></td> 
            <td class="formDeLabel" colspan="2"><bean:message key="prompt.aptSuite"/></td> 
          </tr>
          <tr> 
            <td class="formDe"><bean:write name="officerForm" property="streetType"/>&nbsp;</td> 
            <td class="formDe" colspan="2"><bean:write name="officerForm" property="apartmentNumber"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.city"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.state"/></td> 
            <td class="formDeLabel" ><bean:message key="prompt.zipCode"/></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:write name="officerForm" property="city"/>&nbsp;</td> 
            <td class="formDe"><bean:write name="officerForm" property="state"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="zipCode"/><bean:write name="officerForm" property="additionalZipCode"/></td> 
          </tr> 
        </table> 
        <!-- END ADDRESS TABLE --> 
        <!-- START MANAGER TABLE --> 
        <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
          <tr> 
            <td class="detailHead" colspan="4">&nbsp;<bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.info"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.userId"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="managerId"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.name"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="managerLastName"/><logic:notEmpty name="officerForm" property="managerLastName">, </logic:notEmpty>
                                         <bean:write name="officerForm" property="managerFirstName"/>
                                         <bean:write name="officerForm" property="managerMiddleName"/></td> 
          </tr> 
        </table>
      </td>
    </tr> 
  </table> 
  <!-- END DETAIL TABLE --> 
  <br> 
  <!-- BEGIN BUTTON TABLE --> 
  <table width="100%"> 
    <tr  align="center"><td>
    	<input onclick="document.location.href='/security.war/displayOfficerProfileSearch.do'" type="button" value="Return to Search Officer" />
	</td> 
    </tr> 
  </table> 
  <!-- END BUTTON TABLE --> 

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
