1. Склонировать репозиторий
2. Запустить мой контейнер
3. запустить приложение следующей командой 
(примечание. перед каждым запуском тестов надо останавливать и запускать приложение)

java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/mysqldb -P:jdbc.user=appuser -P:jdbc.password=yes

4. запустить тесты

5. При необходимости таблицы вручную можно зачистить следующим образом 
(но можно и просто лишний раз запустить тесты - там настроена вычистка таблиц после прогона
тестов):

   а)Зайти в оболочку mysql:
      sudo docker-compose exec mysql mysql -u appuser mysqldb -p
      Сначала пароль от машины
      Потом пароль от базы данных - yes

   б) Команды зачистки:
      truncate auth_codes;
      truncate cards;
      truncate card_transactions;
      DELETE FROM users WHERE id IS NOT NULL;