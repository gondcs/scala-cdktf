package dev.gondcs.cdktf.scaladsl

import com.hashicorp.cdktf.{TerraformOutput, TerraformStack}
import software.constructs.Construct

class CdktfStack(scope: Construct, id: String) extends TerraformStack(scope, id):
	given implicitScope: CdktfStack = this

