CREATE DATABASE ordering;
use ordering;

-- 用户表
CREATE TABLE t_user (
  u_id            char(10)      NOT NULL,  -- 用户ID
  u_name          varchar(20)   NOT NULL,  -- 名字
  u_phone_number  char(11)      NOT NULL,  -- 手机号
  u_password      char(32)      NOT NULL,  -- 密码哈希
  u_identity      int           NOT NULL,  -- 身份
  u_balance       double(7,2)   NOT NULL,  -- 余额
  PRIMARY KEY (u_id)
) CHARSET=utf8;


-- 餐品表
CREATE TABLE t_food (
  f_id            char(10)        NOT NULL,  -- 餐品ID
  f_name          varchar(60)     NOT NULL,  -- 餐品名
  f_image_url     varchar(128)    NOT NULL,  -- 餐品图片路径
  f_description   varchar(1024)   NOT NULL,  -- 餐品描述
  f_price         double(7,2)     NOT NULL,  -- 餐品价格
  k_id            int             NOT NULL,  -- 餐品种类
  PRIMARY KEY (f_id)
) CHARSET=utf8;

-- 餐品种类表
CREATE TABLE t_kind (
  k_id			int               NOT NULL,  -- 种类ID
  k_type        varchar(20)       NOT NULL,  -- 餐品种类
  PRIMARY KEY (k_id)
  ) CHARSET=utf8;

-- 订单表
create table t_order (
	o_id             char(12)     NOT NULL,  -- 订单ID
	u_id             char(10)     NOT NULL,  -- 用户ID
	o_start_time     LONG         NOT NULL,  -- 点单时间戳
	o_finish_time    LONG                 ,  -- 完成时间戳
	o_table_number   INT          NOT NULL,  -- 桌号
	o_status         INT          NOT NULL,  -- 订单状态
	PRIMARY KEY (o_id)
) CHARSET=utf8;

-- 订单详情表
create table t_order_detail (
	o_id             char(12)     NOT NULL,  -- 订单ID
	f_id             char(10)     NOT NULL,  -- 餐品ID
	food_count       INT          NOT NULL,  -- 餐品个数
	PRIMARY KEY(o_id, f_id)
) CHARSET=utf8;

-- select unix_timestamp(now()); -- 当前时间戳

-- 序列表（生成ID）
create table t_sequence (         
	seq_name        VARCHAR(50) NOT NULL, -- 序列名称         
	current_val     long        NOT NULL, -- 当前值         
	increment_val   long        NOT NULL, -- 步长(跨度)         
	PRIMARY KEY (seq_name)   
)CHARSET=utf8;
insert into t_sequence values('seq_food_id',0000000000,1);
insert into t_sequence values('seq_user_id',9960000000,1);
insert into t_sequence values('seq_order_id',202010160000,1);

-- 购物车表
create table t_order_cart( 
	u_id             char(10)     NOT NULL,  -- 用户ID
	f_id             char(10)     NOT NULL,  -- 餐品ID
	food_count       INT          NOT NULL,  -- 餐品个数
    PRIMARY KEY(u_id, f_id)
)CHARSET=utf8;

-- 创建获取用户ID的函数
DELIMITER $$
CREATE FUNCTION getUserId(s_name varchar(50)) 
RETURNS char(10)
DETERMINISTIC
BEGIN
  DECLARE userId char(10) DEFAULT '';
  SET @seqName = s_name;
  select current_val from t_sequence where seq_name = @seqName into userId;
  update t_sequence set current_val = current_val + increment_val where seq_name = @seqName;
  return userId;
END $$
DELIMITER ;

-- 创建获取菜品ID的函数
DELIMITER $$
CREATE FUNCTION getFoodId(s_name varchar(50)) 
RETURNS char(10)
DETERMINISTIC
BEGIN
  DECLARE foodId char(10) DEFAULT '';
  SET @seqName = s_name;
  select current_val from t_sequence where seq_name = @seqName into foodId;
  update t_sequence set current_val = current_val + increment_val where seq_name = @seqName;
  return foodId;
END $$
DELIMITER ;

-- 创建获取订单ID的函数
DELIMITER $$
CREATE FUNCTION getOrderId(s_name varchar(50)) 
RETURNS char(12)
DETERMINISTIC
BEGIN
  DECLARE orderId char(12) DEFAULT '';
  declare nowtime char(8) DEFAULT '';
  declare nowOederId char(12) default '';
  SET @seqName = s_name;
  select current_val from t_sequence where seq_name = @seqName into nowOederId;
  select date_format(now(), '%Y%m%d') into nowtime;
  if (select left(nowOederId,8)) = nowtime then
	  select current_val from t_sequence where seq_name = @seqName into orderId;
	  update t_sequence set current_val = current_val + increment_val where seq_name = @seqName;
  else 
	  SELECT CAST(CONCAT(nowtime,'0000') AS SIGNED) into orderId; 
	  update t_sequence set current_val = (SELECT CAST(orderId AS SIGNED)) + increment_val where seq_name = @seqName;
  end if;
  return orderId;
END $$
DELIMITER ;

insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('1', '脆皮鸡腿', 'image/1.jpg', '脆皮鸡腿', 50, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('2', '巧克力蛋糕', 'image/2.jpg', '巧克力蛋糕', 99, 4);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('3', '肥牛卷', 'image/3.jpg', '肥牛卷', 99, 2);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('4', '马卡龙', 'image/4.jpg', '马卡龙', 99, 3);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('5', '黑椒牛排', 'image/5.jpg', '黑椒牛排', 99, 5);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('6', '杭州小笼包', 'image/6.jpg', '杭州小笼包', 99, 6);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('7', '酸汤馄饨', 'image/7.jpg', '酸汤馄饨', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('8', '蜜汁烤全鸡', 'image/8.jpg', '蜜汁烤全鸡', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('9', '番茄蛋塔', 'image/9.jpg', '番茄蛋塔', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('10', '双蛋烤肉', 'image/10.jpg', '双蛋烤肉', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('11', '香菇炖乌鸡', 'image/11.jpg', '香菇炖乌鸡', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('12', '蓝莓蛋糕', 'image/12.jpg', '蓝莓蛋糕', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('13', '重庆火锅', 'image/13.jpg', '重庆火锅', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('14', '手工水饺', 'image/14.jpg', '手工水饺', 99, 6);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('15', '四川涮串', 'image/15.jpg', '四川涮串', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('16', '草莓蛋糕', 'image/16.jpg', '草莓蛋糕', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('17', '牛丸意面', 'image/17.jpg', '牛丸意面', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('18', '汤圆', 'image/18.jpg', '汤圆', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('19', '桂花茶', 'image/19.jpg', '桂花茶', 99, 5);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('20', '可乐鸡翅', 'image/20.jpg', '可乐鸡翅', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('21', '酱香茄子', 'image/21.jpg', '酱香茄子', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('22', '烤生蚝', 'image/22.jpg', '烤生蚝', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('23', '玉米炖鸡腿', 'image/23.jpg', '玉米炖鸡腿', 99, 1);
insert into t_food(f_id, f_name, f_image_url, f_description, f_price, k_id) values('24', '惠灵顿牛排', 'image/24.jpg', '惠灵顿牛排', 99, 1);


insert into t_kind(k_id, k_type) values(1, '炒菜');
insert into t_kind(k_id, k_type) values(2, '凉菜');
insert into t_kind(k_id, k_type) values(3, '汤类');
insert into t_kind(k_id, k_type) values(4, '甜品');
insert into t_kind(k_id, k_type) values(5, '酒水');
insert into t_kind(k_id, k_type) values(6, '主食');