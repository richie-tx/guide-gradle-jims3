<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/04/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 08/05/2009 L Deen - Added labels for phones -->
<!-- 07/10/2012 R Capestani - Added Apt/Suite to addresses and location fax -->
<!-- 04/03/2013 R Capestani - Changes for ER 75386 -->
<!-- 05/24/2013 C Shimek    - #75549 revised layout of SP data from location then program to program teh location -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/spPacket.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>

<style>
.boldTextPacket{
	color: #000000;
	font-family: Arial,Helvetica,sans-serif;
	font-size: 12px;
	font-weight: bold;
	text-decoration: none;
}

.formDeLabelPacket {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #000000;
	background-color: #FFFFFF;
	padding-right:5px;
}

.programFloatRight {
	float:right; 
	padding-right:5px;
}

.smallTextNobr {
   font-family: Arial, Helvetica, sans-serif;
   font-size: 11px;
   text-decoration:none;
   font-style:normal;
   font-weight:normal;
   white-space: nowrap;
   vertical-align: inherit;
}
.borderTableNobottom {
	border-style:solid;
	border-width:1px;
	border-color:#000000;
	border-bottom:0px;
}
.borderTableNotop {
	border-style:solid;
	border-width:1px;
	border-color:#000000;
	border-top:0px;
}
</style>

</head>
<body topmargin="0" leftmargin="0">
<div align="center">
<!-- BEGIN DETAILS ALIGNMENT TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
				       <table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/> - Service Provider Packet</td>
								<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|#">
							</tr>
						</table>
<!-- END HEADING TABLE -->
<!-- BEGIN REFERRAL TYPES TABLE -->
						<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableNobottom">
							<tr>
								<td class="formDeLabelPacket">Referral Types</td>
							</tr>
						</table>	
						<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableNotop">
							<tr>
								<td>
									<logic:iterate id="referralsIndex" name="cscProgRefForm" property="selectedReferralTypesList">
										<div><bean:write name="referralsIndex" property="referralTypeDesc" /></div>
									</logic:iterate>
								</td>
							</tr>
						</table>
<!-- END REFERRAL TYPES TABLE -->
						<div class="spacer4px"></div>
						<div class="spacer4px"></div>
<!-- BEGIN SP PACKET -->
						<nested:iterate id="spPacketIndex" name="cscProgRefForm" property="spPacketBeanList" > 
<!-- BEGIN SP DETAIL TABLE -->
							<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableNobottom">
								<tr>  
									<td width="60%">Service Provider:<strong> 
										<bean:write name="spPacketIndex" property="serviceProviderName" />
										<logic:equal name="spPacketIndex" property="spFaithBased" value="true">
										<span class="smallTextNobr">(Faith Based)</span>
										</logic:equal> </strong>
									</td>
									<td width="5%" nowrap="nowrap"><bean:write name="spPacketIndex" property="serviceProviderPhone" /></td>
									<td width="35%" align="right"><a href='mailto:<bean:write name="spPacketIndex" property="serviceProviderEmail"/>'><bean:write name="spPacketIndex" property="serviceProviderEmail"/></a></td>
								</tr>
							</table>	
<!-- END SP DETAIL TABLE -->
<!-- BEGIN PROGRAMS DETAIL TABLE -->							
							<table width="100%" cellpadding="1" cellspacing="0" class="borderTableNobottom">
								<nested:iterate id="programLocIndex" name="spPacketIndex" property="programLocList" length="1">
									<nested:iterate id="programIndex" name="programLocIndex" property="programBeanList">
										<tr>
											<td width="70%">
												<logic:notEqual name="programIndex" property="programPrice" value="">
												<span class="programFloatRight">$<bean:write name="programIndex" property="programPrice" /></span>
												</logic:notEqual>
												<span class="boldTextPacket"><bean:write name="programIndex" property="programName" /></span>
											</td>
											<td>
												<nested:iterate id="eachLanguage" name="programIndex" property="languagesOffered">
												<bean:write name="eachLanguage" />
												</nested:iterate>
											</td>
										</tr>
									</nested:iterate>
								</nested:iterate> 
							</table>  
<!-- END PROGRAMS DETAIL TABLE -->
<!-- BEGIN LOCATIONS DETAIL TABLE -->							  
							<table width="100%" cellpadding="1" cellspacing="0" class="innerBorderTableBlack">    
								<nested:iterate id="programLocIndex" name="spPacketIndex" property="programLocList">	 
									<tr>
										<td width="70%">
											<bean:write name="programLocIndex" property="streetNumber" />
											<bean:write name="programLocIndex" property="streetName" />
											<bean:write name="programLocIndex" property="streetTypeCd" />
											<logic:notEmpty name="programLocIndex" property="aptNum">
												Apt/Suite <bean:write name="programLocIndex" property="aptNum" />
											</logic:notEmpty>
											<bean:write name="programLocIndex" property="city" />,
											<bean:write name="programLocIndex" property="state" />
											<bean:write name="programLocIndex" property="zipCode" />
										</td>
										<td width="20%">
											<bean:write name="programLocIndex" property="locationPhone.formattedPhoneNumber" /><br>
											<bean:write name="programLocIndex" property="locationFax.formattedPhoneNumber" />
										</td>
										<td width="10%"><bean:write name="programLocIndex" property="locationRegionDesc" /></td>
									</tr>
								</nested:iterate>    
							</table>
<!-- END LOCATIONS DETAIL TABLE -->								
	                        <div class="spacer4px"></div>
    	                </nested:iterate>
                    <!-- END SP PACKET-->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END DETAILS ALIGNMENT TABLE -->
<!-- BEGIN BUTTON TABLE -->
<table width="100%" cellpadding="2" cellspacing="1" border="0">
	<tr>
		<td align="center">
			<input type="button" value="Print" onclick="window.print()">
			<input type="button" value="Close" onclick="window.close()">
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</div>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>