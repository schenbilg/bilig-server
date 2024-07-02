(ns bilig.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[bilig-server started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[bilig-server has shut down successfully]=-"))
   :middleware identity})
