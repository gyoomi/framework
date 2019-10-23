package com.gyoomi.adam.core.properties;

/**
 * JwtTokenProperties
 *
 * @author Leon
 * @version 2019/10/20 18:33
 */
public class JwtTokenProperties {

    private String headerName;

    private String requestName;

    private String secret;

    private String prefix = "Bearer";

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
