FROM node:13-alpine

ENV MONGO_DB_USERNAME=roger \
    MONGO_DB_PWD=roger-pwd

RUN mkdir -p /home/app

COPY . /home/app

CMD ["node", "/home/app/server.js"]