<!DOCTYPE HTML>

<%-- Used to create PACT Risk/Needs Level --%>
<%--MODIFICATIONS --%>
<%-- 02/06/2017	 ugopinath	Create JSP --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<%--BEGIN HEADER TAG--%>
<head>

<msp:nocache />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - caseTabAssignedReferrals - createReferralPACTRiskNeedsLvl.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/referral/pactRiskReferral.js"></script> 

<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>
<script type="text/javascript">
$(function() {
	var selectedReferralNumber = sessionStorage.getItem("selectedReferralNumber");
	var offenses			   = sessionStorage.getItem("offenses");
	var lastPactDate		   = sessionStorage.getItem("lastPactDate");
	var pdaReadOnly			   = sessionStorage.getItem("pdaReadOnly");
	
	if ( pdaReadOnly != null
			&& "true" == pdaReadOnly ) {
		$("#pdaReadOnly").val(pdaReadOnly)
		$("#lastPactDate").prop("disabled", true);
	} else {
		$("#pdaReadOnly").val(pdaReadOnly)
		$("#lastPactDate").prop("disabled", false);
	}
	
	if ( selectedReferralNumber != null ) {
		$("#selectedReferral").val( selectedReferralNumber );
		$("#refNumId").val( selectedReferralNumber );
	}
	
	if ( offenses != null ){
		$("#offenses").html( offenses );
	}
	
	if( lastPactDate != null ){
		$("#lastPactDate").val(lastPactDate);
	}
	
	if(typeof $("#admitDate") != "undefined"){
		datePickerSingle($("#lastPactDate"),"PACT date ", true);		
	}		
	
	if(typeof $("#refNumId") != "undefined" && $("#refNumId").val()!="")
	{	
		$("#riskNeeds").show();
		$("#riskNeedsButton").show();
	}	
	/* if($("#pdaReadOnly").val()=="true")
		document.getElementById('lastPactDate').disabled=true; */
	$("#nextBtn").click(function(){
		var today = formatToday();
		var selectedReferral = $("input[name=selectedReferral]:checked").val();
		sessionStorage.setItem("selectedReferralNumber", selectedReferral )
		sessionStorage.setItem("offenses",  $("#offenses").html() );
		sessionStorage.setItem("pdaReadOnly", $("#pdaReadOnly").val() );
		var crtResult = $("#dispositionCode-"+selectedReferral).val();
		var TJJDCode=$("#TJJDCode-"+selectedReferral).val();
		var crtDate = $("#dispDate-"+selectedReferral).val();
		var subgroup = $("#subGroup-"+selectedReferral).val();
		
 		if(TJJDCode=='CREL'||TJJDCode=='TEMP')
		{
			if(crtResult=='')
			{
				alert("This supervision does not qualify for PACT R/N Levels entry at this time.  Contact Data Corrections for additional assistance.");
				return false;
			}
			
		} 
 		
 		if( subgroup == 'G' && crtDate > today ){
 			
 			alert("This supervision does not qualify for PACT R/N Levels entry at this time.  Contact Data Corrections for additional assistance.");
			return false;
 			
 		}
 		
		if($("#lastPactDate").val()=="")
		{
			alert("PACT Date is required.");
			$("#lastPactDate").focus();
			return false;
		}
		if($("#riskLvl").val()=="")
		{			
			alert("Risk Level is required.");
			$("#riskLvl").focus();
			return false;
		} 
		if($("#needsLvl").val()=="")
		{
			alert("Need Level is required.");
			$("#needsLvl").focus();
			return false;
		}
		var pda=$("#lastPactDate").val();
		sessionStorage.setItem("lastPactDate", pda )
		//$("#dispDate").val(pda);
		//$("#lastPactDate").val( pdaDate );
		$('form')
		.attr(
				'action',
				"/JuvenileCasework/displayJuvenileCasefileAssignedReferralsList.do?submitAction=Next&pda="+pda);

		$('form').submit();	
	});
 });
 
 function selectReferral()
 {
	var selectedReferral = $("input[name=selectedReferral]:checked").val();
	var courtDate = $("#dispDate-"+selectedReferral).val();
	var assignDate = $("#assignDate-"+selectedReferral).val();
	var sprvisionCat = $("#sprvCat-"+selectedReferral).val();
	var sprvnType = $("#sprvType-"+selectedReferral).val();
	var specialCat = $("#specCat-"+selectedReferral).val();
	var dispSubgroup = $("#subGroup-"+selectedReferral).val();
	var pdaDate = $("#pdaDate-"+selectedReferral).val();
	var crtResult = $("#dispositionCode-"+selectedReferral).val();
	var TJJDCode=$("#TJJDCode-"+selectedReferral).val();
	var intakeTJPCCode=$("#intakeDecisionTJPCCode-"+selectedReferral).val();
	var intakeDate = $("#intakeDate-"+selectedReferral).val();
	var pactOID = $("#uPactId").val();
	

	//alert(crtResult)
	var offenses		 = "";
	$("#refNumId").val( selectedReferral );
	$("#tempRefNum").val( selectedReferral );

	$("#riskNeeds").show();
	//$("#riskLvl").val("");
	//$("#needsLvl").val("");
	$("#riskNeedsButton").show();
	document.getElementById('lastPactDate').disabled=false;
	//alert(sprvnType)
			
	<logic:iterate id="assignedReferrals" name="juvenileCasefileForm" property="juvenileCasefileReferralsList" indexId="index">
		<logic:equal name="assignedReferrals" property="offensesAvailable" value="true"> 
			if ( selectedReferral == '<bean:write name="assignedReferrals" property="referralNumber"/>' ) {
				<logic:iterate id="theOffenses" name="assignedReferrals" property="offenses" indexId="index"> 
					if(Number('<%= index %>') > 0 ){
						 offenses =  offenses.concat(",");
					}
					 offenses =  offenses.concat('<bean:write name="theOffenses" property="offenseCodeId"/>');
					 $("#offenses").html(offenses);
				</logic:iterate>
			}
		</logic:equal>
    </logic:iterate>
  //default
  $("#lastPactDate").val( formatToday );
  $("#pdaReadOnly").val("false");
  //task 124903   
  //alert(courtDate)
  //alert('sprvisionCat='+sprvisionCat)
   
  if( typeof( courtDate )!= "undefined" && courtDate != '')
   {  
	  //alert('courtDate='+courtDate);
	if(dispSubgroup!='')
	{
		var today = formatDate(new Date());
		var formattedCourtDate = formatDate(courtDate);
		if(dispSubgroup=='I'&&formattedCourtDate>today)
		{			
			$("#lastPactDate").val( pdaDate );
			document.getElementById('lastPactDate').disabled=true;
			$("#pdaReadOnly").val("true");
		}
		else
		{
			if((dispSubgroup=='B'||dispSubgroup=='C'||dispSubgroup=='D'||dispSubgroup=='E'||dispSubgroup=='F'||dispSubgroup=='G')&&formattedCourtDate<=today)
			{
				$("#lastPactDate").val( courtDate );
				document.getElementById('lastPactDate').disabled=true;
				$("#pdaReadOnly").val("true");
			}
		}
	}
  }
  	
	//bug fix 105403
	else if( sprvisionCat == 'PRE-ADJUDICATION')
	{
		
		if(specialCat=='DP')//check category instead 
		{
				if( typeof( courtDate )!= "undefined" && courtDate != ''){
					//alert("Found "+courtDate);
					$("#lastPactDate").val( courtDate );
					document.getElementById('lastPactDate').disabled=true;
					$("#pdaReadOnly").val("true");
				}
				else
				{
					$("#lastPactDate").val( assignDate );
					document.getElementById('lastPactDate').disabled=true;
					$("#pdaReadOnly").val("true");
				}
		}
		
		//alert("PRE-ADJUDICATION "+sprvisionCat);
	}
	//US 119197	
	else
	{
		if(intakeTJPCCode!='000')
		{
			$("#lastPactDate").val( intakeDate );//add intakedate
			document.getElementById('lastPactDate').disabled=true;	
			$("#pdaReadOnly").val("true");
		}
		
	}
   //console.log("TJJDCode: " + TJJDCode);
  //console.log("crtResult: " + crtResult)
  if(TJJDCode=='CREL'||TJJDCode=='TEMP')
  {	
	  //alert("TJJDCode "+TJJDCode);
			if(crtResult=='')
			{
				 //alert("Court Result "+crtResult);
				 $('#riskNeeds').find('input, select').prop('disabled',true);
				$("#pactReadOnly").val("true");
			}
			else
			{
				//alert("Court Result "+crtResult);
				 $('#riskNeeds').find('input, select').prop('disabled',false);
				 document.getElementById('lastPactDate').disabled=true;
				 document.getElementById('uPactId').disabled=true;
				 document.getElementById('refNumId').disabled=true;
				$("#pactReadOnly").val("false");
			}
  }
  else
  {
	  document.getElementById("riskNeeds").disabled=false;
	  $("#pactReadOnly").val("false");
  }
  
  if( pactOID != ''){
	  
	  //alert(" setting Risk/Needs");
	  var risk = $("#riskLvl").val();
	  var needs = $("#needsLvl").val();
	  
	  $('#needsLvl option[value=needs]').attr("selected", "selected");
	  $('#riskLvl option[value=risk]').attr("selected", "selected");
	  
  }else{
	  
	$("#riskLvl").val("");
	$("#needsLvl").val("");
  }
  
  
 }
	function formatToday() 
	{
 		
		var today = new Date();
		var day = today.getDate();

		var month = today.getMonth()+1; 
		var year = today.getFullYear();
		if( day<10 ) 
		{
		    day ='0'+day;
		} 

		if( month<10 ) 
		{
		    month='0'+month;
		} 

		return month+'/'+day+'/'+year;
	}
	
