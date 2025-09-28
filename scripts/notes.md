# notes

```
echo '{}' | jq '.a.b += { "key": "value" }'  | jq '.a.b += { "key2": "value2" }
```