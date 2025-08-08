<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 D Sruti                #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<html:javascript formName="caseplanUpdateForm" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - updateGenerateCaseplanDetails.jsp</title>
<%-- <html:javascript formName="caseplanForm" /> --%>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/PopupWindow.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<%--END HEADER TAG--%>

 <script type='text/javascript'>
 $(document).ready(function(){
	 var isFosterCare = sessionStorage.getItem("isFosterCare");
	 $("input[name=juvFosterCareCandidateStr][value="+isFosterCare+"]").prop('checked', true);
	 
 })
 var cal1 = new CalendarPopup();
 cal1.showYearNavigation();
 
 function validateFields(theForm)
 {
 	
 	clearAllValArrays();
 		customValRequired ("priorServices","Prior Service is required.");  		  	
 		customValRequired ("supervisionLevelId","Supervision level is required.",""); 
 		customValRequired ("contactInformation","Contact Information is required.","");   				
 		customValRequired ("dtDeterminationMade","Determination date is required.");
 		
 		var fosterCareElements = document.getElementsByName('juvFosterCareCandidateStr');	
 		var otherdate = document.getElementsByName('otherDated');
 		var explanation = document.getElementsByName('explanation');
 		
 		//bug fix 11144
 		var otherDtNotified = document.getElementsByName('otherDtNotified');//date notified
 		var otherNameMailedDt = document.getElementsByName('otherNameMailedDt'); //date of copy/mailed notified
 		var otherNameDtOfParticipation = document.getElementsByName('otherNameDtOfParticipation'); //date of participation
 		
 		if(otherDtNotified[0].value!="" || otherNameMailedDt[0].value!="" || 
 				otherNameDtOfParticipation[0].value!=""){ //bug fix 22292
 			customValRequired ("othername","Name required if date provided for Other.");
 			document.getElementsByName("otherDtNotified")[0].focus();
 		}else{
 			if(document.getElementsByName('otherName')[0].value!=""){
 				alert("Date Required, if Name provided for Other.");
 				document.getElementsByName("otherDtNotified")[0].focus();
 				return false;
 			} 
 		}
 		
		if(fosterCareElements[0].checked == true){
			customValRequired ("riskAssesmentDated","Risk Assessment date is required.");
		}
		if(otherdate[0].value !="" && otherdate[0].value != null){
			customValRequired ("explanation","Explanation is required as Other date is entered.");
		}
 		if(validateCustomStrutsBasedJS(theForm))
 		{
 	    	return validateCaseplanUpdateForm(theForm);
 	  	}
 	 	return false; 
         
 }
 
 function juvFosterCareCallback( isFosterCare )
 {
	 if ( isFosterCare != "" ) {
			sessionStorage.setItem("isFosterCare", isFosterCare);
		}
	 if(isFosterCare == ''){
		/* on load method call - empty parameter*/
		/*var isFosterCare = "<bean:write name='caseplanForm' property='juvFosterCareCandidateStr'/>"; */
		var fosterCareElements = document.getElementsByName('juvFosterCareCandidateStr');			
		if(fosterCareElements[0].checked == true){
			isFosterCare = "Yes";
		}else if(fosterCareElements[1].checked == true){
			isFosterCare = "No";
		}
		
	 }
		if( isFosterCare == 'Yes' )
	 	{
		   show( 'riskAssessDt', 1, 'row' ) ; 
		   show( 'psychologicalDt', 1, 'row' ) ; 
		   show( 'socialInvestDt', 1, 'row' ) ; 
		  
		   show( 'otherDt', 1, 'row' ) ;
		   show( 'otherExplain', 1, 'row' ) ;
	 	}
	 	else
	 	{
	 		var raDate = document.getElementsByName('riskAssesmentDated');
	 		raDate[0].value = "";
	 		var prDate = document.getElementsByName('psychologicalRepDated');
	 		prDate[0].value = "";
	 		var shDate = document.getElementsByName('socialHistDated');
	 		shDate[0].value = "";
	 		var otherDate = document.getElementsByName('otherDated');
	 		otherDate[0].value = "";
	 		var explanation = document.getElementsByName('explanation');
	 		explanation[0].value = "";
	 		
	 		show( 'riskAssessDt', 0, 'row' ) ; 	
	 		show( 'psychologicalDt', 0, 'row' ) ; 
			show( 'socialInvestDt', 0, 'row' ) ; 
			
			show( 'otherDt', 0, 'row' ) ;
			show( 'otherExplain', 0, 'row' ) ;
	 	}
	 
 }

</script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="juvFosterCareCallback('');">

