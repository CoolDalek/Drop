package com.scalamandra.drop

import com.badlogic.gdx.backends.lwjgl3.*

object Main {

  def main(args: Array[String]): Unit = {
    val config = Lwjgl3ApplicationConfiguration()
    config.setTitle("Drop")
    config.setResizable(false)
    config.setWindowSizeLimits(
      800,
      400,
      800,
      400
    )
    Lwjgl3Application(new App, config)
  }

}