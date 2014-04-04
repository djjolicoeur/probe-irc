(ns probe-irc.core
  (require [irclj.core :as irc]))


;;Atom to store our connection info
(def conn (atom nil))

;;Atom to store our connection params
(def conn-params (atom nil))


(defn set-irc-params!
  "Store our IRC settings in an atom"
  [{:keys [host nick port channel username]}]
  (reset! conn-params {:host (or host "irc.freenode.net")
                       :nick (or nick "probe-irc")
                       :port (or port 6667)
                       :channel channel
                       :username username}))


(defn connect-irc-sink!
  "Establish connection to IRC server and join channel.
   Ideally, I would like to do away with storing all of
   the connection into in an atom in the future"
  []
  (let [params @conn-params
        conn (irc/connect (:host params) (:port params) (:nick params)
                          :username (:username params)
                          :callbacks {})]
    (irc/join conn (:channel params))
    conn))

;;TODO this is pretty hackish.  Redo.
(defn ensure-connect
  "Ensure we have a connection to work with and
   re-connect if not."
  []
  (swap! conn #(or % (connect-irc-sink!))))


(defn irc-sink
  "Log the raw state to IRC defined in atom conn"
  [state]
  (ensure-connect)
  (irc/message @conn
               (:channel @conn-params)
               (str state)))
