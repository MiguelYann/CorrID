package fr.myt.learning.demologging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfiguration implements WebMvcConfigurer {

    private final HttpIncomingOutComingRequest incomingOutComingRequest;

    @Autowired
    public ApiConfiguration(HttpIncomingOutComingRequest incomingOutComingRequest) {
        this.incomingOutComingRequest = incomingOutComingRequest;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingOutComingRequest);
    }
}
