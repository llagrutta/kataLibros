name := "KataLibro"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "com.googlecode.jmockit" % "jmockit" % "1.7"

play.Project.playJavaSettings
ScctPlugin.instrumentSettings
