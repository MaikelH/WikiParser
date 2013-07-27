name := "WikiParsing"
 
version := "1.0"
 
scalaVersion := "2.10.2"
 

 
libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.2.0",
	"com.typesafe.akka" %% "akka-slf4j"    % "2.2.0",
	"com.typesafe.akka" %% "akka-remote"   % "2.2.0",
	"com.typesafe.akka" %% "akka-agent"    % "2.2.0")