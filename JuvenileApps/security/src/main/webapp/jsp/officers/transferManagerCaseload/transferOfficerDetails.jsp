<!DOCTYPE HTML>
<!-- Used to view officer information. -->
<!--MODIFICATIONS -->
<!-- DWilliamson 04/05/2011	 Create JSP -->
<!-- RCapestani  10/09/2015 #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

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
<title><bean:message key="title.heading"/> - transferOfficerDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<script language="JavaScript" src="/<msp:webapp/>js/common.js"></script>
 <!-- BEGIN HEADING TABLE -->
<script type='text/javascript'>
	function closeWindow(el) {
		window.close();

		return 0;
	}
</script>
<table width="100%"> 
	<tr> 
		<td align="center" class="header">
			<bean:message key="title.juvenileProbation" />&nbsp;-&nbsp;<bean:message key="title.officer"/>&nbsp;<bean:message key="title.details"/>
		</td> 
	</tr> 
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END HEADING TABLE --> 
<!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
<table width="98%" border="0"> 
	<tr> 
		<td>
			<ul>  
				<li>Select Back button to return to previous page.</li>
			</ul>
		</td> 
    </tr> 
</table> 
<!-- END INSTRUCTION/REQUIRED INFORMATION TABLE --> 
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
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="lastName"/><logic:notEmpty name="officerForm" property="lastName">,</logic:notEmpty>&nbsp;
                                         <bean:write name="officerForm" property="firstName"/>&nbsp;
                                         <bean:write name="officerForm" property="middleName"/></td> 
          	</tr>          
            <logic:notEqual name="officerForm" property="action" value="view" >		  
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
            </logic:notEqual>
            <logic:equal name="officerForm" property="action" value="view" >
	            <logic:equal name="officerForm" property="numberViewable" value="Y" >            
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
    	        </logic:equal>	
	            <logic:equal name="officerForm" property="numberViewable" value="N" >            
					<tr>
			            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.badge#"/></td> 
    			        <td class="formDe" colspan="3"><bean:write name="officerForm" property="badgeNum"/></td> 
			   		</tr>
			   		<tr>
			            <td class="formDeLabel"><bean:message key="prompt.userId"/></td> 
        			    <td class="formDe" colspan="3"><bean:write name="officerForm" property="userId"/></td>
		          	</tr>
    	        </logic:equal>	
            </logic:equal>            
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
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="workPhone"/>&nbsp;&nbsp;
            <logic:notEmpty name="officerForm" property="extn"> <strong><bean:message key="prompt.ext"/></strong>&nbsp;<bean:write name="officerForm" property="extn"/></logic:notEmpty></td>
		  </tr> 
		  <tr> 			
			<td class="formDeLabel"><bean:message key="prompt.homePhone"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="homePhone"/></td>
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
		  <tr> 			
			<td class="formDeLabel"><bean:message key="prompt.faxLocation"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="faxLocation"/></td>
		  </tr>
		  <tr>			
            <td class="formDeLabel"><bean:message key="prompt.email"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="email"/></td> 
          </tr> 
		  <tr> 
		    <td class="formDeLabel"><bean:message key="prompt.rank"/></td> 
            <td class="formDe" colspan="3"><bean:write name="officerForm" property="rank"/></td>
          </tr> 		  
          <tr>
		    <td class="formDeLabel" nowrap><bean:message key="prompt.division"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="divisionName"/>&nbsp;</td>
            <td class="formDeLabel"><bean:message key="prompt.assignedArea"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="assignedArea"/>&nbsp;</td> 
		  </tr>
		  <tr>
			<td class="formDeLabel"><bean:message key="prompt.radioNumber"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="radioNumber"/>&nbsp;</td>
            <td class="formDeLabel"><bean:message key="prompt.workShift"/></td> 
            <td class="formDe"><bean:write name="officerForm" property="workShift"/>&nbsp;</td>
		  </tr> 		  
        </table> 
        <!-- END OFFICER INFO TABLE --> 
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
     <%--   <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
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
        </table> --%>
      </td>
    </tr> 
  </table> 
  <!-- END DETAIL TABLE --> 
  <br> 
  <!-- BEGIN BUTTON TABLE --> 
  <table width="100%"> 
    <tr><td align="center">  
		<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="closeWindow();">
				          <bean:message key="button.close"></bean:message></html:button>    
       </td> 
    </tr> 
  </table> 
  <!-- END BUTTON TABLE --> 

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
