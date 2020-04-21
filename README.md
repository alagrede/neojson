Simple json path parser based on googlecode json-simple

## Usage
json
```
{
  books: [
    {title: "book1"}, 
    {title: "book2"}
  ]
}
```
Path
```
String val = new NeoJson(json)
    .parse("books.[0].title")
```
json
```
[
  {
    employee: {
      name: "tony", 
      firstname: "anthony"
    }
  }, 
  {
    employee: {
      name: "flo"
    }
  }
]
```
Optional property
```
val = new NeoJson(json)
    .parse("[0].employee.?firstname")
```

