<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.common.StringUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="repBean" class="ui.juvenilecase.facility.JuvenileFacilityReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.FACILITY_ADMISSION_FORM.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.FACILITY_ADMISSION_FORM.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size:80; font-family:Helvetica; color:#F0F0F0; }
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;}
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
tr.border-bottom {
border-bottom: 1pt solid black;
}
tr.border-top {
border-top: 1pt solid black;
}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.row0 {
}
td.row1 {
background-color:#bfc1c2
}
p.arial-font-20
	{font-size:20;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}

p.arial-font-8-bold
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
p.left
	{text-align:left;}
hr.left
	{text-align:left;}
</style>
	<macrolist>
		<macro id="watermark1" >
			<p id="watermarkbody" rotate="-30" valign="middle" align="center">
			DRAFT
			</p>
		</macro>
		<macro id="watermark2" >
			<p id="watermarkbody" rotate="-30" valign="middle" align="center">
			CONFIDENTIAL
			</p>
		</macro>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				
				 <tr>
				 	
				 	<td align="center" width="100%">
				 		<p class="arial-font-8 centered">
							Page <pagenumber/> of <totalpages/>
						</p>
					</td>
					
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<c:if test="${repBean.draft == 'Y'}">
<body background-macro="watermark1" footer="myfooter" footer-height="0mm">
</c:if>
<c:if test="${repBean.draft != 'Y'}">
<body background-macro="watermark2" footer="myfooter" footer-height="0mm">
</c:if>
<div class="body">
<!--  Header information -->
	<table width="100%" border-style="none" align="center" cellpadding="0"> 
			<tr width="100%"> 
				<td width="10%" valign="top"> <p> <img height="40" width="40" src="images/HarrisCountySeal.jpg" /> </p> </td> 
				<td width="80%" align="center"> <p class="arial-font-10">HC JUVENILE FACILITY ADMISSION FORM</p></td> 
				<td align="right" width="1%"><p class="arial-font-10"><%=repBean.getThreeLettersOfLastname()%></p></td>				
			</tr> 
		</table> 
	
		<table width="100%" border-style="none" align="center" class="margin-top-10px"> 
			<tr align="center"> 
				
				<td align="center" width="1%">				
				    	
				<%	String printJuvPhotoPath = (String)session.getAttribute("printJuvPhotoPath");
					if(printJuvPhotoPath != null){ // don't show photo if does not exist
						String encodedURL = response.encodeURL(printJuvPhotoPath);					
						%>
						<img src="<%=encodedURL%>"  width="120" height="100"/>
				    <%}
				%>			   
			</td>
			</tr>
		 </table> 

	<table width="100%" border-style="none" align="center" class="margin-top-10px">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" width="100%" valign="top"><p class="arial-font-8"><b>FACILITY= <%=repBean.getDetainedFacility()%></b></p></td>
		</tr>
	</table>
	



	

	<table>
		<tr align="center">
			<td><p class="arial-font-8-bold">**JUV -</p></td>
			 <td width="25%"><p class="arial-font-8-bold">REF NO*</p></td>
			 <td><p class="arial-font-8-bold">SUPERVISION#**</p></td>
			 <td><p class="arial-font-8-bold">NAME/ADDRESS**</p></td>
			 <td><p class="arial-font-8-bold">RACE*</p></td>
			 <td><p class="arial-font-8-bold">HISPANIC*</p></td> 
			<td><p class="arial-font-8-bold">GENDER*</p></td>
			<td><p class="arial-font-8-bold">AGE**</p></td> 
			<td><p class="arial-font-8-bold">D.O.B**</p></td> 
			<td><p class="arial-font-8-bold">VER**</p></td> 
		</tr>
		<tr align="center">
			<td>
				<p class="arial-font-8"><%=repBean.getJuvenileNum()%></p></td> <td>
				<p class="arial-font-8"> <%=repBean.getBookingReferral()%></p></td> <td>
				<p class="arial-font-8"> <c:if test="${repBean.bookingSupervisionNum != null}"><%=repBean.getBookingSupervisionNum()%></c:if></p></td> <td>
				<p class="arial-font-8"><%=repBean.getJuvenileName()%></p></td> <td>
				<p class="arial-font-8">&nbsp;<%=repBean.getRace()%></p></td> <td>
				<p class="arial-font-8">&nbsp;<c:if test="${repBean.getHispanic() != null}"><%=repBean.getHispanic()%></c:if></p></td> <td>
				<p class="arial-font-8">&nbsp;<%=repBean.getGender()%></p></td> <td>
				<p class="arial-font-8">&nbsp;<%=repBean.getAge()%></p></td> <td> <p
					class="arial-font-8"><%=repBean.getDob()%></p></td> <td>
				<p class="arial-font-8"><%=repBean.getVerifiedDOB()%></p></td> 
		</tr>
		<tr align="center">
				<td></td> <td></td> <td></td> 
				<td colspan="8" align="left">
					<p class="arial-font-8"><c:if test="${repBean.getMemberAddress()!=null}"><%=StringUtil.isNotNull(repBean.getMemberAddress().getStreetNumber())%>
					<%=StringUtil.isNotNull(repBean.getMemberAddress().getStreetName())%> <%=repBean.getMemberAddress().getStreetType()%>
					<%=StringUtil.isNotNull(repBean.getMemberAddress().getAptNumber())%> <%=StringUtil.isNotNull(repBean.getMemberAddress().getCity())%>
					<%=StringUtil.isNotNull(repBean.getMemberAddress().getState())%></c:if></p></td> 
		</tr>
			<tr align="center">
			<td></td> <td></td> <td>
				<p class="arial-font-8-bold" align="right">COUNTY:</p></td> <td colspan="1">
				<p class="arial-font-8"><c:if test="${repBean.getMemberAddress()!=null}"><%=StringUtil.isNotNull(repBean.getMemberAddress().getCounty())%></c:if></p>
			</td>

			<td width="5%" align="right">
				<p class="arial-font-8-bold">ZIP:</p></td> <td>
				<p class="arial-font-8"><c:if test="${repBean.getMemberAddress()!=null}"><%=StringUtil.isNotNull(repBean.getMemberAddress().getZipCode())%>
					<c:if test="${repBean.memberAddress.additionalZipCode != ' '}">- </c:if><%=StringUtil.isNotNull(repBean.getMemberAddress().getAdditionalZipCode())%>
					</c:if></p>
			</td>

			<td colspan="3" align="right">
				<p class="arial-font-8-bold">SPECIAL ATTENTION</p></td> <td>
				<p class="arial-font-8" align="left"> 
					<c:if test="${repBean.spAttention == 'CO'}">CLOSE OBSERVATION</c:if>
					<c:if test="${repBean.spAttention == 'CW'}">CONSTANT WATCH</c:if> 
					<c:if test="${repBean.spAttention == 'NO'}">NONE</c:if> 
				</p>
			</td> 
		</tr>
	</table>
	<table>
		<tr> 
			
			<td colspan="1">
				<p class="arial-font-8-bold">SA REASON:</p>
			</td>
			<c:set var="saReasonsList"  value="${repBean.saReasons}" scope="session"/>
				<c:forEach var="saRs" items="${saReasonsList}" varStatus="status">			
					<td width="10%">		
							<p class="arial-font-8"><c:out value="${saRs}"/><c:if test="${saRs != 'Other'}">;</c:if>
		 					
							 <c:if test="${saRs == 'Other'}"> (See JIMS2)
		 						</c:if>
		 					
		 					</p>		
		 			</td>
				</c:forEach>
		</tr>
	
	</table>
	
	<table>
		<tr><td><p class="arial-font-8"></p></td></tr>
		<tr align="center">
			<td><p class="arial-font-8-bold">HEIGHT*</p></td>
			<td ><p class="arial-font-8-bold">WEIGHT***</p></td>
			<td><p class="arial-font-8-bold">EYES*************</p></td>
			<td><p class="arial-font-8-bold">SCARS/MARKS/TATTOOS*********************************************</p></td>
			<td><p class="arial-font-8-bold">AUTHORIZING JPO***</p></td>
			
		</tr>
		<tr align="center">
			<td><p class="arial-font-8"> 
				<c:if test="${repBean.physicalAttribute.heightFeet!=null}">
					<![CDATA[${repBean.physicalAttribute.heightFeet}]]>ft
				</c:if>
				<c:if test="${repBean.physicalAttribute.heightInch!=null}">
					<![CDATA[${repBean.physicalAttribute.heightInch}]]>in
				</c:if>
					
				</p>
			</td>
			<td><p class="arial-font-8">
				<c:if test="${repBean.physicalAttribute.weight!=null}">
					<![CDATA[${repBean.physicalAttribute.weight}]]>
				</c:if>
					
				</p>
			</td>
			<td><p class="arial-font-8">
				<c:if test="${repBean.physicalAttribute.eyeColor!=null}">
					<![CDATA[${repBean.physicalAttribute.eyeColor}]]>
				</c:if>
				</p>
			</td>
			<td><p class="arial-font-8">
			 	<c:if test="${repBean.physicalAttribute.tattoosDescription!=null}">
					<![CDATA[${repBean.physicalAttribute.tattoosDescription}]]>
				</c:if>
				</p>
			</td>
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getAdmitAuthority())%></p></td>
		 </tr>
	</table>
	
	
	<table>
		<tr><td></td></tr>
		<tr align="center">
			<td align="center" width="92%" valign="top"><p class="arial-font-8-bold">DETENTION TRAITS:</p></td>
		</tr>			
	</table>
	<table width="100%" border-style="none">
		
		<c:set var="detTraitList" value="${repBean.detentionTraits}" scope="session"/>
		 <c:forEach var="detTraits" items="${detTraitList}" varStatus="status">
			<tr>
				<td width="35%"><p class="arial-font-8"><c:out value="${detTraits.traitTypeDescription}"/>
				</p></td>				 
			</tr>
		 </c:forEach>
		 
		 <tr><td></td></tr>		
	</table>
	
	<table>
		<tr align="center">
			<td><p class="arial-font-8-bold">*SCHOOL****</p></td>
			<td><p class="arial-font-8-bold">STATUS*</p></td>
			<td><p class="arial-font-8-bold">GRADE****</p></td>
			<td><p class="arial-font-8-bold" align="left">PROGRAM ATTENDING***</p></td>
			<td><p class="arial-font-8-bold" align="left">RELIGIOUS PREFERENCES****</p></td>
			<td><p class="arial-font-8-bold">LIVES WITH******</p></td>
			<td><p class="arial-font-8-bold">MAR**</p></td>
			
			
		</tr>
		<tr align="center">
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getSchoolHistory().getSchoolDistrict())%></p></td>
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getEnrollmentStatus())%></p></td>
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getSchoolHistory().getGradeLevelDescription())%></p></td>
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getSchoolHistory().getProgramAttendingCode())%></p></td>
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getJuvReligiousPreference())%></p></td>
			<td>
				<table>
					<c:set var="relatives" value="${repBean.relatives}" scope="session"/>
					 <c:forEach var="juvRelatives" items="${relatives}" varStatus="status">	
					 <c:if test="${juvRelatives.inHomeStatus == true}" >
						<tr><td><p class="arial-font-8" align="left"><c:out value="${juvRelatives.relationToJuvenile}"/></p></td></tr>
					</c:if>
					</c:forEach>
				</table>
			</td>
			<td width="10%" ><p class="arial-font-8"><%=repBean.getMaritalStatus()%></p></td>
		
			
		</tr>
		</table>
		<table>
		
		 <tr><td></td></tr>		
	</table>
	<table>
		<tr align="center">
			<td><p class="arial-font-8"><%=StringUtil.isNotNull(repBean.getSchoolHistory().getSchool())%>&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
			<td width="20%"><p class="arial-font-8"></p></td>
			<td><p class="arial-font-8"><b>EMPLOYED:</b> <%=StringUtil.isNotNull(repBean.getEmploymentDet().getEmploymentStatusId())%></p></td>
			<td width="20%"><p class="arial-font-8"></p></td>
			<td><p class="arial-font-8"><b>EMP</b>: <%=StringUtil.isNotNull(repBean.getEmploymentDet().getEmploymentPlace())%></p></td>
		</tr>
		 <tr><td></td></tr>		
	</table>
	
	<table>
		<tr align="center">
			<td><p class="arial-font-8-bold">*RELATIVES NAME**********************************</p></td>
			<td ><p class="arial-font-8-bold">ADDRESS*************************************************************</p></td>
			<td><p class="arial-font-8-bold">PHONE*</p></td>	
		</tr>
	
		<c:set var="relatives" value="${repBean.relatives}" scope="session"/>
		 <c:forEach var="juvRelatives" items="${relatives}" varStatus="status">	
		 <c:if test="${juvRelatives.relationToJuvenileId == 'BF'}">
		 	<tr>
		 		<td align="left" width="25%"><p class="arial-font-8">F:			 			
 						<c:out value="${juvRelatives.firstName}"/> <c:out value="${juvRelatives.middleName}"/>
		 				 <c:out value="${juvRelatives.lastName}"/> - <c:out value="${juvRelatives.relationToJuvenile}" />		 		
		 		</p></td>		 	
		 		<td align="left" width="25%"><p class="arial-font-8">				
			 			<c:out value="${juvRelatives.memberAddress.streetNumber}"/>	<c:out value="${juvRelatives.memberAddress.streetName}"/>
			 			<c:out value="${juvRelatives.memberAddress.city}"/>	<c:out value="${juvRelatives.memberAddress.state}"/> 
			 			<c:out value="${juvRelatives.memberAddress.zipCode}"/><c:if test="${not empty juvRelatives.memberAddress.additionalZipCode}">-</c:if><c:out value="${juvRelatives.memberAddress.additionalZipCode}"/>			 			
	 			</p></td>	 		
			 		<td align="right" width="20%"><p class="arial-font-8"><c:out value="${juvRelatives.phone}"/></p></td>	
		 	 	</tr>	 
		 	</c:if>	 	
		 </c:forEach>
		<c:set var="relatives" value="${repBean.relatives}" scope="session"/>
		 <c:forEach var="juvRelatives" items="${relatives}" varStatus="status">
		 	<c:if test="${juvRelatives.relationToJuvenileId == 'BM'}">
		 	<tr>
		 		<td align="left" width="25%"><p class="arial-font-8">M: 		 			
		 			<c:out value="${juvRelatives.firstName}"/> <c:out value="${juvRelatives.middleName}"/>
		 			 <c:out value="${juvRelatives.lastName}"/> - <c:out value="${juvRelatives.relationToJuvenile}" />
		 		</p></td>
		 	 		
		 		<td align="left" width="25%"><p class="arial-font-8">		 			
		 			<c:out value="${juvRelatives.memberAddress.streetNumber}"/>	<c:out value="${juvRelatives.memberAddress.streetNumSuffix}"/> <c:out value="${juvRelatives.memberAddress.streetName}"/>
						 			<c:out value="${juvRelatives.memberAddress.streetTypeId}"/> <c:out value="${juvRelatives.memberAddress.aptNumber}"/>
			 						<c:out value="${juvRelatives.memberAddress.city}"/>	<c:out value="${juvRelatives.memberAddress.state}"/> 
			 						<c:out value="${juvRelatives.memberAddress.zipCode}"/><c:if test="${not empty juvRelatives.memberAddress.additionalZipCode}">-</c:if><c:out value="${juvRelatives.memberAddress.additionalZipCode}"/>		 			
		 		</p></td>	
		 		
		 		<td align="right" width="20%"><p class="arial-font-8">			 			 	
		 					<c:out value="${juvRelatives.phone}"/></p></td>	
		 				 	
		 	</tr>
		 	</c:if>
		 </c:forEach>
		 <c:set var="relatives" value="${repBean.relatives}" scope="session"/>
			 <c:forEach var="juvRelatives" items="${relatives}" varStatus="status">
			 	<c:if test="${juvRelatives.relationToJuvenileId != 'BF'}">
	 				<c:if test="${juvRelatives.relationToJuvenileId != 'BM'}">
	 					<c:if test="${juvRelatives.guardian == 'true'}">
	 						<tr>
		 						<td align="left" width="25%"><p class="arial-font-8">O: 
						 			<c:out value="${juvRelatives.firstName}"/> <c:out value="${juvRelatives.middleName}"/> <c:out value="${juvRelatives.lastName}"/>
						 		</p></td>				 		
						 		
						 		<td align="left" width="25%"><p class="arial-font-8"> 
						 		<c:if test="${juvRelatives.memberAddress != null}">
						 			<c:out value="${juvRelatives.memberAddress.streetNumber}"/>	<c:out value="${juvRelatives.memberAddress.streetNumSuffix}"/> <c:out value="${juvRelatives.memberAddress.streetName}"/>
						 			<c:out value="${juvRelatives.memberAddress.streetTypeId}"/> <c:out value="${juvRelatives.memberAddress.aptNumber}"/>
			 						<c:out value="${juvRelatives.memberAddress.city}"/>	<c:out value="${juvRelatives.memberAddress.state}"/> 
			 						<c:out value="${juvRelatives.memberAddress.zipCode}"/><c:if test="${not empty juvRelatives.memberAddress.additionalZipCode}">-</c:if><c:out value="${juvRelatives.memberAddress.additionalZipCode}"/>
			 						</c:if>		 		
						 		</p></td>
						 	
						 		<td align="right" width="20%"><p class="arial-font-8">O: 
						 			<c:out value="${juvRelatives.phone}"/>
						 		</p></td>
						 	</tr>
						 	<tr>
		 						<td align="left"><p class="arial-font-8">Other Relationship: 						 		
						 			  <c:out value="${juvRelatives.relationToJuvenile}" />
						 		</p></td>
						 	</tr>
				 		</c:if>
				 		<c:if test="${juvRelatives.primaryCareGiver == 'true'}">
	 						<tr>
		 					<td align="left" width="25%"><p class="arial-font-8">O: 
						 			<c:out value="${juvRelatives.firstName}"/> <c:out value="${juvRelatives.middleName}"/> <c:out value="${juvRelatives.lastName}"/>
						 		</p></td>
						 	
						 		<td align="left" width="25%"><p class="arial-font-8"> 
						 			<c:if test="${juvRelatives.memberAddress != null}">
						 			<c:out value="${juvRelatives.memberAddress.streetNumber}"/>	<c:out value="${juvRelatives.memberAddress.streetNumSuffix}"/> <c:out value="${juvRelatives.memberAddress.streetName}"/>
						 			<c:out value="${juvRelatives.memberAddress.streetTypeId}"/> <c:out value="${juvRelatives.memberAddress.aptNumber}"/>
			 						<c:out value="${juvRelatives.memberAddress.city}"/>	<c:out value="${juvRelatives.memberAddress.state}"/> 
			 						<c:out value="${juvRelatives.memberAddress.zipCode}"/><c:if test="${not empty juvRelatives.memberAddress.additionalZipCode}">-</c:if><c:out value="${juvRelatives.memberAddress.additionalZipCode}"/>
			 						</c:if>		 	 		
						 		</p></td>
						 		
						 		<td align="right" width="25%"><p class="arial-font-8"> 
						 			<c:out value="${juvRelatives.phone}"/>
						 		</p></td>
						 	</tr>
						 	<tr>
		 						<td align="left"><p class="arial-font-8">Other Relationship: 						 		
						 			  <c:out value="${juvRelatives.relationToJuvenile}" />
						 		</p></td>
						 	</tr>
				 		</c:if>
				 	</c:if>
	 			</c:if>
			</c:forEach>
			 <tr><td></td></tr>		
	</table>
	
	
	<table>
		<tr><td></td></tr>
		<tr align="center">
			<td><p class="arial-font-8-bold">COMMENTS: </p></td>
			<td><p class="arial-font-8"><bean:write name="admitReleaseForm" property="admissionComments"/></p></td>
		</tr>
		<tr><td></td></tr>
	</table>
	
	<table>
		<tr align="center">
			<td align="center"><p class="arial-font-8-bold">======================================CURRENT FACILITY INFORMATION=========================================== </p></td>
		</tr>
	</table>
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="center"><p class="arial-font-8-bold">ADMITTED BY</p></td>			
			<td align="center" ><p class="arial-font-8-bold">DATE IN</p></td>			
			<td align="center" ><p class="arial-font-8-bold">TIME IN</p></td>
			<td align="center"><p class="arial-font-8-bold">BOOKING OFFENSE</p></td>
			<td align="center" ><p class="arial-font-8-bold">REFERRAL SOURCE</p></td>
			<td align="center" ><p class="arial-font-8-bold">REFERRED BY</p></td>
		</tr>
	
		<tr>	
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="admitBy"/></p></td>		 			
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="admitDateStr"/></p></td>		 			
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="admitTime" /> </p></td>
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="bookingOffense"/></p></td> 		
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="referralSource"/> </p></td> 			
 			<td align="center"><p class="arial-font-8"><bean:write name="admitReleaseForm" property="referralOfficers"/></p></td>
 		</tr>
 	</table>
 
 	<table width="100%" border-style="none">
 	<tr><td></td></tr>
 		<tr align="center">
			<td align="center"><p class="arial-font-8"><b>VALUABLES RECEIPT#: </b><bean:write name="admitReleaseForm" property="valuablesReceipt"/></p></td>			
			<td align="center" ><p class="arial-font-8"><b>LOCKER#: </b><bean:write name="admitReleaseForm" property="locker"/></p></td>			
			<td align="center" ><p class="arial-font-8"><b>REASON: </b><bean:write name="admitReleaseForm" property="admitReasonStr"/></p></td>			
			<td align="center" ><p class="arial-font-8"><b>LOCATION: </b><bean:write name="admitReleaseForm" property="floorLocation"/><logic:notEmpty name="admitReleaseForm" property="unitLocation">/</logic:notEmpty><bean:write name="admitReleaseForm" property="unitLocation"/><logic:notEmpty name="admitReleaseForm" property="roomLocation">/</logic:notEmpty><bean:write name="admitReleaseForm" property="roomLocation"/><bean:write name="admitReleaseForm" property="multipleOccupancyUnit"/></p></td>
		
		</tr>
	 
	</table>	
	
	<table width="100%" border-style="none">
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr align="center">
			<td align="center"><p class="arial-font-8"><b>REL DATE: </b><bean:write name="admitReleaseForm" property="releaseDate"/></p></td>			
						<td align="center" ><p class="arial-font-8"><b>REL TIME: </b><logic:notEqual name="admitReleaseForm" property="releaseTime" value="00:00"> <bean:write name="admitReleaseForm" property="releaseTime"/></logic:notEqual></p></td>				
			<td align="center"><p class="arial-font-8"><b>REL TO: </b><bean:write name="admitReleaseForm" property="releasedTo"/></p></td>
			<td align="center" ><p class="arial-font-8"><b>REL BY: </b><bean:write name="admitReleaseForm" property="releasedBy"/></p></td>
			<td align="center" ><p class="arial-font-8"><b>REL AUTHORITY: </b><bean:write name="admitReleaseForm" property="releaseAuthority"/></p></td>
		</tr>
		 <tr><td></td></tr>
	</table>
	