function formatDate(date) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
 </script>
 
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/displayJuvenileCasefileAssignedReferralsList"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Casefile Referral - Create Referral PACT Risk/Need Levels </td> 
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select a Referral # to add Risk/Needs Level.</li> 
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="assignedreferralstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
		</td>
	</tr>
	<tr>	
		<td>
<%--tabs end--%> 
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
  						<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								</tiles:insert>
								</td>
  							</tr>
  							<tr>
  								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
  						</table>
<%-- BEGIN DETAIL TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
<%-- NOT PART OF I10 --%> 
									<div class="spacer"></div>

<%-- BEGIN OF REFERRALS TABLE--%> 
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
										<tr> 
											<td class="detailHead"><bean:message key="prompt.referrals" /></td> 
										</tr> 
								        <tr>
								        	<td> 
								        		<table cellpadding="2" cellspacing="1" width='100%'> 
									                <tr bgcolor='#cccccc'> 
											              <td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.referral" />
												              <jims:sortResults beanName="juvenileCasefileForm" results="juvenileCasefileReferralsList" primaryPropSort="referralNumber" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
											              </td> 
										                  <td align='left' class="subHead" width='1%' nowrap>Referral <bean:message key="prompt.date" /></td> 
										                  <td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.court" /> <bean:message key="prompt.date" /></td> 
										                  <td align='left' class="subHead"><bean:message key="prompt.court"/> <bean:message key="prompt.id"/></td> 
										                  <td align='left' class="subHead"><bean:message key="prompt.intake"/></td>
										                  <td align='left' class="subHead"><bean:message key="prompt.referral"/> <bean:message key="prompt.type"/></td>		             
									            		  <td align='left' class="subHead"><bean:message key="prompt.petitionAllegation"/></td>
										                  <td align='left' class="subHead"><bean:message key="prompt.finalDisposition"/></td>
										                  <td align='left' class="subHead"><bean:message key="prompt.riskNeedLevel" /></td>		
										                  <td align='left' class="subHead"><bean:message key="prompt.pactDate" /></td>
										                   <td align='left' class="subHead"><bean:message key="prompt.pactStatus" /></td>
										            </tr> 
										            <logic:notEmpty name="juvenileCasefileForm" property="juvenileCasefileReferralsList">
									              		<% String rowClass = "alternateRow" ;
									              			 String casefileUrl = naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM);
										              		%> 
						        						<logic:iterate id="assignedReferrals" name="juvenileCasefileForm" property="juvenileCasefileReferralsList" indexId="index" > 
						        						<%-- Begin Pagination item wrap --%>
						              						<pg:item>						              							
						          								<bean:define id="juvenileNum" name="juvenileCasefileForm" property="juvenileNum" type="java.lang.String"/>
						          								<bean:define id="referralNum" name="assignedReferrals" property="referralNumber" type="java.lang.String"/>
						          								<logic:equal name="assignedReferrals" property="referralFound" value="true">
																	<% rowClass = ((index.intValue()) % 2 == 1) ? "normalRow" : "alternateRow"; %>
							            							<tr class=<%=rowClass%> height="100%">
																		<%StringBuffer url = new StringBuffer();
								            								url.append(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=");
								            								url.append(request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM));
								            								url.append("&" + naming.PDJuvenileCaseConstants.JUVENILENUM_PARAM + "=" + juvenileNum);
								            								url.append("&" + naming.PDJuvenileCaseConstants.REFERRALNUM_PARAM + "=" + referralNum);
								            								String queryUrl = url.toString();							
							             								%>
									             						<td>
									             						<logic:empty name="assignedReferrals" property="refCloseDate">
									             								<logic:notEqual name="assignedReferrals" property="intakeDecisionTJPCCode" value="060">
									             									<logic:notEqual name="juvenileCasefileForm" property="onlyNosRecords" value="true">
									             								  	
									             								    <input type="radio" name="selectedReferral"
									             														indexed="true"
									             														 value="<%= referralNum  %>"
									             														 onclick="selectReferral()"/>
									             									</logic:notEqual>					 
									      									     </logic:notEqual>
									             								<elogic:if name="juvenileCasefileForm" property="onlyNosRecords" op="equal" value="true">
									             									<elogic:then>
									             									 	<input type="radio" name="selectedReferral"
									             														indexed="true"
									             														 value="<%= referralNum  %>"
									             														 onclick="selectReferral()"/>
									             									 </elogic:then>
									             								 </elogic:if>
									             						</logic:empty>
									             						<a href="/<msp:webapp/>displayJuvenileCasefileReferralDetails.do?<%out.print(queryUrl);%>"><bean:write name="assignedReferrals" property="referralNumber"/></a></td>
							      										<td><bean:write name="assignedReferrals" property="referralDate" format="MM/dd/yyyy"/></td>														
							          									<td><bean:write name="assignedReferrals" property="courtDate" format="MM/dd/yyyy"/></td>
							          									<input type="hidden" id='<%="dispDate-" + referralNum%>' name="assignedReferrals" value='<bean:write name="assignedReferrals" property="courtDate" format="MM/dd/yyyy"/>'/>
							          									<html:hidden styleId='<%="sprvCat-"+referralNum%>'  name='assignedReferrals' property='supervisioncategoryPact' indexed="true"/>
							          									<html:hidden styleId='<%="sprvType-"+referralNum%>'  name='assignedReferrals' property='supervisionTypeId' indexed="true"/>
							          									<html:hidden styleId='<%="specCat-"+referralNum%>'  name='assignedReferrals' property='specialCategoryCd' indexed="true"/>
							          									<html:hidden styleId='<%="subGroup-"+referralNum%>' name="assignedReferrals" property="dispositionSubgroup" indexed="true"/>
							          									<html:hidden styleId='<%="pdaDate-"+referralNum%>' name="assignedReferrals" property="pdaDate" indexed="true"/>
							          									<html:hidden styleId='<%="courtResult-"+referralNum%>' name="assignedReferrals" property="courtResult" indexed="true"/>							          									
							          									<html:hidden styleId='<%="TJJDCode-"+referralNum%>' name="assignedReferrals" property="TJJDCode" indexed="true"/>							          									
							          									<html:hidden styleId='<%="intakeDecisionTJPCCode-"+referralNum%>' name="assignedReferrals" property="intakeDecisionTJPCCode" indexed="true"/>
							          									<html:hidden styleId='<%="intakeDate-"+referralNum%>' name="assignedReferrals" property="intakeDate" indexed="true"/>
							          									<html:hidden styleId='<%="dispositionCode-"+referralNum%>' name="assignedReferrals" property="dispositionCode" indexed="true"/>							          									
							          									<input type="hidden" id='<%="assignDate-" + referralNum%>' name="assignedReferrals" value='<bean:write name="assignedReferrals" property="assignmentDate" format="MM/dd/yyyy"/>'/>
							      										<td><bean:write name="assignedReferrals" property="courtId"/></td>
							          									<td><bean:write name="assignedReferrals" property="intakeDecision"/></td>
							          									<td><%--ER changes 11054 adding span--%>
							          										<span title='<bean:write name="assignedReferrals" property="referralTypeDesc"/>'>
							          											<bean:write name="assignedReferrals" property="referralTypeInd"/>
							          										</span>
							      										<td>
							      											<logic:equal name="assignedReferrals" property="petitionsAvailable" value="true">
							      												<logic:iterate id="thePetitions" name="assignedReferrals" property="petitions" indexId="index"> 
							      													<span title='<bean:write name="thePetitions" property="offense"/>'>
							      														<bean:write name="thePetitions" property="offenseCodeId"/>
							      														<logic:notEqual name="assignedReferrals" property="petitionCollectionSize" value="<%=index.toString()%>">
							      															,
							      														</logic:notEqual>
							      													</span>
							      												</logic:iterate>
							      											</logic:equal>
							      										</td>
								      									<td>
								      										<span title='<bean:write name="assignedReferrals" property="finalDispositionDescription"/>'>
								      											<bean:write name="assignedReferrals" property="finalDisposition"/>
								      										</span>
								      									</td>
								      									<td>
								      										<logic:notEmpty name="assignedReferrals" property="riskNeedLvl">
								      											 <span title='<bean:write name="assignedReferrals" property="riskNeedLvlDesc"/>'><bean:write name="assignedReferrals" property="riskNeedLvl"/></span>
								      										</logic:notEmpty></td>								      									
								      									<td><bean:write name="assignedReferrals" property="lastPactDate"/></td>
								      									<logic:notEqual name="assignedReferrals" property="pactStatus" value="SYS GENERATE">
								      										<td><bean:write name="assignedReferrals" property="pactStatus"/></td>
								      									</logic:notEqual>
									          						</tr>
									          						<tr class=<%=rowClass%> >
								      									<td></td> 
								      									<td colspan='10'><b><bean:message key="prompt.offenses"/></b>: 
									      									<logic:equal name="assignedReferrals" property="offensesAvailable" value="true">
								      											<logic:iterate id="theOffenses" name="assignedReferrals" property="offenses" indexId="index"> 
								      												<span title='<bean:write name="theOffenses" property="offenseCode.longDescription"/>'>
								      													<bean:write name="theOffenses" property="offenseCodeId"/>
								      													<logic:notEqual name="assignedReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
								      														,
							    	  													</logic:notEqual>
							      													</span>
							      												</logic:iterate>
							      											</logic:equal>
							      										</td>
							      									</tr>
							        							</logic:equal>
							      							</pg:item>
