/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.platform.loan.constant.CommonConstants;
import com.platform.loan.pojo.LoginSession;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 *
 * @author caogu.wyp
 * @version $Id: JwtUtil.java, v 0.1 2018-05-08 下午10:11 caogu.wyp Exp $$
 */
public class JwtUtil {

    //public static void main(String[] args) throws UnsupportedEncodingException {
    //    String token = JwtUtil.createJwt();
    //
    //    System.out.println("token" + token);
    //
    //    DecodedJWT jwt = JwtUtil.verifyJwt(token);
    //
    //    System.out.println("playload:" + jwt.getPayload());
    //    System.out.println("issuer:" + jwt.getIssuer());
    //
    //    for (Map.Entry<String, Claim> entry : jwt.getClaims().entrySet()) {
    //        System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue().asString());
    //    }
    //    LoginSession ls = JSONObject.parseObject(jwt.getClaim("LoginSession").asString(),
    //        LoginSession.class);
    //    System.out.println(ls.getPhoneNo());
    //}

    /**创建JWT*/
    public static String createJwt(LoginSession loginSession) throws IllegalArgumentException,
                                                             UnsupportedEncodingException {
        Algorithm al = null;
        try {

            al = Algorithm.HMAC256(CommonConstants.JWT_SECRETKEY);

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        String loginSessionStr = JSONObject.toJSONString(loginSession);

        String token = JWT.create().withIssuer("loanPlatform")
            .withClaim("LoginSession", loginSessionStr)
            .withExpiresAt(new Date(System.currentTimeMillis() + 360000)).sign(al);
        return token;

    }

    /**验证jwt*/
    public static DecodedJWT verifyJwt(String token) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secretkey");
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("校验失败");
        }
        return jwt;
    }
}
