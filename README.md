# Утилита фильтрации содержимого файлов.

## Задача
Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt.
## Дополнительные опции
| Дополнительные опции | Описание                                                        |
|----------------------|-----------------------------------------------------------------|
| -o                   | Задать путь к результату                                        |
| -p                   | Префикс к названию файла                                        |                  
| -a                   | Добавления в существующие файлы (по умолчанию перезаписываются) |                  
| -s                   | Краткая статистика                                              |                  
| -f                   | Полная статистика                                               |  
Пример: ```-o /some/path -p sample_ -a -f t1.txt t2.txt```
## Настройка окружения
Для настройки окружения следует выполнить следующие шаги:

1. Установить дистрибутив Java 17 или выше так, чтобы он запускался по умолчанию.
 Так же установить maven
    ```bash 
    sudo apt install openjdk-17-jdk
    ``` 
   ```bash 
    sudo apt install maven
    ```
2. Копировать репозиторий в пустой каталог.
    ```bash 
    git clone https://github.com/NeZorinEgor/File-Filter.git
    ```

## Запуск приложения

### Запуск приложения, собранного Apache Maven
//TODO

### Пример запуска утилиты

```bash
java -jar TestTaskCFT.jar -o /home/egor/java/filter/test_output/ -p result_ -a -f t1.txt /home/egor/t2.txt
```

```
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
### Результат выполнения
```bash
~/java/filter/test_output$ ls
result_floats.txt  result_integers.txt  result_strings.txt

~/java/filter/test_output$ tail -f result_floats.txt
3.1415
-0.00001
1.528535047E-25


~/java/filter/test_output$ tail -f result_integers.txt
45
100500
1234567890123456789


~/java/filter/test_output$ tail -f result_strings.txt
Lorem ipsum dolor sit amet
Пример
consectetur adipiscing
тестовое задание
Нормальная форма числа с плавающей запятой
Long
```
