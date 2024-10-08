(ns bilig.middleware
  (:require [bilig.config :refer [env]]
            [bilig.env :refer [defaults]]
            [bilig.middleware.formats :as formats]
    ;; [bilig.layout :refer [error-page]]
            [clojure.tools.logging :as log]
            [muuntaja.middleware :refer [wrap-format wrap-params]]
            [ring-ttl-session.core :refer [ttl-memory-store]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.webjars :refer [wrap-webjars]])
  (:import))


(defn wrap-internal-error [handler]
  (fn [req]
    (try
      (handler req)
      (catch Throwable t
        (log/error t (.getMessage t))
        {:status 500
                     :title "Something very bad has happened!"
                     :message "We've dispatched a team of highly trained gnomes to take care of the problem."}))))

(defn wrap-csrf [handler]
  (wrap-anti-forgery
    handler
    {:error-response
     
       {:status 403
        :title "Invalid anti-forgery token"}}))


(defn wrap-formats [handler]
  (let [wrapped (-> handler wrap-params (wrap-format formats/instance))]
    (fn [request]
      ;; disable wrap-formats for websockets
      ;; since they're not compatible with this middleware
      ((if (:websocket? request) handler wrapped) request))))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-webjars
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (assoc-in  [:session :store] (ttl-memory-store (* 60 30)))))
      wrap-internal-error))
