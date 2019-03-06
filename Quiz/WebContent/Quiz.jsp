<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="quiz.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="style.css" rel="stylesheet">
<title>Quiz Frage</title>
</head>
<body>
	<%
		Antworten antwort = (Antworten) request.getServletContext().getAttribute("antworten");
		int nr = (int) request.getAttribute("nr");
		Random r = new Random();
		ArrayList<Integer> nrarray = antwort.getNummern();
		int nummer = 1;
		int vorNummer = nr;
		boolean nrtrue = false;

		do {
			nummer = r.nextInt(23);

			for (int i = 0; i < nrarray.size(); i++) {

				if (!(Integer.valueOf(nummer).equals(nrarray.get(i)))) {
					nrtrue = true;

				} else {
					nrtrue = false;
					System.out.print("gleich");
					break;
				}

			}
		} while (!nrtrue);
		System.out.println(nummer);
		nr = nummer;
		nrarray.add(nummer);
	%>
	<form class="form" method="post" action="LoadQuiz">
		<p><%=request.getAttribute("frage")%>
		<p>
			<%=request.getAttribute("m1")%>
			<input type="radio" value="r1" name="radio">
		<p>
			<%=request.getAttribute("m2")%>
			<input type="radio" value="r2" name="radio">
		<p>
			<%=request.getAttribute("m3")%>
			<input type="radio" value="r3" name="radio">
		<p>
			Keine Antwort <input type="radio" value="r4" name="radio">
		<p>
			<input type="hidden" name="nr" value="<%=nr%>"> 
			<input type="hidden" name="vornr" value="<%=vorNummer%>">
			<button class="button">Senden</button>
	</form>
	<p>
	<img src="<%=request.getAttribute("url")%>" alt="Schriftzug" width="400px" length="auto" >
</body>
</html>