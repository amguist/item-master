package org.neoworkz.fms.item.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.neoworkz.fms.item.validations.HeaderValidation;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    private static final String START_TIME_REQUEST_VALUE    =   "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS") || !request.getDispatcherType().name().equals("REQUEST")) {
            return true;
        }
        request.setAttribute(START_TIME_REQUEST_VALUE, LocalDateTime.now());
        try {
            HeaderValidation.validateRequestHeaders(request);
            return true;
        } catch (Exception eX) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            throw eX;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalDateTime requestTime = (LocalDateTime) request.getAttribute(START_TIME_REQUEST_VALUE);
        request.removeAttribute(START_TIME_REQUEST_VALUE);

        LocalDateTime responseTime = LocalDateTime.now();
        log.info(String.format(
            "[Metric] endpoint:%s | method:%s | responseCode:%s | duration:%s ms | startTime:%s | endTime:%s", request.getServletPath(), request.getMethod(), response.getStatus(), Duration.between(requestTime, responseTime).toMillis(), requestTime, responseTime
        ));
        MDC.clear();
    }
}
