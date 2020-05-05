package com.yx.goods.service;

import com.yx.common.ApiResponse;
import com.yx.goods.cache.annotation.GuavaAutoCache;
import com.yx.goods.dao.GoodsDoMapper;
import com.yx.goods.entity.GoodsDo;
import com.yx.goods.facade.service.GoodsReadService;
import com.yx.goods.facade.model.Goods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Component("goodsReadService")
@EnableCaching
public class GoodsReadServiceImpl implements GoodsReadService {

    @Resource
    private GoodsDoMapper goodsDoMapper;

    @Resource(name = "redisClient")
    private Jedis redisClient;

    @Override
    @GuavaAutoCache
    public ApiResponse<Goods> getGoodsById(long goodsId) {
        return ApiResponse.ofSuccess(convert(goodsDoMapper.selectByPrimaryKey(goodsId)));
    }

    @Override
    public String testRedis(String key) {
        redisClient.set(key, key + ", hello");
        String result = redisClient.get(key);
        redisClient.del(key);
        return result;
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
