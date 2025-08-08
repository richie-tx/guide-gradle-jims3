<!DOCTYPE HTML>
<!-- 05/08/2007 CShimek		- #41271 remove badge and other Id number -->
<!-- 05/24/2007 CShimek		- added hidden field genericUserTypeId in update to correct js error, found testing defect #42152 -->
<!-- 07/06/2007 CShimek     - #43456 replaced casework_uitl.js with security_util.js  -->
<!-- 08/10/2007 CShimek     - #44357 remove public indicator selection/display -->
<!-- 08/10/2007 CShimek     - #45756 revised hidden field for generic user type to use Id instesd of description value -->
<!-- 02/06/2009 CShimek     - #56860 add Back to Top  -->
<!-- 10/19/2015 R Young     - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ include file="../../jQuery.fw" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ page import="naming.Features" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<html:javascript formName="userProfileCreateUpdateForm"/>


<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - userProfileCreateUpdate.jsp</title>

<!-- STRUTS VALIDATIONS-->

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/userProfiles/validateDepartment.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/userProfiles/userProfileCreateUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>
<script language="javascript">
$(function(){
	expandSections();
	
	$("#userStatusId").on("change", function(){
		expandSections();
	});
});
function expandSections()
{
	
	 if(document.forms[0].actionType.value == "update")
	 {	 	
	    var offset = document.userProfileForm["userStatusId"].selectedIndex ;
	 	var valId = document.userProfileForm["userStatusId"].options[ offset ].value ;
	 	if(valId == "I")
		{
		 $('#userStat').show();
		}
	 	else
		{
		  document.forms[0].inactivationDate.value="";
		  document.forms[0].inactivationTimeId.selectedIndex=0;
		  $('#userStat').hide();	
		 
		  //clear values
		  //document.userProfileForm["inactivationDate"].value = "";
		  //document.userProfileForm["inactivationTime"].value = "";
		}
	 }
		if(document.forms[0].actionType.value == "create")
		 {
			
		 	var deptId = document.forms[0].departmentType.value ;
		 	var userType = document.forms[0].loggedInType.value ;	
		 
			if(deptId == "JUV" || userType=="MA")
			{
			 $('#uvCode').show();
			}
			else
			{
				$('#uvCode').hide();	 		 
			}
			
			if(userType=="MA")
			{
			 $('#customCode').show();
			 $('#public').show();
			}
			else
			{
			  $('#customCode').hide();	
			}
		}
			
}
</script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<pg:pager></pg:pager>
<!-- BEGIN HEADING TABLE -->
<table align="center" >
	<tr>
		<td class="header" align="center">
			<logic:equal name="userProfileForm" property="action" value="create">
				<bean:message key="title.createUser"/>        
			</logic:equal>
			<logic:equal name="userProfileForm" property="action" value="update">
				<bean:message key="title.updateUser"/>        
			</logic:equal>
		</td>	
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE --> 
<table width="98%" align="center">
	<tr>
    <td>
      <ul>
      <logic:equal name="userProfileForm" property="action" value="create">
        <li>Enter at least the required fields then select Next to create new User Profile.</li>
      </logic:equal>
      <logic:equal name="userProfileForm" property="action" value="update">
      
       	<li>Enter at least the required fields then select Next to update User Profile.</li>
       </logic:equal>
      </ul>
    </td>
  </tr>
   <tr>
     <td class="required"><bean:message key="prompt.requiredFields" /></td>
   </tr>
   <tr>
     <td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/>
    Required if not Generic Login</td>
   </tr>
   <tr>
	 <td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
   </tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN FORM -->
<html:form action="/displayUserProfileCreateUpdateSummary" target="content" focus="userName.lastName">
<logic:equal name="userProfileForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|75">	
</logic:equal>
<logic:equal name="userProfileForm" property="action" value="update">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|238">	
</logic:equal>	
<!--BEGIN INPUT FIELD TABLE -->
<!-- BEGIN BLUE TABLE -->
<table class="borderTableBlue" cellpadding=2 cellspacing="0" border="0" width="98%" align="center">
	<tr>
		<td class="detailHead" nowrap><bean:message key="prompt.generalInfo" /></td>
	</tr>
	<tr>
		<td>
