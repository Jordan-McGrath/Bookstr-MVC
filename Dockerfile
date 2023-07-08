# Use a base image with Java 11 and Tomcat 9 pre-installed
FROM tomcat:9-jdk11

# Remove the default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your application's WAR file to the Tomcat webapps directory
COPY ./Cloud-Enterprise-MVC.war /usr/local/tomcat/webapps/ROOT.war

# Expose the desired port
EXPOSE 8081

# Start Tomcat when the container launches
CMD ["catalina.sh", "run"]
