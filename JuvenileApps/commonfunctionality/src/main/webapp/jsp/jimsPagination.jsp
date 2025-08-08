<!DOCTYPE HTML>

<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>
<%@ page session="false" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>

<script>
  function changePageOffset(paginationURLElement,paginationTEXTElemName,myPaginationURL,myPaginationTotalPageCount,myPaginationResultsPerPage){
  
    var myPaginationstrSize=myPaginationURL.length;
    var myPaginationtestStuff=myPaginationURL.substr(0,myPaginationstrSize-1);
  	var paginationPageInput = document.getElementsByName(paginationTEXTElemName)[0];
  	if(paginationPageInput.value < 1 || paginationPageInput.value > myPaginationTotalPageCount){
  		alert("Page value is out of range- Please enter a valid page between: 1 and " +  myPaginationTotalPageCount);
  		return false;
  	}
  	var myPaginationnewOffset=(paginationPageInput.value-1)*myPaginationResultsPerPage;
  	 var myPaginationnewGOTOURL=myPaginationtestStuff + myPaginationnewOffset + "#pagerSearchLinkId";
  	 paginationURLElement.href=myPaginationnewGOTOURL;
  	 return true;
  }
 function firePagination(aFormElem,evt){
	 var key = evt.keyCode;
    // key=(document.layers)?e.which:window.event.keyCode
     //key2=String.fromCharCode(key)   -- gets the character representaion of the key i.e 27=","
     if (key==13){
		if(aFormElem.name == "pagerSearch"){
			var pagerLinkElem=document.getElementsByName("pagerSearchLinkId")[0];
			if(pagerLinkElem!=null){
				pagerLinkElem.onclick();
				evt.returnValue=false; 
				evt.cancel = true;
				goNavPag(pagerLinkElem.href);
				
				return false;
			}
			else{
			}
		}
	 }
	 return true;
   } 
   
   function goNavPag(location)
	{
		if (location == "back"){
			history.go(-1);
			}else{
				window.location.href=location;
			}
	}
</script>
<tiles:useAttribute id="myPaginationTextBox" name="pagerUniqueName"/>
<tiles:useAttribute id="myPaginationResultsPerPage" name="resultsPerPage"/>
<table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor=e3e9f8>
	<tr valign="bottom">
	<pg:index export="total=itemCount,totalPageCount=pageCount">
		<td align=right valign="bottom" nowrap width=1%><font face=arial size=-1> 
			
				<pg:pager export="pagerOffset=pageOffset,pagerPageNumber=pageNumber">
				</pg:pager>

				<pg:prev export="url,first,last">
									<% int prevItems = (last.intValue() - first.intValue()) + 1; %>
									<b><a href="<%= url %>"><IMG
				      SRC="/<msp:webapp/>images/blue_previous.gif" alt="Previous" border=0></a></b>
				</pg:prev>
				&nbsp;
		</td>
		<td valign="middle">
				<%--<pg:page export="first,last,url">--%>
					<pg:first export="url" >
					<a name="pagerSearchLinkId" id="pagerSearchLinkId" href="<%=url%>" onClick="return changePageOffset(this,'<%=myPaginationTextBox%>','<%=url%>',<%=totalPageCount%>,<%=myPaginationResultsPerPage%>)">Page</a>
				<input type="text" name="<%=myPaginationTextBox%>" size="1" maxLength="3" value="<%=currentPageNumber%>" onKeyPress="return firePagination(this,event)"/> 
				of
				<%=totalPageCount%>
				</pg:first>
				<%--</pg:page>--%>
</td>
<td valign="bottom">&nbsp;
				<pg:next export="url,first,last">
					<% int nextItems = (last.intValue() - first.intValue()) + 1; %>
						<b><a href="<%= url %>"><IMG
      SRC="/<msp:webapp/>images/blue_next.gif" alt="Next" border=0></a></b>
				</pg:next>
 			
 		</td>
 		</pg:index>
	</tr>
</table>

