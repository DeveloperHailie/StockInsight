package Controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.BackThread_Cos;
import model.BackThread_Predict;
import model.BackThread_Stock;

/**
 * Application Lifecycle Listener implementation class StartThread
 *
 */
@WebListener
public class StartThread implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartThread() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// 스레드 종료
    	BackThread_Stock stockProgram = (BackThread_Stock) sce.getServletContext().getAttribute("stockP");
    	BackThread_Cos cosProgram = (BackThread_Cos) sce.getServletContext().getAttribute("cosP");
    	BackThread_Predict predictProgram = (BackThread_Predict) sce.getServletContext().getAttribute("predictP");
    	stockProgram.stop();
    	cosProgram.stop();
    	predictProgram.stop();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
// 스레드 시작
    	
    	BackThread_Stock stockProgram = new BackThread_Stock();
    	BackThread_Cos cosProgram = new BackThread_Cos();
    	BackThread_Predict predictProgram = new BackThread_Predict();
    	
		Thread stockThread = new Thread(stockProgram);
		Thread cosThread = new Thread(cosProgram);
		Thread predictThread = new Thread(predictProgram);
		
		stockThread.setDaemon(true);
		cosThread.setDaemon(true);
		predictThread.setDaemon(true);
		
        stockThread.start();
    	cosThread.start();
    	predictThread.start();
    	
    	System.out.println("startThread!");
    	
    	// 속성에 등록
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute("stockP", stockProgram);
    	sc.setAttribute("cosP", cosProgram);
    	sc.setAttribute("predictP", predictProgram);
    }
	
}
