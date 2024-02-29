# Project：E-Go电子商务系统

### 1、项目介绍

前端：用户可以通过网站查看商品信息并选购自己喜欢的商品，将其添加到购物车。同时，用户可以对自己的个人基本信息以及收货地址进行修改。

后端：商家、管理员可以对商品进行基本信息维护，为用户提供不同的商品。管理员可以对用户信息进行管理。同时，商家可以查看未完成的订单以及订单销售统计，同时也可以对订单的信息进行修改。软件的各个模块操作界面简单、实用。

**运用技术：本项目重点使用MVC设计模式，采用Jsp+Servlet+MySQL+Tomcat技术实现。**

**完成时间：2021.09**

#### **前台**

**用户模块：**

![image-20240229114321100](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114321100.png)

**购物车模块：**

![image-20240229114355703](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114355703.png)

**订单模块：**

![image-20240229114415859](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114415859.png)

**额外功能：**

![image-20240229114442202](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114442202.png)

#### 后台：

**商品模块：**

![image-20240229114514567](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114514567.png)

**订单模块：**

![image-20240229114605713](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114605713.png)

**账户模块：**

![image-20240229114623417](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229114623417.png)

### 2、项目框架

（1）基础Dao：UserDao，AdminDao，AddressDao……

（2）服务Service：UserService，AdminService，OrderService……

（3）业务Servlet：AdminServlet，OrderServlet，AddressServlet……

（4）数据库：user_info，address_info，shoppingcar_info……

（5）jsp页面：login.jsp，userlist.jsp，orderlist.jsp……

### 3、界面展示

**（1）开始界面**

![image-20240229115037149](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115037149.png)

**（2）登录界面**

![image-20240229115102146](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115102146.png)

**（3）管理员界面**

![image-20240229115118988](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115118988.png)

**（4）管理员账号管理**

![image-20240229115136927](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115136927.png)

**（5）管理员账号新增**

![image-20240229115154247](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115154247.png)

**（6）用户管理界面**

![image-20240229115209717](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115209717.png)

**（7）订单处理界面**

![image-20240229115223398](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115223398.png)

**（8）商品管理界面**

![image-20240229115244043](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115244043.png)

**（9）用户首页界面**

![image-20240229115258453](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115258453.png)

**（10）商品界面**

![image-20240229115318401](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115318401.png)

**（11）商品详情界面**

![image-20240229115335594](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115335594.png)

**（12）用户个人信息管理界面**

![image-20240229115351069](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115351069.png)

**（13）用户收货地址管理界面**

![image-20240229115411942](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115411942.png)

**（14）购物车界面**

![image-20240229115426342](C:\Users\26797\AppData\Roaming\Typora\typora-user-images\image-20240229115426342.png)