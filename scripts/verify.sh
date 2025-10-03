#!/bin/bash
set -e

echo "Running Maven verify..."
mvn -q -DskipTests=false clean verify

echo ""
echo "✓ All tests passed!"
