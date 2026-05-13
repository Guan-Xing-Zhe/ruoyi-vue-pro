# ruoyi-vue-pro 架构设计分析

## 整体架构

`
[前端] Vue 3 + Element Plus + Vite
    ↕ API (Axios)
[后端] Spring Boot + Sa-Token
    ↕
[服务层] Flowable(工作流) / XXL-Job(调度) / Redis(缓存)
    ↕
[数据层] MySQL(主库) / Redis(缓存) / ES(搜索)
`

## 核心模块

### ruoyi-admin - 管理后台
- **系统管理**: 用户管理、角色管理、菜单管理、部门管理、岗位管理、字典管理、参数设置、通知公告、日志管理
- **系统监控**: 在线用户、定时任务、数据监控、服务监控、缓存监控
- **系统工具**: 表单构建、代码生成、系统接口

### ruoyi-framework - 核心框架
- **安全机制**: Sa-Token 鉴权 + JWT
- **配置管理**: 多环境配置、跨域配置、拦截器配置
- **数据权限**: 通过 MyBatis 拦截器实现数据级权限控制

### ruoyi-flowable - 工作流
- **流程设计**: 可视化流程建模
- **流程实例**: 发起、审批、驳回、转办
- **表单集成**: 动态表单绑定

## 亮点技术实现

### 多租户方案
1. 租户上下文通过 RequestInterceptor 注入
2. MyBatis Plus 租户插件自动拼接 SQL 条件
3. 支持独立数据库和共享数据库两种模式

### 代码生成器
- 连表查询生成
- 树表结构生成
- 主子表结构生成
- 前端 Vue 组件 + API 自动生成

### 数据权限实现
- 通过注解 @DataPermission 声明
- 基于 MyBatis Interceptor 动态拼接 SQL
- 支持全部/本部门/本部门及以下/仅本人 四种粒度
