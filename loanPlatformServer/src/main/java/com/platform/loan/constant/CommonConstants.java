/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.constant;

/**
 *  公共常量
 * @author caogu.wyp
 * @version $Id: CommonConstants.java, v 0.1 2018-05-05 上午11:04 caogu.wyp Exp $$
 */
public class CommonConstants {

    public static final String IMAGE_CODE_HEADER_KEY     = "IMAGE_CODE_HEADER_KEY";

    public static final String JWT_SECRETKEY             = "loanPlatform~!@#$%^&*()";

    public static final String AUTHORIZATION_HEARDER_KEY = "Authorization";

    public static final String CONTENT_TYPE_HEADER_KEY   = "Content-Type";

    public static final String CLAIM_LOGININFO_KEY       = "CLAIM_LOGININFO_KEY";

    //jwt失效时间，毫秒
    public static final long   JWT_EXPIRES_TIME          = 3600000 * 24L;

    public static final String ALIPAY_APPID              = "2018052660264066";

    public static final String ALIPAY_PUBLIC_KEY         = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsoeLwtaoIEiAPuKOGwzzyhmzwCf5vZ3feHyec9uOKwThCea606L+NACJq7PR5uojDAgjG0IEG3cga52Qr36bVsbCNkyjLjqIGmL9fcmvPWxz2fOyVo8dQT0iIDLlougF1WKJTn1Yw2GJzXbWln1goSrNU+5kfzts/SsqnavcPVKYn1tbTUj8ZhGnHUgBvSdmHjr4wk8E2nqBuiq3Vd8V3ph9A+csOgbkAnhlx5oETxu5RCfCWRQXzkw6rCE1uaRUNP03Ze9V4kgNmG9ZjbzFZyI0VWAjSuWXJn5xWpmFhB/HCieMdFaYj7x8Pf4vSyUWOXYVphGfZu86mfueEu2h1wIDAQAB";

    public static final String PRIVATE_KEY               = "MIIEogIBAAKCAQEAsoeLwtaoIEiAPuKOGwzzyhmzwCf5vZ3feHyec9uOKwThCea6"
                                                           + "06L+NACJq7PR5uojDAgjG0IEG3cga52Qr36bVsbCNkyjLjqIGmL9fcmvPWxz2fOy"
                                                           + "Vo8dQT0iIDLlougF1WKJTn1Yw2GJzXbWln1goSrNU+5kfzts/SsqnavcPVKYn1tb"
                                                           + "TUj8ZhGnHUgBvSdmHjr4wk8E2nqBuiq3Vd8V3ph9A+csOgbkAnhlx5oETxu5RCfC"
                                                           + "WRQXzkw6rCE1uaRUNP03Ze9V4kgNmG9ZjbzFZyI0VWAjSuWXJn5xWpmFhB/HCieM"
                                                           + "dFaYj7x8Pf4vSyUWOXYVphGfZu86mfueEu2h1wIDAQABAoIBABeaA8gGSMO70fh+"
                                                           + "SvsYg0I8k57bqhB/RoPPNcNrSyg0xOtiLWC9M438ZDbm1uEIIwn8KWf5GkHnRcn0"
                                                           + "UqRgwHiAXjx/dTMvWb7jR0/R0fD03Xv606DN2rg9NL28/M7EjcDf/fb6azmpM2aO"
                                                           + "R2AtwxCLcAmggnivioENq9ycU8lnYWYpypBQKcjqQI0JOFzc1R8jflKwfK10PWTe"
                                                           + "ifZdDjbquVC9GfAYGVOfwC6yBe4KtOc88LOMWsESF82s5/+DNJjq3EHtK57EEsKc"
                                                           + "uxiYfoJTqm6tdlVl3T+hbD4cVqfiDMsK+tB7A5Cos98BniXBDbq+NiK8HFsWohiq"
                                                           + "HUY/ocECgYEA3ZPVTKUFuC85ofJ7w/NEt78k5e8g+ypAfgcYDlZOdq1wdkcH+5kF"
                                                           + "bNMYqba3ZXWhWwRJpDjVkU3Dj2KNyE3kSpU1QVD+T0Tq+kbD991/l7WWPin+VFgJ"
                                                           + "aexKYq9nOFMaJG3AU/Vdc1wRAKFKSi5nbERLdYeMJ3IGIZ7WR5xw5OsCgYEAzkOw"
                                                           + "HIqidz2UXz/IiUAol1M/wGHAnMcg37yl9a1VZDdGRrHPCXj/S9FDQaq0Jz02NB3G"
                                                           + "PrtllA1F++ZksV4uis1rJbBwCNy4+3vCnVXV6B1FnbGmNe0QGieRjKxceQiUgdcI"
                                                           + "QlJciryk93vrh0mtQC72mIbvZNViNSGNK25OK8UCgYA5huMvBf0pJ6Gn6SO26Rbj"
                                                           + "Oc4joGUIsJyT29WkC0ZNy5PSMXjiGoVtDpVtopGU1uhwj/lqmJLiKbVnKagz4/0s"
                                                           + "W/a6oYOVIwh6uBqy5J/2zF9YfmCYl07CYGWjn3SGDBVSdegkJCUCCVoktrtp7QhO"
                                                           + "9KA+wFVj/FeZLPrnFre8xwKBgAr+VpOe2/vo/1B4UNLn5YHzQPGbBC4W+uyR3HKU"
                                                           + "s3ls7MT10ygZF9pNo/0LASEaRRpP//H2APr8Hh/H7tP5RhfFzREsLzx5ry5QMVY7"
                                                           + "bfQ6Iyy9fDNhmDscoshRo3gb+fZVpK4MP7xxQ4WhSDNkvOQv3JrSjk3chah5SlHn"
                                                           + "RilVAoGAf+UWfsMvZprUDwsvjCfKu7QLjORGeN+yreG7kyWg+F8T/lMU3T1bSMUB"
                                                           + "VJzBFPy4TD38BWX77ZhYtIEgp7tCqIVaMRJvk074/9wCG2t4tF4DqmVSK1Vbpbu1"
                                                           + "ZP+JvDKqkz5f5YTOjBIctwMB7iOkuocLk3118iMfmwDFP1+OCuA=";

