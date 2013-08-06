name := "WikiParsing"
 
version := "1.0"
 
scalaVersion := "2.10.2"
 
resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/groups/public/"
 
libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.2.0",
	"com.typesafe.akka" %% "akka-slf4j"    % "2.2.0",
	"com.typesafe.akka" %% "akka-remote"   % "2.2.0",
	"com.typesafe.akka" %% "akka-agent"    % "2.2.0",
	"com.typesafe.akka" %% "akka-testkit"    % "2.2.0",
	"org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",	
	"net.debasishg" % "redisclient_2.10" % "2.10")