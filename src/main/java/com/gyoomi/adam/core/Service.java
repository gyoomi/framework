/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam.core;

import java.util.List;

/**
 * The description of interface
 *
 * @author Leon
 * @date 2019-10-18 14:50
 */
public interface Service<T> {

    /**
     * 持久化
     *
     * @param model m
     */
    void save(T model);

    /**
     * 批量持久化
     *
     * @param models ms
     */
    void save(List<T> models);

    /**
     * 通过主鍵刪除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     *
     * @param ids ids
     */
    void deleteByIds(String ids);

    /**
     * 更新
     *
     * @param model m
     */
    void update(T model);

    /**
     * 通过id查找
     *
     * @param id 主键
     * @return 结果T
     */
    T findById(Integer id);

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     *
     * @param ids 主键集合
     * @return 结果列表
     */
    List<T> findByIds(String ids);

    /**
     * 获取所有
     *
     * @return 所有结果列表
     */
    List<T> findAll();
}
