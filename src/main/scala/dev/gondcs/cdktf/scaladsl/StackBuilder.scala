package dev.gondcs.cdktf.scaladsl

object StackBuilder:
  
  def defaultContent(currentFileName: String) =
    s"""
    {
      "language": "java",
      "app": "scala-cli $currentFileName"
    }""".replaceAll("^\\s+", "")

  import java.io._
  def apply(stack: CdktfStack, currentFileName: sourcecode.FileName, configFileName: String = "cdktf.json") =
    val s = defaultContent(currentFileName.value)
    if (!File(configFileName).exists())
      val bw = new BufferedWriter(new FileWriter(new File(configFileName)))      
      try
        bw.write(s)
      finally
        bw.close()
