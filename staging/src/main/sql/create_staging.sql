drop table if exists identifier_mapping;
drop table if exists not_match;
drop table if exists potential_match;
drop table if exists potential_add;
drop table if exists last_run;
drop table if exists current_anu_people;
drop table if exists duplicate_records;

create table identifier_mapping (
	id	bigserial
	, aries_identifier	text
	, item_id	integer
	, handle	text
	, collection_handle	text
	, primary key (id)
	, constraint uq_identifier_mapping_1 unique (item_id, aries_identifier)
);

create table last_run (
	run_type	text
	, run_date	timestamp with time zone
	, primary key (run_type)
);

create table current_anu_people (
	id	bigserial
	, university_id	text
	, surname	text
	, given_name	text
);

create table duplicate_records (
	id bigserial
	, duplicate_id	bigserial
	, sequence_number	int
	, aries_identifier	text
	, primary key (id)
	, constraint uq_duplicate_records_1 unique (aries_identifier)
);