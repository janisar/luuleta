package db.migration;

import java.sql.Connection;
import java.sql.Statement;

public class V201808192205__Create_User {

    public void migrate(Connection connection) throws Exception {
        try (Statement select = connection.createStatement()) {
            select.execute("DROP TABLE IF EXISTS `janis`.`user`;");

            select.executeUpdate("CREATE TABLE `janis`.`user` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `email` VARCHAR(255) NOT NULL,\n" +
                    "  `first_name` VARCHAR(255) NOT NULL,\n" +
                    "  `last_name` VARCHAR(255) NOT NULL,\n" +
                    "  `password` LONGTEXT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `email_UNIQUE` (`email` ASC));");

            select.execute("INSERT INTO `janis`.`user` (`email`, `first_name`, `last_name`, `password`) VALUES ('janis@test.com', 'Janis', 'Ardel', 'test');");
        }
    }
}
