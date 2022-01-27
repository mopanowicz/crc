package main

import (
    "fmt"
    "net/http"
    "os"
)

func main() {
    message := os.Getenv("MESSAGE")
    hostname := os.Getenv("HOSTNAME")

    http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
        fmt.Fprintf(w, "<h1>"+ message +"</h1>")
        if len(hostname) > 0 {
            fmt.Fprintf(w, "<h2>"+ message +"</h2>")
        }
    })

    fmt.Println("Starting server on port 8080.")

    http.ListenAndServe(":8080", nil)

    fmt.Println("Server stopped.")
}
