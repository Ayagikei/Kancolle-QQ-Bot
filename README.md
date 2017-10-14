# Smart QQ Java Kancolle Prinz Eugen Version

这是基于[ScienJus/smartqq](https://github.com/ScienJus/smartqq)制作的一个自用机器人。

### 现有功能

- 自动聊天回复（收到群信息包含“欧根”或“老婆”的时候）
- 整点报时功能，台词取自Kancolle Prinz Eugen的报时语音。
- 简单的查询Kancolle日常特殊日期任务（收到群信息包含“欧根”、“查询"以及“任务”的时候）
- 能自动从[kcwiki官推转发](https://t.kcwiki.moe/)转发新的官推，也可以手动查询最新的官推（收到群信息包含“欧根”和”官推"的时候）
- 内置的一个Roll程序，使用方法是发送群信息包含"Roll"、"欧根"以及一个或多个数字。
- 等等……


### 使用方法

配置好MAVEN环境后，Clone该项目，以Application.java为运行入口运行即可。

#### 需要的改进

- 可能会出现一些不可预料的异常现象
- 使用了一些不建议使用的获取日期的方法
- 报时的功能实现过于暴力
- 文件结构不佳