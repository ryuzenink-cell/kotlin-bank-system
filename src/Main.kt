// Estrutura das contas bancárias
data class Conta(
    val nome: String,
    var saldo: Double
)
// Banco de dados
fun database(): MutableList<Conta>{
    var contas = mutableListOf<Conta>()

    contas.add(Conta("Adrian", 18000.0))
    contas.add(Conta("Maria", 20.14))
    contas.add(Conta("Kin", 0.10))

    return contas
}

// Verifica se o cliente é cadastrado
fun inicial(): String{
    println("Você já é cliente do Banco Kotlin?\n")
    println("[1] - SIM | [2] - NÃO")

    val contas = database()
    var nomeUser: String = ""
    val init = readLine()?.toIntOrNull() ?: 0

    when(init){
        1 -> {
            println("Boa! Então vamos falzer LOGIN.\n")
            println("Digite seu nome de usuário:\n")
            nomeUser = readLine() ?: ""

            var usuario = contas.find{it.nome.equals(nomeUser, ignoreCase = true)}

            if(usuario != null){
                println("Bem-vindo, ${usuario.nome}!" )
                dashboard(contas)
            } else {
                println("Usuário não encontrado")
            }

            return nomeUser
        }

        2 -> {
            println("Boa! Então vamos falzer seu CADASTRO.\n")
            cadastro(contas)
        }

        else -> println("Opção inválida")
    }
    return nomeUser
}

// Caso o cliente não seja cadastrado, realiza o cadastro
fun cadastro(contas: MutableList<Conta>) {
    println("Olá, seja bem vindo ao Banco Kotlin. Vamos iniciar criando seu cadastro!\n")
    println("Informe seu primeiro nome:\n")
    var nome = readLine().toString()

    println("Muito bem, $nome! Agora, informe o valor que deseja depositar:\n")
    var saldo = readLine()?.toDoubleOrNull() ?: 0.0

    println("Entendido! Nome: $nome, saldo: $saldo")

    contas.add(Conta(nome, saldo))

    dashboard(contas)
}

// Área do cliente
fun dashboard(contas: MutableList<Conta>) {

    var rodando = true

    while (rodando) {

        println("\n=== BANCO KOTLIN ===")
        println("1 - Listar contas")
        println("2 - Depositar")
        println("3 - Sacar")
        println("0 - Sair")

        val opcao = readLine()?.toIntOrNull() ?: -1

        when (opcao) {

            1 -> {
                println("\n=== CONTAS ===")
                for (conta in contas) {
                    println("Nome: ${conta.nome} | Saldo: R$ ${conta.saldo}")
                }
            }

            2 -> {
                println("Digite o nome:")
                val nome = readLine() ?: ""

                val conta = contas.find { it.nome.equals(nome, true) }

                if (conta != null) {
                    println("Valor para depósito:")
                    val valor = readLine()?.toDoubleOrNull() ?: 0.0
                    conta.saldo += valor
                    println("Depósito realizado!")
                } else {
                    println("Conta não encontrada")
                }
            }

            3 -> {
                println("Digite o nome:")
                val nome = readLine() ?: ""

                val conta = contas.find { it.nome.equals(nome, true) }

                if (conta != null) {
                    println("Valor para saque:")
                    val valor = readLine()?.toDoubleOrNull() ?: 0.0

                    if (valor <= conta.saldo) {
                        conta.saldo -= valor
                        println("Saque realizado!")
                    } else {
                        println("Saldo insuficiente")
                    }
                } else {
                    println("Conta não encontrada")
                }
            }

            0 -> {
                println("Saindo...")
                rodando = false
            }

            else -> println("Opção inválida")
        }
    }
}
fun main(){
    inicial()
}