Please follow these steps to run the csm_patch.sql. This should fix the
new User Creation issues within the cagwas schema.

For running the script using MySQL GUI Tool ( MySQL Query Browser )
1) Login to MySQL Query Browser.
2) Select 'File - Open Script' and select the file - csm_patch.sql
3) Make sure 'cagwas' schema is selected.
4) Select 'Script - Execute' from the top menu.

For running the script from the command prompt.
1) Navigate to the Mysql directory.
2) Run -> mysql --user=root --password=secret --database=cagwas < csm_patch.sql

