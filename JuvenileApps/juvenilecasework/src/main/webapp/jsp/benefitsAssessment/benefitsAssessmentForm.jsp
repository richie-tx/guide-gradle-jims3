<!DOCTYPE HTML>

<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type='text/javascript'>
function showRemoval()
{
  show('removalGuardian', 1, 'row');
}

function hideRemoval(theForm)
{
  show('removalGuardian', 0, 'row');
}

/* the next two functions are used to simulate the
	 functionality of radio buttons ... however, the
	 widgets tha use these two functions as callbacks
	 are, in actuality, checkboxes (although identified
	 as HTML radios).  this had to be done because the
	 names of the radio widgets are different, since
	 the values have to be persisted as values unto
	 themselves. search on these function names to see
	 the other side of the street.   mjt - 10 may 2006			 
*/
function flipPWE100(theForm, path)
{
    theForm.pweradios[0].checked = true ;
    theForm.pweradios[1].checked = false ;
}

function flipPWEUnder(theForm, path)
{
    theForm.pweradios[0].checked = false ;
    theForm.pweradios[1].checked = true ;
}
/* *************************************************** */

function switchPWE(theForm)
{
    if( theForm.disabledParent.checked ) 
		{
     theForm.pweCheckbox.disabled = true;
      theForm.pweradios[0].disabled = true;
      theForm.pweradios[1].disabled = true;
      theForm.pweCheckbox.checked = false;
      theForm.pweradios[0].checked = false;
      theForm.pweradios[1].checked = false;
    }
    else 
		{
     theForm.pweCheckbox.disabled  = false;
    }	
}

function togglePWE(theForm, path)
{
    if( theForm.isStep.checked ) 
		{
      theForm.pweCheckbox.disabled = true;
      theForm.pweradios[0].disabled = true;
      theForm.pweradios[1].disabled = true;
      theForm.pweCheckbox.checked = false;
      theForm.pweradios[0].checked = false;
      theForm.pweradios[1].checked = false;
    }
    else 
		{
     theForm.pweCheckbox.disabled  = false;
    }	

	checkStepparent(theForm, 0, path );
}

function checkStepparent(theForm, runMulti, path ) 
{
	if( runMulti == 1 )
		showHideMulti('AFDCWorksheet','pWork',2, path);
	
	var elem = document.getElementById("isStep");
	if(elem != null)
	{
		if( elem.checked ) 
		{
				if( document.getElementById("pWork0").className == 'visibleTR' ) 
				{
					show( 'pWork1', 1, 'row') ;
				}
				else 
				{
					show( 'pWork1', 0, 'row') ;
				}
		}
		else 
		{
				show( 'pWork1', 0, 'row') ;
		}
	}
	else
	{
		show( 'pWork1', 0, 'row') ;
	}
		
}

function togglePWEradios(theForm)
{
    if( theForm.pweCheckbox.checked ) 
		{
      theForm.pweradios[0].disabled = false;
      theForm.pweradios[1].disabled = false;
  		showHide2('parCheck', 'row', 1) ;
    }
    else 
		{
      theForm.pweradios[0].disabled = true;
      theForm.pweradios[1].disabled = true;
      theForm.pweradios[0].checked = false;
      theForm.pweradios[1].checked = false;
  		showHide2('parCheck', 'row', 0) ;
    }
}

function enable2a(theForm)
{
    theForm.pweCheckbox.disabled = false;
}

function disable2a(theForm, path)
{
    theForm.pweCheckbox.disabled = true;
    theForm.pweCheckbox.checked = false;
    togglePWE(theForm, path);
}

function disableIncome(theForm)
{
	theForm.incomeAmt.disabled = true;
	incomeEnablement(theForm);
}

function enableIncome(theForm)
{
	theForm.incomeAmt.disabled = false;
	incomeEnablement(theForm);
}

function incomeEnablement(theForm)
{
	if( theForm.steady[0].checked || // yes button
					theForm.irr[0].checked ) // yes button
	{ 
			theForm.incomeAmt.disabled = true;
			theForm.incomeAmt.value = "";
	}
}


