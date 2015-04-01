lazy val scalaSandbox = (project in file (".")).
  settings(
    name := "sandbox-scala",
    version := "1.0",
    scalaVersion := "2.11.6",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"   % "2.3.9",
      "com.typesafe.akka" %% "akka-agent"   % "2.3.9",
      "com.typesafe.akka" %% "akka-testkit" % "2.3.9",
      "org.specs2"        %% "specs2-core"  % "3.3.1"
    ),
    resolvers += "scalaz-binaries" at "http://dl.bintray.com/scalaz/releases",
    scalacOptions in Test ++= Seq("-Yrangepos")
  )