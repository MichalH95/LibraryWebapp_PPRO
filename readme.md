School project for the course PPRO (Advanced programming) at the University of Hradec Králové.

A basic web application for a library. It can display books in the library and information about them, allows client login and book reservations, loan extensions etc. Sends email notifications about late book returns using Spring's JavaMailSender. Allows administrator login with the ability to cancel loans, delete books and accounts and other functionalities.

Second author was my schoolmate Dominik Špinka.

Unfortunately, the code is mostly in Czech language.

This project is made in IntelliJ IDEA.

To start the database:

1. Download and install Docker Toolbox from https://download.docker.com/win/stable/DockerToolbox.exe

2. Run the Docker Quickstart Terminal

3. Wait for the Docker terminal to start (a whale will appear)

4. Start the database in docker with the command:

		docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql

5. Set up a datasource in IntelliJ IDEA:

		URL: 192.168.99.100:3306
		Username: root
		Password: my-secret-pw

	And in the Schemas tab, check All schemas.
