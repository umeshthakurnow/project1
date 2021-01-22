import org.apache.spark.sql.SparkSession

object GroceryShoppingCart {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Grocery-App").getOrCreate()
    val data= spark.sparkContext.textFile("C:\\input\\grocery")

    val productCount= data.flatMap(x=>x.split(",")).map(word => (word,1)).reduceByKey(_ + _)

    val products= productCount.map(x=>"Product: ".concat(x._1.toString)).filter(e=> !e.equals("Product: "))

    //task2 (a)
    products.coalesce(1).saveAsTextFile("C:\\output\\out_1_2a")

    //task2 (b)
    val out = new java.io.PrintWriter("C:\\out_1_2b")
    out.println("Count: "+products.count)
    out.close()

    //task 3
    productCount.filter(e=> !e._1.equals("")).coalesce(1).saveAsTextFile("C:\\output\\out_1_3")
  }

}
