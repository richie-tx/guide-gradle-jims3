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
<tiles:importAttribute name="insurances"/>

<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
	<tr>
		<td class=detailHead>
			<logic:equal name="showhide" value="true">
				<logic:notEmpty name="insurances">
					<bean:size id="insurancesSize" name="insurances"/>
					<a href="javascript:showHideMulti('prevInsurances','pChar',<bean:write name='insurancesSize'/>+1, '/<msp:webapp/>');"><img border=0 src="/<msp:webapp/>images/expand.gif" name="prevInsurances"></a>
				</logic:notEmpty>
			</logic:equal>
			
			<logic:notEmpty name="tableTitle">
				<bean:write name="tableTitle"/>
			</logic:notEmpty>
			<logic:empty name="tableTitle">Insurance Information</logic:empty>
		</td>
	</tr>
	<tr>
		<td valign="top" align=center>														
			<table width='100%' border="0" cellpadding="2" cellspacing="1">
	  			<logic:empty name="insurances">
	    			<tr class=formDeLabel>
		      			<td valign="top" colspan="4">No Insurance Information Available</td>
	    			</tr>
   				</logic:empty>
 				<logic:notEmpty name="insurances">
  					<logic:equal name="showhide" value="true">
  						<tr class='hidden' bgcolor="#F0F0F0" id='pChar0'>
  					</logic:equal>
  					<logic:notEqual name="showhide" value="true">
  						<tr class='formDeLabel'>
  					</logic:notEqual>	
 					<td valign="top">Entry Date</td>
 					<td valign="top">Insurance Type</td>
 					<td valign="top">Policy Number</td>
 					<td valign="top">Insurance Carrier</td>
 				</tr>
				
				<%int RecordCounter = 0;
					String bgcolor = "";%>  
					<logic:iterate id="insurancesIter" name="insurances">
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
  							<td valign="top"><bean:write name="insurancesIter" property="entryDate" format="MM/dd/yyyy"/></td>
  							<td valign="top"><bean:write name="insurancesIter" property="insuranceType"/></td>
  							<td valign="top"><bean:write name="insurancesIter" property="policyNumber"/></td>
  							<td valign="top"><bean:write name="insurancesIter" property="insuranceCarrier"/></td>
  						</tr>
  					</logic:iterate>
				</logic:notEmpty>
			</table>
			<logic:notEmpty name="addButton">
				<logic:equal name="addButton" value="true">
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_BEN_U%>'>
						<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
						<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
							<input type=button value="Add Insurance" onclick="goNav('<bean:write name="buttonForward"/>')">
						</logic:notEqual>
						<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
							<input type=button value="Add Insurance" onclick="goNav('<bean:write name="buttonForward"/>')">
						</jims2:isAllowed>
						</logic:equal>
						</logic:equal>	
					</jims2:isAllowed>
				</logic:equal>
			</logic:notEmpty>
		</td>
	</tr>
</table>
