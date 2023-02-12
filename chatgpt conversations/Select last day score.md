# Select last day score
**You**: select last day score of record in same number group

**ChatGPT**: To select the last day score of a record that belongs to the same group of numbers, you would need to write a query that joins the relevant tables and groups the records by the number group, and then selects the maximum date and the corresponding score for each group. The specific syntax of the query will depend on the database management system (DBMS) and the table structure you are working with, but an example query in SQL for a table named "scores" with columns "id", "score", "date", and "number_group" might look like the following:vbnet
```vbnet
SELECT number_group, MAX(date) as last_day, score
FROM scores
GROUP BY number_group
```

This query selects the number group, maximum date as last_day, and score columns from the scores table and groups the records by number group. The result of the query will be one row for each distinct number group in the table, with the last day score.

It is important to note that this query is only an example and may need to be altered according to your specific requirement, context and underlying database.

**You**: continue

**ChatGPT**: To include more specific details, if you want to fetch last day score of record where number group is equal to certain value, you can add a where clause to filter the rows based on the number group. For example:sql
```sql
SELECT number_group, MAX(date) as last_day, score
FROM scores
WHERE number_group = <specific number group>
GROUP BY number_group
```

This query will return the last day score of the record where number group is equal to the specific number group value specified in the where clause.

If you are also trying to retrieve additional information about the record with the last day score, you will likely need to use a subquery or a join to combine the information from multiple tables.

Keep in mind that depending on the size of your table and indexes on date and number_group, this query may not perform well in case of large datasets and should look into indexing to optimize the performance.

