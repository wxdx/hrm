create table hrm_user
(
    id           varchar(32)              not null
        primary key,
    userId       varchar(32)              not null,
    loginName    varchar(16)              not null,
    nickName     varchar(16) charset utf8 null,
    password     varchar(36)              not null,
    email        varchar(64)              not null,
    registerTime datetime                 not null,
    updateTime   datetime                 null,
    status       varchar(2) default '1'   null
);

