CREATE TABLE IF NOT EXISTS company(
    "company_id" SERIAL PRIMARY KEY,
    "name" VARCHAR(64) NOT NULL,
    "start_year" SMALLINT CHECK ("start_year" > 0),
    "tax_date" DATE NOT NULL,
    "tax_prefix_no" VARCHAR(48),
    "tax_id" VARCHAR(36),
    "tax_responsible_id" VARCHAR(36),
    "tax_responsible_start_date" DATE NOT NULL,

    "version" INTEGER NOT NULL DEFAULT 1,
    "updated_date" TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    "updated_by" VARCHAR(32),
    "deleted" BOOLEAN NOT NULL DEFAULT FALSE 
);

CREATE TABLE IF NOT EXISTS branch(
    "branch_id" VARCHAR(128) PRIMARY KEY,
    "company_id" INTEGER,
    "name" VARCHAR(48),
    "address" VARCHAR(128),
    "city" VARCHAR(48),
    "district" VARCHAR(48),
    "phone" VARCHAR(24),
    "active" BOOLEAN NOT NULL DEFAULT TRUE,
  
    "version" INTEGER NOT NULL DEFAULT 1,
    "updated_date" TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    "updated_by" VARCHAR(32),
    "deleted" BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_branch_company
        FOREIGN KEY(company_id)
            REFERENCES company(company_id)
);

