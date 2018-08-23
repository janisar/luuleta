package db.migration;

//import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

public class V201808192029__Create_Company {


    public void migrate(Connection connection) throws Exception {
        try (Statement select = connection.createStatement()) {
            select.execute("DROP TABLE IF EXISTS `janis`.`company`;");

            select.executeUpdate("CREATE TABLE `janis`.`company` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(255) NOT NULL,\n" +
                    "  `address` LONGTEXT NOT NULL,\n" +
                    "  `description` LONGTEXT NOT NULL,\n" +
                    "  `image` LONGTEXT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));");

            select.execute("INSERT INTO `janis`.`company` (`name`, `address`, `description`, `image`) VALUES ('Fathers & Sons CO', 'Sydney', 'Best IT Solutions ever', 'test');");
        }
    }
}
