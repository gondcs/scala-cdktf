val scala3Version = "3.2.2"
val munitVersion = "0.7.29"
val cdktfVersion = "0.15.5"
val constructsVersion = "10.1.256"
val sourcecodeVersion = "0.3.0"

inThisBuild(
  List(
    organization := "dev.gondcs",
    homepage := Some(url("https://github.com/gondcs")),
    licenses     := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers   := List(
      Developer(
        "gondcs",
        "Gonzalo del Castillo",
        "gdelca5@gmail.com",
        url("https://github.com/gondcs")
      )
    )
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-cdktf",
    organization := "dev.gondcs",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.hashicorp" % "cdktf" % cdktfVersion,
      "software.constructs" % "constructs" % constructsVersion,
      "com.lihaoyi" %% "sourcecode" % sourcecodeVersion,
      "org.scalameta" %% "munit" % munitVersion % Test
    )
  )
