name := "scala-sbt-cross-compile"

organization := "sample"

version := "1.0"

crossScalaVersions := Seq("2.12.7", "2.11.12", "2.10.7", "0.17.0-RC1")

scalaVersion := "2.12.7"

// add dependencies on standard Scala modules, in a way
// supporting cross-version publishing
// taken from: http://github.com/scala/scala-module-dependency-sample
libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if Scala 2.12+ is used, use scala-swing 2.x
    case Some((2, scalaMajor)) if scalaMajor >= 12 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
        "org.scala-lang.modules" %% "scala-swing" % "2.1.1")
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
        "org.scala-lang.modules" %% "scala-swing" % "2.1.1")
    case Some((0, dottyMajor)) =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.2.0" withDottyCompat(scalaVersion.value),
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"  withDottyCompat(scalaVersion.value),
        "org.scala-lang.modules" %% "scala-swing" % "2.1.1"  withDottyCompat(scalaVersion.value))
    case _ =>
      // or just libraryDependencies.value if you don't depend on scala-swing
      libraryDependencies.value :+ "org.scala-lang" % "scala-swing" % scalaVersion.value
  }
}
