import sbt._

object Dependencies {

  object Gdx {

    private def dependency(module: String): ModuleID =
      "com.badlogicgames.gdx" % module % "1.10.0"

    private def moduleName(module: Option[String]): String =
      "gdx" + module.map("-" + _).getOrElse("")

    private def dependency(module: Option[String]): ModuleID = {
      val name = moduleName(module)
      dependency(name)
    }

    private def nativeDependency(module: Option[String]): ModuleID = {
      val name = moduleName(module) + "-platform"
      dependency(name) classifier "natives-desktop"
    }

    def apply(): Seq[ModuleID] = {
      val box2d = Some("box2d")

      val dependencies = Seq(
        None,
        Some("backend-lwjgl3"),
        box2d
      ).map(dependency)

      val nativeDependencies = Seq(
        None,
        box2d
      ).map(nativeDependency)

      dependencies ++ nativeDependencies
    }

  }

}