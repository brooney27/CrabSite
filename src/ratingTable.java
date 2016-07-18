

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ratingTable
 */
@WebServlet("/ratingTable")
public class ratingTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ratingTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<table>");
		out.println("<tr><th>Origin</th><th>Food</th><th>Rating</th>");
		out.println("<tr><td>Baltimore</td><td>Crab Cake</td><td>9</td>");
		out.println("<tr><td>Shore</td><td>Crab Cake</td><td>10</td>");
		out.println("<tr><td>Baltimore</td><td>Old Bay Wings</td><td>11</td>");
		out.println("<tr><td>Miami</td><td>Mangoes</td><td>12</td>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
