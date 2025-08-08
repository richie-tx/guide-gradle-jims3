<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Courts List-->
<!-- 08/08/2005		awidjaja	Create JSP -->
<!-- 09/17/2009 D. Gibler - Added check of valueType on enumerationTypeId=Q to ensure proper validation -->
<!-- 03/12/2010	 Dawn Gibler - #64355 MSO - Allow Deselect of court on in-use condition -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
<title>Common Supervision - common/courts.jsp</title>
</head>

<tiles:useAttribute id="mode" name="mode" classname="java.lang.String" />
<tiles:useAttribute id="agcyId" name="agcyId" classname="java.lang.String" ignore="true" /> <!-- this attribute is optional-->
<tiles:useAttribute id="displayDDNA" name="displayDDNA" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->
<tiles:useAttribute id="ASOSpecialDisplay" name="ASOSpecialDisplay" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->
<tiles:useAttribute id="courtUpdateAllowed" name="courtUpdateAllowed" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->
<tiles:importAttribute name="beanName"/>

<logic:notPresent name="ASOSpecialDisplay">
	<logic:equal name="mode" value="select">
		<td colspan="2">
			
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
				<td><html:radio name="beanName" styleId="allCourtsSelected1" property="allCourtsSelected" value="true" onclick="show('courtSearchResults', 0)"/><bean:message key="prompt.allCourts" /></td>
			  </tr>
			  <tr>
				<td><html:radio name="beanName" styleId="allCourtsSelected2" property="allCourtsSelected" value="false" onclick="show('courtSearchResults', 1)"/><bean:message key="prompt.selectCourts" /></td>
			  </tr>
			</table>

			<logic:equal name="beanName" property="allCourtsSelected" value="true">
				<span id="courtSearchResults" class="hidden">
			</logic:equal>
			<logic:equal name="beanName" property="allCourtsSelected" value="false">
				<span id="courtSearchResults">
			</logic:equal> 
				
				<tiles:insert page="courtsList.jsp" flush="true">
					<tiles:put name="beanName" beanName="beanName" />
				</tiles:insert>
			<br>
			</span>
		</td>
	</logic:equal>
</logic:notPresent>
<logic:present name="ASOSpecialDisplay">
	<logic:equal name="mode" value="select">
		<td colspan="2">
				<html:hidden name="beanName" styleId="allCourtsSelected1" property="allCourtsSelected" value="false"/>
				<span id="courtSearchResults">

				<tiles:insert page="courtsList.jsp" flush="true">
					<tiles:put name="beanName" beanName="beanName" />
					<logic:present name="courtUpdateAllowed">
						<logic:equal name="courtUpdateAllowed" value="true">
							<tiles:put name="courtUpdateAllowed" value="true" />
						</logic:equal>
					</logic:present>
				</tiles:insert>
			<br>
			</span>
		</td>
	</logic:equal>
