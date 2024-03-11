FROM node:20.9.0 AS builder

WORKDIR /app

COPY ./package*.json /app/

RUN npm config set registry "https://registry.npmjs.org/" \
  && npm install
COPY . /app
RUN npm run build

FROM nginx

COPY --from=builder app/dist /usr/share/nginx/html/

COPY --from=builder app/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
