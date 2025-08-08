<%-- 06/28/2016		Task 36225--%>
<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.ClosingPacketReportPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
<meta name="title" value="<%=PDFReport.MJC_RESTRCITED_ACCESS_EN.getReportName()%>"/>
<meta name="author" value="JIMS2, Harris County"/>
<meta name="subject" value="<%=PDFReport.MJC_RESTRCITED_ACCESS_EN.getReportName()%>"/>
<meta name="security-password" value="406"/>
<meta name="encryption-algorithm" value="128bit"/>
<meta name="access-level" value="print-all change-none extract-none"/>
	<meta name="base" value="file:c:/BFOImages/" /> 

<!-- STYLE SHEET LINK -->
<style>
body
	{	
		size:letter;
		margin-left: 0.30in;
    	margin-right: 0.30in;
		margin-top: 0.00in; 
		margin-bottom: 0.00in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 
		bottom: 4px;}
p.div {
    background-color: #B22222;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
li.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;
		}
ul.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;
		}

img.div{
    display: inline-block;
    width: 259px;
	height: 259px; 
}
img.div1{
    display: inline-block;
    width: 263px;
	height: 273px; 
}
  
img.div2{
    
    width: 8px; 
	height: 380px; 
}
 
p.div2 {
    background-color: #6d4a80;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
	text-align: center;
}
p.div1 {
    background-color: #626262;
    width: 3px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
p.div3 {
    background-color: #23698c;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
p.div4 {
     
	height: 140px;
    padding: 15px;
	background-color: #FFFFFF ;
	color: #23698c;
  	border: 15px ;
	}
p.div5 {
     width: 145px;
	height: 140px;
    padding: 5px;
 	border: 4px solid white;
	border: 5px ;
	margin: 0;
}

div.section
	{	
   		 width: 892;
   		 height: 1024;
		
   		 }
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 100%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"sans-serif", Helvetica, sans-serif;}
p.arial-6
	{	font-size:6;
		font-family:"Calibri", Helvetica, sans-serif;}  
p.arial-7
	{	font-size:7;
		font-family:"Calibri", Helvetica, sans-serif;} 
p.arial-8
	{	font-size:8;
		font-family:"Calibri", Helvetica, sans-serif;}
p.arial-9
	{	font-size:9;
		font-family:"Calibri", Helvetica, sans-serif;}
p.arial-9-blur
	{	font-size:9;
		font-family:"Calibri", Helvetica, sans-serif;
		color:#eaf1f5}
td.div3 {
    background-color: #135f81;
}
td.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;} 
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.times-font-18
	{font-size:18;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
td.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
tr.color{ background-color:#B22222;
		color:#FFFFFF;}
td.color1{ background-color:#626262;
		color:#FFFFFF;}
tr.color1{ background-color:#626262;
		color:#FFFFFF;}
tr.color2{ background-color:#6d4a80;
		color:#FFFFFF;}
tr.color3{ background-color:#23698c;
			color:#FFFFFF;}
tr.color4{ background-color:#58595c;
			color:#FFFFFF;}
tr.color7{ background-color:#0a354b;
			color:#FFFFFF;}
tr.div3 { background-color: #23698c;}
p.color3{ background-color:#23698c;
			color:#FFFFFF;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-blue-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-blue-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-18
	{font-size:18;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-12_italic
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		font-style: italic;
		line-height: 100%;}
p.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-10_italic
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		font-style: italic;
		line-height: 100%;}
p.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
nobr { white-space:nowrap; }
pre  { white-space:pre; }


</style>
<style type="text/css">
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td class="arial-7" align="left" width="100%">
						Published September 1, 2017
					</td>
				 	<td class="arial-7" align="right" width="100%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<p class="times-font-18" style="font-variant: small-caps" >Texas Juvenile Justice System Files and Records</p>
	<p class="times-font-12_italic">
		A Juvenile's Guide to Understanding Juvenile Records and Sealing
	</p>
</div>
<!--  Page 1 -->
<div class="body" page-break-before="avoid">
   	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		Who has a juvenile record in Texas?
  	</p>
  	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
  		Anyone referred to juvenile court for conduct occurring before age 17 has a record, even if not taken into custody by police before the referral. 
  		Referrals to juvenile court may be for delinquent conduct (generally Class A or B misdemeanor or felony offenses) or for conduct indicating a need 
  		for supervision (CINS) (e.g. class C misdemeanors, conduct that would not be against the law if committed by an adult, like drinking or running away, 
  		and other specific offenses, such as “sexting”). Juvenile records exist with probation, law enforcement, prosecutors, courts, and in the Juvenile 
  		Justice Information System (JJIS) computer database maintained by the Texas Department of Public Safety. Class C misdemeanor cases handled in justice 
  		or municipal court do not result in a juvenile record.
	</p>
  	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		Who can access juvenile records?
  	</p> 
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Juvenile records are confidential and may be shared only with entities specified in law. These are generally entities that needing access for community 
	  	safety or to provide services to juveniles. DPS may share the records in JJIS only with: criminal and juvenile justice agencies; TJJD and the Ombudsman 
	  	for TJJD’ courts exercising jurisdiction over juveniles; the Department of Family and Protective Services for certain background checks, the military 
	  	(only with the juvenile’s permission); and noncriminal justice agencies (only if authorized by federal law or executive order). If the records are sealed, 
	  	no one may access the records except with a court order, which may be issued in limited circumstances. Entities that provide occupational licenses are not 
	  	authorized to access the information in JJIS whether or not the records are sealed.
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		How do I get my records sealed?
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
		If you were referred to juvenile court for CINS and never referred for delinquent conduct, your records will be sealed when you turn 18 as long as you do 
		not have an adult felony conviction or pending adult charges. If you were referred to juvenile court for delinquent conduct (felony or misdemeanor) but 
		never adjudicated (i.e. “found guilty”) or were adjudicated for a misdemeanor but not a felony, your records will be sealed when you turn 19 as long as 
		you do not have an adult conviction for a jailable misdemeanor or felony and don’t have pending adult or juvenile charges. You do not have to apply to the 
		court for this type of sealing.  
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
		If you were adjudicated for a felony or you do not otherwise meet the criteria for sealing above, you may file an application (with or without an attorney) 
		asking the court to seal your records. You may do this only if you: are at least age 18 or, if not yet 18, at least two years have passed since the last 
		court action or discharge from probation; do not have any adult felony convictions or any pending adult charges (other than Class C misdemeanors); are not 
		currently required to register as a sex offender; and are not currently committed to TJJD or Travis County’s commitment program. The court may choose to 
		order the records sealed without a hearing or may hold a hearing to decide whether or not to seal the records. The court is not authorized to deny an 
		application for sealing without first having a hearing. 
	</p>
  	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
	  	Are there any records that can’t be sealed?
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
		You cannot get your records sealed if you were ever certified by the juvenile court to stand trial as an adult or were ever given a determinate sentence 
		(probation or commitment). If you are required to register as a sex offender, you cannot get your records sealed until your obligation to register has 
		expired. If you were committed to TJJD or Travis County, you are not eligible for sealing until you have been discharged.
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Sealing does not apply to records in a justice or municipal court related to a Class C misdemeanor. Sealing also does not apply to records in the gang 
	  	database; however, these records may only be shared with criminal justice agencies for criminal justice purposes and may be removed in certain circumstances.
	</p>
</div>
<!--  Page 2 -->
<div class="body">
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		What happens when records are sealed? 
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	When records are sealed, all adjudications are vacated and it is as though the referral to juvenile court never happened. You are not required to state 
	  	in any proceeding or in any application for employment, licensing, admission, housing, or other public or private benefit that the records ever existed 
	  	or that you were ever arrested, prosecuted, or adjudicated.
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Additionally, the law states that once records are sealed, the information in the records, the fact that they once existed, or your denial of the existence 
	  	of the records may not be used against you in any manner, including in an perjury prosecution or other criminal proceeding, a civil proceeding, including an 
	  	administrative proceeding involving a governmental entity, an application process for licensing or certification, or an admission, employment, or housing 
	  	decision.
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		Can records be unsealed? 
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	A court can unseal records if you ask them to or if a prosecutor asks them to for limited purposes, including a future prosecution for a capital offense or 
	  	a future prosecution for an offense for which punishment can be enhanced based on your juvenile record.
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		What about records in a justice or municipal court? 
	</p>
	<p class="times-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	For those of juvenile age, class C misdemeanors in justice or municipal court are confidential and may not be disclosed to the public. If you have only one 
	  	conviction prior to your 17th birthday, you may be able to have certain offenses “expunged” or removed from your record.
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		Where can I get additional information? 
	</p>
	<p class="times-font-10_italic" align="center" padding-top="0" padding-bottom="0">
		Confidentiality of Juvenile Records
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		Texas Family Code §§58.005-58.008<br/>
	</p>
	<p class="times-font-10_italic" align="center" padding-top="0" padding-bottom="0">
		Sealing
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		Texas Family Code Chapter 58, Subchapter C-1<br/>
	</p>
	<p class="times-font-10_italic" align="center" padding-top="0" padding-bottom="0">
		Expunction
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		Code of Criminal Procedure §45.0216; §45.0541<br/>
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		You can access Texas laws online at
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		<U>www.statutes.legis.state.tx.us</U>
	</p>
	<p class="times-font-8" align="center" padding-top="0" padding-bottom="0">
		Prepared by the
	</p>
	<p class="times-font-12" color="DodgerBlue" align="center" font-weight="bold" padding-top="0" padding-bottom="0">
		Texas Juvenile Justice Department
	</p>
	<p class="times-font-10" align="center" padding-top="0" padding-bottom="0">
		11209 Metric Boulevard – Bldg. H, Suite A<br/>
		P.O. Box 12757, Austin, Texas 78711<br/>
		Phone (512) 490-7130<br/>
		www.tjjd.texas.gov
	</p>
</div>
<!--  Page 3 -->
<div class="body">
	<p class="times-font-10" color="DodgerBlue" align="center" padding-top="0" padding-bottom="0" font-weight="bold">
		Texas Family Code Chapter 58
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="center" padding-top="0" padding-bottom="0" font-weight="bold">
		SUBCHAPTER C-1. SEALING AND DESTRUCTION OF JUVENILE RECORDS 
	</p>
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.251. DEFINITIONS. In this subchapter:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) "Electronic record" means an entry in a computer file or information on microfilm, microfiche, 
				or any other electronic storage media.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) "Juvenile matter" means a referral to a juvenile court or juvenile probation department and all 
				related court proceedings and outcomes, if any.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) "Physical record" means a paper copy of a record.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) "Record" means any documentation related to a juvenile matter, including information contained in 
				that documentation.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="left" padding-top="0" padding-bottom="0">Sec. 58.252. EXEMPTED RECORDS. The following records are exempt from this subchapter:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) records relating to a criminal combination or criminal street gang maintained by the Department 
				of Public Safety or a local law enforcement agency under Chapter 61, Code of Criminal Procedure;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) sex offender registration records maintained by the Department of Public Safety or a local law 
				enforcement agency under Chapter 62, Code of Criminal Procedure; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) records collected or maintained by the Texas Juvenile Justice Department for statistical and 
				research purposes, including data submitted under Section 221.007, Human Resources Code, and personally identifiable information.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="left" padding-top="0" padding-bottom="0">Sec. 58.253. SEALING RECORDS WITHOUT APPLICATION: DELINQUENT CONDUCT.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) This section does not apply to the records of a child referred to a juvenile court or juvenile 
				probation department solely for conduct indicating a need for supervision.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) A person who was referred to a juvenile probation department for delinquent conduct is entitled 
				to have all records related to the person's juvenile matters, including records relating to any matters involving conduct indicating a need for supervision, sealed without applying to the 
				juvenile court if the person:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) is at least 19 years of age;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) has not been adjudicated as having engaged in delinquent conduct or, if adjudicated for delinquent 
				conduct, was not adjudicated for delinquent conduct violating a penal law of the grade of felony;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) does not have any pending delinquent conduct matters;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) has not been transferred by a juvenile court to a criminal court for prosecution under Section 54.02;
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) has not as an adult been convicted of a felony or a misdemeanor punishable by confinement in jail; and
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6) does not have any pending charges as an adult for a felony or a misdemeanor punishable by confinement 
				in jail.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.254. CERTIFICATION OF ELIGIBILITY FOR SEALING RECORDS WITHOUT APPLICATION FOR DELINQUENT CONDUCT.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) The Department of Public Safety shall certify to a juvenile probation department that has submitted 
				records to the juvenile justice information system that the records relating to a person referred to the juvenile probation department appear to be eligible for sealing under Section 58.253.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) The Department of Public Safety may issue the certification described by Subsection (a) by electronic means, 
				including by electronic mail.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) Except as provided by Subsection (d), not later than the 60th day after the date the juvenile probation 
				department receives a certification under Subsection (a), the juvenile probation department shall:</p></td>
		</tr>
	</table>
