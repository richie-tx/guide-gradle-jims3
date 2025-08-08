<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 C Shimek                #61004 added timeout.js  --%>

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casetabCaseplan/caseplan.js"></script>

<title><bean:message key="title.heading" /> - generateCaseplanDetails.jsp</title>
<%--END HEADER TAG--%>

 <script type='text/javascript'>
 $(document).ready(function(){
	 var isFosterCare = sessionStorage.getItem("isFosterCare");
	 $("input[name=juvFosterCareCandidateStr][value="+isFosterCare+"]").prop('checked', true);
	 
	 $(function(){
		  $('input[type="radio"]').click(function(){
		    if ($(this).is(':checked'))
		    {
		      var radioName = $(this).attr("name");
		      $("input[name= '"+ radioName + "']").css("box-shadow","none");
		    }
		  });
	});
	 
	 $("#submitCaseplan").click(function(){
		 sessionStorage.removeItem("isFosterCare");
	 })
 })
 var cal1 = new CalendarPopup();
 cal1.showYearNavigation();
 function validateFields(theForm)
{	
	
	clearAllValArrays();
	/* on load : based on whether all fields need to be entered or only title IV section needs to be*/
		var priorServices = document.getElementsByName('priorServices');
		var SupervisionLevel = document.getElementsByName('supervisionLevelId');
		var contactInfo = document.getElementsByName('contactInformation');		
		if(priorServices[0].value == ""){	
			 alert("Prior Service is required.");
			 priorServices[0].focus();
			 return false;
			//customValRequired ("priorServices","Prior Service is required."); 
			
		}
		
		if(SupervisionLevel[0].value == ""){	
			alert("Supervision level is required.");
			SupervisionLevel[0].focus();
			return false;
			//customValRequired ("supervisionLevelId","Supervision level is required.","");			
		}
		if(contactInfo[0].value == ""){	
			alert("Contact Information is required.");
			contactInfo[0].focus();
			return false;
			//customValRequired ("contactInformation","Contact Information is required.","");
			
		}		
		
		//customValRequired ("dtDeterminationMade","Determination date is required.");
		var fosterCareElements = document.getElementsByName('juvFosterCareCandidateStr');
		var otherdate = document.getElementsByName('otherDated');
 		var explanation = document.getElementsByName('explanation');
 		//bug 11144
 		var otherDtNotified = document.getElementsByName('otherDtNotified');//date notified
 		var otherNameMailedDt = document.getElementsByName('otherNameMailedDt'); //date of copy/mailed notified
 		var otherNameDtOfParticipation = document.getElementsByName('otherNameDtOfParticipation'); //date of participation 
 		
 		if(otherDtNotified[0].value!="" || otherNameMailedDt[0].value!="" || 
 				otherNameDtOfParticipation[0].value!=""){ //bug fix 22292 	
 			if(document.getElementsByName('othername')[0].value=="")
 			{
	 			alert("Name required if date provided for Other.");
	 			//customValRequired ("othername","Name required if date provided for Other.");
	 			document.getElementsByName("othername")[0].focus();
	 			return false;
 			}
 		}else{
 			if(document.getElementsByName('othername')[0].value!=""){ 			
 				alert("Date Required, if Name provided for Other.");
 				document.getElementsByName("otherDtNotified")[0].focus();
 				return false;
 			} 
 		}
 		
		if(fosterCareElements[0].checked == true){			
			if(document.getElementById("riskAssesmentDated").value == "")
			{
				alert("Risk Assessment date is required.");
				document.getElementById("riskAssesmentDated").focus();
				return false;
			}
			//customValRequired ("riskAssesmentDated","Risk Assessment date is required.");
		}else if(fosterCareElements[1].checked == false){
			var radioId = fosterCareElements[0].id;
        	$("#"+radioId).css("box-shadow","1px 1px 2px 2px grey");
			alert("Is Juvenile a Foster Care Candidate? Please select.");
			 return false;
		}
		
		if(otherdate[0].value !="" && otherdate[0].value != null){
			if(document.getElementsByName('explanation')[0].value == "")
			{
				alert("Explanation is required as Other date is entered.");
				document.getElementsByName('explanation')[0].focus();
				return false;
			}
			//customValRequired ("explanation","Explanation is required as Other date is entered.");			
		}
		if(document.getElementById("dtDeterminationMade").value == "")
		{
			alert("Determination date is required.");
			document.getElementById("dtDeterminationMade").focus();
			return false;
		}
		if(validateCustomStrutsBasedJS(theForm))
		{			
	    	return validateCaseplanUpdateForm(theForm);
	  	}	
		
	 	return false; 
        
} 
 
 function setClicked()
 {			
 	radioChecked = true;
 }

 function juvFosterCareCallback( isFosterCare )
 {
	if ( isFosterCare != "" ) {
		sessionStorage.setItem("isFosterCare", isFosterCare);
	}
	if(isFosterCare == ''){
		/* on load */
		var isFosterCare = "<bean:write name='caseplanForm' property='juvFosterCareCandidateStr'/>";				
		if(isFosterCare == ''){
			/* on load - back */
			var fosterCareElements = document.getElementsByName('juvFosterCareCandidateStr');			
			if(fosterCareElements[0].checked == true){
				isFosterCare = "Yes";
			}else if(fosterCareElements[1].checked == true){
				isFosterCare = "No";
			}
			/*show editable fields for back button*/
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
		 		show( 'riskAssessDt', 0, 'row' ) ; 	 
		 		show( 'psychologicalDt', 0, 'row' ) ; 
		 		show( 'socialInvestDt', 0, 'row' ) ; 
		 		
		 		show( 'otherDt', 0, 'row' ) ;
		 		show( 'otherExplain', 0, 'row' ) ;
		 	}
		} else {	
			/* on load -not back */
			/*show label fields*/
			if(isFosterCare == 'Yes'){
			   show( 'riskAssessDtView', 1, 'row' ) ;			 			 
			   show( 'psychologicalDtView', 1, 'row' ) ; 
			   show( 'socialInvestDtView', 1, 'row' ) ; 
			   
			   show( 'otherDtView', 1, 'row' ) ;
			   show( 'otherExplainView', 1, 'row' ) ;
		 	}
		 	else
		 	{
		 		show( 'riskAssessDtView', 0, 'row' ) ; 	 
		 		show( 'psychologicalDtView', 0, 'row' ) ; 
		 		show( 'socialInvestDtView', 0, 'row' ) ; 
		 		
		 		show( 'otherDtView', 0, 'row' ) ;
		 		show( 'otherExplainView', 0, 'row' ) ;
		 	}
		}
	}else{
		/*on click of radio button event*/
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
 }
