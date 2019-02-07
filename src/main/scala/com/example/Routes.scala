package com.example

import akka.http.scaladsl.server._
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._

class Library {
    def getAFuture(n: Int): Future[String] = {
        println(s"Library.getAFuture() called with $n")
        Future.successful(s"'Library.getAFuture() called with: $n")
    }
}

class Routes(library: Library) extends MyDirectives {
    implicit val timeout = Timeout(5.seconds)

    def unexpected(n: Int): Route = {
        val future = library.getAFuture(n).mapTo[String]
        onSuccess(future) {
            //      println(s"-----future successful from the Unexpected. $n") // this gets printed twice when called with 10
            complete(_)
        }
    }

    def asExpected(n: Int): Route = {
        onSuccess(library.getAFuture(n).mapTo[String]) {
            //      println(s"-----future successful the Expected. $n")  // this also gets printed twice when called when 10
            complete(_)
        }
    }

    val route: Route = {
        pathPrefix("unexpected") {
            path(IntNumber) { number =>
                get {
                    is10orHigher(number) {
                        unexpected(10)
                    } ~
                    is5orHigher(number) {
                        unexpected(5)
                    }
                }
            }
        } ~
        pathPrefix("asexpected") {
            path(IntNumber) { number =>
                get {
                    is10orHigher(number) {
                        asExpected(10)
                    } ~
                    is5orHigher(number) {
                        asExpected(5)
                    }
                }
            }
        }
    }
}

trait MyDirectives extends Directives {
    def is10orHigher(n: Int): Directive0 = {
        if (n >= 10)
            pass
        else
            reject
    }

    def is5orHigher(n: Int): Directive0 = {
        if (n >= 5)
            pass
        else
            reject
    }
}
