package com.ciaociao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Link;

/**
* @author CIA0CIA0
* @description 针对表【link(友链)】的数据库操作Service
* @createDate 2023-09-03 22:52:33
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
