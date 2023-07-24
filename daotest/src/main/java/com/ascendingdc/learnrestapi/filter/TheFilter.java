//package com.ascendingdc.learnrestapi.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//@WebFilter(filterName = "theFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
//public class TheFilter implements Filter {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("something");
//        filterChain.doFilter(servletRequest, servletResponse);
//        logger.info("anything");
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
