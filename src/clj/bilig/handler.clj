(ns bilig.handler
  (:require [bilig.env :refer [defaults]]
            [bilig.middleware :as middleware]
            [bilig.routes.services :refer [service-routes]]
            [clojure.tools.logging :as log]
            [compojure.core :refer [routes]]
            [compojure.route :as route]
            [mount.core :as mount]
            [ring.logger :as logger]
            [ring.middleware.content-type :refer [wrap-content-type]]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (doseq [component (:started (mount/start))]
    (log/info component "started")))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (doseq [component (:stopped (mount/stop))]
    (log/info component "stopped"))
  (shutdown-agents)
  (log/info "grid-marketing has shut down!"))

(mount/defstate app
                :start
                (middleware/wrap-base
                  (routes
                    ;; (-> #'home-routes
                    ;;     (wrap-routes middleware/wrap-csrf)
                    ;;     (wrap-routes middleware/wrap-formats))
                    (-> #'service-routes
                        logger/wrap-with-logger
                        wrap-content-type)
                    (route/not-found
                      (:body
                        {:status 404
                         :title "page not found"})))))
