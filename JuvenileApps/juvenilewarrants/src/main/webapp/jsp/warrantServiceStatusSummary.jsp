<!DOCTYPE HTML>
<!-- Used to display juvenile warrant service Status summary and confirmation pages. -->
<!--MODIFICATIONS -->
<%-- CShimek	02/25/2005	Create JSP --%>
<%-- CShimek    08/15/2005  Revise to new look and feel --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek	10/05/2005	ER#23756 - reivse titles --%>
<%-- CShimek    02/02/2006  Add hidden fields for HelpFile name --%>
<%-- HRodriguez 08/08/2006  #34030 - Revised the look & labels of Prior/Current Service Attempt sections. --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	01/31/2007  #39097 added multiple submit button logic --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RCapestani 09/10/2007  #44003 added Tag LIBRARIES NEEDED FOR CUSTOM JIMS and sort descending into PRIOR SERVICE ATTEMPTS BLOCK --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--TAG LIBRARIES NEEDED FOR CUSTOM JIMS -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
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
<title>JIMS2 - warrantServiceStatusSummary.jsp</title>

</head>
<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitWarrantServiceStatusUpdate" target="content">

<!-- BEGIN SUMMARY HEADING AND INSTRUCTION TABLES  -->
<logic:equal name="juvenileWarrantForm" property="action" value="summary" >
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|154">
  <table width="98%">    
  	<tr>
    	<td align="center" class="header" ><bean:message key="title.update"/>
    	                                   <bean:message key="title.juvWarrantWarrantServiceStatus"/>
                                           <bean:message key="title.summary"/></td>
  	</tr>
  </table>
  <br>
  <table width="98%">
    <tr>
      <td>
        <ul>
          <li>Verify all information is correct and select Finish to update information.</li>	    	
        </ul>
      </td>
    </tr>
  </table>
</logic:equal>
<!-- END SUMMARY HEADING AND INSTRUCTION TABLES  -->

<!-- BEGIN CONFIRMATION HEADING AND INSTRUCTION TABLES  -->
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|138">
  <table width="98%">    
  	<tr>
    	<td align="center" class="header" ><bean:message key="title.update"/>
    	                                   <bean:message key="title.juvWarrantWarrantServiceStatus"/>
                                           <bean:message key="title.confirmation"/></td>
  	</tr>
  </table>
	
  <br>
  <table width="98%" align="center">
    <tr>
      <td class="confirm" align="center">Juvenile Warrant Service has been successfully updated</td>
    </tr>
  </table>
</logic:equal>
<!-- END CONFIRMATION HEADING AND INSTRUCTION TABLES  -->

<!-- BEGIN MAIN INFORMATION TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN GENERAL INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.generalInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
		<td class=formDe>      
			<bean:write name="juvenileWarrantForm" property="warrantNum"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus"/></td>
		<td class=formDe>           
			<bean:write name="juvenileWarrantForm" property="warrantStatus"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantType"/></td>
		<td class=formDe colspan=3>                       
			<bean:write name="juvenileWarrantForm" property="warrantType"/>
		</td>          
	</tr>
	<tr>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantActivationStatus"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantActivationStatus"/>
		</td>          
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>          
		<td class=formDe colspan=3>
		<%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%>
			<bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
			<bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
			<bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
			<bean:write name="juvenileWarrantForm" property="nameSuffix"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
		</td>          
	</tr>
<!-- END GENERAL INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.executorInfo" /></td>
	</tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.executorName"/></td>          
        <td class=formDe colspan=3>
            <bean:write name="juvenileWarrantForm" property="executorName"/>
        </td>
	</tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
        <td class=formDe>                          
            <bean:write name="juvenileWarrantForm" property="executorId"/>
        </td>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
        <td class=formDe>                             
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
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPhoneNum"/></td>
        <td class=formDeLabel><bean:message key="prompt.cellPhone"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorCellNum"/></td>
    </tr>
    <tr>
        <td class=formDeLabel><bean:message key="prompt.pager"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPager"/></td>
        <td class=formDeLabel><bean:message key="prompt.email"/></td>
        <td class=formDe>
           <logic:equal name="juvenileWarrantForm" property="executorEmail" value=""></logic:equal>               
           <bean:write name="juvenileWarrantForm" property="executorEmail"/>
        </td>
    </tr>            
