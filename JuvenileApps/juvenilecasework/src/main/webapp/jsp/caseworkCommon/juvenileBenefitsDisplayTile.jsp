<!DOCTYPE HTML>

<%-- 12/15/2006 Hien Rodriguez  ER#37077 Add Button security feature --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add button --%>

<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<tiles:useAttribute id="tableTitle" name="tableTitle" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="addButton" name="addButton" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="buttonForward" name="buttonForward" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="showhide" name="showhide" classname="java.lang.String" ignore="true" />
<tiles:importAttribute name="benefits"/>
				 

<table align='center' width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
	<tr>
		<td class=detailHead>
			<logic:equal name="showhide" value="true">
				<logic:notEmpty name="benefits">
					<bean:size id="benefitsSize" name="benefits"/>
					<a href="javascript:showHideMulti('prevBenefits','pChar',<bean:write name='benefitsSize'/>+1, '/<msp:webapp/>');"><img border=0 src="/<msp:webapp/>images/expand.gif" name="prevBenefits"></a>
				</logic:notEmpty>
			</logic:equal>
			
			<logic:notEmpty name="tableTitle"><bean:write name="tableTitle"/></logic:notEmpty>
			<logic:empty name="tableTitle">Benefits Information</logic:empty>
		</td>
	</tr>
	<tr>
		<td align=center>														
			<table width='100%' border="0" cellpadding="2" cellspacing="1">
  			<logic:empty name="benefits">
    			<tr class=formDeLabel>
      			<td valign="top" colspan="4">No Benefit Information Available</td>
    			</tr>
  			</logic:empty>

  			<logic:notEmpty name="benefits">
  				<logic:equal name="showhide" value="true">
				
					<tr class='hidden' bgcolor="#F0F0F0" id='pChar0'>
				</logic:equal>
				<logic:notEqual name="showhide" value="true">
					<tr class=formDeLabel>
				</logic:notEqual>
					<td></td>	
					<td valign="top">Entry Date</td>
					<td valign="top">Eligibility Type</td>
					<td valign="top">Eligible</td>
					<td valign="top">Receiving</td>
					<td valign="top">Received By</td>
					<td valign="top">Amount</td>
					<td valign="top">ID No.</td>
					<td valign="top">Benefit Status</td>
				</tr>
				
				<%int RecordCounter = 0;
					String bgcolor = "";%>  
					<logic:iterate id="benefitsIter" name="benefits">
						
						<logic:equal name="showhide" value="true">
  						<tr class="hidden" bgcolor=<%RecordCounter++;
  							bgcolor = (RecordCounter % 2 == 1) ? "FFFFFF" : "F0F0F0";
  							out.print(bgcolor);
  							%> id="pChar<%=RecordCounter%>"
  						> 
						</logic:equal>
						
						<logic:notEqual name="showhide" value="true">
  						<tr class=<%RecordCounter++;
  							bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
  							out.print(bgcolor);%>
							>
						</logic:notEqual>
							
							<td>	
								<logic:notEmpty name="addButton">
									<logic:equal name="addButton" value="true">
										<logic:notEqual name="benefitsIter" property="benefitStatus" value="FORMER">
										<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
										<input type="radio" name="selectedValue" value='<bean:write name="benefitsIter" property="memberBenefitId"/>' styleId="<bean:write name="benefitsIter" property="memberBenefitId"/>- benefitId">
										</logic:notEqual>
										<logic:equal name="juvenileProfileHeader" property="status" value="closed">
										<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
											<input type="radio" name="selectedValue" value='<bean:write name="benefitsIter" property="memberBenefitId"/>' styleId="<bean:write name="benefitsIter" property="memberBenefitId"/>- benefitId">
											</jims2:isAllowed>
										</logic:equal>
										</logic:notEqual>
									</logic:equal>
									
								</logic:notEmpty>
							</td>
							<td valign="top"><bean:write name="benefitsIter" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
							<td valign="top"><bean:write name="benefitsIter" property="eligibilityType"/></td>
							<td valign="top">
  							<logic:equal name="benefitsIter" property="eligibleForBenefits" value="true">Yes</logic:equal>
								<logic:notEqual name="benefitsIter" property="eligibleForBenefits" value="true">No</logic:notEqual>
							</td>
							<td valign="top">
								<logic:equal name="benefitsIter" property="receivingBenefits" value="true">Yes</logic:equal>
								<logic:notEqual name="benefitsIter" property="receivingBenefits" value="true">No</logic:notEqual>
							</td>						
							<td><logic:notEqual name="benefitsIter" property="formattedName" value="NOT AVAILABLE"><bean:write name="benefitsIter" property="formattedName"/></logic:notEqual></td>
							<td>	<logic:equal name="benefitsIter" property="receivingBenefits" value="true"><bean:write name="benefitsIter" property="receivedAmt"/></logic:equal></td>
							<td><bean:write name="benefitsIter" property="idNumber"/></td>	
							<td><bean:write name="benefitsIter" property="benefitStatus"/></td>									
						</tr>
					</logic:iterate>
				</logic:notEmpty>
			</table>
			<table>
				<tr>
					<logic:notEmpty name="addButton">
						<logic:equal name="addButton" value="true">
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_BEN_U%>'>
								<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
								<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
									<td><input type=button value="Add Benefits" onclick="goNav('<bean:write name="buttonForward"/>')"></td>
									<td id="updateButton"><html:submit property="submitAction" styleId="benefitUpdate" disabled="true"><bean:message key="button.updateBenefitStatus"/> </html:submit></td>
								</logic:notEqual>
								<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
								<td><input type=button value="Add Benefits" onclick="goNav('<bean:write name="buttonForward"/>')"></td>
									<td id="updateButton"><html:submit property="submitAction" styleId="benefitUpdate" disabled="true"><bean:message key="button.updateBenefitStatus"/> </html:submit></td>
								</jims2:isAllowed>
								</logic:equal>
								</logic:equal>	
							</jims2:isAllowed>
						</logic:equal>
					</logic:notEmpty>
				</tr>
			</table>
		</td>
	</tr>
</table>
