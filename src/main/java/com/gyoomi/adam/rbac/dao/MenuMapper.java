/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyoomi.adam.rbac.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * The description of interface
 *
 * @author Leon
 * @date 2019-10-23 9:19
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 查询{@code userId} 所拥有的菜单权限
     *
     * @param userId userId
     * @return 菜单列表
     */
    @Select("" +
            "<script>" +
            "  SELECT " +
            "       t1.* " +
            "  FROM " +
            "  adam_menu t1 " +
            "  LEFT JOIN adam_role_menu t2 ON t2.menu_id = t1.id " +
            "  LEFT JOIN adam_user_role t3 ON t3.role_id = t2.role_id " +
            "  where t3.user_id = #{userId} " +
            "</script>")
    List<Menu> findMenuListByUserId(String userId);
}
