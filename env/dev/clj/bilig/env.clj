(ns bilig.env
  (:require [bilig.dev-middleware :refer [wrap-dev]]
            [clojure.tools.logging :as log]
            [selmer.parser :as parser]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[bilig-server started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[bilig-server has shut down successfully]=-"))
   :middleware wrap-dev})