</div>
<div>
	<table>
		<tr align="center">
			<td align="center"><p class="arial-font-8-bold">======================================FACILITY HISTORY INFORMATION============================================ </p></td>
		</tr>
	</table>
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="center"><p class="arial-font-8"></p></td>	
			<td align="center" ><p class="arial-font-8"></p></td>
			<td align="center" ><p class="arial-font-8-bold">&nbsp;&nbsp;&nbsp;ADMITTED</p></td>		
			<td align="center"><p class="arial-font-8"></p></td>
			<td align="center"><p class="arial-font-8"></p></td>
			<td align="center" ><p class="arial-font-8"></p></td>
			<td align="center" ><p class="arial-font-8"></p></td>
			<td align="center" ><p class="arial-font-8"></p></td>			
			<td align="center" ><p class="arial-font-8-bold">&nbsp;&nbsp;&nbsp;RELEASED</p></td>
			<td align="center" ><p class="arial-font-8"></p></td>	
		</tr>
		<tr align="center">
			<td align="center"><p class="arial-font-8-bold">REASON</p></td>		
			<td align="center"><p class="arial-font-8-bold">FACILITY</p></td>
			
			<td align="center"><p class="arial-font-8-bold">BY</p></td>
			<td align="center"><p class="arial-font-8-bold">DATE</p></td>
			<td align="center"><p class="arial-font-8-bold">TIME</p></td>
			<td align="center" ><p class="arial-font-8-bold"></p></td>
			<td align="center"><p class="arial-font-8-bold">DATE</p></td>
			<td align="center"><p class="arial-font-8-bold">TIME</p></td>
		
			<td align="center" ><p class="arial-font-8-bold">BY</p></td>
			<td align="center" ><p class="arial-font-8-bold">AUTHORITY</p></td>
		</tr>		
		
		<c:set var="juvFacList" value="${repBean.juvFacList}" scope="session"/>
	 		<c:forEach var="facilities" items="${juvFacList}" varStatus="status">
 				<tr>	
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.admitReason}"/> </p></td>	
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.detainedFacility}"/> </p></td>	
		 				
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.admittedByJPO}"/> </p></td>		
		 			<td align="center"><p class="arial-font-8">
		 				<fmt:formatDate value="${facilities.admitDate}" pattern="MM/dd/yyyy" var="formattedDate" /><c:out value="${formattedDate}"/> </p></td>		 			
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.admitTime}"/> </p></td>
		 			<td><p class="arial-font-8"></p></td>
		 			<td align="center"><p class="arial-font-8">
		 			<fmt:formatDate value="${facilities.releaseDate}" pattern="MM/dd/yyyy" var="formattedDate" /><c:out value="${formattedDate}"/> </p></td>
		 			<td align="center"><c:if test="${(facilities.releaseTime != '00:00')}"><p class="arial-font-8"><c:out value="${facilities.releaseTime}"/> </p></c:if></td>		 			
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.releaseBy}"/> </p></td>
		 			<td align="center"><p class="arial-font-8"><c:out value="${facilities.releaseAuthorizedBy}"/> </p></td>
		 		</tr>
	 		</c:forEach>
			
	</table>		
</div>
</body>
</pdf>
