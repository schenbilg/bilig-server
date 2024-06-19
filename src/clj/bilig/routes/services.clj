(ns bilig.routes.services
  (:require [bilig.config :as config :refer [env]]
            [bilig.services.users :as users]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all])
  (:import (java.util Date)))

(def service-routes
  (api
    {:swagger {:ui "/swagger-ui"
               :spec "/swagger.json"
               :data {:info {:version "1.0.0"
                             :title "Tulge_System"
                             :description "后端服务"}}}}

    (context "/api/user" []
      :tags ["用户"]
      (POST "/register" []
        :return {:RSP_CODE s/Str :RSP_MSG s/Str}
        :body [{:keys [login_name] :as params }    users/userParams]
        :summary      "用户注册"
        (users/user-register (assos params )))

      ;(POST "/login" []
      ;  :return       recommend/RecommendFeedbackHandleResult
      ;  :body [{:keys [userName password ] :as params} users/user-login]
      ;  :summary      "用户登录"
      ;  (feedback/feedback (assoc params :type "recommend"))
      ;  (ok {:res_code "0" :res_desc ""}))
      ;
      ;(POST "/logout" []
      ;  :return       channel/CustomChannelResult
      ;  :body [{:keys [TELNUM FIELDS] :as params} users/user-logout]
      ;  :summary      "用户登出"
      ;  (ok {:res_code "0" :res_desc ""
      ;       :result {:BUSIINFO (channel/query-user-channels "T" FIELDS TELNUM)}}))
      ;
      ;(POST "/update" []
      ;  :return { :RSP_CODE s/Str :RSP_MSG s/Str}
      ;  :body [{:keys [ACTIVITY_CODE CONTENT]} users/update-user]
      ;  :summary      "用户更新"
      ;  (market-job/stop-market-job ACTIVITY_CODE CONTENT))
      ;
      ;(POST "/delete" []
      ;      :return { :RSP_CODE s/Str :RSP_MSG s/Str}
      ;      :body [{:keys [ACTIVITY_CODE CONTENT]} users/delete-user]
      ;      :summary      "用户更新"
      ;      (market-job/stop-market-job ACTIVITY_CODE CONTENT))
      ;(POST "/list" []
      ;      :return { :RSP_CODE s/Str :RSP_MSG s/Str}
      ;      :body [{:keys [ACTIVITY_CODE CONTENT]} users/user-list]
      ;      :summary      "用户更新"
      ;      (market-job/stop-market-job ACTIVITY_CODE CONTENT))
      ;(POST "/queryByUserId" []
      ;      :return { :RSP_CODE s/Str :RSP_MSG s/Str}
      ;      :body [{:keys [ACTIVITY_CODE CONTENT]} users/queryByUserId]
      ;      :summary      "按用户ID查询用户"
      ;      (market-job/stop-market-job ACTIVITY_CODE CONTENT))
)))