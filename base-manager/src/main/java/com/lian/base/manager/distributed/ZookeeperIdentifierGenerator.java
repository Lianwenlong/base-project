package com.lian.base.manager.distributed;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import javax.annotation.Resource;

/**
 * <p>
 * 分布式ID生成器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 16:31
 */
public class ZookeeperIdentifierGenerator implements IdentifierGenerator {

    @Resource
    IdGeneratorService idGeneratorService;

    @Override
    public Number nextId(Object entity) {
        return idGeneratorService.nextId(entity.toString());
    }
}
