(ns bilig.config
  "配置管理命名空间，负责处理应用程序的配置加载和刷新"
  (:require [clojure.tools.logging :as log]
            [cprop.core :refer [load-config]]
            [cprop.source :as source]
            [mount.core :as mount :refer [args defstate]]))

;; 添加类型提示注释
#_{:clj-kondo/ignore [:unresolved-symbol]}
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
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (mount/stop #'bilig.config/env)           ; 停止当前配置状态
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (mount/start #'bilig.config/env)          ; 重新加载配置
  (log/warn "env = " env)                   ; 输出新的配置内容用于确认
  (log/warn "refresh-config end"))          ; 记录重载完成
