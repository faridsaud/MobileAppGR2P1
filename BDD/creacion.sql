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
   IDCIUDAD             int AUTO_INCREMENT,
   IDPAIS               int,
   NOMBRECIUDAD         varchar(30) not null,
   primary key (IDCIUDAD)
);

/*==============================================================*/
/* Table: DISCOTECA                                             */
/*==============================================================*/
create table DISCOTECA
(
   IDDISCOTECA          bigint AUTO_INCREMENT,
   NOMBREDISCOTECA      varchar(30) not null,
   NOMBRETIPOMUSICA     varchar(20),
   IDCIUDAD    		int,
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
   IDFIESTA             bigint AUTO_INCREMENT,
   NOMBREFIESTA		varchar(50),
   EMAILUSR             varchar(50),
   IDDISCOTECA          bigint,
   FECHAFIESTA          date not null,
   HORAFIESTA           time not null,
   DESCRIPCIONFIESTA    char(150) not null,
   primary key (IDFIESTA)
);

/*==============================================================*/
/* Table: MUSICA                                                */
/*==============================================================*/
create table MUSICA
(
   NOMBRETIPOMUSICA     varchar(20) not null,
   DESCRIPCIONMUSICA    varchar(300),
   primary key (NOMBRETIPOMUSICA)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS
(
   IDPAIS               int AUTO_INCREMENT,
   NOMBREPAIS           varchar(30) not null,
   primary key (IDPAIS)
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

alter table CIUDAD add constraint FK_CIUDADPAIS foreign key (IDPAIS)
      references PAIS (IDPAIS) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_DISCOTECACIUDAD foreign key (IDCIUDAD)
      references CIUDAD (IDCIUDAD) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_DISCOTECAMUSICA foreign key (NOMBRETIPOMUSICA)
      references MUSICA (NOMBRETIPOMUSICA) on delete restrict on update restrict;

alter table DISCOTECA add constraint FK_USUARIODISCOTECA foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

alter table FIESTA add constraint FK_DISCOTECAFIESTA foreign key (IDDISCOTECA)
      references DISCOTECA (IDDISCOTECA) on delete restrict on update restrict;

alter table FIESTA add constraint FK_USUARIOFIESTA foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

