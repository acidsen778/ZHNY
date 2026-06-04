<p align="center">
	<img alt="logo" src="https://oscimg.oschina.net/oscnet/up-d3d0a9303e11d522a06cd263f3079027715.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">ZHNY - 环湖智慧农业系统</h1>
<h4 align="center">基于 RuoYi v3.9.2 (SpringBoot+Vue前后端分离) 的智慧农业管理平台</h4>

## 🌾 项目简介

**ZHNY (环湖智慧农业系统)** 是针对校园/区域环湖农业生态专门设计的智能化管理平台。依托于若依微服务/前后端分离架构，旨在通过信息化手段实现农业环境监控、作物物模型管理以及数据可视化。

* **当前阶段**：已完成**核心业务的前后端交互逻辑**，支持物模型模板配置、基础数据字典、权限管理等。
* **后续规划**：即将接入 **`ruoyi-iot` 物联网模块**，通过 MQTT 协议对接环湖周边的水位、土壤墒情、空气温湿度等传感器节点。

---

## 🚀 技术栈说明

* **前端采用**：Vue 2、Element UI、Vue Router、Vuex。
* **后端采用**：Spring Boot 2.x、Spring Security、Redis & Jwt、MyBatis。
* **物联网层（规划中）**：EMQX (MQTT Broker)、Netty、物模型体系。

---

## 🛠️ 项目架构与模块说明

```text
ZHNY
├── ruoyi-admin          # 后端核心控制层（服务启动入口）
├── ruoyi-iot            # 物联网核心模块（💡 传感器协议解析与数据接入 - 逻辑构建中）
├── ruoyi-system         # 系统管理模块（核心基础业务）
├── ruoyi-framework      # 核心过滤、安全配置防范等
├── ruoyi-common         # 通用工具类
└── ruoyi-ui             # 前端 Vue 项目目录
📌 当前内置功能
1. 🌾 智慧农业核心逻辑（本次更新）
物模型模板配置：支持农业传感器（如土壤湿度计、水位计）的属性、事件、服务的可视化模板一键生成（使用若依 Gen 代码生成器）。

前后端数据闭环：完成从前端管理页面到后端 ruoyi-iot / ruoyi-system 的完整 CRUD 交互。

2. ⚙️ 权限与基础设施
用户/部门/角色管理：配置系统操作者，支持多级农业合作社/管理部门的树状结构控制。

菜单管理：动态加载农业信息化大屏、传感器管理等菜单权限。

代码生成（高效率）：支持一键生成新传感器业务的前后端代码，极大缩短开发周期。

系统监控：提供服务器 CPU、内存、数据库连接池及缓存状态的实时监视，保障环湖数据采集的稳定性。

📡 物联网（IoT）模块接入路线图 (Roadmap)
当前版本已为物联网数据留出核心接口，下一阶段将围绕 ruoyi-iot 模块展开以下工作：

💡 接入状态：⏳ 联调准备中

MQTT 消息服务器对接：配置本地/云端 EMQX 消息中间件。

软硬件协议打通：基于 MQTT 订阅 new_agriculture/+/telemetry 主题，解析传感器上报的 JSON 报文。

数据持久化：将环湖采集的水位、墒情历史数据定时入库，提供大屏趋势图支持。

⏱️ 快速启动
后端启动
创建数据库 ry-vue 并导入项目中的数据脚本。

修改 ruoyi-admin/src/main/resources/application-druid.yml 中的数据库连接配置。

启动 RuoYiApplication 主类。

前端启动
Bash
cd ruoyi-ui
npm install
npm run dev
