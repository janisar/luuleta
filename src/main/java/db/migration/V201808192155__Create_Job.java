package db.migration;

//import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

public class V201808192155__Create_Job {

    public void migrate(Connection connection) throws Exception {
        try (Statement select = connection.createStatement()) {
            select.execute("DROP TABLE IF EXISTS `janis`.`job`;");

            select.executeUpdate("CREATE TABLE `janis`.`job` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `title` VARCHAR(255) NOT NULL,\n" +
                    "  `location` LONGTEXT NOT NULL,\n" +
                    "  `company_id` INT(11) NOT NULL,\n" +
                    "  `description` LONGTEXT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));");

            select.execute("ALTER TABLE `janis`.`job`\n" +
                    " ADD CONSTRAINT `fk_company`\n" +
                    " FOREIGN KEY (`company_id`)\n" +
                    " REFERENCES `janis`.`company` (`id`)\n" +
                    " ON DELETE CASCADE\n" +
                    " ON UPDATE CASCADE;");

            select.execute("INSERT INTO `janis`.`job` (`title`, `location`, `company_id`, `description`) VALUES ('Junior Java Developer', 'Sydney', '1', 'We need a good guy');");
        }
    }
}
