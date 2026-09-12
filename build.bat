@echo off
echo Создание папки out...
if not exist out mkdir out

echo Компиляция исходников...
javac -d out -sourcepath src src\ru\university\lab2\**\*.java src\ru\university\lab2\*.java

echo Создание папки dist для JAR...
if not exist dist mkdir dist

echo Создание manifest.mf...
echo Main-Class: ru.university.lab2.Main > manifest.mf

echo Сборка JAR в dist\java-labs.jar...
jar -cmf manifest.mf dist\java-labs.jar -C out .

echo Готово: JAR сохранён в dist\java-labs.jar
echo Запустите: java -jar dist\java-labs.jar
pause