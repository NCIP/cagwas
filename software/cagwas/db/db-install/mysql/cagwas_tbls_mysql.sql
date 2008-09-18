# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                 cbiodb590.nci.nih.gov
# Database:             cgemstemp
# Server version:       5.0.27-standard-log
# Server OS:            pc-linux-gnu
# Target-Compatibility: MySQL 4.0
# Extended INSERTs:     Y
# max_allowed_packet:   1048576
# HeidiSQL version:     3.0 Revision: 572
# --------------------------------------------------------


/*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cagwas`;

#
# Table structure for table 'study_participant'
#

CREATE TABLE  `study_participant` (
  `participant_did` varchar(50) NOT NULL,
  `age_at_diagnosis` decimal(22,0) default NULL,
  `age_at_enroll` decimal(22,0) default NULL,
  `age_at_death` decimal(22,0) default NULL,
  `days_on_study` decimal(22,0) default NULL,
  `ethnic_group_code` varchar(50) default NULL,
  `race_code` varchar(50) default NULL,
  `gender` varchar(50) default NULL,
  `survival_status` char(1) default NULL,
  `institution_name` varchar(50) default NULL,
  `study_name` varchar(100) default NULL,
  `off_study` char(1) default NULL,
  `days_off_study` decimal(22,0) default NULL,
  `family_history` varchar(200) default NULL,
  `case_control_status` varchar(20) default NULL,
  `age_at_diagnosis_min` decimal(22,0) default NULL,
  `age_at_diagnosis_max` decimal(22,0) default NULL,
  `age_at_enroll_min` decimal(22,0) default NULL,
  `age_at_enroll_max` decimal(22,0) default NULL,
  `age_at_death_min` decimal(22,0) default NULL,
  `age_at_death_max` decimal(22,0) default NULL,
  `participant_id` decimal(22,0) NOT NULL,
  `study_id` decimal(22,0) default NULL,
  PRIMARY KEY  (`participant_id`),
  KEY `std_participant_ageenmax_idx` (`age_at_diagnosis_max`),
  KEY `std_participant_ageenmin_idx` (`age_at_enroll_min`),
  KEY `std_participant_fmhix_bidx` (`family_history`),
  KEY `std_participant_gender_bidx` (`gender`),
  KEY `std_participant_race_bidx` (`race_code`),
  KEY `std_participant_stdid_bidx` (`study_id`),
  KEY `std_participant_stdnm_bidx` (`study_name`),
  KEY `study_participant_did_idx` (`participant_did`)
);


#
# Table structure for table 'chr_start_end'
#

CREATE TABLE  `chr_start_end` (
  `chromosome` varchar(2) default NULL,
  `start_location` decimal(22,0) default NULL,
  `end_location` decimal(22,0) default NULL,
  KEY `chr_start_end_chr_idx` (`chromosome`)
);



#
# Table structure for table 'dbsnp_map_snpid'
#

CREATE TABLE  `dbsnp_map_snpid` (
  `dbsnpid` varchar(50) default NULL,
  `snpanno_id_src` decimal(22,0) default NULL,
  `snpanno_id_tar` decimal(22,0) default NULL,
  KEY `dbsnp_map_dbsnpid_idx` (`dbsnpid`),
  KEY `dbsnp_map_snpidsrc_idx` (`snpanno_id_src`),
  KEY `dbsnp_map_snpidtar_idx` (`snpanno_id_tar`)
);



#
# Table structure for table 'dbsnp_up_inter'
#

CREATE TABLE  `dbsnp_up_inter` (
  `assay_id` decimal(22,0) NOT NULL,
  `snpanno_id_tar` decimal(22,0) default NULL,
  KEY `dbsnp_up_inter_assid_idx` (`assay_id`)
);



#
# Table structure for table 'dna_specimen'
#

CREATE TABLE  `dna_specimen` (
  `dna_material_type` varchar(50) default NULL,
  `dna_extraction_method` varchar(100) default NULL,
  `dna_amplication_method` varchar(20) default NULL,
  `specimen_id` decimal(22,0) default NULL
);



#
# Table structure for table 'enroll_age_lu'
#

CREATE ALGORITHM=UNDEFINED DEFINER=`cagwas`@`%` SQL SECURITY DEFINER VIEW `enroll_age_lu` AS select distinct `study_participant`.`study_id` AS `STUDY_ID`,`study_participant`.`age_at_enroll_min` AS `AGE_AT_ENROLL_MIN`,`study_participant`.`age_at_enroll_max` AS `AGE_AT_ENROLL_MAX` from `study_participant` where (`study_participant`.`age_at_enroll_min` or (`study_participant`.`age_at_enroll_max` is not null)) order by `study_participant`.`age_at_enroll_min`;



#
# Table structure for table 'gene_alias'
#

CREATE TABLE  `gene_alias` (
  `alias` varchar(200) default NULL,
  `gene_id` varchar(20) default NULL,
  KEY `gene_alias_genealias_idx` (`alias`),
  KEY `gene_alias_geneid_idx` (`gene_id`)
);



#
# Table structure for table 'gene_dim'
#

CREATE TABLE  `gene_dim` (
  `gene_id` varchar(20) default NULL,
  `feature_name` varchar(100) default NULL,
  `chromosome` varchar(8) default NULL,
  `chromosome_start` decimal(22,0) default NULL,
  `chromosome_end` decimal(22,0) default NULL,
  `orientation` varchar(20) default NULL,
  `contig` varchar(20) default NULL,
  `contig_start` decimal(22,0) default NULL,
  `contig_end` decimal(22,0) default NULL,
  `contig_orientation` varchar(20) default NULL,
  `feature_type` varchar(20) default NULL,
  `submit_group` varchar(20) default NULL,
  `transcript` varchar(20) default NULL,
  `weight` decimal(22,0) default NULL,
  `gene_dim_id` decimal(22,0) default NULL,
  KEY `gene_dim_genenm_idx` (`feature_name`),
  KEY `gene_id_chrom_idx` (`chromosome`),
  KEY `gene_id_geneid_idx` (`gene_id`)
);



#
# Table structure for table 'gene_snp_asso'
#

CREATE TABLE  `gene_snp_asso` (
  `gene_id` varchar(20) default NULL,
  `feature_name` varchar(100) default NULL,
  `snpanno_id` decimal(22,0) default NULL,
  `dbsnpid` varchar(50) default NULL,
  `physical_location` int(9) default NULL,
  `alt_start` decimal(22,0) default NULL,
  `alt_end` decimal(22,0) default NULL,
  `start_phy` decimal(22,0) default NULL,
  `end_phy` decimal(22,0) default NULL,
  `ind` decimal(22,0) default NULL,
  `abs_distance` decimal(22,0) default NULL,
  `snpanno_id_c` varchar(50) default NULL,
  KEY `gene_snp_asso_dbsnpid_idx` (`dbsnpid`),
  KEY `gene_snp_asso_geneid_idx` (`gene_id`),
  KEY `gene_snp_asso_genenm_idx` (`feature_name`),
  KEY `gene_snp_asso_phyloc_idx` (`physical_location`),
  KEY `gene_snp_asso_snpidc_idx` (`snpanno_id_c`),
  KEY `gene_snp_asso_snpid_idx` (`snpanno_id`)
);



#
# Table structure for table 'genotype_fact'
#

CREATE TABLE  `genotype_fact` (
  `gs_id` decimal(22,0) default NULL,
  `allele1` char(1) default NULL,
  `allele2` char(1) default NULL,
  `quality_score` decimal(12,6) default NULL,
  `normal_x` decimal(12,6) default NULL,
  `normal_y` decimal(12,6) default NULL,
  `raw_x` decimal(12,6) default NULL,
  `raw_y` decimal(12,6) default NULL,
  `assay_id` decimal(22,0) default NULL,
  `scan_id` decimal(22,0) default NULL,
  `snpanno_id` decimal(22,0) default NULL,
  `status` varchar(50) default NULL,
  `specimen_id` decimal(22,0) default NULL,
  `study_name` varchar(50) default NULL,
  `study_id` decimal(22,0) default NULL,
  `specimen_id_c` varchar(50) default NULL,
  KEY `genotype_fact_snpan_spcmen_idx` (`snpanno_id`,`specimen_id`),
  KEY `genotype_fact_status_bidx` (`status`)
);



#
# Table structure for table 'genotype_fact_v'
#

CREATE ALGORITHM=UNDEFINED DEFINER=`cagwas`@`%` SQL SECURITY DEFINER VIEW `genotype_fact_v` AS select `genotype_fact`.`gs_id` AS `gs_id`,`genotype_fact`.`allele1` AS `allele1`,`genotype_fact`.`allele2` AS `allele2`,`genotype_fact`.`quality_score` AS `quality_score`,`genotype_fact`.`normal_x` AS `normal_x`,`genotype_fact`.`normal_y` AS `normal_y`,`genotype_fact`.`raw_x` AS `raw_x`,`genotype_fact`.`raw_y` AS `raw_y`,`genotype_fact`.`assay_id` AS `assay_id`,`genotype_fact`.`scan_id` AS `scan_id`,`genotype_fact`.`snpanno_id` AS `snpanno_id`,`genotype_fact`.`status` AS `status`,`genotype_fact`.`specimen_id` AS `specimen_id`,`genotype_fact`.`study_name` AS `study_name`,`genotype_fact`.`study_id` AS `study_id`,`genotype_fact`.`specimen_id_c` AS `specimen_id_c` from `genotype_fact` where (1 = 0);



#
# Table structure for table 'genotype_status_lu'
#

CREATE TABLE  `genotype_status_lu` (
  `genetype_status` varchar(20) default NULL
);



#
# Table structure for table 'group_dim'
#

CREATE TABLE  `group_dim` (
  `group_id` decimal(22,0) NOT NULL,
  `group_name` varchar(30) default NULL,
  `group_desc` varchar(200) default NULL,
  `member_count` decimal(22,0) default NULL,
  PRIMARY KEY  (`group_id`),
  KEY `group_dim_nm_idx` (`group_name`)
);



#
# Table structure for table 'histology'
#

CREATE TABLE  `histology` (
  `histology_id` decimal(22,0) NOT NULL,
  `diagnosis_name` varchar(50) default NULL,
  `grade` varchar(20) default NULL,
  `stage` varchar(20) default NULL,
  `comments` varchar(200) default NULL,
  `type` varchar(200) default NULL,
  `invasive_presentation` varchar(20) default NULL,
  `participant_id` decimal(22,0) default NULL,
  `timecourse_id` decimal(22,0) default NULL,
  PRIMARY KEY  (`histology_id`)
);



#
# Table structure for table 'odds_ratio'
#

CREATE TABLE  `odds_ratio` (
  `asso_ana_fact_id` decimal(22,0) NOT NULL,
  `heterozygote` decimal(12,6) default NULL,
  `homozygote` decimal(12,6) default NULL,
  `odds_ratio_name` varchar(30) default NULL,
  `comparision_group_id` decimal(22,0) default NULL,
  `baseline_group_id` decimal(22,0) default NULL,
  `study_id` decimal(22,0) default NULL,
  `odds_ratio_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`odds_ratio_id`),
  KEY `odds_ratio_ana_fid_idx` (`asso_ana_fact_id`),
  KEY `odds_ratio_bgrp_bidx` (`baseline_group_id`),
  KEY `odds_ratio_cgrp_bidx` (`comparision_group_id`),
  KEY `odds_ratio_name_bidx` (`odds_ratio_name`),
  KEY `odds_ratio_stdid_bidx` (`study_id`)
);



#
# Table structure for table 'snp_analysis_finding_fact'
#

CREATE TABLE  `snp_analysis_finding_fact` (
  `asso_ana_fact_id` decimal(22,0) NOT NULL,
  `asso_ana_pvalue` decimal(12,6) default NULL,
  `asso_ana_rank` decimal(22,0) default NULL,
  `snpanno_id` decimal(22,0) default NULL,
  `asso_analysis_id` decimal(22,0) default NULL,
  `study_id` decimal(22,0) default NULL,
  PRIMARY KEY  (`asso_ana_fact_id`),
  KEY `snp_ana_ff_assoanaid_bidx` (`asso_analysis_id`),
  KEY `snp_ana_ff_assopv_idx` (`asso_ana_pvalue`),
  KEY `snp_ana_ff_rank_idx` (`asso_ana_rank`),
  KEY `snp_ana_ff_snpid_idx` (`snpanno_id`),
  KEY `snp_ana_ff_stdid_bidx` (`study_id`),
  CONSTRAINT `sys_c005685` FOREIGN KEY (`asso_analysis_id`) REFERENCES `snp_association_analysis` (`asso_analysis_id`) ON DELETE SET NULL ON UPDATE NO ACTION
);



#
# Table structure for table 'snp_analysis_group'
#

CREATE TABLE  `snp_analysis_group` (
  `ana_grp_id` decimal(22,0) NOT NULL,
  `ana_grp_name` varchar(100) default NULL,
  `ana_grp_description` varchar(200) default NULL,
  `asso_analysis_id` decimal(22,0) default NULL,
  `member_count` decimal(22,0) default NULL,
  `group_id` decimal(22,0) default NULL,
  PRIMARY KEY  (`ana_grp_id`),
  KEY `snp_analysis_grpnm_idx` (`ana_grp_name`),
  KEY `sys_c005686` (`asso_analysis_id`),
  CONSTRAINT `sys_c005686` FOREIGN KEY (`asso_analysis_id`) REFERENCES `snp_association_analysis` (`asso_analysis_id`) ON DELETE SET NULL ON UPDATE NO ACTION
);



#
# Table structure for table 'snp_analysis_lu'
#

CREATE TABLE  `snp_analysis_lu` (
  `analysis_method_type` varchar(30) default NULL,
  `analysis_method_name` varchar(50) default NULL,
  `study_name` varchar(100) default NULL,
  `represent_code` varchar(3) default NULL,
  `study_id` decimal(22,0) default NULL,
  `analysis_method_desc` varchar(2000) default NULL,
  `display_order` decimal(22,0) default NULL,
  `snp_analu_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`snp_analu_id`)
);



#
# Table structure for table 'snp_assay'
#

CREATE TABLE  `snp_assay` (
  `assay_id` decimal(22,0) NOT NULL,
  `design_alleles` varchar(50) default NULL,
  `design_codes` varchar(50) default NULL,
  `design_score` decimal(8,2) default NULL,
  `design_sequence` varchar(2000) default NULL,
  `design_strand` char(5) default NULL,
  `status` varchar(50) default NULL,
  `vendor_assay_id` varchar(50) default NULL,
  `technology` varchar(50) default NULL,
  `snpanno_id` decimal(22,0) default NULL,
  `snp_panel_id` decimal(22,0) default NULL,
  `version` varchar(20) default NULL,
  PRIMARY KEY  (`assay_id`),
  KEY `snp_assay_annoid_idx` (`snpanno_id`),
  KEY `snp_assay_panelid_bidx` (`snp_panel_id`),
  CONSTRAINT `sys_c005687` FOREIGN KEY (`snp_panel_id`) REFERENCES `snp_panel` (`snp_panel_id`) ON DELETE SET NULL ON UPDATE NO ACTION
);



#
# Table structure for table 'snp_association_analysis'
#

CREATE TABLE  `snp_association_analysis` (
  `asso_analysis_id` decimal(22,0) NOT NULL,
  `asso_analysis_name` varchar(50) default NULL,
  `asso_analysis_methods` varchar(4000) default NULL,
  `asso_analysis_description` varchar(4000) default NULL,
  `study_name` varchar(100) default NULL,
  `study_id` decimal(22,0) default NULL,
  `asso_analysis_code` varchar(10) default NULL,
  `version_code` varchar(20) default NULL,
  PRIMARY KEY  (`asso_analysis_id`),
  KEY `snp_asso_analysis_accode_idx` (`asso_analysis_code`),
  KEY `snp_asso_analysis_nm_idx` (`asso_analysis_name`),
  KEY `snp_asso_analysis_stdid_idx` (`study_id`)
);



#
# Table structure for table 'snp_dim'
#

CREATE TABLE  `snp_dim` (
  `snpanno_id` decimal(22,0) NOT NULL,
  `chromosome` varchar(6) default NULL,
  `physical_location` int(9) default NULL,
  `dbsnp_build` varchar(25) default NULL,
  `dbsnpid` varchar(50) default NULL,
  `genome_build` varchar(25) default NULL,
  `reference_sequence` varchar(2000) default NULL,
  `reference_strand` char(5) default NULL,
  `snpanno_id_c` varchar(50) default NULL,
  `secondary_id` varchar(50) default NULL,
  PRIMARY KEY  (`snpanno_id`),
  KEY `snpdim_dbsnpid_idx` (`dbsnpid`),
  KEY `snpdm_chr_bidx` (`chromosome`),
  KEY `snp_dim_phyloc_idx` (`physical_location`),
  KEY `snp_dim_refstrand_bidx` (`reference_strand`)
);



#
# Table structure for table 'snp_frequency_fact'
#

CREATE TABLE  `snp_frequency_fact` (
  `snp_freq_id` bigint(12) NOT NULL,
  `hardyweinberg_p_value` decimal(12,6) default NULL,
  `heterozygote_count` int(8) default NULL,
  `missing_allele_freq` decimal(12,6) default NULL,
  `missing_allele_count` int(8) default NULL,
  `misssing_genotype_count` int(8) default NULL,
  `other_allele` char(1) default NULL,
  `other_allele_count` int(8) default NULL,
  `other_homozygote_count` int(8) default NULL,
  `reference_allele` char(1) NOT NULL,
  `reference_allele_count` int(8) default NULL,
  `reference_homozygote_count` int(8) default NULL,
  `snpanno_id` decimal(22,0) default NULL,
  `population_id` decimal(22,0) default NULL,
  `completion_rate` decimal(12,6) default NULL,
  `minor_allele_freq` decimal(12,6) default NULL,
  `study_name` varchar(100) default NULL,
  `study_id` decimal(22,0) default NULL,
  PRIMARY KEY  (`snp_freq_id`),
  KEY `snp_freq_fact_comrt_idx` (`completion_rate`),
  KEY `snp_freq_fact_hwp_idx` (`hardyweinberg_p_value`),
  KEY `snp_freq_fact_minafreq_idx` (`minor_allele_freq`),
  KEY `snp_freq_fact_otal_bidx` (`other_allele`),
  KEY `snp_freq_fact_popuid_bidx` (`population_id`),
  KEY `snp_freq_fact_rfal_bidx` (`reference_allele`),
  KEY `snp_freq_fact_snpanid_idx` (`snpanno_id`),
  KEY `snp_freq_fact_stdid_bidx` (`study_id`),
  KEY `snp_freq_fact_stdnm_bidx` (`study_name`),
  CONSTRAINT `sys_c005688` FOREIGN KEY (`population_id`) REFERENCES `study_population` (`population_id`) ON DELETE SET NULL ON UPDATE NO ACTION
);



#
# Table structure for table 'snp_map'
#

CREATE TABLE  `snp_map` (
  `snpanno_id` decimal(22,0) default NULL,
  `dbsnpid` varchar(50) default NULL,
  `map_snpid` decimal(22,0) default NULL,
  KEY `snp_map_annoid_idx` (`snpanno_id`),
  KEY `snp_map_dbsnpid_idx` (`dbsnpid`),
  KEY `snp_map_mapid_idx` (`map_snpid`)
);



#
# Table structure for table 'snp_panel'
#

CREATE TABLE  `snp_panel` (
  `snp_panel_id` decimal(22,0) NOT NULL,
  `snp_panel_name` varchar(150) default NULL,
  `technology` varchar(100) default NULL,
  `panel_description` varchar(200) default NULL,
  `vendor` varchar(50) default NULL,
  `vendor_panel_id` varchar(30) default NULL,
  `version` varchar(20) default NULL,
  `assay_count` decimal(22,0) default NULL,
  PRIMARY KEY  (`snp_panel_id`),
  KEY `snp_panel_pannm_idx` (`snp_panel_name`)
);



#
# Table structure for table 'snpid_gene_map'
#

CREATE TABLE  `snpid_gene_map` (
  `snpanno_id` decimal(22,0) default NULL,
  `gene_symbol` varchar(200) default NULL,
  KEY `snpid_gene_map_genesym_idx` (`gene_symbol`),
  KEY `snpid_gene_map_snpid_idx` (`snpanno_id`)
);



#
# Table structure for table 'specimen'
#

CREATE TABLE  `specimen` (
  `specimen_id` decimal(22,0) NOT NULL,
  `specimen_type` varchar(50) default NULL,
  `collection_method` varchar(100) default NULL,
  `participant_did` varchar(50) default NULL,
  `timecourse_name` varchar(50) default NULL,
  `disease_name` varchar(50) default NULL,
  `disease_grade` varchar(50) default NULL,
  `institution_id` decimal(22,0) default NULL,
  `timecourse_id` decimal(22,0) default NULL,
  `participant_id` decimal(22,0) default NULL,
  `specimen_id_c` varchar(50) NOT NULL,
  PRIMARY KEY  (`specimen_id`),
  KEY `specimen_id_c_idx` (`specimen_id_c`),
  KEY `specimen_participant_id_idx` (`participant_id`),
  KEY `specimen_ptdid_idx` (`participant_did`)
);



#
# Table structure for table 'specimen_v'
#

CREATE ALGORITHM=UNDEFINED DEFINER=`cagwas`@`%` SQL SECURITY DEFINER VIEW `specimen_v` AS select `specimen`.`specimen_id` AS `specimen_id`,`specimen`.`specimen_type` AS `specimen_type`,`specimen`.`collection_method` AS `collection_method`,`specimen`.`participant_did` AS `participant_did`,`specimen`.`timecourse_name` AS `timecourse_name`,`specimen`.`disease_name` AS `disease_name`,`specimen`.`disease_grade` AS `disease_grade`,`specimen`.`institution_id` AS `institution_id`,`specimen`.`timecourse_id` AS `timecourse_id`,`specimen`.`participant_id` AS `participant_id`,`specimen`.`specimen_id_c` AS `specimen_id_c` from `specimen` where (1 = 0);



#
# Table structure for table 'std_participant_population_as'
#

CREATE TABLE  `std_participant_population_as` (
  `population_id` decimal(22,0) NOT NULL,
  `participant_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`population_id`,`participant_id`)
);



#
# Table structure for table 'stdpt_analysis_grp_as'
#

CREATE TABLE  `stdpt_analysis_grp_as` (
  `ana_grp_id` decimal(22,0) NOT NULL,
  `participant_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`participant_id`,`ana_grp_id`),
  KEY `sys_c005296` (`ana_grp_id`),
  CONSTRAINT `sys_c005296` FOREIGN KEY (`ana_grp_id`) REFERENCES `snp_analysis_group` (`ana_grp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);



#
# Table structure for table 'study_dim'
#

CREATE TABLE  `study_dim` (
  `study_name` varchar(100) NOT NULL,
  `study_description` varchar(3000) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `study_sponsor` varchar(100) default NULL,
  `study_id` decimal(22,0) NOT NULL,
  `version_code` varchar(20) default NULL,
  PRIMARY KEY  (`study_id`),
  KEY `study_dim_name` (`study_name`)
);



#
# Table structure for table 'study_panel_asso'
#

CREATE TABLE  `study_panel_asso` (
  `snp_panel_id` decimal(22,0) NOT NULL,
  `study_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`study_id`,`snp_panel_id`)
);




#
# Table structure for table 'study_participant_v'
#

CREATE ALGORITHM=UNDEFINED DEFINER=`cagwas`@`%` SQL SECURITY DEFINER VIEW `study_participant_v` AS select `study_participant`.`participant_did` AS `participant_did`,`study_participant`.`age_at_diagnosis` AS `age_at_diagnosis`,`study_participant`.`age_at_enroll` AS `age_at_enroll`,`study_participant`.`age_at_death` AS `age_at_death`,`study_participant`.`days_on_study` AS `days_on_study`,`study_participant`.`ethnic_group_code` AS `ethnic_group_code`,`study_participant`.`race_code` AS `race_code`,`study_participant`.`gender` AS `gender`,`study_participant`.`survival_status` AS `survival_status`,`study_participant`.`institution_name` AS `institution_name`,`study_participant`.`study_name` AS `study_name`,`study_participant`.`off_study` AS `off_study`,`study_participant`.`days_off_study` AS `days_off_study`,`study_participant`.`family_history` AS `family_history`,`study_participant`.`case_control_status` AS `case_control_status`,`study_participant`.`age_at_diagnosis_min` AS `age_at_diagnosis_min`,`study_participant`.`age_at_diagnosis_max` AS `age_at_diagnosis_max`,`study_participant`.`age_at_enroll_min` AS `age_at_enroll_min`,`study_participant`.`age_at_enroll_max` AS `age_at_enroll_max`,`study_participant`.`age_at_death_min` AS `age_at_death_min`,`study_participant`.`age_at_death_max` AS `age_at_death_max`,`study_participant`.`participant_id` AS `participant_id`,`study_participant`.`study_id` AS `study_id` from `study_participant` where (1 = 0);



#
# Table structure for table 'study_population'
#

CREATE TABLE  `study_population` (
  `population_id` decimal(22,0) NOT NULL,
  `population_name` varchar(50) default NULL,
  `population_desc` varchar(200) default NULL,
  `source` varchar(100) default NULL,
  `member_count` decimal(22,0) default NULL,
  PRIMARY KEY  (`population_id`),
  KEY `std_pop_nm_idx` (`population_name`)
);



#
# Table structure for table 'study_stdpopuplation_asso'
#

CREATE TABLE  `study_stdpopuplation_asso` (
  `population_id` decimal(22,0) NOT NULL,
  `study_id` decimal(22,0) NOT NULL,
  PRIMARY KEY  (`study_id`,`population_id`)
);



#
# Table structure for table 'study_timecourse_dim'
#

CREATE TABLE  `study_timecourse_dim` (
  `timecourse_id` decimal(22,0) NOT NULL,
  `timecourse_name` varchar(50) default NULL,
  `timecourse_desc` varchar(200) default NULL,
  PRIMARY KEY  (`timecourse_id`)
);

