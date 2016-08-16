package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

@RestController
public class GreetingController {

	private static final String template 
	Connection connection = getConnection();
	Statement stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.product2");

	while (rs.next()) {
		System.out.println("Read from DB: " + rs.getString(3));
		template = rs.getString(3);
	}


	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/index")
	public Product product(@RequestParam(value="name", defaultValue="World") String name) {
		return new Product(counter.incrementAndGet(),
			String.format(template, name));
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
