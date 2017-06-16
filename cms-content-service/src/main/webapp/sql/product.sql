Table	Create Table
yc_product	CREATE TABLE `yc_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` varchar(50) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `imgPath` varchar(255) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `commentAmount` varchar(30) DEFAULT NULL,
  `companyName` varchar(250) DEFAULT NULL,
  `supCate_name` varchar(250) DEFAULT NULL,
  `subCate_name` varchar(200) DEFAULT NULL,
  `type_id` varchar(250) DEFAULT NULL,
  `brandName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8