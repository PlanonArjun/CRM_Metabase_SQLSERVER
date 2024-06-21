package crm.ELKUtils;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import crm.enums.ConfigProperties;
import crm.utilities.JsonUtils;
import crm.utilities.PropertyUtils;
import io.restassured.response.Response;

public class Dashboard_Metabase_SqlServer {
	private Dashboard_Metabase_SqlServer() {
	}

	public static void sendDetailsToMetabase(String testname, String status) throws Exception {

		// JDBC URL construction
		String serverName = JsonUtils.get(ConfigProperties.DB_SERVERNAME);
		String databaseName = JsonUtils.get(ConfigProperties.DB_NAME);
		String encrypt = JsonUtils.get(ConfigProperties.DB_ENCRYPT);
		String trustServerCertificate =JsonUtils.get(ConfigProperties.DB_TRUSTSERVERCERTIFICATE);
		String integratedSecurity = JsonUtils.get(ConfigProperties.DB_INTEGRATEDSECURITY);

		String jdbcUrl = String.format(
				"jdbc:sqlserver://%s;databaseName=%s;encrypt=%s;trustServerCertificate=%s;integratedSecurity=%s;",
				serverName, databaseName, encrypt, trustServerCertificate, integratedSecurity);

		//jdbc:sqlserver://DESKTOP-IIJ6BC7\\SQLEXPRESS;databaseName=TestResult;encrypt=true;trustServerCertificate=true;integratedSecurity=true;
		
		System.setProperty("java.library.path", JsonUtils.get(ConfigProperties.JAVA_LIBRARYPATH));
		Class.forName(JsonUtils.get(ConfigProperties.JDBC_DRIVERCLASS));

		if (PropertyUtils.get(ConfigProperties.SENDRESULTTOELK).equalsIgnoreCase("yes")) {
			Map<String, String> map = new HashMap<>();
			map.put("testName", testname);
			map.put("status", status);
			map.put("executionTime", LocalDateTime.now().toString());

			Response response = given().header("Content-Type", "application/json").log().all().body(map)
					.post(PropertyUtils.get(ConfigProperties.ELASTICURL));

			Assert.assertEquals(response.statusCode(), 201);

			response.prettyPrint();
		}

		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {
			// Create table if it doesn't exist
			createTableIfNotExists(conn);

			// Prepare insert statement
			String insertSQL = "INSERT INTO test_results (test_name_test_method, status, execution_time) VALUES (?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
				pstmt.setString(1, testname);
				pstmt.setString(2, status);
				pstmt.setObject(3, LocalDateTime.now());

				int affectedRows = pstmt.executeUpdate();
				if (affectedRows == 0) {
					throw new SQLException("Inserting test result failed, no rows affected.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error connecting to the database or executing query", e);
		}
	}

	private static void createTableIfNotExists(Connection conn) throws SQLException {
		String createTableSQL = "IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'test_results') "
				+ "BEGIN " + "CREATE TABLE test_results ( " + "id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(), "
				+ "test_name_test_method VARCHAR(255) NOT NULL, " + "status VARCHAR(50) NOT NULL, "
				+ "execution_time DATETIME NOT NULL " + ") " + "END";

		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(createTableSQL);
		}
	}
}
