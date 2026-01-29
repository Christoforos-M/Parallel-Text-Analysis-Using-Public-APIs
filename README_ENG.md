# Parallel Text Analysis Using Public APIs (Java)

This project uses public APIs that return plain text via HTTP GET requests.  
The retrieved text is processed in parallel using multiple threads in Java in order to extract statistical information.

Example APIs:

- https://loripsum.net/api/10/plaintext  
- http://metaphorpsum.com/paragraphs/10  

The API endpoint is provided as a runtime parameter.

## 📚 Information
- Course: PLH 47
- Assignment: Parallel Text Analysis with Public APIs
- Language: Java

## 📝 Description

The program creates 1, 2, 4, or 8 threads.  
Each thread performs *k* HTTP GET requests to the selected API and collects the generated text.

Based on the combined text from all threads, the program computes:

1. The average word length across all retrieved texts  
2. The percentage occurrence of each English alphabet character (A–Z), where the total sums to 100%

For both computations, punctuation is ignored.

If any HTTP request fails (e.g., due to network issues), it is treated as returning an empty text.

The total execution time is measured for different numbers of threads.

## 🛠️ Technologies
- Java
- Java Threads
- HTTP Requests
- HashMap / ConcurrentHashMap
- System.nanoTime() for performance measurements
