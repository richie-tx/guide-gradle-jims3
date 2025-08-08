<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 07/15/2007		AWidjaja Create JSP--%>
<%-- 08/07/2013		CShimek		#75820 added values for displaying school history address and city/state --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<tiles:importAttribute name="goals"/>
<tiles:useAttribute id="title" name="title" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="additionalTitle" name="additionalTitle" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="schoolHistory" name="schoolHistory" ignore="true" />



	<table border="1" width="100%" cellpadding="0" cellspacing="0" frame="void" rules="all">
		<logic:notEmpty name="title">
			<tr bgcolor='#f0f0f0'>
				<td width="720" colspan="6" valign="top">
					<p align="center"><strong>
					<bean:write name="title"/>
					<logic:notEmpty name="additionalTitle">
						<br><em><bean:write name="additionalTitle"/></em>
					</logic:notEmpty>
					</strong></p>
				</td>
			</tr>
		</logic:notEmpty>
		<logic:notEmpty name="schoolHistory">
			<tr>
				<td width="533" colspan="4" valign="top" align="left"><strong>Name: </strong><em><bean:write name="schoolHistory" property="school"/></em></td>
				<td width="187" colspan="2" valign="top" align="left"><strong>Phone #: </strong><em><bean:write name="schoolHistory" property="schoolPhone"/>&nbsp;</em></td>
			</tr>
			<tr>
				<td width="360" colspan="2" valign="top" align="left"><strong>Address: </strong><em><bean:write name="schoolHistory" property="schoolAddress"/>&nbsp;</em></td>
				<td width="360" colspan="4" valign="top" align="left"><strong>City/State: </strong><em><bean:write name="schoolHistory" property="schoolCityState"/>&nbsp;</em>
				</td>
			</tr>
			<tr>
				<td width="360" colspan="2" valign="top" align="left"><strong>Child's current grade level placement: </strong><em><bean:write name="schoolHistory" property="gradeLevelCode"/></em></td>
				<td width="360" colspan="4" valign="top" align="left"><strong>Child's current grade level performance: </strong><em><bean:write name="schoolHistory" property="gradeAverage"/></em></td>
			</tr>
			<tr>
				<td width="720" colspan="6" valign="top" align="left"><strong>Date child's educational records were provided to caregiver: </strong><em><bean:write name="schoolHistory" property="createDate" formatKey="date.format.mmddyyyy"/></em></td>
			</tr>
		</logic:notEmpty>
	  
		<tr bgcolor='#f0f0f0'>
			<td width="228" valign="top"><p align="center"><strong>Goal / Need </strong></p></td>
			<td width="288" colspan="2" valign="top"><p align="center"><strong>Intervention </strong></p></td>
			<td width="108" colspan="2" valign="top"><div align="center"><strong>Person(s) </strong><strong>Responsible </strong></div></td>
			<td width="96" valign="top"><p align="center"><strong>Time Frame </strong></p></td>
		</tr>
		
		<!-- added for ERJIMS200076784 -->
		<logic:empty name="goals">
		<tr>
			<td width="25%" align="center"><em>NOT APPLICABLE</em></td>						
			<td width="45%" align="center" colspan="2"><em></em></td>			
			<td width="15%" colspan="2" align="center">
				<em>
					
				</em>
			</td>
			<td width="15%" align="center"><em></em></td>
		</tr>
		</logic:empty>
		<!-- ended for testing Sruti -->
		
		
		
		<logic:notEmpty name="goals">
			<logic:iterate indexId="goalIndex" id="goalsIter" name="goals">
				<tr>
					<td width="25%" valign="top" align="left"><strong><%=goalIndex.intValue()+1%>. </strong><em><bean:write name="goalsIter" property="goal"/></em></td>
					 <%-- <td width="288" colspan="2" valign="top">
						<em>
							 <logic:iterate id="ruleIter" name="goalsIter" property="associatedRules"> 
								 <bean:write name="goalsIter" property="intervention" /><br>
							 </logic:iterate> 
						</em>
					</td>  --%>
					
					<%--added for ER JIMS200075816--%>
					<td width="45%" valign="top" colspan="2"><em><bean:write name="goalsIter" property="intervention"/></em></td>
					<%--ended by Sruti --%>
					
					<td width="15%" colspan="2" valign="top" align="left">
						<em>
							<logic:iterate indexId="idx" id="persResponsibleIter" name="goalsIter" property="personsResponsibleDisplay">
								
								<bean:write name="persResponsibleIter"/>
							</logic:iterate>
						</em>
					</td>
					<td width="15%" valign="top" align="left"><em><bean:write name="goalsIter" property="timeFrameStr"/></em></td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
		<logic:empty name="goals">
			<tr>
				<td width="25%" valign="top" align="left"><strong>1. </strong><em>&nbsp;</em></td>
				<td width="45%" colspan="2" valign="top" align="left"><em>&nbsp;</em></td>
				<td width="15%" colspan="2" valign="top" align="left"><em>&nbsp;</em></td>
				<td width="15%" valign="top" align="left"><em>&nbsp;</em></td>
			</tr>
			<tr>
				<td width="25%" valign="top" align="left"><strong>2. </strong></td>
				<td width="45%" colspan="2" valign="top" align="left">&nbsp;</td>
				<td width="15%" colspan="2" valign="top" align="left">&nbsp;</td>
				<td width="15%" valign="top" align="left">&nbsp;</td>
			</tr>
			<tr>
				<td width="25%" valign="top" align="left"><strong>3. </strong>&nbsp;</td>
				<td width="45%" colspan="2" valign="top" align="left">&nbsp;</td>
				<td width="15%" colspan="2" valign="top" align="left">&nbsp;</td>
				<td width="15%" valign="top" align="left">&nbsp;</td>
			</tr>
		</logic:empty>
	</table>		
