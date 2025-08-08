<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 05/04/2012		C. Shimek	73382 correct js validation issues --%>
<%-- 05/09/2012		C. Shimek	73382 removed character validations for closing Evaluation and Comments --%>
<%-- 06/21/2012		C. Shimek	73735 add outcome description drop down --%>
<%-- 11/13/2012     DWilliamson ER#73898 Create Deceased Juvenile Warning --%>
<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - closingUpdate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>

<html:javascript formName="casefileClosingForm" />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/closing.js?clr=1"></script>
<%-- <script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>  --%>

<script type="text/javascript">

 $(document).ready(function(){
	 if ( $("#supvOutCome").val() === "D") {
		 $(".deceasedQuestions").show();
	 } else {
		 $(".deceasedQuestions").hide();
	 }
	 
	 $("#supvOutCome").on("change",function(){
		var supervisionOutcome = $("#supvOutCome").val();
	
		if (supervisionOutcome == "D")	{
			$("#sodBlank").show();
			$("#sodOptional").hide();
			$("#sodRequired").hide();
			alert("Is the juvenile deceased?  If so, continue.  If not, correct the outcome.");
			$("#supvOutCome").focus();
			$(".deceasedQuestions").show();
		} else {
			$(".deceasedQuestions").hide();
			$("#deathReason").val("");
			$("#deathVerification").val("");
			$("#deathDate").val("");
			$("#deathAge").val("");
		}
	 })
	 
	 $("#deathReason").change(function(){
		if ( $("#deathReason").val() != "" ) {
			$("#reasonDesc").val( $("#deathReason option:selected").text() );
		} else {
			$("#reasonDesc").val("");
		}
	 })
	 
	 $("#deathVerification").change(function(){
		 if ( $("#deathVerification").val() != "" ){
		 	$("#verificationDesc").val($("#deathVerification option:selected").text());
		 } else {
			 $("#verificationDesc").val("");
		 }
	 })
	 
	 $("#deathDate").change(function(){
		var assignmentDate 	= new Date( $("#assignmentDate").val() );
		var deathDate	  	= new Date( $("#deathDate").val() );
		var dob				= new Date( $("#dob").val() );
		var today 			= new Date();
		
		if ( deathDate > today ) {
			alert("Date of Death cannot be future date.");
			$("#deathDate").val("");
			$("#deathAge").val("");
			return false;
		}
		
		if ( deathDate < assignmentDate ) {
			alert("Date of Death cannot be prior to case assignment date.");
			$("#deathDate").val("");
			$("#deathAge").val("");
			return false;
		}
		
		if (true) {
			var age = ageCal(dob, deathDate );
			if ($.isNumeric(age)) {
				$("#deathAge").val(age);
			} else {
				$("#deathAge").val("");
			}
		}
		
	 })
	 
	 
	 function ageCal(dob, deathDate){
		 var bDate = new Date( dob );
		 var dDate = new Date( deathDate );
		 var ageDifMs = dDate.getTime() - bDate.getTime();
		 var ageDate = new Date( ageDifMs);
		 return ( ageDate.getUTCFullYear() - 1970 );
	 }
	 
 })
</script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0"> <!-- onload="checkandSetDropDownSelects()" -->
<html:form action="/displayCasefileClosingData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|77">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - 	   
      <bean:message key="title.close"/> <bean:message key="title.casefile"/> - <logic:equal name="casefileClosingForm" property="action" value="update"> Update</logic:equal> 
      <bean:message key="title.casefile"/> <bean:message key="title.closing"/>
    </td>
  </tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter information, then click Next button.</li>
				<li>Click "Back" button to return to the previous page. </li>
			</ul>
		</td>
	 </tr>
	<tr>
		<td class="required">
			<bean:message key="prompt.diamond" />Required Fields (one controlling referral selection required)&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" />
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- BEGIN MAIN BODY TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
<%-- BEGIN CASEFILE TABS TABLE --%>
	  		<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
	  			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  				<tiles:put name="tabid" value="casefiledetailstab"/>
	  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  			</tiles:insert>		
	  		</logic:equal>	
	  
	  		<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
	  			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  				<tiles:put name="tabid" value="closing"/>
	  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  			</tiles:insert>		
	  		</logic:notEqual>	
<%-- END CASEFILE TABS TABLE --%>	

