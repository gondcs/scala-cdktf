package dev.gondcs.cdktf.scaladsl.utils

import com.hashicorp.cdktf.{TerraformOutput, TerraformStack}
import software.constructs.Construct

package object cmdline:

  extension[T] (a: Option[T]) {
    def |[B >: T](b: B): B = a.getOrElse(b)
  }

  case class MissingEnvVars(envVars: String*)
    extends IllegalArgumentException(s"Missing any of the following env variables ${envVars.mkString(",")}")

  /**
   * Try to get a sequence of env vars
   * @param keys env vars key ie: AWS_ACCOUNT_ID
   * @return return the first key found or None
   */
  def getEnvVars(keys: String*): Option[String] = keys.foldLeft(None: Option[String]) {
    case (None, envVarKey) => sys.env.get(envVarKey)
    case (envVarFound: Some[String], _) => envVarFound
  }

  /**
   * Try to get a sequence of env vars or fail
   * @param keys env vars key ie: AWS_ACCOUNT_ID
   * @return return the first key found or fail
   */
  def getEnvVarsOrFail(keys: String*): String =
    getEnvVars(keys:_*).getOrElse(throw MissingEnvVars(keys:_*))	