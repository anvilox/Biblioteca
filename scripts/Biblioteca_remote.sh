echo figaredo1 | sudo -S mysql -u root -p root -e "SET GLOBAL
time_zone = '+3:00';"
echo figaredo1 | sudo -S mysql -u root -p root -e "create database
biblioteca;"
echo figaredo1 | sudo -S mysql -u root -p root -e "create user
bibliotecario identified by 'bibliotecario';"
echo figaredo1 | sudo -S mysql -u root -p root -e "grant all
privileges on biblioteca.* to 'bibliotecario';"
echo figaredo1 | sudo -S mysql -u root -p root -D biblioteca -e "create table libros (isbn INT(13) primary key, titulo varchar(30) not
null, autor varchar(30) not null);"