#!/bin/bash
############################################################################
#	Job Name: Business intelligence reports and delivery of data insights.
#	Application/s name: Grocery-App and AirBnB-App
#	Description:This application will allow you to get in depth insights of
#				grocery sales data and Airbnb registered properties.
#				There are two separate spark applications Grocery-App and AirBnB-App
#				to deal with grocery data and Airbnb data respectively.
#
#
#############################################################################
#	Script Maintenance History (Date Descending)
#############################################################################
#	Date			Programmer				Description
#	====			==========				============
#	20200122		Umesh Thakur			Initial Version
#
#############################################################################

set -v
user=(whoami)
app=${1}

spark2-submit \
--master yarn \
--deploy-mode client \
--deploy-mode cluster \
--driver-memory 8g \
--executor-memory 16g \
--executor-cores 2  \
--class $app \
/home/project1_2.12-0.1.jar >>/home/log.txt

if [ $? -eq 0 ];then
sendmail -t <<- END_MAIL
from: $user@comp.com
to: umeshthakurnow@gmail.com
subject: Application successfully executed.
Spark submit job executed, detailed log is available at /home/log.txt
END_MAIL
else
sendmail -t <<- END_MAIL
from: $user@comp.com
to: umeshthakurnow@gmail
subject: Application failed to execute.
Spark submit job failed, detailed log ia available at /home/log.txt
END_MAIL
exit 1
fi