package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import impl.UserDAOImpl;
import model.User;
import utils.AppSettings;
import utils.CryptoUtils;
import utils.MailUtils;
import static utils.AppConstants.*;
import static utils.AppSettings.*;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/app/reg.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        User user = new User();
	        String name = request.getParameter(NAME);
	        user.setName(name);
	        user.setPass(CryptoUtils.encrypt(request.getParameter(PASS1)));
	        //request.getParameter(PASS2);
	        String email = request.getParameter(EMAIL);
	        user.setEmail(email);
	        
	        UserDAO dao = new UserDAOImpl();
	        if(dao.createUser(user)) { // user created like NOT ACTIVE
		        String mailUser = getInstance().getProperty(MAIL_USER);
		        String mailPass = getInstance().getProperty(MAIL_PASS);
		        
	            StringBuffer url = request.getRequestURL(); // http://localhost:8080/app/reg
	            String urlPart = url.substring(0, url.lastIndexOf("/")); //result
	            
	            int id = dao.getUserByLogin(user).getId();
	            String command = urlPart + "/login?" + TOKEN + "="+ CryptoUtils.encrypt(id+"^"+name); // 337John5
	            String link = "<h1>To activate your account please click at this <a href='"+command+"'>link</a></h1>";
	            try {
	                MailUtils.sendEmailWithAttachment(mailUser, mailPass, email, "WELCOM TO CRAZY USERS APP", link, null);
	            } catch (AddressException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (MessagingException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            response.getWriter().print("INSERTED. Check your e-mail!");
	        } else {
	            response.getWriter().print("NOT INSERTED. See LOGS!");
	        }
	        
	    }

}
