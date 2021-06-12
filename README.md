#### 项目介绍
  本项目是基于JSP+JavaBean+Servlet+MySQL开发的小型商城，分为前台和后台管理员界面，前后台都可以登录和注册。
#### 项目基本设置
  1. 将项目下载到本地之后，找到WebContent下image文件夹的绝对路径
    ![image](https://user-images.githubusercontent.com/58590342/118268146-98e10800-b4ef-11eb-8080-d445c11baae0.png)
  2. 在项目的web.xml文件中设置image路径
    ![image](https://user-images.githubusercontent.com/58590342/118268882-8ddaa780-b4f0-11eb-9097-bbe94f91bf6d.png)
  3. 将项目部署到tomcat之后，修改server.xml文件，设置项目访问路径
    ![image](https://user-images.githubusercontent.com/58590342/118270412-9df38680-b4f2-11eb-95fa-68857c7d2eaa.png)
  4. 新建数据库shop，运行shop.sql代码
  5. 数据库连接（com.shop.util.ConnectDB）
    ![image](https://user-images.githubusercontent.com/58590342/118272414-2b37da80-b4f5-11eb-8731-70509b625727.png)
  6. 因为上传的商品的图片都是以url形式访问的，并且是访问的8080端口，所以项目要以8080端口启动（如果要改端口，则要将jsp页面代码中的商品图片的url改变）
#### 前台地址
  http://47.98.199.212:8080/shop/
#### 后台地址
  http://47.98.199.212:8080/shop/admin
#### 项目结构
  ![image](https://user-images.githubusercontent.com/58590342/118215549-44646b00-b4a4-11eb-8caf-50588a2dffba.png)
  ![image](https://user-images.githubusercontent.com/58590342/118214194-e33b9800-b4a1-11eb-9707-85c82b56e329.png)
#### 物理数据模型(PDM)
  ![image](https://user-images.githubusercontent.com/58590342/118206322-d3698700-b494-11eb-9338-8e72d6d52632.png)
#### 项目环境
  1. 服务器：apache-tomcat-9.0.39 
  2. jdbc驱动：mysql-connector-java-8.0.17 
  3. 数据库：mysql-8.0.17
  4. java版本: jdk14.0.1
#### 其他
  这个项目是我的第一个项目，前后端模板都是这个视频中的https://www.bilibili.com/video/BV1zE411Y7Mg?share_source=copy_web ，思路是跟着这个老师走的，但数据库和代码结构是自己设计的。
