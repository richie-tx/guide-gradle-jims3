<%-- START - This is the physical characteristics tile --%>
<%--MODIFICATIONS --%>
<%-- 06/17/2005	Hien Rodriguez	Create JSP --%>
<%-- 02/28/2006 Clarence Shimek Defect#27703 corrected row count in showSomethingFromDropDown() function call from 4 to 6 for new rows added --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Button security feature --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add/Update buttons --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 

	
<tiles:importAttribute name="juvenileProfileMainForm"/>
<html:form action="/displayJuvenilePhysicalCharacteristicsCreate" target="content">
 
<%-- BEGIN PHYSICAL Characteristics TABLE --%>
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign='top' class='detailHead'>
		  
			<table width='100%' cellpadding='0' cellspacing='0'>
				<tr>
					<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'> 							
						<td width='1%'>
						  <a href="javascript:showHideMulti('Photos', 'photo', 1, '/<msp:webapp/>')"><img border='0' src="/<msp:webapp/>images/expand.gif" name="Photos" id="photoId"/></a>
						</td>
						<td class='detailHead'>&nbsp;Photos</td>
					</logic:notEmpty>
					<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
						<td class='detailHead'>&nbsp;Photos (No Photos)</td>
					</logic:empty>	
					
					
				</tr>
			</table>
		</td> 
	</tr>

	<tr id="photo0" class='hidden'>
		<td valign='top' width='30%'>
			<table>
				<tr>
					<td>
						<table>
 							<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
 								<tr>
   								<td>
   									<a href="javascript:newCustomWindowForPhoto('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>&source=photo&rrand=<%out.print((Math.random()*246));%>','juvPhoto',400,400)"  > 
   									<img alt="Mug Shot Not Available" title="Juvenile Photo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>" width="128" border='1' /></a>
   								</td>
   								<td colspan='6'>										
     								<logic:greaterThan name='juvenilePhotoForm' property='totalPhotosAvailable' value='1'>
     									<input type='button' value="View All Photos" onclick="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&selectedValue=&source=main&rrand=<%out.print((Math.random()*246));%>','juvPhoto',450,450);"/>
     								</logic:greaterThan>		
     							</td>
     						</logic:notEmpty>
     						<logic:notEmpty name='juvenilePhotoForm' property='mostRecentTattoo'>
     						<td colspan='6'></td>     							
     							<td>							
									<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&isTattoo=true&juvenileNumber=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentTattoo.photoName"/>&source=photo&rrand=<%out.print((Math.random()*246));%>','juvTattoo',400,400);"> 																
	  									<img alt="Mug Shot Not Available" title="Juvenile Tattoo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&isTattoo=true&juvenileNumber=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>" width="128" border='1'>
	  								</a>
								</td>
						  			<logic:greaterThan name='juvenilePhotoForm' property='totalTattoosAvailable' value='1'>
					    			<td colspan='3'>
					    				&nbsp;&nbsp;<input type='button' value="View All Tattoos" onclick="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&isTattoo=true&selectedValue=&source=main&rrand=<%out.print((Math.random()*246));%>','juvTattoo',450,450);"/>
					    			</td>
					    			</logic:greaterThan>									
			    				</td>				
				
  							</tr>
  							</logic:notEmpty>
  							
							<tr>
							<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
    							<td align='left'><bean:write name='juvenilePhotoForm' property='mostRecentPhoto.entryDate' formatKey="date.format.mmddyyyy"/></td>    	
    						</logic:notEmpty>					
    							<td colspan='6'></td>  
    							<td colspan='6'></td>  	
    						<logic:notEmpty name='juvenilePhotoForm' property='mostRecentTattoo'>						
    							<td align="left"><bean:write name='juvenilePhotoForm' property='mostRecentTattoo.entryDate' formatKey="date.format.mmddyyyy"/></td>
    						</logic:notEmpty>
    							<td></td>
  							</tr>
  						</logic:notEmpty>
 						</table>								
 					</td>
				</tr>	
			</table>
		</td>
	</tr> 
