#!/usr/bin/env bash

#openssl genrsa -out private.key 2048
#openssl req -new -key private.key -sha256 -nodes -out request.csr -subj "/C=US/ST=California/L=Mountain View/O=WSO2/OU=IT/CN=wso2ei"


keytool -genkey -alias wso2ei -keyalg RSA -keysize 2048 -keystore wso2ei.jks -dname "CN=wso2ei, OU=ec,O=sercop,L=Quito,S=Pichincha,C=Ecuador" -storepass wso2ei -keypass wso2ei
./wso2ei.jks


echo wso2ei|keytool -export -alias wso2ei -keystore wso2ei.jks -file wso2ei.pem

cp wso2ei.* dockercompose/cert
cp wso2ei.pem dockercompose/sercop-registro-proveedor-master
cp wso2ei.pem dockercompose/sercop-seguridad-master
rm wso2ei.*
