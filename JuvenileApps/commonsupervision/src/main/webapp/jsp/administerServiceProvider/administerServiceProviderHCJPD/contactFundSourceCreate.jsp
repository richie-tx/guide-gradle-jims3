<!DOCTYPE HTML>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<html>
<head>

<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />

<title><bean:message key="title.heading"/> - contactFundSourceCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		console.log("Loading....");
		if(typeof $("#fundStartDate")!= "undefined"){			
			datePickerSingle( $("#fundStartDate"),"Fund Start Date", false);		 
		}
		if(typeof $("#fundEndDate")!= "undefined"){			
			datePickerSingle( $("#fundEndDate"),"Fund End Date", false);		 
		}
		
		$("#reset").click(function(){
			$("#fundSource").val("");
			$("#fundStartDate").val("");
			$("#fundEndDate").val("");
			$("#comments").val("");
			
		})
		
		$("#nextId").click(function(){
			console.log("click");
			var fundSource 		= $("#fundSource").val();
			var fundStartDate 	= $("#fundStartDate").val();
			//var fundEndDate		= $("#fundEndDate").val();
			var comments		= $("#comments").val();
			
			if ( fundSource == "" || fundSource.length == 0 ){
				alert("Source Fund is required");
				$("#fundSource").focus();
				return false;
			}
			
			if ( fundStartDate == "" || fundStartDate.length == 0 ){
				alert("Fund Start Date is required");
				$("#fundStartDate").focus();
				return false;
			}
			
			
			if ( comments == "" || comments.length == 0 ){
				alert("Comments is required");
				$("#comments").focus();
				return false;
			}
			
			return true;
		})
		
		
		
		
	})

</script>

