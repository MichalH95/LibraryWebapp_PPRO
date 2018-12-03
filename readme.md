Postup spuštění databáze:

1. Stáhnout a nainstalovat Docker Toolbox z https://download.docker.com/win/stable/DockerToolbox.exe

2. Spustit Docker Quickstart Terminal

3. Počkat až naběhne Docker terminál (ukáže se velryba)

4. Spustit v dockeru databázi příkazem:
	
		docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql

5. Nastavit si datasource v IntelliJ IDEA:

		URL: 192.168.99.100:3306
		Username: root
		Password: my-secret-pw
		
	A v záložce Schemas zaškrtnout All schemas.