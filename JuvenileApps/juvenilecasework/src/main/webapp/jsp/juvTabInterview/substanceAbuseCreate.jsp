<!DOCTYPE HTML>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - substanceAbuseCreate.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileDrugForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/substanceAbuse.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script>
	$(document).ready(function(){
		loadData();
		removeData();
		
		$("#associatedCasefile").val('<bean:write name="juvenileDrugForm" property="associateCasefile" />');
		<logic:iterate id="juvenileCasefile" name="juvenileDrugForm" property="juvenileCasfileResps">
			if ( $("#associatedCasefile").val() == '<bean:write name="juvenileCasefile" property="supervisionNum"/>'){
				$("#referralNumber").html( '<bean:write name="juvenileCasefile" property="assignedReferrals"/>' );
			} else if ( $("#associatedCasefile").val() == "" ){
				$("#referralNumber").html("");
				$("#referralNum").val("");
			}
		</logic:iterate>
		
		$("#associatedCasefile").change( function() {
			<logic:iterate id="juvenileCasefile" name="juvenileDrugForm" property="juvenileCasfileResps">
				if ( $("#associatedCasefile").val() == '<bean:write name="juvenileCasefile" property="supervisionNum"/>'){
					$("#referralNumber").html( '<bean:write name="juvenileCasefile" property="assignedReferrals"/>' );
				} else if ( $("#associatedCasefile").val() == "" ){
					$("#referralNumber").html("");
					$("#referralNum").val("");
				}
			</logic:iterate>
		});
	})
		
		
</script>

</head> 
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Substance Abuse</td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>
	<logic:notEqual name="juvenileDrugForm" property="msg" value="">
		<table border="0" width="700" align="center">
			<tr align="center">
				<td class="confirm" colspan="4"><font
					style="font-weight: bold;" color="#FF0000" size="3" face="Arial">
						<bean:write name="juvenileDrugForm" property="msg" />
				</td>
			</tr>
		</table>
	</logic:notEqual>
	<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<html:form styleId="juvenileDrugTestingForm" action="/handleSubstanceAbuseCreateAction" target="content">
<html:hidden styleId="referralNum" name="juvenileDrugForm" property="referralNum"/>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
			  	</tr>
			</table>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
				<tr>
					<td valign=top align=center>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign=top>
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="DRUGS" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
							</td>
						</tr>
						<tr>
					  		<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
					  	</tr>
			  		</table>
					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
						</tr>
						<tr>
							<td valign=top align=center>
									<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td colspan=6 class=detailHead>Substance Abuse(TJJD Required)</td>
										</tr>
										<tr>
											<td width='24%' class=formDeLabel>Associated Casefile #</td>
											<logic:notEmpty name="juvenileDrugForm" property="juvenileCasfileResps"> 	
												<td width='25%' class=formDe>
													<html:select styleId="associatedCasefile" name="juvenileDrugForm" property="associateCasefile">
														<html:option key="select.generic" value="" />
														<html:optionsCollection name="juvenileDrugForm" property="juvenileCasfileResps" value="supervisionNum" label="supervisionNumWithSupervisionType"/>
														
													</html:select>
												</td>
											</logic:notEmpty>
											<td width='24%' class=formDeLabel>Referral Number</td>
											<td width='25%' width='25%' id="referralNumber" class=formDe>
											</td>
										</tr>
										<tr>
											<td width='24%' class=formDeLabel>Substance Abuse ?</td>	
											<td width='25%' class=formDe>
												<html:select styleId="substanceAbuse" name="juvenileDrugForm" property="substanceAbuse">
													<html:option key="select.generic" value="" />
													<html:optionsCollection name="juvenileDrugForm" property="tjjdSubstanceAbuseCodes" value="code" label="description"/>
												</html:select>
											</td>
											<td width='24%' class=formDeLabel>Substance Type</td>
											<td width='25%' width='25%' class=formDe>
												<html:select styleId="substancesType" name="juvenileDrugForm" property="substancesType" multiple = "true">
													<html:option key="select.generic" value="" disabled="true" />
													<html:optionsCollection name="juvenileDrugForm" property="drugTypeCodes" value="code" label="description"/>
												</html:select>
											</td>
										</tr>
										<tr class="hidden" id="treatmentLoc">
											<td width='24%' class=formDeLabel>Location for treatment</td>
											<td width='25%' width='25%' class=formDe>
												<html:textarea rows="2" style="width:100%" name="juvenileDrugForm" property="treatmentLocation" onmouseout="textCounter(this,100)" onkeyup="textCounter(this,100)" styleId="loc"></html:textarea>
											</td>
											<td width='24%' class=formDeLabel></td>
											<td width='25%' width='25%' class=formDe></td>										
										</tr>
										<tr>
											<div class=spacer></div>		
												<table width='98%'>	
													<tr>
														<td align="center">
															<input type="button" value="Back" onclick="history.back();"/>
															<html:submit property="submitAction" styleId="addSubstanceAbuse">				
																<bean:message key="button.add"></bean:message>
															</html:submit>			
															<html:submit property="submitAction">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>
														</td>
													</tr>
												</table>
											<div class=spacer></div>
									</tr>
									</table>
								</td>
							</tr>
							
						</table>
    				</td>
    			</tr>
    			<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
    		</table>
    	</td>
    </tr>
</table>
</html:form>


<div align=center><script type="text/javascript">renderBackToTop()</script>
</body>
</html:html>
