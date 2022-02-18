package com.kaori.modules.sys.vo;

import com.kaori.common.utils.vo.BaseVo;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class LoginLogVo extends BaseVo {

    @ApiParam("用户昵称")
    private String key;

}
