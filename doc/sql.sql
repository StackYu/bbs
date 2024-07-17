-- UserDao
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP  DEFAULT current_timestamp,
    `update_time` TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT        DEFAULT 0,
    `order`       INT        DEFAULT 0,
    `version`     INT        DEFAULT 0,
    `delete`      TINYINT(1) DEFAULT 0,

    `user_name`   VARCHAR(20) UNIQUE,
    `uid`         VARCHAR(50) UNIQUE,
    `password`    VARCHAR(50) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- AttachmentDao
CREATE TABLE IF NOT EXISTS `attachment`
(
    `id`               INT AUTO_INCREMENT,
    `create_time`      TIMESTAMP  DEFAULT current_timestamp,
    `update_time`      TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`           INT        DEFAULT 0,
    `order`            INT        DEFAULT 0,
    `version`          INT        DEFAULT 0,
    `delete`           TINYINT(1) DEFAULT 0,

    `aid`              VARCHAR(50) UNIQUE,
    `path`             VARCHAR(200),
    `type`             TINYINT,
    `originalFilename` VARCHAR(100),
    `mime`             VARCHAR(50),
    `size`             INT,
    `sizeUnit`         VARCHAR(10),
    `parent_id`        INT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- MenuDao
CREATE TABLE IF NOT EXISTS `menu`
(
    `id`            INT AUTO_INCREMENT,
    `create_time`   TIMESTAMP  DEFAULT current_timestamp,
    `update_time`   TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`        INT        DEFAULT 0,
    `order`         INT        DEFAULT 0,
    `version`       INT        DEFAULT 0,
    `delete`        TINYINT(1) DEFAULT 0,

    `menu_name`     VARCHAR(100) UNIQUE,
    `menu_url`      VARCHAR(500),
    `menu_id`       INT,
    `system_infoId` INT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- MenuMetaDao
CREATE TABLE IF NOT EXISTS `menu_meta`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP  DEFAULT current_timestamp,
    `update_time` TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT        DEFAULT 0,
    `order`       INT        DEFAULT 0,
    `version`     INT        DEFAULT 0,
    `delete`      TINYINT(1) DEFAULT 0,

    `title`       VARCHAR(100) UNIQUE,
    `icon`        VARCHAR(200),
    `no_cache`    TINYINT(1),
    `active_menu` TINYINT(1),
    `menu_id`     INT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- MenuVueDao
CREATE TABLE IF NOT EXISTS `menu_vue`
(
    `id`             INT AUTO_INCREMENT,
    `create_time`    TIMESTAMP  DEFAULT current_timestamp,
    `update_time`    TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`         INT        DEFAULT 0,
    `order`          INT        DEFAULT 0,
    `version`        INT        DEFAULT 0,
    `delete`         TINYINT(1) DEFAULT 0,

    `component_path` VARCHAR(300),
    `redirect`       VARCHAR(200),
    `always_show`    VARCHAR(200),
    `hidden`         TINYINT(1),
    `has_children`   TINYINT(1),
    `menu_id`        INT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- SystemInfoDao
CREATE TABLE IF NOT EXISTS `system_info`
(
    `id`             INT AUTO_INCREMENT,
    `create_time`    TIMESTAMP  DEFAULT current_timestamp,
    `update_time`    TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`         INT        DEFAULT 0,
    `order`          INT        DEFAULT 0,
    `version`        INT        DEFAULT 0,
    `delete`         TINYINT(1) DEFAULT 0,

    `systemId`       VARCHAR(300) UNIQUE,
    `system_name`    VARCHAR(300) UNIQUE,
    `url`            VARCHAR(300),
    `link`           VARCHAR(300),
    `system_info_id` INT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;



-- 以下旧的SQL

-- UserInfo
CREATE TABLE IF NOT EXISTS `user_info`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `nick_name`   VARCHAR(30),
    `email`       VARCHAR(100) UNIQUE,
    `phone`       VARCHAR(20) UNIQUE,
    `birthday`    TIMESTAMP,

    `user_id`     INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增UserInfo
insert into user_info (id, nick_name, email, phone, birthday, user_id)
values (1, 'fish', 'fish@mail.com', '18890900909', '2024-03-06 18:00:00', 1);

-- Role
CREATE TABLE IF NOT EXISTS `role`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `name`        VARCHAR(20) UNIQUE,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增Role
insert into role (id, name)
values (1, 'admin');

-- Group-Role
CREATE TABLE IF NOT EXISTS `group_role`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `group_id`    INT,
    `role_id`     INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增Group-Role
insert into group_role (id, group_id, role_id)
values (1, 1, 1);

-- Group
CREATE TABLE IF NOT EXISTS `group`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `name`        VARCHAR(20) UNIQUE,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增Group
insert into `group` (id, name)
values (1, 'manager');

-- User-Group
CREATE TABLE IF NOT EXISTS `user_group`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `user_id`     INT,
    `group_id`    INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增User-Group
insert into user_group (id, user_id, group_id)
values (1, 1, 1);

-- Menu
CREATE TABLE IF NOT EXISTS `menu`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP  DEFAULT current_timestamp,
    `update_time` TIMESTAMP  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT        DEFAULT 0,
    `delete`      INT        DEFAULT 0,

    `name`        VARCHAR(100),
    `path`        VARCHAR(200) NOT NULL,
    `component`   VARCHAR(200),
    `redirect`    VARCHAR(200),
    `alwaysShow`  TINYINT(1) DEFAULT NULL,
    `hidden`      TINYINT(1) DEFAULT NULL,
    `hasChildren` TINYINT(1) DEFAULT 0,

    `title`       VARCHAR(100),
    `icon`        VARCHAR(50),
    `noCache`     TINYINT(1) DEFAULT NULL,
    `activeMenu`  VARCHAR(50),

    `menu_id`     INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增Menu
insert into menu (id, path, component, redirect, alwaysShow, name, title, icon, menu_id)
values (1, '/permission', '@/layout', '/permission/page', true, 'Permission', 'Permission', 'lock', 0);
insert into menu (id, path, component, name, title, menu_id)
values (2, 'page', '@/views/permission/page', 'PagePermission', 'Page Permission', 1);
insert into menu (id, path, component, name, title, menu_id)
values (3, 'directive', '@/views/permission/directive', 'DirectivePermission', 'Directive Permission', 1);
insert into menu (id, path, component, name, title, menu_id)
values (4, 'role', '@/views/permission/role', 'RolePermission', 'Role Permission', 1);
insert into menu (id, path, component, hidden)
values (5, '/401', '@/views/error-page/401', true);

-- Role-Menu
CREATE TABLE IF NOT EXISTS `role_menu`
(
    `id`          INT AUTO_INCREMENT,
    `create_time` TIMESTAMP DEFAULT current_timestamp,
    `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
    `status`      INT       DEFAULT 0,
    `delete`      INT       DEFAULT 0,

    `role_id`     INT,
    `menu_id`     INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
-- 新增Role-Menu
insert into role_menu (id, role_id, menu_id)
values (1, 1, 1),
       (2, 1, 5);
