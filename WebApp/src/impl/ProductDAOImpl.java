package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import model.Product;
import utils.DBUtils;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<Product> getAllProducts() {
		List<Product> resultProducts = new ArrayList<>();
		Product product = null;
		String sql = "SELECT ID, NAME, DETAILS, IMG_PATH FROM USERS.PRODUCTS";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				product = new Product();
				product.setId(rs.getInt("ID"));
				product.setName(rs.getString("NAME"));
				product.setDetail(rs.getString("DETAILS"));
				product.setImgPath(rs.getString("IMG_PATH"));
				resultProducts.add(product);
			}
			DBUtils.release(conn, null, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return resultProducts;
	}
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAOImpl();
		for (Product product : dao.getAllProducts()) {
			System.out.println(product);
		}
	}

}
