load data infile 'chr_start_end.csv'
into table chr_start_end
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'dbsnp_map_snpid.csv'
into table dbsnp_map_snpid
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'dbsnp_up_inter.csv'
into table dbsnp_up_inter
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'dna_specimen.csv'
into table dna_specimen
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'gene_alias.csv'
into table gene_alias
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'gene_dim.csv'
into table gene_dim
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'gene_snp_asso.csv'
into table gene_snp_asso
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'genotype_status_lu.csv'
into table genotype_status_lu
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'group_dim.csv'
into table group_dim
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'odds_ratio.csv'
into table odds_ratio
fields terminated by ','
enclosed by '"'
lines terminated by '\r';

load data infile 'snp_association_analysis.csv'
into table snp_association_analysis
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_analysis_finding_fact.csv'
into table snp_analysis_finding_fact
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_analysis_group.csv'
into table snp_analysis_group
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_analysis_lu.csv'
into table snp_analysis_lu
fields terminated by ','
enclosed by '"'
lines terminated by '\r';

load data infile 'snp_panel.csv'
into table snp_panel
fields terminated by ','
enclosed by '"'
lines terminated by '\r';

load data infile 'snp_assay.csv'
into table snp_assay
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_dim.csv'
into table snp_dim
fields terminated by ','
enclosed by '"'
lines terminated by '\r';

load data infile 'study_population.csv'
into table study_population
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_frequency_fact.csv'
into table snp_frequency_fact
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snp_map.csv'
into table snp_map
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'snpid_gene_map.csv'
into table snpid_gene_map
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'study_dim.csv'
into table study_dim
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'study_panel_asso.csv'
into table study_panel_asso
fields terminated by ','
enclosed by '"'
lines terminated by '\r';


load data infile 'study_stdpopuplation_asso.csv'
into table study_stdpopuplation_asso
fields terminated by ','
enclosed by '"'
lines terminated by '\r';