<%-- BEGIN CASEFILE TABLE --%>
	  		<table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	  	    	<tr>
	  				<td valign='top' align='center'>
						<div class='spacer'></div>
	  					<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign='top' class='detailHead'><bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
							</tr>
							<tr>
								<td>
									<table align='center' width='100%' cellpadding="2" cellspacing="1">
	  									
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.diamond" /> <bean:message key="prompt.casefileEndDate"/> </td>
											<td class="formDe" colspan='3'>
												<html:text name="casefileClosingForm" property="supervisionEndDate" size="10" styleId="supvEndDate" readonly="true"/>
	                           						<input type="hidden" name="activationDatex" id="activationDate" value="<bean:write name='casefileClosingForm' property='activationDateStr' />" />
											</td>
	                   					</tr>
					                    <tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.diamond" /> <bean:message key="prompt.supervisionOutcome"/></td>
											<td class="formDe" colspan='3'>
												<html:select name="casefileClosingForm" property="supervisionOutcomeId" styleId="supvOutCome"> <!--onchange="checkSelect() -->
													<option value="">Please Select</option>
													<html:optionsCollection name="casefileClosingForm" property="activeSupervisionOutcomesList" value="code" label="description"></html:optionsCollection>
												</html:select>
											</td>
										</tr>
										
										
										 <tr class="deceasedQuestions" >
											<td class="formDeLabel deathQuestions" width="1%" nowrap='nowrap'>How did the youth die? </td>
											<td class="formDe deathQuestions" colspan='3'>
											<html:hidden styleId="reasonDesc" name="casefileClosingForm"  property="youthDeathReasonDesc" />
												<html:select name="casefileClosingForm" property="youthDeathReason" styleId="deathReason"> <!--onchange="checkSelect() -->
													<option value="">Please Select</option>
													<html:optionsCollection name="casefileClosingForm" property="causesOfDeath" value="code" label="description"></html:optionsCollection>
												</html:select>
											</td>
										</tr>
										
										<tr class="deceasedQuestions">
											<td class="formDeLabel" width="1%" nowrap='nowrap'>How was death verified?  </td>
											<td class="formDe" colspan='3'>
												<input id="assignmentDate" type="hidden" value='<bean:write name="juvenileCasefileForm" property="assignmentDate"/>' />
												<input id="dob" type="hidden" value='<bean:write name="juvenileCasefileForm" property="dob"/>' />
												<html:hidden styleId="verificationDesc" name="casefileClosingForm" property="youthDeathVerificationDesc"/>
												<html:select name="casefileClosingForm" property="youthDeathVerification" styleId="deathVerification"> <!--onchange="checkSelect() -->
													<option value="">Please Select</option>
													<html:optionsCollection name="casefileClosingForm" property="deathVerficationCodes" value="code" label="description"></html:optionsCollection>
												</html:select>
											</td>
										</tr>
										
										<tr class="deceasedQuestions">
											<td class="formDeLabel" width="1%" nowrap='nowrap'>Date of Death?  </td>
											<td class="formDe" colspan='3'>
												<html:text name="casefileClosingForm" property="deathDate" styleId="deathDate" />
												
											</td>
										</tr>
										
										<tr class="deceasedQuestions">
											<td class="formDeLabel" width="1%" nowrap='nowrap'>Age at Death?  </td>
											<td class="formDe" colspan='3'>
												<html:text name="casefileClosingForm" property="deathAge" styleId="deathAge"/>
											</td>
										</tr>
									
							
							
										<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>">                                        
											<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">
												<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>">
													<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>">
														<bean:define id="contRefReqd" value="" />
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
<%-- 										<logic:notPresent name="contRefReqd"> --%>
											<tr id="sodBlank">
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.supervisionOutcome"/> <bean:message key="prompt.description"/></td>
												<td class="formDe" colspan='3'>
													<select name="blankList" Id="sodBlankId" disabled="true">
														<option value="">Please Select</option>
													</select>
												</td>
											</tr>
											<tr id="sodOptional" >
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.supervisionOutcome"/> <bean:message key="prompt.description"/></td>
												<td class="formDe" colspan='3'>
													<html:select name="casefileClosingForm" property="completedDescId" styleId="sodOptionalId" > 
														<option value="">Please Select</option>
										 				<html:optionsCollection name="casefileClosingForm" property="optionalSupervisionOutcomeDescList" value="code" label="description"></html:optionsCollection> 
													</html:select>
												</td>
											</tr>
											<tr id="sodRequired">
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.supervisionOutcome"/> <bean:message key="prompt.description"/></td>
												<td class="formDe" colspan='3'>
													<html:select name="casefileClosingForm" property="failureDescId" styleId="sodRequiredId">
														<option value="">Please Select</option>
														<html:optionsCollection name="casefileClosingForm" property="requiredSupervisionOutcomeDescList" value="code" label="description"></html:optionsCollection> 
													</html:select>
												</td>
											</tr>
											<html:hidden name="casefileClosingForm" property="supervsionOutcomeDescriptionId" styleId="selectedOutcomeId"/>
											<html:hidden name="casefileClosingForm" property="supervsionOutcomeDescription"   styleId="selectedOutcomeDesc"/>
