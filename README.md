
# Software Containerization Assignment

This project is designed to demonstrate the principles of software containerization. It includes a frontend, a backend, and a MySQL database, which are deployed using Docker containers managed by Kubernetes. Helm charts are used to simplify the deployment and management of the services.

## 1. Project Structure

The project is structured as follows:

- `container.sql`: SQL of the database
- `frontend/`: Contains Dockerfile and the project code of the front end.
- `backend/`: Contains Dockerfile and the project code of the back end.
- `deploy/`:
  - `tls/`: TLS certificates and related Kubernetes configurations.
  - `mysql/`: Contains Kubernetes YAML files for MySQL deployment, including configurations for persistent volumes, deployments, secrets, and config maps.
  - `networkControl/`: Includes Kubernetes network policies for regulating traffic between the services.
  - `roleBasedAuthControl/`: Holds Kubernetes role-based access control configurations, including roles and role bindings for different service accounts.
- `helms/`:
  - `templates/`: Helm templates for Kubernetes resources.
  - `Chart.yaml`: A YAML file containing information about the helm chart.
  - `values.yaml`: The default configuration values for the helm chart.
- `README.md`: The markdown file containing instructions for deploying and managing the project.

## 2. Procedures to Deploy This Project

### 2.1 Prerequisites

Before deploying the application, ensure Docker, Kubernetes, and Helm are installed and configured on your system.

#### Build the Docker Images

The first step is to build the Docker images for the frontend and backend.

- **Frontend Image:**

  Navigate to the `frontend` directory and execute the following command:

  ```shell
  docker buildx build --platform linux/amd64,linux/arm64 -t wujianchun123/frontend:0.1 . --push
  ```

- **Backend Image:**

  Navigate to the `backend` directory and execute the following command:

  ```shell
  docker buildx build --platform linux/amd64,linux/arm64 -t wujianchun123/backend:0.1 . --push
  ```

### 2.2 Deploying MySQL

Set up a MySQL database using Kubernetes for persistent data storage.

- **Persistent Volume:**

  Apply the persistent volume configuration:

  ```shell
  kubectl apply -f deploy/mysql/persistent_volume.yaml
  ```

- **MySQL Deployment:**

  Deploy the MySQL database with all required configurations:
    ```shell
    kubectl apply -f mysql/config_map.yaml \
        -f mysql/mysql_secret.yaml \
        -f mysql/persistent_volume_claim.yaml \
        -f mysql/mysql_deploy.yaml
    ```

- **Initialize Database:**

  Access the MySQL pod to initialize the database:

  ```shell
  kubectl exec -it $(kubectl get pod -l app=mysql -o jsonpath="{.items[0].metadata.name}") -- /bin/bash
  mysql -u root -p
  ```

  Then use the container.sql to create the database and tables
### 2.3 Deploying Backend and Frontend

Deploy the backend and frontend services using Kubernetes.

- **Backend Deployment:**

  ```shell
  kubectl apply -f deploy/roleBasedAuthControl/backend-deploy.yaml \
    -f deploy/roleBasedAuthControl/backend-service.yaml
  ```

- **Frontend Deployment:**

  ```shell
  kubectl apply -f deploy/roleBasedAuthControl/frontend-deploy.yaml \
    -f deploy/roleBasedAuthControl/frontend-service.yaml
  ```

### 2.4 Network Control

Set up the necessary network controls for communication between services.

- **Network Policies:**

  ```shell
  kubectl apply -f deploy/networkControl/backegress.yaml \
    -f deploy/networkControl/backend2db.yaml \
    -f deploy/networkControl/front2back.yaml
  ```


### 2.5 Generate Certificates Signed by a Self-Made Certificate Authority

All the procedures in this step happen in the `cert/` folder.

- **Generate a Self-Made CA**

    ```shell
    openssl genrsa -out ca.key 2048
    openssl req -new -key ca.key -out ca.csr
    echo "subjectAltName=DNS:vegan.test,IP:127.0.0.1" > cert_extensions
    openssl x509 -req -days 36500 -in ca.csr -signkey ca.key -extfile cert_extensions -out  ca.crt
    ```

- **Use OpenSSL to Generate a Self-Made Certificate**

    ```shell
    openssl genrsa -out server.key 2048 
    openssl req -new -key server.key -out server.csr
    openssl x509 -req -CA ca.crt -CAkey ca.key -CAcreateserial -sha256 -days 3650 -extfile  cert_extensions -in server.csr -out server.crt
    ```

Now we can see the server's key: `server.key` and the certificate: `server.crt`.
They will be encoded into base64 format and stored in the secret, which will be mounted on the ingress.

- **Start the Ingress Controller**

Install the nginx ingress add-on for Minikube:

```shell
minikube addons enable ingress
```

To access the ingress, create a tunnel to expose the ingress's endpoint:

```shell
minikube tunnel
```


### 2.6 Using Helm for Deployment

Helm charts are used to simplify the deployment of Kubernetes applications.

- **Install with Helm Chart:**

  Navigate to the `helms` directory and run:

  ```bash
  helm install <release-name> .
  ```

- **Uninstall with Helm Chart:**

  ```bash
  helm uninstall <release-name>
  ```

- **Upgrade with Helm Chart:**

  ```bash
  helm upgrade <release-name> .
  ```

- **View Release List:**

  ```bash
  helm list
  ```

### 2.7 Securing the Application

Apply security configurations to protect sensitive information.

- **TLS Certificates:**

  ```shell
  kubectl apply -f cert/ca-secret.yaml \
    -f cert/certificate.yaml \
    -f cert/cluster-issuer.yaml
  ```

- **Ingress:**

  Configure ingress to manage external access to the services within your cluster.

  ```shell
  kubectl apply -f ingress.yaml
  ```

After following these steps, your application services should be running and accessible as configured in your Kubernetes cluster and ingress settings.


