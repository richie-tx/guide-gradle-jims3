<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- financialHistory.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>


<script type='text/javascript'>

function updateTotalGross(theCheckBox, familyIndex, annualGrossIncome)
{
	//There are 2 objects here, one is the displayed string, and the other one is the
	//value to be passed to the action (hidden)
	var totalGrossHidden = theCheckBox.form["socialHistoryData.familyFinancialHistory["+familyIndex+"].totalGross"];
	var totalGross = parseFloat(totalGrossHidden.value);
		
	if(theCheckBox.checked) {
		totalGross = totalGross + parseFloat(annualGrossIncome);
	}
	else {
		totalGross = totalGross - parseFloat(annualGrossIncome);
	}
	
	var totalGrossObj = document.getElementById("totalGross"+familyIndex);
	totalGrossObj.innerHTML = CommaFormatted(totalGross.toFixed(2));
	totalGrossHidden.value = totalGross.toFixed(2);
}

function CommaFormatted(amount)
{
	var delimiter = ",";
	var a = amount.split('.',2)
	var d = a[1];
	var i = parseInt(a[0]);
	if(isNaN(i)) { return ''; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	var n = new String(i);
	var a = [];
	while(n.length > 3)
	{
		var nn = n.substr(n.length-3);
		a.unshift(nn);
		n = n.substr(0,n.length-3);
	}
	if(n.length > 0) { a.unshift(n); }
	n = a.join(delimiter);
	if(d.length < 1) { amount = n; }
	else { amount = n + '.' + d; }
	amount = minus + amount;
	return amount;
} // function CommaFormatted()



</script>
</head>

<html:form action="/handleSocialHistoryData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|95"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
<tr> 
	<td align="center" class="header">
		Juvenile Casework - Conduct Interview<br>
		Social History Data - Financial History
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
			<li>Click check box to exclude from report, and click on Save Changes button to save the changes.</li>
	        <li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
	        <li>Click Back button to return to previous page. </li>
		</ul>
	</td>
</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
<tr>
	<td valign=top>
		<%-- BEGIN TAB --%>
  	<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
  		<tiles:put name="tabid" value="casefiledetailstab"/>	
  		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  	</tiles:insert>	

		<%-- BEGIN BORDER TABLE BLUE TABLE --%>
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			<tr>
				<td valign=top align=center>
				<div class='spacer'></div>
				 <table width='98%' border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width='100%' border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign=top>
									<%--tabs start--%>
										<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" value="interviewtab"/>
 												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
										</tiles:insert>	
									<%--tabs end--%>
									</td>
								</tr>
								<tr>
								  	<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
								  </tr>
								  
					  		</table>
							<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td valign=top align=center>
				
									<div class='spacer'></div>						
					<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign=top>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
											<%--tabs start--%>
											<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
												<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
											</tiles:insert>
											<%--tabs end--%>
										</td>
									</tr>
									<tr><td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
								</table>

								<%--begin outer blue border --%>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
									<tr>
										<td valign=top align=center>
										<div class='spacer'></div>
											<table id="familyTable" width='100%' border="0" cellpadding="0" cellspacing="0">
 													<tr>
 														<td valign=top align=center>
														<input type="hidden" name="resetTabName" value="financialHistory"/>
 															<%-- BEGIN Financial History TABLE --%>
														
														<nested:iterate indexId="familyIndex" name="socialHistoryForm" property="socialHistoryData.familyFinancialHistory">
														<table width="98%" border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
															<tr>
																<td valign=top class=detailHead>
																	<table border="0" cellpadding="0" cellspacing="0" columns="2" width="100%">
																		<tr>
																			<td align="left">Guardian's Information</td>
																			<td align="right">In-home: 
																					<nested:define id="inHomeStatus" property="inHome"/>
																					<logic:equal name="inHomeStatus" value="true">Yes</logic:equal>
																					<logic:equal name="inHomeStatus" value="false">No</logic:equal>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td align=center>
																	<table width='98%' cellspacing=1 class=borderTableGrey>
																		<tr bgcolor=#cccccc>
																			<td valign=top class=subHead>Name</td>
																			<td valign=top class=subHead>Relationship</td>
																			<td valign=top class=subHead>Address</td>
																			<td valign=top class=subHead>Phone #</td>

																			<td valign=top class=subHead>Total Gross</td>
																		</tr>
																		<tr class=normalRow>
																			<td valign=top><nested:write property="guardian"/> </td>
																			<td valign=top><nested:write property="relationship"/></td>
																			<td valign=top><nested:write property="address"/></td>
																			<td valign=top><nested:write property="formattedPhone"/></td>

																			<td valign=top>
																				<bean:message key="currency.prefix"/><span id="totalGross<nested:write name='familyIndex'/>"><nested:write property="totalGross" formatKey="currency.format"/></span>
																				<nested:hidden property="totalGross"/>
																			</td>
																			
																		</tr>
																	</table>

																	<div class='spacer'></div>
																	<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
																		<tr>
																			<td valign=top class=detailHead>
																				<table width='100%' cellpadding=0 cellspacing=0>

																					<tr>
																						<td class=detailHead>Family Member Employment Information</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td valign=top>

																				<table width='100%'  cellspacing="1">
																					<tr bgcolor=#cccccc>
																						<td class="subhead" width="1%">Include</td>
																						<td class="subhead" width="10%">Entry Date</td>
																						<td class="subhead">Employment Status </td>
																						<td class="subhead">Current Employer</td>
																						<td class="subhead">Job Title</td>

																						<td class="subhead">Annual Gross Income</td>
																					</tr>
																					<nested:iterate property="employmentHistory">
																					
																					<tr class="normalRow" bgcolor="#ffffff" id=row2>
																						
																						<bean:define id="jsfunct">updateTotalGross(this, '<nested:write name='familyIndex'/>', '<nested:write property="annualGrossIncome"/>' );</bean:define>
																						<td width='1%' align=center><nested:checkbox property="included" value="true"
																							onclick="<%=jsfunct%>"/></td>
																						<td width=10%><nested:write property="entryDate" formatKey="date.format.mmddyyyy"/></td>
																						<td><nested:write property="employmentStatus"/></td>
																						<td><nested:write property="placeEmployed"/></td>
																						<td><nested:write property="jobDescription"/></td>
																						<td><bean:message key="currency.prefix"/><nested:write property="annualGrossIncome" formatKey="currency.format"/></td>
																					</tr>
																					</nested:iterate>
																				</table>
																			</td>
																		</tr>
																	</table>
																
																	<div class='spacer'></div>
																	<table width='98%' cellspacing=1 cellpadding=4 border=0>
																		<tr>
																			<td valign=top class=detailHead colspan=4>Income Information </td>
																		</tr>
																		<tr>
																			<td class=formDeLabel width='1%' nowrap>Monthly TANF assistance</td>

																			<td class=formDe colspan="3"><bean:message key="currency.prefix"/><span id="TANFAssistance"><nested:write property="TANFAssistance" formatKey="currency.format"/></span></td>
																		</tr>
																		<tr>
																		  <td class="formDeLabel" width='1%' nowrap>Other income</td>
																		  <td class="formDe"><bean:message key="currency.prefix"/><span id="otherIncome"><nested:write property="otherIncome" formatKey="currency.format"/></span></td>
																	</table>
																</td>

															</tr>
														</table>
														<div class='spacer'></div>
														</nested:iterate>
 															
 														</td>
 													</tr>
 												</table>
											
											
											
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<input type="hidden" name="resetTabName" value="financialHistory"/>
														<input type="submit" name="submitAction" value="<bean:message key='button.back'/>"
															onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
														<html:submit property="submitAction"><bean:message key="button.saveChanges"/></html:submit> 
														<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
													</td>
												</tr>
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
														<html:submit property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit>
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
     <div class='spacer'></div>
     </td>
      </tr>
      </table>
							
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>