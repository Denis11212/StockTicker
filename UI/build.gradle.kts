plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.detekt.plugin)
}

detekt {
  toolVersion = libs.versions.detekt.get()
  config.setFrom(file("../config/detekt/detekt.yml"))
  buildUponDefaultConfig = true
  autoCorrect = true
}

buildscript {
  repositories {
    mavenCentral()
    google()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
  }
  dependencies {
    classpath(kotlin("gradle-plugin", version = "2.0.0"))
  }
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  maven("https://jitpack.io")
  maven("https://maven.google.com")
}

android {
  namespace = "com.github.premnirmal.tickerwidget.ui"
  compileSdk = 36

  defaultConfig {
    minSdk = 26
    targetSdk = 36

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
    }
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.8"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation(AndroidX.core.ktx)
  implementation(AndroidX.appCompat)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.material3)

  implementation(AndroidX.compose.runtime)
  implementation(AndroidX.compose.runtime.liveData)
  implementation(AndroidX.navigation.compose)

  testImplementation(Testing.junit4)
}