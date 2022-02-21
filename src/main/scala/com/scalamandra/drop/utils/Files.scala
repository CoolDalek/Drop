package com.scalamandra.drop.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

object Files {

  inline def internal(name: String): FileHandle = Gdx.files.classpath(s"assets/$name")

}