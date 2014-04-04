# probe-irc

A VERY basic (for now) sink for [Probe] (https://github.com/vitalreactor/probe).

## Usage

Probe itself is not a dependency of this project, but it does get pulled
into the user profile for use when working at the repl.

repl example

```clojure
(require :reload '[probe-irc.core :as irc])

(require '[probe.core :as p])

(irc/set-irc-params! {:host "irc.freenode.net"
                      :channel "#djjsandbox"
                      :username "your-username"
                      :nick "probe-irc-sink"})

(p/add-sink :irc irc/irc-sink)

(p/subscribe #{:irc-events} :irc)

(p/probe [:irc-events] :msg "Hello IRC" :value 42)

(p/probe [:irc-events] :msg "Glad to see you are finally doing my bidding!" :value 42)
```

resulted in the following IRC messages

```
probe-irc-sink: {:ts #inst "2014-04-03T23:47:52.751-00:00", :thread-id 21, :ns user, :tags #{:irc-events :ns/user}, :line 1, :msg "Hello IRC", :value 42}
probe-irc-sink: {:ts #inst "2014-04-03T23:50:25.428-00:00", :thread-id 35, :ns user, :tags #{:irc-events :ns/user}, :line 1, :msg "Glad to see you are finally doing my bidding!", :value 42}
```


## Example

## TODO
 * testing
 * formatting (possibly pulled over from probe if fns made public)
 * support multiple channels
 * support SSL/auth
 * lots to do.  This is about as basic as it gets.



## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
