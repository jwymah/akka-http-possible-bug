package com.example

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, OneInstancePerTest, WordSpec}
import org.mockito.Mockito.{times, verify, when}

import scala.concurrent.Future

class RoutesSpec extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest
    with OneInstancePerTest
    with MockitoSugar {

    val library: Library = mock[Library]
    when(library.getAFuture(10)).thenReturn(Future.successful("'ello 10"))
    when(library.getAFuture(5)).thenReturn(Future.successful("'ello 5"))

    lazy val routes = new Routes(library).route

    "notAsUnexpected" should {
        "call library once with 10" in {
            Get("/unexpected/10") ~> routes ~> check {
                verify(library, times(1)).getAFuture(10)
                verify(library, times(0)).getAFuture(5)
            }
        }
    }

    "asExpected" should {
        "call library once with 10" in {
            Get("/asexpected/10") ~> routes ~> check {
                verify(library, times(1)).getAFuture(10)
                verify(library, times(0)).getAFuture(5)
            }
        }
    }
}
