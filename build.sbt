lazy val scalaSandbox = (project in file (".")).
  settings(
    name := "sandbox-scala",
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "com.typesafe.akka"      %% "akka-actor"               % "2.3.12",
      "com.typesafe.akka"      %% "akka-agent"               % "2.3.12",
      "com.typesafe.akka"      %% "akka-testkit"             % "2.3.12",
      "org.scala-lang"         %  "scala-reflect"            % "2.11.8",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
      "org.scala-lang.modules" %% "scala-xml"                % "1.0.4",
      "org.specs2"             %% "specs2-core"              % "3.4"  ,
      "org.specs2"             %% "specs2-scalacheck"        % "3.4"
    ),
    resolvers += "scalaz-binaries" at "http://dl.bintray.com/scalaz/releases",
    scalacOptions in Test ++= Seq("-Yrangepos")
  )
