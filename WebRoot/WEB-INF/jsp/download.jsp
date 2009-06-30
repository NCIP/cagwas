<%@ page language="java" import="java.util.*, java.net.URLEncoder
, gov.nih.nci.cagwas.web.action.CagwasConstants, javax.servlet.jsp.JspWriter" pageEncoding="UTF-8"%>

<%! String filePath;%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
filePath = (String)request.getAttribute("filePath");
%>
<script type="text/javascript" src="js/yahoo.js"></script>
<script type="text/javascript" src="js/event.js"></script>
<script type="text/javascript" src="js/container_core.js"></script>
<script type="text/javascript" src="js/dom.js"></script>
<script type="text/javascript" src="js/animation.js"></script>
<script type="text/javascript" src="js/connection.js"></script>
<script type="text/javascript" src="js/carousel.js"></script>
<script type="text/javascript" src="js/columnav.js"></script>
<script type="text/javascript" src="js/overlib.js"></script>
<link href="css/carousel.css" rel="stylesheet" type="text/css"/>
<link href="css/columnav.css" rel="stylesheet" type="text/css"/>
<style>
.carousel-component ul.carousel-list li a.up span {
          background-image: url(images/folder_add.png) !important;
      }
</style> 
<script type="text/javascript">
	try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}
	var cn;
  function init() {
      // ... do other stuff first, perhaps ...
      var cn_cfg = { prevElement: 'columnav-prev', source: document.getElementById('file-list')};
       cn = new YAHOO.extension.ColumNav('columnav', cn_cfg);
  }
  YAHOO.util.Event.addListener(window, 'load', init);
</script>
<div class="divHeader">
Download Files
</div>

<div class="divMain">	
<table id="" width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="150">
				Select the file(s) to download
			</td>
		</tr>
		<tr>
			<td>
			<div align="center">
			<div id="columnav" class="carousel-component">
			  <div class="carousel-clip-region">
			    <ul class="carousel-list"></ul>
			  </div>
			</div>
			
			</div>
			
			<ul id="file-list" style="display:none">
			
			<%!
			public void visitAllDirsAndFiles(java.io.File dir, JspWriter out) {
			
				String fname = dir.toString().substring(dir.toString().lastIndexOf(java.io.File.separator)+1);
				
			        if(dir.isFile())	{
			        	//System.out.println(root + " - " + dir.toString());
			        	try	{
			        	String fPath = dir.getPath().toString().substring(this.filePath.length()+1);
						fPath = fPath.replace("/", java.io.File.separator).replace("\\", java.io.File.separator);;
			        	fPath = URLEncoder.encode(fPath,"UTF-8");
				        	out.println("<li><a href=getBulkDownloadFile.do?file=" + fPath +
				        	" onmouseover=\"return overlib(\'Name: " + fname + "<br>Size: " +  (dir.length()/1000) + "KB <br> Modified: " + new Date(dir.lastModified()).toString() + "\', CAPTION, \'File Details\', DELAY, 250, VAUTO, HAUTO);\" onmouseout=\"return nd();\"" + 
				        	">" + fname + " </a></li>");
				        	
			    		}
			    		catch(Exception e)	{};
			        }
			    
			        if (dir.isDirectory()) {
				        try	{
								out.println("<li><a href=\'#\'>" + fname + "</a>\n<ul><li style=\"display:none\">" +
								"<a href=\"javascript:cn.carousel.scrollPrev();\" class=\"up\">.. up one directory</a>\n</li>");
				    		}
			    		catch(Exception e)	{};
			    		
			            String[] children = dir.list();
			            for (int i=0; i<children.length; i++) {
			                visitAllDirsAndFiles(new java.io.File(dir, children[i]), out);
			            }
			            try	{
			            	out.println("</ul>");
			            }	
			            catch(Exception e){}
			        }
			    }
			 %> 
			 
			<%
			String root = filePath;
			java.io.File file;
			java.io.File dir = null;
			try {
				dir = new java.io.File(root);
				java.io.File[]fl = dir.listFiles();
				for(int i=0; i<fl.length; i++)	{
					visitAllDirsAndFiles(fl[i], out);
				}
			} catch(Exception e) {
				System.out.println(e);
			}
			
			
			%>
			</ul>
			<div align="center">
				If you are having difficulties using the file browser, click 
				<a href="#" onclick="document.getElementById('file-list').style.display = (document.getElementById('file-list').style.display=='none') ? '' : 'none'; return false;">here</a>.
				<p>
				<br>
				<br><br>
				<a href="javascript:history.back()">Go Back</a> 
				</div>
			</td>
		</tr>
	</table>

			
