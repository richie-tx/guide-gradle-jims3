
<%-- START - This is the physical characteristics tile --%>
<%--MODIFICATIONS --%>
<%-- 06/17/2005	Hien Rodriguez	Create JSP --%>
<%-- 02/28/2006 Clarence Shimek Defect#27703 corrected row count in showSomethingFromDropDown() function call from 4 to 6 for new rows added --%>
<%-- 07/09/2007	LDeen			Defect #43502-Remove View All Photos button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - juvenilePhoto.jsp</title>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/getJuvenilePhoto">
<%-- BEGIN pics TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign=top class=detailHead>Juvenile Photos</td>
	</tr>
	<tr>
		<td align=center>
			<table>
			
				<logic:notEqual name="juvenilePhotoForm" property="selectedValue" value="">
				<tr>
						<td> <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Photo Detail" width="350" border=1>  </td>
					</tr>
					<tr>
						<td align=center>  </td>
					</tr>
				</logic:notEqual>
				<logic:equal name="juvenilePhotoForm" property="selectedValue" value="">
				<logic:greaterThan name='juvenilePhotoForm' property='totalPhotosAvailable' value='1'>
				<logic:iterate name="juvenilePhotoForm" property="allPhotos" id="photoIndex">
					<tr>
						<td> <a href="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&selectedValue=<bean:write name="photoIndex" property="photoName"/>"> <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Photo Thumbnail&selectedValue=<bean:write name='photoIndex' property='photoName'/>" width="128" border=1> </a></td>
					</tr>
					<tr>
						<td align=center> <bean:write name='photoIndex' property='entryDate' formatKey="date.format.mmddyyyy"/> </td>
					</tr>
					</logic:iterate>
				</logic:greaterThan>	
				</logic:equal>
				</table>
		</td>
	</tr>
</table>
<br>
<%-- END pics TABLE --%>
<!--Defect #43502<logic:greaterThan name='juvenilePhotoForm' property='totalPhotosAvailable' value='1'>
	<input type=button value="View All Photos" onclick="changeFormActionURL('juvenilePhotoForm','/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&selectedValue=',true)"/>
</logic:greaterThan>	-->
<input type=button value=Close onclick=window.close()>
<br>
<%-- END PHYSICAL Characteristics TABLE --%>
</html:form>
<%-- END FORM --%>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</body>
</html:html>