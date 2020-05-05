# yx-goods
高性能商品存储系统

基础架构
- 通信基于[sofa-boot](https://tech.antfin.com/docs/2/73882)、sofa-rpc
- 设计二级缓存，内存缓存+分布式缓存
- ES查询
- 分库分表
- MQ使用kafka，异步处理数据

开发
- 部分代码使用代码生成器（mybatis.generator）
- 使用分页插件

TODO List

 - [x] 封装es
 - [ ] 封装canal接收binlog