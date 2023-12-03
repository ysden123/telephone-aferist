/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.stulsoft.common.ManifestInfo
import com.typesafe.scalalogging.StrictLogging

import scala.util.{Failure, Success}

object Main extends StrictLogging:
  def main(args: Array[String]): Unit =
    ManifestInfo("com.stulsoft", "telephone-swindler").showManifest()
    val filename = s"""${System.getenv("APPDATA")}\\telephone-swindler\\telephones.txt"""
    Parser.parseFile(filename) match
      case Success(telephones) =>
        val commonPrefixes = Processor.findCommonPrefixes(telephones)
        println("Common telephone prefixes:")
        commonPrefixes.foreach(println)
      case Failure(exception) =>
        logger.error(exception.getMessage, exception)