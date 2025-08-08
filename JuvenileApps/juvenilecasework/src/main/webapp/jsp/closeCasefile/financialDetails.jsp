<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 09/10/2007	Uma Gopinath Created JSP --%>
<%-- 07/12/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to Save Changes button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
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
<title><bean:message key="title.heading"/> - financialDetails.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript'>
function updateTotalGross(theCheckBox, familyIndex, annualGrossIncome)
{
	//There are 2 objects here, one is the displayed string, and the other one is the
	//value to be passed to the action (hidden)
	var totalGrossHidden = theCheckBox.form["guardianList["+familyIndex+"].totalGross"];
	var totalGross = parseFloat(totalGrossHidden.value);
		
	if(theCheckBox.checked) 
	{
		totalGross = totalGross + parseFloat(annualGrossIncome);
	}
	else 
	{
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

	if(isNaN(i)) 
  { 
	   return ''; 
	}

	var minus = '';
	if(i < 0) 
	{ 
	  minus = '-'; 
	}

	i = Math.abs(i);
	var n = new String(i);
	var a = [];
	while(n.length > 3)
	{
		var nn = n.substr(n.length -3);
		a.unshift(nn);
		n = n.substr(0, n.length -3);
	}
	if(n.length > 0) 
	{ 
	  a.unshift(n); 
	}

	n = a.join(delimiter);

	if(d.length < 1) 
	{ 
	  amount = n; 
	}
	else 
	{ 
	  amount = n + '.' + d; 
	}
	amount = minus + amount;

	return amount;
} // function CommaFormatted()


</script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/displayCommonAppFinancialDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|138">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Close Casefile - Common App - Financial Details</td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN MESSAGE TABLE -->
<logic:equal name="commonAppForm" property="action" value="confirm">
	<table width='100%'>
		<tr>
			<td align="center" class="confirm">Changes have been saved.</td>
		</tr>
	</table>
</logic:equal>
<!-- END MESSAGE TABLE -->
<div class=spacer></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td>
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
				<ul>
					<li>Select "Save Changes" button to save Financial information.</li>
				</ul>
			</logic:equal>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class=spacer></div>
<!-- Begin Casefile App Tabs -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  		<td valign=top>
      		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        		<tiles:put name="tabid" value="commonApp"/>
        		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      		</tiles:insert>	
      	</td>
	</tr>  
</table>
<%-- BEGIN TABS BLUE BORDER TABLE --%>	    		
<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign="top" align="center">
    		<div class='spacer'></div> 
<!-- Begin Juvenile Details Tabs table -->
			<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
						<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
							<tiles:put name="tabid" value="JuvenileDetails"/>
							<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
						</tiles:insert>	
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>
<!-- End Juvenile Details Tabs table -->			
<%-- BEGIN TABS RED BORDER TABLE --%>
  			<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
     		  	<tr>
        			<td valign=top align="center">
  						<div class=spacer></div>
<!-- Begin Court Order Tabs Table -->
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                 			<tr>
                 		    	<td valign=top>
									<!--tabs start-->
									<tiles:insert page="../caseworkCommon/commonAppJuvDetTabs.jsp" flush="true">
										<tiles:put name="tabid" value="Financial"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
							</tr>
						</table>
<!-- End Court Order Tabs Table -->							
<%-- BEGIN TABS TURQUOISE BORDER TABLE --%>
        				<table width='98%' align="center" border="0" cellpadding="0" cellspacing="2" class="borderTableTurquoise">
        				  	<tr>
        				  		<td valign="top" align="center">
<!-- BEGIN Guardian Info block tables -->
									<table width="99%" align="center" border="0" cellpadding="2" cellspacing="0">
										<logic:empty name="commonAppForm" property="guardianList">
											<tr bgcolor='#cccccc'> 
												<td colspan="6" class="subHead" align="left">No Members Available.</td> 
											</tr> 
										</logic:empty>
														
										<logic:notEmpty name="commonAppForm" property="guardianList">
											<input type="hidden" name="resetTabName"/>
											<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">				
												<nested:iterate indexId="familyIndex" id="memberList" name="commonAppForm" property="guardianList">
													<tr>
														<td>
<!-- BEGIN individual Guardian Info block table -->																
															<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 	
																<tr>
																	<td valign="top" class="detailHead">Guardian's Information</td>
																</tr>
																<tr>
																	<td align="center">
																		<div class='spacer'></div>
<!-- Begin Guardian Name table -->																			
																		<table width='99%' cellspacing="1" class="borderTableGrey">
																			<tr bgcolor='#cccccc'>
																				<td valign="top" class="subHead">Name</td>
																				<td valign="top" class="subHead">Relationship</td>
																				<td valign="top" class="subHead">Address</td>
																				<td valign="top" class="subHead">Phone #</td>
																				<td valign="top" class="subHead">Total Gross</td>
																			</tr>
																			<tr>																							
																				<td><nested:write property="guardian"/></td> 
																				<td><nested:write property="relationship"/></td> 
																				<td><nested:write property="address"/></td>
																				<td><nested:write property="formattedPhone"/></td> 
																				<td valign="top">
																					<bean:message key="currency.prefix"/><span id="totalGross<nested:write name='familyIndex'/>"><nested:write property="totalGross" formatKey="currency.format"/></span>
																					<nested:hidden property="totalGross"/>
																				</td>																												
																			</tr> 																	
																		</table>
<!-- End Guardian Name table -->																				
																		<br>
<!-- Begin Family Member Employment table -->																				
																		<table width='99%' cellspacing='1' class='borderTableBlue'>
																			<tr>
																				<td colspan='6' valign='top' class='detailHead'>Family Member Employment Information</td>
																			</tr>
																		
																			<logic:empty name="memberList" property="employmentHistory">
																				<tr>
																					<td colspan="6" class="subHead" align="left">No Employment records available.</td>
																				</tr>	 
																			</logic:empty>
																		
																			<logic:notEmpty name="memberList" property="employmentHistory">	                              																			  
																				<tr bgcolor="#f0f0f0">
																					<td class="subhead" valign="top" width="10%">Include?</td>
																					<td class="subhead"><bean:message key="prompt.entryDate"/></td>
																					<td class="subhead"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
																					<td class="subhead"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
																					<td class="subhead"><bean:message key="prompt.jobTitle"/></td> 
																					<td class="subhead"><bean:message key="prompt.annualGrossIncome" /></td> 
																				</tr> 
																				<nested:iterate id="employmentList" property="employmentHistory" indexId="index">
																					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">	
																						<bean:define id="jsfunct">updateTotalGross(this, '<nested:write name='familyIndex'/>', '<nested:write property="annualGrossIncome"/>' );</bean:define>
																						<td><nested:multibox property="included" value="true" onclick="<%=jsfunct%>"/></td>																						                                
																						<td><nested:write property="entryDate"  formatKey="date.format.mmddyyyy"/></td> 
																						<td><nested:write property="employmentStatus"/></td> 
																						<td><nested:write property="placeEmployed"/></td>
																						<td><nested:write property="jobDescription"/></td> 
																						<td><bean:message key="currency.prefix"/><nested:write property="annualGrossIncome" formatKey="currency.format"/></td> 
																					</tr>
																				</nested:iterate>
																			</logic:notEmpty>
																		</table>
<!-- End Family Member Employment table -->																				
																		<input type="hidden" name="clearEmploymentCheckBoxes" value=""/>
<!-- Begin income info table -->	
																		<table width='99%' cellspacing="1" cellpadding="4" border="0">
																			<tr>
																				<td valign="top" class="detailHead" colspan="4">Income Information </td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" width='1%' nowrap>Monthly TANF assistance</td>
																				<td class="formDe" colspan="3"><bean:message key="currency.prefix"/><span id="TANFAssistance"><nested:write property="TANFAssistance" formatKey="currency.format"/></span></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" width='1%' nowrap>Other income</td>
																				<td class="formDe"><bean:message key="currency.prefix"/><span id="otherIncome"><nested:write property="otherIncome" formatKey="currency.format"/></span></td>
																			</tr>
																		</table>
<!-- End income info table -->           																	
	                           										</td>
    	                       									</tr>
                                   							</table>
<!-- END individual Guardian Info block table -->	                                   									
                                   						</td>
                                   					</tr>		
	       										</nested:iterate>
	       									</logic:equal>
           									<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
           										<tr>
           											<td>
           												<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 	
															<tr>
																<td valign="top" class="detailHead">Guardian's Information</td>
															</tr>
															<tr>
																<td class="subhead"> Juvenile is 21 or more years old - no updating is allowed.</td>
															</tr>
														</table>
													</td>
												</tr>			
											</logic:notEqual>
        								</logic:notEmpty>                																		
									</table>
<!-- BEGIN Guardian Info block tables -->
								</td>
							</tr>
						</table>
<%-- END TABS TURQUOISE BORDER TABLE --%>
        				<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
						<table width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
									<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
										<html:submit property="submitAction"><bean:message key="button.saveChanges" /></html:submit>
									</logic:equal>	
									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								</td> 
							</tr>
						</table>
<!-- END BUTTON TABLE -->
						<div class=spacer></div>
					</td>
				</tr>
			</table>
<%-- END TABS RED BORDER TABLE --%>			
			<div class=spacer></div>
		</td>
	</tr>
</table>
<%-- END TABS BLUE BORDER TABLE --%>	
<div class=spacer></div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>