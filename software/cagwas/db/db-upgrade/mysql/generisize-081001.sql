/*L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L*/

alter table odds_ratio modify column `heterozygote` decimal(25,12) default NULL;
alter table odds_ratio modify column`homozygote` decimal(12,6) default NULL;


alter table odds_ratio add column(
	`heterozygote_lower_cb` decimal(12,6) default NULL,
	`heterozygote_upper_cb` decimal(12,6) default NULL,
	`heterozygote_std_err` decimal(12,6) default NULL,
	`homozygote_lower_cb` decimal(12,6) default NULL,
	`homozygote_upper_cb` decimal(12,6) default NULL,
	`homozygote_std_err` decimal(12,6) default NULL
);

alter table snp_analysis_lu modify column  `analysis_method_desc` varchar(1000) default NULL;
alter table snp_analysis_lu modify column  `analysis_method_name` varchar(100) default NULL;

alter table snp_analysis_lu add column(
	`def_option_order` decimal(6,3) default NULL
);


alter table snp_association_analysis modify column  `asso_analysis_code` varchar(20) default NULL;
alter table snp_association_analysis modify column  `asso_analysis_name` varchar(500) default NULL;

alter table snp_association_analysis add column(
	`number_of_categories` decimal(22,0) default NULL
);


