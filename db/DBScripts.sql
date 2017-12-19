#Create initial schema
CREATE DATABASE IF NOT EXISTS `ancor` DEFAULT CHARACTER SET utf8;
USE `ancor`;

#Dumping structure for table concretepage.articles
CREATE TABLE IF NOT EXISTS `articles` (
  `article_id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10;

# Dumping data for table concretepage.articles: ~3 rows (approximately)
INSERT INTO `articles` (`article_id`, `title`, `category`) VALUES
  (1, 'Java Concurrency', 'Java'),
  (2, 'Hibernate HQL ', 'Hibernate'),
  (3, 'Spring MVC with Hibernate', 'Spring');