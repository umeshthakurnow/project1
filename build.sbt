
name := "project1"

version := "0.1"

scalaVersion := "2.12.13"

val sparkVersion = "2.4.0"

// Note the dependencies are provided
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"