/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.jwt;

import com.cherry.framework.platform.model.ResourceEntity;
import org.springframework.security.core.GrantedAuthority;

/**
 * Jwt 权限实现类
 *
 * @author Leon
 * @version 2018/7/15 20:24
 */
public class JwtAuthority implements GrantedAuthority {

    private ResourceEntity resourceEntity;

    public JwtAuthority(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public String getAuthority() {
        return resourceEntity.getUrl();
    }
}
