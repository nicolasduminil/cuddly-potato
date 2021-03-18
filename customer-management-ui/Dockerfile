FROM node:15.11.0-alpine3.10 AS node
WORKDIR /usr/src/app
COPY ng-customers .
RUN npm install
RUN mkdir -pv \
  ./node_modules/primeng/resources/components/colorpicker/images \
  ./node_modules/primeng/resources/components/lightbox/images \
  ./node_modules/primeng/resources/components/password/images \
  ./node_modules/primeng/resources/components/tree/images
RUN cp ./node_modules/primeng/resources/images/color.png ./node_modules/primeng/resources/components/colorpicker/images/color.png
RUN cp ./node_modules/primeng/resources/images/hue.png ./node_modules/primeng/resources/components/colorpicker/images/hue.png
RUN cp ./node_modules/primeng/resources/images/loading.gif ./node_modules/primeng/resources/components/lightbox/images/loading.gif
RUN cp ./node_modules/primeng/resources/images/password-meter.png ./node_modules/primeng/resources/components/password/images/password-meter.png
RUN cp ./node_modules/primeng/resources/images/line.gif ./node_modules/primeng/resources/components/tree/images/line.gif
#CMD pwd; ls -al ./node_modules/primeng/resources/images; ls -al ./node_modules/primeng/resources/components/colorpicker
RUN npm run build
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=node /usr/src/app/dist/ .