</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="juvFosterCareCallback('');">
<html:form action="/handleCaseplan" target="content">
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
							<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
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
    			  								<tr>
    			  									<td>
    			  									<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
                  									<logic:empty name="caseplanForm" property="priorServices">                    											
    			  										<html:textarea style="width:100%" rows="3" name="caseplanForm" property="priorServices" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>
    			  									</logic:empty>    			  												
    			  									</logic:equal>
    			  									</td>	
    			  								</tr>
    	  									</table>
      									</td>
      								</tr>
      								<tr>
      									<td class='formDe' colspan='2'>      												 
      									<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
      										<bean:write name="caseplanForm" property="priorServices"/>
      									</logic:notEqual> 
      									</td>      										   
      								</tr>
      										
      								<tr>  
										<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.supervisionLevel"/></td>
										<td class='formDe' width="50%">
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="supervisionLevel"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
	                  						<logic:empty name="caseplanForm" property="priorServices">
	                    					<logic:empty name="caseplanForm" property="contactInformation">                    									
												<html:select name="caseplanForm" property="supervisionLevelId" size="1" >
		   										<option value="">Please Select</option>
		   										<html:optionsCollection name="caseplanForm" property="supervisionLevelList" value="code" label="description" />
		 										</html:select>													
											</logic:empty>
											</logic:empty>
											</logic:equal>
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
                            							<tiles:put name="tTextField" value="GenerateCaseplanDetails" />
                            							<tiles:put name="tSpellCount" value="spellBtn2" />
                          								</tiles:insert>
                          									(Max. characters allowed: 255)
      													</logic:notEqual> 
													</td>			  											
    			  								</tr>
    			  								<tr>
    			  									<td>
    			  										<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">                  												
                    									<logic:empty name="caseplanForm" property="contactInformation">                    											
    			  											<html:textarea style="width:100%" rows="3" name="caseplanForm" property="contactInformation" onmouseout="textCounter(this, 255);" onkeydown="textCounter( this, 255 )"/>
    			  										</logic:empty>    			  												
    			  										</logic:equal>
    			  									</td>	
    			  								</tr>
    	  									</table>
      									</td>
      								</tr>
      								<tr>
      									<td class='formDe' colspan='2'>      												 
      										<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
      											<bean:write name="caseplanForm" property="contactInformation"/>
      										</logic:notEqual>  
      									</td>      										   
      								</tr>																		
								</table>
							</td>														
							</logic:equal>
							<logic:equal name="caseplanForm" property="caseplanExist" value="N">
								<td class="formDeLabel" colspan='4'>No Caseplan available for this case</td> 
							</logic:equal>
						</tr>
					</table>
