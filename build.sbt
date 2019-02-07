lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion = "2.5.13"

lazy val root = (project in file(".")).
    settings(
        inThisBuild(List(
            organization := "com.example",
            scalaVersion := "2.12.4"
        )),
        name := "akka-http-bug",
        libraryDependencies ++= Seq(
            "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
            "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
            "org.scalatest" %% "scalatest" % "3.0.1" % Test,
            "org.mockito" % "mockito-core" % "2.19.0" % Test
        )
    )
