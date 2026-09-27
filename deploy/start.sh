#!/bin/bash
set -e

# Chuyen den thu muc chua script
cd "$(dirname "$0")"

echo "====================================================================="
echo "  KHOI CHAY VA BUILD TU DONG HE THONG PHONG KHAM BENH TU NHAN (BTL 4)"
echo "====================================================================="
echo ""

# 1. Kiem tra Docker daemon
if ! docker info > /dev/null 2>&1; then
    echo "[LOI] Docker daemon chua chay. Vui long bat Docker service va thu lai."
    exit 1
fi

# 2. Cau hinh BuildKit toi uu
export DOCKER_BUILDKIT=1
export COMPOSE_BAKE=false

# 3. Don dep cac container cu neu co de tranh conflict name
docker rm -f phongkham_postgres clinic_backend clinic_frontend > /dev/null 2>&1 || true

# 4. Build lai voi code moi nhat va khoi chay
echo "Dang build lai Image voi code moi nhat (BuildKit Cache sieu toc)..."
docker compose -f docker-compose.yml up -d --build --force-recreate

echo ""
echo "Trang thai cac container:"
docker compose -f docker-compose.yml ps

echo ""
echo "====================================================================="
echo "  HE THONG DA KHOI CHAY THANH CONG VOI CODE MOI NHAT!"
echo "  - Giao dien Web (Frontend) : http://localhost:5173  hoac http://localhost:80"
echo "  - API Backend (Spring Boot): http://localhost:8080/api/v1"
echo "  - Swagger UI API Docs      : http://localhost:8080/swagger-ui.html"
echo "  - CSDL PostgreSQL          : localhost:5432 (Database: phong_kham2)"
echo "====================================================================="
