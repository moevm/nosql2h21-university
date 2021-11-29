# nosql2h21-university

В папке reports хранятся файлы для отчетности.

Файл data.json можно использовать для импорта. Помимо этого, при старте приложения через docker, создаются объекты, находящиеся в папке ./mongo-seed.

## Запуск

С помощью Docker:

`docker-compose down && docker-compose build --no-cache && docker-compose up`

Либо просто:

`docker-compose up`
