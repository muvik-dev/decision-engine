#!/usr/bin/env bash

echo "## START NGINX APP"
envsubst "`printf '${%s} ' $(bash -c "compgen -A variable")`" < /etc/nginx/conf.d/conf.nginx > /etc/nginx/conf.d/default.conf
exec nginx -g "daemon off;"
