(defproject probe-irc "0.1.0-SNAPSHOT"
  :description "Probe messages in your IRC room!!!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [irclj "0.5.0-alpha3"]]
  :profiles {:user
             {:dependencies [[com.vitalreactor/probe "0.9.1"]]}})
