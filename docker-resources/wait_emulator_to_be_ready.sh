#!/bin/bash

  boot_completed=false
  while [ "$boot_completed" == false ]; do
    echo "validate estatus s6"
    statusS6=$(docker exec "$DOCKER_EMULATOR_SAMSUNG_S6" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    echo "validate estatus s10"
    statusS10=$(docker exec "$DOCKER_EMULATOR_SAMSUNG_S10" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    if [ "$statusS6" == "1" ] && [ "$statusS10" == "1" ]; then
      boot_completed=true
      echo "emulators are ready"
      echo "executing tests..."
    else
      echo "waiting for emulators to be ready"
      sleep 1
    fi
  done