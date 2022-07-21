package com.lian.base.manager.distributed;

import cn.hutool.core.util.ObjectUtil;
import com.lian.base.common.exception.BaseException;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.service.SnowflakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分布式ID生成器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 21:31
 */

@Service
@RequiredArgsConstructor
public class IdGeneratorServiceImpl implements IdGeneratorService {

    private final SnowflakeService snowflakeService;

    @Override
    public Long nextId(String key) {
        Result result = snowflakeService.getId(key);
        if (ObjectUtil.equal(Status.EXCEPTION, result.getStatus())) {
            throw BaseException.DISPERSED_ID_ERROR.runtimeException();
        }
        return result.getId();
    }
}
