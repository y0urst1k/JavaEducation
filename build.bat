@echo off
:: Устанавливаем UTF-8, чтобы избежать проблем с кириллицей
chcp 65001 >nul

:: На всякий случай переходим в папку скрипта
cd /d "%~dp0"

echo Создание папки out для .class файлов...
if not exist out mkdir out

echo Создание папки dist для JAR...
if not exist dist mkdir dist

echo Компиляция исходников...
javac -d out -sourcepath src ^
 src\ru\university\lab2\Main.java ^
 src\ru\university\lab2\numbers\Task01IntegerTraps.java ^
 src\ru\university\lab2\numbers\Task02FloatingPoint.java ^
 src\ru\university\lab2\numbers\Task03Bitwise.java ^
 src\ru\university\lab2\strings\Task04TextProcessing.java ^
 src\ru\university\lab2\arrays\Task05SingleArray.java ^
 src\ru\university\lab2\arrays\Task06Matrix.java ^
 src\ru\university\lab2\methods\Task07MethodOverloading.java ^
 src\ru\university\lab2\menu\Task08ConsoleMenu.java

if errorlevel 1 (
    echo.
    echo ❌ Ошибка компиляции Проверьте синтаксис .java файлов.
    pause
    exit /b 1
)

echo.
echo Создание manifest.mf...
echo Main-Class: ru.university.lab2.Main > manifest.mf

echo.
echo Сборка JAR в dist\java-labs.jar...
jar -cmf manifest.mf dist\java-labs.jar -C out .

if errorlevel 1 (
    echo.
    echo ❌ Ошибка при создании JAR!
    pause
    exit /b 1
)

echo.
echo ✅ Готово: JAR сохранён в dist\java-labs.jar
echo Запустите: java -jar dist\java-labs.jar
pause