    public static final String PRIVATE_KEY_PKCS8         = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCyh4vC1qggSIA+"
                                                           + "4o4bDPPKGbPAJ/m9nd94fJ5z244rBOEJ5rrTov40AImrs9Hm6iMMCCMbQgQbdyBr"
                                                           + "nZCvfptWxsI2TKMuOogaYv19ya89bHPZ87JWjx1BPSIgMuWi6AXVYolOfVjDYYnN"
                                                           + "dtaWfWChKs1T7mR/O2z9Kyqdq9w9UpifW1tNSPxmEacdSAG9J2YeOvjCTwTaeoG6"
                                                           + "KrdV3xXemH0D5yw6BuQCeGXHmgRPG7lEJ8JZFBfOTDqsITW5pFQ0/Tdl71XiSA2Y"
                                                           + "b1mNvMVnIjRVYCNK5ZcmfnFamYWEH8cKJ4x0VpiPvHw9/i9LJRY5dhWmEZ9m7zqZ"
                                                           + "+54S7aHXAgMBAAECggEAF5oDyAZIw7vR+H5K+xiDQjyTntuqEH9Gg881w2tLKDTE"
                                                           + "62ItYL0zjfxkNubW4QgjCfwpZ/kaQedFyfRSpGDAeIBePH91My9ZvuNHT9HR8PTd"
                                                           + "e/rToM3auD00vbz8zsSNwN/99vprOakzZo5HYC3DEItwCaCCeK+KgQ2r3JxTyWdh"
                                                           + "ZinKkFApyOpAjQk4XNzVHyN+UrB8rXQ9ZN6J9l0ONuq5UL0Z8BgZU5/ALrIF7gq0"
                                                           + "5zzws4xawRIXzazn/4M0mOrcQe0rnsQSwpy7GJh+glOqbq12VWXdP6FsPhxWp+IM"
                                                           + "ywr60HsDkKiz3wGeJcENur42IrwcWxaiGKodRj+hwQKBgQDdk9VMpQW4Lzmh8nvD"
                                                           + "80S3vyTl7yD7KkB+BxgOVk52rXB2Rwf7mQVs0xiptrdldaFbBEmkONWRTcOPYo3I"
                                                           + "TeRKlTVBUP5PROr6RsP33X+XtZY+Kf5UWAlp7Epir2c4UxokbcBT9V1zXBEAoUpK"
                                                           + "LmdsREt1h4wncgYhntZHnHDk6wKBgQDOQ7AciqJ3PZRfP8iJQCiXUz/AYcCcxyDf"
                                                           + "vKX1rVVkN0ZGsc8JeP9L0UNBqrQnPTY0HcY+u2WUDUX75mSxXi6KzWslsHAI3Lj7"
                                                           + "e8KdVdXoHUWdsaY17RAaJ5GMrFx5CJSB1whCUlyKvKT3e+uHSa1ALvaYhu9k1WI1"
                                                           + "IY0rbk4rxQKBgDmG4y8F/SknoafpI7bpFuM5ziOgZQiwnJPb1aQLRk3Lk9IxeOIa"
                                                           + "hW0OlW2ikZTW6HCP+WqYkuIptWcpqDPj/Sxb9rqhg5UjCHq4GrLkn/bMX1h+YJiX"
                                                           + "TsJgZaOfdIYMFVJ16CQkJQIJWiS2u2ntCE70oD7AVWP8V5ks+ucWt7zHAoGACv5W"
                                                           + "k57b++j/UHhQ0uflgfNA8ZsELhb67JHccpSzeWzsxPXTKBkX2k2j/QsBIRpFGk//"
                                                           + "8fYA+vweH8fu0/lGF8XNESwvPHmvLlAxVjtt9DojLL18M2GYOxyiyFGjeBv59lWk"
                                                           + "rgw/vHFDhaFIM2S85C/cmtKOTdyFqHlKUedGKVUCgYB/5RZ+wy9mmtQPCy+MJ8q7"
                                                           + "tAuM5EZ437Kt4buTJaD4XxP+UxTdPVtIxQFUnMEU/LhMPfwFZfvtmFi0gSCnu0Ko"
                                                           + "hVoxEm+TTvj/3AIba3i0XgOqZVIrVVulu7Vk/4m8MqqTPl/lhM6MEhy3AwHuI6S6"
                                                           + "hwuTfXXyIx+bAMU/X44K4A==";

}