<!-- BEGIN DETAILS TABLE bgcolor="#f0f0f0"-->
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
				<tr>
					<td><input type="hidden" name="actionType" value="<bean:write name="userProfileForm" property="action"/>"/>
						<input type="hidden" name="genericType" value="<bean:write name="userProfileForm" property="genericUserType"/>"/>
						<input type="hidden" name="departmentType" value="<bean:write name="userProfileForm" property="departmentId"/>"/>
						<input type="hidden" name="loggedInType" value="<bean:write name="userProfileForm" property="loggedInUserType"/>"/>
					</td>
				</tr>
				<tr>
	    	    	<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.lastName" /></td>
	    			<td class="formDe" colspan="3"><html:text name="userProfileForm" property="userName.lastName" size="30" maxlength="75"/></td>
				</tr>
				<tr>  
					<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.firstName" /></td>
					<td class="formDe" colspan="3"><html:text name="userProfileForm" property="userName.firstName" size="25" maxlength="50"/></td>           
				</tr>
				<tr>	
					<td class="formDeLabel"><bean:message key="prompt.middleName" /></td>
					<td class="formDe" colspan="3"><html:text name="userProfileForm" property="userName.middleName" size="25" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="formDeLabel">
						<bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateOfBirth"/>
					</td>
					<td class="formDe" colspan="3">
						<html:text name="userProfileForm" property="dateOfBirth" size="10" maxlength="10" styleId="dateOfBirth"/> 
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.SSN"/></td>
					<td class="formDe" colspan="3">
						<html:text name="userProfileForm" property="ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>- 
						<html:text name="userProfileForm" property="ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" />- 
						<html:text name="userProfileForm" property="ssn.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
					</td>
				</tr>          
				<logic:equal name="userProfileForm" property="action" value="create">
					<tr>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.2.diamond"/>
							<bean:message key="prompt.department" /> <bean:message key="prompt.code" />
						</td>
						<td class="formDe" colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>                       		
										<html:text name="userProfileForm" property="departmentId" size="3" maxlength="3" /> &nbsp;&nbsp;
										
										<html:submit property="submitAction" onclick="return validateDepartment();">
											<bean:message key="button.validateDepartmentCode"></bean:message>
										</html:submit>
									</td>
								</tr>
								<tr>
									<td>&nbsp;Or&nbsp;
									     <a href="javascript:changeFormActionURL('userProfileForm', '/<msp:webapp/>displayDepartmentSearch.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
									</td>
								</tr>
							</table>	
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userProfileForm" property="action" value="update">
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.userId" /></td>
						<td class="formDe"><bean:write name="userProfileForm" property="logonId"/></td>           
						<td class="formDeLabel"><bean:message key="prompt.userStatus" /></td>
						<td class="formDe">
							<html:select name="userProfileForm" property="userStatusId" styleId="userStatusId">
								<option value=""><bean:message key="select.generic" /></option>
								<html:optionsCollection name="userProfileForm" property="userStatuses" value="code" label="description"/>
							</html:select>
						</td>    
					</tr>    
               	 	<tr>
		<%-- 		    	<td class="formDeLabel"><bean:message key="prompt.public" /></td>
		              	<td class="formDe">
		              		<html:radio name="userProfileForm" property="publicInd" value="Y"/>Yes
		              		<html:radio name="userProfileForm" property="publicInd" value="N"/>No
		              	</td>  --%>
		           	  <logic:equal name="userProfileForm" property="jims2Ind" value="y">        
					 	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.genericUserType" /></td>
        		      	<td class="formDe" colspan="3">
									<html:select property="genericUserTypeId" styleId="genericUserTypeId">
									   <html:optionsCollection property="genericUserTypes" value="code" label="description"/>
									</html:select>  
			            </td>
			           </logic:equal> 
        		       <logic:notEqual name="userProfileForm" property="jims2Ind" value="y">         
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType" /></td>
        		      	<td class="formDe" colspan="3">
        		      		<bean:write name="userProfileForm" property="genericUserType" />
        		      		<input type="hidden" name="genericUserTypeId" value="<bean:write name="userProfileForm" property="genericUserTypeId" />">
        		      	</td>
        		       </logic:notEqual>
					</tr>   
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.agency" /></td>
						<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="agencyId"/> <bean:write name="userProfileForm" property="agencyName"/></td>           
					</tr>
					<tr>
						<td class="formDeLabel" ><bean:message key="prompt.department" /> <bean:message key="prompt.name" /></td>
						<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentId"/> <bean:write name="userProfileForm" property="departmentName"/></td>
					</tr>
				</logic:equal>
				<logic:equal name="userProfileForm" property="action" value="create">
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.department" /> <bean:message key="prompt.name" /></td>
						<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentName"/></td>
					</tr>
				</logic:equal>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.org" /> <bean:message key="prompt.code" /></td>
					<logic:notEqual name="userProfileForm" property="action" value="update">
						<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="orgCode"/></td>
					</logic:notEqual>
					<logic:equal name="userProfileForm" property="action" value="update">
						<td class="formDe"><bean:write name="userProfileForm" property="orgCode"/></td>
						<td class="formDeLabel"><bean:message key="prompt.operatorId" /></td>
						<td class="formDe"><bean:write name="userProfileForm" property="OPID"/></td>
					</logic:equal>
				</tr>
				<tr> 
					<td class="formDeLabel"><bean:message key="prompt.2.diamond"/>
						<bean:message key="prompt.phone" />
					</td>
					<td class="formDe" colspan="3">
						<html:text name="userProfileForm" property="phoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
						<html:text name="userProfileForm" property="phoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
						<html:text name="userProfileForm" property="phoneNum.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
						<strong><bean:message key="prompt.ext"/></strong>
						<html:text name="userProfileForm" property="phoneNum.ext" size="4" maxlength="6" onkeyup="return autoTab(this, 6);" />
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.email" /></td>
					<td class="formDe" colspan="3"><html:text name="userProfileForm" property="email" size="50" maxlength="100" /></td>
				</tr>
				<logic:equal name="userProfileForm" property="action" value="create">
					<tr id='public' class='hidden'>  
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.genericUserType" /></td>
						<td class="formDe" colspan="3">
							<html:select property="genericUserTypeId" name="userProfileForm" size="1">
								<html:optionsCollection property="genericUserTypes" value="code" label="description" name="userProfileForm"/>
							</html:select>
						</td>
					</tr> 
				</logic:equal>
				<logic:equal name="userProfileForm" property="action" value="create">
					<%-- u.s #79250  --%>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_SECSERVPROV%>'>
					<tr id='uvCode' class='hidden'>  
						<td class="formDeLabel" nowrap><bean:message key="prompt.uvCodeGeneration"/></td>
						<td class="formDe" colspan="3">
							<html:radio name="userProfileForm" property="uvCodeGeneration" value="Y" disabled="true"/>Yes
							<html:radio name="userProfileForm" property="uvCodeGeneration" value="N" disabled="true"/>No
						</td> 
					</tr>
					</jims2:isAllowed> 
					<tr id='customCode' class='hidden'>
						<td class="formDeLabel" nowrap><bean:message key="prompt.customCodeGeneration"/></td>
						<td class="formDe" colspan="3"><html:text property="customCodeGeneration" name="userProfileForm" size="10" maxlength="8" /></td>
					</tr> 
				</logic:equal>
