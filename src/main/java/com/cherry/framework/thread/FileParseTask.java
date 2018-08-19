/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.thread;

import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/8/19 11:56
 */
@Component
public class FileParseTask extends BaseTaskEventThread<FileEntity> {

    public FileParseTask() {
        super("文件解析服务");
    }

    @Override
    public short getWorkers() {
        return 1;
    }

    @Override
    public void doWork(FileEntity task) throws Exception {
        System.out.println("开始解析文件");
        System.out.println("文件名：" + task.getName() + " 作者：" + task.getCreateUser());
        System.out.println("结束解析文件");
    }
}
