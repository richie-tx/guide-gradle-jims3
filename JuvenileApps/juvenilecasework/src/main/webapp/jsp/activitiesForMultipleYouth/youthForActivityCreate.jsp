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


<title><bean:message key="title.heading"/> - youthForActivityCreate.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/activityForYouth.js"></script>
<script>
	$(document).ready(function(){
		$("#juvenileId").val("");
		$("#comments").val("");
		$("#addBtn").click(function(event){
			if ( $("#juvenileId").val() == "") {
				alert("Juvenile number is required.");
				event.preventDefault(); 
				return false;
			} else if( isNaN( $("#juvenileId").val(), false)) {
				alert("Juvenile number must be numeric value.");
				event.preventDefault(); 
				return false;
			}
			else {
				spinner();
				return true;
			}
		})
		
		$("#finishBtn").click(function(){
			spinner();
		})
	})
</script>
</head>
<body>
<html:form action="/handleActivitiesForYouthCreate" target="content">
	<table width='100%'>
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - 
	     	Youth For Activity
	  	</td>
	  </tr>
	</table>
	<table width='100%'>
		  <td>
				<font style="font-weight: bold;" color="#FF0000" size="2" face="Arial"> 
					<logic:notEqual name="processActivitiesForMultipleYouthForm" property="message" value="">
						<bean:write name="processActivitiesForMultipleYouthForm" property="message" />
					</logic:notEqual> 
				</font>
		</td>
	</table>
	<table  align="center" width="99%" border="0" cellpadding="2" cellspacing="1"  class="borderTableBlue">
		<tr>
			<td class='detailHead'>Juvenile</td>
		</tr>
		<tr>
			<td>
				<table width='100%' border="0" cellpadding="4" cellspacing="1" >
            		<tr>								
						<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/>Juvenile Number</td>
            			<td class='formDe' colspan='3'> 
							<html:text name="processActivitiesForMultipleYouthForm" property="juvenileId"  styleId="juvenileId" size="8" maxlength="8"/>						    		      
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
			             <td colspan='4' class='formDe'>       					
			              	<html:textarea styleId="comments" name="processActivitiesForMultipleYouthForm" property="comments" 
			              							onkeyup="textCounter(this.form.comments,32000);" 
			                    					onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"
			                    					ondblclick="myReverseTinyMCEFix(this)"
													styleClass="mceEditor" style="width:100%" rows="15" >
							</html:textarea>                 				 
			                    					
						</td>
			      	</tr>
					<tr align="center">
						<td align="center">
							<html:submit styleId="addBtn" property="submitAction"><bean:message key="button.addJuvenile"/></html:submit>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width='100%' cellpadding='4' cellspacing='2' bgcolor='#999999'>
					<tr bgcolor='#cccccc'>
						<td class="subhead" valign='top' nowrap='nowrap'>Activity Date</td>
						<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.category"/></td>
						<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.type"/></td>
						<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
					</tr>
					<tr class="normalRow">
						<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="activityDate" /></td>
						<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedCategoryIdDesc" /></td>
						<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedTypeIdDesc" /></td>
						<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedActivityIdDesc" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<logic:notEmpty name ="processActivitiesForMultipleYouthForm" property="juveniles">
			<tr>
				<td>
					<table width='100%' cellpadding='4' cellspacing='2' bgcolor='#999999'>
						<tr bgcolor='#cccccc'>
							<td></td>
							<td class="subhead" valign='top' nowrap='nowrap'>Juvenile Number</td>
							<td class="subhead" valign='top' nowrap='nowrap'>Juvenile Name</td>
							<td class="subhead" valign='top' nowrap='nowrap'>Casefile Number</td>
							<td class="subhead" valign='top' nowrap='nowrap'>Activity Comments</td>
						</tr>
						
						<logic:iterate indexId="index" id="juvenile" name ="processActivitiesForMultipleYouthForm" property="juveniles">
							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
								<td><a href="/<msp:webapp/>handleActivitiesForYouthCreate.do?submitAction=Remove Juvenile&juvenileIndex=<%=(index.intValue())%>">Remove</a></td>
								<td valign='top'><bean:write name="juvenile" property="juvenileNum" /></td>
								<td valign='top'><bean:write name="juvenile" property="formattedName" /></td>
								<td valign='top'><bean:write name="juvenile" property="latestCasefileId" /></td>
								<td valign='top'><bean:write name="juvenile" property="comments" /></td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</logic:notEmpty>
		<tr>
			<td align="center">
				<html:submit styleId="finishBtn" property="submitAction"><bean:message key="button.finish"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
