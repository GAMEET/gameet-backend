package gameet.filter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import gameet.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/gameet/*")
public class JwtFilter extends OncePerRequestFilter {
	
    // Lista de rutas que requieren autenticación JWT
    private static final List<String> JWT_AUTH_REQUIRED_PATHS = Arrays.asList("/api");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String requestUri = request.getRequestURI();
        
        if (isJwtAuthenticationRequired(requestUri)) {
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            token = authorizationHeader.substring(7);
	            username = JwtUtil.extractUsername(token);
	        }
	
	        if (username != null && JwtUtil.validateToken(token)) {
	            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            request.setAttribute("username", username);
	        } else {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return;
	        }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private boolean isJwtAuthenticationRequired(String requestUri) {
        return JWT_AUTH_REQUIRED_PATHS.stream().anyMatch(requestUri::startsWith);
    }
}
