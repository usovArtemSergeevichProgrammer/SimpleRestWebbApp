package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
import sun.rmi.server.Dispatcher;
import utils.AppConstants;
import utils.CryptoUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void commandHandler(User user, String command) {
		UserDAO dao = new UserDAOImpl();
		switch (command) {
		case "A":
			// DE-ACTIVATEE OF ACTIVATE USER
			dao.setActive(user, !user.getIsActive());
			break;
		case "D":
			dao.deleteUser(user);
			break;
		case "U":
			dao.updateUser(user);
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Field[] fields = User.class.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(field.getName().toUpperCase());
		}

		UserDAO dao = new UserDAOImpl();
		// id=11&command=A
		String command = request.getParameter("command");
		if (command != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			User user = dao.getUserById(new User(id));

			if (command.equals("D")) {
				String approve = request.getParameter("approve");
				if (approve == null) {
					request.setAttribute(AppConstants.URI_TO_APPROVE,
							request.getServletPath().replace("/", "") + "?id=" + id + "&command=D");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/areYouSurePage.jsp");
					dispatcher.forward(request, response);
					return;
				} else if (approve.equals("Y")) {
					commandHandler(user, command);
				} else{
					
				}

			} else {
				commandHandler(user, command);
			}
		}

		List<User> users = dao.getAllUsers();

		request.setAttribute("fieldNames", fieldNames);
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(request, response);

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
