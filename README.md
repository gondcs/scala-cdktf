## Scala goodies for cdkft

### How to use it

Given your valid scala cdktf script (see Development section below), deploy it using

```bash
$ scala-cli run myScript.scala
Compiling project (Scala 3.2.2, JVM)
Compiled project (Scala 3.2.2, JVM)

✔ ~/cdktf/example 
$ export AWS_PROFILE=dev
✔ ~/cdktf/example 
$ cdktf deploy

⠦  Synthesizing
example-scala-cdktf  Initializing the backend...
example-scala-cdktf  
                     Successfully configured the backend "local"! Terraform will automatically
                     use this backend unless the backend configuration changes.
example-scala-cdktf  Initializing provider plugins...
example-scala-cdktf  - Finding hashicorp/aws versions matching "4.55.0"...
example-scala-cdktf  - Using hashicorp/aws v4.55.0 from the shared cache directory
example-scala-cdktf  Terraform has created a lock file .terraform.lock.hcl to record the provider
                     selections it made above. Include this file in your version control repository
                     so that Terraform can guarantee to make the same selections by default when
                     you run "terraform init" in the future.
...
```

### Development

* First run: Use `scala-cli run myScript.scala`
  * this will create a `cdktf.json`
* Tip: for ide support `scala-cli setup-ide .`

### Example Scala CLI script

```scala
// myScript.scala content
//> using dep "dev.gondcs::scala-cdktf:0.1.0"
//> using dep "com.hashicorp:cdktf-provider-aws:12.0.5"

import dev.gondcs.cdktf.scaladsl._
import dev.gondcs.cdktf.scaladsl.utils.Props
import dev.gondcs.cdktf.scaladsl.utils.cmdline._
import scala.jdk.CollectionConverters.*
import software.constructs.Construct
import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket
import com.hashicorp.cdktf.providers.aws.provider.AwsProvider

@main def main: Unit =
  val app = new com.hashicorp.cdktf.App
  val stack = new MainStack(Props.default, app, "example-scala-cdktf")
  StackBuilder(stack, summon[sourcecode.FileName])
  app.synth()

// From: https://developer.hashicorp.com/terraform/tutorials/cdktf/cdktf-install
class MainStack(val props: Props,
                val scope: Construct,
                val id: String) extends CdktfStack(scope, id):

  // VPC examples https://github.com/ahmadalibagheri/cdktf-typescript-aws-vpc
  val projectName = "example"
  val bucketName = "scala-cdktf-bucket-test"
  val tags =
    Map(
      "project" -> projectName
    ).asJava
~~~~
  val provider = AwsProvider.Builder.create(this, "AWS")
    .region(props.region)
    .profile(props.profile)
    .build()

  val myBucket = S3Bucket.Builder.create(this, bucketName)
    .bucket(bucketName)
    .tags(tags)
    .build()

  myBucket.getArn.terraformOutput("bucket-arn")
  myBucket.getBucket.terraformOutput("bucket-name")  
```

