#!/usr/bin/env bash

docker stop shop_database&& \
docker rm shop_database

docker stop shopping_api&& \
docker rm shopping_api