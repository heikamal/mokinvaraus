FROM mcr.microsoft.com/mssql/server:2022-CU10-ubuntu-22.04

WORKDIR /usr/src/app

COPY . /usr/src/app

USER root

RUN chmod +x /usr/src/app/run-initialization.sh

USER mssql

ENV SA_PASSWORD password123!
ENV ACCEPT_EULA Y
ENV MSSQL_PID Express

EXPOSE 1433

ENTRYPOINT /bin/bash ./entrypoint.sh