<!-- END LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN OPTIONAL COST INFORMATION BLOCK -->
	<tr>
		<td class="detailHead" colspan=4><bean:message key="prompt.serviceCostInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.mileage" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="costMileage"/></td>
		<td class=formDeLabel><bean:message key="prompt.airFare" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="costAirFare"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.perDiem" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="costPerDiem"/></td>
	</tr>
				
<!-- END OPTIONAL COST INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN PRIOR SERVICE ATTEMPTS BLOCK -->
   <tr>
      <td class="detailHead" colspan=4><bean:message key="prompt.priorServiceAttempts" /></td>
   </tr>
   <logic:empty name="juvenileWarrantForm" property="services">
      <tr>
        <td class=formDe colspan=4>No prior service attemps found</td>
      </tr>
      <tr><td>&nbsp;</td></tr>
   </logic:empty>	 
   <logic:notEmpty name="juvenileWarrantForm" property="services">
   <jims:sortResults beanName="juvenileWarrantForm" results="services" primaryPropSort="serviceTimeStamp" primarySortType="DATE" hideMe="True" defaultSort="True" defaultSortOrder="DESC" sortId="0" />
      <logic:iterate id="priorAddressIndex" name="juvenileWarrantForm" property="services"> 
        <tr>
           <td class=formDeLabel><bean:message key="prompt.status" /></td>
           <td class=formDe><bean:write name="priorAddressIndex" property="serviceStatus"/></td>
           <td class=formDeLabel><bean:message key="prompt.dateTime" /></td>
           <td class=formDe><bean:write name="priorAddressIndex" property="serviceTimeStamp" formatKey="date.format.mmddyyyy" />
           		&nbsp;<bean:write name="priorAddressIndex" property="serviceTimeStamp" formatKey="time.format.HHmm" /></td>
        </tr>
        <tr>     				
           <td class=formDeLabel><bean:message key="prompt.address" /></td>
           <td class=formDe colspan=3><bean:write name="priorAddressIndex" /></td>   
        </tr>
        <tr> 
           <td class=formDeLabel><bean:message key="prompt.comments" /></td>
           <td class=formDe colspan=3><bean:write name="priorAddressIndex" property="comments"/></td>
        </tr>
     	<tr><td>&nbsp;</td></tr>
	   </logic:iterate>   
   </logic:notEmpty>
<!-- END PRIOR SERVICE ATTEMPTS BLOCK -->

<!-- BEGIN CURRENT SERVICE ATTEMPT TABLE -->
	<tr>
		<td class="detailHead" colspan=4 ><bean:message key="prompt.currentServiceAttempt" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.status" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="currentWarrantServiceStatusDescription"/></td>
	   	<td class=formDeLabel><bean:message key="prompt.dateTime" /></td>
	   	<td class=formDe><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
   	   			&nbsp;<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" /></td>
   	</tr>	
	<tr>
		<td class=formDeLabel><bean:message key="prompt.address" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="currentServiceAddress"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.addressType" /></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="serviceAddressTypeDesc"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.badAddress" />?</td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="currentServiceBadAddress"/></td>
	</tr>
	<tr>
       	<td class=formDeLabel><bean:message key="prompt.comments" /></td>
   	   	<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="currentServiceAttemptComments"/></td>
   	</tr>
			  
<!-- END CURRENT SERVICE ATTEMPT BLOCK -->	
</table>
<!-- END MAIN TABLE -->

<br>
<!-- BEGIN BUTTON TABLE -->
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">  
  <table width="98%" border="0" align="center">
	<tr>
	  <td align="center">
  		   	<html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
			</html:submit>
</logic:equal>
	  </td>
	</tr>
  </table>	
</logic:equal>  
  <table width="98%" border="0" align="center">
	 <tr valign="top">  
	    <td align="right" width="55%">
 	    <logic:equal name="juvenileWarrantForm" property="action" value="summary">
	      <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>&nbsp;
	       <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
		   </html:submit>&nbsp;
        </logic:equal>
	    </td>
</html:form> 
        <logic:equal name="juvenileWarrantForm" property="action" value="summary">	   
	    <td align="left"> 	  
		   <html:form action="/displayGenericSearch.do?warrantTypeUI=warrantService">
 	  	      <html:submit property="submitAction">
 	  	         <bean:message key="button.cancel"></bean:message>
 	  	      </html:submit>
		   </html:form>       
	    </td>
	    </logic:equal>
     </tr>
  </table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>