@echo off
echo ========================================
echo   AIOps RuoYi-Vue Docker 部署
echo ========================================

REM 检查Docker是否安装
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: Docker未安装，请先安装Docker Desktop
    pause
    exit /b 1
)

REM 检查Docker Compose是否安装
docker-compose --version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: Docker Compose未安装，请先安装Docker Compose
    pause
    exit /b 1
)

REM 检查.env文件
if not exist .env (
    echo 创建.env文件...
    (
        echo # OpenAI API配置（可选）
        echo OPENAI_API_KEY=
    ) > .env
    echo ✓ 已创建.env文件
)

REM 构建并启动服务
echo 开始构建和启动服务...
docker-compose up -d --build

echo.
echo ========================================
echo   部署完成！
echo ========================================
echo   AIOps 平台:  http://localhost:8083
echo   后端API:    http://localhost:8084
echo   MySQL:      localhost:3306
echo.
echo 默认账号: admin / admin123
echo.
echo 查看日志: docker-compose logs -f
echo 停止服务: docker-compose down
echo 重启服务: docker-compose restart
echo.
echo 宝塔面板配置:
echo 1. 在宝塔面板添加反向代理
echo 2. 代理地址: http://127.0.0.1:8083
echo 3. 目标域名: your-domain.com
echo.
echo 注意：首次启动需要等待数据库初始化完成
pause
