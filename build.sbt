import java.util.Calendar

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / version := "0.1.2"
ThisBuild / organization := "com.stulsoft"
ThisBuild / organizationName := "stulsoft"

lazy val root = (project in file("."))
  .settings(
    name := "telephone-swindler",
    libraryDependencies += "com.stulsoft" %% "common" % "latest.integration",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.13",

    // Swing
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test,

    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:postfixOps",
      "-Xfatal-warnings"
    ),
    scalacOptions ++= Seq("-Xmax-inlines", "50")
  )
  .enablePlugins(JavaAppPackaging)

Compile / packageBin / packageOptions += Package.ManifestAttributes("Build-Date" -> Calendar.getInstance().getTime.toString)
Compile / mainClass := Some("com.stulsoft.swindler.Main")