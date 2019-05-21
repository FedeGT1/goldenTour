create table accomodation
(
    id             int auto_increment
        primary key,
    name           varchar(45) not null,
    address        varchar(60) not null,
    id_destination int         not null,
    id_type        int(45)     not null,
    telephone      int(20)     not null,
    constraint id
        foreign key (id_type) references accomodation_type (id),
    constraint id2
        foreign key (id_destination) references destination (Id)
)
    charset = utf8;


create table accomodation_type
(
    id   int auto_increment
        primary key,
    Name varchar(45) not null
)
    charset = utf8;


create table destination
(
    Id      int auto_increment
        primary key,
    Name    varchar(45) not null,
    Country varchar(45) not null
)
    charset = utf8;


create table packet
(
    Id              int auto_increment
        primary key,
    Description     varchar(120) not null,
    id_destination  int          not null,
    start_date      date         not null,
    end_date        date         not null,
    person_number   int          not null,
    id_transport    int          not null,
    id_accomodation int          not null,
    id_user         int          not null,
    constraint id_accomodation
        foreign key (id_accomodation) references accomodation (id),
    constraint id_destination
        foreign key (id_destination) references destination (Id),
    constraint id_transport
        foreign key (id_transport) references transport (id),
    constraint id_user
        foreign key (id_user) references user (id)
)
    charset = utf8;


create table role
(
    id        int auto_increment
        primary key,
    role_name varchar(45) not null
)
    charset = utf8;


create table transport
(
    id         int auto_increment
        primary key,
    id_booking int         not null,
    name       varchar(45) not null
)
    charset = utf8;


create table user
(
    id         int         not null
        primary key,
    username   varchar(45) not null,
    password   varchar(45) not null,
    name       varchar(45) not null,
    lastname   varchar(45) not null,
    address    varchar(45) not null,
    city       varchar(45) not null,
    birthday   datetime    not null,
    birthplace varchar(45) not null,
    cap        int         not null,
    id_role    int         not null,
    constraint id_role
        foreign key (id_role) references role (id)
)
    charset = utf8;

