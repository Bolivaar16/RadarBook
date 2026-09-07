# radarbook

Book price comparator. Portfolio project with a closed goal and a date (see the vault); not a
client project. Scope, the goal, decisions and state live in the Mini Boli vault:
`~/Mini Boli/20-proyectos/radarbook/`. Do not write any of that here.

## Layout and commands

- `backend/` — the live code: Spring Boot, Java 21, JPA. Verify with
  `cd backend && ./mvnw -B test` (what `.github/workflows/backend-ci.yml` runs).
- Root `pom.xml`, `src/main/java`, `src/main/webapp` — the **legacy** webapp, kept for history.
  Changes go to `backend/` unless the ticket says otherwise.
- `compose.yaml` — local database.

## Conventions that are not obvious

- API keys (Rainforest, ScrapingDog) are read from the environment since
  `fix/remove-hardcoded-api-key`; the old literal keys were revoked on 2026-09-07. A key in a
  source file or a properties file is a bug, not a convenience.
- `backend/src/main/resources/application-dev.properties` is versioned: only local development
  values belong there, never anything that also works against a real service.
- The repository is **public** on purpose (portfolio). Everything committed is readable by
  anyone; write accordingly.
- Commits follow `type(scope): subject`, e.g. `fix(model): …`, `test(model): …`.

## Do not

- Push, open or merge PRs without Alex.
- Add a `.env.example`-less configuration key: if the app needs a variable, document it.
- Recreate `memory/` here (see `KNOWLEDGE.md`).
