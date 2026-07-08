#!/bin/bash

echo "========================================"
echo "  AIOps RuoYi-Vue Docker 部署"
echo "========================================"

# 检查Docker是否安装
if ! command -v docker &> /dev/null; then
    echo "错误: Docker未安装，请先安装Docker"
    echo "安装命令: curl -fsSL https://get.docker.com | sh"
    exit 1
fi

# 检查Docker Compose是否安装
if ! command -v docker-compose &> /dev/null; then
    echo "错误: Docker Compose未安装，请先安装Docker Compose"
    exit 1
fi

# 检查.env文件
if [ ! -f .env ]; then
    echo "创建.env文件..."
    cat > .env << 'EOF'
# Redis密码（服务器已有Redis）
REDIS_PASSWORD=

# OpenAI API配置（可选）
OPENAI_API_KEY=
EOF
    echo "✓ 已创建.env文件"
    echo "⚠ 如需Redis密码，请编辑.env文件设置REDIS_PASSWORD"
fi

# 创建文件上传目录
echo "创建文件上传目录..."
mkdir -p /www/wwwroot/aiops/uploadPath
chmod 755 /www/wwwroot/aiops/uploadPath

# 构建并启动服务
echo "开始构建和启动服务..."
docker-compose up -d --build

echo ""
echo "========================================"
echo "  部署完成！"
echo "========================================"
echo "  AIOps 平台:  http://服务器IP:8083"
echo "  后端API:    http://服务器IP:8084"
echo "  MySQL:      Docker容器 (3306)"
echo "  Redis:      服务器已有 (6379)"
echo ""
echo "默认账号: admin / admin123"
echo "MySQL账号: aiops / aiops123"
echo "MySQL Root: root / root123456"
echo ""
echo "查看日志: docker-compose logs -f"
echo "停止服务: docker-compose down"
echo "重启服务: docker-compose restart"
echo ""
echo "宝塔面板配置:"
echo "1. 在宝塔面板添加反向代理"
echo "2. 代理地址: http://127.0.0.1:8083"
echo "3. 目标域名: your-domain.com"
echo ""
echo "注意："
echo "- MySQL容器会自动初始化数据库和表结构"
echo "- 首次启动需要等待数据库初始化完成"
echo "- 确保Redis服务正常运行"
