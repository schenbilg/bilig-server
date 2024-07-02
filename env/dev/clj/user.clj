(ns user
  (:require [bilig.config :refer [env]]
            [bilig.core :refer [start-app]]
            [bilig.db.mysql]
            [clojure.spec.alpha :as s]
            [conman.core :as conman]
            [expound.alpha :as expound]
            [luminus-migrations.core :as migrations]
            [mount.core :as mount]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'bilig.core/repl-server))

(defn stop []
  (mount/stop-except #'bilig.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'bilig.db.mysql/*db*)
  (mount/start #'bilig.db.mysql/*db*)
  (binding [*ns* 'bilig.db.mysql]
    (conman/bind-connection bilig.db.mysql/*db* "sql/mysql.sql"))

  (defn reset-db []
    (migrations/migrate ["reset"] (select-keys env [:database-url])))

  (defn migrate []
    (migrations/migrate ["migrate"] (select-keys env [:database-url])))

  (defn rollback []
    (migrations/migrate ["rollback"] (select-keys env [:database-url])))

  (defn create-migration [name]
    (migrations/create name (select-keys env [:database-url])))
  )