</div>
<!--  Page 4 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) give notice of the receipt of the certification to the juvenile court; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) provide the court with a list of all referrals received by the department relating to that person and the 
				outcome of each referral.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d) If a juvenile probation department has reason to believe the records of the person for whom the department 
				received a certification under Subsection (a) are not eligible to be sealed, the juvenile probation department shall notify the Department of Public Safety not later than the 15th day after the date 
				the juvenile probation department received the certification. If the juvenile probation department later determines that the person's records are eligible to be sealed, the juvenile probation 
				department shall notify the juvenile court and provide the court the information described by Subsection (c) not later than the 30th day after the date of the determination.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e) If, after receiving a certification under Subsection (a), the juvenile probation department determines that 
				the person's records are not eligible to be sealed, the juvenile probation department and the Department of Public Safety shall update the juvenile justice information system to reflect that 
				determination and no further action related to the records is required.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(f) Not later than the 60th day after the date a juvenile court receives notice from a juvenile probation 
				department under Subsection (c), the juvenile court shall issue an order sealing all records relating to the person named in the certification.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.255. SEALING RECORDS WITHOUT APPLICATION: CONDUCT INDICATING NEED FOR SUPERVISION.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) A person who was referred to a juvenile probation department for conduct indicating a need for supervision 
				is entitled to have all records related to all conduct indicating a need for supervision matters sealed without applying to the juvenile court if the person:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) is at least 18 years of age;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) has not been referred to the juvenile probation department for delinquent conduct;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) has not as an adult been convicted of a felony; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) does not have any pending charges as an adult for a felony or a misdemeanor punishable by confinement 
				in jail.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) The juvenile probation department shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) give the juvenile court notice that a person's records are eligible for sealing under Subsection (a); 
				and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) provide the juvenile court with a list of all referrals relating to that person received by the department 
				and the outcome of each referral.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) Not later than the 60th day after the date the juvenile court receives notice from the juvenile probation 
				department under Subsection (b), the juvenile court shall issue an order sealing all records relating to the person named in the notice.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.256. APPLICATION FOR SEALING RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Notwithstanding Sections 58.253 and 58.255, a person may file an application for the sealing of records 
				related to the person in the juvenile court served by the juvenile probation department to which the person was referred. The court may not charge a fee for filing the application, regardless of 
				the form of the application.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) An application filed under this section must include either the following information or the reason that 
				one or more of the following is not included in the application:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the person's:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) full name;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) sex;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) race or ethnicity;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(D) date of birth;</p></td>
		</tr>
	</table>
