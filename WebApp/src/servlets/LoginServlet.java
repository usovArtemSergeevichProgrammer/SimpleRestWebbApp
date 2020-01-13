package servlets;

import static utils.AppConstants.ERROR_DETAIL_MSG;
import static utils.AppConstants.ERROR_MSG;
import static utils.ServletUtils.forward;
import static utils.ServletUtils.storeReqAttr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import impl.UserDAOImpl;
import model.User;
import utils.AppConstants;
import utils.AppSettings;
import utils.CryptoUtils;
import utils.ServletUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        HttpSession session = request.getSession();// 'create' should be 'false'
        System.out.println("********************************");
        System.out.println("unique identifier assigned to this session : " + session.getId());
        System.out.println("session maximum time interval: " + session.getMaxInactiveInterval());
        System.out.println("Creation Time: " + new Date(session.getCreationTime()));
        System.out.println("LastAccessedTime: " +  new Date(session.getLastAccessedTime()));
        System.out.println("is session New: " + session.isNew());
        
        String userToken = request.getParameter(AppConstants.TOKEN);
        if(userToken != null){ // IF THIS IS ACTIVATION CASE
        	String decryptToken = CryptoUtils.decrypt(userToken);//13^John6
        	User user = new User();
        	user.setId(Integer.valueOf(decryptToken.substring(0, decryptToken.indexOf("^"))));
        	new UserDAOImpl().setActive(user, true); // ACTIVATE USER
        	//TODO
        	
        } else {
        	 if(session.getAttribute(AppConstants.LOGGED_USER) == null) {
                 response.setContentType("text/html;charset=UTF-8");
                 RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                 dispatcher.forward(request, response);
             } else {
                 RequestDispatcher dispatcher = request.getRequestDispatcher("home");
                 dispatcher.forward(request, response);
             }
        }
        
       
        
        

            
            
            
            //      } else {
//          response.getWriter().append("ERROR");
//          RequestDispatcher dispatcher = request.getRequestDispatcher("/home.html");
//          dispatcher.include(request, response);
//      }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        try {
            String userName =  request.getParameter("usr");
            String userPass =  CryptoUtils.encrypt(request.getParameter("psw"));
            
//          if(userName.equals("a")) {
//              response.sendRedirect("/WebApp/InternalError.html");
//          }
            
            //CHECK DB
            UserDAO dao = new UserDAOImpl();
            User user = dao.getUserByLogin(new User(userName, userPass));

            HttpSession session = request.getSession();
            System.out.println("********************************");
            System.out.println("unique identifier assigned to this session : " + session.getId());
            System.out.println("session maximum time interval: " + session.getMaxInactiveInterval());
            System.out.println("Creation Time: " + new Date(session.getCreationTime()));
            System.out.println("LastAccessedTime: " +  new Date(session.getLastAccessedTime()));
            System.out.println("is session New: " + session.isNew());

            
            
            if(user != null) {
                if(!user.getIsActive()) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    ServletUtils.storeReqAttr(request, AppConstants.ERROR_MSG, "ACCOUNT IS DEACTIVATED");
                    dispatcher.include(request, response);
                    return;
                } else {
                    session.setAttribute(AppConstants.LOGGED_USER, user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home");
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                ServletUtils.storeReqAttr(request, AppConstants.ERROR_MSG, "Incorrect User Name or Password");
                dispatcher.include(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/InternalError.html");
        }
    
            // TODO Auto-generated method stub
        
        try {
            String userName =  request.getParameter("usr");
            String userPass =  CryptoUtils.encrypt(request.getParameter("psw"));
            
//          if(userName.equals("a")) {
//              response.sendRedirect("/WebApp/InternalError.html");
//          }
            
            //CHECK DB
            UserDAO dao = new UserDAOImpl();
            User user = dao.getUserByLogin(new User(userName, userPass));

            HttpSession session = request.getSession();
            System.out.println("********************************");
            System.out.println("unique identifier assigned to this session : " + session.getId());
            System.out.println("session maximum time interval: " + session.getMaxInactiveInterval());
            System.out.println("Creation Time: " + new Date(session.getCreationTime()));
            System.out.println("LastAccessedTime: " +  new Date(session.getLastAccessedTime()));
            System.out.println("is session New: " + session.isNew());

            
            
            if(user != null) {
                if(!user.getIsActive()) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    ServletUtils.storeReqAttr(request, AppConstants.ERROR_MSG, "ACCOUNT IS DEACTIVATED");
                    dispatcher.include(request, response);
                    return;
                } else {
                    session.setAttribute(AppConstants.LOGGED_USER, user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home");
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                ServletUtils.storeReqAttr(request, AppConstants.ERROR_MSG, "Incorrect User Name or Password");
                dispatcher.include(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            storeReqAttr(request, ERROR_MSG, "SERVER ERROR: " + e.getMessage());
            storeReqAttr(request, ERROR_DETAIL_MSG, "DETAIL TXT:"+e.toString());
            forward(request, response, "error.jsp");
            return;
        }
    
    }}