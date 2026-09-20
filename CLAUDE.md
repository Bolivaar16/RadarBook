# radarbook

Book price comparison backend (Java 21, Spring Boot 4) kept as a portfolio project with a closed
goal. Business context, scope, decisions and state live in the Mini Boli vault:
`~/Mini Boli/20-proyectos/radarbook/`. Do not write any of that here.

## Commands

- Build and verify (must pass before anything is committed): `cd backend && ./mvnw -B test`
- Run: `cd backend && ./mvnw spring-boot:run` (PostgreSQL via `compose.yaml`)

## Conventions that are not obvious

- `backend/` is the real application; `src/` is the 2024 JSP prototype, kept for reference and
  not built by CI. Do not extend `src/`.
- API keys are read from the environment (`RAINFOREST_API_KEY`, `SCRAPINGDOG_API_KEY`,
  `DB_PASSWORD`). The repository is public: never write a value in code, properties or tests.
- The keys that once lived in the history were revoked on 2026-09-07. That is closed; do not
  report them as a finding (see the vault state note).

## Do not

- Merge without Alex. Feature-branch pushes and PR creation for authorized work are allowed.
- Change the repository visibility or rewrite history.
- Recreate `memory/`, `DECISIONS.md` or state notes here (see `KNOWLEDGE.md`).
