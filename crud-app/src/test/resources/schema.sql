create schema SUHAZA;

create table SUHAZA.VENDOR
(
  ID BIGINT generated always as identity
  constraint VENDOR_PK primary key,
  VENDOR_NAME varchar(150),
  VENDOR_ADDRESS  varchar(250),
  VENDOR_PHONE  varchar(50)
);