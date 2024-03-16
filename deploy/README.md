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
Then, go to the pod and init the database
```shell
kubectl exec -it mysql-deploy-deployment-6cc787c55d-gt2nn -- /bin/bash
```
Enter MySQL pod
```shell
mysql -u root -p
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