</table>
	<html:hidden styleId="isTattoo" name="juvenilePhotoForm" property="isTattoo"/>
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign='top' class='detailHead'>
			<table width='100%' cellpadding='0' cellspacing='0'>
				<tr>
					<td width='1%' class='detailHead'>
					  <a href="javascript:showHideMulti('Characteristics', 'pChar', 1, '/<msp:webapp/>')">
   					<img id="imgId" border='0' src="/<msp:webapp/>images/expand.gif" name="Characteristics"/></a>
						<bean:message key="prompt.physicalCharacteristics" />
						<logic:empty name="juvenilePhysicalCharacteristicsForm" property="physicalCharacteristics">
							(No <bean:message key="prompt.physicalCharacteristics" />)
						</logic:empty>
					</td>
				</tr>
			</table>
		</td> 
	</tr>

	<tr id="pChar0" class='hidden'>
		<td valign='top'>
			<table width='100%' cellpadding='2' cellspacing='0'>
				<tr bgcolor='white'>
					<td valign='top' width='100%'>
						<table width='100%' cellpadding='4' cellspacing='1'>
							<logic:notEmpty name="juvenilePhysicalCharacteristicsForm" property="physicalCharacteristics">
								<tr>
									<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.entryDate"/></td>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.build"/></td>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.height"/></td>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.weight"/></td>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.hairColor"/></td>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eyeColor"/></td>  											
								</tr>

								<logic:iterate indexId="pcharCount" id="phycharIndex" name="juvenilePhysicalCharacteristicsForm" property="physicalCharacteristics" >	
									<bean:size id="pcharSize" name="juvenilePhysicalCharacteristicsForm" property="physicalCharacteristics"/>								
									<tr class="<%out.print( (pcharCount.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%> " height="100%">
										<td><bean:write name="phycharIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td>											
										<td><bean:write name="phycharIndex" property="build"/></td>
										<td>
										 	<bean:write name="phycharIndex" property="heightFeet"/>ft
											<bean:write name="phycharIndex" property="heightInch"/>in
										</td>
										<td><bean:write name="phycharIndex" property="weight"/></td>
										<td><bean:write name="phycharIndex" property="hairColor"/></td>
										<td><bean:write name="phycharIndex" property="eyeColor"/></td>
									</tr>										 
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>					

				<tr>					
					<td>
						<logic:equal name="juvenilePhysicalCharacteristicsForm" property="displayTattoos" value="display">
							<div class='spacer'></div>					
							<table width='100%' border="0" cellpadding='4' cellspacing='1' class="borderTableBlue">		
								<tr>				
									<td class='detailHead' colspan="2">  <bean:message key="prompt.scarsMarksTattoos"/></td>					
								</tr>
								<tr>
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.scarsMarks"/></td>	
									<td class="formDe"><bean:write name="juvenilePhysicalCharacteristicsForm" property="allScars"/></td>
								</tr>				
								<tr class="visibleTR">											
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.tattoos"/></td>
									<td class="formDe"><bean:write name="juvenilePhysicalCharacteristicsForm" property="allTatoosDesc"/></td>
								</tr>
								<tr class="visibleTR">											
									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.otherTattooComments"/></td>
									<td class="formDe"><bean:write name="juvenilePhysicalCharacteristicsForm" property="allOtherTattooComments"/></td>
								</tr>
							</table>
						</logic:equal>
					</td>
		  		</tr>	
		  		<tr>
					<td align="center">
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>
							<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">
								<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
								<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
								<html:submit property="submitAction"><bean:message key="button.addMoreCharacteristics" /></html:submit>
								<html:submit property="submitAction"><bean:message key="button.updateScarsMarksTattoos"/></html:submit>
								</logic:notEqual>
								<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
									<html:submit property="submitAction"><bean:message key="button.addMoreCharacteristics" /></html:submit>
									<html:submit property="submitAction"><bean:message key="button.updateScarsMarksTattoos"/></html:submit>
								</jims2:isAllowed>
								</logic:equal>	
								</logic:equal>
							</logic:equal>
						</jims2:isAllowed>
					</td>				  		
	  			</tr>	
			</table>
		</td>
	</tr>
</table>

<!-- Hidden field starts //fromFacilityId added for US 38802 -->
	<html:hidden styleId="fromFacilityId" name="juvenilePhysicalCharacteristicsForm" property="fromFacility"/>
<!-- Hidden field ends -->	
</html:form>
<%-- END PHYSICAL Characteristics TABLE --%>

<html:form action="/displayAliasInfoCreate" target="content">
<!-- BEGIN Aliases TABLE --> 

