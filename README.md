# ruoyi-vue-pro — 企业级后台管理系统深度实践

> 本仓库 Fork 自 [YunaiV/ruoyi-vue-pro](https://github.com/YunaiV/ruoyi-vue-pro)（36k+ Stars），
> 专注于企业级后台管理系统的架构研究与二次开发。基于 Spring Boot + MyBatis Plus + Vue 3 实现。

---

## 📋 项目概述

ruoyi-vue-pro 是 **RuoYi-Vue 的 Pro 版本**，在原有基础上进行了全面优化与功能增强。内置 RBAC 动态权限、数据权限、SaaS 多租户、Flowable 工作流、三方登录、支付、短信、商城、CRM、ERP、AI 大模型、IoT 物联网等模块。

### 核心特性

| 特性 | 说明 |
|------|------|
| RBAC 权限 | 按钮级细粒度权限控制 |
| 多租户 SaaS | 支持多租户数据隔离 |
| 工作流 | Flowable 流程引擎 |
| 代码生成 | 前后端代码一键生成 |
| 数据权限 | 部门/用户级数据隔离 |
| 国际化 | 多语言支持 |

### 技术栈

| 后端 | 前端 | 中间件 |
|------|------|--------|
| Spring Boot | Vue 3 | MySQL |
| Spring Security | Element Plus | Redis |
| MyBatis Plus | Vite | RabbitMQ |
| Flowable | Pinia | Elasticsearch |
| Sa-Token | Axios | XXL-Job |

---

## 🔍 架构分析

### 项目结构

`
ruoyi-vue-pro
├── ruoyi-admin        # 后台管理模块
├── ruoyi-framework    # 核心框架配置
├── ruoyi-system       # 系统模块（用户/角色/菜单等）
├── ruoyi-generator    # 代码生成器
├── ruoyi-common       # 公共工具模块
└── ruoyi-flowable     # 工作流模块
`

### 权限体系

- **用户 → 角色 → 菜单**：经典 RBAC 模型
- **数据权限**：通过 MyBatis 拦截器实现 SQL 级数据过滤
- **Sa-Token**：轻量级 Java 鉴权框架，支持 Redis 共享

### 亮点功能

- [x] 多租户隔离（独立数据库/共享数据库两种模式）
- [x] 工作流引擎（Flowable 可视化流程设计）
- [x] 代码生成器（连表 + 树表 + 主子表）
- [x] 支付集成（微信 + 支付宝）
- [x] IoT 物联网模块（设备接入与管理）

---

## 🚀 快速启动

`ash
# 1. 克隆项目
git clone https://github.com/Guan-Xing-Zhe/ruoyi-vue-pro.git

# 2. 数据库初始化
mysql -uroot -p < sql/ruoyi-vue-pro.sql

# 3. 启动后端
cd ruoyi-admin && mvn spring-boot:run

# 4. 启动前端
cd ruoyi-ui && npm install && npm run dev

# 访问后台: http://localhost:1024
`

---

## 📚 学习笔记

- [权限系统设计解析](docs/auth-design.md)
- [多租户实现方案](docs/tenant.md)
- [工作流引擎集成](docs/flowable.md)
- [代码生成器定制](docs/codegen.md)

---

## 🔗 原项目

- 原作者: [YunaiV](https://github.com/YunaiV)（芋道源码）
- 原仓库: [YunaiV/ruoyi-vue-pro](https://github.com/YunaiV/ruoyi-vue-pro)
- 在线演示: [http://dashboard.yudao.iocoder.cn](http://dashboard.yudao.iocoder.cn)

---

> ⭐ 如果这个项目对你有帮助，欢迎 Star 支持！
> 本仓库仅用于学习研究，请尊重原项目 License。