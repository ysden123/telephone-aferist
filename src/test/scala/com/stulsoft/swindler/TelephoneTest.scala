/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class TelephoneTest extends AnyFlatSpec:
  "fromString" should "return Telephone object" in {
    val text = "+972 54-228-6954, 2023/11/27"
    Telephone.fromString("")
    val telephone = Telephone.fromString(text)
    telephone match
      case Failure(exception) => fail("Does not work properly! " + exception.getMessage)
      case Success(tel) =>
        assertResult("+972 54-228-6954")(tel.number)
        println(tel.date)
  }
