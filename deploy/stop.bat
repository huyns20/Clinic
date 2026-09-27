@echo off
setlocal
cd /d "%~dp0"

echo =====================================================================
echo   DANG DUNG HE THONG PHONG KHAM BENH TU NHAN (CLINIC MASTER)...
echo =====================================================================
echo.

docker compose -f docker-compose.yml down

echo.
echo Da dung toan bo dich vu thanh cong!
echo.
pause
