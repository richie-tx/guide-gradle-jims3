<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<title>Juvenile Casework
	-/specialProcessing/viewJuvenilePurgeQueryResult.jsp</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/specialProcessing/juvenilePurging.js"></script>

<script type='text/javascript'>
	function textLimit(field, maxlen) {
		console.log(field.value.length);
		if (field.value.length > maxlen) {
			alert("Your comment has been truncated to " + maxlen
					+ " characters, the maximum allowed.");
			field.value = field.value.substring(0, maxlen);
		}

	}

	function closeWindow(el) {
		window.close();
		return 0;
	}
</script>
<html:base />
</head>

<html:form action="/PerformJuvenilePurging" onsubmit="return this;">

	<h2 align="center">Juvenile Master Purge Results</h2>
	<br>
	<hr>
	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><font color="red"><html:errors></html:errors>
			</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
	</table>
	<logic:notEmpty name="ProdSupportForm" property="juveniles">
		<p>&nbsp;</p>

		<logic:iterate id="juveniles" name="ProdSupportForm"
			property="juveniles">
			<table border="1" width="900" align="center">
				<elogic:if name="juveniles" property="recType" op="equal"
					value="S.JUVENILE">
					<elogic:then>
						<tr>
							<td align="center"><h4>
									<i>Juvenile Master Record is Sealed. This record does not
										qualify for Juvenile Master Purge.</i>
								</h4></td>
						</tr>
					</elogic:then>
				</elogic:if>
				<elogic:if name="juveniles" property="recType" op="equal"
					value="I.JUVENILE">
					<elogic:then>
						<tr>
							<td align="center"><h4>
									<i>Juvenile Master Record is purged and does not qualify
										for purge processing.</i>
								</h4></td>
						</tr>
					</elogic:then>
				</elogic:if>
				<elogic:if name="ProdSupportForm" property="hasActiveReferral"
					op="equal" value="Y">
					<elogic:then>
						<tr>
							<td align="center"><h4>
									<i>Juvenile has one or more active referral numbers which
										requires closure. This record does not qualify for Juvenile
										Master Purge. </i>
								</h4></td>
						</tr>
					</elogic:then>
				</elogic:if>
				<elogic:if name="ProdSupportForm" property="hasActiveCasefile"
					op="equal" value="Y">
					<elogic:then>
						<tr>
							<td align="center"><h4>
									<i>Juvenile has one or more supervisions not equal to
										CLOSED. All casefiles must be CLOSED to process purge.</i>
								</h4></td>
						</tr>
					</elogic:then>
				</elogic:if>
				<elogic:if name="juveniles" property="recType" op="notequal"
					value="S.JUVENILE">
					<elogic:then>
						<elogic:if name="juveniles" property="recType" op="notequal"
							value="I.JUVENILE">
							<elogic:then>
								<elogic:if name="ProdSupportForm" property="hasActiveReferral"
									op="notequal" value="Y">
									<elogic:then>
										<elogic:if name="ProdSupportForm" property="hasActiveCasefile"
											op="notequal" value="Y">
											<elogic:then>
												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">JUVENILE NUMBER</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="juvenileNum" />&nbsp;</font></td>
												</tr>
												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">JUVENILE NAME</font></td>
													<td><font size="-1"> <bean:write
																name="juveniles" property="firstName" />&nbsp; <bean:write
																name="juveniles" property="middleName" />&nbsp; <bean:write
																name="juveniles" property="lastName" />&nbsp; <bean:write
																name="juveniles" property="nameSuffix" /> </font>
													</td>
												</tr>
												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">RECORD TYPE</font></td>
													<td><font size="-1"> <elogic:if
																name="juveniles" property="recType" op="equal"
																value="JUVENILE">
																<elogic:then>
																	<font size="-1"> ACTIVE</font>
																</elogic:then>
															</elogic:if> <elogic:if name="juveniles" property="recType"
																op="equal" value="I.JUVENILE">
																<elogic:then>
																	<font size="-1"> PURGED </font>
																</elogic:then>
															</elogic:if> <input type="hidden" id="recordTye"
															value='<bean:write  name="juveniles" property="recType" />' />
													</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">DOB</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="dateOfBirth"
																formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">SEX</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="sex" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">RACE</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="race" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">HISPANIC</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="hispanic" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">SSN</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="completeSSN" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">MASTER STATUS</font></td>
													<td><font size="-1"><bean:write
																name="juveniles" property="statusId" />&nbsp;</font></td>
												</tr>
												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">Purge Series</font></td>
													<td><font size="-1"><html:text
																name="ProdSupportForm" property="purgeSerNum"
																styleId="purgeSerNum" size="4" maxlength="3" />&nbsp;</font></td>
												</tr>

												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">Purge Box</font></td>
													<td><font size="-1"><html:text
																name="ProdSupportForm" property="purgeBoxNum"
																styleId="purgeBoxNum" size="4" maxlength="3" />&nbsp;</font></td>
												</tr>
												<br />
												<tr>
													<td bgcolor="gray"><font color="white" face="bold"
														size="-1">Comments</font>
													</td>
													<td class="formDeLabel" colspan="6"><html:textarea
															name="ProdSupportForm" property="purgeComments" rows="2"
															styleId='comments' style="width:100%"
															onchange="textLimit(this, 50);"
															onkeyup="textLimit(this, 50);" /></td>
												</tr>
												<br />
											</elogic:then>
										</elogic:if>
									</elogic:then>
								</elogic:if>
							</elogic:then>
						</elogic:if>
					</elogic:then>
				</elogic:if>
			</table>

		</logic:iterate>
	</logic:notEmpty>
	<br />

	<logic:empty name="ProdSupportForm" property="juveniles">
		<table align="center" border="0" width="700">
			<tr>
				<td align="center"><h4>
						<i>No Juvenile Details found.</i>
					</h4></td>
			</tr>
		</table>
	</logic:empty>

	<table border="0" width="700">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr align="center">
			<td colspan="4"><font style="font-weight: bold;" color="#FF0000"
				size="3" face="Arial"> <logic:notEqual name="ProdSupportForm"
						property="msg" value="">
						<bean:write name="ProdSupportForm" property="msg" />
					</logic:notEqual> </font>
			</td>
		</tr>
	</table>

	<table align="center" border="0" width="10%">
		<elogic:if name="ProdSupportForm" property="rectype" op="notequal"
			value="S.JUVENILE">
			<elogic:then>
				<elogic:if name="ProdSupportForm" property="rectype" op="notequal"
					value="I.JUVENILE">
					<elogic:then>
						<elogic:if name="ProdSupportForm" property="hasActiveReferral"
							op="notequal" value="Y">
							<elogic:then>
								<elogic:if name="ProdSupportForm" property="hasActiveCasefile"
									op="notequal" value="Y">
									<elogic:then>
										<tr>
											<td colspan="2" align="center">
												<!--<html:submit property="submitAction" styleId="submitBtn">CONFIRM JUVENILE SEAL</html:submit> -->
												<input type="button" id="submitBtn"
												value="Confirm Juvenile Purge" />
											</td>
										</tr>
									</elogic:then>
								</elogic:if>
							</elogic:then>
						</elogic:if>
					</elogic:then>
				</elogic:if>
			</elogic:then>
		</elogic:if>

		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="goNav('back')">
					<bean:message key="button.back"></bean:message>
				</html:button></td>
			<td><input type="button" onclick="return closeWindow(this.form)"
				id="BtnCancel" value="<bean:message key='button.cancel'/>" />
			</td>
		</tr>
	</table>
	<br />

	<html:hidden styleId="activeRef" name="ProdSupportForm"
		property="hasActiveReferral" />
	<html:hidden styleId="activeCas" name="ProdSupportForm"
		property="hasActiveCasefile" />
	<html:hidden styleId="activeFac" name="ProdSupportForm"
		property="hasActiveFacility" />
</html:form>

</html:html>