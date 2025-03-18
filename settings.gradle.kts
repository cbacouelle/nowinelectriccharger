pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "nowinelectriccharger"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:chargers:ui")
include(":feature:chargers:data")
include(":feature:chargers:domain")
include(":feature:search:ui")
include(":feature:search:domain")
include(":feature:search:data")
include(":network")
include(":theme")
include(":core:ui")
include(":core:datastore-proto")
include(":core:datastore")
include(":core:utils")
include(":location:data")
include(":location:domain")
include(":feature:location:domain")
include(":feature:location:data")
include(":core:model")
include(":core:common")
