/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/7 12:56:05                           */
/*==============================================================*/


drop table if exists employee_role;

drop table if exists role_privilege;

drop table if exists t_dept;

drop table if exists t_employee;

drop table if exists t_leader;

drop table if exists t_org;

drop table if exists t_privilege;

drop table if exists t_role;

/*==============================================================*/
/* Table: employee_role                                         */
/*==============================================================*/
create table employee_role
(
  emp_id               varchar(32) not null,
  role_id              varchar(32) not null,
  state                int,
  primary key (emp_id, role_id)
);

/*==============================================================*/
/* Table: role_privilege                                        */
/*==============================================================*/
create table role_privilege
(
  role_id              varchar(32) not null,
  pri_id               varchar(32) not null,
  primary key (role_id, pri_id)
);

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
  dept_id              varchar(32) not null,
  org_id               varchar(32),
  name                 varchar(32),
  primary key (dept_id)
);

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
create table t_employee
(
  emp_id               varchar(32) not null,
  dept_id              varchar(32) not null,
  name                 varchar(32),
  primary key (emp_id)
);

/*==============================================================*/
/* Table: t_leader                                              */
/*==============================================================*/
create table t_leader
(
  emp_id               varchar(32) not null,
  dept_id              varchar(32),
  name                 varchar(32),
  position             int,
  primary key (emp_id)
);

/*==============================================================*/
/* Table: t_org                                                 */
/*==============================================================*/
create table t_org
(
  org_id               varchar(32) not null,
  name                 varchar(32),
  primary key (org_id)
);

/*==============================================================*/
/* Table: t_privilege                                           */
/*==============================================================*/
create table t_privilege
(
  pri_id               varchar(32) not null,
  name                 varchar(32),
  primary key (pri_id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
  role_id              varchar(32) not null,
  name                 varchar(32),
  primary key (role_id)
);

alter table employee_role add constraint FK_employee_role foreign key (emp_id)
references t_employee (emp_id) on delete restrict on update restrict;

alter table employee_role add constraint FK_employee_role2 foreign key (role_id)
references t_role (role_id) on delete restrict on update restrict;

alter table role_privilege add constraint FK_belong foreign key (role_id)
references t_role (role_id) on delete restrict on update restrict;

alter table role_privilege add constraint FK_own foreign key (pri_id)
references t_privilege (pri_id) on delete restrict on update restrict;

alter table t_dept add constraint FK_org_dept foreign key (org_id)
references t_org (org_id) on delete restrict on update restrict;

alter table t_employee add constraint FK_dept_employee foreign key (dept_id)
references t_dept (dept_id) on delete restrict on update restrict;

alter table t_leader add constraint FK_Inheritance_1 foreign key (emp_id)
references t_employee (emp_id) on delete restrict on update restrict;

