# bilig-server

A Clojure library designed to ... well, that part is up to you.

## Usage

FIXME

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run

## License

Copyright © 2023 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

# 更新日志

## 依赖版本更新 (project.clj)

### 更新核心依赖到最新稳定版本
- Clojure 核心升级到 1.11.1
- Ring 框架升级到 1.10.0
- Ring-defaults 升级到 0.4.0
- Mount 升级到 0.1.18
- Cprop 升级到 0.1.19
- Logback 升级到 1.4.14

### 新增依赖
- Reitit 0.7.0-alpha7 (现代化路由库)
- next.jdbc 1.3.894 (新一代 JDBC 库)
- PostgreSQL 驱动 42.7.2
- HikariCP 5.1.0 (高性能连接池)
- SLF4J API 2.0.11 (日志接口)

这次更新：
- 提升了项目整体性能和安全性
- 引入了更现代化的数据库访问方案
- 优化了日志系统
- 确保了各个依赖间的兼容性

## 配置文件修改 (src/clj/bilig/config.clj)

### 添加详细代码注释
- 为整个配置模块添加了全面的中文注释
- 说明了各个依赖库的用途
- 解释了配置加载的优先级
- 详细说明了配置热重载功能的使用场景

注释的改进包括：
- 对每个require的库添加了用途说明
- 解释了env状态的作用和配置加载顺序
- 说明了refresh-config函数的使用场景和工作原理

## Luminus 框架更新

### 更新 Luminus 相关依赖到最新版本
- luminus-http-kit 升级到 0.2.0
- luminus-migrations 升级到 0.7.5
- luminus-transit 升级到 0.1.6
- ring-ttl-session 升级到 0.3.3

更新优势：
- 提升了 HTTP 服务器性能
- 改进了数据库迁移功能
- 优化了会话管理
- 增强了数据传输效率

## 依赖修复更新

### 解决依赖冲突和缺失
- 添加 clj-time 0.15.2 支持时间处理
- 添加 jackson-core 2.15.3 解决 JSON 处理依赖
- 显式声明 joda-time 2.12.5 版本
- 更新 compojure-api 到 2.0.0-alpha31

修复内容：
- 解决了 clj-time/jdbc 缺失问题
- 处理了 Jackson 相关的依赖冲突
- 统一了时间处理库的版本

## 代码质量改进

### 更新 clj-kondo 配置
- 完善了 .clj-kondo/config.edn 配置
- 添加了 user 命名空间的特殊处理
- 优化了开发环境的静态分析体验

改进内容：
- 处理了 user 命名空间的警告
- 保持了已有的 mount.core/defstate 配置
- 改进了 REPL 开发体验
