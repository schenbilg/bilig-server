(ns bilig.db.mysql
  (:require
    [bilig.config :refer [env]]
    [cheshire.generate :refer [add-encoder encode-str remove-encoder]]
    [clj-time.jdbc]
    [clojure.java.jdbc :as jdbc]
    [clojure.tools.logging :as log]
    [conman.core :as conman]
    [java-time.pre-java8 :as jt]
    [mount.core :refer [defstate]])
  (:import [java.sql
            BatchUpdateException
            PreparedStatement]))
;mysql配置文件
(defstate ^:dynamic *db*
  :start (if-let [jdbc-url (env :mysql-url)]
           (conman/connect! {:jdbc-url jdbc-url :maximum-pool-size 2 :minimum-idle 1})
           (do
             (log/warn "database connection URL was not found, please set :mysql-url in your config, e.g: dev-config.edn")
             *db*))
  :stop (conman/disconnect! *db*))

(conman/bind-connection *db* "sql/mysql.sql")

(extend-protocol jdbc/IResultSetReadColumn
  java.sql.Timestamp
  (result-set-read-column [v _2 _3]
    (.toLocalDateTime v))
  java.sql.Date
  (result-set-read-column [v _2 _3]
    (.toLocalDate v))
  java.sql.Time
  (result-set-read-column [v _2 _3]
    (.toLocalTime v)))

(extend-protocol jdbc/ISQLValue
  java.util.Date
  (sql-value [v]
    (java.sql.Timestamp. (.getTime v)))
  java.time.LocalTime
  (sql-value [v]
    (jt/sql-time v))
  java.time.LocalDate
  (sql-value [v]
    (jt/sql-date v))
  java.time.LocalDateTime
  (sql-value [v]
    (jt/sql-timestamp v))
  java.time.ZonedDateTime
  (sql-value [v]
    (jt/sql-timestamp v)))

(add-encoder java.time.LocalDateTime
             (fn [c jsonGenerator]
               (.writeString jsonGenerator (.toString c))))
