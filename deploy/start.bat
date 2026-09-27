@echo off
setlocal
cd /d "%~dp0"

echo =====================================================================
echo   KHOI CHAY VA BUILD TU DONG HE THONG PHONG KHAM BENH TU NHAN (BTL 4)
echo =====================================================================
echo.

:: 1. Kiem tra Docker daemon
echo [1/4] Dang kiem tra Docker daemon...
docker info > nul 2>&1
if %errorlevel% neq 0 (
    echo [LOI] Docker Desktop chua khoi dong! Vui long bat Docker Desktop va thu lai.
    pause
    exit /b 1
)

:: 2. Cau hinh moi truong toi uu Docker BuildKit
set DOCKER_BUILDKIT=1
set COMPOSE_BAKE=false

:: 3. Don dep container postgres cu neu thuoc compose khac de tranh conflict
for /f "tokens=*" %%i in ('docker ps -aq -f "name=phongkham_postgres" 2^>nul') do (
    docker rm -f %%i > nul 2>&1
)
for /f "tokens=*" %%i in ('docker ps -aq -f "name=clinic_backend" 2^>nul') do (
    docker rm -f %%i > nul 2>&1
)
for /f "tokens=*" %%i in ('docker ps -aq -f "name=clinic_frontend" 2^>nul') do (
    docker rm -f %%i > nul 2>&1
)

:: 4. Giai phong cac port 8080, 5173 neu co tien trinh chay ngam tren host
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":8080 " ^| findstr "LISTENING"') do (
    taskkill /F /PID %%a > nul 2>&1
)
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":5173 " ^| findstr "LISTENING"') do (
    taskkill /F /PID %%a > nul 2>&1
)

:: 5. Build lai voi code moi nhat va khoi chay
echo [2/4] Dang build lai Image voi code moi nhat (BuildKit Cache sieu toc)...
docker compose -f docker-compose.yml up -d --build --force-recreate
if %errorlevel% neq 0 (
    echo.
    echo [LOI] Qua trinh build hoac khoi chay Docker that bai!
    pause
    exit /b 1
)

echo.
echo [3/4] Trang thai cac container:
docker compose -f docker-compose.yml ps

echo.
echo =====================================================================
echo   HE THONG DA KHOI CHAY THANH CONG VOI CODE MOI NHAT!
echo.
echo   - Giao dien Web (Frontend) : http://localhost:5173  hoac  http://localhost:80
echo   - API Backend (Spring Boot): http://localhost:8080/api/v1
echo   - Swagger UI API Docs      : http://localhost:8080/swagger-ui.html
echo   - CSDL PostgreSQL          : localhost:5432 (Database: phong_kham2)
echo.
echo   * Meo: De dung he thong, hay nhap dup vao file 'stop.bat'.
echo =====================================================================
echo.
pause
