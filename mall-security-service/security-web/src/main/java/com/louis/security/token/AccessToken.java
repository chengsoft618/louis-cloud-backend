package com.louis.security.token;

import io.jsonwebtoken.Claims;

/**
 * @author John·Louis
 * @date create in 2019/4/14
 */
public class AccessToken implements Token{

    private final String rawToken;
    private Claims claims;

    protected AccessToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }

}
