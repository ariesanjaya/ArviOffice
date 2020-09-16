-------------------------------------------------------------
---- COMPANY TABLES -----
-------------------------------------------------------------

DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS branches CASCADE;

CREATE TABLE IF NOT EXISTS company(
    company_id serial PRIMARY KEY,
    name varchar(64) NOT NULL,
    start_year smallint,
    tax_date date,
    tax_prefix_no varchar(48),
    tax_id varchar(36),
    tax_responsible_id varchar(36),
    tax_responsible_start_date date,
    
    version integer NOT NULL DEFAULT 1,
    updated_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by varchar(32),
    deleted boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS branches(
    branch_id varchar(64) PRIMARY KEY,
    company_id integer NOT NULL,
    name varchar(48),
    address varchar(256),
    city varchar(48),
    district varchar(48),
    phone varchar(24),
    active boolean NOT NULL DEFAULT true,
    
    version integer NOT NULL DEFAULT 1,
    created_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(32),
    updated_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by varchar(32),
    deleted boolean NOT NULL DEFAULT false,
    CONSTRAINT fk_branch_company FOREIGN KEY(company_id) 
        REFERENCES company(company_id) ON UPDATE CASCADE ON DELETE CASCADE 
);

-------------------------------------------------------------
---- LEDGER TABLES -----
-------------------------------------------------------------

DROP TABLE IF EXISTS kurs CASCADE;
DROP TABLE IF EXISTS account_classifications CASCADE;
DROP TABLE IF EXISTS account_types CASCADE;
DROP TABLE IF EXISTS account_types CASCADE;
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE IF NOT EXISTS kurs(
    kurs_id varchar(48) PRIMARY KEY,
    name varchar(64) NOT NULL,
    description varchar(64) NOT NULL,
    symbol char(8) NOT NULL,
    value numeric(18,4) NOT NULL DEFAULT 1,
    fiskal numeric(18,4) NOT NULL DEFAULT 1,
    is_default boolean DEFAULT false,
    
    version integer NOT NULL DEFAULT 1,
    created_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(32),
    updated_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by varchar(32),
    deleted boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS account_types(
  	type_id varchar(48) primary key,
  	name varchar(128) not null,
  
  	version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false
);

CREATE TABLE IF NOT EXISTS account_clasifications(
	class_id varchar(48) primary key,
  	type_id varchar(48) not null, 
  	name varchar(128) not null,
  
  	version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
  	constraint fk_account_types_account_classification
  		foreign key(type_id) references account_types(type_id)
);

CREATE TYPE wealth_type AS ENUM(
    'real',
    'nominal'
);

CREATE TYPE balance_type AS ENUM (
    'debit',
    'credit'
);

CREATE TABLE IF NOT EXISTS accounts(
    account_id varchar(128) primary key,
    class_id varchar(128) not null,
    name varchar(64) not null,
    value numeric(18,4) not null,
    wealth_type wealth_type not null,
    
);