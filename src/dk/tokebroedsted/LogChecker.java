package dk.tokebroedsted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/19/12
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogChecker {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(LogChecker.class);
        System.out.println("Is trace enabled: " + logger.isTraceEnabled());
        System.out.println("Is debug enabled: " + logger.isDebugEnabled());
        System.out.println("Is warn enabled: " + logger.isWarnEnabled());
        System.out.println("Is error enabled: " + logger.isErrorEnabled());
        System.out.println("Is info enabled: " + logger.isInfoEnabled());


        logger.warn("check warn");
    }
}
