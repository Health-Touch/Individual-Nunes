        import psutil
        import datetime
        import time
        import mysql.connector
        import pymssql
        import pyodbc
        
      
        # Função para obter a conexão com o banco de dados
def mysql_connection(host, user, passwd, database=None):
    connection = mysql.connector.connect(
        host=host,
        user=user,
        passwd=passwd,
        database=database
    )
    return connection


        def sql_server_connection(server, database, username, password):
            conn_str = f'DRIVER={{SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'
            connection = pyodbc.connect(conn_str)
            return connection

        # Conectar ao banco de dados
        connection = mysql_connection('localhost', 'root', 'Biel0501', 'HealthTouch')

        sql_server_connection = sql_server_connection('54.145.218.19', 'HealthTouch', 'sa', 'urubu100')

        def insert_data(connection, query, values):
            cursor = connection.cursor()
            cursor.execute(query, values)
            connection.commit()
            
        while True:
            cpu = round(psutil.cpu_percent(interval=1), 2)
            disco = round(psutil.disk_usage('/').percent, 2)
            ram = round(psutil.virtual_memory().percent, 2)
            data = datetime.datetime.now()
            
            sql = '''
                insert into Monitoramento(porcentagem, dataHora, fkComponente, fkMaquina, fkPlanoEmpresa, fkTipoMaquina, fkEmpresaMaquina)
                VALUES 
                (? , ? , ? , ? , ? , ? , ?);
            '''
            
            insert_cpu = (
                cpu, data, 1, 1, 1, 1, 1
            )
            
            insert_disco = (
                disco, data, 2, 1, 1, 1, 1
            )
            
            insert_ram = (
                ram, data, 3, 1, 1, 1, 1
            )
            
            print(f"Uso da CPU: {cpu}%")
            print(f"Uso do Disco: {disco}%")
            print(f"Uso da Memória: {ram}%\r\n")
            
            insert_data(sql_server_connection, sql, insert_cpu)
            insert_data(sql_server_connection, sql, insert_disco)
            insert_data(sql_server_connection, sql, insert_ram)