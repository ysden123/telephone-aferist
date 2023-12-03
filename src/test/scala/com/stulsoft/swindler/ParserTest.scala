/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}
class ParserTest extends AnyFlatSpec:
  "parser" should "parse file" in {
    val telephones = Parser.parseFile("src/test/resources/data/list1.txt") match
      case Success(value) =>
        value
      case Failure(exception) =>
        fail(exception.getMessage)

    assertResult(true)(telephones.nonEmpty)
  }

  "parser" should "fail in file" in {
    Parser.parseFile("src/test/resources/data/ERROR.txt") match
      case Success(value) =>
        fail("Should be exception")
      case Failure(exception) =>
        println(exception.getMessage)
        succeed
  }
