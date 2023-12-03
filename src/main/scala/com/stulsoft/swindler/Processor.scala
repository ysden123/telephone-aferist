/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.typesafe.scalalogging.StrictLogging

import scala.collection.mutable

object Processor extends StrictLogging:
  def findCommonPrefixes(telephones: List[Telephone]): List[String] =
    val prefixes = mutable.SortedSet[String]()
    for (i1 <- telephones.indices) {
      val number1 = telephones(i1).number
      for (i2 <- telephones.indices) {
        if i1 != i2 then
          val number2 = telephones(i2).number
          var prefix = ""
          var continue = true
          for (j <- number1.indices if j < number2.length && continue) {
            if number1(j) == number2(j)
            then
              prefix += number1(j)
            else
              continue = false
          }
          if prefix.nonEmpty then prefixes.addOne(prefix)
      }
    }
    prefixes.toList
