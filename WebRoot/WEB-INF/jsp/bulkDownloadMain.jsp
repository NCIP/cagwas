<jsp:directive.page import="org.apache.struts.util.LabelValueBean"/>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*" %>

<div id="bulkHeader">
	<bean:message key="header.bulk.download"/>
</div>
<div id="bulkMain">

<script type="text/javascript">Help.insertHelp("FTP_bulk_download_help", "", "padding:8px;float:right;");</script>

<span class="er"><html:errors/></span>

<html:form action="bulkDownload.do">	
<logic:iterate id="study" name="studies" type="gov.nih.nci.caintegrator.domain.study.bean.Study">
		<input type="hidden" id='description_<bean:write name="study" property="id"/>' name='description_<bean:write name="study" property="id"/>' value='<bean:write name="study" property="description"/>'/> 
	</logic:iterate>
	
	<table>
		<tr>
			<td align="left">Select a Study:</td>
		</tr>
		<tr>
			<td align="left">
				<html:select property="studyName" size="3" styleClass="descriptionDD" onclick="changeStudyVersion(this);">
					<html:options collection="studyNames" property="value" labelProperty="label"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td align="left">Select a Version: </td>
		</tr>
		<tr>
			<td align=left">
					<span style="padding:0px; margin:0px;" id="optionsBox">		
					<%
						Object svm = session.getAttribute("studyVersionsMap");
						if(svm != null){
						Map h = (Map)svm;
						Iterator i = h.keySet().iterator();
						while(i.hasNext())	{
							String k = (String)i.next();
							List al = (List) h.get(k);
					%>
					<select name="studyVersion" id="<%=k%>_options" style="display:none">
					<%
							Iterator i2= al.iterator();
							while(i2.hasNext())	{
								LabelValueBean lv = (LabelValueBean)i2.next();
					%>						
						<option value="<%=lv.getValue() %>"><%=lv.getLabel() %></option>
					
					<%
					}//while
					%>
					</select>
					<%
							}//while
						}//if
					%>
					</span>
					<select name="studyId" id="studyId" onchange="changeStudyText(this);">
					</select>
		</td>
		</tr>
		<tr>
			<td>
				<div id="descriptionHeader">
					<bean:message key="label.studyDescription"/>
				</div>
				<div id="descriptionTxt">There are multiple studies available. Please review the context-sensitive description when you use the list above to select a specific study.</div>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="left">Select a Dataset:</td>
		</tr>
		<tr>
			<td align="left">
			<html:radio property="query" value="Association" styleClass="radio">
				<bean:message key="radio.association"/>
			</html:radio>
			</td>
		</tr>
		<tr>
			<td align="left">
			<html:radio property="query" value="Population" styleClass="radio">
				<bean:message key="radio.population"/>
			</html:radio>
			</td>
		</tr>
		<tr>
			<td align="left">
			<html:radio property="query" value="Subjects" styleClass="radio">
				<bean:message key="radio.subjects"/>
			</html:radio>
			<bean:message key="label.loginRequired"/>
			</td>
		</tr>
		<tr>
			<td align="left">
			<html:radio property="query" value="Genotypes" styleClass="radio">
				<bean:message key="radio.genotypes"/>
			</html:radio>
			<bean:message key="label.loginRequired"/>
			</td>
		</tr>
	</table>
	<p>
	<html:reset styleClass="btn"/>
	<html:submit styleClass="btn"/>
</html:form>
</div>