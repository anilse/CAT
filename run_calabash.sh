#!bin/bash

cd /home/anilse/MasterOzu/cs576/project/calabash/calabash-android
pwd
calabash-android run Email.apk --format html --out reports.html
firefox reports.html
