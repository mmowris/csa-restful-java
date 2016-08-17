package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import com.heroku.sdk.jdbc.DatabaseUrl;

@RestController
public class ProductController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	/* GET Function */
	@RequestMapping("/index")
	public ArrayList<Product> listProducts() {

		try {
			ArrayList<Product> output = new ArrayList<Product>();
			Connection connection = null;

			connection = DatabaseUrl.extract().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.product2");

			while (rs.next()) {
				Product p = new Product(rs.getString(4), rs.getString(10));
				output.add(p);

			}

			return output;
		} catch(Exception e) {

		}

		return null;
		
	}

	@RequestMapping("/new")
	public ArrayList<Product> newProduct() {

		try {
			ArrayList<Product> output = new ArrayList<Product>();
			Connection connection = null;

			connection = DatabaseUrl.extract().getConnection();
			Statement stmt = connection.createStatement();
			//String name = @RequestParam(value="name", defaultValue="NewProduct");
			//String productcode = @RequestParam(value="pc", defaultValue="0000");
			String name = "test";
			String productcode = "test";
			Statement.executeUpdate("INSERT INTO salesforce.product2 (name, productcode) VALUES ('" + name +"','" +productcode +"');");
			ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.product2");

			while (rs.next()) {
				Product p = new Product(rs.getString(4), rs.getString(5));
				output.add(p);

			}

			return output;
		} catch(Exception e) {

		}

		return null;
		
	}
}
