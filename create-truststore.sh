#!/bin/sh
set -e

keytool -importcert \
  -alias mysql-ca \
  -file /app/ca.pem \
  -keystore /app/truststore.jks \
  -storepass changeit \
  -noprompt
