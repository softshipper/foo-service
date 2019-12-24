val Http4sVersion = "0.21.0-M6"
val CirceVersion = "0.12.3"
val Specs2Version = "4.7.0"
val LogbackVersion = "1.2.3"

git.useGitDescribe := true
dockerAlias := DockerAlias(None, Some("softshipper"), (packageName in Docker).value, git.gitDescribedVersion.value)

lazy val root = (project in file("."))
  .settings(
    organization := "com.sweetsoft",
    name := "foo-service",
    version := "1.0.1",
    scalaVersion := "2.13.0",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "org.specs2" %% "specs2-core" % Specs2Version % "test",
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0"),
  ).enablePlugins(GitVersioning, JavaServerAppPackaging, DockerPlugin, JlinkPlugin)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
)
