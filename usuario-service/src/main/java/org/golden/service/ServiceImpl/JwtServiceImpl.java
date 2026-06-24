package org.golden.service.ServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.golden.entity.Usuario;
import org.golden.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {


  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long jwtExpiration;


    @Override
    public String generateToken(Usuario usuario) {


        Map<String, Object> claims = new HashMap<>();

       claims.put("rol", usuario.getRol().getNombreRol());
       claims.put("userName", usuario.getAlias());

       return Jwts.builder()

               .setClaims(claims)
               .setSubject(usuario.getCorreo())
               .setIssuedAt(new Date())

               .setExpiration(
                       new Date(System.currentTimeMillis() + jwtExpiration)
               )

               .signWith(getSignInKey(), SignatureAlgorithm.HS256)

               .compact();
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String correo = extractUsername(token);

        // El token debe pertenecer al usuario y no estar vencido
        return correo.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //Lee la fecha de expiracion embebida en el JWT
    private Date extractExpiration(String token){
        return  Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    //Rechaza token cuya fecha de expiracion ya paso
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(
                secretKey.getBytes()
        );
    }
}
