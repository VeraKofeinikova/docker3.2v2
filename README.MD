1. Склонировать репозиторий
2. Запустить мой контейнер
3. запустить приложение следующей командой 
(примечание. перед каждым запуском тестов надо останавливать и запускать приложение)

java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/mysqldb -P:jdbc.user=appuser -P:jdbc.password=yes

4. запустить тесты

5. При необходимости таблицы вручную можно зачистить следующим образом 
(но можно и просто лишний раз запустить тесты - там настроена вычистка таблиц после прогона
тестов):

   а) Зайти в оболочку mysql:
      1.sudo docker-compose exec mysql mysql -u appuser mysqldb -p
      2.Сначала пароль от машины
      3.Потом пароль от базы данных - yes

   б) Команды зачистки:
      1.truncate auth_codes;
      2.truncate cards;
      3.truncate card_transactions;
      4.DELETE FROM users WHERE id IS NOT NULL;
