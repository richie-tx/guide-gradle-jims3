<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<bean:size id="profileSize" name= "officerForm" property = "officerProfiles"></bean:size>

<head>
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	
	
	#searchFound {
		margin-bottom: 5px;
		font-weight: bold;
		
	}
	
	#command {
		margin-top: 20px;
	}
	#selectBtn, #backBtn {
		width: 70px;
	}
	
	#leftArrow{ display: none;}
	
	#command div { display: inline-block;}
</style> 
<%@include file="../jQuery.fw"%>
<script type="text/javascript">
	$(document).ready(function(){
		
		if ( "DT" == '<bean:write name="officerForm" property="requestOrigin" />' ) {
			$("th").removeClass("referralDetailHead");
			$("th").addClass("detailHead");
		}
		
		if (!Math.trunc) {
		    Math.trunc = function (v) {
		        return v < 0 ? Math.ceil(v) : Math.floor(v);
	    	};
		}
		
		if("MA" === '<bean:write name="officerForm" property="requestOrigin"/>'){
			$("#selectOfficerCode").attr("action","/JuvenileCasework/displayManageAssignment.do");
		} else if ( "OA" === '<bean:write name="officerForm" property="requestOrigin"/>'){
			$("#selectOfficerCode").attr("action","/JuvenileCasework/submitOverrideAssignment.do");
		} else if ( "CR" === '<bean:write name="officerForm" property="requestOrigin"/>') {
			$("#selectOfficerCode").attr("action","/JuvenileCasework/submitCreateReferral.do")
		} 
		
		$("#selectOfficerCode").submit(function(e){
			if ($('input:radio[name="officerCode"]:checked').length == 0 ){
				e.preventDefault();
				alert("Please select an officer code.")
			} else {
				$("#jpo").val( $('input:radio[name="officerCode"]:checked').val() );
			}
					
		});

		$("#backBtn").click(function(){
			$("#searchOfficerProfiles").attr("action",'/JuvenileCasework/searchOfficerProfile.do?clr=Y&requestOrigin=<bean:write name="officerForm" property="requestOrigin"/>');
			$("#searchOfficerProfiles").submit();
		});
		
		if ( (<bean:write name="profileSize" /> % 10) == 0  ){
			var numPages = <bean:write name="profileSize" /> / 10;
		} else {
			var numPages = Math.trunc( (<bean:write name="profileSize" /> / 10) ) + 1;
		}
		
		
		if ( numPages > 1 ) {
			$("#navTbl").append('<tr valign="bottom">');
			$("#navTbl").append('<td valign="bottom"><img id="leftArrow" SRC="/<msp:webapp/>images/blue_previous.gif" alt="Previous" border=0></td>');
			$("#navTbl").append('<td valign="middle"><a id="navPage" href="#">Page</a><input id="currentPage" type="text" size="1" maxLength="3"/> of <span id="numPages"></span></td>');
			$("#navTbl").append('<td valign="bottom"><img id="rightArrow" SRC="/<msp:webapp/>images/blue_next.gif" alt="Next" border=0 ></td>');
			$("#navTbl").append('</tr>');
			
			$("#currentPage").val(1);
			var startPage = $("#currentPage").val() -1;
			displayofficeProfile(startPage);
			$("#numPages").html(numPages);
			
		} else {
			var startPage = 0;
			displayofficeProfile(startPage);
		}
		
			
		$("#navPage").bind('click', function(){
			var currentPage = parseInt($("#currentPage").val());
			if ( currentPage <= 1 ) {
				$("#leftArrow").css("display","none");
				$("#rightArrow").css("display","inline-block");
			}
			
			if( currentPage > 1 
				&& currentPage < numPages  ) {
				$("#leftArrow").css("display","inline-block");
				$("#rightArrow").css("display","inline-block");
			}
			
			if( currentPage >= numPages ) {
				$("#leftArrow").css("display","inline-block");
				$("#rightArrow").css("display","none");
			}
			
			if ( currentPage >=1
				&& currentPage <= numPages ){
				startPage =  ( currentPage -1 ) + ( currentPage -1)*9;
				displayofficeProfile( startPage );
			} else {
				alert ("Page value is out of range- Please enter a valid page between: 1 and " + numPages );
			}
		})
		
		$("#leftArrow").click(function(){
			if ( parseInt($("#currentPage").val()) > 0
				&& parseInt($("#currentPage").val()) <= numPages ){
				
				startPage = startPage - 10;
				if (startPage < 0 ){
					startPage = 0;
				}
				var currentPage = parseInt($("#currentPage").val()) -1 ;
				if ( currentPage <=0) {
					$("#currentPage").val(1);
				} else {
					$("#currentPage").val(currentPage)
				}
				
				if( currentPage <= 1 ) {
					$("#leftArrow").css("display","none");
					$("#rightArrow").css("display","inline-block");
				}
				
				if( currentPage > 1 ) {
					$("#leftArrow").css("display","inline-block");
					$("#rightArrow").css("display","inline-block");
				}
				displayofficeProfile(startPage);
			} else {
				alert ("Page value is out of range- Please enter a valid page between: 1 and " + numPages );
			}
			
		});
		
		$("#rightArrow").click(function(){
			if ( parseInt($("#currentPage").val()) > 0
				&& parseInt($("#currentPage").val()) <= numPages  ){
				
				startPage = startPage + 10;
				if (startPage >= <bean:write name="profileSize" /> ) {
					startPage = startPage - 10;
				}
				var currentPage = parseInt($("#currentPage").val()) + 1  ;
				console.log("Current page: " + currentPage );
				if ( currentPage > numPages ) {
					$("#currentPage").val( numPages);
				} else {
					$("#currentPage").val(currentPage);
				}
				
				if( currentPage > 1 ) {
					$("#leftArrow").css("display","inline-block");
					$("#rightArrow").css("display","inline-block");
				}
				
				if( currentPage >= numPages  ) {
					$("#leftArrow").css("display","inline-block");
					$("#rightArrow").css("display","none");
				}
	
				displayofficeProfile(startPage);
				
			} else {
				alert ("Page value is out of range- Please enter a valid page between: 1 and " + numPages );
			}
		});
		
		function displayofficeProfile(startPage){
			$("#officerProfile tbody").empty();
			var startPage= startPage;
			var endPage = startPage + 10;
			<logic:iterate id="officerProfile" name="officerForm" property="officerProfiles"  indexId="index">
				if ( <%= index %> >= startPage
				&& <%= index %> < endPage
				){
					$("#officerProfile tbody").append("<tr>");
					$("#officerProfile tbody").append('<td class="formDe"><input type="radio"name="officerCode" value="<bean:write name="officerProfile" property="userId" />"></td>');
					$("#officerProfile tbody").append('<td class="formDe"><bean:write name="officerProfile" property="userId" /></td>');
					$("#officerProfile tbody").append('<td class="formDe"><bean:write name="officerProfile" property="firstName" /></td>');
					$("#officerProfile tbody").append('<td class="formDe"><bean:write name="officerProfile" property="middleName" /></td>	');
					$("#officerProfile tbody").append('<td class="formDe"><bean:write name="officerProfile" property="lastName" /></td>');
					$("#officerProfile tbody").append('<td class="formDe"><bean:write name="officerProfile" property="departmentId" /></td>');
					$("#officerProfile tbody").append("</tr>");
				}
			</logic:iterate>
		}
			
	})
