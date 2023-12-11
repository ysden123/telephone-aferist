/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.stulsoft.common.ManifestInfo
import com.typesafe.scalalogging.StrictLogging

import scala.io.StdIn
import scala.util.{Failure, Success}

object Main extends StrictLogging:
  def main(args: Array[String]): Unit =
    ManifestInfo("com.stulsoft", "telephone-swindler").showManifest()
    val filename = s"""${System.getenv("APPDATA")}\\telephone-swindler\\telephones.txt"""
    Parser.parseFile(filename) match
      case Success(telephones) =>
        println("Sorted telephones:")
        telephones.sortBy(telephone => telephone.number).foreach(println)
        println()

        val result= Processor.findCommonPrefixes(telephones)
        println(s"Total number of telephones is ${result.total}")
        println("Common telephone prefixes:")
        result.prefixes.toList.sortBy(prefix => prefix.number).foreach(println)
      case Failure(exception) =>
        logger.error(exception.getMessage, exception)

    println("Press Enter to close the window...")
    println()
    StdIn.readLine()
