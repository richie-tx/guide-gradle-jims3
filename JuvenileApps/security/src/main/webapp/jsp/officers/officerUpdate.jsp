<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	07/28/2005	Create JSP -->
<!-- C Shimek           02/23/2007  Commented out "disableDepartmentLinks" call as part of quick fix for delivery needed for March deployment. Problem found by Shirley -->
<!-- C Shimek 			03/12/2007  #39780 removed hardcoded "A" value in status drop down -->
<!-- C Shimek  			03/28/2007  #40559 removed old logic tags from juv location display -->
<!-- C Shimek			03/30/2007  #40837 added focus to form tag -->
<!-- C Shimek			03/30/2007  #45333 revised changeFormSettings() to function and address input field layout to standard format -->
<!-- C Shimek           02/05/2009  #56860 add Back to Top  -->
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
<!--CUSTOM TAG LIBRARIES FOR PRESENTION -->
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->
<%@ page import="naming.Features" %>
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading" /> - officerUpdate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

	<html:javascript formName="officerForm"/>

<tiles:insert page="/js/officer/officerUpdate.js" flush="true" />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE --> 
<!--  <script language="JavaScript" src="/<msp:webapp/>js/common.js"></script>-->
<!-- Javascript for emulated navigation -->
<script type="text/javascript">
function evaluateMangrFields() {
  element=document.getElementById("managerId");
  if(element.value==""){
  	document.getElementById("managerLastName").disabled=false;
 	document.getElementById("managerFirstName").disabled=false;
  	document.getElementById("managerMiddleName").disabled=false;
  	document.getElementById("managerLastName").value="";
 	document.getElementById("managerFirstName").value="";
  	document.getElementById("managerMiddleName").value="";
  	
   }
   return true;
}
function evaluateMangr() {
  element=document.getElementById("managerId");
  if(element.value==""){
  	document.getElementById("managerLastName").disabled=false;
 	document.getElementById("managerFirstName").disabled=false;
  	document.getElementById("managerMiddleName").disabled=false;
   }
  else{
    document.getElementById("managerLastName").disabled=true;
 	document.getElementById("managerFirstName").disabled=true;
  	document.getElementById("managerMiddleName").disabled=true;
  }
   return true;
}

function resetFix(resetBtn){
resetBtn.form.reset();
	toggleAll();
	evaluateMangr();
}

function changeFormSettings(theForm, theActionString)
{
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,true);
}

</script>

<!-- Javascript for emulated navigation -->

