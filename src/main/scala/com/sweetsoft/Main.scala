package com.sweetsoft

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze._

object Main extends IOApp {

  val helloService = HttpRoutes.of[IO] {
    case GET -> Root / "greet" =>
      Ok(s"Greet from Foo service!!!!!!")
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(helloService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)

}
