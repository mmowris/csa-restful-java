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
	@RequestMapping("/api/v1/product")
	public ArrayList<Product> listProducts() {
		System.out.println("HELLO");
		try {
			ArrayList<Product> output = new ArrayList<Product>();
			Connection connection = null;
			

			connection = DatabaseUrl.extract().getConnection();

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT product2.name, product2.productcode FROM salesforce.product2 LIMIT 10");

			while (rs.next()) {
				Product p = new Product(rs.getString(1), rs.getString(2));
				output.add(p);

			}

			return output;
		} catch(Exception e) {
			System.out.println(e);
		}

		return null;
		
	}

	@RequestMapping("/api/v1/add")
	public ArrayList<Product> newProduct(@RequestParam("name") String name, @RequestParam("pc") String productcode) {

		try {
			ArrayList<Product> output = new ArrayList<Product>();
			Connection connection = null;

			connection = DatabaseUrl.extract().getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO salesforce.product2 (name, productcode) VALUES ('" + name +"'," + "'" +productcode +"');");
			ResultSet rs = stmt.executeQuery("SELECT product2.name, product2.productcode FROM salesforce.product2 WHERE product2.name ='"+name+"'");

			while (rs.next()) {
				Product p = new Product(rs.getString(1), rs.getString(2));
				output.add(p);

			}

			return output;
		} catch(Exception e) {

		}

		return null;
		
	}
}