<html:form action="/displayCreateGenerateCaseplanConfirmAction" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|170">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Process Caseplan - Generate Caseplan</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter information and select the button to view Caseplan.</li>
			</ul>
		</td>
	</tr>
	<tr id='reqFieldsInstruct'> 
    <td class="required"><bean:message key='prompt.diamond' />&nbsp;Required Fields</td> 
  </tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:notEqual name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
    <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">

	    <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	      <tiles:put name="tabid" value="goalstab" />
	      <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	    </tiles:insert>
	
	  	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  		<tr>
	  			<td valign="top" align="center">
	  			<div class="spacer"></div>
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  				<tr>
	  					<td valign="top">
	  						  <tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
	  								<tiles:put name="tabid" value="GenerateCaseplanDetails" />
	  								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  							</tiles:insert>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
	  				</tr>
	  			</table>
	  
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  					<tr>
	  						<td valign="top" align="center">
</logic:notEqual>

<logic:equal name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width='98%'>
  <tr>
    <td bgcolor='#cccccc'>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> <%--header info end--%>
		</td>
  </tr>
</table>
<%-- END HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign="top">
   		<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%> 
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="goalstab" />
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
					  </tiles:insert> 
            <%--tabs end--%>		
					</td>
				</tr>
				<tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
					  			<td valign="top">
					  				<tiles:insert page="../caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
					  					<tiles:put name="tabid" value="GenerateCaseplanDetails"/>
					  					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
					  				</tiles:insert>				
					  			</td>
					  		</tr>
  						<tr>
		    		  	<td bgcolor='6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
		    			</tr>
					  </table>

					  <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 							<tr>
 								<td valign="top" align="center">
		</logic:equal>	

										<%-- BEGIN Activities TABLE --%>
										<div class='spacer'></div>
										<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td class="detailHead">Generate Caseplan</td>
											</tr>
											<tr>
      											<td colspan='0'>
      												<table align="center" width='100%' cellpadding="4" cellspacing="1">	              											
	              										<tr>
      														<td class='formDeLabel' colspan='2' nowrap='nowrap'>
    	  														<table width="100%">
    		  													<tr>
    			  													<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.priorServices"/> 
      														 		 <logic:notEqual name="caseplanForm" property="action" value="view">
      															  		&nbsp;
                            											<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                            											<tiles:put name="tTextField" value="priorServices" />
                            											<tiles:put name="tSpellCount" value="spellBtn1" />
                          												</tiles:insert>
                          												(Max. characters allowed: 255)
      																</logic:notEqual> 
																	</td>			  											
    			  												</tr>
    	  														</table>
      														</td>
      													</tr>
      													<tr>
      										 				<td class='formDe' colspan='2'>      												   
      															<html:textarea style="width:100%" rows="3" name="caseplanForm" property="priorServices" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>
      														</td>
      										   
      													</tr>
			      										<tr>
															<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.supervisionLevel" /></td>
															<td class='formDe' width="50%">
																<html:select name="caseplanForm" property="supervisionLevelId" size="1" >
				   													<option value="">Please Select</option>
				   													<html:optionsCollection name="caseplanForm" property="supervisionLevelList" value="code" label="description" />
				 												</html:select>
															</td>
														</tr>	
      										
														<tr>
			      											<td class='formDeLabel' colspan='2' nowrap='nowrap'>
			    	  											<table width="100%">
			    		  											<tr>
			    			  											<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.contactInformation"/> 
			      														  <logic:notEqual name="caseplanForm" property="action" value="view">
			      															  &nbsp;
								                            					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                            						<tiles:put name="tTextField" value="contactInformation" />
								                            						<tiles:put name="tSpellCount" value="spellBtn2" />
								                          						</tiles:insert>
								                          						(Max. characters allowed: 255)
			      															</logic:notEqual> 
																		</td>			  											
			    			  										</tr>
			    	  											</table>
			      											</td>
			      										</tr>
			      										<tr>
			      										 	<td class='formDe' colspan='2'>      												   
			      													<html:textarea style="width:100%" rows="3" name="caseplanForm" property="contactInformation" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>      											         												
			      											</td>      										   
			      										</tr>														
													</table>
												</td>
											</tr>
																



