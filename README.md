# GraalvmDemo
GraalvmDemo

javafx application on Windows using graalvm



mvn clean gluonfx:build gluonfx:package


## step1 setup graalvm

https://github.com/graalvm/graalvm-ce-builds/releases

for Windows and java17, download this one:

graalvm-ce-java17-windows-amd64-22.0.0.2.zip
https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.0.0.2/graalvm-ce-java17-windows-amd64-22.0.0.2.zip

unzip it to some path on your disk, like D:/java/graalvm/graalvm-ce-java17-22.0.0.2/

add system environments variable, for example:

GRAALVM_HOME
D:/java/graalvm/graalvm-ce-java17-22.0.0.2/

JAVA_HOME
%GRAALVM_HOME%

add this into system path:
path: oldPath;%JAVA_HOME%\bin

in cmd tool, input this command:
```shell
java -version
```

if output like this,it means graalvm install succeed:
```shell
openjdk version "17.0.2" 2022-01-18
OpenJDK Runtime Environment GraalVM CE 22.0.0.2 (build 17.0.2+8-jvmci-22.0-b05)
OpenJDK 64-Bit Server VM GraalVM CE 22.0.0.2 (build 17.0.2+8-jvmci-22.0-b05, mixed mode, sharing)
```

download native-image tool:  native-image-installable-svm-java17-windows-amd64-22.0.0.2.jar
https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.0.0.2/native-image-installable-svm-java17-windows-amd64-22.0.0.2.jar

install native-image tool:
```shell
gu -L install path-to-native-image-jar-file
```
for example, if you save the native-image jar file in D:/download, the command will be:
```shell
gu -L install D:/download/native-image-installable-svm-java17-windows-amd64-22.0.0.2.jar
```

tips: gu is graalvm-ce-java17-22.0.0.2\bin\gu.cmd

## step2 setup visual studio

make sure visual studio installed, if not, here you can download it
https://visualstudio.microsoft.com/zh-hans/downloads/

install MSVS, include C++ desktop module.

add system environments variable:

WINDOWS_KITS
C:\Program Files (x86)\Windows Kits\10\bin\10.0.19041.0\x64

WK10_INCLUDE
C:\Program Files (x86)\Windows Kits\10\Include\10.0.19041.0

WK10_LIB
C:\Program Files (x86)\Windows Kits\10\Lib\10.0.19041.0

10.0.19041.0 is the tool kits version on my System, It maybe different on your System.

input this command in cmd:
```shell
cl -version
```
if you see cl version info, it means visual studio install and setup succeed.

## step3 build

download my project, and open it in IDEA (https://www.jetbrains.com/idea/)

setup maven and project jdk, then run in terminal:

```shell
mvn clean gluonfx:build
```

exe file is in GraalvmDemo\target\gluonfx

### tips:
gluonfx:build = gluonfx:compile + gluonfx:link

## step4 package

download wix:
https://wixtoolset.org/releases/
install wix, than restart idea, open this project, if exe file already exits, run:
```shell
mvn gluonfx:package
```
else run:
```shell
mvn clean gluonfx:build gluonfx:package 
```

if success, exe and msi file is in GraalvmDemo\target\gluonfx

### tips:
1. custom exe icon:
if you want set an icon (.ico file) as icon of exe file, copy your ico file into /src/windows/asset/icon.ico, overwrite exists one.

2. version and vendor
in pom.xml, 

```xml
<!--...-->
<build>
    <plugins>
        <!--...-->
        <plugin>
            <!--...-->
            <configuration>
                <!--...-->
                <releaseConfiguration>
                    <!--...-->
                    <vendor>VendorName</vendor>
                    <version>1.0.0</version>
                </releaseConfiguration>

            </configuration>
        </plugin>

    </plugins>
</build>
<!--...-->
```

VendorName can be any string as you wish, but take care of version, it must be different every time you make package (msi file),
if you install an old version one, and then try to install a new one with the same version, it will be failure. And the version must increase
every time, if new package(msi)'s version less than old one, it also can not install.

