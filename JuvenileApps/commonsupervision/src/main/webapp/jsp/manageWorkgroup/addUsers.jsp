<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/19/2007	 Hien Rodriguez - Create JSP -->
<!-- 04/27/2007  C. Shimek		- defect#41485 added script to check verify at least 1 name selected when user clicks "Add Selected Users to List" button -->
<!-- 05/01/2007  C. Shimek		- defect#41621 added script check to require at least 1 user be on selected list -->
<!-- 07/12/2007  C. Shimek 		- defect#43187 replaced pagination with div tags to solve multiple select problem -->
<!-- 02/06/2008  L. Deen 		- defect#49259 fixed page titles to match prototypes -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>  

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageWorkgroup/addUsers.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupForm2" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/organization.js"></script>
<script type="text/javascript">

function validateFields(theForm)
{	
	if (theForm.userLastName.value == "" &&
	    theForm.divisionId.value == "")
	{		
     	alert("User Last Name or Division is required for search.");
     	theForm["userLastName"].focus();
     	return false;
   	}
	
   	return true;
}
function checkUserSelect(theForm)
{
	var selUsers = document.getElementsByName("selectedUsers");
	for (d=0; d<selUsers.length; d++)
	{
		if (selUsers[d].checked == true)
		{
			return true;
		}
	}
	alert("At least 1 Name must be selected to Add to List.");
	return false;
}
/*  this function does not require a return */
function removeCheck(theForm, URL, pgType)
{
	var minValue = 1;	
	if (pgType.toUpperCase() == "CREATE")
	{
		minValue = -1;
	}	
	var selectedUsers = document.getElementsByName("selectedUser");	
	if (selectedUsers.length > minValue)
	{
		document.forms[0].action = URL;
		document.forms[0].submit();		
	} else {
		alert("Workgroup must have at least 1 user.");
	}
}
function checkSelectedUserList()
{
	var selectedUsers = document.getElementsByName("selectedUser");
	if (selectedUsers.length == 0)
	{
		alert("Workgroup must have at least 1 user.");
		return false;		
	}
	return true;
}

<logic:iterate indexId="organizationIterIndex" id="organizationIter" name="workgroupForm" property="organizations">
	organizations[<bean:write name="organizationIterIndex"/>] = new suborganization("<bean:write name="organizationIter" property="organizationId" />", "<bean:write name="organizationIter" property="description" />");
	<logic:notEmpty  name="organizationIter" property="children">	
	<logic:iterate indexId="organizationIterIndex2" id="organizationIter2" name="organizationIter" property="children">
		var innerOrganization = new suborganization("<bean:write name="organizationIter2" property="organizationId" />", "<bean:write name="organizationIter2" property="description" />");
		organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>] = innerOrganization;
		<logic:notEmpty name="organizationIter2" property="children">
		<logic:iterate indexId="organizationIterIndex3" id="organizationIter3" name="organizationIter2" property="children">
			var innerOrganization = new suborganization("<bean:write name="organizationIter3" property="organizationId" />", "<bean:write name="organizationIter3" property="description" />");
			organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>].suborganizations[<bean:write name="organizationIterIndex3"/>] = innerOrganization;
		</logic:iterate>
		</logic:notEmpty>
	</logic:iterate>
	</logic:notEmpty>
</logic:iterate>

</script> 

</head>

<body topmargin=0 leftmargin="0" onload="reloadOrganization(document.forms[0], 'divisionId','programUnitId','programSectionId','<bean:write name="workgroupForm" property="programUnitId"/>', '<bean:write name="workgroupForm" property="programSectionId"/>')" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayWorkgroupSummary" target="content" focus="userLastName">
<logic:equal name="workgroupForm" property="action" value="create">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|2">
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|4">
</logic:equal>


