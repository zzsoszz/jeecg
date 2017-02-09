mysqld  --skip-grant-tables --console


mysql
update mysql.user set authentication_string=password('123qwe') where user='root' and Host = 'localhost';