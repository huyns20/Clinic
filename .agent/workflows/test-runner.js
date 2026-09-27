/**
 * Automated Test Runner for Clinic Management System
 * Node.js Built-in runner (Zero external dependency)
 */

import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const BASE_URL = process.env.API_BASE_URL || 'http://localhost:8080';
const TESTCASES_DIR = path.join(__dirname, 'testcases');
const REPORTS_DIR = path.join(__dirname, 'reports');

if (!fs.existsSync(REPORTS_DIR)) {
  fs.mkdirSync(REPORTS_DIR, { recursive: true });
}

async function runTestSuite(filePath) {
  const content = fs.readFileSync(filePath, 'utf8');
  const testSuite = JSON.parse(content);
  console.log(`\n======================================================`);
  console.log(`🧪 Running Suite: ${testSuite.suite}`);
  console.log(`======================================================`);

  const results = [];

  for (const tc of testSuite.cases) {
    const url = `${BASE_URL}${tc.endpoint}`;
    const startTime = performance.now();
    let status = 0;
    let passed = false;
    let responseBody = null;
    let errorMsg = null;

    try {
      const options = {
        method: tc.method || 'GET',
        headers: {
          'Content-Type': 'application/json',
          ...(tc.headers || {}),
        },
      };

      if (tc.body && ['POST', 'PUT', 'PATCH'].includes(tc.method)) {
        options.body = JSON.stringify(tc.body);
      }

      const res = await fetch(url, options);
      status = res.status;
      const text = await res.text();
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (tc.expectedStatus) {
        passed = status === tc.expectedStatus;
      } else {
        passed = status >= 200 && status < 300;
      }
    } catch (err) {
      errorMsg = err.message;
      passed = false;
    }

    const duration = Math.round(performance.now() - startTime);

    const result = {
      id: tc.id,
      name: tc.name,
      method: tc.method || 'GET',
      endpoint: tc.endpoint,
      expectedStatus: tc.expectedStatus,
      actualStatus: status,
      durationMs: duration,
      passed,
      errorMsg,
    };

    results.push(result);

    const badge = passed ? '\x1b[32m[PASS]\x1b[0m' : '\x1b[31m[FAIL]\x1b[0m';
    console.log(`${badge} ${tc.id}: ${tc.name} (${duration}ms) - Status: ${status}`);
    if (!passed && errorMsg) {
      console.log(`       Error: ${errorMsg}`);
    }
  }

  return { suite: testSuite.suite, results };
}

async function main() {
  console.log(`🚀 Starting Automated Test Execution against: ${BASE_URL}`);
  const files = fs.readdirSync(TESTCASES_DIR).filter((f) => f.endsWith('.json'));

  const allReports = [];
  let totalTests = 0;
  let totalPass = 0;

  for (const file of files) {
    const fullPath = path.join(TESTCASES_DIR, file);
    const suiteResult = await runTestSuite(fullPath);
    allReports.push(suiteResult);

    for (const r of suiteResult.results) {
      totalTests++;
      if (r.passed) totalPass++;
    }
  }

  const passRate = totalTests > 0 ? Math.round((totalPass / totalTests) * 100) : 0;
  const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
  const reportMdPath = path.join(REPORTS_DIR, `test-report-${timestamp}.md`);

  let mdContent = `# Báo Cáo Kiểm Thử Tự Động (Automated Test Report)\n\n`;
  mdContent += `- **Thời gian chạy**: ${new Date().toLocaleString('vi-VN')}\n`;
  mdContent += `- **Host kiểm thử**: ${BASE_URL}\n`;
  mdContent += `- **Tổng số testcase**: ${totalTests}\n`;
  mdContent += `- **Số lượng ĐẠT**: ${totalPass}\n`;
  mdContent += `- **Số lượng THẤT BẠI**: ${totalTests - totalPass}\n`;
  mdContent += `- **Tỷ lệ thành công**: ${passRate}%\n\n`;

  for (const suite of allReports) {
    mdContent += `### Suite: ${suite.suite}\n\n`;
    mdContent += `| Test ID | Tên Kiểm Thử | Method | Endpoint | Mong Đợi | Thực Tế | Thời Gian | Kết Quả |\n`;
    mdContent += `| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |\n`;
    for (const r of suite.results) {
      const mark = r.passed ? '✅ PASS' : '❌ FAIL';
      mdContent += `| ${r.id} | ${r.name} | ${r.method} | \`${r.endpoint}\` | ${r.expectedStatus} | ${r.actualStatus} | ${r.durationMs}ms | ${mark} |\n`;
    }
    mdContent += `\n`;
  }

  fs.writeFileSync(reportMdPath, mdContent, 'utf8');

  console.log(`\n======================================================`);
  console.log(`🏁 Tổng kết: ${totalPass}/${totalTests} tests PASSED (${passRate}%)`);
  console.log(`📄 Báo cáo chi tiết đã lưu tại: ${reportMdPath}`);
  console.log(`======================================================\n`);
}

main().catch(console.error);
