<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="quizFragen.Frage" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
	
	<% 
	ArrayList<Frage> list = (ArrayList<Frage>)request.getAttribute("list"); 
	%>
	
	<table>
		<tr>
			<th>ID</th>
			<th>Frage</th>7

		</tr>
		<% for(int i = 0; i < list.size(); i++){ %>	
		<tr>
			
			<td>
				<%= list.get(i).getId()%>
			</td>
			<td>
				<%= list.get(i).getFrage()%>
			</td>

			<%} %>
		</tr>
	
	</table>
	
</body>
</html>