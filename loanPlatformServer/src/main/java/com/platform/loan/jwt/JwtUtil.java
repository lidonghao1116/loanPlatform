/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

    public static String createJwt(LoginSession loginSession) throws IllegalArgumentException,
                                                             UnsupportedEncodingException {

        Algorithm al = Algorithm.HMAC256(CommonConstants.JWT_SECRETKEY);

        String loginSessionStr = JSONObject.toJSONString(loginSession);

        String token = JWT.create().withClaim(CommonConstants.CLAIM_LOGININFO_KEY, loginSessionStr)
            .withExpiresAt(new Date(System.currentTimeMillis() + CommonConstants.JWT_EXPIRES_TIME))
            .sign(al);

        return token;

    }

    public static DecodedJWT verifyJwt(String token) throws UnsupportedEncodingException {

        //TODO 超时的时候抛异常

        Algorithm algorithm = Algorithm.HMAC256(CommonConstants.JWT_SECRETKEY);

        return JWT.require(algorithm).build().verify(token);
    }

    public static void main(String[] args) throws UnsupportedEncodingException,
                                          InterruptedException {
        LoginSession session = new LoginSession();
        session.setPhoneNo("15170004409");
        String token = JwtUtil.createJwt(session);
        System.out.println(token);

        Thread.sleep(4000);

        System.out.println(JwtUtil.verifyJwt(token));
    }
}
