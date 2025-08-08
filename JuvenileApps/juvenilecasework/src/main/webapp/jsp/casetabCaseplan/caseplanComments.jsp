<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 08/04/2009     C Shimek  #61364 added onclick validation call to submit button for action = "requestReview" --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- caseplanComments.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>


<script  type='text/javascript'>

$(document).ready(function(){
	
	var isRecomSupLevel = sessionStorage.getItem("isRecomSupLevel");
	var isJpoMaintain = sessionStorage.getItem("isJpoMaintain");
	
	$("input[name=supLevelApproStr][value="+isRecomSupLevel+"]").prop('checked', true);
	$("input[name=jpoMaintainContactStr][value="+isJpoMaintain+"]").prop('checked', true);
	
	$(function(){
		  $('input[type="radio"]').click(function(){
		    if ($(this).is(':checked'))
		    {
		      var radioName = $(this).attr("name");
		      $("input[name= '"+ radioName + "']").css("box-shadow","none");
		    }
		  });
	});
	
	$("#finishCmd").click(function(){
		sessionStorage.removeItem("isRecomSupLevel");
		sessionStorage.removeItem("isJpoMaintain");
	})
		
	$("#resetBtn").click(function(){
		$("#comments").val("");
		$("#maintainExplain").val("");
		$("#recomSupervisionLevelId").val("");
		$('input[name=supLevelApproStr]').attr('checked',false);
		$('input[name=jpoMaintainContactStr]').attr('checked',false);
	})
})

function validateComments(theForm)
{
	
	clearAllValArrays();
	customValRequired("comments", "Comments are required.");
	addDB2FreeTextValidation("comments", "<bean:message key='errors.comments' arg0='Comments'/>");
	customValMaxLength ("comments", "Comments max length is 255 characters.",255);
	
	
	var recomSupLvlElements = document.getElementsByName('supLevelApproStr');		
	var supLvlElementId = "<bean:write name='caseplanForm' property='supervisionLevelId'/>";	
	var selectedRecomSupElements = document.getElementsByName('recomSupervisionLevelId');	
	var isJPOMaintainContElements = document.getElementsByName('jpoMaintainContactStr');
	var jpoContactExplain = document.getElementsByName('jpoMaintainExplain');
	if(typeof isJPOMaintainContElements[1] != "undefined")
	{
		if(isJPOMaintainContElements[1].checked == true){
			customValRequired("jpoMaintainExplain", "Please Explain as JPO did not contact with the child and family at least monthly.");
			customValMaxLength ("jpoMaintainExplain", "Please Explain can not be more than 255 characters.",255);
		}	
	}
	if(typeof recomSupLvlElements[1] != "undefined")
	{
		if(recomSupLvlElements[1].checked == true){			
			if(selectedRecomSupElements[0].value =="" || selectedRecomSupElements[0].value ==null){			
				alert("Recommended Supervision Level is required.");
				return false;
			}		
			if(supLvlElementId == selectedRecomSupElements[0].value){
				alert("Supervision Level and Recommended Supervision Level can not be similar.")
				return false;
			}
		}
		else if(recomSupLvlElements[0].checked == false){
			alert("Is Current Level of Supervision Still appropriate? Please select.");
			var radioId = recomSupLvlElements[0].id;
        	$("#"+radioId).css("box-shadow","1px 1px 2px 2px grey");
			return false;
		}
	}
	
	return validateCustomStrutsBasedJS(theForm);
}

function trim(textbox) 
{
  if (textbox) 
	{
    while (textbox.value.substring(0,1) == " ") 
		{
      textbox.value = textbox.value.substring(1);
    }
  }
}

