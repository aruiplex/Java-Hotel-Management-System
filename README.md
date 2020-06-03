# Java-Hotel-Management-System

[toc]

English:
[README-EN](./README-EN.md)

## System Design ideas

**整体架构**: 尝试着使用了 MVC 的思想, Model 里面存放数据结构, View 里面存放显示系统, Controller 里面存放控制, 与数据库交流的所有信息.

**登录**: 把用户的 id 当作登录使用的 token.

**订单的有效性**: 在每个订单中, status = 1 代表这个订单是有效的, status = 0 代表这个订单是无效的(可能是过期的或者是被取消的), 取消订单就是意味着 set status = 0;

**ID 分配**: 客户和员工的 id 的分配为: id < 10000 为员工 id, id > 10000 为客户 id.

**订单管理进程:** 有一个守护进程 `Book.bookd()` 是用来作为监视房间状态的, 检查房间是否入住或者过期, 食物订单是否有效.

## User guide

用户进入系统之后, 选择身份是员工还是客户, 可以进行选择是登录还是注册.

员工的功能有:

1. 根据房间类型查找房间是状态(是有入住, 或者是空房).
2. 根据房间 ID 查看房间状态,
3. 查看所有现在已经入住的房间,
4. 查看所有现在为空的房间,
5. 查看所有预定的食物,
6. 查看所有被预定的房间,
7. 查看所有现有的食物.

客户的功能有:

1. 根据房间类型预定房间,
2. 根据房间 ID 预定房间,
3. 预定从今天开始 3 天之内的食物,
4. 修改隐私信息,
5. 取消未入住的房间,
6. 取消未到来的食物,
7. 查看自己的房间订单信息,
8. 查看自己的食物订单信息,

根据系统提示选择对应的序号进行相应的功能, 需要注意的是, 点菜的时候需要输入菜名而不是序号.

## Database design

### Specific in tables

总共有 6 张表, 分别是:

1. stuff: 员工信息表

   1. id:
   2. name
   3. password
   4. telenumber
   5. UPDATED_TIME: 员工注册的时间点

2. guest: 客户信息表

   1. id
   2. name
   3. real name
   4. password
   5. telenumber
   6. passportId
   7. UPDATED_TIME: 客户注册的时间点

3. room: 房间信息表

   1. id: 房间 id, 后两位表示房间类型, 前两位表示房间楼层
   2. type: 房间类型
   3. status: 这个房间现在的状态.

4. food: 食物信息表

   1. id: 食物 id
   2. name: 食物名字
   3. food_time: 是一个集合, 从 1 到 7, 代表着星期几
   4. chef: 厨师名字

5. book_room: 房间订单

   1. id: 订单 id
   2. room_id: 房间 id
   3. guest_id: 客户 id
   4. start_time: 房间的开始时间
   5. duration: 房间的进行时间
   6. status: 表示这个订单的状态, 0 表示已经失效, 1 表示正在进行.
   7. order_time: 当下的时间点.

6. book_food: 食物订单

   1. id: 订单 id
   2. food_name: 食物名称
   3. guest_id: 客户 id
   4. food_time: 食物的供应时间
   5. status: 表示这个订单的状态, 0 表示已经失效, 1 表示正在进行.
   6. order_time: 当下的时间点.

### Relationship in tables

Guest books room / food will generate raw information in book_room / book_food. Stuff can check book_room / book_food to know every room / food information.

## ER-table

![](./DataBase-er-table.png)

## Default Account

初始的员工账户:
`username`: "init_stuff",
`password`: "init_stuff".

初始的客户账号:
`username`: "init_guest",
`password`: "init_guest".

## System Operation Specific

在`Controller.ENV`中填写本地数据库的 userName, passWord, DataBaseURL, 该系统会自动进行数据库初始化.



