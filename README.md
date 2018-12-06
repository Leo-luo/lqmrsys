# lqmrsys
公司业务系统接口开发；
jdk1.7版本，部署于tomcat；
SpringMvc框架+SwaggerUi接口文档+AlibabaDruid连接池配置及数据库链接监控；
SpringMvc框架，项目分层明确清晰，代码整体分为common(通用类)、项目代码层、utils（工具类），其中项目代码层分为bean层(数据模型)、controller层（接口）、service层（业务处理）、dao层（数据库交互）；
SwaggerUi注入，在线接口文档，通过注解实现接口文档可视化，可模拟接口调用，参数注释明确且可查看接收参数格式和返回数据格式；
AlibabaDruid连接池可进行多个数据库配置，实现一个项目多个数据库链接进行数据交互，可监控dao层数据库sql调用，反馈时间效率等信息；
拦截器使用，适用于业务中权限功能的完成；