function checkIncomeDeterminationSheet(theForm)
{
  <bean:size id = "incomeWorksheetSize" name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeWorksheetItems"/>
  <bean:define id = "pweGrossIncome" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweGrossMonthlyIncomeForOver100Hours"/>
  var size = <%=incomeWorksheetSize.intValue()%>;
  var income = "currentAssessment.question.pweGrossMonthlyIncomeForOver100Hours";
  
  if(theForm[income].value != ""  && document.getElementById("parCheck").className == 'visibleTR' && theForm[income].value != null)
  {
  	clearAllValArrays();						
  	addCurrencyValidation(income,"PWE Gross Monthly Earned Income is not a valid currency entry. No commas or dollar signs are allowed, only 2 digits allowed after decimal point.  Example: for $1,000 enter 1000.00.");			 
  	if( !validateCustomStrutsBasedJS(theForm) )
		{
  		return false;
  	}	 
  }
  
	var rtn = true ;
  for(i = 0; i < size; i++)
  {
  	element1 = "currentAssessment.question.afdcIncomeWorksheetItems[" + i + "].name";	
  	element2 = "currentAssessment.question.afdcIncomeWorksheetItems[" + i + "].age";	
  	element3 = "currentAssessment.question.afdcIncomeWorksheetItems[" + i + "].grossMonthyIncome";
  	
  	if(theForm[element1] != null)
  	{
  		if(theForm[element1].value == "" && theForm[element2].value != "" && theForm[element2].value != 0)
  		{
  			alert("Name has to be provided if Age is entered.");
  			theForm[element1].focus();
  			return false;
  		}
  		if(theForm[element1].value != "")
  		{
  			if( !isNumeric(theForm[element2].value,false) )
  			{
  				alert("Age should be numeric.");
  				theForm[element2].focus();
  				return false;
  			}
  			if(theForm[element2].value <=0)
  			{
  				alert("Minimum Age is 1.");
  				theForm[element2].focus();
  				return false;
  			}
  			if(theForm[element2].value >125)
  			{
  				alert("Maximum Age is 125.");
  				theForm[element2].focus();
  				return false;
  			} 			
  		}
  		if(theForm[element1].value == "" && theForm[element3].value != "" && theForm[element3].value != 0)
  		{
  			alert("Name has to be provided if Gross Monthly Income is entered.");
  			theForm[element1].focus();
  			return false;
  		}
  		else
  		{
  			if(theForm[element3].value != "" && theForm[element3].value != 0)
  			{
  				clearAllValArrays();						
  				addCurrencyValidation(element3,"Gross Monthly Income is not a valid currency entry. No commas or dollar signs are allowed, only 2 digits allowed after decimal point.  Example: for $1,000 enter 1000.00.");
  				rtn = validateCustomStrutsBasedJS(theForm);
  			}
  		}
  	}
  	else if(theForm[element1] == null  && theForm[element3] != null)
  	{ // special case where data is pre-filled directly in a <td> (not in an <input>)
			if(theForm[element3].value != "" && theForm[element3].value != 0)
			{
				clearAllValArrays();						
				addCurrencyValidation(element3,"Gross Monthly Income is not a valid currency entry. No commas or dollar signs are allowed, only 2 digits allowed after decimal point.  Example: for $1,000 enter 1000.00.");
				rtn = validateCustomStrutsBasedJS(theForm);
			}
  	}
  }
  return rtn ;
}

</script>
	
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/> - benefitsAssessmentForm.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayBenefitsAssessmentEligibility.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|6">

 
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.benefitsAssessmentTitleIVE"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select an Entry Date under Previous Benefits Assessment to view Benefit Details.</li>
        <li>Fill out Benefits Assessment form then select the Next button.</li>
        <li>Select the Back button to return to previous page.</li>
      </ul>&nbsp;&nbsp;<bean:message key="prompt.2.diamond"/>&nbsp;All information is required.
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>
			
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>


