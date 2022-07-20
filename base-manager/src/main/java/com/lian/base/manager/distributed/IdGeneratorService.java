package com.lian.base.manager.distributed;

/**
 * <p>
 * 分布式ID分发服务
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 16:27
 */
public interface IdGeneratorService {

    /**
     * 获取下一个主键,默认使用雪花算法
     *
     * @param key 雪花算法时无效，可传任何值；服务端使用分段取号可用
     * @return Id
     */
    Long nextId(String key);
}
