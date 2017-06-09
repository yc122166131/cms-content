Table	Create Table
yc_product_type	CREATE TABLE `yc_product_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(200) DEFAULT NULL,
  `parent_id` int(20) DEFAULT NULL,
  `enable_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8