</script>
</head>
<body>
<h2 style="text-align: center;"><logic:notEqual name="officerForm" property="requestOrigin" value="DT">Process Referral</logic:notEqual><logic:equal name="officerForm" property="requestOrigin" value="DT">Drug/Substance Abuse Testing</logic:equal> - Search Officer Profile Results</h2>
<div id="instruction">
	<div>
		<ul>
			<li>Select an officer code you want then click <Strong>Select</Strong> button</li>
			<li>Click <Strong>Go Back</Strong> button if you want a new search </li>
		</ul>
	</div>
</div>
<html:form styleId="searchOfficerProfiles" method="post" action="/searchOfficerProfile?clr=Y">
<div><logic:empty name="officerForm" property="officerProfiles">No officer profiles found</logic:empty></div>
<logic:notEmpty	name="officerForm" property="officerProfiles">
	<div id="searchFound"><bean:write name="profileSize" /> officer profiles found</div>
	<div>
		<table align="center" width="98%" class='borderTableBlue'>
			<tr>
				<td colspan="6">
					<table id="officerProfile" align="center" width="100%" border="0">
						<thead>
							<tr>
								<th class='referralDetailHead'></th>
								<th class='referralDetailHead'>Officer Code</th>
								<th class='referralDetailHead'>First Name</th>
								<th class='referralDetailHead'>Middle Name</th>
								<th class='referralDetailHead'>Last Name</th>
								<th class='referralDetailHead'>Agency Code</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<table id="navTbl"  align="center" 
							width=10% cellpadding=2
							cellspacing=0 border=0 
							bgcolor=e3e9f8>
					</table>
				</td>
			</tr>
		</table>
	</div>
</logic:notEmpty>
</html:form>
<div id="command">
	<logic:notEmpty	name="officerForm" property="officerProfiles">
		<div>
		<logic:notEqual name="officerForm" property="requestOrigin" value="DT">
			<html:form styleId="selectOfficerCode" action="/submitOverrideAssignment">
					<html:hidden styleId="jpo" property="jpo" value=""/>
					<html:submit property="submitAction" styleId="selectBtn"><bean:message key="button.select"></bean:message></html:submit>
			</html:form>
		</logic:notEqual>
		<logic:equal name="officerForm" property="requestOrigin" value="DT">
			<html:form styleId="selectOfficerCode" action="/displayJuvenileDrugTestingCreate.do">
						<html:hidden styleId="jpo" property="administeredBy" value=""/>
						<html:submit property="submitAction" styleId="selectBtn"><bean:message key="button.select"></bean:message></html:submit>
			</html:form>
		</logic:equal>
				
			
		</div>
	</logic:notEmpty>
	<div>
		<input type="button" id = "backBtn" value="Go Back"/>
	</div>
</div>
</body>
</html:html>