<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="workgroupsTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>							
					    			<td align="center" class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;
										<logic:equal name="workgroupForm" property="action" value="create">
											<bean:message key="prompt.create" /> <bean:message key="prompt.workgroup" />										
										</logic:equal>
										<logic:equal name="workgroupForm" property="action" value="update">
											<bean:message key="prompt.update" /> <bean:message key="prompt.workgroup" />										
										</logic:equal>
										&nbsp;-&nbsp; <bean:message key="prompt.addUsers" />
									</td>						
				  				</tr>			 						  
							</table>									
						<!-- END HEADING TABLE -->
						
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Enter required search criteria and select Find Users to locate users.</li>
										<li>Select the users and click Add Selected Users to add to your list. Click Next when list is complete.</li>
									</ul></td>
								</tr>																				
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN WORKGROUP INFO TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class=detailHead>
									<td class=paddedFourPix>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><img border=0 src="/<msp:webapp/>images/expand.gif" name="workgroupInfoFields" onclick="showHide('workgroupInfoFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
												<td class=detailHead>&nbsp;<bean:write name="workgroupForm" property="workgroupName" />&nbsp;<bean:message key="prompt.info" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="workgroupInfoFieldsSpan" class=hidden>
									<td colspan="3">
										<table border=0 align=center cellpadding=4 cellspacing=1 width=100%>											
											<tr>
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.workgroupName"/></td>
												<td class=formDe><bean:write name="workgroupForm" property="workgroupName" /></td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.description"/></td>
												<td class=formDe><bean:write name="workgroupForm" property="workgroupDescription" /></td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.type"/></td>
												<td class=formDe><bean:write name="workgroupForm" property="workgroupTypeDesc" /></td>
											</tr>
										</table>
									</td>							                 
								</tr>
							</table>
						<!-- END WORKGROUP INFO TABLE -->
						<!-- BEGIN REQUIRED INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/>One of these fields is required to search for Users</td>												
								</tr>
							</table>
						<!-- END REQUIRED INSTRUCTION TABLE -->
						<!-- BEGIN SEARCH USERS SECTION -->                    
							<table width="98%" border="0" cellspacing=0 cellpadding=0 class=borderTableBlue>
								<tr>
									<td>
										<table width=100% cellpadding=2 cellspacing=0>
											<tr class=detailHead>
												<td class=detailHead><bean:message key="prompt.addUsers" /></td>				                        	
												<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>				                          
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table border=0 align=center cellpadding=4 cellspacing=1 width=100%>
											<tr>
		                                  		<td class="formDeLabel" valign=top nowrap><bean:message key="prompt.name" /></td>
		                                  		<td	class=formDe colspan="2">
		                                    		<table border=0 cellspacing=1>
					                                    <tr>
					                                        <td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/><bean:message key="prompt.last" /></td>
					                                        <td class=formDeLabel><bean:message key="prompt.first" /></td>
					                                    </tr>
					                                    <tr>
					                                        <td class=formDe><html:text property="userLastName" maxlength="75" size="50" /></td>
					                                      	<td class=formDe><html:text property="userFirstName" maxlength="50" size="30" /></td>
					                                    </tr>
					                                </table>
                     		 					</td>
                    						</tr>									
											<tr>
												<td class=formDeLabel><bean:message key="prompt.jobTitle" /></td>
												<td class=formDe colspan="2"><html:select property="jobTitleId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="jobTitles" label="description" value="code"/>														
													</html:select></td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.2.diamond"/><bean:message key="prompt.division" /></td>
												<td class=formDe colspan="2"><html:select property="divisionId" size="1" onchange="updateOrganization2(document.forms[0], 'divisionId','programUnitId','programSectionId')">
														<html:option value=""><bean:message key="select.generic" /></html:option>
										 				<html:optionsCollection property="organizations" value="organizationId" label="description" />
													</html:select></td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.programUnit" /></td>
												<td class=formDe colspan="2"><html:select property="programUnitId" size="1" disabled="true" onchange="updateOrganization3(document.forms[0], 'divisionId','programUnitId','programSectionId')">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													</html:select></td>
											</tr>
											<tr>
												<td class=formDeLabel width=1% nowrap><bean:message key="prompt.positionType" /></td>
												<td class=formDe colspan="2"><html:select property="positionTypeId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="positionTypes" label="description" value="code"/>														
													</html:select></td>
											</tr>
											<tr class=hidden> <!-- For organization.js methods to work properly -->
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.programSection" /></td>
												<td class="formDe">
													<html:select size="1" property="programSectionId" disabled="true">
														<html:option value=""><bean:message key="select.generic"/></html:option>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width=1% nowrap></td>
												<td class="formDe">
													<html:submit property="submitAction" onclick="return (validateFields(this.form) && validateWorkgroupForm2(this.form) && disableSubmit(this, this.form));"><bean:message key="button.findUsers"></bean:message></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>
												</td>			
											</tr>
										</table>
									</td>							                 
								</tr>
							</table>											
						<!-- END SEARCH USERS SECTION -->
							<br>	
						<!-- BEGIN SEARCH USERS RESULTS SECTION -->
							<logic:notEmpty name="workgroupForm" property="userResultList">
							<input type="hidden" name="clearSelectedUsersCheckBoxes" value=""/>
							<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td align="center">
										<bean:size id="userResultListSize" name="workgroupForm" property="userResultList" />	
										<b><bean:write name="userResultListSize"/></b>&nbsp;search results found. </td>            					
            					</tr>
            				</table>	 
            				<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td align="center">           				
									<bean:size id="resultSize" name="workgroupForm" property="userResultList" />	
										<script type="text/javascript">
										renderScrollingArea(<bean:write name="resultSize" />);									
										</script>			 
									<table width=100% cellspacing=1 cellpadding=2 border=0>
								        <thead>   											
										<tr class="formDeLabel">		
											<td align=center>
											<logic:lessEqual name="resultSize" value="1000">
												<input type=checkbox id="checkAllUsersId" name=checkAllUsers onclick="toggleCheckAll(this,'selectedUsers')" title="Check/Uncheck All">				 
					 						</logic:lessEqual>												
											</td>
