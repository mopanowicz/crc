# notes

```
echo '{}' | jq '.a.b += { "key": "value" }'  | jq '.a.b += { "key2": "value2" }
```

```
jq_command=". += {\"key1\":\"value1\"}"
echo '{}' | jq $jq_command | jq ". += {\"key2\":\"value2\"}"
```