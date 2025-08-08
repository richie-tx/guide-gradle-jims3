
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

<html:html> 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateLastAttorneySummary</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
</head>

<body>

	<html:form action="/PerformUpdateLastAttorney" onsubmit="return this;">
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
				<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;" color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
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
						<td colspan="4"><font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> <bean:write name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			
			<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Updated</font></i></b></p>
			
		<table border="1" width="800" align="center">
	<tr>
		<logic:notEqual name="ProdSupportForm" property="atyBarNum" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYBARNUMBER</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="atyBarNum" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="attRecord.atyBarNum" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="atyBarNum" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYBARNUMBER</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.atyBarNum" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="atyName" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYNAME</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="atyName" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="attRecord.atyName" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="atyName" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ATYNAME</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.atyName" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="attorneyConnection" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">CONNECTIONCODE</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attorneyConnection" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="attRecord.attConnect" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="attorneyConnection" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">CONNECTIONCODE</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.attConnect" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="galBarNum" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GALBARNUMBER</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="galBarNum" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="attRecord.galBarNum" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="galBarNum" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GALBARNUMBER</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.galBarNum" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="galAttorneyName" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GALNAME</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="galAttorneyName" />&nbsp;</font></td>	
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="attRecord.galName" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="galAttorneyName" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GALNAME</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="attRecord.galName" />&nbsp;</font></td>	
		</logic:equal>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUMBER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
			<logic:notEmpty name="ProdSupportForm" property="originalDetentionId">
				<bean:define id="originalDetentionId">
	   				 <bean:write  name="ProdSupportForm" property="originalDetentionId" />
				</bean:define>
				<logic:notEqual name="ProdSupportForm" property="detentionId" value="<%= originalDetentionId  %>">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="detentionId" />&nbsp;</font></td>	
					<td>
						<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="originalDetentionId" /></font>
					</td>
				</logic:notEqual>
				<logic:equal name="ProdSupportForm" property="detentionId" value="<%= originalDetentionId  %>">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalDetentionId" />&nbsp;</font></td>	
				</logic:equal>
			</logic:notEmpty>
			
			<logic:empty name="ProdSupportForm" property="originalDetentionId">
				<logic:notEqual name="ProdSupportForm" property="detentionId" value="">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="detentionId" />&nbsp;</font></td>	
					<td>
						<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="originalDetentionId" /></font>
					</td>
				</logic:notEqual>
				<logic:equal name="ProdSupportForm" property="detentionId" value="">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalDetentionId" />&nbsp;</font></td>	
				</logic:equal>
			</logic:empty>
	</tr>
	<tr>
			
			<logic:notEmpty name="ProdSupportForm" property="originalCourtId">
				 <bean:define id="originalCourtId">
	   				 <bean:write  name="ProdSupportForm" property="originalCourtId" />
				 </bean:define>
				 <logic:notEqual name="ProdSupportForm" property="courtId" value="<%= originalCourtId %>">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="courtId" />&nbsp;</font></td>	
					<td>
						<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="originalCourtId" /></font>
					</td>
				 </logic:notEqual>
				 <logic:equal name="ProdSupportForm" property="courtId" value="<%= originalCourtId %>">
				 	<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalCourtId" />&nbsp;</font></td>	
				 </logic:equal>
			 </logic:notEmpty>
			 
			 
			<logic:empty name="ProdSupportForm" property="originalCourtId">
				<logic:notEqual name="ProdSupportForm" property="courtId" value="">
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="courtId" />&nbsp;</font></td>	
					<td>
						<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="originalCourtId" /></font>
					</td>
			 	</logic:notEqual>
			 	<logic:equal name="ProdSupportForm" property="courtId" value="">
			 		<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="originalCourtId" />&nbsp;</font></td>	
			 	</logic:equal>
			 </logic:empty>
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

		</div>

</html:form>
		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>
	</table>
	<table align="center">
		<tr>
		<td>
		<html:form method="post" action="/UpdateLastAttorneyQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Update Last Attorney" />
		</html:form>
		</td>
		<td>
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
		

</body>
</html:html>