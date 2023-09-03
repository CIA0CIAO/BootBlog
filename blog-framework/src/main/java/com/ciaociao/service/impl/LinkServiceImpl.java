package com.ciaociao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciaociao.mapper.LinkMapper;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Link;
import com.ciaociao.service.LinkService;
import org.springframework.stereotype.Service;

/**
* @author CIA0CIA0
* @description 针对表【link(友链)】的数据库操作Service实现
* @createDate 2023-09-03 22:52:33
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        return null;
    }
}




