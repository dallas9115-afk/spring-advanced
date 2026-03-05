// org.example.expert.config.AdminApiLoggingAspect.java
package org.example.expert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminApiLoggingAspect {

    private final ObjectMapper objectMapper; // JSON 변환용

    // 두 어드민 컨트롤러를 대상으로 설정
    @Pointcut("execution(* org.example.expert.domain.user.controller.UserAdminController.*(..)) || " +
            "execution(* org.example.expert.domain.comment.controller.CommentAdminController.*(..))")
    private void adminApiMethods() {}

    @Around("adminApiMethods()") // @Around 사용
    public Object logAdminApi(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Long userId = (Long) request.getAttribute("userId");
        String url = request.getRequestURI();
        LocalDateTime startTime = LocalDateTime.now();

        // 1. 요청 본문(RequestBody) 파싱
        String requestBody = objectMapper.writeValueAsString(joinPoint.getArgs());

        log.info(">>> [Admin API Request] User: {}, Time: {}, URL: {}, Body: {}",
                userId, startTime, url, requestBody);

        // 2. 실제 메서드 실행
        Object result = joinPoint.proceed();

        // 3. 응답 본문(ResponseBody) 파싱
        String responseBody = objectMapper.writeValueAsString(result);

        log.info("<<< [Admin API Response] User: {}, URL: {}, Response: {}",
                userId, url, responseBody);

        return result;
    }
}