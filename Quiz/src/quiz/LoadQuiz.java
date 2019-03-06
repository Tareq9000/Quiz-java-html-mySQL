package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadQuiz
 */
@WebServlet("/LoadQuiz")
public class LoadQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadQuiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		Antworten antworten = new Antworten();
		antworten.getNummern().add(0);
		this.getServletContext().setAttribute("antworten", antworten);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Dao dao = new Dao();
		String nameText = request.getParameter("nameText");
		if (nameText != null) {
			((Antworten) request.getServletContext().getAttribute("antworten")).setName(nameText);
			;
		}
		int nr = 1;
		if (request.getParameter("nr") != null) {
			nr = Integer.parseInt(request.getParameter("nr"));
		}
		int answer = 0;
		int CorrectAnswer = 1;
		int vorNummer = 0;
		if (request.getParameter("vornr") != null) {
			vorNummer = Integer.parseInt(request.getParameter("vornr"));
		}
		if (request.getParameter("radio") != null) {
			if (request.getParameter("radio").equals("r1")) {
				answer = 1;
			} else if (request.getParameter("radio").equals("r2")) {
				answer = 2;
			} else if (request.getParameter("radio").equals("r3")) {
				answer = 3;
			} else if (request.getParameter("radio").equals("r4")) {
				answer = 4;
			}

		}
		if (dao.selectTable(vorNummer)[1] != null) {
			CorrectAnswer = Integer.parseInt(dao.selectTable(vorNummer)[1]);
		}

		if (answer == CorrectAnswer) {
			Antworten antwort = ((Antworten) request.getServletContext().getAttribute("antworten"));
			antwort.getAntworten().add(Boolean.TRUE);

		} else {
			Antworten antwort = ((Antworten) request.getServletContext().getAttribute("antworten"));
			antwort.getAntworten().add(Boolean.FALSE);
		}
		
		if (((Antworten) request.getServletContext().getAttribute("antworten")).getAntworten().size() <= 10) {
			String[] s = dao.selectTable(nr);
			request.setAttribute("url", dao.selectBild(nr));
			request.setAttribute("frage", s[0]);
			request.setAttribute("answer", s[1]);
			request.setAttribute("m1", s[2]);
			request.setAttribute("m2", s[3]);
			request.setAttribute("m3", s[4]);
			request.setAttribute("nr", nr);
			RequestDispatcher rd = request.getRequestDispatcher("Quiz.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("Ergebnis.jsp");
			request.setAttribute("antworten", ((Antworten) request.getServletContext().getAttribute("antworten")));
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
