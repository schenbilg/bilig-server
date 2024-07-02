(ns bilig.db.sql
  (:require
    [clj-time.jdbc]
    [clojure.java.jdbc :as jdbc]
    [conman.core :as conman]
    [java-time.pre-java8 :as jt]
    [hugsql.core :as hugsql]))


; (hugsql/def-db-fns "sql/raw.sql")
; (conman/bind-connection *db* "sql/raw.sql")
(hugsql/def-sqlvec-fns "sql/raw.sql")
