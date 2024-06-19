(defproject bilig-server "0.0.2"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[ch.qos.logback/logback-classic "1.2.3"]
                 [cheshire "5.8.1"]
                 [clj-http "3.9.1"]
                 [clojure.java-time "0.3.2"]
                 [compojure "1.6.1" :exclusions [joda-time]]
                 [com.google.protobuf/protobuf-java "3.6.1"]
                 [com.velisco/clj-ftp "0.3.12" :exclusions [org.apache.commons/commons-compress]]
                 [org.bouncycastle/bcpkix-jdk15on "1.62"]
                 [org.bouncycastle/bcprov-jdk15on "1.62"]
                 [conman "0.8.3"]
                 [cprop "0.1.15"]
                 [funcool/struct "1.3.0"]
                 [luminus-http-kit "0.1.6"]
                 [luminus-migrations "0.6.4"]
                 [luminus-transit "0.1.1"]
                 [luminus/ring-ttl-session "0.3.2"]
                 [markdown-clj "1.0.7"]
                 [metosin/compojure-api "2.0.0-alpha28" :exclusions [ring/ring-codec clj-time com.fasterxml.jackson.core/jackson-core]]
                 [metosin/muuntaja "0.6.3" :exclusions [com.fasterxml.jackson.core/jackson-core]]
                 [metosin/ring-http-response "0.9.1" :exclusions [ring/ring-codec joda-time]]
                 [mount "0.1.16"]
                 [mysql/mysql-connector-java "5.1.49"]
                 [nrepl "0.6.0"]
                 [funcool/cuerdas "2020.03.26-3"]
                 [clj-http "3.10.1"]
                 [org.clojure/clojure "1.10.0"]la

                 [org.clojure/java.jdbc "0.7.9"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.webjars.bower/tether "1.4.4"]
                 [org.webjars/bootstrap "4.3.1"]
                 [org.webjars/font-awesome "5.7.2"]
                 [org.webjars/webjars-locator "0.36" :exclusions [com.fasterxml.jackson.core/jackson-core]]
                 [overtone/at-at "1.2.0"]
                 [redis.clients/jedis "3.0.1"]
                 [ring-logger "1.0.1"]
                 [ring-webjars "0.2.0" :exclusions [joda-time]]
                 [ring/ring-core "1.7.1" :exclusions [ring/ring-codec joda-time]]
                 [ring/ring-defaults "0.3.2" :exclusions [joda-time]]
                 [selmer "1.12.6"]
                 [org.clojars.zentrope/ojdbc "11.2.0.3.0"]
                 [clojure.jdbc/clojure.jdbc-dbcp "0.3.2"]
                 [com.jolbox/bonecp "0.8.0.RELEASE"]]
  :repositories [["central" "https://maven.aliyun.com/nexus/content/groups/public"]
                 ["clojars" "https://mirrors.tuna.tsinghua.edu.cn/clojars/"]]
  :min-lein-version "2.0.0"

  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot bilig.core

  :plugins [[lein-ancient "0.6.15"]
            [lein-uberwar "0.2.1"]
            [lein-localrepo "0.5.3"]]
  :uberwar
  {:handler bilig.handler/app
   :init bilig.handler/init
   :destroy bilig.handler/destroy
   :name "bilig.war"}
  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "bilig-server.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
                  :dependencies [[expound "0.7.2"]
                                 [pjstadig/humane-test-output "0.10.0"]
                                 [prone "2020-01-17"]
                                 [ring/ring-devel "1.8.0" :exclusions [ring/ring-codec joda-time]]
                                 [ring/ring-mock "0.3.2"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.23.0"]]

                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})

