<p align="center">
  <a href="https://github.com/DrakesCraft-Labs/TinkersConstruct/tree/codex/port-1.21.11-neoforge">
    <img src="banner.svg" alt="Tinkers' Construct — DrakesCraft port for Minecraft 1.21.11" width="100%">
  </a>
</p>

<h1 align="center">Tinkers' Construct · DrakesCraft Port</h1>
<p align="center"><b>A new forge for Minecraft 1.21.11</b><br>An unofficial NeoForge porting project based on the original work by SlimeKnights.</p>

<p align="center">
  <img alt="Minecraft 1.21.11" src="https://img.shields.io/badge/Minecraft-1.21.11-55d9c8?style=for-the-badge&labelColor=191629">
  <img alt="NeoForge" src="https://img.shields.io/badge/Loader-NeoForge-c9a45a?style=for-the-badge&labelColor=191629">
  <img alt="Work in progress" src="https://img.shields.io/badge/Status-Work_in_progress-bb8df2?style=for-the-badge&labelColor=191629">
  <img alt="MIT License" src="https://img.shields.io/badge/License-MIT-e5dfd0?style=for-the-badge&labelColor=191629">
</p>

<p align="center">
  <a href="PORTING_1.21.11.md">🛠️ Porting roadmap</a> ·
  <a href="modrinth-icon.svg">⬡ Modrinth icon</a> ·
  <a href="https://github.com/SlimeKnights/TinkersConstruct">↗ Upstream project</a>
</p>

---

> [!WARNING]
> **This port is not playable or release-ready.** The branch is an active migration workspace; do not install or distribute a JAR from it. Build configuration is in place, but the source migration and matching Mantle dependency are not complete.

## The project

Tinkers' Construct is a tool-building and material-customization mod created and maintained by **SlimeKnights**. This DrakesCraft fork prepares a port to **Minecraft 1.21.11 + NeoForge**, aiming to retain the original content, identifiers, and world data wherever the new game APIs allow it.

| Workstream | Current state |
| --- | --- |
| Organization fork | Ready — based on `SlimeKnights/TinkersConstruct` |
| NeoForge 1.21.11 workspace | Bootstrapped; Gradle & decompilation verified (6,623 vanilla classes) |
| Mantle for 1.21.11 | Linked via `mavenLocal` & multi-version Stonecutter target active |
| Bundled/shaded Mantle | Supported via NeoForge `jarJar` (standalone all-in-one JAR target) |
| Tinkers source/API migration | In progress — migrating Forge to NeoForge 21.11 namespaces |
| Playable build or release | Active development |

Follow the detailed [porting roadmap](PORTING_1.21.11.md) for migration phases, test gates, and the Mantle packaging analysis.

## Build workspace

**Requirements:** JDK 21 · Gradle 9.2.1 (wrapper) · NeoForge 21.11.45

```bash
./gradlew tasks
```

This checks that Gradle and NeoGradle configure. It does **not** compile the mod or mean the port is usable. `compileJava` is currently expected to fail until the Minecraft API and Mantle migrations are complete. No production deployment is part of this project.

## License, attribution & project identity

This repository contains work derived from [SlimeKnights/TinkersConstruct](https://github.com/SlimeKnights/TinkersConstruct). Its upstream source and assets are MIT-licensed. The upstream copyright and license notices are retained; the original DrakesCraft `banner.svg` and `modrinth-icon.svg` artwork is also MIT-licensed and separately attributed in [`LICENSE`](LICENSE).

This is an **unofficial community porting effort** and is not endorsed by SlimeKnights. Preserve upstream attribution and the MIT license when redistributing code or substantial portions. No binaries are published from this work-in-progress branch.

<p align="center"><sub>Forged with care by DrakesCraft Labs · Built on the work of SlimeKnights</sub></p>
