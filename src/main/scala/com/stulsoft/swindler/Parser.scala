/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.typesafe.scalalogging.StrictLogging

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object Parser extends StrictLogging:
  def parseFile(filename: String): Try[List[Telephone]] =
    Try {
      val telephones = ListBuffer[Telephone]()
      Using(Source.fromFile(filename)) { src => {
        src.getLines().foreach(line => {
          Telephone.fromString(line) match
            case Success(telephone) =>
              telephones.addOne(telephone)
            case Failure(exception) =>
              logger.error(s"Wrong line: $line")
        })
      }

      } match
        case Success(_) =>
          telephones.toList
        case Failure(exception) =>
          logger.error(exception.getMessage, exception)
          throw exception
    }
