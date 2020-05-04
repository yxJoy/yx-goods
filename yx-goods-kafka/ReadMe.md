#简单的封装KAFKA
 - 目标：
    - 消息的发送者和消费者依赖此包，通过简单的配置即可。
    
    - 发送方定义Producer，即可发送Message
    - Message包含topic、key、value等信息
    - 消费方通过配置，只需要实现Lister即，只关注业务代码