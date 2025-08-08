<!DOCTYPE HTML>
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


<title><bean:message key="title.heading"/> - activitiesForYouthCreate.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/activityForYouth.js"></script>
<script>
	$(document).ready(function(){
		setType();
		setActivity();
		reset();
		
		$("#nextBtn").click(function(event){
			validateInputs(event);
		})
	})
	
	function validateInputs(event){
		
		if ( $("#activityDate").val() == "" ){
			alert("Activity Date is required.");
			$("#activityDate").focus();
			event.preventDefault(); 
			return false;
		}
		
		if ( $("#selectedCategoryId").val() == "" ){
			alert("Category is required.");
			$("#selectedCategoryId").focus();
			event.preventDefault(); 
			return false;
		}
		
		if ( $("#selectedTypeId").val() == "" ){
			alert("Type is required.");
			$("#selectedTypeId").focus();
			event.preventDefault(); 
			return false;
		}
		
		if ( $("#selectedActivityId").val() == "" ){
			alert("Activity is required.");
			$("#selectedActivityId").focus();
			event.preventDefault(); 
			return false;
		}
		
		$("#selectedCategoryIdDesc").val( $("#selectedCategoryId option:selected").text() );
		$("#selectedTypeIdDesc").val( $("#selectedTypeId option:selected").text() );
		$("#selectedActivityIdDesc").val( $("#selectedActivityId option:selected").text() );
		spinner();
		return true;
	}
	
	function setType(){
		$("#selectedCategoryId").change(function(){
			$("#selectedTypeId").empty();
			$("#selectedActivityId").empty();
			$("#selectedTypeId").append("<option value=''>Please Select</option>");
			$("#selectedActivityId").append("<option value=''>Please Select</option>");
			<logic:iterate indexId="activityCodeIndex" id="activityCode" name="processActivitiesForMultipleYouthForm" property="activityCodes">
				if ( $("#selectedCategoryId").val() == '<bean:write name="activityCode" property="code"/>'){
					<logic:iterate indexId="subTypeIndex" id="subType" name="activityCode" property="subTypes">
						$("#selectedTypeId").append("<option value='<bean:write name="subType" property="code"/>'><bean:write name="subType" property="description"/></option>");
					</logic:iterate>
				}
			</logic:iterate>
			
		})
	}
	
	function setActivity(){
		$("#selectedTypeId").change(function(){
			$("#selectedActivityId").empty();
			$("#selectedActivityId").append("<option value=''>Please Select</option>");
			<logic:iterate indexId="activityCodeIndex" id="activityCode" name="processActivitiesForMultipleYouthForm" property="activityCodes">
			if ( $("#selectedCategoryId").val() == '<bean:write name="activityCode" property="code"/>'){
				<logic:iterate indexId="subTypeIndex" id="subType" name="activityCode" property="subTypes">
					if ( $("#selectedTypeId").val() == '<bean:write name="subType" property="code"/>' ){
						<logic:iterate indexId="activitySubTypeIndex" id="activitySubType" name="subType" property="subTypes">
							$("#selectedActivityId").append("<option value='<bean:write name="activitySubType" property="code"/>'><bean:write name="activitySubType" property="description"/></option>");
						</logic:iterate>
					}
				</logic:iterate>
			}
		</logic:iterate>
		})
	}
	
	function reset(){
		$("#selectedCategoryId").val("");
		$("#selectedTypeId").val("");
		$("#selectedActivityId").val("");
		$("#selectedCategoryIdDesc").val("");
		$("#selectedTypeIdDesc").val("");
		$("#selectedActivityIdDesc").val("");
	}
</script>
</head>
<body>
<html:form action="/handleActivitiesForYouthCreate" target="content">
	<table width='100%'>
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> -
	     	Activity For Multiple Youth
	  	</td>
	  </tr>
	</table>
<%-- END HEADING TABLE --%>
	<table  align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class="borderTableBlue">
		<tr>
			<td class='detailHead'><bean:message key="prompt.activityDetail"/></td>
		</tr>
		<tr>
			<td>
				<table width='100%' border="0" cellpadding="4" cellspacing="1" >
            		<tr>								
						<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityDate"/></td>
            			<td class='formDe' colspan='3'> 
							<html:text name="processActivitiesForMultipleYouthForm" property="activityDate"  styleId="activityDate" size="10" maxlength="10" readonly="true"/>						    		      
			          	</td>
					</tr>
					 <tr >
						<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.category"/></td>
						<td class='formDe'>  
							<html:select name="processActivitiesForMultipleYouthForm" property="selectedCategoryId" size="1" styleId="selectedCategoryId">
								<html:option key="select.generic" value="" />
			                 	<html:optionsCollection name="processActivitiesForMultipleYouthForm" property="activityCodes" value="code" label="description" /> 
							</html:select>
							<html:hidden styleId="selectedCategoryIdDesc"  name="processActivitiesForMultipleYouthForm" property="selectedCategoryIdDesc" />   
						</td>
					</tr>
					<tr>
			       		<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.type"/></td>
						<td class='formDe'>
							<html:select name="processActivitiesForMultipleYouthForm" property="selectedTypeId" size="1" styleId="selectedTypeId">
								<html:option key="select.generic" value="" />
							</html:select>
							<html:hidden styleId="selectedTypeIdDesc"  name="processActivitiesForMultipleYouthForm" property="selectedTypeIdDesc" />   	  
						</td>
					</tr>
					<tr>
						<td class='formDeLabel'  nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activity"/></td>
						<td class='formDe'>
							<html:select name="processActivitiesForMultipleYouthForm" property="selectedActivityId" size="1" styleId="selectedActivityId">
								<html:option key="select.generic" value="" />
							</html:select>
							<html:hidden styleId="selectedActivityIdDesc"  name="processActivitiesForMultipleYouthForm" property="selectedActivityIdDesc" />     
						</td>
					
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:submit styleId="nextBtn" property="submitAction"><bean:message key="button.next"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>