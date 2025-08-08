<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mojo.km.util.DateUtil" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseUpdateSummary</title>
</head>
<body>	
<html:form action="/UpdateCasefileQuery" onsubmit="return this;">

<h2 align="center">Update Casefile Summary</h2>
<hr>

<p align="center"><font color="green"><b>Casefile ID 
<bean:write name="ProdSupportForm" property="casefileId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is the record affected by this change. This is for auditing purposes.<br></p>
<hr>
	<logic:notEmpty	name="ProdSupportForm" property="casefileDet">   
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.supervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.juvenileNum" />&nbsp;</font></td>
		<logic:notEmpty	name="ProdSupportForm" property="toJuvenileId"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="fromJuvenileId"/></font>
	        </td>	
	    </logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASESTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.caseStatusId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVATIONDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.activationDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	    <logic:notEmpty	name="ProdSupportForm" property="oldActivationDate"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="oldActivationDate" formatKey="date.format.mmddyyyy"/></font>
	        </td>	
	     </logic:notEmpty>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>   
		<logic:notEmpty	name="ProdSupportForm" property="oldSupervisionEndDate"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="oldSupervisionEndDate" formatKey="date.format.mmddyyyy"/></font>
	        </td>	
	    </logic:notEmpty>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.supervisionTypeId" />&nbsp;</font></td>
		<logic:notEmpty	name="ProdSupportForm" property="supervisionBox"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="supervisionBox"/></font>
	        </td>	
	    </logic:notEmpty>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.probationOfficeId" />&nbsp;</font></td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVSEQNUM</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.sequenceNum" />&nbsp;</font></td>
		<logic:notEmpty	name="ProdSupportForm" property="oldJuvseqnum"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="oldJuvseqnum"/></font>
	        </td>	
	    </logic:notEmpty>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISMAYSINEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.isMAYSINeeded" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.isMAYSINeeded" value="false">
					No
				</logic:equal>			
		</td>
		<bean:define id="originalMaysiNeed" name="ProdSupportForm" property="originalCasefile.isMAYSINeeded"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newMaysiNeed" name="ProdSupportForm" property="casefileDet.isMAYSINeeded"
						type="java.lang.Boolean"></bean:define>		
		<logic:notEmpty	name="ProdSupportForm" property="newPactRiskNeeded"> 
				<% if ( ! newMaysiNeed.equals( originalMaysiNeed ) ) {%>
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.isMAYSINeeded" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.isMAYSINeeded" value="false">
						No
					</logic:equal>			 
		        </td>	
				<% } %>
		</logic:notEmpty>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISPACTRISKNEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.riskNeed" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.riskNeed" value="false">
					No
				</logic:equal>			
		</td>
		<bean:define id="originalRiskNeed" name="ProdSupportForm" property="originalCasefile.riskNeed"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newRiskNeed" name="ProdSupportForm" property="casefileDet.riskNeed"
						type="java.lang.Boolean"></bean:define>
		<logic:notEmpty	name="ProdSupportForm" property="newPactRiskNeeded"> 
				<% if ( ! newRiskNeed.equals( originalRiskNeed ) ) {%>
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.riskNeed" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.riskNeed" value="false">
						No
					</logic:equal>			 
		        </td>	
				<% } %>
		</logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISHISPANICINDICATORNEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.hispanic" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.hispanic" value="false">
					No
				</logic:equal>			
		</td>
		
		<bean:define id="originalHispanicIndicator" name="ProdSupportForm" property="originalCasefile.hispanic"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newHispanicIndicator" name="ProdSupportForm" property="casefileDet.hispanic"
						type="java.lang.Boolean"></bean:define>
		<logic:notEmpty	name="ProdSupportForm" property="newHispanicIndicatorNeeded">
			
			<% if ( ! newHispanicIndicator.equals( originalHispanicIndicator ) ) {%>
			
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.hispanic" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.hispanic" value="false">
						No
					</logic:equal>			 
		        </td>
		   <% } %>    
		</logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISSCHOOLHISTORYNEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.school" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.school" value="false">
					No
				</logic:equal>			
		</td>
		<bean:define id="originalSchoolHistory" name="ProdSupportForm" property="originalCasefile.school"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newSchoolHistory" name="ProdSupportForm" property="casefileDet.school"
						type="java.lang.Boolean"></bean:define>
		<logic:notEmpty	name="ProdSupportForm" property="newSchoolHistoryNeeded">
			
			<% if ( ! newSchoolHistory.equals( originalSchoolHistory ) ) {%>
			
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.school" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.school" value="false">
						No
					</logic:equal>			 
		        </td>
		   <% } %>    
		</logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISVOPENTRYNEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.vop" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.vop" value="false">
					No
				</logic:equal>			
		</td>
		
		<bean:define id="originalVopEntry" name="ProdSupportForm" property="originalCasefile.vop"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newVopEntry" name="ProdSupportForm" property="casefileDet.vop"
						type="java.lang.Boolean"></bean:define>
		<logic:notEmpty	name="ProdSupportForm" property="newVopEntryNeeded">
			
			<% if ( ! newVopEntry.equals( originalVopEntry ) ) {%>
			
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.vop" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.vop" value="false">
						No
					</logic:equal>			 
		        </td>
		   <% } %>    
		</logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISSUBSTANCEABUSENEEDED</font></td>
		<td class='formDe'>
				<logic:equal name="ProdSupportForm" property="casefileDet.subabuse" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.subabuse" value="false">
					No
				</logic:equal>			
		</td>
		
		<bean:define id="originalSubstanceAbuse" name="ProdSupportForm" property="originalCasefile.subabuse"
						type="java.lang.Boolean"></bean:define>
		<bean:define id="newSubstanceAbuse" name="ProdSupportForm" property="casefileDet.subabuse"
						type="java.lang.Boolean"></bean:define>
		<logic:notEmpty	name="ProdSupportForm" property="newSubstanceAbuseNeeded">
			
			<% if ( ! newSubstanceAbuse.equals( originalSubstanceAbuse ) ) {%>
			
				<td align="center">
			    	<font color="red">Updated, previous Value:&nbsp;
			    	<logic:equal name="ProdSupportForm" property="originalCasefile.subabuse" value="true">
						Yes
					</logic:equal>
					<logic:equal name="ProdSupportForm" property="originalCasefile.subabuse" value="false">
						No
					</logic:equal>			 
		        </td>
		   <% } %>    
		</logic:notEmpty>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKREFRNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.referralRiskNeeded" value="true">
					Yes
				</logic:equal>
				<logic:equal name="ProdSupportForm" property="casefileDet.referralRiskNeeded" value="false">
					No
			</logic:equal>
		</td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKINTVNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.interviewRiskNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.interviewRiskNeeded" value="false">
					No
			</logic:equal>
		</td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKTESTNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.testingRiskNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.testingRiskNeeded" value="false">
					No
			</logic:equal>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKCOMMNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.communityRiskNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.communityRiskNeeded" value="false">
					No
			</logic:equal>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKRESINEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.residentialRiskNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.residentialRiskNeeded" value="false">
					No
			</logic:equal>
		</td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKPROGNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.progressRiskNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.progressRiskNeeded" value="false">
					No
			</logic:equal>
		</td>			
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISBENASMNTNEEDED</font></td>
		<td class='formDe'>
			<logic:equal name="ProdSupportForm" property="casefileDet.benefitsAssessmentNeeded" value="true">
					Yes
			</logic:equal>
			<logic:equal name="ProdSupportForm" property="casefileDet.benefitsAssessmentNeeded" value="false">
					No
			</logic:equal>
		</td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.controllingReferralId" />&nbsp;</font></td>
		<logic:notEmpty	name="ProdSupportForm" property="oldControllingReferral"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="oldControllingReferral"/></font>
	        </td>	
	    </logic:notEmpty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEPLAN_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.caseplanId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.createUserID" />&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>   
		<logic:notEmpty	name="ProdSupportForm" property="oldCreateDate"> 
		    <td align="center">
		    	<font color="red">Updated, previous Value:&nbsp; <bean:write name="ProdSupportForm" property="oldCreateDate"/></font>
	        </td>	
	     </logic:notEmpty> 
	</tr>
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
					
	</table>
	</logic:notEmpty>	
</html:form>

	<table align="center" border="0" width="500">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:form method="post" action="/UpdateCasefileQuery?clr=Y" onsubmit="return this;">
					<html:submit value="Back to Query"/>
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:form method="post" action="/MainMenu" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Main Menu"/>
				</html:form>
			</td>
		</tr>
    </table> 
    
</body>
</html:html>