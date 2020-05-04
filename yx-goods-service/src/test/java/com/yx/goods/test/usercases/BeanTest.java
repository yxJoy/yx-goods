package com.yx.goods.test.usercases;

import com.yx.goods.facade.SampleService;
import com.yx.goods.test.base.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanTest extends AbstractTestBase {

    @Autowired
    private SampleService sampleService;

    @Test
    public void testRpcSimple(){
        String result = sampleService.message();
        Assert.assertEquals("Hello, Service slitecore", result);
        Assert.assertTrue(result != null && result.length() > 0);
    }

    @Override
    protected void childSetUp() {

    }
}
