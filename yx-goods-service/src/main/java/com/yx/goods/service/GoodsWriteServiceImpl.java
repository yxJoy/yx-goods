package com.yx.goods.service;

import com.yx.common.ApiRequest;
import com.yx.common.ApiResponse;
import com.yx.goods.dao.GoodsDoMapper;
import com.yx.goods.entity.GoodsDo;
import com.yx.goods.facade.service.GoodsWriteService;
import com.yx.goods.facade.model.Goods;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Log4j2
@Component("goodsWriteService")
public class GoodsWriteServiceImpl implements GoodsWriteService {

    @Resource
    private GoodsDoMapper goodsDoMapper;

    @Override
    public ApiResponse insert(ApiRequest<Goods> request) {
        try {
            Goods goods = request.getData();
            if (null == goods) {
                return ApiResponse.ofFail("param is null");
            }
            goodsDoMapper.insert(convert(goods));
            return ApiResponse.ofSuccess();
        } catch (Exception e) {
            log.info("insert goods error", e);
            return ApiResponse.ofFail(e.getMessage());
        }
    }

    private GoodsDo convert(Goods goods) {
        if (null == goods) {
            return null;
        }
        GoodsDo goodsDo = new GoodsDo();
        BeanUtils.copyProperties(goods, goodsDo);
        return goodsDo;
    }
}
