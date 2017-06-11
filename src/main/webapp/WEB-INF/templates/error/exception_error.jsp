<%
	response.sendError(500 ,(String) request.getAttribute("javax.servlet.error.message"));
%>