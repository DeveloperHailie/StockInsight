package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DBConnecton
 *
 */
@WebListener
public class DBConnection implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConnection() {
        // TODO Auto-generated constructor stub
    }

   /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
       Connection conn = (Connection) sce.getServletContext().getAttribute("DBconnection");

        try {
           conn.close();
        } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }
     }

   /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
       Connection conn = null;
        Properties connectionProps = new Properties();
        ServletContext sc = sce.getServletContext();
        
        String DBUrl = sc.getInitParameter("JDBCUrl");
        String DBuser = sc.getInitParameter("DBuser");
        String DBpasswd = sc.getInitParameter("DBpasswd");
        String DBTimeZone = sc.getInitParameter("DBTimeZone");
        
        connectionProps.put("user", DBuser);
        connectionProps.put("password", DBpasswd);
        connectionProps.put("serverTimezone", DBTimeZone);
        
        try {
           conn = DriverManager.getConnection(DBUrl, connectionProps);
          
        } catch (SQLException e) {
           e.printStackTrace();
        }
        System.out.println(conn);
        sc.setAttribute("DBconnection", conn);
     }
  }
