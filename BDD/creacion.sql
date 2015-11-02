/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/2/2015 12:01:01 PM                        */
/*==============================================================*/


drop table if exists CIUDAD;

drop table if exists DISCOTECA;

drop table if exists FIESTA;

drop table if exists MUSICA;

drop table if exists PAIS;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: CIUDAD                                                */
/*==============================================================*/
create table CIUDAD
(
   NOMBRECIUDAD         varchar(30) not null,
   NOMBREPAIS           varchar(30),
   primary key (NOMBRECIUDAD)
);

/*==============================================================*/
/* Table: DISCOTECA                                             */
/*==============================================================*/
create table DISCOTECA
(
   NOMBREDISCOTECA      varchar(30) not null,
   IDDISCOTECA          bigint not null,
   NOMBRETIPOMUSICA     varchar(20),
   NOMBRECIUDAD         varchar(30),
   EMAILUSR             varchar(50),
   DESCRIPCIONDISCOTECA varchar(300),
   PATHIMAGENDISCOTECA  varchar(300),
   primary key (IDDISCOTECA)
);

/*==============================================================*/
/* Table: FIESTA                                                */
/*==============================================================*/
create table FIESTA
(
   IDFIESTA             bigint not null,
   EMAILUSR             varchar(50),
   IDDISCOTECA          bigint,
   primary key (IDFIESTA)
);

/*==============================================================*/
/* Table: MUSICA                                                */
/*==============================================================*/
create table MUSICA
(
   NOMBRETIPOMUSICA     varchar(20) not null,
   DESCRIPCIONDISCOTECA varchar(300),
   primary key (NOMBRETIPOMUSICA)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS
(
   NOMBREPAIS           varchar(30) not null,
   primary key (NOMBREPAIS)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   EMAILUSR             varchar(50) not null,
   PASSWORDUSR          varchar(20) not null,
   NOMBREUSR            varchar(20) not null,
   APELLIDOUSR          varchar(20),
   ADMINUSR             bool,
   ESTADOUSR            bool,
   primary key (EMAILUSR)
);

alter table CIUDAD add constraint FK_CIUDADPAIS foreign key (NOMBREPAIS)
      references PAIS (NOMBREPAIS) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_DISCOTECACIUDAD foreign key (NOMBRECIUDAD)
      references CIUDAD (NOMBRECIUDAD) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_DISCOTECAMUSICA foreign key (NOMBRETIPOMUSICA)
      references MUSICA (NOMBRETIPOMUSICA) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_USUARIODISCOTECA foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

alter table FIESTA add constraint FK_DISCOTECAFIESTA foreign key (IDDISCOTECA)
      references DISCOTECA (IDDISCOTECA) on delete restrict on update restrict;

alter table FIESTA add constraint FK_USUARIOFIESTA foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

