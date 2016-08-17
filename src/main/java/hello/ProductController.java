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

	Connection connection = null;

	


	@RequestMapping("/greeting")
	public ArrayList<Product> func() {
		ArrayList<String> output = new ArrayList<String>();
		connection = DatabaseUrl.extract().getConnection();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.product2");
		while (rs.next()) {
			Product p = new Product(rs.getString(3), rs.getString(9));
			output.add(p);

		}
		return output
	}
	
	/*public Product product() {
		
		return new Product();
	}*/
}
