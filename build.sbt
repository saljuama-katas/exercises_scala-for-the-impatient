name := "scala-for-the-impatient"
version := "0.1"

scalaVersion := "2.12.4"
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= {
  val scalatestVersion = "3.0.4"
  Seq(
    "org.scalactic" %% "scalactic" % scalatestVersion % Test,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test
  )
}
