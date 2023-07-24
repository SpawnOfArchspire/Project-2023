package com.ascendingdc.learnrestapi.filter;

import com.ascendingdc.learnrestapi.dto.RoleDto;
import com.ascendingdc.learnrestapi.dto.UserDto;
import com.ascendingdc.learnrestapi.service.UserService;
import com.ascendingdc.learnrestapi.service.impl.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
@WebFilter(filterName = "JwtFilter", urlPatterns = {"/"}, dispatcherTypes = {DispatcherType.REQUEST})
@Component
public class JwtFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @Value("${AUTH_URI}")
    private String AUTH_URI;

    @Value("${AUTH_URI_EXTERNAL}")
    private String AUTH_URI_EXTERNAL;

    @Override
    public void initFilterBean() throws ServletException{
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this.getServletContext());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        int statusCode = authorization(request);
        if(statusCode == HttpServletResponse.SC_ACCEPTED){
            filterChain.doFilter(request, response);
        } else{
            response.sendError(statusCode);
        }
    }

    private int authorization(HttpServletRequest request) {
        int statusCode = HttpServletResponse.SC_ACCEPTED;
        String incomingUri = request.getRequestURI();
        logger.info("within authorization(....), incomingUri={}",incomingUri);
        if(incomingUriRequestForAuth(incomingUri)){
            return HttpServletResponse.SC_ACCEPTED;
        }
        try{
            String wholeTokenString = request.getHeader("Authorization");
            if(wholeTokenString != null){
                String token = wholeTokenString.split("1")[1].trim();
                logger.info("within authorization(...),token={}",token);
                if(token == null || token.trim().isEmpty()){
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }
                Claims claims = jwtService.decryptJwtToken(token);
                if(!isClaimValid(claims))
                    return HttpServletResponse.SC_UNAUTHORIZED;
                logger.info("within authorized(....), parsed claims = {}",claims);

                if(!isUserRetrievedByUserIdValid(claims.getId()))
                    return HttpServletResponse.SC_UNAUTHORIZED;

                String httpMethodValue = request.getMethod();
                boolean isRequestUriAllowedToBeAccessed = checkRequestUriAuthorization(claims, httpMethodValue, incomingUri);
                if(isRequestUriAllowedToBeAccessed){
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }
            }
        } catch (Exception e){
            logger.error("Exception is thrown when parsing JWT token, error = {}",e.getMessage());
        }
        return statusCode;
    }

    private boolean checkRequestUriAuthorization(Claims claims, String httpMethodValue, String requestUri) {
        boolean isAuthorized = false;
        String allowedResources = findAllowedResourcesUsingHttpMethodValueWithClaims(claims, httpMethodValue);
        String[] allowedResourcesArray = allowedResources.split("");
        for(String eachAllowedResource : allowedResourcesArray){
            logger.info("requestUri = {}, eachAllowedResource = {}",requestUri, eachAllowedResource);
            if(requestUri.trim().toLowerCase().startsWith(eachAllowedResource.trim().toLowerCase())){
                isAuthorized = true;
                break;
            }
        }
        return isAuthorized;
    }

    private String findAllowedResourcesUsingHttpMethodValueWithClaims(Claims claims, String httpMethodValue) {
        String allowedResources = "";
        switch(httpMethodValue){
            case "GET" :
                allowedResources = (String)claims.get("allowedReadResources");
                break;
            case "POST" :
                allowedResources = (String)claims.get("allowedCreateResources");
                break;
            case "PUT" :
                allowedResources = (String)claims.get("allowedUpdateResources");
                break;
            case "PATCH" :
                allowedResources = (String)claims.get("allowedUpdateResources");
                break;
            case "DELETE" :
                allowedResources = (String)claims.get("allowedDeleteResources");
                break;
        }
        return allowedResources;
    }

    private boolean isUserRetrievedByUserIdValid(String id) {
        boolean isValid = false;
        if(id != null){
            UserDto userDto = userService.getUserById(Long.valueOf(id));
            if(userDto != null){
                isValid = true;
                logger.info("Now using userId={}, retrieved UserDto={}", id, userDto);
            }
        }
        return isValid;
    }

    private boolean isClaimValid(Claims claims) {
        boolean isClaimValid = true;
        if(claims == null || claims.isEmpty())
            isClaimValid = false;
        return isClaimValid;
    }

    private boolean incomingUriRequestForAuth(String incomingUri) {
        boolean isUriRequestForAuth = false;
        if(incomingUri.equalsIgnoreCase(AUTH_URI)){
            isUriRequestForAuth = true;
        }
        return isUriRequestForAuth;
    }


}
