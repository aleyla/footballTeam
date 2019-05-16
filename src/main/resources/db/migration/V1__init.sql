CREATE SEQUENCE seq_player INCREMENT BY 1 MINVALUE 1;

CREATE TABLE player
(
  id                  bigint primary key not null,
  name                varchar not null ,
  surname             varchar not null ,
  birthday            date,
  identity_number     varchar,
  experience_duration decimal
);

CREATE SEQUENCE seq_team INCREMENT BY 1 MINVALUE 1;

CREATE TABLE team
(
  id            bigint primary key not null,
  name          varchar unique     not null,
  currency_code varchar
);

CREATE SEQUENCE seq_contract INCREMENT BY 1 MINVALUE 1;

CREATE TABLE contract
(
  id              bigint primary key not null,
  contract_code   varchar unique     not null,
  start_date      date,
  end_date        date,
  currency_code   varchar,
  team_commission decimal(19, 2),
  contract_price  decimal(19, 2),
  transfer_fee    decimal(19, 2),
  team_id         bigint references team (id),
  player_id       bigint references player (id)
);