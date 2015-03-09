lazy val scalaSandbox = (project in file (".")).
  settings(
    name := "sandbox-scala",
    version := "1.0",
    scalaVersion := "2.11.5",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.3.9",
      "com.typesafe.akka" %% "akka-agent" % "2.3.9",
      "com.typesafe.akka" %% "akka-testkit" % "2.3.9"
    )
  )