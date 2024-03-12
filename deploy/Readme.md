persistent volume
```shell
 kubectl apply -f mysql/persistent_volume.yaml
```
mysql deployment
```shell
 kubectl apply -f mysql/config_map.yaml \
    -f mysql/mysql_secret.yaml \
    -f mysql/persistent_volume_claim.yaml  \
    -f mysql/mysql_deploy.yaml
```
backend
```shell
 kubectl apply -f backend-deploy.yaml \
    -f backend-service.yaml
```
frontend
```shell
 kubectl apply -f frontend-deploy.yaml \
    -f frontend-service.yaml
```