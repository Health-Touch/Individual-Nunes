import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object Conexao {
    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                dataSource.url = "jdbc:mysql://localhost:3306/HealthTouch"
                dataSource.username = "root"
                dataSource.password = "Biel0501"
                val novoJdbcTemplate = JdbcTemplate(dataSource)
                field = novoJdbcTemplate
            }
            return field
        }

    var bdInterServer: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSoruceServer = BasicDataSource()
                dataSoruceServer.url = "jdbc:sqlserver://54.145.218.19;databaseName=HealthTouch;encrypt=false";
                dataSoruceServer.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                dataSoruceServer.username = "sa"
                dataSoruceServer.password = "urubu100"
                bdInterServer = JdbcTemplate(dataSoruceServer)
            }
            return field
        }
    }
