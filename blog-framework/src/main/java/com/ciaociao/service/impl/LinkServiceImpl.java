package com.ciaociao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciaociao.constant.SystemConstants;
import com.ciaociao.mapper.LinkMapper;
import com.ciaociao.model.ResponseResult;
import com.ciaociao.model.domain.Link;
import com.ciaociao.model.vo.LinkVO;
import com.ciaociao.service.LinkService;
import com.ciaociao.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author CIA0CIA0
* @description 针对表【link(友链)】的数据库操作Service实现
* @createDate 2023-09-03 22:52:33
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(queryWrapper);
        //转换成VO对象
        List<LinkVO> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVO.class);
        return ResponseResult.okResult(linkVoList);
    }
}




