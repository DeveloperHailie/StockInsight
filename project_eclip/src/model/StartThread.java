package model;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartThread implements ServletContextListener {
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
	public StartThread() {
		
	}
	
    public void contextDestroyed(ServletContextEvent sce)  { 
       // 스레드 종료
    	BackThread_Stock stockProgram = (BackThread_Stock) sce.getServletContext().getAttribute("stockP");
    	BackThread_Cos cosProgram = (BackThread_Cos) sce.getServletContext().getAttribute("cosP");
    	stockProgram.stop();
    	cosProgram.stop();
     }

   /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	// 스레드 시작
    	BackThread_Stock stockProgram = new BackThread_Stock();
    	BackThread_Cos cosProgram = new BackThread_Cos();
		Thread stockThread = new Thread(stockProgram);
		Thread cosThread = new Thread(cosProgram);
		
		stockThread.setDaemon(true);
		cosThread.setDaemon(true);

        stockThread.start();
    	cosThread.start();
    	
    	// 속성에 등록
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute("stockP", stockProgram);
    	sc.setAttribute("cosP", cosProgram);
     }
}
