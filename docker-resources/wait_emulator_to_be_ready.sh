#!/bin/bash

  boot_completed=false
  while [ "$boot_completed" == false ]; do
    statusPhone=$(docker exec "$DOCKER_EMULATOR_PHONE" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    statusTablet=$(docker exec "$DOCKER_EMULATOR_TABLET" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    if [ "$statusPhone" == "1" ] && [ "$statusTablet" == "1" ]; then
      boot_completed=true
      echo "devices are ready !!"
      echo "executing tests..."
    else
      echo "waiting for devices to be ready"
      sleep 1
    fi
  done