</logic:present>
	<logic:equal name="mode" value="view">
		<logic:notEmpty name="beanName" property="selectedCourts">
			<table width="100%" border="0" cellpadding="2" cellspacing="0">
				<tr>
				<td>
				<logic:iterate id="courtCategoryIter" name="beanName" property="selectedCourts">
					<table width="100%" border="0" cellpadding="2" cellspacing="0">
						<tr class="formDe">
							<td width="1%">
								<a href="javascript:showHide('list<bean:write name="courtCategoryIter" property="category"/>ParentExpand', 'span', '/<msp:webapp/>');" border="0">
								<img src=/<msp:webapp/>images/expand.gif name="list<bean:write name="courtCategoryIter" property="category"/>ParentExpand" border="0"></a>
							</td>
							<td class="boldText"><bean:write name="courtCategoryIter" property="categoryDesc"/></td>
					  </tr>
					</table>
					<span id="list<bean:write name="courtCategoryIter" property="category"/>ParentExpandSpan" class="hidden">
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="formDeLabel" width="10%"><bean:message key="prompt.court" /></td>
								<td class="formDeLabel" width="40%"><bean:message key="prompt.judgeDesc" /></td>
								<logic:present name="displayDDNA">
									<td class="formDeLabel"><bean:message key="prompt.detailsDoNotApply" /></td>
								</logic:present>
							</tr>
							<logic:iterate indexId="courtIndex" id="courtIter" name="courtCategoryIter" property="courts">
								<tr class="<%out.print((courtIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td><bean:write name="courtIter" property="courtNumber"/></td>
									<td>
										<logic:notEmpty name="courtIter" property="judgeLastName">
											<bean:write name="courtIter" property="judgeLastName"/>
											<logic:notEmpty name="courtIter" property="judgeFirstName">
												, <bean:write name="courtIter" property="judgeFirstName"/>
											</logic:notEmpty>
										</logic:notEmpty>
										<bean:write name="courtIter" property="description"/>
									</td>
									
									<%--<bean:define id="courtNo" name="courtIter" property="courtNumber" type="java.lang.String"/>--%>
									<logic:present name="displayDDNA">
										<td>
											<logic:equal name="courtIter" property="exceptionCourt" value="true">
												<bean:message key="prompt.x" />
											</logic:equal>
										</td>
									</logic:present>
								</tr>
							</logic:iterate>
						</table>
						<br>
					</span>
				</logic:iterate>
				</td>
				</tr>
			</table>
			
		</logic:notEmpty>
	</logic:equal>
	
	<!-- SET EXCEPTION COURTS -->
	<logic:equal name="mode" value="setExceptionCourts">
		<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr>
				<td class="detailHead">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="detailHead"><bean:message key="prompt.selectedCourts" /></td>
							<td align="right">
							<logic:notPresent name="agcyId">
							<img src="/<msp:webapp/>images/step_3.gif" vspace="0">
							</logic:notPresent>
							<logic:present name="agcyId">
							<img src="/<msp:webapp/>images/step_2.gif" vspace="0">
							</logic:present>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		 
				
			<logic:notEmpty name="beanName" property="selectedCourts">
			<input type="hidden" name="clearExceptionCourts" value="true">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="0">
						<tr>
						<td>
						<nested:iterate name="beanName" property="selectedCourtsArray">
							
							<table width="100%" border="0" cellpadding="2" cellspacing="0">
								<tr class="formDe">
									<td width="1%"><a href="javascript:showHide('list<nested:write property="category"/>ParentExpand', 'span', '/<msp:webapp/>');" border=0><img src=/<msp:webapp/>images/contract.gif name="list<nested:write property="category"/>ParentExpand" border="0"></a></td>
									<td class="boldText"><nested:write property="categoryDesc"/></td>
							  </tr>
							</table>
							
							<span id="list<nested:write property="category"/>ParentExpandSpan" class="visible">
								<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<tr>
										<td class="formDeLabel" width="10%"><bean:message key="prompt.court" /></td>
										<td class="formDeLabel" width="40%"><bean:message key="prompt.judgeDesc" /></td>
										<logic:notPresent name="agcyId">
										<td class="formDeLabel" ><bean:message key="prompt.detailsDoNotApply" /></td>
										</logic:notPresent>
									</tr>
									<nested:iterate property="courts" indexId="index">
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<td><nested:write property="courtNumber"/>&nbsp;</td>
										<td>
											<nested:notEmpty property="judgeLastName">
												<nested:write property="judgeLastName"/>
												<nested:notEmpty property="judgeFirstName">
													, <nested:write property="judgeFirstName"/>
												</nested:notEmpty>
											</nested:notEmpty>
											<nested:write property="description"/>&nbsp;
										</td>
										<logic:notPresent name="agcyId">
										<td>
												<nested:checkbox property="exceptionCourt" value="true"/>&nbsp;
										</td>
										</logic:notPresent>
									</tr>
									</nested:iterate>
								</table>
							</span>					
						</nested:iterate>
						</td>
						</tr>
					</table>	
				</td>
			</tr>
			</logic:notEmpty>
		</table>
		<br>
		
		<table width="9%" border="0" cellpadding="2" cellspacing="1">
			<tr class="detailHead">
				<td colspan="2">
					<table width="100%" cellspacing="0">
						<tr>
							<td class="detailHead"><bean:message key="prompt.setDetailsForSelectedCourts" /></td>
							<td align="right">
							<logic:notPresent name="agcyId">
							<img src="/<msp:webapp/>images/step_4.gif" vspace="0">
							</logic:notPresent>
							<logic:present name="agcyId">
							<img src="/<msp:webapp/>images/step_3.gif" vspace="0">
							</logic:present>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<logic:notEmpty name="beanName" property="variableElementResponseEventsArray">
			 <% boolean dateCounter=true; %>
			 <% int anchorCounter=0; %>
				<nested:iterate id="vere" name="beanName" property="variableElementResponseEventsArray">
				<% anchorCounter++; %>
					<nested:notEqual property="reference" value="true">
						<nested:notEmpty property="name">	
							<tr>
								<td class="formDeLabel" nowrap width="1%"><nested:write property="name"/></td>
								<td class="formDe">
								<%-- 	<nested:write property="enumerationTypeId"/>&nbsp;  --%>
								<%--	<nested:write property="valueType"/>&nbsp;  --%>
									<bean:define id="fieldName" type="java.lang.String"><nested:write property="name"/></bean:define>
									<nested:equal property="enumeration" value="true">
										<nested:equal property="enumerationTypeId" value="L">
											<nested:select property="valueId" size="1">
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<nested:optionsCollection property="codeValues" value="code" label="description" /> 
											</nested:select>																
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="R">
											<nested:select property="fixed" size="1">
												<html:option value="true">Fixed</html:option>
												<html:option value="false">Variable</html:option>
											</nested:select>
											<logic:iterate id="cdVals" name="vere" property="codeValues">
												<tr>
													<td colspan="2">
														<bean:define name="cdVals" id="valId" property="code" type="java.lang.String" />
														<nested:radio property="valueId" value="<%=valId%>" /> <bean:write name="cdVals" property="description" /> 
													</td>	
												</tr>	
											</logic:iterate>	
										</nested:equal>
									</nested:equal>
								
									<nested:equal property="enumeration" value="false">
										
										<nested:equal property="enumerationTypeId" value="D">
											<script>
											 	addMMDDYYYYDateValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.date" arg0="<%=fieldName%>"/>');
													<% if(dateCounter){ %>
													var cusVarCal = new CalendarPopup();
								      		 	cusVarCal.showYearNavigation();
								      		 	<%}dateCounter=false; %>
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="C">
											<script>
												addCurrencyValidation('<nested:writeNesting property="value"/>','<%=fieldName%> is not a valid currency. Please note no commas or dollar signs are allowed.  Example: for $1,000 enter 1000.00');
											</script>
											$
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="Q">
											<nested:equal property="valueType" value="SYMBOLS">
												<script>
													addAlphaNumericnSpacewSymbolsValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphanumeric" arg0="<%=fieldName%>"/>');
													customValMaxLength('<nested:writeNesting property="value"/>','<bean:message key="errors.maxlength" arg0="<%=fieldName%>" arg1="400"/>','400');
												</script>
											</nested:equal>
											<nested:notEqual property="valueType" value="SYMBOLS">
												<script>
													addAlphaNumericNoFirstSpacewSymbolsValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphanumeric" arg0="<%=fieldName%>"/>');
													customValMaxLength('<nested:writeNesting property="value"/>','<bean:message key="errors.maxlength" arg0="<%=fieldName%>" arg1="400"/>','400');
												</script>
											</nested:notEqual>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="A">
											<script>
												addAlphaValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphabetic" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="F">
											<script>
												addDB2FreeTextValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.freeTextDB2" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										
										<nested:equal property="enumerationTypeId" value="N">
											<script>
												addNumericValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.numeric" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="T">
											<nested:equal property="valueType" value="TIME_12HOUR">
												<script>
													add12HrTimeValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.12HourTime" arg0="<%=fieldName%>"/>');
												</script>
											</nested:equal>
											<nested:notEqual property="valueType" value="TIME_12HOUR">
												<script>
													add24HrTimeValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.24HourTime" arg0="<%=fieldName%>"/>');
												</script>
											</nested:notEqual>
										</nested:equal>
										
										<nested:text property="value"/>
										
										<nested:equal property="enumerationTypeId" value="D">
											    <A HREF="#" onClick="cusVarCal.select(((document.getElementsByName('<nested:writeNesting property="value"/>'))[0]),'anchor<%=anchorCounter%>','MM/dd/yyyy'); return false;" NAME="anchor<%=anchorCounter%>" ID="anchor<%=anchorCounter%>" border="0">
											    <img src="/<msp:webapp/>images/calendar2.gif" alt="calendarImage" title="(mm/dd/yyyy)" border="0"/>
											    </A>
										</nested:equal>
										
										
									</nested:equal>
									<nested:notEqual property="enumerationTypeId" value="R">
										<nested:select property="fixed" size="1">
											<html:option value="true">Fixed</html:option>
											<html:option value="false">Variable</html:option>
										</nested:select>
									</nested:notEqual>	
								</td>
							</tr>
						</nested:notEmpty>
					</nested:notEqual>	
				</nested:iterate>
			</logic:notEmpty>
		</table>
				
	</logic:equal>
	<!-- END FORM -->
					