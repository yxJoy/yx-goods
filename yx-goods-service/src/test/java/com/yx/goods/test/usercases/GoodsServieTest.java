package com.yx.goods.test.usercases;

import com.yx.common.ApiRequest;
import com.yx.common.ApiResponse;
import com.yx.goods.facade.model.Goods;
import com.yx.goods.facade.service.GoodsReadService;
import com.yx.goods.facade.service.GoodsWriteService;
import com.yx.goods.test.base.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GoodsServieTest extends AbstractTestBase {

    @Autowired
    private GoodsWriteService goodsWriteService;

    @Autowired
    private GoodsReadService goodsReadService;

    @Test
    public void insertTest() {
        Goods goods = new Goods();
        goods.setColor("red");
        goods.setName("测试商品名称");
        goods.setNumber("NO00001");
        goods.setRemark("remark");
        goods.setUserCreate("create");
        goods.setUserModified("create");
        ApiResponse response = goodsWriteService.insert(new ApiRequest<Goods>(goods));
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void getGoodsByIdTest() {
        ApiResponse<Goods> response = goodsReadService.getGoodsById(1L);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testRedis() {
        String result = goodsReadService.testRedis("yx-goods");
        Assert.assertEquals("yx-goods, hello", result);
    }

    @Override
    protected void childSetUp() {

    }
}
