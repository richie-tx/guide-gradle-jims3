<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/13/2005		DWilliamson	Create Risk Analysis jsp--%>
<%-- 12/16/2005		UGopinath   Added Validations for Radio buttons--%>
<%-- 07/20/2009     CShimek     #61004 added timeout.js  --%>
<%-- 03/31/2011		DGibler	#69839 MJCW: Detention Risk - Remove Aggregate Score --%>
<%-- 04/14/2011     CShimek		#70051 added onload scripting to correct issue with radio buttons --%>
<%-- 04/21/2011     CShimek		#70118 revised onload function to work with new showHide radio buttons w/o Ids	--%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/c.tld" prefix="c" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm" %>
<%@ page import="java.awt.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="messaging.juvenilecase.reply.RiskQuestionResponseEvent" %>



<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
 
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<html:base />

<title>juvenilecasework/riskAssessment/newReferralRA.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript'>
$(function() { 
	$("input[id*='formElementDate']").each(function(){	 
		datePickerSingle($(this),'Entered Date',false);	      
	});
 });

function initializeRadioSelects()
{
	var str1 = "";
	var str2 = "";
	var str3 = "";
	var strElements = "";
	var otherElement = "";  // element id that needs to be hidden or disabled
	var showHIdeElement = ""; //  Y when element needs to be hidden
	var showHIdeRow = ""; //  row Id to show or hide	
	var enableElement = ""  // Y when Yes is selected
	for (x=0; x<document.forms[0].length; x++)
	{
		if (document.forms[0].elements[x].type == 'radio')
		{
			if (document.forms[0].elements[x].checked == true)
			{	
				val = document.forms[0].elements[x].value;
				otherElement = "";
				showHIdeElement = "N";
				enableElement = "N"  
				showHIdeRow = "";	
				if (document.forms[0].elements[x].onclick != null)
				{		
					str1 = document.forms[0].elements[x].onclick.toString();
					strElements = str1.split("'");
					for (g=0; g<strElements.length; g++)
					{	
						str2 = strElements[g];
						if (str2.indexOf("formElement") == 0)
						{
							otherElement = str2;
						}
						if (str2.indexOf("enableFormElement") > -1)
						{
							enableElement = "Y";
						}
						if (str2.indexOf("show") > -1)
						{
							showHIdeElement = "Y";
							
						}
						if (showHIdeElement == "Y" && str2.indexOf("row") > -1 && showHIdeRow == "")
						{
//alert("setting showHideRow...." + str2);
							showHIdeRow = str2;	
						}		
							
					}
//	alert("value....." + val + "\notherElement..." + otherElement + "\nenableElement.." + enableElement + "\nshowHideElement.." + showHIdeElement + "\nshowHIdeRow.." + showHIdeRow);
					if (enableElement == "Y")
					{
						enableFormElement(otherElement);
					} else {
						str3 = document.getElementById(otherElement);
						if (str3 != null){
							if (str3.type == "select-one" || str3.type == "select-multiple")
							{
								str3.selectedIndex = 0;
							}	
						}		
						disableFormElement(otherElement);
					}	
					if (showHIdeElement == "Y")
					{
						if (enableElement == "N")
						{
							show(showHIdeRow,0,'row');
						} else {
							show(showHIdeRow,1,'row');
						}	
	            	}
				} // end onclick
			}  // end checked
		} // end radio		
	}	// end for loop	
}
</script>
</head>
 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" onload="initializeRadioSelects()">

<html:form action="/displayReferralSummary" >



<%--<html:hidden name="riskReferralForm" property="mode" styleId="theMode"/> --%>
<html:hidden name="riskAnalysisForm" property="mode" styleId="theMode"/>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">
    	<bean:message key="title.juvenileCasework"/> -     	
    	<bean:message key="title.detentionRiskAssessmentInformation"/>
    	<logic:notEqual name="riskAnalysisForm" property="mode" value="update">
    	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|175">
		</logic:notEqual>
    	<logic:equal name="riskAnalysisForm" property="mode" value="update">
    		Update 
    	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|217">
		</logic:equal>	
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
        		<li>Expand (+) Juvenile Delinquency History to view its information.</li> 
				<li>Complete <strong>all</strong> interview questions, then select the Next button to view the summary.</li> 
				<li>Select the Back button to return to the previous page.</li> 
				<li>Select <strong>View Traits (in new window)</strong> hyperlink to search and view Traits in a new browser window.</li>  
		  </ul>
		</td>
	</tr>
  	<tr>
  		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
	<tr>
    	<td>   
     		<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="casefileheader"/>
     		</tiles:insert>
    	</td>   
	</tr>
</table>
<%-- END HEADER INFO TABLE --%>

<%-- hyperlink table for access to viewing traits --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
      		<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
        		<tr>
          			<td align='center' class='detailHead'>
      	    			<a href='javascript:openCustomRestrictiveWindow("/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&casefileId=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&submitAction=Find", 600, 700);'>View Traits (in new window)</a>
      				</td>
        		</tr>
      		</table>

			<div class='spacer'></div>
    		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    			<tiles:put name="tabid" value="casefiledetailstab"/>
    			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    		</tiles:insert>				

			<%-- BEGIN DETAIL TABLE --%>
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
  													<tiles:put name="tabid" value="riskAnalysis"/>
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
                        						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
                        							<tr>
                        								<td class="detailHead" colspan="2">
                        									Detention Screening Instrument               										 
                      									</td>
                      								</tr>
                        							<tr>
                        								<td align='center'>
                        									<table width='100%' cellpadding='4' cellspacing='1'>
                        										<tr>
						                        					<td class='formDeLabel' width="50%"><bean:message key="prompt.dateTaken"/></td>
						                        					<td class='formDe' width="50%">
						                        						<nested:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/>
						                        					</td>
						                             			</tr>
						                             			<tiles:insert page="../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
																	<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
																	<tiles:put name="tilesImageLevel" value=""/>
																</tiles:insert>
							                              	</table>
							                          	</td>
						                        	</tr>
						                        	<logic:equal name="riskAnalysisForm" property="mode" value="update">
													 		<tr id='modificationReasonRow' class=''>
				        										<td colspan='2'>
														          	<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
																			<tr>
						                 									<td class='formDeLabel' colspan='2'>Reason for modification&nbsp;
						                 										<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                  									<tiles:put name="tTextField" value="modReason"/>
								                   									<tiles:put name="tSpellCount" value="spellBtn1" />
						                										</tiles:insert>
						                										(Max. characters allowed: 550)
					                										</td>
						                 									</tr>
							                 								<tr>
							                 									<td class='formDe' colspan='2'><html:textarea name="riskAnalysisForm" property="modReason" rows='3' style="width:100%" onmouseout="textCounter(this,550)" onkeydown="textCounter(this,550)" styleId="reasonMod"></html:textarea></td>
							  	              								</tr>
					      	          								</table>
					          	      							</td>
				        									</tr>
													</logic:equal>
						                      	</table>
						                             			
								                <%-- BEGIN BUTTON TABLE --%>
								                <div class='spacer'></div>
								                <table border="0" width="100%">
								                	<tr>
								                    	<td align="center">
								                        	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
								                        	<html:submit property="submitAction" onclick="return (validateFields(this.form))"><bean:message key="button.next" /></html:submit>
								                        	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
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
      		<div class='spacer'></div>
    	</td>
  	</tr>
</table>

</html:form>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
