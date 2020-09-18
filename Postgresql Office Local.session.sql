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
DROP TABLE IF EXISTS accounts CASCADE;
DROP TYPE IF EXISTS wealth_type;
DROP TABLE IF EXISTS general_journal CASCADE;
DROP TABLE IF EXISTS cash_receipt_journal CASCADE;
DROP TABLE IF EXISTS cash_disbursment_journal CASCADE;
DROP TABLE IF EXISTS purchase_journal CASCADE;
DROP TABLE IF EXISTS sales_journal CASCADE;
DROP TYPE IF EXISTS cash_receipt_debit_type;
DROP TYPE IF EXISTS cash_receipt_credit_type;
DROP TYPE IF EXISTS cash_disbursment_debit_type;
DROP TYPE IF EXISTS cash_disbursment_credit_type;
DROP TYPE IF EXISTS purchase_debit_type;

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
  	constraint fk_account_classification_account_types
  		foreign key(type_id) references account_types(type_id)
);

CREATE TYPE wealth_type AS ENUM(
    'real',
    'nominal'
);

CREATE TABLE IF NOT EXISTS accounts(
    account_id varchar(128) primary key,
    class_id varchar(128) not null,
    name varchar(64) not null,
    value numeric(18,4) not null,
    wealth_type wealth_type,
    balance_type SMALLINT,

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    constraint fk_accounts_account_classifications
  		foreign key(class_id) references account_clasifications(class_id)    
);

CREATE TABLE IF NOT EXISTS general_journal(
    journal_id varchar(128) PRIMARY KEY,
    journal_date date NOT NULL,
    ref_id varchar(128),
    account_id varchar(128) NOT NULL,
    description varchar(128),
    debit_credit varchar(8) NOT NULL,
    value numeric(18,4),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false
);

CREATE TYPE cash_receipt_debit_type AS ENUM(
    'balance',
    'sales_discount'
);

CREATE TYPE cash_receipt_credit_type AS ENUM(
    'account_receivable',
    'sales',
    'other'
);

CREATE TYPE cash_disbursment_debit_type AS ENUM(
    'balance',
    'purchase_discount'
);

CREATE TYPE cash_disbursment_credit_type AS ENUM(
    'account_receivable',
    'sales',
    'other'
);

-- Jurnal Penerimaan Kas
CREATE TABLE IF NOT EXISTS cash_receipt_journal(
    journal_id varchar(128) PRIMARY KEY,
    journal_date date NOT NULL,
    posted boolean DEFAULT false,
    account_id varchar(128) NOT NULL,
    description varchar(128),
    debit_credit varchar(8),
    debit_type cash_receipt_debit_type,
    credit_type cash_receipt_credit_type,
    other_ref_id varchar(128),
    other_account_id varchar(128),
    value numeric(18,4),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false
);

-- Jurnal Pengeluaran Kas
CREATE TABLE IF NOT EXISTS cash_disbursment_journal(
    journal_id varchar(128) PRIMARY KEY,
    journal_date date NOT NULL,
    posted boolean DEFAULT false,
    account_id varchar(128) NOT NULL,
    description varchar(128),
    debit_credit varchar(8),
    debit_type cash_receipt_debit_type,
    credit_type cash_receipt_credit_type,
    other_ref_id varchar(128),
    other_account_id varchar(128),
    value numeric(18,4),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false
);

CREATE TYPE purchase_debit_type AS ENUM(
    'purchase',
    'other'
);

CREATE TABLE IF NOT EXISTS purchase_journal(
    journal_id varchar(128) PRIMARY KEY,
    journal_date date NOT NULL,
    posted boolean DEFAULT false,
    account_id varchar(128) NOT NULL,
    description varchar(128),
    debit_credit varchar(8),
    debit_type purchase_debit_type,
    other_ref_id varchar(128),
    other_account_id varchar(128),
    value numeric(18,4),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false 
);

CREATE TABLE IF NOT EXISTS sales_journal(
    journal_id varchar(128) PRIMARY KEY,
    journal_date date NOT NULL,
    posted boolean DEFAULT false,
    account_id varchar(128) NOT NULL,
    description varchar(128),
    debt_range varchar(64),
    value numeric(18,4),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false 
);