function supLevelApproCallback( isRecomSupLevel ){
	if ("" != isRecomSupLevel ) {
		sessionStorage.setItem("isRecomSupLevel", isRecomSupLevel);
	}
	if(isRecomSupLevel == ''){
		/* on load */
		var isRecomSupLevel = "<bean:write name='caseplanForm' property='supLevelApproStr'/>";	
		
		if(isRecomSupLevel == ''){
			/* on load - back */
			var recomSupLvlElements = document.getElementsByName('supLevelApproStr');			
			if(recomSupLvlElements[0].checked == true){
				isRecomSupLevel = "Yes";
			}else if(recomSupLvlElements[1].checked == true){
				isRecomSupLevel = "No";
			}
			/*show editable fields for back button*/
			if( isRecomSupLevel == 'No' )
		 	{
			   show( 'recomSupLv', 1, 'row' ) ; 
			   
		 	}
		 	else
		 	{
		 		show( 'recomSupLv', 0, 'row' ) ; 	 
		 		
		 	}
		} else {	
			/* on load -not back */
			/*show label fields*/
			if(isRecomSupLevel == 'No'){
			   show( 'recomSupLv', 1, 'row' ) ;	 	
		 	}
		 	else
		 	{
		 		show( 'recomSupLv', 0, 'row' ) ; 	 
		 		
		 	}
		}
	}else{
		/*on click of radio button event*/
		if( isRecomSupLevel == 'No' )
	 	{
		   show( 'recomSupLv', 1, 'row' ) ; 
		  
	 	}
	 	else
	 	{
	 		var recomSupervisionLvl = document.getElementsByName('recomSupervisionLevelId');
	 		recomSupervisionLvl[0].value = "";		 		
	 		show( 'recomSupLv', 0, 'row' ) ;  		 		
	 	}
	} 
}

function jpoMaintainContactCallback( isJpoMaintain ){
	if ("" !=isJpoMaintain ) {
		sessionStorage.setItem("isJpoMaintain", isJpoMaintain);
	}
	
	if(isJpoMaintain == ''){
		/* on load */
		var isJpoMaintain = "<bean:write name='caseplanForm' property='jpoMaintainContactStr'/>";	
		
		if(isJpoMaintain == ''){
			/* on load - back */
			var jpoMaintainElements = document.getElementsByName('jpoMaintainContactStr');			
			if(jpoMaintainElements[0].checked == true){
				isJpoMaintain = "Yes";
			}else if(jpoMaintainElements[1].checked == true){
				isJpoMaintain = "No";
			}
			/*show editable fields for back button*/
			if( isJpoMaintain == 'No' )
		 	{
			   show( 'jpoMaintainExplainLebel', 1, 'row' ) ; 
			   show('jpoMaintainExplain', 1, 'row');
			   
		 	}
		 	else
		 	{
		 		show( 'jpoMaintainExplainLebel', 0, 'row' ) ; 
				   show('jpoMaintainExplain', 0, 'row'); 	 
		 		
		 	}
		} else {	
			/* on load -not back */
			/*show label fields*/
			if(isJpoMaintain == 'No'){
				show( 'jpoMaintainExplainLebel', 1, 'row' ) ; 
				show('jpoMaintainExplain', 1, 'row'); 	
		 	}
		 	else
		 	{
		 		show( 'jpoMaintainExplainLebel', 0, 'row' ) ; 
				show('jpoMaintainExplain', 0, 'row');	 
		 		
		 	}
		}
	}else{
		/*on click of radio button event*/
		if( isJpoMaintain == 'No' )
	 	{
			show( 'jpoMaintainExplainLebel', 1, 'row' ) ; 
			show('jpoMaintainExplain', 1, 'row'); 	
		  
	 	}
	 	else
	 	{
	 		show( 'jpoMaintainExplainLebel', 0, 'row' ) ; 
			show('jpoMaintainExplain', 0, 'row');		 		
	 	}
	} 
}


