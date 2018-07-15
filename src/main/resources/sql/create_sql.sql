/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/14 22:48:35                           */
/*==============================================================*/


drop table if exists fr_dept;

drop table if exists fr_log;

drop table if exists fr_menu;

drop table if exists fr_menu_privilege;

drop table if exists fr_resource;

drop table if exists fr_role;

drop table if exists fr_role_menu;

drop table if exists fr_role_resource;

drop table if exists fr_user;

drop table if exists fr_user_dept;

drop table if exists fr_user_role;

/*==============================================================*/
/* Table: fr_dept                                               */
/*==============================================================*/
create table fr_dept
(
   id                   int not null auto_increment,
   name                 varchar(100),
   node_no              varchar(200),
   parent_node_no       varchar(200),
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   order_no             int,
   primary key (id)
);

/*==============================================================*/
/* Table: fr_log                                                */
/*==============================================================*/
create table fr_log
(
   id                   int not null auto_increment,
   module_id            int,
   module_name          varchar(200),
   user_id              int,
   user_name            varchar(200),
   content              varchar(500),
   parameters           varchar(500),
   create_date          datetime,
   ip_address           varchar(15),
   mac_address          varchar(100),
   remark               varchar(500),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_menu                                               */
/*==============================================================*/
create table fr_menu
(
   id                   int not null auto_increment,
   node_no              varchar(100),
   name                 varchar(200),
   url                  varchar(200),
   icon                 varchar(200) comment '存储图片的URL地址',
   parent_id            int,
   order_no             int,
   is_short_cut         int comment '1 - 是；2 - 否',
   short_cut_name       varchar(200),
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_menu_privilege                                     */
/*==============================================================*/
create table fr_menu_privilege
(
   id                   int not null auto_increment,
   menu_id              int,
   name                 varchar(200) comment '界面显示文字',
   node_no              varchar(200) comment '字符串，同一菜单下不能重复',
   url                  varchar(200),
   value                int default 0 comment '默认值 - 0；0 - 没有该权限；其他 - 拥有该权限',
   type                 int comment '1 - 后台；2 - 钉钉',
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_resource                                           */
/*==============================================================*/
create table fr_resource
(
   id                   int not null auto_increment,
   url                  varchar(500),
   name                 varchar(500),
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_role                                               */
/*==============================================================*/
create table fr_role
(
   id                   int not null auto_increment,
   node_no              varchar(200),
   name                 varchar(200),
   description          varchar(300),
   is_initial           int comment '1 - 系统初始化；1 - 以上表示用户创建',
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_role_menu                                          */
/*==============================================================*/
create table fr_role_menu
(
   id                   int not null auto_increment,
   role_id              int,
   menu_id              int,
   primary key (id)
);

/*==============================================================*/
/* Table: fr_role_resource                                      */
/*==============================================================*/
create table fr_role_resource
(
   id                   int not null,
   role_id              int,
   resource_id          int,
   primary key (id)
);

/*==============================================================*/
/* Table: fr_user                                               */
/*==============================================================*/
create table fr_user
(
   id                   int not null auto_increment,
   login_name           varchar(100) comment '登录时使用的名称',
   user_name            varchar(200),
   password             varchar(100),
   head_img_url         varchar(200),
   gender               int comment '1 - 男；2 - 女',
   phone_number         varchar(32),
   email                varchar(200),
   status               int comment '0 - 禁用；1 - 启用',
   type                 int comment '1 - 系统管理员；2 - 普通用户',
   bind_type            int comment '1 - 不绑定；2 - ip；3 - mac',
   ip_or_mac            char(10),
   item1                varchar(200),
   item2                varchar(200),
   item3                varchar(200),
   item4                varchar(200),
   item5                varchar(200),
   create_user          int,
   create_user_name     varchar(200),
   create_date          datetime,
   update_user          int,
   update_user_name     varchar(200),
   update_date          datetime,
   remark               varchar(300),
   primary key (id)
);

/*==============================================================*/
/* Table: fr_user_dept                                          */
/*==============================================================*/
create table fr_user_dept
(
   id                   int not null auto_increment,
   user_id              int,
   dept_id              int,
   primary key (id)
);

/*==============================================================*/
/* Table: fr_user_role                                          */
/*==============================================================*/
create table fr_user_role
(
   id                   int not null auto_increment,
   user_id              int,
   role_id              int,
   primary key (id)
);

alter table fr_menu_privilege add constraint FK_FK_RBAC_07 foreign key (menu_id)
      references fr_menu (id) on delete restrict on update restrict;

alter table fr_role_menu add constraint FK_FK_RBAC_05 foreign key (menu_id)
      references fr_menu (id) on delete restrict on update restrict;

alter table fr_role_menu add constraint FK_FK_RBAC_06 foreign key (role_id)
      references fr_role (id) on delete restrict on update restrict;

alter table fr_role_resource add constraint FK_FK_RBAC_08 foreign key (resource_id)
      references fr_resource (id) on delete restrict on update restrict;

alter table fr_role_resource add constraint FK_FK_RBAC_09 foreign key (role_id)
      references fr_role (id) on delete restrict on update restrict;

alter table fr_user_dept add constraint FK_FK_RBAC_01 foreign key (dept_id)
      references fr_dept (id) on delete restrict on update restrict;

alter table fr_user_dept add constraint FK_FK_RBAC_02 foreign key (user_id)
      references fr_user (id) on delete restrict on update restrict;

alter table fr_user_role add constraint FK_FK_RBAC_03 foreign key (role_id)
      references fr_role (id) on delete restrict on update restrict;

alter table fr_user_role add constraint FK_FK_RBAC_04 foreign key (user_id)
      references fr_user (id) on delete restrict on update restrict;

