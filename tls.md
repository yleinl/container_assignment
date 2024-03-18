kubectl apply -f https://github.com/jetstack/cert-manager/releases/download/v1.3.1/cert-manager.yaml
openssl genrsa -out ca.key 2048
openssl req -x509 -new -nodes -key ca.key -subj "/CN=My Own CA" -days 1024 -out ca.crt

Copy past the key and crt in ca.key and ca.crt to ca-secret.yaml
kubectl apply -f ca-secret.yaml
kubectl apply -f cluster-issuer.yaml
kubectl apply -f certificate.yaml
kubectl apply -f ingress.yaml
