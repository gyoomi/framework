package com.gyoomi.adam.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/18 21:40
 */
@Configuration
@ConfigurationProperties(prefix = "adam")
public class AdamProperties {

    /**
     * status: 1- dev; 2 - test; 3 - product
     * <p>默认是AdamStatus.DEV</p>
     *
     * @see AdamStatus
     */
    private AdamStatus status = AdamStatus.DEV;

    /**
     * eg: v1.0
     */
    private String version;

    @NestedConfigurationProperty
    private Token token = new Token();

    @NestedConfigurationProperty
    private AccessKey accessKey = new AccessKey();


    @NestedConfigurationProperty
    private Security security = new Security();


    public static class Token {

        private String headerName;

        private String requestName;

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
    }

    public static class AccessKey {

        private String headerName;

        private String requestName;

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
    }

    public static class Security {

        @NestedConfigurationProperty
        private SignIn signIn = new SignIn();

        public SignIn getSignIn() {
            return signIn;
        }

        public void setSignIn(SignIn signIn) {
            this.signIn = signIn;
        }
    }

    public static class SignIn {

        /**
         * default : locked
         */
        private boolean lock = true;

        private int maxErrorTimes = 3;

        /**
         * default : 5 minutes
         */
        private long lockedSeconds = 300;

        public boolean isLock() {
            return lock;
        }

        public void setLock(boolean lock) {
            this.lock = lock;
        }

        public int getMaxErrorTimes() {
            return maxErrorTimes;
        }

        public void setMaxErrorTimes(int maxErrorTimes) {
            this.maxErrorTimes = maxErrorTimes;
        }

        public long getLockedSeconds() {
            return lockedSeconds;
        }

        public void setLockedSeconds(long lockedSeconds) {
            this.lockedSeconds = lockedSeconds;
        }
    }

    public AdamStatus getStatus() {
        return status;
    }

    public void setStatus(AdamStatus status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public AccessKey getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(AccessKey accessKey) {
        this.accessKey = accessKey;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
