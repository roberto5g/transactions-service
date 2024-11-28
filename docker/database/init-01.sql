
DROP SCHEMA IF EXISTS pismo CASCADE;
CREATE SCHEMA pismo;

-- table accounts
DROP TABLE IF EXISTS pismo.accounts;
CREATE TABLE pismo.accounts (
    account_id BIGSERIAL PRIMARY KEY,
    document_number VARCHAR(11) NOT NULL
    available_credit_limit NUMERIC(15, 2) not null;
);

-- table operations_types
DROP TABLE IF EXISTS pismo.operations_types;
CREATE TABLE pismo.operations_types (
    operation_type_id BIGSERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);

-- table transactions
DROP TABLE IF EXISTS pismo.transactions;
CREATE TABLE pismo.transactions (
    transaction_id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_type_id BIGINT NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    FOREIGN KEY (account_id) REFERENCES pismo.accounts(account_id),
    FOREIGN KEY (operation_type_id) REFERENCES pismo.operations_types(operation_type_id)
);

-- insert operations_types
INSERT INTO pismo.operations_types (operation_type_id, description) VALUES
(1, 'Normal Purchase'),
(2, 'Purchase with installments'),
(3, 'Withdrawal'),
(4, 'Credit Voucher');

