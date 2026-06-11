package org.golden.service.ServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.golden.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long jwtExpiration;


    @Override
    public String generateToken(Usuario usuario) {


        Map<String, Object> claims = new HashMap<>();

       claims.put("rol", usuario.getRol().getNombreRol());
       claims.put("userName", usuario.getUserName());

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

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(
                secretKey.getBytes()
        );
    }
}
