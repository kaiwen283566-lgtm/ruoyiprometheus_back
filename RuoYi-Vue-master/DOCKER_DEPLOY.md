# AIOps RuoYi-Vue Docker 部署指南

## 一键部署（推荐）

### 前提条件
- 服务器已安装 Docker 和 Docker Compose
- 服务器已有 Redis 服务 (6379端口)
- 服务器可以访问外网（用于下载镜像）

### 部署步骤

#### 1. 上传项目到服务器
```bash
# 在本地打包
cd D:\xm
tar -czf RuoYi-Vue-docker.tar.gz --exclude=node_modules --exclude=dist --exclude=target RuoYi-Vue-master

# 上传到宝塔路径
scp RuoYi-Vue-docker.tar.gz root@your-server:/www/wwwroot/aiops/
```

#### 2. 在服务器上解压
```bash
cd /www/wwwroot/aiops
tar -xzf RuoYi-Vue-docker.tar.gz
cd RuoYi-Vue-master
```

#### 3. 配置环境变量
```bash
# 编辑.env文件
vim .env

# 设置以下配置
REDIS_PASSWORD=your-redis-password  # 如果Redis有密码
OPENAI_API_KEY=your-api-key       # 可选
```

#### 4. 执行部署脚本
```bash
chmod +x docker-deploy.sh
./docker-deploy.sh
```

#### 5. 等待部署完成
- 首次部署需要下载Docker镜像，约5-10分钟
- 构建前端和后端需要3-5分钟
- MySQL容器会自动初始化数据库和表结构

#### 6. 宝塔面板配置反向代理
1. 登录宝塔面板
2. 网站 -> 添加反向代理
3. 代理名称: aiops-frontend
4. 目标URL: `http://127.0.0.1:8083`
5. 发送域名: `$host`

#### 7. 访问系统
- **系统地址**: http://your-domain.com
- **直接访问**: http://服务器IP:8083
- **默认账号**: admin / admin123

## 手动部署

### 1. 安装Docker（如果没有）
```bash
curl -fsSL https://get.docker.com | sh
systemctl start docker
systemctl enable docker
```

### 2. 安装Docker Compose（如果没有）
```bash
curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

### 3. 配置环境变量
```bash
# 创建.env文件
cat > .env << 'EOF'
# Redis密码（服务器已有Redis）
REDIS_PASSWORD=

# OpenAI API配置（可选）
OPENAI_API_KEY=
EOF
```

### 4. 创建文件上传目录
```bash
mkdir -p /www/wwwroot/aiops/uploadPath
chmod 755 /www/wwwroot/aiops/uploadPath
```

### 5. 构建并启动
```bash
docker-compose up -d --build
```

### 6. 查看日志
```bash
# 查看所有服务日志
docker-compose logs -f

# 查看特定服务日志
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql
```

## 服务说明

### Docker容器服务
- **MySQL**: 3306端口（Docker容器）
  - 数据库: ry-vue
  - 用户: aiops / aiops123
  - Root: root / root123456
- **后端应用**: 8084端口
- **前端应用**: 8083端口

### 服务器已有服务
- **Redis**: 6379端口
- **Prometheus**: 19090端口
- **Loki**: 3100端口

## 常用命令

### 查看服务状态
```bash
docker-compose ps
```

### 重启服务
```bash
docker-compose restart
```

### 停止服务
```bash
docker-compose down
```

### 停止并删除数据
```bash
docker-compose down -v
```

### 重新构建
```bash
docker-compose up -d --build
```

### 进入容器
```bash
# 进入后端容器
docker-compose exec backend bash

# 进入MySQL容器
docker-compose exec mysql bash
```

## 故障排查

### 1. 服务启动失败
```bash
# 查看日志
docker-compose logs backend
docker-compose logs frontend
docker-compose logs mysql
```

### 2. 数据库连接失败
```bash
# 检查MySQL容器是否启动
docker-compose ps mysql

# 检查数据库是否初始化
docker-compose exec mysql mysql -u aiops -paiops123 ry-vue -e "SHOW TABLES;"
```

### 3. Redis连接失败
```bash
# 检查Redis是否运行
redis-cli ping

# 检查Redis密码
redis-cli -a your-password ping
```

### 4. 前端无法访问后端
```bash
# 检查后端是否启动
docker-compose ps backend

# 检查网络连接
docker-compose exec frontend ping backend
```

## 配置修改

### 修改MySQL配置
编辑 `docker-compose.yml` 中的MySQL配置：
```yaml
environment:
  MYSQL_ROOT_PASSWORD: your-root-password
  MYSQL_DATABASE: your-database
  MYSQL_USER: your-user
  MYSQL_PASSWORD: your-password
```

### 修改Redis密码
编辑 `.env` 文件：
```bash
REDIS_PASSWORD=your-redis-password
```

### 修改外部服务地址
编辑 `ruoyi-admin/src/main/resources/application.yml`：
```yaml
aiops:
  prometheus:
    url: http://127.0.0.1:19090
  loki:
    url: http://127.0.0.1:3100
```

## 数据持久化

### 备份数据库
```bash
docker-compose exec mysql mysqldump -u root -proot123456 ry-vue > backup.sql
```

### 恢复数据库
```bash
docker-compose exec -T mysql mysql -u root -proot123456 ry-vue < backup.sql
```

### 备份整个数据卷
```bash
docker run --rm -v aiops-mysql-data:/data -v $(pwd):/backup alpine tar czf /backup/mysql-backup.tar.gz /data
```

### 备份文件上传目录
```bash
tar -czf upload-backup.tar.gz /www/wwwroot/aiops/uploadPath
```

## 性能优化

### 限制资源使用
```yaml
services:
  backend:
    deploy:
      resources:
        limits:
          cpus: '2'
          memory: 2G
        reservations:
          cpus: '1'
          memory: 1G
```

## 安全建议

1. **修改默认密码**: 修改MySQL密码和管理员密码
2. **配置防火墙**: 只开放必要的端口
3. **使用HTTPS**: 在宝塔面板配置SSL证书
4. **定期更新**: 定期更新Docker镜像
5. **备份数据**: 定期备份数据库和上传文件

## 监控和维护

### 查看资源使用
```bash
docker stats
```

### 清理未使用的镜像
```bash
docker image prune -a
```

### 清理未使用的容器
```bash
docker container prune
```

## 升级部署

### 1. 备份数据
```bash
docker-compose exec mysql mysqldump -u root -proot123456 ry-vue > backup.sql
tar -czf upload-backup.tar.gz /www/wwwroot/aiops/uploadPath
```

### 2. 停止服务
```bash
docker-compose down
```

### 3. 更新代码
```bash
# 上传新代码
tar -xzf RuoYi-Vue-new.tar.gz
```

### 4. 重新部署
```bash
docker-compose up -d --build
```

### 5. 恢复数据（如果需要）
```bash
docker-compose exec -T mysql mysql -u root -proot123456 ry-vue < backup.sql
```

## 技术支持

- Docker文档: https://docs.docker.com/
- Docker Compose文档: https://docs.docker.com/compose/
- RuoYi文档: http://doc.ruoyi.vip
- 宝塔面板文档: https://www.bt.cn/bbs/