<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue"> 
	<tr>&nbsp;</tr>
	<tr> 
		<td valign='top' class='detailHead'> 
			<table width='100%' cellpadding='0' cellspacing='0'> 
				<tr> 
					<td width='1%' class='detailHead'>
						<a href="javascript:showHideMulti('Aliases', 'alias', 1, '/<msp:webapp/>')" border='0'>
						<img border='0' src="/<msp:webapp/>images/expand.gif" name="Aliases"></a> 
						Aliases
						<logic:empty name="juvenilePhysicalCharacteristicsForm" property="juvenileAliasList">
							(No Aliases)
						</logic:empty>
					</td>
				</tr> 
			</table> 
		</td> 
	</tr> 
	<tr id="alias0" class='hidden'> 
		<td valign="top" align="center">
			<table width='100%' cellpadding='2' cellspacing='1' border='0'> 
				<logic:notEmpty name="juvenilePhysicalCharacteristicsForm" property="juvenileAliasList">
					<tr class='formDeLabel'> 
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_ALIAS_DELETE%>'>
					    <td><bean:message key="prompt.remove"/></td>
						</jims2:isAllowed> 
						<td><bean:message key="prompt.entryDate"/></td> 
						<td><bean:message key="prompt.name"/></td> 
						<td><bean:message key="prompt.type"/></td> 
						<td><bean:message key="prompt.notes"/></td> 
					</tr> 
	  				<logic:iterate id="aliasInfoIndex" name="juvenilePhysicalCharacteristicsForm" property="juvenileAliasList" indexId='indexer' scope="session">
	    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
		    				<jims2:isAllowed requiredFeatures='<%=Features.JCW_ALIAS_DELETE%>'>
								<td><a href="/<msp:webapp/>displayJuvenileAliasInfoCreateSummary.do?submitAction=<bean:message key="button.deleteAlias"/>&aliasId=<bean:write name="aliasInfoIndex" property="id"/>"><bean:message key="prompt.remove"/></a></td>
							</jims2:isAllowed> 
	    					<td><bean:write name="aliasInfoIndex" property="entryDate" format="MM/dd/yy"/></td>														
	    					<td><bean:write name="aliasInfoIndex" property="aliasName"/></td>
	    					<td><bean:write name="aliasInfoIndex" property="juvenileType"/></td>
	    					<td><bean:write name="aliasInfoIndex" property="notes"/></td>
	    				</tr>
					</logic:iterate> 
				</logic:notEmpty>  
				<tr>
					<td align="center" colspan="5">
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>
							<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
							<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
								<html:submit property="submitAction"><bean:message key="button.addNewAlias" /></html:submit>
							</logic:notEqual>
							<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
									<html:submit property="submitAction"><bean:message key="button.addNewAlias" /></html:submit>	
							</jims2:isAllowed>
							</logic:equal>
							</logic:equal>
						</jims2:isAllowed>
					</td>				  		
			  	</tr>	
			</table>
		</td> 
	</tr>
</table> 

</html:form>
<!-- END Aliases TABLE --> 

<!-- BEGIN JIS Information TABLE --> 
<html:form action="/displayJuvenileJISInfo" target="content">
<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue"> 
	<tr>&nbsp;</tr>
	<tr> 
		<td class='detailHead'> 
			<table width='100%' cellpadding='0' cellspacing='0'> 
				<tr> 
					<td width='1%' class='detailHead'>
						<a href="javascript:showHideMulti('JIS', 'jis', 1, '/<msp:webapp/>')" border='0'>
						<img border='0' src="/<msp:webapp/>images/expand.gif" name="JIS"></a> 
						JIS - <jims2:isAllowed requiredFeatures='<%=Features.JCW_JUVENILEINFORMATIONSHARING_JIS%>'> 
									<a href="javascript:openInNewTab('https://apps.harriscountytx.gov/JIS/');">
									Juvenile Information Sharing 
									</a> 
							  </jims2:isAllowed> 
						Verification					
					</td>
				</tr>				
			</table>
		</td>
	</tr>
	 
		
	<tr id="jis0" class="hidden">
			<td align="center">
				<table width='100%' cellpadding='4' cellspacing='0' border='0'> 					
					<tr>
					 <td colspan="8" class='detailHead'>&nbsp;&nbsp;&nbsp;&nbsp;Agencies with Confirmed Record
					 	<logic:empty name="juvenileProfileMainForm" property="currentJISInfo">None</logic:empty>
					 	<logic:notEmpty name="juvenileProfileMainForm" property="currentJISInfo"><bean:write name="juvenileProfileMainForm" property="currentJISInfo.agency"/>
					 		<logic:notEmpty name="juvenileProfileMainForm" property="currentJISInfo.otherAgency"> :  <bean:write name="juvenileProfileMainForm" property="currentJISInfo.otherAgency"/> </logic:notEmpty>
					 		<bean:write name="juvenileProfileMainForm" property="currentJISInfo.comments"/>
					 	</logic:notEmpty>
					 </td>
					</tr>	
				
					<logic:notEmpty name="juvenileProfileMainForm" property="JISInfoList">
						<tr class="headerData"> 						
							<td><bean:message key="prompt.entryDate"/></td> 
							<td>Agencies with Confirmed Record</td> 
							
						</tr> 
						<logic:iterate id="jisInfoIndex" name="juvenileProfileMainForm" property="JISInfoList" indexId='indexer' scope="session">
							<tr class="<%out.print( (indexer.intValue() % 2 == 0) ? "alternateRow" : "normalRow" );%>" height="100%">
								<td><bean:write name="jisInfoIndex" property="entryDate" format="MM/dd/yyyy"/></td>
								<td><bean:write name="jisInfoIndex" property="agency"/> 
								<logic:notEmpty name="jisInfoIndex" property="otherAgency"> :  <bean:write name="jisInfoIndex" property="otherAgency"/> </logic:notEmpty>
								<bean:write name="jisInfoIndex" property="comments"/></td>
							</tr>
						</logic:iterate>
						<tr></tr>
					</logic:notEmpty>	
											
					<tr>
						<td valign="top" align="center" colspan="8">
							<div class='spacer'></div>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>
								<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">
									<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
									<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
										<html:submit property="submitAction"><bean:message key="button.addNewJISDetails" /></html:submit>									
									</logic:notEqual>
									<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
										<html:submit property="submitAction"><bean:message key="button.addNewJISDetails" /></html:submit>
									</jims2:isAllowed>
									</logic:equal>	
									</logic:equal>	
								</logic:equal>
							</jims2:isAllowed>
						</td>				  		
		  			</tr>
	  			</table>
	  		</td>
	  	</tr>				  	
			
