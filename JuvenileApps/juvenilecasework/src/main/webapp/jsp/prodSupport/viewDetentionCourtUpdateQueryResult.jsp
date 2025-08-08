<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>


<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework
	-/prodSupport/viewDetentionCourtUpdateQueryResult</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/UpdateDetentionCourt.js?clr2=y"></script>

</head>

<body>

	<html:form action="/PerformUpdateDetentionCourtRecord"
		onsubmit="return this;">
		<!-- BEGIN Error Message Table -->
		<logic:messagesPresent message="true">
			<table width="100%">
				<tr>
					<td align="center" class="messageAlert"><html:messages
							id="message" message="true">
							<font style="font-weight: bold;" color="green" size="3"
								face="Arial"><bean:write name="message" />
						</html:messages></font></td>
				</tr>
			</table>
		</logic:messagesPresent>

		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
				<td align="center" colspan="4" class="errorAlert"><font
					style="font-weight: bold;" color="red" size="3" face="Arial"><html:errors></html:errors>
				</font></td>
			</tr>
		</table>
		<!-- END ERROR TABLE -->
		<div>

			<h2 align="center">
				Results for Juvenile ID =
				<bean:write name="ProdSupportForm" property="juvenileId" />
			</h2>

			<!-- Error Message Area -->
			<%-- <logic:notEqual name="ProdSupportForm" property="msg" value="">
				<table border="0" width="700" align="center">

					<tr align="center">
						<td colspan="4"><font style="font-weight: bold;"
							color="#FF0000" size="3" face="Arial"> <bean:write
									name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual> --%>
			<!-- End Error Message Area -->

			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Name</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="juvenileName" />&nbsp;</font>
					</td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Record Type</font>
					</td>
					<td><elogic:if name="ProdSupportForm" property="rectype"
							op="equal" value="S.JUVENILE">
							<elogic:then>
								<font size="-1"> SEALED </font>
							</elogic:then>
						</elogic:if> <elogic:if name="ProdSupportForm" property="rectype" op="equal"
							value="I.JUVENILE">
							<elogic:then>
								<font size="-1"> PURGED </font>
							</elogic:then>
						</elogic:if>
					</td>

				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Master Status</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="statusId" />&nbsp;</font></td>
				</tr>
			</table>
			<p align="center">
				<b><i>Change the values of the field(s). <br> and Click
						Update</i> </b>
			</p>
			<logic:notEmpty name="ProdSupportForm" property="juvDetCourtRecords">
				<logic:iterate id="juvDetCourtRecords" name="ProdSupportForm"
					property="juvDetCourtRecords">
					<h3 align="center">Detention Court Details</h3>

					<table class="resultTbl" border="1" width="800" align="center">

						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
									Number</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="juvenileNumber" />&nbsp;</font>
							</td>
							<td><font size="-1"><html:text
										name="juvDetCourtRecords" property="juvenileNumber" size="6"
										styleId='juvNum' maxlength="8" indexed="true" /> </font></td>
							<html:hidden styleId='oldJuvenileNum' name='juvDetCourtRecords'
								property='juvenileNumber'/>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
									Detention ID</font>
							</td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="detentionId" />&nbsp;</font>
								<html:hidden styleId='olddetentionId' name='juvDetCourtRecords' property='detentionId' />
							</td>								
							<td>
									<font size="-1"><html:text
											name="juvDetCourtRecords" property="detentionId"
											styleId='detentionId' size="10" maxlength="10" indexed="true" />
									</font>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">
									Court </font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="court" />&nbsp;</font></td>
							<td>
									<font size="-1"><html:text
											name="juvDetCourtRecords" property="court"
											styleId='courtId' size="3" maxlength="3" indexed="true" />
									</font>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray">
								<font color="white" face="bold" size="-1">
									Petition Number
								 </font>
							</td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="petitionNumber" />&nbsp;</font>
								<html:hidden styleId='oldPetitionNumber' name='juvDetCourtRecords' property='petitionNumber' />
							</td>			
							<td>
									<font size="-1"><html:text
											name="juvDetCourtRecords" property="petitionNumber"
											styleId='petitionNumber' size="10" maxlength="10" indexed="true" />
									</font>
							</td>
						</tr>										
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">
									Bar Number</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="oldbarNum" />&nbsp;</font></td>
							<td><font size="-1"><html:text
										name="juvDetCourtRecords" property="barNum"
										styleId='barNumber' size="9" maxlength="9" indexed="true" />
										</font> <%-- <html:button property="validateBarNum" value="Validate" styleId='<%="validateBarNumber."+docketEvtIdKey%>' indexed="true"></html:button> --%>
								<html:button property="submitAction" styleId="validateBarNumBtn">
									<bean:message key="button.validateBarNumber" />
								</html:button> <br />
							</td>
							<html:hidden styleId='oldbarNum' name='juvDetCourtRecords'
								property='barNum'/>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney
									Connection</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="attorneyConnection" />&nbsp;</font>
							</td>
							<html:hidden styleId='oldAttorneyConnection' name='juvDetCourtRecords'
								property='attorneyConnection'/>
							<%-- <td><font size="-1"><html:text name="juvDetCourtRecords" property="attorneyConnection" styleId='attorneyConnection' size="3" maxlength="3" indexed="true"/></font></td> --%>
							<td><html:select name="juvDetCourtRecords"
									property="attorneyConnection" styleId='attorneyConnection'
									indexed="true">
									<html:option key="select.generic" value="" />
									<html:option key="1" value="HAT" />
									<html:option key="2" value="AAT" />
									<html:option key="3" value="PDO" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney
									Name</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="oldattorneyName"/>&nbsp;</font>
							</td>							
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="attorneyName"/> </font> <html:button
									property="submitAction" styleId="searchAttorneyBtn">
									<bean:message key="button.searchAttorney" />
								</html:button> <br />
							</td>
							<html:hidden styleId='oldattorneyName' name='juvDetCourtRecords'
								property='oldattorneyName'/>
								<html:hidden styleId='newattorneyName' name='juvDetCourtRecords'
								property='attorneyName'/>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing
									Date</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="courtDate"
										formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
										<html:hidden styleId='oldCourtDate' name='juvDetCourtRecords'
								property='courtDate'/>
							<td><font size="-1"><html:text
										name="juvDetCourtRecords" property="courtDate"
										styleId='hrgDate' size="10" maxlength="10" indexed="true" />
							</font></td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing
									Decision Result</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="courtResult" />&nbsp;</font></td>
										<html:hidden styleId='oldCourtResult' name='juvDetCourtRecords'
								property='courtResult'/>
							<td><html:select name="juvDetCourtRecords"
									property="courtResult" styleId='crtResult' indexed="true">
									<html:option key="select.generic" value="" />
									<html:optionsCollection name="ProdSupportForm"
										property="hearingDecisionResults" value="code"
										label="code" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing
									Type</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="hearingType" />&nbsp;</font></td>
										<html:hidden styleId='oldHearingType' name='juvDetCourtRecords'
								property='hearingType'/>
							<%-- <td><font size="-1"><html:text name="juvDetCourtRecords" property="hearingType" size="2" styleId='hearingType' indexed="true"/></font></td> --%>
							<td><html:select name="juvDetCourtRecords"
									property="hearingType" styleId='hearingType' indexed="true">
									<html:option key="select.generic" value="" />
									<html:option key="1" value="DT" />
									<html:option key="2" value="PC" />
									<%-- <html:optionsCollection property="courtHearingTypes" value="code" label="code"/>  --%>
								</html:select>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral
									Number</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="referralNum" />&nbsp;</font></td>
						<html:hidden styleId='oldReferralNum' name='juvDetCourtRecords'
								property='referralNum'/>
							<td><html:select name="juvDetCourtRecords"
									property="referralNum" styleId='bookingRefNum'
									style="width:105px" indexed="true">
									<option value="" selected="selected">
										<bean:message key="select.generic" />
									</option>
									<html:optionsCollection name="ProdSupportForm"
										property="bookingReferrals" value="description"
										label="description" />
								</html:select></td>						

						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence 
									Number</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="seqNum" />&nbsp;</font>
							</td>
								
								<html:hidden styleId='oldJuvseqnum' name='juvDetCourtRecords' property='seqNum'/>
							<td>
									<font size="-1"><html:text
											name="juvDetCourtRecords" property="seqNum"
											styleId='seqNum' size="10" maxlength="10" indexed="true" />
									</font>
							</td>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Last
									Change Date/Time</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="lcDt"
										formatKey="date.format.mmddyyyy" />&nbsp;</font> <font size="-1"><bean:write
										name="juvDetCourtRecords" property="lcTime" />&nbsp;</font>
							</td>
							<%-- <td><font size="-1"><html:text name="juvDetCourtRecords" property="lcDate" styleId='lcDate' size="10" indexed="true"/></font></td> --%>
						</tr>
						<tr>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Last
									Change User</font></td>
							<td><font size="-1"><bean:write
										name="juvDetCourtRecords" property="lcUser" />&nbsp;</font></td>
							<%-- <td><font size="-1"><html:text name="juvDetCourtRecords" property="lcUser" styleId='lcUser' size="5" indexed="true"/></font></td> --%>
						</tr>

					</table>
				</logic:iterate>
			</logic:notEmpty>
			<BR>

			<table align="center"">

				<logic:notEmpty name="ProdSupportForm" property="juvDetCourtRecords">
					<td>
						<p align="center">
							<html:submit property="submitAction" styleId="updateBtn"
								onclick="return disableSubmit(this, this.form);">
								<bean:message key="button.updateRecord" />
							</html:submit>
						</p>
					</td>
				</logic:notEmpty>

			</table>


			<logic:empty name="ProdSupportForm" property="juvDetCourtRecords">
				<br>
				<table align="center" border="1" width="700">
					<tr>
						<td>
							<h3 align="center">
								<font color="green"><i>No Records Returned</i> </font>
							</h3>
						</td>
					</tr>
				</table>
			</logic:empty>

		</div>


		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>

			<tr>
				<td><html:button property="submitAction" styleId="backToQryBtn">
						<bean:message key="button.backToSearch"></bean:message>
					</html:button>
				</td>
			</tr>
		</table>
		<html:hidden styleId="prevJuvNum" name="ProdSupportForm"
			property="fromJuvenileId" />
		<html:hidden styleId="juvenileNum" name="ProdSupportForm"
			property="juvenileId" />
		<html:hidden styleId="referralNum" name="ProdSupportForm"
			property="referralId" />
	</html:form>

</body>
</html:html>