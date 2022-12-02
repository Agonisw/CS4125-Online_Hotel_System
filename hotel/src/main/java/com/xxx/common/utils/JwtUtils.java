package com.xxx.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "authority.jwt") //从配置文件中获取数据
public class JwtUtils {

	private long expire;
	private String secret;
	private String heard;


public String 	generateToken(String username,Long userId){
	Date nowDate=new Date();
	Date expireDate=new Date(nowDate.getTime() + 1000 * expire);
	return 	Jwts.builder()
			.setHeaderParam("typ","JWT")
			.setSubject(username)
			.setId(userId.toString())
			.setIssuedAt(nowDate)
			.setExpiration(expireDate)
			.signWith(SignatureAlgorithm.HS512,secret)
			.compact();
}


	public Claims getClaimsByToken(String jwt){

		try{
			return Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(jwt)
					.getBody();
		}
		catch(Exception e){
			return null;
		}
	}


	public boolean isTokenExpried(Claims claims) {
		return claims.getExpiration().before(new Date());
	}

}





