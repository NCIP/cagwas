/*L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L*/

﻿alter table csm_user_pe drop foreign key fk_user_pe_pe
;

alter table csm_user_pe drop foreign key fk_user_pe_user
;

alter table csm_user_group_role_pg drop foreign key fk_user_group_role_pg_group
;

alter table csm_user_group_role_pg drop foreign key fk_user_group_role_pg_pg
;

alter table csm_user_group_role_pg drop foreign key fk_user_group_role_pg_role
;

alter table csm_user_group_role_pg drop foreign key fk_user_group_role_pg_user
;

alter table csm_user_group drop foreign key fk_user_group_group
;

alter table csm_user_group drop foreign key fk_user_group_user
; 

alter table csm_role drop foreign key fk_role_application
;

alter table csm_role_privilege drop foreign key fk_role_privilege_privilege
;

alter table csm_role_privilege drop foreign key fk_role_privilege_role
;

alter table csm_protection_group drop foreign key fk_pg_application
;

alter table csm_protection_group drop foreign key fk_pg_pg
;

alter table csm_protection_element drop foreign key fk_pe_application
;

alter table csm_pg_pe drop foreign key fk_pg_pe_pe
;

alter table csm_pg_pe drop foreign key fk_pg_pe_pg
;

alter table csm_group drop foreign key fk_csm_group_csm_application
;

alter table csm_application
	modify application_id bigint auto_increment  not null,
	modify application_name varchar(255) not null,
	modify application_description varchar(200) not null,
	modify declarative_flag bool not null default 0,
	modify active_flag bool not null default 0,
	modify update_date date not null default '1950-01-01',
	modify database_url varchar(100),
	modify database_user_name varchar(100),
	modify database_password varchar(100),
	modify database_dialect varchar(100),
	modify database_driver varchar(100)
;

alter table csm_group 
	modify group_id bigint auto_increment  not null,
	modify group_name varchar(255) not null,
	modify group_desc varchar(200),
	modify update_date date not null default '1950-01-01',
	modify application_id bigint not null
;

alter table csm_pg_pe 
	modify pg_pe_id  bigint auto_increment not null,
	modify protection_group_id  bigint not null,
	modify protection_element_id  bigint not null
;

alter table csm_privilege 
	modify privilege_id bigint auto_increment  not null,
	modify privilege_name varchar(100) not null,
	modify privilege_description varchar(200),
	modify update_date date not null default '1950-01-01'
;

alter table csm_protection_element
	modify protection_element_id bigint auto_increment  not null,
	modify protection_element_name varchar(100) not null,
	modify protection_element_description varchar(200),
	modify object_id varchar(100) not null,
	modify attribute varchar(100) ,
	modify application_id bigint not null,
	modify update_date date not null default '1950-01-01',
	modify protection_element_type varchar(100)
;

alter table csm_protection_group 
	modify protection_group_id bigint auto_increment  not null,
	modify protection_group_name varchar(100) not null,
	modify protection_group_description varchar(200),
	modify application_id bigint not null,
	modify large_element_count_flag bool not null,
	modify update_date date not null default '1950-01-01',
	modify parent_protection_group_id bigint
;

alter table csm_pg_pe 
	modify pg_pe_id bigint auto_increment  not null,
	modify protection_group_id bigint not null,
	modify protection_element_id bigint not null,
	modify update_date date not null default '1950-01-01'
;

alter table csm_role 
	modify role_id bigint auto_increment  not null,
	modify role_name varchar(100) not null,
	modify role_description varchar(200),
	modify application_id bigint not null,
	modify active_flag bool not null,
	modify update_date date not null default '1950-01-01'
;

alter table csm_role_privilege 
	modify role_privilege_id bigint auto_increment  not null,
	modify role_id bigint not null,
	modify privilege_id bigint not null,
	modify update_date date not null default '1950-01-01'
;

alter table csm_user
	modify user_id bigint auto_increment  not null,
	modify login_name varchar(100) not null,
	modify first_name varchar(100) not null,
	modify last_name varchar(100) not null,
	modify organization varchar(100),
	modify department varchar(100),
	modify title varchar(100),
	modify phone_number varchar(15),
	modify password varchar(100),
	modify email_id varchar(100),
	modify start_date date,
	modify end_date date,
	modify update_date date not null default '1950-01-01'
;

alter table csm_user_group  
	modify user_group_id bigint auto_increment  not null,
	modify user_id bigint not null,
	modify group_id bigint not null
;

alter table csm_user_group_role_pg  
	modify user_group_role_pg_id bigint auto_increment  not null,
	modify user_id bigint,
	modify group_id bigint,
	modify role_id bigint not null,
	modify protection_group_id bigint not null,
	modify update_date date not null default '1950-01-01'
;

alter table csm_user_pe  
	modify user_protection_element_id bigint auto_increment  not null,
	modify protection_element_id bigint not null,
	modify user_id bigint not null,
	modify update_date date not null default '1950-01-01'
;
 
alter table csm_group add constraint fk_csm_group_csm_application 
foreign key (application_id) references csm_application (application_id)
on delete cascade
;

alter table csm_protection_element add constraint fk_pe_application 
foreign key (application_id) references csm_application (application_id)
on delete cascade
;
 
alter table csm_protection_group add constraint fk_pg_application 
foreign key (application_id) references csm_application (application_id)
on delete cascade
;
 
alter table csm_protection_group add constraint fk_pg_pg 
foreign key (parent_protection_group_id) references csm_protection_group (protection_group_id)
; 

alter table csm_pg_pe add constraint fk_pg_pe_pg 
foreign key (protection_element_id) references csm_protection_element (protection_element_id)
on delete cascade
;

alter table csm_pg_pe add constraint fk_pg_pe_pe 
foreign key (protection_group_id) references csm_protection_group (protection_group_id)
on delete cascade
;
 
alter table csm_role add constraint fk_role_application 
foreign key (application_id) references csm_application (application_id)
on delete cascade
; 

alter table csm_role_privilege add constraint fk_role_privilege_privilege 
foreign key (privilege_id) references csm_privilege (privilege_id)
on delete cascade
; 

alter table csm_role_privilege add constraint fk_role_privilege_role 
foreign key (role_id) references csm_role (role_id)
on delete cascade
;

alter table csm_user_group add constraint fk_user_group_user 
foreign key (user_id) references csm_user (user_id)
on delete cascade
;
 
alter table csm_user_group add constraint fk_user_group_group 
foreign key (group_id) references csm_group (group_id)
on delete cascade
;

alter table csm_user_group_role_pg add constraint fk_user_group_role_pg_group 
foreign key (group_id) references csm_group (group_id)
on delete cascade
; 

alter table csm_user_group_role_pg add constraint fk_user_group_role_pg_role 
foreign key (role_id) references csm_role (role_id)
on delete cascade
;

alter table csm_user_group_role_pg add constraint fk_user_group_role_pg_pg 
foreign key (protection_group_id) references csm_protection_group (protection_group_id)
on delete cascade
;

alter table csm_user_group_role_pg add constraint fk_user_group_role_pg_user
foreign key (user_id) references csm_user (user_id)
on delete cascade
;
 
alter table csm_user_pe add constraint fk_user_pe_user 
foreign key (user_id) references csm_user (user_id)
on delete cascade
;

alter table csm_user_pe add constraint fk_user_pe_pe 
foreign key (protection_element_id) references csm_protection_element (protection_element_id)
on delete cascade
;
