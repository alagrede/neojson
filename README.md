Simple json path parser based on googlecode json-simple

## Usage
```
String val = new NeoJson(json)
    .parse("books.[0].title")

// optional property
val = new NeoJson(json)
    .parse("[0].employee.?firstname")
```

