
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

<html:html> 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateLastAttorneyQueryResult</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateLastAttorney.js"></script>

<script language="javascript">

function confirmUpdate(){
	
	if ((document.forms[0].atyBarNum!=null && document.forms[0].atyBarNum.value !="")&&(document.forms[0].atyName==null || document.forms[0].atyName.value=="")){
		alert("If ATYBARNUMBER is supplied, then ATYNAME is required. ");
		return false;
	}
	if ((document.forms[0].atyName!=null && document.forms[0].atyName.value !="")&&(document.forms[0].atyBarNum==null || document.forms[0].atyBarNum.value=="")){
		alert("If ATYNAME is supplied, then ATYBARNUMBER is required. ");
		return false;
	}
	if ((document.forms[0].atyName!=null && document.forms[0].atyName.value !="")&&(document.forms[0].atyBarNum!=null && document.forms[0].atyBarNum.value!="")&&(document.forms[0].attorneyConnection ==null || document.forms[0].attorneyConnection.value ==""))
	{
		alert("If ATYBARNUMBER and ATYNAME is supplied, then Attorney Connection Code is required.");
		return false;
	}
	if ((document.forms[0].galBarNum!=null && document.forms[0].galBarNum.value !="")&&(document.forms[0].galAttorneyName==null || document.forms[0].galAttorneyName.value=="")){
		alert("If GALBARNUMBER is supplied, then GALNAME is required. ");
		return false;
	}
	if ((document.forms[0].atyBarNum==null || document.forms[0].atyBarNum.value =="")&&(document.forms[0].atyName==null || document.forms[0].atyName.value=="")&&(document.forms[0].attorneyConnection !=null && document.forms[0].attorneyConnection.value !=""))
	{
		alert("If Attorney Connection Code is Supplied, then ATYBARNUMBER and ATYNAME are required. ");
		return false;
	}
	
	else 
	{
		if(confirm('Are you sure you want to update the Record?')) {
			spinner();
			return true;	
		} else {
			return false;
		}
	}

}
</script>
</head>
<body>
<html:form method="post" action="/PerformUpdateLastAttorney" onsubmit="return this;">
<div>
	
	<h2 align="center">Modify Last Attorney Details </h2>
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
			
			<tr>
						<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->		

<table class="resultTbl" border="1" width="700" align="center">
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileId"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileName" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Record Type</font></td>
		<td><font size="-1">
			<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="JUVENILE">
				<elogic:then>
					<font size="-1">ACTIVE</font>
				</elogic:then>	
			</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1">PURGED </font>
				</elogic:then>
			</elogic:if>
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
						<elogic:then>
						<font size="-1">SEALED </font>
				</elogic:then>
			</elogic:if>
			</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="birthDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileSsn" />&nbsp;</font></td>
		</tr>	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Current Chain Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="chainNum" />&nbsp;</font></td>
		</tr>			
		
	</table>  
   
     <table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>
	</table>
	<table class="resultTbl" border="1" width="800" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYBARNUMBER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.atyBarNum" />&nbsp;</font></td>
       <td><font size="-1"><html:text name="ProdSupportForm" property="atyBarNum" styleId='barNumber' size="9" maxlength="9"/></font>
		<html:button property="submitAction" styleId="validateBarNumBtn"><bean:message key="button.validateBarNumber"/></html:button> <br/>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYNAME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.atyName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="atyName"/></font>
		<html:hidden styleId="atyName" name="ProdSupportForm" property="atyName"></html:hidden>
		<html:button property="submitAction" styleId="searchAttorneyBtn"><bean:message key="button.searchAttorney"/></html:button> <br/>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONNECTIONCODE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.attConnect" />&nbsp;</font></td>
		<td> 
			<html:select name="ProdSupportForm" property="attorneyConnection" styleId='attorneyConnection'>
			<html:option key="select.generic" value="" /> 
			<html:option key="1" value="HAT" />
			<html:option key="2" value="AAT" />
			<html:option key="3" value="PDO" />			 
			</html:select>
		</td>
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GALBARNUMBER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.galBarNum" />&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="galBarNum" styleId='galBarNumber' size="9" maxlength="9"/></font>
		<html:button property="submitAction" styleId="validateGalBarNumBtn"><bean:message key="button.validateGalBarNumber"/></html:button> <br/>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GALNAME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.galName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="galAttorneyName"/></font>
		<html:hidden styleId="galAttorneyName" name="ProdSupportForm" property="galAttorneyName"></html:hidden>
		<html:button property="submitAction" styleId="searchGalAttorneyBtn"><bean:message key="button.searchGalAttorney"/></html:button> <br/>
		</td>
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUMBER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalDetentionId" />&nbsp;</font></td>
		
			<logic:notEmpty name="ProdSupportForm" property="juvDetCourtRecords">
				<td> 
					<html:select name="ProdSupportForm" property="detentionId" styleId='detentionCourtId'>
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="juvDetCourtRecords" value="docketEventId" label="docketEventId" 	 />		 
					</html:select>
				</td>
			</logic:notEmpty>
		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalCourtId" />&nbsp;</font></td>
	
			<logic:notEmpty name="ProdSupportForm" property="juvDistCourtRecords">
				<td> 
					<html:select name="ProdSupportForm" property="courtId" styleId='courtId'>
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="juvDistCourtRecords" value="docketEventId" label="docketEventId" 	 />
					</html:select>
				</td>
			</logic:notEmpty>
	
	</tr>				
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE DATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.createTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.updateUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LAST DATE/TIME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.updateTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE JIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE JIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	
	</table>

	<table align="center"">
	<tr><td>&nbsp;</td>
	</tr>
	<tr>
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="updateRec" onclick="return confirmUpdate();" ><bean:message key="button.updateRecord"></bean:message></html:submit>
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" />
	</td>
	</tr>
	</table>
	
	</div>

</html:form>

<html:form action="/UpdateLastAttorneyQuery?clr=Y" onsubmit="return this;">
<table align="center">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>