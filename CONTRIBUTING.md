# Contributing to Flux Core

## Branch Naming

Use feature branches with the following naming conventions:
- `feature/*` - New features
- `fix/*` - Bug fixes
- `chore/*` - Maintenance tasks
- `refactor/*` - Code refactoring

## Commit Messages

Follow [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
type(scope): subject

[optional body]
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `chore`: Maintenance tasks
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `docs`: Documentation changes

**Examples:**
```
feat(combat): add damage calculation system
fix(rendering): correct grid line alignment
chore(ci): update GitHub Actions workflow
```

## Running Tests

Run all tests:
```bash
mvn clean verify
```

Run tests only:
```bash
mvn test
```

Run with the verify script:
```bash
./scripts/verify.sh
```

## Pull Request Process

1. Create a feature branch from `main`
2. Make your changes
3. Ensure all tests pass locally (`mvn verify`)
4. Update documentation if needed
5. Commit using conventional commit format
6. Push and create a pull request
7. Fill out the PR template checklist
