// Databricks notebook source
val df1 = sc.textFile("dbfs:/FileStore/tables/Sales.txt")

// COMMAND ----------

display(dbutils.fs.ls("dbfs:/FileStore/tables"))

// COMMAND ----------

df1.take(3)

// COMMAND ----------

df1.count()

// COMMAND ----------

df1.collect.foreach(println)

// COMMAND ----------

val linesWithVisa = df1.filter(line => line.contains("Visa"))
val lineWithMastercard= df1.filter(line => line.contains("Mastercard"))
linesWithVisa.count()

// COMMAND ----------

lineWithMastercard.count()

// COMMAND ----------

val allwords = df1.flatMap(_.split("\\W+")) 
val pairs = allwords.map((_,1))
val reducedByKey = pairs.reduceByKey(_+_)
val top20words = reducedByKey.takeOrdered(20)(Ordering[Int].reverse.on(_._2))
top20words.foreach(println) 

// COMMAND ----------

val df2 = sqlContext.sql("SELECT * FROM stock_prices_5_csv")
df2.show(30)

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from stock_prices_5_csv

// COMMAND ----------



// COMMAND ----------

// MAGIC %sql
// MAGIC select open,high,low from stock_prices_5_csv

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT ticker, avg(close - open) as Avg_return
// MAGIC FROM stock_prices_5_csv
// MAGIC group by ticker;

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT ticker, avg(close * volume) as most_frequent
// MAGIC FROM stock_prices_5_csv
// MAGIC group by ticker;

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT stock_date, ticker, avg(close - open) as Apple_stock_returns
// MAGIC FROM stocks
// MAGIC where ticker= 'AAPL'
// MAGIC AND year(stock_date)= 2015
// MAGIC group by ticker,stock_date

// COMMAND ----------


