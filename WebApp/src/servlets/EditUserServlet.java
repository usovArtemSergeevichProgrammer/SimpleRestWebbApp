package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDAO;
import dao.UserDAO;
import impl.RoleDAOImpl;
import impl.UserDAOImpl;
import model.Role;
import model.User;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import utils.ServletUtils;

import static utils.AppConstants.*;

import static utils.ServletUtils.*;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoleDAO roleDAO = new RoleDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		User user = null;
		List<Role> roles = null;
		String idStr = request.getParameter(ID);
		try {
			if(idStr != null){
				int userId = Integer.valueOf(idStr);
				roles = roleDAO.getAllRoles();
				user = userDAO.getUserById(new User(userId));
				storeReqAttr(request,USER, user);
				storeReqAttr(request,ROLES, roles);
				forward(request, response, "editUser.jsp");
				return;
			} else{
				storeReqAttr(request, ERROR_MSG, "WRONG REQUEST");
				forward(request, response, "errorHandler.jsp");
				return;
			}
		} catch (Exception e) {
			//to do
			e.printStackTrace();
			storeReqAttr(request, ERROR_MSG, e.getMessage());
			forward(request, response, "errorHandler.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAOImpl();
		RoleDAO roleDAO = new RoleDAOImpl();
		User userFromDB = null;
		User userFromGUI = new User();
		String idStr = request.getParameter(ID);
		try {
			if(idStr != null){
				int id = Integer.valueOf(idStr);
				userFromDB = userDAO.getUserById(new User(id));
				userFromGUI.setId(id);
				userFromGUI.setActive(userFromDB.getIsActive());
				userFromGUI.setPass(userFromDB.getPass());
				//GET FROM FORM
				userFromGUI.setName(request.getParameter("name"));
				userFromGUI.setEmail(request.getParameter("email"));
				userFromGUI.setRole(roleDAO.getRoleByName(new Role(request.getParameter("user_role"))));
				userDAO.updateUser(userFromGUI);
				forward(request, response, "admin");
				
			}
		} catch (Exception e) {
			//to do
			e.printStackTrace();
			storeReqAttr(request, ERROR_MSG, e.getMessage());
			forward(request, response, "errorHandler.jsp");
			return;
		}
	}

}
