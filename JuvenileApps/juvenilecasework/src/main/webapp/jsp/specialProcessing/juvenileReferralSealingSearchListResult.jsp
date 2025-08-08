<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>juvenileReferralSealingSearchListResult</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileReferralSealingSearch.js"></script>
<script type="text/javascript">
	function closeWindow(el) {
		window.close();

		return 0;
	}
	function enableSelectButton()
	{
		document.getElementById("selectRefBtn").disabled = false;
	}
	function validate() {
		var recType = $("#hdnRectype").val();
		var cnt = $("#hdnCnt").val();
		var currDate = new Date();
		localStorage.setItem("SearchWin", "open");
		if ($("#comments").val() == "") {
			alert("Comments is required.");
			$("#comments").focus();
			return false;
		}
		if ($("#sealedDate").val() == "") {
			alert("Sealed Date is required.");
			$("#sealedDate").focus();
			return false;
		} else {
			var sealdt = $("#sealedDate").val();
			var today = formatDate(new Date());
			var refSealDateFormatted = formatDate(sealdt);
			if (refSealDateFormatted > today) {
				alert('Sealed Date cannot be a future date.');
				$("#sealedDate").focus();
				return false;
			}
		}
		/* if (cnt == 1) {
			/* var sel = confirm("The selected referral number is the only assigned referral number.  Please determine if juvenile master seal is required");
			return sel; 
			alert('The referral number is the only referral assigned and has an active record type.  Juvenile Master Seal is required.');
			return false;
		} */
		
		if (recType.match("^I.")) {
			var sel = confirm("Juvenile has been purged.  Do you wish to continue with sealing the selected referral number?");
			return sel;
		}
				
		return true;

	}
	function formatDate(date) {

		var newStr = '';
		if (date > '') {

			var tempDate = new Date(date).toISOString().substr(0, 10);
			newStr = tempDate.replace(/-/g, "");
		}
		return newStr;
	}
	function validateForm()
	{			
		$("#comments").val("");
		$("#sealedDate").val("");
		
		return true;
	}
	window.onload=validateForm;
	
</script>
<style>
.row-disabled {
	   background-color: #dfdfdf;
	   pointer-events: none;
	   width: 100%;
	}
