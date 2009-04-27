# This mysqldump file was generated from a MySQL instance running on version 5.0.27
# To create the cagwas database and sample data from this dump file (cagwas_tbls.dmp), 
# Run either of the following commands
 
# OPTION 1: 
# Run the following command on the database server
# MYSQL_DIRECTORY - references where MySQL was installed
# CONF_DIRECOTRY  - references the location of where the my.cnf file is located
 
<MYSQL_DIRECTORY>/bin/mysql --defaults-file=<CONF_DIRECTORY>/my.cnf < cagwas_tbls.dmp
 
 
# OPTION 2:
# Log into the MySQL instance as a user with the proper privileges. 
# Run the following at the mysql prompt
 
source cagwas_tbls.dmp

# ----------------------------------------------------------------------------------
# To create the cagwas database and just the table structure without the sample data
# use this dump file (cagwas_tbls_mysql.sql)
 
# OPTION 1: 
# Run the following command on the database server
# MYSQL_DIRECTORY - references where MySQL was installed
# CONF_DIRECOTRY  - references the location of where the my.cnf file is located
 
<MYSQL_DIRECTORY>/bin/mysql --defaults-file=<CONF_DIRECTORY>/my.cnf < cagwas_tbls_mysql.dmp
 
 
# OPTION 2:
# Log into the MySQL instance as a user with the proper privileges. 
# Run the following at the mysql prompt
 
source cagwas_tbls_mysql.dmp
