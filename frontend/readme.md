Сборка
```
docker build -t anton-melnikov/library-frontend .
```
Запуск
```
docker run -d -p 8090:80 --rm --name library-frontend anton-melnikov/library-frontend
```