</style>
</head>
<body>

	<div align="center">
		<html:form method="post" action="/displayJuvenileReferralSealingSearchResults"
			onsubmit="return this;">
			<div>
				<table width='100%'>
					<tr>
						<h3 align="center">Juvenile Details</h3>
					</tr>
				</table>
				 <!-- BEGIN Error Message Table -->
				 <logic:messagesPresent message="true"> 
					<table width="100%">
						<tr>		  
							<td align="center" class="messageAlert"><html:messages id="message" message="true"><font style="font-weight: bold;"
									color="green" size="3" face="Arial"><bean:write name="message"/></html:messages></font></td>		  
						</tr>   	  
					</table>
				</logic:messagesPresent>
				
				<!-- Error Message Area -->
				<table width="100%">
					<tr>
						<td align="center" class="errorAlert"><font color="red"><html:errors></html:errors>
						</font></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
				</table>
				
				<table border="1" width="750" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
								Number</font></td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="juvenileId" />&nbsp;</font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
								Name</font></td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="juvenileName" />&nbsp; </font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Record
								Type</font></td>
						<td><font size="-1"> <elogic:if name="ProdSupportForm"
									property="rectype" op="equal" value="JUVENILE">
									<elogic:then>
										<font size="-1">ACTIVE</font>
									</elogic:then>
								</elogic:if> <elogic:if name="ProdSupportForm" property="rectype" op="equal"
									value="I.JUVENILE">
									<elogic:then>
										<font size="-1">PURGED </font>
									</elogic:then>
								</elogic:if> </font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font>
						</td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="birthDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Sex</font>
						</td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="sex" />&nbsp;</font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Race</font>
						</td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="race" />&nbsp;</font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Hispanic</font>
						</td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="hispanic" />&nbsp;</font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font>
						</td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="juvenileSsn" />&nbsp;</font></td>
					</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">Master
								Status</font></td>
						<td><font size="-1"><bean:write name="ProdSupportForm"
									property="statusId" />&nbsp;</font></td>
					</tr>
					<tr>
						<html:hidden styleId="hdnRectype" name="ProdSupportForm"
							property="rectype" />
					</tr>
					<tr>
						<html:hidden styleId="hdnCnt" name="ProdSupportForm"
							property="hdnCount" />
					</tr>
				</table>
				<table width='100%'>
					<h3 align="center">Referral Details</h3>
				</table>
				<p align="center">
					<b><i>Select the radio button next to the record you want
							to Seal and click Submit.</i> </b>
				</p>
				<logic:notEmpty name="ProdSupportForm" property="juvprogrefs">
					<table border="1" width="980" align="center">
						<tr>
							<td bgcolor="gray"></td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral
									Number</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral
									Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense
									Code</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake
									Decision</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake
									Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court
									Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court
									Decision</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Closed
									Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Record
									Type</font>
							</td>
						</tr>

						<logic:iterate id="juvprogrefs" name="ProdSupportForm"
							property="juvprogrefs" indexId="index">

							
							<logic:notEqual name="juvprogrefs" property="rectype" value="REFERRAL">						
								<tr class ="row-disabled" disabled="true">							
							</logic:notEqual>						
							<logic:equal name="juvprogrefs" property="rectype" value="REFERRAL">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							</logic:equal>
							<logic:equal name="juvprogrefs" property="rectype" value="I.REFERRAL">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							</logic:equal>
								<bean:define id="referralNum" name='juvprogrefs'
									property='referralNum' type="java.lang.String"></bean:define>
								<%-- <bean:define id="oid" name='juvprogrefs' property='referralOID' type="java.lang.String"></bean:define> --%>
								<td width="1%"><input type="radio" onclick="enableSelectButton();" name="referralNum"
									value="<bean:write name='juvprogrefs' property='referralNum'/>" />
								</td>								
								<td width="10%"><html:text name="juvprogrefs"
										property="referralNum" styleId='<%="refNum-" + referralNum%>'
										size="4" style="border: 0;background-color: white"
										indexed="true" disabled="true" />
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="referralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="offenseCode" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="intakeDecision" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="intakeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<!-- intakeDate -->
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="dispositionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="courtResult" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs"
											property="closeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td width="10%"><font size="-1"><bean:write name="juvprogrefs"
											property="rectype" />&nbsp;</font>
								</td>

								<%-- <html:hidden styleId='<%="referralId-"+oid%>'  name='juvprogrefs' property='referralOID' indexed="true"/> --%>

							</tr>
						</logic:iterate>

					</table>

				</logic:notEmpty>
				<BR>
				<table align="center"">

					<logic:notEmpty name="ProdSupportForm" property="juvprogrefs">
						<tr>
							<td><font color="black" face="bold" size="-1">Comments:</font>
							</td>
							<td class="formDeLabel" colspan="6"><html:textarea
									style="width:100%" name="ProdSupportForm" styleId='comments'
									property="sealComments" rows='3'
									onkeyup="textCounter(this.form.progrefcomments,255)" />
							</td>
							<td><BR> <BR> <BR></td>
							<td><font color="black" face="bold" size="-1">Sealed
									Date:</font></td>
							<td><font size="-1"><html:text name="ProdSupportForm"
										property="sealedDate" styleId='sealedDate' maxlength="10"
										size="10" /> </font></td>
							<%-- <html:text property="courtDate" styleId="courtDate"  maxlength="10" size="10"/> --%>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center"><html:submit property="submitAction"
									styleId="selectRefBtn" disabled="true" onclick="return validate()">
									<bean:message key="button.confirmReferralSeal" />
								</html:submit> <!-- <input type="submit" value="Confirm Referral Seal" name="submitAction" id="selectRefBtn" class="hidden"  > -->
							<td><html:submit property="submitAction"
									onclick="return Back()">
									<bean:message key="button.back" />
								</html:submit> <html:submit property="submitAction"
									onclick="return closeWindow(this.form)">
									<bean:message key="button.cancel" />
								</html:submit></td>
						</tr>


					</logic:notEmpty>

				</table>
				<html:hidden styleId="tempRefNum" name="ProdSupportForm"
					property="referralNum" />
				<%--<html:hidden styleId="tempJuvNum" name="juvenileProfileSearchForm" property="juvenileId"/> --%>
		</html:form>



	</div>

</body>
</html:html>