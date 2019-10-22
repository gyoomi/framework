package com.gyoomi;

import com.gyoomi.adam.core.CHERRY;
import com.gyoomi.adam.rbac.dao.InterfaceClientMapper;
import com.gyoomi.adam.rbac.model.InterfaceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统配置基类
 *
 * @author Leon
 * @version 2018/5/23 14:40
 */
@SpringBootApplication
public class FrameworkApplication {

    private static final Logger lg = LoggerFactory.getLogger(FrameworkApplication.class);

    public static void main(String[] args) {
        lg.info("Adam-framework start at 【{}】", LocalDateTime.now().format(CHERRY.FORMATTER_DATE_TIME));
        CHERRY.SPRING_CONTEXT = SpringApplication.run(FrameworkApplication.class, args);
        initLocalCache();
    }

    /**
     * 初始化本地Cache
     */
    private static void initLocalCache() {
        // 1. 接口访问客户端
        CHERRY.CACHE_INTERFACE.clear();

        InterfaceClientMapper mapper = CHERRY.SPRING_CONTEXT.getBean(InterfaceClientMapper.class);
        List<InterfaceClient> clients = mapper.selectList(null);
        for (InterfaceClient ic : clients) {
            CHERRY.CACHE_INTERFACE.put(ic.getAk(), ic);
        }
        lg.info("{} AK caches initialized [OK]", clients.size());
    }

}
