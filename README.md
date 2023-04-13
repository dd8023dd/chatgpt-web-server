# chatgpt-web-server
## 介绍

chatgpt套壳网站。

https://github.com/dd8023dd/chatgpt-web-server

- 支持自定义key访问，然后分发自己生成的key。每个key可以设置次数（目前直接在数据库修改）。
- 后台设置apikey，可以配置多个apikey自动轮询。对于自定义key来说，调用失败不扣减次数。
- 使用次数到限制次数后即可限制访问，聊天的消耗次数可调。
- 支持图片能力。
- 流式输出。
- 聊天记录保存，导出。
- 可以部署在腾讯云海外服务器上便于国内免梯子访问。

## 部署

### 后端

后端采用Java开发，Java版本是JDK1.8。

1. 本项目使用Maven方式构建。在根目录下使用maven构建即可（需先安装maven）。也可以根据自己需要改成gradle。

2. 安装MySQL数据库，执行项目根目录下init.sql。表结构可以看此sql文件说明。

3. 本地直接运行项目。如果需要在服务器运行，项目构建完成后找到Jar包，target\dd-chat-0.0.1-SNAPSHOT.jar上传到服务器。使用java -jar dd-chat-0.0.1-SNAPSHOT.jar 即可。

4. 项目中的redis相关因为目前还没实际使用，可以先注释掉。如果需要使用redis可以自行改造。如未删除相关模块，服务器运行时会检测redis，需要使用正确的配置。

5. 代码的一些简单说明：

   1. resources文件夹下的sensitivewords.dic文件就是敏感词文件。采用离线的敏感词过滤方式，效率会非常高。-- 使用了hutool的敏感词过滤工具。SensitiveWordsUtils就是敏感词过滤的工具类。
   2. SensitiveWordsConfig类原本设计是想作为敏感词的缓存加载的。但其实项目启动就加载在内存里了，现在这里配置的是敏感词触发的次数记录，初始化了一个MAP，可以用以记录userkey触发的敏感词次数。如果过于频繁可以做封号处理。
   3. 由于上下文对话会加倍消耗apikey的余额（token），所以项目中设置的也是加倍消耗userkey的有效次数的，在代码里也可以直接调整。

6. 项目启动前，需要在tb_apikey表中初始化数据。其中你的apikey是你从openai网站中获取的apikey，也可以通过一些渠道去购买。本表配置后，会根据使用次数自动轮询，会均衡使用次数。但不会自动识别哪些key是失效了。这个可能会在后面更新，看我有没有空了 = =

   ```sql
   --SQL示例
   INSERT INTO `db_chat`.`tb_apikey`(`create_date`, `create_time`, `update_date`, `update_time`, `update_times`, `api_key`, `balance`, `expire_date`, `use_times`, `valid_status`) VALUES (20230411, 0, 20230411, 0, 0, '你的apikey', '5', 20230601, 37, '1');
   
   ```

## 服务器安装教程

### MySQL

```shell
#下载MySQL的yum源
wget http://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
#安装yum源
yum -y install mysql57-community-release-el7-11.noarch.rpm
#yum方式安装MySQL  --nogpgcheck 不校验数据签名
yum -y install mysql-server --nogpgcheck
#启停MySQL
systemctl start mysqld.service
systemctl status mysql.service
#获取密码
cat /var/log/mysqld.log| grep password
##############
[root@localhost soft]# cat /var/log/mysqld.log| grep password
2023-02-08T05:59:45.213332Z 1 [Note] A temporary password is generated for root@localhost: =ggp_YD!q5)q
##############
# 其中“ =ggp_YD!q5)q”就是密码
#本地登录MySQL
mysql -u root -p'=ggp_YD!q5)q'
#修改root密码
mysql>ALTER USER USER() IDENTIFIED BY 'yourpassword';
#授权访问
mysql>grant all privileges on *.* to 'root'@'%' identified by 'yourpassword' with grant option;
#刷新权限
mysql>flush privileges;
```

### niginx（服务器运行需要）

1. 安装Nginx（供参考）

   ```shell
   #添加CentOS 7 Nginx yum资源库
   rpm -Uvh  http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
   #安装nginx
   yum -y install nginx
   #启动nginx
   systemctl start nginx
   ```

2. Nginx配置如下（供参考）

   ```nginx
   server {
       listen 443 ssl;
       # 配置自己的域名
       server_name chatp.zzhomee.cn;
   	# 如果不需要配置证书，就不需要如下配置。
       # 证书直接云厂商申请即可。
       ssl_certificate /etc/nginx/cert/xxx.pem;
       ssl_certificate_key /etc/nginx/cert/xxx.key;
       ssl_session_timeout 5m;
       ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
       ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
       ssl_prefer_server_ciphers on;
       # web为前端项目地址
       root /root/xxx/web;
       # proxy_http_version 1.1;
       # proxy_set_header Connection "";
       proxy_buffering off;
       location /chatbot/api/ {
       proxy_pass http://localhost:8081/chatbot/api/;
           proxy_set_header Host $http_host;
       }
       location / {
           try_files $uri $uri/ /index.html;
           proxy_set_header Host $http_host;
       }
   }
   
   #以http形式请求的，转发的到Https方式。
   server {
       listen 80;
       #填写绑定证书的域名
       server_name chatp.zzhomee.cn;
       #把http的域名请求转成https
       return 301 https://$server_name$request_uri;
       root html;	
   }
   
   ```

### redis（单机版）

```shell
#下载
wget http://download.redis.io/releases/redis-6.2.5.tar.gz
#解压
tar -xf redis-6.2.5.tar.gz
#编译
make -C redis-6.2.5
#如果编译出现问题，一般来说是gcc没装，需要解决后执行
make -C redis-6.2.5 distclean
#安装并配置环境变量
make -C redis-6.2.5 install PREFIX=/opt/soft/redis6
#编辑环境变量
vi /etc/profile
#----------------------redis------------------------
export REDIS_HOME=/opt/soft/redis6
export PATH=$PATH:$REDIS_HOME/bin
#---------------------------------------------------

#拷贝一个redis.conf 到/opt/soft/redis6下。
#启动redis服务

```

### 前端

​	前端项目参考：https://github.com/Chanzhaoyu/chatgpt-web

1. 项目根目录下web即为前端项目
2. 需要安装node
3. 在web的根目录下执行npm install 安装依赖
4. 执行 npm run dev 开发模式，可以和本项目进行本地联调
5. 执行npm run build 进行打包，然后在项目根目录下的dist文件夹中找到打包的文件。在服务器部署的时候，将dist文件夹下的文件复制到nginx配置的前端目录下。根据配置的域名访问即可（域名别忘了做DNS映射）。



项目参考：

https://gitee.com/aceysx/chatgpt-proxy/tree/main/



## 加群&赞赏

各位可爱的大佬们点个Star呗 =v=

如果疑问，进群讨论：

<img src="https://user-images.githubusercontent.com/34155196/231454597-1c7c85e9-75fd-4a9a-ab31-1b7f563794f9.jpg" width="350">



请作者喝一杯可乐
（微信赞赏码）

<img src="https://user-images.githubusercontent.com/34155196/231450085-4b383983-bfc3-4606-be71-ab082fa4a02d.jpg" width="350">

