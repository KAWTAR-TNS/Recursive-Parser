# Use a stable OpenJDK version with GUI support
FROM --platform=linux/amd64 openjdk:17-jdk-slim

# Install required X11 libraries for Java GUI (Swing/AWT)
RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy project files
COPY RecursiveParser /app/RecursiveParser
COPY META-INF /app/META-INF
COPY module-info.class /app/

# Run the Java program
CMD ["java", "-cp", "/app", "RecursiveParser.SimpleFrameExample"]
