# Утилита фильтрации содержимого файлов.

## Задача
Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt.

## Настройка окружения
Для настройки окружения следует выполнить следующие шаги:

1. Установить дистрибутив Java 17 или выше так, чтобы он запускался по умолчанию.
 Так же установить maven
    ```bash 
    sudo apt install openjdk-17-jdk
   sudo apt install maven
    ```
2. Копировать репозиторий в пустой каталог.
    ```bash 
    git clone https://github.com/NeZorinEgor/File-Filter.git
    ```

## Запуск приложения

### Запуск приложения, собранного Apache Maven
```bash
cd File-Filter
```
```bash
mvn clean package
```
```bash
mvn exec:java -Dexec.mainClass="ru.neZorinEgor.task.Application"
```

Пример запуска утилиты

```
-o /home/egor/java/filter/test_output/ -p result_ -a -f t1.txt t2.txt
Statistics for: result_integers.txt
└─ Number of elements: 3
        └─ Additional details:
                ├─── Minimum: 45.0
                ├─── Maximum: 1.23456793955060941E18
                ├──── Amount: 1.23456793955071002E18
                └─── Average: 4.1152264651690336E17

Statistics for: result_floats.txt
└─ Number of elements: 3
        └─ Additional details:
                ├─── Minimum: -9.999999747378752E-6
                ├─── Maximum: 3.1414999961853027
                ├──── Amount: 3.1414899961855554
                └─── Average: 1.0471633320618519

Statistics for: result_strings.txt
└─ Number of elements: 6
        └─ Additional details:
                ├─── Minimum length: 4.0
                └─── Maximum length: 42.0
```