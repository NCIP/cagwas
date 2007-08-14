<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationAnalysis" %>

<%
ArrayList analysisCol = (ArrayList)session.getAttribute("analysisCol");
int index = Integer.parseInt(request.getParameter("index"));
SNPAssociationAnalysis analysis = (SNPAssociationAnalysis)analysisCol.get(index);
%>
<h3><% out.print(analysis.getName()); %> Description</h3>
<% out.println(analysis.getDescription()); %>
					