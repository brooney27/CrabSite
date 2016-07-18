

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Query() {
        super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		
		Connection con = null;
		String classForName = "oracle.jdbc.driver.OracleDriver";
		String connectionPath = "jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl";

		List<String> header = new ArrayList<String>();
		
		List<String> data = new ArrayList<String>();
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			int numRows=0;
			request.setAttribute("columns", numColumns);
			//get header
			for(int i = 1; i <= numColumns; i++){
				header.add(rsmd.getColumnName(i));
			}
			//loop through rows
			while(rs.next()){
				numRows++;
				//loop through row items
				for(int i = 1; i <= numColumns;i++){
					data.add(rs.getObject(i).toString());
				}
			}
			request.setAttribute("rows", numRows);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("sqlheader", header);
		System.out.println(header);
		request.setAttribute("data", data);
		
		request.getRequestDispatcher("/table.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
