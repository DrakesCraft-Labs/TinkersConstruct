# Porting route: Minecraft 1.21.11 / NeoForge

This is the work plan and acceptance contract for the DrakesCraft fork. A green Gradle configuration is only the first gate; the port is not complete until the gameplay, resources, data, saves, and dedicated server have been exercised.

## Baseline and target

- Source baseline: upstream Tinkers' Construct 1.20.1, Forge 47.x (`1.20.1` branch).
- Target: Minecraft 1.21.11, NeoForge 21.11.x, Java 21, NeoGradle.
- Keep `tconstruct`, registry names, serialized data, and resource namespaces stable unless a migration is explicitly designed and tested.
- Upstream source and assets are MIT-licensed. Preserve upstream copyright/license notices and identify this as an unofficial port; do not imply upstream endorsement.
- Upstream's published FAQ currently says it does not authorize platform/version ports while the project remains actively maintained. Treat public binary distribution as gated on written upstream clarification/permission, even though local porting and this source fork are being prepared.

## Work packages and gates

### 0. Build foundation — in progress

- [x] Establish a dedicated `codex/port-1.21.11-neoforge` branch, isolated from the existing checkout's local README deletion.
- [x] Record this fork's scope, attribution, target and non-release status.
- [x] Verify wrapper/bootstrap with JDK 21 and the official NeoForge MDK versions (`./gradlew tasks` succeeds).
- [ ] Run the original 1.20.1 build separately as a reproducible baseline (JDK 17); capture the baseline result before API edits.

### 1. Mantle target first — blocked on a matching port

- [ ] Port Mantle to the exact same Minecraft/NeoForge target before moving dependent Tinkers APIs.
- [ ] Record Mantle API and data changes; validate serialization and registries.
- [ ] Decide packaging with both mods' maintainers and license notices in view.

The DrakesCraft Mantle repository currently has a `1.21.X` line and a Stonecutter/NeoGradle layout; its checked-out metadata does not establish a compatible 1.21.11 artifact. Do not point Tinkers at a 1.21.1 JAR or silently widen dependency ranges.

### 2. Loader and source migration

- [ ] Replace ForgeGradle / Forge run configurations with NeoGradle 7.x and NeoForge 21.11.x.
- [ ] Move metadata to `META-INF/neoforge.mods.toml`; replace `forge` dependency metadata with the correct NeoForge declarations.
- [ ] Migrate Forge event buses, registries, capabilities, networking, data generation, mixins/access transformers and client hooks, in small reviewable groups.
- [ ] Keep optional integrations (JEI, Immersive Engineering, JSON Things, etc.) disabled until a maintained NeoForge 1.21.11 artifact is verified for each one.
- [ ] Resolve compilation failures by subsystem, not by mass textual replacement. Document each changed behavior/API.

### 3. Content and save compatibility

- [ ] Compare generated registries, recipes, tags, loot, advancements, item/block/entity definitions and language/model/texture assets against the source baseline.
- [ ] Add data migration/codec coverage for changed Minecraft serialization APIs.
- [ ] Test clean worlds and copies of representative 1.20.1 worlds. Never use production worlds for migration tests.
- [ ] Verify materials, tools, modifiers, stations, fluids, entities, structures, recipes and JEI/EMI-facing behavior.

### 4. Runtime, QA, and release gate

- [ ] `./gradlew clean check build` with JDK 21; retain logs and artifact hashes.
- [ ] Launch client and dedicated server, verify fresh startup/shutdown, world save/reopen, datagen and no new severe exceptions.
- [ ] Add automated tests for ported behavior and any migration code.
- [ ] Obtain upstream clarification before public binary distribution; publish port-specific release notes, source commit, dependencies, checksum, and known issues.
- [ ] Only then mark the port release-ready. No server deployment is included in this task.

## Mantle packaging decision

“Shade Mantle into Tinkers” can mean two very different things:

1. **Jar-in-jar** keeps Mantle as its own mod/library artifact inside the Tinkers JAR. It still has separate mod metadata and classloading semantics, does not necessarily remove Mantle as a user-visible required mod, and needs NeoForge JarJar/version-range verification. It is not equivalent to merging Mantle into Tinkers.
2. **Source/module integration** compiles Mantle code into the Tinkers artifact. This removes the separate runtime JAR only if packages, registries, metadata, license notices, and public APIs are deliberately reconciled. It risks class/API conflicts and breaks other mods compiled against `slimeknights.mantle`; it also makes Mantle fixes/releases coupled to Tinkers.

Preferred investigation order:

1. Build a matching Mantle 1.21.11 artifact and test the normal hard dependency first. This is the safest compatibility baseline.
2. Inventory Mantle's public API, runtime classes, mod metadata, registered content, resources, entrypoints, and license/copyright files.
3. Prototype JarJar separately and verify NeoForge loading, dependency declarations, duplicate class behavior, client/server behavior, and other mods' ability to resolve Mantle classes.
4. Consider source integration only after explicit maintainer agreement and a migration plan for mods using Mantle's API. Preserve all required MIT notices and avoid duplicate runtime copies.

Do not remove Mantle's required-mod declaration or claim “no Mantle JAR required” until a clean instance loads Tinkers and a representative Mantle API consumer without a separately installed Mantle, on both client and dedicated server.

## Current caveats

- This branch still contains the original Forge 1.20.1 Java source. `compileJava` was run against the NeoForge 1.21.11 workspace and fails as expected: the current snapshot has roughly 2,200 `ResourceLocation` references that need a semantic migration to the target `Identifier` API, and compilation lacks a matching Mantle 1.21.11 API. These are migration tasks, not a passing build.
- The first `compileJava` run completed NeoGradle's initial Minecraft setup/decompilation, so subsequent diagnostics should be faster. The current error set is only the first javac batch; it is not a complete inventory of source incompatibilities.
- Mantle is deliberately not bundled or declared as a working compile dependency. The metadata has a fail-closed placeholder range so this WIP cannot accidentally load against a mismatched Mantle JAR. Replace it only after a matching Mantle port passes its own tests.
- A Gradle `tasks` or configuration success does not imply a 1.21.11 mod exists.
- No JAR produced from this branch is suitable for players or production.
