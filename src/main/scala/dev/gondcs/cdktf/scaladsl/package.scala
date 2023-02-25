package dev.gondcs.cdktf

import com.hashicorp.cdktf.TerraformOutput.Builder

package object scaladsl:

	extension (a: String)
		def terraformOutput(key: String)(using CdktfStack): Unit =
		  Builder.create(summon[CdktfStack], key)
		    .value(a)
		    .build()
