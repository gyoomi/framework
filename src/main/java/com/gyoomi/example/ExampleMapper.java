/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.example;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 示例 Mapper
 *
 * @author Leon
 * @date 2019-10-18 16:01
 */
@Mapper
public interface ExampleMapper extends BaseMapper<Example> {


    @Select("" +
            "<script>" +
            "  SELECT " +
            "    * " +
            "  FROM " +
            "    example " +
            "  WHERE " +
            "    1=1 " +
            "  <if test=\"null != name and '' != name\"> AND name LIKE concat('%', #{name}, '%')</if>" +
            "</script>")
    IPage<Example> findByPage(IPage<Example> page, String name);
}
