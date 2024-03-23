
# Software Containerization Assignment

This project is designed to demonstrate the principles of software containerization. It includes a frontend, a backend, and a MySQL database, which are deployed using Docker containers managed by Kubernetes. Helm charts are used to simplify the deployment and management of the services.

## 1. Project Structure

The project is structured as follows:

- `container.sql`: SQL of the database
- `frontend/`: Contains Dockerfile and the project code of the front end.
- `backend/`: Contains Dockerfile and the project code of the back end. The backend includes the GET methods for get available courses or selected course and POST methods for login, registration, change password and so on. 
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

### 2.5 Using Helm for Deployment

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

### 2.6 Securing the Application
The yaml used here are in the folder of `deploy/tls/`

  Firstly, we need to install the cert-manager

```shell
kubectl apply -f https://github.com/jetstack/cert-manager/releases/download/v1.3.1/cert-manager.yaml
```

  Then we use Openssl to generate the 
  Apply security configurations to protect sensitive information.

```shell
openssl genrsa -out ca.key 2048
openssl req -x509 -new -nodes -key ca.key -subj "/CN=My Own CA" -days 1024 -out ca.crt
```

Then use the generated keys and cert to create the ca-secret.yaml. After that, we can start deploying the tls. 
- **TLS Certificates:**

  ```shell
  kubectl apply -f tls/ca-secret.yaml \
    -f tls/certificate.yaml \
    -f tls/cluster-issuer.yaml
  ```

- **Ingress:**

  Configure ingress to manage external access to the services within your cluster.

  ```shell
  kubectl apply -f tls/ingress.yaml
  ```
The IP used in the cluster-issuer.yaml should be changed to the IP of the ingress. To make the TLS available on your browser, you need to import the cert.