<%-- 										</logic:notPresent> --%>
									    <logic:equal  name="juvenileCasefileForm" property="controllingReferralNumber" value="">
						                   	<logic:notPresent name="contRefReqd">
									        	<bean:define id="contRefReqd" value=""/>
									       </logic:notPresent>
									    </logic:equal>
										<logic:present name="contRefReqd">
						                   	<tr>
						                         <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.controlling"/> <bean:message key="prompt.referralNumber"/> </td>  										
									             <td class="formDe" colspan='4'>
											       <html:select property="controllingReferral" name="casefileClosingForm" styleId="cntrlRef">
											          <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
											          <html:optionsCollection property="referrals" value="referralNumber" label="referralNumber"  name="casefileClosingForm"/>
										    	   </html:select>
										    	   <input type="hidden" name="cfx" value="<bean:write name="casefileClosingForm" property="controllingReferral" />" id="cntrlRefId" />
									          </td>
									       </tr>
										</logic:present>
										<logic:notEqual  name="juvenileCasefileForm" property="controllingReferralNumber" value="">	  
											<jims:if name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>" op="equal">
						                       <jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>" op="equal" />
						                        <jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>" op="equal" />
						                          <jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>" op="equal" />
						                         <jims:then>
						                             <tr>
						                                 <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.controlling"/> <bean:message key="prompt.referralNumber"/> </td>  										
						                                 <td class="formDe" colspan='4'>
						                                     <bean:write property="controllingReferralNumber" name="juvenileCasefileForm"  />
											             </td>
											         </tr>  
											    </jims:then>
											</jims:if>
										</logic:notEqual>
	
										<tr>
											<td class="formDeLabel" colspan='4'><bean:message key="prompt.diamond" /> <bean:message key="prompt.closingEvaluation"/>
												&nbsp;
												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
													<tiles:put name="tTextField" value="closingEvalution" />
													<tiles:put name="tSpellCount" value="spellBtn1" />
												</tiles:insert>
												(Max. characters allowed: 5200)
											</td>
										</tr>
										<tr>
											<td class="formDe" colspan='4'><html:textarea name="casefileClosingForm" property="closingEvalution" styleId="closingEval" style="width:100%" rows="5" /></td>
										</tr>

										<%-- <tr>
											<td class="formDeLabel" colspan='4'><bean:message key="prompt.closing"/> <bean:message key="prompt.comments"/>
												&nbsp;
												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
													<tiles:put name="tTextField" value="closingComments" />
													<tiles:put name="tSpellCount" value="spellBtn2" />
												</tiles:insert>
												(Max. characters allowed: 4100)
											</td>
										</tr>
										<tr>
											<td class="formDe" colspan='4'><html:textarea name="casefileClosingForm" property="closingComments" styleId="closingCmnts"  style="width:100%" rows="5"/></td>
										</tr> --%>
									</table>
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
										
<%-- BEGIN BUTTON TABLE --%>
						<table width="100%">
						  	<tr>
						    	<td align="center">
						    		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
						    		<input type="hidden" name="cldt" value="<bean:write name="casefileClosingForm" property="earliestAssignAddDate" />" id="earliestAsgnDate" />
						    		<input type="hidden" name="eadcf" value="<bean:write name="casefileClosingForm" property="earliestDateCasefile" />" id="earliestAsgnDateCaseNum" />
						    		<input type="hidden" name="jvno" value="<bean:write name="casefileClosingForm" property="juvenileNum" />" id="juvNum" />
						    		<input type="hidden" name="supno" value="<bean:write name="casefileClosingForm" property="supervisionNumber" />" id="supervisionNum" />
						    		<html:hidden  name="casefileClosingForm" property="hasTransferredOffense" styleId="transferredOffense"/>
						    		<html:hidden  name="casefileClosingForm" property="closingInfoAvail" styleId="facilityMessage"/>
						    		<html:hidden  name="casefileClosingForm" property="transferCasefileId" styleId="transferCasefileId"/>
						    		<html:hidden name="casefileClosingForm" property="skipSubOutCome" styleId="hdnSkipSubOutCome"/>
						    		<html:hidden name="juvenileCasefileForm" property="supervisionCategoryId" styleId="hdnSupCatId"/>
						    		
						    		<%-- <input type="hidden" name="cldt" value="<bean:write name="casefileClosingForm" property="hasWarningStatus" id="warnStatId" /> --%>
						    		<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>">                                        
	                                      <logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">
	                                         <logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>">
	                                         	<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>">
						    		         <html:submit property="submitAction" styleId="next"><bean:message key="button.next" /></html:submit> 
						    		         </logic:notEqual>
						    		      </logic:notEqual>
						    		   </logic:notEqual>
						    		 </logic:notEqual>          
						    		 <jims:if name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>" op="equal">
	                                    	<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>" op="equal" />
	                                       <jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>" op="equal" />
	                                       		<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>" op="equal" />
		                                          <jims:then>
		                                              <html:submit  property="submitAction" styleId="next"><bean:message key="button.next" /></html:submit>
		                                          </jims:then>
	                                    </jims:if>      
						    		 <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						    		
						    	</td>
						  	</tr>
						</table>
						<%-- END BUTTON TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- END CASEFILE TABLE --%>
		</td>
	</tr>
</table>
<%-- END MAIN BODY TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>