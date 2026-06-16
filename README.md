# Hex Template

[![powered by hexdoc](https://img.shields.io/endpoint?url=https://hexxy.media/api/v0/badge/hexdoc?label=1)](https://github.com/hexdoc-dev/hexdoc)

This is an up-to-date, opinionated template for starting a **multiloader, Kotlin-based** Hex Casting addon on 1.21.1 with Cloche. Includes all necessary dependencies on both Neoforge and Fabric loaders, plus some demo bits. 

This template is based on [HexDummy](https://github.com/FallingColors/hexdummy#usage) by FallingColors

> [!CAUTION]
> **Do not fork or clone this repository** to set up a new mod! See the usage steps and FAQ for more details.

> [!TIP]
> If you're writing a Fabric-only or Forge-only mod, you probably don't need this template. Consider using the [Fabric template mod generator](https://fabricmc.net/develop/template/) or [Neoforge MDK](https://docs.neoforged.net/docs/1.21.1/gettingstarted/) instead.

## Usage

1. Install the prerequisites:
   - [Git](https://github.com/git-guides/install-git)
   - [uv](https://docs.astral.sh/uv/getting-started/installation/)
   - Any IDE with Java and Kotlin support (recommended: [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/?section=windows))
2. Create, clone, and enter a **new** GitHub repo. You don't need to use any other project templates (e.g. IntelliJ's Minecraft mod template) with Hex Template.
> [!CAUTION]
> **Do not** fork/clone/copy this repo directly.
3. From the repo root, run this command to copy the template, then follow the prompts to set it up:
   ```sh
   uvx copier copy gh:Master-Bw3/Hex-Casting-1.21.1-Addon-Template .
   ```
4. Set up your Python environment and lockfile:
   ```sh
   uv sync

   .\.venv\Scripts\activate   # Windows
   . .venv/bin/activate.fish  # fish
   source .venv/bin/activate  # everything else
   ```
5. Look through the generated project to make sure everything looks good, then add, commit, and push the generated files (including `uv.lock`):
   ```
   git add .
   git update-index --chmod=+x gradlew
   git commit -m "Set up mod template"
   git push
   ```
6. Follow the [hexdoc setup instructions](https://hexdoc.hexxy.media/docs/guides/deployment/github-pages) for GitHub Pages.

## Resources

See the following branches for up-to-date example projects generated using Hex Template:

- https://github.com/Master-Bw3/Hex-Casting-1.21.1-Addon-Template/tree/example

The following repository contains an example of a project generated using Hex Template, including GitHub Actions workflow runs and a hexdoc web book:

- TODO

## FAQ

### Why isn't this a template repository / why shouldn't I fork this repo to set up my mod?

Hex Template uses [Copier](https://copier.readthedocs.io), a Python app for generating projects from *parameterized* templates. When you run `uvx copier copy`, Copier clones this repository, prompts you for some values, then fills those values into many placeholders in the template, making a customized project with no placeholder values for you to have to replace by hand.

As a consequence, this repository by itself is *not* a working Minecraft mod - if you look in the [template](./template) directory, you'll see a lot of `.jinja` files with placeholders that still need to be filled in.

### How do I add dependencies?

- Maven repositories: `build.gradle.kts`
- Dependency definitions: `libs.versions.toml` ([Gradle docs](https://docs.gradle.org/current/userguide/version_catalogs.html#sec::toml-dependencies-format))
- Gradle dependency configurations: `build.gradle.kts -> cloche -> (common/fabric/neoforge)` (depending which platform you need it on)
- hexdoc dependencies: `pyproject.toml` and `doc/hexdoc.toml` (remember to run `uv sync` to update `uv.lock` after changing)
- Local JAR dependency files: `libs/`
- Maven repositories for Gradle plugins: `settings.gradle.kts`


### What's a (ModName)Registrar?

Hex Template generates a Registrar base class that you can use to register things in all registries, vanilla or modded, for all platforms.

To add a registrar for a registry, [create an `object` subclassing the registrar](https://github.com/object-Object/HexDummyExample/blob/a7d8d5b58bd018a62456e4653fe46ba3eb22bc69/common/src/main/kotlin/io/github/objectobject/hexdummyexample/registry/HexdummyexampleActions.kt#L11-L14), and pass  the ResourceKey of the registry and a callback that returns the actual registry to the superclass. (A callback is used to avoid initializing the registry too early on Neoforge.) Add the subclass to the [initRegistries call](https://github.com/object-Object/HexDummyExample/blob/a7d8d5b58bd018a62456e4653fe46ba3eb22bc69/common/src/main/kotlin/io/github/objectobject/hexdummyexample/Hexdummyexample.kt#L21) in your common mod initializer.

Registering things with the Registrar generally looks like this:

```kt
val REGISTERED_THING = register("registered_thing") { ThingConstructor("parameters") }
```

`register` takes a callback for the same reason as the registry object: to avoid constructing the instance too early on Forge. The string is used as the path for a ResourceLocation, with your mod id as the namespace.

The value returned by `register` is an [`Entry<V>`](https://github.com/object-Object/HexDummyExample/blob/a7d8d5b58bd018a62456e4653fe46ba3eb22bc69/common/src/main/kotlin/io/github/objectobject/hexdummyexample/registry/HexdummyexampleRegistrar.kt#L50). You can use this to access the registered thing's id (`.id`) or ResourceKey (`.key`), and when you're sure registration has finished, the actual registered instance (`.value`).

For a more concrete example, the template comes with an [example for registering patterns](https://github.com/Master-Bw3/Hex-Casting-1.21.1-Addon-Template/blob/example/src/common/main/kotlin/io/github/example/hextemplate/registry/HexTemplateActions.kt), and HexDebug has [several more examples](https://github.com/object-Object/HexDebug/tree/5db5f78f5489863e2c20a3df472571a984877b7d/Common/src/main/kotlin/gay/object/hexdebug/registry) for various types of registries.

## TODO

- Set up Mod Publish
- Set up Github Actions Workflows
- See if there's a better way to download dependencies for CI tests than just manually listing Modrinth URLs.
- Improve documentation.
- Investigate https://github.com/headlesshq/mc-server-test more. From the logs, it seems like the Fabric server test might not actually be loading any mods?
- Fix the CI tests in this repository.
- Figure out how to publish to CurseForge/Modrinth/GitHub without having to build the mod again.
