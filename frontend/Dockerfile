FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# этап production (production-stage)
FROM nginx:stable-alpine as production-stage
ENV PORT $PORT
COPY default.config.template /etc/nginx/conf.d/default.conf.template
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE $PORT
CMD /bin/sh -c "envsubst '\$PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf" && nginx -g 'daemon off;'
