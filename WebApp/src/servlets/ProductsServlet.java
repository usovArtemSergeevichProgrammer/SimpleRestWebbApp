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

import dao.ProductDAO;
import dao.UserDAO;
import impl.ProductDAOImpl;
import impl.UserDAOImpl;
import model.Product;
import model.User;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Field[] fields = Product.class.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(field.getName().toUpperCase());
		}
		
		ProductDAO dao = new ProductDAOImpl();
		List<Product> products = dao.getAllProducts();

		request.setAttribute("fieldNames", fieldNames);
		request.setAttribute("products", products);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
