package dk.tokebroedsted.hibernate;


import org.apache.log4j.Logger;

import javax.servlet.*;

public class SessionFilter implements Filter {
    Logger logger = Logger.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            HibernateHelper.closeSession();
        }
    }

    @Override
    public void destroy() {

    }
}