<div class='hidden'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>
		
 		  <%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
  			<tr>
  			  <td valign='top' align='center'>
  			  
  			    <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
  			    <br />
	  			  <table width='100%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>

								<table width='100%' border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign='top' align='center'>
										
										  <div class='hidden'></div>
											<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign='top' align='center'>
				
                            <%-- BEGIN Previous Benefits TABLE --%>
                            <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align='center'>
                            	<tr>
                            		<td colspan='4' class='detailHead'>Juvenile's Previous Benefits</td>
                            	</tr>
                            	<tr>
                            		<td align='center'>
                            			<%-- BEGIN BENEFITS TABLE --%>

												  					<bean:size id="prevJuvCount" name="processBenefitsAssessmentForm" property="previousJuvenileBenefits" />
																		<logic:notEmpty name="processBenefitsAssessmentForm" property="previousJuvenileBenefits" >          
																			<logic:greaterThan name="prevJuvCount" value="10">
																				<%-- make it a scrolling region with header titles locked --%>
																				<div class='scrollingDiv200'>
			                            			<table cellpadding='2' cellspacing='1' width='100%' class='notFirstColumn'>
																	   			<thead>
																			</logic:greaterThan>
																		</logic:notEmpty>

																		<logic:lessThan name="prevJuvCount" value="10">
		                            			<table cellpadding='2' cellspacing='1' width='100%'>
																		</logic:lessThan>
																	
                            				<logic:empty name="processBenefitsAssessmentForm" property="previousJuvenileBenefits">
	                            				<tr class='formDeLabel'>
	                            					<td colspan='4'>No Previous Benefits.</td>
	                            				</tr>
                            				</logic:empty>

                            				<logic:notEmpty name="processBenefitsAssessmentForm" property="previousJuvenileBenefits">

																			<logic:greaterThan name="prevJuvCount" value="10">
																				</thead>
																				<tbody>
																			</logic:greaterThan>

	                            				<tr class='formDeLabel'>
	                            					<td valign='top'>Entry Date</td>
	                            					<td valign='top'>Eligible</td>
	                            					<td valign='top'>Receiving</td>
	                            					<td valign='top'>Eligibility Type </td>
	                            				</tr>
                              				<logic:iterate indexId="index" id="prevJuvBenefitsIter" name="processBenefitsAssessmentForm" property="previousJuvenileBenefits">
                   											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                                 					<td><bean:write name="prevJuvBenefitsIter" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
                                 					<td>
                                 						<logic:equal name="prevJuvBenefitsIter" property="eligibleForBenefits" value="true">Yes</logic:equal>
                                 						<logic:notEqual name="prevJuvBenefitsIter" property="eligibleForBenefits" value="true">No</logic:notEqual>
                                 					</td>
                                 					<td>
                                 						<logic:equal name="prevJuvBenefitsIter" property="receivingBenefits" value="true">Yes</logic:equal>
                                 						<logic:notEqual name="prevJuvBenefitsIter" property="receivingBenefits" value="true">No</logic:notEqual>
                                 					</td>
                                 					<td><bean:write name="prevJuvBenefitsIter" property="eligibilityType"/></td>
                                				</tr>
		                          				</logic:iterate>

																			<logic:greaterThan name="prevJuvCount" value="10">
																				<tbody>
																			</logic:greaterThan>

		                        				</logic:notEmpty>
		                        			</table>

																	<logic:greaterThan name="prevJuvCount" value="10">
																		</div>
																	</logic:greaterThan>

		                        		</td>
		                        	</tr>
		                        </table>
		                        <%-- END BENEFITS TABLE --%>


		                        <%-- BEGIN PREVIOUS BENEFIT ASSESSMENT TABLE --%> 
		                        <tiles:insert page="/jsp/benefitsAssessment/previousBenefitsAssessmentTile.jsp" flush="true">
		                        	<tiles:put name="previousBenefitsAssessments" beanName="processBenefitsAssessmentForm" beanProperty="previousBenefitsAssessments" />	
		                        </tiles:insert>
		                            
		       									<div class='spacer'></div>
		            						<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="borderTableBlue">
		            							<tr>
		            								<td class="detailHead"><bean:message key="prompt.guardianName"/> <bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
		            							</tr>
		
		            							<tr>
		      											<td>
		      												<div class='spacer'></div>			
		      												<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              									<tr>
		             											<td valign="top">	
		                										<tiles:insert page="/jsp/caseworkCommon/employmentSummary.jsp" flush="false">
		                                      <tiles:put name="employmentInfoList"  beanName="processBenefitsAssessmentForm" beanProperty="employmentInfoList"/>	
		                                      <tiles:put name="rowIdHeader" value="benAsses0"/>
		                                    </tiles:insert>
		    												      </td>
		            										</tr>
		           										</table>                           
		         										</td>
		       										</tr>
		
															<tr><td><div class='spacer'></div></td></tr>
															
		            							<tr>
		            								<td>
		            									<%-- BEGIN Family Financial Info TABLE --%>
		            									
		            									<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
		            										<tr>
		            											<td valign="top" class="detailHead">						
		            												<table width="100%" cellpadding="0" cellspacing="0">
		            													<tr>
		            														<td width="1%">
		            															<a href="javascript:showHideMulti('Characteristics','pChar',1, '/<msp:webapp/>')" border="0"> 
		            																<img border="0" src="/<msp:webapp/>images/expand.gif" name="Characteristics">
		            															</a>
		            														</td>
		            														<td class="detailHead">&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.financialInformation"/></td>
		            													</tr>
		            												</table>
		            											</td>
		            										</tr>
			
		            										<tr id="pChar0" class="hidden">
		            											<td valign="top">
		            												<tiles:insert page="/jsp/caseworkCommon/familyFinancialInfo.jsp" flush="true">
		            													<tiles:put name="familyFinancialInfo" beanName="processBenefitsAssessmentForm" beanProperty="guardianFinancialInfo" />	
		            												</tiles:insert>
		            											</td>
		            										</tr>
		            									</table>
		            									<%-- END Family Financial Info TABLE --%>
		
		            									<%-- Begin Title IV-e Assessment TABLE --%>
		            									<br />
		            									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		              									<tr>
		              										<td class="detailHead"><bean:message key="prompt.titleIVEPlacementScreening"/></td>
		              									</tr>
		              									<tr>
		              										<td align='center'>
		                										<table cellpadding="4"  cellspacing="1" width="100%" class="borderTableGrey">
		                											<tr>
		                												<td bgcolor="999999"><strong><bean:message key="prompt.AFDCRequirement"/></strong></td>
		                											</tr>
		                											<tr>
		                												<td align="center">
		                												<table cellpadding="2" cellspacing="1" width="100%">
		                													<tr>
		                														<td></td>
		                														<td class="formDeLabel"><bean:message key="prompt.dateTaken"/></td>
		                														<td class="formDe" colspan="2"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.question.dateTaken" formatKey="date.format.mmddyyyy"/></td>
		                													</tr>
		                													<tr>
		                														<td width="1%">1.</td>
		                														<td class="formDeLabel">Is the child a U.S. citizen, Legal Permanent Resident, or Qualified alien? If a permanent resident, attach a copy of the INS Form I-551 (green card)</td>
		                														<td class="formDe" colspan="2"><bean:message key="prompt.yes"/><html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.legalResident" value="true"></html:radio> <bean:message key="prompt.no"/><html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.legalResident" value="false"></html:radio></td>
		                													</tr>
		               														<tr><td><div class='spacer'></div></td>
		                													</tr>
		                						
		                													<%-- new deprivation section --%>
		                													<tr>
		                														<td valign="top">2.</td>
		                														<td colspan="3">
		                															<table cellpadding="2" cellspacing="0" width="100%" class="borderTableGrey">
		                																<tr>
		                																	<td colspan="4" bgcolor="999999"><strong><bean:message key="prompt.parentalDeprivationSection"/></strong></td>
		                																</tr>
		                																
		                																<%-- AFDC Determination Worksheet  --%>
		                																<tr>  
		                																	<td colspan="4">
		                																		<%-- BEGIN Worksheet TABLE --%>
		                																		<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
		                																			<tr>
		                																				<td valign="top" class="detailHead">
		                																					<table width="100%" cellpadding="0" cellspacing="0">
		                																						<tr>
		                																							<td width="1%">
		                																								<a href="javascript:showHideMulti('Deprivation','pDep',1, '/<msp:webapp/>')" border="0">
		                																									<img border="0" src="/<msp:webapp/>images/contract.gif" name="Deprivation">
		                																								</a>
		                																							</td>
		                																							<td class="detailHead">&nbsp;<bean:message key="prompt.parentalDeprivationSection"/></td>
		                																						</tr>
		                																					</table>
		                																				</td>
		                																			</tr>
		                							 
		                																			<%-- Income of Certified Group section of worksheet --%>
		                																			<tr id="pDep0" class="visibleTR">
		                																				<td valign="top">
		                																					<table width="100%" cellpadding="2" cellspacing="0">
		                																						<tr> 
		                																							<td> 
		                																								<table width='100%' cellspacing='1'>
		                																									<tr>
		                																										<td></td>
		                																										<td class="formDeLabel"><html:checkbox styleId="isStep" name="processBenefitsAssessmentForm" property="currentAssessment.question.oneParentIsStepparent" onclick="togglePWE(this.form, '/<msp:webapp/>')"/>&nbsp;Select if one of the parents is a stepparent</td>
		                																									</tr>
		                																									<tr>
		                																										<td>2a.</td>
		                																										<td  class="formDeLabel">If parental deprivation existed, mark the basis for the deprivation below:
		                																											<br />&nbsp;&nbsp;<html:checkbox name="processBenefitsAssessmentForm" property="currentAssessment.question.deathOrAbsence"/>Death or absence of parent(s)
		                																											<br />&nbsp;&nbsp;<html:checkbox name="processBenefitsAssessmentForm" property="currentAssessment.question.incapacityOrDisabilityOfParent" styleId="disabledParent" onclick="switchPWE(this.form)"/>Incapacity/disability of a parent
		                																											<br />&nbsp;&nbsp;<html:checkbox name="processBenefitsAssessmentForm" property="currentAssessment.question.primaryWageEarnerUnderemployment" styleId="pweCheckbox" onclick="togglePWEradios(this.form)"/>Primary Wage Earner (PWE) Underemployment based upon either:
		                																											<br />&nbsp;&nbsp;&nbsp;&nbsp;<html:radio onclick="flipPWE100(this.form, '/<msp:webapp/>');"   value='true'   name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedLessThen100Hours" styleId="pweradios" />PWE worked less than 100 hours a month on average , or
		                																											<br />&nbsp;&nbsp;&nbsp;&nbsp;<html:radio onclick="flipPWEUnder(this.form, '/<msp:webapp/>');" value='true' name="processBenefitsAssessmentForm" property="currentAssessment.question.pweIncomeLessThanUnderemployedLimit" styleId="pweradios" />PWE monthly gross income is equal to or less than the
		                																											<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;applicable amount on the&nbsp;<a href="javascript:openWindow('/<msp:webapp/>jsp/benefitsAssessment/titleIVeIncomeGuidelinesforUnderemployedParentLookup.jsp');">Underemployed Parent Income Chart.</a>
		                																											<input type="hidden" name="checkBoxName" value="parentalDeprivationCheckBoxes"> <%-- to reset checkboxes upon submit --%>
		                																										</td>
		                																									</tr>
		                									 
		                																									<tr id="parCheck" class="hidden">
		                																										<td></td>
		                																										<td>                                          					
		                																											<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		                																												<tr>
		                																													<td colspan='2' class='detailHead'>Underemployed Parent Checklist</td>
		                																												</tr>
		                										 
		                																												<tr>
		                																													<td class='formDeLabel' width='1%' nowrap='nowrap'>1) Who is the Primary Wage Earner (PWE) in the home of removal?</td>
		                																													<td class='formDe'>
		                																														<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.pweRelationshipToJuvenile" value="Father">Father</html:radio>
		                																														<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.pweRelationshipToJuvenile" value="Mother">Mother</html:radio>
		                																													</td>
		                																												</tr>
		                										 
		                																												<tr>
		                																													<td class='formDeLabel' width='1%'>2) If the PWE has steady employment, did the PWE work less than 100 hours during the month of removal (or review)?</td>
		                																													<td class='formDe'>Yes<html:radio styleId="steady" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedSteadyLessThan100Hours" value="true" onclick="disableIncome(this.form)"/> 
		                																														No<html:radio styleId="steady" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedSteadyLessThan100Hours" value="false" onclick="enableIncome(this.form)"/> 
		                																														N/A<html:radio styleId="steady" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedSteadyLessThan100Hours" value="false"/>
		                																													</td>
		                																												</tr>
		                										 
		                																												<tr>
		                																													<td class='formDeLabel' width='1%'>3) If the PWE works irregularly, does the PWE work less than 100 hours per month on average?</td>
		                																													<td class='formDe'>
		                																														Yes<html:radio styleId="irr" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedIrregularLessThan100HoursAvg" value="true" onclick="disableIncome(this.form)"/> 
		                																														No<html:radio styleId="irr" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedIrregularLessThan100HoursAvg" value="false" onclick="enableIncome(this.form)"/> 
		                																														N/A<html:radio styleId="irr" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweWorkedIrregularLessThan100HoursAvg" value="false"/>
		                																													</td>
		                																												</tr>
		                										 
		                																												<tr>
		                																													<td class='formDeLabel' width='1%'>4) If the PWE works more than 100 hours per month on average, what is their gross monthly earned income?</td>
		                																													<td class='formDe'><html:text styleId="incomeAmt" name="processBenefitsAssessmentForm" property="currentAssessment.question.pweGrossMonthlyIncomeForOver100Hours"/>
		                																													</td>
		                																												</tr>
		                																											</table>
		                																										</td>
		                																									</tr>
		                																								</table>
		                																							</td>
		                																						</tr>
		                																					</table>
		                																				</td>
		                																			</tr>
		                																		</table>
		                																		<%-- END Worksheet TABLE --%>
		                																	</td>
		                																	</tr>
		                																</table>
		                															</td>
		                														</tr>
		                  													<%-- end new deprivation section --%>
		                							 
		                														<tr><td><div class='spacer'></div></td></tr>
		                														<tr>
		                															<td>3.</td>
		                															<td class="formDeLabel">Was the child living with a parent or specified relative at the time of removal, or was the child living with a parent or specified relative with legal managing conservatorship within six months of removal?</td>
		                															<td class="formDe" colspan="2">
		                																Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.wasChildLivingWithParent" value="true" onclick="showRemoval()"/> 
		                																No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.question.wasChildLivingWithParent" value="false" onclick="hideRemoval(this.form)"/>
		                															</td>
		                														</tr>
		                														
		                														<tr <logic:notEqual name="processBenefitsAssessmentForm" property="currentAssessment.question.wasChildLivingWithParent" value="true"> class="hidden" </logic:notEqual> id="removalGuardian">
		                															
		                															<td>&nbsp;</td>
		                															<td colspan='3'>
		                																<table cellpadding="2" cellspacing="1" width="100%">
		                																	<tr>
		                																		<td class="formDeLabel">Name:</td>
		                																		<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.name.formattedName"/></td>
		                																	</tr>
		                																	<tr>
		                																		<td class="formDeLabel">Relationship:</td>
		                																		<td colspan="2" class="formDe"><bean:write name="processBenefitsAssessmentForm" property="currentAssessment.guardian.relationship"/></td>
		                																	</tr>
		                																</table>
		                															</td>
		                														</tr>
		                														<tr><td><div class='spacer'></div></td></tr>
		                							 
		                														<tr>
		                															<td>4.</td>
		                															<td colspan="3">
		                																<table cellpadding="2" cellspacing="1" width="100%" class="borderTableGrey">
		                																	<tr>
		                																		<td colspan="3" bgcolor="999999"><strong><bean:message key="prompt.AFDCIncomeDeterminationWorksheet"/></strong></td>
		                																	</tr>
		                							 
		                																	<tr>  <%-- AFDC Determination Worksheet  --%>
		                																		<td colspan="3">
		                																			<%-- BEGIN Worksheet TABLE --%>
		                																			<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
		                																				<tr>
		                																					<td valign="top" class="detailHead">
		                																					<table width="100%" cellpadding="0" cellspacing="0">
		                																				<%--/tr--%>
		                																				<tr>
		                																					<td width="1%">
		                																						<a href="javascript:checkStepparent(this.form, 1, '/<msp:webapp/>' )" border="0"><img border="0" src="/<msp:webapp/>images/contract.gif" name="AFDCWorksheet"></a>
		                																					</td>
		                																					<td class="detailHead">&nbsp;<bean:message key="prompt.AFDCIncomeDeterminationWorksheet"/></td>
		                																				</tr>
		                																			</table>
		                																		</td>
		                																	</tr>
		                							 
		                																	<%-- Income of Certified Group section of worksheet --%>
		                																	<tr id="pWork0" class="visibleTR" >
		                																		<td valign="top">
		                																			<table width="100%" cellpadding="2" cellspacing="0">
		                																				<tr>
		                																					<td>
		                																						<table width='100%' cellspacing='1'>
		                																							<tr bgcolor='#cccccc'>
		                																								<td valign='top' class='subHead'>Member #</td>
		                																								<td valign='top' class='subHead'>Name</td>
		                																								<td valign='top' class='subHead'>Age</td>
		                																								<td valign='top' class='subHead'>Relationship</td>
		                																								<td valign='top' class='subHead'>Comments</td>
		                																								<td valign='top' class='subHead'>Income Source</td>
		                																								<td valign='top' class='subHead'>Gross Monthly Income</td> 
		                																							</tr>
		                																							
		                																							<logic:notEmpty name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeWorksheetItems">
		                																							<bean:size id="afdcIncomeWorksheetSize" name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeWorksheetItems"/>
		                																							
		                																							<bean:define id="ttt" name="afdcIncomeWorksheetSize" type="java.lang.Integer"/>
			                																							<nested:iterate indexId="adfcIndex" id="adfcIter"name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeWorksheetItems">
			                																								<tr class="<%out.print( (adfcIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
		                																									
		                																									<logic:equal name="adfcIter" property="juvenile" value="true">
		                  																									<td valign='top'>J<bean:write name="adfcIter" property="memberId"/></td>
		                  																									<%-- As per PAM she watns the Juvenile number to display here instead of the member number
		                  																									when showing the juvenile prefixed by a J --%>
		                  																									<td valign='top'><nested:write property="name"/></td>
		                																										<td valign='top'><nested:write property="age"/></td>
		                																										<td valign='top'>SELF</td>
		                  																								</logic:equal>
		
		                  																								<logic:notEqual name="adfcIter" property="juvenile" value="true">
		                  																									<td valign='top'><bean:write name="adfcIter" property="memberId"/></td>
		                  																									<logic:notEmpty name="adfcIter" property="memberId">
		                  																										<td valign='top'><nested:write property="name"/></td>
		                 																										<td valign='top'><nested:write property="age"/></td>
		                 																										<td valign='top'><nested:write property="relationshipToJuvenile"/></td>
		                																									</logic:notEmpty>
		
		                																									<logic:empty name="adfcIter" property="memberId">
		                 																										<td valign='top'><nested:text property="name" size="20"/></td>
		                 																										<td valign='top'><nested:text property="age" size="5"/></td>
		                 																										<td valign='top'>
		                 																											<nested:select property="relationshipToJuvenileId">
		                               																					<option value="">Please Select</option>
		                 																												<html:optionsCollection name="processBenefitsAssessmentForm" property="relationshipsToJuvenileAFDCCodeTable" value="code" label="description"/>
		                 																											</nested:select>
		                 																										</td>
		                 																									</logic:empty>
		                																								</logic:notEqual>
																										
		                																									<td valign='top'><nested:text property="comments"/></td>
		                																									<td valign='top'>
		                																										<nested:select property="incomeSourceId">
		                               																				<option value="">Please Select</option>
		                																											<html:optionsCollection name="processBenefitsAssessmentForm" property="incomeSourceCodeTable" value="code" label="description"/>
		                																										</nested:select>
		                																									</td>
		                																									<td valign='top'><nested:text property="grossMonthyIncome" size="5"/></td>
		                																								</tr>
		                																							</nested:iterate>
		                																							</logic:notEmpty>                      																						
		                																						</table>
		                																					</td>
		                																				</tr>
		                																			</table>
		                																		</td>
		                																	</tr>
		                																	<%-- End Income of Certified Group section of worksheet --%>
		                							 
		                																	<%-- Stepparent income section of worksheet --%>
		                																	<tr id="pWork1" class="hidden">
		                																		<td valign="top">
		                																			<table width="100%" cellpadding="4" cellspacing="0">
		                																				<tr>
		                																					<td class="detailHead">&nbsp;<strong>Applied Income of Stepparent</strong></td>
		                																				</tr>
		                																				<tr> 
		                																					<td> 
		                																						<table width='100%' cellspacing='1'> 
		                																							<tr>
		                																								<td class="formDeLabel">Stepparent's Monthly Gross Earned Income</td>
		                																								<td class="formDe" nowrap='nowrap'>$&nbsp;<html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentsMonthlyGross" size="10"/></td>
		                																							</tr>
		                																							<tr>
		                																								<td class="formDeLabel">Work related expenses standard deduction of $90</td>
		                																								<td class="formDe" nowrap='nowrap'>$&nbsp;<html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentWorkRelatedExpenses" size="10"/></td>
		                																							</tr>
		                																							<tr>
		                																								<td class="formDeLabel">Other Monthly Income of Stepparent</td>
		                																								<td class="formDe" nowrap='nowrap'>$&nbsp;<html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentOtherMonthlyIncome" size="10"/></td>
		                																							</tr>
		                																							<tr>
		                																								<td class="formDeLabel">Monthly Payments to Dependents Outside of Home</td>
		                																								<td class="formDe" nowrap='nowrap'>$&nbsp;<html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentMonthyPaymentsToDependent" size="10"/></td>
		                																							</tr>
		                																							<tr>
		                																								<td class="formDeLabel">Monthly Alimony and Child Support Payments Outside of Home</td>
		                																								<td class="formDe" nowrap='nowrap'>$&nbsp;<html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentMonthyAlimonyChildSupport" size="10"/></td>
		                																							</tr>
		                																							<tr>
		                																								<td class="formDeLabel">Number of Non-certified Dependents</td>
		                																								<td class="formDe" nowrap='nowrap'><html:text name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeStepparentNoncertifiedCount" size="10"/></td>
		                																							</tr>
		                																						</table>
		                																					</td> 
		                																			  </tr>
		                																			</table>
		                																		</td>
		                																	</tr>
		                																	<%-- End Stepparent income section of worksheet --%>
		                																	<%-- End Child and Family Certified Group section of worksheet --%>
		                							 
		                																</table>
		                																<%-- END Worksheet TABLE --%>
		                															</td>
		                														</tr>
		                													</table>
		                												</td>
		                											</tr>
		
		                											<tr><td></td></tr>
		                											<tr>
		                												<td colspan="4"  class="detailHead"><strong>Source(s) used to gather information</strong></td>
		                											</tr>
		                											<tr>
		                												<td class="formDeLabel" colspan="3">The following source(s) were used to gather this information</td>
		                												<td class='formDe'>
		                													<html:select name="processBenefitsAssessmentForm" property="currentAssessment.question.selectedInfoSourceId" multiple="true" size="3">
		                														<html:optionsCollection name="processBenefitsAssessmentForm" property="benefitsAssessmentInfoSourceCodeTable" value="code" label="description"/>
		                													</html:select>
		                													<input type="hidden" name="checkBoxName" value="infoSource">
		                												</td>
		                											</tr>
		                												  
		                										</table>
		                									</td>
		                								</tr>
		                							</table>
		
		            									<tr><td valign='top'></td></tr>
		                							<tr>
		                								<td>
		                									<table cellpadding="2" cellspacing="1" width="100%" align='center' class="borderTableGrey">
		                										<tr>
		                											<td bgcolor="999999"><strong>IV-e Eligibility (To be completed by JPD Staff ONLY)</strong></td>
		                										</tr>
		                										<tr>
		                											<td align="center">
		                												<table cellpadding="4" cellspacing="1" width="100%">
		                													<tr>
		                														<td width="1%">7a.</td>
		                														<td class="formDeLabel">Does the initial order of removal contain the "Best Interest" language?</td>
		                														<td class="formDe" nowrap='nowrap' width="16%" colspan="2">
		                															Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.initialOrderRemovalContainBestInterest" value="true"/> 
		                															No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.initialOrderRemovalContainBestInterest" value="false"/>
		                														</td>
		                													</tr>
		                													<tr>
		                														<td>7b.</td>
		                														<td class="formDeLabel">Was the "Reasonable Efforts" language made within 60 days of the child's removal?</td>
		                														<td class="formDe" nowrap='nowrap' colspan="2">
		                															Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.reasonableEffortsMadeWithin60DaysOfChildRemoval" value="true"/> 
		                															No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.reasonableEffortsMadeWithin60DaysOfChildRemoval" value="false"/>
		                														</td>
		                													</tr>
		                													<tr>
		                														<td>7c.</td>
		                														<td class="formDeLabel" nowrap='nowrap'>Do court orders include the finding of "responsibility for the child's care and placement?</td>
		                														<td class="formDe" colspan="2">
		                															Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.courtOrderIncludeFindingChildCareAndPlacement" value="true"/> 
		                															No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.courtOrderIncludeFindingChildCareAndPlacement" value="false"/>
		                														</td>
		                													</tr>
		                													<tr>
		                														<td>8.</td>
		                														<td class="formDeLabel">Does this child meet the AFDC requirements and did the court orders contain the appropriate language?</td>
		                														<td class="formDe" colspan="2">
		                															Yes<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.childMeetAFDCRequirement" value="true"/> 
		                															No<html:radio name="processBenefitsAssessmentForm" property="currentAssessment.childMeetAFDCRequirement" value="false"/>
		                														</td>
		                													</tr>
		                													<tr>
		                														<td>&nbsp;</td>
		                														<td>
		                															<table width="100%" class="borderTableGrey">
		                																<tr>
		                																	<td class="formDeLabel" width='1%' nowrap='nowrap'>Title IV-E Officer</td>
		                																	<td class="formDe"><bean:write name="processBenefitsAssessmentForm" property="titleIVeOfficerName.formattedName"/></td>
		                																</tr>
		                															</table>
		                														</td>
		                													</tr>
		                												</table>
		                											</td>
		                										</tr>
		                									</table>
		                								</td>
		                							</tr>
		                 						</table>
		
		                            <%-- BEGIN BUTTON TABLE --%>
		                            <table border="0" width="100%">
		                              <tr>
		                                <td align="center">
		                                	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
		                                	<html:submit property="submitAction" onclick="return checkIncomeDeterminationSheet(this.form);"><bean:message key="button.next"/></html:submit>
		                                	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		                                </td>
		                              </tr>
		                            </table>
		                            <%-- END BUTTON TABLE --%>
		
		                        	  </td>
		                          </tr>
		                        </table>
		                        <%-- END Title IV-e Assessment TABLE --%>
		                      </td>
		      							</tr>
		      						</table>
		                </td>
		              </tr>
		            </table>
		          </td>
		        </tr>
		      </table>
		    </td>
		  </tr>
		</table>
    </td>
  </tr>
</table>

</pg:pager>
<%-- End Pagination Closing Tag --%>


<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</html:form>

</body>
</html:html>

