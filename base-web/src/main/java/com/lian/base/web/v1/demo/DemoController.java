package com.lian.base.web.v1.demo;

import cn.hutool.json.JSONUtil;
import com.lian.base.common.service.BaseController;
import com.lian.base.web.v1.demo.vo.Dispatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生管理控制器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 19:00
 */
@RestController
@RequestMapping("/v1/demo")
@Slf4j
@Api(tags = "Demo-对接测试")
public class DemoController implements BaseController {


    @ApiOperation("接收测试")
    @PostMapping("/test")
    void insert(@RequestBody Dispatch<?> dto) {
        log.info("接收数据：{}",JSONUtil.toJsonStr(dto));
    }

}
