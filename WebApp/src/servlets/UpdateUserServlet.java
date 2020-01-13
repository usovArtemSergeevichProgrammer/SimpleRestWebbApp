package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        User user = (User) request.getSession().getAttribute("user");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.append("<!DOCTYPE html>");
        out.append("<html>");out.append("<head>");out.append("<meta charset='UTF-8'>");
        out.append("<title>Registration'</title>'");
        out.append("<link rel='stylesheet' type='text/css' href='css/login-style.css'>");
        out.append("</head>");
        out.append("<body>");
        out.append("<div class='login-page'>'");
        out.append("<div class='form'>");
        out.append("<form class='register-form' action='update' method='post'>");
        
        out.append("<input type='text' name='new_name' value = '"+user.getName()+"'/>");
        out.append("<input type='password' placeholder='new password' name='pass1' />");
        out.append("<input type='password' placeholder='re-type new password' name='pass2' />");
        out.append("<input type='text' name='email' value = "+user.getEmail()+"'/>");
        out.append("<input type='submit' value='UPDATE'>");
        out.append("</form>");  out.append("</div>");out.append("</div>");out.append("</body>");
        out.append("</html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User actualUser = (User) request.getSession().getAttribute("user");
        User userToUpdate = new User();
        request.getParameter("new_name");
        
    }

}