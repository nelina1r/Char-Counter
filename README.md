# Char Counter

Char Counter - это веб-сервис, предназначенный для подсчета количества 
одинаковых символов в строке.
## Стек
Java 17, Spring Boot, Spring Web, JUnit, Mockito, Lombok, Maven

## Запуск (windows)
Для запуска приложения вы можете использовать следующие команды:

1. Разархивируйте архив с исходным кодом, и перейдите в разархивированную папку:
```
cd charCounter
```
2. Откройте терминал и выполните следующую команду:
```
mvn clean package
```
Maven соберет проект и создаст jar-файл в каталоге target.

3. Запустите приложение командой:
```
java -jar target/<название_jar-файла>.jar
```
Где <название_jar-файла> - это имя jar-файла, созданного в предыдущем шаге. <br>
Приложение будет доступно по адресу http://localhost:8080
## Документация
Просмотреть документацию проекта можно перейдя по URL после запуска: http://localhost:8080/swagger-ui/index.html
## Использование

Выполните POST-запрос на эндпоинт `/count`, передав строку в теле запроса в формате JSON:

```json
{
  "inputText": "aabbbccccc"
}
```

## Response
В случае корректной входной строки, сервис вернет результат - количественный подсчет каждого символа в строке:
```json
{
  "c": 5,
  "b": 3,
  "a": 2
}
```
В случае отсутствия текста, или null, сервис вернет:
```
текст пуст либо null
```
