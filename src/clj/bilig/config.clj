(ns bilig.config
  (:require [clojure.tools.logging :as log]
            [cprop.core :refer [load-config]]
            [cprop.source :as source]
            [mount.core :as mount :refer [args defstate]]))

(defstate env
  :start
  (load-config
    :merge
    [(args)
     (source/from-system-props)
     (source/from-env)]))

(defn refresh-config []
  (log/warn "refresh-config start")
  (mount/stop #'bilig.config/env)
  (mount/start #'bilig.config/env)
  (log/warn "env = " env)
  (log/warn "refresh-config end"))
