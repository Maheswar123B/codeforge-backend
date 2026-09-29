$Container = "codeforge-redpanda"

$topics = @(
    @{
        Name = "execution.requested"
        Partitions = 3
        Retention = "3600000"
    },
    @{
        Name = "execution.started"
        Partitions = 3
        Retention = "3600000"
    },
    @{
        Name = "execution.output"
        Partitions = 3
        Retention = "3600000"
    },
    @{
        Name = "execution.completed"
        Partitions = 3
        Retention = "3600000"
    },
    @{
        Name = "execution.stats"
        Partitions = 3
        Retention = "3600000"
    },
    @{
        Name = "execution.dlq"
        Partitions = 1
        Retention = "86400000"
    }
)

Write-Host "======================================"
Write-Host "Creating CodeForge Kafka topics"
Write-Host "======================================"

foreach ($topic in $topics) {

    $name = $topic.Name
    $partitions = $topic.Partitions
    $retention = $topic.Retention

    Write-Host ""
    Write-Host "Checking topic: $name"

    $existingTopics = docker exec $Container rpk topic list 2>$null

    if ($existingTopics -match "(?m)^$([regex]::Escape($name))\s") {

        Write-Host "Topic already exists: $name"

        # Get existing partition count
        $describe = docker exec $Container rpk topic describe $name 2>&1
        $describeText = $describe -join "`n"

        if ($describeText -notmatch "PARTITIONS\s+$partitions") {
            Write-Host "ERROR: $name exists with an unexpected partition count." -ForegroundColor Red
            Write-Host "Expected partitions: $partitions" -ForegroundColor Yellow
            exit 1
        }

        Write-Host "Partition count is correct: $partitions"
        continue
    }

    Write-Host "Creating $name..."

    docker exec $Container rpk topic create $name `
        --partitions $partitions `
        --topic-config "retention.ms=$retention"

    if ($LASTEXITCODE -ne 0) {
        Write-Host "ERROR: Failed to create $name" -ForegroundColor Red
        exit 1
    }

    Write-Host "Created: $name" -ForegroundColor Green
}

Write-Host ""
Write-Host "======================================"
Write-Host "Final topic list"
Write-Host "======================================"

docker exec $Container rpk topic list

Write-Host ""
Write-Host "Topic setup completed."