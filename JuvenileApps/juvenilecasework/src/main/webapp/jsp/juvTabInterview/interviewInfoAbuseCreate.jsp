<!DOCTYPE HTML>
<%-- User selects the "Add More Abuse Info" button to create new abuse info --%>
<%--MODIFICATIONS --%>
<%-- 06/22/2005	Hien Rodriguez	Create JSP --%>
<%-- 02/23/2006	Leslie Deen		Revised JSP for ER#27756 800x600 layout issue--%>
<%-- 12/15/2006 Hien Rodriguez  ER#37077 Remove CasefileOperation box --%>
<%-- 07/16/2007 Leslie Deen		Defect #43633 add nowrap attribute to label tag --%>
<%-- 10/29/2015 R Capestani #30819 MJCW: PROD Juv Profile > Abuse Tab - click on any hyperlink in abuse list - see 4 Tiny Squares Showing (IE11 conversion) --%>

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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<html:javascript formName="juvenileAbuseForm" />

<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
} 

  function populateTraitTypeDescription(theForm)
  {
    var descriptionElement = theForm['traitsForm.traitTypeDescriptionId'];
    var typeElement = theForm['traitsForm.traitTypeId'];
    var selectedTraitId = "<bean:write name='juvenileAbuseForm' property='traitsForm.traitTypeDescriptionId'/>";  
    var parentId = typeElement.value;
    var selectedDescriptionVal = 0;

    descriptionElement.options.length = 0;
    descriptionElement.options[0] = new Option( "Please Select", "");

    if(parentId == "" || parentId == "All")
			{
      descriptionElement.disabled = true;
    }
    else
			{
      descriptionElement.disabled = false;

      <logic:notEmpty name="juvenileAbuseForm" property="traitsForm.descriptionTraitMap">
        <logic:iterate id="iter" name="juvenileAbuseForm" property="traitsForm.descriptionTraitMap">

          if( parentId == "<bean:write name='iter' property='key'/>" )
          {
            <logic:notEmpty name="iter" property="value">
              <bean:define id="listOfChilds" name="iter" property="value"/>
              
              <logic:iterate id="childIter" name="listOfChilds"> 
                var tempOption = new Option( "<bean:write name='childIter' property='traitName'/>", "<bean:write name='childIter' property='traitTypeId'/>" );
    
                descriptionElement.options[descriptionElement.options.length] = tempOption;
        
                if( selectedTraitId == "<bean:write name='childIter' property='traitTypeId'/>" )
       					{
                  tempOption.selected = true;
                }
              </logic:iterate>
            </logic:notEmpty>
          } // if(parentId ==
        </logic:iterate>
      </logic:notEmpty>
    } // else
  }
</script>

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
<title><bean:message key="title.heading"/> - interviewInfoAbuseCreate.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayJuvenileAbuseCreate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|183">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Abuse Information</td> 
  </tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class=spacer></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Fill in required fields then click "Next" button to continue.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
  <tr>
    <td><b>+</b> Required if Abuse Type selected.</td> 
  </tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>

