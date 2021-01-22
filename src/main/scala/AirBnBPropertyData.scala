import org.apache.spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object AirBnBPropertyData {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.appName("test").getOrCreate()
    //task1
    val propertyData= spark.read.parquet("/home/data.parquet")

    //task2
    val pricesMinMax = propertyData.agg(min("price").alias("min_price"), max("price").alias("max_price"),count("*").alias("row_count"))
    pricesMinMax.write.option("header","true").option("inferSchema","true").option("delimiter","|").mode("overwrite").csv("out_2_2.csv")

    //task 3
    val avgRoomsForTopRatedProperties=
      propertyData.filter(col("price")>5000)
        .filter(
            col("review_scores_rating")===10D and
            col("review_scores_accuracy")===10D and
            col("review_scores_cleanliness")===10D and
            col("review_scores_checkin")===10D and
            col("review_scores_communication")===10D and
            col("review_scores_location")===10D and
            col("review_scores_value")===10D
        ).agg(avg("bathrooms").alias("avg_bathrooms"),avg("bedrooms").alias("avg_bedrooms"))
    //Need to get confirmation if the the average count needs to be a real number since we are counting the number of bedrooms.

    avgRoomsForTopRatedProperties.write.option("header","true").option("inferSchema","true").option("delimeter","|").mode("overwrite").csv("/out_2_3")

    //task 4
    val aggregatedData= propertyData.agg(min("price").alias("lowest_price"), max("review_scores_rating").alias("highest_rating")).persist

    /* Alternate Approach to avoid join
    val lowestPrice= aggregatedData.select("lowest_price").collect().map(_.getDouble())
    val highestRating= aggregatedData.select("highest_rating").collect().map(_.getDouble())

    val task4= part2Data.filter(col("price")===lowestPrice && col("review_scores_rating")==highestRating)
    .dropDuplicates()
    agg(sum("accommodates").alias("total_number_of_people_can_be_accommodated"))
    */
    val avgStrengthsBestProperties= propertyData.join(aggregatedData,col("price")===col("lowest_price") and col("review_scores_rating")===col("highest_rating"))
      .dropDuplicates()
      .agg(sum("accommodates").alias("total_number_of_people_can_be_accommodated"))

    avgStrengthsBestProperties.rdd.map(x=>x(0)).coalesce(1).saveAsTextFile("/out_2_4")
    //since spark will not allow to store the long values as text files.
  }

}
