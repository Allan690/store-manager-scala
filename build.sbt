import sbt.Keys._

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library = new {
  object Version {
    val akka = "2.6.1"
    val akkaHttp = "10.1.11"
    val flyway = "3.2.1"
    val scalaTest = "3.1.0"
    val logbackVersion = "1.2.3"
    val slick = "3.3.2"
    val circeVersion = "0.12.3"
    val akkaHttpCirce = "1.30.0"
  }

  val logBack: ModuleID = "ch.qos.logback" % "logback-classic" % Version.logbackVersion
  val flyway: ModuleID = "org.flywaydb" % "flyway-core" % Version.flyway
  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Version.scalaTest
  val akkaHttpCirce: ModuleID = "de.heikoseeberger" %% "akka-http-circe" % Version.akkaHttpCirce

  lazy val akka: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-actor" % Version.akka,
    "com.typesafe.akka" %% "akka-stream" % Version.akka,
    "com.typesafe.akka" %% "akka-slf4j" % Version.akka,
    "com.typesafe.akka" %% "akka-testkit" % Version.akka % Test
  )

  val akkaHttp: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-http" % Version.akkaHttp,
    "com.typesafe.akka" %% "akka-http-core" % Version.akkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % Version.akkaHttp,
    "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp % "test"
  )
  val slick: Seq[ModuleID] = Seq(
    "com.typesafe.slick" %% "slick" % Version.slick,
    "com.typesafe.slick" %% "slick-hikaricp" % Version.slick,
    "org.postgresql" % "postgresql" % "9.4.1211",
    "com.h2database" % "h2" % "1.4.192" % "test"
  )

  val circe: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core" % Version.circeVersion,
    "io.circe" %% "circe-generic" % Version.circeVersion,
    "io.circe" %% "circe-parser" % Version.circeVersion,
  )
}

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings = projectSettings

lazy val projectSettings =
  Seq(
    scalaVersion := "2.13.1",
    organization := "net.storemanager",
    version := "0.1",
    organizationName := "Allan Mogusu"
  )

// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `StoreManager-scala` = (project in file("storemanager"))
  .enablePlugins(JavaAppPackaging)
  .settings(settings)
  .settings(
    libraryDependencies ++= library.akka ++ library.akkaHttp ++ library.slick ++ library.circe ++
      Seq(library.flyway, library.logBack, library.akkaHttpCirce, library.scalaTest % Test),
    logBuffered := false
  )

lazy val root = (project in file("."))
  .settings(settings)
  .settings(
    aggregate in update := false,
    publishArtifact := false
  )
  .aggregate(`StoreManager-scala`)