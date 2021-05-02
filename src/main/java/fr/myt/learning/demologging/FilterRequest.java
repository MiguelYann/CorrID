package fr.myt.learning.demologging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterRequest extends OncePerRequestFilter {
   public static final Logger log = LoggerFactory.getLogger(FilterRequest.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    log.info("Init filter of request {} ", httpServletRequest.getRequestURL().toString());
    log.info("Try to verify authentication of {}", httpServletRequest.getRemoteUser() );
    log.info("Try to verify authentication of {}", httpServletRequest.getParameter("name") );
    log.info(String.valueOf(httpServletResponse.isCommitted()));

    filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
