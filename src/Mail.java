

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/Mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Mail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", 8081);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.quitwait", "false");
		//NOTE: Session object is part of javax.mail.Session
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);

		String subject = request.getParameter("subject");
		boolean bodyIsHTML = false;
		String body = request.getParameter("body");
		String from = request.getParameter("from");
		String to = request.getParameter("to");

		try{
			Message message = new MimeMessage(session);
			message.setSubject(subject);
			if (bodyIsHTML) {
				message.setContent(body, "text/html");
			} else {
				message.setText(body);
			}

			Address fromAddress = new InternetAddress(from);
			Address toAddress = new InternetAddress(to);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);

			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		request.getRequestDispatcher("/mailhome.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
