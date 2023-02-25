## Scala goodies for cdkft


### How to use it

Given your valid scala cdktf script (see Development section below), deploy it using

```bash
$ scala-cli run myScript.scala
$ cdktf deploy 
```

### Development

* Use `scala-cli run myScript.scala`
* For ide support `scala-cli setup-ide .`

```scala
// myScript.scala content
//> using dep "dev.gondcs.cdktf::scala-cdktf:0.1.2"
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

