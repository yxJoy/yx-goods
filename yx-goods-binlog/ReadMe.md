# binlog

- 使用阿里开源工具cannal 接收binlog


## 开启mysql binlog

- 检查是或否开启
    ```sql
    show variables like 'log_bin';
    ```
    结果为OFF则没有开启
- 开启binlog

    - 找到my.cnf 中 [mysqld]  添加如下
        ```text
        [mysqld]
        # binlog 配置
        log-bin = /usr/local/var/mysql/logs/mysql-bin.log
        expire-logs-days = 14
        max-binlog-size = 500M
        server-id = 1
        ```
    - 如果没有my.cnf  或者是集成的环境 找到my.ini 中 [mysqld]  添加如下
        ```text
        [mysqld]
        # binlog 配置
        log-bin = /usr/local/var/mysql/logs/mysql-bin.log
        expire-logs-days = 14
        max-binlog-size = 500M
        server-id = 1
        ``` 
- 重启mysql后    show variables like 'log_bin';  Value 为 ON即可
 
- 查询binlog 变动信息   show binlog events;