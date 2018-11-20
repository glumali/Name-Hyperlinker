# Name-Hyperlinker
HTTP Web Service to Hyperlink Names in an HTTP Snippet

## Build Instructions
```bash
$ ./gradlew clean build
$ java -jar build/libs/hyperlinker-service-0.1.0.jar
```

## Sample Commands
```
$ curl -XDELETE "http://$HOST:$PORT/names"
$ curl -H 'Content-Type:application/json' -XPUT "http://$HOST:$PORT/names/alex" -d $ '{ "url": "http://alex.com" }'
$ curl -XGET "http://$HOST:$PORT/names/alex" >> $OUT
$ curl -H 'Content-Type:application/json' -XPUT "http://$HOST:$PORT/names/alex" -d '{ "url": "http://alex.org" }'
$ curl -XGET "http://$HOST:$PORT/names/alex" >> $OUT
$ curl -H 'Content-Type:text/plain' -XPOST "http://$HOST:$PORT/annotate" -d 'my name is alex' | formatHTML >> $OUT
```

## Endpoint Performance

_For all below, N is the number of names inserted into the server._

**PUT on /names/{name}**

Space: O(N) - HashMap structure is used to hold name-link pairs, space grows with each added

Time: O(1) - constant-time put into a HashMap

**GET on /names/{name}**

Space: O(1) - no extra space needed for a get in a HashMap

Time: O(1) - contant-time retrieval from a HashMap

**DELETE on /names**

Space: O(1) - no extra space needed to delete entries in a HashMap

Time: O(N) - must delete all N entries from the HashMap

**POST on /annotate**

_For this below, k is the number of elements in the parsed HTML._

Space: O(k) - each element is encapsulated in a Jsoup.Element object

Time: O(Nk) - for each element, must search through HashMap if contained within
