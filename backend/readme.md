Сборка
```
docker build -t anton-melnikov/library-backend .
```
Запуск
```
docker run -d -p 8080:8080 --rm --name library-backend anton-melnikov/library-backend
```