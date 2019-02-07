calling localhost:8080/unexpected/10
will print the lines

```
Library.getAFuture() called with 10
Library.getAFuture() called with 5
```

when it should only be printing the first line.
localhost:8080/expected/10 will print a single line and the code difference afaik should be behaving the same
