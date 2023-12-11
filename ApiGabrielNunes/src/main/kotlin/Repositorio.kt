import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate


class Repositorio {
    val maquinas = Maquina()
    lateinit var jdbcTemplate: JdbcTemplate
    var bdInterServer = Conexao.bdInterServer!!

    fun iniciarConexao(){
        jdbcTemplate = Conexao.jdbcTemplate!!

    }

    fun VerificarColaborador (email: String, senha: String) : Int?{
        var colaborador: Int? = 0
        try {
            colaborador = bdInterServer.queryForObject(
                """
                  select count(idColaborador) from Colaborador where email = '${email}' and senha = '${senha}';
                """, Int::class.java
            );
        }
        catch (e: EmptyResultDataAccessException) { colaborador = 0 }

        return colaborador
    }

    fun buscarInfo(email: String, senha: String) : Colaborador? {
        val colaborador = bdInterServer.queryForObject(
            """
                select Colaborador.nome, Colaborador.idColaborador, empresa.NomeFantasia as NomeEmpresa FROM Colaborador join empresa on fkEmpresa = idEmpresa where email = '${email}' and senha = '${senha}';
            """, BeanPropertyRowMapper(Colaborador::class.java)
        );

        return colaborador

    }

//    fun buscarInfoMaquina(idMaquina: Int): Maquina? {
//        val maquina = jdbcTemplate.queryForObject(
//            """
//              select fkEmpresa, fkPlanoEmpresa, fkStatusMaquina, fkTipoMaquina from maquina where idMaquina = $idMaquina;
//            """ BeanPropertyRowMapper(Maquina::class.java),
//
//        );
//
//        return maquina
//    }

    fun buscarfkEmpresa(idMaquina: Int, maquina: Maquina):Int?{
        val fkEmpresa = bdInterServer.queryForObject(
            """
                 select fkEmpresa from maquina where idMaquina = $idMaquina;
                """, Int::class.java
        );

//        if (fkEmpresa != null) {
//            maquinas.fkEmpresa = fkEmpresa
//            println(maquinas.fkEmpresa)
//        }
        return fkEmpresa
    }

    fun buscarfkPlanoEmpresa(idMaquina: Int, maquina: Maquina): Int?{
        val fkPlanoEmpresa = bdInterServer.queryForObject(
            """
                 select fkPlanoEmpresa from maquina where idMaquina = $idMaquina;
                """, Int::class.java
        );

//        if (fkPlanoEmpresa != null) {
//            maquinas.fkPlanoEmpresa = fkPlanoEmpresa
//            println(maquinas.fkPlanoEmpresa)
//        }
//
        return fkPlanoEmpresa
    }

    fun buscarfkStatusMaquina(idMaquina: Int, maquina: Maquina):Int?{
        val fkStatusMaquina = bdInterServer.queryForObject(
            """
                 select fkStatusMaquina from maquina where idMaquina = $idMaquina;
                """, Int::class.java
        );

//        if (fkStatusMaquina != null) {
//            maquinas.fkStatusMaquina = fkStatusMaquina
//            println(maquinas.fkStatusMaquina)
//        }
        return fkStatusMaquina
    }

    fun buscarfkTipoMaquina(idMaquina: Int, maquina: Maquina): Int? {
        val fkTipoMaquina = bdInterServer.queryForObject(
            """
                 select fkTipoMaquina from maquina where idMaquina = $idMaquina;
            """, Int::class.java
        );

//        if (fkTipoMaquina != null) {
//            maquinas.fkTipoMaquina = fkTipoMaquina
//            println(maquinas.fkTipoMaquina)
//        }
        return fkTipoMaquina
    }
}