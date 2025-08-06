package com.example.timesheet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.timesheet.security.AESUtil;

@Component
public class EncryptionFilter extends OncePerRequestFilter {

    private final AESUtil aesUtil;

    public EncryptionFilter(AESUtil aesUtil) {
        this.aesUtil = aesUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String method = request.getMethod();

        // Wrap response always to capture output
        CachedBodyHttpServletResponse wrappedResponse = new CachedBodyHttpServletResponse(response);

        try {
            HttpServletRequest requestToUse = request;

            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
                // Only decrypt body if content-type is json and method supports body
                if (request.getContentType() != null && request.getContentType().contains("application/json")) {
                    CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);
                    String encryptedBody = new String(wrappedRequest.getInputStream().readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
                    String decryptedBody = aesUtil.decrypt(encryptedBody);
                    requestToUse = new DecryptedRequestWrapper(wrappedRequest, decryptedBody);
                }
            }

            // Proceed with filter chain
            filterChain.doFilter(requestToUse, wrappedResponse);

            // Encrypt response if content-type json
            String contentType = response.getContentType();
            if (contentType != null && contentType.contains("application/json")) {
                String originalResponse = new String(wrappedResponse.getCapturedBody(), response.getCharacterEncoding());
                String encryptedResponse = aesUtil.encrypt(originalResponse);

                response.setContentLength(encryptedResponse.getBytes().length);
                response.getOutputStream().write(encryptedResponse.getBytes());
            } else {
                // If not json, write original response as is
                byte[] originalBytes = wrappedResponse.getCapturedBody();
                response.getOutputStream().write(originalBytes);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Encryption error: " + e.getMessage());
        }
    }
}
