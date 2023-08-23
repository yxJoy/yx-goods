# yx-goods
高性能商品存储系统，期望支撑百亿级

基础架构
- 通信基于[sofa-boot](https://tech.antfin.com/docs/2/73882)、sofa-rpc
- 设计二级缓存，内存缓存+分布式缓存（guava + redis）
- 接收数据库binlog同步缓存，以及es数据，记录审计日志信息
- 缓存预热功能，统计热点数据，提前加载到缓存
- ES查询
- 分库分表
- MQ使用kafka，异步处理数据

开发
- 部分代码使用代码生成器（mybatis.generator）
- 使用分页插件

TODO List

 - [ ] 封装es
 - [ ] 封装canal接收binlog
 - [ ] 分库分表 
