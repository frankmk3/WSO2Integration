#!/usr/bin/env bash

keytool -genkey -alias wso2ei -keyalg RSA -keysize 2048 -keystore wso2ei.jks -dname "CN=wso2ei, OU=ec,O=Example,L=Quito,S=Pichincha,C=Ecuador" -storepass wso2ei -keypass wso2ei
./wso2ei.jks

echo wso2ei|keytool -export -alias wso2ei -keystore wso2ei.jks -file wso2ei.pem

cp wso2ei.* dockercompose/cert
cp wso2ei.pem dockercompose/wso2integration
rm wso2ei.*