</table>
<!-- END JIS TABLE --> 
<!-- Added for 39094 -->
<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FACILITY_HEADER%>'>  
	<tr>&nbsp;</tr>
	<tr> 
		<td valign='top' class='detailHead'> 
			<table width='100%' cellpadding='0' cellspacing='0'> 
				<tr> 
					<td width='1%' class='detailHead'>
					<logic:notEmpty name="juvenileProfileMainForm" property="juvHeaderDetails">
						<a href="javascript:showHideMulti('HeaderDetails', 'HeaderDetails', 1, '/<msp:webapp/>')" border='0'>
						<img border='0' src="/<msp:webapp/>images/expand.gif" name="HeaderDetails"></a> </logic:notEmpty>
							Juvenile Facility Header Details
						<logic:empty name="juvenileProfileMainForm" property="juvHeaderDetails">
							(No Juvenile Facility Header Details)
						</logic:empty>
					</td>
				</tr> 
			</table> 
		</td> 
	</tr>
	<tr id="HeaderDetails0" class='hidden'> 
		<td valign="top" align="center">
			<table width='100%' cellpadding='2' cellspacing='1' border='0'> 
				<logic:notEmpty name="juvenileProfileMainForm" property="juvHeaderDetails">
					<tr class='formDeLabel'> 
						<td><bean:message key="prompt.juvenileNumber"/></td>
						<td><bean:message key="prompt.bookingSupervision"/></td>
					    <td><bean:message key="prompt.detainedFacility"/></td>
						<td><bean:message key="prompt.referralNumber"/></td> 
						<td><bean:message key="prompt.sequenceNumber"/></td> 
						<td><bean:message key="prompt.facilityStatus"/></td>
						<td><bean:message key="prompt.escapeDate"/></td>
						<td><bean:message key="prompt.escapeTime"/></td> 
						<td><bean:message key="prompt.nextHearingDate"/></td>
						<td><bean:message key="prompt.probableCauseDate"/></td>
					</tr> 
	    				<tr  height="100%">
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.juvenileNumber"/></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.bookingSupervision"/></td>
	    					<td><span title='<bean:write name="juvenileProfileMainForm" property="facilityCodeDesc"/>'><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.facilityCode"/></span></td>														
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.referralNo"/></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.lastSeqNum"/></td>
	    					<td><span title='<bean:write name="juvenileProfileMainForm" property="facilityStatusDesc"/>'><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.facilityStatus"/></span></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.relocationDate" format="MM/dd/yy"/></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.relocationTime"/></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.nextHearingDate"/></td>
	    					<td><bean:write name="juvenileProfileMainForm" property="juvHeaderDetails.probableCauseDate"/></td>
	    				</tr>
				</logic:notEmpty>  
			</table>
		</td> 
	</tr>
	</jims2:isAllowed>
</table> 

</html:form>

