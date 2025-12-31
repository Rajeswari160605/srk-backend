#!/bin/sh
set -e

TRUSTSTORE_PATH=/app/truststore.jks
TRUSTSTORE_PASSWORD=changeit
ALIAS=aiven-mysql-ca

echo "Creating Java truststore from ca.pem..."

keytool -importcert \
  -noprompt \
  -trustcacerts \
  -alias ${ALIAS} \
  -file /app/ca.pem \
  -keystore ${TRUSTSTORE_PATH} \
  -storepass ${TRUSTSTORE_PASSWORD}

echo "Truststore created successfully"
