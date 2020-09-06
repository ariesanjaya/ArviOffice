CREATE TABLE IF NOT EXISTS company(
    "company_id" SERIAL PRIMARY KEY,
    "name" VARCHAR(64) NOT NULL,
    "start_year" SMALLINT,
    "tax_date" DATE NOT NULL DEFAULT CURRENT_DATE,
    "tax_prefix_no" VARCHAR(48),
    "tax_id" VARCHAR(36),
    "tax_responsible_id" VARCHAR(36),
    "tax_responsible_start_date" DATE NOT NULL DEFAULT CURRENT_DATE,
  
    "version" INTEGER DEFAULT 1,
    "updated_date" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    "updated_by" VARCHAR(32),
    "deleted" BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS branch(
    "branch_id" VARCHAR(128) PRIMARY KEY,
    "company_id" INTEGER NOT NULL,
    "name" VARCHAR(48),
    "address" VARCHAR(128),
    "city" VARCHAR(48),
    "district" VARCHAR(48),
    "phone" VARCHAR(24),
    "active" BOOLEAN NOT NULL DEFAULT TRUE,
    
    "version" INTEGER DEFAULT 1,
    "created_date" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    "created_by" VARCHAR(32),
    "updated_date" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    "updated_by" VARCHAR(32),
    "deleted" BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_branch_company
        FOREIGN KEY("company_id")
        REFERENCES company("company_id") ON UPDATE CASCADE ON DELETE CASCADE
);