</div>
<!--  Page 5 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(E) driver's license or identification card number; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(F) social security number;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the conduct for which the person was referred to the juvenile probation department, including the date 
				on which the conduct was alleged or found to have been committed;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) the cause number assigned to each petition relating to the person filed in juvenile court, if any, and 
				the court in which the petition was filed; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) a list of all entities the person believes have possession of records related to the person, including 
				the applicable entities listed under Section 58.258(b).</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) Except as provided by Subsection (d), the juvenile court may order the sealing of records related to all 
				matters for which the person was referred to the juvenile probation department if the person:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) is at least 18 years of age, or is younger than 18 years of age and at least two years have elapsed after 
				the date of final discharge in each matter for which the person was referred to the juvenile probation department;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) does not have any delinquent conduct matters pending with any juvenile probation department or juvenile 
				court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) was not transferred by a juvenile court to a criminal court for prosecution under Section 54.02;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) has not as an adult been convicted of a felony; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) does not have any pending charges as an adult for a felony or a misdemeanor punishable by confinement in 
				jail.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d) A court may not order the sealing of the records of a person who:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) received a determinate sentence for engaging in:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) delinquent conduct that violated a penal law listed under Section 53.045; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) habitual felony conduct as described by Section 51.031;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) is currently required to register as a sex offender under Chapter 62, Code of Criminal Procedure; or
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) was committed to the Texas Juvenile Justice Department or to a post-adjudication secure correctional 
				facility under Section 54.04011, unless the person has been discharged from the agency to which the person was committed.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e) On receipt of an application under this section, the court may:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) order the sealing of the person's records immediately, without a hearing; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) hold a hearing under Section 58.257 at the court's discretion to determine whether to order the 
				sealing of the person's records.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.257. HEARING REGARDING SEALING OF RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) A hearing regarding the sealing of a person's records must be held not later than the 60th day after the 
				date the court receives the person's application under Section 58.256.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) The court shall give reasonable notice of a hearing under this section to:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the person who is the subject of the records;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the person's attorney who made the application for sealing on behalf of the person, if any;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) the prosecuting attorney for the juvenile court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) all entities named in the application that the person believes possess eligible records related to 
				the person; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) any individual or entity whose presence at the hearing is requested by the person or prosecutor.</p></td>
		</tr>
	</table>
