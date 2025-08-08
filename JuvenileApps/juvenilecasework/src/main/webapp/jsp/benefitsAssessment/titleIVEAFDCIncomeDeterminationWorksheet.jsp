<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="titleIVEQuestion"/>




<%--BEGIN HEADER TAG--%>
<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<html:base />
	<title></title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- 	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> --%>
	<script  type='text/javascript'>
	function checkStepparent(theForm, runMulti, path ) {
	
		if( runMulti == 1 )
			showHideMulti('AFDCWorksheet','pWork',2, path);
		
		
	}
	</script>
</head> 

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign="top" class="detailHead" align="left">

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="1%">
					<%-- 17may2006 - mjt - next href had '4' as the third argument  --%>
					<logic:equal name="processBenefitsAssessmentForm" property="currentAssessment.question.oneParentIsStepparent" value="true">
						<a href="javascript:showHideMulti('AFDCWorksheet','pWork',3, '/<msp:webapp/>')" border="0">
					</logic:equal>
					<logic:notEqual name="processBenefitsAssessmentForm" property="currentAssessment.question.oneParentIsStepparent" value="true">
						<a href="javascript:showHideMulti('AFDCWorksheet','pWork',2, '/<msp:webapp/>')" border="0">
					</logic:notEqual>
							<img border="0" src="/<msp:webapp/>images/expand.gif" name="AFDCWorksheet">
						</a>
					</td>
					<td class="detailHead" align="left">&nbsp;AFDC Income Determination Worksheet</td>
				</tr>
			</table>
		</td>
	</tr>

	<%-- Income of Certified Group section of worksheet --%>
	<tr id="pWork0" class="hidden" >
		<td valign="top">
			<table width="100%" cellpadding="4" cellspacing="0">
				<tr>
					<td>
						<table width='100%' cellspacing=1>
							<tr bgcolor=#cccccc>
								<td valign=top class=subHead>Member #</td>
								<td valign=top class=subHead>Name</td>
								<td valign=top class=subHead>Age</td>
								<td valign=top class=subHead>Relationship</td>
								<td valign=top class=subHead>Comments</td>
								<td valign=top class=subHead>Income Source</td>
								<td valign=top class=subHead>Gross Monthly Income</td> 
							</tr>
							
							<logic:notEmpty name="titleIVEQuestion" property="afdcIncomeWorksheetItems">
								<logic:iterate id="adfcIter" indexId="adfcIndex" name="titleIVEQuestion" property="afdcIncomeWorksheetItems">
								<bean:size id="afdcIncomeWorksheetSize" name="processBenefitsAssessmentForm" property="currentAssessment.question.afdcIncomeWorksheetItems"/>
								 <bean:define id="ttt" name="afdcIncomeWorksheetSize" type="java.lang.Integer"/>
									<logic:notEmpty name="adfcIter" property="name">
										<tr <%if(adfcIndex.intValue()%2 != 0)
													out.println("class=alternateRow");
												else
													out.println("class=normalRow");
											%>
										height="100%">
										
										<logic:equal name="adfcIter" property="juvenile" value="false">
											<td valign=top><bean:write name="adfcIter" property="memberId"/></td>
										</logic:equal>
										<logic:notEqual name="adfcIter" property="juvenile" value="false">
											<td valign=top>J<bean:write name="adfcIter" property="memberId"/></td> 
											<%-- As per PAM she watns the Juvenile number to display here instead of the member number
											when showing the juvenile prefixed by a J --%>
										</logic:notEqual>
											<td valign=top><bean:write name="adfcIter" property="name"/></td>
											<td valign=top><bean:write name="adfcIter" property="age"/></td>
											<logic:equal name="adfcIter" property="juvenile" value="false">
											<td valign=top>
												<logic:notEmpty name="adfcIter" property="memberId">
													<bean:write name="adfcIter" property="relationshipToJuvenile"/>
												</logic:notEmpty>
												<%-- Those that doesn't memberId are the newly entered family member --%>
												<logic:empty name="adfcIter" property="memberId">
													<bean:write name="adfcIter" property="relationshipToJuvenileAFDC"/>
												</logic:empty>
											</td>
										</logic:equal>
										<logic:notEqual name="adfcIter" property="juvenile" value="false">
											<td valign=top>SELF</td>
										</logic:notEqual>
											
											<td valign=top><bean:write name="adfcIter" property="comments"/></td>
											<td valign=top><bean:write name="adfcIter" property="incomeSource"/></td>
											<td valign=top>$&nbsp;<bean:write name="adfcIter" property="grossMonthyIncome" formatKey="currency.format"/></td></td>
									</tr>
									</logic:notEmpty>
								</logic:iterate>
							</logic:notEmpty>					
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr id="pWork1" class="hidden">
		<td valign="top">
			<table width="100%" cellpadding="4" cellspacing="0">
				<tr> 
					<td> 
						<table width='100%' cellspacing=1> 
							<tr>
								<td class="formDeLabel">Total number of people in the Certified Group</td>
								<td class="formDe" nowrap align="right"><bean:write name="titleIVEQuestion" property="afdcIncomeCertifiedGroupSize"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">Number of parents in the Certified Group</td>
								<td class="formDe" nowrap align="right"><bean:write name="titleIVEQuestion" property="afdcIncomeCertifiedGroupParentsSize" /></td>
							</tr>
							<tr>
								<td class="formDeLabel">Income limit for the Certified Group</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeCertifiedGroupLimit" formatKey="currency.format"/></td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>
		</td>
	</tr>																
	<%-- End Income of Certified Group section of worksheet --%>
	
	<logic:equal name="processBenefitsAssessmentForm" property="currentAssessment.question.oneParentIsStepparent" value="true">
	<%-- Stepparent income section of worksheet --%>
	<tr id="pWork2" class="hidden">
		<td valign="top">
			<table width="100%" cellpadding="4" cellspacing="0">
				<tr>
					<td class="detailHead" align="left">&nbsp;<strong>Applied Income of Stepparent</strong></td>
				</tr>
				<tr> 
					<td> 
						<table width='100%' cellspacing=1> 
							<tr>
								<td class="formDeLabel">Stepparent's Monthly Gross Earned Income</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentsMonthlyGross" formatKey="currency.format"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">Work related expenses standard deduction of $90</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentWorkRelatedExpenses" formatKey="currency.format"/></td>
							</tr>																			
							<tr>
								<td class="formDeLabel">Countable Earned Monthly Income</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentCountableEarnedMonthy" formatKey="currency.format"/></td>
							</tr>																					
							<tr>
								<td class="formDeLabel">Other Monthly Income of Stepparent</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentOtherMonthlyIncome" formatKey="currency.format"/></td>
							</tr>																				
							<tr>
								<td class="formDeLabel">Total Countable Monthly Income</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentTotalCountableMonthy" formatKey="currency.format"/></td>
							</tr>																			
							<tr>
								<td class="formDeLabel">Monthly Payments to Dependents Outside of Home</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentMonthyPaymentsToDependent" formatKey="currency.format"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">Monthly Alimony and Child Support Payments Outside of Home</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentMonthyAlimonyChildSupport" formatKey="currency.format"/></td>
							</tr>	
							<tr>
								<td class="formDeLabel">Number of Non-certified Dependents</td>
								<td class="formDe" nowrap align="right"><bean:write name="titleIVEQuestion" property="afdcIncomeStepparentNoncertifiedCount"/></td>
							</tr>																				
							<tr>
								<td class="formDeLabel">Allowance Amount for Stepparent and Non-certified Dependents taken from the Stepparent Deduction Chart</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentAllowanceAmount" formatKey="currency.format"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">Applied Income of Stepparent</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentAppliedIncome" formatKey="currency.format"/></td>
							</tr>																		
						</table>
					</td> 
				</tr>
			</table>
		</td>
	</tr>
	<%-- End Stepparent income section of worksheet --%>
	</logic:equal>
	
	<%-- Child and Family Certified Group section of worksheet --%>
 <%-- 17may2006 - mjt - next tr had attribute: class="hidden" --%>	 
	<tr id="pWork3" >
		<td valign="top">
			<table width="100%" cellpadding="4" cellspacing="0">
				<tr>
					<td class="detailHead" align="left">&nbsp;Child and Family Certified Group</td>
				</tr>
				<tr> 
					<td> 
						<table width='100%' cellspacing=1> 
							<tr>
								<td class="formDeLabel">1. Total monthly gross income of Certified Group</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeTotalMonthy" formatKey="currency.format"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">2. Line 9 of Applied Income of Stepparent Table</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeStepparentAppliedIncome" formatKey="currency.format"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">3. Total Countable Income</td>
								<td class="formDe" nowrap align="right">$&nbsp;<bean:write name="titleIVEQuestion" property="afdcIncomeTotalCountable" formatKey="currency.format"/></td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>
		</td>
	</tr>																
	<%-- End Child and Family Certified Group section of worksheet --%>
</table>

</body>
</html:html>
