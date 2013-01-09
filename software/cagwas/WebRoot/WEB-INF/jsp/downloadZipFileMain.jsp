<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<div class="divHeader">
	Download Zip File
</div>
<div class="divMain" style="height:200px;">
<% String fileName = (String)request.getSession().getAttribute("zipFile"); %>
<% java.io.File file = null; %>
<% String filePath = (String)request.getSession().getAttribute("filePath"); %>
<% String fileRetentionPeriodInDays = (String)request.getSession().getAttribute("fileRetentionPeriodInDays"); %>
<%
if(filePath != null && fileName != null){

		filePath = filePath + java.io.File.separator + fileName;
		
		try {
				file = new java.io.File(filePath);
				
			} catch(Exception e) {
				System.out.println(e);
			}
	}	

if(fileRetentionPeriodInDays == null){
	fileRetentionPeriodInDays = "5";
}
 %>
<div align="center">
<br>
<% if(fileName != null && file != null  && filePath != null){ %>
<br>
Please click on the link below to download your file.<br><br>
<%
		out.println("<li><a href=getBulkDownloadFile.do?file=" + fileName +
				        	" onmouseover=\"return overlib(\'Name: " + fileName + "<br>Size: " +  (file.length()/1000) + "KB <br> Modified: " + new java.util.Date(file.lastModified()).toString() + "\', CAPTION, \'File Details\', DELAY, 250, VAUTO, HAUTO);\" onmouseout=\"return nd();\"" + 
				        	">" + fileName + " </a></li>");

	} 
else if(fileName != null && file == null  && filePath == null){ %>
		File <strong><%=fileName%></strong> does not exist.<br><br>
		Please Note: After <%=fileRetentionPeriodInDays%> days, the files are deleted from this server. 
		</div>
<%} %>
<div align="center">
<br>
<br><br>
If you need assistance, please contact <a href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank" alt="Support">NCICB Application Support
</div>
