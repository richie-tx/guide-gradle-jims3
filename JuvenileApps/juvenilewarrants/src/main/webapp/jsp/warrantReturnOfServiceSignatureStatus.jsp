<!DOCTYPE HTML>
<!-- Used to update juvenile warrant return of service signature. -->
<!--MODIFICATIONS -->
<%-- CShimek	01/13/2005	Create JSP --%>
<%-- CShimek    08/15/2005  Revise to new look and feel --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    10/06/2005  ER#23756 revise titles --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	01/31/2007  #39097 added multiple submit button logic --%>
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
<html:base />
<title>JIMS2 - warrantReturnOfServiceSignatureStatus.jsp</title>
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitWarrantReturnOfServiceSignatureStatus" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|42">
   
<!-- BEGIN HEADING TABLE -->
<table align="center">
  <tr>
    <td  class="header"><bean:message key="title.juvWarrantFileReturnOfServiceSignature"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTURCTION TABLE -->
<table width="98%">
   <tr>
     <td>
       <ul>
          <li>Select submit to update service signature status to Filed.</li>
       </ul>    
     </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN MAIN INFORMATION TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN WARRANT INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.warrantInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="warrantNum" value="">&nbsp;</logic:equal>             
			<bean:write name="juvenileWarrantForm" property="warrantNum"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="warrantStatus" value="">&nbsp;</logic:equal>             
			<bean:write name="juvenileWarrantForm" property="warrantStatus"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantType"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="warrantType" value="">&nbsp;</logic:equal>             
			<bean:write name="juvenileWarrantForm" property="warrantType"/>
		</td>          
		</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>          
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="juvenileName.formattedName"/> 
		</td>
	</tr>
<!-- END WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN LAW ENFORCEMENT/EXECUTOR INFORMATION TABLE -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.executorInfo" /></td>
	</tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.officerName"/></td>          
        <td class=formDe colspan=3>
           <bean:write name="juvenileWarrantForm" property="executorName"/>
        </td>
    </tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
        <td class=formDe>
           <logic:equal name="juvenileWarrantForm" property="executorId" value="">&nbsp;</logic:equal>               
           <bean:write name="juvenileWarrantForm" property="executorId"/>
        </td>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
        <td class=formDe>
           <logic:equal name="juvenileWarrantForm" property="executorIdType" value="">&nbsp;</logic:equal>               
           <bean:write name="juvenileWarrantForm" property="executorIdType"/>
        </td> 
    </tr>
    <tr>   
	    <td class=formDeLabel><bean:message key="prompt.department"/></td>
        <td class=formDe colspan=3>
                  <bean:write name="juvenileWarrantForm" property="executorDepartmentName"/>
        </td>
    </tr>            
    <tr>
        <td class=formDeLabel><bean:message key="prompt.workPhone"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPhoneNum.formattedPhoneNumber"/></td>
        <td class=formDeLabel><bean:message key="prompt.cellPhone"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorCellNum.formattedPhoneNumber"/></td>
    </tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.pager"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPager.formattedPhoneNumber"/></td>
        <td class=formDeLabel><bean:message key="prompt.email"/></td>
        <td class=formDe>
           <logic:equal name="juvenileWarrantForm" property="executorEmail" value="">&nbsp;</logic:equal>
           <bean:write name="juvenileWarrantForm" property="executorEmail"/>
        </td>
    </tr>            
<!-- END LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN SERVICE INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.serviceInfo" /></td>
	</tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.serviceDate"/></td>
        <td class=formDe>
   	 	   <bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
   	 	</td>
   		<td class=formDeLabel><bean:message key="prompt.serviceTime"/></td>
   		<td class=formDe>
   		   <bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" />
   		</td>
	</tr>
   	<tr>
        <td class=formDeLabel><bean:message key="prompt.serviceStatus"/></td>
        <td class=formDe colspan=3>
           <logic:equal name="juvenileWarrantForm" property="serviceStatus" value="">&nbsp;</logic:equal>                
           <bean:write name="juvenileWarrantForm" property="serviceStatus"/>
        </td>
 	</tr>
	<tr>
        <td class=formDeLabel><bean:message key="prompt.serviceAddress"/></td>          
        <td class=formDe colspan=3>
           <logic:equal name="juvenileWarrantForm" property="serviceAddress" value="">&nbsp;</logic:equal>
           <bean:write name="juvenileWarrantForm" property="serviceAddress"/>
        </td>             
	</tr>
	<tr>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.serviceReturnSignatureStatus"/></td>
        <td class=formDe colspan=3>
           <logic:equal name="juvenileWarrantForm" property="serviceReturnSignatureStatus" value="">&nbsp;</logic:equal>             
           <bean:write name="juvenileWarrantForm" property="serviceReturnSignatureStatus"/>
        </td>          
    </tr>  
<!-- END SERVICE INFORMATION BLOCK -->    
</table>
<!-- END MAIN INFORMATION TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr>
	  <td align="center">
	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
	    	<bean:message key="button.submit"></bean:message>
	    </html:submit>&nbsp;
  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
			<bean:message key="button.cancel" />
	  	</html:submit>
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>