package com.yx.goods.facade.service;

import com.yx.common.ApiResponse;
import com.yx.goods.facade.model.Goods;

public interface GoodsReadService {

    ApiResponse<Goods> getGoodsById(long goodsId);

    String testRedis(String key);
}
