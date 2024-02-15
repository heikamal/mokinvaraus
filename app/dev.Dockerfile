FROM node:21-alpine3.19 AS base
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN npm install

FROM base AS development
EXPOSE 5173
CMD ["npm", "run", "dev"]