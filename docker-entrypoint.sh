#!/bin/sh
echo "Waiting for MySQL to be ready..."
until timeout 1 bash -c 'echo >/dev/tcp/mysql/3306' 2>/dev/null; do
    sleep 1
done
echo "MySQL is ready!"

exec "$@"
