import psutil
import datetime
import time
import mysql.connector

conexao = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Biel0501",
    database="HealthTouch"
)

# Criar um cursor para executar consultas SQL
cursor = conexao.cursor()

while True:
    
    # Obtém estatísticas sobre o uso de RAM
    cpu = round(psutil.cpu_percent(interval=1), 2)
    disco = round(psutil.disk_usage('/').percent, 2)
    ram = round(psutil.virtual_memory().percent, 2)


    data = datetime.datetime.now()

    # Consulta SQL para inserir dados em uma tabela
    sql = '''
        insert into Monitoramento(porcentagem, dataHora, fkComponente, fkMaquina, fkPlanoEmpresa, fkTipoMaquina, fkEmpresaMaquina)
        VALUES (%s, %s, %s, %s, %s, %s, %s), (%s, %s, %s, %s, %s, %s, %s), (%s, %s, %s, %s, %s, %s, %s);
        '''

    insert = [
        cpu, data, 1, 4, 1, 1, 1,
        disco, data, 2, 4, 1, 1, 1,
        ram, data, 3, 4, 1, 1, 1, 
    ]


    # Executar a consulta SQL
    cursor.execute(sql, insert)

    # Confirmar as mudanças
    conexao.commit()

    print(f"Uso da CPU: {cpu}%")
    print(f"Uso do Disco: {disco}%")
    print(f"Uso da Memória: {ram}%\r\n")

    time.sleep(25)

# Fechar o cursor e a conexão
cursor.close()
conexao.close()











