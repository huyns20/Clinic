#!/bin/bash
set -e
cd "$(dirname "$0")"
echo "====================================================================="
echo "  ĐANG DỪNG HỆ THỐNG PHÒNG KHÁM BỆNH TƯ NHÂN (CLINIC MASTER)..."
echo "====================================================================="
docker compose -f docker-compose.yml down
echo ""
echo "Đã dừng toàn bộ dịch vụ thành công!"
