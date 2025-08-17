from datetime import datetime
import json
import socket
import sys

batch = int(sys.argv[1]) if len(sys.argv) > 1 else 1
batch_size = int(sys.argv[2]) if len(sys.argv) > 2 else 1000

trigger_event = {
  "batch": 0,
  "index": 0,
  "timestamp": ""
}

key = socket.gethostname()
for i in range(1, batch_size + 1):
  trigger_event["batch"] = batch
  trigger_event["index"] = i
  trigger_event["timestamp"] = datetime.now().isoformat()
  print(f"__TypeId__:com.example.poc.trigger_event.TriggerEvent;{key}:{json.dumps(trigger_event)}")

sys.stdout.flush()