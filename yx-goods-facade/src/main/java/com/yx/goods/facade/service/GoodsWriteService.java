package com.yx.goods.facade.service;

import com.yx.common.ApiRequest;
import com.yx.common.ApiResponse;
import com.yx.goods.facade.model.Goods;

public interface GoodsWriteService {

    ApiResponse insert(ApiRequest<Goods> request);
}
