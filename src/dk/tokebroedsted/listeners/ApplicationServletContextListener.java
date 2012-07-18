package dk.tokebroedsted.listeners; /**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();

        String prefix = ctx.getRealPath("/");
        String file = "WEB-INF" + System.getProperty("file.separator") + "classes" + System.getProperty("file.separator") + "log4j.properties";

        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
            System.out.println("Log4J Logging started for application: " + prefix + file);
        } else {
            System.out.println("Log4J Is not configured for application Application: " + prefix + file);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {

    }

}