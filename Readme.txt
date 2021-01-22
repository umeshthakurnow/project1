$project1-
Business intelligence reports and delivery of data insights.
========
Description-
-----------
This application will allow you to get in depth insights of grocery sales data and Arbnb registered properties.
There are two seperate spark applications Grocery-App and AirBnB-App to deal with grocery data and Airbnb data respectively.
Once run the application will read data from location C:\input\ and generate final output at location C:\output\.
None of the above application needs any parameter to run and can be executed individually by using manual run or automated tivoil pipeline.

the input and output file names are as follows.

Grocery-App
input: C:\input\groceries.csv
output: 
C:\output\output_1_2a
C:\output\output_1_2b
C:\output\output_1_3


AirBnB-App
C:\input\airbnb\part-00000-tid-4320459746949313749-5c3d407c-c844-4016-97ad-2edec446aa62-6688-1-c000.snappy.parquet
output:
C:\output\out_2_2
C:\output\out_2_3
C:\output\out_2_4

Features
--------

- Get an overall count of total item sold out in a given day(or in the given time-frame)
- Get a count of individual items sold for analytical purpose.
- Get a count of total items sold for analytical purpose.
- Make things faster
- Get a quick glance of minimum price and maximum price and total number of registered airbnb properties.
- Average number of bathrooms and bedrooms avialable in a given property when price is below 5000 and is top rated.
- Quickly know the total strength in terms of accomodation for the best properties considering the prices and user ratings.


Installation
------------

Installation and execution:
prerequisite- 
Spark Version 2.4.0 and above.
Scala Version 2.12.13 and above.
Java Verion 1.8.0 and above.

1.  Download the repository as an project on your local directory and launch using intellij
2  . Download the required input files from below locations.
https://raw.githubusercontent.com/stedy/Machine-Learning-with-R-datasets/master/groceries.csv
https://github.com/databricks/LearningSparkV2/blob/master/mlflow-project-example/data/sf-airbnb-clean.parquet/part-00000-tid-4320459746949313749-5c3d407c-c844-4016-97ad-2edec446aa62-6688-1-c000.snappy.parquet

3. Once the dependencies are resolved on intellij you can compile and execute the application to generate output data.


Contribute
----------

- Source Code: github.com/$project1/$project

Support
-------

If you are having issues, please let us know.
We have a mailing list located at: umeshthakurnow@gmail.com

License
-------

The project is licensed under the BSD license.