</div>
<!--  Page 6 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.258. ORDER SEALING RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) An order sealing the records of a person under this subchapter must include either the following information 
				or the reason one or more of the following is not included in the order:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the person's:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) full name;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) sex;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) race or ethnicity;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(D) date of birth;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(E) driver's license or identification card number; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(F) social security number;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) each instance of conduct indicating a need for supervision or delinquent conduct alleged against the 
				person or for which the person was referred to the juvenile justice system;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) the date on which and the county in which each instance of conduct was alleged to have occurred;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) if any petitions relating to the person were filed in juvenile court, the cause number assigned to each 
				petition and the court and county in which each petition was filed; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) a list of the entities believed to be in possession of the records that have been ordered sealed, including 
				the entities listed under Subsection (b).</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) Not later than the 60th day after the date of the entry of the order, the court shall provide a copy of the 
				order to:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the Department of Public Safety;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the Texas Juvenile Justice Department, if the person was committed to the department;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) the clerk of court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) the juvenile probation department serving the court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) the prosecutor's office;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6) each law enforcement agency that had contact with the person in relation to the conduct that is the 
				subject of the sealing order;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(7) each public or private agency that had custody of or that provided supervision or services to the person 
				in relation to the conduct that is the subject of the sealing order; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(8) each official, agency, or other entity that the court has reason to believe has any record containing 
				information that is related to the conduct that is the subject of the sealing order.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) On entry of the order, all adjudications relating to the person are vacated and the proceedings are 
				dismissed and treated for all purposes as though the proceedings had never occurred. The clerk of court shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) seal all court records relating to the proceedings, including any records created in the clerk's case 
				management system; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) send copies of the order to all entities listed in the order.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.259. ACTIONS TAKEN ON RECEIPT OF ORDER TO SEAL RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) An entity receiving an order to seal the records of a person issued under this subchapter shall, not 
				later than the 61st day after the date of receiving the order, take the following actions, as applicable:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the Department of Public Safety shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) limit access to the records relating to the person in the juvenile justice information 
				system to only the Texas Juvenile Justice Department for the purpose of conducting research and statistical studies;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) destroy any other records relating to the person in the department's possession, including 
				DNA records as provided by Section 411.151, Government Code; and</p></td>
		</tr>
	</table>