<%-- Know issue with this sort tag - 04/27/2007
	 Results are unsorted on original find but sorts fine once user selects sort arrow.
	 The userResultList is not being sorted in the action 
	  --%>
											<td>
												<bean:message key="prompt.name" />
												<jims:sortResults beanName="workgroupForm" results="userResultList" primaryPropSort="formattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC"  sortId="1"  />
											</td>
											<td><bean:message key="prompt.jobTitle"/></td>
											<td><bean:message key="prompt.division"/></td>
											<td><bean:message key="prompt.programUnit"/></td>
											<td><bean:message key="prompt.positionType"/></td>
										</tr>
										</thead>
										<tbody>
									<%int RecordCounter = 0;
											String bgcolor = "";%>  
										<logic:iterate id="userResultListIndex" name="workgroupForm" property="userResultList">													
										<tr
											class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>
												id="<bean:write name="userResultListIndex" property="userId" />"
													>
											<td align=center>
											<logic:lessEqual name="resultSize" value="1000">
												<html:multibox property="selectedUsers" onclick="parent(this,'checkAllUsersId')">
													<bean:write name="userResultListIndex" property="userId"/> 
												</html:multibox>				 
					 						</logic:lessEqual>	
					 						<logic:greaterEqual name="resultSize" value="1001">
												<html:multibox property="selectedUsers">
													<bean:write name="userResultListIndex" property="userId"/> 
												</html:multibox>															
						 					</logic:greaterEqual> 											       		
											</td>	
											<td><bean:write name="userResultListIndex" property="formattedName" /></td>
											<td><bean:write name="userResultListIndex" property="jobTitleDesc" /></td>
											<td><bean:write name="userResultListIndex" property="divisionDesc" /></td>
											<td><bean:write name="userResultListIndex" property="programUnitDesc" /></td>
											<td><bean:write name="userResultListIndex" property="positionTypeDesc" /></td>
										</tr>										
									</logic:iterate>
									</tbody>
								</table>
							</div>	
							</td>
							</tr>
							</table>						
					<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">									  	
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return checkUserSelect(this.form) && disableSubmit(this, this.form);"><bean:message key="button.addSelectedUsers"></bean:message></html:submit>
									</td>							
								</tr>
							</table>								
					<!-- END BUTTON TABLE -->
							<script>
			  					 reverseToggleCheckAll('checkAllUsersId', 'selectedUsers');
							</script>						
							</logic:notEmpty>
							
					<!-- END SEARCH USERS RESULTS SECTION -->
						
					<!-- BEGIN SELECTED USERS LIST SECTION -->						
							<logic:notEmpty name="workgroupForm" property="userSelectedList">						
							<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td>
										<table width=100% cellpadding=2 cellspacing=0>
											<tr>
												<td class="subhead"><bean:message key="prompt.currentSelectedUsersList" /></td>
											</tr>
										</table>
										<table border=0 width=100% cellspacing=1 cellpadding=2 class=borderTable>
											<tr class="formDeLabel">				
												<td align="center"><img src="/<msp:webapp/>images/spacer.gif" width=50 height=1></td>
												<td class="formDeLabel">
													<bean:message key="prompt.name" />
													<jims:sortResults beanName="workgroupForm" results="userSelectedList" primaryPropSort="formattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC"  sortId="2"  />
												</td>
												<td class="formDeLabel"><bean:message key="prompt.jobTitle"/></td>
												<td class="formDeLabel"><bean:message key="prompt.division"/></td>
												<td class="formDeLabel"><bean:message key="prompt.programUnit"/></td>
												<td class="formDeLabel"><bean:message key="prompt.positionType"/></td>
											</tr>
								
											<%int RecordCounter2 = 0;
											String bgcolor = "";%>
											<logic:iterate id="userSelectedListIndex" name="workgroupForm" property="userSelectedList">
												<tr
													class=<%RecordCounter2++;
													bgcolor = "alternateRow";
													if (RecordCounter2 % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
													<td width=1%>
<%-- 													<a href="/<msp:webapp/>displayWorkgroupSummary.do?submitAction=<bean:message key="button.remove"/>&&userId=<bean:write name="userSelectedListIndex" property="userId"/>">  --%>
														<a href="javascript:removeCheck(this.form,'/<msp:webapp/>displayWorkgroupSummary.do?submitAction=<bean:message key="button.remove"/>&&userId=<bean:write name="userSelectedListIndex" property="userId"/>', '<bean:write name="workgroupForm" property="action" />' )">													
														<bean:message key="button.remove" /></a></td>
													<td>
														<bean:write name="userSelectedListIndex" property="formattedName" />
														<input type="hidden" name="selectedUser" value="">
													</td>
													<%-- td colspan="4"></td --%>
													<td><bean:write name="userSelectedListIndex" property="jobTitleDesc" /></td>
													<td><bean:write name="userSelectedListIndex" property="divisionDesc" /></td>
													<td><bean:write name="userSelectedListIndex" property="programUnitDesc" /></td>
													<td><bean:write name="userSelectedListIndex" property="positionTypeDesc" /></td>				
												</tr>						
											</logic:iterate>											
										</table>																		
									</td>
								</tr>
							</table>
							</logic:notEmpty>					
						<!-- END SELECTED USERS LIST SECTION -->			
							<br>                   
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">		
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return checkSelectedUserList() && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>  																																		 			
									</td>
								</tr>									
							</table>
						<!-- END BUTTON TABLE -->
             		  </td>
				</tr>
            </table><br>
		</td>
	</tr>
</table>
</td>
	</tr>
</table>            	
		
</div>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
