<!DOCTYPE HTML>
<!-- Used to select release to Decision information. -->
<!--MODIFICATIONS -->
<!-- CShimek	01/13/2005	Create JSP -->
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek	10/05/2005	ER#23756 - revise titles --%>
<%-- CShimek    01/24/2006  Removed title error found by training --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<tiles:insert page="../js/warrantRelease.js" />
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseDecision.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<!--BEGIN HEADER TAG-->

<!--BEGIN BODY TAG-->
<body>
<html:form action="/displayReleaseDecisionSummary" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|28">
<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header">
       <bean:message key="title.juvWarrantEnterReleaseDecision"/> 
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
   <table width="98%" align=center>
   	<tr>
     	<td><ul>
        	<li>Select Release Decision then select Next button to view summary.</li>
	  	</ul></td>
  	</tr>
</table>
<!-- BEGIN INSTRUCTION TABLE -->
<!-- BEGIN REQUIRED TABLE -->
<table border="0" width="98%" align=center>	
	<tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td></tr>	
</table>
<!-- END REQUIRED TABLE -->

					<table width=98% align="center" cellspacing="1" cellpadding=4 border=0>
					<!-- BEGIN WARRANT INFORMATION SECTION -->
          	<tr>
						 	<td class=detailHead colspan=4><bean:message key="prompt.juvenileWarrantInfo" /></td>
						</tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.warrantNumber"/></td>          
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
                <td class="formDeLabel"><bean:message key="prompt.warrantStatus"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantStatus"/></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.serviceAddress"/></td>          
                <td class="formDe" colspan="3">&nbsp;<bean:write name="juvenileWarrantForm" property="serviceAddress"/></td>             
             </tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.serviceDate"/></td>          
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" /></td>
                <td class="formDeLabel"><bean:message key="prompt.serviceTime"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" /></td>
             </tr>             
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.activationDate"/></td>
                <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" /></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.returnOfServiceSignatureStatus"/></td>
                <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="serviceReturnSignatureStatus"/></td>
             </tr>   
             <!-- END WARRANT INFORMATION SECTION -->          
							<tr><td><br></td></tr>
							<!-- BEGIN JUVENILE SERVED INFORMATION SECTION -->
               <tr>
					<td class=detailHead colspan=4>Juvenile Served Information<%-- <bean:message key="prompt.juvenileServedInfo" /> --%></td>
			   </tr>
			   <tr>
					<td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>          
					<td class="formDe" colspan="3">
						<bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/>
					</td>
			   </tr>
			   <tr>
					<td class="formDeLabel"><bean:message key="prompt.race"/></td>
					<td class="formDe"><bean:write name="juvenileWarrantForm" property="race"/></td>
					<td class="formDeLabel"><bean:message key="prompt.sex"/></td>
					<td class="formDe"><bean:write name="juvenileWarrantForm" property="sex"/></td>
	            </tr>      
	            <tr>
                <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>          
                <td class="formDe" colspan="3">
                	<bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
                </td>
	            </tr>
	            <!-- END JUVENILE SERVED INFORMATION SECTION -->
	            <tr>
				 	<td><br></td>
				</tr>
				<!-- BEGIN executorInfo INFORMATION SECTION -->
			    <tr>
				 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.executorInfo" /></td>
				</tr>
	            <tr>
               <td class="formDeLabel"><bean:message key="prompt.officerIdNumber"/></td>
               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorId"/></td>
               <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.officerIdType"/></td>
               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
	            </tr>
	            <tr>   
		           <td class="formDeLabel"><bean:message key="prompt.department"/></td>
	               <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="executorDepartmentName"/></td>
	            </tr>
	            <tr>   
		           <td class="formDeLabel"><bean:message key="prompt.executorName"/></td>
	               <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="executorName"/></td>
	            </tr>            
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPhoneNum"/></td>
	               <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorCellNum"/></td>
	            </tr>
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPager"/></td>
		           <td class="formDeLabel"><bean:message key="prompt.email"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorEmail"/></td>
	            </tr> 
	            <!-- END executorInfo INFORMATION SECTION -->
	            <tr>
							 	<td><br></td>
							</tr>
							<!-- BEGIN RELEASE SECTION -->
							<tr>
							 	<td class=detailHead nowrap colspan=4><bean:message key="prompt.releaseDecision"/> Information<%-- <bean:message key="prompt.releaseDecisionInfo" /> --%></td>
							</tr>
							<tr>
                <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.releaseDecisionName"/></td>          
                <td class="formDe" colspan="3">
                   <bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/>
                </td>
	            </tr>
	            <tr>
                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.releaseDecision"/></td>          
                <td class="formDe" colspan="3"> 
                    <html:select property="releaseDecisionId"> 	
  	                   <html:option value=""><bean:message key="select.generic" /></html:option>  	                  	
                       <html:optionsCollection name="codeHelper" property="releaseDecisions" value="code" label="description" />
	                 </html:select>
                </td>
	            </tr>
	            <!-- END RELEASE SECTION -->
           </table>
<!-- END RELEASE DECISION INFORMATION TABLES -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" align="center">
	<tr valign="top">
	  <td>
		<logic:notEmpty name="juvenileWarrantForm" property="warrants">
	         <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			 </html:button>&nbsp;
		</logic:notEmpty>
	    <html:submit property="submitAction" onclick="return validateReleaseDecision(this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
		<html:reset />&nbsp;
	  </td>
	  </html:form>
	  <!-- END 1st FORM -->
	  <td>	
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="releaseDecision">
		  <html:form action="/displayGenericSearch.do?warrantTypeUI=releaseDecision"> 			  
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		  </html:form>
		</logic:equal> 
	  
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>