<%-- End Pagination item wrap --%>
														</logic:iterate> 
													</logic:notEmpty>
							    					
								         		</table> 
<%-- Begin Pagination navigation Row--%>
												<table align="center">
													<tr>
														<td>
															<pg:index>
																<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																	<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																	<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
																</tiles:insert>
															</pg:index>
														</td>
													</tr>
												</table>
<%-- End Pagination navigation Row--%>
								        	</td>
								    	</tr> 
									</table> 
<%-- END OF REFERRALS TABLE--%> 
									<div class='spacer'></div>
									
									<table width='98%' border="0" cellpadding="2" cellspacing="0"> 
									<tr  id="riskNeeds" class="hidden">
										<td>
											<table width='100%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
												<tr>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.referral" /></td>
													<td class="formDe" width='3%'><html:text name="juvenileCasefileForm" property="selectedValue" styleId="refNumId" size="4" maxlength="4" disabled="true"/>	</td>
													<td align='left' class="formDeLabel" width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.pactDate" /></td>
													 <td class="formDe" width='1%'> 	
													 
													 	<span title='<bean:write name="juvenileCasefileForm" property="userPACTDateHover"/>'>
								      											<html:text name="juvenileCasefileForm" property="userPACTDate" styleId="lastPactDate" size="10" maxlength="10" />
								      					</span>
													 </td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.riskLevel" /></td>
													<td class="formDe" width='3%'><html:select property="userRiskLvl" name="juvenileCasefileForm" styleId="riskLvl">
															   <html:option value=""><bean:message key="select.generic" /></html:option>
															   <html:option value="H">HIGH</html:option>
															   <html:option value="L">LOW</html:option>
															   <html:option value="M">MODERATE</html:option>
															</html:select>  </td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.needLevel" /></td>
													<td class="formDe" width='3%'>	<html:select property="userNeedsLvl" name="juvenileCasefileForm" styleId="needsLvl">
															   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
															   <html:option value="H">HIGH</html:option>
															   <html:option value="L">LOW</html:option>
															   <html:option value="M">MODERATE</html:option>
															</html:select>  </td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.pactId" /></td>
													<td class="formDe" width='3%'><html:text name="juvenileCasefileForm" property="userPactId" styleId="uPactId" size="4" maxlength="8" disabled="true"/></td>
												</tr>
												<tr></tr>
												<tr >
			      									<td class="formDe"></td>
			      									<td class="formDeLabel" width='1%'><b><bean:message key="prompt.offenses"/></b>: </td>
			      									<td class="formDe" colspan='12'>
			      										<div id="offenses"></div>
		      										</td>
		      									</tr>
		      								</table>
			      						</td>
			      					</tr>
								</table>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" cellpadding=1 cellspacing=1 align=center>
										<tr id="riskNeedsButton" class="hidden">
										
											<td align="center">										
											<input type=button value=Back onclick="goNav('back')">	
											<html:submit property="submitAction" styleId="nextBtn">
												<bean:message key="button.next"></bean:message>
											</html:submit>			
											<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
											</td>											
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class='spacer'></div> 
								</td>
							</tr>
						</table>
<%-- END DETAIL TABLE --%> 						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class='spacer4px'></div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<html:hidden styleId="tempRefNum" name="juvenileCasefileForm" property="selectedValue"/>
<html:hidden styleId="dispDate" name="juvenileCasefileForm" property="userPACTDate"/>
<html:hidden styleId="specialCategoryCd" name="juvenileCasefileForm" property="specialCategoryCd"/>
<html:hidden styleId="pdaReadOnly" name="juvenileCasefileForm" property="pdaReadOnly"/>
<html:hidden styleId="pactReadOnly" name="juvenileCasefileForm" property="pactReadOnly"/>
<%-- <html:hidden styleId="assignmentDate" name="juvenileCasefileForm" property="assignmentDate"/> --%>
<input type="hidden" id="assignmentDate" name="juvenileCasefileForm" value='<bean:write name="juvenileCasefileForm" property="assignmentDate" format="MM/dd/yyyy"/>'/>
</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>