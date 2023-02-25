package dev.gondcs.cdktf.scaladsl.utils

import cmdline._

case class Props(region: String,
                 profile: String,
                 environment: String)

object Props:
	def default: Props =
	  val region = getEnvVars("CDK_DEPLOY_REGION", "CDK_DEFAULT_REGION") | "eu-central-1"
	  val environment = getEnvVars("ENVIRONMENT_NAME") | "development"
	  val profile = getEnvVarsOrFail("AWS_PROFILE")
	  Props(region, profile, environment)
