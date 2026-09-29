$Container = "codeforge-redpanda"
$ProbeTopic = "execution.nonexistent.verify"

Write-Host "======================================"
Write-Host "1. LIST TOPICS"
Write-Host "======================================"

docker exec $Container rpk topic list

Write-Host ""
Write-Host "======================================"
Write-Host "2. DESCRIBE execution.requested"
Write-Host "======================================"

docker exec $Container rpk topic describe execution.requested

Write-Host ""
Write-Host "======================================"
Write-Host "3. DESCRIBE execution.dlq"
Write-Host "======================================"

docker exec $Container rpk topic describe execution.dlq

Write-Host ""
Write-Host "======================================"
Write-Host "4. VERIFY NON-EXISTENT TOPIC"
Write-Host "======================================"

Write-Host "Attempting to produce to: $ProbeTopic"

"verification" | docker exec -i $Container rpk topic produce $ProbeTopic

$Status = $LASTEXITCODE

if ($Status -eq 0) {
    Write-Host ""
    Write-Host "FAIL: Producing to a non-existent topic succeeded." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "Produce command failed as expected."

Write-Host ""
Write-Host "Checking that the probe topic was NOT created..."

$Topics = docker exec $Container rpk topic list

if ($Topics -match [regex]::Escape($ProbeTopic)) {
    Write-Host "FAIL: $ProbeTopic was created unexpectedly." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "======================================"
Write-Host "PASS"
Write-Host "======================================"
Write-Host "Non-existent topic correctly rejected." -ForegroundColor Green