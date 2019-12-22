package com.sweetsoft

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze._

object Main extends IOApp {

  val readEnv : IO[Int] = IO {
    if (System.getenv("PORT").isEmpty) {
       8080
    } else {
      System.getenv("PORT").toInt
    }
  }

  val runServer : Int => IO[ExitCode] = port =>
    BlazeServerBuilder[IO]
      .bindHttp(port, "0.0.0.0")
      .withHttpApp(helloService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)

  val helloService = HttpRoutes.of[IO] {
    case GET -> Root / "greet" =>
      Ok(s"Greet from Foo service!!!!!!")
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =

    for {
      a <- readEnv
      b <- runServer(a)
    } yield b


}
