#!/bin/sh
set -e

if [ "$1" = "build" ]; then
  docker build -f docker/Dockerfile -t iam-service:latest .
elif [ "$1" = "run" ]; then
  docker run --rm -p 8189:8189 \
    -e DB_HOST=host.docker.internal \
    -e DB_PORT=5432 \
    -e DB_NAME=post_hub_local \
    -e DB_USERNAME=postgres \
    -e DB_PASSWORD=postgres \
    iam-service:latest
else
  echo "Usage: ./docker/iam-service.sh [build|run]"
  exit 1
fi
