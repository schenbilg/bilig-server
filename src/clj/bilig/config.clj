(ns bilig.config
  "配置管理命名空间，负责处理应用程序的配置加载和刷新"
  (:require [clojure.tools.logging :as log]
            [cprop.core :refer [load-config]]
            [cprop.source :as source]
            [mount.core :as mount :refer [args defstate]]))

;; env 是一个全局配置状态
;; 使用mount的defstate宏定义，在应用启动时自动初始化
;; 配置加载优先级：命令行参数 > 系统属性 > 环境变量
(defstate env
  :start
  (load-config
    :merge
    [(args)                     ; 从命令行参数加载配置
     (source/from-system-props) ; 从Java系统属性加载配置
     (source/from-env)]))       ; 从环境变量加载配置

;; 提供配置热重载功能的函数
;; 在不重启应用的情况下重新加载配置
;; 使用场景：需要动态更新配置时调用此函数
(defn refresh-config []
  (log/warn "refresh-config start")         ; 记录重载开始
  (mount/stop #'bilig.config/env)           ; 停止当前配置状态
  (mount/start #'bilig.config/env)          ; 重新加载配置
  (log/warn "env = " env)                   ; 输出新的配置内容用于确认
  (log/warn "refresh-config end"))          ; 记录重载完成
