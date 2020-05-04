package com.yx.goods.dao;

import com.yx.goods.entity.GoodsDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsDo record);

    int insertSelective(GoodsDo record);

    GoodsDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsDo record);

    int updateByPrimaryKey(GoodsDo record);
}