# CRC

## Insecure registry

https://github.com/code-ready/crc/wiki/Adding-an-insecure-registry

```bash
oc login -u kubeadmin -p <kubeadmin_password> https://api.crc.testing:6443

oc patch --type=merge --patch='{
  "spec": {
    "registrySources": {
      "insecureRegistries": [
      "192.168.1.11:5000"
      ]
    }
  }
}' image.config.openshift.io/cluster

ssh -i ~/.crc/machines/crc/id_ecdsa -o StrictHostKeyChecking=no core@$(crc ip)

sudo cat /etc/containers/registries.conf 
```