</head>
<body onLoad="javascript:toggleAll();" onKeyDown="return checkEnterKeyAndSubmit(event,true);"> 
<html:form  action="/displayOfficerProfileSummary" target="content" focus="lastName">
  <logic:equal name="officerForm" property="action" value="update">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|225">	
  </logic:equal>
  <logic:equal name="officerForm" property="action" value="create">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|55">	
  </logic:equal>
  <!-- BEGIN HEADING TABLE --> 
  <html:hidden property="clearCheckBoxes" value="true"/>
  <table width="100%"> 
    <tr>
      <td align="center" class="header">
          <logic:equal name="officerForm" property="action" value="update">
             Update&nbsp;<bean:message key="title.officerProfile"/>
          </logic:equal>
          <logic:equal name="officerForm" property="action" value="create">
             Create&nbsp;<bean:message key="title.officerProfile"/>
          </logic:equal>
      </td>	
    </tr> 
  </table> 
  <table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
  <!-- END HEADING TABLE --> 
  <br> 
  <!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
  <table width="98%"> 
    <tr> 
      <td><ul> 
          <li>Enter at least the required officer information and select Next button. </li>
          <li>Only an active manager User ID may be entered in the Manager ID field.</li> 
        </ul></td> 
    </tr> 
    <tr> 
      <td class="required"><bean:message key="prompt.2.diamond" />&nbsp;Required Fields</td> 
    </tr> 
    <tr> 
      <td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />&nbsp;At least one of these fields is required.</td> 
    </tr> 
     <tr> 
      <td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />&nbsp;Required for Officer Type of Juvenile Probation.</td> 
    </tr> 
  </table> 
  <br> 
  <!-- BEGIN DETAIL TABLE --> 
  <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
    <tr> 
      <td class="detailHead">&nbsp;<bean:message key="prompt.officer" />&nbsp;<bean:message key="prompt.info" /></td> 
    </tr> 
    <tr> 
      <td> <!-- BEGIN OFFICER INFORMATION TABLE --> 
        <table width="100%" border="0" cellpadding="2" cellspacing="1"> 
		<tr> 
            <td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.lastName" /></td> 
            <td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
                <html:text property="lastName" size="30" maxlength="75"/>
			</td>
        </tr>
		<tr> 
            <td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.firstName" /></td> 
            <td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
                <html:text property="firstName" size="25" maxlength="50"/>
			</td>
        </tr>
		<tr> 
            <td class="formDeLabel"><bean:message key="prompt.middleName" /></td> 
            <td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
                <html:text property="middleName" size="25" maxlength="50"/>
			</td>
        </tr>	
		  <tr> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" />
                                                  <bean:message key="prompt.2.diamond" />&nbsp;
                                                  <bean:message key="prompt.badge#" /></td> 
            <td class="formDe"><html:text property="badgeNum" size="11" maxlength="11"/></td> 
            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" />
                                                  <bean:message key="prompt.2.diamond" />&nbsp;
                                                  <bean:message key="prompt.otherId#" /></td> 
            <td class="formDe"><html:text property="otherIdNum" size="10" maxlength="10"/></td> 
          </tr>
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />
            <bean:message key="prompt.userId"/></td> 
            <td class="formDe">
            	<logic:equal name='officerForm' property='officer' value='true' >  
            	    <html:hidden name="officerForm" property="userId" />
            		<bean:write name="officerForm" property="userId" /> 
            	</logic:equal>
            	<logic:equal name='officerForm' property='officer' value='false'>  
            		<html:text property="userId" size="8" maxlength="8" /> <%-- onkeyup="disableDepartmentLinks(this.form)"/>   --%>
            	</logic:equal>
			</td> 
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.socialSecurity#" /></td> 
            <td class="formDe" > 
				
				<html:text property="ssn1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>
				-
				<html:text property="ssn2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>
				-
				<html:text property="ssn3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
			</td> 
          </tr> 
		  <tr>
			<td class="formDeLabel" nowrap width="1%">
				<bean:message key="prompt.2.diamond" />
				<bean:message key="prompt.departmentCode" />
			</td> 
            <td class="formDe" colspan="3">
              <logic:equal name="officerForm" property="action" value="update">
                 <html:text property="departmentId" size="3" maxlength="3"/>
  				 <html:submit property="submitAction" onclick="return validateDepartmentCode(this.form);">
				    <bean:message key="button.validateDepartmentCode"></bean:message>
				 </html:submit>
				 &nbsp;Or&nbsp;
			     <a href="javascript:changeFormActionURL('officerForm', '/<msp:webapp/>displaySearchForDepartments.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
			  </logic:equal>    
			  <logic:equal name="officerForm" property="action" value="create">
<%-- 02-23-2007 commented out logic tags becuase dept code unchangeable when duplicate user found --%>			  
<%-- 			     <logic:equal name="officerForm" property="userId" value=""> --%>
                    <html:text property="departmentId" size="3" maxlength="3"/>
