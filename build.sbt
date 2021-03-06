name := """play-swagger-ui"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

//routesGenerator := InjectedRoutesGenerator

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
    "mysql" % "mysql-connector-java" % "5.1.34",
    "com.typesafe.play" %% "play-slick" % "2.0.0",
    "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
    "com.h2database" % "h2" % "1.4.192",
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test",
    "io.swagger" %% "swagger-play2" % "1.5.3",
    "io.swagger" % "swagger-core" % "1.5.12",
    "io.swagger" %% "swagger-scala-module" % "1.0.2",
    specs2 % Test
)

libraryDependencies += filters

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

fork in run := true

assemblyMergeStrategy in assembly := {
    case PathList("javax", "servlet", xs @ _*)           => MergeStrategy.first
    case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
    case "application.conf"                              => MergeStrategy.concat
    case PathList(ps @ _*) if ps.last endsWith ".html"   => MergeStrategy.first
    case "unwanted.txt"                                  => MergeStrategy.discard
    case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
}

