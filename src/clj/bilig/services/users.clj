(ns bilig.services.users
  (:require
   [bilig.db.mysql :as mysql]
   [clojure.tools.logging :as log]
   [schema.core :as s]))

;api/user/register 用户注册
(defn user-register [user]
  (log/warn "start user-register ...." user)
  (let [result (mysql/user-register! user)]
    (if (empty? result) "注册成功" "注册失败")))

;;api/user/login 用户登录
;(defn user-login []
;  (log/warn "start user-login ...." type code)
;  (let [detection-nums (redis/hkeys detection-num)]
;    (if (empty? detection-nums) "" (redis/hdel detection-num detection-nums)))
;  (select-detection))
;
;;api/user/logout 用户登录
;(defn user-logout []
;      (log/warn "start user-login ...." type code)
;      (let [detection-nums (redis/hkeys detection-num)]
;           (if (empty? detection-nums) "" (redis/hdel detection-num detection-nums)))
;      (select-detection))
;
;;api/user/update 用户更新
;(defn update-user [user]
;  (log/warn "start update-user ...." type code)
;    (let [result (db/update-user :user_id user)]
;      (if (empty? result) "已修改用户信息！" ))
;    )
;;api/user/delete 用户删除
;(defn delete-user [user_id]
;  (log/warn "start delete-user ...." type code)
;    (let [result (db/delete-user user_id)]
;      (if (empty? result) "已删除用户信息！" ))
;  )
;;api/user/list 用户列表
;(defn user-list []
;  (let [detection-nums (redis/hkeys detection-num)]
;    (if (empty? detection-nums) "" (redis/hdel detection-num detection-nums)))
;  (select-detection))
;;api/user/queryByUserId 按用户编号查询用户
;(defn queryByUserId []
;      (let [detection-nums (redis/hkeys detection-num)]
;           (if (empty? detection-nums) "" (redis/hdel detection-num detection-nums)))
;      (select-detection))

;;使用schema 定义用户模型：入参信息
(s/defschema User
          {:login_name s/Str :login_pwd s/Str :nick_name s/Str :gender s/Str :phone s/Str :remark s/Str})
