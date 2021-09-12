FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123456
ENV POSTGRES_DB docker
COPY create.sql /docker-entrypoint-initdb.d/