<%-- this hidden value needed for struts validation  05/08/2007 CShimek--%>				
				<logic:equal name="userProfileForm" property="action" value="update">
						<input type="hidden" name="customCodeGeneration" >
				</logic:equal>
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.requestorLastName" /></td>
					<td class="formDe" colspan="3"><html:text name="userProfileForm" property="requestorName.lastName" size="30" maxlength="75"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.requestorFirstName" /></td>
					<td class="formDe" colspan="3"><html:text name="userProfileForm" property="requestorName.firstName" size="25" maxlength="50"/></td>
				</tr>
				<logic:equal name="userProfileForm" property="action" value="update">
						<tr> 
		    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.date"/></td>
		    				<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferDate"/></td>    				
		    			</tr>
		    			<tr>
		    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.time"/></td>
		    				<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferTime"/></td> 
		 				</tr>
		 			
					
				</logic:equal>
				<tr id='userStat' class='hidden'>
					<td class="formDeLabel" nowrap><bean:message key="prompt.inactivate" /> <bean:message key="prompt.date" /></td>
					<td class="formDe"><html:text name="userProfileForm" property="inactivationDate" size="10" maxlength="10" />
					    <A HREF="#" onClick="cal1.select(document.userProfileForm.inactivationDate,'anchor2','MM/dd/yyyy'); return false;"
						   NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar" /></A></td>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.inactivate" /> <bean:message key="prompt.time" /></td>
					<td class="formDe">
						<html:select property="inactivationTimeId" name="userProfileForm">
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection property="workDays" value="code" label="description"  name="userProfileForm"/>
						</html:select>
					</td>
				</tr>
			</table>
<!-- END DETAILS TABLE -->
		</td>
	</tr>      
</table>
<!--END BLUE TABLE -->
<br>
<table class="borderTableBlue" cellpadding="0" cellspacing="0" border="0" width="98%" align="center">
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
				<tr>
					<td class="detailHead" nowrap><bean:message key="prompt.comments" /></td>
				</tr>
				<tr bgcolor="#f0f0f0">
					<td class="formDeLabel">
						<html:textarea style="width:100%" rows="5" property="comments" name="userProfileForm"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END INPUT FIELD TABLE -->
<!-- BEGIN BUTTON TABLE -->
<br>
<table border="0" width="98%">
	<TBODY>
		<tr>
			<td align="center">
				<html:submit property="submitAction">
					<bean:message key="button.back"></bean:message>
				</html:submit>
				<html:submit property="submitAction" onclick="return validateFields() && disableSubmit(this, this.form)">
					<bean:message key="button.next"></bean:message>
				</html:submit>
				<html:reset><bean:message key="button.reset"></bean:message></html:reset>
				<html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
			</td>
		</tr>
	</TBODY>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
