<!DOCTYPE HTML>
<!-- Used to update juvenile warrant service Status page 1 of 2. -->
<!--MODIFICATIONS -->
<%-- CShimek	02/25/2005	Create JSP --%>
<%-- CShimek    08/12/2005  Revised Look and Feel --%>
<%-- CShimek	10/05/2005	ER#23756 - revise titles --%>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- LDeen      02/14/2006  Fixed instruction re Defect #28189 --%>
<%-- HRodriguez 09/21/2006  ER#35269 Revised Executor Information section. --%>
<%-- HRodriguez 11/08/2006  Defect#36560 & #36564 Reset other fields when user switch Search By 
     fields and display OfficerId after clicking back. --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek    03/23/2007  Add missing disablebutton found while testing height/weight defect --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- CShimek    06/13/2007  #42801 moved embedded scripts into warrantServiceStatusUpdate1.js --%>
<%-- CShimek    08/28/2007  #41035 added required field marker to Search By field --%>
<%-- DWilliamson 04/17/2008 #51016 Increased Officer ID field length to 11. --%>
<%-- RYoung     06/16/2014  #TFS 11654 Pre-populate logged on users id --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<html:base />
<title><bean:message key="title.heading"/> - warrantServiceStatusUpdate1.jsp</title>
<!-- JAVASCRIPT FILES-->

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/warrant_utils.js"></script>

<tiles:insert page="../js/warrantServiceStatusUpdate1.js" />

</head>

<!--BEGIN BODY TAG-->      
<body  onload="javascript:evalSearch(document.forms[0],false);" && onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayWarrantServiceStatusUpdate2" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|132">

<!-- BEGIN HEADING TABLE -->
<table width="98%">
  	<tr>
    	<td align="center" class="header" ><bean:message key="title.juvWarrantRecordWarrantExecutorInfo"/></td>
  	</tr> 	
</table>
<!-- END HEADING TABLE -->

<br>
<!-- BEGIN INSTURCTION TABLE -->
<table width="98%">
  <tr>
    <td>
    	<ul>
        <li>Enter User ID or Officer ID Number, then select Officer ID Type and select Agency.
        <li>Select Next button to retrieve the Executor Information and continue updating status.</li>
      </ul>
  	</td>
  </tr>
  <tr>
    <td class="required">&nbsp;<bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>      
  </tr>
  <tr>
  	<td align="center" class="errorAlert"><html:errors /></td>
   </tr>
   <logic:equal name="juvenileWarrantForm" property="validDeptCode" value="true">
      <tr>
       <td align="center" class="confirm">Department code entered is valid.</td>
    </tr>
    </logic:equal> 
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN MAIN BODY TABLE -->
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
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantStatus"/></td>
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
		<td class=formDeLabel 
		 width=1% nowrap><bean:message key="prompt.warrantActivationStatus"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantActivationStatus"/>
		</td>          
	</tr>
	
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/>
			<input type="hidden" id="juvenileNum" value="<bean:write name="juvenileWarrantForm" property="juvenileNum" />" />
			<input type="hidden" id="warrantNumber" value="<bean:write name="juvenileWarrantForm" property="warrantNum" />" />
			<input type="hidden" id="firstName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.firstName" />" />
			<input type="hidden" id="lastName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.lastName" />" />
		</td>          
		<td class=formDe colspan=3>
		<%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%>
			<bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
			<bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
			<bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
			<bean:write name="juvenileWarrantForm" property="nameSuffix"/>
		</td>
	</tr>
	
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
		</td>          
	</tr>
<!-- END GENERAL INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan="4" nowrap><bean:message key="prompt.executorInfo" /></td>
	</tr>
	<!-- BEGIN INQUIRY TABLE -->
	<tr>
		<td  colspan="4">
			<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.searchBy" /></td>
					<td class="formDe">
						<html:select name="juvenileWarrantForm" property="search" onchange="evalSearch(this.form,true)">
						  <html:option value="">Please Select</html:option>
						  <html:option value="userSearch">USER ID</html:option>
						  <html:option value="officerSearch">OFFICER ID</html:option>			 
						</html:select>						
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
				<tr id="byUserId" class=hidden>
	        		<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.userId"/></td>
	        		<td class=formDe><html:text property="userId" size="10" maxlength="5" onchange ="clearDeptName()" onkeypress="clearDeptName()"/>
 	        			<html:submit property="submitAction" onclick=" return loadDepartment('/<msp:webapp/>displayWarrantServiceStatusUpdate2.do?submitAction=findDepartment', 'userDepartment') && disableSubmit(this, this.form);">
							<bean:message key="button.findDepartment"></bean:message>
						</html:submit></td>
	  			</tr>
  			
	  			<tr id="byOfficerId" class=hidden>
	                <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdNumber"/></td>
	                <td class="formDe"><html:text property="officerId" size="11" maxlength="11"/></td>
	                <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdType"/></td>
	                <td class="formDe">
	                	<html:select property="officerIdTypeId">
	               			<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
	               			<html:optionsCollection name="codeHelper" property="officerIdTypes" value="code" label="description" />
	           			</html:select></td>    
	            </tr>
           	  
	        	<tr id="officerDept" class=hidden>
					<td class=formDeLabel valign="top" nowrap width=1%><bean:message key="prompt.1.diamond"/>
							<bean:message key="prompt.department" /> <bean:message key="prompt.code" />
					</td>
					<td class=formDe colspan="3">
						<table width="100%" border="0" cellpadding=1 cellspacing=0>
							<tr>
								<td>                       		
									<html:text name="juvenileWarrantForm" property="officerAgencyId" size="3" maxlength="3" />
								</td>
								<td>	
									<html:submit property="submitAction" onclick="return validateDepartment() && disableSubmit(this, this.form);">
										<bean:message key="button.validateDepartmentCode"></bean:message>
									</html:submit>
								</td>
							</tr>
							<tr> 
								<td></td>
								<td>&nbsp;Or&nbsp;
									<a href="javascript:changeFormActionURL('juvenileWarrantForm', '/<msp:webapp/>displayDepartmentSearch.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
								</td>
							</tr>
						</table>	
					</td>
				</tr>
				<tr>
	                <td class="formDeLabel" nowrap width=1%><bean:message key="prompt.department"/> <bean:message key="prompt.name"/></td>
	                <td class="formDe" colspan="3" id="deptNameTd"><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>  
	        	</tr>
			</table>
       </td>
   </tr>  
<!-- END LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->	
</table>
<!-- END MAIN BODY TABLE -->

<br>
<!-- BEGIN BUTTON TABLE -->
<table align="center">
	 <tr valign="top">
	    <td>
			<html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="goNav('back');">
				<bean:message key="button.back"></bean:message>
		</html:button>&nbsp; 
	    <html:submit property="submitAction" onclick="return validateFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.reset"/></html:submit>&nbsp;
		</td>
		</html:form>
		<!-- END 1ST FORM -->	
		<td>
		  <html:form action="/displayGenericSearch.do?warrantTypeUI=warrantService"> 
             <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
          </html:form> 
       </td> 
  	</tr>
</table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
