<div class="divHeader">
	<h3>
	Study: <% out.println(session.getAttribute("studyName")); %>
	<br>
	Version: <% out.println(session.getAttribute("studyVersion")); %>
	</h3>
	<bean:message key="header.bulk.download.dataNotAvailable"/>
</div>
<div class="divMain" style="height:200px;">
<% String query = (String)session.getAttribute("query"); %>
<% String queryType = (String)session.getAttribute("queryType"); %>
<div align="center">
<br>
<br>
<% if(queryType != null && queryType.equals("browse")){ %>
<%=query%> Search is unavailable for  <br>
<strong><% out.println(session.getAttribute("studyName")); %>,	<% out.println(session.getAttribute("studyVersion")); %></strong></div>
<%} 
else {
%>
<%=query%> Bulk Data Download is unavailable for <br>
<strong><% out.println(session.getAttribute("studyName")); %>,	<% out.println(session.getAttribute("studyVersion")); %></strong></div>
<%} %>
<div align="center">
<br>
<br><br>
<a href="javascript:history.back()">Go Back</a> 
</div>
