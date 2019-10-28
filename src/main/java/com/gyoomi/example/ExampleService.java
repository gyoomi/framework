package com.gyoomi.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gyoomi.adam.core.BaseService;
import org.springframework.stereotype.Service;

/**
 * 示例 Service
 *
 * @author Leon
 * @version 2019/10/23 22:11
 */
@Service
public class ExampleService extends BaseService {

    /**
     * 示例分页查询
     *
     * @param page 分页及排序对象
     * @param name 名字
     * @return  示例的分页数据
     */
    public IPage<Example> findByPage(IPage<Example> page, String name) {
        return getSpringBean(ExampleMapper.class).findByPage(page, name);
    }
}
