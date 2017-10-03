# Miscellaneous

## Time Complexity

### Asymptotic Bounds

> f(n) = O(g(n))

- `g(n)` is the asymptotic upper bound of `f(n)`.

> f(n) = Θ(g(n))

- `g(n)` is the asymptotic tight bound of `f(n)`.

> f(n) = Ω(g(n))

- `g(n)` is the asymptotic lower bound of `f(n)`.

### Complexity Classes

- `O(1)`: Constant
- `O(log n)`: Logarithmic
- `O(n)`: Linear
- `O(n log n)`: Loglinear
- `O(n^2)`: Quadratic
- `O(n^c)`: Polynomial
- `O(c^n)`: Exponential
- `O(n!)`: Factorial

## Math

### Combinatorics

- `2^(n + 1) - 1`: Sum of powers of two from 1 though `n`.
- `(n (n + 1)) / 2`: Sum of integers from 1 through `n`.
- `(n (n - 1)) / 2`: No. of handshakes in a group.
- `n - 1`: No. of matches in a knockout tournament.
- `2^k`: No. of binary strings of length `k`.
- `n! / ((n - k)!)`: Permutations of `n` items taken `k` at a time.
- `n! / (k! (n - k)!)`: Combinations of `n` items taken `k` at a time.

## Internet

### HTTP Methods

- `GET`: Used to retrieve data, no other effect on the data.
- `POST`: Used to send data to the server (e.g. form).
- `PUT`: Replaces current representation of resource (idempotent).
- `DELETE`: Removes current representation resource.

### HTTP Status Codes

- `200 OK`: Success
- `400 Bad Request`: Syntax could not be understood.
- `401 Unauthorized`: Request not fulfilled due to lack of authorization.
- `403 Forbidden`: Request understood but not fulfilled, authorization will not help.
- `404 Not Found`: URI could not be matched.
- `408 Request Timeout`: Server did not receive a timely response from client.
- `500 Internal Server Error`: Server exception.
- `503 Service Unavailable`: Server unable to handle the request (temporary).
- `504 Gateway Timeout`: Server did not receive a timely response from an upstream server.

## Rabin-Karp Substring Search

The brute force way to search for a substring of length `b` in a larger string of length `S` takes `O(b (S - b))` time, since the first `S - b + 1` characters are searched in the larger string and for each, the next `b` characters are checked.

The Rabin-Karp algorithm takes advantage of the fact that two identical strings will have the same hash value. Note that two different strings may also have the same hash value.

Therefore, using this trick can reduce the search time (in the best case) to `O(S)` since we just need to compute the hash value for every sequence of `b` characters in the larger string and validate those substrings.