<%-- begin Participation in  development of caseplan and distribution--%>
				<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class="detailHead">Participation in  Development of Caseplan and Distribution</td>
						</tr>
						<tr>
							
      						<td colspan='0'>
      						
      						
      						
      						
      							<table align="center" width='100%' >         			
		    						<tr>
		    							<td class='formDeLabel'></td>
		    							<td class='formDeLabel' align="center">Child</td>  
		    							<td class='formDeLabel' align="center">Family</td>
		    							<td class='formDeLabel' align="center">CareGiver</td>
		    							<td class='formDeLabel'>
		    								<table border="0">
		    									<tr>
		    										<td class='formDeLabel' align="right"></td>
		    										<td class='formDeLabel' align="center">Other</td>
		    									</tr>
		    									<tr>
		    										<td class='formDeLabel'>Name</td>
		    										<td class='formDe'>	           					
															<html:text property="othername" size="25" maxlength="75" />
														
		    										 </td>
		    									</tr>
		    								</table>
		    							</td>										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Notified</td>
		    							<td class='formDe' align="left">
		    								<html:text name="caseplanForm" property="childDtNotified" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].childDtNotified,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>											
										</td>  
		    							<td class='formDe' align="left">
												<html:text name="caseplanForm" property="familyDtNotified" maxlength="10" size="15" />
												<a href="#" onClick="cal1.select(document.forms[0].familyDtNotified,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 
		    							<td class='formDe' align="left">
		    									<html:text name="caseplanForm" property="caregiverDtNotified" maxlength="10" size="15" />
												<a href="#" onClick="cal1.select(document.forms[0].caregiverDtNotified,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 
		    							<td class='formDe' align="left">		    							
												<html:text name="caseplanForm" property="otherDtNotified" maxlength="10" size="15" />
												<a href="#" onClick="cal1.select(document.forms[0].otherDtNotified,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 										
										</tr>	
										
										<tr>
		    							<td class='formDeLabel'>Method of Notification</td>
		    							<td class='formDe' align="left">	
		    								<html:text property="childNotifMethod" size="25" maxlength="50" />
										</td>  
		    							<td class='formDe' align="left">		    							
											<html:text property="familyNotifMethod" size="25" maxlength="50" />
										</td> 
		    							<td class='formDe' align="left">
												<html:text property="caregiverNotifMethod" size="25" maxlength="50" />
										</td> 
		    							<td class='formDe' align="left">         					
											<html:text property="otherNameNotifMethod" size="25" maxlength="50" />
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date of Participation</td>
		    							<td class='formDe' align="left">	
		    								<html:text name="caseplanForm" property="childDtOfParticipation" maxlength="10" size="15" />
												<a href="#" onClick="cal1.select(document.forms[0].childDtOfParticipation,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td>  
		    							<td class='formDe' align="left">
		    								<html:text name="caseplanForm" property="familyDtOfParticipation" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].familyDtOfParticipation,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 
		    							<td class='formDe' align="left">
		    								<html:text name="caseplanForm" property="caregiverDtOfParticipation" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].caregiverDtOfParticipation,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 
		    							<td class='formDe' align="left">
		    								<html:text name="caseplanForm" property="otherNameDtOfParticipation" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].otherNameDtOfParticipation,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Copy Received/Mailed</td>
		    							<td class='formDe' align="left">	
		    								<html:text name="caseplanForm" property="childMailedDt" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].childMailedDt,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td>  
		    							<td class='formDe' align="left"> 
		    								<html:text name="caseplanForm" property="familyMailedDt" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].familyMailedDt,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 
		    							<td class='formDe' align="left">
		    								<html:text name="caseplanForm" property="caregiverMailedDt" maxlength="10" size="15" />
											<a href="#" onClick="cal1.select(document.forms[0].caregiverMailedDt,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>											
										</td> 
		    							<td class='formDe' align="left">
												<html:text name="caseplanForm" property="otherNameMailedDt" maxlength="10" size="15" />
												<a href="#" onClick="cal1.select(document.forms[0].otherNameMailedDt,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
										</td> 										
										</tr>							
								</table>
							</td>					
						</tr>
					</table>
