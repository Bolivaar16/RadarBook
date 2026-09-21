# RadarBook

Book price comparison across online stores: given an ISBN, find the offers and show the cheapest.
Backend portfolio project (Java 21, Spring Boot 4) with a closed goal; business context, decisions
and state live in the Mini Boli vault (`~/Mini Boli/20-proyectos/radarbook/`).

## Status

- **Portfolio with a closed goal, currently `algun-dia`** in the vault: no active work. The goal
  and the archive date are recorded in the vault decision `radarbook-portfolio-con-meta-cerrada`.
- **Public repository.** The Rainforest and ScrapingDog API keys that used to be hard-coded in
  `src/main/java/radarbook/` were **revoked on 2026-09-07** and replaced by environment variables
  (`RAINFOREST_API_KEY`, `SCRAPINGDOG_API_KEY`). The old strings remain in git history as dead
  values; this is known and closed.
- `backend/` is the Spring Boot rewrite; `src/` is the 2024 JSP prototype kept for reference.

## Run and verify

```sh
cd backend
./mvnw -B test        # what CI runs (.github/workflows/backend-ci.yml)
./mvnw spring-boot:run
```

Database settings come from `application-{dev,prod}.properties`; `DB_PASSWORD` is read from the
environment (the dev profile has a local-only default for Docker).

## Layout

- `backend/` — Spring Boot application, tests in `backend/src/test/`.
- `src/` — legacy JSP prototype (2024), not built by CI.
- `compose.yaml` — local PostgreSQL for development.

See `CLAUDE.md` for working conventions and `KNOWLEDGE.md` for where knowledge lives.
