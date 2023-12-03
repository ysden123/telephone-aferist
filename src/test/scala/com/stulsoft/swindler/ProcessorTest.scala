/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class ProcessorTest extends AnyFlatSpec:
  "findCommonPrefixes" should "find common prefixes" in {
    Parser.parseFile("src/test/resources/data/list2.txt") match
      case Success(telephones) =>
        val commonPrefixes = Processor.findCommonPrefixes(telephones)
        assertResult(2)(commonPrefixes.length)
        //        commonPrefixes.foreach(println)
        assertResult(true)(commonPrefixes.contains("+972 54-228-"))
        assertResult(true)(commonPrefixes.contains("+972 "))
      case Failure(exception) =>
        fail(exception.getMessage)
  }
