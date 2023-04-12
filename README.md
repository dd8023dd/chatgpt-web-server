# chatgpt-web-server
## 介绍

chatgpt套壳网站。

- 支持自定义key访问，然后分发自己生成的key。每个key可以设置次数（目前直接在数据库修改）。
- 后台设置apikey，可以配置多个apikey自动轮询。对于自定义key来说，调用失败不扣减次数。
- 使用次数到限制次数后即可限制访问，聊天的消耗次数可调。
- 支持图片能力。
- 流式输出。
- 聊天记录保存，导出。
- 可以部署在腾讯云海外服务器上便于国内免梯子访问。

## 部署

1. 本项目使用Maven方式构建。在根目录下使用maven构建即可（需先安装maven）。

2. 安装MySQL数据库，执行跟目录下init.sql。表结构可以看此sql文件说明。

3. 本地直接运行项目。如果需要在服务器运行，项目构建完成后找到Jar包，target\dd-chat-0.0.1-SNAPSHOT.jar上传到服务器。使用java -jar dd-chat-0.0.1-SNAPSHOT.jar 即可。（需要安装JDK。本项目使用1.8版本）

4. 安装Nginx，Nginx配置如下（供参考）

   ```nginx
   server {
       listen 443 ssl;
       # 配置自己的域名
       server_name chatp.zzhomee.cn;
   	# 如果不需要配置证书，就不需要如下配置
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
   
   server {
       listen 80;
       #填写绑定证书的域名
       server_name chatp.zzhomee.cn;
       #把http的域名请求转成https
       return 301 https://$server_name$request_uri;
       root html;	
   }
   
   ```

   

5. 前端项目:

   1. 项目根目录下web即为前端项目
   2. 需要安装node（自己找教程）
   3. 在web的根目录下执行npm install 安装依赖
   4. 执行 npm run dev 开发模式，可以和本项目进行本地联调
   5. 执行npm run build 进行打包，然后在项目根目录下的dist文件夹中找到打包的文件。在服务器部署的时候，将dist文件夹下的文件复制到nginx配置的前端目录下。根据配置的域名访问即可（域名别忘了做DNS映射）。



项目参考：

https://gitee.com/aceysx/chatgpt-proxy/tree/main/



各位可爱的大佬们点个Star呗 =v=

如果疑问，进群讨论：

<img src="https://user-images.githubusercontent.com/34155196/231454597-1c7c85e9-75fd-4a9a-ab31-1b7f563794f9.jpg" width="350">



请作者喝一杯可乐
（微信赞赏码）

<img src="https://user-images.githubusercontent.com/34155196/231450085-4b383983-bfc3-4606-be71-ab082fa4a02d.jpg" width="350">

