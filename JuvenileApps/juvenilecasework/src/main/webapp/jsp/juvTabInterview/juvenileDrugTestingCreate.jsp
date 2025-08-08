<!DOCTYPE HTML>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileDrugTestingCreate.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileDrugForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/drugs.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Drug/Substance Testing</td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>

<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<script language=javascript>

	$(document).ready(function(){
		loadData();
		removeData();
		//console.log("Test administered : " + $("#testAdministered").val()  );
		
		if( "YES" != $("#testAdministered").val() ){
			$("#drugTestResult").prop("disabled", true);
			$("#substancesTested").prop("disabled", true);
		} else {
			$("#drugTestResult").prop("disabled", false);
			$("#substancesTested").prop("disabled", false);
		}
		
		$("#addDrugTesting").click(function(){
			console.log( $("#substancesTested").val() == null );
			if ( fieldsValidation() ) {
				saveData();
				//removeData();
				spinner();
			}  else {
				return false;
			}
		})
		
		$("#validateBtn").click(function(e){
			e.preventDefault();
			spinner();
			
		   	$.ajax({
		            url: '/JuvenileCasework/handleJuvenileDrugTestingCreate.do?submitAction=Validate',
		            type: 'post',
		            data: $('form#juvenileDrugTestingForm').serialize(),
		            success: function(data, textStatus , xhr) {
		                 if (200 == xhr.status){
		                     $(".overlay").remove();
		                     if (isJson(data)) {
		                    	 if ( (JSON.parse(data)).error != ''){
		   							alert((JSON.parse(data)).error);
		   						 }
		                    	 if ( (JSON.parse(data)).success != '' ) {
		   							alert((JSON.parse(data)).success); 
		   						 }
		                        
		                     } 
		                 }
		            }
			});
		})
		
		function isJson(str) {
	   		try {
	        	json = JSON.parse(str);
	    	} catch (e) {
	        	return false;
	    	}
    		return true;
		}
		
		$("#testAdministered").change(function(){
			if( "YES" != $("#testAdministered").val() ){
				$("#drugTestResult").prop("disabled", true);
				$("#substancesTested").prop("disabled", true);
			} else {
				$("#drugTestResult").val("");
				$("#substancesTested").val(null);
				$("#drugTestResult").prop("disabled", false);
				$("#substancesTested").prop("disabled", false);
			
			}
		})
	})
	
	

	function fieldsValidation(){
		if ( $("#associatedCasefile").val() == "" ) {
			alert("Associated Casefile is required.");
			$("#associatedCasefile").focus();
			return false;
		}
		
		if ( $("#testAdministered").val() == "" ){
			alert("Administered is required.");
			$("#testAdministered").focus();
			return false;
		}
		

		if ( $("#drugTestDate").val() == "" ){
			alert("Test Date is required.");
			$("#drugTestDate").focus();
			return false;
		}
		

		if ( $("#drugTestTime").val() == "" ){
			alert("Test Time is required.");
			 $("#drugTestTime").focus();
			return false;
		}
		
		if ( $("#testAdministered").val() == "YES" ) {
			if (  $("#substancesTested").val() == ""
					||  $("#substancesTested").val() == null ) {
				alert("Substance Tested is required.");
				$("#substancesTested").focus();
				return false;
			}
			
			if (  $("#drugTestResult").val() == "") {
				alert("Drug Test Results are required.");
				$("#drugTestResult").focus();
				return false;
			}
		}
		
		if (  $("#testLocation").val() == "") {
			alert("Test Location is required.");
			$("#testLocation").focus();
			return false;
		}
		
		if (  $("#administeredBy").val() == "") {
			alert("Administered By is required.");
			$("#administeredBy").focus();
			return false;
		}
		
		if (  $("#comments").val() == "") {
			alert("Comments are required.");
			$("#comments").focus();
			return false;
		}
		
		validateDrugTestDate();	
		
		return true;
	}

</script>
<br>

