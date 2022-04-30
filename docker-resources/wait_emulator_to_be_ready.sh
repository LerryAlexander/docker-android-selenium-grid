#!/bin/bash

  boot_completed=false
  while [ "$boot_completed" == false ]; do
    statusS6=$(docker exec "$DOCKER_EMULATOR_SAMSUNG_S6" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    statusN5=$(docker exec "$DOCKER_EMULATOR_NEXUS_5" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    if [ "$statusS6" == "1" ] && [ "$statusN5" == "1" ]; then
      boot_completed=true
      echo "emulators are ready"
      echo "executing tests..."
    else
      echo "waiting for emulators to be ready"
      sleep 1
    fi
  done