</head>
<body>
<html:form action="/submitJuvContactCreate"  >
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
    		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
		</tr>
		<tr>
			<td valign=top>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" >
	       			<tr>
	    				<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
	    			</tr>	
      			</table>
      			 <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	      			 <tr>
	          			<td valign=top align=center>
		           			<%-- BEGIN HEADING TABLE --%>
		            		<div class='spacer'></div>
		            		 <table width='100%'>
			             		 <tr>
				    			      <td class="header" align="center">
				    			      		Create Contact Funding
				    			      </td>
			    			      </tr>
			    			</table>
			    			 <table cellpadding=1 cellspacing=0 border=0 width='98%'>
	    						<tr>
					      			<td bgcolor='#cccccc'>
						        		<table width='100%' border=0 cellpadding=2 cellspacing=1>
						          			<tr>
						          				<td class="headerLabel" width='1%' nowrap>Provider <bean:message key="prompt.name" /></td>
						          				<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="providerName"/></td>
						          			</tr>
						          			<tr>
						          				<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
						          				<td class="headerData"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
						          				<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
						          				<td class="headerData"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
						          			</tr>
						          		</table>
					        		</td>
	      						</tr>
    						</table>
    						<div class='spacer'></div>
    						<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
          						<tr>
            						<td class=detailHead>
            							<table width='100%' cellpadding=2 cellspacing=0>
                							<tr>
            		  							<td class=detailHead nowrap colspan=4><bean:message key="prompt.contact" /> <bean:message key="prompt.info" /></td>
            								</tr>
            							</table>
            						</td>
            					</tr>
            					<tr>
            						<td>
            							<bean:define id="contact" name="serviceProviderForm" property="currentContact"/>
            							<table width="100%" border=0 cellpadding="4" cellspacing="1">
            								<logic:equal name="serviceProviderForm" property="isInHouse" value="YES">
						                      <tr class=formDe>
						                        <td class=formDeLabel nowrap><bean:message key="prompt.userId" /></td>
						                        <td colspan=3 class=formDe><bean:write name="contact" property="logonId"/></td>                  
						                      </tr>
						                      <tr>
						                        <td class="formDeLabel" valign=top><bean:message key="prompt.name"/></td>
						                        <td class="formDe" colspan=3>
						                          <bean:write name="contact" property="contactName.prefix"/>
						                         	 <bean:write name="contact" property="contactName.lastName"/><logic:notEqual name="contact" property="contactName.firstName" value="">,</logic:notEqual>
						                         	 <bean:write name="contact" property="contactName.firstName"/> <bean:write name="contact" property="contactName.middleName"/> <bean:write name="contact" property="contactName.suffix"/>
						                        </td>
						                      </tr>
                    						</logic:equal>
                    						 <logic:notEqual name="serviceProviderForm" property="isInHouse" value="YES">
                    						 	<tr>
							                        <td class="formDeLabel" valign=top><bean:message key="prompt.name"/></td>
							                        <td class="formDe" colspan=3><msp:formatter name="contact" property="contactName" format="P L, F M S"/></td>
                      							</tr> 
                    						 </logic:notEqual>
                    						 <tr>
                      							<td class=formDeLabel nowrap><bean:message key="prompt.officePhone"/></td>
                      							<td class=formDe colspan=3><msp:formatter name="contact" property="officePhone" format="A-P-F"/>  <logic:notEqual name="contact" property="officePhone.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="contact" property="officePhone" format="X"/></logic:notEqual></td>
                    						</tr>
                    						<tr>
                      							<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.email"/></td>
                      							<td class=formDe colspan=3><bean:write name="contact" property="contactEmail"/></td>
                    						</tr>
                    						 <tr>
							                      <td class=formDeLabel nowrap width='1%'>Login</td>
							                      <td class=formDe colspan=3><bean:write name="contact" property="email"/></td>
                    						</tr>
                    						<tr>
                     						 	<td class=formDeLabel nowrap width='1%'>Therapist ID</td>
                     							<td class=formDe ><bean:write name="contact" property="contactId"/></td>
                     							<td class=formDeLabel nowrap width='1%'>Service Provider ID</td>
                     							<td class=formDe ><bean:write name="serviceProviderForm" property="providerId"/></td>
                    						</tr>
                    						<tr>
                    							<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.3.diamond"/>Source Fund</td>
                     							<td class=formDe colspan=3 >
                     								<html:select name="serviceProviderForm" property="fundSource" styleId="fundSource">
          												<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          												<html:optionsCollection property="fundSourceList" value="code" label="description"  name="serviceProviderForm"/>
          											</html:select>
                     							</td>
                    						</tr>
                    						<tr>
                    						<logic:equal name="contact" property="status" value="I">
                    							<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.3.diamond"/>Fund Start Date</td>
                     							<td class=formDe><html:text styleId="fundStartDate" name="serviceProviderForm" property="fundStartDate" /></td>                     							
                     							<td class=formDeLabel nowrap width='1%'>Fund End Date</td>
                     							<td class=formDe><html:text styleId="fundEndDate" name="serviceProviderForm" property="fundEndDate" /></td>
                     						</logic:equal>
                     						<logic:equal name="contact" property="status" value="A">
                     							<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.3.diamond"/>Fund Start Date</td>
                     							<td class=formDe colspan=3><html:text styleId="fundStartDate" name="serviceProviderForm" property="fundStartDate" /></td>
                     						</logic:equal>
                    						</tr>
                    						<tr>
                    							<td class=formDeLabel nowrap width='1%' colspan=4><bean:message key="prompt.3.diamond"/>
                    									Comments
                    									<tiles:insert page="../../common/spellCheckTile.jsp" flush="false">
               												<tiles:put name="tTextField" value="comments"/>
               												<tiles:put name="tSpellCount" value="spellBtn1" />
             											</tiles:insert> 
                    							</td>
                    						</tr>
                    						<tr>
                      							<td class='formDe' colspan='4'><html:textarea styleId="comments" rows="8" style="width:100%" name="serviceProviderForm" property="comments" onkeyup="textCounter(this,500)"></html:textarea></td>
                    						</tr>
            							</table>
            						</td>
            					</tr>
            				</table>
            				
            				<table border="0">
		  						<tr>
		  							<td align="center">
		    							<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
		   								<html:submit property="submitAction" styleId="nextId"><bean:message key="button.next"></bean:message></html:submit>
		   								<html:reset styleId="reset"><bean:message key="button.reset"></bean:message></html:reset>
		  								<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
		  							</td>
		  						</tr>
            			</table>
	            		</td>
	            	</tr>
      			 </table>
      			 
      			 <logic:notEmpty name="serviceProviderForm" property="contactFundSourceList">
			  		 <tr>
			        	<td>
			        		  <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			        		  	 <tr class=detailHead>
			                          <td>Contact Fund Source History</td>
			                     </tr>
			                     <tr>
			                          <td>
			                            <table width="100%" cellpadding="2" cellspacing="1" align="center">
			                              <tr class=formDeLabel>
				                                <td valign=top><bean:message key="prompt.entryDate" /></td>
				                                <td valign=top><bean:message key="prompt.sourceFund"/></td>
				                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
				                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>	
				                                <td valign=top><bean:message key="prompt.status"/></td>                               
				                          </tr>
				                      
				                  				 <% int RecordCounter=0;
				                                  int locCounter=0;
							                      String bgcolor="";
							                      String selectUser=""; %>
				                              <logic:iterate name="serviceProviderForm" id="fundsIndex" property="contactFundSourceList">
				                              	 <tr class= <% RecordCounter++;
							                     		bgcolor = "alternateRow";                      
				            				     		if (RecordCounter % 2 == 1)
							                         	bgcolor = "normalRow";
				               				     		out.print(bgcolor); %>>			                             
						                                <td><bean:write name="fundsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
						                                <td><bean:write name="fundsIndex" property="sourceFundDescription"/></td>
						                                <td><bean:write name="fundsIndex" property="startDate" formatKey="date.format.mmddyyyy"/></td>
						                                <td><bean:write name="fundsIndex" property="endDate" formatKey="date.format.mmddyyyy"/></td>
						                                <td><bean:write name="fundsIndex" property="status"/></td>
						                          </tr>
						                         
				                              </logic:iterate>
				                            </table>
				                       </td>
				                  </tr>
			        		  </table>
			        	</td>
			        </tr>
			</logic:notEmpty>
      			
			</td>
		</tr>
	</table>
</div>
</html:form>
</body>
</html>