<%-- BEGIN DETAIL TABLE --%>  
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  				<tr>
  					<td valign=top>
    					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    						<tiles:put name="tabid" value="interviewinfotab"/>
    						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
    					</tiles:insert>
  					</td>
  				</tr>
  				<tr>
             <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
          </tr>
   			</table>
  			
  			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  				<tr>
  					<td valign=top align=center>
						<div class=spacer></div>
    					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
        				<tr>
        					<td valign=top>
        						<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
        							<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
        							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
        						</tiles:insert>
        					</td>
        				</tr>
        				<tr>
       			  		<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
       			  	</tr>
        			</table>
					<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
							<div class=spacer></div>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				              <tr>
				                <td valign="top">
				                  <tiles:insert page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
				                  	<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
				                  	<%-- <tiles:put name="tabid" beanName="juvenileAbuseForm" beanProperty="subCategory"/> --%>
				                   <%-- <tiles:put name="tabid" beanName="juvenileDualStatusForm" beanProperty="categoryName" /> --%>
				                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
				                  </tiles:insert>
				                </td>
				              </tr>
				              <tr>
				                <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				              </tr>
				            </table>
				            </logic:notEmpty>
        			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
        				<tr>
        					<td valign=top align=center>
        			      <%-- BEGIN CASEFILE LIST TABLE --%>
										<div class=spacer></div>					
        						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
        							<tr class=detailHead>
        								<td colspan=2 class=detailHead><bean:message key="prompt.addAbuseSelectCasefile" /></td>
        							</tr>							
        							<tr>
        								<td valign=top align=center>																	
        								
        									<% int RecordCounter = 0; %>												
        									<table width="100%" border="0" cellpadding=2 cellspacing=1>														
        									  <%-- display detail header --%> 
        										<tr class=formDeLabel>											
        											<td><bean:message key="prompt.supervision#" /></td>
        											<td><bean:message key="prompt.probationOfficerName" /></td>
        											<td><bean:message key="prompt.supervisionType" /></td>
        											<td><bean:message key="prompt.supervisionEndDate" /></td>
        											<td><bean:message key="prompt.caseStatus" /></td>																				
        										</tr>
        									  <%-- display detail info --%> 			   							
       			   							<tr bgcolor='#ffffff'>													
        											<td align="left">
        												<a href="javascript:openWindow('/JuvenileCasework/displayJuvenileProfileCasefileDetails.do?casefileId=<bean:write name="juvenileTraitsForm" property="currentCasefile.supervisionNum"/>')">
        													<bean:write name="juvenileTraitsForm" property="currentCasefile.supervisionNum"/>
        												</a>
        											</td>
        											<td align="left"><bean:write name="juvenileTraitsForm" property="currentCasefile.probationOfficerFullName"/></td>
        											<td align="left"><bean:write name="juvenileTraitsForm" property="currentCasefile.supervisionType"/></td>
        											<td align="left"><bean:write name="juvenileTraitsForm" property="currentCasefile.supervisionEndDate" format="MM/dd/yyyy" /></td>
        											<td align="left"><bean:write name="juvenileTraitsForm" property="currentCasefile.caseStatus"/></td>																							
        										</tr>	     																	
        									</table>
        								</td>
        							</tr>							
        						</table>			    		 
        			<%-- END CASEFILE LIST TABLE --%>					

								<%-- BEGIN ABUSE INFO TABLE --%>		
						<div class=spacer></div>																	
   				<bean:size id="moreThanOne" name="juvenileTraitsForm" property="rootTraitTypes" />																
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">						 
							<tr class=detailHead>
								<td colspan="4"><bean:message key="prompt.abuseInfo" /></td>																	
							</tr>
	
							<tr>
							  <td class=formDeLabel width="18%"><bean:message key="prompt.diamond" /><bean:message key="prompt.traitType" /></td>
								<td class=formDe width="40%">
								  <logic:greaterThan name="moreThanOne" value="1">
			        					<html:select property="traitsForm.traitTypeId"
			        						onchange="this.form.changeSelection.value='true';populateTraitTypeDescription(this.form);">
			        						<html:option value=""><bean:message key="select.generic" /></html:option>
			        						<html:optionsCollection property="traitsForm.rootTraitTypes" value="traitTypeId" label="traitName" />
			        					</html:select>
			        				</logic:greaterThan>
			
			        				<logic:lessEqual name="moreThanOne" value="1">
			        					<html:select property="traitsForm.traitTypeId"
			        						onchange="this.form.changeSelection.value='true';populateTraitTypeDescription(this.form);" disabled="true">
			        						<html:optionsCollection property="traitsForm.rootTraitTypes" value="traitTypeId" label="traitName" />
			        					</html:select>
			        				</logic:lessEqual>
							  </td>	
							      <td class=formDeLabel width="18%" nowrap>
				  					<bean:message key="prompt.2.diamond"/> <bean:message key="prompt.info" />&nbsp;<bean:message key="prompt.source" />
				  				</td>
						       <td class="formDe" width="40%">
						       	  <html:select name="juvenileTraitsForm" property="informationSrcCd" styleId="informationSrcCd" >
						       	  	  <html:option value="">Please Select</html:option>
					          		  <html:optionsCollection name="juvenileTraitsForm" property="informationSources" value="code" label="descriptionWithCode"/>
					        	  </html:select>
								
								</td>		
							</tr>
	
							<tr>
							  <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.traitType" />&nbsp;<bean:message key="prompt.description" /></td>
								<td class=formDe>
									<html:select property="traitsForm.traitTypeDescriptionId"></html:select> 
									<script type="text/javascript">populateTraitTypeDescription(document.forms[0]);</script>
								</td>
								<td class=formDeLabel>CPS Case Number</td>
								<td class=formDe><html:text property="cpsCaseNumber" size="25" maxlength="25"/></td>		
							</tr>
							<tr>
								<td class=formDeLabel><bean:message key="prompt.diamond" /><bean:message key="prompt.abuseLevel" /></td>
								<td class=formDe>
								  <html:select property="abuseLevelId">
 									<html:option key="select.generic" value="" />
 									<html:optionsCollection property="abuseLevels" value="code" label="description"/>				
 								</html:select>
							  </td>	
							  <td class=formDeLabel width="1%" nowrap>Was CPS Involved?</td>
							  <td class=formDe colspan=3>Yes<html:radio property="cpsInvolvement" value="1"/>&nbsp;No<html:radio property="cpsInvolvement" value="0"/></td>									
							</tr>
							<tr>
								<td class=formDeLabel><bean:message key="prompt.diamond" /><bean:message key="prompt.informationBasis" /></td>
								<td class=formDe colspan="3">
								  <html:select property="informationBasisId">
 									<html:option key="select.generic" value="" />
 									<html:optionsCollection property="informationBasisLevels" value="code" label="description"/>				
 								</html:select>
							  </td>		
							</tr>
							<tr>
							<tr>								
					             <td class=formDeLabel nowrap width="1%"><bean:message key="prompt.relationshipOfAllegedAbuser" /></td>
					             <td class=formDe colspan=3>
													 <html:select property="allegedAbuserRelationship" multiple="true">
					  									<%-- <html:option key="select.generic" value="" /> --%>
					  									<%-- <html:optionsCollection property="juvenileRelationships" value="code" label="description"/>	 --%>			
					  									<html:option value="IN HOUSEHOLD FAMILY">IN HOUSEHOLD FAMILY</html:option>
														<html:option value="OUT OF HOUSEHOLD FAMILY">OUT OF HOUSEHOLD FAMILY</html:option>
														<html:option value="FRIEND OF FAMILY">FRIEND OF FAMILY</html:option>
														<html:option value="ACQUAINTANCE OF FAMILY">ACQUAINTANCE OF FAMILY</html:option>
														<html:option value="STRANGER">STRANGER</html:option>
													</html:select>
												 </td>				
           					</tr>
							</tr>
							<tr> 
							
				              <td class="formDeLabel" valign=top nowrap><bean:message key="prompt.perpetratorName" /></td> 
				              <td class=formDe colspan="3">
				  						  <%--NAME TABLE--%>
				  							<html:select property="perpId" multiple="true">
				  					            <html:optionsCollection property="perpetrators" value="perpetratorId" label="description" />				
				  							</html:select>
				 							</td> 
           					</tr> 
      <%--   <tr>								
             <td class=formDeLabel nowrap width="1%"><bean:message key="prompt.relationshipToJuvenile" /></td>
             <td class=formDe colspan=3>
									<html:select property="relationshipToJuvenileId">
  									<html:option key="select.generic" value="" />
  									<html:optionsCollection property="juvenileRelationships" value="code" label="description"/>				
  							</html:select>
							 </td>				
           </tr>  --%>
           					<tr>								
								<%-- <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.wasCpsInvolved" /></td> --%>
								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.didCPStakeCustody" /></td>
								<td class=formDe colspan=3>Yes<html:radio property="cpsCustody" value="1"/>&nbsp;No<html:radio property="cpsCustody" value="0"/></td>								
							</tr>

							<tr>
								<td class=formDeLabel colspan="4" nowrap><bean:message key="prompt.didTheYouthReceiveAnyTreatment" /></td>
							</tr>
							<tr>
								<td class=formDe colspan="4"><html:text size="55" maxlength="55" property="abuseTreatment" /></td>	
							</tr>

							<%-- <tr>
								<td class=formDeLabel colspan="4"><bean:message key="prompt.describeWhatThePerpetratorDid" />&nbsp;
		        					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
		        						<tiles:put name="tTextField" value="abuseEvent" />
		         						<tiles:put name="tSpellCount" value="spellBtn1" />
		      						</tiles:insert>
		      						(Max. characters allowed: 255)
								</td>
							</tr> 
							<tr>
								<td class=formDe colspan="4"><html:textarea property="abuseEvent" rows="3" style="width:100%" onmouseout="textLimit(this, 255);" onkeyup="textLimit(this, 255);" ></html:textarea></td>
							</tr>--%>
							
							<tr>
								<td class=formDeLabel colspan="4">
								  <bean:message key="prompt.plusSign" /><bean:message key="prompt.describeTheCircumstancesOfTheIndicatedAbuse" />&nbsp;
		        					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
		        						<tiles:put name="tTextField" value="abuseDetails" />
		        						<tiles:put name="tSpellCount" value="spellBtn2" />
		      						</tiles:insert>
								</td>
							</tr>
							<tr>
								<td class=formDe colspan="4"><html:textarea property="abuseDetails" rows="3" style="width:100%" onkeyup="textCounter(this.form.abuseDetails,3170);"></html:textarea></td>
							</tr>
						</table>
						<%-- END ABUSE INFO TABLE --%>

            <%-- BEGIN BUTTON TABLE --%>
            <div class=spacer></div> 
					    <table width="98%">	
					    	<tr>
					    		<td align="center">
					    			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>				
					    			<html:submit property="submitAction" onclick="return validateJuvenileAbuseForm(this.form)">				
					    				<bean:message key="button.next"></bean:message>
					    			</html:submit>
					    			<html:submit property="submitAction">
					    				<bean:message key="button.cancel"></bean:message>
					    			</html:submit>
					    		</td>
					    	</tr>
					    </table>
					    <div class=spacer></div> 
             <%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<div class=spacer></div>
    </td>
	</tr>
</table>	
<%-- END DETAIL TABLE --%>


</html:form>

<div class=spacer></div>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

