<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/viewPetitionDetailsResult</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updatePetitionDetails.js"></script>

</head>

<body>

<html:form action="/updatePetitionDetailsQuery" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Referral <bean:write name="ProdSupportForm" property="referralId" /></h2>
	     
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<bean:write name="ProdSupportForm" property="msg" />
	 		</td>
	 	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
	
		<table class="resultTbl" border="1" width="750" align="center">
		<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile Name</th>
				<td><bean:write name="ProdSupportForm" property="juvenileDetail.lastName" />,
					<bean:write name="ProdSupportForm" property="juvenileDetail.firstName" />
				</td>
			</tr>
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile Number</th>
				<td><bean:write name="ProdSupportForm" property="juvenileDetail.juvenileNum"/></td>
			</tr>
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile DOB</th>
				<td><bean:write name="ProdSupportForm" property="juvenileDetail.dob"/></td>
			</tr>
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile Master Status</th>
				<td><bean:write name="ProdSupportForm" property="juvenileDetail.masterStatus"/></td>
			</tr>
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">SSN</th>
				<td><bean:write name="ProdSupportForm" property="juvenileDetail.juvenileSsn"/></td>
			</tr>
	</table>  
	<h3 align="center">Referral Details</h3>
	<table class="resultTbl" border="1" width="750" align="center">
		<tr>
		<td bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Referral Number</font></td>
		<td bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Referral Date</font></td>
		<td bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Referral Offense</font></td>
		<td bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Referral Status</font></td>
	</tr>
	
	<logic:iterate id="referralDetail" name="ProdSupportForm" property="referralDetails">
     	<td>
			<bean:write name="referralDetail" property="referralNum" />	 
		</td>
		<td>
			<bean:write name="referralDetail" property="referralDate" />
			<input type="hidden" id="referralDate" value="<bean:write name="referralDetail" property="referralDate" />"/>
		</td>
		<td>
			<bean:write name="ProdSupportForm" property="currentOffense" />	 
		</td>
		<td>
			<bean:write name="referralDetail" property="closeDate" />	 
		</td>
	</logic:iterate> 
	</table>    
<p align="center"><b><i>Select the radio button next to the record <br>
you want to update and click the Submit button.</i></b></p>	     
	<logic:notEmpty	name="ProdSupportForm" property="petitionDetails">
	<h3 align="center">Petition Details</h3>
	<div align="center">
		<table class="resultTbl" border="1" width="850" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1"></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Juvenile Number
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Referral Number
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Status
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Type
					</td>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Date
					</td>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Number
					</td>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Amendment
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Allegation
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Petition Allegation Severity
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Sequence #(Petition)
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						CJIS Number
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Last Change Date/Time
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Last Change User
					</th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="petitionDetail" name="ProdSupportForm" property="petitionDetails" indexId="index">
					<tr>
						<td><input type="radio" 
									name="selectedValue"
									value="<bean:write name="petitionDetail" property="OID" />"/></td>
						<td>
							<bean:write name="petitionDetail" property="juvenileNum" />
						</td>
						<td>
							<bean:write name="petitionDetail" property="referralNum" />	 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petitionStatus" /> 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petitionType" />	 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petition_Date" />	 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petitionNum" />	
						</td>
						<td>
							<bean:write name="petitionDetail" property="amend" />	 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petitionAllegation" />
						</td>
						<td>
							<bean:write name="petitionDetail" property="severity" /> 
						</td>
						<td>
							<bean:write name="petitionDetail" property="sequenceNum" /> 
						</td>
						<td>
							<bean:write name="petitionDetail" property="petCJISNum" /> 
						</td>
						<td>
							<bean:write name="petitionDetail" property="last_Change_Date" />
						</td>
						<td>
							<bean:write name="petitionDetail" property="lastChangeUser" />
						</td>
					</tr>
			   </logic:iterate>
			</tbody>
		</table>
	</div>
</logic:notEmpty>		

	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="referralDetails">
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="selectPetBtn">Update Petition Detail</html:submit>
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="referralDetails">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>

	</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>
</table>
<html:hidden styleId="petitionIndex" name="ProdSupportForm" property="referralOID"/>
<html:hidden styleId="tempJuvNum" name="ProdSupportForm" property="juvenileId"/>
<html:hidden styleId="referralDt" name="ProdSupportForm" property="referralDate"/>
</html:form>

<html:form action="/viewPetitionDetailsQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>