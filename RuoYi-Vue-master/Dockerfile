# 后端构建Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# 复制pom文件
COPY pom.xml ./

# 下载依赖
RUN mvn dependency:go-offline -B

# 复制源码
COPY . .

# 构建后端
RUN mvn clean package -DskipTests -B

# 生产环境镜像
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 复制jar包
COPY --from=builder /app/ruoyi-admin/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
