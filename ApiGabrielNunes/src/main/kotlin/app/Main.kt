package app

import javax.swing.JOptionPane
import Colaborador
import Repositorio
import ApiPython
import Maquina

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            val colaborador = Colaborador()
            val repositorio = Repositorio()
            val apiPython = ApiPython()

            repositorio.iniciarConexao()
            colaborador.email = JOptionPane.showInputDialog("Digite seu email")
            colaborador.senha = JOptionPane.showInputDialog("Digite sua senha")

            val verificacao : Int? = repositorio.VerificarColaborador(colaborador.email, colaborador.senha)

            if (verificacao != 0){
                val respostaInformacoes = repositorio.buscarInfo(colaborador.email, colaborador.senha)
                if (respostaInformacoes != null) {

                    val opcao = JOptionPane.showInputDialog(
                        null, """
                Olá ${respostaInformacoes.nome} da empresa ${respostaInformacoes.NomeEmpresa}
                1 - Iniciar o monitoramento da minha maquina
                2 - Sair 
            """.trimIndent()
                    ).toInt()

                    when(opcao){
                        1 -> {
                            val maquina = Maquina()
                            val IdBusca = JOptionPane.showInputDialog("Qual o id da maquina que deseja monitorar").toInt()

                            maquina.idMaquina = IdBusca

                            val ResultadoFkPlano = repositorio.buscarfkPlanoEmpresa(IdBusca, maquina)
                            if (ResultadoFkPlano != null) {
                                maquina.fkPlanoEmpresa = ResultadoFkPlano
                            }

                            val ResultadoFkEmpresa = repositorio.buscarfkEmpresa(IdBusca, maquina)
                            if (ResultadoFkEmpresa != null) {
                                maquina.fkEmpresa = ResultadoFkEmpresa
                            }

                            val ResultadoStatusMaq = repositorio.buscarfkStatusMaquina(IdBusca, maquina)
                            if (ResultadoStatusMaq != null) {
                                maquina.fkStatusMaquina = ResultadoStatusMaq
                            }

                            val ResultadoTipoMaq = repositorio.buscarfkTipoMaquina(IdBusca, maquina)
                            if (ResultadoTipoMaq != null) {
                                maquina.fkTipoMaquina = ResultadoTipoMaq
                            }

                            apiPython.CriarPython(maquina)

                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Email ou Senha inválida")
            }

        }
    }
}