<%-- END Participation in  development of caseplan and distribution --%> 

													
													
	<!-- title IV E Start-->							
										
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">Title IV E Candidacy</td>
	</tr>
	<tr>
	
		<td colspan='0'>
			<table align="center" width='100%' cellpadding="4" cellspacing="1">
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.isJuvFosterCareCand"/></td>
					<td class='formDe' width="50%">	
						<bean:message key="prompt.yes" /><html:radio onclick="juvFosterCareCallback('Yes')" styleId='juvFosterCareCandidateStr' property="juvFosterCareCandidateStr" value="Yes"/> 
						<bean:message key="prompt.no"  /><html:radio onclick="juvFosterCareCallback('No')" styleId='juvFosterCareCandidateStr' property="juvFosterCareCandidateStr" value="No"/>
					</td>
				</tr>
				
				<tr id='socialInvestDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.socialInvestHistRepDated"/></td>
					<td class='formDe' width="50%">
						<html:text name="caseplanForm" property="socialHistDated" maxlength="10" size="10" />
						<a href="#" onClick="cal1.select(document.forms[0].socialHistDated,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border="0"><bean:message key="prompt.2.calendar" /></a>
					</td>
				</tr>
								
				<tr id='psychologicalDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.psychological"/>&nbsp;<bean:message key="prompt.report"/>&nbsp;<bean:message key="prompt.dated"/></td>
					<td class='formDe' width="50%">
						<html:text name="caseplanForm" property="psychologicalRepDated" maxlength="10" size="10" />
						<a href="#" onClick="cal1.select(document.forms[0].psychologicalRepDated,'anchor2','MM/dd/yyyy'); return false;" name="anchor2" id="anchor2" border="0"><bean:message key="prompt.2.calendar" /></a>
					</td>
				</tr>
				
				<tr id='riskAssessDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.riskAssessdated"/></td>
					<td class='formDe' width="50%">
						<html:text name="caseplanForm" property="riskAssesmentDated" maxlength="10" size="10" />
						<a href="#" onClick="cal1.select(document.forms[0].riskAssesmentDated,'anchor3','MM/dd/yyyy'); return false;" name="anchor3" id="anchor3" border="0"><bean:message key="prompt.2.calendar" /></a>
					</td>
				</tr>
				
				<tr id='otherDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.other"/></td>
					<td class='formDe' width="50%">
						<html:text name="caseplanForm" styleId='otherDated' property="otherDated" maxlength="10" size="10" />
						<a href="#" onClick="cal1.select(document.forms[0].otherDated,'anchor4','MM/dd/yyyy'); return false;" name="anchor4" id="anchor4" border="0"><bean:message key="prompt.2.calendar" /></a>
					</td>
				</tr>
				
				<tr id='otherExplain' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.explain"/></td>
					<td class='formDe' width="50%">							        					
							<html:text size="50" maxlength="150" property="explanation" />						
					</td>
				</tr>
				
				<tr>
					<td class='formDeLabel' colspan='2' nowrap='nowrap'>
						<table width="100%">
							<tr>
								<td class='formDeLabel'><bean:message key="prompt.comments"/> 
								  <logic:notEqual name="caseplanForm" property="action" value="view">
									  &nbsp;
                						<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                						<tiles:put name="tTextField" value="GenerateCaseplanDetails" />
                						<tiles:put name="tSpellCount" value="spellBtn2" />
              							</tiles:insert>
              							(Max. characters allowed: 3200)
									</logic:notEqual> 
								</td>			  											
							</tr>
							<tr>
								<td>									
									<html:textarea style="width:100%" rows="3" name="caseplanForm" property="titleIVEComment" onmouseout="textCounter(this, 3200);" onkeydown="textCounter( this, 3200 )"/>
								</td>	
							</tr>
						</table>
					</td>
				</tr>
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateDetermMade"/></td>
					<td class='formDe' width="50%">
						<html:text name="caseplanForm" property="dtDeterminationMade" maxlength="10" size="10" />
						<a href="#" onClick="cal1.select(document.forms[0].dtDeterminationMade,'anchor5','MM/dd/yyyy'); return false;" name="anchor5" id="anchor5" border="0"><bean:message key="prompt.2.calendar" /></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class="spacer"></div>										
<!--end of title IV E -->						
													
										</table>
										<%-- END casefile TABLE --%>  
										<%-- BEGIN BUTTON TABLE --%>

										<div class="spacer"></div>
										<table border="0" align="center">
                  	<tr>
                  	
                  		<td align="center">
                  		      <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>    		         		
                    	      <%-- <html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.generateCaseplan"/></html:submit> --%>
						      <html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.next"/></html:submit>
						      <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
						</td>
                  		
                    </tr>
                  </table>
										<div class="spacer"></div>
										<%-- END BUTTON TABLE --%>
									</td>
							</tr>
						</table>
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
			<%-- END DETAIL TABLE --%> <br>
			<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
		</td>
	</tr>
</table>


</html:form>
<div class='spacer'></div>
<logic:present name="statusReport">
  <logic:equal name="statusReport" value="confirmFinal">
    <script type="text/javascript">
      goNav('/<msp:webapp/>handleGenerateCaseplan.do?submitAction=<bean:message key="button.print"/>&selectedValue=<bean:write name="caseplanForm" property="selectedValue"/>');
    </script>
  </logic:equal>
</logic:present>
</body>
</html:html>

