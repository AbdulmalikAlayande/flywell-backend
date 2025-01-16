ARG PROJECT_VERSION=0.0.1

# Expose port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "flywell.jar"]
