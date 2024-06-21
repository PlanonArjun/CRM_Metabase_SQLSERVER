/**
 * Package contains classes related to user defined exceptions
 * 
 * @author Ansumam
 */
package crm.ELKUtils;
/*
 * Notes Database Connection: The DB_URL property uses db as the hostname, which
 * corresponds to the service name defined in your Docker Compose file. Docker’s
 * internal DNS will resolve db to the correct container IP address. JDBC
 * Driver: Ensure the PostgreSQL JDBC driver version matches the one specified
 * in your project dependencies. Security: Handle database credentials securely,
 * especially in production environments. Error Handling: Enhance error handling
 * and logging as per your application’s requirements. Metabase Configuration:
 * Ensure Metabase is running (docker-compose up) and accessible at
 * localhost:3000 after launching Docker Compose. By following these steps, your
 * Java application should be able to connect to the PostgreSQL database
 * (metabase_db) running in Docker via Metabase, and insert test results into
 * the test_results table. Adjust the code and configurations based on your
 * specific needs and environment setup.
 */

/*DashboardViewMetabase and MetabaseUtils are same classes via code wise both performs the same operation of inserting data
  into database via jdbc.
  *
  */
 