</div>
<!--  Page 7 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) send written verification of the limitation and destruction of the records to the 
				issuing court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the Texas Juvenile Justice Department shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) seal all records relating to the person, other than those exempted from sealing under 
				Section 58.252; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) send written verification of the sealing of the records to the issuing court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) a public or private agency or institution that had custody of or provided supervision or services to 
				the person who is the subject of the records, the juvenile probation department, a law enforcement entity, or a prosecuting attorney shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) seal all records relating to the person; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) send written verification of the sealing of the records to the issuing court; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) any other entity that receives an order to seal a person's records shall:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) send any records relating to the person to the issuing court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) delete all index references to the person's records; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) send written verification of the deletion of the index references to the issuing court.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) Physical or electronic records are considered sealed if the records are not destroyed but are stored in a 
				manner that allows access to the records only by the custodian of records for the entity possessing the records.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) If an entity that received an order to seal records relating to a person later receives an inquiry about a 
				person or the matter contained in the records, the entity must respond that no records relating to the person or the matter exist.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d) If an entity receiving an order to seal records under this subchapter is unable to comply with the order 
				because the information in the order is incorrect or insufficient to allow the entity to identify the records that are subject to the order, the entity shall notify the issuing court not later than 
				the 30th day after the date of receipt of the order. The court shall take any actions necessary and possible to provide the needed information to the entity, including contacting the person who is 
				the subject of the order or the person's attorney.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e) If an entity receiving a sealing order under this subchapter has no records related to the person who is 
				the subject of the order, the entity shall provide written verification of that fact to the issuing court not later than the 30th day after the date of receipt of the order.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.260. INSPECTION AND RELEASE OF SEALED RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) A juvenile court may allow, by order, the inspection of records sealed under this subchapter or under Section 
				58.003, as that law existed before September 1, 2017, only by:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) a person named in the order, on the petition of the person who is the subject of the records;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) a prosecutor, on the petition of the prosecutor, for the purpose of reviewing the records for possible use:
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) in a capital prosecution; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) for the enhancement of punishment under Section 12.42, Penal Code; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) a court, the Texas Department of Criminal Justice, or the Texas Juvenile Justice Department for the purposes 
				of Article 62.007(e), Code of Criminal Procedure.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) After a petitioner inspects records under this section, the court may order the release of any or all of the 
				records to the petitioner on the motion of the petitioner.</p></td>
		</tr>
	</table>			
