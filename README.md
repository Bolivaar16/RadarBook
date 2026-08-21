# \# RadarBook

# 

# > An extensible book aggregation and price-comparison platform built around independent bookstore providers and a normalized book domain.

# 

# \[Backend CI] \[Java 21] \[Spring Boot 4] \[PostgreSQL] \[Docker] \[MIT]

# 

# \## Overview

# 

# RadarBook aggregates book information and commercial offers from multiple external sources.

# 

# The backend is designed around three main concepts:

# 

# 1\. \*\*Books\*\* represent normalized bibliographic information.

# 2\. \*\*Offers\*\* represent store-specific commercial information.

# 3\. \*\*Providers\*\* isolate integration logic for each external bookstore.

# 

# This separation allows new sources to be introduced without coupling external integration concerns to the application's core domain.

# 

# \## Engineering Goals

# 

# RadarBook is designed around:

# 

# \* clear separation of concerns,

# \* provider-independent domain models,

# \* reproducible development environments,

# \* automated testing,

# \* incremental migration,

# \* documented architectural decisions,

# \* issue-driven development.

# 

# \## Technology

# 

# | Component                  | Technology                  |

# | -------------------------- | --------------------------- |

# | Runtime                    | Java 21                     |

# | Backend                    | Spring Boot 4.1             |

# | API                        | Spring Web                  |

# | ORM                        | Hibernate / Spring Data JPA |

# | Database                   | PostgreSQL 17               |

# | Security                   | Spring Security             |

# | Tests                      | Spring Boot Test + H2       |

# | Dependency management      | Maven                       |

# | Development infrastructure | Docker Compose              |

# | CI                         | GitHub Actions              |

# 

# \## Repository Layout

# 

# ```text

# .

# ├── .github/

# │   ├── PULL\_REQUEST\_TEMPLATE.md

# │   └── workflows/

# ├── backend/

# │   ├── src/main/java/io/github/bolivaar16/radarbook/

# │   │   ├── config/

# │   │   ├── controller/

# │   │   ├── model/

# │   │   ├── provider/

# │   │   └── repository/

# │   └── src/main/resources/

# ├── compose.yaml

# ├── docs/

# └── README.md

# ```

# 

# \## Architecture

# 

# ```text

# ┌─────────────────────┐

# │ External Bookstores │

# └──────────┬──────────┘

# &#x20;          │

# &#x20;          ▼

# &#x20;     ┌───────────┐

# &#x20;     │ Providers │

# &#x20;     └─────┬─────┘

# &#x20;           │

# &#x20;           ▼

# &#x20;      Application

# &#x20;           │

# &#x20;      ┌────┴────┐

# &#x20;      ▼         ▼

# &#x20;    Books     Offers

# &#x20;      │         │

# &#x20;      └────┬────┘

# &#x20;           ▼

# &#x20;      Repositories

# &#x20;           │

# &#x20;           ▼

# &#x20;       PostgreSQL

# ```

# 

# This README intentionally provides only an architectural overview.

# 

# See `docs/ARCHITECTURE.md` and the ADR directory for detailed decisions.

# 

# \## Local Development

# 

# \### Requirements

# 

# \* Java 21

# \* Git

# \* Docker with Docker Compose

# 

# \### Clone

# 

# ```bash

# git clone https://github.com/Bolivaar16/RadarBook.git

# cd RadarBook

# ```

# 

# \### Infrastructure

# 

# Start PostgreSQL:

# 

# ```bash

# docker compose up -d

# ```

# 

# Check its status:

# 

# ```bash

# docker compose ps

# ```

# 

# Stop it:

# 

# ```bash

# docker compose down

# ```

# 

# Delete the local database volume:

# 

# ```bash

# docker compose down -v

# ```

# 

# > Removing the volume permanently deletes the local development database.

# 

# \### Backend

# 

# ```bash

# cd backend

# ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# ```

# 

# On Windows:

# 

# ```powershell

# .\\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev

# ```

# 

# \## Development Configuration

# 

# The development profile uses PostgreSQL running through Docker Compose.

# 

# Default local database connection:

# 

# ```text

# Host: 127.0.0.1

# Port: 5433

# Database: radarbook

# Username: radarbook

# ```

# 

# Configuration can be overridden through:

# 

# ```text

# DB\_URL

# DB\_USERNAME

# DB\_PASSWORD

# ```

# 

# \## Tests

# 

# ```bash

# cd backend

# ./mvnw test

# ```

# 

# GitHub Actions executes the backend test suite automatically against changes to `main`.

# 

# \## API

# 

# The REST API is under active development.

# 

# Planned core resources include:

# 

# ```text

# /api/books

# /api/books/{id}

# /api/books/search

# ```

# 

# The definitive endpoint contract will be documented as the API stabilizes.

# 

# \## External Providers

# 

# RadarBook external integrations implement a common provider boundary.

# 

# Provider responsibilities include:

# 

# \* communicating with an external source,

# \* parsing provider-specific data,

# \* translating results into RadarBook DTOs,

# \* avoiding leakage of provider-specific concerns into domain entities.

# 

# \## Roadmap

# 

# \* \[x] Spring Boot foundation

# \* \[x] Domain migration

# \* \[x] Persistence layer

# \* \[x] PostgreSQL environment

# \* \[x] CI

# \* \[ ] Provider migration

# \* \[ ] Service layer

# \* \[ ] REST API

# \* \[ ] Normalization pipeline

# \* \[ ] Frontend rebuild

# \* \[ ] Authentication

# \* \[ ] Wishlists

# \* \[ ] Additional providers

# \* \[ ] Production deployment

# 

# Implementation is tracked through \[GitHub Issues](https://github.com/Bolivaar16/RadarBook/issues).

# 

# \## Engineering Workflow

# 

# RadarBook uses:

# 

# \* GitHub Issues for units of work

# \* focused branches

# \* Conventional Commits

# \* pull requests

# \* GitHub Actions

# \* ADRs for significant architectural decisions

# 

# ```text

# issue → branch → commits → pull request → CI → main

# ```

# 

# \## Documentation

# 

# Project documentation is progressively being moved into `/docs`.

# 

# Planned documentation includes:

# 

# ```text

# docs/

# ├── ARCHITECTURE.md

# └── adr/

# ```

# 

# The README remains focused on project discovery and local development.

# 

# \## Contributing

# 

# Contributions are welcome.

# 

# Before submitting a pull request:

# 

# \* associate the change with an issue when possible,

# \* keep the PR focused,

# \* follow Conventional Commits,

# \* add appropriate tests,

# \* verify the backend CI locally where practical.

# 

# \## Project History

# 

# RadarBook originated as a university group project.

# 

# The current version is an independent modernization of that application, undertaken to evolve its architecture, replace legacy components incrementally and apply modern software engineering techniques beyond the requirements of the original project.

# 

# \## Status

# 

# \*\*Active development / incremental modernization.\*\*



# The repository temporarily contains both legacy components and their modern replacements while migration is in progress.

# 

# \## License

# 

# MIT License.

# 

# \## Author

# 

# \*\*Alejandro Bolívar\*\*

# 

# GitHub · LinkedIn

