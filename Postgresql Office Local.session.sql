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
    branch_id serial PRIMARY KEY,
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

-------------------------------------------------------------
---- INVENTORY TABLES -----
-------------------------------------------------------------

DROP TABLE IF EXISTS warehouses CASCADE;
DROP TABLE IF EXISTS item_groups CASCADE;
DROP TABLE IF EXISTS item_brands CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS item_branches CASCADE;
DROP TABLE IF EXISTS item_units CASCADE;


CREATE TABLE IF NOT EXISTS warehouses(
    warehouse_id serial PRIMARY KEY,
    name varchar(128) NOT NULL,
    active boolean DEFAULT true,

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false
);

CREATE TABLE IF NOT EXISTS item_groups(
    group_id serial PRIMARY KEY,
    parent_id integer,
    name varchar(64) NOT NULL,
    active boolean DEFAULT true,

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    CONSTRAINT fk_item_groups_parent 
        FOREIGN KEY(parent_id) REFERENCES item_groups(group_id)
);

CREATE TABLE IF NOT EXISTS item_brands(
    brand_id serial PRIMARY KEY,
    parent_id integer,
    name varchar(64) NOT NULL,
    active boolean DEFAULT true,

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    CONSTRAINT fk_item_brands_parent 
        FOREIGN KEY(parent_id) REFERENCES item_brands(brand_id)
);

CREATE TABLE IF NOT EXISTS items(
    item_id bigserial PRIMARY KEY,
    name varchar(128) NOT NULL,
    alias_name varchar(128),
    description varchar(128),
    item_type varchar(24) NOT NULL,
    unit_string varchar(36),
    group_id integer,
    brand_id integer,
    active boolean DEFAULT true,

    purchase_unit varchar(8) NOT NULL,
    sales_unit varchar(8) NOT NULL,

    supplier_id varchar(128),
    supplier_barcode varchar(24),

    stock_id varchar(128),
    base_price_id varchar(128),
    sales_id varchar(128),
    sales_return_id varchar(128),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    CONSTRAINT fk_items_item_groups 
        FOREIGN KEY(group_id) REFERENCES item_groups(group_id) ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_items_item_brands 
        FOREIGN KEY(brand_id) REFERENCES item_brands(brand_id) ON UPDATE CASCADE ON DELETE SET NULL 

);

CREATE TABLE IF NOT EXISTS item_branches(
    item_id bigint,
    branch_id integer,
    
    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    
    CONSTRAINT fk_item_branches_items FOREIGN KEY(item_id) 
        REFERENCES items(item_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_item_branches_branches FOREIGN KEY(branch_id) 
        REFERENCES branches(branch_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS item_units(
    unit_id serial PRIMARY KEY,
    item_id integer NOT NULL,
    barcode varchar(48),
    name varchar(8),
    index smallint NOT NULL DEFAULT 0,
    value double precision, 
    
    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    
    CONSTRAINT fk_item_units_items FOREIGN KEY(item_id) 
        REFERENCES items(item_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS price_level(
    level_id serial PRIMARY KEY,
    kurs_id varchar(128),
    name varchar(64) NOT NULL,
    active boolean DEFAULT true,

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false,
    
    CONSTRAINT fk_price_level_kurs FOREIGN KEY(kurs_id) 
        REFERENCES kurs(kurs_id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS item_unit_prices(
    unit_id integer PRIMARY KEY,
    warehouse_id integer NOT NULL,
    level_id varchar(128),

    version integer not null default 1,
  	created_date timestamptz not null default CURRENT_TIMESTAMP,
  	created_by varchar(32),
  	updated_date timestamptz not null default CURRENT_TIMESTAMP,
  	updated_by varchar(32),
  	deleted boolean not null default false    
);