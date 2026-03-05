package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AdminApiLoggingAspect {

    // 1. 포인트컷 설정: 어드민 컨트롤러 내의 모든 메서드를 대상으로 함
    // 이 코드는 UserAdminController가 있는 위치를 지정.
    @Pointcut("execution(* org.example.expert.domain.user.controller.UserAdminController.*(..))")
    private void adminApiMethods() {}

    // 2. 어드민 API가 실행되기 전에 로그를 남김.
    @Before("adminApiMethods()")
    public void logAdminApiCall(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // JwtFilter에서 담아둔 userId 꺼냄
            Long userId = (Long) request.getAttribute("userId");
            String requestUrl = request.getRequestURI();
            LocalDateTime requestTime = LocalDateTime.now();

            log.info("Admin API Call - User ID: {}, Time: {}, URL: {}, Method: {}",
                    userId, requestTime, requestUrl, joinPoint.getSignature().getName());
        }
    }
}