function doAtEnd()
{
  <logic:equal name="caseplanForm" property="action" value="review">
    <logic:present name="status" >
      <logic:equal name="status" value="confirmJPO">
  	goNav('/<msp:webapp/>submitCaseplanComments.do?submitAction=<bean:message key="button.print"/>&selectedValue=<bean:write name="caseplanForm" property="selectedValue"/>');
   </logic:equal>
    </logic:present>
  </logic:equal>
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="supLevelApproCallback('');jpoMaintainContactCallback('');">
<html:form styleId="caseplanForm" action="/displayCaseplanCommentsSummary" target="content">

<logic:notEqual name="caseplanForm" property="action" value="requestReview">
	<logic:equal name="caseplanForm" property="action" value="review">
		<logic:notEqual name="status" value="summary">
    	<logic:notEqual name="status" value="confirm">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|61"> 
	    </logic:notEqual>
		</logic:notEqual>
		<logic:equal name="status" value="summary">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|63"> 
		</logic:equal>
		<logic:equal name="status" value="confirm">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|62"> 
		</logic:equal>
	</logic:equal>
	<logic:notEqual name="caseplanForm" property="action" value="review">	
		<logic:notEqual name="status" value="summary">
    	<logic:notEqual name="status" value="confirm">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|58"> 
	    </logic:notEqual>
		</logic:notEqual>
		<logic:equal name="status" value="summary">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|60"> 
		</logic:equal>
		<logic:equal name="status" value="confirm">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|59"> 
		</logic:equal> 
        <logic:equal name="status" value="confirmJPO">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|59"> 
		</logic:equal> 

	</logic:notEqual>
</logic:notEqual>
<logic:equal name="caseplanForm" property="action" value="requestReview">
	<%-- input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|120"--%>
    <logic:notEqual name="status" value="summary">
    	<logic:notEqual name="status" value="confirm">
		    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|69"> 
	    </logic:notEqual>
		</logic:notEqual>
		<logic:equal name="status" value="summary">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|167"> 
		</logic:equal>
		<logic:equal name="status" value="confirm">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|168"> 
		</logic:equal>
</logic:equal> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan -
  		<logic:notEqual name="caseplanForm" property="action" value="requestReview">
  			<logic:equal name="caseplanForm" property="action" value="review"><bean:message key="prompt.casePlan"/> Review</logic:equal>
  			<logic:equal name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS"> <bean:message key="prompt.clm"/> <bean:message key="prompt.review"/> - <bean:message key="prompt.rejection"/> <bean:message key="prompt.comments"/></logic:equal>

	 			<logic:notEqual name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">	
	 				<bean:message key="prompt.casePlan"/> <bean:message key="prompt.comments"/>
	 			</logic:notEqual>
    	</logic:notEqual>  		

  		<logic:equal name="caseplanForm" property="action" value="requestReview">Create Request to Review Caseplan</logic:equal>

  		<logic:equal name="status" value="summary"><bean:message key="prompt.summary"/></logic:equal>
  		<logic:equal name="status" value="confirm"><bean:message key="prompt.confirmation"/></logic:equal>
      <logic:equal name="status" value="confirmJPO"><bean:message key="prompt.confirmation"/></logic:equal>
  	</td>
  </tr>

  <logic:equal name="status" value="confirm">
		<tr id='confMessage'><td align='center' class='confirm'>Submitted for Review. Notification sent.</td></tr>
  </logic:equal>
  <logic:equal name="status" value="confirmCLM">
		<tr id='confMessage'><td align='center' class='confirm'>Caseplan review has been saved and Notification sent.</td></tr>
  </logic:equal>
  <logic:equal name="status" value="confirmJPO">
		<tr id='confMessage'><td align='center' class='confirm'>JPO Review successfully completed.</td></tr>
  </logic:equal>
</table>
<%-- END HEADING TABLE --%>

<logic:messagesPresent>
<div class='spacer'></div>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors /></td>		  
	</tr>   	  
</table>
<div class='spacer'></div>
</logic:messagesPresent> 

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<logic:empty name="status">
					<logic:equal name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
						<li>Enter Comments, then select the Submit button to view the Summary.</li>
					</logic:equal> 
					<logic:notEqual name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
						<logic:notEqual name="caseplanForm" property="action" value="requestReview">
							<li>Enter comments and select Next button to view Summary.</li>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="action" value="requestReview">
							<li>Enter comments and select Submit button to view Summary.</li>
						</logic:equal>
					</logic:notEqual>
				</logic:empty>
										
				<logic:equal name="status" value="confirmCLM">
					<li>Select the Notifications button to view the Notifications screen.</li>
				</logic:equal>
				
				<logic:equal name="status" value="summary">
					<li>Review comments entered and select Finish button to save comments.</li>
					<li>To change comments, select the Back button.</li>
				</logic:equal>
		
			</ul>
		</td>
	</tr>

	<logic:equal name="status" value="">
  	<tr id='reqFieldsInstruct'> 
  		<td class="required"><bean:message key="prompt.diamond" />&nbsp;Required Fields</td> 
  	</tr> 
	</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign='top'>

  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
					
					  <div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  						<tr>
  							<td valign='top'>
    							<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
					  		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
					  	</tr>
					  </table>

      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      				<tr>
      					<td valign='top' align='center'>
      
        					<div class='spacer'></div>						
      						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      							<tr>
      								<td class='detailHead' colspan='2' nowrap='nowrap'>
      									<logic:equal name="caseplanForm" property="action" value="requestReview">Request to Review Caseplan</logic:equal>
      									<logic:notEqual name="caseplanForm" property="action" value="requestReview"><bean:message key="prompt.casePlan"/>
      										<logic:equal name="caseplanForm" property="action" value="review">Review</logic:equal>
        									<bean:message key="prompt.comments" /> 
        								</logic:notEqual>
      								</td>
      							</tr>
      							
      							<tr>
      								<td>						  			
      			  					<table width='100%' cellpadding='2' cellspacing='1'>	    			  					
      			  					
      			  					
      			  					<tr><logic:equal name="caseplanForm" property="action" value="review">  
											<td class='formDeLabel' width="50%"><bean:message key="prompt.jpoMaintainContactStr"/></td>
											<td class='formDe' width="50%">
												<logic:notEmpty name="status">										
													<logic:equal name="caseplanForm" property="jpoMaintainContactStr" value="Yes"> Yes</logic:equal>
													<logic:equal name="caseplanForm" property="jpoMaintainContactStr" value="No"> No</logic:equal>							
												</logic:notEmpty>
												<logic:empty name="status">							               					
													<bean:message key="prompt.yes" /><html:radio onclick="jpoMaintainContactCallback('Yes')" styleId='jpoMaintainContactStr' property="jpoMaintainContactStr" value="Yes"/> 
													<bean:message key="prompt.no"  /><html:radio onclick="jpoMaintainContactCallback('No')" styleId='jpoMaintainContactStr' property="jpoMaintainContactStr" value="No"/>			
												</logic:empty>
											</td></logic:equal>
									</tr>
									<tr id='jpoMaintainExplainLebel' class='hidden'><logic:equal name="caseplanForm" property="action" value="review"> 		
      			  							<td class="formDeLabel" colspan="2">
   												<logic:empty name="status"><bean:message key='prompt.diamond' /></logic:empty>Please Explain
   												<logic:empty name="status">
        											&nbsp;
	                      						<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
	                      						<tiles:put name="tTextField" value="jpoMaintainExplain"/>
	                      						<tiles:put name="tSpellCount" value="spellBtn1" />
	                    						</tiles:insert> 
													(Max. characters allowed: 255)
												</logic:empty>
  											</td></logic:equal>
      			  						</tr>
      			  						<tr id='jpoMaintainExplain' class='hidden'><logic:equal name="caseplanForm" property="action" value="review">
      			  							<td class="formDe" colspan="2">
      												<logic:empty name="status">
														<html:textarea styleId= "maintainExplain" rows="4" style="width:100%" name="caseplanForm" property="jpoMaintainExplain" onmouseout="textCounter(this,255)" onkeyup="textCounter(this,255)"></html:textarea>
													</logic:empty>
      												<logic:notEmpty name="status">
      													<bean:write name="caseplanForm" property="jpoMaintainExplain"/>
      												</logic:notEmpty>
      											</td></logic:equal>					
        	  							</tr>	
	      			  					<tr> <logic:equal name="caseplanForm" property="action" value="review"> 
											<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionLevel"/></td>
											<td class='formDe' width="50%">
												<bean:write name="caseplanForm" property="supervisionLevel"/>
											</td></logic:equal>
										</tr>
								
      			  						<tr><logic:equal name="caseplanForm" property="action" value="review">  
											<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.SupervisionLvlAppro"/></td>
											<td class='formDe' width="50%">
												<logic:notEmpty name="status">										
													<logic:equal name="caseplanForm" property="supLevelApproStr" value="Yes"> Yes</logic:equal>
													<logic:equal name="caseplanForm" property="supLevelApproStr" value="No"> No</logic:equal>							
												</logic:notEmpty>
												<logic:empty name="status">							               					
													<bean:message key="prompt.yes" /><html:radio onclick="supLevelApproCallback('Yes')" styleId='supLevelApproStrYes' property="supLevelApproStr" value="Yes"/> 
													<bean:message key="prompt.no"  /><html:radio onclick="supLevelApproCallback('No')" styleId='supLevelApproStrNo' property="supLevelApproStr" value="No"/>			
												</logic:empty>
											</td></logic:equal>
										</tr>
								
										<tr id='recomSupLv' class='hidden'><logic:equal name="caseplanForm" property="action" value="review">  
											<td class='formDeLabel' width="50%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.recSupervisionLevel"/></td>
											<td class='formDe' width="50%">
												<logic:notEmpty name="status">
													<bean:write name="caseplanForm" property="recomSupervisionLevel"/>
												</logic:notEmpty>
												<logic:empty name="status">                    									
													<html:select name="caseplanForm" property="recomSupervisionLevelId" styleId="recomSupervisionLevelId" size="1" >
   													<option value="">Please Select</option>
   													<html:optionsCollection name="caseplanForm" property="supervisionLevelList" value="code" label="description" />
 												    </html:select>													
											</logic:empty>
											</td></logic:equal>
										</tr>
															
      			  					<!-- ended -->
      			  					
      			  					
      			  						
      			  						<tr>		
      			  							<td class="formDeLabel" colspan="2">
      												<logic:empty name="status"><bean:message key='prompt.diamond' /></logic:empty>Comments
      												<logic:empty name="status">
          											&nbsp;
                      					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
                      						<tiles:put name="tTextField" value="comments"/>
                      						<tiles:put name="tSpellCount" value="spellBtn1" />
                    						</tiles:insert> 
																(Max. characters allowed: 255)
															</logic:empty>
  													</td>
      			  						</tr>	
      			  						<tr>
      			  							<td class="formDe" colspan="2">
      												<logic:empty name="status">
															  <html:textarea styleId="comments" rows="4" style="width:100%" name="caseplanForm" property="comments" onmouseout="textCounter(this,255)" onkeyup="textCounter(this,255)"></html:textarea>
															</logic:empty>

      												<logic:notEmpty name="status">
      													<bean:write name="caseplanForm" property="comments"/>
      												</logic:notEmpty>
      											</td>					
        	  							</tr>
        	  						</table>
      								</td>
      							</tr>
      						</table>

                  <%-- BEGIN BUTTON TABLE --%>
                  <div class='spacer'></div>
      						<table width="100%">
      							<tr>
      								<td align="center">
      									<%-- this is the JPO login requesting a review (entering comments) by the CLM --%>
      									<logic:empty name="status">
	      									<logic:equal name="caseplanForm" property="action" value="requestReview">
	      										<html:submit property="submitAction" onclick="return validateComments(this.form);"><bean:message key="button.submit"/></html:submit>
	      										<html:reset><bean:message key="button.reset"></bean:message></html:reset>
	      										
	      										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	      									</logic:equal>
	      								</logic:empty>

      									<logic:equal name="status" value="summary">
      										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      										<!--<input type="button" 
      												onclick="changeFormActionURL('caseplanForm',
      														 '/<msp:webapp/>displayCaseplanComments.do?submitAction=Next', true);"
      												value="Back"/>-->
      										<input id="finishCmd" type="submit" name="submitAction" value="<bean:message key='button.finish'/>" 
      											onclick="changeFormActionURL('/<msp:webapp/>submitCaseplanComments.do', false);">
      										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      									</logic:equal>
      									<logic:equal name="status" value="confirm">
     										<logic:equal name="caseplanForm" property="action" value="review">
     											<input type="button" name="submitAction" value="<bean:message key='button.generateCaseplan'/>" disabled='disabled'>					
     										</logic:equal>
      										
     										<input type="button" name="submitAction" value="<bean:message key='button.caseplanDetails'/>" 
      											onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do?submitAction=Link', true);">
      									</logic:equal>
      									<logic:equal name="status" value="confirmCLM">
      									 	<table width="100%" >  
				                 		<tr>
			                  			<td align="center">
				                   		  <html:submit property="submitAction"><bean:message key="button.notification"/></html:submit>
		                  			  </td>
						                </tr>
						              </table>
      									 </logic:equal> 
      									 
      
                        <logic:equal name="status" value="confirmJPO">
      										<logic:equal name="caseplanForm" property="action" value="review">
      											<input type="button" name="submitAction" value="<bean:message key='button.generateJPOReview'/>" onclick="goNav('/<msp:webapp/>submitCaseplanComments.do?submitAction=<bean:message key="button.print"/>&selectedValue=<bean:write name="caseplanForm" property="selectedValue"/>');">					
      										</logic:equal>
      										
      										<input type="button" name="submitAction" value="<bean:message key='button.caseplanDetails'/>" 
      											onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do?submitAction=Link', true);">
      									</logic:equal>
      
      									<logic:empty name="status">
	      									<logic:notEqual name="caseplanForm" property="action" value="requestReview">
	      										<logic:equal name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
	      											<html:submit property="submitAction" onclick="return validateComments(this.form);"><bean:message key="button.submit"/></html:submit>
	      										</logic:equal>
	
	      										<logic:notEqual name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
	      											<html:submit property="submitAction" onclick="return validateComments(this.form);"><bean:message key="button.next"/></html:submit>
	      										</logic:notEqual>
	
	      										<!--<html:reset><bean:message key="button.reset"></bean:message></html:reset>-->
	      										<button type="button" id="resetBtn">Reset</button>
	      										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	      									</logic:notEqual>
      									</logic:empty>
      								</td>
      							</tr>
      						</table>
      						<div class='spacer'></div>
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

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
