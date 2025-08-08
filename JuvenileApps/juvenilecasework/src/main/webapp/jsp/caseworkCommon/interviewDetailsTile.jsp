<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 12/11/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>

<tiles:useAttribute id="juvenileNum" name="juvenileNum" classname="java.lang.String" ignore="false" />
<tiles:useAttribute id="mode" name="mode" classname="java.lang.String" ignore="true" />
<tiles:importAttribute name="currentInterview"/>
<tiles:importAttribute name="juvenilePhotoForm"/>
							
<%-- BEGIN Interview TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td align='center'>
		
  		<div class='spacer'></div>
			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
        	<td class='detailHead'>Interview Detail</td>
      	</tr>
      	<tr>
      		<td valign='top' align='center'>
      			<table width='100%' border="0" cellpadding="4" cellspacing="1" >
      				<tr>
      					<td valign='top' class='formDeLabel' colspan='4'>
      						<table width='100%' cellpadding='0' cellspacing='0' border='0'>
      							<tr>
      								<td width='1%'><a href="javascript:showHideMulti('Photos', 'phChar', 1, '/<msp:webapp/>')" border='0'><img border='0' src="/<msp:webapp/>images/expand.gif" name="Photos"></a></td>
      								<td class='formDeLabel' nowrap='nowrap' valign='top'>&nbsp;Photos</td>
      							</tr>
      						</table>
      					</td>
      				</tr>
      				<tr id="phChar0" class='hidden'>
      					<td valign='top'>
      						<table width='98%' cellpadding='2' cellspacing='2' border='0'>
      							<tr bgcolor='white'>
      								<td valign='top' width='70%'>
      									<table width='98%' cellpadding='4' cellspacing='1'>              								
      										<tr>
      											<td width='6%' nowrap='nowrap'>
      												
      												<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
      													<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)"  > 
      													 <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>" width="128" border='1'></a>
      												</logic:notEmpty>
      
      												<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
      													<b>Juvenile has no photos</b>
      												</logic:empty>
      											</td>
      										</tr>
      										<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
      											<tr>
      												<td align='left'><bean:write name="juvenilePhotoForm" property="mostRecentPhoto.entryDate" formatKey="date.format.mmddyyyy"/></td>
      											</tr>
      										</logic:notEmpty>	
      									</table>
      								</td>
      							</tr>
      						</table>
      					</td>
      				</tr>
      				
      				<tr>
      					<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Date</td>
      					<td class='formDe'><bean:write name="currentInterview" property="interviewDate" formatKey="date.format.mmddyyyy"/></td>
      					<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Time</td>
      					<td id='interviewTime' class='formDe'><bean:write name="currentInterview" property="interviewDate" formatKey="time.format.HHmm"/></td>
      				</tr>
      				<tr> 
      					<td class='formDeLabel' nowrap='nowrap' valign='top' width="1%">Person(s) Interviewed</td> 
      					<td class='formDe' colspan="3">
      						<logic:notEmpty name="currentInterview" property="selectedPersonsInterviewed">
      							<logic:iterate id="personsIter" name="currentInterview" property="selectedPersonsInterviewed">
      								<bean:write name="personsIter" property="formattedName"/><br>
      							</logic:iterate>
      						</logic:notEmpty>
      					</td> 
      				 </tr> 
      				
      				<tr id='recordInventory'> 
      					<td class='formDeLabel' nowrap='nowrap' valign='top' width='1%'>Records Inventory</td> 
      					<td class='formDe' colspan="3">
      						<logic:notEmpty name="currentInterview" property="recordsInventoryDisplay">
      							<bean:size id="recordsSize" name="currentInterview" property="recordsInventoryDisplay"/>	
      							<logic:iterate indexId="recordsIndex" id="recordsIter" name="currentInterview" property="recordsInventoryDisplay">
      								<logic:notEmpty name="recordsIter">
      									<bean:write name="recordsIter"/><br>
      								</logic:notEmpty>
      							</logic:iterate>
      						</logic:notEmpty>
      					</td> 
      				</tr> 
      				<logic:notEmpty name="currentInterview" property="otherInventoryRecords">
      					<tr>
      						<td class='formDeLabel' nowrap='nowrap' valign='top' width='1%'>Other Records Inventory</td> 
      						<td class='formDe' colspan="3"><bean:write name="currentInterview" property="otherInventoryRecords" /></td> 
      					</tr>
      				</logic:notEmpty>
      				<tr> 
      					<td class='formDeLabel' nowrap='nowrap' width='1%'>Interview Type</td> 
      					<td class='formDe' colspan="3"><bean:write name="currentInterview" property="interviewType"/></td> 
      				</tr> 
      				<tr id='interviewLocation'>
      					<td class='formDeLabel' nowrap='nowrap' width="1%">Interview Location</td> 
      					<td class='formDe' colspan="3"><bean:write name="currentInterview" property="juvLocUnitDescription"/></td> 
      				</tr>
      				
      				<jims:if name="currentInterview" property="juvLocUnitDescription" value="New Address" op="equal">
      					<jims:or name="currentInterview" property="juvLocUnitDescription" value="" op="equal" />
      						<jims:then>
      				
      							<tr id='saddr0'>
      								<td class='formDeLabel' >Street number</td>
      								<td class='formDe' ><bean:write name="currentInterview" property="newAddress.streetNum"/></td>
      								<td class="formDeLabel">Street Name</td>
      								<td class='formDe'><bean:write name="currentInterview" property="newAddress.streetName"/></td>
      							</tr>
      
      							<tr id='saddr1'>
      								<td class="formDeLabel">Street Type</td>
      								<td class='formDe'>
      									<logic:notEmpty name="currentInterview" property="newAddress.streetTypeCode">
      										<bean:write name="juvenileInterviewForm" property="currentInterview.streetType"/>
      									</logic:notEmpty>
      								</td>
      								<td class="formDeLabel">Apt/Suite</td>
      								<td class='formDe'><bean:write name="currentInterview" property="newAddress.aptNum"/></td>
      							</tr>
      							  <tr id='saddr2'>
      							  <td class="formDeLabel">City</td>
      							  <td class='formDe'><bean:write name="currentInterview" property="newAddress.city"/></td>
      							  <td class="formDeLabel">State</td>
      							  <td class='formDe'>
      								<logic:notEmpty name="currentInterview" property="newAddress.stateCode">
      									<bean:write name="juvenileInterviewForm" property="currentInterview.state"/>
      								</logic:notEmpty>
      							  </td>
      							</tr>
      							  <tr id='saddr3'>
      							  <td class="formDeLabel">Zip Code</td>
      							  <td class='formDe' colspan='3'><bean:write name="currentInterview" property="newAddress.zipCode"/>-<bean:write name="currentInterview" property="newAddress.additionalZipCode"/></td>
      							</tr>
      						</jims:then>												
      				</jims:if>				
      				
      				<tr> 
      					<td class='formDeLabel' valign='top' nowrap='nowrap' colspan="4">Summary Notes
      						<logic:present name="mode">
      							<logic:equal name="mode" value="updateSummaryNotes">
          						&nbsp;
            					<tiles:insert page="spellCheckTile.jsp" flush="false">
            						<tiles:put name="tTextField" value="currentInterview.summaryNote"/>
            				    	<tiles:put name="tSpellCount" value="spellBtn1" />		
          						</tiles:insert>
          						(Max. characters allowed: 32000) 
      							</logic:equal>
      						</logic:present>
      					</td> 
      				</tr> 
      				<tr>
      					<td class='formDe' colspan="4">
      						<logic:present name="mode">
      							<logic:equal name="mode" value="updateSummaryNotes">
      								<textarea rows='3' style="width:100%" name="currentInterview.summaryNote" onmouseout="textLimit(this, 32000);" onkeyup="textLimit(this, 32000);"><bean:write name='currentInterview' property='summaryNote'/></textarea>
      							</logic:equal>
      							<logic:notEqual name="mode" value="updateSummaryNotes">
      								<bean:write name="currentInterview" property="summaryNote"/>
      							</logic:notEqual>
      						</logic:present>
      						<logic:notPresent name="mode">
      							<bean:write name="currentInterview" property="summaryNote"/>
      						</logic:notPresent>
      					</td> 
      				</tr>
      			</table>
      		</td>
      	</tr>
      </table>
		</td>
	</tr>
</table>

