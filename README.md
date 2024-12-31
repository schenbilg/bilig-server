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
