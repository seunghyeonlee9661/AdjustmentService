package com.sparta.user.filter;

import com.sparta.user.security.JwtUtil;
import com.sparta.user.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.getTokenFromRequest(req, JwtUtil.AUTHORIZATION_HEADER);
        log.info("Processing request. AccessToken: {}", accessToken);

        if (accessToken != null) {
            String accessTokenValue = jwtUtil.substringToken(accessToken);
            if (jwtUtil.validateToken(accessTokenValue)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getUserInfoFromToken(accessTokenValue).getSubject());
                log.info("Token validated. UserDetails: {}", userDetails.getUsername());
                setAuthentication(userDetails, req);
            } else {
                String newAccessToken = jwtUtil.refreshAccessToken(accessToken);
                if (newAccessToken != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(newAccessToken)).getSubject());
                    log.info("Token refreshed. UserDetails: {}", userDetails.getUsername());
                    jwtUtil.addTokenToCookie(newAccessToken, res);
                    setAuthentication(userDetails, req);
                } else {
                    log.warn("Refresh Token expired or invalid.");
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.getWriter().write("Refresh Token expired or invalid.");
                    return;
                }
            }
        }
        filterChain.doFilter(req, res);
    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest req) {
        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Authentication set for user: {}", userDetails.getUsername());
        }
    }
}
