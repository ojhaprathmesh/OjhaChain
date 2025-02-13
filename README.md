# Multi-Threaded GPU-Accelerated Blockchain Miner

## Overview

This project is a multi-threaded, GPU-accelerated blockchain miner implemented in Java. It utilizes **JCuda** to offload SHA-256 computations to the GPU, significantly improving mining performance compared to CPU-only implementations. Additionally, it leverages Java's `ExecutorService` to parallelize nonce searching across multiple CPU threads.

## Features

- **Multi-threaded CPU mining**: Uses Java threads to divide nonce ranges across CPU cores.
- **GPU acceleration**: Offloads SHA-256 calculations to an NVIDIA GPU using JCuda.
- **Blockchain integrity verification**: Ensures mined blocks are valid.

## Prerequisites

- Java 8 or higher
- NVIDIA GPU with CUDA support
- Maven for dependency management
- JCuda (Java bindings for CUDA)

## Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.12.1</version>
    </dependency>
    <dependency>
        <groupId>org.jcuda</groupId>
        <artifactId>jcuda</artifactId>
        <version>12.6.0</version>
    </dependency>
</dependencies>
```

## Installation & Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/multi-threaded-mining.git
   cd multi-threaded-mining
   ```
2. Install the required dependencies using Maven:
   ```sh
   mvn clean install
   ```
3. Ensure CUDA is installed and JCuda is properly linked.

## How It Works

1. **Mining Process**

    - The `mineBlock()` method launches multiple CPU threads.
    - Each thread tries different nonces until a valid hash is found.
    - SHA-256 computation is offloaded to the GPU via JCuda.

2. **Blockchain Validation**

    - Ensures each block's hash matches its content.
    - Checks previous block hashes to maintain integrity.

## Running the Miner

Compile and run the project using:

```sh
mvn compile
java -jar target/multi-threaded-mining.jar
```

## Next Steps

- Implement CUDA kernel for GPU-based SHA-256 hashing.
- Optimize memory transfers between CPU and GPU.
- Enhance blockchain features (e.g., transactions, consensus mechanisms).

## License

This project is licensed under the MIT License.

## Author

Prathmesh Ojha
