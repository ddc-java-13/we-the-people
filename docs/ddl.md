---
title: Entity Relationship Diagram
subtitle: "Relationship diagram between data entities"
menu: ERD
order: 60
---

```sqlite
CREATE TABLE IF NOT EXISTS `law_or_bill`
(
    `law_or_bill_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `external_key`   TEXT                              NOT NULL,
    `creation_date`  INTEGER                           NOT NULL,
    `author`         TEXT                              NOT NULL COLLATE NOCASE,
    `agency`         TEXT                              NOT NULL COLLATE NOCASE,
    `signers`        TEXT                              NOT NULL COLLATE NOCASE,
    `type`           INTEGER                           NOT NULL
);
CREATE INDEX IF NOT EXISTS `index_law_or_bill_creation_date` ON `law_or_bill` (`creation_date`);
CREATE INDEX IF NOT EXISTS `index_law_or_bill_author` ON `law_or_bill` (`author`);
CREATE INDEX IF NOT EXISTS `index_law_or_bill_agency` ON `law_or_bill` (`agency`);
CREATE INDEX IF NOT EXISTS `index_law_or_bill_signers` ON `law_or_bill` (`signers`);
CREATE INDEX IF NOT EXISTS `index_law_or_bill_type` ON `law_or_bill` (`type`);
```

[`ddl.sql`](sql/ddl.sql)