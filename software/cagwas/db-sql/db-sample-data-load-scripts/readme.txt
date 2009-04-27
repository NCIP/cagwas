 To load sample data into an emtpy cagwas schema, please follow the below steps
1)  Create the cagwas database with just the table structure using this following script :   cagwas/db-sql/cagwas_tbls_mysql.sql
2)  Next, execute the load_all_tbls.sql to load the tables with sample data 
3)  To load your own data into caGWAS, please mimic the CSV file format


For running the script using MySQL GUI Tool ( MySQL Query Browser )
1) Login to MySQL Query Browser.
2) Select 'File - Open Script' and select the file - cagwas/db-sql/cagwas_tbls_mysql.sql
3) Select 'Script - Execute' from the top menu.
4) Make sure 'cagwas' schema is selected.
5) Select 'File - Open Script' and select the file - cagwas/db-sql/db-sample-data-load-scripts/load_all_tbls.sql




For running the script from the command prompt.
1) Navigate to the Mysql directory.
2) Run -> mysql --user=root --password=secret --database=cagwas < cagwas_tbls_mysql.sql
3) Run -> mysql --user=root --password=secret --database=cagwas < load_all_tbls.sql


