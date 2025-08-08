<!DOCTYPE HTML>
<!-- Used to display release Decision sumamry and confirmation -->
<!--MODIFICATIONS -->
<%-- CShimek	02/10/2005	Create JSP --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek	10/05/2005	ER#23756 - revise titles --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseDecisionSummary.jsp</title>
</head>
<!--BEGIN HEADER TAG-->

<!--BEGIN BODY TAG-->
<body>
<html:form action="/submitWarrantReleaseDecision" target="content">

<!-- BEGIN HEADING AND INSTRUCTION TABLES -->
<logic:equal name="juvenileWarrantForm" property="action" value="summary"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|31">
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantEnterReleaseDecision"/> Summary
        </td>
     </tr>
   </table>
   <table width="98%">
   	<tr>
     	<td><ul>
        	<li>Select Finish button to release juvenile to <bean:write name="juvenileWarrantForm" property="releaseDecision"/>.</li>
	  	</ul></td>
  	</tr>
</table>
</logic:equal>
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|29">   
   <table align="center">
     <tr>
        <td class="header">
          <bean:message key="title.juvWarrantJuvenileReleaseDecision"/> Confirmation
        </td>  
     </tr>
   </table>
   <table width="98%">
   	 <tr>
       <td align="center" class="confirm">Juvenile Warrant Release Decision successfully updated and notification sent.</td>
    </tr>
   </table>
</logic:equal>
<!-- END HEADING AND INSTRCTION TABLE -->

          <table width=98% align="center" cellspacing="1" cellpadding=4 border=0>
          <!-- BEGIN WARRANT INFORMATION TABLE -->
	          <tr>
						 	<td class=detailHead nowrap colspan=4><bean:message key="prompt.juvenileWarrantInfo" /></td>
						</tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.warrantNumber"/></td>          
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
                <td class="formDeLabel"><bean:message key="prompt.warrantStatus"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantStatus"/></td>
             </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.serviceAddress"/></td>          
                <td class="formDe" colspan="3">
                   <logic:equal name="juvenileWarrantForm" property="serviceAddress" value="">Not Available</logic:equal>                                                
                   <bean:write name="juvenileWarrantForm" property="serviceAddress"/>
                </td>             
             </tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.serviceDate"/></td>          
                <td class="formDe">
                   <bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
                </td>
                <td class="formDeLabel"><bean:message key="prompt.serviceTime"/></td>
                <td class="formDe">                          
                   <bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" />
                </td>
             </tr>             
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.activationDate"/></td>
                <td class="formDe" colspan="3">
                   <bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" /></td>
             </tr>
             <tr>
                <td class="formDeLabel" ><bean:message key="prompt.returnOfServiceSignatureStatus"/></td>
                <td class="formDe" colspan="3">
                   <logic:equal name="juvenileWarrantForm" property="serviceReturnSignatureStatus" value="">Not Available</logic:equal>                                                
                   <bean:write name="juvenileWarrantForm" property="serviceReturnSignatureStatus"/>
                </td>
             </tr>
             <!-- END WARRANT INFORMATION TABLE -->
             <tr><td><br></td></tr>
             <!-- BEGIN JUVENILE SERVERED INFORMATION TABLE -->
             <tr>
				<td class=detailHead nowrap colspan=4>Juvenile Served Information<%-- <bean:message key="prompt.juvenileServedInfo" /> --%></td>
			 </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>          
                <td class="formDe" colspan="3">
                    <bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/>
                </td>
            </tr>          
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.race"/></td>          
                <td class="formDe">
                   <logic:equal name="juvenileWarrantForm" property="race" value="">Not Available</logic:equal>                                                
                   <bean:write name="juvenileWarrantForm" property="race"/>
                </td>
                <td class="formDeLabel"><bean:message key="prompt.sex"/></td>
                <td class="formDe">
                   <logic:equal name="juvenileWarrantForm" property="sex" value="">Not Available</logic:equal>                                                                
                   <bean:write name="juvenileWarrantForm" property="sex"/>
                </td>
            </tr>             
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>          
                <td class="formDe" colspan="3">
                   <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
                </td>
            </tr>
            <!-- END JUVENILE SERVERED INFORMATION TABLE -->
            <tr><td><br></td></tr>
            <!-- BEGIN EXECUTOR INFORMATION -->
            <tr>
				<td class=detailHead nowrap colspan=4><bean:message key="prompt.executorInfo" /></td>
			</tr>
            <tr>
               <td class="formDeLabel"><bean:message key="prompt.officerIdNumber"/></td>
               <td class="formDe">
                  <logic:equal name="juvenileWarrantForm" property="executorId" value="">Not Available</logic:equal>                                                               
                  <bean:write name="juvenileWarrantForm" property="executorId"/>
               </td>
               <td width=1% nowrap class="formDeLabel"><bean:message key="prompt.officerIdType"/></td>
               <td class="formDe">
               <logic:equal name="juvenileWarrantForm" property="executorIdType" value="">Not Available</logic:equal>                                                               
                  <bean:write name="juvenileWarrantForm" property="executorIdType"/>
               </td> 
            </tr>
            <tr>   
	           <td class="formDeLabel"><bean:message key="prompt.department"/></td>
               <td class="formDe" colspan="3">
                  <bean:write name="juvenileWarrantForm" property="executorDepartmentName"/>
               </td>
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
               <td class="formDe">
                  <bean:write name="juvenileWarrantForm" property="executorEmail"/>
               </td>
            </tr>
         		<!-- END EXECUTOR INFORMATIN TABLE -->
         		<tr><td><br></td></tr>
         		<!-- BEGIN RELEASE DECISION INFORMATION TABLES -->
            <tr>
				<td class=detailHead colspan=4 nowrap><bean:message key="prompt.releaseDecision"/> Information<%-- <bean:message key="prompt.releaseDecisionInfo" /> --%></td>
			</tr>
         		<tr>
                <td class="formDeLabel" nowrap width= "1%"><bean:message key="prompt.releaseDecisionName"/></td>          
                <td class="formDe" colspan=3>
                   <bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/>
                </td>
            </tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.releaseDecision"/></td>          
                <td class="formDe" colspan=3>                   
                   <bean:write name="juvenileWarrantForm" property="releaseDecision"/>
                </td>
            </tr>
            <logic:equal name="juvenileWarrantForm" property="action" value="confirm">
            	<tr>
               		<td class="formDeLabel"><bean:message key="prompt.releaseDecisionDate"/></td>
               		<td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" /></td>
               		<td class="formDeLabel" nowrap width= "1%"><bean:message key="prompt.releaseDecisionTime"/></td>
               		<td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" /></td>               		
            	</tr>
            </logic:equal>
         		<!-- END RELEASE DECISION INFORMATION TABLES -->
           </table>
        <br>
<!-- BEGIN BUTTON TABLE -->
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">  
  <table width="98%" border="0" align="center">
	<tr>
	  <td align="center">
  		   <html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%>
	  </td>
    </tr>
  </table>  	  	   
</logic:equal>
<!-- cancel button for summary added to action -- could not split into seperate form without html error --> 
<logic:equal name="juvenileWarrantForm" property="action" value="summary">
  <table width="98%" border="0" align="center">
	 <tr valign="top">
	    <td align="center"> 
          <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
		  </html:button>&nbsp;
	      <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
		  </html:submit>&nbsp;
          <html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
		  </html:submit>     
       </td>
	 </tr>
  </table>
</logic:equal> 
<!-- END BUTTON TABLE -->
</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>