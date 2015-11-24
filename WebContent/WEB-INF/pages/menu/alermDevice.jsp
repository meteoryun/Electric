<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>load</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
   </head>
  
  <body>
    <table width="100%" border="0" id="table8">
    
	
				<tr>
					<td align="left" valign="middle"  style="word-break: break-all">
					<span class="style1">
						<s:property value="deviceRunMsg" escapeHtml="false"/>
					</span></td>
				</tr>		
	
				<tr>
					<td align="left" valign="middle"  style="word-break: break-all">
					<span class="style1">
					<font color="red"><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></font>
					</span></td>
				</tr>			
	</table>
  </body>
</html>