<%-- BEGIN DETAIL TABLE --%>  
<% int RecordCounter = 0; 
									String bgcolor = "";%>	
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
			  	</tr>
			</table>
			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
				<tr>
					<td valign=top align=center>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
							<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			  	</tr>
			  </table>
		<html:form styleId="juvenileDrugTestingForm" action="/handleJuvenileDrugTestingCreate" target="content">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|174">
			
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
				<tr>
					<td valign=top align=center>																							
			<%-- BEGIN SUBSTANCE USE INFO TABLE --%>					
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td colspan=6 class=detailHead>Drug Testing</td>
							</tr>
							<tr>
								<td class=formDeLabel>Associated Casefile #</td>	
								<td class=formDe>
									<html:select styleId="associatedCasefile" name="juvenileDrugForm" property="associateCasefile">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="juvenileCasfileResps" value="supervisionNum" label="supervisionNumWithSupervisionType"/>
										
									</html:select>
								</td>
								<td class=formDeLabel>Administered ? </td>
								<td class=formDe>
									<html:select styleId="testAdministered"  name="juvenileDrugForm" property="testAdministered">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="drugTestAdmins" value="code" label="description"/>	
									</html:select>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel>Test Date</td>	
								<td class=formDe>
									<html:text styleId="drugTestDate" name="juvenileDrugForm" property="testDate"/>
								</td>
								<td class=formDeLabel>Test Time</td>
								<td class=formDe>
									<html:select styleId="drugTestTime"  name="juvenileDrugForm" property="testTime">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="workDays" value="description" label="description"/>	
									</html:select>
								</td>
								
							</tr>
							<tr>
								<td class=formDeLabel>Test Location Unit</td>	
								<td class=formDe>
									<html:select styleId="testLocation"  name="juvenileDrugForm" property="testLocation">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="locationCodes" value="juvLocationUnitId" label="locationUnitName"/>	
									</html:select>
								</td>
								<td class=formDeLabel>Administered By</td>
								<td class=formDe>
									<html:text styleId="administeredBy" name="juvenileDrugForm" property="administeredBy"/>
									<input id="validateBtn" type="button" value="Validate"/>
									<input id="searchBtn" type="button" value="Search" />
									
								</td>
								
							</tr>
							<tr>
								<td class=formDeLabel>Substance Tested</td>	
								<td class=formDe>
									<html:select  styleId="substancesTested" name="juvenileDrugForm" property="substancesTested" multiple = "true">
											<html:option key="select.generic" value="" disabled="true"  />
											<html:optionsCollection property="drugTypeCodes" value="code" label="description"/>	
									</html:select>
								</td>
								<td class=formDeLabel>Drug Test Results</td>
								<td class=formDe>
									<html:select styleId="drugTestResult" name="juvenileDrugForm" property="drugTestResult">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="drugTestResults" value="code" label="description"/>	
									</html:select>
								</td>
								
							</tr>
							<tr>
								<td class="formDeLabel" colspan="6">
									Comments
								</td>
							</tr>
							<tr>
								<td class="formDe" colspan="6">
									<html:textarea styleId="comments" property="comments" style="width:100%" rows="3" onmouseout="textLimit($('#comments'), 255 )" onkeyup="textLimit($('#comments'), 255 )"/>
								</td>
							</tr>
							
						</table>			    		 
			<%-- END SUBSTANCE USE INFO TABLE --%>
			<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div>		
						<table width='98%'>	
						  <tr>
							<td align="center">
								<input type="button" value="Back" onclick="history.back();"/>
								  <html:submit property="submitAction" styleId="addDrugTesting">				
									<bean:message key="button.add"></bean:message>
								  </html:submit>			
								<html:submit property="submitAction">
									<bean:message key="button.cancel"></bean:message>
								</html:submit>
							</td>
						</tr>
					</table>
					<div class=spacer></div>
					<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<br>		
   		</td>
  	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<br>
<%-- END FORM --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

