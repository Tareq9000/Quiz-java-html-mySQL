<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="quiz.*" %>
		<%@page import="java.util.*" %>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript">

	function myFunction(){
		var x = Math.random()*1000;
		var y = Math.random()*1000;
		document.getElementById("MyButton").style.top = x+'px';
		document.getElementById("MyButton").style.left = y+'px';
		//for(var i = 0; i<5;i++)
		//alert("Hallo");
	}
</script>
<link href="style.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Quiz Ergebnis</title>
</head>
<body>
	<form class="form" method="post" action="Start.jsp">
	<%
	Antworten antwort = (Antworten)request.getServletContext().getAttribute("antworten");
	Dao dao = new Dao();
		int counter = 0;
		for(int i = 1; i < antwort.getAntworten().size(); i++){
			boolean temp = antwort.getAntworten().get(i).booleanValue();
			String correct = temp ? "Richtig!" : "Falsch!";
			if(correct.equals("Richtig!")){
				counter++;
			}
		}
		
		out.print( "<h1>"+counter+" /10 Fragen Richtig("+counter/10f*100+"%)</h1>");
		ArrayList<Highscore> hsList = dao.selectHighscore();
		if(hsList.get(0).getAnzahl() < counter){
			
			dao.updateHighscore(antwort.getName(), counter);
			hsList = dao.selectHighscore();
			out.print( "<h1>Highscore: "+hsList.get(0).getName()+","+hsList.get(0).getAnzahl()*10+"%</h1>");
		}else{
			out.print( "<h1>Highscore: "+hsList.get(0).getName()+","+hsList.get(0).getAnzahl()*10+"%</h1>");
		}
		antwort.getAntworten().clear();
		antwort.getNummern().clear();
		antwort.setName(null);
		antwort.getNummern().add(0);
	%>
	<button class="button">Wiederholen</button>
	<p>
	</form>
	<button id="MyButton" style="position:absolute;" onMouseOver="myFunction();" onClick="myFunction();" >Lösungen</button>
	<img src="quiz1.png" alt="Schriftzug" width="24%" length="24%" >
</body>
</html>