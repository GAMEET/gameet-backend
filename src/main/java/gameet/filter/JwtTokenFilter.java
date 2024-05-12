/*
 * package gameet.filter;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts; import
 * jakarta.servlet.FilterChain; import jakarta.servlet.ServletException; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @Component public class JwtTokenFilter extends OncePerRequestFilter {
 * 
 * @Value("${jwt.secret}") private String jwtSecret;
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException { // Obtener el token JWT del encabezado
 * Authorization final String header = request.getHeader("Authorization");
 * 
 * if (header != null && header.startsWith("Bearer ")) { final String token =
 * header.substring(7); try { // Verificar y parsear el token JWT Claims claims
 * = Jwts.parser() .setSigningKey(jwtSecret) .parseClaimsJws(token) .getBody();
 * 
 * // Si el token es válido, establecer el usuario en el contexto de seguridad
 * request.setAttribute("username", claims.getSubject()); } catch (Exception e)
 * { // En caso de token inválido o expirado, devolver error de autenticación
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * response.getWriter().write("Token inválido o expirado"); return; } }
 * 
 * // Continuar con la cadena de filtros filterChain.doFilter(request,
 * response); } }
 * 
 */