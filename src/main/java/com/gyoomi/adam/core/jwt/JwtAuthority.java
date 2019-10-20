package com.gyoomi.adam.core.jwt;

import com.gyoomi.adam.rbac.model.Menu;
import org.springframework.security.core.GrantedAuthority;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/20 21:22
 */
public class JwtAuthority implements GrantedAuthority {

    private Menu menu;

    public JwtAuthority(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String getAuthority() {
        return menu.getUrl();
    }
}
