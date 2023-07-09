# setup

The server is accessible via web console at:
  https://console-openshift-console.apps-crc.testing

Log in as administrator:
  Username: kubeadmin
  Password: PThyA-rZiyH-LZpyG-29jYr

Log in as user:
  Username: developer
  Password: developer

Use the 'oc' command line interface:
  $ eval $(crc oc-env)
  $ oc login -u developer https://api.crc.testing:6443

# registry

https://docs.openshift.com/container-platform/4.13/registry/securing-exposing-registry.html
