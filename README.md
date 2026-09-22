# Tinkers' Construct — DrakesCraft 1.21.11 port

<p align="center">
  <img src="banner.svg" alt="Tinkers' Construct — DrakesCraft port for Minecraft 1.21.11" width="100%">
</p>

> **Work in progress.** This repository is an unofficial DrakesCraft-led porting workspace based on SlimeKnights' Tinkers' Construct. It is not a playable or release-ready 1.21.11 build yet. Do not install artifacts from this branch on a server.

The aim is to port Tinkers' Construct to **Minecraft 1.21.11 on NeoForge**, preserve its gameplay and save compatibility where technically possible, and make the Mantle packaging decision explicit and testable.

## Port status

| Area | Status |
| --- | --- |
| DrakesCraft organization fork | Created from `SlimeKnights/TinkersConstruct` |
| 1.21.11 / NeoForge build bootstrap | Initial workspace configuration |
| Existing Tinkers source migration | Not complete |
| Mantle 1.21.11 port | Required; current DrakesCraft Mantle line targets an earlier 1.21 release |
| Tinkers + Mantle shaded/integrated artifact | Design and license/classloader validation pending |
| Playable build, tests, release | Not available |

See [PORTING_1.21.11.md](PORTING_1.21.11.md) for the phased route, acceptance gates, and Mantle packaging analysis.

## Development setup

- JDK 21
- Gradle wrapper (Gradle 9.2.1)
- NeoForge 21.11.45 / Minecraft 1.21.11 workspace

```sh
./gradlew tasks
```

The first NeoForge setup may need to download and prepare Minecraft artifacts. A successful `tasks`/configuration run only validates the build bootstrap; it does **not** mean the mod source has been ported. Do not treat this branch as buildable gameplay until `compileJava`, tests, client/server smoke tests, and migration checks pass.

## Upstream and license

This work is derived from [SlimeKnights/TinkersConstruct](https://github.com/SlimeKnights/TinkersConstruct), authored and maintained upstream by SlimeKnights. The upstream project is MIT-licensed; retain its copyright and license notices in redistributed copies and substantial portions. This fork is unofficial and is not endorsed by SlimeKnights.

Upstream project: [Tinkers' Construct](https://github.com/SlimeKnights/TinkersConstruct) · [Mantle](https://github.com/SlimeKnights/Mantle)

## Scope and safety

- Keep the upstream mod ID and data namespaces unchanged unless a compatibility review explicitly approves a change.
- Do not publish a mod file or announce compatibility until the port passes the gates in the porting guide.
- No production server deployment is part of this repository task.
