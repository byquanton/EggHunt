import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
  `java-library`
  id("xyz.jpenilla.run-paper") version "1.0.6"
  id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
  id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "me.byquanton.plugin"
version = "1.0.0-SNAPSHOT"
description = "Plugin Template"

repositories {
  mavenCentral()
  maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
  compileOnly("io.papermc.paper","paper-api","1.18.2-R0.1-SNAPSHOT")
  implementation("cloud.commandframework", "cloud-paper", "1.6.1")
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

bukkit {
  main = "me.byquanton.plugin.egghunt.EggHuntPlugin"
  apiVersion = "1.18"
  load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
  authors = listOf("byquanton","Laazuli")
}

tasks {
  shadowJar {
    fun reloc(pkg: String) = relocate(pkg, "me.byquanton.plugin.egghunt.dependency.$pkg")

    reloc("cloud.commandframework")
    reloc("io.leangen.geantyref")
  }

  runServer{
    minecraftVersion.set("1.18.2")
  }

}
