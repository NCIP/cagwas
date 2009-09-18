# This mysqldump file was generated from a MySQL instance running on version 5.0.27
# To create the cgemstemp database from this dump file (mysql_cgemstemp), 
# Run either of the following commands
 
# OPTION 1: 
# Run the following command on the database server
# MYSQL_DIRECTORY - references where MySQL was installed
# CONF_DIRECOTRY  - references the location of where the my.cnf file is located
 
<MYSQL_DIRECTORY>/bin/mysql --defaults-file=<CONF_DIRECTORY>/my.cnf < mysql_cgemstemp.dmp
 
 
# OPTION 2:
# Log into the MySQL instance as a user with the proper privileges. 
# Run the following at the mysql prompt
 
source mysql_cgemstemp.dmp