</div>
<!--  Page 8 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.261. EFFECT OF SEALING RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) A person whose records have been sealed under this subchapter or under Section 58.003, as that law existed 
				before September 1, 2017, is not required to state in any proceeding or in any application for employment, licensing, admission, housing, or other public or private benefit that the person has been 
				the subject of a juvenile matter.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) If a person's records have been sealed, the information in the records, the fact that the records once 
				existed, or the person's denial of the existence of the records or of the person's involvement in a juvenile matter may not be used against the person in any manner, including in:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) a perjury prosecution or other criminal proceeding;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) a civil proceeding, including an administrative proceeding involving a governmental entity;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) an application process for licensing or certification; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) an admission, employment, or housing decision.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) A person who is the subject of the sealed records may not waive the protected status of the records or the 
				consequences of the protected status.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.262. INFORMATION GIVEN TO CHILD REGARDING SEALING OF RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) When a child is referred to the juvenile probation department, an employee of the juvenile probation 
				department shall give the child and the child's parent, guardian, or custodian a written explanation describing the process of sealing records under this subchapter and a copy of this subchapter.
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) On the final discharge of a child, or on the last official action in the matter if there is no adjudication, 
				a probation officer or official at the Texas Juvenile Justice Department, as appropriate, shall give the child and the child's parent, guardian, or custodian a written explanation regarding the 
				eligibility of the child's records for sealing under this subchapter and a copy of this subchapter.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) The written explanation provided to a child under Subsections (a) and (b) must include the requirements for 
				a record to be eligible for sealing, including an explanation of the records that are exempt from sealing under Section 58.252, and the following information:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) that, regardless of whether the child's conduct was adjudicated, the child has a juvenile record with the 
				Department of Public Safety and the Federal Bureau of Investigation;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the child's juvenile record is a permanent record unless the record is sealed under this subchapter;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) except as provided by Section 58.260, the child's juvenile record, other than treatment records made 
				confidential by law, may be accessed by a police officer, sheriff, prosecutor, probation officer, correctional officer, or other criminal or juvenile justice official unless the record is sealed as 
				provided by this subchapter;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4) sealing of the child's records under Section 58.253 or Section 58.255, as applicable, does not require any 
				action by the child or the child's family, including the filing of an application or hiring of a lawyer, but occurs automatically at age 18 or 19 as applicable based on the child's referral and 
				adjudication history;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5) the child's juvenile record may be eligible for an earlier sealing date under Section 58.256, but an earlier 
				sealing requires the child or an attorney for the child to file an application with the court;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6) the impact of sealing records on the child; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(7) the circumstances under which a sealed record may be reopened.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d) The Texas Juvenile Justice Department shall adopt rules to implement this section and to facilitate the 
				effective explanation of the information required to be communicated by this section.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.263. DESTRUCTION OF RECORDS: NO PROBABLE CAUSE.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">The court shall order the destruction of the records relating to the conduct for which a child is taken into 
				custody, including records contained in the juvenile justice information system, if:</p></td>
		</tr>
	</table>
