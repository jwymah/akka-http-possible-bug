calling localhost:8080/unexpected/10
will print the lines

```
Library.getAFuture() called with 10
Library.getAFuture() called with 5
```

when it should only be printing the first line.
Written another way, localhost:8080/expected/10 will print only the first line, which is correct. Both implementations should be printing only the first line.

Submitted and triaged into `help wanted`: https://github.com/akka/akka-http/issues/2420
It was suggested it was due to eager evaluation of the directives, but eager evaluation should not be executing code behind a directive too.