<%--                  </logic:equal>
                 <logic:notEqual name="officerForm" property="userId" value="">
                    <html:text property="departmentId" disabled="true" size="3" maxlength="3"/>
                 </logic:notEqual>   --%>
                 <span id="userSpan">
 				    <html:submit property="submitAction" onclick="return validateDepartmentCode(this.form);">
					   <bean:message key="button.validateDepartmentCode"></bean:message>
				    </html:submit>
				     &nbsp;Or&nbsp;
			         <a href="javascript:changeFormActionURL('officerForm', '/<msp:webapp/>displaySearchForDepartments.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
			     </span>
			  </logic:equal>    
			</td> 
          <tr> 
            <td class="formDeLabel" nowrap width="1%">
				<bean:message key="prompt.departmentName" />
			</td>
            <td colspan="3" class="formDe"><bean:write name="officerForm" property="departmentName"/></td>				
          </tr>
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.2.diamond" />
                <bean:message key="prompt.officerType"/>
            </td>
            <jims:if name="officerForm" property="departmentId" value="juv" op="notEqual">
			  <jims:and name="officerForm" property="departmentId" value="JUV" op="notEqual"/>
			    <jims:then>
				<td class="formDe">
				   <html:select property="officerTypeId">
					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
					   <html:optionsCollection property="officerIdTypes" value="code" label="description" />
					</html:select>  
				</td>
			  </jims:then>	
		    </jims:if>
			<jims:if name="officerForm" property="departmentId" value="juv" op="equal">
			  <jims:or name="officerForm" property="departmentId" value="JUV" op="equal"/>
			    <jims:then>
			       <td class="formDe">
					   <html:select property="officerTypeId" value="J">
						   <html:option value="C">CRIMINAL JUSTICE</html:option>
						   <html:option value="J">JUVENILE PROBATION</html:option>  			            	  	
						   <html:option value="L">LAW ENFORCEMENT AGENCY</html:option>
						</html:select>  
			       </td>
			    </jims:then>
			</jims:if>
			<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.status"/></td>
			<logic:equal name="officerForm" property="action" value="create">
			  <td class="formDe">
			  	<html:select property="statusId">
					<html:optionsCollection name="officerForm" property="officerStatuses" value="code" label="description" />
				</html:select>
			  </td>
			</logic:equal>
			<logic:equal name="officerForm" property="action" value="update">
			  <td class="formDe">
			  	<html:select property="statusId">		
				    <html:optionsCollection name="officerForm" property="officerStatuses" value="code" label="description" />
				</html:select>
			  </td>
			</logic:equal>  
		  </tr>
		  <tr> 
            <td class="formDeLabel">
                <bean:message key="prompt.officerSubtype"/>
            </td>
			<td class="formDe" colspan="3">
			   <html:select property="officerSubTypeId">
				   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
				   <html:optionsCollection property="officerSubTypes" value="code" label="description" />
				</html:select>  
			</td>				
		  </tr>  
		  
		  <jims:if name="officerForm" property="departmentId" value="juv" op="equal">
			<jims:or name="officerForm" property="departmentId" value="JUV" op="equal"/>
			   <jims:then> 
				  <tr>
				    <td class="formDeLabel" nowrap>Juv&nbsp;<bean:message key="prompt.location" /></td>
		            <td colspan="3" class="formDe">
					   <html:select property="juvLocationId" onchange="changeFormSettings('officerForm', 'displayOfficerProfileCreate.do?submitAction=Find')">
						   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
						   <html:optionsCollection property="juvLocations" value="locationId" label="locationName" />
						</html:select>  
		            </td>
		          </tr>
		          <logic:notEmpty name="officerForm" property="juvUnits">
					 <tr>
					    <td class="formDeLabel" nowrap>Juv&nbsp;<bean:message key="prompt.unit" /></td>
			            <td colspan="3" class="formDe">
						   <html:select property="juvUnitId">
							   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							   <html:optionsCollection property="juvUnits" value="juvLocationUnitId" label="locationUnitName" />
							</html:select>  
			            </td>
					  </tr>
				  </logic:notEmpty>			  
		      </jims:then> 
		  </jims:if> 
          <tr> 
            <!-- in jsp use workPhone.areaCode, workPhone.prefix and workPhone.4Digit --> 
            <td class="formDeLabel"><bean:message key="prompt.workPhone" /></td> 
			<td class="formDe"> 
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>
						  <html:text property="workPhoneAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
						   <html:text property="workPhonePrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
						   <html:text property="workPhoneMain" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />&nbsp;
                	    </td>
                    	<td>&nbsp;</td><td class="formDeLabel">&nbsp;<bean:message key="prompt.ext"/>&nbsp;</td>
 						<td class="formDe"> <html:text property="extn" size="10" maxlength="10" onkeyup="return autoTab(this, 10);" />&nbsp; </td>
					</tr>
				</table>
			</td>
			<td colspan="2" class="formDe">
								<table cellpadding="2" cellspacing="2" class="formDe">
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.includeVendorSurvey" /></td>
										<td class="formDe">Yes <html:radio name="officerForm"
												property="survey" value="YES" /> No <html:radio
												name="officerForm" property="survey" value="NO" /></td>
									</tr>
								</table>
		   </td>
		  </tr> 
          <tr> 			  
			  <td class="formDeLabel"><bean:message key="prompt.homePhone" /></td> 
			  <td class="formDe"><html:text property="homePhoneAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="homePhonePrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="homePhoneMain" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
              </td>
			  <td colspan="2" class="formDe">
				<table cellpadding="2" cellspacing="2" class="formDe">
				 <tr>
				 <td class="formDeLabel"><bean:message key="prompt.inSupervisorPos" /></td>
				 <td class="formDe">Yes <html:radio name="officerForm"
				    	property="supervisor" value="YES" /> No <html:radio
						name="officerForm" property="supervisor" value="NO" />
			   	</td>
			 	</tr>
				</table>
			 </td>
			</tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.cellPhone" /></td> 
            <td class="formDe" colspan="3"><html:text property="cellPhoneAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="cellPhonePrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="cellPhoneMain" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" /></td> 
            </tr> 
          <tr>             
			<td class="formDeLabel"><bean:message key="prompt.pager" /></td> 
            <td class="formDe" colspan="3"><html:text property="pagerAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="pagerPrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
				  <html:text property="pagerMain" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" /></td> 			  
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.fax" /></td> 
            <td class="formDe" colspan="3"> 
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>
						  <html:text property="faxAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
						  <html:text property="faxPrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />&nbsp;-
						  <html:text property="faxMain" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
                        </td>  
        			</tr>
				</table>  
			</td>
          </tr> 
		 <jims:isAllowed requiredFeatures="<%=Features.JCW_OFFICERPROFILEJPOUPDATE%>">  
	          <tr> 
				<td class="formDeLabel"><bean:message key="prompt.specialtyUnit" /></td> 
				<!-- check for MA or SA -->
				<logic:equal name="officerForm" property="SA" value="true" >
					<td class="formDe" colspan="3"><html:text property="faxLocation" size="20" maxlength="20"/></td>            	   
            	</logic:equal>
	            <logic:equal name="officerForm" property="SA" value="false">
	            	<logic:equal name="officerForm" property="MA" value="true" >
	            		<td class="formDe" colspan="3"><html:text property="faxLocation" size="20" maxlength="20"/></td>             			
            		</logic:equal> 
            		<logic:equal name="officerForm" property="MA" value="false" >
            			<td class="formDe"><bean:write name='officerForm'  property="faxLocation" /></td> 
            		</logic:equal>
            	</logic:equal>
			  </tr>
		</jims:isAllowed>
		  <tr>
	            <td class="formDeLabel"><bean:message key="prompt.email" /></td> 
	            <td class="formDe" colspan="3"><html:text property="email" size="30" maxlength="100"/></td> 
	      </tr>
		  <tr>
			<td class="formDeLabel"><bean:message key="prompt.rank" /></td> 
			<td class="formDe" colspan="3">
			   <html:select property="rankId">
				   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
				   <html:optionsCollection property="ranks" value="code" label="description" />
				</html:select>  
			</td>    
          </tr> 		  
		  <tr> 
            <td class="formDeLabel"><bean:message key="prompt.ccj" /></td> 
            <td class="formDe"><html:text property="divisionName" size="25" maxlength="30"/></td> 
          
            <td class="formDeLabel"><bean:message key="prompt.onLeave" /></td> 
            <td class="formDe"><html:text property="assignedArea" size="20" maxlength="30"/></td>
		  </tr>
		  <tr>
            <td class="formDeLabel"><bean:message key="prompt.radioNumber" /></td> 
            <td class="formDe"><html:text property="radioNumber" size="10" maxlength="10"/></td> 
          
            <td class="formDeLabel"><bean:message key="prompt.positionTitle" /></td> 
            <td class="formDe"><html:text property="workShift" size="20" maxlength="20"/></td> 
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
												<td class="hidden">
            										<jims:sortResults beanName="officerForm" results="officerTraining" primaryPropSort="sortOrder" primarySortType="INTEGER" defaultSortOrder="DESC" sortId="1" />
            									</td>
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
													<td class="hidden"><bean:write name="trainingIndex"
														property="sortOrder" />
													</td>
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
            <td class="detailHead" colspan="4">
            <bean:message key="prompt.workDaySchedule" /></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.workDay" /></td> 
            <td class="formDeLabel"><bean:message key="prompt.startTime" /></td> 
            <td class="formDeLabel"><bean:message key="prompt.stopTime" /></td> 
            <td class="formDeLabel"><bean:message key="prompt.offDay" /></td> 
          </tr> 
          <tr>
            <td class="formDe"><bean:message key="prompt.sunday" /></td> 
				<td class="formDe">
				<span id="sundayStart" >
                  <html:select property="startTimeId0">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
				</td> 
				<td class="formDe">
				<span id="sundayEnd" >
                  <html:select property="endTimeId0">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
				</td> 
                <td class="formDe">
                  <html:multibox property="dayOff0" value="Y" onclick="toggle(this,'sundayStart');toggle(this,'sundayEnd');document.getElementById('startTimeId0').selectedIndex=0;document.getElementById('endTimeId0').selectedIndex=0;" />
               <!--input name="sundayOff" type="checkbox" value="" onclick="toggle(this,'sundayStart');toggle(this,'sundayEnd');"-->
                </td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.monday" /></td> 
            <td class="formDe">
            	<span id="mondayStart" >
                  <html:select property="startTimeId1" >
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code" label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
            <span id="mondayEnd" >
                  <html:select property="endTimeId1">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe"><html:multibox property="dayOff1" value="Y" onclick="toggle(this,'mondayStart');toggle(this,'mondayEnd');document.getElementById('startTimeId1').selectedIndex=0;document.getElementById('endTimeId1').selectedIndex=0;" /></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.tuesday" /></td> 
            <td class="formDe">
            <span id="tuesdayStart" >
                  <html:select property="startTimeId2">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
            <span id="tuesdayEnd" >
                  <html:select property="endTimeId2">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe"><html:multibox property="dayOff2" value="Y" onclick="toggle(this,'tuesdayStart');toggle(this,'tuesdayEnd');document.getElementById('startTimeId2').selectedIndex=0;document.getElementById('endTimeId2').selectedIndex=0;" /></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.wednesday" /></td> 
            <td class="formDe">
            <span id="wednesdayStart" >
                  <html:select property="startTimeId3">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
            <span id="wednesdayEnd" >
                  <html:select property="endTimeId3">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>&nbsp;
			</td> 
            <td class="formDe"><html:multibox property="dayOff3" value="Y" onclick="toggle(this,'wednesdayStart');toggle(this,'wednesdayEnd');document.getElementById('startTimeId3').selectedIndex=0;document.getElementById('endTimeId3').selectedIndex=0;" /></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.thursday" /></td> 
            <td class="formDe">
            <span id="thursdayStart" >
                  <html:select property="startTimeId4">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
             <span id="thursdayEnd" >
                  <html:select property="endTimeId4">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe"><html:multibox property="dayOff4" value="Y" onclick="toggle(this,'thursdayStart');toggle(this,'thursdayEnd');document.getElementById('startTimeId4').selectedIndex=0;document.getElementById('endTimeId4').selectedIndex=0;" /></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.friday" /></td> 
            <td class="formDe">
             <span id="fridayStart" >
                  <html:select property="startTimeId5">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
             <span id="fridayEnd" >
                  <html:select property="endTimeId5">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe"><html:multibox property="dayOff5" value="Y" onclick="toggle(this,'fridayStart');toggle(this,'fridayEnd');document.getElementById('startTimeId5').selectedIndex=0;document.getElementById('endTimeId5').selectedIndex=0;" /></td> 
          </tr> 
          <tr> 
            <td class="formDe"><bean:message key="prompt.saturday" /></td> 
            <td class="formDe">
             <span id="saturdayStart" >
                  <html:select property="startTimeId6">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe">
            <span id="saturdayEnd" >
                  <html:select property="endTimeId6">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="workDays" value="code"
							label="description" />
				  </html:select>
				  </span>
			</td> 
            <td class="formDe"><html:multibox property="dayOff6" value="Y" onclick="toggle(this,'saturdayStart');toggle(this,'saturdayEnd');document.getElementById('startTimeId6').selectedIndex=0;document.getElementById('endTimeId6').selectedIndex=0;" /></td> 
          </tr> 
        </table> 
        
        <!-- END WORK DAY TABLE --> 
        <!-- BEGIN ADDRESS TABLE --> 
        <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
          <tr> 
            <td class="detailHead" colspan="3">&nbsp;<bean:message key="prompt.workAddress" /></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td> 
            <td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName" /></td> 
          </tr>

          <tr>  
            <td class="formDe"><html:text property="streetNumber" size="15" maxlength="15"/></td> 
            <td class="formDe" colspan="2"><html:text property="streetName" size="25" maxlength="40"/></td> 
          </tr>
          <tr>  
            <td class="formDeLabel"><bean:message key="prompt.streetType" /></td> 
            <td class="formDeLabel" colspan="2"><bean:message key="prompt.aptSuite" /></td> 
          </tr> 
          <tr>            
            <td class="formDe">
                  <html:select property="streetTypeId">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="streetTypes" value="code"
							label="description" />
				  </html:select>
            </td> 
            <td class="formDe" colspan="2"><html:text property="apartmentNumber" size="6" maxlength="10"/></td> 
          </tr> 
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.city"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.state"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.zipCode"/></td> 
          </tr> 
          <tr> 
            <td class="formDe"><html:text property="city" size="19" maxlength="25" /></td> 
            <td class="formDe"><html:select property="stateId">
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="officerForm" property="states" value="code"
							label="description" />
				  </html:select></td> 
            <td class="formDe" colspan="2">
            	<html:text property="zipCode" size="5" maxlength="5"/>
            	<html:text property="additionalZipCode" value="" size="4" maxlength="4"/>
            </td> 
          </tr> 
        </table> 
        <!-- END ADDRESS TABLE --> 
       
	   <!-- Manager Information table -->
	   <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
		  <tr class="detailHead">
            <td class="detailHead" colspan="4">
            <bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.info"/></td>
          </tr>
          <tr>
			  <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.userId"/></td>
			  <td class="formDe">
				<html:text property="managerId" size="8" maxlength="8" onchange="javascript:evaluateMangrFields();"/>
				<html:submit property="submitAction" onclick="return validateManagerId(this.form);">
					<bean:message key="button.validateManagerId"></bean:message>
				</html:submit>
				&nbsp;Or&nbsp;
				<a href="javascript:changeFormActionURL('officerForm', '/<msp:webapp/>displayOfficerManagerSearch.do', true);" onclick="return validateDepartmentCode(document.getElementById('officerForm'));"><bean:message key="prompt.searchForManager" /></a>
			  </td>
			</tr>
			<tr>
				<td class="formDeLabel"></td>
				<td class="formDe">
				Or
				</td>
			</tr>
			<tr>
			  <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.manager"/>&nbsp;<bean:message key="prompt.name"/></td>
			  <td class="formDe"> 
				<table> 
					<tr> 
					  <td class="formDeLabel"><bean:message key="prompt.last"/></td> 
					  <td class="formDeLabel"><bean:message key="prompt.first"/></td>
					  <td class="formDeLabel"><bean:message key="prompt.middle"/></td>
					</tr>
					<tr>
					
					<logic:notEmpty name="officerForm" property="managerId">
						 <td class="formDe"><html:text property="managerLastName" disabled="true" size="25" maxlength="75"  /></td>
                      <td class="formDe"><html:text property="managerFirstName" disabled="true" size="20" maxlength="50"/></td>
                      <td class="formDe"><html:text property="managerMiddleName" disabled="true" size="20" maxlength="50"/></td>
					</logic:notEmpty>
					<logic:empty name="officerForm" property="managerId">
						<td class="formDe"><html:text property="managerLastName" size="25" maxlength="75"  /></td>
                      <td class="formDe"><html:text property="managerFirstName" size="20" maxlength="50"/></td>
                      <td class="formDe"><html:text property="managerMiddleName" size="20" maxlength="50"/></td>
					</logic:empty>
                   
					</tr> 
				</table> 
			  </td>
			</tr>
		</table>
       </td>
    </tr>
  </table> 
  <!-- END DETAIL TABLE --> 
  <br> 
  <!-- BEGIN BUTTON TABLE --> 
  <table width="100%"> 
    <tr> 
      <td align="center"> 
			<html:submit property="submitAction">
				<bean:message key="button.back"></bean:message>
		   </html:submit>
            
              <html:submit property="submitAction" onclick="return validateCreateFields(this.form)">
			    <bean:message key="button.next"></bean:message>
			 
			  </html:submit>
           
			<html:reset onclick="resetFix(this)"><bean:message key="button.reset"/></html:reset>
		   <logic:equal name="officerForm" property="action" value="create">
		    <html:submit property="submitAction">
				<bean:message key="button.cancelCreate"></bean:message>
		   </html:submit>
		   </logic:equal>
		   <logic:notEqual name="officerForm" property="action" value="create">
		    <html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
		   </html:submit>
		   </logic:notEqual>
	</td>
    </tr> 
  </table> 
  <!-- END BUTTON TABLE --> 
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
