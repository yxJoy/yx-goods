package com.yx.goods.service;

import com.yx.common.ApiResponse;
import com.yx.goods.cache.annotation.GuavaAutoCache;
import com.yx.goods.dao.GoodsDoMapper;
import com.yx.goods.entity.GoodsDo;
import com.yx.goods.facade.service.GoodsReadService;
import com.yx.goods.facade.model.Goods;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("goodsReadService")
@EnableCaching
public class GoodsReadServiceImpl implements GoodsReadService {

    @Resource
    private GoodsDoMapper goodsDoMapper;

    @Override
    @GuavaAutoCache
    public ApiResponse<Goods> getGoodsById(long goodsId) {
        return ApiResponse.ofSuccess(convert(goodsDoMapper.selectByPrimaryKey(goodsId)));
    }

    private Goods convert(GoodsDo goodsDo) {
        if (null == goodsDo) {
            return null;
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsDo, goods);
        return goods;
    }
}
