# container_assignment
Software Containerization Assignment

## Persistent layer (SQL or No-SQL database)
Create a Deployment/Statefulset, a Service and a Persistent Volume, Persistent Volume Claim for a database of your choice (Max 3 points). You can use a pre-built docker image for this database, or a pre-built Helm chart that you package as a sub-chart in your chart. Ensure that the Service exposed by the database is such that it can be accessed by the REST API, but not by users outside of the cluster (Max 3 points). Ensure that the configuration of the database makes use of ConfigMaps and Secrets appropriately (Max 3 points).

## REST API
Create a deployment for a REST API of your choice, that uses the database. The REST API should have at least one GET and one POST methods to illustrate that it can read and write from/to the database (Max 3 points).
It is expected that you create your own Dockerfile for this image (Max 3 points). Ensure that the application can scale horizontally (this is graded in the presentation). Create an appropriate type of Service such that the REST API can be accessed by the Web Application. Depending on where the Web Application runs, this might require the Web API to be exposed outside of the cluster (Max 3 points).

## Web front-end
Create a Deployment for a Web application of your choice, that uses the REST API (Max 3 points). It is expected that you create your own Dockerfile for this image (Max 3 points). Ensure that the application can scale horizontally (graded in the presentation). Create an appropriate type of Service/Ingress/API Gateway, such that the application can be accessed by users outside of the cluster (Max 3 points).

## Transport Level Security 
Configure TLS for the web application. You can use self-signed certificates (1 point) or certificates signed by a self-made certificate authority (2 points). You can use cert-manager (2 points) and/or openssl (1 point) to generate the certificates. Ensure that if the REST API is accessible outside of the Cluster, it is running on https as well (1 point). Exposing http endpoints that redirect to https is fine (1 point).

## Helm Chart
Create a Helm Chart to package the deployment of the entire application so it can be installed (3 points), uninstalled (3 points) and upgraded (3 points) with a single command. Provide the code of the Helm Chart and the commands that you use for:
- Generating the Chart
- Installing with the Chart
- Uninstalling with the Chart
- Upgrading an image with the Chart

## Security - Network policies
Configure Network Policies for the application. (2 points for each correct and useful network policy, with a maximum of 3 policies)

## Security - RBAC
Configure Role Based Access Control for the application. (2 points for each correct and useful role, with a maximum of 3 roles)

## Google Cloud Platform (or other public cloud)
Run the Application on Google Cloud Platform or other public cloud that supports managed Kubernetes clusters. If you do the whole presentation using GCP you will get the whole 6 points and you don't need to present it also with microk8s. If you are unable to present some specific component (say for example Network policies) in GCP but you do it on microk8s, you will get the points for Network Policies and less points for GCP (but not zero).
