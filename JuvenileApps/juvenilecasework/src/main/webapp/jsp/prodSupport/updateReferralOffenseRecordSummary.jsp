<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>Juvenile Casework
	-/prodSupport/updateReferralOffenseRecordSummary.jsp</title>
<style>
	 .message {
	 	text-align: center;
	 	color: green;
	 }
</style>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/UpdateReferralOffense.js"></script>
<script>
	$(document).ready(function(){
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.juvenileNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.juvenileNum" />'){
			$("#juvenileNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.juvenileNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.referralNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.referralNum" />'){
			$("#referralNo").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.referralNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseCode"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseCode" />'){
			$("#offenseCode").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseCode"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offDate"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offDate" />'){
			$("#offenseDate").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offDate"/>' + "</td>" );
		}
		

		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.keyMapLocation"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.keyMapLocation" />'){
			$("#offenseKeyMap").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.keyMapLocation"/>' + "</td>" );
		}
		

		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.investigationNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.investigationNum" />'){
			$("#offenseInvestigation").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.investigationNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseStreetNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseStreetNum" />'){
			$("#streetNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseStreetNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseStreetName"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseStreetName" />'){
			$("#streetName").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseStreetName"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseAptNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseAptNum" />'){
			$("#aptNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseAptNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseCity"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseCity" />'){
			$("#offenseCity").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseCity"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseState"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseState" />'){
			$("#offenseState").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseState"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseZip"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.offenseZip" />'){
			$("#offenseZip").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.offenseZip"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.weaponType"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.weaponType" />'){
			$("#weaponType").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.weaponType"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.cjisNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.cjisNum" />'){
			$("#cjisNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.cjisNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.arrestDate"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.arrestDate" />'){
			$("#arrestDate").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.arrestDate"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.arrestTime"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.arrestTime" />'){
			$("#arrestTime").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.arrestTime"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.sequenceNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.sequenceNum" />'){
			$("#sequenceNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.sequenceNum"/>' + "</td>" );
		}
		
		if ('<bean:write name="ProdSupportForm" property="originalReferralOffense.chargeSequenceNum"/>' != '<bean:write name="ProdSupportForm" property="referralOffense.chargeSequenceNum" />'){
			$("#chargeSeqNum").append("<td class='message'>Updated from previous value, " + '<bean:write name="ProdSupportForm" property="originalReferralOffense.chargeSequenceNum"/>' + "</td>" );
		}
		
		console.log("Hello world");
		
			
		
	})
</script>

</head>

<body>

	<html:form action="/PerformUpdateReferralOffenseRecord"
		 onsubmit="return this;">
	<!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><font style="font-weight: bold;"
							color="green" size="3" face="Arial"><bean:write name="message"/></html:messages></font></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
						<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
		<div>

			<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>

			<!-- Error Message Area -->
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<table border="0" width="700" align="center">

					<tr align="center">
						<td colspan="4"><font style="font-weight: bold;"
							color="#FF0000" size="3" face="Arial"> <bean:write
									name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			
			<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Updated</font></i></b></p>
	<logic:notEmpty	name="ProdSupportForm" property="referralOffense"> 
		
	<table border="1" width="500" align="center">
				<tr id="juvenileNum">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Number</font>
					</td>
					<%-- <td><font size="-1"><bean:write name="ProdSupportForm"
								property="juvenileId" />&nbsp;</font>
					</td> --%>
					<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.juvenileNum"/>&nbsp;</font></td>
					
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Name</font>
					</td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="juvenileName" />&nbsp;</font>
					</td>
					
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Record Type</font>
					</td>
					<td><elogic:if name="ProdSupportForm" property="rectype" op="equal"
						value="S.JUVENILE">
						<elogic:then>
							<font size="-1"> SEALED </font>
						</elogic:then>
					</elogic:if>
					<elogic:if name="ProdSupportForm" property="rectype" op="equal"
						value="I.JUVENILE">
						<elogic:then>
							<font size="-1"> PURGED </font>
						</elogic:then>
					</elogic:if>				
					</td>
				</tr>
				<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
	<tr id="referralNo">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.referralNum"/>&nbsp;</font></td>				
	</tr>
	<tr id="offenseCode">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseCode"/>&nbsp;</font></td>
		<%-- <td><font size="-1"><html:text name="referralOffenses" property="offenseCode" styleId='offCode' size="6" indexed="true"/></font>
		<html:submit property="submitAction" styleId="validateCode"><bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
		<html:submit property="submitAction" onclick="return Set();"><bean:message key="button.searchForOffenseCode" /></html:submit>
		<input type="hidden" name="validateMsg" value="<bean:write name="ProdSupportForm" property="msg" />"  id="valOffMsgId" /></td> --%>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Severity</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseSeverity"/>&nbsp;</font></td>		
	</tr>
	<tr id="offenseDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>	
	<tr id="offenseKeyMap">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Keymap</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.keyMapLocation"/>&nbsp;</font></td>
	</tr>
	<tr id="offenseInvestigation">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Investigation</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.investigationNum"/>&nbsp;</font></td>
	</tr>
	<tr id="streetNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Street Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseStreetNum"/>&nbsp;</font></td>
	</tr>
	<tr id="streetName">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Street Name</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseStreetName"/>&nbsp;</font></td>
	</tr>
	<tr id="aptNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Apt. Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseAptNum"/>&nbsp;</font></td>
	</tr>
	<tr id="offenseCity">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense City</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseCity"/>&nbsp;</font></td>
	</tr>
	<tr id="offenseState">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense State</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseState"/>&nbsp;</font></td>
	</tr>
	<tr id="offenseZip">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Zip</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseZip"/>&nbsp;</font></td>
	</tr>
	<tr id="weaponType">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Weapon Type</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.weaponType"/>&nbsp;</font></td>
	</tr>
	<tr id="cjisNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CJIS Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.cjisNum"/>&nbsp;</font></td>
	</tr>
	<tr id="arrestDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Date of Arrest</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.arrestDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr id="arrestTime">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Arrest Time</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.arrestTime"/>&nbsp;</font></td>
	</tr>
	<tr id="onCampOffense">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus Offense</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.onCampOffense"/>&nbsp;</font></td>
	</tr>
	<tr id="onCampDistrict">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus District</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.onCampDistrict"/>&nbsp;</font></td>
	</tr>
	<tr id="onCampSchool">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus School</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.onCampSchool"/>&nbsp;</font></td>
	</tr>
	<tr id="sequenceNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.sequenceNum"/>&nbsp;</font></td>
	</tr>
		
	<tr id="chargeSeqNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Charge Sequence Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.chargeSequenceNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.lcUser"/>&nbsp;</font></td>		
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date/Time</font></td>
		<td>
			<font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.lcDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
			<font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.lcTime" />&nbsp;</font>
		</td>	
		
	</tr>
	</table>
	</logic:notEmpty>
					

		</div>

</html:form>
		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>

			<html:form action="/UpdateReferralOffenseRecordQuery?clr=Y"
				onsubmit="return this;">
		<table align="center"">
			<tr>
				<td><html:submit value="Back to Query" />
				</td>
			</tr>
		</table>
	</html:form>
	<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
		</table>
		
		<html:hidden styleId="hdnrefDate" name="ProdSupportForm"
			property="referralDate" />
	<%-- </html:form> --%>

</body>
</html:html>