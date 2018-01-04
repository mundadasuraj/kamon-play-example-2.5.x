name := """kamon-play-example-2.5.x"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

val kamonVersion = "0.6.6"

libraryDependencies ++= Seq(
  "io.kamon"   %% "kamon-core" 			 % kamonVersion,
  "io.kamon" 	%% "kamon-annotation" 	 % kamonVersion,
  "io.kamon"   %% "kamon-play-2.5"       % kamonVersion,
  "io.kamon"   %% "kamon-system-metrics" % kamonVersion,
  "io.kamon"   %% "kamon-statsd"         % kamonVersion,
  "io.kamon"   %% "kamon-log-reporter"   % kamonVersion,
  "org.aspectj" % "aspectjweaver"        % "1.8.9"
)