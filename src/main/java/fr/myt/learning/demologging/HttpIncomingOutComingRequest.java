package fr.myt.learning.demologging;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class HttpIncomingOutComingRequest implements AsyncHandlerInterceptor {

    public static final Logger log = LoggerFactory.getLogger(HttpIncomingOutComingRequest.class);

    public static final String CORRELATION_ID = "X-Correlation-Id";
    public static final String CORRELATION_ID_LOG = "correlationId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = generateCorrelationIdFromHeader(request);
        MDC.put(CORRELATION_ID_LOG, header);
        log.info("Start request");
        log.info("Start request request {}", request.getRequestURI());
        log.info("Remote IP {}", request.getRemoteHost());
        return true;
    }

    private String generateCorrelationIdFromHeader(HttpServletRequest request) {
        String correlation = request.getHeader(CORRELATION_ID);
        if (Strings.isBlank(correlation)) {
            correlation = UUID.randomUUID().toString();
        }
        return correlation;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CORRELATION_ID);
        log.info(String.valueOf(response.getStatus()));
    }
}