</div>
<!--  Page 9 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) a determination is made under Section 53.01 that no probable cause exists to believe the child engaged in the 
				conduct and the case is not referred to a prosecutor for review under Section 53.012; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) a determination that no probable cause exists to believe the child engaged in the conduct is made by a 
				prosecutor under Section 53.012.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.264. PERMISSIBLE DESTRUCTION OF RECORDS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Subject to Subsections (b) and (c) of this section, Section 202.001, Local Government Code, and any other 
				restrictions imposed by an entity's records retention guidelines, the following persons may authorize the destruction of records in a closed juvenile matter, regardless of the date the records were 
				created:</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) a juvenile board, in relation to the records in the possession of the juvenile probation department;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) the head of a law enforcement agency, in relation to the records in the possession of the agency; and</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) a prosecuting attorney, in relation to the records in the possession of the prosecuting attorney's office.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) The records related to a person referred to a juvenile probation department may be destroyed if the person:</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) is at least 18 years of age, and:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) the most serious conduct for which the person was referred was conduct indicating a need for 
				supervision, whether or not the person was adjudicated; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) the referral or information did not relate to conduct indicating a need for supervision or 
				delinquent conduct and the juvenile probation department, prosecutor, or juvenile court did not take action on the referral or information for that reason;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) is at least 21 years of age, and:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) the most serious conduct for which the person was adjudicated was delinquent conduct that 
				violated a penal law of the grade of misdemeanor; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) the most serious conduct for which the person was referred was delinquent conduct and the person 
				was not adjudicated as having engaged in the conduct; or</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3) is at least 31 years of age and the most serious conduct for which the person was adjudicated was delinquent 
				conduct that violated a penal law of the grade of felony.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) If a record contains information relating to more than one person referred to a juvenile probation department, 
				the record may only be destroyed if:</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) the destruction of the record is authorized under this section; and</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) information in the record that may be destroyed under this section can be separated from information that is 
				not authorized to be destroyed.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d) Electronic records are considered to be destroyed if the electronic records, including the index to the 
				records, are deleted.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e) Converting physical records to electronic records and subsequently destroying the physical records while 
				maintaining the electronic records is not considered destruction of a record under this subchapter.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(f) This section does not authorize the destruction of the records of the juvenile court or clerk of court.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(g) This section does not authorize the destruction of records maintained for statistical and research purposes by 
				the Texas Juvenile Justice Department in a juvenile information and case management system authorized under Section 58.403.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(h) This section does not affect the destruction of physical records and files authorized by the Texas State 
				Library Records Retention Schedule.</p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.265. JUVENILE RECORDS NOT SUBJECT TO EXPUNCTION.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Records to which this chapter applies are not subject to an order of expunction issued by any court.</p></td>
		</tr>
	</table>
</div>
</body>
</pdf>
