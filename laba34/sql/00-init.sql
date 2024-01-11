CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


create table pharmacy
(
    unique_id              bigserial    constraint pharmacy_pkey primary key,
    uuid                   uuid                     default uuid_generate_v4()    not null,
    created_timestamp      timestamp with time zone default statement_timestamp() not null,
    modified_timestamp     timestamp with time zone default statement_timestamp() not null,
    is_deleted             boolean                  default false                 not null,

    address                varchar                                                not null,
    head                   varchar                                                not null,
    district               varchar                                                not null,
    phone                  varchar                                                not null
);

create table drug
(
    unique_id              bigserial    constraint drug_pkey primary key,
    uuid                   uuid                     default uuid_generate_v4()    not null,
    created_timestamp      timestamp with time zone default statement_timestamp() not null,
    modified_timestamp     timestamp with time zone default statement_timestamp() not null,
    is_deleted             boolean                  default false                 not null,

    name                   varchar                                                not null,
    category               varchar                                                not null,
    price                  integer                                                not null,
    conditions             varchar                                                not null
);

create table link_pharmacy_drug
(
    unique_id              bigserial    constraint link_pharmacy_drug_pkey primary key,
    pharmacy_ref           bigint not null constraint link_pharmacy_drug_pharmacy_ref_fkey
        references pharmacy
        on update restrict on delete restrict,
    drug_ref          bigint not null constraint link_pharmacy_drug_drug_ref_fkey
        references drug
        on update restrict on delete restrict,
    quantity                 integer
);

create table log_event
(
    unique_id              bigserial constraint log_event_pkey primary key,
    uuid                   uuid                     default uuid_generate_v4()    not null,
    created_timestamp      timestamp with time zone default statement_timestamp() not null,
    modified_timestamp     timestamp with time zone default statement_timestamp() not null,
    is_deleted             boolean                  default false                 not null,

    event_type               varchar                                              not null,
    table_name               varchar                                              not null,
    description              varchar
);

create table mail_condition
(
    unique_id              bigserial constraint mail_condition_pkey primary key,
    uuid                   uuid                     default uuid_generate_v4()    not null,
    created_timestamp      timestamp with time zone default statement_timestamp() not null,
    modified_timestamp     timestamp with time zone default statement_timestamp() not null,
    is_deleted             boolean                  default false                 not null,

    address               varchar                                                 not null,
    condition             varchar                                                 not null
);