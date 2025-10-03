# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Flux Core is a LibGDX game project using the Ashley Entity Component System (ECS) framework, built with Maven and Java 17. The project uses LWJGL3 as the desktop backend.

## Build and Run Commands

**Build the project:**
```bash
mvn clean compile
```

**Run the application:**
```bash
mvn exec:java -Dexec.cleanupDaemonThreads=false
```

**Run without cleanup flag (alternative):**
```bash
mvn exec:java
```

Note: The `-Dexec.cleanupDaemonThreads=false` flag is necessary because LibGDX uses daemon threads that would otherwise be cleaned up on application exit.

## Architecture

**Main Entry Point:** `de.felixstaude.fluxcore.Main:7`
- Configures the LWJGL3 application with window settings (1280x800, VSync enabled, MSAA x4)
- Creates and launches the LibGDX application

**Application Core:** `de.felixstaude.fluxcore.FluxCore:7`
- Extends `ApplicationAdapter` (LibGDX application lifecycle)
- Currently implements basic rendering with a dark blue clear color

**Technology Stack:**
- LibGDX 1.12.1: Game development framework
- Ashley 1.7.4: Entity Component System for game object management
- LWJGL3: Desktop backend
- FreeType: Font rendering support (included for future UI development)

**Project Structure:**
- Java package: `de.felixstaude.fluxcore`
- Main class: `Main.java`
- Application class: `FluxCore.java`

## Development Notes

When working with this codebase, remember that this is an ECS-based architecture. Future game logic should be implemented using Ashley's Entity, Component, and System patterns rather than traditional object-oriented hierarchies.
