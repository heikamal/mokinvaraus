FROM mcr.microsoft.com/mssql/server:2022-CU10-ubuntu-22.04

WORKDIR /usr/src/app

COPY . /usr/src/app

USER root

RUN chmod +x /usr/src/app/run-initialization.sh

USER mssql

EXPOSE 1433

ENTRYPOINT /bin/bash ./entrypoint.sh