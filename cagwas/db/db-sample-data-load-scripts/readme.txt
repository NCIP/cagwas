To load sample data into an empty cagwas schema, please follow the steps below.
1) Unzip db-sample-data-load-scripts.zip.
1) Create the empty table schema using the following script : cagwas_tbls_mysql.sql
2) Next, execute the load_all_tbls.sql to load the tables with sample data 
3) To load your own data into caGWAS, please mimic the CSV file format.


For running the script using MySQL GUI Tool ( MySQL Query Browser )
1) Login to MySQL Query Browser.
2) Select 'File - Open Script' and select the file - cagwas_tbls_mysql.sql
3) Make sure 'cagwas' schema is selected.
4) Select 'Script - Execute' from the top menu. This loads the empty table schema.
5) To load the data, Select 'File - Open Script' and select the file - 
   load_all_tbls.sql.
6) Select 'Script - Execute' from the top menu. This loads the table data.




For running the script from the command prompt.
1) Navigate to the Mysql directory.
2) Run -> mysql --user=root --password=secret --database=cagwas < \$PATH$\cagwas_tbls_mysql.sql
3) Run -> mysql --user=root --password=secret --database=cagwas < \$PATH$\load_all_tbls.sql



