import de.johoop.jacoco4sbt._
import JacocoPlugin._

name := "KataLibro"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "com.googlecode.jmockit" % "jmockit" % "1.7"

play.Project.playJavaSettings

jacoco.settings

itJacoco.settings
