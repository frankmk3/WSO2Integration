cd %CD%\sercop-email-master\
mvn clean package
cd %CD%.
COPY  %CD%\sercop-email-master\target\sercop-email*%CD%jar %CD%\dockercompose\sercop-email-master\app.jar
cd %CD%\sercop-registro-proveedor-master\
mvn clean package
cd %CD%.
COPY  %CD%\sercop-registro-proveedor-master\target\sercop-registro-proveedor*jar %CD%\dockercompose\sercop-registro-proveedor-master\app.jar
cd %CD%\sercop-seguridad-master\
mvn clean package
cd %CD%.
COPY  %CD%\sercop-seguridad-master\target\sercop-seguridad*%CD%jar %CD%\dockercompose\sercop-seguridad-master\app.jar
cd %CD%\registro-proveedores\
npm install
npm run build
cd %CD%.
COPY  %CD%\registro-proveedores\build\* %CD%\dockercompose\nginx\var\www\html\
docker-compose up -d