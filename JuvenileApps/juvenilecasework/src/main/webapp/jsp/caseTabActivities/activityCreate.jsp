<!DOCTYPE HTML>
<%-- User selects the "Activities" Tab --%>
<%--MODIFICATIONS --%>
<%-- 11/16/2006	Debbie Williamson	Create JSP --%>
<%-- 07/09/2007 C Shimke			43488 revised script to check activity date format --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>

<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
 <%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />


<%--html:javascript formName="activityCreate"/--%>
<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - activityCreate.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<!-- JQuery function -->
<script type='text/javascript' src="/<msp:webapp/>js/activity.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>

<script type='text/javaScript'>

<logic:iterate indexId="groupIterIndex" id="groupIter" name="activitiesForm" property="activityCodes">
	activityCodes[<bean:write name="groupIterIndex"/>] = new subTypes("<bean:write name="groupIter" property="code" />", "<bean:write name="groupIter" property="description" />");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subTypes">
		var innerGroup = new subTypes("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description" />");
		activityCodes[<bean:write name="groupIterIndex"/>].subTypes[<bean:write name="groupIterIndex2"/>] = innerGroup;

		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subTypes">
			var innerGroup = new subTypes("<bean:write name="groupIter3" property="code" />", "<bean:write name="groupIter3" property="description" />");
			activityCodes[<bean:write name="groupIterIndex"/>].subTypes[<bean:write name="groupIterIndex2"/>].subTypes[<bean:write name="groupIterIndex3"/>] = innerGroup;		
		</logic:iterate>		
	</logic:iterate>	
</logic:iterate>

/* 
 <!-- Replaced with JQuery function --> 
 function validateMandatoryFields(el) 
{
 	var regexp = /^[a-zA-Z0-9_\- ]*$/;
 	var msg = "";
  
 	if (el.activityDateAsStr.value == "") 
  {
		msg += "Activity Date is required.";
		el.activityDateAsStr.focus();
 	}
 	else if (el.selectedCategoryId.value == "") 
  {
		msg += "Activity Category is required.";
		el.selectedCategoryId.focus();					
 	}
 	else if (el.selectedTypeId.value == "") 
  {
		msg += "Activity Type is required.";
		el.selectedTypeId.focus();					
 	}
 	else if (el.selectedDescriptionId.value == "") 
  {
		msg += "Activity Description is required.";
		el.selectedDescriptionId.focus();		
 	}
  
 	if (msg == "")
 	{
    return true;
  }
  
  alert(msg);     
	return false;
}

function validateActivityDate(theForm)
{
	var date1 = document.activitiesForm.activityDateAsStr.value;
	var chkfmt = isDate(date1,'<bean:message key="date.format.mmddyyyy"/>');

	if( chkfmt == false)
	{
    alert("Activity Date is invalid.  Valid format is mm/dd/yyyy.");
		theForm.activityDateAsStr.focus();	    
    return false;		
	}

	var date2 = formatDate( new Date(), '<bean:message key="date.format.mmddyyyy"/>');
  var chk = compareDates( date1,'<bean:message key="date.format.mmddyyyy"/>',date2,'<bean:message key="date.format.mmddyyyy"/>');

	if( chk == 1)
	{
	  alert("Activity Date cannot be future date.");
		theForm.activityDateAsStr.focus();	    
	  return false;
	}

	return true;
}

function textCounter(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to " +maxlen +" characters.");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
} */

function setSelectedGroups(){
	if (document.forms[0].selectedGrp2Id.disabled == false){
		updateType(document.forms[0]);
		for (x = 0; x<document.forms[0].selectedTypeId.length; x++){
			
			if (document.forms[0].selectedTypeId.options[x].value == document.forms[0].selectedGrp2Id.value){
				document.forms[0].selectedTypeId.selectedIndex = x;
				// 	'T35' is also added in the line below for BUG 49920
				//alert(document.forms[0].vendorActivity.value)
				if(document.forms[0].vendorActivity.value=="Y")
					/* if(document.forms[0].selectedTypeId.value == "BAQ" || 
						document.forms[0].selectedTypeId.value == "BVB" || 
						document.forms[0].selectedTypeId.value == "WCH" || 
						document.forms[0].selectedTypeId.value == "T35" ||
						document.forms[0].selectedTypeId.value == "MBK" ||
						document.forms[0].selectedTypeId.value == "HRJ") commenting as no need to hard code everytime a SP is added, they have to make it BA in SM*/
				{
				document.forms[0].selectedTypeId.disabled=true;}
				break;
			}
		}
		updateActivity(document.forms[0]);
		if (document.forms[0].selectedGrp3Id.disabled == false){
			for (x = 0; x<document.forms[0].selectedDescriptionId.length; x++){
				if (document.forms[0].selectedDescriptionId.options[x].value == document.forms[0].selectedGrp3Id.value){
					document.forms[0].selectedDescriptionId.selectedIndex = x;
					break;
				}
			}
		}			
	}	
	
}

</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="setSelectedGroups()">
<html:form action="/displayActivitySummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|132">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/><br>
      <bean:message key="title.create"/> <bean:message key="title.casefile"/> <bean:message key="prompt.activity"/> 
  	</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Enter information and select Submit button to view Summary page.</li>
        <li>Enter date on clicking the date field.</li>
      </ul>
    </td>
  </tr>
   <tr>
    <td class="required"><bean:message key="prompt.diamond" /> &nbsp; Required Fields&nbsp;&nbsp;*All date fields must be entered via calendar.</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>

      <%--tabs start--%>
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="casefiledetailstab"/>
        <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>				
      <%--tabs end--%>

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 			<tr>
 			  <td valign='top' align='center'>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign='top'>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="activitiestab"/>
 												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
								  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
								  </tr>
						  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'>
										<div class='spacer'></div>
            					<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            						<tr>
            							<td class='detailHead'><bean:message key="prompt.activityDetail"/></td>
            						</tr>
            						<tr><input type="hidden" name="vendorActivity" value=<bean:write name="activitiesForm" property="vendorActivity"/>>
            							<td valign='top' align='center'>
            								<table width='100%' border="0" cellpadding="4" cellspacing="1" >
            									<tr>								
                             						<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityDate"/></td>
            
			                    					<td class='formDe' colspan='3'> 
			                    						<html:text name="activitiesForm" property="activityDateAsStr"  styleId="activityDate" size="10" maxlength="10" readonly="true"/>						    		      
			                    					</td>
                            					</tr>
                            					<tr>
                            						<td class='formDeLabel' nowrap='nowrap' width='1%'> Activity Time</td>
                            						<td class="formDe" colspan='3'>
														<html:select styleId="activityTime" property="activityTimeStr">
															<html:option key="select.generic" value="" />
															<html:optionsCollection name="activitiesForm" property="workDays" value="description" label="description" />
														</html:select>
													</td>
                            					</tr>
			                           			 <tr >
			                    					<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.category"/></td>
			                    				<td class='formDe'>     
			                    			     <logic:equal name="activitiesForm" property="vendorActivity" value="Y">
			                    					<html:select property="selectedCategoryId" size="1" onchange="updateType(this.form)" disabled="true" styleId="selectedCategoryId">
			                    							<html:optionsCollection property="activityCodes" value="code" label="description" /> 
			                    						</html:select>
			                    					</logic:equal>
			                    					<logic:notEqual name="activitiesForm" property="vendorActivity" value="Y">
			                    						<html:select property="selectedCategoryId" size="1" onchange="updateType(this.form)" styleId="selectedCategoryId">
			                    							<html:option value=""><bean:message key="select.generic" /></html:option>
			                    							<html:optionsCollection property="activityCodes" value="code" label="description" /> 
			                    						</html:select>
			                    					</logic:notEqual>
			                    					</td>
			                    				</tr>
			                    				<tr>
			                    				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.type"/></td>
			                    					<td class='formDe'>
			                    					<logic:equal name="activitiesForm" property="vendorActivity" value="Y">
			                    						<html:select property="selectedTypeId" size="1" onchange="updateActivity(this.form)" disabled="true" styleId="selectedTypeId">
			                    						</html:select>
			                    						<input type="hidden" name="selectedGrp2Id" value=<bean:write name="activitiesForm" property="selectedTypeId"/>>
			                    					</logic:equal>
			                    					<logic:notEqual name="activitiesForm" property="vendorActivity" value="Y">
			                    						<html:select property="selectedTypeId" onchange="updateActivity(this.form)" styleId="selectedTypeId">
			                    						</html:select>
			                    						<input type="hidden" name="selectedGrp2Id" value=<bean:write name="activitiesForm" property="selectedTypeId"/>>
			                    					</logic:notEqual>
			                    					</td>
			                    				</tr>
			                                    <tr>
			                    					<td class='formDeLabel'  nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activity"/></td>
			                    					<td class='formDe' colspan='3' >
			                    
			                    						<html:select property="selectedDescriptionId" styleId="selectedDescriptionId">
			                    							<html:option value=""><bean:message key="select.generic" /></html:option>
			                    							
			                    						</html:select>
			                    						<!-- hidden value for setting selectedIndex after submit -->
																<input type="hidden" name="selectedGrp3Id" value=<bean:write name="activitiesForm" property="selectedDescriptionId" /> >
																<%--input type="hidden" name="group3" value="" --%>
			                    					</td>
			                    				</tr>                    								
			                    				<tr>
			                    					<td colspan='4' class='formDeLabel'><bean:message key="prompt.activityComments"/>&nbsp;
			                      					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                      						<tiles:put name="tTextField" value="comments"/>
			                      						<tiles:put name="tSpellCount" value="spellBtn1" />
			                    					</tiles:insert>
			                    						(Max. characters allowed: 32000)
			                    					</td>
			                    				</tr>
			                    				<tr>
			                    					<%-- <td colspan='4' class='formDe'>
			                               				 <html:textarea name="activitiesForm" property="comments" styleId="comments" rows="3" cols="40" style="width:100%"></html:textarea>
			                    					</td> --%>
			                    					<td colspan='4' class='formDe'>
			                    					<html:textarea name="activitiesForm" property="comments" onkeyup="textCounter(this.form.comments,32000);" 
			                    					onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"
			                    					ondblclick="myReverseTinyMCEFix(this)"
														styleClass="mceEditor" style="width:100%" rows="15" >
													</html:textarea>
													</td>
			                    				</tr>
			                    				<bean:define id="typeId" name="activitiesForm" property="selectedTypeId"/>
			                    				<bean:define id="act" name="activitiesForm" property="selectedDescriptionId"/>
			                    				<input type="hidden" name="activityType" value="<%=typeId%>" />
			                    				<input type="hidden" name="activity" value="<%=act%>" />
			                    				<input type="hidden" value="" id="activityPage"/> <%-- added for bug fix 39771 --%>
                    					</table>
                    			
                    		<div class='spacer'></div>
                    		<html:submit property="submitAction" styleId="addActivity"><bean:message key="button.addActivity" /></html:submit>
							<div class='spacer'></div>	
							
							<tr id="activityList1">
									<td valign='top' colspan='4'>
										<table width='100%' cellpadding='4' cellspacing='1' bgcolor='#999999'>
											
													
											<logic:notEmpty name="activitiesForm" property="newActivities">
												<tr bgcolor='#cccccc'>
													<td></td>
													<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.entryDate"/></td>
													<td class="subhead" valign='top' nowrap='nowrap'>Entry Time</td>
													<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.category"/></td>
													<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.type"/></td>
													<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
												</tr>

												<logic:iterate indexId="index" id="activityIndex" name="activitiesForm" property="newActivities">
												<%-- Begin Pagination item wrap --%>
													
														<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
															<td><a href="/<msp:webapp/>displayUpdateActivity.do?submitAction=Remove&activityId=<%=(index.intValue())%>">Remove&nbsp;</a></td>
															<td><a href="/<msp:webapp/>displayActivitySummary.do?submitAction=<bean:message key='button.view'/>&activityId=<%=(index.intValue())%>">
																<bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /></a>
															</td>
															<td valign='top'><bean:write name="activityIndex" property="activityTime" /></td>
															<td valign='top'><bean:write name="activityIndex" property="categoryDesc" /></td>
															<td valign='top'><bean:write name="activityIndex" property="typeDesc" /></td>
															<td valign='top'><bean:write name="activityIndex" property="activityDesc" /></td>
														</tr>
													
													
												<%-- End Pagination item wrap --%>
												</logic:iterate>
											</logic:notEmpty>   
                      
											<bean:define id="typeId" name="activitiesForm" property="selectedTypeId"/>
											<bean:define id="act" name="activitiesForm" property="selectedDescriptionId"/>
											<input type="hidden" name="activityType" value="<%=typeId%>" />
											<input type="hidden" name="activity" value="<%=act%>" />                              
										</table>
       							<%-- Begin Pagination navigation Row--%>
										<table align="center">
											<tr>
												<td>
												<logic:empty name="activitiesForm" property="newActivities">
													<pg:index>
														<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
															<tiles:put name="pagerUniqueName" value="pagerSearch"/>
															<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
														</tiles:insert>
													</pg:index>
												</logic:empty>
												</td>
											</tr>
										</table>
       							<%-- End Pagination navigation Row--%>
									</td>
								</tr>
							</table>	
                    		</td>
                    	 </tr>           	 
                    </table>
                   
        					<div class='spacer'></div>
            			<%-- BEGIN BUTTON TABLE --%>
            			<!--
            			<table width="100%">
            			  <tr>
            				<td align="center">
            					<html:submit property="submitAction" onclick="return validateMandatoryFields(this.form) && validateActivityDate(this.form)"><bean:message key="button.submit" /></html:submit>
            					<html:reset property="submitAction"><bean:message key="button.reset" /></html:reset>
            					<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
            				</td>
            			  </tr>
            			</table> -->
            			
            			
		        <table border="0" cellpadding=1 cellspacing=1 align=center>
		          <tr>
		            <td align="center">
     							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
     							<html:submit property="submitAction" onclick="return (disableSubmit(this, this.form));" styleId="finish"><bean:message key="button.finish"/></html:submit>
     							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      					</td>
		          </tr>
		        </table> 
						<div class=spacer></div>
					</td>
			  </tr>
			</table>
            			<%-- END BUTTON TABLE --%>    
                </td>
            </tr>
            </table>
            <div class='spacer'></div>
           </td>
            </tr>
            </table>

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>
</pg:pager>
<div class='spacer'></div>
<script  type='text/javascript'>updateType(document.forms[0]);</script>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

