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