<%-- END casefile TABLE --%>



<%-- begin Participation in  development of caseplan and distribution--%>
				<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class="detailHead">Participation in  Development of Caseplan and Distribution</td>
						</tr>
						<tr>
							<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
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
			    										 <logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
															<bean:write name="caseplanForm" property="othername"/>
														</logic:notEqual>
														<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
														<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
															<html:text property="othername" size="25" maxlength="75" styleId="othername"/>
														</logic:empty>
														</logic:equal>
		    										 </td>
		    									</tr>
		    								</table>
		    							</td>										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Notified</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childDtNotified"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text name="caseplanForm" property="childDtNotified" maxlength="10" size="15" styleId="childDtNotified"/>
												
											</logic:empty>
											</logic:equal>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyDtNotified"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text name="caseplanForm" property="familyDtNotified" maxlength="10" size="15" styleId="familyDtNotified"/>
												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverDtNotified"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text name="caseplanForm" property="caregiverDtNotified" maxlength="10" size="15" styleId="caregiverDtNotified"/>												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherDtNotified"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text name="caseplanForm" property="otherDtNotified" maxlength="10" size="15" styleId="otherDtNotified"/>												
											</logic:empty>
											</logic:equal>
										</td> 										
										</tr>	
										
										<tr>
		    							<td class='formDeLabel'>Method of Notification</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childNotifMethod"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text property="childNotifMethod" size="25" maxlength="50" />
											</logic:empty>
											</logic:equal>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyNotifMethod"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text property="familyNotifMethod" size="25" maxlength="50" />
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverNotifMethod"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">        					
												<html:text property="caregiverNotifMethod" size="25" maxlength="50" />
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameNotifMethod"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text property="otherNameNotifMethod" size="25" maxlength="50" />
											</logic:empty>
											</logic:equal>
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date of Participation</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childDtOfParticipation"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">  
											<logic:empty name="caseplanForm" property="dtDeterminationMade">        					
												<html:text name="caseplanForm" property="childDtOfParticipation" maxlength="10" size="15" styleId="childDtOfParticipation"/>												
											</logic:empty>
											</logic:equal>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyDtOfParticipation"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text name="caseplanForm" property="familyDtOfParticipation" maxlength="10" size="15" styleId="familyDtOfParticipation"/>												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverDtOfParticipation"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text name="caseplanForm" property="caregiverDtOfParticipation" maxlength="10" size="15" styleId="caregiverDtOfParticipation"/>												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameDtOfParticipation"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text name="caseplanForm" property="otherNameDtOfParticipation" maxlength="10" size="15" styleId="otherNameDtOfParticipation"/>												
											</logic:empty>
											</logic:equal>
										</td> 										
										</tr>
										
										<tr>
		    							<td class='formDeLabel'>Date Copy Received/Mailed</td>
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="childMailedDt"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
											<logic:empty name="caseplanForm" property="dtDeterminationMade">         					
												<html:text name="caseplanForm" property="childMailedDt" maxlength="10" size="15" styleId="childMailedDt"/>												
											</logic:empty>
											</logic:equal>
										</td>  
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="familyMailedDt"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text name="caseplanForm" property="familyMailedDt" maxlength="10" size="15" styleId="familyMailedDt"/>												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="caregiverMailedDt"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">  
											<logic:empty name="caseplanForm" property="dtDeterminationMade">        					
												<html:text name="caseplanForm" property="caregiverMailedDt" maxlength="10" size="15" styleId="caregiverMailedDt"/>												
											</logic:empty>
											</logic:equal>
										</td> 
		    							<td class='formDe' align="left">		    							
											<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
												<bean:write name="caseplanForm" property="otherNameMailedDt"/>
											</logic:notEqual>
											<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
											<logic:empty name="caseplanForm" property="dtDeterminationMade">          					
												<html:text name="caseplanForm" property="otherNameMailedDt" maxlength="10" size="15" styleId="otherNameMailedDt"/>												
											</logic:empty>
											</logic:equal>
										</td> 										
										</tr>							
								</table>
							</td>														
							</logic:equal>
							<logic:equal name="caseplanForm" property="caseplanExist" value="N">
								<td class="formDeLabel" colspan='4'>No Caseplan available for this case</td> 
							</logic:equal>
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
	<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
		<td colspan='0'>
			<table align="center" width='100%' cellpadding="4" cellspacing="1">
				<tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.isJuvFosterCareCand"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">												
								<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="Yes"> Yes</logic:equal>
								<logic:equal name="caseplanForm" property="juvFosterCareCandidateStr" value="No"> No</logic:equal>							
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
               				<logic:empty name="caseplanForm" property="dtDeterminationMade">
               					<!--<div>Yes<nested:radio property="juvFosterCareCandidateStr" value="Yes" onclick="juvFosterCareCallback('Yes')"/></div>
							<div>No<nested:radio property="juvFosterCareCandidateStr"  value="No" onclick="juvFosterCareCallback('No')"/></div>
							     -->
							<bean:message key="prompt.yes" /><html:radio onclick="juvFosterCareCallback('Yes')" styleId='juvFosterCareCandidateStr1' property="juvFosterCareCandidateStr" value="Yes"/> 
							<bean:message key="prompt.no"  /><html:radio onclick="juvFosterCareCallback('No')" styleId='juvFosterCareCandidateStr2' property="juvFosterCareCandidateStr" value="No"/>			
							</logic:empty>
						</logic:equal>
					</td>
				</tr>
				
				<tr id='socialInvestDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.socialInvestHistRepDated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="socialHistDated"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">           					
							<html:text name="caseplanForm" property="socialHistDated" maxlength="10" size="15" styleId="socialHistDated"/>						
						</logic:equal>
					</td>
				</tr>	
				<tr id='socialInvestDtView' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.socialInvestHistRepDated"/></td>
					<td class='formDe' width="50%">						
							<bean:write name="caseplanForm" property="socialHistDated"/>											
					</td>
				</tr>			
				<tr id='psychologicalDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.psychological"/>&nbsp;<bean:message key="prompt.report"/>&nbsp;<bean:message key="prompt.dated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="psychologicalRepDated"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
						    <html:text name="caseplanForm" property="psychologicalRepDated" maxlength="10" size="15" styleId="psychologicalRepDated"/>							
						</logic:equal>
					</td>
				</tr>
				<tr id='psychologicalDtView' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.psychological"/>&nbsp;<bean:message key="prompt.report"/>&nbsp;<bean:message key="prompt.dated"/></td>
					<td class='formDe' width="50%">
							<bean:write name="caseplanForm" property="psychologicalRepDated"/>											
					</td>
				</tr>
				<tr id='riskAssessDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.riskAssessdated"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="riskAssesmentDated"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">          					
							<html:text name="caseplanForm" property="riskAssesmentDated" maxlength="10" size="15" styleId="riskAssesmentDated" styleId="riskAssesmentDated"/>							
						</logic:equal>
					</td>
				</tr>
				
				<tr id='riskAssessDtView' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.riskAssessdated"/></td>
					<td class='formDe' width="50%">						
							<bean:write name="caseplanForm" property="riskAssesmentDated"/>
					</td>
				</tr>
				
				<tr id='otherDt' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.other"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="otherDated"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">           					
							<html:text name="caseplanForm" property="otherDated" maxlength="10" size="15" styleId="otherDated"/>							
						</logic:equal>
					</td>
				</tr>
				<tr id='otherDtView' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.other"/></td>
					<td class='formDe' width="50%">					
							<bean:write name="caseplanForm" property="otherDated"/>											
					</td>
				</tr>
				
				<tr id='otherExplain' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.explain"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="explanation"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">          					
							<html:text size="50" maxlength="150" property="explanation" />
						</logic:equal>
					</td>
				</tr>
				
				<tr id='otherExplainView' class='hidden'>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.explain"/></td>
					<td class='formDe' width="50%">						
							<bean:write name="caseplanForm" property="explanation"/>
					</td>
				</tr>
				
				
				<tr>
					<td class='formDeLabel' colspan='2' nowrap='nowrap'>
						<table width="100%">
							<tr>
								<td class='formDeLabel'><bean:message key="prompt.comments"/> 
								  <logic:notEqual name="caseplanForm" property="action" value="view">
								  		<logic:empty name="caseplanForm" property="dtDeterminationMade">
									  		&nbsp;
	                						<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
	                						<tiles:put name="tTextField" value="GenerateCaseplanDetails" />
	                						<tiles:put name="tSpellCount" value="spellBtn2" />
	              							</tiles:insert>
	              							(Max. characters allowed: 3200)
              							</logic:empty>
									</logic:notEqual> 
								</td>			  											
							</tr>
							<tr>
								<td>
									<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
									 <logic:empty name="caseplanForm" property="dtDeterminationMade">    										
										<html:textarea style="width:100%" rows="3" name="caseplanForm" property="titleIVEComment" onmouseout="textCounter(this, 3200);" onkeydown="textCounter( this, 3200 )"/>
									</logic:empty>
									</logic:equal>
									
								</td>	
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
				 <td class='formDe' colspan='2'>
					 <logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
					<bean:write name="caseplanForm" property="titleIVEComment"/>
					</logic:notEqual>
					</td>
				</tr>

				<!--<tr>
					 <td class='formDe' colspan='2'>							 
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="titleIVEComment"/>
						</logic:notEqual>  
					</td>					   
				</tr>
				--><tr>  
					<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateDetermMade"/></td>
					<td class='formDe' width="50%">
						<logic:notEqual name="caseplanForm" property="caseplanInfoEditable" value="true">
							<bean:write name="caseplanForm" property="dtDeterminationMade"/>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
           					<logic:empty name="caseplanForm" property="dtDeterminationMade">
								<html:text name="caseplanForm" property="dtDeterminationMade" maxlength="10" size="15" styleId="dtDeterminationMade"/>								
							</logic:empty>
						</logic:equal>
					</td>
				</tr>
			</table>
		</td>
	</logic:equal>	
	<logic:equal name="caseplanForm" property="caseplanExist" value="N">
		<td class="formDeLabel" colspan='4'>No Caseplan available for this case</td> 		
	</logic:equal>
	</tr>
