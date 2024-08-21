package com.sparta.user.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.common.dto.UserLoginRequestDTO;
import com.sparta.common.entity.User;
import com.sparta.user.security.JwtUtil;
import com.sparta.user.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/login");
        log.info("JwtAuthenticationFilter initialized with login URL: /api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.info("Attempting authentication. Request URI: {}", request.getRequestURI());

            UserLoginRequestDTO requestDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestDTO.class);
            log.info("Login attempt for user: {}", requestDto.getUsername());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error("Error during authentication: {}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();
        log.info("Successful authentication for user: {}", user.getUsername());

        String accessToken = jwtUtil.createAccessToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);

        log.info("JWT tokens created. AccessToken: {}, RefreshToken: {}", accessToken, refreshToken);

        jwtUtil.addTokenToRedis(accessToken, refreshToken);
        jwtUtil.addTokenToCookie(accessToken, response);

        sendResponse(response, HttpStatus.OK, "Login successful");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.warn("Authentication failed: {}", failed.getMessage());
        sendResponse(response, HttpStatus.UNAUTHORIZED, "Login failed: " + failed.getMessage());
    }

    private void sendResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status.value());

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", message);

        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        objectMapper.writeValue(out, responseBody);
        out.flush();

        log.info("Response sent. Status: {}, Message: {}", status, message);
    }
}
