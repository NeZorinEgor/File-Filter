# Утилита фильтрации содержимого файлов.

## Задача
Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt.

## Настройка окружения
Для настройки окружения следует выполнить следующие шаги:

1. Установить дистрибутив Java 21 так, чтобы он запускался по умолчанию. Запуск команды ```java -version``` должен выводить в консоль правильную версию JVM.
2. Копировать репозиторий в пустой каталог.
3. В консоли перейти в каталог, куда был копирован репозиторий и запустить сборку ```mvnw clean package```. Если всё правильно настроено, должно начаться загрузка Apache Maven и сборка проекта.

## Запуск приложения

### Запуск приложения, собранного Apache Maven
```bash
1. cd File-Filter
```
```bash
2. mvn clean package
```
```bash
3. mvn exec:java -Dexec.mainClass="ru.neZorinEgor.task.Application"
```

Промежуточный результат выполнения программы:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< ru.neZorinEgor.task:TestTaskCFT >-------------------
[INFO] Building TestTaskCFT 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- exec-maven-plugin:3.1.1:java (default-cli) @ TestTaskCFT ---
Enter file path: t1.txt t2.txt
Statistics for: integers.txt
└─ Number of elements: 3
        └─ Additional details:
                ├── Minimum: 45.0
                ├── Maximum: 1234567.0
                ├─── Amount: 1335112.0
                └── Average: 445037.3333333333

Statistics for: floats.txt
└─ Number of elements: 3
        └─ Additional details:
                ├── Minimum: -9.999999747378752E-6
                ├── Maximum: 3.1414999961853027
                ├─── Amount: 3.1414899961855554
                └── Average: 1.0471633320618519

Statistics for: strings.txt
└─ Number of elements: 6
        └─ Additional details:
                ├─────── Minimum length: 4.0
                └─────── Maximum length: 42.0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.149 s
[INFO] Finished at: 2024-01-21T03:37:22+08:00
[INFO] ------------------------------------------------------------------------
```