</table>
<div class="spacer"></div>										
<!--end of title IV E -->					
										
										
										
										
										
										
										
										

			<div class="spacer"></div>
				<table border="0" align="center">
                  	<tr>
                  	<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
                  	<td align="center">
                  	
                  		<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb"> 
                  		<logic:notEmpty name="caseplanForm" property="priorServices">
                    	<logic:notEmpty name="caseplanForm" property="contactInformation"> 
                    	<logic:notEmpty name="caseplanForm" property="dtDeterminationMade">
                  		<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
                  		</logic:notEmpty>
                  		</logic:notEmpty>
                  		</logic:notEmpty>
                  		</logic:equal>
                  		</td>
                  		<td align="center"> 
                  		 <%-- <html:submit property="submitAction"><bean:message key="button.generateCaseplan"/></html:submit>  --%>
                  		 <logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">         		
                    	 <logic:notEmpty name="caseplanForm" property="priorServices">
                    	<logic:notEmpty name="caseplanForm" property="contactInformation">
                    	<logic:notEmpty name="caseplanForm" property="dtDeterminationMade">                    	
							<html:submit property="submitAction"><bean:message key="button.generateCaseplan"/></html:submit>
						</logic:notEmpty>
						</logic:notEmpty>
						</logic:notEmpty>
						</logic:equal> 
                  		</td>
                  		<td align="center">
                  		<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="fromDb">
                  		<logic:empty name="caseplanForm" property="priorServices">                  			                 		
                  			<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.nextFrmTab"/></html:submit>
                  			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                  		</logic:empty>
                  		<logic:notEmpty name="caseplanForm" property="priorServices">
	                    	<logic:empty name="caseplanForm" property="contactInformation">	                    		                		
	                  			<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.nextFrmTab"/></html:submit>
	                  			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
	                  		</logic:empty>
                  			<logic:notEmpty name="caseplanForm" property="contactInformation">                    	
		                    	<logic:empty name="caseplanForm" property="dtDeterminationMade">		                  			                 		
		                  			<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.nextFrmTab"/></html:submit>
		                  			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
		                  		</logic:empty>                  		
                  			</logic:notEmpty>
                  		</logic:notEmpty>
                  		</logic:equal>
                  		</td>
                  		<td align="center">
                  		<logic:equal name="caseplanForm" property="caseplanInfoFrmDb" value="notDb">
                  		<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
                  		<html:submit styleId="submitCaseplan" property="submitAction"><bean:message key="button.submitCaseplan"/></html:submit>
                  		<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                  		</logic:equal>
                  		</td>
                  		</logic:equal>
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

