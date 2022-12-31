package com.plexus.apisuperheroes.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CorrelationInterceptor implements HandlerInterceptor {

  private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
  private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    final String correlationId = getCorrelationIdFromHeader(request);
    MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);

    return true;
  }

  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final Exception ex) {
    MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
  }

  private String getCorrelationIdFromHeader(final HttpServletRequest request) {
    String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
    if (StringUtils.isBlank(correlationId)) {
      correlationId = generateUniqueCorrelationId();
    }
    return correlationId;
  }

  private String generateUniqueCorrelationId() {
    return UUID.randomUUID().toString();
  }
}